<%-- 
    Document   : navegacion
    Created on : 16/11/2018, 12:39:29 AM
    Author     : edgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">		
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">		
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,600|Open+Sans" rel="stylesheet"> 
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
	<link rel="stylesheet" href="resources/css/navegacion.css">		
		
	<title>Menu de Navegación</title>
</head>
<header style="top: 0;">
	<nav class="nav">
		<ul style="padding-top: 20px;">
			<li><a href="#">INICIO</a></li>
			<li><a href="formularioUsuarios.jsp">USUARIOS</a></li>
			<li><a href="formularioMovimientos.jsp">MOVIMIENTOS</a></li>
			<li><a href="formularioProductos.jsp">PRODUCTOS</a></li>						
			</li>
		</ul>
	</nav>
	

	<div class="dropdown">
	  <ul class="nav nav-pills" style="padding-top: 30px;">
  		<li class="nav-item dropdown">
    		<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">SESION</a>
    <div class="dropdown-menu" x-placement="bottom-start">
    	 <li><a class="dropdown-item" href="#" id="btn-abrir-popup">Mis Movimientos</a></li>    	
    </div>
		</li>
	  </ul>
	</div>
</header>

<body>

</body>
</html>
