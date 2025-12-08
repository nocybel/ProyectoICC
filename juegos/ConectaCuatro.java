/**
 * Clase ConectaCuatro. Es el juego de conecta 4, donde tiras fichas y tienes que conectar 4
 * 
 * @author Iago HB
 */
package juegos;
import java.util.Scanner;
public class ConectaCuatro {

    private int[][] tablero = new int[6][7]; // Tablero 7x6 (7 de ancho y 6 de alto, pero puse las coordenadas chuecas y no las quiero cambiar)
    private boolean turno; // Boolean para manejar turnos

    // Colores para imprimir bonito en la terminal
    private final String RESET = "\u001B[0m";
    private final String CYN = "\u001B[36m";
    private final String RED = "\u001B[31m";
    private final String BLU = "\u001B[34m";

    /** Este metodo genera un numero al azar dentro de un intervalo cerrado */
    private int generarNumero(int min, int max) {
        int num = (int) (Math.random() * (max - min + 1) + min);

        // Lei en un lado que este metodo no es 100% preciso, pues a veces puede generar un numero fuera del rango. Estos ifs corrigen eso creo
        if (num >= max) {num = max;}
        if (num <= min) {num = min;}

        return num;
    }

    /** Este es un metodo auxiliar de dibujarTablero(). Recibe una casilla del tablero y una orientacion y devuelve un string mas bonito para imprimir en el tablero */
    private String ficha(int x, int top) {
        String texto;

        // Primer if es para orientacion. Los ifs de dentro son para el color
        if (top == 1) {
            if(x == 0) {
                texto = "   ";
            } else if (x == 1) {
                texto = RED+"▄█▄"+RESET;
            } else {
                texto = BLU+"▄█▄"+RESET;
            }
        } else {
            if(x == 0) {
                texto = "   ";
            } else if (x == 1) {
                texto = RED+"▀█▀"+RESET;
            } else {
                texto = BLU+"▀█▀"+RESET;
            }
        }

        return texto;
    }

    /** Dibuja el tablero con fichas rojas y azules */
    private void dibujarTablero() {
        System.out.println("╔══"+CYN+"1"+RESET+"══╦══"+CYN+"2"+RESET+"══╦══"+CYN+"3"+RESET+"══╦══"+CYN+"4"+RESET+"══╦══"+CYN+"5"+RESET+"══╦══"+CYN+"6"+RESET+"══╦══"+CYN+"7"+RESET+"══╗");
        System.out.println("║ "+ficha(tablero[0][0],1)+" ║ "+ficha(tablero[0][1],1)+" ║ "+ficha(tablero[0][2],1)+" ║ "+ficha(tablero[0][3],1)+" ║ "+ficha(tablero[0][4],1)+" ║ "+ficha(tablero[0][5],1)+" ║ "+ficha(tablero[0][6],1)+" ║");
        System.out.println("║ "+ficha(tablero[0][0],0)+" ║ "+ficha(tablero[0][1],0)+" ║ "+ficha(tablero[0][2],0)+" ║ "+ficha(tablero[0][3],0)+" ║ "+ficha(tablero[0][4],0)+" ║ "+ficha(tablero[0][5],0)+" ║ "+ficha(tablero[0][6],0)+" ║");
        System.out.println("╠═════╬═════╬═════╬═════╬═════╬═════╬═════╣");
        System.out.println("║ "+ficha(tablero[1][0],1)+" ║ "+ficha(tablero[1][1],1)+" ║ "+ficha(tablero[1][2],1)+" ║ "+ficha(tablero[1][3],1)+" ║ "+ficha(tablero[1][4],1)+" ║ "+ficha(tablero[1][5],1)+" ║ "+ficha(tablero[1][6],1)+" ║");
        System.out.println("║ "+ficha(tablero[1][0],0)+" ║ "+ficha(tablero[1][1],0)+" ║ "+ficha(tablero[1][2],0)+" ║ "+ficha(tablero[1][3],0)+" ║ "+ficha(tablero[1][4],0)+" ║ "+ficha(tablero[1][5],0)+" ║ "+ficha(tablero[1][6],0)+" ║");
        System.out.println("╠═════╬═════╬═════╬═════╬═════╬═════╬═════╣");
        System.out.println("║ "+ficha(tablero[2][0],1)+" ║ "+ficha(tablero[2][1],1)+" ║ "+ficha(tablero[2][2],1)+" ║ "+ficha(tablero[2][3],1)+" ║ "+ficha(tablero[2][4],1)+" ║ "+ficha(tablero[2][5],1)+" ║ "+ficha(tablero[2][6],1)+" ║");
        System.out.println("║ "+ficha(tablero[2][0],0)+" ║ "+ficha(tablero[2][1],0)+" ║ "+ficha(tablero[2][2],0)+" ║ "+ficha(tablero[2][3],0)+" ║ "+ficha(tablero[2][4],0)+" ║ "+ficha(tablero[2][5],0)+" ║ "+ficha(tablero[2][6],0)+" ║");
        System.out.println("╠═════╬═════╬═════╬═════╬═════╬═════╬═════╣");
        System.out.println("║ "+ficha(tablero[3][0],1)+" ║ "+ficha(tablero[3][1],1)+" ║ "+ficha(tablero[3][2],1)+" ║ "+ficha(tablero[3][3],1)+" ║ "+ficha(tablero[3][4],1)+" ║ "+ficha(tablero[3][5],1)+" ║ "+ficha(tablero[3][6],1)+" ║");
        System.out.println("║ "+ficha(tablero[3][0],0)+" ║ "+ficha(tablero[3][1],0)+" ║ "+ficha(tablero[3][2],0)+" ║ "+ficha(tablero[3][3],0)+" ║ "+ficha(tablero[3][4],0)+" ║ "+ficha(tablero[3][5],0)+" ║ "+ficha(tablero[3][6],0)+" ║");
        System.out.println("╠═════╬═════╬═════╬═════╬═════╬═════╬═════╣");
        System.out.println("║ "+ficha(tablero[4][0],1)+" ║ "+ficha(tablero[4][1],1)+" ║ "+ficha(tablero[4][2],1)+" ║ "+ficha(tablero[4][3],1)+" ║ "+ficha(tablero[4][4],1)+" ║ "+ficha(tablero[4][5],1)+" ║ "+ficha(tablero[4][6],1)+" ║");
        System.out.println("║ "+ficha(tablero[4][0],0)+" ║ "+ficha(tablero[4][1],0)+" ║ "+ficha(tablero[4][2],0)+" ║ "+ficha(tablero[4][3],0)+" ║ "+ficha(tablero[4][4],0)+" ║ "+ficha(tablero[4][5],0)+" ║ "+ficha(tablero[4][6],0)+" ║");
        System.out.println("╠═════╬═════╬═════╬═════╬═════╬═════╬═════╣");
        System.out.println("║ "+ficha(tablero[5][0],1)+" ║ "+ficha(tablero[5][1],1)+" ║ "+ficha(tablero[5][2],1)+" ║ "+ficha(tablero[5][3],1)+" ║ "+ficha(tablero[5][4],1)+" ║ "+ficha(tablero[5][5],1)+" ║ "+ficha(tablero[5][6],1)+" ║");
        System.out.println("║ "+ficha(tablero[5][0],0)+" ║ "+ficha(tablero[5][1],0)+" ║ "+ficha(tablero[5][2],0)+" ║ "+ficha(tablero[5][3],0)+" ║ "+ficha(tablero[5][4],0)+" ║ "+ficha(tablero[5][5],0)+" ║ "+ficha(tablero[5][6],0)+" ║");
        System.out.println("╚═════╩═════╩═════╩═════╩═════╩═════╩═════╝");
    }

    /** "Prepara" el tablero (solo limpia el tablero) */
    private void genInicio() {
        turno = true;
        for (int i = 0; i < 7; i++) {for (int j = 0; j < 6; j++) {
            tablero[j][i] = 0;
        }}
    }

    /** Metodo para colocar la ficha de un jugador. Si arcade es true entonces la ficha se coloca al azar */
    private void colocarFicha(int jugador, boolean arcade) {
        Scanner in = new Scanner(System.in);
        int x, y = 0;
        boolean valido;

        do {
            valido = true; // Valido es true para volverlo false en caso de que no pase los checks

            // Si arcade es true se genera un numero al azar, si es false entonces el usuario elige x. Además, segun el turno, el color del texto es rojo o azul
            if(arcade) {
                x = generarNumero(0,6);
            } else {
                if(turno) {System.out.println(RED+"Elija una columa"+RESET);} else {System.out.println(BLU+"Elija una columa"+RESET);}
                x = in.nextInt();
                x--;
            }

            // Si x se sale del rango entonces se corrige
            if (x > 6) {x = 6;}
            if (x < 0) {x = 0;}

            // Si la columna esta llena, entonces valido es false
            if (tablero[0][x] != 0) {valido = false;}

            // En caso de que la columna no este llena, se hace un loop para "dejar caer la ficha" hasta que llega al fondo o se tope con otra ficha
            if (valido) {
                do {
                    if(y != 6 && tablero[y][x] == 0) {
                        y++;
                    } else {
                        break;
                    }
                } while (true);

                tablero[y-1][x] = jugador;
            }
        } while (!valido);
    }

    /** Metodo para ver si alguien ha ganado o si se deberia continuar el juego */
    private int ganar() {
        // Jugador 1:

        //checar vertical
        for(int i = 0; i < 7; i++) {for(int j = 0; j < 3; j++) {
            if (tablero[j][i] == 1 && tablero[j][i] == tablero[j+1][i] && tablero[j+1][i] == tablero[j+2][i] && tablero[j+2][i] == tablero[j+3][i]) {return 2;}
        }}
        //checar horizontal
        for(int i = 0; i < 6; i++) {for(int j = 0; j < 4; j++) {
            if (tablero[i][j] == 1 && tablero[i][j] == tablero[i][j+1] && tablero[i][j+1] == tablero[i][j+2] && tablero[i][j+2] == tablero[i][j+3]) {return 2;}
        }}
        //checar diagonal \
        for(int i = 0; i < 4; i++){ for(int j = 0; j < 3; j++) {
            if (tablero[j][i] == 1 && tablero[j][i] == tablero[j+1][i+1] && tablero[j+1][i+1] == tablero[j+2][i+2] && tablero[j+2][i+2] == tablero[j+3][i+3]) {return 2;}
        }}
        //checar diagonal /
        for(int i = 0; i < 4; i++){ for(int j = 5; j > 2; j--) {
            if (tablero[j][i] == 1 && tablero[j][i] == tablero[j-1][i+1] && tablero[j-1][i+1] == tablero[j-2][i+2] && tablero[j-2][i+2] == tablero[j-3][i+3]) {return 2;}
        }}


        // Jugador 2:

        //checar vertical
        for(int i = 0; i < 7; i++) {for(int j = 0; j < 3; j++) {
            if (tablero[j][i] == 2 && tablero[j][i] == tablero[j+1][i] && tablero[j+1][i] == tablero[j+2][i] && tablero[j+2][i] == tablero[j+3][i]) {return 3;}
        }}
        //checar horizontal
        for(int i = 0; i < 6; i++) {for(int j = 0; j < 4; j++) {
            if (tablero[i][j] == 2 && tablero[i][j] == tablero[i][j+1] && tablero[i][j+1] == tablero[i][j+2] && tablero[i][j+2] == tablero[i][j+3]) {return 3;}
        }}
        //checar diagonal \
        for(int i = 0; i < 4; i++){ for(int j = 0; j < 3; j++) {
            if (tablero[j][i] == 2 && tablero[j][i] == tablero[j+1][i+1] && tablero[j+1][i+1] == tablero[j+2][i+2] && tablero[j+2][i+2] == tablero[j+3][i+3]) {return 3;}
        }}
        //checar diagonal /
        for(int i = 0; i < 4; i++){ for(int j = 5; j > 2; j--) {
            if (tablero[j][i] == 2 && tablero[j][i] == tablero[j-1][i+1] && tablero[j-1][i+1] == tablero[j-2][i+2] && tablero[j-2][i+2] == tablero[j-3][i+3]) {return 3;}
        }}

        // Si el tablero esta lleno entonces es un empate
        if (tablero[0][0] != 0 && tablero[0][1] != 0 && tablero[0][2] != 0 && tablero[0][3] != 0 && tablero[0][4] != 0 && tablero[0][5] != 0 && tablero[0][6] != 0) {return 1;}

        // Si el juego no ha terminado entonces devuelve 0 para continuar
        return 0;
    }

    /** Combina los metodos anteriores para jugar
     * @return int puntuacion
     */
    public int jugar() {
        Scanner in = new Scanner(System.in);
        int ganar;
        boolean arcade;

        genInicio(); // Se limpia el tablero

        // Se elije el modo de juego. Si es una opcion incorrecta entonces se elije arcade por default (vs cpu)
        System.out.println("Elija un modo de juego:\n"+CYN+"1. Arcade\n2. Versus"+RESET);
        try {
            int seleccion = in.nextInt();
            if (seleccion == 1) {arcade = true;}
            else if (seleccion == 2) {arcade = false;}
            else {throw new Exception();}
        } catch (Exception e) {
            System.out.println("Opcion invalida, por defecto se eligirá modo "+CYN+"arcade");
            arcade = true;
        }

        if (arcade) {System.out.println(CYN+"\nMODO ARCADE\n"+RESET);} else {System.out.println(CYN+"\nMODO VERSUS\n"+RESET);} // Se anuncia el modo de juego seleccionado

        // En un bucle:
        do {
            dibujarTablero(); // Se dibuja el tablero

            // Se trata de colocar una ficha. Segun el turno, juega el jugador 1 o 2, y segun el modo de juego, el jugador 2 tiene que colocar sus fichas manualmente o son al azar
            try{
            if (turno) {
                colocarFicha(1, false);
            } else {
                colocarFicha(2, arcade);
            }
            } catch (Exception e) {
                System.out.println("Opción invalida, intente de nuevo");
                turno = !turno; // Se cambia de turno si no se pudo colocar la ficha para que se vuelva a cambiar y en total no se cambie de turno
            }

            
            turno = !turno; // Se cambia de turno
            ganar = ganar(); // Se ve si alguien ha ganado
            if (ganar != 0) {break;} // Si alguien ha ganado entonces se sale del bucle
        } while (true);

        dibujarTablero(); // Se dibuja el tablero una ultima vez

        // Depende de quien gane, se devuelven x cantidad de puntos
        if (ganar == 1) {return 5;} // Empate
        else if (ganar == 2) {return 10;} // Ganar
        else {return 2;} // Perder
    }

}
