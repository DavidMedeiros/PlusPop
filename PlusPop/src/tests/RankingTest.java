package tests;

import static org.junit.Assert.*;
import interaction.Post;

import java.text.ParseException;

import manager.SystemPop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import user.UsuarioFactory;
import exceptions.EntradaException;
import exceptions.LogicaException;

public class RankingTest {
	private UsuarioFactory factory;

	private SystemPop systemPop;

	private Post post1;
	private Post post2;
	private Post post3;

	@Before
	public void setUp() {
		factory = new UsuarioFactory();

		try {
			systemPop = new SystemPop();

			systemPop.cadastraUsuario("Bianca", "biancasllima@gmail.com",
					"Bi123", "16/10/1994");
			systemPop.cadastraUsuario("Jonas", "jonasaguiar@gmail.com",
					"jonas1234", "23/08/1942");

		} catch (ParseException | EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
	}

	@Test
	public void Test() throws EntradaException, LogicaException {
		// REALIZA LOGIN DO USUARIO
		try {
			post1 = new Post(
					"Descansando no feriado <audio>musicas/happy.mp3</audio>",
					"31/10/2015 21:12:30");
			post2 = new Post(
					"Entrega do projeto <imagem>videos/fotonaaula.jpg</imagem> #LP2 #Trabalhando",
					"03/11/2012 14:12:25");
			post3 = new Post("Sonooo #queroFerias", "03/11/2015 20:12:25");
			systemPop.login("biancasllima@gmail.com", "Bi123");
			
		} catch (EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
	
	systemPop.criaPost(post1.getMensagem(), post1.getData());
	systemPop.logout();
	systemPop.login("jonasaguiar@gmail.com", "jonas1234");
	systemPop.curtirPost("biancasllima@gmail.com", 1);
	
	systemPop.atualizaRanking();
	
	
	}
}