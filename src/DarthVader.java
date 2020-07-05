
import java.awt.Point;


public class DarthVader extends Karakter{

    public DarthVader(String Ad, String Tur, Lokasyon DarthVaderKonum,String kapi,Point[] guzergah,int x, int y) {
        super(Ad, Tur, DarthVaderKonum, kapi,guzergah,x,y);
    }
/*
    public DarthVader(String Ad, String Tur, Lokasyon karakterKonum, String kapi) {
        super(Ad, Tur, karakterKonum, kapi);
    }*/

    @Override
    public Point[] EnKısaYolBul(int[][] izgara, Point kaynak, Point hedef) {
        return super.EnKısaYolBul(izgara, kaynak, hedef); //To change body of generated methods, choose Tools | Templates.
    }
  
    
}
