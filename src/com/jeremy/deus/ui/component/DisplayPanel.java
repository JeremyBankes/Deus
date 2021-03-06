package com.jeremy.deus.ui.component;

import static java.lang.Math.round;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * An extended version of a JPanel that allows for a background image to be set.
 * 
 * @author Jeremy
 */
public class DisplayPanel extends JPanel {

	// Constants for background sizing and positioning
	public static final int BACKGROUND_COVER = 0;
	public static final int BACKGROUND_CONTAIN = 1;
	public static final int BACKGROUND_STRETCH = 2;
	public static final int BACKGROUND_REPEAT = 3;

	private int backgroundSizeStrategy;
	private Image image;

	public DisplayPanel(Color color) {
		super(null, true);
		setBackground(color);
		setOpaque(true);
	}

	public DisplayPanel(Image image, LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		setBackground(image);
		setOpaque(false);
	}

	public DisplayPanel(Image image, LayoutManager layout) {
		this(image, layout, true);
	}

	public DisplayPanel(LayoutManager layout) {
		this(null, layout);
	}

	public DisplayPanel(Image image) {
		this(image, new FlowLayout());
	}

	public DisplayPanel() {
		this(null, new FlowLayout());
	}

	/**
	 * @return the background image for the component.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets the background image for the component.
	 * 
	 * @param image
	 */
	public void setBackground(Image image) {
		this.image = image;
		repaint();
	}

	/**
	 * @return the background size and positioning strategy.
	 */
	public int getBackgroundSizeStrategy() {
		return backgroundSizeStrategy;
	}

	/**
	 * Sets the background size and positioning strategy.
	 * 
	 * @param backgroundSizeStrategy
	 */
	public void setBackgroundSizeStrategy(int backgroundSizeStrategy) {
		this.backgroundSizeStrategy = backgroundSizeStrategy;
	}

	/**
	 * Overridden to draw and position 'image' according to 'backgroundSizeStrategy'
	 */
	@Override
	protected void paintComponent(Graphics graphics) {
		if (image != null) {
			int x = 0, y = 0, width = 0, height = 0;
			int imageWidth = image.getWidth(this);
			int imageHeight = image.getHeight(this);

			float aspectRatio = (float) getWidth() / getHeight();
			float imageAspectRatio = (float) imageWidth / imageHeight;
			if (getBackgroundSizeStrategy() == BACKGROUND_COVER) {
				if (aspectRatio > imageAspectRatio) {
					width = getWidth();
					height = round(width / imageAspectRatio);
					y = -(height - getHeight()) / 2;
				} else {
					height = getHeight();
					width = round(height * imageAspectRatio);
					x = -(width - getWidth()) / 2;
				}
			} else if (getBackgroundSizeStrategy() == BACKGROUND_CONTAIN) {
				if (aspectRatio > imageAspectRatio) {
					height = getHeight();
					width = round(height * imageAspectRatio);
					x = (getWidth() - width) / 2;
				} else {
					width = getWidth();
					height = round(width / imageAspectRatio);
					y = (getHeight() - height) / 2;
				}
			} else if (getBackgroundSizeStrategy() == BACKGROUND_STRETCH) {
				width = getWidth();
				height = getHeight();
			} else if (getBackgroundSizeStrategy() == BACKGROUND_REPEAT) {
				width = imageWidth;
				height = imageHeight;
				while (y < getHeight()) {
					graphics.drawImage(getImage(), x, y, width, height, null);
					x += width;
					if (x > getWidth()) {
						y += height;
						x = 0;
					}
				}
				return;
			}
			graphics.drawImage(getImage(), x, y, width, height, null);
		}
		super.paintComponent(graphics);
	}

}
