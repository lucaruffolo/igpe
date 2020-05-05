package project.igpe.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javafx.scene.image.Image;

public class Maps {
	
	private static Cell[][] cella = new Cell[Settings.x][Settings.y];

	private static ArrayList<File> contenitoreTxt = new ArrayList<File>();
	private static ArrayList<Image> contenitoreImg = new ArrayList<Image>();
	
	public Maps() {
		

		for(int i = 0; i < cella.length; i++) {
			for(int j = 0; j < cella[i].length; j++) {
				cella[i][j] = new Cell(Cell.EMPTY);
			}
		}
				
		try {
			loadMap(); //int random come paramentro
		} catch (Exception e) {
			System.out.println("non carica metodo LoadMap");
			e.printStackTrace();
		}
		
		loadcontenitoreMappe();
		cella[7][5] = new Cell(Cell.OBSTACLE);
		 
	}	

	public static void loadcontenitoreMappe() {
		File txt1 = new File("src/project/igpe/maps/TemplateMAP.txt");
		Image img1 = new Image ("src/project/igpe/images/femmina.png");
		
		contenitoreTxt.add(txt1);
		contenitoreImg.add(img1);
	}
	
	
	
	public void loadMap() throws Exception { //int random come paramentro
		int riga = 0;
		int colonne = 0;
		 try {
				BufferedReader b = new BufferedReader(new FileReader("src/project/igpe/maps/TemplateMAP.txt"));
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

	public static ArrayList<Image> getContenitoreImg() {
		return contenitoreImg;
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
