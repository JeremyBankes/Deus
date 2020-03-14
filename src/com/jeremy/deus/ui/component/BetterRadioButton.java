package com.jeremy.deus.ui.component;

import javax.swing.JRadioButton;

public class BetterRadioButton extends JRadioButton {

	public BetterRadioButton(String message) {
		super(message);
	}

	/**
	 * Takes in a Runnable interface parameter instead of a ActionListener to allow
	 * for lambdas.
	 * 
	 * @param runnable A runnable to execute on button fire.
	 */
	public void addActionListener(Runnable runnable) {
		addActionListener(event -> runnable.run());
	}

}
