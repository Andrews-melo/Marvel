package br.com.marvel;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.marvel.modelo.ModeloHeroi;
import br.com.prototipomarvel.activity.R;
import br.com.prototipomarvel.activity.R.drawable;

public class HeroiAdapter extends BaseAdapter{
	
	
	public HeroiAdapter(ArrayList<Bitmap> imgs, List<ModeloHeroi> list, Activity activity) {
		super();
		this.list = list;
		this.activity = activity;
		this.imgs = imgs;
	}
	
	private final Activity activity;
	private final List<ModeloHeroi> list;
	private final ArrayList<Bitmap> imgs;
	
	@Override
	public int getCount() {
		return list.size();
		
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return list.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View viewObj = activity.getLayoutInflater().inflate(R.layout.item_list, null);
		ModeloHeroi heroi = list.get(position);
		 
		//Bitmap imv = imgs.get(position);
		
		ImageView img = (ImageView) viewObj.findViewById(R.id.imagem_heroi);
		
		img.setImageResource(R.drawable.ic_launcher);
		
		TextView nome = (TextView) viewObj.findViewById(R.id.nome);
		nome.setText(heroi.getNome());
		
		return viewObj;
	}

}
