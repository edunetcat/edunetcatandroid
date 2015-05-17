package com.ioc.edunet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MissatgeActivity extends Activity{
	TextView titol;
	TextView emisor;
	TextView contingut;
	String titolS;
	String emisorS;
	String contingutS;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_missatge);
		
		titol = (TextView)findViewById(R.id.titol);
		emisor = (TextView)findViewById(R.id.emisor);
		contingut = (TextView)findViewById(R.id.contingut);
		
		//Obtenim el Bundle amblainformació extra de l'Intent
	    Bundle extras = getIntent().getExtras();
	    if(extras!=null) {
	    	//extraiem les dades del bundle i creem l'usuari
	    	titolS = extras.getString("titol");
	    	emisorS = extras.getString("emisor");
	    	contingutS = extras.getString("contingut");
	    }
	    
	    titol.setText(titolS);
	    emisor.setText(emisorS);
	    contingut.setText(contingutS);
	}
}
