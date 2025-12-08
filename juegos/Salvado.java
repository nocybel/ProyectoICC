/**
 * Clase Salvado. Juego aburrido es basicamente elije un numero entre 1 y 100 y a ver si ganas
 * 
 * @author Iago HB
 */
package juegos;
import java.util.Scanner;
public class Salvado {

    boolean[] gente = new boolean[100]; // las 100 personas

    // colores :3
    private final String RESET = "\u001B[0m";
    private final String GRN = "\u001B[32m";
    private final String RED = "\u001B[31m";

    /** Este metodo genera un numero al azar dentro de un intervalo cerrado */
    private int generarNumero(int min, int max) {
        int num = (int) (Math.random() * (max - min + 1) + min);

        // Lei en un lado que este metodo no es 100% preciso, pues a veces puede generar un numero fuera del rango. Estos ifs corrigen eso creo
        if (num >= max) {num = max;}
        if (num <= min) {num = min;}

        return num;
    }

    /** Metodo para elegir la persona que crees sobrevivira */
    private int seleccion() {
        Scanner in = new Scanner(System.in);
        int seleccion;
        boolean valido;

        do {
            valido = true;
            System.out.println("Elija la silla que se salvará:");
            seleccion = in.nextInt();

            seleccion--;
            if (seleccion > 99) {valido = false;}
            if (seleccion < 0) {valido = false;}

            if (!valido) {System.out.println("Elija un numero adecuado");}
            in.nextLine();
        } while (!valido);

        return seleccion;
    }

    /** Metodo que combina los anteriores para jugar.
     * @return int puntuacion
     */
    public int jugar() {
        
        int seleccion, recorrido, posicion = 0, cont = 0;

        // Se sientan a todas las personas
        for(int i = 0; i < 100; i++) {gente[i] = true;}

        System.out.println("Imagina un circulo de 100 personas en una silla, numerados del 1 al 100");

        // Se hace la seleccion
        do { 
            try {
                seleccion = seleccion();
                break;
            } catch (Exception e) {
                System.out.println("Intente de nuevo");
            }
        } while (true);
        
        // En bucle se genera un numero, se recorre ese numero de personas sentadas y luego se elimina en la que caiga
        do {
            recorrido = generarNumero(1, 100);

            // Esto hace el recorrido, si llega a 99 se regresa a 0, de lo contrario se avanza uno
            do {
                if (posicion >= 99) {
                    posicion = 0;
                } else {
                    posicion++;
                }
                if(gente[posicion] == true) {recorrido--;}
            } while (recorrido != 0);

            // Se mata a la persona encontrada y se imprime una tira representante de las personas que siguen vivas
            gente[posicion] = false;
            if (cont < 99) {
                System.out.println("Persona " + (1 + posicion) + " tuvo una muerte tragica :c");

                for(int i = 0; i < 100; i++) {
                    if (gente[i]) {
                        System.out.print(GRN+"█"+RESET);
                    } else {
                        System.out.print(RED+"█"+RESET);
                    }
                }
                System.out.print("\n\n");
            }
            
            cont++;
        } while (cont < 100);

        // Se anuncia el sobreviviente, y si ganas el metodo regresa 12 puntos, si pierdes regresa 2
        System.out.println("\n\nEl sobreviviente fue la persona " + (1 + posicion));
        if (seleccion == posicion) {
            System.out.println("\nGanaste :D");
            return 12;
        } else {
            System.out.println("\nPerdiste :C");
            return 2;
        }
    }

}