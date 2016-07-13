<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Препараты-2</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/style1.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/operations.js"></script>
</head>
<body>
<script type="text/javascript">
var categoryId = '${categoryId}';
var message = '${message}';

$(document).ready(function () {
    if (message.length != 0){
        exception(message);
    }
    addForm();
});
</script>

<jsp:include page="Header.jsp"/>


<div>
    <div id="content">
        <div id="header">
            <div id = "message"></div>
            <div id = "search"></div>

            <div id = "out"></div>
        </div>

        <div id="header_right">



        </div>
        <div id="header_left">



        </div>
    </div>
</div>
</body>
</html>
