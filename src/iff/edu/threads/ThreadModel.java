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

	//procedimento de impress�o da palavra vermelho N vezes
	public void imprimeVermelho(int N) {
		for(int i = 0; i<N; i++)
			System.out.println("vermelho");
	}

	//procedimento de impress�o da palavra amarelo N vezes
	public void imprimeAmarelo(int N) {
		for(int i = 0; i<N; i++)
			System.out.println("amarelo");
	}

	//procedimento de impress�o da palavra verde N vezes
	public void imprimeVerde(int N) {
		for(int i = 0; i<N; i++)
			System.out.println("verde");
	}

	//implementando m�todo run sobrescrito da classe Thread
	public void run() {
		try {
			//definindo a quantidade n vezes que as threads ser�o impressas
			int N = 2;
			
			//entrando em regi�o cr�tica, travando acesso para uma �nica thread
			semaforo.acquire();
			
			//descobrindo o ID da thread atual
			Thread thread = Thread.currentThread();
			long threadID = thread.getId();
			
			//chamando m�todo sleep(dormir) passando a vari�vel aleat�ria "tempo"
			ThreadModel.sleep(tempo);
			
			//garantindo que independente da thread que acessar esse recurso primeiro
			//as cores sejam impressas na ordem correta, atrav�s do ID da thread atual e sem�foro
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
			//saindo de regi�o cr�tica, liberando recurso para utiliza��o de uma nova thread
			semaforo.release();
		}
	}
}
