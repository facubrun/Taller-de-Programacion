<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="servidor.DataRutaVuelo" %>
<%@ page import="servidor.EstadoRutaVuelo" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Resultados de Búsqueda - Volando.uy</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/index.js" defer></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <jsp:include page="./header.jsp" />
    <jsp:include page="./side-menu.jsp" />
    
    <div class="main-page">
        <div id="main-content" class="main-content">
            <h1>Resultados de la Búsqueda</h1>
            <%
                String query = (String) request.getAttribute("query");
                List<DataRutaVuelo> rutas = (List<DataRutaVuelo>) request.getAttribute("rutas");
            %>
            <p>Resultados para: <strong><%= query %></strong></p>
            
            <% if (rutas != null && !rutas.isEmpty()) { %>
                <% for (DataRutaVuelo ruta : rutas) { 
                    if (ruta.getEstado() == EstadoRutaVuelo.Confirmada) { 
                %>
                <div class="fila-viaje mb-4 p-3 border rounded" onclick="window.location='./detalle-ruta-vuelo?ruta=<%=ruta.getNombre()%>';">
                    <div class="row g-3">
                        <div class="col-md-4">
                            <img src="${pageContext.request.contextPath}/utils/images/<%=ruta.getImagen()%>" alt="Foto del viaje" class="img-fluid rounded">
                        </div>
                        <div class="col-md-8">
                            <h2 class="titulo-viaje"><%=ruta.getDescCorta()%></h2>
                            <p class="descripcion-viaje"><%=ruta.getDescripcion()%></p>
                            <p class="detalles-viaje">
                                <strong>Hora de salida:</strong> <%=ruta.getHoraFormatted()%> |
                                <strong>Costo Turista:</strong> $<%=ruta.getCostoTurista()%> |
                                <strong>Estado:</strong> <%=ruta.getEstado()%>
                            </p>
                        </div>
                    </div>
                </div>
                <% } %>
                <% } %>
            <% } else { %>
                <p class="text-danger">No se encontraron resultados para la búsqueda realizada.</p>
            <% } %>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
