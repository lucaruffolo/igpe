package project.igpe.classes;

public class Maps {
	
	private Cell[][] cella = new Cell[Settings.cellSize][Settings.cellSize];

	
	public Maps() {
		
		for(int i = 0; i < cella.length; i++) {
			for(int j = 0; j < cella[i].length; j++) {
				cella[i][j] = new Cell(Cell.EMPTY);
			}
		}
		
	}	
		/* DA modificare per aggiugere ostacoli
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
		
		
		/*
		// Aggiungere MURO 
	
		*/
	
	
	
	public void setCella(Cell[][] cella) {
		this.cella = cella;
	}


	public Cell[][] getCella() {
		return cella;
	}
	
	public int getCellType(int x, int y) {
		return cella[x][y].getType();
	}
}
