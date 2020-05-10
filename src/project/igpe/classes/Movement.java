package project.igpe.classes;

public class Movement {

    public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	private Maps room;
	private Hero pg;
	
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
		
		
		if(!collision(newPosX, newPosY)) {
			posHeroX=newPosX;
			posHeroY=newPosY;
		}
		pg.setX(posHeroX);
		pg.setY(posHeroY);
	}
	
	/*verifica se c'è una porta nella prossima casella

	public boolean door (int newX, int newY) {
		if(firstmap.getCellType(newX, newY) == Cell.DOOR) {
			return true;
		}
		else
			return false;
	}
	*/

	public boolean collision(int newX, int newY) {
		if((newX<0 || newX>Settings.x-2) || (newY<0 || newY>Settings.y-2))
			return true;
		else
			return room.getCellType(newX, newY) == Cell.WALL || room.getCellType(newX, newY) == Cell.OBSTACLE ;
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
}
