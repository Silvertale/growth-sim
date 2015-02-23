import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

//TO USE THIS PROGRAM: click to spawn a plant cell, ctrl+click to spawn an organism cell (which eats the plants)

public class GUI extends JFrame implements WindowListener, MouseListener, KeyListener{
	int winW, winH;
	Viewport viewport;//extend viewport and overload paint function
	long startTime, endTime;
	
	public GUI(){
		
		viewport = new Viewport(350, 350, 2);
		viewport.setBackground(Color.WHITE);
		viewport.addMouseListener(this);	
		
		
		this.add(viewport);
		this.addWindowListener(this);
		this.addKeyListener(this);
		this.setLayout(null);
		this.setSize(viewport.getViewportSize().width, viewport.getViewportSize().height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//do paint and logic loop:
		while(true){
			startTime = System.currentTimeMillis() % 1000;
			viewport.repaint();
			viewport.doWorldLogic();	
			endTime = System.currentTimeMillis() % 1000;
			try {
				if(1000/10 - (endTime - startTime) >= 0){
				Thread.sleep((1000/10) - (endTime - startTime));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
	}
	
	
	public static void main(String args[]){
		new GUI();
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}


	@Override
	public void windowClosed(WindowEvent arg0) {
		
		
	}


	@Override
	public void windowClosing(WindowEvent arg0) {//save image before closing the window
		try {
		    // retrieve image
			viewport.repaint();
		    BufferedImage bi = viewport.getImage();
		    File outputfile = new File("/home/silvertale/Desktop/alifeOutput.png");
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
		    System.out.println("There was a problem saving the image!");
		}
		
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
			viewport.doWorldLogic();//don't repaint if deactivated
		
		
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		//translate mouse position to viewport area
		if(!arg0.isControlDown()){
		int x = arg0.getXOnScreen() - this.getX();
		int y = arg0.getYOnScreen() - this.getY();
		//make new organism random color
		//Color randCol = new Color((int)Math.round(Math.random() * 255), (int)Math.round(Math.random() * 255), (int)Math.round(Math.random() * 255));
		viewport.agents[(x/viewport.scale)-1/viewport.scale][(y/viewport.scale)+0/viewport.scale] = new Plant((x/viewport.scale)-1/viewport.scale, (y/viewport.scale)+0/viewport.scale, true, viewport);//-30/scale to account for top ribbon	
		}else{
			int x = arg0.getXOnScreen() - this.getX();
			int y = arg0.getYOnScreen() - this.getY();
			viewport.agents[(x/viewport.scale)-1/viewport.scale][(y/viewport.scale)+0/viewport.scale] = new Microbe((x/viewport.scale)-1/viewport.scale, (y/viewport.scale)+0/viewport.scale, true, viewport);//-30/scale to account for top ribbon	
		}
		}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_I){//if "i" key is pressed, take a screenshot
			System.out.println("written");
			//get date for output file string:
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
			Date date = new Date();
			String sDate = dateFormat.format(date);
			
			try {
			BufferedImage bi = viewport.getImage();
			String path = "/home/silvertale/Pictures/ALifeImages/alifeOutput" + sDate + ".png";
		    File outputfile = new File(path);//make output file string
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
		    System.out.println("There was a problem saving the image!");
		}
		}
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
}
