<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="servidor.DataRutaVuelo" %>
<%@ page import="servidor.DataVuelo" %>
<%@ page import="servidor.DataReserva" %>
<%@ page import="servidor.EstadoRutaVuelo" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <title>Volando.uy | Detalle de ruta de vuelo</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/detalle-ruta-vuelo.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" defer></script>
</head>

<body>
    <jsp:include page="./header.jsp" />
    <jsp:include page="./side-menu.jsp" />

    <div class="main-page">
        <div id="main-content" class="main-content">
            <% 
                DataRutaVuelo ruta = (DataRutaVuelo) request.getAttribute("ruta");
                Boolean puedeFinalizar = (Boolean) request.getAttribute("puedeFinalizar");
                String mensaje = (String) request.getAttribute("mensaje");
                String error = (String) request.getAttribute("error");
            %>

            <!-- Mensajes de éxito o advertencia -->
            <% if (mensaje != null) { %>
                <div class="alert alert-success" role="alert">
                    <%= mensaje %>
                </div>
            <% } %>
            <% if (error != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= error %>
                </div>
            <% } %>

            <!-- Detalle de la Ruta de Vuelo -->
            <h1><%= ruta.getDescCorta() %></h1>
            <div class="detalle-vuelo">
                <div class="contenido-detalle">
                    <div class="imagen-container">
                        <img src="${pageContext.request.contextPath}/utils/images/<%= ruta.getImagen() %>" alt="Imagen del viaje" class="imagen-detalle">
                    </div>
                    <div class="info-detalle">
                        <p><strong>Origen:</strong> <%= ruta.getCiudadOrigen() %></p>
                        <p><strong>Destino:</strong> <%= ruta.getCiudadDestino() %></p>
                        <p><strong>Hora de salida:</strong> <%= ruta.getHoraFormatted() %></p>
                        <p><strong>Fecha de ingreso:</strong> <%= ruta.getFechaAltaFormatted() %></p>
                        <p><strong>Estado:</strong> <%= ruta.getEstado() %></p>
                        <p><strong>Categorías:</strong> <%= String.join(", ", ruta.getCategorias()) %></p>
                        <p><strong>Costos:</strong></p>
                        <ul>
                            <li>Turista: $<%= ruta.getCostoTurista() %></li>
                            <li>Ejecutivo: $<%= ruta.getCostoEjecutivo() %></li>
                            <li>Equipaje extra: $<%= ruta.getCostoExtra() %></li>
                        </ul>
                    </div>
                </div>
            </div>
            
            <% 
			    String videoUrl = ruta.getVideo();
			    if (videoUrl.contains("youtu.be")) {
			        String videoId = videoUrl.substring(videoUrl.lastIndexOf("/") + 1, videoUrl.contains("?") ? videoUrl.indexOf("?") : videoUrl.length());
			        videoUrl = "https://www.youtube.com/embed/" + videoId;
			    } else if (videoUrl.contains("watch?v=")) {
			        String videoId = videoUrl.substring(videoUrl.indexOf("watch?v=") + 8, videoUrl.contains("&") ? videoUrl.indexOf("&") : videoUrl.length());
			        videoUrl = "https://www.youtube.com/embed/" + videoId;
			    }
			%>
			
			<% if (!videoUrl.isEmpty()) { %>
			    <div class="embed-responsive embed-responsive-16by9">
			        <iframe class="embed-responsive-item" src="<%= videoUrl %>" allowfullscreen></iframe>
			    </div>
			<% } %>

            <!-- Botón para finalizar la ruta si cumple las condiciones -->
            <% if (Boolean.TRUE.equals(puedeFinalizar)) { %>
                <form action="detalle-ruta-vuelo" method="POST">
				    <input type="hidden" name="ruta-finalizar" value="<%= ruta.getNombre() %>">
				    <button type="submit" class="btn btn-danger mt-3">Finalizar Ruta de Vuelo</button>
				</form>

            
            <% } %>

			            
            <h2>Vuelos</h2>
            <div class="accordion" id="accordionExample">
                <%
                List<servidor.DataVuelo> vuelos = ruta.getVuelos();
                if (vuelos != null) {
                    for (DataVuelo vuelo : vuelos) {
                %>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="<%=vuelo.getNombre()%>-heading">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#<%=vuelo.getNombre()%>-collapse" aria-expanded="false" aria-controls="<%=vuelo.getNombre()%>-collapse">
                            <%=vuelo.getNombre()%>
                            </button>
                        </h2>
                        <div class="accordion-collapse collapse" id="<%=vuelo.getNombre()%>-collapse" aria-labelledby="<%=vuelo.getNombre()%>-heading" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                            <strong>Detalle:</strong>
                            <ul>
                                <li><strong>Fecha:</strong> <%=vuelo.getFechaFormatted()%></li>
                                <li><strong>Duracion:</strong> <%=vuelo.getDuracionFormatted()%></li>
                                <li><strong>Capacidad Turistas:</strong> <%=vuelo.getMaxTurista()%></li>
                                <li><strong>Capacidad Ejecutivos:</strong> <%=vuelo.getMaxEjecutivo()%></li>
                                <li><strong>Fecha alta:</strong> <%=vuelo.getFechaAltaFormatted()%></li>
                            </ul>
                            </div>
                        </div>
                    </div>
                    <% 
                    }
                }
                %>
            </div>
        </div>
    </div>
</body>
</html>
