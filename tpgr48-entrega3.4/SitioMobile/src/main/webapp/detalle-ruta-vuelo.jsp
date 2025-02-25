<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="servidor.DataRutaVuelo" %>
<%@ page import="servidor.DataVuelo" %>
<%@ page import="servidor.DataReserva" %>
<%@ page import="servidor.EstadoRutaVuelo" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Volando.uy | Detalle de ruta de vuelo</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/detalle-ruta-vuelo.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
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
            <% servidor.DataRutaVuelo ruta = (servidor.DataRutaVuelo) request.getAttribute("ruta");
            %>
            <h1><%=ruta.getDescCorta()%></h1>
            <div class="detalle-vuelo">
                <div class="row">
                    <div class="col-12 col-md-6">
                        <img src="${pageContext.request.contextPath}/utils/images/<%=ruta.getImagen()%>" alt="Imagen del viaje" class="imagen-detalle">
                    </div>
                    <div class="col-12 col-md-6">
                        <p><strong>Origen:</strong> <img src="${pageContext.request.contextPath}/utils/images/icons/location.svg" class="icono-responsive" /> <%=ruta.getCiudadOrigen()%></p>
                        <p><strong>Destino:</strong> <img src="${pageContext.request.contextPath}/utils/images/icons/location.svg" class="icono-responsive" /> <%=ruta.getCiudadDestino()%></p>
                        <p><strong>Hora de salida:</strong> <%=ruta.getHoraFormatted()%></p>
                        <p><strong>Fecha de ingreso:</strong> <%=ruta.getFechaAltaFormatted()%></p>
                        <p><strong>Estado:</strong> <%=ruta.getEstado()%></p>
                        <p><strong>Categorías:</strong> <%=String.join(", ", ruta.getCategorias())%></p>
                        <p><strong>Costos:</strong></p>
                        <ul>
                            <li>Turista: $<%=ruta.getCostoTurista()%></li>
                            <li>Ejecutivo: $<%=ruta.getCostoEjecutivo()%></li>
                            <li>Equipaje extra: $<%=ruta.getCostoExtra()%></li>
                        </ul>
                    </div>
                </div>
            </div>
            <h2>Vuelos</h2>
            <div class="accordion" id="accordionExample">
                <%
                List<servidor.DataVuelo> vuelos = ruta.getVuelos();
                if (vuelos != null) {
                    for (servidor.DataVuelo vuelo : vuelos) {
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
    			    String videoUrl = ruta.getVideo();
    			    if (videoUrl.contains("youtu.be")) {
    			        String videoId = videoUrl.substring(videoUrl.lastIndexOf("/") + 1, videoUrl.contains("?") ? videoUrl.indexOf("?") : videoUrl.length());
    			        videoUrl = "https://www.youtube.com/embed/" + videoId;
    			    } else if (videoUrl.contains("watch?v=")) {
    			        String videoId = videoUrl.substring(videoUrl.indexOf("watch?v=") + 8, videoUrl.contains("&") ? videoUrl.indexOf("&") : videoUrl.length());
    			        videoUrl = "https://www.youtube.com/embed/" + videoId;
    			    }
    			
    			if (!videoUrl.isEmpty()) { %>
    			    <div class="embed-responsive embed-responsive-16by9">
    			        <iframe class="embed-responsive-item" src="<%= videoUrl %>" allowfullscreen></iframe>
    			    </div>
    			<% } 
                    }
                }
                %>
            </div>

        </div>
        </body>
</html>