package fi.coloroccupation;

public class Player {

	private int x;
	private int y;
	private char color;
	
	public Player(int startX, int startY) {
		this.x = startX;
		this.y = startY;
		
		
	}
	
	public void setColor(char color) {
		this.color = color;
	}

	public char getColor() {
		return this.color;
	}
	
	public int getX() {
		return this.x;
		
	}
	public int getY() {
		return this.y;
	}
	

}
