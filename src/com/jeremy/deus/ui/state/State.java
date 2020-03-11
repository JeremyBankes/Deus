package com.jeremy.deus.ui.state;

import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.Timer;

import com.jeremy.deus.Deus;
import com.jeremy.deus.ui.component.DisplayPanel;

public class State extends DisplayPanel {

	private static final HashMap<Class<? extends State>, State> states = new HashMap<>();

	protected static final Timer timer = new Timer(true);

	public static void enter(Class<? extends State> stateClass) {
		states.get(stateClass).enter();
	}

	public State(LayoutManager layout) {
		super(layout);
	}

	public void register() {
		Deus.INSTANCE.add(getClass().getName(), this);
		states.put(getClass(), this);
	}

	public void enter() {
		Deus.INSTANCE.setState(getClass().getName());
	}

}
