package tests;

import java.text.ParseException;

import manager.SystemPop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.EntradaException;
import exceptions.LogicaException;

public class RankingTest {
	private SystemPop systemPop;

	@Before
	public void setUp() {
		try {
			systemPop = new SystemPop();

			systemPop.cadastraUsuario("Bianca", "biancasllima@gmail.com",
					"Bi123", "16/10/1994");
			systemPop.cadastraUsuario("David", "sdavidmedeiros@gmail.com",
					"david951", "16/12/1995");
			systemPop.cadastraUsuario("Jonas", "jonasgomes@gmail.com", "jonas123", "20/10/1993");
			systemPop.cadastraUsuario("Ana", "anabarros@gmail.com", "aninha123", "01/04/1993");
			systemPop.cadastraUsuario("Gabi", "gabimotta@gmail.com", "gabi123", "18/01/1996");
			
		} catch (ParseException | EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
	}

	@Test
	public void polimorfismoDeUsuarioTest() {

		try {
			// REALIZA LOGIN DO USUARIO
			systemPop.login("biancasllima@gmail.com", "Bi123");

			// VERIFICA A POPULARIDADE DO USUARIO
			Assert.assertEquals("Normal", systemPop.getUsuarioLogado()
					.getPopularidade());

			// ALTERA A POPULARIDADE DO USUARIO
			systemPop.getUsuarioLogado().setPopsMagico(501);
			// VERIFICA SE A POPULARIDADE CONTINUA DO MESMO TIPO
			Assert.assertEquals("CelebridadePOP", systemPop.getUsuarioLogado()
					.getPopularidade());
			// VERIFICA NUMERO DE POPS DO USUARIO LOGADO
			Assert.assertEquals(501, systemPop.getUsuarioLogado().getPops());
			// VERIFICA SE A POPULARIDADE FOI ATUALIZADA
			Assert.assertEquals("CelebridadePOP", systemPop.getUsuarioLogado()
					.getPopularidade());
			// ALTERA A POPULARIDADE DO USUARIO
			systemPop.getUsuarioLogado().setPopsMagico(1001);
			// VERIFICA NUMERO DE POPS DO USUARIO LOGADO
			Assert.assertEquals(1001, systemPop.getUsuarioLogado().getPops());
			// VERIFICA SE A POPULARIDADE FOI ATUALIZADA
			Assert.assertEquals("IconePOP", systemPop.getUsuarioLogado()
					.getPopularidade());

			// ALTERA A POPULARIDADE DO USUARIO
			systemPop.getUsuarioLogado().setPopsMagico(600);
			// VERIFICA NUMERO DE POPS DO USUARIO LOGADO
			Assert.assertEquals(600, systemPop.getUsuarioLogado().getPops());
			// VERIFICA SE A POPULARIDADE FOI ATUALIZADA
			Assert.assertEquals("CelebridadePOP", systemPop.getUsuarioLogado()
					.getPopularidade());

			systemPop.logout();

		} catch (EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
	}

	@Test
	public void RankingSistemaTest() {
		try {
			
			// ---------------- BIANCA ------------------ //
			// REALIZA LOGIN DO USUARIO
			systemPop.login("biancasllima@gmail.com", "Bi123");
			
			// ALTERA POPULARIDADE
			systemPop.getUsuarioLogado().setPopsMagico(555);
			Assert.assertEquals("CelebridadePOP", systemPop.getUsuarioLogado().getPopularidade());
			
			// CRIA POSTS CORRETAMENTE
			systemPop.criaPost("Sonoooo #BomDia #QueroFerias #Trabalhando #LP2", "03/11/2015 21:22:12");
			
			// ADICIONA AMIGO
			systemPop.adicionaAmigo("sdavidmedeiros@gmail.com");
			
			// REALIZA LOGOUT DO USUARIO
			systemPop.logout();

			// ---------------- DAVID ------------------ //
			// REALIZA LOGIN DO USUARIO
			systemPop.login("sdavidmedeiros@gmail.com", "david951");
			
			// ACEITA AMIGO
			systemPop.aceitaAmizade("biancasllima@gmail.com");
			
			// ADICIONA AMIGO
			systemPop.adicionaAmigo("anabarros@gmail.com");
			
			// CRIA POST 
			systemPop.criaPost( 
					"Entrega do projeto <imagem>videos/fotonaaula.jpg</imagem> #BomDia #LP2 #Trabalhando",
					"03/11/2012 14:12:25");
			
			// REALIZA LOGOUT DO USUARIO
			systemPop.logout();
			
			// ---------------- ANA ------------------ //
			// REALIZA LOGIN DO USUARIO
			systemPop.login("anabarros@gmail.com", "aninha123");
			
			// ALTERA POPULARIDADE
			systemPop.getUsuarioLogado().setPopsMagico(1111);
			Assert.assertEquals("IconePOP", systemPop.getUsuarioLogado().getPopularidade());
			
			// CRIA POSTS CORRETAMENTE
			systemPop.criaPost("Estudando! Prova amanha! #LP2", "03/11/2015 13:15:05");
			
			// ACEITA AMIGO
			systemPop.aceitaAmizade("sdavidmedeiros@gmail.com");
			
			// ADICIONA AMIGO
			systemPop.adicionaAmigo("gabimotta@gmail.com");
			
			// REJEITA POST COM EPICFAIL, -50 de popularidade para david
			systemPop.rejeitarPost("sdavidmedeiros@gmail.com", 0);
			
			// REALIZA LOGOUT DO USUARIO
			systemPop.logout();
						
			// ---------------- JONAS ------------------ //
			// REALIZA LOGIN DO USUARIO
			systemPop.login("jonasgomes@gmail.com", "jonas123");

			// ADICIONA AMIGO
			systemPop.adicionaAmigo("gabimotta@gmail.com");
			
			// REALIZA LOGOUT DO USUARIO
			systemPop.logout();
			
			// ---------------- GABI ------------------ //
			// REALIZA LOGIN DO USUARIO
			systemPop.login("gabimotta@gmail.com", "gabi123");
			
			// ALTERA POPULARIDADE
			systemPop.getUsuarioLogado().setPopsMagico(1111);
			Assert.assertEquals("IconePOP", systemPop.getUsuarioLogado().getPopularidade());
			
			// ACEITA AMIGO
			systemPop.aceitaAmizade("anabarros@gmail.com");
			systemPop.aceitaAmizade("jonasgomes@gmail.com");

			// CURTE POST COM EPICWIN, +50 de popularidade para ana
			systemPop.rejeitarPost("anabarros@gmail.com", 0);
			
			// REALIZA LOGOUT DO USUARIO
			systemPop.logout();
						
			
		} catch (EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		} catch (LogicaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			System.out.println(e.getMessage());
			Assert.fail();
		}

	}
}