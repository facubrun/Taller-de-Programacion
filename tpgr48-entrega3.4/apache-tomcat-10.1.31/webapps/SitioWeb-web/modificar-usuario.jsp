<%@ page import="servidor.DataCliente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
 <jsp:include page="./header.jsp" />
    <jsp:include page="./side-menu.jsp" />
    <div class="container col-6">
        <h2>Editar Usuario</h2>
        <form action="modificar-usuario" method="post">
            <input type="hidden" name="nickname" value="<%= request.getParameter("nickname") %>">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" id="nombre" name="nombre" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="apellido" class="form-label">Apellido:</label>
                <input type="text" id="apellido" name="apellido" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="nacimiento" class="form-label">Fecha de Nacimiento:</label>
                <input type="date" id="nacimiento" name="nacimiento" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="nacionalidad" class="form-label">Nacionalidad:</label>
                <input type="text" id="nacionalidad" name="nacionalidad" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        </form>
    </div>
</body>
</html>
