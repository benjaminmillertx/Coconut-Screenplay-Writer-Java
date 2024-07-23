<?php
session_start();
include('includes/db.php');

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
                header("Location: auth/dashboard.php");
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
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coconut Screenplay Writing</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            text-align: center;
        }

        .header {
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .logo {
            width: 50px;
            height: 50px;
            vertical-align: middle;
        }

        h1 {
            display: inline;
            font-size: 24px;
            margin-left: 10px;
        }

        .main-content {
            display: flex;
            justify-content: space-between;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .info {
            flex: 2;
            padding-right: 30px;
        }

        .info h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .info p {
            color: #666;
            line-height: 1.6;
        }

        .side-panel {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .form-container {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h2 {
            margin-bottom: 20px;
            color: #1877f2;
        }

        .input-container {
            position: relative;
            margin-bottom: 20px;
        }

        .input-container i {
            position: absolute;
            left: 10px;
            top: 50%;
            transform: translateY(-50%);
            color: #999;
        }

        .input-container input {
            width: 100%;
            padding: 10px 10px 10px 40px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        button {
            background-color: #1877f2;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }

        button:hover {
            background-color: #145db2;
        }

        .error {
            color: red;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <img src="assets/img/coconut_logo.png" alt="Coconut Logo" class="logo">
            <h1>Coconut Screenplay Writing</h1>
        </div>
        <div class="main-content">
            <div class="info">
                <h2>Welcome to Coconut Screenplay Writing</h2>
                <p>Experience seamless screenplay writing with our collaborative platform. Our software includes spellcheck, shortcuts for screenplay format words, and more to make your writing process smoother.</p>
            </div>
            <div class="side-panel">
                <div class="form-container">
                    <h2>Login</h2>
                    <?php if (isset($error)) echo "<p class='error'>$error</p>"; ?>
                    <form action="index.php" method="POST">
                        <div class="input-container">
                            <i class="fas fa-user"></i>
                            <input type="text" name="username" placeholder="Username" required>
                        </div>
                        <div class="input-container">
                            <i class="fas fa-lock"></i>
                            <input type="password" name="password" placeholder="Password" required>
                        </div>
                        <button type="submit" name="login">Login</button>
                    </form>
                </div>
                <div class="form-container">
                    <h2>Register</h2>
                    <form action="index.php" method="POST">
                        <div class="input-container">
                            <i class="fas fa-user"></i>
                            <input type="text" name="username" placeholder="Username" required>
                        </div>
                        <div class="input-container">
                            <i class="fas fa-envelope"></i>
                            <input type="email" name="email" placeholder="Email" required>
                        </div>
                        <div class="input-container">
                            <i class="fas fa-lock"></i>
                            <input type="password" name="password" placeholder="Password" required>
                        </div>
                        <button type="submit" name="register">Register</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
