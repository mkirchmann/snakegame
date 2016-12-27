package de.neuenberger.game.snake.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class SnakeModel {

	public static final String LIFES = "lifes";

	PropertyChangeSupport support = new PropertyChangeSupport(this);

	SnakePlayer p1 = new SnakePlayer();
	SnakePlayer p2 = new SnakePlayer();
	List<Vector2D> blocks = null;
	List<Vector2D> bites = null;

	final int width = 30;
	final int height = 45;

	private int level;

	private boolean twoPlayerGame;

	public void setLifes(final SnakePlayer p, final int x) {
		final PropertyChangeEvent event = new PropertyChangeEvent(p, LIFES, p.getLifes(), x);
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
	 * @param blocks
	 *            the blocks to set
	 */
	public void setBlocks(final List<Vector2D> blocks) {
		this.blocks = blocks;
	}

	/**
	 * @return the bites
	 */
	public List<Vector2D> getBites() {
		return bites;
	}

	/**
	 * @param bites
	 *            the bites to set
	 */
	public void setBites(final List<Vector2D> bites) {
		this.bites = bites;
	}

	/**
	 * @return the p1
	 */
	public SnakePlayer getP1() {
		return p1;
	}

	/**
	 * @param p1
	 *            the p1 to set
	 */
	public void setP1(final SnakePlayer p1) {
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
	 * @param level
	 *            the level to set
	 */
	public void setLevel(final int level) {
		this.level = level;
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(final PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String,
	 *      java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String,
	 *      java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

	public boolean isTwoPlayerGame() {
		return twoPlayerGame;
	}

	public SnakePlayer getP2() {
		return p2;
	}

	public void setTwoPlayerGame(boolean selected) {
		this.twoPlayerGame = selected;
	}

}
