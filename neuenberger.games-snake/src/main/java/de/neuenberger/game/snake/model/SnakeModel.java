package de.neuenberger.game.snake.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class SnakeModel {
	
	public static final String LIFES = "lifes";

	PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	SnakePlayer p1 = new SnakePlayer();
	List<Vector2D> blocks = null;
	List<Vector2D> bites = null;
	
	final int width = 30;
	final int height = 45;

	private int level;
	
	public void setLifes(SnakePlayer p, int x) {
		PropertyChangeEvent event = new PropertyChangeEvent(p, LIFES, p.getLifes(), x);
		p.setLifes(x);
		support.firePropertyChange(event);
	}

	/**
	 * @return the blocks
	 */
	public List<Vector2D> getBlocks() {
		return blocks;
	}

	/**
	 * @param blocks the blocks to set
	 */
	public void setBlocks(List<Vector2D> blocks) {
		this.blocks = blocks;
	}

	/**
	 * @return the bites
	 */
	public List<Vector2D> getBites() {
		return bites;
	}

	/**
	 * @param bites the bites to set
	 */
	public void setBites(List<Vector2D> bites) {
		this.bites = bites;
	}

	/**
	 * @return the p1
	 */
	public SnakePlayer getP1() {
		return p1;
	}

	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(SnakePlayer p1) {
		this.p1 = p1;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

}
