<?php
include('../includes/db.php');
include('../includes/auth.php');
requireLogin();

$id = $_GET['id'];
$user_id = $_SESSION['user_id'];

$query = "SELECT * FROM screenplays WHERE id='$id' AND user_id='$user_id'";
$result = $conn->query($query);

if ($result->num_rows != 1) {
    echo "Screenplay not found.";
    exit();
}

$row = $result->fetch_assoc();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $title = $_POST['title'];
    $content = $_POST['content'];

    $query = "UPDATE screenplays SET title='$title', content='$content' WHERE id='$id' AND user_id='$user_id'";
