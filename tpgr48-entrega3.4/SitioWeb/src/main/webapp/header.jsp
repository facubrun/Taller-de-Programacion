<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="servidor.DataCliente" %>
<%@ page import="servidor.DataAerolinea" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/index.js" defer></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .search-results {
            margin-top: 10px;
            border: 1px solid #ccc;
            background-color: #fff;
            border-radius: 5px;
            max-height: 300px;
            overflow-y: auto;
            padding: 10px;
            display: none;
        }

        .result-item {
            border-bottom: 1px solid #eee;
            padding: 10px;
            transition: background-color 0.3s ease;
        }

        .result-item:last-child {
            border-bottom: none;
        }

        .result-item:hover {
            background-color: #f1f1f1;
        }

        .result-item a {
            text-decoration: none;
            color: #007bff;
        }

        .result-item a:hover {
            text-decoration: underline;
        }

        .result-item h4 {
            margin: 0;
            font-size: 1.2em;
        }

        .result-item p {
            margin: 5px 0;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<header id="header">
    <div class="main-header">
        <div id="index-logo" class="header-logo logo-gradient">
            <h1><a href="${pageContext.request.contextPath}/">Volando.uy</a></h1>
        </div>
        
<div class="search-bar">
    <input 
        id="input-search" 
        type="text" 
        class="search-bar-input" 
        placeholder="Buscar..."
        onkeydown="buscar(event)">
</div>


        
        <div id="user-buttons" class="user-buttons">
            <% 
                String tipoUsuario = (String) session.getAttribute("tipoUsuario");
                String nickname = (String) session.getAttribute("usuario");

                if (tipoUsuario == null) { 
            %>
                <a href="login" class="btn btn-outline-primary">Iniciar Sesión</a>
                <a href="registro_user" class="btn btn-primary">Registrarse</a>
            <% 
                } else if (tipoUsuario.equals("cliente")) { 
                    if (nickname != null) { 
            %>
                <a href="profile?nickname=<%= nickname %>" class="btn btn-outline-primary">Perfil</a>
            <% 
                    } 
            %>
                <a href="logout" class="btn btn-danger">Desconectar</a>
            <% 
                } else if (tipoUsuario.equals("aerolinea")) { 
            %>
                <a href="logout" class="btn btn-danger">Desconectar</a>
            <% 
                } 
            %>
        </div>
    </div>
</header>
<script>
    function buscar(event) {
        if (event.key === "Enter") { // Detecta la tecla Enter
            const query = document.getElementById('input-search').value.trim();
            if (query !== "") {
                // Redirige al Servlet con el parámetro query
                window.location.href = "resultados-busqueda?query=" + encodeURIComponent(query);
            }
        }
    }
</script>


</body>
</html>
