




import java.awt.Point;


public class KyloRen extends Karakter{

    public KyloRen(String Ad, String Tur, Lokasyon KyloRenKonum,String kapi,Point[] guzergah,int x, int y) {
        super(Ad, Tur, KyloRenKonum,kapi, guzergah,x,y);
    }
/*
    public KyloRen(String Ad, String Tur, Lokasyon karakterKonum, String kapi) {
        super(Ad, Tur, karakterKonum, kapi);
    }
*/
    @Override
    public Point[] EnKısaYolBul(int[][] izgara, Point kaynak, Point hedef) {
        return super.EnKısaYolBul(izgara, kaynak, hedef); //To change body of generated methods, choose Tools | Templates.
    }
   

    
    
}



