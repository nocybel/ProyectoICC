import juegos.*;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //borrar o cambiar luego:
        int dia = 1; //tiene que cambiar cada que abras el programa
        int creditos = 100; //creditos totales para ambos dias y para un solo jugador

        // Cosas que si sirven
        Scanner in = new Scanner(System.in);
        int seleccion, puntos = 0;

        // Registrar jugador
        System.out.println("Ingrese su nombre:");
        String nombre = in.nextLine();

        System.out.println("Hola " + nombre + "\n¿Qué le gustaría hacer?");

        do {
        // Realizar la seleccion de menu
        seleccion = 0;
        do {
            try {
                System.out.println("1. Jugar\n2. Ver mejores 3 jugadores\n3. Ver puntos acumulados\n4. Salir");
                seleccion = in.nextInt();
                if (seleccion > 4) {throw new Exception();}
                if (seleccion < 1) {throw new Exception();}
                break;
            } catch (Exception e) {
                seleccion = 0;
                System.out.println("\nEscriba una opcion válida\n");
            }
        } while (true);
        
        


        // Opcion de jugar
        if (seleccion == 1) {
        switch (dia) {

        case 1:

            // Se crean objetos de juego1 y juego2
            CuadroMagico juego1 = new CuadroMagico();
            ConectaCuatro juego2 = new ConectaCuatro();

            // Anunciar el dia de hoy y los juegos disponibles
            System.out.println("Hoy es día 1\n\n");

            do{
            
            System.out.println("Juegos disponibles:\n1. Cuadro Magico (x creditos)\n2. Conecta Cuatro (x creditos)\n3. Salir\n");

            // Realizar la seleccion de juego
            seleccion = 0;
            do {
                try {
                    System.out.println("¿Qué quiere jugar?");
                    seleccion = in.nextInt();
                    if (seleccion > 3) {throw new Exception();}
                    if (seleccion < 1) {throw new Exception();}
                    break;
                } catch (Exception e) {
                    seleccion = 0;
                    System.out.println("\nEscriba una opcion válida\n");
                }
            } while (true);

            // Opciones para jugar un juego o salir
            System.out.println("\n\n");
            if (seleccion == 1) {
                puntos += juego1.jugar();
                creditos -= 15;
                // todo: hacer que se muestre los primeros 3 jugadores en terminar el juego, y guardar todos los jugadores que lo han completado jamas, y mostrar el lugar en el que lo terminaste tu ???
            }
            if (seleccion == 2) {
                puntos += juego2.jugar();
                creditos -= 15;
                // todo: hacer que se muestre los primeros 3 jugadores en terminar el juego, y guardar todos los jugadores que lo han completado jamas, y mostrar el lugar en el que lo terminaste tu ???
            }
            if (seleccion == 3) {break;}
            System.out.println("\n\n");

            } while (true);

            break;

        case 2:

            // Se crean objetos de juego1 y juego2
            Salvado juego3 = new Salvado();
            AdivinaYSobrevive juego4 = new AdivinaYSobrevive();

            // Anunciar el dia de hoy y los juegos disponibles
            System.out.println("Hoy es día 1\n\n");

            do{
            
            System.out.println("Juegos disponibles:\n1. Salvado (x creditos)\n2. Adivina y Sobrevive (x creditos)\n3. Salir\n");

            // Realizar la seleccion de juego
            do {
                try {
                    System.out.println("¿Qué quiere jugar?");
                    seleccion = in.nextInt();
                    if (seleccion > 3) {throw new Exception();}
                    if (seleccion < 1) {throw new Exception();}
                    break;                    } catch (Exception e) {
                    seleccion = 0;
                    System.out.println("\nEscriba una opcion válida\n");
                }
            } while (true);

            // Opciones para jugar un juego o salir
            System.out.println("\n\n");
            if (seleccion == 1) {
                int puntosTemp = juego3.jugar();
                puntos += puntosTemp;
                if (puntosTemp != 12) {creditos -= 15;}
                // todo: hacer que se muestre los primeros 3 jugadores en terminar el juego, y guardar todos los jugadores que lo han completado jamas, y mostrar el lugar en el que lo terminaste tu ???
            }
            if (seleccion == 2) {
                puntos += juego4.jugar();
                creditos -= 15;
                // todo: hacer que se muestre los primeros 3 jugadores en terminar el juego, y guardar todos los jugadores que lo han completado jamas, y mostrar el lugar en el que lo terminaste tu ???
            }
            if (seleccion == 3) {break;}
            System.out.println("\n\n");

            } while (true);

            break;
        }
        }
        if (seleccion == 2) {
        // Ver top 3 jugadores y puntuacion
        }
        if (seleccion == 3) {
        // Ver puntuacion personal
        }
        if (seleccion == 4) {break;}
        } while (true);

        //secuencia de salida:
    }
}
