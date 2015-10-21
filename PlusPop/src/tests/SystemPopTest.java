package tests;

import interaction.Post;

import java.text.ParseException;

import manager.SystemPop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import user.UsuarioFactory;
import exceptions.EntradaException;

public class SystemPopTest {

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
			
			systemPop.cadastraUsuario("Gretchen", "rainhadorebolado@gmail.com",
					"T@mmy123", "29/05/1959");
			systemPop.cadastraUsuario("Susana Vieira", "vieirasusu@ig.com.br",
					"EternaSenhoraDoDestino", "23/08/1942");
			
		} catch (ParseException | EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
	}

	@Test
	public void AtualizaCorretamentePerfilTest() {
		// REALIZA LOGIN DO USUARIO
		try {
			systemPop.login("rainhadorebolado@gmail.com", "T@mmy123");
		} catch (EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
		
		// VERIFICA INFORMACOES DO PERFIL DO USUARIO
		Assert.assertEquals("T@mmy123", systemPop.getUsuarioLogado().getSenha());
		Assert.assertEquals("Gretchen", systemPop.getUsuarioLogado().getNome());
		Assert.assertEquals("rainhadorebolado@gmail.com", systemPop.getUsuarioLogado().getEmail());
		Assert.assertEquals("1959-05-29", systemPop.getUsuarioLogado().getDataDeNascimento());
		Assert.assertEquals("resources/default.jpg", systemPop.getUsuarioLogado().getFoto());
		
		// ATUALIZA PERFIL DE FORMA CORRETA
		try {
			systemPop.atualizaPerfil("senha", "Gretchen951!", "T@mmy123");
		} catch (EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
	
		try {
			systemPop.atualizaPerfil("Nome", "Maria Odete");
		} catch (EntradaException | ParseException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
		
		try {
			systemPop.atualizaPerfil("e-mail", "mariaodete@hotmail.com");
		} catch (EntradaException | ParseException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
		
		try {
			systemPop.atualizaPerfil("data de nascimento", "29/06/1959");
		} catch (EntradaException | ParseException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
		
		try {
			systemPop.atualizaPerfil("foto", "imagens/eucomportada.png");
		} catch (EntradaException | ParseException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
		
		// VERIFICA SE INFORMACOES DO PERFIL DO USUARIO FORAM ATUALIZADAS
		Assert.assertEquals("mariaodete@hotmail.com", systemPop.getUsuarioLogado().getEmail());
		Assert.assertEquals("Gretchen951!", systemPop.getUsuarioLogado().getSenha());
		Assert.assertEquals("Maria Odete", systemPop.getUsuarioLogado().getNome());
		Assert.assertEquals("1959-06-29", systemPop.getUsuarioLogado().getDataDeNascimento());
		Assert.assertEquals("imagens/eucomportada.png", systemPop.getUsuarioLogado().getFoto());	
	}
	
	@Test
	public void AtualizaIncorretamentePerfilTest() {
		
		// REALIZA LOGIN DO USUARIO
		try {
			systemPop.login("rainhadorebolado@gmail.com", "T@mmy123");
		} catch (EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}
		
		// TENTA ATUALIZAR PERFIL DE FORMA INCORRETA
		try {
			systemPop.atualizaPerfil("Nome", "");
			//NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException | ParseException e) {
			Assert.assertEquals("Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.", e.getMessage());
		}
		
		try {
			systemPop.atualizaPerfil("senha", "    ", "T@mmy123");
			//NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException e) {
			Assert.assertEquals("Erro na atualizacao de perfil. Senha dx usuarix nao pode ser vazia.", e.getMessage());
		}
		
		try {
			systemPop.atualizaPerfil("senha", "Gretcheen", "Tammy321");
			//NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException e) {
			Assert.assertEquals("Erro na atualizacao de perfil. A senha fornecida esta incorreta.", e.getMessage());
		}
		
		try {
			systemPop.atualizaPerfil("e-mail", "mariaodete");
			//NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException | ParseException e) {
			Assert.assertEquals("Erro na atualizacao de perfil. Formato de e-mail esta invalido.", e.getMessage());
		}
		
		try {
			systemPop.atualizaPerfil("data de nascimento", "88/12/465");
			//NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException | ParseException e) {
			Assert.assertEquals("Erro na atualizacao de perfil. Formato de data esta invalida.", e.getMessage());
		}
		
		try {
			systemPop.atualizaPerfil("data de nascimento", "29 de maio de 1959");
			//NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException | ParseException e) {
			Assert.assertEquals("Erro na atualizacao de perfil. Formato de data esta invalida.", e.getMessage());
		}
		
		try {
			systemPop.atualizaPerfil("data de nascimento", "99/06/1959");
			//NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException | ParseException e) {
			Assert.assertEquals("Erro na atualizacao de perfil. Data nao existe.", e.getMessage());
		}
		
		try {
			systemPop.atualizaPerfil("data de nascimento", null);
			//NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException | ParseException e) {
			Assert.assertEquals("Erro na atualizacao de perfil. Data nao existe.", e.getMessage());
		}
		
		try {
			systemPop.atualizaPerfil("foto", null);
			//NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException | ParseException e) {
			Assert.assertEquals("Erro na atualizacao de perfil. Foto dx usuarix nao pode ser vazia.", e.getMessage());
		}
		
		try {
			systemPop.atualizaPerfil("e-mail", null);
			//NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (EntradaException | ParseException e) {
			Assert.assertEquals("Erro na atualizacao de perfil. Formato de e-mail esta invalido.", e.getMessage());
		}
		
	}
}