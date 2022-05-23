package sample.gameplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class GameClient {

    Socket s = null;
    private BufferedReader inFromServer = null;
    private PrintWriter outToServer = null;
    private int koyjonerMatch;
    private Vector<String>jaygarNam = new Vector<>();
    private Vector<Integer> amarJayga = new Vector<>();
    int serial ;


    public BufferedReader getInFromServer() {
        return inFromServer;
    }

    public PrintWriter getOutToServer() {
        return outToServer;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getSerial() {
        return serial;
    }

    public int getKoyjonerMatch() {
        return koyjonerMatch;
    }

    public void setKoyjonerMatch(int koyjonerMatch) {
        this.koyjonerMatch = koyjonerMatch;
    }

    public Vector<String> getJaygarNam() {
        return jaygarNam;
    }

    public Vector<Integer> getAmarJayga() {
        return amarJayga;
    }

    public GameClient()
    {
        try {
            s = new Socket("localhost", 61689);
            //s = new Socket("172.26.15.226", 61689);
            System.out.println("connected");
        } catch (Exception e) {
            System.err.println("Problem in connecting with the server. Exiting main.");
            System.exit(1);
        }

        try {
            inFromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outToServer = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        jaygarNam.add("Farmgate");
        jaygarNam.add("Shahbag");
        jaygarNam.add("Palashi");
        jaygarNam.add("Azimpur");
        jaygarNam.add("Dhanmondi");
        jaygarNam.add("Tongi");
        jaygarNam.add("Khilkhet");
        jaygarNam.add("Shyamoli");
        jaygarNam.add("Pallabi");
        jaygarNam.add("Mirpur");
        jaygarNam.add("Uttara");
        jaygarNam.add("Motijheel");
        jaygarNam.add("Arambagh");
        jaygarNam.add("Wari");
        jaygarNam.add("Jatrabari");
        jaygarNam.add("Khilgaon");
        jaygarNam.add("Shantinagar");
        jaygarNam.add("Baridhara");
        jaygarNam.add("Kuril");
        jaygarNam.add("Badda");
        jaygarNam.add("Gulshan");
        jaygarNam.add("Banani");
        jaygarNam.add("Tejgaon Station");
        jaygarNam.add("Airport Station");
        jaygarNam.add("Kamalapur Station");
        jaygarNam.add("Cantonment Station");
        jaygarNam.add("Electric Company");
        jaygarNam.add("Water Works");


        for(int i=0;i<28; i++)
        {
            amarJayga.add(0);
        }



    }

}
