package ui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class UI extends JFrame {
	private static final long serialVersionUID = 8855226818816761045L;
	private ContentPanel contentPanel;
	private JLabel scoreLabel;

	public UI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(305, 663);
		this.getContentPane().setLayout(new FlowLayout());
		this.setLocation(400, 0);

		scoreLabel = new JLabel();
		scoreLabel.setText("score:0");

		contentPanel = new ContentPanel();

		this.getContentPane().add(scoreLabel);
		this.getContentPane().add(contentPanel);

		this.setVisible(true);
	}

	public void setScore(int a) {
		this.scoreLabel.setText("score:" + a);
	}

	public void set(boolean state[][]) {
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 10; j++)
				if (state[i][j])
					contentPanel.setShow(i, j);
				else
					contentPanel.setHide(i, j);
	}
}
