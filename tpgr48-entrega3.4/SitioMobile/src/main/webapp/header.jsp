<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Volando.uy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/index.js" defer></script>
    <style>
        .navbar-brand {
            font-size: 1.5rem;
            font-weight: bold;
            color: #6c63ff;
        }
        .navbar-toggler-icon {
            background-color: #6c63ff;
        }
        .navbar-nav .nav-item .nav-link {
            color: #6c63ff;
        }
        .navbar-nav .nav-item .nav-link:hover {
            color: #ffa763;
        }
        .dropdown-menu {
            width: 100%; /* Adaptar el men� desplegable a m�viles */
        }
    </style>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">Volando.uy</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav me-auto">
                        <% 
                            String tipoUsuario = (String) session.getAttribute("tipoUsuario");
                            String nickname = (String) session.getAttribute("usuario");
                        %>
                        <% if ("cliente".equals(tipoUsuario)) { %>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="userMenu" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Mi perfil
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="userMenu">
                                    <li><a class="dropdown-item" href="profile?nickname=<%= nickname %>">Mi perfil</a></li>
                                    <li><a class="dropdown-item" href="./consulta-reserva">Mis Reservas</a></li>
                                    <li><a class="dropdown-item" href="./check-in">Realizar Check-in</a></li>
                                    <li><a class="dropdown-item" href="./consulta-ruta-vuelo">Consultar Rutas de Vuelo</a></li>
                                    <li><a class="dropdown-item" href="./consulta-vuelos">Consultar Vuelos</a></li>
                                </ul>
                            </li>
                        <% } %>
                    </ul>
                    <% if (tipoUsuario == null) { %>
                        <a href="login" class="btn btn-outline-primary">Iniciar Sesion</a>
                    <% } else if ("cliente".equals(tipoUsuario)) { %>
                        <a href="profile?nickname=<%= nickname %>" class="btn btn-outline-primary me-2">Perfil</a>
                        <a href="logout" class="btn btn-danger">Desconectar</a>
                    <% } %>
                </div>
            </div>
        </nav>
    </header>

    <!-- Espaciador para evitar superposici�n del contenido con el navbar -->
    <div style="margin-top: 60px;"></div>

    <!-- Cerrar autom�ticamente el men� desplegable al hacer clic -->
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const navLinks = document.querySelectorAll('.dropdown-item');
            const navbarCollapse = document.getElementById('navbarNav');

            navLinks.forEach(link => {
                link.addEventListener('click', () => {
                    const bsCollapse = new bootstrap.Collapse(navbarCollapse, {
                        toggle: false
                    });
                    bsCollapse.hide(); // Cierra el men� colapsado
                });
            });
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
