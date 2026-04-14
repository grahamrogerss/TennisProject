package TennisProject;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Tennis {
	
	//all the variables that do not change at any time
	final int WIDTH = 900, HEIGHT = 900, RACKETWIDTH = 75, COURTWIDTH = 500, COURTHEIGHT = 600, RACKETHEIGHT = 75, PLAYERY = (HEIGHT - 100) - RACKETHEIGHT, 
			OPPONENTY = 100, PLAYERSTARTX = (WIDTH/2 - RACKETWIDTH/2), OPPONENTSTARTX = WIDTH/2, BOXWIDTH = 150, BOXHEIGHT = 100, COURTLEFT = WIDTH/2 - COURTWIDTH/2, COURTRIGHT = COURTLEFT + COURTWIDTH, 
			COURTTOP = HEIGHT/2 - COURTHEIGHT/2, COURTBOTTOM = COURTTOP + COURTHEIGHT, BALLWIDTHHEIGHT = 15, BALLSTARTX = PLAYERSTARTX, 
			BALLSTARTY = PLAYERY - BALLWIDTHHEIGHT, ICONWIDTHHEIGHT = 100, STARTBUTTONWIDTH = 180, STARTBUTTONHEIGHT = 70, 
			STARTBUTTONX = WIDTH/2 - STARTBUTTONWIDTH/2, STARTBUTTONY = 200, OPTIONFONT = 25, SELECTEDFONT = 20, PLAYER1X = 60, PLAYER1Y = 50, PLAYER2X = 650, 
			PLAYER2Y = 50, BALLWIDTH = 15, WINSCORE = 10, INGAMEICONX1 = 40, INGAMEICONY1 = 720, INGAMEICONX2 = 770, INGAMEICONY2 = 10, RESETSPEED = 0;

	
	//all the variables that do change throughout the game
	int playerX = PLAYERSTARTX, opponentX = OPPONENTSTARTX, playerSpeed = 0, oppRunSpeed = 0, 
		playerScore = 0, oppScore = 0;
	
	//booleans
	boolean gameStarted = false, player1Selected = false, player2Selected = false, serve = true, served = false, playerTurn = true, won = false, 
			lost = false;
	 
	//setting variables for images
	Image court;	
	Image opponent;
	Image player;
	Image startButton;
	
	//getting RBG specific colors for the background and the score box
	Color backgroundColor = new Color(105, 142, 255);
	Color scoreBoxColor = new Color(46, 138, 18);
	
	//calling other classes for the player options on the start screen
	Player option1 = new Player1(WIDTH/3 - ICONWIDTHHEIGHT/2, HEIGHT/3 - ICONWIDTHHEIGHT/2, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, OPTIONFONT), option2 = new Player2(2*WIDTH/3 - ICONWIDTHHEIGHT/2, HEIGHT/3 - ICONWIDTHHEIGHT/2, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, OPTIONFONT), 
			option3 = new Player3(WIDTH/3 - ICONWIDTHHEIGHT/2, 2*HEIGHT/3 - ICONWIDTHHEIGHT/2, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, OPTIONFONT), option4 = new Player4(2*WIDTH/3 - ICONWIDTHHEIGHT/2, 2*HEIGHT/3 - ICONWIDTHHEIGHT/2, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, OPTIONFONT);
	
	//two variables for the players that are selectable and will be defined later
	Player p1, p2;
	
	//creating a new ball at the start location
	Ball ball = new Ball(BALLSTARTX, BALLSTARTY);	

	
	// this method is for setting up any images that the game needs to start with
	//sets up the court, player, opponent, and startButton images
	public void setup() {
		
		
		try {
			court = ImageIO.read(new File("TennisImages/court.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			player = ImageIO.read(new File("TennisImages/racketLeft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			opponent = ImageIO.read(new File("TennisImages/racketLeft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			startButton = ImageIO.read(new File("TennisImages/startbutton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	//controls everything that moves in the game
	public void move() {
		//makes sure there is no movement on the start screen or if the game is lost or won
		//if the game is going, the ball and player will move
		if (!lost && !won && gameStarted) {
			if (!playerTurn) {
				ball.moveUp();
			}
			if (playerTurn) {
				ball.moveDown();
			}
			if (!serve) {
				playerX += playerSpeed;
			}
		}
		
		//sets boundaries for the player and opponent movement
		if (playerX + RACKETWIDTH >= COURTRIGHT) {
			playerX = COURTRIGHT - RACKETWIDTH;
		}
		if (playerX <= COURTLEFT) {
			playerX = COURTLEFT;
		}
		
		if (opponentX + RACKETWIDTH >= COURTRIGHT) {
			opponentX = COURTRIGHT - RACKETWIDTH;
		}
		
		if (opponentX <= COURTLEFT) {
			opponentX = COURTLEFT;
		}
		
		
	}
	//makes the player and opponent rackets switch images from left to right when the racket goes across the screen
	public void racketSwitch() {
		
			if (playerX + 0.5 * RACKETWIDTH > WIDTH/2) {
				try {
					player = ImageIO.read(new File("TennisImages/racketRight.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
			if (playerX + 0.5 * RACKETWIDTH < WIDTH/2) {
				try {
					player = ImageIO.read(new File("TennisImages/racketLeft.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
			if (opponentX + 0.5 * RACKETWIDTH > WIDTH/2) {
				try {
						opponent = ImageIO.read(new File("TennisImages/racketRight.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
			if (opponentX + 0.5 * RACKETWIDTH < WIDTH/2) {
				try {
					opponent = ImageIO.read(new File("TennisImages/racketLeft.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	
	//makes the game end when either player gets to 10
	public void endGame() {
		if(oppScore == WINSCORE) {
			lost = true;
		}
		if(playerScore == WINSCORE) {
			won = true;
		}
	}
	
	//if the ball goes out of bounds, it is the opposite player's point, and if it goes out the back, it is the player who hit it's point
	//the game is then reset and the ball is served again
	public void addPoint() {
		
		if (((playerTurn && ball.y < COURTBOTTOM && (ball.x < COURTLEFT || ball.x > COURTRIGHT)) || ball.y < 0)) {
			playerScore ++;
			serve = true;
			reset();
		
		}
		
		if ((!playerTurn && ball.y > COURTTOP && (ball.x < COURTLEFT || ball.x > COURTRIGHT)) || ball.y > HEIGHT) {
			oppScore ++;
			serve = true;
			reset();
		}
	}
	
	//sets the controls for the artificial opponent
	public void AI() {
		//makes sure there is no movement unless the game is being played
		if (!serve && gameStarted && !won && !lost) {
			//if the ball is to the right of the racket, the player moves in that direction at a little slower than the speed of the player
			if (opponentX + RACKETWIDTH/2 < ball.x) {
				opponentX += (2*p2.speed/3);
			} 
			//same thing with the left side
			else if (opponentX + RACKETWIDTH/2 > ball.x){
				opponentX += -(2*p2.speed/3);
				
			}
			//if the ball makes contact with the paddle, it is sent back in a random direction that is calculated with a random number and a slope formula
			if ((ball.x + BALLWIDTH > opponentX && ball.x < opponentX + RACKETWIDTH) && (ball.y > OPPONENTY && ball.y < OPPONENTY + RACKETHEIGHT)){
				int i = (int)(Math.random() * COURTWIDTH);
				
				ball.setSlope(((i + COURTLEFT)-ball.x)/(COURTBOTTOM-ball.y), p2.strength);
				playerTurn = true;
				
			}
			
		}
	}
	
	//everything goes back it its starting position/number when the game is reset
	public void reset() {
		playerX = PLAYERSTARTX;
		opponentX = OPPONENTSTARTX;
		ball.speed = RESETSPEED;
		ball.x = BALLSTARTX;
		ball.y = BALLSTARTY;
		playerTurn = true;
	}
	
	
	// everything that happens when the mouse is pressed
	public void mousePressed(int mouseX, int mouseY) {
		
		//process for selecting player 1
		if (!player1Selected && !player2Selected) {
			if ((mouseX > option1.x && mouseX < option1.x + ICONWIDTHHEIGHT) && (mouseY > option1.y && mouseY < option1.y + ICONWIDTHHEIGHT)) {
				p1 =  new Player1(PLAYER1X, PLAYER1Y, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, SELECTEDFONT);
				player1Selected = true;
			}
			else if ((mouseX > option2.x && mouseX < option2.x + ICONWIDTHHEIGHT) && (mouseY > option2.y && mouseY < option2.y + ICONWIDTHHEIGHT)) {
				p1 = new Player2(PLAYER1X, PLAYER1Y, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, SELECTEDFONT);
				player1Selected = true;
			}
			else if ((mouseX > option3.x && mouseX < option3.x + ICONWIDTHHEIGHT) && (mouseY > option3.y && mouseY < option3.y + ICONWIDTHHEIGHT)) {
				p1 =  new Player3(PLAYER1X, PLAYER1Y, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, SELECTEDFONT);
				player1Selected = true;
			}
			else if ((mouseX > option4.x && mouseX < option4.x + ICONWIDTHHEIGHT) && (mouseY > option4.y && mouseY < option4.y + ICONWIDTHHEIGHT)) {
				p1 =  new Player4(PLAYER1X, PLAYER1Y, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, SELECTEDFONT);
				player1Selected = true;
			}
		}
		//process for selecting player 2
		else if (player1Selected && !player2Selected) {
			if ((mouseX > option1.x && mouseX < option1.x + ICONWIDTHHEIGHT) && (mouseY > option1.y && mouseY < option1.y + ICONWIDTHHEIGHT)) {
				p2 =  new Player1(PLAYER2X, PLAYER2Y, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, SELECTEDFONT);
				player2Selected = true;
			}
			else if ((mouseX > option2.x && mouseX < option2.x + ICONWIDTHHEIGHT) && (mouseY > option2.y && mouseY < option2.y + ICONWIDTHHEIGHT)) {
				p2 = new Player2(PLAYER2X, PLAYER2Y, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, SELECTEDFONT);
				player2Selected = true;
			}
			else if ((mouseX > option3.x && mouseX < option3.x + ICONWIDTHHEIGHT) && (mouseY > option3.y && mouseY < option3.y + ICONWIDTHHEIGHT)) {
				p2 =  new Player3(PLAYER2X, PLAYER2Y, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, SELECTEDFONT);
				player2Selected = true;
			}
			else if ((mouseX > option4.x && mouseX < option4.x + ICONWIDTHHEIGHT) && (mouseY > option4.y && mouseY < option4.y + ICONWIDTHHEIGHT)) {
				p2 =  new Player4(PLAYER2X, PLAYER2Y, ICONWIDTHHEIGHT, ICONWIDTHHEIGHT, SELECTEDFONT);
				player2Selected = true;
			}
		}
		//process for serving the ball
		if (gameStarted && serve) {
			//can only serve if the ball will be going towards the opponent
			if (mouseY < ball.y) {
				//sets direction of the ball based on slope formula based on mouse location
				ball.setSlope(-(mouseX-ball.x)/(mouseY-ball.y), p1.serveSpeed);
				serve = false;
				playerTurn = false;
					
			}
		}
		
			//process for hitting the ball while the game is being played so not on a serve
			if (playerTurn && !serve) {
				//if the mouse is clicked when the ball hits the paddle then it is sent in the direction of the mouse at the speed of the player
				if ((PLAYERY < ball.y && ball.y < PLAYERY + RACKETHEIGHT) && (ball.x > playerX - 20 && ball.x < playerX + RACKETWIDTH + 20)) {
					if (mouseY < ball.y) {
						ball.setSlope(-(mouseX-ball.x)/(mouseY-ball.y), p1.strength);
						playerTurn = false;
						
					}
				}
			}
		
		//starts the game when the start button is pressed
		if (!gameStarted) {
			if (player1Selected && player2Selected) {
				if ((mouseX > STARTBUTTONX && mouseX < STARTBUTTONX + STARTBUTTONWIDTH) && (mouseY > STARTBUTTONY && mouseY < STARTBUTTONY + STARTBUTTONHEIGHT)) {
					gameStarted = true;
					//setting coords of the in game player icons
					p1.x = INGAMEICONX1; 
					p1.y = INGAMEICONY1; 
					p2.x = INGAMEICONX2; 
					p2.y = INGAMEICONY2;
				}
			}
		}
		
	}
	
	// dont have anything happen here... I tried to get rid of it and everything was red underlined so I'm keeping it
	public void mouseReleased(int mouseX, int mouseY) {
		// your code here
	}
	
	// draws everything in the project
	public void draw(Graphics g) {
		
		//draws the background
		g.setColor(backgroundColor);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Color fontColor = new Color(0, 0, 0);
		Font font = new Font ("Impact", Font.BOLD, 100);
		g.setFont(font);
		g.setColor(fontColor);
		
		//everything that is drawn in the start menu
		if (!gameStarted) {
			
			//some text that is always in the start menu
			g.drawString("Tennis", WIDTH/3, 100);
			font = new Font ("Impact", Font.BOLD, 40);
			g.setFont(font);
			g.drawString("by Graham", WIDTH/3 + 50, 150);
			font = new Font ("Impact", Font.BOLD, 40);
			g.setFont(font);
			g.drawString("Player 1:", 10, 50);
			g.drawString("Player 2:", 600, 50);
			
			//all the player options
			option1.drawImage(g);
			option1.drawText(g);
			option2.drawImage(g);
			option2.drawText(g);
			option3.drawImage(g);
			option3.drawText(g);
			option4.drawImage(g);
			option4.drawText(g);
			
			//the text in the start menu that changes based on whether youve picked the players
			if (!player1Selected && !player2Selected) {
				g.drawString("Choose your character", WIDTH/3 + 10, 200);
				
			}
			//also draws the player that you selected
			if (player1Selected && !player2Selected) {
				g.drawString("Choose your opponent", WIDTH/3 + 10, 200);
				p1.drawImage(g);
				
			}
			//draws the player you selected and the opponent you selected and the start button
			if (player1Selected && player2Selected) {
				p1.drawImage(g);
				p2.drawImage(g);
				g.drawImage(startButton, STARTBUTTONX, STARTBUTTONY, STARTBUTTONWIDTH, STARTBUTTONHEIGHT, null);
				
			}
			
			
			
		

		}
		
		
		
		//everything that is drawn when the game is being played
		if (gameStarted) {
			g.setColor(scoreBoxColor);
			g.fillRect(5, 5, BOXWIDTH, BOXHEIGHT);
			
			
			g.drawImage(court, COURTLEFT, COURTTOP, COURTWIDTH, COURTHEIGHT, null);
			g.drawImage(player, playerX, PLAYERY, RACKETWIDTH, RACKETHEIGHT, null);
			g.drawImage(opponent, opponentX, OPPONENTY, RACKETWIDTH, RACKETHEIGHT, null);
			ball.draw(g);
			
			fontColor = new Color(255, 255, 255);
			font = new Font ("Impact", Font.BOLD, 18);
			g.setFont(font);
			g.setColor(fontColor);
			g.drawString("Player: " + playerScore, 7, 30);
			g.drawString("Opponent: " + oppScore, 7, 60); 
			fontColor = new Color(0, 0, 0);
			font = new Font ("Impact", Font.BOLD, 20);
			g.setFont(font);
			g.setColor(fontColor);
			g.drawString("When the ball comes to your racket, click where you want the ball to go!", 175, 30);
			g.drawString("Use arrow keys to move!", 375, 50);
		
			
			
			//drawing the in game player icons
			p1.drawImage(g);
			p2.drawImage(g);
						
			//if the game is lost then it draws "you lose" in the middle of the screen
			if (lost) 
				g.drawString("You lose", WIDTH/2-25, HEIGHT/2);
			
			//if the game is won then it draws "you win! " in the middle of the screen
			if (won) 
				g.drawString("You win!", WIDTH/2-30, HEIGHT/2);
			
			
		}
		
		
	}
	
	
	// ************** DON'T TOUCH THE BELOW CODE ********************** //
	
	public Tennis() {
		setup();
		
		JFrame frame = new JFrame();
		frame.setSize(WIDTH,HEIGHT);
		JPanel canvas = new JPanel() {
			public void paint(Graphics g) {
				draw(g);
			}
		};
		canvas.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				Tennis.this.mousePressed(e.getX(),e.getY());	
			}
			public void mouseReleased(MouseEvent e) {
				Tennis.this.mouseReleased(e.getX(),e.getY());
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "Left");
		canvas.getActionMap().put("Left", new LeftAction());
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "LeftRelease");
		canvas.getActionMap().put("LeftRelease", new LeftReleaseAction());
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "Right");
		canvas.getActionMap().put("Right", new RightAction());
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "RightRelease");
		canvas.getActionMap().put("RightRelease", new RightReleaseAction());

		frame.add(canvas);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			move();
			AI();
			endGame();
			addPoint();
			canvas.repaint();
			
			try {Thread.sleep(20);} 
			catch (InterruptedException e) {}
		}
	}
	public static void main(String[] args) {
		new Tennis();
	}
	
	private class RightAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			playerSpeed = p1.speed;
		}
	}
	private class LeftAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			playerSpeed = -p1.speed;
		}
	} 
	private class LeftReleaseAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			playerSpeed = 0;
		}
	}
	private class RightReleaseAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			playerSpeed = 0;
		}
	}
}
