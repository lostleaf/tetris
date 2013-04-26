package ui;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class Unit extends JPanel 
{
	
	private static final long serialVersionUID = -2639730617713216793L;
	final static int size=30;
	Unit()
	{
		this.setPreferredSize(new Dimension(size,size));
	}
	public void show()
	{
		Border border=BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		this.setBorder(border);
		this.setBackground(Color.BLUE);
	}
	public void hide()
	{
		this.setBorder(null);
		this.setBackground(Color.WHITE);
	}
}
