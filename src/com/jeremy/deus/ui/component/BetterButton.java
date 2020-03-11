package com.jeremy.deus.ui.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class BetterButton extends JButton {

	public BetterButton(String message) {
		super(message);
	}

	@Override
	public void addActionListener(ActionListener listener) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				listener.actionPerformed(new ActionEvent(event.getSource(), event.getID(), null, event.getWhen(), event.getModifiers()));
			}
		});
	}

	public void addActionListener(Runnable runnable) {
		addActionListener(event -> runnable.run());
	}

}
