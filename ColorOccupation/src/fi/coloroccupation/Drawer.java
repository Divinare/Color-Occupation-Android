package fi.coloroccupation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Drawer extends View {

	private char[][] gameboard;
	private String green;
	private String red;
	private String blue;
	private String yellow;
	private String purple;

	public Drawer(Context context) {
		super(context);
		setColors();
	}

	public void setGameboard(char[][] gameboard) {
		this.gameboard = gameboard;
	}

	@Override
	public void draw(Canvas canvas) {

		Paint paint = new Paint();
		paint.setStrokeWidth(6);

		int dSide = 37; // how much pixels away from left/right side
		int dTop = 200; // how much pixels away from top
		for (int i = 0; i < gameboard.length; i++) {
			int row = i * 25;
			int column = i * 25 + 25;
			for (int j = 0; j < gameboard[0].length; j++) {
				if (gameboard[i][j] == 'g') {
					paint.setColor(Color.parseColor(this.green));
				} else if (gameboard[i][j] == 'b') {
					paint.setColor(Color.parseColor(this.blue));
				} else if (gameboard[i][j] == 'y') {
					paint.setColor(Color.parseColor(this.yellow));
				} else if (gameboard[i][j] == 'r') {
					paint.setColor(Color.parseColor(this.red));
				} else if (gameboard[i][j] == 'p') {
					paint.setColor(Color.parseColor(this.purple));
				}
				canvas.drawRect(dSide + j * 25, dTop + row, dSide + j * 25 + 25, dTop + column, paint);
			}
		}
	}

	private void setColors() {
		this.yellow = "#FFFF00";
		this.green = "#00CC00";
		this.red = "#FF0000";
		this.purple = "#FF00FF";
		this.blue = "#0000FF";
	}

	public String getRed() {
		return this.red;
	}
	public String getGreen(){
		return this.green;
	}
	public String getYellow() {
		return this.yellow;
	}
	public String getBlue() {
		return this.blue;
	}
	public String getTeal() {
		return this.purple;
	}
	
}
