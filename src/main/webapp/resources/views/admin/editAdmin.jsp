<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-03-22
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Admin Page</title>
</head>
<body>
    <form:form action="/panelAdmin/adminList/adminE/" modelAttribute="admin" method="POST">
        <form:input type="hidden" path="userId"/><br>
        Login: <form:input type="text" path="login"/><br>
        Hasło: <form:input type="password" path="password"/>Podać tylko jeśli chcesz zmienić <br>
        Imię: <form:input type="text" path="name"/><br>
        Nazwisko: <form:input type="text" path="surname"/><br>
        Telefon: <form:input type="text" path="telephone"/><br>
        E-Mail: <form:input type="text" path="EMail"/><br>

        <input type="submit" name="submit" value="Zatwierdź"/>
    </form:form>

    <br>
    <jsp:include page="../bottomMenu.jsp"/>
</body>
</html>
