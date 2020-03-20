package com.jeremy.deus.ui.state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.jeremy.deus.Deus;
import com.jeremy.deus.assets.Assets;
import com.jeremy.deus.character.Character;
import com.jeremy.deus.character.Player;
import com.jeremy.deus.ui.DeusDisplayConstants;
import com.jeremy.deus.ui.component.DisplayPanel;
import com.jeremy.deus.ui.component.PercentageBar;

/**
 * 
 * The battle state. A state in which the user fights against opponent(s)
 * 
 * @author Jeremy
 *
 */
public class BattleState extends State {

	private static final Random RNG = new Random();

	private PercentageBar playerHealthBar, playerEnergyBar, playerArmourBar;
	private PercentageBar enemyHealthBar, enemyEnergyBar, enemyArmourBar;

	private Character enemy;

	public BattleState() {
		super(new BorderLayout());

		final Player player = Deus.INSTANCE.getPlayer();

		{ // Top
			DisplayPanel panel = new DisplayPanel(new GridBagLayout());
			panel.setBackground(Assets.getImage("background"));
			panel.setBackgroundSizeStrategy(BACKGROUND_REPEAT);
			Border bottomBorder = new MatteBorder(0, 0, 2, 0, DeusDisplayConstants.COLOR_BORDER);
			panel.setBorder(new CompoundBorder(bottomBorder, new EmptyBorder(15, 15, 15, 15)));

			JLabel label1 = new JLabel(player.getName());
			JLabel label2 = new JLabel(enemy.getName());

			label1.setHorizontalAlignment(JLabel.LEFT);
			label2.setHorizontalAlignment(JLabel.RIGHT);

			panel.add(label1, lay(0, 0, 1, 1, 1.0, 1.0));
			panel.add(label2, lay(1, 0, 1, 1, 1.0, 1.0));

			add(panel, BorderLayout.NORTH);
		}

		{ // Center
			DisplayPanel panel = new DisplayPanel();
			panel.setBackground(Assets.getImage("battleground" + (new Random().nextInt(7) + 1)));
			add(panel, BorderLayout.CENTER);
		}

		{ // Bottom
			DisplayPanel panel = new DisplayPanel(new GridBagLayout());
			panel.setBackground(Assets.getImage("background"));
			panel.setBackgroundSizeStrategy(BACKGROUND_REPEAT);

			Border bottomBorder = new MatteBorder(2, 0, 0, 0, DeusDisplayConstants.COLOR_BORDER);
			panel.setBorder(new CompoundBorder(bottomBorder, new EmptyBorder(15, 15, 15, 15)));

			DisplayPanel panel1 = new DisplayPanel(new GridBagLayout());
			DisplayPanel panel2 = new DisplayPanel(new GridBagLayout());
			DisplayPanel panel3 = new DisplayPanel(new GridBagLayout());

			playerHealthBar = new PercentageBar("Health", player.getHealth(), player.getMaxHealth(), Color.RED);
			playerEnergyBar = new PercentageBar("Energy", player.getEnergy(), player.getMaxEnergy(), Color.BLUE);
			playerArmourBar = new PercentageBar("Armour", player.getArmour(), player.getMaxArmour(), Color.GRAY);

			enemyHealthBar = new PercentageBar("Health", enemy.getHealth(), enemy.getMaxHealth(), Color.RED);
			enemyEnergyBar = new PercentageBar("Energy", enemy.getEnergy(), enemy.getMaxEnergy(), Color.BLUE);
			enemyArmourBar = new PercentageBar("Armour", enemy.getArmour(), enemy.getMaxArmour(), Color.GRAY);

			panel1.add(playerHealthBar, lay(0, 0, 1, 1));
			panel1.add(playerEnergyBar, lay(0, 1, 1, 1));
			if (player.getMaxArmour() > 0) panel1.add(playerArmourBar, lay(0, 2, 1, 1));

			panel3.add(enemyHealthBar, lay(0, 0, 1, 1));
			panel3.add(enemyEnergyBar, lay(0, 1, 1, 1));
			if (enemy.getMaxArmour() > 0) panel3.add(enemyArmourBar, lay(0, 2, 1, 1));

			panel.add(panel1, lay(0, 0, 1, 1));
			panel.add(panel2, lay(1, 0, 1, 1));
			panel.add(panel3, lay(2, 0, 1, 1));

			add(panel, BorderLayout.SOUTH);
		}
	}

}
