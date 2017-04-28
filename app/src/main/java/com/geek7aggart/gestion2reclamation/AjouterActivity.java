
package com.geek7aggart.gestion2reclamation;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geek7aggart.gestion2reclamation.Model.Reclamation;
import com.geek7aggart.gestion2reclamation.activity.ListeReclamationActivity;
import com.geek7aggart.gestion2reclamation.utils.DAOdbReclamation;

import java.util.ArrayList;
import java.util.Calendar;

public class AjouterActivity extends AppCompatActivity {

    EditText etAjtTire,
            etAjtDescr;
    Spinner spnEtat;
    TextView tvAjtDate;
    Button btnAjout;

    private DatePicker datePicker;
    private Calendar calendar;
    private int year,month,day;


    private ArrayList<Reclamation> reclamations;
    private DAOdbReclamation daOdbReclamation;
    SQLiteDatabase db;
    private Reclamation reclamation;

    public AjouterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etAjtTire= (EditText) findViewById(R.id.etTitreRec);
        etAjtDescr= (EditText) findViewById(R.id.etDescRec);
        tvAjtDate= (TextView) findViewById(R.id.tvDateRec);
        spnEtat= (Spinner) findViewById(R.id.spEtatRec);
        btnAjout= (Button) findViewById(R.id.btnEnrgRec);
        reclamations=new ArrayList<>();
        intiDAOREC();
        String[] etats={"-- Select Etat --","Crée","Envoyée","En Attend","En cours","Terminé",};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,etats);
        spnEtat.setAdapter(arrayAdapter);

        calendar =Calendar.getInstance();
        year=calendar.get(calendar.YEAR);
        month=calendar.get(calendar.MONTH);
        day=calendar.get(calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

    }
public void intiDAOREC(){
    daOdbReclamation= new DAOdbReclamation(this);
    for(Reclamation r : daOdbReclamation.getReclamations()){
        reclamations.add(r);
    }
}
    public void setDate(View v){
        //showDilog(999);
       // return true;
 }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==999){
            return new DatePickerDialog(this,myDateListener,year,month,day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showDate(year,monthOfYear+1,dayOfMonth);
        }
    };

    private void showDate(int year, int month, int day) {
        tvAjtDate.setText(new StringBuilder()
                .append(day)
                .append("-")
                .append(month)
                .append("-")
                .append(year));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_view:

startActivity(new Intent(this,ListeReclamationActivity.class));
                Toast.makeText(this, "search",
                        Toast.LENGTH_SHORT).show();
                return true;

        }
        return false;
    }
    public void btnAjouterOnclick(View view){
        reclamation= new Reclamation();
        reclamation.setTitre(etAjtTire.getText().toString());
        reclamation.setDescription(etAjtDescr.getText().toString());
       // reclamation.setDatetime(tvAjtDate.getText().toString());
        reclamation.setEtat(spnEtat.getSelectedItem().toString());
 daOdbReclamation.addReclamation(reclamation);


       // toast("ajout"+reclamation.getDatetime());
    }

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    } //end toasttt
}
