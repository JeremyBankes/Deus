package com.jeremy.deus;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.jeremy.deus.ui.DeusButtonUI;
import com.jeremy.deus.ui.state.AdventureState;
import com.jeremy.deus.ui.state.BattleState;
import com.jeremy.deus.ui.state.CreatorState;
import com.jeremy.deus.ui.state.SplashState;
import com.jeremy.deus.ui.state.State;

/**
 * 
 * An extended singleton version of <code>javax.swing.JFrame</code> that adds
 * everything required for the Deus game.
 * 
 * @author Jeremy Bankes
 *
 */

public class Deus extends JFrame {

	public static final Deus INSTANCE = new Deus();

	private CardLayout pages;

	private Deus() {
		super("Deus");
		final Container content = getContentPane();
		content.setLayout(pages = new CardLayout());
		content.setPreferredSize(new Dimension(600, 600));

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				dispose();
			}
		});
	}

	/**
	 * Called right before validating the Deus object. Any pre-game setup should
	 * be done here.
	 */
	private void initiate() {
		DeusButtonUI.use();

		State.register(new SplashState());
		State.register(new CreatorState());
		State.register(new AdventureState());
		State.register(new BattleState());

		State.enter(SplashState.class);
	}

	@Override
	public void pack() {
		initiate();
		super.pack();
	}

	/**
	 * Starts the Deus game.
	 */
	public void start() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Sets the current state by changing the displayed page
	 * 
	 * @param name name of the desired state
	 */
	public void setState(String name) {
		pages.show(getContentPane(), name);
	}

	public CardLayout getPages() {
		return pages;
	}

}
