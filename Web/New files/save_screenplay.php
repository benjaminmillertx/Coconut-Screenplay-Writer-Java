<?php
$servername = "sql201.infinityfree.com";
$username = "if0_36946962";
$password = "your_password";
$dbname = "if0_36946962_screenplay_website";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$email = $_POST['email'];
$title = $_POST['title'];
$content = $_POST['content'];

$sql = "SELECT id FROM users WHERE email='$email'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $user_id = $row['id'];
    $sql = "INSERT INTO screenplays (user_id, title, content) VALUES ('$user_id', '$title', '$content')";
    if ($conn->query($sql) === TRUE) {
        echo "Screenplay saved successfully!";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
} else {
    echo "User not found";
}

$conn->close();
?>
