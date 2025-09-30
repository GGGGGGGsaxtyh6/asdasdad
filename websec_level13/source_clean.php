<?php
// Defines $flag
include 'flag.php';

$db = new PDO('sqlite::memory:');
$db->exec('CREATE TABLE users (
  user_id   INTEGER PRIMARY KEY,
  user_name TEXT NOT NULL,
  user_privileges INTEGER NOT NULL,
  user_password TEXT NOT NULL
)');

$db->prepare("INSERT INTO users VALUES(0, 'admin', 0, '$flag');")->execute();

for($i=1; $i<25; $i++) {
  $pass = md5(uniqid());
  $user = "user_" . substr(crc32($pass), 0, 2);
  $db->prepare("INSERT INTO users VALUES($i, '$user', 1, '$pass');")->execute();
}

if (isset($_GET['ids'])) {
    if ( ! is_string($_GET['ids'])) {
        die("Don't be silly.");
    }

    if ( strlen($_GET['ids']) > 70) {
        die("Please don't check all the privileges at once.");
    }

  $tmp = explode(',',$_GET['ids']);
  for ($i = 0; $i < count($tmp); $i++ ) {
        $tmp[$i] = (int)$tmp[$i];
        if( $tmp[$i] < 1 ) {
            unset($tmp[$i]);
        }
  }

  $selector = implode(',', array_unique($tmp));

  $query = "SELECT user_id, user_privileges, user_name
  FROM users
  WHERE (user_id in (" . $selector . "));";

  $stmt = $db->query($query);

    echo '<br>';
    echo '<div class="well">';
  echo '<ul>';
  while ($row = $stmt->fetch(\PDO::FETCH_ASSOC)) {
        echo "<li>";
        echo "User <em>" . $row['user_name'] . "</em>";
      echo "    with id <code>" . $row['user_id'] . '</code>';
        echo " has <b>" . ($row['user_privileges'] == 0?"all":"no") . "</b> privileges.";
        echo "</li>\n";
  }
    echo "</ul>";
    echo "</div>";
}
?>