<?php
include "flag.php";

if (isset($POST['obj'])) {
    setcookie('obj', $_POST['obj']);
} elseif (!isset($_COOKIE['obj'])) {
    $obj = new stdClass;
    $obj->input = 1234;
    setcookie('obj', serialize($obj));
}
?>
<!DOCTYPE html>
<!-- ... HTML ... -->
<?php if (isset($_COOKIE['obj'])): ?>
    <?php
        $obj = $_COOKIE['obj'];
        $unserialized_obj = unserialize($obj);
        $unserialized_obj->flag = $flag;  
        if (hash_equals($unserialized_obj->input, $unserialized_obj->flag))
            echo '<div class="alert alert-success">Here is your flag: <mark>' . $flag . '</mark>.</div>';  
        else
            echo '<div class="alert alert-danger"><code>' . htmlentities($obj) . '</code> is an invalid object, sorry.</div>';
    ?>
<?php endif ?>
?>