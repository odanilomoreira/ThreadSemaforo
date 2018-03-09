//PP-T3-Danilo-Roberta-Jessica

package iff.edu.threads;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {

		//criando vetor de 3 posi��es com tempos aleat�rios
		int vet[] = new int[3];
		//instanciando uma vari�vel aleat�ria e 
		//preenchendo o vetor com valores entre 1 e 10
		Random tempo = new Random();
		for(int i=0; i<3; i++) 
			vet[i] = tempo.nextInt(10)+1;

		//multiplicando por mil para passar como milissegundos
		int x0 = vet[0]*1000;
		int x1 = vet[1]*1000;
		int x2 = vet[2]*1000;

		//instanciando sem�foro definindo que apenas 1 thread por vez poder� acessar o recurso
		Semaphore s = new Semaphore(1);

		//instanciando as 3 threads passando nome, milissegundos e semaforo
		ThreadModel thread0 = new ThreadModel("0", x0, s);
		ThreadModel thread1 = new ThreadModel("1", x1, s);
		ThreadModel thread2 = new ThreadModel("2", x2, s);

		//utilizando a fun��o join, para que uma thread s� 
		//seja executada ap�s a execu��o da interior finalizar com try e catch
		try {
			thread0.join();
			thread1.join();
			thread2.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
