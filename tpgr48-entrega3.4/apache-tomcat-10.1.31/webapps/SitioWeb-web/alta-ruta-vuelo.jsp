<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Volado.uy | Nueva ruta de vuelo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link href="css/alta-ruta-vuelo.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<style>
	.is-invalid {
    border-color: #dc3545;
    background-color: #f8d7da;
}
	
	</style>
</head>
<body>
	<div class="row">
		<jsp:include page="./header.jsp" />
	</div>
	<div class="row">
		<jsp:include page="./side-menu.jsp" />
		
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

			<form id="form_alta_ruta" action="alta-ruta-vuelo" method="post" enctype="multipart/form-data">
	        	<div class="mb-3">
	        		<h1>Dar de alta una Ruta de Vuelo</h1>
	        	</div>
	        	
	            
	            <div class="mb-3">
				    <label for="nombre" class="form-label">Nombre de la Ruta:</label>
				    <input type="text" id="nombre" name="nombre" class="form-control" required>
				    <small id="nombreError" style="color: red; display: none;">El nombre de la ruta ya existe.</small>
				</div>
	            
	            <div class="mb-3">
		            <label for="descripcion_corta" class="form-label">Descripción corta:</label>
		            <input type="text" id="descripcion_corta" name="descripcion_corta" class="form-control" required>
	            </div>
	            
	            <div class="mb-3">
		            <label for="descripcion" class="form-label">Descripción:</label>
		            <input type="text" id="descripcion" name="descripcion" class="form-control" required>
	            </div>
	            
	            <div class="mb-3">
		            <label for="hora" class="form-label">Hora:</label>
		            <input type="time" id="hora" name="hora" class="form-control" required>
	            </div>
	            
	            <div class="mb-3">
		            <label for="costo_turista" class="form-label">Costo turista:</label>
		            <input type="number" id="costo_turista" name="costo_turista" class="form-control" min="1" value="0" required>
	            </div>
	            
	            <div class="mb-3">
		            <label for="costo_ejecutivo" class="form-label">Costo ejecutivo:</label>
		            <input type="number" id="costo_ejecutivo" name="costo_ejecutivo" class="form-control" min="1" value="0" required>
	            </div>
	            
	            <div class="mb-3">
		            <label for="costo_extra" class="form-label">Costo de equipaje extra:</label>
		            <input type="number" id="costo_extra" name="costo_extra" class="form-control" min="1" value="0" required>
	            </div>
	            
	            <div class="mb-3">
		            <label for="ciudad_origen" class="form-label">Ciudad de origen:</label>
		            <select id="ciudad_origen" name="ciudad_origen" class="form-control" required>
		            	<option value="seleccionar-ciudad-origen">Seleccionar ciudad de origen...</option>
		            	<% 
		            		List<String> ciudades = (List<String>) request.getAttribute("ciudades");
		            		if (ciudades != null) {
		            			for (String ciudad : ciudades) {
		            				
		            				%>
		            				<option value="<%= ciudad %>"><%= ciudad %></option>
		            			<% 
		            			}
		            		} %>
		            </select>
	            </div>
	            
	            <div class="mb-3">
		            <label for="ciudad_destino" class="form-label">Ciudad de destino:</label>
		            <select id="ciudad_destino" name="ciudad_destino" class="form-control" required>
		            	<option value="seleccionar-ciudad-destino">Seleccionar ciudad de destino...</option>
		            	<% 
		            		if (ciudades != null) {
		            			for (String ciudad : ciudades) {
		            				
		            				%>
		            				<option value="<%= ciudad %>"><%= ciudad %></option>
		            			<% 
		            			}
		            		} %>
		            </select>
	            </div>
	            
	            <div class="mb-3">
		            <label for="categorias" class="form-label">Categorias:</label>
		            <select id="categorias" name="categorias" class="form-control" multiple size="4">
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
	            
	            <div class="mb-3">
		            <label for="imagen" class="form-label">Imagen (opcional):</label>
		            <input type="file" id="imagen" name="imagen" class="form-control" accept="image/*">
	            </div>
	            
	            <div class="mb-3 row">
	            	<div class="col">
	           			<button class="btn btn-primary w-100" type="submit">Dar de Alta</button>
	           		</div>
	           		
	           		<div class="col">
	           			<button class="btn btn-primary w-100" type="reset">Cancelar</button>
	           		</div>
           		</div>
           </form>
		</div>
	</div>
<script>
document.getElementById('nombre').addEventListener('blur', function () {
    var nombreRuta = this.value.trim();
    var nombreError = document.getElementById('nombreError');

    if (nombreRuta) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "verificarRuta?nombre=" + encodeURIComponent(nombreRuta), true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                if (response.existe) {
                    nombreError.style.display = "block"; // Mostrar mensaje de error
                    document.getElementById('nombre').classList.add('is-invalid'); // Resaltar campo
                } else {
                    nombreError.style.display = "none"; // Ocultar mensaje de error
                    document.getElementById('nombre').classList.remove('is-invalid'); // Quitar resaltado
                }
            }
        };
        xhr.send();
    } else {
        nombreError.style.display = "none"; // Ocultar mensaje si el campo está vacío
    }
});

</script>
</body>
</html>