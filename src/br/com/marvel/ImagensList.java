package br.com.marvel;

import java.io.Serializable;
import java.util.ArrayList;

import android.graphics.Bitmap;

public class ImagensList implements Serializable {
	public ArrayList<Bitmap> imgs;
	public static final String KEY = "imagens";
	
	public ImagensList(ArrayList<Bitmap> imgs){
		this.imgs = imgs;
	}
	
	

}
