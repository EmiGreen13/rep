<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Каталог препаратов</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/style1.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/operations.js"></script>
</head>

<script type="text/javascript">

    var message = '${message}';

    $(document).ready(function () {
        if (message.length != 0){
            exception(message);
        }
        selectAll();
    });
</script>

<body>
    <jsp:include page="Header.jsp"/>

    <section class="container">
        <div id = "categories"></div>

    </section>
</body>
</html>
