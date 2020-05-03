package project.igpe.classes;

public class Maps {
	
	private Cell[][] cella = new Cell[Settings.x][Settings.y];

	
	public Maps() {
		
		for(int i = 0; i < cella.length; i++) {
			for(int j = 0; j < cella[i].length; j++) {
				cella[i][j] = new Cell(Cell.EMPTY);
			}
		}
		
		for(int i = 0; i < cella.length; i++) {
			for(int j = 0; j < cella[i].length; j++) {
				if ((j == 0 || j == cella.length-1) || (i == 0 || i == cella.length-1) || (i == 0 && j == 0))  {
					cella[i][j] = new Cell(Cell.WALL);
				}
			}
			
		}
		
		cella[7][5] = new Cell(Cell.OBSTACLE);
		
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
