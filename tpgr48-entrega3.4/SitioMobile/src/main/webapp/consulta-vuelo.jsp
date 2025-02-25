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
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Volando.uy | Consultar vuelos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/consulta-vuelo.css">

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
	<div class="col d-flex align-items-center">
    <div class="main-page">
        <div class="main-content">
            <h1>Consulta de vuelo</h1>
            <form id="datos-consulta">
                <div class="container">
                    <div class="aerolinea">
                        <label for="aerolinea">Aerolinea</label>
                        <select name="aerolinea" id="aerolinea">
                            <option value="">Seleccionar aerolinea...</option>
                            <% 
                                List<servidor.DataAerolinea> aerolineas = (List<servidor.DataAerolinea>) request.getAttribute("aerolineas");
                                if (aerolineas != null) {
                                    for (servidor.DataAerolinea aerolinea : aerolineas) {
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
                                for (servidor.DataAerolinea aerolinea : aerolineas) {
                                    String nicknameAero = aerolinea.getNickname();
                                    List<servidor.DataRutaVuelo> rutas = aerolinea.getRutasVuelo();
                                    if (rutas != null) {
                                        for (servidor.DataRutaVuelo ruta : rutas) {
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
                                for (servidor.DataAerolinea aerolinea : aerolineas) {
                                    String nicknameAero = aerolinea.getNickname();
                                    List<servidor.DataRutaVuelo> rutas = aerolinea.getRutasVuelo();
                                    if (rutas != null) {
                                        for (servidor.DataRutaVuelo ruta : rutas) {
                                            String nombreRuta = ruta.getNombre();
                                            List<servidor.DataVuelo> vuelos = ruta.getVuelos();
                                            if (vuelos != null) {
                                                for (servidor.DataVuelo vuelo : vuelos) {
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
            <div class="container">
                <% 
                    servidor.DataVuelo infoVuelo = (servidor.DataVuelo) request.getAttribute("infoVuelo");
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

            <%
                String aerolinea = (String)request.getAttribute("aerolinea");
                String usuario = (String) session.getAttribute("usuario");
                String tipoUsuario = (String) session.getAttribute("tipoUsuario");
                String nickname = "";
                if (tipoUsuario.equals("cliente")) {
                    nickname = usuario;
                    %>
                    <div style="display: flex; flex-direction: column;" class="container" >
                        <h2>Reservas</h2>
                        <div class="accordion" id="accordionExample">
                            <%
                            List<DataReserva> reservas = (List<DataReserva>) infoVuelo.getReservas();
                            if (reservas != null) {
                                for (DataReserva reserva : reservas) {
                                    if (reserva.getNickC().equals(nickname)) {
                            %>
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-heading">
                                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-collapse" aria-expanded="true" aria-controls="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-collapse">
                                        <%=reserva.getNickC()%> - <%=reserva.getNomV()%>
                                        </button>
                                    </h2>
                                    <div class="accordion-collapse collapse show" id="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-collapse" aria-labelledby="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-heading" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                        <strong>Detalle:</strong>
                                        <ul>
                                            <li><strong>Nick Cliente:</strong> <%=reserva.getNickC()%></li>
                                            <li><strong>Nombre Vuelo:</strong> <%=reserva.getNomV()%></li>
                                            <li><strong>Fecha Reserva:</strong> <%=reserva.getFechaReservaFormatted()%></li>
                                            <li><strong>Cantidad Pasajes:</strong> <%=reserva.getCantPasajes()%></li>
                                            <li><strong>Cantidad Extra:</strong> <%=reserva.getCantExtra()%></li>
                                            <li><strong>Costo Total:</strong> <%=reserva.getCostoTotal()%></li>
                                            <li><strong>Pasajes:</strong>
                                                <ul>
                                                    <% 
                                                    List<DataPasajes> pasajes = reserva.getPasajes();
                                                    if (pasajes != null) {
                                                        for (DataPasajes pasaje : pasajes) {
                                                    %>
                                                        <li><%=pasaje.getApellido()%>, <%=pasaje.getNombre()%></li>
                                                    <%
                                                        }
                                                    }
                                                    %>
                                                </ul>
                                            </li>
                                        </ul>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                }
                            }
                            %>
                        </div>
                    </div>
                    <%
                } else if ((usuario != null) && (usuario.equals(aerolinea))) {
                    %>
                    <div style="display: flex; flex-direction: column;" class="container" >
                        <h2>Reservas</h2>
                        <div class="accordion" id="accordionExample">
                            <%
                            List<DataReserva> reservas = (List<DataReserva>) infoVuelo.getReservas();
                            if (reservas != null) {
                                for (DataReserva reserva : reservas) {
                            %>
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-heading">
                                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-collapse" aria-expanded="true" aria-controls="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-collapse">
                                        <%=reserva.getNickC()%> - <%=reserva.getNomV()%>
                                        </button>
                                    </h2>
                                    <div class="accordion-collapse collapse" id="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-collapse" aria-labelledby="<%=reserva.getNickC()%>-<%=reserva.getNomV()%>-heading" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                        <strong>Detalle:</strong>
                                        <ul>
                                            <li><strong>Nick Cliente:</strong> <%=reserva.getNickC()%></li>
                                            <li><strong>Nombre Vuelo:</strong> <%=reserva.getNomV()%></li>
                                            <li><strong>Fecha Reserva:</strong> <%=reserva.getFechaReservaFormatted()%></li>
                                            <li><strong>Cantidad Pasajes:</strong> <%=reserva.getCantPasajes()%></li>
                                            <li><strong>Cantidad Extra:</strong> <%=reserva.getCantExtra()%></li>
                                            <li><strong>Costo Total:</strong> <%=reserva.getCostoTotal()%></li>
                                            <li><strong>Pasajes:</strong>
                                                <ul>
                                                    <% 
                                                    List<DataPasajes> pasajes = reserva.getPasajes();
                                                    if (pasajes != null) {
                                                        for (DataPasajes pasaje : pasajes) {
                                                    %>
                                                        <li><%=pasaje.getApellido()%>, <%=pasaje.getNombre()%></li>
                                                    <%
                                                        }
                                                    }
                                                    %>
                                                </ul>
                                            </li>
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
                    <%
                }
            %>

        </div>
    </div>
    </div>

<script>
document.addEventListener('DOMContentLoaded', function () {
    const optionsRutas = document.getElementById('rutaVuelo').options;
    for (let i = 0; i < optionsRutas.length; i++) {
        if (optionsRutas[i].value !== '') {
            optionsRutas[i].hidden = true;
        }
    };

    const optionsVuelos = document.getElementById('vuelo').options;
    for (let i = 0; i < optionsVuelos.length; i++) {
        if (optionsVuelos[i].value !== '') {
            optionsVuelos[i].hidden = true;
        }
    };

    if (document.getElementById('nombre-vuelo').value === 'null') {
        document.getElementById('nombre-vuelo').value = '';
    }
    if (document.getElementById('fecha').value === 'null') {
        document.getElementById('fecha').value = '';
    }
    if (document.getElementById('duracion').value === 'null') {
        document.getElementById('duracion').value = '';
    }
    if (document.getElementById('capacidad-turistas').value === 'null') {
        document.getElementById('capacidad-turistas').value = '';
    }
    if (document.getElementById('capacidad-ejecutivos').value === 'null') {
        document.getElementById('capacidad-ejecutivos').value = '';
    }
    if (document.getElementById('fecha-alta').value === 'null') {
        document.getElementById('fecha-alta').value = '';
    }


    document.getElementById('aerolinea').addEventListener('change', function () {
        const aerolinea = document.getElementById('aerolinea').value;
        const optionsRutas = document.getElementById('rutaVuelo').options
        document.getElementById('rutaVuelo').value = '';
        for (let i = 0; i < optionsRutas.length; i++) {
            const rutaVuelo = optionsRutas[i].getAttribute('data-aerolinea');
            if (rutaVuelo === aerolinea) {
                optionsRutas[i].hidden = false;
            } else {
                if (optionsRutas[i].value !== '') {
                    optionsRutas[i].hidden = true;
                }
            }
        };

        const optionsVuelo = document.getElementById('vuelo').options;
        document.getElementById('vuelo').value = '';
        for (let i = 0; i < optionsVuelo.length; i++) {
            if (optionsVuelo[i].value !== '') {
                optionsVuelo[i].hidden = true;
            }
        };

    });

    document.getElementById('rutaVuelo').addEventListener('change', function () {
        const rutaVuelo = document.getElementById('rutaVuelo').value;
        const optionsVuelo = document.getElementById('vuelo').options;
        document.getElementById('vuelo').value = '';
        for (let i = 0; i < optionsVuelo.length; i++) {
            const rutaVueloVuelo = optionsVuelo[i].getAttribute('data-ruta-vuelo');
            if (rutaVueloVuelo === rutaVuelo) {
                optionsVuelo[i].hidden = false;
            }
            else {
                if (optionsVuelo[i].value !== '') {
                    optionsVuelo[i].hidden = true;
                }
            }
        };
    });
});

</script>
</body>
</html>
