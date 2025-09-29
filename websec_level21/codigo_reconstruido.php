<?php
// CÓDIGO RECONSTRUIDO A PARTIR DE LOS SOURCES

// === INDEX.PHP ===
include "flag.php";
include "utils.php";
include "session_management.php";

if (isset($_POST['username']) and isset($_POST['password'])) {
    if (isset($_POST['register'])) {
        $message = register($_POST['username'], $_POST['password']);
    } elseif (isset($_POST['login'])) {
        $message = login($_POST['username'], $_POST['password']);
    }
}

if (isset($_COOKIE['session'])) {
    $user_type = auth($_COOKIE['session'], $aes_key);
    echo "You are: " . ($user_type === "admin" ? 'admin' : 'user');
    
    if ($user_type === "admin") {
        echo 'Hello admin, here is your flag: ' . $flag;
        // Luego borra el usuario de esta IP
        $pdo = new SQLite3('database.db', SQLITE3_OPEN_READWRITE);
        $stmt = $pdo->prepare('DELETE FROM users WHERE ip = ":ip";');
        $stmt->bindValue(':ip', $_SERVER['REMOTE_ADDR'], SQLITE3_TEXT);
        $stmt->execute();
    } else {
        echo 'You need to be admin to see the flag';
    }
}

// === SESSION_MANAGEMENT.PHP ===
function login($username, $password) {
    global $aes_key;
    $pdo = new SQLite3('database.db', SQLITE3_OPEN_READONLY);
    $stmt = $pdo->prepare('SELECT * FROM users WHERE username=:username AND password=:password;');
    $stmt->bindValue(':username', $username, SQLITE3_TEXT);
    $stmt->bindValue(':password', md5($password), SQLITE3_TEXT);
    $row = $stmt->execute()->fetchArray(SQLITE3_ASSOC);
    $pdo->close();
    
    if ($row) {
        create_cookies($row['username'], $row['password'], $aes_key);
        return ['success', 'Login successful'];
    }
    return ['danger', 'Wrong user or password.'];
}

function register($username, $password) {
    if (!ctype_alnum($username))
        return ["danger", "Your nick isn't alphanumeric."];
    if (strlen($username) < 6)
        return ["danger", "Your nick is too small"];
    if (preg_match('/(a|d|m|i|n)/', strtolower($username)))
        return ["danger", "Your nick is matching [admin]."];
    
    $pdo = new SQLite3('database.db', SQLITE3_OPEN_READWRITE);
    $stmt = $pdo->prepare('INSERT INTO users (username, password, ip) VALUES (:username, :password, :ip);');
    $stmt->bindValue(':username', $username, SQLITE3_TEXT);
    $stmt->bindValue(':password', md5($password), SQLITE3_TEXT);
    $stmt->bindValue(':ip', $_SERVER['REMOTE_ADDR'], SQLITE3_TEXT);
    $stmt->execute();
    $pdo->close();
    return ["success", "The user was created successfully."];
}

// === CRYPTO.PHP ===
function create_cookies($username, $password, $key) {
    $iv = mcrypt_create_iv(16, MCRYPT_RAND);
    $plain = 'user/pass:' . $username . '/' . $password;
    $session = bin2hex($iv) . bin2hex(mcrypt_encrypt(MCRYPT_RIJNDAEL_128, $key, $plain, MCRYPT_MODE_CBC, $iv));
    setcookie("session", $session, time() + 60*1000*15);
}

function auth($session, $key) {
    $iv = hex2bin(substr($session, 0, 32));
    $ciphertext = hex2bin(substr($session, 32));
    $session = rtrim(mcrypt_decrypt(MCRYPT_RIJNDAEL_128, $key, $ciphertext, MCRYPT_MODE_CBC, $iv), "\0");
    
    if (strpos($session, ":") > 0 && substr($session, 0, strlen('user/pass')) === 'user/pass') {
        $session = explode(":", $session)[1];
        if (strpos($session, "/") > 0) {
            list($username, $password) = explode("/", $session);
            if (verify_credentials($username, $password)) {
                return $username;
            }
            return "Wrong login or password.!";
        }
    }
    return "The session is corrupted!";
}

// === UTILS.PHP ===
function verify_credentials($username, $password) {
    global $aes_key;
    $pdo = new SQLite3('database.db', SQLITE3_OPEN_READONLY);
    // ⚠️ SQL INJECTION AQUÍ - NO USA PREPARED STATEMENTS
    $result = $pdo->query("SELECT * FROM users WHERE username='$username' AND password='$password';");
    $row = $result->fetchArray(SQLITE3_ASSOC);
    
    if ($row) {
        create_cookies($row['username'], $row['password'], $aes_key);
        $pdo->close();
        return True;
    }
    $pdo->close();
    return False;
}
?>