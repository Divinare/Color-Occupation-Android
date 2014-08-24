package fi.coloroccupation;

public class Player {

	private int[] startCoords = new int[2];
	private char color;
	
	public Player(int startX, int startY) {
		this.startCoords[0] = startX;
		this.startCoords[1] = startY;
		
		
	}
	
	public void setColor(char color) {
		this.color = color;
	}

	public void initColor(char[][] gameboard) {
		this.color = gameboard[startCoords[0]][startCoords[1]];
	}

	public char getColor() {
		return this.color;
	}
	
	private int[] getStartCoords() {
		return this.startCoords;
	}
}
