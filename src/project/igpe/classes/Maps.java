package project.igpe.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Maps {
	
	private static Cell[][] cella = new Cell[Settings.x][Settings.y];

	private static ArrayList<File> contenitoreTxt = new ArrayList<File>();
	private static ArrayList<String> contenitoreImg = new ArrayList<String>();
	
	public Maps() {
		

		for(int i = 0; i < cella.length; i++) {
			for(int j = 0; j < cella[i].length; j++) {
				cella[i][j] = new Cell(Cell.EMPTY);
			}
		}
		
		loadcontenitoreMappe();
				
		try {
			loadMap(); //int random come paramentro
		} catch (Exception e) {
			System.out.println("non carica metodo LoadMap");
			e.printStackTrace();
		}
		
		
		cella[7][5] = new Cell(Cell.OBSTACLE);
		 
	}	

	public static void loadcontenitoreMappe() {
		File txt1 = new File("src/project/igpe/maps/TemplateMAP.txt");
		contenitoreTxt.add(txt1);
		String img1 = new String("src/project/igpe/images/sfondo.jpg");
		contenitoreImg.add(img1);
	}
	
	
	
	public void loadMap() throws Exception { //int random come paramentro
		int riga = 0;
		int colonne = 0;
		 try {
				BufferedReader b = new BufferedReader(new FileReader(contenitoreTxt.get(0)));
				while (b.ready()) {				
					String costruzioneRiga;
					String line = b.readLine();
					StringTokenizer tok = new StringTokenizer(line, " ");					
						while (tok.hasMoreTokens()) {
							costruzioneRiga = tok.nextToken();
							if (costruzioneRiga.equals("3")) {
								cella[riga][colonne++] = new Cell(Cell.WALL);
							} else
								cella[riga][colonne++] = new Cell(Cell.EMPTY);		
						} 
					riga++;
					colonne=0;
				}
				b.close();
				System.out.println("TemplateMAP Caricato");	
			} catch (FileNotFoundException e) {
				System.out.println("TemplateMAP Non trovato");
				e.printStackTrace();
			}
		      
	}
	
	
	
	

	public Cell[][] getCella() {
		return cella;
	}
	
	public int getCellType(int x, int y) {
		return cella[x][y].getType();
	}

	public static String getContenitoreImg(int index) {
		return contenitoreImg.get(index);
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