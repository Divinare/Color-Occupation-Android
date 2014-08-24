package fi.coloroccupation;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	private char[][] gameboard; // y = yellow, b = blue, g = green, r = red, p = purple
	private char[] colors;
	private String turn = "player1";
	private double p1pts = 0;
	private double p2pts = 0;
	
	Random random = new Random();
	
	
	public Game(char... chars) {
		initColors(chars);
		this.gameboard = initGameboard(45,40);
	}
	
	public void playTurn(char color) {
		if(this.turn.equals("player1")) {
			changeColors(gameboard.length-1, gameboard[0].length-1, color);
			this.turn = "player2";
		} else {
			changeColors(0, 0, color);
			this.turn = "player1";
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
		while(true) {
			Dot current = queue.remove(0);
			int i = current.getI();
			int j = current.getJ();
			if(isColorToCapture(i+1, j, visited, oldColor, newColor)) {
				Dot child = new Dot(i+1, j);
				queue.add(child);
				visited[i+1][j] = true;
			}
			if(isColorToCapture(i-1, j, visited, oldColor, newColor)) {
				Dot child = new Dot(i-1, j);
				queue.add(child);
				visited[i-1][j] = true;
			}
			if(isColorToCapture(i, j+1, visited, oldColor, newColor)) {
				Dot child = new Dot(i, j+1);
				queue.add(child);
				visited[i][j+1] = true;
			}
			if(isColorToCapture(i, j-1, visited, oldColor, newColor)) {
				Dot child = new Dot(i, j-1);
				queue.add(child);
				visited[i][j-1] = true;
			}
			if(isColorToCapture(i-1, j-1, visited, oldColor, newColor)) {
				Dot child = new Dot(i-1, j-1);
				queue.add(child);
				visited[i-1][j-1] = true;
			}
			if(isColorToCapture(i+1, j+1, visited, oldColor, newColor)) {
				Dot child = new Dot(i+1, j+1);
				queue.add(child);
				visited[i+1][j+1] = true;
			}
			if(isColorToCapture(i+1, j-1, visited, oldColor, newColor)) {
				Dot child = new Dot(i+1, j-1);
				queue.add(child);
				visited[+1][j-1] = true;
			}
			if(isColorToCapture(i-1, j+1, visited, oldColor, newColor)) {
				Dot child = new Dot(i-1, j+1);
				queue.add(child);
				visited[i-1][j+1] = true;
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
			int i = current.getI();
			int j = current.getJ();
			if(isPoint(i+1, j, visited, color)) {
				Dot child = new Dot(i+1, j);
				queue.add(child);
				visited[i+1][j] = true;
				points++;
			}
			if(isPoint(i-1, j, visited, color)) {
				Dot child = new Dot(i-1, j);
				queue.add(child);
				visited[i-1][j] = true;
				points++;
			}
			if(isPoint(i, j+1, visited, color)) {
				Dot child = new Dot(i, j+1);
				queue.add(child);
				visited[i][j+1] = true;
				points++;
			}
			if(isPoint(i, j-1, visited, color)) {
				Dot child = new Dot(i, j-1);
				queue.add(child);
				visited[i][j-1] = true;
				points++;
			}
			if(isPoint(i-1, j-1, visited, color)) {
				Dot child = new Dot(i-1, j-1);
				queue.add(child);
				visited[i-1][j-1] = true;
				points++;
			}
			if(isPoint(i+1, j+1, visited, color)) {
				Dot child = new Dot(i+1, j+1);
				queue.add(child);
				visited[i+1][j+1] = true;
				points++;
			}
			if(isPoint(i+1, j-1, visited, color)) {
				Dot child = new Dot(i+1, j-1);
				queue.add(child);
				visited[+1][j-1] = true;
				points++;
			}
			if(isPoint(i-1, j+1, visited, color)) {
				Dot child = new Dot(i-1, j+1);
				queue.add(child);
				visited[i-1][j+1] = true;
				points++;
			}
			if(queue.isEmpty()) {
				break;
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
	
	private void initColors(char... chars) {
		this.colors = new char[chars.length];
		int index = 0;
		for(int i : chars) {
			
			this.colors[index] = chars[index];
			index++;
		}
	}
	
	public char[][] initGameboard(int height, int width) {
		char[][] gameboard = new char[height][width];
		for(int i = 0; i < gameboard.length; i++) {
			for(int j = 0; j < gameboard[0].length; j++) {
				gameboard[i][j] = randomizeLetter();
			}
		}
		return gameboard;
	}
	
	private char randomizeLetter() {
		int randomIndex = random.nextInt(this.colors.length);
		return this.colors[randomIndex];
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
