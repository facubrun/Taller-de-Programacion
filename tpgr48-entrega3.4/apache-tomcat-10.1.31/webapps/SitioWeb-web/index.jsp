<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="servidor.DataRutaVuelo" %>
<%@ page import="servidor.EstadoRutaVuelo" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>

<head>
	<title>Volando.uy</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<script src="${pageContext.request.contextPath}/js/index.js" defer></script>
</head>

<body>
	<jsp:include page="./header.jsp" />
	<jsp:include page="./side-menu.jsp" />
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