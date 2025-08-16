
const data = new Date();

const dias = ["Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"];

const meses = [
    "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
    "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];

const map = new Map();

const año = data.getFullYear();

const esBisiesto = (año) => {
    return (año % 400 === 0) || (año % 100 !== 0 && año % 4 === 0);
};

for (i = 0; i < meses.length; i++) {
     if (i === 1) {
        map.set(meses[i], esBisiesto(año) ? 29 : 28);
    } else if (i === 0 || i === 2 || i === 4 || i === 6 || i === 7 || i === 9 || i === 11) {
        map.set(meses[i], 31);
    } else {
        map.set(meses[i], 30);
    }
}

function darFechaActual() {

    const mesActual = meses[data.getMonth()];

    document.getElementById("dia").innerHTML=`${data.getDate()}`;
    document.getElementById("mesAño").innerHTML=`${mesActual} de ${data.getFullYear()}`;
}

function llenarTabla() {
    const mes = meses[data.getMonth()];

    const tabla = document.getElementById("tabla").getElementsByTagName("tbody")[0];
    
    const diasDelMes = map.get(mes);
    const primerDia = new Date(año, data.getMonth(), 1).getDay();

    let dia = 1;
    let fila = document.createElement("tr");

    //CREA LOS ESPACIOS EN BLANCO
    for (let i = 0; i < primerDia; i++) {
        fila.appendChild(document.createElement("td"));
    }

    //RELLANA SEMANAL 7 DIAS
    for (let i = primerDia; i < 7; i++) {
        const celda = document.createElement("td");
        celda.textContent = dia++; //AGREGA EL DIA SUMANDO +1
        fila.appendChild(celda);
    }
    tabla.appendChild(fila);


   while (dia <= diasDelMes) {

        fila = document.createElement("tr");
        for (let i = 0; i < 7 && dia <= diasDelMes; i++) {
            const celda = document.createElement("td");
            celda.textContent = dia++;
            fila.appendChild(celda);
        }
        tabla.appendChild(fila);
    }

}

window.addEventListener("DOMContentLoaded", darFechaActual);
window.addEventListener("DOMContentLoaded", llenarTabla);