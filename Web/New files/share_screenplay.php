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
$collaboratorEmail = $_POST['collaboratorEmail'];

$sql = "SELECT id FROM users WHERE email='$email'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $user_id = $row['id'];

    $sql = "SELECT content FROM screenplays WHERE user_id='$user_id' LIMIT 1";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $screenplay = $result->fetch_assoc();
        $content = $screenplay['content'];

        $sql = "SELECT id FROM users WHERE email='$collaboratorEmail'";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            $collaborator = $result->fetch_assoc();
            $collaborator_id = $collaborator['id'];

            $sql = "INSERT INTO screenplays (user_id, title, content) VALUES ('$collaborator_id', 'Shared Screenplay', '$content')";
            if ($conn->query($sql) === TRUE) {
                echo "Screenplay shared successfully!";
            } else {
                echo "Error: " . $sql . "<br>" . $conn->error;
            }
        } else {
            echo "Collaborator not found.";
        }
    } else {
        echo "No screenplay found for this user.";
    }
} else {
    echo "User not found.";
}

$conn->close();
?>
