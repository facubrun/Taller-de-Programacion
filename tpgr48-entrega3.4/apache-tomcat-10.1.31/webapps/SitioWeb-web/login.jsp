<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Volando.uy | Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .main-content {
            margin: 20px auto;
            padding: 20px;
            max-width: 600px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
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
        .register-link {
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>
<body>
	<div class="main-content">
	    <h1>Iniciar Sesión</h1>
	    <form action="login" method="post">
	        <div class="mb-3">
	            <label for="email" class="form-label">Email:</label>
	            <input type="email" id="email" name="email" class="form-control" required>
	            <small id="emailError" style="color: red; display: none;">El usuario no existe.</small>
	        </div>
	        <div class="mb-3">
	            <label for="password" class="form-label">Contraseña:</label>
	            <input type="password" id="password" name="password" class="form-control" required>
	        </div>
	        <button type="submit" class="btn btn-primary">Ingresar</button>
	    </form>

	    <% String error = (String) request.getAttribute("error"); %>
	    <% if (error != null) { %>
	        <p style="color: red;"><%= error %></p>
	    <% } %>

	    <div class="register-link">
	        <p>¿No tienes cuenta? <a href="registro_user">Regístrate</a></p>
	    </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
    document.getElementById("email").addEventListener("blur", function () {
        var email = this.value;
        var emailError = document.getElementById("emailError");
        
        if (email) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "VerificarUsuarioServlet?email=" + encodeURIComponent(email), true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    if (!response.existe) {
                        emailError.style.display = "block";
                    } else {
                        emailError.style.display = "none";
                    }
                }
            };
            xhr.send();
        } else {
            // Si el campo está vacío, oculta el mensaje de error
            emailError.style.display = "none";
        }
    });
</script>

    
</body>
</html>
