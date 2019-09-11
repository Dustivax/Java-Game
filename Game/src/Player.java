import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Main.HEIGHT-64) velY *= -2;
		if(x <= 0 || x >= Main.WIDTH-64) velX *= -2;
	
		handler.addObject(new Trail(x, y, ID.Trail, Color.black, 32, 32, 0.05f, handler));
		
		collision();
	}

	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					HUD.HEALTH -= 2;
				}
			}
			
		}
	}
	
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x,  y,  32,  32);
	}

}
