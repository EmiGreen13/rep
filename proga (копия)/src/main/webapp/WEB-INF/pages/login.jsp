<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleLogin.css" media="screen" type="text/css">
    <title>Авторизация</title>
</head>
<body>

<div id="wrapper">
    <div id="menubar">
        <div id="menu">
            <ul>
                <li id = "menu0"><a href="${pageContext.request.contextPath}/index"><span>Главная</span></a></li>
                <li id = "menu1"><a href="${pageContext.request.contextPath}/tickets"><span>Билеты</span></a></li>
                <li id = "menu2"><a href="${pageContext.request.contextPath}/office"><span>Кабинет</span></a></li>
                <li id = "menu3"><a href="${pageContext.request.contextPath}/management"><span>Управление</span></a></li>
                <c:if test="${pageContext.request.userPrincipal == null}">
                    <li id = "menu4"><a href="${pageContext.request.contextPath}/registration"><span>Регистрация</span></a></li>
                </c:if>
            </ul>
        </div>
    </div>

    <form id="loginForm" action="j_security_check" method="post">

        <div class="field">
            <div class="input"><input type="text" placeholder="Имя пользователя" required name="j_username" value="" id="login" /></div>
        </div>

        <div class="field">
            <div class="input"><input type="password" placeholder="Пароль" required name="j_password" value="" id="pass" /></div>
        </div>

        <div class="submit">
            <button type="submit">Войти</button>
        </div>

    </form>



    <div id="footer12"></div>
</div>

</body>
</html>
