<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="servidor.DataCliente" %>
<%@ page import="servidor.DataAerolinea" %>
<%@ page import="servidor.DataRutaVuelo"%>
<%@ page import="servidor.DataReserva"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Volando.uy | Consultar Reservas de vuelo</title>

	<link href="${pageContext.request.contextPath}/css/consulta-reserva.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/index.js" defer></script>

	<%if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
	}

	%>


	

	
	<style>
		body {
		    display: flex;
		    flex-direction: column;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    font-family: Arial, sans-serif;
		    overflow: auto;
		}
		
		h1 {
		    color: #333;
		}
		

		
		label {
		    display: block;
		    margin-bottom: 5px;
		    font-weight: bold;
		}
		
		
		
		.volver-button {
		    background-color: white;
		    color: #1E3E62;
		    padding: 10px 15px;
		    border-radius: 4px;
		    border: 2px solid transparent;
		    cursor: pointer;
		    font-size: 16px;
		    transition: background-color 0.3s;
		    text-decoration: underline;
		    margin-top: 50px;
		}
		
		.volver-button:hover {
		    background-color: #EF9C66;
		}
		
		h2 {
		    text-align: left;
		    font-family: Arial, sans-serif;
		    font-weight: bold;
		    font-size: 40px;
		}
		
		.etiqueta {
		    font-weight: bold;
		    width: 100px;
		}
		
		.info {
		    color: #2a2a2a;
		}
		
		p {
		    margin: 5px 0;
		}
		
		body {
		    margin: 0;
		    padding: 0;
		}
		
		
		img {
		    max-width: 20%;
		    /* Para que la imagen no sea más grande que la columna */
		    height: auto;
		}
		
		.main-page {
		    display: flex;
		}
		
		.main-content {
		    align-items: center;
		    justify-content: center;
		}
		
		  .error-message {
		    margin-top: 20px;
		  }
		  
		@media (min-width: 992px) {
		.main-page {
		    margin-left: 300px;
		}
	}
		
	</style>

	
	
</head>

<body>
	<jsp:include page="./header.jsp" />
	<div>
	<div class="container">
	<% 
	String ok = (String) request.getAttribute("ok");
	if ("true".equals(ok)){
	%>
			
			<% servidor.DataReserva reserva = (servidor.DataReserva) request.getAttribute("reserva");
			String aerolinea = (String) request.getAttribute("aerolinea");
			String ruta = (String) request.getAttribute("ruta");%>
				<div clas="row-3">
					<h1>Reserva del Vuelo:<%=reserva.getNomV()%></h1>
				</div>
				<div class="row">
				<div class="col-12 col-md-6" >
					<p><span class="etiqueta">Realizada por: </span>
					<p><img id="imgC" src="" >
						<span class="info" id="cliente"><%=reserva.getNickC()%></span>
					</p>

					<p><span class="etiqueta">Aerolinea:</span>
					<p><img id="imgA" src="" >
						<span class="info" id="aerolinea"><%=aerolinea%></span>
					</p>

					<p><span class="etiqueta">Ruta de vuelo:</span>
					<p><img id="imgR"  src="">
						<span class="info" id="ruta"><%=ruta%></span>
					</p>

					<p><span class="etiqueta">Vuelo:</span>
					<p><img id="imgV" src="">
						<span class="info" id="vuelo"><%=reserva.getNomV()%></span>
					</p>
				</div>
				
				<div class="col-12 col-md-6 " >

					<p><span class="etiqueta">Tipo asiento:</span>
						<span class="info" id="tipo" ><%=reserva.getTipoAsiento()%></span>
					</p>

					<p><span class="etiqueta">Cantidad de pasajes:</span>
						<span class="info" id="cant-pas" ><%=reserva.getCantPasajes()%></span>
					</p>

					<p><span class="etiqueta">Cantidad de equipaje extra:</span>
						<span class="info" id="cant-eq"><%=reserva.getCantExtra()%></span>
					</p>

					<p><span class="etiqueta">Fecha:</span>
						<span class="info" id="fecha"><%=reserva.getFechaReservaFormatted()%></span>
					</p>

					<p><span class="etiqueta">Costo:</span>
						<span class="info" id="costo"><%=reserva.getCostoTotal()%></span>
					</p>


					<p> <span class="etiqueta">Pasajeros:</span></p>
					<% 
														List<servidor.DataPasajes> pasajes = reserva.getPasajes();
														if (pasajes != null) {
															for (servidor.DataPasajes pasaje : pasajes) {
														%>
										<li><%=pasaje.getApellido()%>, <%=pasaje.getNombre()%></li>
										<%
															}
														}
														%></span></p>
					
					<%if(reserva.isCheckIn()){ %>
						<form  action="check-in" method="get">
							<input type="hidden" name="vuelo" value="<%=reserva.getNomV()%>">
							<button class="volver-button" onclick="volver();">Realizar Check-in!</button>
						</form>
					<%} %>
				</div>
				</div>
				
			<%}else{ %>	
				<div class="alert alert-danger error-message" role="alert">
				  ¡No tienes ninguna reserva! Intentalo de nuevo.
				</div>
		<%}%>
				

	</div>

</div>

  

</body>

</html>