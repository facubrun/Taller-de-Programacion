document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('btnAgregar').addEventListener('click', function() {
        const texto = document.getElementById('inputTexto').value;
        if (texto) {
            const li = document.createElement('li');
            li.className = 'list-group-item d-flex justify-content-between align-items-center';
            
            li.innerHTML = `
                ${texto}
                <div>
                    <button type="button" class="btn btn-danger btn-sm" onclick="eliminarElemento(this)">Eliminar</button>
                </div>
            `;

            document.getElementById('lista').appendChild(li);
            document.getElementById('inputTexto').value = '';
        }
    });

    document.getElementById('datos-reserva').addEventListener('submit', function(event) {
        const listaItems = Array.from(document.querySelectorAll('#lista li'));
        const datos = listaItems.map(li => li.firstChild.textContent.trim().replace(/\n/g, ''));
        document.getElementById('nombres-pasajeros').value = JSON.stringify(datos);
    });
});