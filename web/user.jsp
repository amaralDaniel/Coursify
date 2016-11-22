<%--
  Created by IntelliJ IDEA.
  User: tomasfrancisco
  Date: 21/11/2016
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
<head>
    <title>Coursify</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="nav-logout.jsp"/>

<c:import url="/user?id=${param.id}"/>
<c:if test="${not empty user}">
<section class="section">
    <div class="container">
        <h1 class="title">Edit user information</h1>
        <div class="columns">
            <div class="column">
                <div class="box">
                    <article class="media">
                        <div class="media-content">
                            <div class="content">
                                <form action="user?update=true" method="POST">
                                    <input style="display: none;" type="text" value="${user.userId}" name="userId">
                                    <input style="display: none;" type="text" value="${user.userType}" name="userType">
                                    <p class="control has-icon">
                                        <input class="input" type="text" placeholder="Name" name="name" value="${user.name}">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </p>
                                    <p class="control has-icon">
                                        <input class="input flatpickr" type="text" placeholder="Birthdate" name="birthdate" value="${user.birthdate}">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </p>
                                    <p class="control has-icon">
                                        <input class="input" type="email" placeholder="Institutional E-Mail" name="institutional-email" value="${user.institutionalEmail}">
                                        <i class="fa fa-envelope"></i>
                                    </p>
                                    <p class="control has-icon">
                                        <input class="input" type="email" placeholder="Alternative E-Mail" name="alternative-email" value="${user.email}">
                                        <i class="fa fa-envelope"></i>
                                    </p>
                                    <p class="control has-icon">
                                        <input class="input" type="text" placeholder="Address" name="address" value="${user.address}">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </p>
                                    <p class="control has-icon">
                                        <input class="input" type="text" placeholder="Phone" name="phone" value="${user.telephone}">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </p>
                                    <c:if test="${user.userType == 'STUDENT'}">
                                    <p class="control has-icon">
                                        <input class="input" type="text" placeholder="Year" name="year" value="${user.yearRegistry}">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </p>
                                    </c:if>
                                    <c:if test="${user.userType == 'PROFESSOR'}">
                                        <p class="control has-icon">
                                            <input class="input" type="text" placeholder="Office" name="office" value="${user.office}">
                                            <i class="fa fa-user" aria-hidden="true"></i>
                                        </p>
                                        <p class="control has-icon">
                                            <input class="input" type="text" placeholder="Category" name="category" value="${user.category}">
                                            <i class="fa fa-user" aria-hidden="true"></i>
                                        </p>
                                        <p class="control has-icon">
                                            <input class="input" type="text" placeholder="Internal phone" name="internal-phone" value="${user.internalTelephone}">
                                            <i class="fa fa-user" aria-hidden="true"></i>
                                        </p>
                                        <p class="control has-icon">
                                            <input class="input" type="text" placeholder="Salary" name="salary" value="${user.salary}">
                                            <i class="fa fa-user" aria-hidden="true"></i>
                                        </p>
                                    </c:if>
                                    <p class="control">
                                        <button class="button is-primary" type="submit">
                                            <span class="icon">
                                                <i class="fa fa-floppy-o" aria-hidden="true"></i>
                                            </span>
                                            <span>Save</span>
                                        </button>
                                    </p>
                                </form>
                                <c:if test="${loggedInUserId != user.userId}">
                                <form action="user?delete=true" method="POST">
                                    <input style="display: none;" type="text" value="${user.userId}" name="userId">
                                    <button class="button is-danger" type="submit">
                                            <span class="icon">
                                                <i class="fa fa-trash" aria-hidden="true"></i>
                                            </span>
                                        <span>Delete</span>
                                    </button>
                                </form>
                                </c:if>
                            </div>
                        </div>
                    </article>
                </div>
            </div>
            <div class="column">

            </div>
        </div>
    </div>
</section>
</c:if>
<jsp:include page="footer.jsp"/>
</body>
</html>
