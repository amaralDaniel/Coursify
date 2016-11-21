<%--
  Created by IntelliJ IDEA.
  User: tomasfrancisco
  Date: 20/11/2016
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Coursify</title>
    <link rel="stylesheet" type="text/css" href="assets/css/bulma.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="assets/js/main.js"></script>
</head>
<body>
<section class="hero is-info is-large">
    <!-- Hero header: will stick at the top -->
    <div class="hero-head">
        <header class="nav">
            <div class="container">
                <div class="nav-left">
                    <a class="nav-item">
                        <img src="assets/images/student.svg" alt="Logo">
                        <span style="padding-left: 5px;">Coursify</span>
                    </a>
                </div>
                <span class="nav-toggle">
              <span></span>
              <span></span>
              <span></span>
            </span>
                <div class="nav-right nav-menu">
                    <a class="nav-item is-active">
                        Home
                    </a>
                    <span class="nav-item">
                <a class="button is-info is-inverted modal-open" for="login-modal" href="logout">
                  <span class="icon">
                    <i class="fa fa-sign-out" aria-hidden="true"></i>
                  </span>
                  <span>Logout</span>
                </a>
              </span>
                </div>
            </div>
        </header>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="columns">
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        My Professors
                    </p>
                    <a class="panel-block is-active" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        José Alberto
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Andreia Rodrigues
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        Luís Marmelo
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Rui Luís
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Gonçalo Marques
                    </a>
                    <a class="panel-block is-active" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        José Alberto
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Andreia Rodrigues
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        Luís Marmelo
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Rui Luís
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Gonçalo Marques
                    </a>
                </nav>
            </div>
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        My Courses
                    </p>
                    <a class="panel-block is-active" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        José Alberto
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Andreia Rodrigues
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        Luís Marmelo
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Rui Luís
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Gonçalo Marques
                    </a>
                    <a class="panel-block is-active" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        José Alberto
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Andreia Rodrigues
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        Luís Marmelo
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Rui Luís
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Gonçalo Marques
                    </a>
                </nav>

            </div>
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        My Students
                    </p>
                    <a class="panel-block is-active" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        José Alberto
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Andreia Rodrigues
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        Luís Marmelo
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Rui Luís
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Gonçalo Marques
                    </a>
                    <a class="panel-block is-active" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        José Alberto
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Andreia Rodrigues
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        Luís Marmelo
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Rui Luís
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Gonçalo Marques
                    </a>
                </nav>
            </div>
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        Manage Users
                    </p>
                    <p class="panel-tabs">
                        <a class="is-active" href="#">All</a>
                        <a href="#">Professors</a>
                        <a href="#">Students</a>
                    </p>
                    <a class="panel-block is-active" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        José Alberto
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Andreia Rodrigues
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        Luís Marmelo
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Rui Luís
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Gonçalo Marques
                    </a>
                    <a class="panel-block is-active" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        José Alberto
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Andreia Rodrigues
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        Luís Marmelo
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Rui Luís
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Gonçalo Marques
                    </a>
                </nav>
            </div>
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        Manage Courses
                    </p>
                    <a class="panel-block is-active" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        José Alberto
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Andreia Rodrigues
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        Luís Marmelo
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Rui Luís
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Gonçalo Marques
                    </a>
                    <a class="panel-block is-active" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        José Alberto
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Andreia Rodrigues
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                </span>
                        Luís Marmelo
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Rui Luís
                    </a>
                    <a class="panel-block" href="#">
                <span class="panel-icon">
                  <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                </span>
                        Gonçalo Marques
                    </a>
                </nav>
            </div>
        </div>
    </div>
</section>
<footer class="footer">
    <div class="container">
        <div class="content has-text-centered">
            <p>
                <strong>Bulma</strong> by <a href="http://jgthms.com">Jeremy Thomas</a>. The source code is licensed
                <a href="http://opensource.org/licenses/mit-license.php">MIT</a>. The website content
                is licensed <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">CC ANS 4.0</a>.
            </p>
            <p>
                <a class="icon" href="https://github.com/jgthms/bulma">
                    <i class="fa fa-github"></i>
                </a>
            </p>
        </div>
    </div>
</footer>
</body>
</html>

