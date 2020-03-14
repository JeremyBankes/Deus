package com.jeremy.deus.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

public class DeusButtonUI extends BasicButtonUI {

	private final static DeusButtonUI INSTANCE = new DeusButtonUI();

	private static final Color COLOR_NORMAL = new Color(0xFFFFFF);
	private static final Color COLOR_HOVER = new Color(0xEEEEEE);

	/**
	 * Makes all Buttons use the DeusButtonUI
	 */
	public static void use() {
		UIManager.put("ButtonUI", DeusButtonUI.class.getName());
	}

	public static ComponentUI createUI(JComponent c) {
		return INSTANCE;
	}

	/**
	 * Set the default look for all buttons
	 */
	@Override
	protected void installDefaults(AbstractButton button) {
		button.setFocusPainted(false);
		button.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1), new EmptyBorder(5, 5, 5, 5)));
		button.setBackground(COLOR_NORMAL);
		button.setFont(new Font("Consolas", Font.PLAIN, 16));
		// TODO click sound effect
		// button.addActionListener(event -> SoundManager.playSound("/button.wav"));
		button.addChangeListener(event -> {
			ButtonModel model = button.getModel();
			if (model.isRollover()) {
				button.setBackground(COLOR_HOVER);
			} else {
				button.setBackground(COLOR_NORMAL);
			}
		});
		super.installDefaults(button);
	}

}
