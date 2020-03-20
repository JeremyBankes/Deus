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

import com.jeremy.combat.Fighter;
import com.jeremy.deus.assets.Assets;
import com.jeremy.deus.character.CharacterClassType;
import com.jeremy.deus.character.Player;
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

	private Player player;

	private Deus() {
		super("Deus");
		pages = new CardLayout();
		oldSize = new Dimension(880, 660);
		DisplayPanel content = new DisplayPanel(pages);
		content.setPreferredSize(oldSize);
		setContentPane(content);
		setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		setMinimumSize(new Dimension(880, 660));
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
		Assets.loadImage("battleground1", "/battlegrounds/battleground1.png");
		Assets.loadImage("battleground2", "/battlegrounds/battleground2.png");
		Assets.loadImage("battleground3", "/battlegrounds/battleground3.png");
		Assets.loadImage("battleground4", "/battlegrounds/battleground4.png");
		Assets.loadImage("battleground5", "/battlegrounds/battleground5.png");
		Assets.loadImage("battleground6", "/battlegrounds/battleground6.png");
		Assets.loadImage("battleground7", "/battlegrounds/battleground7.png");
		Assets.loadImage("weapons", "/character/weapons.png");
		Assets.loadImage("icons", "/icons.png");
		Assets.loadImage("background", "/background.png");
		Assets.loadImage("classes", "/character/classes.png");
		Assets.loadImage("background", "/background.png");
		Assets.loadImage("logo", "/logo.png");
		DeusDisplayConstants.initiate();

		setIconImage(Assets.sprite("icons", 0, 16, 16));
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Assets.sprite("icons", 2, 16, 16), new Point(), null));

		Fighter.registerSkill("constitution", x -> x * x / 2);
		Fighter.registerSkill("dexterity", x -> x * x / 2);
		Fighter.registerSkill("fortitude", x -> x * x / 2);

		setPlayer(new Player("Charles", CharacterClassType.WARRIOR, 6, 6, 6));

		State.register(new SplashState());
		State.register(new CreatorState());
		State.register(new AdventureState());
		State.register(new BattleState());

		State.enter(BattleState.class);
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
