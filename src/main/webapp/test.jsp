<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: Vivien
  Date: 23/02/2016
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        Random rd = new Random();
        int chiffre = rd.nextInt();
    %>

    Chiffre N1 : <%=chiffre%> <br>
    Chiffre N2 : <span id="chiffre"></span> <br>

    <div>
        <button id="btn">MAJ CHIFFRE</button>
    </div>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>


    $( "#btn" ).click(function() {
        $.get( "./game", function( data ) {
            $( "#chiffre" ).html( data );
        });
    });


</script>

</html>
