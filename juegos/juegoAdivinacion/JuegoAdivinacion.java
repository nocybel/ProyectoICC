/**
 * No deberia ser una clase abstracta. ¿Para que sirve que esto sea una clase abstracta? Seria mejor si fuera una clase normal con metodos para cada ronda
 * Mierda de clase
 * 
 * @author Iago HB
 */
package juegos.juegoAdivinacion;
public abstract class JuegoAdivinacion {

    protected int vidas;

    /** Este metodo genera un numero al azar dentro de un intervalo cerrado */
    protected int generarNumero(int min, int max) {
        int num = (int) (Math.random() * (max - min + 1) + min);

        // Lei en un lado que este metodo no es 100% preciso, pues a veces puede generar un numero fuera del rango. Estos ifs corrigen eso creo
        if (num >= max) {num = max;}
        if (num <= min) {num = min;}

        return num;
    }

    /** Este metodo muestra las vidas restantes. Nunca se usa */
    protected void muestraVidasRestantes() {
        System.out.println("Vidas restantes: " + vidas);
    }

    /** Este metodo quita una vida y muestra cuantas vidas quedan o si moriste. Nunca se usa */
    protected void quitaVida() {
        vidas--;
        System.out.println("Perdiste una vida");
        if (vidas == 0) {System.out.println("Moriste");} else {muestraVidasRestantes();}
    }

    /** Las clases hijas deben implementar este metodo para jugar */
    public abstract int jugar(int vidas);

}