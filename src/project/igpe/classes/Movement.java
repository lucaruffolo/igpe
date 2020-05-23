package project.igpe.classes;

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
	
	private static int nRand = 0;
	private static boolean checkNrand2 = false;
	
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
				
		
		if (door(newPosX,newPosY)) {  //controllo porta in nuova posizione
			try {
				MovementControl.setRipristinoGame(Main.window.getScene());
				GraphicsGame.setTransition(true);				
				//ChangeRoomScene.changeRoom();
				
			    nRand = (int)(14.0 * Math.random());
			    
			    while(!checkNrand2) {
			    	checkNrand2=true;
					for (int i = 0; i < Maps.getIndexYetChoosen().size(); i++) {
						while (nRand == Maps.getIndexYetChoosen().get(i)) {
							nRand = (int) (14.0 * Math.random());
							checkNrand2=false;
							i=0;
						}
					}
					System.out.println("ciao");
					if (doorDown) {
				    	  while (nRand != 1 && nRand != 2 && nRand != 6 && nRand != 7 && nRand != 8 && nRand != 9 && nRand != 11) {
				    		  nRand = (int) (14.0 * Math.random());
				    		  checkNrand2=false;
				    	  }
				    } 
				    
				    if (doorLx) {
				    	  while (nRand != 2 && nRand != 3 && nRand != 5 && nRand != 7 && nRand != 8 && nRand != 10 && nRand != 11) {
				    		  nRand = (int) (14.0 * Math.random());
				    		  checkNrand2=false;
				    	  }
				    }
				    
				    if (doorUp) {
				    	while (nRand != 3 && nRand != 4 && nRand != 6 && nRand != 8 && nRand != 9 && nRand != 10 && nRand != 11) {
				    		  nRand = (int) (14.0 * Math.random());
				    		  checkNrand2=false;
				    	  }
				    }
				    
				    if (doorDx) {
				    	while (nRand != 1 && nRand != 4 && nRand != 5 && nRand != 7 && nRand != 9 && nRand != 10 && nRand != 11) {
				    		  nRand = (int) (14.0 * Math.random());
				    		  checkNrand2=false;
				    	  }
				    }
			    }	
			    
				System.out.println(nRand);
			    Maps.getIndexYetChoosen().add(nRand);
				Maps.setIndiceMappe(nRand);				
				graphicGame.setBg(nRand);
				checkNrand2=false;
				
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
