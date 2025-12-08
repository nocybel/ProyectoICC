/**
 * Clase NumSecreto hereda de JuegoAdivinacion. Es un juego donde tienes que adivinar un numero entre 0 y 10
 * 
 * @author Iago HB
 */
package juegos.juegoAdivinacion;
import java.util.Scanner;
public class NumSecreto extends JuegoAdivinacion {

    /** Metodo auxiliar para que el usuario escriba un numero */
    private int adivinar() {
        Scanner in = new Scanner(System.in);
        System.out.println("Adivine el numero secreto entre 0 y 10:");
        int adivinar = in.nextInt();
        in.nextLine();
        return adivinar;
    }

    /** Metodo para jugar
     * @return int vidas que fueron eliminadas, o -1 si se perdieron todas
     */
    @Override
    public int jugar(int vidas) {
        int vecesPerdidas = 0;
        
        int adivinar;
        int numSecreto = generarNumero(0,10);
        
        
        do {
            System.out.println("Vidas restantes: " + (vidas - vecesPerdidas));
            do {
                try{
                    adivinar = adivinar();
                    break;
                } catch (Exception e) {
                    System.out.println("Escriba un numero entero");
                }
            } while (true);
            
            if (adivinar == numSecreto) {break;}
            if (adivinar < numSecreto) {System.out.println("El numero es mayor");vecesPerdidas++;}
            if (adivinar > numSecreto) {System.out.println("El numero es menor");vecesPerdidas++;}

            if (vecesPerdidas >= vidas) {return -1;}

        } while (true);

        System.out.println("Ganaste");

        return vecesPerdidas;
    }
    
}