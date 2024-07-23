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
$password = $_POST['password'];

$sql = "SELECT password FROM users WHERE email='$email'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    if (password_verify($password, $row['password'])) {
        echo "Login successful";
    } else {
        echo "Invalid email or password";
    }
} else {
    echo "Invalid email or password";
}

$conn->close();
?>
