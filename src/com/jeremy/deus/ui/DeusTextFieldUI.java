package com.jeremy.deus.ui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
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

import com.jeremy.deus.assets.Assets;

public class DeusTextFieldUI extends BasicTextFieldUI {

	private final static DeusTextFieldUI INSTANCE = new DeusTextFieldUI();

	private final Cursor cursor;

	/**
	 * Makes all TextFields use the DeusTextFieldUI
	 */
	public static void use() {
		UIManager.put("TextFieldUI", DeusTextFieldUI.class.getName());
	}

	public DeusTextFieldUI() {
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(Assets.sprite("icons", 3, 16, 16), new Point(), null);
	}

	public static ComponentUI createUI(JComponent c) {
		return INSTANCE;
	}

	@Override
	protected void installDefaults() {
		JTextComponent field = getComponent();
		field.setFont(DeusDisplayConstants.FONT_REGULAR);
		field.setForeground(DeusDisplayConstants.COLOR_TEXT);
		field.setBorder(new CompoundBorder(new LineBorder(DeusDisplayConstants.COLOR_BORDER, 1), new EmptyBorder(5, 5, 5, 5)));
		field.setSelectionColor(DeusDisplayConstants.COLOR_SELECTION);
		field.setBackground(DeusDisplayConstants.COLOR_COMPONENT);
		field.setCaretColor(DeusDisplayConstants.COLOR_TEXT);
		field.setCursor(cursor);
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
//				SoundManager.playSound("/key.wav");
			}
		});
		super.installDefaults();
	}

}
