package manager;

import java.text.ParseException;

import post.Post;
import user.Usuario;
import easyaccept.EasyAccept;
import exceptions.EntradaException;
import exceptions.LogicaException;
import exceptions.NotificacoesException;

public class Facade {

	private SystemPop systemPop;

	public Facade() {
		this.systemPop = new SystemPop();
	}

	public void iniciaSistema() {
		this.systemPop.iniciaSistema();
	}

	public void fechaSistema() throws LogicaException {
		this.systemPop.fechaSistema();
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataDeNascimento, String foto) throws EntradaException,
			ParseException {

		return this.systemPop.cadastraUsuario(nome, email, senha,
				dataDeNascimento, foto);
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataDeNascimento) throws EntradaException, ParseException {

		return this.systemPop.cadastraUsuario(nome, email, senha,
				dataDeNascimento);
	}

	public boolean login(String email, String senha) throws EntradaException {
		return this.systemPop.login(email, senha);
	}

	public boolean logout() throws EntradaException {
		return this.systemPop.logout();
	}

	public void atualizaPerfil(String atributo, String valor)
			throws EntradaException, ParseException {
		systemPop.atualizaPerfil(atributo, valor);
	}

	public void atualizaPerfil(String atributo, String valor, String velhaSenha)
			throws EntradaException {
		systemPop.atualizaPerfil(atributo, valor, velhaSenha);
	}
	
	public String atualizaRanking() throws LogicaException {
		return systemPop.atualizaRanking();
	}

	public String atualizaTrendingTopics() throws LogicaException {
		int quantidadeDeHashtagsDoTrending = 3;
		return systemPop.atualizaTrending(quantidadeDeHashtagsDoTrending);
	}
	
	public void criaPost(String mensagem, String data) throws EntradaException,
			LogicaException {
		systemPop.criaPost(mensagem, data);
	}

	public Usuario buscarUsuario(String email) throws LogicaException {
		return this.systemPop.buscarUsuario(email);
	}

	public boolean removeUsuario(String emailDoUsuario) throws LogicaException {
		return this.systemPop.removeUsuario(emailDoUsuario);
	}
	
	public String getInfoUsuario(String atributo) throws LogicaException {
		return this.systemPop.getInfoUsuario(atributo);
	}

	public String getInfoUsuario(String atributo, String email)
			throws LogicaException {
		return this.systemPop.getInfoUsuario(atributo, email);
	}

	public String getPost(int post) {
		return this.systemPop.getPost(post);
	}

	public String getPost(String atributo, int post) {
		return this.systemPop.getPost(atributo, post);
	}
	
	public String getConteudoPost(int indice, int post) throws LogicaException {
		return this.systemPop.getConteudoPost(indice, post);
	}
	
	public void curtirPost(String emailAmigo, int indice) throws LogicaException {
		this.systemPop.curtirPost(emailAmigo, indice);
	}
	
	public void rejeitarPost(String emailAmigo, int indice) throws LogicaException {
		this.systemPop.rejeitarPost(emailAmigo, indice);
	}
	
	public void adicionaAmigo(String email) throws LogicaException {
		this.systemPop.adicionaAmigo(email);
	}
	
	public void removeAmigo(String emailAmigoExcluido) throws LogicaException {
		this.systemPop.removeAmigo(emailAmigoExcluido);
	}
	
	public void aceitaAmizade (String emailDoUsuarioAceito) throws LogicaException {
		this.systemPop.aceitaAmizade(emailDoUsuarioAceito);
	}
	
	public void rejeitaAmizade(String emailDoUsuarioRejeitado) throws LogicaException {
		this.systemPop.rejeitaAmizade(emailDoUsuarioRejeitado);
	}

	public int getNotificacoes() throws NotificacoesException {
		return this.systemPop.getNotificacoes();
		
	}
	
	public String getNextNotificacao() throws LogicaException {
		return this.systemPop.getNextNotificacao();	
	}
	
	public int getQtdAmigos() {
		return this.systemPop.getQtdAmigos();
	}
	
	public void adicionaPops(int pops) throws LogicaException {
		this.systemPop.adicionaPops(pops);
	}
	
	public String getPopularidade() throws LogicaException {
		return this.systemPop.getPopularidade();
	}
	
	public int getPopsPost(int post) throws LogicaException {
		return this.systemPop.getPopsPost(post);
	}
	
	public int getPopsUsuario(String emailDoUsuario) throws LogicaException {
		return this.systemPop.getPopsUsuario(emailDoUsuario);
	}
	
	public int getPopsUsuario() throws LogicaException {
		return this.systemPop.getPopsUsuario();
	}
	
	public int qtdCurtidasDePost(int post) throws LogicaException {
		return this.systemPop.getQtdCurtidasDoPost(post);
	}
	
	public int qtdRejeicoesDePost(int post) throws LogicaException {
		return this.systemPop.getQtdRejeicoesDoPost(post);
	}
	
	public void ordenaFeed(String ordenacao) {
		this.systemPop.ordenaFeed(ordenacao);
	}
	
	public Post getPostFeedNoticiasRecentes(int indiceDoPost) {
		return this.systemPop.getPostFeedNoticiasRecentes(indiceDoPost);
	}
	
	public Post getPostFeedNoticiasMaisPopulares(int indiceDoPost) {
		return this.systemPop.getPostFeedNoticiasMaisPopulares(indiceDoPost);
	}
		
	public void atualizaFeed() {
		this.systemPop.atualizaFeed();
	}
	
	public void baixaPosts() throws LogicaException {
		this.systemPop.baixaPosts();
	}
	
	public static void main(String[] args) {
		args = new String[] { "manager.Facade",
				"testes/usecase_1.txt",
				"testes/usecase_2.txt",
				"testes/usecase_3.txt",
				"testes/usecase_4.txt",
				"testes/usecase_5.txt",
				"testes/usecase_6.txt",
				"testes/usecase_7.txt",
				"testes/usecase_8.txt",
				"testes/usecase_9.txt"};

		EasyAccept.main(args);
	}

}