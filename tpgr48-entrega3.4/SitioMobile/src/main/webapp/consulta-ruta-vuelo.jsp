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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/index.js" defer></script>
    
     <style>
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
                        <label for="aerolinea">Aerolinea</label>
                        <select name="aerolinea" id="aerolinea" class="form-control">
                            <option value="">Seleccionar aerolinea...</option>
                            <% 
                           		List<servidor.DataAerolinea> aerolineas = (List<servidor.DataAerolinea>) request.getAttribute("aerolineas");
                                if (aerolineas != null) {
                                    for (servidor.DataAerolinea aerolinea : aerolineas) {
                            %>
                                <option value="<%=aerolinea.getNickname() %>"><%=aerolinea.getNombre() %></option>
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
                                List<servidor.DataRutaVuelo> rutasConfirmadas = (List<servidor.DataRutaVuelo>) request.getAttribute("rutasConfirmadas");
                                if (rutasConfirmadas != null) {
                                    for (servidor.DataRutaVuelo ruta : rutasConfirmadas) {
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
    <script src="js/consulta-ruta-vuelo.js"></script>
    <script>
        function buscarPor() {
            const seleccion = document.getElementById('buscar_por').value;
            document.getElementById('rutaVuelo').value = '';  // Limpiar rutas seleccionadas

            if (seleccion === "aerolinea") {
                document.getElementById('busqueda_aerolinea').classList.remove('d-none');
                document.getElementById('busqueda_categoria').classList.add('d-none');
                filtrarRutas('aerolinea');
            } else if (seleccion === "categoria") {
                document.getElementById('busqueda_aerolinea').classList.add('d-none');
                document.getElementById('busqueda_categoria').classList.remove('d-none');
                filtrarRutas('categoria');
            }
        }

        function filtrarRutas(tipoFiltro) {
            const optionsRutas = document.getElementById('rutaVuelo').options;
            let filtroSeleccionado = '';

            if (tipoFiltro === 'aerolinea') {
                filtroSeleccionado = document.getElementById('aerolinea').value;
                for (let i = 0; i < optionsRutas.length; i++) {
                    const aerolinea = optionsRutas[i].getAttribute('data-aerolinea');
                    optionsRutas[i].hidden = aerolinea !== filtroSeleccionado;
                }
            } else if (tipoFiltro === 'categoria') {
                filtroSeleccionado = document.getElementById('categoria').value;
                for (let i = 0; i < optionsRutas.length; i++) {
                    const categoriasRuta = optionsRutas[i].getAttribute('data-categorias').split(',');
                    optionsRutas[i].hidden = !categoriasRuta.includes(filtroSeleccionado);
                }
            }
        }
        
        document.addEventListener('DOMContentLoaded', function () {
            const optionsRutas = document.getElementById('rutaVuelo').options;

            // Función para ocultar todas las rutas inicialmente
            function ocultarTodasLasRutas() {
                for (let i = 0; i < optionsRutas.length; i++) {
                    if (optionsRutas[i].value !== '') {
                        optionsRutas[i].hidden = true;
                    }
                }
            }

            // Inicialmente ocultamos todas las rutas
            ocultarTodasLasRutas();

            // Evento para seleccionar aerolínea
            document.getElementById('aerolinea').addEventListener('change', function () {
                const aerolineaSeleccionada = this.value;
                ocultarTodasLasRutas(); // Ocultar todas las rutas antes de mostrar las correctas

                // Mostrar las rutas que correspondan a la aerolínea seleccionada
                for (let i = 0; i < optionsRutas.length; i++) {
                    const aerolinea = optionsRutas[i].getAttribute('data-aerolinea');
                    if (aerolinea === aerolineaSeleccionada) {
                        optionsRutas[i].hidden = false;
                    }
                }

                // Resetear el combo de categorías si se selecciona una aerolínea
                document.getElementById('categoria').value = '';
            });

            // Evento para seleccionar categoría
        	document.getElementById('categoria').addEventListener('change', function () {
        	    const categoriaSeleccionada = this.value;

        	    for (let i = 0; i < optionsRutas.length; i++) {
        	        const categoriasRuta = optionsRutas[i].getAttribute('data-categorias');
        	        
        	        // Validar que categoriasRuta no sea null o vacío antes de hacer el split
        	        if (categoriasRuta && categoriasRuta !== "") {
        	            const categoriasArray = categoriasRuta.split(',');
        	            if (categoriasArray.includes(categoriaSeleccionada)) {
        	                optionsRutas[i].hidden = false;
        	            } else {
        	                optionsRutas[i].hidden = true;
        	            }
        	        } else {
        	            // Si no tiene categorías, lo escondemos por defecto
        	            optionsRutas[i].hidden = true;
        	        }
        	    }
        	});



            // Reseteo de las rutas al cambiar de selección en buscar_por
            document.getElementById('buscar_por').addEventListener('change', function () {
                document.getElementById('rutaVuelo').value = ''; // Limpiar la selección de rutas de vuelo
                ocultarTodasLasRutas(); // Ocultar todas las rutas cuando se cambia el criterio de búsqueda
            });
        });

    </script>
</body>
</html>
