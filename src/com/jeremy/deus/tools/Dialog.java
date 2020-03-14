package com.jeremy.deus.tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.jeremy.deus.assets.Assets;
import com.jeremy.deus.ui.DeusDisplayConstants;
import com.jeremy.deus.ui.component.BetterButton;
import com.jeremy.deus.ui.component.DisplayPanel;

public class Dialog {

	public static void showMessage(Component component, String message) {
		JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(component));
		dialog.setUndecorated(true);

		Point startPoint = new Point();

		dialog.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				startPoint.setLocation(event.getPoint());
			}
		});

		dialog.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent event) {
				dialog.setLocation(event.getXOnScreen() - startPoint.x, event.getYOnScreen() - startPoint.y);
			}
		});

		DisplayPanel content = new DisplayPanel(Assets.getImage("background"), new BorderLayout(25, 25));
		content.setBackgroundSizeStrategy(DisplayPanel.BACKGROUND_REPEAT);
		content.setBorder(new CompoundBorder(new LineBorder(DeusDisplayConstants.COLOR_BORDER, 2), new EmptyBorder(25, 25, 25, 25)));

		JLabel label = new JLabel(message);
		content.add(label, BorderLayout.CENTER);

		BetterButton button = new BetterButton("Okay");
		button.addActionListener(() -> dialog.dispose());
		content.add(button, BorderLayout.SOUTH);

		dialog.setContentPane(content);

		dialog.setAlwaysOnTop(true);
		dialog.pack();
		dialog.setLocationRelativeTo(component);
		dialog.setVisible(true);

		new Thread(() -> {
			try {
				while (dialog.isVisible()) {
					Color color = Color.getHSBColor(oscillate(0.0f, 1.0f), 0.5f, 0.5f);
					content.setBorder(new CompoundBorder(new LineBorder(color, 2), new EmptyBorder(25, 25, 25, 25)));
					content.repaint();
					Thread.sleep(1000 / 30);
				}
			} catch (InterruptedException exception) {}
		}).start();
	}

	private static float oscillate(float min, float max) {
		double epoch = (double) System.currentTimeMillis() / 1000;
		return (float) (Math.sin(epoch) / 2 + 0.5f) * (max - min) + min;
	}

}
