/**
 * Clase MasMenos hereda de JuegoAdivinacion. Es un juego donde tienes que adivinar una combinacion de 3 numeros
 * 
 * @author Iago HB
 */
package juegos.juegoAdivinacion;
import java.util.Scanner;
public class TresCifras extends JuegoAdivinacion{

    /** Metodo auxiliar para que el usuario escriba los numeros */
    private int[] elegirFichas() {
        Scanner in = new Scanner(System.in);
        System.out.println("Escribe un número de 3 cifras separadas por espacios");
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        int[] lista = {x, y, z};
        in.nextLine();
        return lista;
    }

    /** Metodo para jugar
     * @return int vidas que fueron eliminadas, o -1 si se perdieron todas
     */
    @Override
    public int jugar(int vidas) {
        int vecesPerdidas = 0;
        
        int num1 = generarNumero(0,9);
        int num2 = generarNumero(0,9);
        int num3 = generarNumero(0,9);
        int[] lista = new int[3];

        do {
            System.out.println("Vidas restantes: " + (vidas - vecesPerdidas));

            do {
                try {
                    lista = elegirFichas();
                    break;
                } catch (Exception e) {
                    System.out.println("Escriba números enteros solamente");
                }
            } while (true);

            if (lista[0] == num1 && lista[1] == num2 && lista[2] == num3) {System.out.println("Ganaste");break;}

            if (lista[0] == num1) {System.out.println("El primer número es correcto");}
            if (lista[1] == num2) {System.out.println("El segundo número es correcto");}
            if (lista[2] == num3) {System.out.println("El tercer número es correcto");}
            if (lista[0] != num1 && lista[1] != num2 && lista[2] != num3) {System.out.println("Ningún número es correcto");}

            vecesPerdidas++;
            if (vecesPerdidas >= vidas) {return -1;}
        } while (true);

        return vecesPerdidas;
    }
    
}
