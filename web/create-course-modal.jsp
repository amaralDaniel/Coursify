<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasfrancisco
  Date: 22/11/2016
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="create-course-modal" class="modal">
    <div class="modal-background"></div>
    <div class="modal-content">
        <div class="box">
            <article class="media">
                <div class="media-content">
                    <div class="content">
                        <form action="course?create=true" method="POST">
                            <p class="control has-icon">
                                <input class="input" type="text" placeholder="Name" name="name">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </p>
                            <p class="control has-icon">
                                <input class="input" type="text" placeholder="Description" name="description">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </p>
                            <p class="control">
                              <span class="select">
                                <select name="professorId">
                                  <option>Select a Professor</option>
                                    <c:forEach items="${users}" var="user">
                                        <c:if test="${user.userType == 'PROFESSOR'}">
                                            <option value="${user.userId}">${user.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                              </span>
                            </p>
                            <p class="control">
                                <button class="button is-primary" type="submit">
                                   <span class="icon">
                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                    </span>
                                    <span>Create Course</span>
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