package sample.behindthescene;

import sun.awt.SunHints;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class PlayerWorld implements Runnable {

    private Socket sckt = null;
    private int playerIDwhole;
    private static int numPlayer = 0;
    private static int p2 =0, p3= 0, p4 = 0;
    private int mwID = 0;
    private boolean breakfromwhile = false;

    private BufferedReader inFromClient = null;
    private PrintWriter outToClient = null;


    //this arrays will temporarily hold playerIDwholes of players who are yet
    //to be assigned to any match
    private static int playerIDwholelist2[] = new int[2];
    private static int playerIDwholelist3[] = new int[3];
    private static int playerIDwholelist4[] = new int[4];

    public static int getP2() { return p2; }

    public static int getP3() {
        return p3;
    }

    public static int getP4() { return p4; }

    public BufferedReader getInFromClient() {
        return inFromClient;
    }

    public PrintWriter getOutToClient() {
        return outToClient;
    }

    public PlayerWorld(Socket connectionSocket, int playerIDwhole, int twoPlayers, int threePlayers, int fourPlayers)
    {

        this.sckt = connectionSocket;
        this.playerIDwhole = playerIDwhole;

        try
        {
            inFromClient = new BufferedReader(new InputStreamReader(sckt.getInputStream()));
            outToClient = new PrintWriter(sckt.getOutputStream());

        }
        catch (Exception e) {
            System.err.println("Problem in connecting with the server. Exiting main.");
            System.exit(1);
        }

        p2 = twoPlayers;
        p3 = threePlayers;
        p4 = fourPlayers;
        ///constructor ends
    }

    public void run()
    {
        MonopolyWorld mw;
        String str = new String();


        while (true) ///while loop for taking in players from server and passing them on to monopolyworld
        {

            //System.out.println("pw er run er while loop er shuru");
            try {
                System.out.println(" player "+playerIDwhole+" readline er age str ---"+str);
                str = inFromClient.readLine();
                System.out.println("   player "+playerIDwhole+" str hoilo "+str);

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException npe) {
                System.out.println("nulllptr");
            }


            /*if(str.contains("rolldice"))
            {
                System.out.println("rolldice in playerworld");
                try {
                    outToMW.write(str);
                    outToMW.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("DONE");
                continue;
            }
            else */if(str.equals("2") || str.equals("3") || str.equals("4"))
            {
                numPlayer = Integer.parseInt(str);

                System.out.println(numPlayer+" jon er match khelte chay");


                if(numPlayer == 2 && p2<2)
                {
                    p2 ++;
                    playerIDwholelist2[p2-1] = playerIDwhole;/*
                    outToClient.write(String.valueOf(p2));
                    outToClient.flush();*/

                }

                if(numPlayer == 3 && p3<3)
                {
                    p3 ++;
                    playerIDwholelist3[p3-1] = playerIDwhole;/*
                    outToClient.write(String.valueOf(p3));
                    outToClient.flush();*/

                }

                if(numPlayer == 4 && p4<4)
                {
                    p4 ++;
                    playerIDwholelist4[p4-1] = playerIDwhole;/*
                    outToClient.write(String.valueOf(p4));
                    outToClient.flush();*/

                }

//ফেব্রুয়ারি ৩ ২০১৯ বিকাল ৫ টা ২৮
            /*৩টা নুতুন আরে তে যেইসব প্লেয়ার কানেক্টেড হচ্ছে তাদের গ্লোবাল আইডী টা রেখে দিলাম।
            আরেগুলা এরপর মনোপলি ওয়ার্ল্ডে পাঠাইতে হবে যাতে বোঝা যায় এক ম্যাচে কোন কোণ প্লেয়ার খেলতেসে
            */

               //System.out.println(p2+p3+p4);

                if(numPlayer == 2 && p2 == 2)
                {
                    mwID++;

                    mw = new MonopolyWorld(2, mwID, playerIDwholelist2);
                    GameServer.mwList.add(mw);

                    ///loading shesh howar request
                    for(int idx=0;idx<playerIDwholelist2.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelist2[idx] - 1).outToClient.println("loading shesh");
                        GameServer.pwList.elementAt(playerIDwholelist2[idx] - 1).outToClient.flush();
                    }

                    ///loading shesh howar confirmation
                    for(int idx=0;idx<playerIDwholelist2.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelist2[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                /*outToClient.print("playerhoyegese");
                outToClient.flush();*/

                    Thread t = new Thread(mw);
                    t.start();

                    p2 = 0;
                    System.out.println("2 joner khela shuru");

                    for(int idx=0;idx<playerIDwholelist2.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelist2[idx] - 1).outToClient.println("enableKoroButtonczKhelaShuru"+String.valueOf(idx));
                        GameServer.pwList.elementAt(playerIDwholelist2[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelist2[idx] - 1).breakfromwhile = true;
                    }
                }
                if(numPlayer == 3 && p3 == 3)
                {
                    mwID++;

                    mw = new MonopolyWorld(3, mwID, playerIDwholelist3);
                    GameServer.mwList.add(mw);


                    ///loading shesh howar request
                    for(int idx=0;idx<playerIDwholelist3.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelist3[idx] - 1).outToClient.println("loading shesh");
                        GameServer.pwList.elementAt(playerIDwholelist3[idx] - 1).outToClient.flush();
                    }

                    ///loading shesh howar confirmation
                    for(int idx=0;idx<playerIDwholelist3.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelist3[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                /*outToClient.print("playerhoyegese");
                outToClient.flush();*/
                    Thread t = new Thread(mw);
                    t.start();

                    p3 = 0;
                    System.out.println("3 joner khela shuru");

                    for(int idx=0;idx<playerIDwholelist3.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelist3[idx] - 1).outToClient.println("enableKoroButtonczKhelaShuru"+String.valueOf(idx));
                        GameServer.pwList.elementAt(playerIDwholelist3[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelist3[idx] - 1).breakfromwhile = true;
                    }
                }
                if(numPlayer == 4 && p4 == 4)
                {
                    mwID++;

                    mw = new MonopolyWorld(4, mwID, playerIDwholelist4);
                    GameServer.mwList.add(mw);


                    ///loading shesh howar request
                    for(int idx=0;idx<playerIDwholelist4.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelist4[idx] - 1).outToClient.println("loading shesh");
                        GameServer.pwList.elementAt(playerIDwholelist4[idx] - 1).outToClient.flush();
                    }

                    ///loading shesh howar confirmation
                    for(int idx=0;idx<playerIDwholelist4.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelist4[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                /*outToClient.print("playerhoyegese");
                outToClient.flush();*/

                    Thread t = new Thread(mw);
                    t.start();

                    p4 = 0;
                    System.out.println("4 joner khela shuru");

                    for(int idx=0;idx<playerIDwholelist4.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelist4[idx] - 1).outToClient.println("enableKoroButtonczKhelaShuru"+String.valueOf(idx));
                        GameServer.pwList.elementAt(playerIDwholelist4[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelist4[idx] - 1).breakfromwhile = true;
                    }
                }
            }

          /*  else
            {
                outToMW.write("-----");
                outToMW.close();
            }
*/
            /*try {
                numPlayer = Integer.parseInt(inFromClient.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NullPointerException npe) {
                System.out.println("nulllptr");
            }*/
            //System.out.println("pw er run er while loop er shesh");

            while (!breakfromwhile)
            {

            }
            if(breakfromwhile)
            {
                System.out.println("i am player "+playerIDwhole+ " breaking from while");
                break;
            }
        }

    }

}
