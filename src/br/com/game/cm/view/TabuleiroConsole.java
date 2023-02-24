package br.com.game.cm.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.game.cm.exceptions.ExplosionException;
import br.com.game.cm.exceptions.SairException;
import br.com.game.cm.models.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.setTabuleiro(tabuleiro);
		
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		executarJogo();
	}
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			while(continuar) {
				cicloDoJogo();
				System.out.println("Outra partida? (S/n) ");
				String resposta = entrada.nextLine();
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else { 
					tabuleiro.reiniciar();
				}
			}
		} catch(SairException e) {
			System.out.println("Tchau!!!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
			String digitado = capturarValorDigitado("Digite (linha, coluna): ");
			Iterator<Integer> linhacoluna = Arrays.stream(digitado.split(","))
					.map(e -> Integer.parseInt(e.trim())).iterator();
			
			digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar");
			if("1".equals(digitado)) {
				tabuleiro.abrir(linhacoluna.next(), linhacoluna.next());
			} else if("2".equals(digitado)) {
				tabuleiro.alternarMarcacao(linhacoluna.next(), linhacoluna.next());
			}
			}
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!");
			
		} catch(ExplosionException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}

	public Scanner getEntrada() {
		return entrada;
	}

	public void setEntrada(Scanner entrada) {
		this.entrada = entrada;
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
}
