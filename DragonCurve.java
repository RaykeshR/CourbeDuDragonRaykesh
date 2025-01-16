import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
// Pour lancer javac DragonCurve.java;java DragonCurve
// OU 
// javac DragonCurve.java;java DragonCurve --Choix --délai/Durée_entre_rendu
//DANGER eliptique à partir de là :
//javac DragonCurve.java;java DragonCurve 50 25 // NE pas faire sans connaisance
import java.util.concurrent.ThreadLocalRandom;
//javac DragonCurve.java;java DragonCurve 5 250 // NE pas faire sans connaisance


public class DragonCurve extends JFrame {
    int X,Y;
    private List<Integer> turns;
    private double AngleDeDepart, TailleDesCotes;
    private boolean rnd;
    
    public DragonCurve(int n) {
        super("Dragon Curve Raykesh");
        //int x=1920,y=1080; 
        //this.X=1920;this.Y=1080;  // resolution 
        //this.X=1080;this.Y=720;  // resolution 
        //setBounds(this.X/8, this.Y/8, this.X-this.X/4, this.Y-this.Y/4);// resolution 
        this.X=750;this.Y=500;//Par Defaut
        setBounds(50,50, X,Y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        turns = getSequence(n);
        AngleDeDepart = -n * (Math.PI / 4);//Optionel
        //AngleDeDepart =0;
        TailleDesCotes = 400 / Math.pow(2, n / 2.);
        if(AngleDeDepart == 0)
            TailleDesCotes/=2;
    }
    public DragonCurve(int n,int x, int y) {
        this(n,x,y,true);
    }
    public DragonCurve(int n,int x, int y,boolean AvecAngleDeDepartNonNull) {//AvecAngleDeDepartNonNull par Default à true
        super("Dragon Curve Raykesh");
        this.X=x;this.Y=y;
        //setBounds(50,50, X,Y);
        setBounds(this.X/8, this.Y/8, this.X-this.X/4, this.Y-this.Y/4);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        turns = getSequence(n);
        if (AvecAngleDeDepartNonNull){
            AngleDeDepart = -n * (Math.PI / 4);//Optionel
        }else{AngleDeDepart =0;}

        TailleDesCotes = 400 / Math.pow(2, n / 2.);

        if(AngleDeDepart == 0)
            TailleDesCotes/=2;
    }
    public DragonCurve(int n,int x, int y,boolean AvecAngleDeDepartNonNull,boolean rnd) {//AvecAngleDeDepartNonNull par Default à true
        super("Dragon Curve Raykesh");
        this.rnd = rnd;
        if(rnd){
            //int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            //int randomNum = ThreadLocalRandom.current().nextInt(0, max + 1);
            //...

            this.X=x;
            this.Y=y;
            int r  =(ThreadLocalRandom.current().nextInt(00, this.X/8 + 1)/100);
            int r2 =(ThreadLocalRandom.current().nextInt(00, this.Y/8 + 1)/100);
            //setBounds(50,50, X,Y);
            //setBounds(this.X/8, this.Y/8, (this.X-this.X/4)*(ThreadLocalRandom.current().nextInt(90, 100 + 1)/100), (this.Y-this.Y/4)*(ThreadLocalRandom.current().nextInt(100, 100 + 1)/100));
            setBounds(r, r2, (this.X-this.X/4), (this.Y-this.Y/4));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            turns = getSequence(n);
            if (AvecAngleDeDepartNonNull){
                AngleDeDepart = -n * (Math.PI / 4);//Optionel
            }else{AngleDeDepart =0;}
    
            TailleDesCotes = 400 / Math.pow(2, n / 2.);
    
            if(AngleDeDepart == 0)
                TailleDesCotes/=2;
        }else{
            //this(n,x,y,true);
            this.X=x;this.Y=y;
            //setBounds(50,50, X,Y);
            setBounds(this.X/8, this.Y/8, this.X-this.X/4, this.Y-this.Y/4);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            turns = getSequence(n);
            if (AvecAngleDeDepartNonNull){
                AngleDeDepart = -n * (Math.PI / 4);//Optionel
            }else{AngleDeDepart =0;}
    
            TailleDesCotes = 400 / Math.pow(2, n / 2.);
    
            if(AngleDeDepart == 0)
                TailleDesCotes/=2;
        }
    }
    

    public List<Integer> getSequence(int iteration) {
        List<Integer> L = new ArrayList<Integer>();
        for (int i = 0; i < iteration; i++) {
            List<Integer> copy = new ArrayList<Integer>(L);
            Collections.reverse(copy);
            L.add(1);
            for (Integer turn : copy) {
                L.add(-turn);
            }
        }
        return L;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK); // La couleur
        //g.setColor(Color.RED); // La couleur
        double angle = AngleDeDepart;
        int x1,y1;
        if(AngleDeDepart != 0){
            x1 = 230; y1 = 350;
        }else{
            x1 = 50+X/2; y1 = 50+Y/2;
        }
        int x2 = x1 + (int) (Math.cos(angle) * TailleDesCotes);
        int y2 = y1 + (int) (Math.sin(angle) * TailleDesCotes);
        g.drawLine(x1, y1, x2, y2);     // On trace La premiere ligne 
        x1 = x2;
        y1 = y2;                        // On passe au point suivant
        for (Integer turn : turns) {
            angle += turn * (Math.PI / 2);          //on tourne de pi/2 
            x2 = x1 + (int) (Math.cos(angle) * TailleDesCotes);
            y2 = y1 + (int) (Math.sin(angle) * TailleDesCotes);
            g.drawLine(x1, y1, x2, y2); // On trace La ligne
            x1 = x2;
            y1 = y2;                    // On passe au point suivant
        }
    }

    public static void main(String[] args) {
        new DragonCurve(12).setVisible(true);
        int x=250; // temps d'attente entre chaque affichage (en ms)    // Deuxieme Argument De args (lors de l'appel)
        int Choix=2;                                                    // Premiere Argument De args (lors de l'appel)
        if (args.length>0)  //{} inutile
            try {
                Choix = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Erreur detectee : "+e);
            }
        if (args.length>1)
            try {x = Integer.valueOf(args[1]);} catch (NumberFormatException e) {System.err.println("Erreur detectee : "+e);}


        try {
            switch (Choix) {
                case 50:
                    //Tout //déconseillé si vous ne savez pas ce que ça fait
                case 1:
                    for (int i = 0; i < 17; i++) {
                        //new DragonCurve(12).setVisible(true);Thread.sleep(100);  //System.wait(x);
                        //new DragonCurve(6 ).setVisible(true);Thread.sleep(100);  //System.wait(x);
                        new DragonCurve(i).setVisible(true);Thread.sleep(x);
                    }
                    if(Choix!=50) break;
                case 2:
                    for (int i = 16; i > 0; i--){
                        new DragonCurve(i).setVisible(true);Thread.sleep(x);
                    }
                    if(Choix!=50) break;
                case 3:
                    for (int i = 16; i > 0; i--){
                        new DragonCurve(i,1080,720).setVisible(true);Thread.sleep(x);
                    }
                    if(Choix!=50) break;
                case 4:
                    for (int i = 16; i > 0; i--){
                        new DragonCurve(i,1920,1080).setVisible(true);Thread.sleep(x);
                    }
                    if(Choix!=50) break;
                case 5:
                    //int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                    for (int i = 0; i < 10; i++){
                        new DragonCurve(i,1920,1080,true,true).setVisible(true);Thread.sleep(x);
                    }
                    break;
            
                default:
                    System.err.println("?"+"Vous vous etes trompe de choix");//?Vous vous êtes trompé de choix
                    if(Choix!=50)
                        for (int n = 0; n < Choix; n++)
                            for (int i = 17; i > 0; i--){
                                new DragonCurve(i).setVisible(true);Thread.sleep(Choix);
                            }
                    if(Choix==50)
                        for (int n = 0; n < Choix; n++)
                            for (int i = 17; i > 0; i--){
                                new DragonCurve(i).setVisible(true);Thread.sleep(x);//Attention x au lieux de choix (Donc ne pas prendre de x trop petit)
                            }
                        System.exit(0);
                    break;
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }

    }
}
