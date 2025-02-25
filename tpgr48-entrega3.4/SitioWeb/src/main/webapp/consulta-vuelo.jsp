<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="servidor.DataAerolinea" %>
<%@ page import="servidor.DataRutaVuelo" %>
<%@ page import="servidor.DataVuelo" %>
<%@ page import="servidor.DataReserva" %>
<%@ page import="servidor.DataPasajes" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Volando.uy | Consultar vuelos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/consulta-vuelo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>

<body>
    <jsp:include page="./header.jsp" />
    <jsp:include page="./side-menu.jsp" /> 
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
                                    for (DataAerolinea aerolinea : aerolineas) {
                            %>
                                <option value="<%=aerolinea.getNickname()%>"><%=aerolinea.getNickname()%></option>
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
                                        List<DataRutaVuelo> rutas = aerolinea.getRutasVuelo();
                                        if (rutas != null) {
                                            for (DataRutaVuelo ruta : rutas) {
                            %>
                                <option value="<%=ruta.getNombre()%>" data-aerolinea="<%=aerolinea.getNickname()%>"><%=ruta.getNombre()%></option>
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
                                        List<DataRutaVuelo> rutas = aerolinea.getRutasVuelo();
                                        if (rutas != null) {
                                            for (DataRutaVuelo ruta : rutas) {
                                                List<DataVuelo> vuelos = ruta.getVuelos();
                                                if (vuelos != null) {
                                                    for (DataVuelo vuelo : vuelos) {
                            %>
                                <option value="<%=vuelo.getNombre()%>" data-aerolinea="<%=aerolinea.getNickname()%>" data-ruta-vuelo="<%=ruta.getNombre()%>"><%=vuelo.getNombre()%></option>
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

            <!-- Información del vuelo -->
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

            <!-- Reservas -->
            <%
                String tipoUsuario = (String) session.getAttribute("tipoUsuario");
                String usuarioSesion = (String) session.getAttribute("usuario");
                List<DataReserva> reservas = infoVuelo.getReservas();

                if (reservas != null && !reservas.isEmpty()) {
            %>
            <div style="display: flex; flex-direction: column;" class="container" >
                <h2>Reservas</h2>
                <div class="accordion" id="accordionReservas">
                    <%
                        for (DataReserva reserva : reservas) {
                            if ("cliente".equals(tipoUsuario) && reserva.getNickC().equals(usuarioSesion)) {
                    %>
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseCliente<%=reserva.getNomV()%>" aria-expanded="true">
                                Reserva: <%=reserva.getNomV()%>
                            </button>
                        </h2>
                        <div id="collapseCliente<%=reserva.getNomV()%>" class="accordion-collapse collapse show">
                            <div class="accordion-body">
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
                                                        <li><%=pasaje.getApellido()%>,<%=pasaje.getNombre()%></li>
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
                            } else if ("aerolinea".equals(tipoUsuario) && usuarioSesion.equals(request.getAttribute("aerolinea"))) {
                    %>
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseAerolinea<%=reserva.getNomV()%>" aria-expanded="true">
                                Reserva: <%=reserva.getNomV()%>
                            </button>
                        </h2>
                        <div id="collapseAerolinea<%=reserva.getNomV()%>" class="accordion-collapse collapse show">
                            <div class="accordion-body">
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
                                                        <li><%=pasaje.getApellido()%>,<%=pasaje.getNombre()%></li>
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
</body>

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
</html>
