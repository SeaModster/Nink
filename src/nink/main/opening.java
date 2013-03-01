package nink.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class opening extends BasicGameState {
	
	Image opening = null;
	
	int timer;
	
	protected int stateID = 3;
	
	public opening(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public int getID() {
		return stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		opening = new Image("res/opening.png");
		gc.setShowFPS(false);
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stb, Graphics g) throws SlickException {
		opening.draw(0, 0);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer += delta;
		System.out.println("Timer: "+timer/1000);
		Input i = gc.getInput();
		if(i.isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(nink.MAINMENUSTATE);
		}
		if(i.isKeyPressed(Input.KEY_ENTER)){
			sbg.enterState(nink.GAMEPLAYSTATE);
		}
		if(timer == 5000){
			sbg.enterState(nink.MAINMENUSTATE);
		}
	}
}
