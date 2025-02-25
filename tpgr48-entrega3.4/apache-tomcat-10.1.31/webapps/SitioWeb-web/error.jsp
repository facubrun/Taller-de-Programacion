<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error - Página no encontrada</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <jsp:include page="./header.jsp" />
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3">
                <jsp:include page="./side-menu.jsp" />
            </div>
            <div class="col-md-9">
                <div class="container mt-5">
                    <div class="alert alert-danger" role="alert">
                        <h1 class="display-4">404 - Página no encontrada</h1>
                        <p class="lead">Lo sentimos, la página que estás buscando no existe.</p>
                        <hr class="my-4">
                        <p>Es posible que la página haya sido eliminada, que la dirección URL esté mal escrita o que el contenido ya no esté disponible.</p>
                        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/" role="button">Volver al inicio</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
