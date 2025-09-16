
//API DEL CLIMA
async function consultarClima() {

    const emojiClimas=["☀️","⛅","🌤️","❄️"];
    let emoji="";
    const ciudad = document.getElementById("ciudad").textContent;
    const url = `/clima?ciudad=${encodeURIComponent(ciudad)}`;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error("Error en la respuesta: " + response.status);
        }

        const data = await response.json(); //TRANFORMA EL DATO EN JSON

        //EN EL data.data pedimos el JSON del bloque data{ datos}
        const clima = data.data[0]; //AQUI OBTENEMOS TODOS LOS DATOS DEL JSON
    
        // Evaluar la temperatura y asignar el emoji correspondiente
        if (clima.temp <= 15) {
            emoji = emojiClimas[3]; // Frío ❄️
        } else if (clima.temp > 15 && clima.temp <= 25) {
            emoji = emojiClimas[1]; // Nublado ⛅
        } else if (clima.temp > 25 && clima.temp <= 35) {
            emoji = emojiClimas[2]; // Calor moderado 🌤️
        } else {
            emoji = emojiClimas[0]; // Mucho calor ☀️
        }

        //MANDAMOS LA TEMPERATURA COMO TEXTO clima.temp es el dato del json como un mapeo
       document.getElementById("temperatura").innerHTML = `${clima.temp} °C ${emoji}`;

    } catch (error) {
        document.getElementById("resultado").innerHTML = `<p style="color:red;">${error.message}</p>`;
    }
}

//document.addEventListener("DOMContentLoaded", consultarClima);