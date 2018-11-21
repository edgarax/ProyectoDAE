<%-- 
    Document   : index
    Created on : 15/11/2018, 11:17:35 AM
    Author     : edgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
      <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
  <div class="login-wrap">
    <div class="login-html">
      <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Login</label>
      <div class="login-form">
          <form class="sign-in-htm" action="loginUsuario" method="POST">
	  <div class="group">
	    <label for="email" class="label">Email</label>
            <input id="emailSI" name="emailSI" type="text" class="input" required>
	  </div>
	  <div class="group">
	    <label for="pass" class="label">Cotraseña</label>
            <input id="passSI" name="passSI" type="password" class="input" data-type="password" required    >
	  </div>
	  <div class="group">
	    <input type="submit" class="button" value="Entrar">
	  </div>
	  <div class="hr"></div>
	</form>
      </div>
    </div>
  </div>
</body>
</html>
