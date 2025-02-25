var submenuUsuario = document.getElementById('perfil-submenu-div');

if (sessionStorage.getItem('user') != undefined) {
    if (JSON.parse(sessionStorage.getItem('user')).tipo == 'C') { // para cliente
        nickname = JSON.parse(sessionStorage.getItem('user')).nickname;
        submenuUsuario.innerHTML = `
            <li class="mb-1">
            <p class="subtitle"><a href="consulta-usuario.html" target="_PARENT">Mi perfil</a></p>
            <div id="perfil-submenu" class="submenu">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="./reserva-vuelo.html" class="link-dark rounded" target="_PARENT">Reservar Vuelo</a></li>
                    <li><a href="./consulta-reserva.html" class="link-dark rounded" target="_PARENT">Mis Reservas</a></li>
                    <li><a href="./compra-paquete.html" class="link-dark rounded" target="_PARENT">Comprar Paquete</a></li>
                    <li><a href="./consulta-paquete.html" class="link-dark rounded" target="_PARENT">Mis Paquetes</a></li>
                    <li><a href="./consulta-usuario" class="link-dark rounded" target="_PARENT">Consultar Usuarios</a></li>
                    <li><a href="./consulta-ruta-vuelo.html" class="link-dark rounded" target="_PARENT">Consultar Rutas de Vuelo</a></li>
                    <li><a href="./consulta-vuelo.html" class="link-dark rounded" target="_PARENT">Consultar Vuelos</a></li>

                </ul>
            </div>
            </li>
        `;
    } else if (JSON.parse(sessionStorage.getItem('user')).tipo == 'A') { // para aerolinea
        nickname = JSON.parse(sessionStorage.getItem('user')).nickname;
        submenuUsuario.innerHTML = `
            <li class="mb-1">
            <p class="subtitle"><a href="consulta-usuario.html" target="_PARENT">Mi perfil</a></p>
            <div id="perfil-submenu" class="submenu">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="./alta-ruta-vuelo.html" class="link-dark rounded" target="_PARENT">Nueva Ruta</a></li>
                    <li><a href="./consulta-ruta-vuelo.html" class="link-dark rounded" target="_PARENT">Consultar Rutas de Vuelo</a></li>
                    <li><a href="./alta-vuelo.html" class="link-dark rounded" target="_PARENT">Nuevo Vuelo</a></li>
                    <li><a href="./consulta-vuelo.html" class="link-dark rounded" target="_PARENT">Consultar Vuelos</a></li>
                    <li><a href="./consulta-usuario" class="link-dark rounded" target="_PARENT">Consultar Usuarios</a></li>
                    <li><a href="./consulta-reserva.html" class="link-dark rounded" target="_PARENT">Consultar Reservas</a></li>
                </ul>
            </div>
            </li>
        `;
    }
} else {
    submenuUsuario.innerHTML = `
    <li class="mb-1">
    <p class="subtitle">Acciones</p>
        <div id="perfil-submenu" class="submenu">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                <li><a href="./consulta-usuario" class="link-dark rounded" target="_PARENT">Consultar Usuarios</a></li>
                <li><a href="./consulta-ruta-vuelo.html" class="link-dark rounded" target="_PARENT">Consultar Rutas de Vuelo</a></li>
                <li><a href="./consulta-vuelo.html" class="link-dark rounded" target="_PARENT">Consultar Vuelos</a></li>
                <li><a href="./consulta-paquete.html" class="link-dark rounded" target="_PARENT">Consultar Paquetes</a></li>
            </ul>
        </div>
    </li>
`;
}


