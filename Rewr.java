/**
 * Clase Rewr (aka Read-Write). Maneja todo lo relacionado con leer y escribir cosas en archivos
 * 
 * @author Iago HB
 */
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
public class Rewr {

    // Metodos relacionados con leer archivos
    public String[] leer(String archivo) {
        int cont = 0;
        
        try (BufferedReader re = new BufferedReader(new FileReader(archivo))) {
            while (re.readLine() != null) {
                cont++;
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo :C");
        }

        try (BufferedReader re = new BufferedReader(new FileReader(archivo))) {
            String[] lineas = new String[cont];
            for (int i = 0; i < cont; i++){
                lineas[i] = re.readLine();
            }
            return lineas;
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo :C");
        }

        String[] backup = {};
        return backup;
    }

    public int buscar(String archivo, String texto) {
        String[] lineas = leer(archivo);

        for (int i = 0; i < lineas.length; i++) {
            if (lineas[i] != null && lineas[i].contains(texto)) {
                return i;
            }
        }

        return -1;
    }

    public String[] primerasLineas(String archivo, int fin) {
        String[] lineas = leer(archivo);
        String[] regreso = new String[fin];
        for (int i = 0; i < fin; i++){
            if(i >= lineas.length) {break;}
            regreso[i] = lineas[i];
        }

        String[] backup = {};
        if (regreso[0] != null) {return regreso;} else {return backup;}
    }

    // Metodos relacionados con escribir archivos

    public void escribirN(String archivo, String texto) {
        try (FileWriter wr = new FileWriter(archivo,true)) {
            wr.write(texto+"\n");
        } catch (Exception e) {
            System.out.println("No se pudo escribir en el archivo :C");
        }
    }

    public void escribirO(String archivo, int linea, String texto) {

        String[] lineas = leer(archivo);

        lineas[linea] = texto;


        for (int i = 0; i < lineas.length; i++) {
            try (FileWriter wr = new FileWriter(archivo,false)) {
                wr.write(lineas[i]+"\n");
            } catch (Exception e) {
                System.out.println("No se pudo escribir en el archivo :C");
            }
        }
    }

}