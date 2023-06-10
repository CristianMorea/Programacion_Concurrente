package Monitores;

//CONSUMIDOR = SI HAY TARTA LE RESTO 1 A LA VARIABLE
//CONSUMIDOR =SI NO HAY TARTA DESPIERTO AL COSINERO Y ME DUERMO
//COSINERO = ME DUERMO ESPERANDO A QUE ME LLAMEN
//CO


public class Principal implements Runnable{
    private static monitor mon = new monitor();
	private boolean consumidor;
	private static int tarta=0;
	private static Object lock =new Object();
	
	

	public Principal(boolean consumidor) {
		this.consumidor = consumidor;
	}
	
	@Override
	public void run() 
	{
		int conta= mon.inc();
		System.out.println(conta);
		
	}
	
	private void consumiendo()
	{
		synchronized (lock)
		{
			if(tarta>0)
			{
				tarta --;
				System.out.println("quedan"+tarta+" porciones de tarta");
				//PARAMOS LA EJECUCION DEL HILO DURANTE 1000 MILISEGUNDOS
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				lock.notifyAll();
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}

	private void cocinando() 
	{
		synchronized (lock) 
		{
			if(tarta==0)
			{
				tarta=10;
				System.out.println("soy el cocinero y quedan "+tarta+" tartas");
				//DESPERTAMOS A TODOS LOS HILOS
				lock.notifyAll();
				
			
			}
			try {
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) 
	{
		//LANZAR HILOS
		int nHilos =8;
		
		Thread[] hilos = new Thread[nHilos];
		
		for (int i=0;i<hilos.length;i++)
		{
			Runnable runnable = null;
			//CON EL IF DIFERENCIAMOS SI ERES COCINERO O CONSUMIDOR
			if(i !=0)
			{
				runnable = new Principal(true);
			}
			else
			{
				runnable =new Principal(false);
			}
			hilos[i] = new Thread(runnable);
			hilos[i].start();
			
		}
		
		for (int i=0;i<hilos.length;i++)
		{
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
       
	}

	

}
