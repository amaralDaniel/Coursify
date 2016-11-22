<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasfrancisco
  Date: 22/11/2016
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="create-material-modal" class="modal">
    <div class="modal-background"></div>
    <div class="modal-content">
        <div class="box">
            <article class="media">
                <div class="media-content">
                    <div class="content">
                        <form action="material?create=true" method="POST">
                            <input style="display: none;" type="text" value="${course.courseId}" name="courseId">
                            <p class="control has-icon">
                                <input class="input" type="text" placeholder="Filename" name="filename">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </p>
                            <p class="control has-icon">
                                <input class="input" type="text" placeholder="Type" name="type">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </p>
                            <p class="control">
                              <span class="select">
                                  <c:if test="${userType == 'ADMINISTRATOR'}">
                                    <select name="professorId">
                                      <option>Select a Professor</option>
                                        <c:forEach items="${users}" var="user">
                                            <c:if test="${user.userType == 'PROFESSOR'}">
                                                <option value="${user.userId}">${user.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                  </c:if>
                                  <c:if test="${(userType == 'PROFESSOR')}">
                                      <input style="display: none;" type="text" value="${userId}" name="professorId">
                                  </c:if>
                              </span>
                            </p>
                            <p class="control">
                                <button class="button is-primary" type="submit">
                                    <span class="icon">
                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                    </span>
                                    <span>Create Material</span>
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