package Condicion_guarda_wait_notifyall;

import java.util.Random;

public class Principal implements Runnable
{
	private int id;	
	private static Random cerrojo = new Random();
	private static int cont =0;
	public Principal(int id) 
	{
		this.id = id;
	}
public void run()
{
	synchronized (cerrojo) 
	{
		while(id != cont)
		{
			try {
				//WAIT ES PARRA ENVIAR A LA COLA DEL CERROJO DORMIDOS
				cerrojo.wait();
			} catch (InterruptedException e)
			{
			
				e.printStackTrace();
			}
		}
		System.out.println("SOY EL HILO: "+id);
			cont ++;
			//PARA DESPERTAR LOS HILOS
			cerrojo.notifyAll();
	}
}
	public static void main(String[] args) 
	{
		Runtime runtime = Runtime.getRuntime();
		int nNucleos = runtime.availableProcessors();
		
		Thread[] hilos= new Thread[nNucleos];
		
		for(int i=0;i<hilos.length;i++)
		{
			Runnable runnable = new Principal(i);
			hilos[i]=new Thread(runnable);
			hilos[i].start();		
		}
		
		for(int i=0;i<hilos.length;i++)
		{
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		}
		System.out.println("SOY EL HILO PRINCIPAL");

	}


}
