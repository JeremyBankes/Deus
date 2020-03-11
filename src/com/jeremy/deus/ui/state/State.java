package com.jeremy.deus.ui.state;

import java.awt.LayoutManager;
import java.util.HashMap;

import com.jeremy.deus.Deus;
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
	 * @param stateClass The class of the state you wish to enter.
	 */
	public static void enter(Class<? extends State> stateClass) {
		states.get(stateClass).enter();
	}

	/**
	 * @param layout The layout for components within the state.
	 */
	public State(LayoutManager layout) {
		super(layout);
	}

	/**
	 * Registers the state in the <code>com.jeremy.deus.State.states</code> map
	 * Registered states can be entered using a class rather than an instance.
	 */
	public void register() {
		Deus.INSTANCE.add(getClass().getName(), this);
		states.put(getClass(), this);
	}

	/**
	 * Changes the current state to this one.
	 */
	public void enter() {
		Deus.INSTANCE.setState(getClass().getName());
	}

}
