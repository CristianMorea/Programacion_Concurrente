package InterbloqueosYestrategiaBufer;

import java.util.Random;
//NUCA TENER UN CERROJO DENTRO DE OTRO YA QUE CREARA INTERBLOQUEO
 
public class Principal  implements Runnable
{
	private int id;
	
	//UN CERROJO DEVE SER STATIC Y PUEDE SER UN OBJETO DE CUALQQUIER CLASE
	private static Random cerrojoA = new Random();
	private static Thread cerrojoB = new Thread();
	
	private int con_private =0;
	private static int cont=0;
	public Principal(int id)
	{
		this.id = id;
	}
	@Override
	public void run() 
	{
		
		//LA CARGA DE TRABAJO ES AQUI
		//TODOS LOS HILOS
	    for(int i = 0 ;i<20000;i++)
	    {
	    	con_private++;
	    }
		
	    //AQUI SOLO ASIGNAMOS
		synchronized (cerrojoA) 
		{
			cont += con_private;
		}
		
		
		
		
	/*//CREANDO DOS GRUPOS SEPARADOS PARA TODOS LOS HILOS
		if(id %2 ==0)
		{
			synchronized (cerrojoA) // H2 ACTIVO: COLA
			{
				mostrarA();
			}
		}
		else
		{
			synchronized (cerrojoB) // H2 ACTIVO:COLA
			{
				mostrarB();
			}
		}
		
	 *
	 */
		
		
		
	}
    
	/*
	 * private void mostrarA()
	{
		synchronized (cerrojoB) 
		{
			System.out.println("SOY EL HILO "+id);//H2
		}
	}
	
	private void mostrarB()
	{
		synchronized (cerrojoA)
		{
			System.out.println("SOY EL HILO "+id);
		}
	}
	 */
	public static void main(String[] args)
{
	Runtime runtime = Runtime.getRuntime();
	//LOS NUCLEOS DEL PC GRACIAS AVAILABLE
	//runtime.availableProcessors()
	int nNucleos =8;
	
	//VECTOR DE HILOS
	Thread[]hilos = new Thread[nNucleos];
	
	for(int i=0;i<hilos.length;i++)
	{
		Runnable runnable = new Principal(i);
		hilos[i] =new Thread(runnable);
		hilos[i].start();
	}
	
	
	for(int i=0;i<hilos.length;i++)
	{
		try {
			hilos[i].join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	System.out.println("SOY EL HILO "+cont);
	
	
}

	

	

}
