

import java.awt.Point;


public class MasterYoda extends  Karakter{
    
    private double can;

    public MasterYoda(String Ad, String Tur, Lokasyon MasterYodaKonum) {
        super(Ad, Tur, MasterYodaKonum);
        this.can = 3;
    }

    /**
     * @return the can
     */
    public double getCan() {
        return can;
    }

    /**
     * @param can the can to set
     */
    public void setCan(double can) {
        this.can = can;
    }

    
    
}







