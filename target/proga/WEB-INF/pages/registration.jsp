<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleLogin.css" media="screen" type="text/css">
    <title>Регистрация</title>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
</head>
<body>

<script type="text/javascript">
    $(document).ready(function(){
        var message = '${message}';
        if (message.length != 0){
            alert(message);
        }
    });
</script>

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

    <form id="loginForm" action="${pageContext.request.contextPath}/registration" method="post">

        <div class="field">
            <div class="input"><input type="text" placeholder="Имя пользователя" required name="username" value="" id="login" /></div>
        </div>

        <div class="field">
            <div class="input"><input type="password" placeholder="Пароль" required name="password" value="" id="pass" /></div>
        </div>

        <div class="field">
            <div class="input"><input type="password" placeholder="Пароль" required name="password2" value="" id="pass2" /></div>
        </div>

        <div class="field">
            <div class="input"><input type="email" placeholder="Email" required name="email" value="" id="email" /></div>
        </div>

        <div class="submit">
            <button type="submit">Регистрация</button>
        </div>

    </form>

    <div id="footer12"></div>

    </div>

</body>
</html>
