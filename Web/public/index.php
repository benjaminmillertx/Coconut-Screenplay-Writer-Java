<?php
include('../includes/db.php');
include('../includes/auth.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST['login'])) {
        $username = $_POST['username'];
        $password = $_POST['password'];

        $query = "SELECT * FROM users WHERE username='$username'";
        $result = $conn->query($query);

        if ($result->num_rows == 1) {
            $user = $result->fetch_assoc();
            if (password_verify($password, $user['password'])) {
                $_SESSION['username'] = $username;
                $_SESSION['user_id'] = $user['id'];
                header("Location: ../auth/dashboard.php");
            } else {
                $error = "Invalid username or password.";
            }
        } else {
            $error = "Invalid username or password.";
        }
    } elseif (isset($_POST['register'])) {
        $username = $_POST['username'];
        $email = $_POST['email'];
        $password = password_hash($_POST['password'], PASSWORD_BCRYPT);

        $query = "INSERT INTO users (username, email, password) VALUES ('$username', '$email', '$password')";
        if ($conn->query($query) === TRUE) {
            header("Location: index.php");
        } else {
            $error = "Error: " . $query . "<br>" . $conn->error;
        }
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<?php include('../includes/header.php'); ?>
    <h2>Login</h2>
    <?php if (isset($error)) echo "<p>$error</p>"; ?>
    <form action="index.php" method="POST">
        <label for="username">Username:</label>
        <input type="text" name="username" required>
        <label for="password">Password:</label>
        <input type="password" name="password" required>
        <button type="submit" name="login">Login</button>
    </form>
    <h2>Register</h2>
    <form action="index.php" method="POST">
        <label for="username">Username:</label>
        <input type="text" name="username" required>
        <label for="email">Email:</label>
        <input type="email" name="email" required>
        <label for="password">Password:</label>
        <input type="password" name="password" required>
        <button type="submit" name="register">Register</button>
    </form>
<?php include('../includes/footer.php'); ?>
</html>
