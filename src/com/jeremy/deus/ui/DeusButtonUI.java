package com.jeremy.deus.ui;

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
		button.setBorder(new CompoundBorder(new LineBorder(DeusDisplayConstants.COLOR_BORDER, 1), new EmptyBorder(5, 5, 5, 5)));
		button.setBackground(DeusDisplayConstants.COLOR_COMPONENT);
		button.setFont(DeusDisplayConstants.FONT_REGULAR);
		button.addChangeListener(event -> {
			ButtonModel model = button.getModel();
			if (model.isRollover()) {
				button.setBackground(DeusDisplayConstants.COLOR_COMPONENT.darker());
			} else {
				button.setBackground(DeusDisplayConstants.COLOR_COMPONENT);
			}
		});
		super.installDefaults(button);
	}

}
