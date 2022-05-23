package sample.behindthescene;

public class Dolil
{
     private String name;
     private int dolilID, ownerID , price, rent;
     private boolean isBought, isMortgaged ;

    public Dolil() {
    }

    public Dolil(String name, int dolilID, int ownerID, int price, int rent, boolean isBought, boolean isMortgaged) {
        this.name = name;
        this.dolilID = dolilID;
        this.ownerID = ownerID;
        this.price = price;
        this.rent = rent;
        this.isBought = isBought;
        this.isMortgaged = isMortgaged;
        //System.out.println("constructor in dolil");
    }

    public String getName() {
        return name;
    }

    public int getDolilID() {
        return dolilID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public boolean isBought() {
        return isBought;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDolilID(int dolilID) {
        this.dolilID = dolilID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }
}