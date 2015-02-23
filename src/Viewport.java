import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;



public class Viewport extends JPanel{
	int panelW, panelH;
	int arrayW, arrayH;
	int scale;
	int gridsize;
	int numberOfOrganisms = 0;
	BufferedImage image;
	Organism agents[][];//add array of agents
	
	
	public Viewport(int width, int height, int s){
		scale = s;
		arrayW = width;
		arrayH = height;
		panelW = width*scale;
		panelH = height*scale;
		
		this.setSize(panelW, panelH);
		agents = new Organism[arrayW][arrayH];//create an array of size width and height respective to scale
		image = new BufferedImage(panelW, panelH, BufferedImage.TYPE_INT_ARGB);
		
		//Microbe microTest = new Microbe(100, 100, true, this);//TODO just for testing
		
		
		for(int x=0; x<arrayW; x++){
			for(int y=0; y<arrayH; y++){
				agents[x][y] = new Microbe(x, y, false, this);//set all cells to be dead and give them their x and y coordinates
			}
		}

	}
	
	
	public void paint(Graphics g){
		//Graphics g = this.getGraphics();
		Graphics imageG = image.getGraphics();
		for(int x=0; x<arrayW; x++){
			for(int y=0; y<arrayH; y++){
				if(agents[x][y].getAlive()){
			imageG.setColor(agents[x][y].getColor());
			imageG.fillRect((x * scale), (y * scale), scale, scale);
				}else if(!agents[x][y].getAlive()){
					//make all dead cells white
					imageG.setColor(new Color(255, 255, 255));
					imageG.fillRect(x * scale, y * scale, scale, scale);
				}
			}
		}
		g.drawImage(image, 0, 0, panelW, panelH, null);
	}
	
		
	public void doWorldLogic(){
		for(int x=0; x<arrayW; x++){
			for(int y=0; y<arrayH; y++){
				if(agents[x][y].getAlive()){
					
					agents[x][y].doGeneration();//do the logic move for that cell

				}
			}
		}
	}
	
	
	public BufferedImage getImage(){
		return image;
	}
	
	
	public Dimension getViewportSize(){
		return new Dimension(panelW, panelH);
	}
	
	public Organism getLeft(int x, int y){
		return agents[x-1][y];
	}
	
	public Organism getRight(int x, int y){
		return agents[x+1][y];
	}
	
	public Organism getUp(int x, int y){
		return agents[x][y-1];
	}
	
	public Organism getDown(int x, int y){
		return agents[x][y+1];
	}
	
	
	
	
	
}



	


	
