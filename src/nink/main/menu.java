package nink.main;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class menu extends BasicGameState {

	protected int stateID = 1;
	
	Image background = null;
	Image sg = null;
	Image option = null;
	Image qg = null;
	
	int MouseX, MouseY;
    
	boolean instart = false;
	boolean inoptions = false;
	boolean inquit = false;
	
	ArrayList<Image> blockImages = new ArrayList<Image>();
	
	public menu(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public int getID() {
		return stateID;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame stg) throws SlickException {
		background = new Image("res/ninkmenu.png");
		sg = new Image("res/startgame.png");
		option = new Image("res/opitions.png");
		qg = new Image("res/quitgame.png");
		Image STARTGAME = sg.getSubImage(40, 100, 193, 47);
		Image OPTIONS = sg.getSubImage(40, 200, 130, 47);
		Image QUITGAME = sg.getSubImage(40, 300, 170, 47);
		this.blockImages.add(STARTGAME);
		this.blockImages.add(OPTIONS);
		this.blockImages.add(QUITGAME);
		gc.setShowFPS(false);
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
	}
	@Override
	public void init(GameContainer gc, StateBasedGame stg) throws SlickException {
		
	}
	@Override
	public void render(GameContainer gc, StateBasedGame stg, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		g.drawImage(sg, 40, 100);
		g.drawImage(option, 40, 200);
		g.drawImage(qg, 40, 300);
		g.drawString("FPS: "+gc.getFPS(), 0, 0);
		g.drawString("MouseX: "+this.MouseX, 100, 0);
		g.drawString("MouseY: "+this.MouseX, 220, 0);
		g.drawString("InsideStart: "+this.instart, 0, 580);
		g.drawString("InsideQuit: "+this.inquit, 200, 580);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input i = gc.getInput();
		
		MouseX = i.getAbsoluteMouseX();
		MouseY = i.getAbsoluteMouseY();
		if ((MouseX >= 40 && MouseX <= 40 + sg.getWidth())
				&& (MouseY >= 100 && MouseY <= 100 + sg.getHeight())) {
        	instart = true;
        }else{
        	instart = false;
        }
        if(MouseX >= 40 && MouseX <= 40 + qg.getWidth() && (MouseY >= 300 && MouseY <= 300 + qg.getHeight())){
        	inquit = true;
        }else{
        	inquit = false;
        }
        if(instart){
        	if (i.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
        		this.startGame(sbg);
        	}
        }     
        if(inquit){
        	if (i.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
        		gc.exit();
        	}
        }
	}
	public void startGame(StateBasedGame sbg) {
		sbg.enterState(nink.GAMEPLAYSTATE);
	}
}
