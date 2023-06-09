<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Users with pagination</title>
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

    <form action="/user" method="GET">
        <label>Search Name:</label>
        <input type="text" name="string" value="${param.string}">
        <input type="submit" value="Search">
        <br>
        <label>Page Size:</label>
        <input type="number" name="size" value="${pageSize}">
        <input type="submit" value="Update">
        <br>
        <label>Sort By:</label>
        <select name="sortBy">
            <option value="id" ${sortBy == 'id' ? 'selected' : ''}>ID</option>
            <option value="name" ${sortBy == 'name' ? 'selected' : ''}>Name</option>
            <option value="dateOfBirth" ${sortBy == 'dateOfBirth' ? 'selected' : ''}>Date of Birth</option>
        </select>
        <label>Sort Direction:</label>
        <select name="sortDirection">
            <option value="asc" ${sortDirection == 'asc' ? 'selected' : ''}>Ascending</option>
            <option value="desc" ${sortDirection == 'desc' ? 'selected' : ''}>Descending</option>
        </select>
        <input type="submit" value="Update">
    </form>

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

    <div>
        <c:if test="${currentPage > 0}">
            <a href="/user?page=${currentPage - 1}&string=${param.string}&size=${pageSize}&sortBy=${param.sortBy}&sortDirection=${param.sortDirection}">Previous</a>
        </c:if>

        <c:forEach var="i" begin="0" end="${totalPages - 1}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <strong>${i + 1}</strong>
                </c:when>
                <c:otherwise>
                    <a href="/user?page=${i}&string=${param.string}&size=${pageSize}&sortBy=${param.sortBy}&sortDirection=${param.sortDirection}">${i + 1}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage < totalPages - 1}">
            <a href="/user?page=${currentPage + 1}&string=${param.string}&size=${pageSize}&sortBy=${param.sortBy}&sortDirection=${param.sortDirection}">Next</a>
        </c:if>
    </div>
</body>
</html>
