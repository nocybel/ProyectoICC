/**
 * Cuadrado magico
 * 
 * todo:
 * - Randomizar tablero inicial
 * - Controles para poner valores
 */
public class DiaUno {
    int[][] tablero = {{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0}};

    public DiaUno() {
        //ns q poner aqui la verdad
    }

    private String ficha(int x) {
        if (x < 10) { return " " + x;}
        return x;
    }

    private void dibujarTablero() {
        System.out.println("\n╔════════╦════════╦════════╦════════╗");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("║   "+ficha(tablero[0][0])+"   ║   "+ficha(tablero[0][1])+"   ║   "+ficha(tablero[0][2])+"   ║   "+ficha(tablero[0][3])+"   ║");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("╠════════╬════════╬════════╬════════╣");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("║   "+ficha(tablero[1][0])+"   ║   "+ficha(tablero[1][1])+"   ║   "+ficha(tablero[1][2])+"   ║   "+ficha(tablero[1][3])+"   ║");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("╠════════╬════════╬════════╬════════╣");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("║   "+ficha(tablero[2][0])+"   ║   "+ficha(tablero[2][1])+"   ║   "+ficha(tablero[2][2])+"   ║   "+ficha(tablero[2][3])+"   ║");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("╠════════╬════════╬════════╬════════╣");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("║   "+ficha(tablero[3][0])+"   ║   "+ficha(tablero[3][1])+"   ║   "+ficha(tablero[3][2])+"   ║   "+ficha(tablero[3][3])+"   ║");
        System.out.println("║        ║        ║        ║        ║");
        System.out.println("╚════════╩════════╩════════╩════════╝\n");
    }

    private int generarNumero(int min, int max) {
        int numero = Math.random() * (max - min + 1) + min;
        return numero;
    }

    private int validarNumero(int numero, int min, int max) {
        if (numero <= max && numero >= min) {
            return numero;
        } else if (numero > max) {
            return max;
        } else {
            return min;
        }
    }

    public int jugar() {
        
        do {
            dibujarTablero();

        } while (true);

    }
}
