package usuario;

public class UsuarioFactory {

	public Usuario criaUsuario(String nome, String email, String senha, String dataDeNascimento, String foto) {
		Usuario novoUsuario = new Usuario(nome, email, senha, dataDeNascimento, foto);
		return novoUsuario;
	}
	
	public Usuario criaUsuario(String nome, String email, String senha, String dataDeNascimento) {
		Usuario novoUsuario = new Usuario(nome, email, senha, dataDeNascimento);
		return novoUsuario;
	}
	
}