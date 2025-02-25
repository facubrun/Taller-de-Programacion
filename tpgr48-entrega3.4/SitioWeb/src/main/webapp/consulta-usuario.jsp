<%@ page import="java.util.List" %>
<%@ page import="servidor.DataCliente" %>
<%@ page import="servidor.DataAerolinea" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Consulta de Usuarios</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Estilo para caja de búsqueda */
        #searchInput {
            max-width: 500px;
        }
        /* Espaciado adicional para la lista */
        .list-group-item {
            margin-bottom: 10px;
        }
        .user-item-hidden {
    display: none !important;
}
.user-item-visible {
    display: block !important;
}
    </style>
</head>
<body>

    <jsp:include page="./header.jsp" />
    <jsp:include page="./side-menu.jsp" />

    <div class="container col-6">
        <h2 class="mt-4">Consulta de Usuarios</h2>
        
        <!-- Caja de búsqueda -->
        <div class="form-group">
            <label for="searchInput">Buscar por nombre o tipo de usuario:</label>
            <input type="text" id="searchInput" class="form-control" placeholder="Buscar...">
        </div>

        <!-- Lista de usuarios -->
        <div id="usuariosList" class="list-group mt-3">
            <%
                List<DataCliente> clientes = (List<DataCliente>) request.getAttribute("clientes");
                List<DataAerolinea> aerolineas = (List<DataAerolinea>) request.getAttribute("aerolineas");
                List<String> usuariosSeguidos = (List<String>) request.getAttribute("usuariosSeguidos");
                String usuarioLogueado = (String) session.getAttribute("usuario");
                String tipoUsuario = (String) session.getAttribute("tipoUsuario");

                if ((clientes == null || clientes.isEmpty()) && (aerolineas == null || aerolineas.isEmpty())) {
            %>
                <p class="text-danger">No hay usuarios registrados en el sistema.</p>
            <% 
                } else {
                    if (clientes != null && !clientes.isEmpty()) { 
            %>
                <% 
                        for (DataCliente cliente : clientes) { 
                %>
                    <div class="list-group-item user-item d-flex justify-content-between align-items-center">
					    <div>
					        <a href="profile?nickname=<%= cliente.getNickname() %>" class="text-primary">
					            <strong><%= cliente.getNombre() %> <%= cliente.getApellido() %></strong> (Cliente)
					        </a>
					    </div>
					    <% if (usuarioLogueado != null && !usuarioLogueado.equals(cliente.getNickname())) { %>
					        <form method="get" action="consulta-usuario" class="mb-0">
					            <input type="hidden" name="nickname" value="<%= cliente.getNickname() %>" />
					            <% if (usuariosSeguidos != null && usuariosSeguidos.contains(cliente.getNickname())) { %>
					                <input type="hidden" name="action" value="dejar" />
					                <button type="submit" class="btn btn-danger btn-sm float-end">Dejar de seguir</button>
					            <% } else { %>
					                <input type="hidden" name="action" value="seguir" />
					                <button type="submit" class="btn btn-success btn-sm float-end">Seguir</button>
					            <% } %>
					        </form>
					    <% } %>
					</div>
                <% 
                        } 
                    }
                    if (aerolineas != null && !aerolineas.isEmpty()) { 
                %>
                <% 
                        for (DataAerolinea aerolinea : aerolineas) { 
                %>
                    <div class="list-group-item user-item d-flex justify-content-between align-items-center">
					    <div>
					        <a href="profile_aerolinea?nickname=<%= aerolinea.getNickname() %>" class="text-primary">
					            <strong><%= aerolinea.getNombre() %></strong> (Aerolínea)
					        </a>
					    </div>
					    <% if (usuarioLogueado != null && !usuarioLogueado.equals(aerolinea.getNickname())) { %>
					        <form method="get" action="consulta-usuario" class="mb-0">
					            <input type="hidden" name="nickname" value="<%= aerolinea.getNickname() %>" />
					            <% if (usuariosSeguidos != null && usuariosSeguidos.contains(aerolinea.getNickname())) { %>
					                <input type="hidden" name="action" value="dejar" />
					                <button type="submit" class="btn btn-danger btn-sm float-end">Dejar de seguir</button>
					            <% } else { %>
					                <input type="hidden" name="action" value="seguir" />
					                <button type="submit" class="btn btn-success btn-sm float-end">Seguir</button>
					            <% } %>
					        </form>
					    <% } %>
					</div>
                <% 
                        } 
                    }
                } 
            %>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <!-- Filtro de búsqueda -->
    <script>
    document.addEventListener('DOMContentLoaded', function () {
        const searchInput = document.getElementById('searchInput'); // Campo de búsqueda
        const userItems = document.querySelectorAll('.user-item'); // Selecciona todos los usuarios

        searchInput.addEventListener('keyup', function () {
            const searchTerm = this.value.toLowerCase().trim(); // Convierte el término de búsqueda a minúsculas

            userItems.forEach(item => {
                const userName = item.textContent.toLowerCase(); // Obtén el contenido de texto del usuario

                // Si el término de búsqueda está incluido, muestra el elemento, si no, ocúltalo
                item.style.display = userName.includes(searchTerm) ? '' : 'none';
            });
        });
    });
</script>


</body>
</html>
