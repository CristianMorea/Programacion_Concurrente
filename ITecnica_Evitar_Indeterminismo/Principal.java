package ITecnica_Evitar_Indeterminismo;

import java.util.Random;

//MULTIPLICAR POR 10 TODOS LOS ELEMENTOS DE UNA MATRIZ DE FORMA CONCURRENTE Y MEDIR EL TIEMPO
//ESTA TECNICA SE USA EN MATRICES
public class Principal extends Thread {
    private static int tam =20;
    private int ini, fin;
    //MATRIS 4*4
    private static int[][] matriz =new int[tam][tam];
	
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
		
		double tiempo_inicio, tiempo_final;
	// GENERANDO ELEMENTOS RAMDOM ATRAVEZ DE LA SEMILLA A LA INSTANCIA RAMDOM
	Random rand = new Random(System.nanoTime());
	for (int i = 0;i<matriz.length;i++)
	{
		for (int j =0;j<matriz.length;j++)
		{
			//AQUI SE LLENA LA MATRIZ DE NUMEROS ALEATORIOS COMPRENDIDOS EN 10
			matriz[i][j] = rand.nextInt(10);
		}
	}
	
	tiempo_inicio  =System.nanoTime();//HORA EN NANO SEGUNDOS
	
	Principal h1 = new Principal(0,5);//DANDO EL RANGO DE FUNCIONAMIENTO PARA CADA HILO
	Principal h2 = new Principal(5,10);
	Principal h3 = new Principal(10,15);
	Principal h4 = new Principal(15,20);
	
	//INICIALIZANDO HILOS
	h1.start();
	h2.start();
	h3.start();
	h4.start();
	
	try {
		h1.join();
		h2.join();
		h3.join();
		h4.join();
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
	
	tiempo_final= System.nanoTime()-tiempo_inicio; //12-9 = 3
	
	//EL TIEMPO EN NANO SEGUNDOS ES DEMACIADO GRANDE 
	//POR ESO LO CONC¿VERTIMOS A MILISEGUNDOS
	System.out.println((tiempo_final/1000000)+" MILISEGUNDOS");
	
	
	//IMPRIMIR MATRIZ
	for(int i=0;i<matriz.length;i++)
	{
		for(int j=0;j<matriz[0].length;j++)
		{
			System.out.print(matriz[i][j]+" ");
		}
		System.out.println();
	}
	
	}
	public Principal(int ini, int fin) {
		this.ini = ini;
		this.fin = fin;
	}

}
