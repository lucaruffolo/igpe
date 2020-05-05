package project.igpe.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Maps {
	
	private static Cell[][] cella = new Cell[Settings.x][Settings.y];

	
	public Maps() {
		

		for(int i = 0; i < cella.length; i++) {
			for(int j = 0; j < cella[i].length; j++) {
				cella[i][j] = new Cell(Cell.EMPTY);
			}
		}
		
		/*
		for(int i = 0; i < cella.length; i++) {
			for(int j = 0; j < cella[i].length; j++) {
				if (i == 1 || j == 1|| j == 0 || i == 0 || j == cella[i].length-1 || i == cella.length-1 || j == cella[i].length-2 || i == cella.length-2 )  {
					cella[i][j] = new Cell(Cell.WALL);
				}
			}
		}
		
		*/
		
		
		try {
			loadMap();
		} catch (Exception e) {
			System.out.println("non funziona un ca");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		for(int i = 0; i < cella.length; i++) {
			for(int j = 0; j < cella[i].length; j++) {
				System.out.print(cella[i][j].getType()+" ");
			}
			System.out.println();
		}
		*/
		cella[7][5] = new Cell(Cell.OBSTACLE);
		 
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
		}*/
	
	public void loadMap() throws Exception {
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
							//System.out.print(costruzioneRiga + " - ");
							if (costruzioneRiga.equals("3")) {
								cella[riga][colonne++] = new Cell(Cell.WALL);
							} else
								cella[riga][colonne++] = new Cell(Cell.EMPTY);		
						} 
					//System.out.println();
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
	

	/*
	public void setCella(Cell[][] cella) {
		this.cella = cella;
	}*/
}
