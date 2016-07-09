package br.com.marvel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import br.com.marvel.modelo.ModeloHeroi;
import br.com.prototipomarvel.activity.R;


public class Principal extends Activity {
	
	  private ProgressDialog dialog;
	  String url;
	  private  Long timestamp =  Calendar.getInstance().getTimeInMillis();
      private  String privateKey = "9dbe50784908e086e1d57bea506c79cb3206a1f1";
      private  String apikey ="21d6def8b9676443fcb68aa244ebdc36";
      ImageView imgHeroi;
      ArrayList<ModeloHeroi> listHeroi = new ArrayList<ModeloHeroi>();
      HeroiAdapter heroidp;
      ListView listaHeroi;
      private ArrayList<Bitmap> imgs = new ArrayList<Bitmap>();
      
      Handler handler = new Handler();
      private ModeloHeroi heroiSelecionado=null;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_principal);
        
     
        listaHeroi = (ListView) findViewById(R.id.lv_principal);
        
        //URL com as chaves e o hash
       String hash = Principal.geraHash(timestamp+privateKey+apikey);
       
       url= "http://gateway.marvel.com/v1/public/comics?ts=" + timestamp + "&apikey=" + apikey + "&hash=" + hash;
       Log.d("hash",""+url); 
     
       
     
       if(listHeroi == null || listHeroi.size() == 0){
			Log.i("Script", "Entrei 1");
			new CallJsonAsyncTask<ModeloHeroi>().execute();
		}
		
     

       listaHeroi.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				
			Intent form = new Intent (Principal.this, ExibeHeroi.class);
				heroiSelecionado = (ModeloHeroi) listaHeroi.getItemAtPosition(posicao);
				form.putExtra("HEROI_SELECIONADO", heroiSelecionado);
				startActivity(form);
			}
			
			
		});	
     
       
    }
	
    public static String geraHash(String chaves){
    		MessageDigest md = null;
    	try{
    		md = MessageDigest.getInstance("MD5");
    		
    	}catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
    	
    	 md.update(chaves.getBytes());
         
         //chama o método digest para criptografar a chave
         byte[] mdDigest = md.digest();
         
         StringBuilder hexString = new StringBuilder();
         
         for (byte aMessageDigest : mdDigest) {
         //converte um valor na base decimal em hexadecimal
             String h = Integer.toHexString(0xFF & aMessageDigest);
            
            if (h.length() < 2){
                 h = "0" + h;
                
             }
            
             hexString.append(h);
         }
                  
    	return hexString.toString();
    }
    
    class CallJsonAsyncTask<Params> extends AsyncTask<Void, Void,List<ModeloHeroi>> {

    	
    	

    	//Exibe pop-up indicando que está sendo feito o download do JSON
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		dialog = new ProgressDialog(Principal.this);
    		dialog.setMessage("Por favor aguarde...");
    		dialog.setCancelable(false);
    		dialog.show();
    	}
    	
    	@Override
    	protected List<ModeloHeroi> doInBackground(Void... params) {
    		
    		HttpConnection conn = new HttpConnection();
    		
    		try {
    			    			
    			String response = conn.callService(url);
    			if (response != null) {
    				
    				List<ModeloHeroi> pessoas = getPessoas(response);
    				return pessoas;
    			}
    		} catch (Exception e) {
    			Log.e("Erro", "Falha ao acessar Web service", e);
    		}
    		return null;
    	}
    	
    	
    	@Override
    	protected void onPostExecute(List<ModeloHeroi> result) {
    		super.onPostExecute(result);
    		dialog.dismiss();
    		if (result.size() > 0) {
    			Log.d("result",result.toString());
    			heroidp = new HeroiAdapter(imgs,result, Principal.this);
    			listaHeroi.setAdapter(heroidp);
    		}
    		else{
    			
    			AlertDialog.Builder builder = new AlertDialog.Builder(
    					Principal.this)
    					.setTitle("Erro")
    					.setMessage("Não foi possível acessar as informações!!")
    					.setPositiveButton("OK", null);
    			builder.create().show();
    		}
    	}
    	
    	private List<ModeloHeroi> getPessoas(String jsonString) {
    		List<ModeloHeroi> herois = new ArrayList<ModeloHeroi>();
    		
    		
    		try {
    			
    			JSONObject heroiOb = new JSONObject(jsonString);
    		
    			JSONObject data = heroiOb.getJSONObject("data");
    			JSONArray result = data.getJSONArray("results");
    			
				
				
    			for (int i = 1; i < 20; i++) {
    				JSONObject au = result.getJSONObject(i);
    				
    			
    				ModeloHeroi objetoHeroi = new ModeloHeroi();
        			objetoHeroi.setNome(au.getString("title"));
        			objetoHeroi.setId(au.getLong("id"));
        			objetoHeroi.setDescricao(au.getString("description"));
        			
        			
        			JSONObject img = au.getJSONObject("thumbnail");
        			String pathImg = img.getString("path")+"/portrait_xlarge.jpg";
        			  		
        			objetoHeroi.setUrl(pathImg);
        			
    				
    				herois.add(objetoHeroi);
    			}

    		} catch (JSONException e) {
    			Log.e("Erro", "Erro no parsing do JSON", e);
    		}
    		return herois;
    	}
    	
    	
      }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	
    	MenuItem menu1 = menu.add(0,0,0, "item 1");
    	menu1.setIcon(R.drawable.ic_search);
    	menu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    	return (true);
    }
    
    @Override
    public boolean onMenuItemSelected(int panel, MenuItem item){
    	switch(item.getItemId()){
    	case 0:
    		Intent it = new Intent(Principal.this, ExibePesquisa.class);
    		startActivity(it);    		
    		break;
    	}
    	return true;
    }
    
    @Override
    protected void onSaveInstanceState(Bundle bundle){
    	super.onSaveInstanceState(bundle);
    	bundle.putSerializable(ModeloHeroi.key, new ImagensList(imgs));
    	Log.d("Bundle", "entrou Aqui"+listaHeroi);
    }
    
    
		
				
	
}

