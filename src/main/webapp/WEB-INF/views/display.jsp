<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Users</title>
    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
</head>
<body>
    <h1>All Users</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date of Birth</th>
            <th>Adhaar Card Number</th>
            <th>City</th>
            <th>Languages Known</th>
            <th>Stream</th>
            <th>State</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${formattedDates[user]}</td>
                <td>${user.adhaarCardNumber}</td>
                <td>${user.city}</td>
                <td>
                    <c:forEach var="language" items="${user.languagesKnown}">
                        ${language}<br>
                    </c:forEach>
                </td>
                <td>${user.stream}</td>
                <td>${user.state}</td>
                <td><a href="/users/edit/${user.id}">Edit</a></td>
                <td>
                    <form action="/users/delete/${user.id}" method="post">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
