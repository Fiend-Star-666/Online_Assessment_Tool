<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
    <script>
        function validateForm() {
            var adhaarCardNumber = document.getElementById("adhaarCardNumber").value;
            var languagesKnown = document.getElementById("languagesKnown").value;
            var dateOfBirth = document.getElementById("dateOfBirth").value;
            var currentDate = new Date().toISOString().split('T')[0];

            if (adhaarCardNumber.length !== 12) {
                alert("Aadhaar Card Number must be 12 digits long.");
                return false;
            }

            if (dateOfBirth >= currentDate) {
                alert("Date of Birth cannot be a current or future date.");
                return false;
            }

            document.getElementById("languagesKnown").value = languagesKnown.toUpperCase();
            return true;
        }
    </script>
    <style>
        table {
            border-collapse: collapse;
        }

        td {
            padding: 5px;
        }
    </style>
</head>
<body>
    <h1>Edit User</h1>
    <form:form action="/users/edit/${user.id}" method="post" modelAttribute="user" onsubmit="return validateForm()">
        <table>
            <tr>
                <td><label>Name:</label></td>
                <td><form:input path="name" id="name" value="${user.name}" /></td>
            </tr>
            <tr>
                <td><label>Date of Birth:</label></td>
                <td>
                    <fmt:formatDate var="formattedDate" value="${user.dateOfBirth}" pattern="yyyy-MM-dd" />
                    <input type="date" name="dateOfBirth" id="dateOfBirth" value="${formattedDate}">
                </td>
            </tr>
            <tr>
                <td><label>Aadhaar Card Number:</label></td>
                <td><form:input path="adhaarCardNumber" id="adhaarCardNumber" value="${user.adhaarCardNumber}" /></td>
            </tr>
            <tr>
                <td><label>City:</label></td>
                <td><form:input path="city" id="city" value="${user.city}" /></td>
            </tr>
            <tr>
                <td><label>Languages Known:</label></td>
                <td><form:input path="languagesKnown" id="languagesKnown" value="${user.languagesKnown.toString().replace('[', '').replace(']', '')}" /></td>
            </tr>
            <tr>
                <td><label>Stream:</label></td>
                <td>
                    <form:select path="stream">
                        <c:forEach items="${streams}" var="stream">
                            <option value="${stream}" ${stream == user.stream ? 'selected' : ''}>${stream}</option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><label>State:</label></td>
                <td>
                    <form:select path="state">
                        <c:forEach items="${states}" var="state">
                            <option value="${state}" ${state == user.state ? 'selected' : ''}>${state}</option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
        </table>
        <br>
        <input type="submit" value="Update">
    </form:form>
</body>
</html>
