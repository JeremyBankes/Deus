package com.jeremy.deus.ui.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

public class BetterRadioButton extends JRadioButton {

	public BetterRadioButton(String message) {
		super(message);
	}

	/**
	 * Overridden to have the given action performed occur on the mouse down event
	 * instead of the click event. This gives the UI a more responsive feel.
	 */
	@Override
	public void addActionListener(ActionListener listener) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				listener.actionPerformed(new ActionEvent(event.getSource(), event.getID(), null, event.getWhen(), event.getModifiers()));
			}
		});
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
