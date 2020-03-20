package com.jeremy.deus.ui.state;

import java.awt.GridBagLayout;
import java.util.Enumeration;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import com.jeremy.deus.Deus;
import com.jeremy.deus.assets.Assets;
import com.jeremy.deus.character.CharacterClassType;
import com.jeremy.deus.character.Player;
import com.jeremy.deus.item.WeaponType;
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

	private JTextField playerName;

	private ValueLabel.Integer constitution, dexterity, fortitude, power;
	private ValueLabel.Integer heightening, grace;

	private CharacterClassType playerClass;
	private WeaponType weapon;

	private DisplayPanel classDisplay, weaponDisplay;
	private ButtonGroup weaponButtons;

	public CreatorState() {
		super(new GridBagLayout());
		setOpaque(true);
		setBackground(DeusDisplayConstants.COLOR_BACKGROUND);
		setBackground(Assets.getImage("background"));
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
			playerName = new JTextField();
			PlainDocument document = (PlainDocument) playerName.getDocument();
			document.setDocumentFilter(new AlphabeticalDocumentFilter());
			BetterButton button = new BetterButton(Assets.sprite("icons", 1, 16, 16));
			panel.add(label, lay(0, 0, 1, 1, 0.0, 1.0));
			panel.add(playerName, lay(1, 0, 1, 1, 1.0, 1.0, 0, 5, 0, 5));
			panel.add(button, lay(2, 0, 1, 1, 0.0, 1.0));
			add(panel, lay(0, 1, 2, 1, 1.0, 0.0, 5, 5, 5, 5));

			button.addActionListener(() -> playerName.setText(NameGenerator.getName()));
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
			classDisplay = new DisplayPanel();
			classDisplay.setBackgroundSizeStrategy(DisplayPanel.BACKGROUND_CONTAIN);
			panel.add(classDisplay, lay(1, 1, 1, 3, 1.0, 1.0));

			radio1.addActionListener(() -> chooseCharacterClass(CharacterClassType.WARRIOR));
			radio2.addActionListener(() -> chooseCharacterClass(CharacterClassType.RANGER));
			radio3.addActionListener(() -> chooseCharacterClass(CharacterClassType.MAGE));

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
				constitution.setValue(RNG.nextInt(50) + 1);
				dexterity.setValue(RNG.nextInt(50) + 1);
				fortitude.setValue(RNG.nextInt(50) + 1);
				power.setValue((constitution.getValue() + dexterity.getValue() + fortitude.getValue()) / 3);
			};
			roll.run();
			button.addActionListener(roll);
			panel.add(button, lay(0, 5, 2, 1, 1.0, 1.0, 5, 0, 5, 0));

			add(panel, lay(1, 2, 1, 1, 1.0, 1.0, 5, 5, 5, 5));
		}

		{ // Choose weapon panel
			JPanel panel = new JPanel(new GridBagLayout());
			panel.setOpaque(false);
			JLabel labelTitle1 = header("Choose a Weapon");
			panel.add(labelTitle1, lay(0, 0, 2, 1));
			weaponButtons = new ButtonGroup();
			BetterRadioButton radio1 = new BetterRadioButton("");
			BetterRadioButton radio2 = new BetterRadioButton("");
			BetterRadioButton radio3 = new BetterRadioButton("");
			weaponButtons.add(radio1);
			weaponButtons.add(radio2);
			weaponButtons.add(radio3);
			panel.add(radio1, lay(0, 1, 1, 1, 0.0, 1.0));
			panel.add(radio2, lay(0, 2, 1, 1, 0.0, 1.0));
			panel.add(radio3, lay(0, 3, 1, 1, 0.0, 1.0));
			weaponDisplay = new DisplayPanel();
			weaponDisplay.setBackgroundSizeStrategy(DisplayPanel.BACKGROUND_CONTAIN);
			panel.add(weaponDisplay, lay(1, 1, 1, 3, 1.0, 1.0));

			radio1.addActionListener(() -> chooseWeapon(WeaponType.getByIndex(playerClass.ordinal() * 3 + 0)));
			radio2.addActionListener(() -> chooseWeapon(WeaponType.getByIndex(playerClass.ordinal() * 3 + 1)));
			radio3.addActionListener(() -> chooseWeapon(WeaponType.getByIndex(playerClass.ordinal() * 3 + 2)));

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
				if (playerName == null || playerName.getText().length() == 0) {
					Dialog.showMessage(this, "Please choose a name for your character before continuing!");
					return;
				} else if (playerClass == null) {
					Dialog.showMessage(this, "Please choose a class before continuing!");
					return;
				} else if (weapon == null) {
					Dialog.showMessage(this, "Please choose a weapon before continuing!");
					return;
				}
				Player player = new Player(playerName.getText(), playerClass, constitution.getValue(), dexterity.getValue(), fortitude.getValue());
				player.setWeaponType(weapon);
				Deus.INSTANCE.setPlayer(player);
				State.enter(BattleState.class);
			});

			add(button, lay(0, 4, 2, 1, 1.0, 0.0, 5, 5, 5, 5));
		}
	}

	private void chooseCharacterClass(CharacterClassType characterClass) {
		classDisplay.setBackground(Assets.sprite("classes", characterClass.ordinal(), 300, 220));
		if (playerClass != characterClass) {
			playerClass = characterClass;
			chooseWeapon(weapon);
			int i = 0;
			Enumeration<AbstractButton> iterator = weaponButtons.getElements();
			while (iterator.hasMoreElements()) {
				AbstractButton button = iterator.nextElement();
				button.setText(WeaponType.getByIndex(playerClass.ordinal() * 3 + i).toString());
				i++;
			}
		}
	}

	private void chooseWeapon(WeaponType weaponType) {
		if (weaponType == null) return;
		weaponType = WeaponType.getByIndex(playerClass.ordinal() * 3 + weaponType.ordinal() % 3);
		if (weapon != weaponType) {
			weaponDisplay.setBackground(weaponType.getTexture());
			weapon = weaponType;
			heightening.setValue(weapon.getHeightening());
			grace.setValue(weapon.getGrace());
		}
	}

	private static JLabel header(String text) {
		JLabel header = new JLabel(text);
		header.setFont(DeusDisplayConstants.FONT_HEADER);
		header.setForeground(DeusDisplayConstants.COLOR_HEADER);
		header.setBorder(new EmptyBorder(15, 5, 15, 5));
		return header;
	}

}
