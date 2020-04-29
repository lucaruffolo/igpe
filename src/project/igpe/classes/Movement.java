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
		
		if(!collision(newPosX, newPosY)) {
			posHeroX=newPosX;
			posHeroY=newPosY;
		}
		pg.setX(posHeroX);
		pg.setY(posHeroY);
	}

	public boolean collision(int newX, int newY) {
		if((newX<0 || newX>Settings.cellSize-1) || (newY<0 || newY>Settings.cellSize-1))
			return true;
		else
			return firstmap.getCellType(newX, newY) == Cell.WALL || firstmap.getCellType(newX, newY) == Cell.OBSTACLE ;
	}
	
	
	public Maps getFirstmap() {
		return firstmap;
	}

	public void setFirstmap(Maps firstmap) {
		this.firstmap = firstmap;
	}

	public Hero getPg() {
		return pg;
	}

	public void setPg(Hero pg) {
		this.pg = pg;
	}

	public Movement(Hero pg, Maps map ) {
		this.pg = pg;
		this.firstmap = map;
		this.firstmap.getCella()[0][0].setType(1);
	}
}
