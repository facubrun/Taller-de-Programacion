<%@ page import="java.util.List" %>
<%@ page import="servidor.DataCliente" %>
<%@ page import="servidor.DataReserva" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Perfil de Cliente</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/index.js" defer></script>

</head>
<body>
<jsp:include page="./header.jsp" />
<div class="container mt-5">
    <h1 class="mb-4 text-center">Perfil del Cliente</h1>

    <% 
        DataCliente cliente = (DataCliente) request.getAttribute("cliente");
        Boolean esPropioPerfil = (Boolean) request.getAttribute("esPropioPerfil");
        if (cliente != null) {
            String imagenUsuario = cliente.getImagen();
            String rutaImagen = request.getContextPath() + "/utils/images/" + imagenUsuario;
    %>
        <div class="card mb-4 shadow-sm">
            <div class="row g-0 align-items-center">
                <div class="col-lg-4 col-md-5 col-sm-12 text-center">
                    <img src="<%= rutaImagen %>" class="img-fluid rounded-start w-75" alt="Imagen de <%= cliente.getNombre() %>">
                </div>
                <div class="col-lg-8 col-md-7 col-sm-12">
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
        <ul class="nav nav-tabs mb-4" id="clienteTabs" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="seguidos-tab" data-bs-toggle="tab" data-bs-target="#seguidos" type="button" role="tab" aria-controls="seguidos" aria-selected="true">Seguidos</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="seguidores-tab" data-bs-toggle="tab" data-bs-target="#seguidores" type="button" role="tab" aria-controls="seguidores" aria-selected="false">Seguidores</button>
            </li>
        </ul>

        <!-- Mostrar reservas solo si el cliente está viendo su propio perfil -->
        <% if (Boolean.TRUE.equals(esPropioPerfil)) { %>
            <h2 class="mt-4 text-center">Mis Reservas</h2>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
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
                                <td colspan="6" class="text-center">No se encontraron reservas.</td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        <% } %>
    <% } else { %>
        <p class="text-center text-danger">No se encontró el cliente.</p>
    <% } %>
</div>
</body>
</html>
