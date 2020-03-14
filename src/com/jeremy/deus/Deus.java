package com.jeremy.deus;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

import com.jeremy.deus.assets.Assets;
import com.jeremy.deus.tools.Dialog;
import com.jeremy.deus.ui.DeusDisplayConstants;
import com.jeremy.deus.ui.component.DisplayPanel;
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

	private Dimension oldSize;

	private Deus() {
		super("Deus");
		pages = new CardLayout();
		oldSize = new Dimension(760, 600);
		DisplayPanel content = new DisplayPanel(pages);
		content.setPreferredSize(oldSize);
		setContentPane(content);
		setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		setMinimumSize(new Dimension(600, 600));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				dispose();
			}
		});

		addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent event) {
				if ((event.getNewState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) {
					dispose();
					setUndecorated(true);
					setExtendedState(MAXIMIZED_BOTH);
					setVisible(true);
					Dialog.showMessage(Deus.this, "You have entered fullscreen mode. Press ESC to leave.");
				}
			}

		});

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent event) {
				if (getExtendedState() != MAXIMIZED_BOTH) oldSize = getSize();
			}
		});

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEvent event) -> {
			if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (isUndecorated()) {
					dispose();
					setUndecorated(false);
					setExtendedState(NORMAL);
					setSize(oldSize);
					setLocationRelativeTo(null);
					setVisible(true);
				}
			}
			return false;
		});
	}

	/**
	 * Called right before validating the Deus object. Any pre-game setup should be
	 * done here.
	 */
	private void initiate() {
		Assets.loadImage("icons", "/icons.png");
		DeusDisplayConstants.initiate();

		setIconImage(Assets.sprite("icons", 0, 16, 16));
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Assets.sprite("icons", 2, 16, 16), new Point(), null));

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
