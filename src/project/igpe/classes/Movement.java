package project.igpe.classes;

import java.util.HashMap;

import project.igpe.GUI.ChangeRoomScene;
import project.igpe.main.Main;

public class Movement {

    public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	public static Maps room;
	private Hero pg;
	private static int dir;
	
	private static GraphicsGame graphicGame;

	private static boolean doorDx = false;
	private static boolean doorUp = false;
	private static boolean doorDown = false;
	private static boolean doorLx = false;
	
	private static int nRand = -20;
	
	private static HashMap<Integer, HashMap<String, Integer>> saveDoorOpened = new HashMap<Integer, HashMap<String,Integer>>();
	private static int mappaAttuale = 0;
	private static int prossimaMappa;
	private boolean firstTime = true;
	
	public int dacancellare = 0;
	
	
	public void move(int direction) {
		
		if (direction == MOVE_RIGHT && !Hero.lockRight) { // && room.getCellType(pixelInMatrixX(newPosX), pixelInMatrixY(newPosY)) != Cell.WALL) {
			Hero.setDirHero(MOVE_RIGHT);
			Bullet.setDirBullet(MOVE_RIGHT);
			Hero.setVelX(Hero.speed);			
			Hero.resetHeroLockMove();
			
		} else if (direction == MOVE_LEFT && !Hero.lockLeft) {					
			Hero.setDirHero(MOVE_LEFT);
			Bullet.setDirBullet(MOVE_LEFT);
			Hero.setVelX(-Hero.speed);	
			Hero.resetHeroLockMove();		
			
		} else if (direction == MOVE_UP  && !Hero.lockUp) {					
			Hero.setDirHero(MOVE_UP);
			Bullet.setDirBullet(MOVE_UP);
			Hero.setVelY(-Hero.speed);
			Hero.resetHeroLockMove();
			
		} else if (direction == MOVE_DOWN && !Hero.lockDown) {					
			Hero.setDirHero(MOVE_DOWN);
			Bullet.setDirBullet(MOVE_DOWN);
			Hero.setVelY(Hero.speed);		
			Hero.resetHeroLockMove();
		}
		
		GraphicHero.setImgDir(direction);
		GraphicHero.setImgPistolDir(direction);		
	
	}
	
	//Funzione che viene richiamata nel Hero.movehero() per il gameloop e richiama altre funzioni per il Check movimento/porte ostacoli ecc.
	public static void checkHero (int x, int y) { 
		
		checkDoor(x, y);
		collisionDamage(x, y);
		collisionHeart(x, y);
		collisionPistol(x, y);
		collisionEnemy(x, y);		
	}
	
	public static void collisionPistol(int newX, int newY) {
		
		if (room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.PISTOL) {		
			Hero.takePistol = true;
			room.setCellType(pixelInMatrixX(newX), pixelInMatrixY(newY), Cell.EMPTY); //elimino cella heart
				
		}
	}
	
	public static void collisionHeart(int newX, int newY) {
		
		if (room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.HEART) {											
				int i = 0;
				if (Hero.getLife() < 100) {					
					while (i<25 && Hero.getLife()<100) {
						Hero.setLife(Hero.getLife()+1);
						i++;
					}
					room.setCellType(pixelInMatrixX(newX), pixelInMatrixY(newY), Cell.EMPTY); //elimino cella heart
				}
			}
	}
	
	
	//Controllo PORTA
	public static void checkDoor (int newPosX, int newPosY) {
		
		if (door(pixelInMatrixX(newPosX), pixelInMatrixY(newPosY))) {
					
			Main.GameInPause = true;	
			MovementControl.setRipristinoGame(Main.window.getScene());
			try { 
					ChangeRoomScene.changeRoom();
				} catch (Exception e) {	e.printStackTrace();}
			
			checkMap(); // funzione controllo hasmap

			if (doorDown) {				
				newPosX = 612;
				newPosY = 180;
			}
			if (doorLx) {
				newPosX = 998;
				newPosY = 420;
			}
			if (doorUp) {
				newPosX = 606;
				newPosY = 638;
			}
			if (doorDx) {
				newPosX = 202;
				newPosY = 420;
			}
			
			Hero.setX(newPosX);
			Hero.setY(newPosY);
			
			doorDown = false;
			doorDx = false;
			doorLx = false;
			doorUp = false;

			GraphicsGame.setFirstRoom(false);
		}
	
	
		
	}
	// INIZIO COLLISIONI
	
	public static boolean collisionWall(int newX, int newY) {
		
		if((newX<0 || newX>Settings.x) || (newY<0 || newY>Settings.y)) //end world
			return true;
		
		if (room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.WALL)
			return true;	
		return false;
	}
	
	public static boolean collisionObstacle(int newX, int newY) {
		
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.OBSTACLE
			|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.OBSTACLE
				|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.OBSTACLE
					|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.OBSTACLE)
							return true;
					
		return false;		
	}
public static boolean collisionDoor(int newX, int newY) {
		
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.DOOR
			|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.DOOR
				|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.DOOR
					|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.DOOR)
							return true;
					
		return false;		
	}
	
	public static boolean collisionEnemy(int newX, int newY) {
		
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.ENEMY
			|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.ENEMY
				|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.ENEMY
					|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.ENEMY) {
			
							Hero.setLife(Hero.getLife()-Enemy.damage);							
							return true;
		}
					
		return false;		
	}

	public static boolean collisionHero(int newX, int newY) {
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.HERO
				|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.HERO
					|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.HERO
						|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.HERO) {		
												
								return true;
			}
						
			return false;	
	}
	
	public static void collisionDamage(int newX, int newY) {	
		
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.OBSTACLEDAMAGE
			|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.OBSTACLEDAMAGE
				|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.OBSTACLEDAMAGE
					|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.OBSTACLEDAMAGE) {
			
			if (Hero.getLife() > 0)
				Hero.setLife(Hero.getLife()-0.1);
		}
			
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.FALLINGDOWN
				|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.FALLINGDOWN
					|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.FALLINGDOWN
						|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.FALLINGDOWN) {
							
			Hero.setLife(0);
		}
	}
	
	// FINE COLLISIONI
	
	// INIZIO PIXEL IN MATRIX E VICEVERSA
	public static int pixelInMatrixX (int x) {
		// pos pixel : totpixel = pos matrice : tot matrice
		// pos pixel * totale matrice / tot pixel		
		return (x*Settings.xMatrix)/Settings.x;		
	}
	
	public static int pixelInMatrixY (int y) {		
		return (y*Settings.yMatrix)/Settings.y;
	}
	
	public static int matrixInPixelX (int x) {		
		// totpixel * pos matrice / tot matrice		
		return (Settings.x*x)/Settings.xMatrix;		
	}
	
	public static int matrixInPixelY (int y) {		
		return (Settings.y*y)/Settings.yMatrix;		
	}
	
	// FINE PIXEL IN MATRIX E VICEVERSA--------
	public static HashMap<String, Integer> questaStanza = null;
	public static void checkMap () {
		
		System.out.println("cusenzappp");
		
		GraphicsGame.nRandObstacles = (int) (2.0 * Math.random());
		mappaAttuale=Maps.getIndiceMappe();
		
		if(GraphicsGame.getFirstRoom()==true) {
			questaStanza = new HashMap<String, Integer>();
			questaStanza.put("portaDown", -1);
			questaStanza.put("portaLeft", -1);
			questaStanza.put("portaUp", -1);
			questaStanza.put("portaRight", -1);
			saveDoorOpened.put(mappaAttuale, questaStanza);
			System.out.println(saveDoorOpened.get(mappaAttuale));
		}
		
		if (saveDoorOpened.containsKey(mappaAttuale)) {
			questaStanza = saveDoorOpened.get(mappaAttuale);
			if (doorUp && questaStanza.get("portaUp")!=-1) {
				Maps.setIndiceMappe(questaStanza.get("portaUp"));
				System.out.println("questa stanza:"+ questaStanza.get("portaUp"));
				try {
					graphicGame.setBg(Maps.getIndiceMappe());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (doorUp && questaStanza.get("portaUp")==-1) {
				newRoom(questaStanza);
			}
			
			if (doorDown && questaStanza.get("portaDown")!=-1) {
				Maps.setIndiceMappe(questaStanza.get("portaDown"));
				System.out.println("questa stanza:"+ questaStanza.get("portaDown"));
				try {
					graphicGame.setBg(Maps.getIndiceMappe());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (doorDown && questaStanza.get("portaDown")==-1) {
				newRoom(questaStanza);
			}
			
			if (doorLx && questaStanza.get("portaLeft")!=-1) {
				Maps.setIndiceMappe(questaStanza.get("portaLeft"));
				System.out.println("questa stanza:"+ questaStanza.get("portaLeft"));
				try {
					graphicGame.setBg(Maps.getIndiceMappe());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (doorLx && questaStanza.get("portaLeft")==-1) {
				newRoom(questaStanza);
			}
			
			if (doorDx && questaStanza.get("portaRight")!=-1) {
				Maps.setIndiceMappe(questaStanza.get("portaRight"));
				System.out.println("questa stanza:"+ questaStanza.get("portaRight"));
				try {
					graphicGame.setBg(Maps.getIndiceMappe());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(doorDx && questaStanza.get("portaRight")==-1) {
				newRoom(questaStanza);
			}
			
		} else {
			newRoom(questaStanza);
		}

	}
	
	public static int newRandRoom () {
		nRand = (int) (22.0 * Math.random());
		for (int i = 0; i < Maps.getIndexYetChoosen().size(); i++) {
			while (nRand == Maps.getIndexYetChoosen().get(i)) {
				nRand = (int) (22.0 * Math.random());
				System.out.println(nRand);
				i=0;
			}
		}
		return nRand;
	}
	
	
	public static void newRoom(HashMap<String, Integer> questaStanza) {
		
		newRandRoom();
			
			if (doorDown) {
				if (Maps.isClosedMap()) {
					while (nRand != 1 && nRand != 2 && nRand != 6 && nRand != 7 && nRand != 8 && nRand != 9
							&& nRand != 11) {
						newRandRoom();
					}
				} else {
					while (nRand != 12 && nRand != 13 && nRand != 17 && nRand != 18 && nRand != 19 && nRand != 20) {
						newRandRoom();
					}
				}
			}

			if (doorLx) {
				if (Maps.isClosedMap()) {
					while (nRand != 2 && nRand != 3 && nRand != 5 && nRand != 7 && nRand != 8 && nRand != 10
							&& nRand != 11) {
						newRandRoom();
					}
				} else {
					while (nRand != 13 && nRand != 14 && nRand != 16 && nRand != 18 && nRand != 19 && nRand != 21) {
						newRandRoom();
					}
				}
			}

			if (doorUp) {
				if (Maps.isClosedMap()) {
					while (nRand != 3 && nRand != 4 && nRand != 6 && nRand != 8 && nRand != 9 && nRand != 10
							&& nRand != 11) {
						newRandRoom();
					}
				} else {
					while (nRand != 14 && nRand != 15 && nRand != 17 && nRand != 19 && nRand != 20 && nRand != 21) {
						newRandRoom();
					}
				}
			}

			if (doorDx) {
				if (Maps.isClosedMap()) {
					while (nRand != 1 && nRand != 4 && nRand != 5 && nRand != 7 && nRand != 9 && nRand != 10
							&& nRand != 11) {
						newRandRoom();
					}
				} else {
					while (nRand != 12 && nRand != 15 && nRand != 16 && nRand != 18 && nRand != 20 && nRand != 21) {
						newRandRoom();
					}
				}
			}
			
		System.out.println("prossima mappa:"+nRand);
		prossimaMappa=nRand;
		
		HashMap<String, Integer> prossimaStanza;
		prossimaStanza = new HashMap<String, Integer>();
		prossimaStanza.put("portaDown", -1);
		prossimaStanza.put("portaLeft", -1);
		prossimaStanza.put("portaUp", -1);
		prossimaStanza.put("portaRight", -1);
		saveDoorOpened.put(prossimaMappa, prossimaStanza);
		
		if (doorDown) {
			questaStanza.put("portaDown", prossimaMappa);
			prossimaStanza.put("portaUp", mappaAttuale);
			System.out.println(saveDoorOpened.get(mappaAttuale)+"giu");
			System.out.println(saveDoorOpened.get(prossimaMappa)+"giu");
		}
		if (doorLx) {
			questaStanza.put("portaLeft", prossimaMappa);
			prossimaStanza.put("portaRight", mappaAttuale);
			System.out.println(saveDoorOpened.get(mappaAttuale)+"lx");
			System.out.println(saveDoorOpened.get(prossimaMappa)+"lx");
		}
		if (doorUp) {
			questaStanza.put("portaUp", prossimaMappa);
			prossimaStanza.put("portaDown", mappaAttuale);
			System.out.println(saveDoorOpened.get(mappaAttuale)+"sopra");
			System.out.println(saveDoorOpened.get(prossimaMappa)+"sopra");
		}
		if (doorDx) {
			questaStanza.put("portaRight", prossimaMappa);
			prossimaStanza.put("portaLeft", mappaAttuale);
			System.out.println(saveDoorOpened.get(mappaAttuale)+"DESTRO");
			System.out.println(saveDoorOpened.get(prossimaMappa)+"DESTRO");
		}

		Maps.getIndexYetChoosen().add(prossimaMappa);
		Maps.setIndiceMappe(prossimaMappa);
		try {
			graphicGame.setBg(prossimaMappa);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	
	
	//verifica se c'è una porta nella prossima casella

	public static boolean door (int newX, int newY) {

		if(newX==2 && newY==7) {
			doorLx=true;
		}
		else if(newX==10 && newY==12) {
			doorDown=true;
		}
		else if(newX==18 && newY==7) {
			doorDx=true;
		}
		else if(newX==10 && newY==2) {
			doorUp=true;
		}
		
		if(room.getCellType(newX, newY) == Cell.DOOR) {
			return true;//porta trovata
		}
		else
			return false;
		
	}
	


	
	
	public static int getProssimaMappa() {
		return prossimaMappa;
	}

	public static void setProssimaMappa(int prossimaMappa) {
		Movement.prossimaMappa = prossimaMappa;
	}

	public static int getMappaAttuale() {
		return mappaAttuale;
	}

	public static void setMappaAttuale(int mappaAttuale) {
		Movement.mappaAttuale = mappaAttuale;
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

	public HashMap<Integer, HashMap<String, Integer>> getSaveDoorOpened() {
		return saveDoorOpened;
	}

	public void setSaveDoorOpened(HashMap<Integer, HashMap<String, Integer>> saveDoorOpened) {
		Movement.saveDoorOpened = saveDoorOpened;
	}


	public static int getnRand() {
		return nRand;
	}

	public static void setnRand(int nRand) {
		Movement.nRand = nRand;
	}

	public static int getDir() {
		return dir;
	}


	public Maps getRoom() {
		return room;
	}

	public void setRoom(Maps room) {
		Movement.room = room;
	}

	public Hero getPg() {
		return pg;
	}

	public void setPg(Hero pg) {
		this.pg = pg;
	}

	public Movement(Hero pg, Maps map) {
		this.pg = pg;
		Movement.room = map;
	}

	public GraphicsGame getGraphicGame() {
		return graphicGame;
	}

	public void setGraphicGame(GraphicsGame graphicGame) {
		Movement.graphicGame = graphicGame;
	}

	public static boolean isDoorDx() {
		return doorDx;
	}

	public static void setDoorDx(boolean doorDx) {
		Movement.doorDx = doorDx;
	}

	public static boolean isDoorUp() {
		return doorUp;
	}

	public static void setDoorUp(boolean doorUp) {
		Movement.doorUp = doorUp;
	}

	public static boolean isDoorDown() {
		return doorDown;
	}

	public static void setDoorDown(boolean doorDown) {
		Movement.doorDown = doorDown;
	}

	public static boolean isDoorLx() {
		return doorLx;
	}

	public static void setDoorLx(boolean doorLx) {
		Movement.doorLx = doorLx;
	}


}

//MOVEMENT BACKUP PRIMA DELLA MODIFICA STATICA SE RIPRISTINIAMO QUESTO BISOGNA TROVARE UN MODO DI FARE UN CHECK DOOR IN TICK IN GAMELOOP
/*package project.igpe.classes;

import java.util.HashMap;

import project.igpe.GUI.ChangeRoomScene;
import project.igpe.main.Main;

public class Movement {

    public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	public static Maps room;
	private Hero pg;
	private static int dir;
	
	private GraphicsGame graphicGame;

	private static boolean doorDx = false;
	private static boolean doorUp = false;
	private static boolean doorDown = false;
	private static boolean doorLx = false;
	
	private static int nRand = -20;
	private static boolean checkNrand2 = false;
	
	private HashMap<Integer, HashMap<String, Integer>> saveDoorOpened = new HashMap<Integer, HashMap<String,Integer>>();
	private static int mappaAttuale = 0;
	private static int prossimaMappa;
	private boolean firstTime = true;
	
	public int dacancellare = 0;
	
	
	public void move(int direction) {
		int newPosX=Hero.getX();
		int newPosY=Hero.getY();
		
		if (direction == MOVE_RIGHT && !Hero.lockRight) { // && room.getCellType(pixelInMatrixX(newPosX), pixelInMatrixY(newPosY)) != Cell.WALL) {
			Hero.setDirHero(MOVE_RIGHT);
			Hero.setVelX(Hero.speed);
			newPosX = (int) (Hero.getX() + Hero.getVelX());
			
			Hero.resetHeroLockMove();
			
		} else if (direction == MOVE_LEFT && !Hero.lockLeft) {			
			Hero.setDirHero(MOVE_LEFT);
			Hero.setVelX(-Hero.speed);
			newPosX = (int) (Hero.getX() - Hero.getVelX());
			
			Hero.resetHeroLockMove();
			
		} else if (direction == MOVE_UP  && !Hero.lockUp) {			
			Hero.setDirHero(MOVE_UP);
			Hero.setVelY(-Hero.speed);
			newPosY = (int) (Hero.getY() - Hero.getVelY());
			
			Hero.resetHeroLockMove();
			
		} else if (direction == MOVE_DOWN && !Hero.lockDown) {			
			Hero.setDirHero(MOVE_DOWN);
			Hero.setVelY(Hero.speed);
			newPosY = (int) (Hero.getY() + Hero.getVelY());
			
			Hero.resetHeroLockMove();
		}
		
		
		GraphicHero.setImgDir(direction);
		
		//Controllo PORTA
		if (door(pixelInMatrixX(newPosX), pixelInMatrixY(newPosY))) {
			
			//System.out.println("Porta trovata x: " + Hero.getX() + "  y: "+ Hero.getY()+ " -> in matr " + pixelInMatrixX(newPosX) + pixelInMatrixY(newPosY));
			
			MovementControl.setRipristinoGame(Main.window.getScene());
			try { 
				ChangeRoomScene.changeRoom();
				} catch (Exception e) {	e.printStackTrace();}
			
			checkMap(); // funzione controllo hasmap

			if (doorDown) {				
				newPosX = 600;
				newPosY = 140;
			}
			if (doorLx) {
				newPosX = 1050;
				newPosY = 410;
			}
			if (doorUp) {
				newPosX = 600;
				newPosY = 672;
			}
			if (doorDx) {
				newPosX = 150;
				newPosY = 420;
			}
			
			Hero.setX(newPosX);
			Hero.setY(newPosY);
			doorDown = false;
			doorDx = false;
			doorLx = false;
			doorUp = false;

			GraphicsGame.setFirstRoom(false);
		}
		
		System.out.println("X " + pixelInMatrixX(newPosX) +"  - Y "+pixelInMatrixY(newPosY));
		collisionDamage(newPosX, newPosY);		
	
	}
	
	// INIZIO COLLISIONI
	
	public static boolean collisionWall(int newX, int newY) {
		
		if((newX<0 || newX>Settings.x) || (newY<0 || newY>Settings.y)) //end world
			return true;
		
		if (room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.WALL)
			return true;	
		return false;
	}
	
	public static boolean collisionObstacle(int newX, int newY) {
		
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.OBSTACLE
			|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.OBSTACLE
				|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.OBSTACLE
					|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.OBSTACLE)
							return true;
					
		return false;		
	}
	
	public void collisionDamage(int newX, int newY) {	
		
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.OBSTACLEDAMAGE
			|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.OBSTACLEDAMAGE
				|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.OBSTACLEDAMAGE
					|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.OBSTACLEDAMAGE) {
			
			if (Hero.getLife() > 0)
				Hero.setLife(Hero.getLife()-1);
		}
			
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.FALLINGDOWN
				|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.FALLINGDOWN
					|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.FALLINGDOWN
						|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.FALLINGDOWN) {
							
			Hero.setLife(0);
		}
	}
	
	// FINE COLLISIONI
	
	// INIZIO PIXEL IN MATRIX E VICEVERSA
	public static int pixelInMatrixX (int x) {
		// pos pixel : totpixel = pos matrice : tot matrice
		// pos pixel * totale matrice / tot pixel
		
		return (x*Settings.xMatrix)/Settings.x;		
	}
	
	public static int pixelInMatrixY (int y) {
		
		return (y*Settings.yMatrix)/Settings.y;
	}
	
	public static int matrixInPixelX (int x) {		
		// totpixel * pos matrice / tot matrice		
		return (Settings.x*x)/Settings.xMatrix;		
	}
	
	public static int matrixInPixelY (int y) {
		
		return (Settings.y*y)/Settings.yMatrix;		
	}
	
	
	// FINE --------
	public void checkMap () {
		
		mappaAttuale=Maps.getIndiceMappe();
		
		HashMap<String, Integer> questaStanza = null;
		if(GraphicsGame.getFirstRoom()==true) {
			questaStanza = new HashMap<String, Integer>();
			questaStanza.put("portaDown", -1);
			questaStanza.put("portaLeft", -1);
			questaStanza.put("portaUp", -1);
			questaStanza.put("portaRight", -1);
			saveDoorOpened.put(mappaAttuale, questaStanza);
		}
		
		if (saveDoorOpened.containsKey(mappaAttuale)) {
			questaStanza = saveDoorOpened.get(mappaAttuale);
			if (doorUp && questaStanza.get("portaUp")!=-1) {
				Maps.setIndiceMappe(questaStanza.get("portaUp"));
				System.out.println("questa stanza:"+ questaStanza.get("portaUp"));
				try {
					graphicGame.setBg(Maps.getIndiceMappe());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (doorUp && questaStanza.get("portaUp")==-1) {
				newRoom(questaStanza);
			}
			
			if (doorDown && questaStanza.get("portaDown")!=-1) {
				Maps.setIndiceMappe(questaStanza.get("portaDown"));
				System.out.println("questa stanza:"+ questaStanza.get("portaDown"));
				try {
					graphicGame.setBg(Maps.getIndiceMappe());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (doorDown && questaStanza.get("portaDown")==-1) {
				newRoom(questaStanza);
			}
			
			if (doorLx && questaStanza.get("portaLeft")!=-1) {
				Maps.setIndiceMappe(questaStanza.get("portaLeft"));
				System.out.println("questa stanza:"+ questaStanza.get("portaLeft"));
				try {
					graphicGame.setBg(Maps.getIndiceMappe());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (doorLx && questaStanza.get("portaLeft")==-1) {
				newRoom(questaStanza);
			}
			
			if (doorDx && questaStanza.get("portaRight")!=-1) {
				Maps.setIndiceMappe(questaStanza.get("portaRight"));
				System.out.println("questa stanza:"+ questaStanza.get("portaRight"));
				try {
					graphicGame.setBg(Maps.getIndiceMappe());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(doorDx && questaStanza.get("portaRight")==-1) {
				newRoom(questaStanza);
			}
			
		} else {
			newRoom(questaStanza);
		}

	}
	
	
	public void newRoom(HashMap<String, Integer> questaStanza) {
		
		
		
		nRand = (int) (22.0 * Math.random());

		while (!checkNrand2) {
			checkNrand2 = true;
			for (int i = 0; i < Maps.getIndexYetChoosen().size(); i++) {
				while (nRand == Maps.getIndexYetChoosen().get(i)) {
					nRand = (int) (22.0 * Math.random());
					checkNrand2 = false;
					i = 0;
				}
			}

			
			
			if (doorDown) {
				if (Maps.isClosedMap()) {
					while (nRand != 1 && nRand != 2 && nRand != 6 && nRand != 7 && nRand != 8 && nRand != 9
							&& nRand != 11) {
						nRand = (int) (22.0 * Math.random());
						checkNrand2 = false;
					}
				} else {
					while (nRand != 12 && nRand != 13 && nRand != 17 && nRand != 18 && nRand != 19 && nRand != 20) {
						nRand = (int) (22.0 * Math.random());
						checkNrand2 = false;
					}
				}
			}

			if (doorLx) {
				if (Maps.isClosedMap()) {
					while (nRand != 2 && nRand != 3 && nRand != 5 && nRand != 7 && nRand != 8 && nRand != 10
							&& nRand != 11) {
						nRand = (int) (22.0 * Math.random());
						checkNrand2 = false;
					}
				} else {
					while (nRand != 13 && nRand != 14 && nRand != 16 && nRand != 18 && nRand != 19 && nRand != 21) {
						nRand = (int) (22.0 * Math.random());
						checkNrand2 = false;
					}
				}
			}

			if (doorUp) {
				if (Maps.isClosedMap()) {
					while (nRand != 3 && nRand != 4 && nRand != 6 && nRand != 8 && nRand != 9 && nRand != 10
							&& nRand != 11) {
						nRand = (int) (22.0 * Math.random());
						checkNrand2 = false;
					}
				} else {
					while (nRand != 14 && nRand != 15 && nRand != 17 && nRand != 19 && nRand != 20 && nRand != 21) {
						nRand = (int) (22.0 * Math.random());
						checkNrand2 = false;
					}
				}
			}

			if (doorDx) {
				if (Maps.isClosedMap()) {
					while (nRand != 1 && nRand != 4 && nRand != 5 && nRand != 7 && nRand != 9 && nRand != 10
							&& nRand != 11) {
						nRand = (int) (22.0 * Math.random());
						checkNrand2 = false;
					}
				} else {
					while (nRand != 12 && nRand != 15 && nRand != 16 && nRand != 18 && nRand != 20 && nRand != 21) {
						nRand = (int) (22.0 * Math.random());
						checkNrand2 = false;
					}
				}
			}
		}
		System.out.println("prossima mappa:"+nRand);
		prossimaMappa=nRand;
		
		HashMap<String, Integer> prossimaStanza;
		prossimaStanza = new HashMap<String, Integer>();
		prossimaStanza.put("portaDown", -1);
		prossimaStanza.put("portaLeft", -1);
		prossimaStanza.put("portaUp", -1);
		prossimaStanza.put("portaRight", -1);
		saveDoorOpened.put(prossimaMappa, prossimaStanza);
		
		if (doorDown) {
			questaStanza.put("portaDown", prossimaMappa);
			prossimaStanza.put("portaUp", mappaAttuale);
		}
		if (doorLx) {
			questaStanza.put("portaLeft", prossimaMappa);
			prossimaStanza.put("portaRight", mappaAttuale);
		}
		if (doorUp) {
			questaStanza.put("portaUp", prossimaMappa);
			prossimaStanza.put("portaDown", mappaAttuale);
		}
		if (doorDx) {
			questaStanza.put("portaRight", prossimaMappa);
			prossimaStanza.put("portaLeft", mappaAttuale);
		}

		Maps.getIndexYetChoosen().add(prossimaMappa);
		Maps.setIndiceMappe(prossimaMappa);
		try {
			graphicGame.setBg(prossimaMappa);
		} catch (Exception e) {

			e.printStackTrace();
		}
		checkNrand2 = false;
	}
	
	
	
	//verifica se c'è una porta nella prossima casella

	public boolean door (int newX, int newY) {

		if(newX==2 && newY==7 || newX==3 && newY==7) {
			doorLx=true;
		}
		else if(newX==10 && newY==12) {
			doorDown=true;
		}
		else if(newX==18 && newY==7) {
			doorDx=true;
		}
		else if(newX==10 && newY==2) {
			doorUp=true;
		}
		
		if(room.getCellType(newX, newY) == Cell.DOOR) {
			return true;//porta trovata
		}
		else
			return false;
		
	}
	


	
	
	public static int getProssimaMappa() {
		return prossimaMappa;
	}

	public static void setProssimaMappa(int prossimaMappa) {
		Movement.prossimaMappa = prossimaMappa;
	}

	public static int getMappaAttuale() {
		return mappaAttuale;
	}

	public static void setMappaAttuale(int mappaAttuale) {
		Movement.mappaAttuale = mappaAttuale;
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

	public HashMap<Integer, HashMap<String, Integer>> getSaveDoorOpened() {
		return saveDoorOpened;
	}

	public void setSaveDoorOpened(HashMap<Integer, HashMap<String, Integer>> saveDoorOpened) {
		this.saveDoorOpened = saveDoorOpened;
	}

	public static boolean isCheckNrand2() {
		return checkNrand2;
	}

	public static void setCheckNrand2(boolean checkNrand2) {
		Movement.checkNrand2 = checkNrand2;
	}

	public static int getnRand() {
		return nRand;
	}

	public static void setnRand(int nRand) {
		Movement.nRand = nRand;
	}

	public static int getDir() {
		return dir;
	}


	public Maps getRoom() {
		return room;
	}

	public void setRoom(Maps room) {
		Movement.room = room;
	}

	public Hero getPg() {
		return pg;
	}

	public void setPg(Hero pg) {
		this.pg = pg;
	}

	public Movement(Hero pg, Maps map) {
		this.pg = pg;
		Movement.room = map;
	}

	public GraphicsGame getGraphicGame() {
		return graphicGame;
	}

	public void setGraphicGame(GraphicsGame graphicGame) {
		this.graphicGame = graphicGame;
	}

	public static boolean isDoorDx() {
		return doorDx;
	}

	public static void setDoorDx(boolean doorDx) {
		Movement.doorDx = doorDx;
	}

	public static boolean isDoorUp() {
		return doorUp;
	}

	public static void setDoorUp(boolean doorUp) {
		Movement.doorUp = doorUp;
	}

	public static boolean isDoorDown() {
		return doorDown;
	}

	public static void setDoorDown(boolean doorDown) {
		Movement.doorDown = doorDown;
	}

	public static boolean isDoorLx() {
		return doorLx;
	}

	public static void setDoorLx(boolean doorLx) {
		Movement.doorLx = doorLx;
	}

}


if (doorDown) {				
				newPosX = 600;
				newPosY = 140;
			}
			if (doorLx) {
				newPosX = 1050;
				newPosY = 410;
			}
			if (doorUp) {
				newPosX = 600;
				newPosY = 672;
			}
			if (doorDx) {
				newPosX = 150;
				newPosY = 420;
			}
*/