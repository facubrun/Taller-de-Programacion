<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="servidor.DataAerolinea" %>
<%@ page import="servidor.DataRutaVuelo" %>
<%@ page import="servidor.DataVuelo" %>
<%@ page import="servidor.DataUsuario" %>
<%@ page import="servidor.DataCliente" %>
<%@ page import="servidor.DataReserva" %>
<%@ page import="servidor.DataPasajes" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Volando.uy | Reservar un Vuelo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" defer></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/consulta-vuelo.css">
    <script src="${pageContext.request.contextPath}/js/consulta-vuelo.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/reserva-vuelo.js" defer></script>
</head>

<body>
	<jsp:include page="./header.jsp" />
	<jsp:include page="./side-menu.jsp" />
    <div class="main-page">
        <div class="main-content">
            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" style="z-index: 9999;" role="alert">
                <%= request.getAttribute("error") %>
            </div>
            <%}%>
            <% if (request.getAttribute("success") != null) { %>
            <div class="alert alert-success" style="z-index: 9999;" role="alert">
                <%= request.getAttribute("success") %>
            </div>
            <%}%>
            <h1>Reserva de vuelo</h1>
            <form id="datos-consulta">
                <div class="container">
                    <div class="aerolinea">
                        <label for="aerolinea">Aerolinea</label>
                        <select name="aerolinea" id="aerolinea">
                            <option value="">Seleccionar aerolinea...</option>
                            <% 
                        		List<DataAerolinea> aerolineas = (List<DataAerolinea>) request.getAttribute("aerolineas");
                                if (aerolineas != null) {
                                    for (DataAerolinea aerolinea : aerolineas) {
                                        String nicknameAero = aerolinea.getNickname();
                            %>
                                <option value="<%=nicknameAero %>"><%=nicknameAero %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="RutaVuelo">
                        <label for="ruta">Ruta</label>
                        <select name="rutaVuelo" id="rutaVuelo">
                            <option value="">Seleccionar ruta...</option>
                            <% 
                            if (aerolineas != null) {
                                for (DataAerolinea aerolinea : aerolineas) {
                                    String nicknameAero = aerolinea.getNickname();
                                    List<DataRutaVuelo> rutas = aerolinea.getRutasVuelo();
                                    if (rutas != null) {
                                        for (DataRutaVuelo ruta : rutas) {
                                            String nombreRuta = ruta.getNombre();
                                        %>
                                            <option value="<%=nombreRuta%>" data-aerolinea="<%=nicknameAero %>"><%=nombreRuta %></option>
                                        <%
                                    }
                                    
                                    }
                                }
                            }
                        %>
                        </select>
                    </div>

                    <div class="vuelo">
                        <label for="vuelo">Vuelo</label>
                        <select name="vuelo" id="vuelo">
                            <option value="">Seleccionar vuelo...</option>
                            <% 
                            if (aerolineas != null) {
                                for (DataAerolinea aerolinea : aerolineas) {
                                    String nicknameAero = aerolinea.getNickname();
                                    List<DataRutaVuelo> rutas = aerolinea.getRutasVuelo();
                                    if (rutas != null) {
                                        for (DataRutaVuelo ruta : rutas) {
                                            String nombreRuta = ruta.getNombre();
                                            List<DataVuelo> vuelos = ruta.getVuelos();
                                            if (vuelos != null) {
                                                for (DataVuelo vuelo : vuelos) {
                                                    String nombreVuelo = vuelo.getNombre();          
                                                %>
                                                    <option value="<%=nombreVuelo%>" data-aerolinea="<%=nicknameAero %>" data-ruta-vuelo="<%=nombreRuta%>"><%=nombreVuelo %></option>
                                                <%
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        %>
                        </select>
                    </div>
                    <button type="submit" id="btn-consulta" class="btn-consulta" formmethod="POST">Consultar</button>
                </div>
            </form>
            <div class="container">
                <% 
                    DataVuelo infoVuelo = (DataVuelo) request.getAttribute("infoVuelo");
                    if (infoVuelo == null) {
                        infoVuelo = new DataVuelo();
                    }
                %>
                <div class="nombre">
                    <label for="nombre">Nombre</label>
                    <input type="text" name="nombre" id="nombre-vuelo" value="<%=infoVuelo.getNombre()%>" disabled>
                </div>
                <div class="fecha">
                    <label for="fecha">Fecha</label>
                    <input type="text" name="fecha" id="fecha" value="<%=infoVuelo.getFechaFormatted()%>" disabled>
                </div>
                <div class="duracion">
                    <label for="duracion">Duración</label>
                    <input type="text" name="duracion" id="duracion" value="<%=infoVuelo.getDuracionFormatted()%>" disabled> 
                </div>
                <div class="capacidad-turistas">
                    <label for="capacidad-turistas">Capacidad turistas</label>
                    <input type="text" name="capacidad-turistas" id="capacidad-turistas" value="<%=infoVuelo.getMaxTurista()%>" disabled>
                </div>
                <div class="capacidad-ejecutiva">
                    <label for="capacidad-ejecutiva">Capacidad ejecutiva</label>
                    <input type="text" name="capacidad-ejecutiva" id="capacidad-ejecutivos" value="<%=infoVuelo.getMaxEjecutivo()%>" disabled>
                </div>
                <div class="fecha-alta">
                    <label for="fecha-alta">Fecha de alta</label>
                    <input type="text" name="fecha-alta" id="fecha-alta" value="<%=infoVuelo.getFechaAltaFormatted()%>" disabled>
                </div>
            </div>
            <form id="datos-reserva">
                <input type="hidden" name="vuelo" id="vuelo-hidden" value="<%=infoVuelo.getNombre()%>">
                <div class="mb-3">
                    <label for="tipo-asiento" class="form-label">Seleccionar tipo de asiento</label>
                    <select name="tipo-asiento" id="tipo-asiento">
                        <option value="" disabled selected>Seleccionar...</option>
                        <option value="turista">Turista</option>
                        <option value="ejecutivo">Ejecutivo</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="cantidad-pasajes" class="form-label">Cantidad de pasajes adicionales</label>
                    <input type="number" class="form-control" name="cantidad-pasajes" id="cantidad-pasajes" min="0" value="0" required>
                </div>

                <div class="mb-3">
                    <label for="cantidad-equipaje" class="form-label">Cantidad de equipaje extra</label>
                    <input type="number" class="form-control" name="cantidad-equipaje" id="cantidad-equipaje" min="0" value="0" required>
                </div>
                <input type="hidden" id="nombres-pasajeros" name="nombres-pasajeros">

                <div class="container-fluid mt-5">
                    <div class="row">
                        <div class="col-md-4 d-flex flex-column">
                            <div class="mb-3 w-100">
                                <input type="text" class="form-control" id="inputTexto" placeholder="Ingresa un texto" style="width: 100%;">
                            </div>
                            <div class="mb-3 w-100">
                                <button type="button" class="btn btn-primary" id="btnAgregar" style="width: 100%;">Agregar</button>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <ul class="list-group w-100" id="lista">
                            </ul>
                        </div>
                    </div>
                </div>
                
                <button type="submit" class="btn btn-primary w-100" formmethod="POST">Reservar</button>
            </form>
        </div>
    </div>
</body>
<script>
    function eliminarElemento(button) {
            const li = button.closest('li');
            li.remove();
        }
</script>
</html>