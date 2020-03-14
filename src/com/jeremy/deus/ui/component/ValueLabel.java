package com.jeremy.deus.ui.component;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ValueLabel extends JLabel {

	private ValueLabel(Object initialValue) {
		super();
		setBackground(Color.WHITE);
		setOpaque(true);
		setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1), new EmptyBorder(5, 5, 5, 5)));
		setValue(initialValue);
	}

	public static class Integer extends ValueLabel {

		public Integer(int initialValue) {
			super(initialValue);
		}

		private int value;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
			super.setValue(value);
		}

	}

	private void setValue(Object value) {
		setText(String.valueOf(value));
	}

}
