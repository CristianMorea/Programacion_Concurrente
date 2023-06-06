package Hilos_segun_capacidad_de_CPU;

import java.util.Random;

public class Principal extends Thread
{ private static int tam =8;
private int ini, fin;
//MATRIS 4*4
private static int[][] matriz =new int[tam][tam];

public Principal(int ini, int fin) {
	this.ini = ini;
	this.fin = fin;
}
//METODO PARA QUE FUNCIONEN LOS HILOS
public void run()
{
	for(int i = ini; i<fin;i++)
	{
		for(int j =0;j<matriz[0].length;j++)
		{
			matriz[i][j]*=10;
						
		}
	}
}
	public static void main(String[] args)
	{
		//INSTANCIA PARA OBTENER INFORMACION DEL HARWARE
		Runtime runtime=  Runtime.getRuntime();
		
		//ASIGNANDO LOS NUCLEOS DEL PC A LA VARIABLE NNUCLEOS
		int nNucleos = runtime.availableProcessors();
		
		
		Random rand = new Random(System.nanoTime());
		for (int i = 0;i<matriz.length;i++)
		{
			for (int j =0;j<matriz.length;j++)
			{
				//AQUI SE LLENA LA MATRIZ DE NUMEROS ALEATORIOS COMPRENDIDOS EN 10
				matriz[i][j] = rand.nextInt(10);
			}
		}
		
		Thread[] hilos= new Thread[nNucleos];
		
		int rango = tam/nNucleos; 
		int start =0;
		int end= rango;
		
		for (int i=0;i<nNucleos;i++)
		{
			if(i!= nNucleos-1)
			{
				hilos[i]= new Principal(start,end);
				hilos[i].start();
				start = end;
				end += rango;
			}
			else
			{
				hilos[i]= new Principal(start,tam);
				hilos[i].start();
			}
			
		}
		
		for (int i=0;i<nNucleos;i++)
		{
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<matriz.length;i++)
		{
			for(int j=0;j<matriz[0].length;j++)
			{
				System.out.print(matriz[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}

}
