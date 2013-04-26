package ui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class ContentPanel extends JPanel 
{
	private static final long serialVersionUID = 3111829872066623341L;
	private Unit[][] unit;
	ContentPanel()
	{
		unit=new Unit[20][10];
		this.setPreferredSize(new Dimension(300,600));
		this.setLayout(new GridLayout(20,10));
		for(int i=0;i<20;i++)
			for(int j=0;j<10;j++)
			{
				unit[i][j]=new Unit();
				this.add(unit[i][j]);
				unit[i][j].hide();
			}
	}
	public void setShow(int i,int j)
	{
		unit[i][j].show();
	}
	public void setHide(int i,int j)
	{
		unit[i][j].hide();
	}
}
