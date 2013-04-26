package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import ui.UI;

public class Handler 
{
	boolean[][] map;
	UI ui;
	Brick brick;
	private int score=0;
	private int space=300;
	class Brick
	{
		int ox,oy,kind;
		int[][] cx,cy;
		Brick()
		{
			oy=3;ox=0;
			cx=new int[4][4];
			cy=new int[4][4];
			kind=3;
			int choose=(int)(Math.random()*7);
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
				{
					cx[i][j]=Data.cx[choose][i][j];
					cy[i][j]=Data.cy[choose][i][j];
				}
			
		}
		public void refreshUI()
		{
			boolean[][] tmp=new boolean[20][10];
			for(int i=0;i<20;i++)
				for(int j=0;j<10;j++)
					tmp[i][j]=map[i][j];
			brick.update(tmp);
			ui.set(tmp);			
		}
		private boolean legal(int x,int y)
		{
			return x<20&&y>=0&&y<10;
		}
		public boolean ableToFall()
		{
			for(int i=0;i<4;i++)
			{
				int x=ox+cx[kind][i]+1;
				int y=oy+cy[kind][i];
				if(!legal(x,y))return false;
				if(x<0)continue;
				if(map[x][y])return false;
			}
			return true;
		}
		private boolean ableToLeftShift()
		{
			for(int i=0;i<4;i++)
			{
				int x=ox+cx[kind][i];
				int y=oy+cy[kind][i]-1;
				if(!legal(x,y))return false;
				if(x<0)continue;
				if(map[x][y])return false;
			}
			return true;
		}		
		private boolean ableToRightShift()
		{
			for(int i=0;i<4;i++)
			{
				int x=ox+cx[kind][i];
				int y=oy+cy[kind][i]+1;
				if(!legal(x,y))return false;
				if(x<0)continue;
				if(map[x][y])return false;
			}
			return true;
		}		
		private boolean ableToRotate()
		{
			int k=(kind+1)%4;
			for(int i=0;i<4;i++)
			{
				int x=ox+cx[k][i];
				int y=oy+cy[k][i];
				if(!legal(x,y))return false;
				
				if(x<0)continue;
				if(map[x][y])return false;
			}
			return true;
		}
		public boolean rotate()
		{
			if(!ableToRotate())
				return false;
			kind=(kind+1)%4;
			return true;
		}
		public boolean leftShift()
		{
			if(!ableToLeftShift())
				return false;
			oy--;
			return true;
		}
		public boolean rightShift()
		{
			if(!ableToRightShift())
				return false;
			oy++;
			return true;			
		}
		public boolean fall()
		{
			if(!ableToFall())
				return false;
			ox++;
			return true;
		}
		public void update(boolean[][] mat)
		{
			for(int i=0;i<4;i++)
			{
				int x=ox+cx[kind][i];
				int y=oy+cy[kind][i];
				if(x<0)continue;
				mat[x][y]=true;
			}
		}
	}
	class KListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode()==37)
				if(brick.leftShift())
				  brick.refreshUI();
			if(e.getKeyCode()==38)
				if(brick.rotate())
					brick.refreshUI();
			if(e.getKeyCode()==39)
				if(brick.rightShift())
					brick.refreshUI();
			if(e.getKeyCode()==40)
				space=30;
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public Handler()
	{
		map=new boolean[20][10];
		ui=new UI();
	}
	private void remove()
	{
		boolean[] full=new boolean[20];
		boolean f=false;
		for(int i=0;i<20;i++)
		{
			full[i]=true;
			for(int j=0;j<10;j++)
				full[i]&=map[i][j];
			f|=full[i];
		}		
		if(!f)return;
		int k=19;
		for(int i=19;i>=0;i--)
			if(!full[i])
			{
				for(int j=0;j<10;j++)
				{
					boolean t=map[i][j];
					map[i][j]=false;map[k][j]=t;
				}
				k--;
			}
		if(k==0)score+=100;
		if(k==1)score+=300;
		if(k==2)score+=500;
		if(k==3)score+=800;
		ui.setScore(score);
		//System.out.println(score);
	}
	public void work() throws InterruptedException
	{
		ui.addKeyListener(new KListener());
		while(true)
		{
			space=300;
			ui.requestFocus();
			brick=new Brick();
			if(!brick.ableToFall())
			{
				//System.out.println(score);
				JOptionPane.showMessageDialog(ui, "游戏结束，您的得分是："+score);
				System.exit(0);
			}
			while(brick.fall())
			{
				this.remove();
				brick.refreshUI();
				Thread.sleep(space);
			}
		
			brick.update(map);
		}
	}
	
}
