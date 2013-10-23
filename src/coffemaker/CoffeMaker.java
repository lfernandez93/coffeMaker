/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffemaker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author -Luis Sergio-
 */
public class CoffeMaker {

    private ArrayList participants;
    private String name;
    private Calendar c1 = Calendar.getInstance();

    public CoffeMaker(ArrayList participants) {
        this.participants = participants;
    }

    public void randomize() {
        Random r = new Random();
        int value = r.nextInt(participants.size());
        try {
            if (participants.get(value) != null) {

                name = participants.get(value).toString();
            }
        } catch (IndexOutOfBoundsException e) {
            randomize();
        }
    }

    public String getCoffeMaker() {
        return name;
    }

    public void saveCoffeMaker() {
        File f = new File("coffeMakers.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(CoffeMaker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            String linea2 = "";
            while ((linea2 = br.readLine()) != null) {
                linea += linea2 + "\n";
            }
            linea += getCoffeMaker() + " - " + getFecha();
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write(linea);
            wr.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getFecha() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
}
