<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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


	<link href="${pageContext.request.contextPath}/consulta-reserva.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../css/main.css">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/index.js" defer></script>

	<%if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
	}

	%>

	<script>
		String ruta;
		String aerolinea;
	</script>
	

	
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
		    text-align: center;
		}
		
		form {
		    background-color: #ffffff;
		    padding: 20px;
		    border-radius: 8px;
		    max-width: 400px;
		    margin: 0 auto;
		    align-items: center;
		    justify-content: center;
		}
		
		label {
		    display: block;
		    margin-bottom: 5px;
		    font-weight: bold;
		}
		
		select {
		    width: 100%;
		    padding: 10px;
		    border: 1px solid #E78F79;
		    border-radius: 4px;
		    margin-bottom: 15px;
		    font-size: 16px;
		}
		
		
		.consulta-button {
		    background-color: #11e4fb;
		    color: black;
		    border: none;
		    padding: 10px 15px;
		    border-radius: 4px;
		    cursor: pointer;
		    font-size: 16px;
		    transition: background-color 0.3s;
		}
		
		.consulta-button:hover {
		    background-color: #004d40;
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
		
		#info-2 {
		    display: none;
		}
		
		#info-1 {
		    display: none;
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
	</style>
</head>

<body>
	<jsp:include page="./header.jsp" />
	
	<div class="main-page">
		<div class="col d-flex align-items-center">
			<form id="form-consulta" action="consulta-reserva" method="post">
				<h1>Consulta una reserva de Vuelo!</h1>
				
					<label id="label-c">Seleccione Aerolinea:</label>
					<select id="Aerolinea" name="Aerolinea" class="contenido" onchange="seleccionarAerolinea()" required>
						<option disabled selected>Selecciona una opción</option>
						<%List<servidor.DataAerolinea> aerolineas = (List<servidor.DataAerolinea>) request.getAttribute("aerolineas");
						if(aerolineas!=null){
							for(servidor.DataAerolinea aero : aerolineas){
								String nickname= aero.getNickname();%>
								<option value="<%=nickname%>"><%=nickname%></option>
							<%}
						}
						%>
					</select>
				
				<label>Seleccione Ruta de Vuelo:</label>
				<select id="Ruta" name="Ruta" class="contenido" onchange="seleccionarRuta()" required>
				</select>


				<label>Seleccione Vuelo:</label>
				<select id="Vuelos" name="Vuelos" class="contenido" onchange="seleccionarVuelo()" required>
				</select>

				<button class="consulta-button" type="submit">Consultar reserva</button>
			</form>
		</div>
	</div>

	<script>
		function seleccionarAerolinea() {
			Ruta.innerHTML = '<option disabled selected>Selecciona una opción</option>';
			Vuelos.innerHTML = '<option disabled selected>Selecciona una opción</option>';
			aerolinea = document.getElementById('Aerolinea').value;
			let url= "rutas-fetch?opcion="+aerolinea;
			
            fetch(url)
            .then(response => {
            	if (!response.ok) {
            		throw new Error('Network response was not ok');
            	}
            	return response.json();
            })
            .then(data => {
                data.forEach(ruta => {
                    Ruta.add(new Option(ruta.Nombre,ruta.Nombre));
                });
            })
            .catch(error => console.error('Error:', error));

            
		}
	</script>

	<script>
		function seleccionarRuta() {
			
			Vuelos.innerHTML = '<option disabled selected>Selecciona una opción</option>';
			ruta = document.getElementById('Ruta').value;
			let url= "vuelos-fetch?opcion="+ruta;
            fetch(url)
            .then(response => response.json())
            .then(data => {
                data.forEach(vuelo => {
                    Vuelos.add(new Option(vuelo.Nombre,vuelo.Nombre));
                });
            })
            .catch(error => console.error('Error:', error));
		}
	</script>

</body>

</html>