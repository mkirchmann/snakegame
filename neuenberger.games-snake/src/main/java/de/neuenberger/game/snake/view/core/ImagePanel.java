package de.neuenberger.game.snake.view.core;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private Image image;
	
	
	public ImagePanel() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (image!=null) {
			g.drawImage(image, 0, 0, this);
		}
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	public Graphics getImageGraphics() {
		return image.getGraphics();
	}

	public int getImageHeight() {
		return image.getHeight(this);
	}
	
	public int getImageWidth() {
		return image.getWidth(this);
	}
}
