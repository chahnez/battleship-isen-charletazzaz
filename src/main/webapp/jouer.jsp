<%@page import="java.util.Random" %>
<%@page import="java.awt.Point" %>
<%@page import="com.charletazzaz.battleship.isen.Grid" %>
<%@page import="com.charletazzaz.battleship.isen.GridImpl" %>
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
    <link rel="stylesheet" type="text/css" href="css/sweetalert.css">
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

        <a href="random.jsp" class="btn btn-primary btn-lg">Nouvelle Partie</a>

        <%
            Grid grid_me = (GridImpl) session.getAttribute("grid_me");
            Grid grid_enemy = (GridImpl) session.getAttribute("grid_enemy");

            String click_i = request.getParameter("i");
            String click_j = request.getParameter("j");
            String action = request.getParameter("action");

            if (action != null && action.equals("click")) {
                Random rd = new Random();
                grid_me.play(new Point(rd.nextInt(10), rd.nextInt(10)));
                grid_enemy.play(new Point(Integer.parseInt(click_i), Integer.parseInt(click_j)));
            }

        %>

        <div class="row">
            <div class="col-md-6">
                <h3>Votre grille</h3>
                <div class="battleship-grid">
                    <% for (int i = 0; i < 10; i++) { %>
                    <% for (int j = 0; j < 10; j++) { %>
                    <%
                        StringBuilder c = new StringBuilder();
                        if (grid_me.getShipsPositions().contains(new Point(i, j))) {
                            c.append("boat ");
                        }
                        if (grid_me.isPlayed(new Point(i, j))) {
                            c.append("hit ");
                        }
                    %>
                    <a href="#" class="<%=c%>"></a>
                    <% } %>
                    <% } %>
                </div>
            </div>
            <div class="col-md-6">
                <h3>Grille adverse</h3>
                <div class="battleship-grid">
                    <% for (int i = 0; i < 10; i++) { %>
                    <% for (int j = 0; j < 10; j++) { %>
                    <%
                        StringBuilder c = new StringBuilder();
                        if (grid_enemy.isPlayed(new Point(i, j))) {
                            if (grid_enemy.getShipsPositions().contains(new Point(i, j))) {
                                c.append("hit boat");
                            } else {
                                c.append("hit");
                            }
                        }
                    %>
                    <a href="?action=click&i=<%=i + "&j=" + j%>" class="<%=c%>"></a>
                    <% } %>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/sweetalert.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
    <% if(grid_me.isWin()) { %>
        swal({
            title: "Perdu !",
            text: "Retentez votre chance ...",
            imageUrl: "img/thumbs-down-icon.png",
            confirmButtonColor: "#8f7a66",
            confirmButtonText: "Nouvelle Partie",
            closeOnConfirm: false
        }, function () {
            window.location.href = "./random.jsp";
        });
    <% } %>
    <% if(grid_enemy.isWin()) { %>
        swal({
            title: "Bravo !",
            text: "Vous avez gagn\351",
            imageUrl: "img/thumbs-up-icon.png",
            confirmButtonColor: "#8f7a66",
            confirmButtonText: "Nouvelle Partie",
            closeOnConfirm: false
        }, function () {
            window.location.href = "./random.jsp";
        });
    <% } %>
</script>
</body>
</html>