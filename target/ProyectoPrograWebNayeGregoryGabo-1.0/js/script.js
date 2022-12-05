
const sidebar = document.querySelector('.sidebar');
const navItems = document.querySelectorAll('nav .nav-item');
const toggle = document.querySelector('.sidebar .toggle');

function Cargado() {
    Validar();
}

function Validar() {

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

toggle.addEventListener('click', () => {

    if (sidebar.className === 'sidebar')
        sidebar.classList.add('open');
    else
        sidebar.classList.remove('open');

});

navItems.forEach(navItem => {

    navItem.addEventListener('click', () => {

        navItems.forEach(navItem => {
            navItem.classList.remove('active');
        });

        navItem.classList.add('active');

    });

});
