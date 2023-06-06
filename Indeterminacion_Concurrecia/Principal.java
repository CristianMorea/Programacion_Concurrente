package Indeterminacion_Concurrecia;
//INDETERMINISMO
/*
 * CUANDO DOS O MAS HILOS ESTAN ESCRIBIENDO A LA VEZ EN UNA  VARIABLE
 * COMPARTIDA, EL VALOR DE ESTA VARIABLE ES INDETERMINADO
 * 
 * SECCION CRITICA
 * ES AQUELLA PARTE DEL CODIGO DONDE NOSOSTROS SABEMOS QUE SUCEDERA 
 * INDETERMINISMO
 * 
 */


public class Principal extends Thread 
{
	private static int cont = 0;
	
 public void run()
 {
	 for(int i=0;i<1000;i++)
	 {
		 //AL FINALIZAR CON DEVE TENER UN VALOR DE 1000*1000=1.000.000
		 cont++;
	 }
 }
	public static void main(String[] args) 
	{
	Principal[]vec=new Principal[1000];
	
	for (int i=0; i<vec.length;i++)
	{
		vec[i]=new Principal();
		vec[i].start();
	}
	
	try 
	{
		for(int i=0;i<vec.length;i++)
		{
			vec[i].join();
		}
	} catch (Exception e) {
		System.out.println(e);
	}
System.out.println(cont);
	}

}
