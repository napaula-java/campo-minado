package br.com.game.cm.models;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.game.cm.exceptions.ExplosionException;

class CampoTest {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3,3);
	}
	
	@Test
	void testeVizinhoReal() {
	Campo vizinhoEsquerda = new Campo(3,2);
	boolean result = campo.adicionarVizinho(vizinhoEsquerda);
	assertTrue(result);
	
	Campo vizinhoDireita = new Campo(3,4);
	boolean result2 = campo.adicionarVizinho(vizinhoDireita);
	assertTrue(result2);
	
	Campo vizinhoEmCima = new Campo(2, 3);
	boolean result3 = campo.adicionarVizinho(vizinhoEmCima);
	assertTrue(result3);

	Campo vizinhoEmbaixo = new Campo(4,3);
	boolean result4 = campo.adicionarVizinho(vizinhoEmbaixo);
			assertTrue(result4);
			
			Campo naoVizinho = new Campo(1,1);
			boolean result5 = campo.adicionarVizinho(naoVizinho);
			assertFalse(result5);
	}
	
	@Test
	void testeValorAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDeNovo() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbriSemMinaNaoMarcado() {
		assertTrue(campo.abrir());
		
	}
	
	@Test
	void testeAbrirSemMinaMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test 
	void testeAbrirComMinaMarcado(){
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirComMinaNaoMarcado(){
		campo.minar();
		assertThrows(ExplosionException.class, ()  -> {
			campo.abrir();
		});
	}
	
	@Test
	void testeAbrirComVizinhos() {
		Campo vizinhoDoVizinho1 = new Campo(1, 1);
		Campo vizinho1 = new Campo(2, 2);
		vizinho1.adicionarVizinho(vizinhoDoVizinho1);
		campo.adicionarVizinho(vizinho1);
		campo.abrir();
		assertTrue(vizinho1.abrir() && vizinhoDoVizinho1.abrir());
	}
	
//	@Test
//	void testeAbrirComVizinhos2() {
//		Campo campo11 = new Campo(1, 1);
//		Campo campo12 = new
//	}
}