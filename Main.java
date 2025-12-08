/**
 * mostrar top del jugador actual (seleccion 2)
 * sistema de dias
 */

import juegos.*;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //borrar o cambiar luego:
        int dia = 1; //tiene que cambiar cada que abras el programa
        //int creditos = 100; //creditos totales para ambos dias y para un solo jugador

        // Cosas que si sirven
        Scanner in = new Scanner(System.in);
        Rewr rewr = new Rewr();
        int seleccion, puntos = 0;

        final String orden1 = "datosguardados/orden1.txt";
        final String orden2 = "datosguardados/orden2.txt";
        final String orden3 = "datosguardados/orden3.txt";
        final String orden4 = "datosguardados/orden4.txt";
        final String puntaje = "datosguardados/puntuacion.txt";

        // Registrar jugador
        System.out.println("Ingrese su nombre:");
        String nombre = in.nextLine();

        int creditos;

        if (rewr.buscar(puntaje,"\""+nombre+"\"") == -1) {creditos = 100;}
        else {
            String[] texto = rewr.leer(puntaje);
            creditos = Integer.parseInt(texto[rewr.buscar(puntaje,"\""+nombre+"\"")].substring(0, texto[rewr.buscar(puntaje,"\""+nombre+"\"")].indexOf("\""+nombre+"\"")));
        }

        System.out.println("Usted tiene " + creditos + " creditos");

        System.out.println("\n\nHola " + nombre + "\n\n¿Qué le gustaría hacer?");

        do {
            // Realizar la seleccion de menu
            
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
            
            // Opcion de jugar
            if (seleccion == 1) {
                switch (dia) {

                case 1:
                    // Se crean objetos de juego1 y juego2
                    CuadroMagico juego1 = new CuadroMagico();
                    ConectaCuatro juego2 = new ConectaCuatro();

                    // Anunciar el dia de hoy y los juegos disponibles
                    System.out.println("\nHoy es día 1");

                    do{
                        System.out.println("Juegos disponibles:\n1. Cuadro Magico (15 creditos)\n2. Conecta Cuatro (15 creditos)\n3. Salir\n");

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
                                System.out.println("\nEscriba una opcion válida\n");
                            }
                        } while (true);

                        // Opciones para jugar un juego o salir
                        System.out.println("\n\n");
                        if (seleccion == 1) {
                            int puntoAct = juego1.jugar();
                            puntos += puntoAct;
                            creditos -= 15;

                            if (rewr.buscar(orden1,"\""+nombre+"\"") == -1) {rewr.escribirN(orden1, "\""+nombre+"\"");} // Registrar que completaste el juego

                            // Mostrar top3
                            System.out.println("\nPrimeros 3 jugadores en terminar:\n");
                            String[] top3 = rewr.primerasLineas(orden1, 3);
                            if (top3[0] != null) {System.out.println("1: " + top3[0]);}
                            if (top3[1] != null) {System.out.println("2: " + top3[1]);}
                            if (top3[2] != null) {System.out.println("3: " + top3[2]);}

                            // Mostrar tu lugar
                            if (rewr.buscar(orden1,"\""+nombre+"\"") != -1) {System.out.println("\nTu quedaste en el puesto " + (rewr.buscar(orden1, "\""+nombre+"\"") + 1) + "\n\n");}
                        }
                        if (seleccion == 2) {
                            int puntoAct = juego2.jugar();
                            puntos += puntoAct;
                            creditos -= 15;

                            if (rewr.buscar(orden2,"\""+nombre+"\"") == -1) {rewr.escribirN(orden2, "\""+nombre+"\"");} // Registrar que completaste el juego
                            
                            //Mostrar top3
                            System.out.println("\nPrimeros 3 jugadores en terminar:\n");
                            String[] top3 = rewr.primerasLineas(orden2, 3);
                            if (top3[0] != null) {System.out.println("1: " + top3[0]);}
                            if (top3[1] != null) {System.out.println("2: " + top3[1]);}
                            if (top3[2] != null) {System.out.println("3: " + top3[2]);}

                            if (rewr.buscar(orden2,"\""+nombre+"\"") != -1) {System.out.println("\nTu quedaste en el puesto " + (rewr.buscar(orden2, "\""+nombre+"\"") + 1) + "\n\n");}
                        }
                        if (seleccion == 3) {
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

                case 2:

                    // Se crean objetos de juego1 y juego2
                    Salvado juego3 = new Salvado();
                    AdivinaYSobrevive juego4 = new AdivinaYSobrevive();

                    // Anunciar el dia de hoy y los juegos disponibles
                    System.out.println("Hoy es día 1\n\n");

                    do{
                        
                        System.out.println("Juegos disponibles:\n1. Salvado (15 creditos, gratis si ganas)\n2. Adivina y Sobrevive (15 creditos)\n3. Salir\n");

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
                                System.out.println("\nEscriba una opcion válida\n");
                            }
                        } while (true);

                        // Opciones para jugar un juego o salir
                        System.out.println("\n\n");
                        if (seleccion == 1) {
                            int puntoAct = juego3.jugar();
                            puntos += puntoAct;
                            if (puntoAct != 12) {creditos -= 15;}
                            
                            if (rewr.buscar(orden3,"\""+nombre+"\"") == -1) {rewr.escribirN(orden3, "\""+nombre+"\"");} // Registrar que completaste el juego

                            //Mostrar top3
                            System.out.println("\nPrimeros 3 jugadores en terminar:\n");
                            String[] top3 = rewr.primerasLineas(orden3, 3);
                            if (top3[0] != null) {System.out.println("1: " + top3[0]);}
                            if (top3[1] != null) {System.out.println("2: " + top3[1]);}
                            if (top3[2] != null) {System.out.println("3: " + top3[2]);}

                            if (rewr.buscar(orden3,"\""+nombre+"\"") != -1) {System.out.println("\nTu quedaste en el puesto " + (rewr.buscar(orden3, "\""+nombre+"\"") + 1) + "\n\n");}
                        }
                        if (seleccion == 2) {
                            int puntoAct = juego4.jugar();
                            puntos += puntoAct;
                            creditos -= 15;
                            
                            if (rewr.buscar(orden4,"\""+nombre+"\"") == -1) {rewr.escribirN(orden4, "\""+nombre+"\"");} // Registrar que completaste el juego

                            //Mostrar top3
                            System.out.println("\nPrimeros 3 jugadores en terminar:\n");
                            String[] top3 = rewr.primerasLineas(orden4, 3);
                            if (top3[0] != null) {System.out.println("1: " + top3[0]);}
                            if (top3[1] != null) {System.out.println("2: " + top3[1]);}
                            if (top3[2] != null) {System.out.println("3: " + top3[2]);}

                            if (rewr.buscar(orden4,"\""+nombre+"\"") != -1) {System.out.println("\nTu quedaste en el puesto " + (rewr.buscar(orden4, "\""+nombre+"\"") + 1) + "\n\n");}
                        }
                        if (seleccion == 3) {
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
                System.out.println("");
                String[] texto = rewr.leer(puntaje);
                int[] puntajes = new int[texto.length];
                String[] nombres = new String[texto.length];

                for (int i = 0; i < texto.length; i++) {
                    int primeraComilla = texto[i].indexOf("\"");
                    puntajes[i] = Integer.parseInt(texto[i].substring(texto[i].indexOf("\"", primeraComilla+1)+1));
                    nombres[i] = texto[i].substring(primeraComilla+1, texto[i].indexOf("\"", primeraComilla+1));
                }

                //bubblesort
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

                int cont = 0;
                for(int i = 0; i < puntajes.length; i++) {
                    if (nombres[i] != null) {
                        System.out.println("Top " + (i+1) + ": " + nombres[i] + " con " + puntajes[i] + " puntos");
                        cont++;
                    }
                    if (cont >= 3) {break;}
                }
                System.out.println("");
                System.out.print("Tu posicion ");

                //if ()


            }

            // Ver puntuacion personal
            if (seleccion == 3) {
                String[] texto = rewr.leer(puntaje);
                int jotaro = rewr.buscar(puntaje,"\""+nombre+"\"");
                if (jotaro == -1) {
                    System.out.println("\nPuntaje personal: " + puntos);
                    System.out.println("Creditos Restantes: " + creditos);
                } else {
                    System.out.println("\nPuntaje personal: " + (texto[jotaro].substring(texto[jotaro].indexOf("\""+nombre+"\"")+nombre.length()+2)));
                    System.out.println("Creditos Restantes: " + creditos);
                }
            }
            // Salir
            if (seleccion == 4) {
                break;
            }
        } while (true);

        System.out.println("\n\nAdios :3");
    }
}
