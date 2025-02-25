<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<link href="css/consulta-reserva.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../css/main.css">

	<%if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String tipousu = (String) session.getAttribute("tipoUsuario");
    String usuario = (String) session.getAttribute("usuario");
    String aerolinea = null;

    // Asignar el valor de aerolinea si el usuario logueado es de tipo aerolinea
    if ("aerolinea".equals(tipousu)) {
        aerolinea = usuario;
    }

	%>

	<script>
		let reservas=null;
		var ok=false;
		String ruta;
	</script>


	<script>
		function volver() {
			document.getElementById("message").textContent = "";
			document.getElementById("form-consulta").style.display = "block";
			document.getElementById("info-reserva").style.display = "none";
		}
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
		    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
		    max-width: 400px;
		    margin: 0 auto;
		    align-items: center;
		    justify-content: center;
		    margin-left: 590px;
		    margin-top: 50px;
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
		    background-color: #0c6ef3;
		    color: black;
		    border: none;
		    padding: 10px 15px;
		    border-radius: 4px;
		    cursor: pointer;
		    font-size: 16px;
		    transition: background-color 0.3s;
		    margin-left:15px;
		}
		
		.consulta-button:hover {
		    background-color: #2C3F8B;
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
		
		.container {
		    display: flex;
		    justify-content: space-between;
		    display: none;
		    height: 100vh;
		}
		
		.text-column {
		    width: 900px;
		}
		
		.image-column {
		    width: 40%;
		    margin-top:40px;
		    margin-left:10px;
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
		#info-reserva{
			margin-left:380px;
			margin-top:50px;
		}
	</style>
</head>

<body>
	<jsp:include page="./header.jsp" />
	
	<div class="main-page">
		<div class="cat">
	    	<jsp:include page="./side-menu.jsp" />
		</div>
	
		<div class="main-content">
			<form id="form-consulta">
				<h1>Consulta una reserva de Vuelo!</h1>
				
				<%if(tipousu.equals("cliente")){%>
					<label id="label-c">Seleccione Aerolinea:</label>
					<select id="Aerolinea" class="contenido" onchange="seleccionarAerolinea()" required>
						<option disabled selected>Selecciona una opción</option>
						<% 
						List<DataAerolinea> aerolineas = (List<DataAerolinea>) request.getAttribute("aerolineas");
						if(aerolineas!=null){
							for(DataAerolinea aero : aerolineas){
								String nickname= aero.getNickname();%>
								<option value="<%=nickname%>"><%=nickname%></option>
							<%}
						}
						%>
					</select>
				<% } %>
				
				<label>Seleccione Ruta de Vuelo:</label>
				<select id="Ruta" class="contenido" onchange="seleccionarRuta()" required>
					<%if(tipousu.equals("aerolinea")){%>
						<option disabled selected>Selecciona una opción</option>
						<% List<DataRutaVuelo> rutas = (List<DataRutaVuelo>) request.getAttribute("rutas");
       					if (rutas != null){			
							for (DataRutaVuelo r : rutas) { 
								String nombre= r.getNombre();%>
								<option value="<%=nombre%>"><%=nombre%></option>
							<%}
						}
					}
					%>
				</select>


				<label>Seleccione Vuelo:</label>
				<select id="Vuelos" class="contenido" onchange="seleccionarVuelo()" required>
				</select>
				
				<% if ("aerolinea".equals(tipousu)){ %>
					<label id="label-r">Seleccione el cliente que realizo la reserva:</label>
					<select id="Reserva" class="contenido" onchange="seleccionarReserva()">
					</select>
				<%} %>

				<button class="consulta-button" type="submit">Consultar reserva</button>
				<p id="message"></p>
			</form>
			
			
			<div class="container" id="info-reserva">

				<div class="text-column">
					<h2>Informacion de reserva:</h2>

					<p><span class="etiqueta">Tipo asiento:</span>
						<span class="info" id="tipo" ></span>
					</p>

					<p><span class="etiqueta">Cantidad de pasajes:</span>
						<span class="info" id="cant-pas" ></span>
					</p>

					<p><span class="etiqueta">Cantidad de equipaje extra:</span>
						<span class="info" id="cant-eq"></span>
					</p>

					<p><span class="etiqueta">Fecha:</span>
						<span class="info" id="fecha"></span>
					</p>

					<p><span class="etiqueta">Costo:</span>
						<span class="info" id="costo"></span>
					</p>


					<p> <span class="etiqueta">Pasajeros:</span></p>
					<p><span class="info" id="pasajeros"></span></p>

					<button class="volver-button" onclick="volver();">Haz click aqui para realizar otra
						consulta!</button>

				</div>

				<div class="image-column">
					<p><span class="etiqueta">Realizada por:</span>
					<p><img id="imgC" src="" >
						<span class="info" id="cliente"></span>
					</p>

					<p><span class="etiqueta">Aerolinea:</span>
					<p><img id="imgA" src="" >
						<span class="info" id="aerolinea"></span>
					</p>

					<p><span class="etiqueta">Ruta de vuelo:</span>
					<p><img id="imgR"  src="">
						<span class="info" id="ruta"></span>
					</p>

					<p><span class="etiqueta">Vuelo:</span>
					<p><img id="imgV" src="">
						<span class="info" id="vuelo"></span>
					</p>
				</div>
			</div>
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
			<%if("aerolinea".equals(tipousu)){%>
				Reserva.innerHTML = '<option disabled selected>Selecciona una opción</option>';
			<%}%>
			
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

	<script>
		function seleccionarVuelo() {
			ok=false;
        	<%if ("aerolinea".equals(tipousu)){%>
        		Reserva.innerHTML = '';
        	<%}%>
			let url= "reservas-fetch?opcion="+Vuelos.value;
			
            fetch(url)
            .then(response => {
            	if (!response.ok) {
            		throw new Error('Network response was not ok');
            	}
            	return response.json();
            })
            .then(data => {
            	reservas=data;
                data.forEach(reserva => {
                	<%if ("aerolinea".equals(tipousu)){%>
                    	Reserva.add(new Option(reserva.NickC,reserva.NickC));
                	<%}else {%>
        				if(reserva.NickC==="<%=usuario%>"){
        					ok=true;
							document.getElementById('cliente').textContent = reserva.NickC;
							document.getElementById("aerolinea").textContent= aerolinea;
							document.getElementById("ruta").textContent= ruta;
							document.getElementById("vuelo").textContent= reserva.NomV;
							document.getElementById("tipo").textContent= reserva.tipoAsiento;
							document.getElementById("cant-pas").textContent= reserva.CantPasajes;
							document.getElementById("cant-eq").textContent= reserva.CantExtra;
							document.getElementById("fecha").textContent= reserva.FechaReserva;
							document.getElementById("costo").textContent= reserva.CostoTotal;
							//document.getElementById("tipo-res").textContent= reserva.TipoReserva;
							document.getElementById("pasajeros").textContent= reserva.pasajeros;
							//document.getElementById("imgC").src = request.getContextPath() + "/utils/images/" + "<=%imagenUsuario%>";
							//document.getElementById("imgA").src = 
							//document.getElementById("imgR").src = 
							//document.getElementById("imgV").src = 
        				}
                	<%}%>
                });
            })
            .catch(error => console.error('Error:', error));

		}
	</script>



	<script>
			document.getElementById('form-consulta').addEventListener('submit', function (event) {
				event.preventDefault();
				<%if("aerolinea".equals(tipousu)){%>
					usuario = document.getElementById('Reserva').value;
		            reservas.forEach(reserva => {
						if(usuario===reserva.NickC){
							ok=true;
							document.getElementById('cliente').textContent = reserva.NickC;
							document.getElementById("aerolinea").innerHTML= "<%=aerolinea%>";
							document.getElementById("ruta").textContent= ruta;
							document.getElementById("vuelo").textContent= reserva.NomV;
							document.getElementById("tipo").textContent= reserva.tipoAsiento;
							document.getElementById("cant-pas").textContent= reserva.CantPasajes;
							document.getElementById("cant-eq").textContent= reserva.CantExtra;
							document.getElementById("fecha").textContent= reserva.FechaReserva;
							document.getElementById("costo").textContent= reserva.CostoTotal;
							//document.getElementById("tipo-res").textContent= reserva.TipoRes;
							document.getElementById("pasajeros").textContent= reserva.pasajeros;
							//document.getElementById("imgC").src = 
							//document.getElementById("imgA").src = 
							//document.getElementById("imgR").src = 
							//document.getElementById("imgV").src = 
						}
		            });
		        <%}%>
                if(!ok){
					document.getElementById("message").style.color = 'red';
					document.getElementById("message").textContent = 'No existen reservas de este vuelo';
            	}else{
					document.getElementById("form-consulta").style.display = "none";
					document.getElementById("info-reserva").style.display = "flex";
            	}
		});
	</script>
	

</body>

</html>