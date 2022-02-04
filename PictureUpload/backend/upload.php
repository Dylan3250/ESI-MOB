<?php

define('HOST', 'localhost');
define('USER', 'esi_he2b');
define('PASS', 'esi_he2b00');
define('DB', 'pictures');

define('UPLOAD_DIRECTORY', 'uploads/');
define('UPLOAD_URL', 'https://site-concept.eu/cours/' . UPLOAD_DIRECTORY);

try {
    $db = new PDO('mysql:host=' . HOST . ';dbname=' . DB, USER, PASS);
} catch (PDOException $e) {
    echo "Erreur de connexion à la DB !";
    die();
}

$response = [];

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    if (isset($_POST['name']) && isset($_POST['user']) && isset($_FILES['image']['name'])) {
        $name = $_POST['name'];
        $user = $_POST['user'];
        $fileinfo = pathinfo($_FILES['image']['name']);
        $extension = $fileinfo['extension'];

        $nameFile = getFileName($db);
        $file_url = UPLOAD_URL . $nameFile . '.' . $extension;
        $file_path = UPLOAD_DIRECTORY . $nameFile . '.' . $extension;

        try {
            move_uploaded_file($_FILES['image']['tmp_name'], $file_path);

            $stmt = $db->prepare("INSERT INTO pictures (url, name, user) VALUES (?,?, ?)");

            try {
                $db->beginTransaction();
                $stmt->execute([$file_url, $name, $user]);
                $db->commit();

                $response['error'] = false;
                $response['url'] = $file_url;
                $response['name'] = $name;
            } catch (Exception $e) {
                $db->rollback();

                $response['error'] = true;
                $response['message'] = $e->getMessage();
            }
        } catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }

        echo json_encode($response);
        $db = null;
    } else {
        $response['error'] = true;
        $response['message'] = 'Please choose a file';
    }
}

function getFileName($db) {
    return sha1($_POST['name'] . $_POST['user'] . date('U'));
}


