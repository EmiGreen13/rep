<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Ошибка входа</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="all">
</head>
<body>

<div id="wrapper">
    <div id="menubar">
        <div id="menu">
            <ul>
                <li id = "menu0"><a href="index"><span>Главная</span></a></li>
                <li id = "menu1"><a href="timetable"><span>Расписание</span></a></li>
                <li id = "menu2"><a href="office"><span>Кабинет</span></a></li>
                <li id = "menu3"><a href="management"><span>Управление</span></a></li>
            </ul>
        </div>
    </div>

    <div class="main-box">

        <p>Вход не выполнен</p>
        <p>Неверный логин или пароль</p>

        <li>Дата и время: <fmt:formatDate value="${date}" type="both" dateStyle="long" timeStyle="long" />
        <li>URI: <c:out value="${requestScope['javax.servlet.forward.request_uri']}" />
        <li>Браузер: <c:out value="${header['user-agent']}" />

    </div>

</div>
</body>
</html>