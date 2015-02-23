import java.awt.Color;
//TODO add clans to fight each other

public class Microbe extends Organism{//must always use getters and setters to access own variables because not using protected for most variables (they are private)

	public Microbe(int xIn, int yIn, boolean live, Viewport parent) {
		super(xIn, yIn, live, parent);
		x = xIn;
		y = yIn;
		world = parent;
		setAlive(live);
		setType('m');
		this.setLevel(100);
		setColor(new Color(255, 0, 0));
		int numberOfPlants = 0;
		int numberOfMicrobes = 0;
	}
	
	public void doGeneration(){
		//die after no eat:
		this.setLevel(this.getLevel() - 1);
		if(getLevel() <= 0){
			setAlive(false);
			setColor(new Color(255, 255, 255));
		}
		//get random space, decide if move/multiply/fight
		//do world.getLeft()/right/up/down
		//if fighting: do fight
		//multiply/move
				
				//choose random space to go to:
int address = (int)Math.round(Math.random() * 3);//has gaussian distribution (not entirely random). Must distribute such that "strong" numbers pull against each other
switch(address){

//TODO add eating

case 0:
	//check left:
	if(x - 1 >= 0){
		
	if(world.getLeft(x, y).getAlive() == true){
		if(world.getLeft(x, y).getType() == 'p'){//TODO out of bounds when big!
			feed(x-1, y);
		}
			//fight
	}else if(world.getLeft(x, y).getAlive() != true){
		if(getLevel() <= 80){
			move(x-1, y);
		}else{
			multiply(x, y, x-1, y);
		}
	}
		//TODO check level to see if ready to multiply -> else move			
	}
	break;
case 3:
	//check right
	if(x + 1 < world.arrayW){	
	if(world.getRight(x, y).getAlive()){
		if(world.getRight(x,  y).getType() == 'p'){
			feed(x+1, y);
		}
			//fight
	}else if(!world.getRight(x, y).getAlive()){
		if(getLevel() <= 80){
			move(x+1, y);
		}else{
			multiply(x, y, x+1, y);
		}
	}
	}
	break;
case 2:
	//check up	
	if(y-1 >= 0){			
	if(world.getUp(x, y).getAlive()){
		if(world.getUp(x,  y).getType() == 'p'){
			feed(x, y-1);
		}
	}else if(!world.getUp(x, y).getAlive()){
		if(getLevel() <= 80){
			move(x, y-1);
		}else{
			multiply(x, y, x, y-1);
		}
	}
	}
	break;
case 1:
	//check down:
	if(y+1 < world.arrayH){
	if(world.getDown(x, y).getAlive()){
		if(world.getDown(x, y).getType() == 'p'){
			feed(x, y+1);
		}
			//fight
	}else if(!world.getDown(x, y).getAlive()){
		if(getLevel() <= 80){
			move(x, y+1);
		}else{
			multiply(x, y, x, y+1);
		}
	}
	}
	break;	
}
	}
	
	/*public void addMicrobe(int n){
		
	}
	
	public int killMicrobe(){
		
	}
	
	public void addMicrobe(int n){
		
	}
	
	public int killPlant(){
		
	}*/
	
	
	public void feed(int newX, int newY){
		move(newX, newY);
		world.agents[newX][newY].incLevel(15);//Determines how well cell colony survives (5 for equilibrium, 15 for clear ring)
	}
	
	
	public void move(int newX, int newY){
		world.agents[newX][newY] = new Microbe(newX, newY, true, world);
		this.clone(newX, newY);
		setAlive(false);
	}
	
	
	public void multiply(int oldX, int oldY, int newX, int newY){
		world.agents[newX][newY] = new Microbe(newX, newY, true, world);//make the cell a Plant, not a microbe
		clone(newX, newY);//now clone the cell
		world.agents[newX][newY].setLevel(getLevel()/2);//give new cell half the mother cell's level
		setLevel(getLevel()/2);//set the mother cell to have half of it's level
		world.agents[newX][newY].setType('m');
	}
	
	
	public void winFight(int newX, int newY){
		world.agents[newX][newY].setColor(getColor());
		world.agents[newX][newY].world = world;
		world.agents[newX][newY].setAlive(true);
		setAlive(false);
		setColor(new Color(255, 255, 255));
		//can add food gain... here
	}

}
