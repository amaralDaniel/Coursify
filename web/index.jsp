<%--
  Created by IntelliJ IDEA.
  User: tomasfrancisco
  Date: 19/11/2016
  Time: 15:18
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
          <a class="nav-item modal-open" for="sign-up-modal">
            Create account
          </a>
          <span class="nav-item">
                <a class="button is-info is-inverted modal-open" for="login-modal">
                  <span class="icon">
                    <i class="fa fa-sign-in" aria-hidden="true"></i>
                  </span>
                  <span>Log in</span>
                </a>
              </span>
        </div>
      </div>
    </header>
  </div>

  <!-- Hero content: will be in the middle -->
  <div class="hero-body">
    <div class="container has-text-centered">
      <img src="assets/images/student.svg" alt="Logo">
      <h1 class="title">
        Coursify
      </h1>
      <h2 class="subtitle">
        The new NONIO interface
      </h2>
    </div>
  </div>

  <!-- Hero footer: will stick at the bottom -->
  <div class="hero-foot">
    <nav class="tabs is-boxed is-fullwidth">
      <div class="container">
        <ul>
          <li class="is-active"><a>Overview</a></li>
          <li><a>How It Works</a></li>
          <li><a>Authors</a></li>
          <li><a>About</a></li>
        </ul>
      </div>
    </nav>
  </div>
</section>

<section class="section">
  <div class="container">
    <div class="heading">
      <h1 class="title">Section</h1>
      <h2 class="subtitle">
        A simple container to divide your page into <strong>sections</strong>, like the one you're currently reading
      </h2>
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


<div id="login-modal" class="modal">
  <div class="modal-background"></div>
  <div class="modal-content">
    <div class="box">
      <article class="media">
        <div class="media-content">
          <div class="content">
            <form action="login" method="post">
              <p class="control has-icon">
                <input class="input" type="email" name="email" placeholder="Email">
                <i class="fa fa-envelope"></i>
              </p>
              <p class="control has-icon">
                <input class="input" type="password" name="password" placeholder="Password">
                <i class="fa fa-lock"></i>
              </p>
              <p class="control">
                <button class="button is-primary" type="submit">
                      <span class="icon">
                        <i class="fa fa-sign-in" aria-hidden="true"></i>
                      </span>
                  <span>Login</span>
                </button>
              </p>
            </form>
          </div>
        </div>
      </article>
    </div>
  </div>
  <button class="modal-close"></button>
</div>

<div id="sign-up-modal" class="modal">
  <div class="modal-background"></div>
  <div class="modal-content">
    <div class="box">
      <article class="media">
        <div class="media-content">
          <div class="content">
            <form action="signup" method="POST">
              <p class="control">
                  <span class="select">
                    <select>
                      <option>Role</option>
                      <option>Teacher</option>
                      <option>Student</option>
                    </select>
                  </span>
              </p>
              <p class="control has-icon">
                <input class="input" type="text" placeholder="Name" name="name">
                <i class="fa fa-user" aria-hidden="true"></i>
              </p>
              <p class="control has-icon">
                <input class="input" type="email" placeholder="Email" name="email">
                <i class="fa fa-envelope"></i>
              </p>
              <p class="control has-icon">
                <input class="input" type="password" placeholder="Password" name="password">
                <i class="fa fa-lock"></i>
              </p>
              <p class="control has-icon">
                <input class="input" type="password" placeholder="Confirm password">
                <i class="fa fa-lock"></i>
              </p>
              <p class="control">
                <button class="button is-primary" type="submit">
                      <span class="icon">
                        <i class="fa fa-sign-in" aria-hidden="true"></i>
                      </span>
                  <span>Create account</span>
                </button>
              </p>
            </form>
          </div>
        </div>
      </article>
    </div>
  </div>
  <button class="modal-close"></button>
</div>
</body>
</html>


