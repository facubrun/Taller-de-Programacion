<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="servidor.DataCliente" %>
<%@ page import="servidor.DataAerolinea" %>
<%@ page import="servidor.DataRutaVuelo"%>
<%@ page import="servidor.DataReserva"%>
<%@ page import="servidor.DataPasajes"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Volando.uy | Consultar Reservas de vuelo</title>
	<link href="css/consulta-reserva.css" rel="stylesheet">

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
		.consulta-button {
		    background-color: white;
		    color: #1E3E62;
		    margin-left:5px;
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
		
    	@media (min-width: 992px) {
		.main-page{
		    margin-left: 300px;
        }
		}
		
	</style>
</head>

<body>
	<jsp:include page="./header.jsp" />
	<div class="main-page">
	<% String error =(String) request.getAttribute("error");
	if ("exito".equals(error)){ %>
		<div class="alert alert-success" role="alert"> 
			Check-in realizado con éxito.
		</div>
	<%}else if("error".equals(error)){ %>
		<div class="alert alert-danger error-message" role="alert">
			¡Se ha producido un error! Intentalo de nuevo.
		</div>
	<%} %>
	                    <div style="display: flex; flex-direction: column;" class="container" >
                        <h2>Check-in pendientes:</h2>
                        <div class="accordion" id="accordionExample">
                            <%
                            ArrayList<DataReserva> reservas = (ArrayList<DataReserva>) request.getAttribute("reservas");
                            if (reservas == null) { %>
                                <div class="alert alert-warning" role="alert">
                                	No tienes reservas realizadas.
                            	</div>
                            
                           <% }else	{
                                for (servidor.DataReserva reserva : reservas) {
                            %>
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-heading">
                                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-collapse" aria-expanded="true" aria-controls="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-collapse">
                                        <%=reserva.getNomV()%>
                                        </button>
                                    </h2>
                                    <div class="accordion-collapse collapse" id="<%=reserva.getNickC() %>-<%=reserva.getNomV()%>-collapse" aria-labelledby="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-heading" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                        <strong>Detalle:</strong>
                                        <ul>
                                            <li><strong>Nick Cliente:</strong> <%=reserva.getNickC()%></li>
                                            <li><strong>Nombre Vuelo:</strong> <%=reserva.getNomV()%></li>
                                            <li><strong>Fecha Reserva:</strong> <%=reserva.getFechaReservaFormatted()%></li>
                                            <li><strong>Tipo Asiento:</strong> <%=reserva.getTipoAsiento() %></li>
                                            <li><strong>Cantidad Pasajes:</strong> <%=reserva.getCantPasajes()%></li>
                                            <li><strong>Cantidad Extra:</strong> <%=reserva.getCantExtra()%></li>
                                            <li><strong>Costo Total:</strong> <%=reserva.getCostoTotal()%></li>
                                            <li><strong>Pasajes:</strong>
                                         <ul>
                                                    <% 
                                                    List<servidor.DataPasajes> pasajes = reserva.getPasajes();
                                                    if (pasajes != null) {
                                                        for (servidor.DataPasajes pasaje : pasajes) {
                                                    %>
                                                        <li><%=pasaje.getApellido()%>, <%=pasaje.getNombre()%>. Asiento: <%=pasaje.getAsiento()%></li>
                                                    <%
                                                        }
                                                    }
                                                    %>
                                                </ul>
                                            </li>
                                            <form  action="check-in" method="post">
                                            	<input type="hidden" name="vuelo" value="<%=reserva.getNomV()%>">
                                            	<button class="consulta-button" type="submit">Realizar check-in</button>
                                            </form>
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