/**
 * Created by Paul on 7/23/2017.
 */
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Craft craft;
    private ArrayList<Alien> aliens;
    private boolean ingame;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 15;
    private BackgroundMusic backgroundMusic;
    private BackgroundMusic end;
    private Image bg;
    public int nilai=0;

    private final int[][] pos = {
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30},
 
    };

    private final int[][] pos1 = {
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30},
            
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30},            //tambahan
    };
    
    private final int[][] pos2 = {
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30},
            
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30},            //tambahan
            
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30},    
    };

    
    public Board() {
        initBoard();
    }

    private void initBoard() {
    	BackgroundMusic mulai= new BackgroundMusic(getClass().getResource("Music/play.wav"));
    	backgroundMusic=mulai;
    	backgroundMusic.start();
    	
    	BackgroundMusic gameover= new BackgroundMusic(getClass().getResource("Music/dead.wav"));
    	end=gameover;
    	
    	
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        craft = new Craft(ICRAFT_X, ICRAFT_Y);

        initAliens();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAliens() {
        aliens = new ArrayList<>();

        //tambahan
        Highscores mode=new Highscores();
        mode.initMode();
    
        
        if(mode.mode==1) {
        	for(int[] p : pos) {
        		aliens.add(new Alien(p[0], p[1]));
        	}
        }
        	
        else if(mode.mode==2) {
        	 for(int[] p : pos1) {
                 aliens.add(new Alien(p[0], p[1]));
             }
        }
           
        else if(mode.mode==3) {
        	  for(int[] p : pos2) {
                  aliens.add(new Alien(p[0], p[1]));
              }
        }
          
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(ingame) {
        	ImageIcon j = new ImageIcon("menu.jpg");
            bg = j.getImage();
        	g.drawImage(bg, 0, 0, null);
            drawObjects(g);
        }
        else {
        	ImageIcon j = new ImageIcon("menu.jpg");
            bg = j.getImage();
        	g.drawImage(bg, 0, 0, null);
            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    
}
    private void drawObjects(Graphics g) {

        if(craft.isVisible()) {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                    this);
        }

        ArrayList<Missile> ms = craft.getMissiles();

        for(Missile m : ms) {
            if(m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }

        for(Alien a : aliens) {
            if(a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + aliens.size()+" Score: "+nilai, 5, 15);
    }

    private void drawGameOver(Graphics g) {
    	

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateCraft();
        updateMissiles();
        updateAliens();

        checkCollisions();

        repaint();
        
    }

    private void inGame() {

        if(!ingame) {
        	
        	
        	int pilih=0;
        	int data;
        	int data2;
        	int data3;
        	
            
            
            Highscores score = new Highscores();
            score.initNilaiScore();
            
            data=Integer.parseInt(score.data);
            data2=Integer.parseInt(score.data2);
            data3=Integer.parseInt(score.data3);
            
//            System.out.println(score.tempNama);
            
            if(nilai>data) {
            	pilih=1;
            }
            else if (nilai>data2) {
            	pilih=2;
            }
            else if (nilai>data3) {
            	pilih=3;
            }
            else pilih=4;
            
    		score.pilihTulisScore(pilih, nilai);
    		timer.stop();
    		backgroundMusic.stop();
        	end.stop();
            
            
        }
    }

    private void updateCraft() {

        if(craft.isVisible()) {
            craft.move();
        }
    }

    private void updateMissiles() {
    	

        ArrayList<Missile> ms = craft.getMissiles();

        for(int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);

            if(m.isVisible()) {
                m.move();
                
            }
            else {
                ms.remove(i);
                
            }
            
        }
    }

    private void updateAliens() {

        if(aliens.isEmpty()) {
            ingame = false;
            return;
        }

        for(int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);
            if(a.isVisible()) {
                a.move();
            }
            else {
                aliens.remove(i);
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = craft.getBounds();

        for(Alien alien : aliens) {

            Rectangle r2 = alien.getBounds();

            if(r3.intersects(r2)) {
            	
            	
                craft.setVisible(false);
                alien.setVisible(false);
                ingame = false;
                
                end.start();
        		
        		
        		
                
            }
        }

        ArrayList<Missile> ms = craft.getMissiles();

        for(Missile m : ms) {
        	

            Rectangle r1 = m.getBounds();

            for(Alien alien : aliens) {

                Rectangle r2 = alien.getBounds();

                if(r1.intersects(r2)) {
                    m.setVisible(false);
                    alien.setVisible(false);
                    nilai++;
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}
