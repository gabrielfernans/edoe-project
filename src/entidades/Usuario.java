package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario implements Comparable<Usuario>{
	
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String status;
	private int contadorOrdem;
	private String id;
	private Map<Integer, Item> listaItens = new HashMap<>();
	
	public Usuario(String id, String nome, String email, String celular, String classe, String status, int cont) {
		if(nome == null ||nome.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		
		if(email == null || email.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		
		if(celular == null || celular.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		
		if(classe == null || classe.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		
		if(id == null || id.trim().equals("")) 
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = status;
		this.id = id;
		this.contadorOrdem = cont;
	}
	
	// adicionei o get de itens para poder ter acesso a lista no controller
	public Map<Integer, Item> getListaItens() {
		return listaItens;
	}

	public String atualizaUsuario(String nome, String email, String celular) {
		if(nome != null && nome.length()!=0 )
			this.nome = nome;
		else if(email != null && email.length() != 0)
			this.email = email;
		else if(celular != null && celular.length() != 0)
			this.celular = celular;
		return this.toString();
	}

	
	public String atualizaReceptor(String nome, String email, String celular) {
		if(!nome.equals(this.nome))
			this.nome = nome;
		if(!email.equals(this.email))
			this.email = email;
		if(!celular.equals(this.celular))
			this.celular = celular;
		return this.toString();
	}
	
	
	public int cadastraItem(int idItem, String descritor, int quantidade, String tags) {
		String[] vetorTags = tags.split(",");
		List<String> listaTags = new ArrayList<String>();
		
		//Adicionando as tags do vetor no ArrayList
		for (String c : vetorTags) {
			listaTags.add(c);
		}
		
		for (Item c : listaItens.values()) {
			if (c.getDescritor().equals(descritor) && c.getTags().equals(listaTags)) {
				c.setQuantidade(quantidade);
			}
		}
		this.listaItens.put(idItem, new Item(idItem, quantidade, descritor, listaTags));
		
		return idItem;
	}
	
	/**
	 * 
	 * @param idItem
	 * @return
	 */
	public String exibeItem(int idItem) {
		if (!this.listaItens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		return this.listaItens.get(idItem).toString();
	}
	
	/**
	 * 
	 * @param idItem
	 * @param novasTags
	 * @param novaQuantidade
	 */
	public String atualizaItem(int idItem, String novasTags, int novaQuantidade) {
		if (!this.listaItens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}

		return this.listaItens.get(idItem).atualizaItem(novasTags, novaQuantidade);
	}
	
	public void removeItem(int idItem) {
		if (this.listaItens.size() == 00 ) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (!this.listaItens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		this.listaItens.remove(idItem);
	}
	
	@Override
	public int compareTo(Usuario o) {
		return this.getContador() - o.getContador(); 
	}


	public String getNome() {
		return this.nome;
	}

	public String getEmail() {
		return this.email;
	}

	public String getCelular() {
		return this.celular;
	}

	public String getClasse() {
		return this.classe;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public int getContador() {
		return this.contadorOrdem;
	}
	
	private String formataId() {
		if(getClasse().equals("PESSOA_FISICA"))
			return id.substring(0, 3) + "." + id.substring(3, 6) + "." + id.substring(6, 9) + "-" + id.substring(9, 11);
		return id.substring(0, 2) + "." + id.substring(2, 5) + "." + id.substring(5, 8) + "/" + id.substring(8, 12) + "-" + id.substring(12, 14);	
	}

	public void adicionaItem(int id, int quantidade, String descricao, String data, List<String> tags) {
		this.listaItens.put(id, new Item(id, quantidade, descricao, tags));
	}

	public String getId() {
		return formataId();
	}
	
	public String toString() {
		return getNome() + "/" + this.getId() + ", " + getEmail() + ", " + getCelular() + ", status: " + getStatus(); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
