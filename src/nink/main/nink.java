package nink.main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class nink extends StateBasedGame {
	
	public static final int OPENINGSTATE = 0;
	public static final int MAINMENUSTATE = 1;
	public static final int GAMEPLAYSTATE = 2;
	
	public static int BULLETCOUNT = 10;
	
	public nink() {
		super("Nink - NASA's last resort");
	}
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
		this.addState(new opening(OPENINGSTATE));
		this.addState(new menu(MAINMENUSTATE));
		this.addState(new play(GAMEPLAYSTATE));
		
	}
        // # - Begin - # //
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new nink());
		app.setDisplayMode(800, 600, false);
		app.start();
	}
}
