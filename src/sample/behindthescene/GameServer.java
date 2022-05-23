package sample.behindthescene;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

public class GameServer {
    ServerSocket welcomeSocket = null ;
    Socket connectionSocket = null ;
    static int PlayerHandlerCount, plID, matchID;
    static Vector<PlayerWorld> pwList;
    static Vector<MonopolyWorld> mwList;

    Scanner sc =null;
    static int twoPlayers , threePlayers , fourPlayers ;


    public GameServer()
    {
        twoPlayers =0; threePlayers = 0; fourPlayers = 0;
        //PlayerHandlerCount = 0;
        plID = 1;
        pwList = new Vector<>();
        mwList = new Vector<>();
        System.out.println("waiting to take in players...");
        try {
            welcomeSocket = new ServerSocket(61689);
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
        }

        while (true )
        {
            try
            {
                connectionSocket = welcomeSocket.accept();
                System.out.printf("Player %d has joined\n",plID);
            }
            catch (IOException ioe)
            {
                System.out.println(ioe);
            }

            twoPlayers = PlayerWorld.getP2();
            threePlayers = PlayerWorld.getP3();
            fourPlayers = PlayerWorld.getP4();

            PlayerWorld pw = new PlayerWorld(connectionSocket, plID, twoPlayers, threePlayers, fourPlayers );

            pwList.add(pw);
            Thread t = new Thread(pw);
            t.start();
//            PlayerHandlerCount++;

            plID++;
            //howmanyplayers--;
            //get player values

        }

    }

    public static void main(String argv[])
    {
        new GameServer();
    }
}
