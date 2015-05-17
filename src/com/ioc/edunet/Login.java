package com.ioc.edunet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity implements OnClickListener {
	//assignació dels elements
	EditText userEditText;
	EditText passEditText;
	Button loginButton;
	String usuari;
	String contrassenya;
	boolean validat = false;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//creació dels elements
		loginButton = (Button)findViewById(R.id.loginButon);
	    loginButton.setOnClickListener(this);
	    
	    userEditText = (EditText)findViewById(R.id.usuari);
	    userEditText = (EditText)findViewById(R.id.contrasenya);
		
		
	}
	
	//métode que valida si l'usuari i contrasenya son correctes
	//Accedeix a l'API i retorna un token
	public void validar (String uIntro, String cIntro) {
		//1- accedeix a l'API
		//2- extrau el token
		//3- si el token és vàlid, canvia validat = true
		validat = true;
	}
	
	
	//Métode que rep el click del botó Login
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		//en el moment que fa click, validem
		validar(usuari, contrassenya);
		
		//si està validad, accedeix a la pantalla del menu
		//si no està validat, mostra un missatge d'advertèndia
		if (validat) {
			if (v == loginButton) {
				Intent i = new Intent("com.ioc.action.Menu");
				Bundle extras = new Bundle();
				extras.putString("usuari", usuari);
				i.putExtras(extras);
				startActivity(i);
			}
		} else {
			Toast toastInvalid = Toast.makeText(getApplicationContext(),"L'usuari o contrasenya introduits no són vàlids",Toast.LENGTH_SHORT);
        	toastInvalid.show();
		}
		
	}
	



}
