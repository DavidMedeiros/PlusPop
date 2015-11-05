package tests;

import static org.junit.Assert.fail;
import interaction.Post;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.EntradaException;

public class PostTest {

	private Post post1;
	private Post post2;
	private Post post3;

	@Before
	public void setUp() {
		try {
			// CRIA POSTS COM PARAMETROS CORRETOS
			post1 = new Post(
					"Ouvindo Freak le bom bom! <audio>musicas/freaklebombom.mp3</audio>",
					"01/10/2015 21:12:30");
			post2 = new Post(
					"Nao tenho paciencia pra quem ta comecando! <video>videos/videoshowentrevista.mp3</video> #Globo #VideoShow",
					"25/08/2012 14:12:25");
			post3 = new Post("Boa noiteeee plus popers!", "25/08/2015 20:12:25");
		} catch (EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
	}

	@Test
	public void CriaPostIncorretoTest() {
		// CRIA POSTS COM PARAMETROS INCORRETOS
		try {
			post1 = new Post("    ", "01/10/2015 21:12:30");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException e) {
			Assert.assertEquals(
					"Nao eh possivel criar o post. A mensagem nao pode ser vazia",
					e.getMessage());
		}

		try {
			post1 = new Post(
					"Genteeee!!! Nao percam hoje o meu programa na Record! Vai ter muita coisa boa e muitos quadros de tirar o folego! Ah, tambem teremos a participacao de voces ai de casa. Vai ser XUXESSO! Espero voces as 22h.",
					"19/10/2015 19:00:45");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException e) {
			Assert.assertEquals(
					"Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.",
					e.getMessage());
		}

		try {
			post1 = new Post(
					"Cansaderrima do programa de hoje, boa noite! Cansada VeryTired",
					"  ");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException e) {
			Assert.assertEquals(
					"Nao eh possivel criar o post. Data nao existe.",
					e.getMessage());
		}

		try {
			post1 = new Post(
					"Cansaderrima do programa de hoje, boa noite! Cansada VeryTired",
					"99/10/1945");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException e) {
			Assert.assertEquals(
					"Nao eh possivel criar o post. Data nao existe.",
					e.getMessage());
		}
	}

	@Test
	public void PostsCriadosTest() {
		Assert.assertEquals(0, post1.getCurtidas());
		Assert.assertEquals(0, post1.getPopularidade());
		Assert.assertEquals(0, post1.getRejeicoes());
		Assert.assertEquals("2015-10-01 21:12:30", post1.getDataFormatada());
		Assert.assertEquals("", post1.getHashtags());
		Assert.assertEquals(
				"Ouvindo Freak le bom bom! <audio>musicas/freaklebombom.mp3</audio>",
				post1.getMensagem());
		Assert.assertEquals("Ouvindo Freak le bom bom!", post1.getTexto());
		Assert.assertEquals("<audio>musicas/freaklebombom.mp3</audio>",
				post1.getMidias());
		Assert.assertEquals(
				"Ouvindo Freak le bom bom! <audio>musicas/freaklebombom.mp3</audio> (2015-10-01 21:12:30)",
				post1.getPostFormatado());
		Assert.assertEquals("#Globo,#VideoShow", post2.getHashtags());
		Assert.assertEquals("", post3.getHashtags());
		Assert.assertEquals(null, post3.getMidias());

		post3.curtir();
		post3.rejeitar();

		Assert.assertEquals(1, post3.getCurtidas());
		Assert.assertEquals(1, post3.getRejeicoes());
	}

}