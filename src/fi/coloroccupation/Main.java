package fi.coloroccupation;

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
	
	
	private Game game = new Game('r', 'b', 'g', 'y', 'p');
	private Drawer drawer;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.drawer = new Drawer(this);
        ViewGroup.LayoutParams params = 
                new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT,
                                           LayoutParams.FILL_PARENT);
        addContentView(drawer, params);
        setColors();
        
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.BLACK);
        
        drawer.setGameboard(game.getGameboard());
        drawer.invalidate();
        
        setOnClickListeners((Button)findViewById(R.id.p1b1), 'r');
        setOnClickListeners((Button)findViewById(R.id.p1b2), 'b');
        setOnClickListeners((Button)findViewById(R.id.p1b3), 'y');
        setOnClickListeners((Button)findViewById(R.id.p1b4), 'g');
        setOnClickListeners((Button)findViewById(R.id.p1b5), 'p');
        
        setOnClickListeners((Button)findViewById(R.id.p2b1), 'r');
        setOnClickListeners((Button)findViewById(R.id.p2b2), 'b');
        setOnClickListeners((Button)findViewById(R.id.p2b3), 'y');
        setOnClickListeners((Button)findViewById(R.id.p2b4), 'g');
        setOnClickListeners((Button)findViewById(R.id.p2b5), 'p');
        
        
    }
    
    private void setOnClickListeners(Button button, final char c) {
        button.setOnClickListener(new View.OnClickListener() {
     	   @Override
     	   public void onClick(View view) {
     		   game.playTurn(c);
     	       drawer.setGameboard(game.getGameboard());
     	       drawer.invalidate();
     	       if (game.getTurn().equals("player1")) {
     	    	  showPlayer1Buttons(c);
     	    	  hidePlayer2Buttons();
     	       } else {
      	    	  showPlayer2Buttons(c);
      	    	  hidePlayer1Buttons();
     	       }
     	       TextView p1pts = (TextView)findViewById(R.id.p1pts);
     	       p1pts.setText("" + game.getP1pts() + "%");
     	       TextView p2pts = (TextView)findViewById(R.id.p2pts);
     	       p2pts.setText("" + game.getP2pts() + "%");
     	   }
     	});
    }
    
  
    private void showPlayer1Buttons(char c) {
    	findViewById(R.id.p1b1).setVisibility(View.VISIBLE);
    	findViewById(R.id.p1b2).setVisibility(View.VISIBLE);
    	findViewById(R.id.p1b3).setVisibility(View.VISIBLE);
    	findViewById(R.id.p1b4).setVisibility(View.VISIBLE);
    	findViewById(R.id.p1b5).setVisibility(View.VISIBLE);
    	if (c == 'r') {
    		findViewById(R.id.p1b1).setVisibility(View.INVISIBLE);
    	}
    	if (c == 'b') {
    		findViewById(R.id.p1b2).setVisibility(View.INVISIBLE);
    	}
    	if (c == 'y') {
    		findViewById(R.id.p1b3).setVisibility(View.INVISIBLE);
    	}
    	if (c == 'g') {
    		findViewById(R.id.p1b4).setVisibility(View.INVISIBLE);
    	}
    	if (c == 'p') {
    		findViewById(R.id.p1b5).setVisibility(View.INVISIBLE);
    	}
    }
    
    private void showPlayer2Buttons(char c) {
    	findViewById(R.id.p2b1).setVisibility(View.VISIBLE);
    	findViewById(R.id.p2b2).setVisibility(View.VISIBLE);
    	findViewById(R.id.p2b3).setVisibility(View.VISIBLE);
    	findViewById(R.id.p2b4).setVisibility(View.VISIBLE);
    	findViewById(R.id.p2b5).setVisibility(View.VISIBLE);
    	if (c == 'r') {
    		findViewById(R.id.p2b1).setVisibility(View.INVISIBLE);
    	}
    	if (c == 'b') {
    		findViewById(R.id.p2b2).setVisibility(View.INVISIBLE);
    	}
    	if (c == 'y') {
    		findViewById(R.id.p2b3).setVisibility(View.INVISIBLE);
    	}
    	if (c == 'g') {
    		findViewById(R.id.p2b4).setVisibility(View.INVISIBLE);
    	}
    	if (c == 'p') {
    		findViewById(R.id.p2b5).setVisibility(View.INVISIBLE);
    	}
    	
    }
    
    private void hidePlayer1Buttons() {
    	findViewById(R.id.p1b1).setVisibility(View.INVISIBLE);
    	findViewById(R.id.p1b2).setVisibility(View.INVISIBLE);
    	findViewById(R.id.p1b3).setVisibility(View.INVISIBLE);
    	findViewById(R.id.p1b4).setVisibility(View.INVISIBLE);
    	findViewById(R.id.p1b5).setVisibility(View.INVISIBLE);
    }
    
    private void hidePlayer2Buttons() {
    	findViewById(R.id.p2b1).setVisibility(View.INVISIBLE);
    	findViewById(R.id.p2b2).setVisibility(View.INVISIBLE);
    	findViewById(R.id.p2b3).setVisibility(View.INVISIBLE);
    	findViewById(R.id.p2b4).setVisibility(View.INVISIBLE);
    	findViewById(R.id.p2b5).setVisibility(View.INVISIBLE);
    }
    
    
  
    

    private void setColors() {

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.BLACK);
        setButtonColor((Button)findViewById(R.id.p1b1), drawer.getRed());
        setButtonColor((Button)findViewById(R.id.p1b2), drawer.getBlue());
        setButtonColor((Button)findViewById(R.id.p1b3), drawer.getYellow());
        setButtonColor((Button)findViewById(R.id.p1b4), drawer.getGreen());
        setButtonColor((Button)findViewById(R.id.p1b5), drawer.getTeal());
        
        setButtonColor((Button)findViewById(R.id.p2b1), drawer.getRed());
        setButtonColor((Button)findViewById(R.id.p2b2), drawer.getBlue());
        setButtonColor((Button)findViewById(R.id.p2b3), drawer.getYellow());
        setButtonColor((Button)findViewById(R.id.p2b4), drawer.getGreen());
        setButtonColor((Button)findViewById(R.id.p2b5), drawer.getTeal());
        
    }
    
    private void setButtonColor(Button button, String color) {
    	button.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.MULTIPLY);
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
}
