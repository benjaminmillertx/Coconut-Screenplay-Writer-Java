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

$sql = "SELECT screenplays.content FROM screenplays JOIN users ON screenplays.user_id = users.id WHERE users.email='$email' LIMIT 1";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    echo $row['content'];
} else {
    echo "No screenplay found for this user.";
}

$conn->close();
?>
