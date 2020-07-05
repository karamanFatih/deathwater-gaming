
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;

public class Test implements KeyListener {

    private static String kolUzunluk;
    Lokasyon oyuncuLokasyon;
    private boolean oyunsecim = false;

    private List<Karakter> kotuOyuncuArrayList = new ArrayList<Karakter>();
    private final List<Karakter> kotuOyuncuBaslangicKonumArrayList = new ArrayList<Karakter>();
    private List<LukeSkywalker> lukeOyuncuArrayList = new ArrayList<LukeSkywalker>();
    private List<MasterYoda> masOyuncuArrayList = new ArrayList<MasterYoda>();
    private MasterYoda masteryoda;
    private LukeSkywalker lukeskywalker;

    private Karakter iyi;

    Lokasyon kotuOyuncuLokasyon;
    String kotuName;

    Stormtrooper stormtrooper;
    DarthVader darthvader;
    KyloRen kyloren;

    Point[] guzergah; //= new Point[100];

    public int[][] izgara = new int[11][14];
    public int[][] DartVaderIzgara = new int[11][14];

    int q = 0, w = 0, e = 0;
    String ad;
    int StormtrooperSay = 0;

    Image image, image2, imageS, imageK, imageD;
    private JFrame frame = new JFrame("proje 1");
    private static Timer myTimer = new Timer();
    private static JButton[][] butonlar = new JButton[11][14];
    private Container grid = new Container();

    //String oyuncu = JOptionPane.showInputDialog("Oyuncu seçiniz");
    public Test() throws FileNotFoundException, IOException {
        int secim = 0;
        String tempDeger = "";
        String[] options = {"Luke Skywalker", "Master Yoda",};

        secim = JOptionPane.showOptionDialog(null, "Oyuncu seciniz", "Oyuncu secimi", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        setOyunsecim(true);
        frame.setSize(610, 550);
        frame.setLayout(new BorderLayout());
        //frame.add(reset, BorderLayout.NORTH);
        //reset.addActionListener(this);
        grid.setLayout(new GridLayout(0, 14));
        File myFile = new File("C:\\Users\\sams\\Desktop\\proje_1\\Harita.txt");
        FileReader fileReader = new FileReader(myFile);
        String line;

        BufferedReader br = new BufferedReader(fileReader);
        String[] birSifirlar = new String[11];
        String[] birSifirlar1 = new String[11];
        int count = 0;
        int a = 0;
        char[] charDizi2 = new char[154];
        while ((line = br.readLine()) != null) {
            char[] charDizi = line.toCharArray();
            //   char [] charDizi2 =new  char[15];

            System.out.println(line);
            if (charDizi.length > 0) {
                if ('0' != charDizi[0] && '1' != charDizi[0]) {
                    tempDeger += line;
                }

                if ('0' == charDizi[0] || '1' == charDizi[0]) {

                    String[] diziString = line.split("\t");

                    for (int i = 0; i < diziString.length; i++) {
                        charDizi2[i + (a * 14)] = diziString[i].toCharArray()[0];

                    }
                    a++;
                    for (int i = 0; i < 11; i++) {
                        if (i == 1) {

                            break;
                        }
                        for (int j = 0; j < 14; j++) {

                            butonlar[count][j] = new JButton();

                            butonlar[count][j].addKeyListener(this);
                            butonlar[count][j].setName(Integer.toString(i) + Integer.toString(j));//buraya sonra bak
                            StringBuffer str = new StringBuffer("");
                            str.append(charDizi2[j + (14 * count)]);
                            butonlar[count][j].setText(str.toString());

                            grid.add(butonlar[count][j]);
                            if (butonlar[count][j].getText().equals("0")) {
                                butonlar[count][j].setBackground(Color.white);
                                izgara[count][j] = 1;
                                DartVaderIzgara[count][j] = 0;

                            }
                            if (butonlar[count][j].getText().equals("1")) {
                                izgara[count][j] = 0;
                                DartVaderIzgara[count][j] = 0;
                                if (count == 5 && j == 0) {
                                    butonlar[count][j].setText("A");
                                    butonlar[count][j].setBackground(Color.BLUE);
                                    butonlar[count][j].setForeground(Color.white);
                                } else if (count == 0 && j == 4) {
                                    butonlar[count][j].setText("B");
                                    butonlar[count][j].setBackground(Color.BLUE);
                                    butonlar[count][j].setForeground(Color.white);
                                } else if (count == 0 && j == 12) {
                                    butonlar[count][j].setText("C");
                                    butonlar[count][j].setBackground(Color.BLUE);
                                    butonlar[count][j].setForeground(Color.white);
                                } else if (count == 5 && j == 13) {
                                    butonlar[count][j].setText("D");
                                    butonlar[count][j].setBackground(Color.BLUE);
                                    butonlar[count][j].setForeground(Color.white);
                                } else if (count == 10 && j == 4) {
                                    butonlar[count][j].setText("E");
                                    butonlar[count][j].setBackground(Color.BLUE);
                                    butonlar[count][j].setForeground(Color.white);
                                } else if (count == 5 && j == 6) {
                                    butonlar[count][j].setBackground(Color.yellow);

                                } else {

                                    butonlar[count][j].setBackground(Color.cyan);
                                }

                            }

                        }

                        count++;
                    }

                    frame.add(grid, BorderLayout.CENTER);

                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);

                }

            }
        }

        if (secim == 0) {
            image = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Luke_Skywalker.jpg").getImage();
            butonlar[5][6].setIcon(new ImageIcon(image));
            oyuncuLokasyon = new Lokasyon(5, 6);
            lukeskywalker = new LukeSkywalker("LukeSkywalker", "iyi", oyuncuLokasyon);
            lukeOyuncuArrayList.add(lukeskywalker);

        } else if (secim == 1) {
            image = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Master_Yoda.jpg").getImage();
            butonlar[5][6].setIcon(new ImageIcon(image));
            oyuncuLokasyon = new Lokasyon(5, 6);
            masteryoda = new MasterYoda("MasterYoda", "iyi", oyuncuLokasyon);
            masOyuncuArrayList.add(masteryoda);

        }
        String sentence = tempDeger;
        String ayraclar = ":,";
        StringTokenizer tokenizer2 = new StringTokenizer(sentence, ayraclar);
        //int i=0;

        while (tokenizer2.hasMoreElements()) {
            String token = (String) tokenizer2.nextElement();
            kotuOyuncuLokasyon = new Lokasyon(0, 0);
            if ("Stormtrooper".equals(token)) {

                image2 = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();
                kotuName = "Stormtrooper";
                StormtrooperSay++;

            }
            if ("DarthVader".equals(token)) {

                image2 = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();
                kotuName = "DarthVader";

            }
            if ("KyloRen".equals(token)) {

                image2 = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();
                kotuName = "KyloRen";

            }

            if ("AKarakter".equals(token) || "A".equals(token)) {

                butonlar[5][0].setIcon(new ImageIcon(image2));

                if (kotuName.equals("Stormtrooper") && StormtrooperSay != 0) {
                    StormtrooperSay--;
                    kotuOyuncuLokasyon = new Lokasyon(5, 0);
                    ad = kotuName;

                    stormtrooper = new Stormtrooper(ad, "kotu", kotuOyuncuLokasyon, "A", null, 5, 0);
                    guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(0, 5), new Point(6, 5));
                    stormtrooper.setGuzergah(guzergah);

                    for (Point point : guzergah) {
                        if ((point.y == 5 && point.x == 0) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }
                }
                kotuOyuncuArrayList.add(stormtrooper);
                kotuOyuncuBaslangicKonumArrayList.add(stormtrooper);
                if (kotuName.equals("DarthVader")) {
                    kotuOyuncuLokasyon = new Lokasyon(5, 0);
                    ad = kotuName;

                    darthvader = new DarthVader(ad, "kotu", kotuOyuncuLokasyon, "A", null, 5, 0);
                    guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(darthvader.karakterKonum.getY(), darthvader.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    darthvader.setGuzergah(guzergah);
                    kotuOyuncuArrayList.add(darthvader);
                    kotuOyuncuBaslangicKonumArrayList.add(darthvader);

                    for (Point point : guzergah) {
                        if ((point.y == 5 && point.x == 0) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }

                }
                if (kotuName.equals("KyloRen")) {
                    kotuOyuncuLokasyon = new Lokasyon(5, 0);
                    ad = kotuName;

                    kyloren = new KyloRen(ad, "kotu", kotuOyuncuLokasyon, "A", null, 5, 0);
                    guzergah = kyloren.EnKısaYolBul(izgara, new Point(kyloren.karakterKonum.getY(), kyloren.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    kyloren.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(kyloren);
                    kotuOyuncuBaslangicKonumArrayList.add(kyloren);

                    for (Point point : guzergah) {
                        if ((point.y == 5 && point.x == 0) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }

                }

                //System.out.println(kotulerDizi);
            }
            if ("BKarakter".equals(token) || "B".equals(token)) {

                butonlar[0][4].setIcon(new ImageIcon(image2));

                if (kotuName.equals("Stormtrooper")) {
                    kotuOyuncuLokasyon = new Lokasyon(0, 4);
                    ad = kotuName;

                    stormtrooper = new Stormtrooper(ad, "kotu", kotuOyuncuLokasyon, "B", null, 0, 4);
                    guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(stormtrooper.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    stormtrooper.setGuzergah(guzergah);
                    kotuOyuncuArrayList.add(stormtrooper);
                    kotuOyuncuBaslangicKonumArrayList.add(stormtrooper);

                    for (Point point : guzergah) {
                        if ((point.y == 0 && point.x == 4) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }

                }
                if (kotuName.equals("DarthVader")) {
                    kotuOyuncuLokasyon = new Lokasyon(0, 4);
                    ad = kotuName;

                    darthvader = new DarthVader(ad, "kotu", kotuOyuncuLokasyon, "B", null, 0, 4);
                    guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(darthvader.karakterKonum.getY(), darthvader.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    darthvader.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(darthvader);
                    kotuOyuncuBaslangicKonumArrayList.add(darthvader);

                    for (Point point : guzergah) {
                        if ((point.y == 0 && point.x == 4) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }
                }
                if (kotuName.equals("KyloRen")) {
                    kotuOyuncuLokasyon = new Lokasyon(0, 4);
                    ad = kotuName;

                    kyloren = new KyloRen(ad, "kotu", kotuOyuncuLokasyon, "B", null, 0, 4);
                    guzergah = kyloren.EnKısaYolBul(izgara, new Point(kyloren.karakterKonum.getY(), kyloren.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    kyloren.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(kyloren);
                    kotuOyuncuBaslangicKonumArrayList.add(kyloren);
                    for (Point point : guzergah) {
                        if ((point.y == 0 && point.x == 4) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }
                }

            }
            if ("CKarakter".equals(token) || "C".equals(token)) {

                butonlar[0][12].setIcon(new ImageIcon(image2));

                if (kotuName.equals("Stormtrooper")) {
                    kotuOyuncuLokasyon = new Lokasyon(0, 12);
                    ad = kotuName;

                    stormtrooper = new Stormtrooper(ad, "kotu", kotuOyuncuLokasyon, "C", null, 0, 12);
                    guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(stormtrooper.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    stormtrooper.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(stormtrooper);
                    kotuOyuncuBaslangicKonumArrayList.add(stormtrooper);

                    for (Point point : guzergah) {
                        if ((point.y == 0 && point.x == 12) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }

                }
                if (kotuName.equals("DarthVader")) {
                    kotuOyuncuLokasyon = new Lokasyon(0, 12);
                    ad = kotuName;

                    darthvader = new DarthVader(ad, "kotu", kotuOyuncuLokasyon, "C", null, 0, 12);
                    guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(darthvader.karakterKonum.getY(), darthvader.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    darthvader.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(darthvader);
                    kotuOyuncuBaslangicKonumArrayList.add(darthvader);

                    for (Point point : guzergah) {
                        if ((point.y == 0 && point.x == 12) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }
                }
                if (kotuName.equals("KyloRen")) {
                    kotuOyuncuLokasyon = new Lokasyon(0, 12);
                    ad = kotuName;

                    kyloren = new KyloRen(ad, "kotu", kotuOyuncuLokasyon, "C", null, 0, 12);
                    guzergah = kyloren.EnKısaYolBul(izgara, new Point(kyloren.karakterKonum.getY(), kyloren.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    kyloren.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(kyloren);
                    kotuOyuncuBaslangicKonumArrayList.add(kyloren);
                    for (Point point : guzergah) {
                        if ((point.y == 0 && point.x == 12) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }

                }

            }
            if ("DKarakter".equals(token) || "D".equals(token)) {

                butonlar[5][13].setIcon(new ImageIcon(image2));

                if (kotuName.equals("Stormtrooper")) {
                    kotuOyuncuLokasyon = new Lokasyon(5, 13);
                    ad = kotuName;

                    stormtrooper = new Stormtrooper(ad, "kotu", kotuOyuncuLokasyon, "D", null, 5, 13);
                    guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(stormtrooper.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    stormtrooper.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(stormtrooper);
                    kotuOyuncuBaslangicKonumArrayList.add(stormtrooper);
                    for (Point point : guzergah) {
                        if ((point.y == 5 && point.x == 13) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }
                }
                if (kotuName.equals("DarthVader")) {
                    kotuOyuncuLokasyon = new Lokasyon(5, 13);
                    ad = kotuName;

                    darthvader = new DarthVader(ad, "kotu", kotuOyuncuLokasyon, "D", null, 5, 13);
                    guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(darthvader.karakterKonum.getY(), darthvader.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    darthvader.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(darthvader);
                    kotuOyuncuBaslangicKonumArrayList.add(darthvader);
                    for (Point point : guzergah) {
                        if ((point.y == 5 && point.x == 13) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }
                }
                if (kotuName.equals("KyloRen")) {
                    kotuOyuncuLokasyon = new Lokasyon(5, 13);
                    ad = kotuName;

                    kyloren = new KyloRen(ad, "kotu", kotuOyuncuLokasyon, "D", null, 5, 13);
                    guzergah = kyloren.EnKısaYolBul(izgara, new Point(kyloren.karakterKonum.getY(), kyloren.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    kyloren.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(kyloren);
                    kotuOyuncuBaslangicKonumArrayList.add(kyloren);
                    for (Point point : guzergah) {
                        if ((point.y == 5 && point.x == 13) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }
                }

            }
            if ("EKarakter".equals(token) || "E".equals(token)) {

                butonlar[10][4].setIcon(new ImageIcon(image2));

                if (kotuName.equals("Stormtrooper")) {
                    kotuOyuncuLokasyon = new Lokasyon(10, 4);
                    ad = kotuName;

                    stormtrooper = new Stormtrooper(ad, "kotu", kotuOyuncuLokasyon, "E", null, 10, 4);
                    guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(stormtrooper.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    stormtrooper.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(stormtrooper);
                    kotuOyuncuBaslangicKonumArrayList.add(stormtrooper);

                    for (Point point : guzergah) {
                        if ((point.y == 10 && point.x == 4) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }
                }
                if (kotuName.equals("DarthVader")) {
                    kotuOyuncuLokasyon = new Lokasyon(10, 4);
                    ad = kotuName;

                    darthvader = new DarthVader(ad, "kotu", kotuOyuncuLokasyon, "E", null, 10, 4);
                    guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(darthvader.karakterKonum.getY(), darthvader.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    darthvader.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(darthvader);
                    kotuOyuncuBaslangicKonumArrayList.add(darthvader);

                    for (Point point : guzergah) {
                        if ((point.y == 10 && point.x == 4) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }
                }
                if (kotuName.equals("KyloRen")) {
                    kotuOyuncuLokasyon = new Lokasyon(10, 4);
                    ad = kotuName;

                    kyloren = new KyloRen(ad, "kotu", kotuOyuncuLokasyon, "E", null, 10, 4);
                    guzergah = kyloren.EnKısaYolBul(izgara, new Point(kyloren.karakterKonum.getY(), kyloren.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                    kyloren.setGuzergah(guzergah);

                    kotuOyuncuArrayList.add(kyloren);
                    kotuOyuncuBaslangicKonumArrayList.add(kyloren);
                    for (Point point : guzergah) {
                        if ((point.y == 10 && point.x == 4) || (point.y == 5 && point.x == 6)) {

                        } else {
                            butonlar[point.y][point.x].setBackground(Color.red);
                        }

                    }

                }

            }

        }

    }

    public static void main(String[] args) throws IOException, AWTException {
        new Test();
        String[] diziKotu = new String[20];
        Robot robot = new Robot();
        robot.mouseMove(300, 300);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

    }

    @Override
    public void keyTyped(KeyEvent e) {// tuşa basıldığında çalışır

    }

    @Override
    public void keyPressed(KeyEvent e) {// tuşa basıldığında çalışır

        if (oyuncuLokasyon.getX() == 9 && oyuncuLokasyon.getY() == 13) {

            String[] options = {"OK",};
            int sec = JOptionPane.showOptionDialog(null, "MADALYA SENİNDİR KAHRAMAN :)", "    TEBRİKLER", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 14; j++) {
                if (butonlar[i][j].getText().equals("1")) {
                    butonlar[i][j].setBackground(Color.CYAN);
                }
                if (butonlar[i][j].getText().equals("0")) {
                    butonlar[i][j].setBackground(Color.WHITE);
                }
                if (butonlar[i][j].getName().equals("A") || butonlar[i][j].getName().equals("B") || butonlar[i][j].getName().equals("C") || butonlar[i][j].getName().equals("D") || butonlar[i][j].getName().equals("E")) {
                    butonlar[i][j].setBackground(Color.BLUE);
                }

            }
        }

        int i = 5;
        int j = 6;
        if (e.getKeyCode() == 39) {//sag
            if (this.getButonlar()[oyuncuLokasyon.getX()][oyuncuLokasyon.getY() + 1].getText().equals("0")) {

                for (Karakter karekter : kotuOyuncuArrayList) {
                    if (karekter.getAd().equals("Stormtrooper")) {
                        guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                    if (karekter.getAd().equals("KyloRen")) {
                        guzergah = kyloren.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                    if (karekter.getAd().equals("DarthVader")) {
                        guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                }

            } else {
                this.getButonlar()[oyuncuLokasyon.getX()][oyuncuLokasyon.getY() + 1].setIcon(new ImageIcon(image));
                if (!butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].getText().equals("A")) {
                    butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].setBackground(Color.cyan);
                }
                butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].setIcon(null);
                oyuncuLokasyon.setY(oyuncuLokasyon.getY() + 1);

                for (Karakter karekter : kotuOyuncuArrayList) {
                    if (karekter != null) {
                        if ("Stormtrooper".equals(karekter.getAd())) {
                            guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                        if (karekter.getAd().equals("KyloRen")) {
                            guzergah = kyloren.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                        if (karekter.getAd().equals("DarthVader")) {
                            guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                    }
                }

            }

            for (Karakter karekter : kotuOyuncuArrayList) {
                if (karekter != null) {

                    if (karekter.getKapi().equals("E")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("D")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("C")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("B")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("A")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                }
            }

        }
        if (e.getKeyCode() == 37) {//sol

            if (this.getButonlar()[oyuncuLokasyon.getX()][oyuncuLokasyon.getY() - 1].getText().equals("0")) {

                for (Karakter karekter : kotuOyuncuArrayList) {
                    if (karekter.getAd().equals("Stormtrooper")) {
                        guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                    if (karekter.getAd().equals("KyloRen")) {
                        guzergah = kyloren.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                    if (karekter.getAd().equals("DarthVader")) {
                        guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                }

            } else {
                this.getButonlar()[oyuncuLokasyon.getX()][oyuncuLokasyon.getY() - 1].setIcon(new ImageIcon(image));
                if (!butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].getText().equals("D")) {
                    butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].setBackground(Color.cyan);
                }
                butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].setIcon(null);
                oyuncuLokasyon.setY(oyuncuLokasyon.getY() - 1);

                for (Karakter karekter : kotuOyuncuArrayList) {
                    if (karekter != null) {
                        if ("Stormtrooper".equals(karekter.getAd())) {
                            guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                        if (karekter.getAd().equals("KyloRen")) {
                            guzergah = kyloren.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                        if (karekter.getAd().equals("DarthVader")) {
                            guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                    }
                }
            }

            for (Karakter karekter : kotuOyuncuArrayList) {
                if (karekter != null) {

                    if (karekter.getKapi().equals("E")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("D")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("C")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("B")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("A")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                }
            }

        }
        if (e.getKeyCode() == 38) {//yukarı

            if (this.getButonlar()[oyuncuLokasyon.getX() - 1][oyuncuLokasyon.getY()].getText().equals("0")) {

                for (Karakter karekter : kotuOyuncuArrayList) {
                    if (karekter.getAd().equals("Stormtrooper")) {
                        guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                    if (karekter.getAd().equals("KyloRen")) {
                        guzergah = kyloren.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                    if (karekter.getAd().equals("DarthVader")) {
                        guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                }

            } else {
                this.getButonlar()[oyuncuLokasyon.getX() - 1][oyuncuLokasyon.getY()].setIcon(new ImageIcon(image));
                if (!butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].getText().equals("E")) {
                    butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].setBackground(Color.cyan);
                }
                butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].setIcon(null);
                oyuncuLokasyon.setX(oyuncuLokasyon.getX() - 1);

                for (Karakter karekter : kotuOyuncuArrayList) {
                    if (karekter != null) {
                        if ("Stormtrooper".equals(karekter.getAd())) {
                            guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                        if (karekter.getAd().equals("KyloRen")) {
                            guzergah = kyloren.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                        if (karekter.getAd().equals("DarthVader")) {
                            guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                    }
                }

            }

            for (Karakter karekter : kotuOyuncuArrayList) {
                if (karekter != null) {

                    if (karekter.getKapi().equals("E")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("D")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("C")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("B")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("A")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                }
            }

        }
        if (e.getKeyCode() == 40) {//aşagı
            if (this.getButonlar()[oyuncuLokasyon.getX() + 1][oyuncuLokasyon.getY()].getText().equals("0")) {
                for (Karakter karekter : kotuOyuncuArrayList) {
                    if (karekter.getAd().equals("Stormtrooper")) {
                        guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                    if (karekter.getAd().equals("KyloRen")) {
                        guzergah = kyloren.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                    if (karekter.getAd().equals("DarthVader")) {
                        guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(karekter.karakterKonum.getY(), stormtrooper.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                        karekter.setGuzergah(guzergah);
                    }
                }

            } else {
                this.getButonlar()[oyuncuLokasyon.getX() + 1][oyuncuLokasyon.getY()].setIcon(new ImageIcon(image));
                if (!butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].getText().equals("B")
                        && !butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].getText().equals("C")) {
                    butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].setBackground(Color.cyan);
                }
                butonlar[oyuncuLokasyon.getX()][oyuncuLokasyon.getY()].setIcon(null);
                oyuncuLokasyon.setX(oyuncuLokasyon.getX() + 1);
                for (Karakter karekter : kotuOyuncuArrayList) {
                    if (karekter != null) {
                        if ("Stormtrooper".equals(karekter.getAd())) {
                            guzergah = stormtrooper.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                        if (karekter.getAd().equals("KyloRen")) {
                            guzergah = kyloren.EnKısaYolBul(izgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                        if (karekter.getAd().equals("DarthVader")) {
                            guzergah = darthvader.EnKısaYolBul(DartVaderIzgara, new Point(karekter.karakterKonum.getY(), karekter.karakterKonum.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX()));
                            karekter.setGuzergah(guzergah);
                        }
                    }
                }

            }

            for (Karakter karekter : kotuOyuncuArrayList) {
                if (karekter != null) {

                    if (karekter.getKapi().equals("E")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("D")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("C")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("B")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                    if (karekter.getKapi().equals("A")) {

                        if (karekter.getAd().equals("DarthVader")) {

                            int count = 0;
                            imageD = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageD));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(DartVaderIzgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("KyloRen")) {

                            int count = 0;
                            imageK = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 2) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageK));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                        if (karekter.getAd().equals("Stormtrooper")) {

                            int count = 0;
                            imageS = new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg").getImage();

                            Point[] temp = karekter.getGuzergah();

                            for (Point point : karekter.getGuzergah()) {

                                if (count == 0) {
                                    butonlar[point.y][point.x].setIcon(null);
                                }
                                if (count == 1) {
                                    butonlar[point.y][point.x].setIcon(new ImageIcon(imageS));
                                    kotuOyuncuLokasyon = new Lokasyon(point.y, point.x);
                                    karekter.setKarakterKonum(kotuOyuncuLokasyon);
                                    karekter.setGuzergah(karekter.EnKısaYolBul(izgara, new Point(point.x, point.y), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                                }
                                if (point.y == oyuncuLokasyon.getX() && point.x == oyuncuLokasyon.getY()) {

                                } else if (count > 1) {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }
                                count++;

                                //System.out.println(point.y + ", " + point.x);
                            }

                        }

                    }

                }
            }
        }

        for (Karakter karekter : kotuOyuncuArrayList) {

            if (karekter.getKarakterKonum().getX() == oyuncuLokasyon.getX() && karekter.getKarakterKonum().getY() == oyuncuLokasyon.getY()) {

                for (MasterYoda m : masOyuncuArrayList) {
                    m.setCan(m.getCan() - (0.5));
                    if (m.getCan() == 0.00) {
                        String[] options = {"Oyunu Bitir"};
                        String can = " \n   GAME OVER !";
                        int sec12 = JOptionPane.showOptionDialog(null, "   GAME OVER !", " CANIN KALMADI", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                        if (sec12 == 0) {
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.dispose();
                            System.exit(sec12);
                        }
                        
                    }

                }
                for (LukeSkywalker l : lukeOyuncuArrayList) {

                    l.setCan(l.getCan() - 1);
                    if (l.getCan() == 0) {
                        String can1 = l.getCan() + " canın kaldi\n   GAME OVER !";
                        String[] options = {"Oyunu Bitir"};
                        int sec11 = JOptionPane.showOptionDialog(null,can1, " CANIN KALMADI", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                        if (sec11 == 0) {
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.dispose();
                            System.exit(sec11);
                        }
                     

                    }
                }

                String[] options = {"DEVAM ET"};
                //String[] satır = {kar + "canın kaldı",};

                int sec = JOptionPane.showOptionDialog(null, "YAKALANDIN !", "CANIN AZALDI", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if (sec == 0) {

                    for (int v = 0; v < 11; v++) {
                        for (int c = 0; c < 14; c++) {

                            if (butonlar[v][c].getText().equals("1")) {
                                butonlar[v][c].setBackground(Color.CYAN);
                            }
                            if (butonlar[v][c].getText().equals("0")) {
                                butonlar[v][c].setBackground(Color.WHITE);
                            }
                            if (butonlar[v][c].getName().equals("A") || butonlar[i][j].getName().equals("B") || butonlar[i][j].getName().equals("C") || butonlar[i][j].getName().equals("D") || butonlar[i][j].getName().equals("E")) {
                                butonlar[v][c].setBackground(Color.BLUE);
                            }
                            butonlar[v][c].setIcon(null);
                        }
                    }

                    oyuncuLokasyon.setX(5);
                    oyuncuLokasyon.setY(6);
                    butonlar[5][6].setIcon(new ImageIcon(image));

                    for (Karakter k : kotuOyuncuArrayList) {
                        if (k.getAd().equals("Stormtrooper")) {
                            butonlar[k.getX()][k.getY()].setIcon(new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Stormtrooper.jpg"));
                            k.karakterKonum.setX(k.getX());
                            k.karakterKonum.setY(k.getY());
                            k.setGuzergah(k.EnKısaYolBul(izgara, new Point(k.getY(), k.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));

                            for (Point point : k.guzergah) {
                                if ((point.y == k.getX() && point.x == k.getY()) || (point.y == 5 && point.x == 6)) {

                                } else {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }

                            }
                        }
                        if (k.getAd().equals("DarthVader")) {
                            butonlar[k.getX()][k.getY()].setIcon(new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Darth_Vader.jpg"));
                            k.karakterKonum.setX(k.getX());
                            k.karakterKonum.setY(k.getY());
                            k.setGuzergah(k.EnKısaYolBul(DartVaderIzgara, new Point(k.getY(), k.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));
                            for (Point point : k.guzergah) {
                                if ((point.y == k.getX() && point.x == k.getY()) || (point.y == 5 && point.x == 6)) {

                                } else {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }

                            }
                        }
                        if (k.getAd().equals("KyloRen")) {
                            butonlar[k.getX()][k.getY()].setIcon(new ImageIcon("C:\\Users\\sams\\Desktop\\proje_1\\Kylo_Ren.jpg"));
                            karekter.karakterKonum.setX(k.getX());
                            karekter.karakterKonum.setY(k.getY());
                            k.setGuzergah(k.EnKısaYolBul(izgara, new Point(k.getY(), k.getX()), new Point(oyuncuLokasyon.getY(), oyuncuLokasyon.getX())));
                            for (Point point : k.guzergah) {
                                if ((point.y == k.getX() && point.x == k.getY()) || (point.y == 5 && point.x == 6)) {

                                } else {
                                    butonlar[point.y][point.x].setBackground(Color.red);
                                }

                            }
                        }

                    }

                }

            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e
    ) {//elimizi tuştan çektiğimizde çalışır

    }

    /**
     * @return the kolUzunluk
     */
    public static String getKolUzunluk() {
        return kolUzunluk;
    }

    /**
     * @param aKolUzunluk the kolUzunluk to set
     */
    public static void setKolUzunluk(String aKolUzunluk) {
        kolUzunluk = aKolUzunluk;
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * @return the myTimer
     */
    public static Timer getMyTimer() {
        return myTimer;
    }

    /**
     * @param aMyTimer the myTimer to set
     */
    public static void setMyTimer(Timer aMyTimer) {
        myTimer = aMyTimer;
    }

    /**
     * @return the butonlar
     */
    public static JButton[][] getButonlar() {
        return butonlar;
    }

    /**
     * @param aButonlar the butonlar to set
     */
    public static void setButonlar(JButton[][] aButonlar) {
        butonlar = aButonlar;
    }

    /**
     * @return the grid
     */
    public Container getGrid() {
        return grid;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(Container grid) {
        this.grid = grid;
    }

    /**
     * @return the kotuOyuncuArrayList
     */
    public List<Karakter> getKotuOyuncuArrayList() {
        return kotuOyuncuArrayList;
    }

    /**
     * @param kotuOyuncuArrayList the kotuOyuncuArrayList to set
     */
    public void setKotuOyuncuArrayList(List<Karakter> kotuOyuncuArrayList) {
        this.kotuOyuncuArrayList = kotuOyuncuArrayList;
    }

    public MasterYoda getMasteryoda() {
        return masteryoda;
    }

    public void setMasteryoda(MasterYoda masteryoda) {
        this.masteryoda = masteryoda;
    }

    public LukeSkywalker getLukeskywalker() {
        return lukeskywalker;
    }

    public void setLukeskywalker(LukeSkywalker lukeskywalker) {
        this.lukeskywalker = lukeskywalker;
    }

    /**
     * @return the oyunsecim
     */
    /**
     * @param oyunsecim the oyunsecim to set
     */
    public void setOyunsecim(boolean oyunsecim) {
        this.oyunsecim = oyunsecim;
    }

}














