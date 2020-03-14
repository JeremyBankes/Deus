package com.jeremy.deus.ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class AlphabeticalDocumentFilter extends DocumentFilter {

	private StringBuilder builder;

	public AlphabeticalDocumentFilter() {
		builder = new StringBuilder();
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		builder.setLength(0);
		for (char c : text.toCharArray()) if (Character.isAlphabetic(c) || c == ' ') builder.append(c);
		if (builder.length() > 0) super.replace(fb, offset, length, builder.toString(), attrs);
	}

}
