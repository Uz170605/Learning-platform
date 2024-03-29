<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Azizjon
  Date: 14.02.2022
  Time: 0:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select course</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <script src=
                    "https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
</head>
<body style="background-color: rgba(19,213,246,0.1);">
<div class="row mt-5 ml-0 mr-0" style="height: 400px;">
    <div class="col-md-6 offset-3 mb-5" style="background-color: white; border-radius:10px ;border: 2px solid gray;box-shadow: 5px 10px 8px #888888;z-index: 11;">
        <form  class="mt-5 mb-5">
            <div class="form-group">
                <label for="courseName">Name: </label>
                <input value="${selectCourse.name}" name="name" type="text" class="form-control" id="courseName"
                       placeholder="Enter course name here">
            </div>
            <div class="form-group">
                <label for="coursePrice">Price: </label>
                <input value="${selectCourse.price}" name="price" type="number" class="form-control"
                       id="coursePrice"
                       placeholder="Enter course price here">
            </div>

            <div class="form-group">
                <label for="courseDescription">Description: </label>
                <textarea  name="description" type="text" class="form-control"
                           id="courseDescription" placeholder="Enter course description here">${selectCourse.description}</textarea>
            </div>

            <div class="form-check my-4">
                <label class="form-check-label mr-2" for="status">Is active: </label>
                <input
                <c:if test="${selectCourse.active == true}">
                        checked
                </c:if>
                        name="active"
                        type="checkbox"
                        class="form-check-input ml-0 mt-2 mb-0"
                        id="status">
            </div>

            <div class="form-group">
                <div class="container-md p-0">
                <h6 style="color: #00cc00;">The authors of the course</h6>
                <c:forEach var="author" items="${selectCourse.authors}">
                    <h6><a href="/users/userAllData/${author.id}" style="color: black">${author.firstName} ${author.lastName}</a></h6>
                </c:forEach>
                </div>
            </div>
            <div class="form-group">
                <div class="container-md p-0">
                    <h6 style="color: #00cc00;">Course's modules</h6>
                    <c:forEach var="module" items="${selectCourse.module}">
                        <h6><a href="/modules/moduleAllData/${module.id}"
                               style="color: black">${module.name}</a> </h6>
                    </c:forEach>
                </div>
            </div>


            <a href="/courses" class="btn btn-primary mt-3"> Back </a>
        </form>
    </div>
</div>

















<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
