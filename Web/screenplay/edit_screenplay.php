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
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $title = $_POST['title'];
    $content = $_POST['content'];

    $query = "UPDATE screenplays SET title='$title', content='$content' WHERE id='$id' AND user_id='$user_id'";
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
    <h2>Edit Screenplay</h2>
    <form action="edit_screenplay.php?id=<?php echo $id; ?>" method="POST">
        <label for="title">Title:</label>
        <input type="text" name="title" value="<?php echo htmlspecialchars($row['title']); ?>" required>
        <label for="content">Content:</label>
        <textarea name="content" rows="20" cols="80" required><?php echo htmlspecialchars($row['content']); ?></textarea>
        <button type="submit">Save</button>
    </form>
<?php include('../includes/footer.php'); ?>
</html>
