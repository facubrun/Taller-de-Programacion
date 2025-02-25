document.addEventListener('DOMContentLoaded', function () {
    const optionsRutas = document.getElementById('rutaVuelo').options;
    for (let i = 0; i < optionsRutas.length; i++) {
        if (optionsRutas[i].value !== '') {
            optionsRutas[i].hidden = true;
        }
    };

    const optionsVuelos = document.getElementById('vuelo').options;
    for (let i = 0; i < optionsVuelos.length; i++) {
        if (optionsVuelos[i].value !== '') {
            optionsVuelos[i].hidden = true;
        }
    };

    if (document.getElementById('nombre-vuelo').value === 'null') {
        document.getElementById('nombre-vuelo').value = '';
    }
    if (document.getElementById('fecha').value === 'null') {
        document.getElementById('fecha').value = '';
    }
    if (document.getElementById('duracion').value === 'null') {
        document.getElementById('duracion').value = '';
    }
    if (document.getElementById('capacidad-turistas').value === 'null') {
        document.getElementById('capacidad-turistas').value = '';
    }
    if (document.getElementById('capacidad-ejecutivos').value === 'null') {
        document.getElementById('capacidad-ejecutivos').value = '';
    }
    if (document.getElementById('fecha-alta').value === 'null') {
        document.getElementById('fecha-alta').value = '';
    }


    document.getElementById('aerolinea').addEventListener('change', function () {
        const aerolinea = document.getElementById('aerolinea').value;
        const optionsRutas = document.getElementById('rutaVuelo').options
        document.getElementById('rutaVuelo').value = '';
        for (let i = 0; i < optionsRutas.length; i++) {
            const rutaVuelo = optionsRutas[i].getAttribute('data-aerolinea');
            if (rutaVuelo === aerolinea) {
                optionsRutas[i].hidden = false;
            } else {
                if (optionsRutas[i].value !== '') {
                    optionsRutas[i].hidden = true;
                }
            }
        };

        const optionsVuelo = document.getElementById('vuelo').options;
        document.getElementById('vuelo').value = '';
        for (let i = 0; i < optionsVuelo.length; i++) {
            if (optionsVuelo[i].value !== '') {
                optionsVuelo[i].hidden = true;
            }
        };

    });

    document.getElementById('rutaVuelo').addEventListener('change', function () {
        const rutaVuelo = document.getElementById('rutaVuelo').value;
        const optionsVuelo = document.getElementById('vuelo').options;
        document.getElementById('vuelo').value = '';
        for (let i = 0; i < optionsVuelo.length; i++) {
            const rutaVueloVuelo = optionsVuelo[i].getAttribute('data-ruta-vuelo');
            if (rutaVueloVuelo === rutaVuelo) {
                optionsVuelo[i].hidden = false;
            }
            else {
                if (optionsVuelo[i].value !== '') {
                    optionsVuelo[i].hidden = true;
                }
            }
        };
    });
});
