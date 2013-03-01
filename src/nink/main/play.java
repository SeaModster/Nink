package nink.main;

import java.util.ArrayList;
import java.util.List;

import nink.resources.StandardMissile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class play extends BasicGameState {
	
	protected int stateID = 2;
	
	Image background = null;
	Image ship = null;
	Image bullet = null;
	float x = 395;
	float y = 460;
	float threta;
	float scale = 1.0f;
	public int MouseX, MouseY;
	int SHIPX, SHIPY;
	float ShipSpeed;
	float BulletSpeed;
	
	boolean paused = false;
	
	List<StandardMissile> smlist = new ArrayList<StandardMissile>();
	private StandardMissile sma;
	
	public play(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/ninkbg.png");
		ship = new Image("res/ship_start.png");
		bullet = new Image("res/bullet.png");
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0);
		ship.draw(x, y, scale);
		g.drawString("ShipX: "+this.SHIPX+" ShipY: "+this.SHIPY, 0, 0);
		for(StandardMissile smal : smlist){
			smal.update(SHIPX, SHIPY, threta, true);
		}
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		MouseX = gc.getInput().getAbsoluteMouseX();
		MouseY = gc.getInput().getAbsoluteMouseY();
		Input in = gc.getInput();
		ShipSpeed = 0.1f;
		SHIPX = (int) x;
		SHIPY = (int) y;
		threta = delta;
		if(in.isKeyDown(Input.KEY_W)){
			if(y > 300){
				double hip = ShipSpeed * delta;
				float rotation = ship.getRotation();
				x += hip * Math.sin(Math.toRadians(rotation));
				y -= hip * Math.cos(Math.toRadians(rotation));
			}
		}
		if(in.isKeyDown(Input.KEY_S)){
			if(y < 500){
				float hip = -ShipSpeed * delta;
				float rotation = ship.getRotation();
				x += hip * Math.sin(Math.toRadians(rotation));
				y -= hip * Math.cos(Math.toRadians(rotation));
			}
		}
		if(in.isKeyDown(Input.KEY_D)){
			if(x < 785){
				float hip = ShipSpeed * delta;
				float rotation = ship.getRotation();
				x += hip * Math.cos(Math.toRadians(rotation));
			}
		}
		if(in.isKeyDown(Input.KEY_A)){
			if(x > 5){
				float hip = ShipSpeed * delta;
				float rotation = ship.getRotation();
				x -= hip * Math.cos(Math.toRadians(rotation));
			}
		}
		if(in.isKeyPressed(Input.KEY_SPACE)){
			StandardMissile sm = new StandardMissile(SHIPX, SHIPY, BulletSpeed);
			smlist.add(sm);
		}
		if(in.isKeyDown(Input.KEY_P)){
			if(this.paused == false){
				gc.pause();
				this.paused = true;
			}else if(this.paused == true){
				gc.resume();
				this.paused = false;
			}
		}
		if(in.isKeyDown(Input.KEY_ESCAPE)){
			this.returntomenu(sbg);
		}
	}
	public void returntomenu(StateBasedGame sbg) {
		sbg.enterState(1);
	}
}
