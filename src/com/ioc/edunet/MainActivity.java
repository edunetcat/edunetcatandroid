package com.ioc.edunet;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks, OnClickListener {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	private static TextView benvinguda;
	private static TextView missatge1;
	private static TextView missatge2;
	private static TextView missatge3;
	private static TextView missatge4;
	private static TextView missatge5;
	private static final Alumne alumne = new Alumne();
	private static String usuari;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Crea el menu NavigationDrawer
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		
		//titol de l'activitat, que anirà canviant segons la secció triada
		mTitle = getTitle();

		// Instala el menu NavigationDrawer
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		//Obtenim el Bundle amblainformació extra de l'Intent
	    Bundle extras = getIntent().getExtras();
	    if(extras!=null) {
	    	//extraiem les dades del bundle i creem l'usuari
	    	usuari = extras.getString("usuari");	
	    }
	    //Extraiem les dades de l'alumne. Canviem el métode per a extraure-les quan funcioni l'API
    	extraureAlumneProva(usuari);
    	//Alumne alumne extraureAlumne();
		    
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// Actualitza el contingut segons la opció triada
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	//métode que escolta la opció triada del menú lateral
	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		//Aquest métode és el que mostra, segons l'opció triada, la vista XML del contingut
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			//s'extrau l'opció (recordem que la opció primera és 1 i no 0) dels arguments del fragment
			int opcio = getArguments().getInt(ARG_SECTION_NUMBER);
			View rootView;
			switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 1:
                rootView = inflater.inflate(R.layout.fragment_main, container, false);
                //substitueix el titol de l'inici, mostrant el nom de l'usuari
        	    benvinguda = (TextView)rootView.findViewById(R.id.benvinguda);
        		benvinguda.setText("Hola " + alumne.getNom() + " " + alumne.getCognom1() + " " + alumne.getCognom2() + " (" + alumne.getUsuari() + ")" + "!");
        		emplenarMissatges(rootView);
        		if (alumne.numMissatgesNous() == 1) {
        			missatge1.setOnClickListener(new View.OnClickListener() {

      				  @Override
      				  public void onClick(View v) {
      					  Intent i = new Intent("com.ioc.action.Missatge");
      						Bundle extras = new Bundle();
      						extras.putString("titol", alumne.obtenirMissatgeNou(0).getTitol());
      						extras.putString("emisor", alumne.obtenirMissatgeNou(0).getEmisor());
      						extras.putString("contingut", alumne.obtenirMissatgeNou(0).getContingut());
      						i.putExtras(extras);
      						startActivity(i);
      				  }

      				});
        		} else if (alumne.numMissatgesNous() == 2){
        			missatge1.setOnClickListener(new View.OnClickListener() {

      				  @Override
      				  public void onClick(View v) {
      					  Intent i = new Intent("com.ioc.action.Missatge");
      						Bundle extras = new Bundle();
      						extras.putString("titol", alumne.obtenirMissatgeNou(0).getTitol());
      						extras.putString("emisor", alumne.obtenirMissatgeNou(0).getEmisor());
      						extras.putString("contingut", alumne.obtenirMissatgeNou(0).getContingut());
      						i.putExtras(extras);
      						startActivity(i);
      				  }

      				});
        			missatge2.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(1).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(1).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(1).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        		} else if (alumne.numMissatgesNous() == 3){
        			missatge1.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(0).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(0).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(0).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge2.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(1).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(1).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(1).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge3.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(2).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(2).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(2).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        		} else if (alumne.numMissatgesNous() == 4){
        			missatge1.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(0).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(0).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(0).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge2.setOnClickListener(new View.OnClickListener() {

      				  @Override
      				  public void onClick(View v) {
      					  Intent i = new Intent("com.ioc.action.Missatge");
      						Bundle extras = new Bundle();
      						extras.putString("titol", alumne.obtenirMissatgeNou(1).getTitol());
      						extras.putString("emisor", alumne.obtenirMissatgeNou(1).getEmisor());
      						extras.putString("contingut", alumne.obtenirMissatgeNou(1).getContingut());
      						i.putExtras(extras);
      						startActivity(i);
      				  }

      				});
        			missatge3.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(2).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(2).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(2).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge4.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(3).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(3).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(3).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        		} else if (alumne.numMissatgesNous() >= 5){
        			missatge1.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(0).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(0).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(0).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge2.setOnClickListener(new View.OnClickListener() {

      				  @Override
      				  public void onClick(View v) {
      					  Intent i = new Intent("com.ioc.action.Missatge");
      						Bundle extras = new Bundle();
      						extras.putString("titol", alumne.obtenirMissatgeNou(1).getTitol());
      						extras.putString("emisor", alumne.obtenirMissatgeNou(1).getEmisor());
      						extras.putString("contingut", alumne.obtenirMissatgeNou(1).getContingut());
      						i.putExtras(extras);
      						startActivity(i);
      				  }

      				});
        			missatge3.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(2).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(2).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(2).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge4.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(3).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(3).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(3).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge5.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(4).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(4).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(4).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        		}
        		
        		
        		break;
            case 2:
                rootView = inflater.inflate(R.layout.fragment_tramits, container, false);
                break;
            case 3:
                rootView = inflater.inflate(R.layout.fragment_missatges, container, false);
                emplenarMissatges(rootView);
                if (alumne.numMissatgesNous() == 1) {
        			missatge1.setOnClickListener(new View.OnClickListener() {

      				  @Override
      				  public void onClick(View v) {
      					  Intent i = new Intent("com.ioc.action.Missatge");
      						Bundle extras = new Bundle();
      						extras.putString("titol", alumne.obtenirMissatgeNou(0).getTitol());
      						extras.putString("emisor", alumne.obtenirMissatgeNou(0).getEmisor());
      						extras.putString("contingut", alumne.obtenirMissatgeNou(0).getContingut());
      						i.putExtras(extras);
      						startActivity(i);
      				  }

      				});
        		} else if (alumne.numMissatgesNous() == 2){
        			missatge1.setOnClickListener(new View.OnClickListener() {

      				  @Override
      				  public void onClick(View v) {
      					  Intent i = new Intent("com.ioc.action.Missatge");
      						Bundle extras = new Bundle();
      						extras.putString("titol", alumne.obtenirMissatgeNou(0).getTitol());
      						extras.putString("emisor", alumne.obtenirMissatgeNou(0).getEmisor());
      						extras.putString("contingut", alumne.obtenirMissatgeNou(0).getContingut());
      						i.putExtras(extras);
      						startActivity(i);
      				  }

      				});
        			missatge2.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(1).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(1).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(1).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        		} else if (alumne.numMissatgesNous() == 3){
        			missatge1.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(0).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(0).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(0).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge2.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(1).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(1).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(1).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge3.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(2).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(2).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(2).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        		} else if (alumne.numMissatgesNous() == 4){
        			missatge1.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(0).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(0).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(0).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge2.setOnClickListener(new View.OnClickListener() {

      				  @Override
      				  public void onClick(View v) {
      					  Intent i = new Intent("com.ioc.action.Missatge");
      						Bundle extras = new Bundle();
      						extras.putString("titol", alumne.obtenirMissatgeNou(1).getTitol());
      						extras.putString("emisor", alumne.obtenirMissatgeNou(1).getEmisor());
      						extras.putString("contingut", alumne.obtenirMissatgeNou(1).getContingut());
      						i.putExtras(extras);
      						startActivity(i);
      				  }

      				});
        			missatge3.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(2).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(2).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(2).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge4.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(3).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(3).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(3).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        		} else if (alumne.numMissatgesNous() >= 5){
        			missatge1.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(0).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(0).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(0).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge2.setOnClickListener(new View.OnClickListener() {

      				  @Override
      				  public void onClick(View v) {
      					  Intent i = new Intent("com.ioc.action.Missatge");
      						Bundle extras = new Bundle();
      						extras.putString("titol", alumne.obtenirMissatgeNou(1).getTitol());
      						extras.putString("emisor", alumne.obtenirMissatgeNou(1).getEmisor());
      						extras.putString("contingut", alumne.obtenirMissatgeNou(1).getContingut());
      						i.putExtras(extras);
      						startActivity(i);
      				  }

      				});
        			missatge3.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(2).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(2).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(2).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge4.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(3).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(3).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(3).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        			missatge5.setOnClickListener(new View.OnClickListener() {

        				  @Override
        				  public void onClick(View v) {
        					  Intent i = new Intent("com.ioc.action.Missatge");
        						Bundle extras = new Bundle();
        						extras.putString("titol", alumne.obtenirMissatgeNou(4).getTitol());
        						extras.putString("emisor", alumne.obtenirMissatgeNou(4).getEmisor());
        						extras.putString("contingut", alumne.obtenirMissatgeNou(4).getContingut());
        						i.putExtras(extras);
        						startActivity(i);
        				  }

        				});
        		}
                
                break;
            case 4:
                rootView = inflater.inflate(R.layout.fragment_calendari, container, false);
                break;
            case 5:
                rootView = inflater.inflate(R.layout.fragment_avaluacio, container, false);
                break;
            case 6:
                rootView = inflater.inflate(R.layout.fragment_expedient, container, false);
                break;
            default:
                rootView = inflater.inflate(R.layout.fragment_main, container, false);
        }
        return rootView;
			
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
		
		
	}

	//métode que crea unes dades de prova i les guarda a un objecte alumne
	public static void extraureAlumneProva (String usuari) {
		alumne.esborrarMissatges();
		alumne.setUsuari(usuari);
		String nom = "Juan";
		String cognom1 = "Pérez";
		String cognom2 = "García";
		Missatge missatgeNou1 = new Missatge(0, "Missatge de benvinguda", 
				"Hola, això és el contingut del missatge de benvinguda", 
				"Professor");
		Missatge missatgeNou2 = new Missatge(1, "Inici de les classes", 
				"Hola, això és el contingut del missatge d'inici de les classes", 
				"Professor");
		alumne.setNom(nom);
		alumne.setCognom1(cognom1);
		alumne.setCognom2(cognom2);
		alumne.afegirMissatgeNou(missatgeNou1);
		alumne.afegirMissatgeNou(missatgeNou2);
	}
			
	//métode que extreu les dades de l'usuari desde l'API i les guarda a un objecte alumne
	public static void extraureAlumne (String usuari) {;
		//1- extrau les dades de l'api
		//2- extrau els missatges nous
		//3- crea els objectes dels missatges que siguin nous
		//4- crea l'objecte alumne i li afegeix la llista de missatges nous
	}
	
	public static void emplenarMissatges(View rootView) {
		if (alumne.numMissatgesNous() == 1) {
			missatge1 = (TextView)rootView.findViewById(R.id.missatge1);
    		missatge1.setText(alumne.obtenirMissatgeNou(0).getTitol());
		} else if (alumne.numMissatgesNous() == 2){
			missatge1 = (TextView)rootView.findViewById(R.id.missatge1);
    		missatge1.setText(alumne.obtenirMissatgeNou(0).getTitol());
			missatge2 = (TextView)rootView.findViewById(R.id.missatge2);
    		missatge2.setText(alumne.obtenirMissatgeNou(1).getTitol());	
		} else if (alumne.numMissatgesNous() == 3){
			missatge1 = (TextView)rootView.findViewById(R.id.missatge1);
    		missatge1.setText(alumne.obtenirMissatgeNou(0).getTitol());
			missatge2 = (TextView)rootView.findViewById(R.id.missatge2);
    		missatge2.setText(alumne.obtenirMissatgeNou(1).getTitol());	
			missatge3 = (TextView)rootView.findViewById(R.id.missatge3);
    		missatge3.setText(alumne.obtenirMissatgeNou(2).getTitol());
    		missatge1.setClickable(true);
    		missatge2.setClickable(true);
    		missatge3.setClickable(true);
		} else if (alumne.numMissatgesNous() == 4){
			missatge1 = (TextView)rootView.findViewById(R.id.missatge1);
    		missatge1.setText(alumne.obtenirMissatgeNou(0).getTitol());
			missatge2 = (TextView)rootView.findViewById(R.id.missatge2);
    		missatge2.setText(alumne.obtenirMissatgeNou(1).getTitol());	
			missatge3 = (TextView)rootView.findViewById(R.id.missatge3);
    		missatge3.setText(alumne.obtenirMissatgeNou(2).getTitol());
    		missatge4 = (TextView)rootView.findViewById(R.id.missatge4);
    		missatge4.setText(alumne.obtenirMissatgeNou(3).getTitol());
    		missatge1.setClickable(true);
    		missatge2.setClickable(true);
    		missatge3.setClickable(true);
    		missatge4.setClickable(true);
		} else if (alumne.numMissatgesNous() >= 5){
			missatge1 = (TextView)rootView.findViewById(R.id.missatge1);
    		missatge1.setText(alumne.obtenirMissatgeNou(0).getTitol());
			missatge2 = (TextView)rootView.findViewById(R.id.missatge2);
    		missatge2.setText(alumne.obtenirMissatgeNou(1).getTitol());	
			missatge3 = (TextView)rootView.findViewById(R.id.missatge3);
    		missatge3.setText(alumne.obtenirMissatgeNou(2).getTitol());
    		missatge4 = (TextView)rootView.findViewById(R.id.missatge4);
    		missatge4.setText(alumne.obtenirMissatgeNou(3).getTitol());
    		missatge5 = (TextView)rootView.findViewById(R.id.missatge5);
    		missatge5.setText(alumne.obtenirMissatgeNou(4).getTitol());
    		missatge1.setClickable(true);
    		missatge2.setClickable(true);
    		missatge3.setClickable(true);
    		missatge4.setClickable(true);
    		missatge5.setClickable(true);
		}
	}
	
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
	    if (keyCode == KeyEvent.KEYCODE_BACK) { 
	        moveTaskToBack(true); 
	        return true; 
	    } 
	    return super.onKeyDown(keyCode, event); 
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
