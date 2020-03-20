package com.jeremy.deus.ui.component;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.jeremy.deus.ui.DeusDisplayConstants;

public class ValueLabel extends JLabel {

	protected ValueLabel(Object initialValue) {
		super();
		setOpaque(true);
		setBorder(new CompoundBorder(new LineBorder(DeusDisplayConstants.COLOR_BORDER, 1), new EmptyBorder(5, 5, 5, 5)));
		setHorizontalAlignment(CENTER);
	}

	public static class Integer extends ValueLabel {

		public Integer(int initialValue) {
			super(initialValue);
			setValue(initialValue);
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

	public static class Float extends ValueLabel {

		public Float(float initialValue) {
			super(initialValue);
			setValue(initialValue);
		}

		protected float value;

		public float getValue() {
			return value;
		}

		public void setValue(float value) {
			this.value = value;
			super.setValue(value);
		}

	}

	private void setValue(Object value) {
		setText(String.valueOf(value));
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension size = super.getPreferredSize();
		size.setSize(size.height, size.height);
		return size;
	}

}
