package com.jeremy.deus.character;

import com.jeremy.deus.tools.StringUtilities;

public enum CharacterClassType {

	WARRIOR,
	MAGE,
	RANGER;

	private CharacterClassType() {

	}

	@Override
	public String toString() {
		return StringUtilities.beautify(super.toString());
	}

}
