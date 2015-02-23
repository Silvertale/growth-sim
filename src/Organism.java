import java.awt.Color;


public class Organism {
	protected int x, y;//should NEVER be changed. Only denote the cells own address in the world
	private Color color;
	private boolean alive;
	protected Viewport world;
	private int level;
	private char type;//hold organism type ('p' for plant, 'm' for microbe)
	//can hold other things such as size, genes...
	
	
	public Organism(int xIn, int yIn, boolean live, Viewport parent){//ONLY for initializing! 
		alive = live;
		world = parent;
		x = xIn;
		y = yIn;
		color = new Color(100, 100, 100);//just for now, colors will be set in multiplication process
		level = 100;
	}
	
	public void setColor(int r, int g, int b){
		this.color = new Color(r, g, b);
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color col){
		color = col;
	}
	
	public void doGeneration(){		
		
	}
	
	
	public void setAlive(boolean a){
		alive = a;
		if(!a){
			color = new Color(255, 255, 255);//if dead, also set color to white
		}
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	public void setLevel(int l){
		level = l;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void incLevel(int inc){
		if(inc + level <= 100){//constrain level to between 0 and 100
			int newLevel = level + inc;
			level = newLevel;	
		}else{
			level = 100;
		}
	}
	
	public void clone(int newX, int newY){//clone to new spot
		world.agents[newX][newY].setColor(getColor());
		world.agents[newX][newY].world = world;
		world.agents[newX][newY].setAlive(getAlive());//set to mother's alive so does not escape when moving
		world.agents[newX][newY].level = getLevel();
		world.agents[newX][newY].setType(getType());//set type to mother's type. Make sure cell type is actually this type!!!
	}	
	
	
	public void multiply(int oldX, int oldY, int newX, int newY){
		world.agents[oldX][oldY].clone(newX, newY);//TODO could add color modification in here!
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	
}
