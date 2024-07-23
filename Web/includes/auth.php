<?php
session_start();

function isLoggedIn() {
    return isset($_SESSION['username']);
}

function requireLogin() {
    if (!isLoggedIn()) {
        header("Location: /auth/login.php");
        exit();
    }
}
?>
