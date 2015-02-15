package fi.coloroccupation;

import android.util.Log;

public class GameOptions {

	private int players;
	
	char[] colors;
	char[] allColors = {'r', 'b', 'g', 'y', 'p'};
	int gameWidth;
	int gameHeight;
	
	public GameOptions(int players, int colors, int gameWidth, int gameHeight) {
		
		this.players = players;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		

	
	}
		
	public void setUpColors(int colors) {
		
		// not 100% implemented
		this.colors = this.allColors;
	}
	
	public void setUpStartCoords() {
		
	}
	
	public char[] getColors() {
		return this.colors;
	}
	
	public int getPlayers() {
		return this.players;
	}
	public int getGameWidth() {
		return this.gameWidth;
	}
	
	public int getGameHeight() {
		return this.gameHeight;
	}
	
	public int convertCharToInt(char color) {
		for(int i = 0; i < colors.length-1; i++) {
			if(color == colors[i]) {
				return i;
			}
		}
		return colors.length-1;
	}
	
	public char convertIntToChar(int index) {
		return this.colors[index];
	}
	
	
}
