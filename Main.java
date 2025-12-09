/**
 * Clase Main. Es un menu y tambien maneja el sistema de puntos, creditos y orden de completamiento de juegos
 * 
 * @author Iago HB
 */

import java.util.Scanner;
import juegos.*;
public class Main {

    /** 
     * Metodo main. Probablemente hubiera sido mejor segmentar todo en varias fuciones pero ya lo hice asi y no quiero tocar mucho el codigo porque ya funciona
     * Tratare de explicarlo con comentarios xd
     */
    public static void main(String[] args) {
        // Variables varias
        Scanner in = new Scanner(System.in); // Scanner para leer input
        Rewr rewr = new Rewr(); // ReadWrite para guardar y cargar puntaje
        int seleccion, puntos = 0, creditos; // Seleccion para elegir cosas en los menus, puntos para llevar la cuenta de puntos actuales antes de guardarlos, creditos para llevar cuenta de creditos

        // Leer el dia actual en el que se abre el juego
        String[] diaTemp = rewr.leer("datosguardados/dia.txt"); // Variable que sirve para apoyar a la siguiente
        int dia = Integer.parseInt(diaTemp[0]); // Dia actual

        // Strings para facilitar acceder a archivos importantes
        final String orden1 = "datosguardados/orden1.txt";
        final String orden2 = "datosguardados/orden2.txt";
        final String orden3 = "datosguardados/orden3.txt";
        final String orden4 = "datosguardados/orden4.txt";
        final String puntaje = "datosguardados/puntuacion.txt";

        // Se ingresa el nombre del jugador primero
        System.out.println("Ingrese su nombre:");
        String nombre = in.nextLine();

        // Si el jugador no existe, se le asignan 100 creditos
        if (rewr.buscar(puntaje,"\""+nombre+"\"") == -1) {creditos = 100;}
        else { // Si el jugador existe, se cargan los creditos que tenia en su cuenta
            String[] texto = rewr.leer(puntaje);
            creditos = Integer.parseInt(texto[rewr.buscar(puntaje,"\""+nombre+"\"")].substring(0, texto[rewr.buscar(puntaje,"\""+nombre+"\"")].indexOf("\""+nombre+"\"")));
        }

        // Se anuncian los creditos disponibles y se pregunta que quiere hacer
        System.out.println("Usted tiene " + creditos + " créditos");
        System.out.println("\n\nHola " + nombre + "\n\n¿Qué le gustaría hacer?");

        do { // Dentro de un bucle (para poder repetir el menu hasta que se quiera salir)
            
            // Se realiza la seleccion de que se quiere hacer
            do {
                seleccion = 0;
                try {
                    System.out.println("\n\n1. Jugar\n2. Ver mejores 3 jugadores\n3. Ver puntos acumulados\n4. Salir");
                    seleccion = in.nextInt();
                    if (seleccion > 4) {throw new Exception();}
                    if (seleccion < 1) {throw new Exception();}
                    in.nextLine();
                    break;
                } catch (Exception e) {
                    seleccion = 0;
                    in.nextLine();
                    System.out.println("\nEscriba una opcion válida\n");
                }
            } while (true);
            
            // Si se elije 1 (jugar), entonces sigue lo siguiente:
            if (seleccion == 1) {
                switch (dia) { // Dependiendo del dia actual se muestran los juegos

                case 1:
                     // En el dia uno hay CuadroMagico y ConectaCuatro
                    CuadroMagico juego1 = new CuadroMagico();
                    ConectaCuatro juego2 = new ConectaCuatro();

                    // Anunciar el dia de hoy y los juegos disponibles
                    System.out.println("\nHoy es día 1");

                    do{
                        System.out.println("Juegos disponibles:\n1. Cuadro Mágico (15 créditos)\n2. Conecta Cuatro (15 créditos)\n3. Salir\n");

                        // Realizar la seleccion de juego
                        do {
                            seleccion = 0;
                            try {
                                System.out.println("¿Qué quiere jugar?");
                                seleccion = in.nextInt();
                                if (seleccion > 3) {throw new Exception();}
                                if (seleccion < 1) {throw new Exception();}
                                in.nextLine();
                                break;
                            } catch (Exception e) {
                                seleccion = 0;
                                in.nextLine();
                                System.out.println("\nEscriba una opción válida\n");
                            }
                        } while (true);

                        // Opciones para jugar un juego o salir
                        System.out.println("\n\n");
                        if (seleccion == 1) { // Secuencia de CuadroMagico
                            int puntoAct;
                            if (creditos >= 15) {puntoAct = juego1.jugar();}
                            else {puntoAct = 0; System.out.println("No tiene suficientes créditos para jugar");break;}
                            puntos += puntoAct;
                            creditos -= 15;

                            if (rewr.buscar(orden1,"\""+nombre+"\"") == -1 && puntoAct == 10) {rewr.escribirN(orden1, "\""+nombre+"\"");} // Registrar que completaste el juego

                            // Mostrar top3
                            System.out.println("\nPrimeros 3 jugadores en terminar:\n");
                            String[] top3 = rewr.primerasLineas(orden1, 3);
                            if (top3[0] != null) {System.out.println("1: " + top3[0]);}
                            if (top3[1] != null) {System.out.println("2: " + top3[1]);}
                            if (top3[2] != null) {System.out.println("3: " + top3[2]);}

                            // Mostrar tu lugar
                            if (rewr.buscar(orden1,"\""+nombre+"\"") != -1) {System.out.println("\nTú quedaste en el puesto " + (rewr.buscar(orden1, "\""+nombre+"\"") + 1) + "\n\n");}
                        }
                        if (seleccion == 2) { // Secuencia de ConectaCuatro
                            int puntoAct;
                            if (creditos >= 15) {puntoAct = juego2.jugar();}
                            else {puntoAct = 0; System.out.println("No tiene suficientes créditos para jugar");break;}
                            puntos += puntoAct;
                            creditos -= 15;

                            if (rewr.buscar(orden2,"\""+nombre+"\"") == -1 && puntoAct != 0 && puntoAct != 2) {rewr.escribirN(orden2, "\""+nombre+"\"");} // Registrar que completaste el juego
                            
                            //Mostrar top3
                            System.out.println("\nPrimeros 3 jugadores en terminar:\n");
                            String[] top3 = rewr.primerasLineas(orden2, 3);
                            if (top3[0] != null) {System.out.println("1: " + top3[0]);}
                            if (top3[1] != null) {System.out.println("2: " + top3[1]);}
                            if (top3[2] != null) {System.out.println("3: " + top3[2]);}

                            if (rewr.buscar(orden2,"\""+nombre+"\"") != -1) {System.out.println("\nTú quedaste en el puesto " + (rewr.buscar(orden2, "\""+nombre+"\"") + 1) + "\n\n");}
                        }
                        if (seleccion == 3) { // Secuencia de salir
                            // Leer puntaje, buscar el renglon donde estas registrado
                            String[] texto = rewr.leer(puntaje);
                            int jotaro = rewr.buscar(puntaje,"\""+nombre+"\"");

                            // Si no estas registrado, se registran tus creditos, nombre y puntos
                            if (jotaro == -1) {
                                rewr.escribirN(puntaje,creditos+"\""+nombre+"\""+puntos);
                            } else { // Si ya estas registrado, se actualizan tus creditos, nombre y puntos
                                puntos += Integer.parseInt(texto[jotaro].substring(texto[jotaro].indexOf("\""+nombre+"\"")+nombre.length()+2));
                                rewr.escribirO(puntaje,jotaro,creditos+"\""+nombre+"\""+puntos);
                            }

                            puntos = 0; // Como ya se depositaron los puntos, se vuelven 0
                            break; // Salir
                        }

                    } while (true);

                    break;

                case 2:

                    // En el dia 2 hay Salvado y AdivinaYSobrevive
                    Salvado juego3 = new Salvado();
                    AdivinaYSobrevive juego4 = new AdivinaYSobrevive();

                    // Anunciar el dia de hoy y los juegos disponibles
                    System.out.println("\nHoy es día 2");

                    do{
                        
                        System.out.println("Juegos disponibles:\n1. Salvado (15 créditos, gratis si ganas)\n2. Adivina y Sobrevive (15 créditos)\n3. Salir\n");

                        // Realizar la seleccion de juego
                        do {
                            try {
                                System.out.println("¿Qué quiere jugar?");
                                seleccion = in.nextInt();
                                if (seleccion > 3) {throw new Exception();}
                                if (seleccion < 1) {throw new Exception();}
                                in.nextLine();
                                break;
                            } catch (Exception e) {
                                in.nextLine();
                                seleccion = 0;
                                System.out.println("\nEscriba una opción válida\n");
                            }
                        } while (true);

                        // Opciones para jugar un juego o salir
                        System.out.println("\n\n");
                        if (seleccion == 1) { // Secuencia de Salvado
                            int puntoAct;
                            if (creditos >= 15) {puntoAct = juego3.jugar();}
                            else {puntoAct = 0; System.out.println("No tiene suficientes créditos para jugar");break;}
                            puntos += puntoAct;
                            if (puntoAct != 12) {creditos -= 15;}
                            
                            if (rewr.buscar(orden3,"\""+nombre+"\"") == -1) {rewr.escribirN(orden3, "\""+nombre+"\"");} // Registrar que completaste el juego

                            //Mostrar top3
                            System.out.println("\nPrimeros 3 jugadores en terminar:\n");
                            String[] top3 = rewr.primerasLineas(orden3, 3);
                            if (top3[0] != null) {System.out.println("1: " + top3[0]);}
                            if (top3[1] != null) {System.out.println("2: " + top3[1]);}
                            if (top3[2] != null) {System.out.println("3: " + top3[2]);}

                            if (rewr.buscar(orden3,"\""+nombre+"\"") != -1) {System.out.println("\nTú quedaste en el puesto " + (rewr.buscar(orden3, "\""+nombre+"\"") + 1) + "\n\n");}
                        }
                        if (seleccion == 2) { // Secuencia de AdivinaYSobrevive
                            int puntoAct;
                            if (creditos >= 15) {puntoAct = juego4.jugar();}
                            else {puntoAct = 0; System.out.println("No tiene suficientes créditos para jugar");break;}
                            puntos += puntoAct;
                            creditos -= 15;
                            
                            if (rewr.buscar(orden4,"\""+nombre+"\"") == -1 && puntoAct != 1 && puntoAct != 0) {rewr.escribirN(orden4, "\""+nombre+"\"");} // Registrar que completaste el juego

                            //Mostrar top3
                            System.out.println("\nPrimeros 3 jugadores en terminar:\n");
                            String[] top3 = rewr.primerasLineas(orden4, 3);
                            if (top3[0] != null) {System.out.println("1: " + top3[0]);}
                            if (top3[1] != null) {System.out.println("2: " + top3[1]);}
                            if (top3[2] != null) {System.out.println("3: " + top3[2]);}

                            if (rewr.buscar(orden4,"\""+nombre+"\"") != -1) {System.out.println("\nTú quedaste en el puesto " + (rewr.buscar(orden4, "\""+nombre+"\"") + 1) + "\n\n");}
                        }
                        if (seleccion == 3) { // Secuencia de salida. Es lo mismo que la del dia 1
                            String[] texto = rewr.leer(puntaje);
                            int jotaro = rewr.buscar(puntaje,"\""+nombre+"\"");

                            if (jotaro == -1) {
                                rewr.escribirN(puntaje,creditos+"\""+nombre+"\""+puntos);
                            } else {
                                puntos += Integer.parseInt(texto[jotaro].substring(texto[jotaro].indexOf("\""+nombre+"\"")+nombre.length()+2));
                                rewr.escribirO(puntaje,jotaro,creditos+"\""+nombre+"\""+puntos);
                            }
                            puntos = 0;
                            break;
                        }
                        
                        System.out.println("\n\n");

                    } while (true);

                    break;
                }
            }
            // Ver top 3 jugadores y puntuacion
            if (seleccion == 2) {
                // Se lee la tabla de puntuaciones y se asignan, en 2 arreglos, los puntos y los nombres
                System.out.println("");
                String[] texto = rewr.leer(puntaje);
                int[] puntajes = new int[texto.length];
                String[] nombres = new String[texto.length];

                for (int i = 0; i < texto.length; i++) {
                    int primeraComilla = texto[i].indexOf("\"");
                    puntajes[i] = Integer.parseInt(texto[i].substring(texto[i].indexOf("\"", primeraComilla+1)+1));
                    nombres[i] = texto[i].substring(primeraComilla+1, texto[i].indexOf("\"", primeraComilla+1));
                }

                //bubblesort para ordenar nombres y puntos por puntuacion
                for (int i = 0; i < (puntajes.length - 1); i++) {
                    boolean swapped = false;
                    for (int j = 0; j < (puntajes.length - i - 1); j++) {
                        if (puntajes[j] < puntajes[j + 1]) {
                            int temp = puntajes[j];
                            String tempS = nombres[j];
                            puntajes[j] = puntajes[j + 1];
                            nombres[j] = nombres[j + 1];
                            puntajes[j + 1] = temp;
                            nombres[j + 1] = tempS;
                            swapped = true;
                        }
                    }
                    if (swapped == false)
                    break;
                }

                // Mostrar el top3 (o top2 o top1 si no hay suficientes jugadores)
                int cont = 0;
                for(int i = 0; i < puntajes.length; i++) {
                    if (nombres[i] != null) {
                        System.out.println("Top " + (i+1) + ": " + nombres[i] + " con " + puntajes[i] + " puntos");
                        cont++;
                    }
                    if (cont >= 3) {break;}
                }
                System.out.println("");

                // Mostrar top personal (por si no apareces en el top3 y quieres saber donde estas)
                System.out.print("Tu posición ");

                int topPersonal = -1;
                for (int i = 0; i < nombres.length; i++) {
                    if (nombres[i].equals(nombre)) {
                        topPersonal = i;
                        break;
                    }
                }

                if (topPersonal == -1) {System.out.print("no esta registrada\n");}
                else {System.out.print("es top " + (topPersonal + 1) + "\n");}

                System.out.println("");

            }
            // Ver puntuacion personal
            if (seleccion == 3) {

                // Se lee el texto, se busca el renglon donde apareces tu y se muestran tus puntos y creditos. Si no estas registrado se muestran los puntos y los creditos actuales
                String[] texto = rewr.leer(puntaje);
                int jotaro = rewr.buscar(puntaje,"\""+nombre+"\"");
                if (jotaro == -1) {
                    System.out.println("\nPuntaje personal: " + puntos);
                    System.out.println("Créditos Restantes: " + creditos);
                } else {
                    System.out.println("\nPuntaje personal: " + (texto[jotaro].substring(texto[jotaro].indexOf("\""+nombre+"\"")+nombre.length()+2)));
                    System.out.println("Créditos Restantes: " + creditos);
                }
            }
            // Salir
            if (seleccion == 4) {
                break;
            }
        } while (true);

        // Cuando se sale del programa se cambia de dia para la siguiente vez que lo abras
        if (dia == 1) {rewr.escribirO("datosguardados/dia.txt",0,"2");}
        else {rewr.escribirO("datosguardados/dia.txt",0,"1");}
        
        System.out.println("\n\nAdiós :3");
    }
}
