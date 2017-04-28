package com.geek7aggart.gestion2reclamation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geek7aggart.gestion2reclamation.Model.Reclamation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import com.geek7aggart.gestion2reclamation.activity.ListeReclamationActivity;
import com.geek7aggart.gestion2reclamation.utils.DAOdbReclamation;

public class ModifierActivity extends AppCompatActivity {

    private String keyIntent;
    private EditText etAjtTire;
    private EditText etAjtDescr;
    private TextView tvAjtDate;
    private Spinner spnEtat;
    private Button btnAjout;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private DAOdbReclamation daOdbReclamation;
    private ArrayList<Reclamation> reclamations;
    private Reclamation reclamation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle KEY_INTENT_bundle = getIntent().getExtras();
        if(KEY_INTENT_bundle!=null){
            keyIntent=KEY_INTENT_bundle.getString("REC");
        }

        reclamation=getRec(keyIntent);


        etAjtTire= (EditText) findViewById(R.id.etEditTitreRec);

        etAjtDescr= (EditText) findViewById(R.id.etEditDescRec);
        tvAjtDate= (TextView) findViewById(R.id.etEditDateRec);
        spnEtat= (Spinner) findViewById(R.id.spEditEtatRec);
        btnAjout= (Button) findViewById(R.id.btnEnrgRec);

        etAjtTire.setText(reclamation.getTitre());
        etAjtDescr.setText(reclamation.getDescription());
        tvAjtDate.setText(reclamation.getEtat());
        //spnEtat.setSelectedItem(reclamation.getDatetime());



        reclamations= new ArrayList<>();
        intiDAOREC();
        String[] etats={"-- Select Etat --","Crée","Envoyée","En Attend","En cours","Terminé",};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,etats);
        spnEtat.setAdapter(arrayAdapter);

        calendar = Calendar.getInstance();
        year=calendar.get(calendar.YEAR);
        month=calendar.get(calendar.MONTH);
        day=calendar.get(calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);





    }

    private void showDate(int year, int month, int day) {
        tvAjtDate.setText(new StringBuilder()
                .append(day)
                .append("-")
                .append(month)
                .append("-")
                .append(year));
    }
    private void intiDAOREC() {
        daOdbReclamation = new DAOdbReclamation(this);
        for (Reclamation r : daOdbReclamation.getReclamations()) {
            reclamations.add(r);
        }
    }

    private Reclamation getRec(String keyIntent)  {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(keyIntent);
            return (new Reclamation(
                  // jsonObj.getString("etat"),
                  // jsonObj.getString("datetime"),
                  // jsonObj.getString("description"),
                  // jsonObj.getString("titre"),
                  // jsonObj.getInt("idReclamation")
                    ));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:

                startActivity(new Intent(this, AjouterActivity.class));
                toast("add");
                return true;
            case R.id.action_view:

                startActivity(new Intent(this, ListeReclamationActivity.class));
                toast("view");
                return true;

        }
        return false;


    }

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    } //end toasttt
}