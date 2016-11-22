<%--
  Created by IntelliJ IDEA.
  User: tomasfrancisco
  Date: 21/11/2016
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="hero-head">
    <header class="nav">
        <div class="container">
            <div class="nav-left">
                <a class="nav-item" href="/coursify">
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
                <%--<a class="nav-item modal-open" for="sign-up-modal">--%>
                    <%--Create account--%>
                <%--</a>--%>
                <span class="nav-item">
                <a class="button is-info is-inverted modal-open" for="login-modal">
                  <span class="icon">
                    <i class="fa fa-sign-in" aria-hidden="true"></i>
                  </span>
                  <span>Login</span>
                </a>
              </span>
            </div>
        </div>
    </header>
</div>
