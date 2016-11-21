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
  <jsp:include page="head.jsp"/>
</head>
<body>
<section class="hero is-info is-large">
  <!-- Hero header: will stick at the top -->
  <jsp:include page="nav-login.jsp"/>

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

<jsp:include page="footer.jsp"/>


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
                    <select name="userType">
                      <option>Role</option>
                      <option value="ADMINISTRATOR">Administrator</option>
                      <option value="PROFESSOR">Professor</option>
                      <option value="STUDENT">Student</option>
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


