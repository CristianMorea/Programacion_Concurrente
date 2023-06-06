package Hilos_implement_Runable;

import java.util.Objects;

public class Principal implements Runnable
{
   private static int cont =0;
   private static Object  object =new Object();
	@Override 
	public void run() 
	{
		
		synchronized (object)//ACTIVO:H1  COLA:H4, H3 
		{
		//SECCION CRITICA CONTROLADA
		for(int i=0; i< 2000;i++)
		{
			
				cont ++;
			}
		}
		
	}

	

	public static void main(String[] args) 
	{
		//METODO PARA SABER LOS NUCLEOS DE MI PC
		Runtime runtime = Runtime.getRuntime();
		int nNucleos = runtime.availableProcessors();
		
		Thread [] hilos = new Thread[nNucleos];
		for(int i=0;i<hilos.length;i++)
		{
			Runnable runable = new Principal();
			hilos[i]= new Thread(runable);
			hilos[i].start();
		}
		
		for (int i=0;i<hilos.length;i++)
		{
			try {
				hilos[i].join();
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
		}
		System.out.println(cont);
	}

}
