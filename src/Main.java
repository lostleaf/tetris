import control.Handler;


public class Main 
{
	public static void main(String args[])
	{
		Handler h=new Handler();
		try {
			h.work();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
