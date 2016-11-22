<%--
  Created by IntelliJ IDEA.
  User: tomasfrancisco
  Date: 21/11/2016
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:import url="/course?id=${param.id}"/>
<c:import url="/material?courseId=${param.id}"/>
<c:import url="/users"/>

<html>
<head>
    <title>Coursify</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="nav-logout.jsp"/>

<c:if test="${not empty course}">
<section class="section">
    <div class="container">
        <nav class="nav">
            <div class="container">
                <div class="nav-left">
                    <c:if test="${(userType == 'ADMINISTRATOR') || (userType == 'PROFESSOR')}">
                    <span class="nav-item">
                        <a class="button is-info is-inverted modal-open" for="create-material-modal">
                          <span class="icon">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>
                          </span>
                          <span>Create Material</span>
                        </a>
                    </span>
                    </c:if>
                </div>
            </div>
        </nav>
        <div class="columns">
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        Course
                    </p>
                    <div class="box" style="box-shadow: none;">
                        <form action="course?update=true" method="POST">
                            <input style="display: none;" type="text" value="${course.courseId}" name="courseId">
                            <p class="control has-icon">
                                <input class="input" type="text" placeholder="Name" name="name" value="${course.name}">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </p>
                            <p class="control has-icon">
                                <input class="input" type="text" placeholder="Description" name="description" value="${course.description}">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </p>
                            <c:if test="${userType == 'ADMINISTRATOR'}">
                            <p class="control">
                                <button class="button is-primary" type="submit">
                                    <span class="icon">
                                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                                    </span>
                                    <span>Save</span>
                                </button>
                            </p>
                            </c:if>
                        </form>
                        <c:if test="${userType == 'ADMINISTRATOR'}">
                        <form action="course?delete=true" method="POST">
                            <input style="display: none;" type="text" value="${course.courseId}" name="courseId">
                            <button class="button is-danger" type="submit">
                                <span class="icon">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </span>
                                <span>Delete</span>
                            </button>
                        </form>
                        </c:if>
                    </div>
                </nav>
            </div>
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        Materials
                    </p>
                    <c:forEach items="${materials}" var="material">
                        <div class="panel-block">
                            <div class="columns">
                                    <%--<form method="GET" action="download" enctype="multipart/form-data" >--%>
                                    <%--File:--%>
                                    <%--<input type="submit" value="Download" name="download" id="download" />--%>
                                    <%--</form>--%>
                                <div class="column">
                                    <a class="panel-block is-active" href="material?id=${material.materialId}&download=true" enctype="multipart/form-data">
                                        <span class="panel-icon">
                                          <i class="fa fa-download" style="color: #56b881;" aria-hidden="true"></i>
                                        </span>
                                            ${material.filename} by ${material.professorName}
                                    </a>
                                </div>
                                <div class="column">
                                    <a class="button is-danger" href="material?delete=true&id=${material.materialId}">
                                        <i class="fa fa-trash" style="color: white;" aria-hidden="true"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </nav>
            </div>
        </div>
    </div>
</section>
</c:if>
<jsp:include page="footer.jsp"/>
<jsp:include page="create-material-modal.jsp"/>
</body>
</html>