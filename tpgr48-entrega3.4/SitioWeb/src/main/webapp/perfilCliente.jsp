<%@ page import="java.util.List" %>
<%@ page import="servidor.DataCliente" %>
<%@ page import="servidor.DataReserva" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Perfil de Cliente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="./header.jsp" />
<jsp:include page="./side-menu.jsp" />
<div class="container mt-5 col-6">
    <h1 class="mb-4">Perfil del Cliente</h1>

    <% 
        DataCliente cliente = (DataCliente) request.getAttribute("cliente");
        Boolean esPropioPerfil = (Boolean) request.getAttribute("esPropioPerfil");
        if (cliente != null) {
            String imagenUsuario = cliente.getImagen();
            String rutaImagen = request.getContextPath() + "/utils/images/" + imagenUsuario;
    %>
        <div class="card mb-3">
        <% if (Boolean.TRUE.equals(esPropioPerfil)) { %>
        	<div class="d-flex justify-content-between">
			        <button class="btn btn-secondary" onclick="location.href='modificar-usuario.jsp?nickname=<%= cliente.getNickname() %>'">Editar</button>
			    </div>
        <% } %>
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="<%= rutaImagen %>" class="img-fluid rounded-start" alt="Imagen de <%= cliente.getNombre() %>">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title"><%= cliente.getNombre() %> <%= cliente.getApellido() %></h5>
                        <p class="card-text"><strong>Email:</strong> <%= cliente.getEmail() %></p>
                        <p class="card-text"><strong>Nickname:</strong> <%= cliente.getNickname() %></p>
                        <p class="card-text"><strong>Nacionalidad:</strong> <%= cliente.getNacionalidad() %></p>
                        <p class="card-text"><strong>Fecha de Nacimiento:</strong> <%= cliente.getNacimientoFormatted() %></p>
                        <p class="card-text"><strong>Documento:</strong> <%= cliente.getDocumento() %></p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Tabs para Seguidos y Seguidores -->
        <ul class="nav nav-tabs" id="clienteTabs" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="seguidos-tab" data-bs-toggle="tab" data-bs-target="#seguidos" type="button" role="tab" aria-controls="seguidos" aria-selected="true">Seguidos</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="seguidores-tab" data-bs-toggle="tab" data-bs-target="#seguidores" type="button" role="tab" aria-controls="seguidores" aria-selected="false">Seguidores</button>
            </li>
        </ul>
        <div class="tab-content mt-3" id="clienteTabsContent">
            <!-- Lista de Seguidos -->
            <div class="tab-pane fade show active" id="seguidos" role="tabpanel" aria-labelledby="seguidos-tab">
                <ul class="list-group">
                    <% for (String seguido : cliente.getSeguidos()) { %>
                        <li class="list-group-item"><a href="profile?nickname=<%= seguido %>"><%= seguido %></a></li>
                    <% } %>
                </ul>
            </div>

            <!-- Lista de Seguidores -->
            <div class="tab-pane fade" id="seguidores" role="tabpanel" aria-labelledby="seguidores-tab">
                <ul class="list-group">
                    <% for (String seguidor : cliente.getSeguidores()) { %>
                        <li class="list-group-item"><a href="profile?nickname=<%= seguidor %>"><%= seguidor %></a></li>
                    <% } %>
                </ul>
            </div>
        </div>

        <!-- Mostrar reservas solo si el cliente está viendo su propio perfil -->
        <% if (Boolean.TRUE.equals(esPropioPerfil)) { %>
            <h2 class="mt-5">Mis Reservas</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Vuelo</th>
                        <th>Fecha de Reserva</th>
                        <th>Asiento</th>
                        <th>Cantidad de Pasajes</th>
                        <th>Cant Extra Pasajes</th>
                        <th>Costo Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<DataReserva> reservas = cliente.getReservas();
                        if (reservas != null && !reservas.isEmpty()) {
                            for (DataReserva reserva : reservas) {
                    %>
                        <tr>
                            <td><%= reserva.getNomV() %></td>
                            <td><%= reserva.getFechaReservaFormatted() %></td>
                            <td><%= reserva.getTipoAsiento() %></td>
                            <td><%= reserva.getCantPasajes() %></td>
                            <td><%= reserva.getCantExtra() %></td>
                            <td><%= reserva.getCostoTotal() %></td>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="6">No se encontraron reservas.</td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <% } %>
    <% } else { %>
        <p>No se encontró el cliente.</p>
    <% } %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
