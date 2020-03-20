package com.jeremy.deus.ui.state;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.HashMap;

import com.jeremy.deus.Deus;
import com.jeremy.deus.ui.DeusDisplayConstants;
import com.jeremy.deus.ui.component.DisplayPanel;

/**
 * 
 * A class to represent the current state of the game. Because the state will
 * always have a display, this class can extend DisplayPanel.
 * 
 * @author Jeremy
 *
 */
public class State extends DisplayPanel {

	/**
	 * A static map off all states.
	 */
	private static final HashMap<Class<? extends State>, State> states = new HashMap<>();

	/**
	 * Registers the state in the <code>com.jeremy.deus.State.states</code> map
	 * Registered states can be entered using a class rather than an instance.
	 * 
	 * @param state The state to register
	 */
	public static void register(State state) {
		Deus.INSTANCE.add(state.getClass().getName(), state);
		states.put(state.getClass(), state);
	}

	/**
	 * @param stateClass The class of the state you wish to enter.
	 */
	public static void enter(Class<? extends State> stateClass) {
		states.get(stateClass).enter();
	}

	public static <T extends State> T getState(Class<T> stateClass) {
		return stateClass.cast(states.get(stateClass));
	}

	/**
	 * @param layout The layout for components within the state.
	 */
	public State(LayoutManager layout) {
		super(layout);
		setBackground(DeusDisplayConstants.COLOR_BACKGROUND);
	}

	/**
	 * Changes the current state to this one.
	 */
	public void enter() {
		Deus.INSTANCE.setState(getClass().getName());
	}

	protected static GridBagConstraints lay( //
			int x, int y, int width, int height, double xWeight, double yWeight, int top, int left, int bottom, int right //
	) {
		return new GridBagConstraints(x, y, width, height, xWeight, yWeight, 256, 1, new Insets(top, left, bottom, right), 0, 0);
	}

	protected static GridBagConstraints lay(int x, int y, int width, int height, double xWeight, double yWeight) {
		return lay(x, y, width, height, xWeight, yWeight, 0, 0, 0, 0);
	}

	protected static GridBagConstraints lay(int x, int y, int width, int height) {
		return lay(x, y, width, height, 1.0, 1.0);
	}

}
