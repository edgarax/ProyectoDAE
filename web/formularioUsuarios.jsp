<%-- 
    Document   : formularioUsuarios
    Created on : 15/11/2018, 10:08:18 PM
    Author     : edgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <link rel="stylesheet" href="resources/css/formularios.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="nave">
            <jsp:include page="navegacion.jsp"/>
        </div>
        <div id="container">
            <h1>&bull; Usuarios &bull;</h1>
            <div class="underline">
            </div>
            <div class="icon_wrapper">
                <i class="fa fa-users" aria-hidden="true"></i>
            </div>
            <form action="#" method="post" id="contact_form">
                <div class="id">
                    <label for="id"></label>
                    <input type="text" placeholder="Id" name="id" id="id_input">
                </div>
                <div class="name">
                    <label for="name"></label>
                    <input type="text" placeholder="Nombre(s)" name="name" id="name_input" required>
                </div>
                <div class="lastName">
                    <label for="lastName"></label>
                    <input type="text" placeholder="Apellidos" name="lastName" id="lastName_input" required>
                </div>
                <div class="rol">
                    <label for="rol"></label>
                    <input type="text" placeholder="Rol" name="rol" id="rol_input" required>
                </div>
                <div class="telephone">
                    <label for="telephone"></label>
                    <input type="text" placeholder="Telefono" name="telephone" id="telephone_input" required>
                </div>
                <div class="email">
                    <label for="email"></label>
                    <input type="email" placeholder="Email" name="email" id="email_input" required>
                </div>
                <div class="password">
                    <label for="password"></label>
                    <input type="password" placeholder="Contraseña" name="password" id="password_input" required>
                </div>
                <!--<div class="subject">
                    <label for="subject"></label>
                    <select placeholder="Subject line" name="subject" id="subject_input" required>
                        <option disabled hidden selected>Subject line</option>
                        <option>I'd like to start a project</option>
                        <option>I'd like to ask a question</option>
                        <option>I'd like to make a proposal</option>
                    </select>
                </di>-->
                <div class="dateC">
                    <label for="dateC"></label>
                    <input type="text" placeholder="fecha de creacion" name="dateC" id="dateC_input">
                </div>
                <div class="userC">
                    <label for="userC"></label>
                    <input type="text" placeholder="usuario Creador" name="userC" id="userC_input">
                </div>
                <div class="dateU">
                    <label for="dateU"></label>
                    <input type="text" placeholder="fecha de ultima modificación" name="dateU" id="dateU_input">
                </div>
                <div class="userU">
                    <label for="userU"></label>
                    <input type="text" placeholder="usuario de ultima modificación" name="userU" id="userU_input">
                </div>
                <div class="submit">
                    <input type="submit" name="accion" value="Alta" id="alta_button" />
                    <input type="submit" name="accion" value="Baja" id="baja_button" />
                    <input type="submit" name="accion" value="Modificar" id="modific_button" />
                    <input type="submit" name="accion" value="Consultar" id="consultar_button" />
                </div>
            </form><!-- // End form -->
        </div><!-- // End #container -->
    </body>
</html>
