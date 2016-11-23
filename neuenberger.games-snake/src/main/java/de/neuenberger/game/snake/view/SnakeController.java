package de.neuenberger.game.snake.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import de.neuenberger.game.snake.model.SnakeModel;
import de.neuenberger.game.snake.model.SnakePlayer;
import de.neuenberger.game.snake.model.Vector2D;
import de.neuenberger.game.snake.view.core.GameImageProducer;
import de.neuenberger.game.snake.view.core.ImagePanel;

public class SnakeController {
	SnakeModel model;
	private GameImageProducer gameImageProducer;
	
	public static final Image imageBrick = getImage("wall.png");
	public static final Image imageSnake = getImage("snake_green.png");
	public static final Image imageSnakeU = getImage("snake_green_up.png");
	public static final Image imageSnakeL = getImage("snake_green_left.png");
	public static final Image imageSnakeD = getImage("snake_green_down.png");
	public static final Image imageSnakeR = getImage("snake_green_right.png");
	public static final Image imageApple = getImage("apple.png");
	
	public static final int BLOCK_SIZE = 10;
	
	public SnakeController(SnakeModel model) {
		this(model,new GameImageProducer(BLOCK_SIZE, new SnakePanel(), model.getWidth(),model.getHeight()));
	}
	
	public SnakeController(SnakeModel model, GameImageProducer gameImageProducer) {
		this.model = model;
		this.gameImageProducer = gameImageProducer;
	}
	
	private static Image getImage(String string) {
		ImageIcon imageIcon = new ImageIcon(SnakeController.class.getResource("res/"+string));
		return imageIcon.getImage();
	}

	public void update() {
		gameImageProducer.clear(Color.BLACK);
		
		gameImageProducer.drawListWithImage(model.getBites(), 0, 10, imageApple);
		gameImageProducer.drawListWithImage(model.getBlocks(), 0, imageBrick);
		
		
		SnakePlayer p1 = model.getP1();
		Vector2D head = p1.getDots().get(0);
		Vector2D direction = p1.getDirection();
		Image result = null;
		if (direction.getX()==1) {
			result = imageSnakeR;
		} else if (direction.getX()==-1) {
			result = imageSnakeL;
		} else if (direction.getY()==1) {
			result = imageSnakeD;
		} else if (direction.getY()==-1) {
			result = imageSnakeU;
		}
		gameImageProducer.drawImage(result, head.getX()*BLOCK_SIZE, head.getY()*BLOCK_SIZE);
		gameImageProducer.drawListWithImage(p1.getDots(), 1, imageSnake);
		
		gameImageProducer.repaint();
	}
	
	

	/**
	 * @return the panel
	 */
	public ImagePanel getPanel() {
		return gameImageProducer.getPanel();
	}
}
