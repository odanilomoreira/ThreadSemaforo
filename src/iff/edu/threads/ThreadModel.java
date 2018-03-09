//PP-T3-Danilo-Roberta-Jessica

package iff.edu.threads;

import java.util.concurrent.Semaphore;

public class ThreadModel extends Thread {
	//definindo atributos
	private String nome;
	private int tempo;
	private Semaphore semaforo;

	//construtor
	public ThreadModel(String nome, int tempo, Semaphore semaforo) {
		this.nome = nome;
		this.tempo = tempo;
		this.semaforo = semaforo;
		//iniciando a thread no construtor
		start();
	}

	//procedimento de impressão da palavra vermelho N vezes
	public void imprimeVermelho(int N) {
		for(int i = 0; i<N; i++)
			System.out.println("vermelho");
	}

	//procedimento de impressão da palavra amarelo N vezes
	public void imprimeAmarelo(int N) {
		for(int i = 0; i<N; i++)
			System.out.println("amarelo");
	}

	//procedimento de impressão da palavra verde N vezes
	public void imprimeVerde(int N) {
		for(int i = 0; i<N; i++)
			System.out.println("verde");
	}

	//implementando método run sobrescrito da classe Thread
	public void run() {
		try {
			//definindo a quantidade n vezes que as threads serão impressas
			int N = 2;
			
			//entrando em região crítica, travando acesso para uma única thread
			semaforo.acquire();
			
			//descobrindo o ID da thread atual
			Thread thread = Thread.currentThread();
			long threadID = thread.getId();
			
			//chamando método sleep(dormir) passando a variável aleatória "tempo"
			ThreadModel.sleep(tempo);
			
			//garantindo que independente da thread que acessar esse recurso primeiro
			//as cores sejam impressas na ordem correta, através do ID da thread atual e semáforo
			if(threadID == 10) {
				System.out.println("\nSou a Thread "+nome+", vou dormir por "+tempo/1000+"s e imprimir 'vermelho' "+N+" vezes.");
				imprimeVermelho(N);
			}
			else
				if(threadID == 11) {
					System.out.println("\nSou a Thread "+nome+", vou dormir por "+tempo/1000+"s e imprimir 'amarelo' "+N+" vezes.");
					imprimeAmarelo(N);
				}
					else {
						System.out.println("\nSou a Thread "+nome+", vou dormir por "+tempo/1000+"s e imprimir 'verde' "+N+" vezes.");
						imprimeVerde(N);
					}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//saindo de região crítica, liberando recurso para utilização de uma nova thread
			semaforo.release();
		}
	}
}
