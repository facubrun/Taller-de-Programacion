<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="servidor.DataRutaVuelo" %>
<%@ page import="servidor.EstadoRutaVuelo" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Volando.uy</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/header-load.js" defer></script>
    <script src="${pageContext.request.contextPath}/utils/constants.js" defer></script>
	<script src="${pageContext.request.contextPath}/js/index.js" defer></script>
	
	<style>
    		@media (min-width: 992px) {
		.main-page {
		    margin-left: 300px;
		}
		}
	</style>
</head>

<body>
	<jsp:include page="./header.jsp" />
	<div class="main-page">
		<div id="main-content" class="main-content">
			<% 
			List<DataRutaVuelo> rutas = (List<DataRutaVuelo>) request.getAttribute("rutas");
			if (rutas != null) {
 
				for (DataRutaVuelo ruta : rutas) { 
					if (ruta.getEstado() == EstadoRutaVuelo.Confirmada) {
			%>
					<div class="fila-viaje" onclick="window.location='./detalle-ruta-vuelo?ruta=<%=ruta.getNombre()%>';">
						<div class="contenedor-imagen-viaje">
							<img src="${pageContext.request.contextPath}/utils/images/<%=ruta.getImagen()%>" alt="Foto del viaje" class="imagen-viaje">
						</div>
						<div class="informacion-viaje">
							<h2 class="titulo-viaje"><%=ruta.getDescCorta()%></h2>
							<p class="descripcion-viaje"><%=ruta.getDescripcion()%></p>
							<p class="detalles-viaje">
								Hora de salida: <%=ruta.getHoraFormatted()%> | Costo Turista: $<%=ruta.getCostoTurista()%> | Estado: <%=ruta.getEstado()%>
							</p>
						</div>
					</div>

			<% } 
			}
			}%>
		</div>
	</div>
</body>

</html>