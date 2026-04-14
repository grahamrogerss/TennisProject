package TennisProject;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball {
	
	//all necessary ball variables
	Image ball;
	double x;
	double y;
	double slope;
	double speed;
	
	//constuctor- also shows where to get the image
	public Ball(int startX, int startY){
		try {
			ball = ImageIO.read(new File("TennisImages/ball.png")).getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			System.out.println("Image file not found");
		}
		
		x = startX;
		y = startY;
		slope = 0;
		speed = 0;
	}
	
	//draws the ball image
	public void draw(Graphics g) {
		g.drawImage(ball, (int)x, (int)y, null);
		
	}
	//sets the direction and speed if the ball is moving up
	public void moveUp() {
		x += slope * speed;
		y -= speed;
		
	}
	//sets the direction and speed if the ball is moving down
	public void moveDown() {
		x += slope * speed;
		y += speed;
		
	}
	//so I can set slope later in the game
	public void setSlope(double slope, double speed) {
		this.slope = slope;
		this.speed = speed;
	}

}
