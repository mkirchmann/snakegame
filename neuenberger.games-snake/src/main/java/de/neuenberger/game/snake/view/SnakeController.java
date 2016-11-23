package de.neuenberger.game.snake.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;

import de.neuenberger.game.snake.model.SnakeModel;
import de.neuenberger.game.snake.model.SnakePlayer;
import de.neuenberger.game.snake.model.Vector2D;

public class SnakeController {
	Image image;
	SnakeModel model;
	private SnakePanel panel;
	
	public static final Image imageBrick = getImage("wall.png");
	public static final Image imageSnake = getImage("snake_green.png");
	public static final Image imageSnakeU = getImage("snake_green_up.png");
	public static final Image imageSnakeL = getImage("snake_green_left.png");
	public static final Image imageSnakeD = getImage("snake_green_down.png");
	public static final Image imageSnakeR = getImage("snake_green_right.png");
	public static final Image imageApple = getImage("apple.png");
	
	public static final int BLOCK_SIZE = 10;
	
	public SnakeController(SnakeModel model) {
		this.model = model;
		panel = new SnakePanel();
		image = new BufferedImage(model.getWidth()*BLOCK_SIZE, model.getHeight()*BLOCK_SIZE, BufferedImage.TYPE_INT_RGB);
		panel.setImage(image);
	}
	
	private static Image getImage(String string) {
		ImageIcon imageIcon = new ImageIcon(SnakeController.class.getResource("res/"+string));
		return imageIcon.getImage();
	}

	public void update() {
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0,model.getWidth()*BLOCK_SIZE, model.getHeight()*BLOCK_SIZE);
		
		graphics.setColor(Color.GREEN);
		drawListWithImage(graphics, model.getBites(), 0, 10, imageApple);
		graphics.setColor(Color.LIGHT_GRAY);
		drawListWithImage(graphics, model.getBlocks(), 0, imageBrick);
		graphics.setColor(Color.WHITE);
		
		
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
		graphics.drawImage(result, head.getX()*BLOCK_SIZE, head.getY()*BLOCK_SIZE, 10, 10, panel);
		drawListWithImage(graphics, p1.getDots(), 1, imageSnake);
		
		panel.repaint();
	}
	
	public void drawListWithImage(Graphics g, List<Vector2D> list, int offset, Image image) {
		drawListWithImage(g, list, offset, Integer.MAX_VALUE, image);
	}
	
	public void drawListWithImage(Graphics g, List<Vector2D> list, int offset, int max, Image image) {
		if (list!=null) {
			for (int i=offset; i<list.size() && i<max; i++) {
				Vector2D v = list.get(i);
				int x = v.getX()*BLOCK_SIZE;
				int y = v.getY()*BLOCK_SIZE;
				g.drawImage(image, x, y, 10, 10, panel);
			}
		}
	}
	
	public static void drawListWithColors(Graphics g, List<Vector2D> list, int offset) {
		drawListWithColors(g, list, offset, Integer.MAX_VALUE);
	}
	
	public static void drawListWithColors(Graphics g, List<Vector2D> list, int offset, int max) {
		if (list!=null) {
			for (int i=offset; i<list.size() && i<max; i++) {
				Vector2D v = list.get(i);
				int x = v.getX()*BLOCK_SIZE;
				int y = v.getY()*BLOCK_SIZE;
				g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
			}
		}
	}

	/**
	 * @return the panel
	 */
	public SnakePanel getPanel() {
		return panel;
	}
}
