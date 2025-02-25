<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Volando.uy | Nuevo Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .main-content {
            margin: 20px auto;
            padding: 20px;
            max-width: 600px;
        }
       @media (min-width: 992px) {
        .main-content {
        	background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
        }
        button[type="submit"] {
            width: 100%;
        }
        .form-label {
            font-weight: bold;
        }
    </style>
</head>
<body>    
    <div class="main-content">
        <h1>Registrar Usuario</h1>

        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
            <p style="color: red;"><%= error %></p>
        <% } %>

        <form action="registro_user" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="nickname" class="form-label">Nickname:</label>
                <input type="text" id="nickname" name="nickname" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" id="nombre" name="nombre" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" id="email" name="email" class="form-control" required>
            </div>
            
            <div class="mb-3">
            	<label for="password" class="form-label">Contraseña:</label>
            	<input type="password" id="password" name="password" class="form-control" required>
        	</div>
        	
        	<div class="mb-3">
		        <label for="profileImage" class="form-label">Imagen de Perfil:</label>
		        <input type="file" id="profileImage" name="profileImage" class="form-control" accept="image/*" required>
		    </div>
            
            <div class="mb-3">
			    <label for="tipo" class="form-label">Tipo de Usuario:</label>
			    <select id="tipo" name="tipo" class="form-select" required>
			        <option value="cliente">Cliente</option>
			        <option value="aerolinea">Aerolínea</option>
			    </select>
			</div>
			
            <div id="cliente-fields" style="display: block;">
		        <div class="mb-3">
		            <label for="apellido" class="form-label">Apellido:</label>
		            <input type="text" id="apellido" name="apellido" class="form-control">
		        </div>
		        <div class="mb-3">
		            <label for="nacimiento" class="form-label">Fecha de Nacimiento:</label>
		            <input type="date" id="nacimiento" name="nacimiento" class="form-control">
		        </div>
		        <div class="mb-3">
		            <label for="nacionalidad" class="form-label">Nacionalidad:</label>
		            <input type="text" id="nacionalidad" name="nacionalidad" class="form-control">
		        </div>
		        <div class="mb-3">
		            <label for="tipo_documento" class="form-label">Tipo de Documento:</label>
		            <select id="tipo_documento" name="tipo_documento" class="form-select">
		                <option value="CI">Cédula de Identidad</option>
		                <option value="Pasaporte">Pasaporte</option>
		            </select>
		        </div>
		        <div class="mb-3">
		            <label for="documento" class="form-label">Número de Documento:</label>
		            <input type="text" id="documento" name="documento" class="form-control">
		        </div>
		    </div>
		    
		    <div id="aerolinea-fields" style="display: none;">
		        <div class="mb-3">
		            <label for="descripcion" class="form-label">Descripción:</label>
		            <input type="text" id="descripcion" name="descripcion" class="form-control">
		        </div>
		        <div class="mb-3">
		            <label for="link" class="form-label">Sitio Web:</label>
		            <input type="url" id="link" name="link" class="form-control">
		        </div>
		    </div>
            <button type="submit" class="btn btn-primary">Registrar</button>
        </form>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
    document.getElementById("tipo").addEventListener("change", function() {
        var tipo = this.value;
        var clienteFields = document.getElementById("cliente-fields");
        var aerolineaFields = document.getElementById("aerolinea-fields");
        
        if (tipo === "cliente") {
            clienteFields.style.display = "block";
            aerolineaFields.style.display = "none";
        } else if (tipo === "aerolinea") {
            clienteFields.style.display = "none";
            aerolineaFields.style.display = "block";
        }
    });
	</script>

</body>
</html>
