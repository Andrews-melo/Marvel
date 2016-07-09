package br.com.marvel;

import br.com.prototipomarvel.activity.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class ExibePesquisa extends Activity{
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exibe_pesquisa);
	
		ActionBar ab =  getActionBar();
	    ab.setDisplayHomeAsUpEnabled(true);

	}
	
	@Override
    public boolean onMenuItemSelected(int panel, MenuItem item){
    	switch(item.getItemId()){
    	case android.R.id.home:
    		Intent it = new Intent(ExibePesquisa.this, Principal.class);
    		startActivity(it);    		
    		break;
    	}
    	return true;
    }

}
