<%@ page import="servidor.DataAerolinea" %>
<%@ page import="servidor.DataRutaVuelo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Perfil de la Aerolínea</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="./header.jsp" />
<jsp:include page="./side-menu.jsp" />
<div class="container mt-5 col-6">
    <h1 class="mb-4">Perfil de la Aerolínea</h1>

    <% 
        DataAerolinea aerolinea = (DataAerolinea) request.getAttribute("aerolinea");
        Boolean esPropioPerfil = (Boolean) request.getAttribute("esPropioPerfil");
        if (aerolinea != null) {
            String imagenUsuario = aerolinea.getImagen();
            String rutaImagen = request.getContextPath() + "/utils/images/" + imagenUsuario;
    %>
        <div class="card mb-3">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="<%= rutaImagen %>" class="img-fluid rounded-start" alt="Imagen de <%= aerolinea.getNombre() %>">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title"><%= aerolinea.getNombre() %></h5>
                        <p><strong>Email:</strong> <%= aerolinea.getEmail() %></p>
                        <p><strong>Nickname:</strong> <%= aerolinea.getNickname() %></p>
                        <p><strong>Descripción:</strong> <%= aerolinea.getDescripcion() %></p>
                        <p><strong>Web:</strong> <a href="<%= aerolinea.getWeb() %>" target="_blank"><%= aerolinea.getWeb() %></a></p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Tabs para Seguidos y Seguidores -->
        <ul class="nav nav-tabs" id="aerolineaTabs" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="seguidos-tab" data-bs-toggle="tab" data-bs-target="#seguidos" type="button" role="tab" aria-controls="seguidos" aria-selected="true">Seguidos</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="seguidores-tab" data-bs-toggle="tab" data-bs-target="#seguidores" type="button" role="tab" aria-controls="seguidores" aria-selected="false">Seguidores</button>
            </li>
        </ul>
        <div class="tab-content mt-3" id="aerolineaTabsContent">
            <!-- Lista de Seguidos -->
            <div class="tab-pane fade show active" id="seguidos" role="tabpanel" aria-labelledby="seguidos-tab">
                <ul class="list-group">
                    <% for (String seguido : aerolinea.getSeguidos()) { %>
                        <li class="list-group-item"><a href="profile?nickname=<%= seguido %>"><%= seguido %></a></li>
                    <% } %>
                </ul>
            </div>

            <!-- Lista de Seguidores -->
            <div class="tab-pane fade" id="seguidores" role="tabpanel" aria-labelledby="seguidores-tab">
                <ul class="list-group">
                    <% for (String seguidor : aerolinea.getSeguidores()) { %>
                        <li class="list-group-item"><a href="profile?nickname=<%= seguidor %>"><%= seguidor %></a></li>
                    <% } %>
                </ul>
            </div>
        </div>

        <h2 class="mt-5">Rutas de Vuelo</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Ciudad Origen</th>
                    <th>Ciudad Destino</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<DataRutaVuelo> rutas = aerolinea.getRutasVuelo();
                    if (rutas != null && !rutas.isEmpty()) {
                        for (DataRutaVuelo ruta : rutas) {
                            if (!esPropioPerfil && ruta.getEstado() != servidor.EstadoRutaVuelo.Confirmada) {
                                continue;
                            }
                %>
                    <tr>
                        <td><%= ruta.getNombre() %></td>
                        <td><%= ruta.getDescripcion() %></td>
                        <td><%= ruta.getCiudadOrigen() %></td>
                        <td><%= ruta.getCiudadDestino() %></td>
                        <td><%= ruta.getEstado().name() %></td>
                        <td>
                            <a href="detalle-ruta-vuelo?ruta=<%= ruta.getNombre() %>" class="btn btn-primary">Ver Detalle</a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="6">No se encontraron rutas de vuelo.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <% } else { %>
        <p>No se encontró la aerolínea.</p>
    <% } %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
