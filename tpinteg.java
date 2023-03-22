import java.io.*;
import java.util.Scanner;
public class tpinteg {
    public static void main(String[] args) {
        // nueva instancia de set
        set inst = new set();
        inst.readTxt();
        // organiza todos los partidos
        for(int i = 0; i < inst.tms.length; i += 2) {
            // nueva instancia de calc
            calc game = new calc();
            // asigna equipos
            game.mch[0] = inst.tms[i];
            game.mch[1] = inst.tms[i + 1];
            game.res[0] = inst.gls[i];
            game.res[1] = inst.gls[i + 1];
            // anuncia ganador
            game.win();
        }
    }
}
class set {
    // gls por goles, tms por equipos
    public int[] gls = new int[4];
    public String[] tms = new String [4];
    // metodo principal
    public void readTxt() {
        try {
            // lee archivo y separa por comas
            BufferedReader in = new BufferedReader(new FileReader("resultados.txt"));
            Scanner read = new Scanner(in);
            read.useDelimiter(",");
            // itera hasta que no hay mas valores para agregar a las listas
            for(int i = 0; read.hasNext(); i++) {
                String pais = read.next();
                int gol = Integer.parseInt(read.next());
                tms[i] = pais;
                gls[i] = gol;
            }
            read.close();
        }
        catch (IOException e) {
            System.out.println("no se encontró el archivo");
        }
    }
}
class calc{
    // mch por partido, res por resultado
    public String[] mch = new String[2];
    public int[] res = new int[2];
    // metodo principal
    public void win() {
        if(res[0] > res[1]) {
            System.out.println(mch[0] + " ganó");
        }
        else if(res[1] > res[0]) {
            System.out.println(mch[1] + " ganó");
        }
        else {
            System.out.println("Es un empate");
        }
    }
}