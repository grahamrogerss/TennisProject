package TennisProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	
	//all the necessary variables for a player
	String name;
	int speed;
	int strength;
	int serveSpeed;
	Image img;
	int x;
	int y;
	int w;
	int h;
	int fs;
	
	
	//constructor- also shows where to get the images
	public Player(String name, int speed, int strength, int serveSpeed, String imgName, int x, int y, int w, int h, int fs) {
		
		this.name = name;
		this.speed = speed;
		this.strength = strength;
		this.serveSpeed = serveSpeed;
		try {
			img = ImageIO.read(new File(imgName)).getScaledInstance(w, h, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			System.out.println("Image file not found");
		}		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.fs = fs;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//draws the player image
	public void drawImage(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, null);
	}
	//draws the player name and stats
	public void drawText(Graphics g) {
		Color fontColor = new Color(0, 0, 0);
		Font font = new Font ("Impact", Font.BOLD, fs);
		g.setFont(font);
		g.setColor(fontColor);
		g.drawString(name, x, y + 150);
		g.drawString("Speed: " + speed, x, y + 175);
		g.drawString("Strength: " + strength, x, y + 200);
		g.drawString("Serve Speed: " + serveSpeed, x, y + 225);
	}

}
