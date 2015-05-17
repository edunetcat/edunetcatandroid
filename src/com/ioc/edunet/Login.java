package com.ioc.edunet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity implements OnClickListener {
	//assignaci� dels elements
	EditText userEditText;
	EditText passEditText;
	Button loginButton;
	String usuari;
	String contrassenya;
	String nom;
	String cognom1;
	String cognom2;
	boolean validat = false;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//creaci� dels elements
		loginButton = (Button)findViewById(R.id.loginButon);
	    loginButton.setOnClickListener(this);
	    
	    userEditText = (EditText)findViewById(R.id.usuari);
	    passEditText = (EditText)findViewById(R.id.contrasenya);
		
	    userEditText.setText("prova");
	    passEditText.setText("prova");
		
	}
	
	//M�tode que rep el click del bot� Login
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		//per motius de seguretat, tornem a posar el semafor en fals
		validat = false;
		
		//agafem l'usuari i contrassenya introduits i les guardem a les variables Editable
		usuari = userEditText.getText().toString();
	    contrassenya = passEditText.getText().toString();
		
		//en el moment que fa click, validem
		//canviar pel m�tode validar() quan es pugui connectar amb l'API
		validarProva(usuari, contrassenya);
		
		
		//si est� validad, accedeix a la pantalla del menu
		//si no est� validat, mostra un missatge d'advert�ndia
		if (validat) {
			if (v == loginButton) {
				Intent i = new Intent("com.ioc.action.Menu");
				Bundle extras = new Bundle();
				extras.putString("usuari", usuari);
				i.putExtras(extras);
				startActivity(i);
				Toast toastInvalid = Toast.makeText(getApplicationContext(),"Benvingut, " + usuari + "!",Toast.LENGTH_SHORT);
	        	toastInvalid.show();
			}
		} else {
			Toast toastInvalid = Toast.makeText(getApplicationContext(),"L'usuari o contrasenya introduits no s�n v�lids",Toast.LENGTH_SHORT);
        	toastInvalid.show();
		}
		
	}
	
	//m�tode que valida si l'usuari i contrasenya son correctes
	//Accedeix a l'API i retorna un token //^PER IMPLEMENTAR
	public void validar (Editable uIntro, Editable cIntro) {
		//1- accedeix a l'API
		//2- extrau el token
		//3- si el token �s v�lid, canvia validat = true
	}
		
	//m�tode que valida l'usuari de prova sense connectar a l'API
	public void validarProva (String uIntro, String cIntro) {
		if ((uIntro.equals("prova")) && (cIntro.equals("prova"))){
			validat = true;
		} else {
			validat = false;
		}
	}

}
