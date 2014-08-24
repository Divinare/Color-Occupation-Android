package fi.coloroccupation;

import java.util.ArrayList;

import fi.coloroccupation.R;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.PorterDuff;

public class Main extends ActionBarActivity {

	// http://www.w3schools.com/tags/ref_colorpicker.asp
	
	
	private Game game;
	private Drawer drawer;
	private GameOptions options;
	private Button[] buttonsP1;
	private Button[] buttonsP2;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.drawer = new Drawer(this);
        ViewGroup.LayoutParams params = 
                new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT,
                                           LayoutParams.FILL_PARENT);
        addContentView(drawer, params);
        this.options = new GameOptions(2, 5);
        this.options.setUpColors(5);
        this.game = new Game(options);
        
        initPlayerButtons();
        setGraphics();
        
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.BLACK);
        
        drawer.setGameboard(game.getGameboard());
        drawer.invalidate();
        
        setTags();
        setColors();
        for(int i = 0; i < 3; i++) {
        	setOnClickListeners(buttonsP1[i]);
        	setOnClickListeners(buttonsP2[i]);
        }
    }
    
    private void setTags() {
    	char[] takenColors = new char[options.getPlayers()];
    	for(int i = 0; i < takenColors.length; i++) {
    		takenColors[i] = this.game.getPlayers().get(i).getColor();
    	}
    	
    	ArrayList<Integer> availableColors = new ArrayList<Integer>();
    	for(int i = 0; i < options.getColors().length; i++) {
    		boolean found = false;
    		for(int j = 0; j < takenColors.length; j++) {
    			if (options.getColors()[i] == takenColors[j]) {
    				found = true;
    			}
    		}
    		if(!found) {
    			int color = options.convertCharToInt(options.getColors()[i]);
    			availableColors.add(color);
    		}
    	}
    	
        for(int i = 0; i < 3; i++) {
        	setTag(buttonsP1[i], availableColors.get(i));
        	setTag(buttonsP2[i], availableColors.get(i));
        }
    }
    
    private void setTag(Button button, int color) {
    	button.setTag(color);
    }
        
    private void setOnClickListeners(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
     	   @Override
     	   public void onClick(View view) {
     		   int colorIndex = (Integer) button.getTag();
     		   
     		   char color = options.convertIntToChar(colorIndex);
     		   game.playTurn(color);
     	       drawer.setGameboard(game.getGameboard());
     	       drawer.invalidate();
     	       if (game.getTurn().equals("p1")) {
     	    	  showPlayer1Buttons(color);
     	    	  hidePlayer2Buttons();
     	       } else {
      	    	  showPlayer2Buttons(color);
      	    	  hidePlayer1Buttons();
     	       }
     	       TextView p1pts = (TextView)findViewById(R.id.p1pts);
     	       p1pts.setText("" + game.getP1pts() + "%");
     	       TextView p2pts = (TextView)findViewById(R.id.p2pts);
     	       p2pts.setText("" + game.getP2pts() + "%");
     	       setTags();
     	       setColors();
     	   }
     	});
    }
  
    private void showPlayer1Buttons(char previouslyPressedButton) {
    	for(int i = 0; i < 3; i++) {
    		showPlayerButton(buttonsP1[i], game.getPlayers().get(0).getColor(), previouslyPressedButton, this.options.getColors()[i]);
    	}
    }
    
    private void showPlayer2Buttons(char previouslyPressedButton) {
    	for(int i = 0; i < 3; i++) {
    		showPlayerButton(buttonsP2[i], game.getPlayers().get(1).getColor(), previouslyPressedButton, this.options.getColors()[i]);
    	}
    }
    
    private void showPlayerButton(Button button, char playersCurrentColor, char previouslyPressedButton, char buttonColor) {
    	button.setVisibility(View.VISIBLE);

    }

       
    private void hidePlayer1Buttons() {
    	for(int i = 0; i < 3; i++) {
    		buttonsP1[i].setVisibility(View.INVISIBLE);
    	}
    }
    
    private void hidePlayer2Buttons() {
    	for(int i = 0; i < 3; i++) {
    		buttonsP2[i].setVisibility(View.INVISIBLE);
    	}
    }

    private void setColors() {
    	
        for(int i = 0; i < 3; i++) {
        	char color = options.convertIntToChar((Integer) buttonsP1[i].getTag());
        	setButtonColor(buttonsP1[i], drawer.getColor(color));
        }
        for(int i = 0; i < 3; i++) {
        	char color = options.convertIntToChar((Integer) buttonsP2[i].getTag());
        	setButtonColor(buttonsP2[i], drawer.getColor(color));
        }
    }
    
    private void setButtonColor(Button button, String color) {
    	button.getBackground().setColorFilter(new LightingColorFilter(Color.parseColor(color), Color.parseColor(color)));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void setGraphics() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.BLACK);
    }
    
    private void initPlayerButtons() {
    	buttonsP1 = new Button[3];
    	buttonsP1[0] = (Button)findViewById(R.id.p1b1);
    	buttonsP1[1] = (Button)findViewById(R.id.p1b2);
    	buttonsP1[2] = (Button)findViewById(R.id.p1b3);
    	buttonsP2 = new Button[3];
    	buttonsP2[0] = (Button)findViewById(R.id.p2b1);
    	buttonsP2[1] = (Button)findViewById(R.id.p2b2);
    	buttonsP2[2] = (Button)findViewById(R.id.p2b3);
    }
    
}
