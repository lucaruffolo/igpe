package project.igpe.classes;

import java.util.HashMap;
import project.igpe.GUI.ChangeRoomScene;
import project.igpe.main.Main;

public class Movement {

    public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	private Maps room;
	private Hero pg;
	private static int dir;
	
	private GraphicsGame graphicGame;

	private static boolean doorDx = false;
	private static boolean doorUp = false;
	private static boolean doorDown = false;
	private static boolean doorLx = false;
	
	private static int nRand = -20;
	private static boolean checkNrand2 = false;
	
	//
	private HashMap<Integer, HashMap<String, Integer>> saveDoorOpened = new HashMap<Integer, HashMap<String,Integer>>();
	//private static int lastMap=0;
	private static int mappaAttuale = 0;
	private static int prossimaMappa;
	private boolean firstTime = true;
	//private Integer nRandConv;
	//private Integer nextRoom = -1;
	//
	
	
	public void move(int direction) {
		int posHeroX=pg.getX();
		int posHeroY=pg.getY();
		int newPosX=posHeroX;
		int newPosY=posHeroY;
		
		if(direction == MOVE_RIGHT) {
			newPosX=pg.getX()+1;
			setDir(MOVE_RIGHT);
			}
		else if(direction == MOVE_LEFT) {
			newPosX=pg.getX()-1;
			setDir(MOVE_LEFT);
		}
		else if(direction == MOVE_UP) {
			newPosY=pg.getY()-1;
			setDir(MOVE_UP);
		}
		else if(direction == MOVE_DOWN) {
			newPosY=pg.getY()+1;
			setDir(MOVE_DOWN);
		}
		GraphicHero.setImgDir(direction);
				
		
		if (door(newPosX,newPosY)) {
			
			if(GraphicsGame.getFirstRoom()==true) {
				Maps.getIndexYetChoosen().add(0);
			}
			
			HashMap<String, Integer> questaStanza = null;
			if (saveDoorOpened.containsKey(mappaAttuale)) {
				questaStanza = saveDoorOpened.get(mappaAttuale);
				//
				System.out.println("we"+ questaStanza);
				//
				
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
					ciuski(questaStanza);
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
				else if (doorDown && questaStanza.get("portaDown")!=-1) {
					ciuski(questaStanza);
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
					ciuski(questaStanza);
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
					ciuski(questaStanza);
				}
				
			} else {
				ciuski(questaStanza);
			}
			
			if (doorDown) {
				newPosX = 10;
				newPosY = 2;
			}
			if (doorLx) {
				newPosX = 18;
				newPosY = 7;
			}
			if (doorUp) {
				newPosX = 10;
				newPosY = 12;
			}
			if (doorDx) {
				newPosX = 2;
				newPosY = 7;
			}
			doorDown=false;
			doorDx=false;
			doorLx=false;
			doorUp=false;
			
			mappaAttuale=Maps.getIndiceMappe();
			
		}
		
		
		if(!collision(newPosX, newPosY)) {
			posHeroX=newPosX;
			posHeroY=newPosY;
		}
		
		collisionDamage(newPosX, newPosY);
		
		
		Hero.setX(posHeroX);
		Hero.setY(posHeroY);
	}
	
	public void ciuski(HashMap<String, Integer> questaStanza) {
		//
		System.out.println("sono qui");
		//
		questaStanza = new HashMap<String, Integer>();
		questaStanza.put("portaDown", -1);
		questaStanza.put("portaLeft", -1);
		questaStanza.put("portaUp", -1);
		questaStanza.put("portaRight", -1);
		saveDoorOpened.put(mappaAttuale, questaStanza);
	
	
		try {
			//MovementControl.setRipristinoGame(Main.window.getScene());
			//ChangeRoomScene.changeRoom();

			nRand = (int) (23.0 * Math.random());

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
					while (nRand != 1 && nRand != 2 && nRand != 6 && nRand != 7 && nRand != 8 && nRand != 9
							&& nRand != 11 && nRand != 12 && nRand != 13 && nRand != 17 && nRand != 18
							&& nRand != 19 && nRand != 20 && nRand != 22) {
						nRand = (int) (23.0 * Math.random());
						checkNrand2 = false;
					}
				}

				if (doorLx) {
					while (nRand != 2 && nRand != 3 && nRand != 5 && nRand != 7 && nRand != 8 && nRand != 10
							&& nRand != 11 && nRand != 13 && nRand != 14 && nRand != 16 && nRand != 18
							&& nRand != 19 && nRand != 21 && nRand != 22) {
						nRand = (int) (23.0 * Math.random());
						checkNrand2 = false;
					}
				}

				if (doorUp) {
					while (nRand != 3 && nRand != 4 && nRand != 6 && nRand != 8 && nRand != 9 && nRand != 10
							&& nRand != 11 && nRand != 14 && nRand != 15 && nRand != 17 && nRand != 19
							&& nRand != 20 && nRand != 21 && nRand != 22) {
						nRand = (int) (23.0 * Math.random());
						checkNrand2 = false;
					}
				}

				if (doorDx) {
					while (nRand != 1 && nRand != 4 && nRand != 5 && nRand != 7 && nRand != 9 && nRand != 10
							&& nRand != 11 && nRand != 12 && nRand != 15 && nRand != 17 && nRand != 18
							&& nRand != 20 && nRand != 21 && nRand != 22) {
						nRand = (int) (23.0 * Math.random());
						checkNrand2 = false;
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
				//
				System.out.println(questaStanza.get("portaDown"));
				prossimaStanza.put("portaUp", mappaAttuale);
				//
				System.out.println(prossimaStanza.get("portaUp"));
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
			graphicGame.setBg(prossimaMappa);
			checkNrand2 = false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			return true;//porta trovata
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
					//aggiungere schermata morte
				}
			}
				
			if (room.getCellType(newX, newY) == Cell.FALLINGDOWN) {
				Hero.setLife(0);
				//aggiungere schermata morte
			}
	}
	
	
	



	//
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

	
	/*
	public Integer getNextRoom() {
		return nextRoom;
	}


	public void setNextRoom(Integer nextRoom) {
		this.nextRoom = nextRoom;
	}

	
	public Integer getnRandConv() {
		return nRandConv;
	}


	public void setnRandConv(Integer nRandConv) {
		this.nRandConv = nRandConv;
	}
	*/
	
	public boolean isFirstTime() {
		return firstTime;
	}


	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

	/*
	public static int getLastMap() {
		return lastMap;
	}


	public static void setLastMap(int lastMap) {
		Movement.lastMap = lastMap;
	}
	*/

	public HashMap<Integer, HashMap<String, Integer>> getSaveDoorOpened() {
		return saveDoorOpened;
	}


	public void setSaveDoorOpened(HashMap<Integer, HashMap<String, Integer>> saveDoorOpened) {
		this.saveDoorOpened = saveDoorOpened;
	}

	//
	
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

	public static void setDir(int direzione) {
		dir = direzione;
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
