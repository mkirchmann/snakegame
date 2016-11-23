package de.neuenberger.game.snake.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

public class SnakePlayer {
	List<Vector2D> dots = new LinkedList<Vector2D>();
	Vector2D direction = new Vector2D(0, -1);
	private int lifes = 0;
	private int growth = 0;
	private int points = 0;

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	/**
	 * @return the dots
	 */
	public List<Vector2D> getDots() {
		return dots;
	}

	/**
	 * @param dots
	 *            the dots to set
	 */
	public void setDots(final List<Vector2D> dots) {
		this.dots = dots;
	}

	/**
	 * @return the lifes
	 */
	public int getLifes() {
		return lifes;
	}

	/**
	 * @param lifes
	 *            the lifes to set
	 */
	void setLifes(final int lifes) {
		final PropertyChangeEvent event = new PropertyChangeEvent(this, "lifes", this.lifes, lifes);
		this.lifes = lifes;
		propertyChangeSupport.firePropertyChange(event);
	}

	/**
	 * @return the growth
	 */
	public int getGrowth() {
		return growth;
	}

	/**
	 * @param growth
	 *            the growth to set
	 */
	public void setGrowth(final int growth) {
		this.growth = growth;
	}

	/**
	 * @return the direction
	 */
	public Vector2D getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(final Vector2D direction) {
		this.direction = direction;
	}

	public void clearPoints() {
		setPoints(0);
	}

	public void addPoints(final int p) {
		setPoints(points + p);
	}

	private void setPoints(final int points) {
		final PropertyChangeEvent event = new PropertyChangeEvent(this, "points", this.points, points);
		this.points = points;
		propertyChangeSupport.firePropertyChange(event);
	}

	public int getPoints() {
		return points;
	}

	public void addPropertyChangeListener(final PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	}

}
