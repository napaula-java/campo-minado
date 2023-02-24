package br.com.game.cm;

import br.com.game.cm.models.Tabuleiro;
import br.com.game.cm.view.TabuleiroConsole;

public class Aplicacao {
	
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		
		new TabuleiroConsole(tabuleiro);
	}

}
