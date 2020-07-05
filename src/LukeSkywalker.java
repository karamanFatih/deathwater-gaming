

public class LukeSkywalker extends Karakter{
    
    int can ;

    public LukeSkywalker(String Ad, String Tur, Lokasyon LukeSkywalkerKonum) {
        super(Ad, Tur, LukeSkywalkerKonum);
        this.can = 3 ;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }
    
}


