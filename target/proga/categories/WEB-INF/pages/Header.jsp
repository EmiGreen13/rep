<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Категории медпрепаратов</title>
        <link href="${pageContext.request.contextPath}/css/style1.css" rel="stylesheet" type="text/css">
    </head>

<body>
<div id="TopBar">

    <div id="logo">
        <a href="index"><img src="images/hearts for hope logo.png" alt="" width="360px" height="
        100px" class="img1" /></a>
    </div>

    <div id="menu">
        <div id="navigation" >
            <ul>
                <c:if test="${pageContext.request.remoteUser != null}">
                    <li><a href="../j_security_logout">Здравствуйте, ${pageContext.request.remoteUser}</a></li>
                </c:if>
                <li><a href="../index">Главная</a></li>


                <c:if test="${pageContext.request.remoteUser != null}">
                    <li class="last"><a href="../j_security_logout">Выход</a></li>
                </c:if>
                <c:if test="${pageContext.request.remoteUser == null}">
                    <li class="last"><a href="../login">Вход</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>