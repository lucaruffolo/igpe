package project.igpe.classes;

public class Movement {

    public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	private Maps firstmap;
	private Hero pg;
	
	public void move(int direction) {
		int posHeroX=pg.getX();
		int posHeroY=pg.getY();
		int newPosX=posHeroX;
		int newPosY=posHeroY;
		
		if(direction == MOVE_RIGHT)
			newPosX=pg.getX()+1;
		else if(direction == MOVE_LEFT)
			newPosX=pg.getX()-1;
		else if(direction == MOVE_UP)
			newPosX=pg.getY()-1;
		else if(direction == MOVE_DOWN)
			newPosX=pg.getY()+1;
		
		if(!collision(newPosX, newPosY)) {
			posHeroX=newPosX;
			posHeroY=newPosY;
		}
	}

	public boolean collision(int newX, int newY) {
		return firstmap.getCellType(newX, newY) == Cell.WALL || (newX<0 || newX>Settings.cellSize-1) || (newY<0 || newY>Settings.cellSize-1) ;
	}
	
	
	public Movement(Hero pg, Maps map ) {
		this.pg = pg;
		this.firstmap = map;
	}
}
