package com.jeremy.deus.ui.component;

import static java.lang.Math.round;

import java.awt.Color;
import java.awt.Graphics;

public class PercentageBar extends ValueLabel.Float {

	private static final int INSETS = 3;

	private String label;
	private float maxValue;
	private Color color, darkerColor;

	public PercentageBar(String label, float initialValue, float maxValue, Color color) {
		super(initialValue);
		this.label = label;
		this.maxValue = maxValue;
		this.color = color;
		this.darkerColor = color.darker();
		setFont(getFont().deriveFont(20f));
		setValue(initialValue);
	}

	@Override
	public void setValue(float value) {
		this.value = value;
		setText(String.format("%s %.0f/%.0f", label, value, maxValue));
	}

	public float getMaxValue() {
		return maxValue;
	}

	public float getPercentage() {
		return getValue() / getMaxValue();
	}

	@Override
	public boolean isOpaque() {
		return false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (getBackground() != null) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		g.setColor(color);
		g.fillRect(INSETS, INSETS, round((getWidth() - INSETS * 2) * getPercentage()), getHeight() / 2);
		g.setColor(darkerColor);
		g.fillRect(INSETS, getHeight() / 2, round((getWidth() - INSETS * 2) * getPercentage()), getHeight() / 2 - INSETS);
		super.paintComponent(g);
	}

}
