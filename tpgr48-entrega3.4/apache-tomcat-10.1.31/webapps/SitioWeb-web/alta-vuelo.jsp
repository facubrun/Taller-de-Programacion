<%@ page import="servidor.DataRutaVuelo"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Volando.uy | Nuevo Vuelo</title>
	<link rel="stylesheet" type="text/css" href="../css/alta-vuelo.css">
	<link rel="stylesheet" href="../css/main.css">
	
	<style>
		body {
		    margin: 0;  
		}
		
		.main-page {
		    display: flex;
		    padding: 0;
		    margin: 0;
		    top: 0;
		    left: 0;
		    width: 100%;
		    height: 100%;
		    font-family: 'Poppins', sans-serif;
		    overflow: auto;
		    align-items: center;
		    justify-content: center;
		    margin-top: 5px;
		   
		}
		
		label {
		    font-size: 13px;
		    font-weight: bold;
		}
		
		.form-alta {
		    display: flex;
		    flex-direction: column;
		    width: 90%;
		    padding: 20px;
		    border-radius: 8px;
		    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
		    background-color: white;
		    margin-left: 90px;
		}
		
		select {
		    width: 97%;
		    height: 35px; 
		    padding: 10px;
		    border: 1px solid #E78F79;
		    border-radius: 4px;
		    margin-bottom: 15px;
		    font-size: 16px;
		}
		
		form {
		    display: flex;
		    flex-direction: column;
		}
		
		.attribute {
		    margin: 10px;
		}
		
		.alta-button {
		    margin: 10px;
		    padding: 10px;
		    background-color: #0c6ef3;
		    color: rgb(0, 0, 0);
		    cursor: pointer;
		    border: none;
		    border-radius: 5px;
		}
		
		.alta-button:hover {
		    background-color: #2C3F8B;
		}
		
		input {
		    /* width: 100%; */
		    height: 35px;
		    padding: 10px;
		    border: 1px solid #E78F79;
		    border-radius: 4px;
		    margin-bottom: 15px;
		    font-size: 16px;
		}
		
		input[type="file"]::file-selector-button {
		    /*padding: 8px 16px;*/
		    border: none;
		    background-color: #007bff;
		    color: white;
		    font-size: 14px;
		    border-radius: 4px;
		    cursor: pointer;
		}
		
		input[type="file"]::file-selector-button:hover {
		    background-color: #1B42FF;
		}
		
		#message {
		    color: green;
		    font-size: 14px;
		    font-style: italic;
		    font-family: 'Poppins', sans-serif;
		}
	</style>
	
</head>

<body>
	<jsp:include page="./header.jsp" />
	<jsp:include page="./side-menu.jsp" />
	<div class="main-page">
		<div class="main-content">
			<div class="form-alta">
				<h1>Da de alta un Vuelo!</h1>
				<form id="alta-vuelo-form" action="alta-vuelo" method="post" enctype="multipart/form-data">
				
					<label for="nombre">Nombre:</label>
					<input class="attribute" type="text" id="nombre" pattern="^[a-zA-Z0-9]+$" title="letras y numeros" name="nombre" required>
					
					<label>Ruta de Vuelo:</label>
					<select id="Ruta" class="contenido" name="ruta" required>
						<% 
							List<DataRutaVuelo> rutas = (List<DataRutaVuelo>) request.getAttribute("rutas");
							if (rutas != null && !rutas.isEmpty()) {
								for (DataRutaVuelo ruta : rutas) {
									String nombre = ruta.getNombre();
						%>
									<option value="<%=nombre %>"><%=nombre %></option>
						<% 		}
							} else { %>
								<option value="">No hay rutas disponibles</option>
						<% } %>
					</select>

					<label for="fecha">Fecha:</label>
					<input class="attribute" type="date" id="fecha" name="fecha" required>

					<label for="Duracion">Duracion:</label>
					<input class="attribute" type="time" id="duracion" name="duracion" min="00:01" required>

					<label for="turista">Cantidad maxima de asientos turista:</label>
					<input class="attribute" type="number" id="turista" name="turista" min="0" required>

					<label for="ejecutivo">Cantidad maxima de asientos ejecutivo:</label>
					<input class="attribute" type="number" id="ejecutivo" name="ejecutivo" min="0" required>

					<label for="image">Sube una foto asociada al vuelo (opcional):</label>
					<input class="attribute" type="file" id="image" name="image" accept="image/*">

					<button class="alta-button" type="submit">Dar de Alta</button>
				</form>
				<p id="message"></p>
			</div>
		</div>
	</div>

	<!-- Handle messages after form submission -->
    <% String error = (String) request.getAttribute("error");
    if (error != null) {
	    if (error.equals("exito")) { %>
	        <script>
	        	document.getElementById('message').textContent = 'Alta de vuelo ejecutada con éxito';
	        </script>
	   <% } else if (error.equals("NombreVRepetidoExce")) { %>
	        <script>
					document.getElementById('message').style.color = 'red';
					document.getElementById('message').textContent = 'El nombre de Vuelo ya está en uso';
	        </script>
	   <% } else if (error.equals("RVNoExisteExce")) { %>
	        <script>
	        		document.getElementById('message').style.color = 'red';
					document.getElementById('message').textContent = 'La ruta de vuelo no existe';
	        </script>
	   <% } else { %>
	        <script>
	        		document.getElementById('message').style.color = 'red';
					document.getElementById('message').textContent = 'No se pudo ejecutar el Alta de Vuelo';
	        </script>
	   <% } } %>

	<script>
		const hoy = new Date().toISOString().split('T')[0];
		document.getElementById('fecha').setAttribute('min', hoy);
	</script>

	<script>
		// File validation for image type
		document.getElementById('alta-vuelo-form').addEventListener('submit', function (event) {
			const file = document.getElementById('image').files[0];
			if (file && !file.type.startsWith('image/')) {
				document.getElementById('message').style.color = 'red';
				document.getElementById('message').textContent = 'Por favor, ingrese una imagen válida';
				event.preventDefault();
				return;
			}
		});
	</script>
</body>
</html>