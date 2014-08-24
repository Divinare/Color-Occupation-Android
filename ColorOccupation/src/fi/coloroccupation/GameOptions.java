package fi.coloroccupation;

public class GameOptions {

	private int players;
	
	char[] colors;
	char[] allColors = {'r', 'b', 'g', 'y', 'p'};
	
	public GameOptions(int players, int colors) {
		this.players = players;
		
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
