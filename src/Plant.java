import java.awt.Color;


public class Plant extends Organism{//just multiplies away

	public Plant(int xIn, int yIn, boolean live, Viewport parent) {
		super(xIn, yIn, live, parent);
		x = xIn;
		y = yIn;
		world = parent;
		setColor(new Color(0, 150, 50));//set plants to always be green
		setAlive(live);
		setType('p');
	}
	
	public void doGeneration(){
		int address = (int)Math.round(Math.random() * 3);//has gaussian distribution (not entirely random). Must distribute such that "strong" numbers pull against each other
		switch(address){
		case 0:
			if(x - 1 >= 0){
				if(!world.getLeft(x, y).getAlive()){
				multiply(x, y, x-1, y);
				}
			}
				break;
		case 3:
			if(x + 1 < world.arrayW){
				if(!world.getRight(x, y).getAlive()){
				multiply(x, y, x+1, y);
				}
			}
				break;
		case 2:
			if(y-1 >= 0){
				if(!world.getUp(x, y).getAlive()){
				multiply(x, y, x, y-1);
				}
			}
				break;
		case 1:
			if(y+1 < world.arrayH){
				if(!world.getDown(x, y).getAlive()){
				multiply(x, y, x, y+1);
				}
			}
				break;		
		}
	}
	
	public void multiply(int oldX, int oldY, int newX, int newY){
		world.agents[newX][newY] = new Plant(newX, newY, true, world);//make the cell a Plant, not a microbe
		clone(newX, newY);//now clone the cell
		world.agents[newX][newY].setType('p');
	}





}
