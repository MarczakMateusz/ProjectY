<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-03-19
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Instructor Registration Form</title>
</head>
<body>
Login<form:input type="text" path="login"/><br>
Hasło<form:input type="text" path="pass"/><br>

Imię<form:input type="text" path="name"/><br>
Nazwisko<form:input type="text" path="surname"/><br>
Telefon<form:input type="text" path="phone"/><br>
Email<form:input type="text" path="email"/><br>
Pesel<form:input type="text" path="pesel"/><br>
Numer ewidencyjny<form:input type="text" path="regNum"/><br>

Ulica<form:input type="text" path="street"/><br>
Kod pocztowy<form:input type="text" path="postCode"/><br>
Miasto<form:input type="text" path="city"/><br>

<input type="submit" name="submit" value="Zarejestruj"/>
</body>
</html>

private String eMail;
private String pesel;
private String instNumber;
private String street;
private String postCode;
private String city;