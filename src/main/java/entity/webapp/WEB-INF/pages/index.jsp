<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html">

<head>
    <title>Добро пожаловать</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/style1.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
</head>
<body>

<script type="text/javascript">
    var message = '${message}';
    $(document).ready(function () {
        if (message.length != 0){
            alert(message);
        }
    });
</script>

<jsp:include page="Header.jsp"/>


<div>
    <div id="content">
        <div id="header">
            <div class="mainline">Медицинские препараты</div>
            <div id="tagline">Для просмотра лекарственных препаратов и возможности заказа используйте меню</div>
        </div>
        <div id="header_right">
            <div class="header_content_boxline">О проекте
                <div class="header_content_boxcontent">
                    Проект интернет-магазина медицинских препаратов был создан для возможности заказа лекарственных препаратов
                </div>
            </div>
        </div>
        <div id="header_left">
            <div class="header_content_boxline">Контакты</div>
            <div class="header_content_boxcontent">
            </div>
        </div>
    </div>
</div>
</body>
</html>
