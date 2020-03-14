package com.jeremy.deus.ui.state;

import java.awt.BorderLayout;

import com.jeremy.deus.ui.component.BetterButton;

/**
 * 
 * A splash screen to welcome the user
 * 
 * @author Jeremy
 *
 */
public class SplashState extends State {

	private BetterButton startButton;

	public SplashState() {
		super(new BorderLayout());

		startButton = new BetterButton("Start");
		startButton.addActionListener(() -> {
			State.enter(CreatorState.class);
		});

		add(startButton, BorderLayout.SOUTH);
	}

}
