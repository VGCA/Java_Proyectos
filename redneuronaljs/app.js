
/**
 * LIBRERÍA UTILIZADA PARA ESTA PRÁCTICA
 */
let network = new brain.NeuralNetwork();

/**
 * SE COLOCAN EJEMPLOS SOBRE CUÁNDO PONER
 * TEXTO BLANCO Ó NEGRO SEGÚN EL FONDO DONDE
 * 1 ES BLANCO Y NEGRO ES 0
 */
network.train([
    { input: { rojo: 0, verde: 0, azul: 0 }, output: { color: 1 } },
    { input: { rojo: 1, verde: 1, azul: 1 }, output: { color: 0 } },
    { input: { rojo: 0, verde: 1, azul: 0 }, output: { color: 0 } },
    { input: { rojo: 0, verde: .43, azul: 1 }, output: { color: 1 } },
    { input: { rojo: 1, verde: 0, azul: 0 }, output: { color: 1 } },
]);

function update(color) {

    let rgb = [color.channels.r, color.channels.g, color.channels.b];

    let div = document.getElementById("caja");
    div.style.background = color.toHEXString(); //Colocar el color de fondo

    /**
     * TOMAMOS EL COLOR DE FONDO PARA QUE LA
     * LIBRERÍA DETERMINE EL MEJOR COLOR PARA EL TEXTO
     */
    let entrada = {
        rojo: rgb[0] / 255,
        verde: rgb[1] / 255,
        azul: rgb[2] / 255
    };

    //Obtenemos la predicción del color del texto
    let resultado = network.run(entrada);

    //Si resultado es mayor que .5, se considera color de texto blanco
    if (resultado.color > .5) {
        div.style.color = "white";
    } else {
        div.style.color = "black";
    }
}