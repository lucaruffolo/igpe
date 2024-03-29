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
	
	public static ArrayList<Integer> indexYetChoosen = new ArrayList<Integer>();
	private static int indiceMappe;

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
	
	public static boolean closedMap = false;
	public static boolean mapBoss = false;
	public static boolean mapKey = false;
	public static boolean controllo = false;
	
	public Maps() {
		
		randomMapFirst();
		
		loadcontenitoreMappe();
		if(!GraphicsGame.getFirstRoom())
		{
			try {
				//System.out.println("carico mappa"+indiceMappe);
				loadMap(indiceMappe); //int random come paramentro
			} catch (Exception e) {
				System.out.println("non carica metodo LoadMap");
				e.printStackTrace();
			}
		}
		else {
			try {
			//	System.out.println("carico primo mappa"+indiceMappe);
				loadFirstRoom();
			} catch (Exception e) {
				System.out.println("non carica metodo LoadFirstMap");
				e.printStackTrace();
			}
		}
	}	
	
	public static void randomMapFirst() {
		int nRand = (int) (2.0 * Math.random());
		if(nRand == 0) {
			setClosedMap(true);
			setIndiceMappe(0);
		}
		else {
			setClosedMap(false);
			setIndiceMappe(22);
		}
		//
	//	System.out.println("primo"+indiceMappe+closedMap);
		//
	}

	public static void loadcontenitoreMappe() {
		
		File img0 = new File("src/project/igpe/images/mappaSpawn.png");
		String txt0 = new String("src/project/igpe/maps/TemplateSpawn.txt");
		
		File img22 = new File("src/project/igpe/images/mappaAcqua4.png");
		String txt22 = new String("src/project/igpe/maps/Template4doorACQUA.txt");
		
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
		
		File img12 = new File("src/project/igpe/images/mappaAcqua2(SX-SU).png");
		File img13 = new File("src/project/igpe/images/mappaAcqua2(SU-DX).png");
		File img14 = new File("src/project/igpe/images/mappaAcqua2(DX-GIU).png");
		File img15 = new File("src/project/igpe/images/mappaAcqua2(GIU-SX).png");
		File img16 = new File("src/project/igpe/images/mappaAcqua2(SX-DX).png");
		File img17 = new File("src/project/igpe/images/mappaAcqua2(SU-GIU).png");
		File img18 = new File("src/project/igpe/images/mappaAcqua3(SX-SU-DX).png");
		File img19 = new File("src/project/igpe/images/mappaAcqua3(SU-DX-GIU).png");
		File img20 = new File("src/project/igpe/images/mappaAcqua3(SU-SX-GIU).png");
		File img21 = new File("src/project/igpe/images/mappaAcqua3(SX-GIU-DX).png");
		
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
		
		String txt12 = new String("src/project/igpe/maps/Template2doorACQUA(SX-SU).txt");
		String txt13 = new String("src/project/igpe/maps/Template2doorACQUA(SU-DX).txt");
		String txt14 = new String("src/project/igpe/maps/Template2doorACQUA(DX-GIU).txt");
		String txt15 = new String("src/project/igpe/maps/Template2doorACQUA(GIU-SX).txt");
		String txt16 = new String("src/project/igpe/maps/Template2doorACQUA(SX-DX).txt");
		String txt17 = new String("src/project/igpe/maps/Template2doorACQUA(SU-GIU).txt");
		String txt18 = new String("src/project/igpe/maps/Template3doorACQUA(SX-SU-DX).txt");
		String txt19 = new String("src/project/igpe/maps/Template3doorACQUA(SU-DX-GIU).txt");
		String txt20 = new String("src/project/igpe/maps/Template3doorACQUA(SU-SX-GIU).txt");
		String txt21 = new String("src/project/igpe/maps/Template3doorACQUA(SX-GIU-DX).txt");
		
		//chiave
		File img23 = new File("src/project/igpe/images/mappaChiusaChiaveDx-Sx.png");
		File img24 = new File("src/project/igpe/images/mappaChiusaChiaveGiu-Su.png");
		File img25 = new File("src/project/igpe/images/mappaChiusaChiaveSu-Giu.png");
		File img26 = new File("src/project/igpe/images/mappaChiusaChiaveSx-Dx.png");
		
		String txt23 = new String("src/project/igpe/maps/Template2door(DX-SX)chiave.txt");
		String txt24 = new String("src/project/igpe/maps/Template2door(GIU-SU)chiave.txt");
		String txt25 = new String("src/project/igpe/maps/Template2door(SU-GIU)chiave.txt");
		String txt26 = new String("src/project/igpe/maps/Template2door(SX-DX)chiave.txt");
		
		File img31 = new File("src/project/igpe/images/mappaChiaveAcqua2(DX-SX).png");
		File img32 = new File("src/project/igpe/images/mappaChiaveAcqua2(GIU-SU).png");
		File img33 = new File("src/project/igpe/images/mappaChiaveAcqua2(SU-GIU).png");
		File img34 = new File("src/project/igpe/images/mappaChiaveAcqua2(SX-DX).png");
		
		String txt31 = new String("src/project/igpe/maps/Template2doorACQUA(DX-SX)chiave.txt");
		String txt32 = new String("src/project/igpe/maps/Template2doorACQUA(GIU-SU)chiave.txt");
		String txt33 = new String("src/project/igpe/maps/Template2doorACQUA(SU-GIU)chiave.txt");
		String txt34 = new String("src/project/igpe/maps/Template2doorACQUA(SX-DX)chiave.txt");
		
		
		//fine chiave
		//boss
		File img27 = new File("src/project/igpe/images/portaBossDx.png");
		File img28 = new File("src/project/igpe/images/portaBossGiu.png");
		File img29 = new File("src/project/igpe/images/portaBossSu.png");
		File img30 = new File("src/project/igpe/images/portaBossLx.png");
		
		String txt27 = new String("src/project/igpe/maps/TemplateBossDx.txt");
		String txt28 = new String("src/project/igpe/maps/TemplateBossGIU.txt");
		String txt29 = new String("src/project/igpe/maps/TemplateBossSU.txt");
		String txt30 = new String("src/project/igpe/maps/TemplateBossLx.txt");
		//fine boss
		if(closedMap) {
			firstRoomImg = img0;
			firstRoomTxt = txt0;
		}
		else {
			firstRoomImg = img22;
			firstRoomTxt = txt22;
		}
		
		
		
		upImg.put(0, img0);
		upTxt.put(0, txt0);
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
		upImg.put(12, img12);
		upTxt.put(12, txt12);
		upImg.put(13, img13);
		upTxt.put(13, txt13);
		upImg.put(17, img17);
		upTxt.put(17, txt17);
		upImg.put(18, img18);
		upTxt.put(18, txt18);
		upImg.put(19, img19);
		upTxt.put(19, txt19);
		upImg.put(20, img20);
		upTxt.put(20, txt20);
		upImg.put(22, img22);
		upTxt.put(22, txt22);
		upImg.put(24, img24);
		upTxt.put(24, txt24);
		upImg.put(29, img29);
		upTxt.put(29, txt29);
		upImg.put(25, img25);
		upTxt.put(25, txt25);
		upImg.put(32, img32);
		upTxt.put(32, txt32);
		upImg.put(33, img33);
		upTxt.put(33, txt33);
		
		downImg.put(0, img0);
		downTxt.put(0, txt0);
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
		downImg.put(14, img14);
		downTxt.put(14, txt14);
		downImg.put(15, img15);
		downTxt.put(15, txt15);
		downImg.put(17, img17);
		downTxt.put(17, txt17);
		downImg.put(19, img19);
		downTxt.put(19, txt19);
		downImg.put(20, img20);
		downTxt.put(20, txt20);
		downImg.put(21, img21);
		downTxt.put(21, txt21);
		downImg.put(22, img22);
		downTxt.put(22, txt22);
		downImg.put(25, img25);
		downTxt.put(25, txt25);
		downImg.put(28, img28);
		downTxt.put(28, txt28);
		downImg.put(24, img24);
		downTxt.put(24, txt24);
		downImg.put(32, img32);
		downTxt.put(32, txt32);
		downImg.put(33, img33);
		downTxt.put(33, txt33);
		
		leftImg.put(0, img0);
		leftTxt.put(0, txt0);
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
		leftImg.put(12, img12);
		leftTxt.put(12, txt12);
		leftImg.put(15, img15);
		leftTxt.put(15, txt15);
		leftImg.put(16, img16);
		leftTxt.put(16, txt16);
		leftImg.put(18, img18);
		leftTxt.put(18, txt18);
		leftImg.put(20, img20);
		leftTxt.put(20, txt20);
		leftImg.put(21, img21);
		leftTxt.put(21, txt21);
		leftImg.put(22, img22);
		leftTxt.put(22, txt22);
		leftImg.put(23, img23);
		leftTxt.put(23, txt23);
		leftImg.put(30, img30);
		leftTxt.put(30, txt30);
		leftImg.put(26, img26);
		leftTxt.put(26, txt26);
		leftImg.put(31, img31);
		leftTxt.put(31, txt31);
		leftImg.put(34, img34);
		leftTxt.put(34, txt34);
		
		rightImg.put(0, img0);
		rightTxt.put(0, txt0);
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
		rightImg.put(13, img13);
		rightTxt.put(13, txt13);
		rightImg.put(14, img14);
		rightTxt.put(14, txt14);
		rightImg.put(16, img16);
		rightTxt.put(16, txt16);
		rightImg.put(18, img18);
		rightTxt.put(18, txt18);
		rightImg.put(19, img19);
		rightTxt.put(19, txt19);
		rightImg.put(21, img21);
		rightTxt.put(21, txt21);
		rightImg.put(22, img22);
		rightTxt.put(22, txt22);
		rightImg.put(26, img26);
		rightTxt.put(26, txt26);
		rightImg.put(27, img27);
		rightTxt.put(27, txt27);
		rightImg.put(23, img23);
		rightTxt.put(23, txt23);
		rightImg.put(31, img31);
		rightTxt.put(31, txt31);
		rightImg.put(34, img34);
		rightTxt.put(34, txt34);
		
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
							if (costruzioneRiga.equals("4")) {
								cella[riga][colonne++] = new Cell(Cell.ENEMY);
							} 
							if (costruzioneRiga.equals("5")) {
								cella[riga][colonne++] = new Cell(Cell.DOOR);
							} 
							if (costruzioneRiga.equals("6")) {
								cella[riga][colonne++] = new Cell(Cell.OBSTACLE);
							}
							if (costruzioneRiga.equals("8")) {
								cella[riga][colonne++] = new Cell(Cell.OBSTACLEDAMAGE);
							}
							if (costruzioneRiga.equals("9")) {
								cella[riga][colonne++] = new Cell(Cell.FALLINGDOWN);
							}	
							if (costruzioneRiga.equals("10")) {
								cella[riga][colonne++] = new Cell(Cell.HEART);
							}
							if (costruzioneRiga.equals("11")) {
								cella[riga][colonne++] = new Cell(Cell.PISTOL);
							}
							if (costruzioneRiga.equals("12")) {
								cella[riga][colonne++] = new Cell(Cell.ENEMYKEY);
							}
							if (costruzioneRiga.equals("13")) {
								cella[riga][colonne++] = new Cell(Cell.FIRE);
							}
							if (costruzioneRiga.equals("14")) {
							cella[riga][colonne++] = new Cell(Cell.BOSS);
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
						if (costruzioneRiga.equals("4")) {
							cella[riga][colonne++] = new Cell(Cell.ENEMY);
						} 
						if (costruzioneRiga.equals("5")) {
							cella[riga][colonne++] = new Cell(Cell.DOOR);
						}
						if (costruzioneRiga.equals("6")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLE);
						}
						if (costruzioneRiga.equals("8")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLEDAMAGE);
						}
						if (costruzioneRiga.equals("9")) {
							cella[riga][colonne++] = new Cell(Cell.FALLINGDOWN);
						}
						if (costruzioneRiga.equals("10")) {
							cella[riga][colonne++] = new Cell(Cell.HEART);
						}
						if (costruzioneRiga.equals("11")) {
							cella[riga][colonne++] = new Cell(Cell.PISTOL);
						}
						if (costruzioneRiga.equals("12")) {
							cella[riga][colonne++] = new Cell(Cell.ENEMYKEY);
						}
						if (costruzioneRiga.equals("13")) {
							cella[riga][colonne++] = new Cell(Cell.FIRE);
						}
						if (costruzioneRiga.equals("14")) {
							cella[riga][colonne++] = new Cell(Cell.BOSS);
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
						if (costruzioneRiga.equals("4")) {
							cella[riga][colonne++] = new Cell(Cell.ENEMY);
						} 
						if (costruzioneRiga.equals("5")) {
							cella[riga][colonne++] = new Cell(Cell.DOOR);
						}
						if (costruzioneRiga.equals("6")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLE);
						}	
						if (costruzioneRiga.equals("8")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLEDAMAGE);
						}
						if (costruzioneRiga.equals("9")) {
							cella[riga][colonne++] = new Cell(Cell.FALLINGDOWN);
						}
						if (costruzioneRiga.equals("10")) {
							cella[riga][colonne++] = new Cell(Cell.HEART);
						}
						if (costruzioneRiga.equals("11")) {
							cella[riga][colonne++] = new Cell(Cell.PISTOL);
						}
						if (costruzioneRiga.equals("12")) {
							cella[riga][colonne++] = new Cell(Cell.ENEMYKEY);
						}
						if (costruzioneRiga.equals("13")) {
							cella[riga][colonne++] = new Cell(Cell.FIRE);
						}
						if (costruzioneRiga.equals("14")) {
							cella[riga][colonne++] = new Cell(Cell.BOSS);
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
						if (costruzioneRiga.equals("4")) {
							cella[riga][colonne++] = new Cell(Cell.ENEMY);
						} 
						if (costruzioneRiga.equals("5")) {
							cella[riga][colonne++] = new Cell(Cell.DOOR);
						}
						if (costruzioneRiga.equals("6")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLE);
						}						
						if (costruzioneRiga.equals("8")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLEDAMAGE);
						}
						if (costruzioneRiga.equals("9")) {
							cella[riga][colonne++] = new Cell(Cell.FALLINGDOWN);
						}
						if (costruzioneRiga.equals("10")) {
							cella[riga][colonne++] = new Cell(Cell.HEART);
						}
						if (costruzioneRiga.equals("11")) {
							cella[riga][colonne++] = new Cell(Cell.PISTOL);
						}
						if (costruzioneRiga.equals("12")) {
							cella[riga][colonne++] = new Cell(Cell.ENEMYKEY);
						}
						if (costruzioneRiga.equals("13")) {
							cella[riga][colonne++] = new Cell(Cell.FIRE);
						}
						if (costruzioneRiga.equals("14")) {
							cella[riga][colonne++] = new Cell(Cell.BOSS);
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
						if (costruzioneRiga.equals("4")) {
							cella[riga][colonne++] = new Cell(Cell.ENEMY);
						} 
						if (costruzioneRiga.equals("5")) {
							cella[riga][colonne++] = new Cell(Cell.DOOR);
						}
						if (costruzioneRiga.equals("6")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLE);
						}
						if (costruzioneRiga.equals("8")) {
							cella[riga][colonne++] = new Cell(Cell.OBSTACLEDAMAGE);
						}
						if (costruzioneRiga.equals("9")) {
							cella[riga][colonne++] = new Cell(Cell.FALLINGDOWN);
						}
						if (costruzioneRiga.equals("10")) {
							cella[riga][colonne++] = new Cell(Cell.HEART);
						}
						if (costruzioneRiga.equals("11")) {
							cella[riga][colonne++] = new Cell(Cell.PISTOL);
						}
						if (costruzioneRiga.equals("12")) {
							cella[riga][colonne++] = new Cell(Cell.ENEMYKEY);
						}
						if (costruzioneRiga.equals("13")) {
							cella[riga][colonne++] = new Cell(Cell.FIRE);
						}
						if (costruzioneRiga.equals("14")) {
							cella[riga][colonne++] = new Cell(Cell.BOSS);
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
	
	
	
	

	public static boolean isClosedMap() {
		return closedMap;
	}
	
	public static void setClosedMap(boolean closedMap) {
		Maps.closedMap = closedMap;
	}

	public Cell[][] getCella() {
		return cella;
	}
		
	public int getCellType(int x, int y) {
		return cella[x][y].getType();
	}
	public void setCellType(int x, int y,int tipo) {
		cella[x][y].setType(tipo);
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

	public static boolean isMapBoss() {
		return mapBoss;
	}

	public static void setMapBoss(boolean mapBoss) {
		Maps.mapBoss = mapBoss;
	}

	public static boolean isMapKey() {
		return mapKey;
	}

	public static void setMapKey(boolean mapKey) {
		Maps.mapKey = mapKey;
	}

	public static boolean isControllo() {
		return controllo;
	}

	public static void setControllo(boolean controllo) {
		Maps.controllo = controllo;
	}
	
	
	
	
}