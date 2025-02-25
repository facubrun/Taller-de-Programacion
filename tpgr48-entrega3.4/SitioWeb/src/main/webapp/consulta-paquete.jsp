<%@ page import="logica.Datatypes.DataPaquete" %>
<%@ page import="logica.Datatypes.DataRutaVuelo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Paquetes</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body><jsp:include page="./header.jsp" />
	<jsp:include page="./side-menu.jsp" />
    <div class="main-page container col-6">
    	<div id="main-content" class="main-content">
        	<h2 class="mt-4">Paquetes agregados</h2>
        	<div id="listaPaquetes">
            <%
                ArrayList<DataPaquete> paquetes = (ArrayList<DataPaquete>) request.getAttribute("paquetes");
                if (paquetes == null) { 
            %>
                <p>No hay paquetes en el sistema.</p>
            <% 
                } else { 
            %>
            <div class="contenido-detalle">
                <ul class="list-group">
                    <% for (DataPaquete paquete : paquetes) { %>
                        <li class="list-group-item">
                            <p><strong>Nombre:</strong> <%= paquete.getNombre() %><p>
                            <p><strong>Descripcion:</strong> <%= paquete.getDescripcion() %><p>
                            <p><strong>Validez (dias):</strong> <%= paquete.getValidezDias() %><p>
                            <p><strong>Descuento:</strong> <%= paquete.getDescuento() %><p>
                            <p><strong>Costo:</strong> <%= paquete.getCosto() %><p>
                            <p><strong>Fecha de Alta:</strong> <%= paquete.getFechaAlta() %><p>
                            <ul class="list-group">
                            <% if (paquete.getRutasPaquete() != null) {  %>
                            <p><strong>Rutas de vuelo asociadas:</strong><br>
                            	<% for (DataRutaVuelo ruta : paquete.getRutasPaquete()) { %>
                            		<li class="list-group-item">
                            			<p><a href="./detalle-ruta-vuelo?ruta=<%= session.getAttribute("ruta") != null ? ruta.getNombre() : "" %>" class="btn btn-outline-primary">Mas información de esta ruta aquí</a> <%= ruta.getNombre() %> <p>
                            		</li>	
                            	<% } %>
                            </ul>
                            
                        </li>
                    <% }%>
                </ul>
                 <% } %>
            <% } %>
        </div>
    </div>
</body>
</html>
