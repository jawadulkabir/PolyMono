package sample.behindthescene;

public class Property extends Dolil
{
     private String color;
     private int noOfHouse, noOfHotel, rent1house, rent2houses, rent3houses, rent4houses, rentHotel, housePrice, hotelPrice;

    public Property(String name, int dolilID, int ownerID, int price, int rent, boolean isBought, boolean isMortgaged, String color, int noOfHouse, int noOfHotel, int rent1house, int rent2houses, int rent3houses, int rent4houses, int rentHotel, int housePrice, int hotelPrice) {

        super(name, dolilID, ownerID, price, rent, isBought, isMortgaged);

        this.color = color;
        this.noOfHouse= noOfHouse;
        this.noOfHotel = noOfHotel;
        this.rent1house = rent1house;
        this.rent2houses = rent2houses;
        this.rent3houses = rent3houses;
        this.rent4houses = rent4houses;
        this.rentHotel = rentHotel;
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
        //System.out.println("constructor in property");
    }

    public String getColor() { return color; }

    public int getNoOfHouse() { return noOfHouse; }

    public int getNoOfHotel() { return noOfHotel; }

    public int getRent1house() {
        return rent1house;
    }

    public int getRent2houses() {
        return rent2houses;
    }

    public int getRent3houses() {
        return rent3houses;
    }

    public int getRent4houses() {
        return rent4houses;
    }

    public int getRentHotel() {
        return rentHotel;
    }

    public int getHousePrice() { return housePrice; }

    public int getHotelPrice() { return hotelPrice; }

    public void setNoOfHouse(int noOfHouse) {
        this.noOfHouse = noOfHouse;
    }

    public void setNoOfHotel(int noOfHotel) {
        this.noOfHotel = noOfHotel;
    }
}
