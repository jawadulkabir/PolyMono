package sample.gameplay;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.concurrent.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Trial implements Initializable {

    @FXML
    Circle nil_guti, lal_guti, sobuj_guti, holud_guti, guti;

    @FXML
    GridPane grid;

    @FXML
    ImageView dice1, dice2, dolilShower;

    @FXML
    Button rollButton, payButton, cancelButton, buyButton;

    final int interval = 170;
    int numPlr;
    int pos[] ;
    String string;
    int serialint;
    boolean jailCommand = false;
    int jail_e_jawar_turn = -1;
    int Dice1, Dice2;
    boolean directly = true;
    int indirectDice;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        rollButton.setDisable(true);
        payButton.setDisable(true);
        cancelButton.setDisable(true);
        buyButton.setDisable(true);

        numPlr = PlayOptions1.getgc().getKoyjonerMatch() ;
        pos = new int[numPlr];
        for(int i=0;i<numPlr; i++)
        {
            pos[i] = 0;
        }

        //System.out.println(numPlr);
        if(numPlr == 2)
        {
            sobuj_guti.setVisible(false); holud_guti.setVisible(false);
        }
        if(numPlr == 3)
        {
            holud_guti.setVisible(false);
        }


        Thread t = new Thread(inputFromServer);
        t.setDaemon(true);
        t.start();

    }

    public Trial() {
    }

    public void rollDice(int dice_1, int dice_2, Circle guti, int ii) {

        int dice = dice_1 + dice_2;
        RotateTransition rt1 = new RotateTransition(Duration.seconds(0.25));
        RotateTransition rt2 = new RotateTransition(Duration.seconds(0.25));

        rt1.setNode(dice1);    rt1.setByAngle(360);    rt1.setFromAngle(0);
        rt2.setNode(dice2);    rt2.setByAngle(360);    rt2.setFromAngle(0);

        rt1.setCycleCount(3);    rt1.setAutoReverse(true);
        rt2.setCycleCount(3);    rt2.setAutoReverse(true);

        rt1.play();    rt2.play();

        rt2.setOnFinished(e -> {

            if (dice_1 == 1) dice1.setImage(new Image("file:d1.jpg"));
            else if (dice_1 == 2) dice1.setImage(new Image("file:d2.jpg"));
            else if (dice_1 == 3) dice1.setImage(new Image("file:d3.jpg"));
            else if (dice_1 == 4) dice1.setImage(new Image("file:d4.jpg"));
            else if (dice_1 == 5) dice1.setImage(new Image("file:d5.jpg"));
            else dice1.setImage(new Image("file:d6.jpg"));

            if (dice_2 == 1) dice2.setImage(new Image("file:d1.jpg"));
            else if (dice_2 == 2) dice2.setImage(new Image("file:d2.jpg"));
            else if (dice_2 == 3) dice2.setImage(new Image("file:d3.jpg"));
            else if (dice_2 == 4) dice2.setImage(new Image("file:d4.jpg"));
            else if (dice_2 == 5) dice2.setImage(new Image("file:d5.jpg"));
            else dice2.setImage(new Image("file:d6.jpg"));

            rt1.setByAngle(0);  rt1.setFromAngle(360);
            rt2.setByAngle(0);  rt2.setFromAngle(360);

            rt1.setCycleCount(1);
            rt2.setCycleCount(1);

            rt1.play();    rt2.play();

            rt2.setOnFinished(event -> doTask(guti, ii, dice));


        });

    }


    public void rollDiceClicked() {

        rollButton.setDisable(true);

        Random rand = new Random();

        int dice_1 = rand.nextInt(6) + 1;
        int dice_2 = rand.nextInt(6) + 1;

        //if(PlayOptions1.getgc().getSerial()==1)
       // {dice_1 = 4;        dice_2 = 6 ; }


        int dice = dice_1 + dice_2;
        System.out.println("kiii "+dice);

        sendmsg2server("rolldice#"+String.valueOf(dice_1)+"#"+String.valueOf(dice_2));

    }


    public void porer_ghore_ja(Circle guti, int ii) {

        if (pos[ii] >= 0 && pos[ii] < 10)
        {
            int x = 9 - pos[ii];
            pos[ii] = (pos[ii] + 1);
            grid.getChildren().remove(guti);
            grid.add(guti, x, 10);
        }
        else if (pos[ii] > 9 && pos[ii] < 20)
        {
            int x = 19 - pos[ii];
            pos[ii] = (pos[ii] + 1);
            grid.getChildren().remove(guti);
            grid.add(guti, 0, x);
        }
        else if (pos[ii] > 19 && pos[ii] < 30)
        {
            int x = pos[ii] - 19;
            pos[ii] = (pos[ii] + 1);
            grid.getChildren().remove(guti);
            grid.add(guti, x, 0);
        }
        else if (pos[ii] > 29 && pos[ii] < 40)
        {
            int x = pos[ii] - 29;
            if (pos[ii] == 39)
                pos[ii] = (0);
            else
                pos[ii] = (pos[ii] + 1);
            grid.getChildren().remove(guti);
            grid.add(guti, 10, x);
        }
    }

    public void jaile_ja(Circle guti, int ii)
    {
        pos[ii] = 10;
        grid.getChildren().remove(guti);
        grid.add(guti, 0, 10);
    }

    public void p1() { dolilShower.setImage(new Image("file:P1.png")); }
    public void p2() { dolilShower.setImage(new Image("file:P2.png")); }
    public void p3() { dolilShower.setImage(new Image("file:P3.png")); }
    public void p4() { dolilShower.setImage(new Image("file:P4.png")); }
    public void p5() { dolilShower.setImage(new Image("file:P5.png")); }
    public void p6() { dolilShower.setImage(new Image("file:P6.png")); }
    public void p7() { dolilShower.setImage(new Image("file:P7.png")); }
    public void p8() { dolilShower.setImage(new Image("file:P8.png")); }
    public void p9() { dolilShower.setImage(new Image("file:P9.png")); }
    public void p10() { dolilShower.setImage(new Image("file:P10.png")); }
    public void p11() { dolilShower.setImage(new Image("file:P11.png")); }
    public void p12() { dolilShower.setImage(new Image("file:P12.png")); }
    public void p13() { dolilShower.setImage(new Image("file:P13.png")); }
    public void p14() { dolilShower.setImage(new Image("file:P14.png")); }
    public void p15() { dolilShower.setImage(new Image("file:P15.png")); }
    public void p16() { dolilShower.setImage(new Image("file:P16.png")); }
    public void p17() { dolilShower.setImage(new Image("file:P17.png")); }
    public void p18() { dolilShower.setImage(new Image("file:P18.png")); }
    public void p19() { dolilShower.setImage(new Image("file:P19.png")); }
    public void p20() { dolilShower.setImage(new Image("file:P20.png")); }
    public void p21() { dolilShower.setImage(new Image("file:P21.png")); }
    public void p22() { dolilShower.setImage(new Image("file:P22.png")); }

    public void s1() { dolilShower.setImage(new Image("file:S1.png")); }
    public void s2() { dolilShower.setImage(new Image("file:S2.png")); }
    public void s3() { dolilShower.setImage(new Image("file:S3.png")); }
    public void s4() { dolilShower.setImage(new Image("file:S4.png")); }

    public void u1() { dolilShower.setImage(new Image("file:U1.png")); }
    public void u2() { dolilShower.setImage(new Image("file:U2.png")); }

    Task<Void> inputFromServer = new Task<Void>() {
        @Override
        protected Void call() {
            while (true)
            {
                string="";
                try {
                    string = PlayOptions1.getgc().getInFromServer().readLine();
                    System.out.println("stringta hoilo.."+string);
                    if(string.contains("confirm"))
                    {
                        System.out.println("successfuol");
                        //for(int i=0;i<numPlr;i++)
                        {
                            String newmsg[] = new String[4];
                            newmsg =  string.split("#");

                            String serial =newmsg[0];
                            serialint = Integer.parseInt(serial);


                            serialint--; // 1 baray pathano hoise, ekhane 0 theke indexing dorkar
                            Dice1 = Integer.parseInt(newmsg[2]);
                            Dice2 = Integer.parseInt(newmsg[3]);
                            System.out.println("dice1 "+Dice1+" dice2 "+Dice2);

                            /*if(PlayOptions1.getgc().getSerial() == (porerjon-1))
                             */
                            //System.out.println(serialint+" , "+numPlr+" "+serialint % numPlr+" == "+PlayOptions1.getgc().getSerial()+"-1");
                            /*if( serialint  == (PlayOptions1.getgc().getSerial()+numPlr-2)% numPlr) //real shit
                                rollButton.setDisable(false);*/

                            System.out.println("serial ceck " + serial.equals("1"));
                            if(serial.equals("1"))  rollDice(Dice1, Dice2, nil_guti, serialint);
                            else if(serial.equals("2") ) rollDice(Dice1, Dice2, lal_guti, serialint);
                            else if(serial.equals("3")) rollDice(Dice1, Dice2, sobuj_guti, serialint);
                            else if(serial.equals("4") ) rollDice(Dice1, Dice2, holud_guti, serialint);
                            //System.out.println("exited ifelse");

                        }
                    }

                    else if(string.startsWith("enableKoroButtonczKhelaShuru"))
                    {
                        int serialint = Integer.parseInt(string.substring(string.length()-1));
                        serialint++; //raw index pathano hoise, serial 1 theke shuru hobe
                        PlayOptions1.getgc().setSerial(serialint);
                        System.out.println("game e serial "+serialint);
                        if (serialint== 1) rollButton.setDisable(false);
                    }

                    else if(string.equals("you are in jail. pay mofo."))
                    {
                        rollButton.setDisable(true);
                        alertbox(1, "You Have To Pay BDT 50 To Get Out Of Jail");
                        jail_e_jawar_turn = -1;
                    }

                    else if(string.contains("jail e ja"))
                    {

                        //System.out.println("hmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
                        jailCommand = true;
                        jail_e_jawar_turn = Integer.parseInt(string.substring(0, 1));
                        if(jail_e_jawar_turn == PlayOptions1.getgc().getSerial())
                            sendmsg2server("ok gelam :(");
                    }


                    else if(string.equals("you are free !!"))
                    {
                        sendmsg2server("yesss im free");
                        rollButton.setDisable(false);
                    }


                    else if(string.equals("porerjon enable"))
                    {
                        if( serialint  == (PlayOptions1.getgc().getSerial()+numPlr-2)% numPlr) //real shit
                            rollButton.setDisable(false);
                    }

                    else if(string.equals("paytax1"))
                    {
                        alertbox(Dice1+Dice2, "You Have To Pay Income Tax");
                    }

                    else if(string.equals("paytax2"))
                    {
                        alertbox(Dice1+Dice2, "You Have To Pay Luxury Tax");
                    }

                    else if(string.equals("pay rent"))
                    {
                        if(directly == false)
                        {
                            alertbox(indirectDice, "You Have To Pay Rent For This Property");
                            directly = true;
                        }
                        else alertbox(Dice1+Dice2, "You Have To Pay Rent For This Property");
                    }

                    else if(string.contains("buy this shit"))
                    {
                        String x = string.substring("buy this shit".length());

                        StringBuffer sb = new StringBuffer("Do You Want To Buy This Property?");
                        sb.append(x);
                        String s = sb.toString();

                        if(directly == false)
                        {
                            alertbox(indirectDice, s);
                            directly = true;
                        }
                        else {
                            alertbox(Dice1 + Dice2, s);
                        }
                    }

                    else if(string.contains("vaggo") || string.contains("sujog"))
                    {
                        alertbox(Dice1 + Dice2, string);
                    }

                    else if(string.equals("congo tumi jatra shuru otikrom korso"))
                    {
                        alertbox(Dice1 + Dice2, string);
                        directly = false;
                        indirectDice  = 1;
                    }

                    /*else if(string.contains("advance to tejgaon"))
                    {
                        indirectDice = Integer.parseInt(string.substring(19));
                        int ii;
                        if(string.charAt(0) == '1') { guti = nil_guti;  ii = 0;}
                        else if(string.charAt(0) == '2') { guti = lal_guti;  ii = 1;}
                        else if(string.charAt(0) == '3') { guti = sobuj_guti;  ii = 2;}
                        else { guti = holud_guti;  ii = 3;}
                        doTask(guti, ii, indirectDice);
                        directly = false;
                    }

                    else if(string.contains("advance to kamalapur"))
                    {
                        indirectDice = Integer.parseInt(string.substring(21));
                        int ii;
                        if(string.charAt(0) == '1') { guti = nil_guti;  ii = 0;}
                        else if(string.charAt(0) == '2') { guti = lal_guti;  ii = 1;}
                        else if(string.charAt(0) == '3') { guti = sobuj_guti;  ii = 2;}
                        else { guti = holud_guti;  ii = 3;}
                        doTask(guti, ii, indirectDice);
                        directly = false;
                    }

                    else if(string.contains("advance to cantonment"))
                    {
                        indirectDice = Integer.parseInt(string.substring(22));
                        System.out.println("ekhane esheche");
                        int ii;
                        if(string.charAt(0) == '1') { guti = nil_guti;  ii = 0;}
                        else if(string.charAt(0) == '2') { guti = lal_guti;  ii = 1;}
                        else if(string.charAt(0) == '3') { guti = sobuj_guti;  ii = 2;}
                        else { guti = holud_guti;  ii = 3;}
                        doTask(guti, ii, indirectDice);
                        directly = false;
                    }

                    else if(string.contains("advance to tongi"))
                    {
                        indirectDice = Integer.parseInt(string.substring(17));
                        int ii;
                        if(string.charAt(0) == '1') { guti = nil_guti;  ii = 0;}
                        else if(string.charAt(0) == '2') { guti = lal_guti;  ii = 1;}
                        else if(string.charAt(0) == '3') { guti = sobuj_guti;  ii = 2;}
                        else { guti = holud_guti;  ii = 3;}
                        doTask(guti, ii, indirectDice);
                        directly = false;
                    }

                    else if(string.contains("advance to wari"))
                    {
                        indirectDice = Integer.parseInt(string.substring(16));
                        int ii;
                        if(string.charAt(0) == '1') { guti = nil_guti;  ii = 0;}
                        else if(string.charAt(0) == '2') { guti = lal_guti;  ii = 1;}
                        else if(string.charAt(0) == '3') { guti = sobuj_guti;  ii = 2;}
                        else { guti = holud_guti;  ii = 3;}
                        doTask(guti, ii, indirectDice);
                        directly = false;
                    }

                    else if(string.contains("advance to banani"))
                    {
                        indirectDice = Integer.parseInt(string.substring(18));
                        int ii;
                        if(string.charAt(0) == '1') { guti = nil_guti;  ii = 0;}
                        else if(string.charAt(0) == '2') { guti = lal_guti;  ii = 1;}
                        else if(string.charAt(0) == '3') { guti = sobuj_guti;  ii = 2;}
                        else { guti = holud_guti;  ii = 3;}
                        doTask(guti, ii, indirectDice);
                        directly = false;
                    }*/

                    else if(string.contains("someplace"))
                    {
                        indirectDice = Integer.parseInt(string.substring(21));
                        int ii;
                        if(string.charAt(0) == '1') { guti = nil_guti;  ii = 0;}
                        else if(string.charAt(0) == '2') { guti = lal_guti;  ii = 1;}
                        else if(string.charAt(0) == '3') { guti = sobuj_guti;  ii = 2;}
                        else { guti = holud_guti;  ii = 3;}
                        doTask(guti, ii, indirectDice);
                        if(!string.contains("go"))
                                directly = false;
                    }

                   /* else if(string.contains("advance to go"))
                    {
                        indirectDice = Integer.parseInt(string.substring(14));
                        int ii;
                        if(string.charAt(0) == '1') { guti = nil_guti;  ii = 0;}
                        else if(string.charAt(0) == '2') { guti = lal_guti;  ii = 1;}
                        else if(string.charAt(0) == '3') { guti = sobuj_guti;  ii = 2;}
                        else { guti = holud_guti;  ii = 3;}
                        doTask(guti, ii, indirectDice);
                        //directly = false;
                    }*/

                   else if(string.equals("bari boshabi?"))
                    {
                        alertbox(Dice1 + Dice2, "Do You Want To Build A House Here?");
                    }

                    else if(string.equals("hotel boshabi?"))
                    {
                        alertbox(Dice1 + Dice2, "Do You Want To Build A Hotel Here?");
                    }

                    else if(string.equals("kicchu boshanor nai"))
                    {
                        alertbox(Dice1 + Dice2, "This Is Your Property, But You Cannot Construct Anything Here");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("client side e sompod:");
                for(int i =0; i<28;i++)
                {
                    if(PlayOptions1.getgc().getAmarJayga().elementAt(i)==1)
                    {
                        System.out.println(PlayOptions1.getgc().getJaygarNam().elementAt(i));
                    }
                }
            }

        }


    };

    public void buyClicked()
    {
        buyButton.setDisable(true);
        cancelButton.setDisable(true);
        sendmsg2server("ok buy");
    }
    public void cancelClicked()
    {
        cancelButton.setDisable(true);
        buyButton.setDisable(true);
        sendmsg2server("me no buy");
    }
    public void payClicked()
    {
        payButton.setDisable(true);
        sendmsg2server("ok pay");
    }

    public void sendmsg2server(String msg)
    {
        PlayOptions1.getgc().getOutToServer().println(msg);
        PlayOptions1.getgc().getOutToServer().flush();
    }

    public void doTask(Circle guti, int ii, int dice) {

        System.out.println("Running Task");

        Thread th = new Thread(new Task<String>() {

            @Override
            protected String call() throws Exception {

                Thread.sleep(200);

                for(int i = 0; i < dice; i++)
                {
                    Thread.sleep(interval);
                    Platform.runLater(() -> porer_ghore_ja(guti, ii));
                }

                if(jailCommand == true)
                {
                    jailCommand = false;
                    Thread.sleep(1018); //11701018
                    Platform.runLater(() -> jaile_ja(guti, ii));

                }

                return "Hudai 1ta String Pathailam Task Er Return Type ErJ onno";
            }
        });

        th.setDaemon(true);
        th.start();
    }

    public void alertbox(int d, String msg){
        System.out.println("Running Task");
        AlertBox ab = new AlertBox();
        Thread th = new Thread(new Task<String>() {

            @Override
            protected String call() throws Exception {
                Thread.sleep(1500 + d*interval);
                Platform.runLater(() -> ab.display("Confirmation Box", msg) );

                return "Hudai 1ta String Pathailam Task Er Return Type ErJ onno";
            }
        });

        th.setDaemon(true);
        th.start();

    }
}