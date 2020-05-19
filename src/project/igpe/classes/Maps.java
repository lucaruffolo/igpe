package project.igpe.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Maps {
	
	private static Cell[][] cella = new Cell[Settings.x][Settings.y];
	
	private static ArrayList<Integer> indexYetChoosen = new ArrayList<Integer>();
	private static int indiceMappe=0;

	private static File firstRoomImg;
	private static String firstRoomTxt;
	
	private static HashMap<Integer, File> upImg = new HashMap<Integer, File>();
	private static HashMap<Integer, String> upTxt = new HashMap<Integer, String>();
	private static HashMap<Integer, File> downImg = new HashMap<Integer, File>();
	private static HashMap<Integer, String> downTxt = new HashMap<Integer, String>();
	private static HashMap<Integer, File> leftImg = new HashMap<Integer, File>();
	private static HashMap<Integer, String> leftTxt = new HashMap<Integer, String>();
	private static HashMap<Integer, File> rightImg = new HashMap<Integer, File>();
	private static HashMap<Integer, String> rightTxt = new HashMap<Integer, String>();

	public Maps() {
			
		loadcontenitoreMappe();
		if(!GraphicsGame.getFirstRoom())
		{
			try {
				loadMap(indiceMappe); //int random come paramentro
			} catch (Exception e) {
				System.out.println("non carica metodo LoadMap");
				e.printStackTrace();
			}
		}
		else {
			try {
				loadFirstRoom();
			} catch (Exception e) {
				System.out.println("non carica metodo LoadFirstMap");
				e.printStackTrace();
			}
		}
	}	

	public static void loadcontenitoreMappe() {
		
		File img0 = new File("src/project/igpe/images/mappa2(SX-SU).png");
		File img1 = new File("src/project/igpe/images/mappa2(SX-SU).png");
		File img2 = new File("src/project/igpe/images/mappa2(SU-DX).png");
		File img3 = new File("src/project/igpe/images/mappa2(DX-GIU).png");
		File img4 = new File("src/project/igpe/images/mappa2(GIU-SX).png");
		File img5 = new File("src/project/igpe/images/mappa2(SX-DX).png");
		File img6 = new File("src/project/igpe/images/mappa2(SU-GIU).png");
		File img7 = new File("src/project/igpe/images/mappa3(SX-SU-DX).png");
		File img8 = new File("src/project/igpe/images/mappa3(SU-DX-GIU).png");
		File img9 = new File("src/project/igpe/images/mappa3(SU-SX-GIU).png");
		File img10 = new File("src/project/igpe/images/mappa3(SX-GIU-DX).png");
		File img11 = new File("src/project/igpe/images/mappa4.png");
		//File img12 = new File("src/project/igpe/images/mappaAcqua.png");
		
		String txt0 = new String("src/project/igpe/maps/Template2door(SX-SU).txt");
		String txt1 = new String("src/project/igpe/maps/Template2door(SX-SU).txt");
		String txt2 = new String("src/project/igpe/maps/Template2door(SU-DX).txt");
		String txt3 = new String("src/project/igpe/maps/Template2door(DX-GIU).txt");
		String txt4 = new String("src/project/igpe/maps/Template2door(GIU-SX).txt");
		String txt5 = new String("src/project/igpe/maps/Template2door(SX-DX).txt");
		String txt6 = new String("src/project/igpe/maps/Template2door(SU-GIU).txt");
		String txt7 = new String("src/project/igpe/maps/Template3door(SX-SU-DX).txt");
		String txt8 = new String("src/project/igpe/maps/Template3door(SU-DX-GIU).txt");
		String txt9 = new String("src/project/igpe/maps/Template3door(SU-SX-GIU).txt");
		String txt10 = new String("src/project/igpe/maps/Template3door(SX-GIU-DX).txt");
		String txt11 = new String("src/project/igpe/maps/Template4door.txt");
		//String txt12 = new String("src/project/igpe/maps/Template4doorACQUA.txt");
		
		firstRoomImg = img0;
		firstRoomTxt = txt0;
		
		upImg.put(1, img1);
		upTxt.put(1, txt1);
		upImg.put(2, img2);
		upTxt.put(2, txt2);
		upImg.put(6, img6);
		upTxt.put(6, txt6);
		upImg.put(7, img7);
		upTxt.put(7, txt7);
		upImg.put(8, img8);
		upTxt.put(8, txt8);
		upImg.put(9, img9);
		upTxt.put(9, txt9);
		upImg.put(11, img11);
		upTxt.put(11, txt11);
		
		downImg.put(3, img3);
		downTxt.put(3, txt3);
		downImg.put(4, img4);
		downTxt.put(4, txt4);
		downImg.put(6, img6);
		downTxt.put(6, txt6);
		downImg.put(8, img8);
		downTxt.put(8, txt8);
		downImg.put(9, img9);
		downTxt.put(9, txt9);
		downImg.put(10, img10);
		downTxt.put(10, txt10);
		downImg.put(11, img11);
		downTxt.put(11, txt11);
		
		leftImg.put(1, img1);
		leftTxt.put(1, txt1);
		leftImg.put(4, img4);
		leftTxt.put(4, txt4);
		leftImg.put(5, img5);
		leftTxt.put(5, txt5);
		leftImg.put(7, img7);
		leftTxt.put(7, txt7);
		leftImg.put(9, img9);
		leftTxt.put(9, txt9);
		leftImg.put(10, img10);
		leftTxt.put(10, txt10);
		leftImg.put(11, img11);
		leftTxt.put(11, txt11);
		
		rightImg.put(2, img2);
		rightTxt.put(2, txt2);
		rightImg.put(3, img3);
		rightTxt.put(3, txt3);
		rightImg.put(5, img5);
		rightTxt.put(5, txt5);
		rightImg.put(7, img7);
		rightTxt.put(7, txt7);
		rightImg.put(8, img8);
		rightTxt.put(8, txt8);
		rightImg.put(10, img10);
		rightTxt.put(10, txt10);
		rightImg.put(11, img11);
		rightTxt.put(11, txt11);
		
	}
	
	public static void loadFirstRoom() throws Exception{
		int riga = 0;
		int colonne = 0;
		 try {
				BufferedReader b = new BufferedReader(new FileReader(firstRoomTxt));
				while (b.ready()) {				
					String costruzioneRiga;
					String line = b.readLine();
					StringTokenizer tok = new StringTokenizer(line, " ");					
						while (tok.hasMoreTokens()) {
							costruzioneRiga = tok.nextToken();
							if (costruzioneRiga.equals("0")) {
								cella[riga][colonne++] = new Cell(Cell.EMPTY);
							}				
							if (costruzioneRiga.equals("3")) {
								cella[riga][colonne++] = new Cell(Cell.WALL);
							} 
							if (costruzioneRiga.equals("5")) {
								cella[riga][colonne++] = new Cell(Cell.DOOR);
							} 
							if (costruzioneRiga.equals("6")) {
								cella[riga][colonne++] = new Cell(Cell.OBSTACLE);
							}
							if (costruzioneRiga.equals("7")) {
								cella[riga][colonne++] = new Cell(Cell.HEAL);
							}
							if (costruzioneRiga.equals("8")) {
								cella[riga][colonne++] = new Cell(Cell.OBSTACLEDAMAGE);
							}
							if (costruzioneRiga.equals("9")) {
								cella[riga][colonne++] = new Cell(Cell.FALLINGDOWN);
							}								
						} 
					riga++;
					colonne=0;
				}
				b.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public static void loadMap(int indiceMappe) throws Exception { //int random come paramentro
		if (Movement.isDoorDown()) {
			int riga = 0;
			int colonne = 0;
			try {
				BufferedReader b = new BufferedReader(new FileReader(upTxt.get(indiceMappe)));
				while (b.ready()) {
					String costruzioneRiga;
					String line = b.readLine();
					StringTokenizer tok = new StringTokenizer(line, " ");
					while (tok.hasMoreTokens()) {
						costruzioneRiga = tok.nextToken();
						if (costruzioneRiga.equals("0")) {
							cella[riga][colonne++] = new Cell(Cell.EMPTY);
						}
						if (costruzioneRiga.equals("3")) {
							cella[riga][colonne++] = new Cell(Cell.WALL);
						}
						if (costruzioneRiga.equals("5")) {
							cella[riga][colonne++] = new Cell(Cell.DOOR);
						}
						if (costruzioneRiga.equals("6")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLE);
						}
						if (costruzioneRiga.equals("7")) {
							cella[riga][colonne++] = new Cell(Cell.HEAL);
						}
						if (costruzioneRiga.equals("8")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLEDAMAGE);
						}
						if (costruzioneRiga.equals("9")) {
							cella[riga][colonne++] = new Cell(Cell.FALLINGDOWN);
						}
					}
					riga++;
					colonne = 0;
				}
				b.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		}
		
		if (Movement.isDoorDx()) {
			int riga = 0;
			int colonne = 0;
			try {
				BufferedReader b = new BufferedReader(new FileReader(leftTxt.get(indiceMappe)));
				while (b.ready()) {
					String costruzioneRiga;
					String line = b.readLine();
					StringTokenizer tok = new StringTokenizer(line, " ");
					while (tok.hasMoreTokens()) {
						costruzioneRiga = tok.nextToken();
						if (costruzioneRiga.equals("0")) {
							cella[riga][colonne++] = new Cell(Cell.EMPTY);
						}
						if (costruzioneRiga.equals("3")) {
							cella[riga][colonne++] = new Cell(Cell.WALL);
						}
						if (costruzioneRiga.equals("5")) {
							cella[riga][colonne++] = new Cell(Cell.DOOR);
						}
						if (costruzioneRiga.equals("6")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLE);
						}
						if (costruzioneRiga.equals("7")) {
							cella[riga][colonne++] = new Cell(Cell.HEAL);
						}
						if (costruzioneRiga.equals("8")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLEDAMAGE);
						}
						if (costruzioneRiga.equals("9")) {
							cella[riga][colonne++] = new Cell(Cell.FALLINGDOWN);
						}
					}
					riga++;
					colonne = 0;
				}
				b.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		}
		
		if (Movement.isDoorLx()) {
			int riga = 0;
			int colonne = 0;
			try {
				BufferedReader b = new BufferedReader(new FileReader(rightTxt.get(indiceMappe)));
				while (b.ready()) {
					String costruzioneRiga;
					String line = b.readLine();
					StringTokenizer tok = new StringTokenizer(line, " ");
					while (tok.hasMoreTokens()) {
						costruzioneRiga = tok.nextToken();
						if (costruzioneRiga.equals("0")) {
							cella[riga][colonne++] = new Cell(Cell.EMPTY);
						}
						if (costruzioneRiga.equals("3")) {
							cella[riga][colonne++] = new Cell(Cell.WALL);
						}
						if (costruzioneRiga.equals("5")) {
							cella[riga][colonne++] = new Cell(Cell.DOOR);
						}
						if (costruzioneRiga.equals("6")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLE);
						}
						if (costruzioneRiga.equals("7")) {
							cella[riga][colonne++] = new Cell(Cell.HEAL);
						}
						if (costruzioneRiga.equals("8")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLEDAMAGE);
						}
						if (costruzioneRiga.equals("9")) {
							cella[riga][colonne++] = new Cell(Cell.FALLINGDOWN);
						}
					}
					riga++;
					colonne = 0;
				}
				b.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		}
		
		if (Movement.isDoorUp()) {
			int riga = 0;
			int colonne = 0;
			try {
				BufferedReader b = new BufferedReader(new FileReader(downTxt.get(indiceMappe)));
				while (b.ready()) {
					String costruzioneRiga;
					String line = b.readLine();
					StringTokenizer tok = new StringTokenizer(line, " ");
					while (tok.hasMoreTokens()) {
						costruzioneRiga = tok.nextToken();
						if (costruzioneRiga.equals("0")) {
							cella[riga][colonne++] = new Cell(Cell.EMPTY);
						}
						if (costruzioneRiga.equals("3")) {
							cella[riga][colonne++] = new Cell(Cell.WALL);
						}
						if (costruzioneRiga.equals("5")) {
							cella[riga][colonne++] = new Cell(Cell.DOOR);
						}
						if (costruzioneRiga.equals("6")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLE);
						}
						if (costruzioneRiga.equals("7")) {
							cella[riga][colonne++] = new Cell(Cell.HEAL);
						}
						if (costruzioneRiga.equals("8")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLEDAMAGE);
						}
						if (costruzioneRiga.equals("9")) {
							cella[riga][colonne++] = new Cell(Cell.FALLINGDOWN);
						}
					}
					riga++;
					colonne = 0;
				}
				b.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		}
		      
	}
	
	

	public Cell[][] getCella() {
		return cella;
	}
	
	public int getCellType(int x, int y) {
		return cella[x][y].getType();
	}

	public static int getIndiceMappe() {
		return indiceMappe;
	}


	public static void setIndiceMappe(int indiceMappe) {
		Maps.indiceMappe = indiceMappe;
	}
	

	public static File getFirstRoomImg() {
		return firstRoomImg;
	}

	public static void setFirstRoomImg(File firstRoomImg) {
		Maps.firstRoomImg = firstRoomImg;
	}

	public static String getFirstRoomTxt() {
		return firstRoomTxt;
	}

	public static void setFirstRoomTxt(String firstRoomTxt) {
		Maps.firstRoomTxt = firstRoomTxt;
	}

	public static HashMap<Integer, File> getUpImg() {
		return upImg;
	}

	public static void setUpImg(HashMap<Integer, File> upImg) {
		Maps.upImg = upImg;
	}

	public static HashMap<Integer, String> getUpTxt() {
		return upTxt;
	}

	public static void setUpTxt(HashMap<Integer, String> upTxt) {
		Maps.upTxt = upTxt;
	}

	public static HashMap<Integer, File> getDownImg() {
		return downImg;
	}

	public static void setDownImg(HashMap<Integer, File> downImg) {
		Maps.downImg = downImg;
	}

	public static HashMap<Integer, String> getDownTxt() {
		return downTxt;
	}

	public static void setDownTxt(HashMap<Integer, String> downTxt) {
		Maps.downTxt = downTxt;
	}

	public static HashMap<Integer, File> getLeftImg() {
		return leftImg;
	}

	public static void setLeftImg(HashMap<Integer, File> leftImg) {
		Maps.leftImg = leftImg;
	}

	public static HashMap<Integer, String> getLeftTxt() {
		return leftTxt;
	}

	public static void setLeftTxt(HashMap<Integer, String> leftTxt) {
		Maps.leftTxt = leftTxt;
	}

	public static HashMap<Integer, File> getRightImg() {
		return rightImg;
	}

	public static void setRightImg(HashMap<Integer, File> rightImg) {
		Maps.rightImg = rightImg;
	}

	public static HashMap<Integer, String> getRightTxt() {
		return rightTxt;
	}

	public static void setRightTxt(HashMap<Integer, String> rightTxt) {
		Maps.rightTxt = rightTxt;
	}

	public static ArrayList<Integer> getIndexYetChoosen() {
		return indexYetChoosen;
	}

	public static void setIndexYetChoosen(ArrayList<Integer> indexYetChoosen) {
		Maps.indexYetChoosen = indexYetChoosen;
	}
	
	
	
	
	/* DA modificare per aggiugere ostacoli random
	count = 0;
	while(count < 4) {
		int randX = r.nextInt(Settings.cellSize-1)+1;
		for(int j = 0; j < blocks[randX].length/2; j++) {
			if(blocks[randX][j].getType()==Block.EMPTY) {
				blocks[randX][j].setType(Block.WALL);
			}
		}
		count++;	
	}
	*/
}