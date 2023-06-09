<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
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
</head>
<body>
    <h2>User Registration:</h2>
    <label>Kindly input the Languages Known in a comma-separated list.</label>
    <br><br>
    <form:form method="POST" action="/register" modelAttribute="user" onsubmit="return validateForm()">
        <table>
            <tr>
                <td><label>Name:</label></td>
                <td><form:input path="name" required="true" /></td>
            </tr>
            <tr>
                <td><label>Date of Birth:</label></td>
                <td><input type="date" name="dateOfBirth" id="dateOfBirth" required="true" /></td>
            </tr>
            <tr>
                <td><label>Aadhaar Card Number:</label></td>
                <td><input type="text" name="adhaarCardNumber" id="adhaarCardNumber" required="true" /></td>
            </tr>
            <tr>
                <td><label>State:</label></td>
                <td>
                    <form:select path="state">
                        <c:forEach items="${states}" var="state">
                            <option value="${state}">${state}</option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><label>City:</label></td>
                <td><form:input path="city" required="true" /></td>
            </tr>
            <tr>
                <td><label>Languages Known:</label></td>
                <td><input type="text" name="languagesKnown" id="languagesKnown" /></td>
            </tr>
            <tr>
                <td><label>Stream:</label></td>
                <td>
                    <form:select path="stream">
                        <c:forEach items="${streams}" var="stream">
                            <option value="${stream}">${stream}</option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
        </table>
        <br>
        <button type="submit">Register</button>
    </form:form>
</body>
</html>
