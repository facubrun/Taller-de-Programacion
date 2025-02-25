// document.addEventListener("DOMContentLoaded", function () {
//     RUTASVUELO.forEach(function (ruta) {
//         document.getElementById("main-content").innerHTML += `
            // <div class="fila-viaje" onclick="verDetalles('${ruta.nombre}')">
            //     <div class="contenedor-imagen-viaje">
            //         <img src="${ruta.imagen}" alt="Foto del viaje" class="imagen-viaje">
            //     </div>
            //     <div class="informacion-viaje">
            //         <h2 class="titulo-viaje">${ruta.descripcionCorta}</h2>
            //         <p class="descripcion-viaje">${ruta.descripcion}</p>
            //         <p class="detalles-viaje">
            //             Hora de salida: ${ruta.hora} | Costo Turista: $${ruta.costoTurista} | Estado: ${ruta.estado}
            //         </p>
            //     </div>
            // </div>
//         `;
//     });
// });

// verDetalles = function (nombre) {
//     window.location.href = "../html/detalle-ruta-vuelo.html?nombre=" + nombre;
// }