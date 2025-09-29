<?php
ini_set('display_errors', 'on');
ini_set('error_reporting', E_ALL);

if (isset($_GET['submit']) && isset($_GET['c'])) {
    $randVal = sha1(time());
    
    setcookie('session_id', $randVal, time() + 2, '', '', true, true);
    
    try {
        $fh = fopen('/tmp/' . $randVal, 'w');
        
        fwrite(
            $fh,
            str_replace(
                ['<?', '?>', '"', "'", '$', '&', '|', '{', '}', ';', '#', ':', '#', ']', '[', ',', '%', '(', ')'],
                '',
                $_GET['c']
            )
        );
        fclose($fh);
    } catch (Exception $e) {
        var_dump($e->getMessage());
    }
}

if (isset($_GET['cache_file'])) {
    if (file_exists($_GET['cache_file'])) {
        echo eval(stripcslashes(file_get_contents($_GET['cache_file'])));
    }
}
?>