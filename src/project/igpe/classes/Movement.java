package project.igpe.classes;

public class Movement {

    public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	private Maps room;
	private Hero pg;
	
	private GraphicsGame graphicGame;

	private static boolean doorDx = false;
	private static boolean doorUp = false;
	private static boolean doorDown = false;
	private static boolean doorLx = false;
	
	public void move(int direction) {
		int posHeroX=pg.getX();
		int posHeroY=pg.getY();
		int newPosX=posHeroX;
		int newPosY=posHeroY;
		
		if(direction == MOVE_RIGHT) {
			newPosX=pg.getX()+1;
			}
		else if(direction == MOVE_LEFT)
			newPosX=pg.getX()-1;
		else if(direction == MOVE_UP)
			newPosY=pg.getY()-1;
		else if(direction == MOVE_DOWN)
			newPosY=pg.getY()+1;
		
		GraphicHero.setImgDir(direction);
		
		//chiamare il cambio stanza qui
		if (door(newPosX,newPosY)) {
			try {
				graphicGame.switchRoom();
				graphicGame.setBg((Maps.getIndiceMappe()+1)%Maps.getNumMappe());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(doorDown) {
				newPosX=10;
				newPosY=2;
				doorDown=false;
			}
			if(doorLx) {
				newPosX=18;
				newPosY=7;
				doorLx=false;
			}
			if(doorUp) {
				newPosX=10;
				newPosY=12;
				doorUp=false;
			}
			if(doorDx) {
				newPosX=2;
				newPosY=7;
				doorDx=false;
			}
		}
		
		
		if(!collision(newPosX, newPosY)) {
			posHeroX=newPosX;
			posHeroY=newPosY;
		}
		
		collisionDamage(newPosX, newPosY);
		
		
		Hero.setX(posHeroX);
		Hero.setY(posHeroY);
	}
		
	//verifica se c'è una porta nella prossima casella

	public boolean door (int newX, int newY) {
		
		if(newX==1 && newY==7) {
			doorLx=true;
		}
		else if(newX==10 && newY==13) {
			doorDown=true;
		}
		else if(newX==19 && newY==7) {
			doorDx=true;
		}
		else if(newX==10 && newY==1) {
			doorUp=true;
		}
		
		if(room.getCellType(newX, newY) == Cell.DOOR) {
			//sleep
			return true;
		}
		else
			return false;
	}
	

	public boolean collision(int newX, int newY) {
		if((newX<0 || newX>Settings.x-2) || (newY<0 || newY>Settings.y-2))
			return true;
		else
			return room.getCellType(newX, newY) == Cell.WALL || room.getCellType(newX, newY) == Cell.OBSTACLE ;
	}
	
	public void collisionDamage(int newX, int newY) {	
			if (room.getCellType(newX, newY) == Cell.OBSTACLEDAMAGE) {
				if (Hero.getLife() > 0)
					Hero.setLife(Hero.getLife()-10);
				if (Hero.getLife() == 0) {
					//schermata morte
				}
			}
				
			if (room.getCellType(newX, newY) == Cell.FALLINGDOWN) {
				Hero.setLife(0);
				//inserire schermata morte
			}
	}
	
	public Maps getRoom() {
		return room;
	}

	public void setRoom(Maps room) {
		this.room = room;
	}

	public Hero getPg() {
		return pg;
	}

	public void setPg(Hero pg) {
		this.pg = pg;
	}

	public Movement(Hero pg, Maps map ) {
		this.pg = pg;
		this.room = map;
	}

	public GraphicsGame getGraphicGame() {
		return graphicGame;
	}

	public void setGraphicGame(GraphicsGame graphicGame) {
		this.graphicGame = graphicGame;
	}
}
