<%-- 
    Document   : formulatioMovimientos
    Created on : 15/11/2018, 11:36:10 PM
    Author     : edgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link rel="stylesheet" href="resources/css/formularios.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="nave">
            <jsp:include page="navegacion.jsp"/>
        </div>
        <div id="container">
            <h1>&bull; Movimientos &bull;</h1>
            <div class="underline">
            </div>
            <div class="icon_wrapper">
                <i class="fa fa-arrows-alt" aria-hidden="true"></i>
            </div>
            <form action="#" method="post" id="contact_form">
                <div class="id">
                    <label for="id"></label>
                    <input type="text" placeholder="id Movimiento" name="id" id="id_input">
                </div>
                <div class="descripcion">
                    <label for="descripcion"></label>
                    <input type="text" placeholder="Deecripcion" name="descripcion" id=descripcion_input" required>
                </div>
                <div class="fecha">
                    <label for="unidad"></label>
                    <input type="text" placeholder="Unidad" name="unidad" id="unidad_input" required>
                </div>
                <div class="tipo">
                    <label for="tipo"></label>
                    <input type="text" placeholder="Tipo" name="tipo" id="tipo_input" required>
                </div>
                <div class="idUsuario">
                    <label for="idUsuario"></label>
                    <input type="text" placeholder="Id Usuario" name="idUsuario" id="idUsuario_input">
                </div>
                <div class="idCliente">
                    <label for="idCliente"></label>
                    <input type="text" placeholder="id Cliente" name="idCliente" id="idCliente_input" required>
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
