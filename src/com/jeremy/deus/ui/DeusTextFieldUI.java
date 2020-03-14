package com.jeremy.deus.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;

public class DeusTextFieldUI extends BasicTextFieldUI {

	private final static DeusTextFieldUI INSTANCE = new DeusTextFieldUI();

	/**
	 * Makes all TextFields use the DeusTextFieldUI
	 */
	public static void use() {
		UIManager.put("TextFieldUI", DeusTextFieldUI.class.getName());
	}

	public static ComponentUI createUI(JComponent c) {
		return INSTANCE;
	}

	@Override
	protected void installDefaults() {
		JTextComponent field = getComponent();
		field.setFont(DeusDisplayConstants.FONT_REGULAR);
		field.setBorder(new CompoundBorder(new LineBorder(DeusDisplayConstants.COLOR_BORDER, 1), new EmptyBorder(5, 5, 5, 5)));
		field.setSelectionColor(DeusDisplayConstants.COLOR_SELECTION);
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
//				SoundManager.playSound("/key.wav");
			}
		});
		super.installDefaults();
	}

}
