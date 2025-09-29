<!DOCTYPE html>
<html>
<head>
	<title>#WebSec Level Twelve</title>

    <link href="/static/bootstrap.min.css" rel="stylesheet" />
    <link href="/static/websec.css" rel="stylesheet" />

    <link rel="icon" href="/static/favicon.png" type="image/png">
</head>
	<body>
		<div id="main">
			<div class="container">
				<div class="row">
					<h1>LevelTwelve <small> - This time, it's different.</small></h1>
				</div>
				<div class="row">
					<p class="lead">
						Since we trust you <em>very much</em>, you can instanciate a class of your choice, with two arbitrary parameters.</br>
						Well, except the dangerous ones, like <code>splfileobject</code>, <code>globiterator</code>, <code>filesystemiterator</code>,
						and <code>directoryiterator</code>.<br>
 						Lets see what you can do with this.
                    			</p>
				</div>
			</div>
			<br>
			<div class="container">
				<div class="row">
					<form name="username" method="post" class="form-inline">
						<samp>
						<div class="form-group">
							<label for="class" class="sr-only">class</label>
							echo <span class='text-success'>new</span>
							<input type="text" class="form-control" id="class" name="class" placeholder="class" required>
							(
						</div>
						<div class="form-group">
							<label for="param1" class="sr-only">first parameter</label>
							<input type="text" class="form-control" id="param1" name="param1" placeholder="first parameter" required>
							,
						</div>
						<div class="form-group">
							<label for="param2" class="sr-only">second parameter</label>
							<input type="text" class="form-control" id="param2" name="param2" placeholder="second parameter" required>
							);
						</div>
						</samp>
      						<button type="submit" class="btn btn-default">launch!</button>
					</form>
				</div>
                <?php
                ini_set('display_errors', 'on');
                ini_set('error_reporting', E_ALL);

                if (isset ($_POST['class']) && isset ($_POST['param1'])  && isset ($_POST['param2'])) {
                    $class = strtolower ($_POST['class']);

                    if (in_array ($class, ['splfileobject', 'globiterator', 'directoryiterator', 'filesystemiterator'])) {
			    die ('Dangerous class detected.');
                    } else {
			    $result = new $class ($_POST['param1'], $_POST['param2']);
			    echo '<br><hr><br><div class="row"><pre>' . $result . '</pre></div>';
		    }
                }
                ?>
			</div>
		</div>
	</body>
</html>

<?php
/*
Congratulation, you can read this file, but this is not the end of our journey.

- Thanks to cutz for the QA.
- Thanks to blotus for finding a (now fixed) weakness in the "encryption" function.
- Thanks to nurfed for nagging us about a cheat
*/

$text = 'Niw0OgIsEykABg8qESRRCg4XNkEHNg0XCls4BwZaAVBbLU4EC2VFBTooPi0qLFUELQ==';
$key = ini_get ('user_agent');

if ($_SERVER['REMOTE_ADDR'] === '127.0.0.1') {
    if ($_SERVER['HTTP_USER_AGENT'] !== $key) {
    	die ("Cheating is bad, m'kay?");
    }
    
    $i = 0;
    $flag = '';
    foreach (str_split (base64_decode ($text)) as $letter) {
        $flag .= chr (ord ($key[$i++]) ^ ord ($letter));
    }
    die ($flag);
}
?>
