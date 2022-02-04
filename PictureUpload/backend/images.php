<?php

define('HOST', 'localhost');
define('USER', 'esi_he2b');
define('PASS', 'esi_he2b00');
define('DB', 'pictures');

try {
    $db = new PDO('mysql:host=' . HOST . ';dbname=' . DB, USER, PASS);
} catch (PDOException $e) {
    echo "Erreur de connexion à la DB !";
    die();
}

$response = [];
$response['error'] = false;
$response['images'] = [];

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    if (isset($_POST['user'])) {
        $user = $_POST['user'];

        try {
            $stmt = $db->prepare("SELECT id, url, name, user FROM pictures WHERE user = ? ORDER BY id");

            try {
                $stmt->execute([$user]);
                $datas = $stmt->fetchAll();

                if ($datas) {
                    foreach ($datas as $row) {
                        $temp = [];
                        $temp['id'] = $row['id'];
                        $temp['url'] = $row['url'];
                        $temp['name'] = $row['name'];
                        array_push($response['images'], $temp);
                    }
                }
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


