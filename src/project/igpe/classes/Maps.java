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
		
		File img1 = new File("src/project/igpe/images/sfondo1.jpg");
		File img2 = new File("src/project/igpe/images/sfondo2.jpg");
		File img3 = new File("src/project/igpe/images/sfondo2.jpg");
		
		String txt1 = new String("src/project/igpe/maps/TemplateMAP.txt");
		String txt2 = new String("src/project/igpe/maps/TemplateMAP2.txt");
		String txt3 = new String("src/project/igpe/maps/TemplateMAP3.txt");

		contenitoreImg.add(img1);
		contenitoreImg.add(img2);
		contenitoreImg.add(img3);

		contenitoreTxt.add(txt1);
		contenitoreTxt.add(txt2);
		contenitoreTxt.add(txt3);


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
	/*
	public void setCella(Cell[][] cella) {
		this.cella = cella;
	}*/
	

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
	}*/
}