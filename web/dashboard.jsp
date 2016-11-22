<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:import url="/users"/>
<c:import url="/courses"/>
<html>
<head>
    <title>Coursify</title>
    <jsp:include page="head.jsp"/>
</head>
<body>


<jsp:include page="nav-logout.jsp"/>

<section class="section">
    <div class="container">
        <nav class="nav">
            <div class="container">
                <div class="nav-left">
                <c:if test="${userType == 'ADMINISTRATOR'}">
                    <span class="nav-item">
                        <a class="button is-info is-inverted modal-open" for="create-student-modal">
                          <span class="icon">
                            <i class="fa fa-user-plus" aria-hidden="true"></i>
                          </span>
                          <span>Create Student</span>
                        </a>
                    </span>
                    <span class="nav-item">
                        <a class="button is-info is-inverted modal-open" for="create-professor-modal">
                          <span class="icon">
                            <i class="fa fa-user-plus" aria-hidden="true"></i>
                          </span>
                          <span>Create Professor</span>
                        </a>
                    </span>
                    <span class="nav-item">
                        <a class="button is-info is-inverted modal-open" for="create-course-modal">
                          <span class="icon">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>
                          </span>
                          <span>Create Course</span>
                        </a>
                    </span>
                </c:if>
                </div>
            </div>
        </nav>

        <div class="columns">
            <c:if test="${userType == 'STUDENT'}">
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        My Professors
                    </p>
                    <c:forEach items="${users}" var="user">
                        <a class="panel-block is-active" href="user.jsp?id=${user.userId}">
                            <span class="panel-icon">
                              <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                            </span>
                            ${user.name}
                        </a>
                    </c:forEach>
                </nav>
            </div>
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        My Courses
                    </p>
                    <c:forEach items="${courses}" var="course">
                        <a class="panel-block is-active" href="course.jsp?id=${course.courseId}">
                            <span class="panel-icon">
                              <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                            </span>
                            ${course.name}
                        </a>
                    </c:forEach>
                </nav>
            </div>
            </c:if>
            <c:if test="${userType == 'PROFESSOR'}">
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        My Students
                    </p>
                    <c:forEach items="${users}" var="user">
                        <a class="panel-block is-active" href="user.jsp?id=${user.userId}">
                            <span class="panel-icon">
                              <i class="fa fa-podcast" style="color: #56b881;" aria-hidden="true"></i>
                            </span>
                            ${user.name}
                        </a>
                    </c:forEach>
                </nav>
            </div>
            </c:if>
            <c:if test="${(userType == 'PROFESSOR') || (userType == 'STUDENT')}">
                <div class="column">
                    <nav class="panel">
                        <p class="panel-heading">
                            My Courses
                        </p>
                        <c:forEach items="${courses}" var="course">
                            <a class="panel-block is-active" href="course.jsp?id=${course.courseId}">
                                <span class="panel-icon">
                                  <i class="fa fa-podcast" style="color: #56b881;" aria-hidden="true"></i>
                                </span>
                                ${course.name}
                            </a>
                        </c:forEach>
                    </nav>
                </div>
            </c:if>
            <c:if test="${userType == 'ADMINISTRATOR'}">
            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        Manage Users
                    </p>
                    <c:forEach items="${users}" var="user">
                        <c:if test="${user.userType == 'ADMINISTRATOR'}">
                        <a class="panel-block is-active" href="user.jsp?id=${user.userId}">
                            <span class="panel-icon">
                                <i class="fa fa-user-md" style="color: #e55e5e;" aria-hidden="true"></i>
                            </span>
                            ${user.name}
                        </a>
                        </c:if>
                        <c:if test="${user.userType == 'PROFESSOR'}">
                            <a class="panel-block is-active" href="user.jsp?id=${user.userId}">
                            <span class="panel-icon">
                              <i class="fa fa-briefcase" style="color: #ff9933;" aria-hidden="true"></i>
                            </span>
                                ${user.name}
                            </a>
                        </c:if>
                        <c:if test="${user.userType == 'STUDENT'}">
                            <a class="panel-block is-active" href="user.jsp?id=${user.userId}">
                            <span class="panel-icon">
                              <i class="fa fa-graduation-cap" style="color: #34a9ca;" aria-hidden="true"></i>
                            </span>
                                ${user.name}
                            </a>
                        </c:if>
                    </c:forEach>
                </nav>
            </div>

            <div class="column">
                <nav class="panel">
                    <p class="panel-heading">
                        Manage Courses
                    </p>
                    <c:forEach items="${courses}" var="course">
                    <a class="panel-block is-active" href="course.jsp?id=${course.courseId}">
                        <span class="panel-icon">
                          <i class="fa fa-book" aria-hidden="true"></i>
                        </span>
                            ${course.name}
                    </a>
                    </c:forEach>
                </nav>
            </div>
            </c:if>
        </div>
        <form method="POST" action="upload" enctype="multipart/form-data" >
            File:
            <input type="file" name="file" id="file" /> <br/>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>

        <form method="GET" action="download" enctype="multipart/form-data" >
            File:
            <input type="submit" value="Download" name="download" id="download" />
        </form>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<jsp:include page="create-student-modal.jsp"/>
<jsp:include page="create-professor-modal.jsp"/>
<jsp:include page="create-course-modal.jsp"/>
</body>
</html>
