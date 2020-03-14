package com.jeremy.deus.ui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

public class DeusLabelUI extends BasicLabelUI {

	private final static DeusLabelUI INSTANCE = new DeusLabelUI();

	/**
	 * Makes all Labels use the DeusLabelUI
	 */
	public static void use() {
		UIManager.put("LabelUI", DeusLabelUI.class.getName());
	}

	public static ComponentUI createUI(JComponent c) {
		return INSTANCE;
	}

	@Override
	protected void installDefaults(JLabel label) {
		label.setFont(DeusDisplayConstants.FONT_REGULAR);
		label.setBackground(DeusDisplayConstants.COLOR_COMPONENT);
		label.setForeground(DeusDisplayConstants.COLOR_TEXT);
		super.installDefaults(label);
	}

}
