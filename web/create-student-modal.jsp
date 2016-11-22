<%--
  Created by IntelliJ IDEA.
  User: tomasfrancisco
  Date: 21/11/2016
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="create-student-modal" class="modal">
    <div class="modal-background"></div>
    <div class="modal-content">
        <div class="box">
            <article class="media">
                <div class="media-content">
                    <div class="content">
                        <form action="user?create=true" method="POST">
                            <input style="display: none;" type="text" value="STUDENT" name="userType">
                            <p class="control has-icon">
                                <input class="input" type="text" placeholder="Name" name="name">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </p>
                            <p class="control has-icon">
                                <input class="input flatpickr" type="text" placeholder="Birthdate" name="birthdate">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </p>
                            <p class="control has-icon">
                                <input class="input" type="email" placeholder="Institutional E-Mail" name="institutional-email">
                                <i class="fa fa-envelope"></i>
                            </p>
                            <p class="control has-icon">
                                <input class="input" type="email" placeholder="Alternative E-Mail" name="alternative-email">
                                <i class="fa fa-envelope"></i>
                            </p>
                            <p class="control has-icon">
                                <input class="input" type="text" placeholder="Address" name="address">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </p>
                            <p class="control has-icon">
                                <input class="input" type="text" placeholder="Phone" name="phone">
                                <i class="fa fa-user" aria-hidden="true"></i>
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
                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                    </span>
                                    <span>Create Student</span>
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