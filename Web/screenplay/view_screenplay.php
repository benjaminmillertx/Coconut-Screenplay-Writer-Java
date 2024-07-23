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
?>

<!DOCTYPE html>
<html lang="en">
<?php include('../includes/header.php'); ?>
    <h2><?php echo htmlspecialchars($row['title']); ?></h2>
    <p><?php echo nl2br(htmlspecialchars($row['content'])); ?></p>
    <a href="edit_screenplay.php?id=<?php echo $id; ?>">Edit</a>
<?php include('../includes/footer.php'); ?>
</html>
