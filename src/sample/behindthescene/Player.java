package sample.behindthescene;

import java.util.Vector;

public class Player {
    private int playerIDmatch;
    private String name;
    private boolean inJail;
    private int position;
    private int cash;
    private int money;
    private Vector<Integer> dolils;


    public Player(int playerIDmatch, String name, boolean inJail, int position, int cash, int money) {
        this.playerIDmatch = playerIDmatch;
        this.name = name;
        this.inJail = inJail;
        this.position = position;
        this.cash = cash;
        this.money = money;
        dolils = new Vector<>();
    }

    //getters
    public int getplayerIDmatch() {
        return playerIDmatch;
    }

    public String getName() {
        return name;
    }

    public boolean isInJail() {
        return inJail;
    }

    public int getPosition() {
        return position;
    }

    public int getCash() {
        return cash;
    }

    public int getMoney() {
        return money;
    }

    public Vector<Integer> getDolils() {
        return dolils;
    }

    ///setters
    public void setplayerIDmatch(int playerIDmatch) {
        this.playerIDmatch = playerIDmatch;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
