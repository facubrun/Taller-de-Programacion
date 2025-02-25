<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="servidor.DataAerolinea" %>
<%@ page import="servidor.DataRutaVuelo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Volando.uy | Consultar Ruta de Vuelo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="./header.jsp" />
    <jsp:include page="./side-menu.jsp" />
    <div class="main-page container col-6">
        <div class="main-content">
            <h1>Consulta de Rutas de Vuelo</h1>
            <form id="datos-consulta" action="detalle-ruta-vuelo" method="GET">
                <div class="container">
                    <!-- Select de criterio de búsqueda -->
                    <div class="mb-3">
                        <label for="buscar_por">Buscar por:</label>
                        <select id="buscar_por" class="form-control" onChange="buscarPor()">
                            <option value="buscarPor">Buscar por...</option>
                            <option value="aerolinea">Aerolínea</option>
                            <option value="categoria">Categoría</option>
                        </select>
                    </div>
                    
                    <!-- Select de Aerolínea -->
                    <div class="mb-3 d-none" id="busqueda_aerolinea">
                        <label for="aerolinea">Aerolínea</label>
                        <select name="aerolinea" id="aerolinea" class="form-control">
                            <option value="">Seleccionar aerolínea...</option>
                            <% 
                                List<DataAerolinea> aerolineas = (List<DataAerolinea>) request.getAttribute("aerolineas");
                                if (aerolineas != null) {
                                    for (DataAerolinea aerolinea : aerolineas) {
                            %>
                                <option value="<%=aerolinea.getNickname()%>"><%=aerolinea.getNombre()%></option>
                            <% 
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <!-- Select de Categoría -->
                    <div class="mb-3 d-none" id="busqueda_categoria">
                        <label for="categoria">Categoría</label>
                        <select name="categoria" id="categoria" class="form-control">
                            <option value="">Seleccionar categoría...</option>
                            <% 
                            	ArrayList<String> categorias = (ArrayList<String>) request.getAttribute("nombresCategorias");
                                if (categorias != null) {
                                    for (String categoria : categorias) {
                            %>
                                <option value="<%= categoria %>"><%= categoria %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <!-- Select de Rutas de Vuelo -->
                    <div class="mb-3">
                        <label for="rutaVuelo">Ruta de Vuelo</label>
                        <select name="ruta" id="rutaVuelo" class="form-control">
                            <option value="">Seleccionar ruta...</option>
                            <% 
                                List<DataRutaVuelo> rutasConfirmadas = (List<DataRutaVuelo>) request.getAttribute("rutasConfirmadas");
                                if (rutasConfirmadas != null) {
                                    for (DataRutaVuelo ruta : rutasConfirmadas) {
                            %>
                                <option value="<%=ruta.getNombre()%>" data-aerolinea="<%=ruta.getAerolinea()%>" data-categorias="<%=String.join(",", ruta.getCategorias())%>">
                                    <%=ruta.getNombre()%>
                                </option>
                            <% 
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Consultar Ruta</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Script para mostrar u ocultar selectores y filtrar rutas -->
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        const buscarPorSelect = document.getElementById('buscar_por');
        const aerolineaSelect = document.getElementById('aerolinea');
        const categoriaSelect = document.getElementById('categoria');
        const rutaVueloSelect = document.getElementById('rutaVuelo');

        // Definir buscarPor como una función global
        window.buscarPor = function() {
            const seleccion = buscarPorSelect.value;
            rutaVueloSelect.value = '';  // Limpiar rutas seleccionadas

            if (seleccion === "aerolinea") {
                document.getElementById('busqueda_aerolinea').classList.remove('d-none');
                document.getElementById('busqueda_categoria').classList.add('d-none');
                filtrarRutas('aerolinea');
            } else if (seleccion === "categoria") {
                document.getElementById('busqueda_aerolinea').classList.add('d-none');
                document.getElementById('busqueda_categoria').classList.remove('d-none');
                filtrarRutas('categoria');
            }
        };

        function filtrarRutas(tipoFiltro) {
            const optionsRutas = rutaVueloSelect.options;
            let filtroSeleccionado = '';

            if (tipoFiltro === 'aerolinea') {
                filtroSeleccionado = aerolineaSelect.value;
                for (let i = 0; i < optionsRutas.length; i++) {
                    const aerolinea = optionsRutas[i].getAttribute('data-aerolinea');
                    optionsRutas[i].hidden = aerolinea !== filtroSeleccionado;
                }
            } else if (tipoFiltro === 'categoria') {
                filtroSeleccionado = categoriaSelect.value;
                for (let i = 0; i < optionsRutas.length; i++) {
                    const categoriasRuta = optionsRutas[i].getAttribute('data-categorias');
                    if (categoriasRuta) {
                        const categoriasArray = categoriasRuta.split(',');
                        optionsRutas[i].hidden = !categoriasArray.includes(filtroSeleccionado);
                    } else {
                        optionsRutas[i].hidden = true;
                    }
                }
            }
        }

        buscarPorSelect.addEventListener('change', buscarPor);
        aerolineaSelect.addEventListener('change', function() {
            ocultarTodasLasRutas();
            filtrarRutas('aerolinea');
            categoriaSelect.value = '';
        });
        categoriaSelect.addEventListener('change', function() {
            ocultarTodasLasRutas();
            filtrarRutas('categoria');
            aerolineaSelect.value = '';
        });

        function ocultarTodasLasRutas() {
            const optionsRutas = rutaVueloSelect.options;
            for (let i = 0; i < optionsRutas.length; i++) {
                optionsRutas[i].hidden = optionsRutas[i].value !== '';
            }
        }

        ocultarTodasLasRutas(); // Ocultar todas las rutas al cargar la página
    });

    </script>
</body>
</html>
