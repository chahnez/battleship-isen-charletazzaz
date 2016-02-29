<%@page import="java.awt.Point" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.charletazzaz.battleship.isen.Grid" %>
<%@page import="com.charletazzaz.battleship.isen.GridImpl" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <link href='https://fonts.googleapis.com/css?family=Indie+Flower' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container text-center">

    <h1>Isen Jee : Bataille Navale</h1>

    <hr/>

    <div class="row">

        <h2>Placez vos bateaux et cliquez sur Jouer </h2>

        <a href="random.jsp" class="btn btn-primary btn-lg">Placer les bateaux</a>
        <a href="jouer.jsp" class="btn btn-primary btn-lg">Jouer</a>

        <%
            Grid grid_me = new GridImpl();
            grid_me.putRandomShips();
            session.setAttribute("grid_me", grid_me);
            Grid grid_enemy = new GridImpl();
            grid_enemy.putRandomShips();
            session.setAttribute("grid_enemy", grid_enemy);
        %>

        <div class="battleship-grid">

            <% for (int i = 0; i < 10; i++) { %>
            <% for (int j = 0; j < 10; j++) { %>
            <%
                String c = "";
                if (grid_me.getShipsPositions().contains(new Point(i, j))) {
                    c = "boat";
                }
            %>
            <a href="<%=i + " " + j%>" class="<%=c%>"></a>
            <% } %>
            <% } %>

        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>