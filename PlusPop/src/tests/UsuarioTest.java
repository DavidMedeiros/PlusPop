package tests;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import user.Usuario;
import user.UsuarioFactory;
import exceptions.EntradaException;

public class UsuarioTest {

	private UsuarioFactory factory;
	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	private Usuario usuario4;
	
	@Before
	public void setUp() {
		factory = new UsuarioFactory();
		
		// CRIA USUARIOS COM PARAMETROS CORRETOS
		try {
			// SEM FOTO DO PERFIL
			usuario1 = factory.criaUsuario("Gretchen", "rainhadorebolado@gmail.com", "T@mmy123", "29/05/1959");
			usuario2 = factory.criaUsuario("Susana Vieira", "vieirasusu@ig.com.br", "EternaSenhoraDoDestino", "23/08/1942");
			
			// COM FOTO DO PERFIL
			usuario3 = factory.criaUsuario("Xuxa Meneghel", "xuxa@hotmail.com", "bilaBilu987", "27/03/1963", "resources/euzinha.jpg");
			usuario4 = factory.criaUsuario("Mara Maravilha", "maravilha@uol.com.br", "CurumimIEIE", "06/03/1968", "resources/mdemaravilha.jpg");
		} catch (ParseException | EntradaException e) {
			// NAO DEVE CAPTURAR EXCESSAO
			Assert.fail();
		}		
	}
	
	@Test
	public void CadastroIncorretoDeUsuariosTest() {
		try {
			usuario1 = factory.criaUsuario("", "rainhadorebolado@gmail.com", "T@mmy123", "29/05/1959");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (ParseException | EntradaException e) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.", e.getMessage());
		}
				
		try {
			usuario1 = factory.criaUsuario("Gretchen", "", "T@mmy123", "29/05/1959");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (ParseException | EntradaException e) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.", e.getMessage());
		}
		
		try {
			usuario1 = factory.criaUsuario("Gretchen", "FreakLeBoomBoom", "T@mmy123", "29/05/1959");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (ParseException | EntradaException e) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.", e.getMessage());
		}
		
		try {
			usuario1 = factory.criaUsuario("Gretchen", "FreakLeBoomBoom@CongaConga", "T@mmy123", "29/05/1959");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (ParseException | EntradaException e) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.", e.getMessage());
		}
		
		try {
			usuario1 = factory.criaUsuario("Gretchen", "rainhadorebolado@gmail.com", "", "29/05/1959");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (ParseException | EntradaException e) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Senha dx usuarix nao pode ser vazia.", e.getMessage());
		}
		
		try {
			usuario1 = factory.criaUsuario("Gretchen", "rainhadorebolado@gmail.com", "T@mmy123", "29 de maio de 1959");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (ParseException | EntradaException e) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Formato de data esta invalida.", e.getMessage());
		}
		
		try {
			usuario1 = factory.criaUsuario("Gretchen", "rainhadorebolado@gmail.com", "T@mmy123", "29/13/1999");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (ParseException | EntradaException e) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Data nao existe.", e.getMessage());
		}
		
		try {
			usuario1 = factory.criaUsuario("Gretchen", "rainhadorebolado@gmail.com", "T@mmy123", "-1/11/1999");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (ParseException | EntradaException e) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Data nao existe.", e.getMessage());
		}
		
		try {
			usuario1 = factory.criaUsuario("Gretchen", "rainhadorebolado@gmail.com", "T@mmy123", "10/11/-1");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (ParseException | EntradaException e) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Data nao existe.", e.getMessage());
		}
		
		try {
			usuario1 = factory.criaUsuario("Gretchen", "rainhadorebolado@gmail.com", "T@mmy123", "10/11/1999", "   ");
			// NAO DEVE CHEGAR AQUI
			Assert.fail();
		} catch (ParseException | EntradaException e) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Foto dx usuarix nao pode ser vazia.", e.getMessage());
		}
	}

	@Test
	public void UsuariosCriadosTest() {		
		Assert.assertEquals("Susana Vieira", usuario2.getNome());
		Assert.assertEquals("vieirasusu@ig.com.br", usuario2.getEmail());
		Assert.assertEquals("EternaSenhoraDoDestino", usuario2.getSenha());
		Assert.assertEquals("1942-08-23", usuario2.getDataDeNascimento());
		Assert.assertEquals("resources/default.jpg", usuario2.getFoto());	
	}
	
	
}