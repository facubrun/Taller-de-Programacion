document.addEventListener('DOMContentLoaded', function() {
    const buscarPorSelect = document.getElementById('buscar_por');
    const aerolineaSelect = document.getElementById('aerolinea');
    const categoriaSelect = document.getElementById('categoria');
    const rutaVueloSelect = document.getElementById('rutaVuelo');

    // Definir la función buscarPor en el contexto global
    window.buscarPor = function() {
        const seleccion = buscarPorSelect.value;
        rutaVueloSelect.value = '';  // Limpiar rutas seleccionadas

        if (seleccion === "aerolinea") {
            document.getElementById('busqueda_aerolinea').classList.remove('d-none');
            document.getElementById('busqueda_categoria').classList.add('d-none');
            filtrarRutas('aerolinea');
        } else if (seleccion === "categoria") {
            document.getElementById('busqueda_aerolinea').classList.add('d-none');
            document.getElementById('busqueda_categoria').classList.remove('d-none');
            filtrarRutas('categoria');
        }
    };

    function filtrarRutas(tipoFiltro) {
        const optionsRutas = rutaVueloSelect.options;
        let filtroSeleccionado = '';

        if (tipoFiltro === 'aerolinea') {
            filtroSeleccionado = aerolineaSelect.value;
            for (let i = 0; i < optionsRutas.length; i++) {
                const aerolinea = optionsRutas[i].getAttribute('data-aerolinea');
                optionsRutas[i].hidden = aerolinea !== filtroSeleccionado;
            }
        } else if (tipoFiltro === 'categoria') {
            filtroSeleccionado = categoriaSelect.value;
            for (let i = 0; i < optionsRutas.length; i++) {
                const categoriasRuta = optionsRutas[i].getAttribute('data-categorias');
                if (categoriasRuta) {
                    const categoriasArray = categoriasRuta.split(',');
                    optionsRutas[i].hidden = !categoriasArray.includes(filtroSeleccionado);
                } else {
                    optionsRutas[i].hidden = true;
                }
            }
        }
    }

    buscarPorSelect.addEventListener('change', buscarPor);
    aerolineaSelect.addEventListener('change', function() {
        ocultarTodasLasRutas();
        filtrarRutas('aerolinea');
        categoriaSelect.value = '';
    });
    categoriaSelect.addEventListener('change', function() {
        ocultarTodasLasRutas();
        filtrarRutas('categoria');
        aerolineaSelect.value = '';
    });

    function ocultarTodasLasRutas() {
        const optionsRutas = rutaVueloSelect.options;
        for (let i = 0; i < optionsRutas.length; i++) {
            optionsRutas[i].hidden = optionsRutas[i].value !== '';
        }
    }

    ocultarTodasLasRutas(); // Ocultar todas las rutas al cargar la página
});