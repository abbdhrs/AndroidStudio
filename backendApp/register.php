<?php
$host = "localhost";
$user = "root";
$pass = "";
$db = "db_app";

$conn = new mysqli($host, $user, $pass, $db);

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];

    echo $username .''. $password .'';

    $stmt = $conn->prepare("SELECT * FROM tb_user WHERE username = ? AND password = ?");
    $stmt->bind_param("ss", $username, $password);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        echo json_encode(["status" => "success", "message" => "Login berhasil"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Data tidak ditemukan"]);
    }

    $stmt->close();
}



$conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="" method="POST">
        <input type="text" name="username">
        <input type="password" name="password" id="">
        <button name="Login">Submit</button>
    </form>
</body>
</html>