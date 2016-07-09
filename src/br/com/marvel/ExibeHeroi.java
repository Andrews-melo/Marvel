package br.com.marvel;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.marvel.modelo.ModeloHeroi;
import br.com.prototipomarvel.activity.R;

public class ExibeHeroi extends Activity{
	
	ModeloHeroi heroi;
	ImageView img;
	TextView nome;
	TextView descricao;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exibe_heroi);
		
		ActionBar ab =  getActionBar();
		ab.setIcon(R.drawable.ic_back);
	    ab.setDisplayHomeAsUpEnabled(true);
	        
		img = (ImageView) findViewById(R.id.imagem);
		nome = (TextView) findViewById(R.id.nome);
		descricao = (TextView) findViewById(R.id.descricao);
		
		
		heroi= (ModeloHeroi) getIntent().getSerializableExtra("HEROI_SELECIONADO");
		
		if(heroi!=null){
			img.setImageResource(R.drawable.ic_launcher);
			nome.setText(heroi.getNome());
			descricao.setText(heroi.getDescricao());
		}
	}
	 
	    
	    @Override
	    public boolean onMenuItemSelected(int panel, MenuItem item){
	    	switch(item.getItemId()){
	    	case android.R.id.home:
	    		Intent it = new Intent(ExibeHeroi.this, Principal.class);
	    		startActivity(it);    		
	    		break;
	    	}
	    	return true;
	    }
	  
}
