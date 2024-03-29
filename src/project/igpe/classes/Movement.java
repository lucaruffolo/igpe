package project.igpe.classes;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.AudioClip;
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
	
	public static HashMap<Integer, HashMap<String, Integer>> saveDoorOpened = new HashMap<Integer, HashMap<String,Integer>>();
	private static int mappaAttuale = 0;
	private static int prossimaMappa;
	
	public int dacancellare = 0;
	
	private static AudioClip key= new AudioClip(new File("src/project/igpe/sounds/key.wav").toURI().toString());
	
	
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
		collisionKey(x, y);
	}
	
	public static void collisionPistol(int newX, int newY) {
		
		if (room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.PISTOL) {		
			Hero.takePistol = true;
			room.setCellType(pixelInMatrixX(newX), pixelInMatrixY(newY), Cell.EMPTY); //elimino cella pistol
				
		}
	}
	
	public static void collisionKey(int newX, int newY) {
		if(GraphicsGame.EnemySpawn && GraphicsGame.nemico.isAlive == false &&
				(Maps.getIndiceMappe() == 23 || Maps.getIndiceMappe() == 24 ||
				Maps.getIndiceMappe() == 25 || Maps.getIndiceMappe() == 26 || Maps.getIndiceMappe() == 31 
				|| Maps.getIndiceMappe() == 32 || Maps.getIndiceMappe() == 33 || Maps.getIndiceMappe() == 34)) {
			if (room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.ENEMYKEY) {		
				Hero.takeKey = true;
				key.play();
				room.setCellType(pixelInMatrixX(newX), pixelInMatrixY(newY), Cell.EMPTY); //elimino cella key	
			}
		}
	}
	
	public static void collisionHeart(int newX, int newY) {
		
		if (room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.HEART
				|| room.getCellType(pixelInMatrixX(newX+Hero.getSize()), pixelInMatrixY(newY)) == Cell.HEART) {											
				int i = 0;
				if (Hero.getLife() < 100) {					
					while (i<25 && Hero.getLife()<100) {
						Hero.setLife(Hero.getLife()+1);
						i++;
					}
					room.setCellType(pixelInMatrixX(newX), pixelInMatrixY(newY), Cell.EMPTY);
					room.setCellType(pixelInMatrixX(newX+Hero.getSize()), pixelInMatrixY(newY), Cell.EMPTY);

					//elimino cella heart
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
			if (Maps.closedMap) {
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
			}else {
				if (doorDown) {				
					newPosX = 599;
					newPosY = 220;
				}
				if (doorLx) {
					newPosX = 977;
					newPosY = 416;
				}
				if (doorUp) {
					newPosX = 603;
					newPosY = 600;
				}
				if (doorDx) {
					newPosX = 225;
					newPosY = 420;
				}
				/*
				if (doorDown) {				
					newPosX = Settings.x/2;
					newPosY = Settings.y/2;
				}
				if (doorLx) {	
					newPosX = Settings.x/2;
					newPosY = Settings.y/2;
				}
				if (doorUp) {	
					newPosX = Settings.x/2;
					newPosY = Settings.y/2;
				}
				if (doorDx) {	
					newPosX = Settings.x/2;
					newPosY = Settings.y/2;
				}*/
			}
			
			Hero.setX(newPosX);
			Hero.setY(newPosY);
			
			doorDown = false;
			doorDx = false;
			doorLx = false;
			doorUp = false;

			GraphicsGame.setFirstRoom(false);
			Hero.clearAmmo();
			resetEnemy();
			Enemy.resetEnemy();
		}
	
	
		
	}
	
	public static void resetEnemy() {
		if (GraphicsGame.EnemySpawn)
			GraphicsGame.nemico.setLife(0);
		if (GraphicsGame.BossSpawn)
			GraphicsGame.boss.setLife(1000);
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
	
	public static boolean collisionObstacleDmg(int newX, int newY) { //per enemy
		
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.OBSTACLEDAMAGE
			|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.OBSTACLEDAMAGE
				|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.OBSTACLEDAMAGE
					|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.OBSTACLEDAMAGE)
							return true;
					
		return false;		
	}
	
	public static boolean collisionFall(int newX, int newY) {
		
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.FALLINGDOWN
			|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.FALLINGDOWN
				|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.FALLINGDOWN
					|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.FALLINGDOWN)
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
	
	public static void collisionEnemy(int x, int y) {
		if (GraphicsGame.EnemySpawn) {
			if (x>= GraphicsGame.nemico.getX()-Enemy.getSize() && x<= GraphicsGame.nemico.getX()+Enemy.getSize()-10 
					&& y>= GraphicsGame.nemico.getY()-Enemy.getSize() && y<= GraphicsGame.nemico.getY()+Enemy.getSize()-10){
				
				if (Hero.getLife() > 0 && GraphicsGame.nemico.isAlive)
					Hero.setLife(Hero.getLife()-0.1);					
			}
		}
		if (GraphicsGame.BossSpawn) {
			if (x>= GraphicsGame.boss.getX()-GraphicsGame.boss.getSize() && x<= GraphicsGame.boss.getX()+GraphicsGame.boss.getSize()-10 
					&& y>= GraphicsGame.boss.getY()-GraphicsGame.boss.getSize() && y<= GraphicsGame.boss.getY()+GraphicsGame.boss.getSize()-10){
				
				if (Hero.getLife() > 0 && GraphicsGame.boss.isAlive 
						&& (Maps.getIndiceMappe() == 27
							|| Maps.getIndiceMappe() == 28 
								|| Maps.getIndiceMappe() == 29 
									|| Maps.getIndiceMappe() == 30))
					Hero.setLife(Hero.getLife()-0.1);						
			}
		}
	}
	
	public static boolean collisionHero(int x, int y) {
		
		if (x>= GraphicsGame.nemico.getX()-Enemy.getSize() && x<= GraphicsGame.nemico.getX()+Enemy.getSize() 
			&& y>= GraphicsGame.nemico.getY()-Enemy.getSize() && y<= GraphicsGame.nemico.getY()+Enemy.getSize()){												
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
		
		if (room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY)) == Cell.FIRE
				|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.FIRE
					|| room.getCellType(pixelInMatrixX(newX+Settings.obstacleSize), pixelInMatrixY(newY+Settings.obstacleSize)) == Cell.FIRE
						|| room.getCellType(pixelInMatrixX(newX), pixelInMatrixY(newY)) == Cell.FIRE) {
				
				if (Hero.getLife() > 0)
					Hero.setLife(Hero.getLife()-0.1);
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
		
		
		GraphicsGame.nRandObstacles = (int) (2.0 * Math.random());
	//	System.out.println("mappa pre"+mappaAttuale);
		mappaAttuale=Maps.getIndiceMappe();
		
		if(GraphicsGame.getFirstRoom()==true) {
			questaStanza = new HashMap<String, Integer>();
			questaStanza.put("portaDown", -1);
			questaStanza.put("portaLeft", -1);
			questaStanza.put("portaUp", -1);
			questaStanza.put("portaRight", -1);
			saveDoorOpened.put(mappaAttuale, questaStanza);
	//		System.out.println(saveDoorOpened.get(mappaAttuale));
		}
		
		
		if (saveDoorOpened.containsKey(mappaAttuale)) {
			questaStanza = saveDoorOpened.get(mappaAttuale);
			if (doorUp && questaStanza.get("portaUp")!=-1) {
				Maps.setIndiceMappe(questaStanza.get("portaUp"));
		//		System.out.println("questa stanza:"+ questaStanza.get("portaUp"));
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
	//			System.out.println("questa stanza:"+ questaStanza.get("portaDown"));
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
	//			System.out.println("questa stanza:"+ questaStanza.get("portaLeft"));
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
		if(Maps.isControllo()) {
			if (1 < Maps.getIndexYetChoosen().size()) {
				if (Maps.getIndexYetChoosen().get(1) == Maps.getIndiceMappe()) {
					Maps.mapKey = false;
					Enemy.setKeyDrop(false);
		//			System.out.println("disabilito drop");
				} 
			}
			if(Maps.getIndiceMappe()==23 || Maps.getIndiceMappe()==24 || Maps.getIndiceMappe()==25 || Maps.getIndiceMappe()==26 || Maps.getIndiceMappe()==31 || Maps.getIndiceMappe()==32 || Maps.getIndiceMappe()==33 || Maps.getIndiceMappe()==34) {
				Maps.setMapKey(true);
			}
		}
		
		if(Maps.isClosedMap()) {
			if(Maps.getIndiceMappe()==23) {
				Maps.setMapBoss(true);
				Maps.setMapKey(true);
			}
			else if(Maps.getIndiceMappe()==24) {
				Maps.setMapBoss(true);
				Maps.setMapKey(true);
			}
			else if(Maps.getIndiceMappe()==25) {
				Maps.setMapBoss(true);
				Maps.setMapKey(true);
			}
			else if(Maps.getIndiceMappe()==26) {
				Maps.setMapBoss(true);
				Maps.setMapKey(true);
			}
		}
		else {
			if(Maps.getIndiceMappe()==31){
				Maps.setMapBoss(true);
				Maps.setMapKey(true);
			}
			else if(Maps.getIndiceMappe()==32) {
				Maps.setMapBoss(true);
				Maps.setMapKey(true);
			}
			else if(Maps.getIndiceMappe()==33) {
				Maps.setMapBoss(true);
				Maps.setMapKey(true);
			}
			else if(Maps.getIndiceMappe()==34) {
				Maps.setMapBoss(true);
				Maps.setMapKey(true);
			}
		}
		

	}
	
	public static int newRandRoom () {
		nRand = (int) (22.0 * Math.random());
		for (int i = 0; i < Maps.getIndexYetChoosen().size(); i++) {
			while (nRand == Maps.getIndexYetChoosen().get(i)) {
				nRand = (int) (22.0 * Math.random());
		//		System.out.println(nRand);
				i=0;
			}
		}
		return nRand;
	}
	
	
	public static void newRoom(HashMap<String, Integer> questaStanza) {
		
		if(Maps.getIndexYetChoosen().size()==2 && Maps.isMapBoss()==false) {
			if(Maps.isClosedMap()) {
				if (doorDown) {
					Maps.setIndiceMappe(24);
					nRand=24;
					try {
						graphicGame.setBg(24);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					
				else if (doorDx) {
					Maps.setIndiceMappe(23);
					nRand=23;
					try {
						graphicGame.setBg(23);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else if (doorLx) {
					Maps.setIndiceMappe(26);
					nRand=26;
					try {
						graphicGame.setBg(26);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else if (doorUp) {
					Maps.setIndiceMappe(25);
					nRand=25;
					try {
						graphicGame.setBg(25);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			else {
				if (doorDown) {
					Maps.setIndiceMappe(32);
					nRand=32;
					try {
						graphicGame.setBg(32);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					
				else if (doorDx) {
					Maps.setIndiceMappe(31);
					nRand=31;
					try {
						graphicGame.setBg(31);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else if (doorLx) {
					Maps.setIndiceMappe(34);
					nRand=34;
					try {
						graphicGame.setBg(34);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else if (doorUp) {
					Maps.setIndiceMappe(33);
					nRand=33;
					try {
						graphicGame.setBg(33);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
				
			Maps.setMapBoss(true);
			Enemy.setKeyDrop(true);
			Maps.setMapKey(true);
			Maps.setControllo(true);
	//		System.out.println("abilito drop");
		}
		else if(Maps.isMapBoss()==true && Maps.isMapKey()==true && Hero.isTakeKey()==true) {
		//	System.out.println("creo mappa boss");
			if (doorDown) {
				Maps.setIndiceMappe(29);
				nRand=29;
				try {
					graphicGame.setBg(29);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
			else if (doorDx) {
				Maps.setIndiceMappe(30);
				nRand=30;
				try {
					graphicGame.setBg(30);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (doorLx) {
				Maps.setIndiceMappe(27);
				nRand=27;
				try {
					graphicGame.setBg(27);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (doorUp) {
				Maps.setIndiceMappe(28);
				nRand=28;
				try {
					graphicGame.setBg(28);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			Maps.setMapBoss(false);
		}
		else {
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
				
			}
	//		System.out.println("prossima mappa:"+nRand);
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
		//		System.out.println(saveDoorOpened.get(mappaAttuale)+"giu");
		//		System.out.println(saveDoorOpened.get(prossimaMappa)+"giu");
			}
			if (doorLx) {
				questaStanza.put("portaLeft", prossimaMappa);
				prossimaStanza.put("portaRight", mappaAttuale);
		//		System.out.println(saveDoorOpened.get(mappaAttuale)+"lx");
		//		System.out.println(saveDoorOpened.get(prossimaMappa)+"lx");
			}
			if (doorUp) {
				questaStanza.put("portaUp", prossimaMappa);
				prossimaStanza.put("portaDown", mappaAttuale);
		//		System.out.println(saveDoorOpened.get(mappaAttuale)+"sopra");
		//		System.out.println(saveDoorOpened.get(prossimaMappa)+"sopra");
			}
			if (doorDx) {
				questaStanza.put("portaRight", prossimaMappa);
				prossimaStanza.put("portaLeft", mappaAttuale);
		//		System.out.println(saveDoorOpened.get(mappaAttuale)+"DESTRO");
		//		System.out.println(saveDoorOpened.get(prossimaMappa)+"DESTRO");
			}
	
			Maps.getIndexYetChoosen().add(prossimaMappa);
			Maps.setIndiceMappe(prossimaMappa);
			try {
				graphicGame.setBg(prossimaMappa);
			} catch (Exception e) {
	
				e.printStackTrace();
			}
			
	}
	
	
	
	//verifica se c'� una porta nella prossima casella

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