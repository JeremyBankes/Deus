package com.jeremy.deus.ui.component;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BetterButton extends JButton {

	public BetterButton(String message) {
		super(message);
	}

	public BetterButton(String message, Icon icon) {
		super(message, icon);
	}

	public BetterButton(String message, Image icon) {
		this(message, new ImageIcon(icon));
	}

	public BetterButton(ImageIcon icon) {
		super(icon);
	}

	public BetterButton(Image icon) {
		this(new ImageIcon(icon));
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
	 * for lambda actions.
	 * 
	 * @param runnable A runnable to execute on button fire.
	 */
	public void addActionListener(Runnable runnable) {
		addActionListener(event -> runnable.run());
	}

}
