package br.com.marvel.modelo;

import java.io.Serializable;
import java.util.List;

import android.graphics.Bitmap;

public class ModeloHeroi implements Serializable {
	
	/**
	 * 
	 */
	public List<ModeloHeroi> listaHeroi;
	public static final String key = "1L";
	private long id;
	private String nome;
	private Bitmap imagem;
	private String descricao;
	private String url;
	
	
	public ModeloHeroi(){
		
	}
	public ModeloHeroi(List<ModeloHeroi> listaHeroi){
		this.listaHeroi = listaHeroi;
		
	}
	
	public ModeloHeroi(long id, String nome, Bitmap imagem, String descricao) {
		this.id = id;
		this.nome = nome;
		this.imagem = this.imagem;
		this.descricao = descricao;
		
	}
	

	public  String getUrl() {
		return url;
	}
	public  void setUrl(String url) {
		this.url = url;
	}



	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public Bitmap getImagem() {
		return imagem;
	}




	public void setImagem(Bitmap imagem) {
		this.imagem = imagem;
	}




	public String getDescricao() {
		return descricao;
	}




	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	@Override
	public String toString() {
		return "ModeloHeroi [id=" + id + ", nome=" + nome + ", imagem="
			 + ", Descricao=" + descricao
				+ "]";
	}
	
	

}
