package de.neuenberger.game.snake.view;

import java.awt.Color;
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
	public static final Image imageSnake1 = getImage("snake_green.png");
	public static final Image imageSnake1U = getImage("snake_green_up.png");
	public static final Image imageSnake1L = getImage("snake_green_left.png");
	public static final Image imageSnake1D = getImage("snake_green_down.png");
	public static final Image imageSnake1R = getImage("snake_green_right.png");
	public static final Image imageSnake2 = getImage("snake_blue.png");
	public static final Image imageSnake2U = getImage("snake_blue_up.png");
	public static final Image imageSnake2L = getImage("snake_blue_left.png");
	public static final Image imageSnake2D = getImage("snake_blue_down.png");
	public static final Image imageSnake2R = getImage("snake_blue_right.png");
	public static final Image imageApple = getImage("apple.png");
	
	public static final int BLOCK_SIZE = 10;
	
	public SnakeController(SnakeModel model) {
		this(model,new GameImageProducer(BLOCK_SIZE, new ImagePanel(), model.getWidth(),model.getHeight()));
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
		PlayerImageProvider drawer = p1Drawer;
		
		drawPlayer(p1, drawer);
		if (model.isTwoPlayerGame()) {
			drawPlayer(model.getP2(), p2Drawer);
		}
		
		gameImageProducer.repaint();
	}

	private void drawPlayer(SnakePlayer p1, PlayerImageProvider drawer) {
		Vector2D head = p1.getDots().get(0);
		Vector2D direction = p1.getDirection();
		Image result = null;
		if (direction.getX()==1) {
			result = drawer.getImageRight();
		} else if (direction.getX()==-1) {
			result = drawer.getImageLeft();
		} else if (direction.getY()==1) {
			result = drawer.getImageDown();
		} else if (direction.getY()==-1) {
			result = drawer.getImageUp();
		}
		gameImageProducer.drawImage(result, head.getX()*BLOCK_SIZE, head.getY()*BLOCK_SIZE);
		gameImageProducer.drawListWithImage(p1.getDots(), 1, drawer.getImageBody());
	}
	
	PlayerImageProvider p1Drawer = new PlayerImageProvider(imageSnake1R, imageSnake1L, imageSnake1U, imageSnake1D, imageSnake1);
	PlayerImageProvider p2Drawer = new PlayerImageProvider(imageSnake2R, imageSnake2L, imageSnake2U, imageSnake2D,
			imageSnake2);
	
	class PlayerImageProvider {

		final Image imageRight;
		final Image imageLeft;
		final Image imageUp;
		final Image imageDown;
		final Image imageBody;

		public PlayerImageProvider(Image ir, Image il, Image iU, Image iD, Image iB) {
			imageRight = ir;
			imageLeft = il;
			imageUp = iU;
			imageDown = iD;
			imageBody = iB;
		}

		public Image getImageRight() {
			return imageRight;
		}

		public Image getImageLeft() {
			return imageLeft;
		}

		public Image getImageUp() {
			return imageUp;
		}

		public Image getImageDown() {
			return imageDown;
		}

		public Image getImageBody() {
			return imageBody;
		}
	}
	


	/**
	 * @return the panel
	 */
	public ImagePanel getPanel() {
		return gameImageProducer.getPanel();
	}
}
