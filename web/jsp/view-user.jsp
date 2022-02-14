<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Azizjon
  Date: 11.02.2022
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mentors</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <script src=
                    "https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
</head>
<body>

<div class="row ml-0 mr-0">
    <div class="col-md-10 offset-1 mt-5">
        <c:choose>
            <c:when test="${param.message != null}">
                <h1 style="color: #00cc00; font-family: 'Comic Sans MS'; text-align: center">${param.message}</h1>
            </c:when>
        </c:choose>
        <a href="/users/addUser" class="btn btn-success"> + Add</a>
        <div class="row mt-4">
            <table class="table table-hover table-responsive-sm table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last name</th>
                    <th scope="col">Phone number</th>
                    <th scope="col">Email</th>
                    <th scope="col">Password</th>
                    <th scope="col">Role</th>
                    <th scope="col">Edit</th>
                    <th scope="col">Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" step="1" items="${users}">
                    <tr>
                        <th scope="row"> ⚫️ </th>
                       <td>${user.firstName}</td>
                       <td>${user.lastName}</td>
                       <td>${user.phoneNumber}</td>
                       <td>${user.email}</td>
                       <td>${user.password}</td>
                       <td>
                           <c:forEach var="role" items="${user.roles}">
                               <span>${role.name},</span>
                           </c:forEach>
                       </td>

                        <td><a href="/courses/${user.id}" class="btn btn-warning">Edit</a></td>
                        <td><button class="btn btn-danger"
                                    onclick="makeDELETErequest('/users/${user.id}')"> Delete </button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


















<script>
    function makePUTrequest() {
        $.ajax({
            url: 'test.html',
            type: 'PUT',
            success: function (result) {
                // Do something with the result
            }
        });
    }

    function makeDELETErequest(url) {
        fetch(
            url,
            {method:'DELETE'}
        ).then(response => location.reload())
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
