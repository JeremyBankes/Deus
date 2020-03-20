package com.jeremy.deus.ui.state;

import java.awt.BorderLayout;

import com.jeremy.deus.assets.Assets;
import com.jeremy.deus.ui.component.BetterButton;
import com.jeremy.deus.ui.component.DisplayPanel;

/**
 * 
 * A splash screen to welcome the user
 * 
 * @author Jeremy
 *
 */
public class SplashState extends State {

	public SplashState() {
		super(new BorderLayout());
		setBackgroundSizeStrategy(BACKGROUND_REPEAT);
		setBackground(Assets.getImage("background"));

		DisplayPanel display = new DisplayPanel(Assets.getImage("logo"));
		display.setBackgroundSizeStrategy(BACKGROUND_CONTAIN);

		BetterButton button = new BetterButton("Start");
		button.addActionListener(() -> {
			State.enter(CreatorState.class);
		});

		add(display, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}

}
