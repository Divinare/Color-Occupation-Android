package fi.coloroccupation;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;

public class Game {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private GameOptions options;
	
	private char[][] gameboard; // y = yellow, b = blue, g = green, r = red, p = purple
	private String turn = "none";
	private double p1pts = 0;
	private double p2pts = 0;
	
	Random random = new Random();
	
	
	public Game(GameOptions options) {
		this.options = options;
		this.players.add(new Player(options.getGameWidth()-1, options.getGameHeight()-1));
		this.players.add(new Player(0,0));
		this.gameboard = initGameboard(options.getGameWidth(),options.getGameHeight());
	}
	
	public void playTurn(char color, String player) {
		if(this.turn.equals("p1") || player.equals("p1")) {
			changeColors(gameboard.length-1, gameboard[0].length-1, color);
			players.get(0).setColor(color);
			this.turn = "p2";
			Log.w("p1", "color: " + color);
		} else {
			changeColors(0, 0, color);
			players.get(1).setColor(color);
			this.turn = "p1";
			Log.w("p2", "color: " + color);
		}
		this.p1pts = calcPoints(gameboard.length-1, gameboard[0].length-1);
		this.p2pts = calcPoints(0,0);
	}
	
	private void changeColors(int startX, int startY, char newColor) {
		boolean[][] visited = new boolean[gameboard.length][gameboard[0].length];
		for (int i = 0; i < visited.length; i++) {
			for(int j = 0; j < visited[0].length; j++) {
				visited[i][j] = false;
			}
		}
		ArrayList<Dot> queue = new ArrayList();
		Dot root = new Dot(startX, startY);
		queue.add(root);
		char oldColor = gameboard[startX][startY];
		gameboard[startX][startY] = newColor;
		Log.w("gameboard", "startX: " + startX + " startY: " + startY + "start color: " + gameboard[startX][startY]);
		while(true) {
			Dot current = queue.remove(0);
			int i = current.getI();
			int j = current.getJ();

			int[] di = {1,0,-1,0,1,-1,1,-1};
			int[] dj = {0,1,0,-1,1,-1,-1,1};
			for (int i = 0; i < di.length; i++) {
				if(isColorToCapture(d[i], d[j], visited, oldColor, newColor)) {
					Dot child = new Dot(d[i], d[j]);
					queue.add(child);
					visited[i][j] = true;
				}
			}
			if(queue.isEmpty()) {
				break;
			}
		}
	}
	
	private boolean isColorToCapture(int i, int j, boolean[][] visited, char oldColor, char newColor) {
		if(i >= 0 && j >= 0 && i < gameboard.length && j < gameboard[0].length) {
			if(visited[i][j] == false && gameboard[i][j] == oldColor) {
				gameboard[i][j] = newColor;
				Log.w("captured", "i:" + i + " j: " + j + " gameboard[i][j]: " + gameboard[i][j]);
				return true;
			}
		}
		return false;
	}
	
	private double calcPoints(int startX, int startY) {
		boolean[][] visited = new boolean[gameboard.length][gameboard[0].length];
		for (int i = 0; i < visited.length; i++) {
			for(int j = 0; j < visited[0].length; j++) {
				visited[i][j] = false;
			}
		}
		char color = gameboard[startX][startY];
		ArrayList<Dot> queue = new ArrayList();
		Dot root = new Dot(startX, startY);
		queue.add(root);
		int points = 0;
	    while(true) {
	      Dot current = queue.remove(0);
	      int currentI = current.getI();
	      int currentJ = current.getJ();
	      int[] di = {1,0,-1,0,1,-1,1,-1};
	      int[] dj = {0,1,0,-1,1,-1,-1,1};
	      for (int i = 0; i < di.length; i++) {
	        if(isPoint(currentI+di[i], currentJ + dj[i], visited, color)) {
	          Dot child = new Dot(currentI+di[i], currentJ + dj[i]);
	          queue.add(child);
	          visited[currentI + di[i]], currentJ + dj[i]] = true;
	          points++;
	        }
	      }
	    }
		int totalPoints = gameboard.length*gameboard[0].length;
		float sum = (float)points/(float)totalPoints;
		sum = sum*100;
		String formatedSum = String.format("%.4f", sum);
		return Double.parseDouble(formatedSum);
	}
	
	private boolean isPoint(int i, int j, boolean[][] visited, char color) {
		if(i >= 0 && j >= 0 && i < gameboard.length && j < gameboard[0].length) {
			if(visited[i][j] == false && gameboard[i][j] == color) {
				return true;
			}
		}
		return false;
	}
	
	
	public char[][] getGameboard() {
		return this.gameboard;
	}
		
	public char[][] initGameboard(int width, int height) {
		Log.w("player coords", "p1: " + players.get(0).getX() + " " + players.get(0).getY() + " p2: " + players.get(1).getX() + " " + players.get(1).getY());
		char[][] gameboard = new char[height][width];
		for(int i = 0; i < gameboard.length; i++) {
			for(int j = 0; j < gameboard[0].length; j++) {
				if (!isPlayerCoordinate(i, j)) {
					gameboard[i][j] = randomizeLetter();
				} else {
					gameboard[i][j] = randomizeLetterPlayerTile(i, j);
				}
			}
		}
		return gameboard;
	}
	
	public boolean isPlayerCoordinate(int x, int y) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getX() == x && players.get(i).getY() == y) {
				return true;
			}
		}
		return false;
	}
	
	private char randomizeLetterPlayerTile(int x, int y) {
		Player player = players.get(0);
		for (int i = 0; i < players.size(); i ++) {
			if (players.get(i).getX() == x && players.get(i).getY() == y) {
				player = players.get(i);
			}
		}
		char color;
		boolean found = false;
		while(true) {
			int randomIndex = random.nextInt(this.options.getColors().length);
			color = this.options.getColors()[randomIndex];
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getColor() == color) {
					player.setColor(color);
					found = true;
				}
			}
			if(!found) {
				break;
			}
			found = false;
		}
		return color;
	}
	
	private char randomizeLetter() {
		int randomIndex = random.nextInt(this.options.getColors().length);
		return this.options.getColors()[randomIndex];
	}	
	
	public String getTurn() {
		return this.turn;
	}
	
	public double getP1pts() {
		return this.p1pts;
	}
	public double getP2pts() {
		return this.p2pts;
	}
		
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
		
	private class Dot {
		private int i;
		private int j;
		public Dot(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		private int getI() {
			return i;
		}
		private int getJ() {
			return j;
		}
	}
	

}
