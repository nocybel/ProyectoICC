/**
 * Coordinador de las clases de JuegoAdivinacion. Este es el peor juego, esta mal hecho, es aburrido. Dividir cada ronda en objetos distintos es tonto, perdida de tiempo.
 * 
 * @author Iago HB
 */
package juegos;
import juegos.juegoAdivinacion.*;
public class AdivinaYSobrevive {

    /** Metodo para jugar
     * @return int puntuacion
     */
    public int jugar() {
        int vidas = 5;
        int puntuacion = 0;

        // Ronda 1
        if (vidas > 0) {
            NumSecreto ronda1 = new NumSecreto();
            int vidasPerdidas = ronda1.jugar(vidas);

            if (vidasPerdidas == -1) {puntuacion += 1; return puntuacion;}
            vidas -= vidasPerdidas;
            if (vidas < 0) {puntuacion += 1; return puntuacion;}
        }

        // Ronda 2
        if (vidas > 0) {
            MasMenos ronda2 = new MasMenos();
            int vidasPerdidas = ronda2.jugar(vidas);

            if (vidasPerdidas == -1) {puntuacion += 3; return puntuacion;}
            vidas -= vidasPerdidas;
            if (vidas < 0) {puntuacion += 3; return puntuacion;}
        }

        // Ronda 3
        if (vidas > 0) {
            TresCifras ronda3 = new TresCifras();
            int vidasPerdidas = ronda3.jugar(vidas);

            if (vidasPerdidas == -1) {puntuacion += 5; return puntuacion;}
            vidas -= vidasPerdidas;
            if (vidas < 0) {puntuacion += 5; return puntuacion;}
        }

        puntuacion += 10;
        return puntuacion;
    }
}