<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>New user registration</title>
</head>
<body>
 
<h2>New user registration</h2>
 
<form:form method="post" action="saveCustomer" commandName = "main">
 
    <table>
    <tr>
        <td><form:label path="mobile"><spring:message code="mobile"/></form:label></td>
        <td><form:input path="mobile" /></td>
    </tr>
    <tr>
        <td><form:label path="password"><spring:message code="password"/></form:label></td>
        <td><form:input path="password" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="register"/>"/>
        </td>
    </tr>
</table> 
</form:form>
 
     
<%--<h3>Employees</h3>
<c:if  test="${!empty employeeList}">
<table class="data">
<tr>
    <th>Name</th>
    <th>Email</th>
    <th>Telephone</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${employeeList}" var="emp">
    <tr>
        <td>${emp.lastname}, ${emp.firstname} </td>
        <td>${emp.email}</td>
        <td>${emp.telephone}</td>
        <td><a href="delete/${emp.id}">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>--%>
 
</body>
</html>