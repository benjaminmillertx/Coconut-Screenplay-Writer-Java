<?php
include('../includes/db.php');
include('../includes/auth.php');
requireLogin();

$user_id = $_SESSION['user_id'];
$query = "SELECT * FROM screenplays WHERE user_id='$user_id'";
$result = $conn->query($query);
?>

<!DOCTYPE html>
<html lang="en">
<?php include('../includes/header.php'); ?>
    <h2>Dashboard</h2>
    <a href="../screenplay/create_screenplay.php">Create New Screenplay</a>
    <h3>Your Screenplays</h3>
    <ul>
        <?php while ($row = $result->fetch_assoc()): ?>
            <li>
                <a href="../screenplay/view_screenplay.php?id=<?php echo $row['id']; ?>">
                    <?php echo htmlspecialchars($row['title']); ?>
                </a>
            </li>
        <?php endwhile; ?>
    </ul>
<?php include('../includes/footer.php'); ?>
</html>
