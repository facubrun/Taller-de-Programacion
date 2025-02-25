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
                <input id="input-search" type="text" class="search-bar-input" placeholder="Buscar...">
                <svg class="search-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path d="M0 0h24v24H0z" fill="none"/>
                    <path d="M15.5 14h-.79l-.28-.27a6.5 6.5 0 0 0 1.48-5.34c-.47-2.78-2.79-5-5.59-5.34a6.505 6.505 0 0 0-7.27 7.27c.34 2.8 2.56 5.12 5.34 5.59a6.5 6.5 0 0 0 5.34-1.48l.27.28v.79l4.25 4.25c.41.41 1.08.41 1.49 0 .41-.41.41-1.08 0-1.49L15.5 14zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/>
                </svg>
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

</body>
</html>
