package project.igpe.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Maps {
	
	private static Cell[][] cella = new Cell[Settings.x][Settings.y];

	private static ArrayList<File> contenitoreImg = new ArrayList<File>();
	private static ArrayList<String> contenitoreTxt = new ArrayList<String>();
	private static int indiceMappe=0;
	


	public Maps() {
			
		loadcontenitoreMappe();
				
		try {
			loadMap(indiceMappe); //int random come paramentro
		} catch (Exception e) {
			System.out.println("non carica metodo LoadMap");
			e.printStackTrace();
		}
				 
	}	

	public static void loadcontenitoreMappe() {
		
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
		File img12 = new File("src/project/igpe/images/mappaAcqua.png");
		
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
		String txt12 = new String("src/project/igpe/maps/Template4doorACQUA.txt");
		
		contenitoreImg.add(img1);
		contenitoreImg.add(img2);
		contenitoreImg.add(img3);
		contenitoreImg.add(img4);
		contenitoreImg.add(img5);
		contenitoreImg.add(img6);
		contenitoreImg.add(img7);
		contenitoreImg.add(img8);
		contenitoreImg.add(img9);
		contenitoreImg.add(img10);
		contenitoreImg.add(img11);
		contenitoreImg.add(img12);

		contenitoreTxt.add(txt1);
		contenitoreTxt.add(txt2);
		contenitoreTxt.add(txt3);
		contenitoreTxt.add(txt4);
		contenitoreTxt.add(txt5);
		contenitoreTxt.add(txt6);
		contenitoreTxt.add(txt7);
		contenitoreTxt.add(txt8);
		contenitoreTxt.add(txt9);
		contenitoreTxt.add(txt10);
		contenitoreTxt.add(txt11);
		contenitoreTxt.add(txt12);

	}
	
	
	
	public static void loadMap(int indiceMappe) throws Exception { //int random come paramentro
		int riga = 0;
		int colonne = 0;
		 try {
				BufferedReader b = new BufferedReader(new FileReader(contenitoreTxt.get(indiceMappe)));
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
			//	System.out.println("TemplateMAP Caricato");	
			} catch (FileNotFoundException e) {
			//	System.out.println("TemplateMAP Non trovato");
				e.printStackTrace();
			}
		      
	}
	
	

	public Cell[][] getCella() {
		return cella;
	}
	
	public int getCellType(int x, int y) {
		return cella[x][y].getType();
	}


	public static File getContenitoreImg(int index) {
		return contenitoreImg.get(index);
	}

	public static int getIndiceMappe() {
		return indiceMappe;
	}


	public static void setIndiceMappe(int indiceMappe) {
		Maps.indiceMappe = indiceMappe;
	}
	
	public static int getNumMappe() {
		return contenitoreImg.size();
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