import java.io.*;
import java.util.Scanner;
public class tpinteg {
    public static void main(String[] args) {
        // nueva instancia de pronosticos
        setOracle oraculo = new setOracle();
        oraculo.readPredictions("pronosticos.txt");
        int score = 0;
        // nueva instancia de partidos
        setGame partidos = new setGame();
        partidos.readResults("resultados.txt");
        // organiza todos los partidos uno por uno
        System.out.println("Resultados de la ronda: ");
        for(int i = 0, j = 0; i < partidos.tms.length; i += 2, j++) {
            // nueva instancia de calc
            calc play = new calc();
            // asigna equipos
            play.mch[0] = partidos.tms[i];
            play.mch[1] = partidos.tms[i + 1];
            play.res[0] = partidos.gls[i];
            play.res[1] = partidos.gls[i + 1];
            // anuncia ganador
            String resFin = play.win();
            // j es para contar los indices de oraculo.str
            if(resFin.equalsIgnoreCase(oraculo.str[j])) {
                score++;
            }
        }
        System.out.println("Puntaje: " + Integer.toString(score));
    }
}
class setOracle {
    // str contiene todo
    public String[] str;
    // metodo principal
    public void readPredictions(String oraclePath) {
        try {
            // lee el archivo, guarda su totalidad en un String y su version dividida en un array
            BufferedReader in = new BufferedReader(new FileReader(oraclePath));
            Scanner read = new Scanner(in);
            String whole = read.nextLine();
            str = whole.split(",");
            read.close();
        }
        catch (IOException e) {
            System.out.println("no se encontró el archivo");
        }
    }
}
class setGame {
    // tms por equipos, gls por goles
    public String[] tms;
    public int[] gls;
    // metodo principal
    public void readResults(String gamePath) {
        try {
            // lee el archivo, guarda su totalidad en un String y su version dividida en un array
            BufferedReader in = new BufferedReader(new FileReader(gamePath));
            Scanner read = new Scanner(in);
            String whole = read.nextLine();
            String[] allItems = whole.split(",");
            // determina el largo de los arrays independiente de la cantidad de partidos
            tms = new String[allItems.length / 2];
            gls = new int[allItems.length / 2];
            // llena los arrays con los valores correspondientes
            for(int i = 0, j = 0, k = 0; i < allItems.length; i++) {
                if(i % 2 == 0) {
                    tms[j] = allItems[i];
                    // j es para contar los indices de tms
                    j++;
                }
                else {
                    gls[k] = Integer.parseInt(allItems[i]);
                    // k es para contar los indices de gls
                    k++;
                }
            }
            read.close();
        }
        catch (IOException e) {
            System.out.println("no se encontró el archivo");
        }
    }
}
class calc {
    // mch por partido, res por resultado
    public String[] mch = new String[2];
    public int[] res = new int[2];
    public String end;
    // metodo principal
    public String win() {
        if(res[0] > res[1]) {
            System.out.println(mch[0] + " ganó " + res[0] + " a " + res[1] + " contra " +mch[1]);
            return mch[0];
        }
        else if(res[1] > res[0]) {
            System.out.println(mch[1] + " ganó " + res[1] + " a " + res[0] + " contra " +mch[0]);
            return mch[1];
        }
        else {
            System.out.println(mch[0]  +" y " + mch[1] + " empataron con " + res[0] + " goles cada uno.");
            return "Empate";
        }
    }
}