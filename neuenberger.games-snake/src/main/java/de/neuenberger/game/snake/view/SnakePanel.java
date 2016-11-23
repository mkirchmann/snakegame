package de.neuenberger.game.snake.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class SnakePanel extends JPanel {
	private Image image;
	
	
	SnakePanel() {
		
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
}
