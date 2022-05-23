package sample.behindthescene;

public class Station extends Dolil {
    public Station(String name, int dolilID, int ownerID, int price, int rent, boolean isBought, boolean isMortgaged) {
        super(name, dolilID, ownerID, price, rent, isBought, isMortgaged);
    }

    public static void rentControl(Station stations[], int i)
    {
        //if(super.name)
        int c = 0, len = stations.length;
        for (int st = 0; st< len; st ++)
        {
            if (stations[st].getOwnerID() == (i + 1))
                c++;
        }
        if(c>1)
        {
            for ( int st = 0; st< len; st ++)
            {
                if(stations[st].getOwnerID() == (i+1))
                {
                    if(c==2) stations[st].setRent(50);
                    else if(c==3) stations[st].setRent(100);
                    else if(c==4) stations[st].setRent(200);
                }
            }
        }
    }

}
