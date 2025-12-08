/**
 * Clase CuadroMagico. Es un juego donde tienes que colocar en un tablero 4x4 los numeros del 1 al 16, y todas las lineas y diagonales tienen que sumar la misma cantidad (34)
 * 
 * @author Iago HB
 */
import java.util.Scanner;

package juegos;
public class CuadroMagico {

    private int[][] tablero = new int[4][4]; // Tablero 4x4
    private int[] numsDisponibles = new int[16]; // Lista de numeros disponibles, es decir, los numeros que aun no estan en el tablero

    /** Colores para imprimir mas bonito texto en la terminal */ 
    private final String RESET = "\u001B[0m";
    private final String GRN = "\u001B[32m";
    private final String YLW = "\u001B[33m";
    private final String CYN = "\u001B[36m";

    /** Este metodo genera un numero al azar dentro de un intervalo cerrado */
    private int generarNumero(int min, int max) {
        int num = (int) (Math.random() * (max - min + 1) + min);

        // Lei en un lado que este metodo no es 100% preciso, pues a veces puede generar un numero fuera del rango. Estos ifs corrigen eso creo
        if (num >= max) {num = max;}
        if (num <= min) {num = min;}

        return num;
    }

    /** Este es un metodo auxiliar de dibujarTablero(). Recibe una casilla del tablero y devuelve un string mas bonito para imprimir en el tablero */
    private String ficha(int x) {
        String texto;

        if (x == 0) {texto = "  ";}
        else if (x < 10) {texto = " " + x;}
        else {texto = Integer.toString(x);}

        return texto;
    }

    /** Este metodo imprime el tablero y la lista de numeros disponibles. Amarillo significa que son los numeros default, verde son los numeros que puso el usuario, cyan es solo para que preste atencion */
    private void dibujarTablero() {
        System.out.println("\n╔════"+CYN+"1"+RESET+"═══╦════"+CYN+"2"+RESET+"═══╦════"+CYN+"3"+RESET+"═══╦════"+CYN+"4"+RESET+"═══╗");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println(CYN+"1"+RESET+"   "+YLW+ficha(tablero[0][0])+RESET+"   ║   "+GRN+ficha(tablero[0][1])+RESET+"   ║   "+GRN+ficha(tablero[0][2])+RESET+"   ║   "+GRN+ficha(tablero[0][3])+RESET+"   ║");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("╠════════╬════════╬════════╬════════╣");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println(CYN+"2"+RESET+"   "+GRN+ficha(tablero[1][0])+RESET+"   ║   "+YLW+ficha(tablero[1][1])+RESET+"   ║   "+GRN+ficha(tablero[1][2])+RESET+"   ║   "+GRN+ficha(tablero[1][3])+RESET+"   ║");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("╠════════╬════════╬════════╬════════╣");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println(CYN+"3"+RESET+"   "+GRN+ficha(tablero[2][0])+RESET+"   ║   "+GRN+ficha(tablero[2][1])+RESET+"   ║   "+YLW+ficha(tablero[2][2])+RESET+"   ║   "+GRN+ficha(tablero[2][3])+RESET+"   ║");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("╠════════╬════════╬════════╬════════╣");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println(CYN+"4"+RESET+"   "+GRN+ficha(tablero[3][0])+RESET+"   ║   "+GRN+ficha(tablero[3][1])+RESET+"   ║   "+GRN+ficha(tablero[3][2])+RESET+"   ║   "+YLW+ficha(tablero[3][3])+RESET+"   ║");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("╚════════╩════════╩════════╩════════╝\n");

        System.out.print("Numeros restantes:\n| ");
        for(int i = 0; i < 16; i++) {
            if (numsDisponibles[i] != 0) {System.out.print(GRN + ficha(numsDisponibles[i]) + RESET + " | ");}
        }
        System.out.print("\n");
    }

    /** Este metodo limpia el tablero, genera numeros al azar y los coloca en una diagonal para preparar el juego (siempre y cuando sean diferentes y sumen 34) */
    private void genInicio() {
        int[] estadoInicial = new int[4];
        boolean valido;

        // Limpia el tablero
        for (int i = 0; i < 4; i++) {for (int j = 0; j < 4; j++) {
            tablero[i][j] = 0;
        }}

        // Limpia los numeros disponibles
        for (int i = 0;i < 16;i++){numsDisponibles[i] = i+1;}

        // En un bucle, genera 4 numeros, y se asegura que sean diferentes y que sumen 34 antes de continuar. Si no se cumplen las 2 condiciones genera otros 4 numeros
        do {
            valido = true;
            estadoInicial[0] = generarNumero(1, 16);
            estadoInicial[1] = generarNumero(1, 16);
            estadoInicial[2] = generarNumero(1, 16);
            estadoInicial[3] = generarNumero(1, 16);

            for (int i = 0; i < 4; i++) {for (int j = 0; j < 4; j++) {
                if(i != j) {if(estadoInicial[i] == estadoInicial[j]) {
                    valido = false;
                }}                
            }}

            if(estadoInicial[0]+estadoInicial[1]+estadoInicial[2]+estadoInicial[3] != 34) {valido = false;}
        } while (!valido);

        // Se asignan los 4 numeros a la diagonal del tablero
        tablero[0][0] = estadoInicial[0];
        tablero[1][1] = estadoInicial[1];
        tablero[2][2] = estadoInicial[2];
        tablero[3][3] = estadoInicial[3];

        // Se quitan los numeros usados de la lista de numeros disponibles
        for (int i = 0; i < 16; i++) {for (int j = 0; j < 4; j++) {
            if (numsDisponibles[i] == estadoInicial[j]) {numsDisponibles[i] = 0;}
        }}
    }

    /** Este metodo sirve para colocar una ficha */
    private void colocarFicha() {
        Scanner in = new Scanner(System.in);
        int x, y, ficha;
        boolean valido;

        do {
            // Se asume que valido es false, luego, si se cumplen ciertas condiciones, se vuelve true. 'x' y 'y' se inicializan por si acaso, realmente no importan
            valido = false;
            x = -1; y = -1;

            System.out.println("¿Qué "+GRN+"número"+RESET+" quiere colocar?");
            ficha = in.nextInt();

            // Despues de leer la ficha que se quiere colocar, primero se verifica que este dentro de los numeros disponibles. De ser asi, valido = true
            for (int i = 0; i < 16; i++) {
                if (ficha == numsDisponibles[i] && ficha != 0) {valido = true;}
            }

            // Si valido es true luego del check anterior, se leen las coordenadas para colocar la ficha
            if (valido) {
                System.out.println("\n¿"+CYN+"Dónde"+RESET+" lo quiere colocar? (Escriba de forma \""+CYN+"X Y"+RESET+"\" con un espacio)");
                x = in.nextInt();
                y = in.nextInt();
            }

            // Se verifica que las coordenadas esten dentro del tablero...
            if (x < 1 || x > 4 || y < 1 || y > 4) {valido = false;}

            // ...y que no haya numeros en esa casilla
            if (tablero[x-1][y-1] != 0) {valido = false;}

            // Si no se completaron los checks de antes, se pide que se escoja un numero valido y se reinicia el bucle
            if (!valido) {System.out.println("\nEscoja un número válido\n");}
        } while (!valido);

        //Si fue valido, se coloca la ficha en el tablero y se elimina de los numeros disponibles
        tablero[y-1][x-1] = ficha;
        numsDisponibles[ficha - 1] = 0;
    }

    /** Este metodo checa si se termina el juego, ya sea porque ganaste o porque perdiste */
    private int ganar() {
        // Se suman todas las lineas y diagonales del tablero para compararlas despues
        int columna1 = tablero[0][0] + tablero[1][0] + tablero[2][0] + tablero[3][0];
        int columna2 = tablero[0][1] + tablero[1][1] + tablero[2][1] + tablero[3][1];
        int columna3 = tablero[0][2] + tablero[1][2] + tablero[2][2] + tablero[3][2];
        int columna4 = tablero[0][3] + tablero[1][3] + tablero[2][3] + tablero[3][3];
        int fila1 = tablero[0][0] + tablero[0][1] + tablero[0][2] + tablero[0][3];
        int fila2 = tablero[1][0] + tablero[1][1] + tablero[1][2] + tablero[1][3];
        int fila3 = tablero[2][0] + tablero[2][1] + tablero[2][2] + tablero[2][3];
        int fila4 = tablero[3][0] + tablero[3][1] + tablero[3][2] + tablero[3][3];
        int diagonal1 = tablero[0][0] + tablero[1][1] + tablero[2][2] + tablero[3][3];
        int diagonal2 = tablero[3][0] + tablero[2][1] + tablero[1][2] + tablero[0][3];

        // Si todas las lineas suman 34, se devuelve 1, es decir, ganaste
        if (columna1 == 34 && columna2 == 34 && columna3 == 34 && columna4 == 34 &&
            fila1 == 34 && fila2 == 34 && fila3 == 34 && fila4 == 34 &&
            diagonal1 == 34 && diagonal2 == 34)
            {return 1;}

        // Si alguna de las filas suma mas de 34, se devuelve 2, es decir, perdiste
        if (columna1 > 34 || columna2 > 34 || columna3 > 34 || columna4 > 34 ||
            fila1 > 34 || fila2 > 34 || fila3 > 34 || fila4 > 34 ||
            diagonal1 > 34 || diagonal2 > 34)
            {return 2;}

        // Si no haz ganado pero no haz perdido, se devuelve 0, es decir, continua el juego
        return 0;
    }

    /** Este metodo es como el "main" de la clase. Uno llama a este metodo para jugar el juego y se combinan todos los metodo anteriores para hacerlo funcionar
     * @return int puntuacion
     */
    public int jugar() {
        int ganar; // int para ver si se ha terminado o no el juego

        genInicio(); // Primero se llama a genInicio para preparar el juego

        // Dentro de un bucle:
        do {
            dibujarTablero(); // Se dibuja el tablero

            // Se intenta colocar la ficha. Esta rodeada de un try-catch y un do-while asegurarse que SI coloques una ficha antes de continuar
            do {try {
                colocarFicha();
                break;
            } catch (Exception e) {
                System.out.println("\nElija un número entero (no letras y sin decimales)\n");
            }} while(true);

            ganar = ganar(); // Se verifica la condicion de ganar
            if(ganar != 0) {break;} // Si ganar != 0, es decir, el juego termino, entonces se sale del bucle
        } while (true);

         // Se dibuja el tablero una ultima vez y luego el metodo regresa la puntuacion obtenida
        dibujarTablero();
        if (ganar == 1) {System.out.println("\nGanaste :D!");return 10;} else {System.out.println("\nPerdiste :c!");return 0;}
    }

}
