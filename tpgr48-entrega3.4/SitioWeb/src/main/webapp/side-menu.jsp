<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="servidor.DataCliente" %>
<%@ page import="servidor.DataAerolinea" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Menu Lateral</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/side-menu.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div id="main-side-menu" class="side-menu">
        <ul class="list-unstyled ps-0">
            <div id="perfil-submenu-div" class="div-submenu">
                <li class="mb-1">
                <%
				    Object usuario = session.getAttribute("usuario");
				    String nickname = "";
				    if (usuario instanceof DataCliente) {
				        nickname = ((DataCliente) usuario).getNickname();  
				    } else if (session.getAttribute("tipoUsuario") != null && "aerolinea".equals(session.getAttribute("tipoUsuario"))) {
				        nickname = (String) usuario;
				    }
				%>
                            <% 
                    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
                    if ("cliente".equals(tipoUsuario)) { 
                %>
                            <p class="subtitle"><a href="profile?nickname=<%= session.getAttribute("usuario") %>" target="_PARENT">Mi perfil</a></p>                    <div id="perfil-submenu" class="submenu">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                                <li><a href="./reserva-vuelo" class="link-dark rounded" target="_PARENT">Reservar Vuelo</a></li>
			                    <li><a href="./consulta-reserva" class="link-dark rounded" target="_PARENT">Mis Reservas</a></li>
			                    <li><a href="./consulta-usuario" class="link-dark rounded" target="_PARENT">Consultar Usuarios</a></li>
			                    <li><a href="./consulta-ruta-vuelo" class="link-dark rounded" target="_PARENT">Consultar Rutas de Vuelo</a></li>
			                    <li><a href="./consulta-vuelos" class="link-dark rounded" target="_PARENT">Consultar Vuelos</a></li>
			                    <li><a href="./consulta-check-in" class="link-dark rounded" target="_PARENT">Consultar CheckIn</a></li>
                            <% } else if ("aerolinea".equals(tipoUsuario)) { %>
                            <p class="subtitle"><a href="profile_aerolinea?nickname=<%= nickname %>" target="_PARENT">Mi perfil</a></p>                    <div id="perfil-submenu" class="submenu">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                                <li><a href="./alta-ruta-vuelo" class="link-dark rounded" target="_PARENT">Nueva Ruta</a></li>
			                    <li><a href="./consulta-ruta-vuelo" class="link-dark rounded" target="_PARENT">Consultar Rutas de Vuelo</a></li>
			                    <li><a href="./alta-vuelo" class="link-dark rounded" target="_PARENT">Nuevo Vuelo</a></li>
			                    <li><a href="./consulta-vuelos" class="link-dark rounded" target="_PARENT">Consultar Vuelos</a></li>
			                    <li><a href="./consulta-usuario" class="link-dark rounded" target="_PARENT">Consultar Usuarios</a></li>
			                    <li><a href="./consulta-reserva" class="link-dark rounded" target="_PARENT">Consultar Reservas</a></li>
                            <% } else { %>
                            <p class="subtitle">Acciones</p>                    <div id="perfil-submenu" class="submenu">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                                <li><a href="./consulta-usuario" class="link-dark rounded" target="_PARENT">Consultar Usuarios</a></li>
                                <li><a href="./consulta-ruta-vuelo" class="link-dark rounded" target="_PARENT">Consultar Rutas de Vuelo</a></li>
                                <li><a href="./consulta-vuelos" class="link-dark rounded" target="_PARENT">Consultar Vuelos</a></li>
                        	<% } %>
                        </ul>
                    </div>
                </li>
            </div>
        </ul>
    </div>
</body>
</html>
