<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-03-26
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Lesson Page</title>
</head>
<body>
<form:form action="/panelAdmin/lessonList/lessonE/" modelAttribute="lesson" method="POST">
    <form:input type="hidden" path="lessonId"/><br>
    Data<form:input type="date" path="date" /><br>
    Godzina rozpoczęcia: <form:input type="number" path="startHour"/><br>
    Godzina zakończenia: <form:input type="number" path="finishHour"/><br>
    Instruktor :    <form:select path="instructor">
                        <form:options label="Wybierz" items="${insOpt}" itemValue="userId" itemLabel="fullName"/>
                    </form:select><br>
    Student:        <form:select path="student">
                        <form:options label="Wybierz" items="${stuOpt}" itemValue="userId" itemLabel="fullName"/>
                    </form:select><br>

    <input type="submit" name="submit" value="Zapisz"/>
</form:form>

<br>
<jsp:include page="../bottomMenu.jsp"/>
</body>
</html>
