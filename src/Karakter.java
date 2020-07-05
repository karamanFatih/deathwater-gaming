

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Karakter {

    String Ad;
    String Tur;

    Lokasyon karakterKonum;
    
    String kapi;
    Point[] guzergah;
    int x,y;



    public Karakter(String Ad, String Tur, Lokasyon karakterKonum,String kapi,Point[] guzergah,int x, int y) {
        this.Ad = Ad;
        this.Tur = Tur;
        this.karakterKonum = karakterKonum;
        this.kapi = kapi;
        this.guzergah = guzergah;
        this.x = x;
        this.y = y;

    }
    public Karakter(String Ad, String Tur, Lokasyon karakterKonum,String kapi) {
        this.Ad = Ad;
        this.Tur = Tur;
        this.karakterKonum = karakterKonum;
        this.kapi = kapi;
    }

    public Karakter(String Ad, String Tur, Lokasyon karakterKonum) {
        this.Ad = Ad;
        this.Tur = Tur;
        this.karakterKonum = karakterKonum;
    }

 

    Karakter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    public String getAd() {
        return Ad;
    }

    public void setAd(String Ad) {
        this.Ad = Ad;
    }

    public String getTur() {
        return Tur;
    }

    public void setTur(String Tur) {
        this.Tur = Tur;
    }

    public Lokasyon getKarakterKonum() {
        return karakterKonum;
    }

    public void setKarakterKonum(Lokasyon karakterKonum) {
        this.karakterKonum = karakterKonum;
    }
    public Point[] getGuzergah() {
        return guzergah;
    }

    public void setGuzergah(Point[] guzergah) {
        this.guzergah = guzergah;
    }

    

    public String getKapi() {
        return kapi;
    }

    public void setKapi(String kapi) {
        this.kapi = kapi;
    }

    private static final boolean hata = false;

    public Point[] EnKısaYolBul(int[][] izgara, Point kaynak, Point hedef) {
        if (izgaraAsildiİse(izgara, hedef)) {
            return null;
        }
        if (izgaraAsildiİse(izgara, hedef)) {
            return null;
        }
        if (engelle(izgara, hedef)) {
            return null;
        }
        if (engelle(izgara, hedef)) {
            return null;
        }
        LinkedList<Point> queue1 = new LinkedList<>();
        LinkedList<Point> queue2 = new LinkedList<>();

        queue1.add(kaynak);
        izgara[kaynak.y][kaynak.x] = -1;
        int adım = 2;
        while (!queue1.isEmpty()) {
            if(queue1.size() >= izgara.length * izgara[0].length){
                throw new Error("izgara(harita) limiti asildi !");
            }
            for (Point nokta : queue1) {
                if (nokta.x == hedef.x && nokta.y == hedef.y) {
                    Point[] optimalPath = new Point[adım - 1];
                    cozumle(izgara, nokta.x, nokta.y, adım - 1, optimalPath);
                    izgaraYenile(izgara);
                    return optimalPath;
                }
                LinkedList<Point> finalQueue = queue2;
                int sonAdım = adım;
                etrafıKesfet(izgara, nokta, (x, y) -> {
                    if (engelle(izgara, x, y)) {
                        return;
                    }
                    Point e = new Point(x, y);

                    finalQueue.add(e);
                    izgara[e.y][e.x] = -sonAdım;
                });
            }

            if (hata) {
                izgaraYazdir(izgara);
            }

            queue1 = queue2;
            queue2 = new LinkedList<>();
            adım++;
        }
        izgaraYenile(izgara);
        return null;
    }

    private void izgaraYenile(int[][] izgara) {
        for (int y = 0; y < izgara.length; y++) {
            for (int x = 0; x < izgara[0].length; x++) {
                if (izgara[y][x] < 0) {
                    izgara[y][x] = 0;
                }
            }
        }
    }

    private boolean engelle(int[][] izgara, Point p) {
        return engelle(izgara, p.x, p.y);
    }

    private boolean engelle(int[][] izgara, int x, int y) {
        int i = izgara[y][x];
        return i < 0 || i == 1;
    }

    private void izgaraYazdir(int[][] izgara) {
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0, izgaraUzunluk = izgara.length; i < izgaraUzunluk; i++) {
            int[] izgaray = izgara[i];
            for (int x = 0; x < izgara[0].length; x++) {
                System.out.print(izgaray[x] + "\t");
            }
            System.out.println();
        }
        System.out.println("****************************************");
    }

    private void cozumle(int[][] izgara, int x, int y, int adımSay, Point[] enkısayol) {
        if (izgaraAsildiİse(izgara, x, y) || izgara[y][x] == 0) {
            return;
        }

        if ( -adımSay != izgara[y][x]) {
            return;
        }

        Point p = new Point(x, y);
        enkısayol[adımSay - 1] = p;
        etrafıKesfet(izgara, p, (x1, y1) -> cozumle(izgara, x1, y1, adımSay - 1, enkısayol));
    }

    private void etrafıKesfet(int[][] izgara, Point p, Callback callback) {
        //callback.incele(map, p.x + 1, p.y + 1);
        //callback.incele(map, p.x - 1, p.y + 1);
        //callback.incele(map, p.x - 1, p.y - 1);
        //callback.incele(map, p.x + 1, p.y - 1);
        callback.incele(izgara, p.x + 1, p.y);
        callback.incele(izgara, p.x - 1, p.y);
        callback.incele(izgara, p.x, p.y + 1);
        callback.incele(izgara, p.x, p.y - 1);
    }

    private static boolean izgaraAsildiİse(int[][] map, Point p) {
        return izgaraAsildiİse(map, p.x, p.y);
    }

    private static boolean izgaraAsildiİse(int[][] map, int x, int y) {
        if (x < 0 || y < 0) {
            return true;
        }
        return map.length <= y || map[0].length <= x;
    }

    private interface Callback {
        default void incele(int[][] izgara, int x, int y) {
            if (izgaraAsildiİse(izgara, x, y)) {
                return;
            }
            incelendi(x, y);
        }

        void incelendi(int x, int y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}


