package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import manager.Facade;

import org.junit.Test;

import usuario.Usuario;

public class FacadeTest {

	@Test
	public void test() {
		Facade facade = new Facade();
		
		try {
			Usuario usuario = facade.criaUsuario("email", "senha", "nome", "data", null);
		
			assertEquals(true, facade.login("email", "senha"));
			assertTrue(facade.logout("email", "senha"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}