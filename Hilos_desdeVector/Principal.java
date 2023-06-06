package Hilos_desdeVector;

public class Principal extends Thread
{   //UTILIZAMOS UN VECTOR PARA LANZAR HILOS
	private int id;
	
	public Principal(int id)
    {
		this.id = id;
	}

	
	public void run()
	{
		System.out.println("SOY EL HILO:"+id);
	}
 
	public static void main(String[] args)
	{
		
		Principal[] vec = new Principal[5];
		//INICIALIZAMOS EL VECTOR
		for ( int i=0;i<vec.length;i++)
		{
			//TENEMOS INSTANCIADOS TODOS LOS ELEMENTOS DEL VECTOR
			vec[i] = new Principal(i+1);
			//LANZANDO LOS HILOS DEL VECTOR ATRAVEZ DE UN BUCLE FOR
			vec[i].start();
		}
		
		try //5+1=6 HILOS
		{
			//HACE QUE EL HILO PRINCIPAL SIMEPRE ESTE DETRAS DEL HILO 1
			//AUNQUE SE ASEGURA QUE ESTE DETRAS NO SE SAVE QUE TAN DETRAS
			vec[1].join();
			vec[4].join();
			//PARA QUE EL HILO PRINCIPAL SE EJECUTE DESPUES DE TODOS LOS HILOS
			for(int i=0;i<vec.length;i++)
			{
				vec[i].join();
			}//0+1=1
		} 
		catch (Exception e) 
		{
		System.out.println(e);
		}
		
	   
		System.out.println("SOY EL HILO PRINCIPAL");
		
	}

	
}
