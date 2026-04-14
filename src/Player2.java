package TennisProject;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player2 extends Player{
	
	private Image img;

	//not much going on here, just the name and stats and coords and image name for this player
	public Player2(int x, int y, int w, int h, int fs) {
		super("Rafael Nadal", 8, 10, 6, "TennisImages/nadal.png", x, y, w, h, fs);
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
