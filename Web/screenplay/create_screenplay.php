<?php
include('../includes/db.php');
include('../includes/auth.php');
requireLogin();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $title = $_POST['title'];
    $content = $_POST['content'];
    $user_id = $_SESSION['user_id'];

    $query = "INSERT INTO screenplays (user_id, title, content) VALUES ('$user_id', '$title', '$content')";
    if ($conn->query($query) === TRUE) {
        header("Location: ../auth/dashboard.php");
    } else {
        echo "Error: " . $query . "<br>" . $conn->error;
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<?php include('../includes/header.php'); ?>
    <h2>Create Screenplay</h2>
    <form action="create_screenplay.php" method="POST">
        <label for="title">Title:</label>
        <input type="text" name="title" required>
        <label for="content">Content:</label>
        <textarea name="content" rows="20" cols="80" required></textarea>
        <button type="submit">Save</button>
    </form>
<?php include('../includes/footer.php'); ?>
</html>
