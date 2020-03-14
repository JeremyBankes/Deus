package com.jeremy.deus.ui.state;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import com.jeremy.deus.assets.Assets;
import com.jeremy.deus.character.CharacterClassType;
import com.jeremy.deus.item.ItemType;
import com.jeremy.deus.tools.Dialog;
import com.jeremy.deus.tools.NameGenerator;
import com.jeremy.deus.ui.AlphabeticalDocumentFilter;
import com.jeremy.deus.ui.DeusDisplayConstants;
import com.jeremy.deus.ui.component.BetterButton;
import com.jeremy.deus.ui.component.BetterRadioButton;
import com.jeremy.deus.ui.component.DisplayPanel;
import com.jeremy.deus.ui.component.ValueLabel;

/**
 * 
 * The character creation state. The state in which the user creates their
 * character.
 * 
 * @author Jeremy
 *
 */
public class CreatorState extends State {

	private static final Random RNG = new Random();

	private ValueLabel.Integer constitution, dexterity, fortitude, power;
	private ValueLabel.Integer heightening, grace;

	public CreatorState() {
		super(new GridBagLayout());

		Assets.loadImage("background", "/background.png");
		Assets.loadImage("classes", "/classes.png");
		setOpaque(true);
		setBackground(DeusDisplayConstants.COLOR_BACKGROUND);
		setImage(Assets.getImage("background"));
		setBackgroundSizeStrategy(BACKGROUND_REPEAT);

		{ // Title
			JLabel label = header("Create Your Character");
			label.setBorder(new EmptyBorder(15, 15, 15, 15));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			add(label, lay(0, 0, 2, 1, 1.0, 0.0, 5, 5, 5, 5));
		}

		{ // Name panel
			JPanel panel = new JPanel(new GridBagLayout());
			panel.setOpaque(false);
			JLabel label = new JLabel("Name");
			JTextField field = new JTextField();
			PlainDocument document = (PlainDocument) field.getDocument();
			document.setDocumentFilter(new AlphabeticalDocumentFilter());
			BetterButton button = new BetterButton(Assets.sprite("icons", 1, 16, 16));
			panel.add(label, lay(0, 0, 1, 1, 0.0, 1.0));
			panel.add(field, lay(1, 0, 1, 1, 1.0, 1.0, 0, 5, 0, 5));
			panel.add(button, lay(2, 0, 1, 1, 0.0, 1.0));
			add(panel, lay(0, 1, 2, 1, 1.0, 0.0, 5, 5, 5, 5));

			button.addActionListener(() -> {
				field.setText(NameGenerator.generateName());
			});
		}

		{ // Class select panel
			JPanel panel = new JPanel(new GridBagLayout());
			panel.setOpaque(false);
			JLabel labelTitle = header("Choose a Class");
			panel.add(labelTitle, lay(0, 0, 2, 1));
			ButtonGroup group = new ButtonGroup();
			BetterRadioButton radio1 = new BetterRadioButton("Warrior");
			BetterRadioButton radio2 = new BetterRadioButton("Ranger");
			BetterRadioButton radio3 = new BetterRadioButton("Mage");
			group.add(radio1);
			group.add(radio2);
			group.add(radio3);
			panel.add(radio1, lay(0, 1, 1, 1, 0.0, 1.0));
			panel.add(radio2, lay(0, 2, 1, 1, 0.0, 1.0));
			panel.add(radio3, lay(0, 3, 1, 1, 0.0, 1.0));
			DisplayPanel display = new DisplayPanel();
			display.setBackgroundSizeStrategy(DisplayPanel.BACKGROUND_CONTAIN);
			panel.add(display, lay(1, 1, 1, 3, 1.0, 1.0));

			radio1.addActionListener(() -> chooseCharacterClass(CharacterClassType.WARRIOR, display));
			radio2.addActionListener(() -> chooseCharacterClass(CharacterClassType.RANGER, display));
			radio3.addActionListener(() -> chooseCharacterClass(CharacterClassType.MAGE, display));

			add(panel, lay(0, 2, 1, 1, 1.0, 1.0, 5, 5, 5, 5));
		}

		{ // Stats roll panel
			JPanel panel = new JPanel(new GridBagLayout());
			panel.setOpaque(false);
			JLabel labelTitle = header("Stats");
			panel.add(labelTitle, lay(0, 0, 2, 1));
			JLabel label1 = new JLabel("Constitution");
			JLabel label2 = new JLabel("Dexterity");
			JLabel label3 = new JLabel("Fortitude");
			JLabel label4 = new JLabel("Power");
			panel.add(label1, lay(0, 1, 1, 1, 1.0, 1.0));
			panel.add(label2, lay(0, 2, 1, 1, 1.0, 1.0));
			panel.add(label3, lay(0, 3, 1, 1, 1.0, 1.0));
			panel.add(label4, lay(0, 4, 1, 1, 1.0, 1.0));

			constitution = new ValueLabel.Integer(0);
			dexterity = new ValueLabel.Integer(0);
			fortitude = new ValueLabel.Integer(0);
			power = new ValueLabel.Integer(0);
			panel.add(constitution, lay(1, 1, 1, 1, 0.0, 1.0));
			panel.add(dexterity, lay(1, 2, 1, 1, 0.0, 1.0));
			panel.add(fortitude, lay(1, 3, 1, 1, 0.0, 1.0));
			panel.add(power, lay(1, 4, 1, 1, 0.0, 1.0));

			BetterButton button = new BetterButton("Reroll Character Stats");
			Runnable roll = () -> {
				constitution.setValue(RNG.nextInt(100) + 1);
				dexterity.setValue(RNG.nextInt(100) + 1);
				fortitude.setValue(RNG.nextInt(100) + 1);
				power.setValue(RNG.nextInt(100) + 1);
			};
			roll.run();
			button.addActionListener(roll);
			panel.add(button, lay(0, 5, 2, 1, 1.0, 1.0, 5, 0, 5, 0));

			add(panel, lay(1, 2, 1, 1, 1.0, 1.0, 5, 5, 5, 5));
		}

		{ // Choose class panel
			JPanel panel = new JPanel(new GridBagLayout());
			panel.setOpaque(false);
			JLabel labelTitle1 = header("Choose a Weapon");
			panel.add(labelTitle1, lay(0, 0, 2, 1));
			ButtonGroup group = new ButtonGroup();
			BetterRadioButton radio1 = new BetterRadioButton("Option I");
			BetterRadioButton radio2 = new BetterRadioButton("Option II");
			BetterRadioButton radio3 = new BetterRadioButton("Option III");
			group.add(radio1);
			group.add(radio2);
			group.add(radio3);
			panel.add(radio1, lay(0, 1, 1, 1, 0.0, 1.0));
			panel.add(radio2, lay(0, 2, 1, 1, 0.0, 1.0));
			panel.add(radio3, lay(0, 3, 1, 1, 0.0, 1.0));
			DisplayPanel display = new DisplayPanel();
			display.setBackgroundSizeStrategy(DisplayPanel.BACKGROUND_CONTAIN);
			panel.add(display, lay(1, 1, 1, 3, 1.0, 1.0));

			radio1.addActionListener(() -> chooseWeapon(ItemType.DAGGER, display));
			radio2.addActionListener(() -> chooseWeapon(ItemType.SHORTSWORD, display));
			radio3.addActionListener(() -> chooseWeapon(ItemType.RAPIER, display));

			add(panel, lay(0, 3, 1, 1, 1.0, 1.0));
		}

		{ // Weapon stats panel
			JPanel panel = new JPanel(new GridBagLayout());
			panel.setOpaque(false);
			JLabel labelTitle2 = header("Weapon Stats");
			panel.add(labelTitle2, lay(3, 0, 3, 1));
			JLabel label1 = new JLabel("Heightening");
			JLabel label2 = new JLabel("Grace");
			panel.add(label1, lay(3, 1, 1, 1, 1.0, 1.0));
			panel.add(label2, lay(3, 2, 1, 1, 1.0, 1.0));
			heightening = new ValueLabel.Integer(0);
			grace = new ValueLabel.Integer(0);
			panel.add(heightening, lay(4, 1, 1, 1, 0.0, 1.0));
			panel.add(grace, lay(4, 2, 1, 1, 0.0, 1.0));
			add(panel, lay(1, 3, 1, 1, 1.0, 1.0, 5, 5, 5, 5));
		}

		{ // Begin Adventure

			BetterButton button = new BetterButton("Begin Adventure");

			button.addActionListener(() -> {
				Dialog.showMessage(this, "This game is not yet implemented.");
			});

			add(button, lay(0, 4, 2, 1, 1.0, 0.0, 5, 5, 5, 5));
		}
	}

	private void chooseCharacterClass(CharacterClassType characterClass, DisplayPanel display) {
		display.setImage(Assets.sprite("classes", characterClass.ordinal(), 16, 16));
	}

	private void chooseWeapon(ItemType weaponType, DisplayPanel display) {
		display.setImage(weaponType.getTexture());
	}

	private static GridBagConstraints lay( //
			int x, int y, int width, int height, double xWeight, double yWeight, int top, int left, int bottom, int right //
	) {
		return new GridBagConstraints(x, y, width, height, xWeight, yWeight, 256, 1, new Insets(top, left, bottom, right), 0, 0);
	}

	private static GridBagConstraints lay(int x, int y, int width, int height, double xWeight, double yWeight) {
		return lay(x, y, width, height, xWeight, yWeight, 0, 0, 0, 0);
	}

	private static GridBagConstraints lay(int x, int y, int width, int height) {
		return lay(x, y, width, height, 1.0, 1.0);
	}

	private static JLabel header(String text) {
		JLabel header = new JLabel(text);
		header.setFont(DeusDisplayConstants.FONT_HEADER);
		header.setForeground(DeusDisplayConstants.COLOR_HEADER);
		header.setBorder(new EmptyBorder(15, 5, 15, 5));
		return header;
	}

}
