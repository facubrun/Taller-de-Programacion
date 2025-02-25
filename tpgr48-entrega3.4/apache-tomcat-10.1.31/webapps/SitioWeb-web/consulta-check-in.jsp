<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="servidor.DataCliente"%>
<%@ page import="servidor.DataAerolinea"%>
<%@ page import="servidor.DataRutaVuelo"%>
<%@ page import="servidor.DataReserva"%>
<%@ page import="servidor.DataPasajes"%>
<%@ page import="servidor.DataCheckIn"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Volando.uy | Consultar Reservas de vuelo con Check In</title>
<link href="css/consulta-reserva.css" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">

<%if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
	}

	%>


<style>
.consulta-button {
	background-color: white;
	color: #1E3E62;
	margin-left: 5px;
	border-radius: 4px;
	border: 2px solid transparent;
	cursor: pointer;
	font-size: 16px;
	transition: background-color 0.3s;
	text-decoration: underline;
	margin-top: 50px;
}

.consulta-button:hover {
	background-color: #EF9C66;
}

@media ( min-width : 992px) {
	.main-page {
		margin-left: 300px;
	}
}
</style>
</head>

<body>
<jsp:include page="./header.jsp" />
	<jsp:include page="./side-menu.jsp" />
    <div class="main-page container col-6">
    	<div id="main-content" class="main-content">
	<div style="display: flex; flex-direction: column;" class="container">
		<h2>Tus reservas con check-in:</h2>
		<div class="accordion" id="accordionExample">
			<%
                            ArrayList<DataCheckIn> checkins = (ArrayList<DataCheckIn>) request.getAttribute("checkins");
                            if (checkins == null) { %>
			<div class="alert alert-warning" role="alert">No tienes
				ningun Check In realizado.</div>

			<% }else	{
                                for (servidor.DataCheckIn checkIn: checkins) { 
                            %>
			<div class="accordion-item">
				<h2 class="accordion-header"
					id="<%=checkIn.getReserva().getNickC()%>-<%=checkIn.getReserva().getNomV()%>-heading">
					<button class="accordion-button" type="button"
						data-bs-toggle="collapse"
						data-bs-target="#<%=checkIn.getReserva().getNickC()%>-<%=checkIn.getReserva().getNomV()%>-collapse"
						aria-expanded="true"
						aria-controls="<%=checkIn.getReserva().getNickC()%>-<%=checkIn.getReserva().getNomV()%>-collapse">
						<%= checkIn.getReserva().getNomV() %>
					</button>
				</h2>
				<div class="accordion-collapse collapse"
					id="<%=checkIn.getReserva().getNickC()%>-<%=checkIn.getReserva().getNomV()%>-collapse"
					aria-labelledby="<%=checkIn.getReserva().getNickC()%>-<%=checkIn.getReserva().getNomV()%>-heading"
					data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<strong>Detalle:</strong>
						<ul>
							<li><strong>Nickname del cliente:</strong> <%=checkIn.getReserva().getNickC()%></li>
							<li><strong>Hora de embarque:</strong> <%=checkIn.getHoraEmbarqueFormatted()%></li>
							<li><strong>Nombre de vuelo:</strong> <%=checkIn.getReserva().getNomV()%></li>
							<li><strong>Fecha de reserva:</strong> <%=checkIn.getReserva().getFechaReserva()%></li>
							<li><strong>Tipo de asiento:</strong> <%=checkIn.getReserva().getTipoAsiento()%></li>
							<li><strong>Cantidad de pasajes:</strong> <%=checkIn.getReserva().getCantPasajes()%></li>
							<li><strong>Cantidad extra equipaje:</strong> <%=checkIn.getReserva().getCantExtra()%></li>
							<li><strong>Costo Total:</strong> <%=checkIn.getReserva().getCostoTotal()%></li>
							<li><strong>Pasajes:</strong>
								<ul>
									<% 
                                                    List<servidor.DataPasajes> pasajes = checkIn.getReserva().getPasajes();
                                                    if (pasajes != null) {
                                                        for (servidor.DataPasajes pasaje : pasajes) {
                                                    %>
									<li><%=pasaje.getApellido()%>, <%=pasaje.getNombre()%>. Asiento: <%=pasaje.getAsiento()%></li>
									<%
                                                        }
                                                    }
                                                    %>
								</ul></li>
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
	</div>
    </div>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>

</html>
