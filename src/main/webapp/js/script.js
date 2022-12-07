//Variables para la animación del sidebar
const sidebar = document.querySelector('.sidebar');
const navItems = document.querySelectorAll('nav .nav-item');
const toggle = document.querySelector('.sidebar .toggle');

//Event Listener que actualiza la clase del sidebar para mostrar si está abierto o cerrado
toggle.addEventListener('click', () => {

    if (sidebar.className === 'sidebar')
        sidebar.classList.add('open');
    else
        sidebar.classList.remove('open');

});

//Event Listeners que actualizan las clases de los botones
/*navItems.forEach(navItem => {
 
 navItem.addEventListener('click', () => {
 
 navItems.forEach(navItem => {
 navItem.classList.remove('active');
 });
 
 navItem.classList.add('active');
 
 });
 
 });*/

//Función que deshabilita los botones correspondientes según el rol
function Cargado() {
    var rol = document.getElementById("rol").innerHTML;
    navItems.forEach(navItem => {

        if (rol === 'Recursos Humanos') {
            if (navItem.className !== 'empleado nav-item') {
                if (navItem.className !== 'salir nav-item') {
                    navItem.remove();
                }
            }

        }
        if (rol === 'Planillero') {
            if (navItem.className === 'empleado nav-item') {
                navItem.remove();
            }
        }
    });
}


