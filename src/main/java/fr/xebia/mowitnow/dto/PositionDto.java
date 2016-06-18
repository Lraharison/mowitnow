package fr.xebia.mowitnow.dto;

import java.io.Serializable;

/**
 * Class position sur une grille.
 * 
 * @author Raharison L
 */
public class PositionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ORIENTATION {
		NORTH, EAST, SOUTH, WEST;
		public static ORIENTATION getValueString(String command) {
			command = command.toUpperCase();
			for (ORIENTATION o : values()) {
				if (o.toString().startsWith(command)) {
					return o;
				}
			}
			return null;
		}
	}

	/** abscisse. */
	private int x;

	/** ordonnee. */
	private int y;

	private ORIENTATION orientation;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ORIENTATION getOrientation() {
		return orientation;
	}

	public void setOrientation(ORIENTATION orientation) {
		this.orientation = orientation;
	}

}
