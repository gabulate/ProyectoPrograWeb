//Función para descargar PDF
function CreatePDFfromHTML() {
    var doc = new jsPDF('l', 'px', [1500, 1080]);

    // Source HTMLElement or a string containing HTML.
    var elementHTML = document.querySelector("#contenido");

    doc.html(elementHTML, {
        callback: function (doc) {
            // Save the PDF
            var nombre = document.getElementsByClassName('fecha')[0].innerHTML;
            doc.save(nombre);
        },
        x: 200,
        y: 15
    });
}
;






