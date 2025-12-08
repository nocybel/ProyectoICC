/**
 * Clase MasMenos hereda de JuegoAdivinacion. Es un juego donde tienes que adivinar si un numero es mayor o menor que otro numero
 * 
 * @author Iago HB
 */
package juegos.juegoAdivinacion;
import java.util.Scanner;
public class MasMenos extends JuegoAdivinacion {

    /** Metodo para jugar
     * @return int vidas que fueron eliminadas, o -1 si se perdieron todas
     */
    @Override
    public int jugar(int vidas) {
        int vecesPerdidas = 0;
        Scanner in = new Scanner(System.in);
        int numero = generarNumero(1,50);
        int numeroOculto = generarNumero(1, 50);
        int adivinar;

        System.out.println("Vidas restantes: " + (vidas - vecesPerdidas));

        System.out.println("El numero es " + numero);

        do {
            System.out.println("Crees que el numero oculto es:\n1. Igual\n2. Menor\n3. Mayor");

            do {
                try{
                    adivinar = in.nextInt();
                    if (adivinar < 1 || adivinar > 3) {throw new Exception();}
                    break;
                } catch (Exception e) {
                    in.nextLine();
                    System.out.println("Escriba una opcion correcta");
                }
            } while (true);

            switch (adivinar) {
                case 1:
                    if (numero == numeroOculto) {System.out.println("Ganaste");return vecesPerdidas;} else {System.out.println("El numero oculto no es igual a " + numero);vecesPerdidas++;}
                    break;
                case 2:
                    if (numero < numeroOculto) {System.out.println("Ganaste");return vecesPerdidas;} else {System.out.println("El numero oculto no es menor a " + numero);vecesPerdidas++;}
                    break;
                case 3:
                    if (numero > numeroOculto) {System.out.println("Ganaste");return vecesPerdidas;} else {System.out.println("El numero oculto no es mayor a " + numero);vecesPerdidas++;}
                    break;
            }

            if (vecesPerdidas >= vidas) {return -1;}
        } while (true);
    }
    
}
