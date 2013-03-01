package nink.resources;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tests.xml.Entity;

public class StandardMissile extends Entity{
	
    public static float VELOCITY;
    private Vector2f position;

    public StandardMissile(float x, float y, float velocity) {
        position = new Vector2f(x, y);
        VELOCITY = velocity;
    }
    public void update(float x, float y, float delta, boolean vertical) {
        if(vertical) {
            y += VELOCITY * delta;
        }else{
            x += VELOCITY * delta;
        }
    }
}
