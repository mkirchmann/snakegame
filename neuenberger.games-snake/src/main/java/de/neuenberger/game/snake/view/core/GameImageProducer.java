package de.neuenberger.game.snake.view.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import de.neuenberger.game.snake.model.Vector2D;
import de.neuenberger.game.snake.view.SnakePanel;

public class GameImageProducer {
	private int blockSize;
	private ImagePanel imagePanel;

	public GameImageProducer(int blockSize, ImagePanel imagePanel, int width, int height) {
		this(blockSize,imagePanel,createImage(blockSize,width,height));
	}
	
	public GameImageProducer(int blockSize, ImagePanel imagePanel, Image image) {
		this.blockSize = blockSize;
		this.imagePanel = imagePanel;
		this.imagePanel.setImage(image);
	}
	
	public static Image createImage(int blockSize, int width, int height) {
		return new BufferedImage(width*blockSize, height*blockSize, BufferedImage.TYPE_INT_RGB);
	}

	public void drawListWithImage(List<Vector2D> list, int offset, Image image) {
		drawListWithImage(list, offset, Integer.MAX_VALUE, image);
	}
	
	public void drawListWithImage(List<Vector2D> list, int offset, int max, Image image) {
		if (list!=null) {
			Graphics g = getImageGraphics();
			for (int i=offset; i<list.size() && i<max; i++) {
				Vector2D v = list.get(i);
				int x = v.getX()*blockSize;
				int y = v.getY()*blockSize;
				g.drawImage(image, x, y, imagePanel);
			}
		}
	}
	
	public void drawListWithColors(Color color, List<Vector2D> list, int offset) {
		drawListWithColors(color, list, offset, Integer.MAX_VALUE);
	}
	
	public void drawListWithColors(Color color, List<Vector2D> list, int offset, int max) {
		if (list!=null) {
			Graphics g = getImageGraphics();
			g.setColor(color);
			for (int i=offset; i<list.size() && i<max; i++) {
				Vector2D v = list.get(i);
				int x = v.getX()*blockSize;
				int y = v.getY()*blockSize;
				g.fillRect(x, y, blockSize, blockSize);
			}
		}
	}
	
	public Graphics getImageGraphics() {
		return imagePanel.getImageGraphics();
	}

	public void drawImage(Image result, int x, int y) {
		getImageGraphics().drawImage(result, x, y, imagePanel);
	}

	public ImagePanel getPanel() {
		return imagePanel;
	}

	public void repaint() {
		imagePanel.repaint();
	}

	public void clear(Color black) {
		Graphics graphics = getImageGraphics();
		graphics.setColor(black);
		graphics.fillRect(0, 0, imagePanel.getImageWidth(), imagePanel.getImageHeight());
		
	}
}
