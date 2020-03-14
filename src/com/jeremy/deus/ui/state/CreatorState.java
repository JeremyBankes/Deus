package com.jeremy.deus.ui.state;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

		{ // Title
			JLabel label = new JLabel("Create Your Character");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			add(label, lay(0, 0, 2, 1));
		}

		{ // Name panel
			JPanel panel = new JPanel(new GridBagLayout());
			JLabel label = new JLabel("Name");
			JTextField field = new JTextField();
			BetterButton button = new BetterButton("Roll");
			panel.add(label, lay(0, 0, 1, 1, 0.0, 1.0));
			panel.add(field, lay(1, 0, 1, 1, 1.0, 1.0));
			panel.add(button, lay(2, 0, 1, 1, 0.0, 1.0));
			add(panel, lay(0, 1, 2, 1));
		}

		{ // Class select panel
			JPanel panel = new JPanel(new GridBagLayout());
			JLabel labelTitle = new JLabel("Choose a Class");
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
			display.setBackground(Color.GREEN);
			panel.add(display, lay(1, 1, 1, 3, 1.0, 1.0));
			add(panel, lay(0, 2, 1, 1));
		}

		{ // Skill roll panel
			JPanel panel = new JPanel(new GridBagLayout());
			JLabel labelTitle = new JLabel("Skills");
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

			BetterButton button = new BetterButton("Reroll");
			Runnable roll = () -> {
				constitution.setValue(RNG.nextInt(100) + 1);
				dexterity.setValue(RNG.nextInt(100) + 1);
				fortitude.setValue(RNG.nextInt(100) + 1);
				power.setValue(RNG.nextInt(100) + 1);
			};
			roll.run();
			button.addActionListener(roll);
			panel.add(button, lay(0, 5, 2, 1));

			add(panel, lay(1, 2, 1, 1));
		}

		{ // Bottom
			JPanel panel = new JPanel(new GridBagLayout());
			JLabel labelTitle1 = new JLabel("Choose a Weapon");
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
			display.setBackground(Color.GREEN);
			panel.add(display, lay(1, 1, 1, 3, 1.0, 1.0));

			JLabel labelTitle2 = new JLabel("Weapon Stats");
			panel.add(labelTitle2, lay(3, 0, 3, 1));
			JLabel label1 = new JLabel("Heightening");
			JLabel label2 = new JLabel("Grace");
			panel.add(label1, lay(3, 1, 1, 1, 1.0, 1.0));
			panel.add(label2, lay(3, 2, 1, 1, 1.0, 1.0));
			heightening = new ValueLabel.Integer(0);
			grace = new ValueLabel.Integer(0);
			panel.add(heightening, lay(4, 1, 1, 1, 0.0, 1.0));
			panel.add(grace, lay(4, 2, 1, 1, 0.0, 1.0));

			add(panel, lay(0, 3, 2, 1));
		}

		{ // Begin Adventure
			BetterButton button = new BetterButton("Begin Adventure");
			add(button, lay(0, 4, 2, 1));
		}

	}

	private static GridBagConstraints lay(int x, int y, int width, int height, double xWeight, double yWeight) {
		return new GridBagConstraints(x, y, width, height, xWeight, yWeight, 256, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
	}

	private static GridBagConstraints lay(int x, int y, int width, int height) {
		return lay(x, y, width, height, 1.0, 1.0);
	}

}
