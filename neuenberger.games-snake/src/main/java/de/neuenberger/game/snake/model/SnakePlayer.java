package de.neuenberger.game.snake.model;

import java.util.LinkedList;
import java.util.List;

public class SnakePlayer {
	List<Vector2D> dots = new LinkedList<Vector2D>();
	Vector2D direction = new Vector2D(0, -1);
	int lifes = 0;
	int growth = 0;
	
	/**
	 * @return the dots
	 */
	public List<Vector2D> getDots() {
		return dots;
	}
	/**
	 * @param dots the dots to set
	 */
	public void setDots(List<Vector2D> dots) {
		this.dots = dots;
	}
	/**
	 * @return the lifes
	 */
	public int getLifes() {
		return lifes;
	}
	/**
	 * @param lifes the lifes to set
	 */
	void setLifes(int lifes) {
		this.lifes = lifes;
	}
	/**
	 * @return the growth
	 */
	public int getGrowth() {
		return growth;
	}
	/**
	 * @param growth the growth to set
	 */
	public void setGrowth(int growth) {
		this.growth = growth;
	}
	/**
	 * @return the direction
	 */
	public Vector2D getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}
	
	
}
