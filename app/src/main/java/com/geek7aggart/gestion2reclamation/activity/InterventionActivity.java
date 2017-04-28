
package com.geek7aggart.gestion2reclamation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.geek7aggart.gestion2reclamation.Model.Intervention;
import com.geek7aggart.gestion2reclamation.Model.Reclamation;
import com.geek7aggart.gestion2reclamation.R;
import com.geek7aggart.gestion2reclamation.utils.DAOIntervention;
import com.geek7aggart.gestion2reclamation.utils.DAOdbReclamation;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterventionActivity extends AppCompatActivity {
    private String keyIntent;
    private Reclamation reclamation;
    private EditText etAffiInterReslt;
    private TextView tvAffInterRef;
    private TextView tvAffInterClient;
    private TextView tvAffiInterAdress;
    private TextView tvAffiInterContact;
    private TextView tvAffiInterRG;
    private TextView tvAffiInterSR;
    private Spinner spTT;
    private Spinner spTD;
    private Spinner spAT;
    private Spinner spAD;
    private Spinner spCT;
    private Spinner spCD;
    int idrec;
    private TextView tvAffiInterdate;
    private TextView tvAffiInterSgnlPar;
    private TextView tvAffiInterObsrvInit;
    DAOIntervention daoIntervention;
    DAOdbReclamation daOdbReclamation;

    Intervention intervention;
    private Spinner spObserv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervention);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        daoIntervention = new DAOIntervention(this);
        daOdbReclamation = new DAOdbReclamation(this);

        intervention = new Intervention();
        tvAffInterRef = (TextView) findViewById(R.id.tv_AffInterRef);
        tvAffInterClient = (TextView) findViewById(R.id.tv_AffInterClient);
        tvAffiInterAdress = (TextView) findViewById(R.id.tv_AffiInterAdress);
        tvAffiInterContact = (TextView) findViewById(R.id.tv_AffiInterContact);

        tvAffiInterRG = (TextView) findViewById(R.id.tv_AffiInterRG);
        tvAffiInterSR = (TextView) findViewById(R.id.tv_AffiInterSR);

        spTT = (Spinner) findViewById(R.id.sp_TT);
        spTD = (Spinner) findViewById(R.id.sp_DT);
        spAT = (Spinner) findViewById(R.id.sp_AT);
        spAD = (Spinner) findViewById(R.id.sp_AD);
        spCT = (Spinner) findViewById(R.id.sp_CT);
        spCD = (Spinner) findViewById(R.id.sp_CD);
        spObserv = (Spinner) findViewById(R.id.sp_Observ);

//
        tvAffiInterdate = (TextView) findViewById(R.id.tv_AffiInterdate);
        tvAffiInterSgnlPar = (TextView) findViewById(R.id.tv_AffiInterSgnlPar);
        tvAffiInterObsrvInit = (TextView) findViewById(R.id.tv_AffiInterObsrvInit);
        etAffiInterReslt = (EditText) findViewById(R.id.et_AffiInterReslt);

        List<String> etatPann = new ArrayList<>();

        etatPann.add("En cours");
        etatPann.add("Terminer");


        List<String> tete = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            tete.add("" + i);
        }

        List<String> amorce = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            amorce.add("" + i);
        }

        List<String> couleur = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            couleur.add("" + i);
        }

        ArrayAdapter<String> observData = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, etatPann);
        observData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> dataAdapterT = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tete);
        dataAdapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> dataAdapterA = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, amorce);
        dataAdapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> dataAdapterC = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, couleur);
        dataAdapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spObserv.setAdapter(observData);
        spTT.setAdapter(dataAdapterT);
        spTD.setAdapter(dataAdapterT);
        spAT.setAdapter(dataAdapterA);
        spAD.setAdapter(dataAdapterA);
        spCT.setAdapter(dataAdapterC);
        spCD.setAdapter(dataAdapterC);


        Bundle KEY_INTENT_bundle = getIntent().getExtras();
        if (KEY_INTENT_bundle != null) {
            keyIntent = KEY_INTENT_bundle.getString("REC");
        }
        reclamation = getRec(keyIntent);
        idrec = reclamation.getIdReclamation();
        Log.i("hiiiii", "--------------------------------------------->" + idrec + reclamation.getRefRec());
        tvAffInterRef.setText(reclamation.getRefRec());
        tvAffInterClient.setText(reclamation.get_client());
        tvAffiInterAdress.setText(reclamation.getAdresse());
        tvAffiInterContact.setText(reclamation.getContactTel());
        tvAffiInterRG.setText(reclamation.getRG_Rec());
        tvAffiInterSR.setText(reclamation.getSR_Rec());
        // spTT.setText(reclamation.getTete_Rec());
        // spDT.setText(reclamation.getTete_Rec());
        // spAT.setText(reclamation.getAmorce_Rec());
        // spAD.setText(reclamation.getAmorce_Rec());
        // spCT.setText(reclamation.getCouleur_Rec());
        // spCD.setText(reclamation.getCouleur_Rec());
        tvAffiInterdate.setText(reclamation.getDateRec());
        tvAffiInterSgnlPar.setText(reclamation.getSgnlPar());
        tvAffiInterObsrvInit.setText(reclamation.getObservInit());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "intervention achevé avec succès", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                addintervention();
                Intent intent = new Intent(getApplicationContext(),ListeReclamationActivity.class);
                startActivity(intent);

            }
        });
    }  private Reclamation getRec(String keyIntent) {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(keyIntent);
            return (new Reclamation(
                    jsonObj.getInt("idReclamation"),
                    jsonObj.getString("titre"),
                    jsonObj.getString("refRec"),
                    jsonObj.getString("_client"),
                    jsonObj.getString("adresse"),
                    jsonObj.getString("dateRec"),
                    jsonObj.getString("contactTel"),
                    jsonObj.getString("RG_Rec"),
                    jsonObj.getString("SR_Rec"),
                    jsonObj.getString("tete_TRec"),
                    jsonObj.getString("amorce_TRec"),
                    jsonObj.getString("couleur_TRec"),
                    jsonObj.getString("tete_DRec"),
                    jsonObj.getString("amorce_DRec"),
                    jsonObj.getString("couleur_DRec"),
                    jsonObj.getString("sgnlPar"),
                    jsonObj.getString("observInit"),
                    jsonObj.getString("etat"),
                    jsonObj.getInt("syncRec")

            ));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void btnOnClickAddIntervention(View view) {


    }

    public void addintervention() {
        intervention.setRefInterv("INT" + idrec);
        intervention.setDescription(etAffiInterReslt.getText().toString());
        intervention.set_tete("" + spTT.getSelectedItem().toString());
        intervention.set_tete("" + spTD.getSelectedItem().toString());
        intervention.set_amorce("" + spAT.getSelectedItem().toString());
        intervention.set_amorce("" + spAD.getSelectedItem().toString());
        intervention.set_couleur("" + spCT.getSelectedItem().toString());
        intervention.set_couleur("" + spCD.getSelectedItem().toString());
        intervention.setEtatPane("");
        intervention.set_idInterRec(idrec);
        Log.i("ooooook", "-------------------------------------------------------->" + idrec);
        intervention.set_RG(reclamation.getRG_Rec());
        intervention.set_SR(reclamation.getSR_Rec());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        intervention.setDatetimeIntervention(currentDateandTime);
        daoIntervention.newIntervention(intervention);
        daoIntervention.getAllIntervention();
        /******************************************************************************/
        reclamation.setEtat(spObserv.getSelectedItem().toString());
        reclamation.setAmorce_TRec(spAT.getSelectedItem().toString());
        reclamation.setTete_TRec(spTT.getSelectedItem().toString());
        reclamation.setCouleur_TRec(spCT.getSelectedItem().toString());
        reclamation.setAmorce_DRec(spAD.getSelectedItem().toString());
        reclamation.setTete_DRec(spTD.getSelectedItem().toString());
        reclamation.setCouleur_DRec(spCD.getSelectedItem().toString());
        reclamation.setObserveTech(etAffiInterReslt.getText().toString());
        daOdbReclamation.updateReclamation(reclamation);
        List<Intervention> interventions = new ArrayList<>();

        for (Intervention i : daoIntervention.getAllIntervention()) {
            interventions.add(i);

            Log.i("inter", "---------------------------------------------->" + i.get_idInterv() + " "
                    + i.getDatetimeIntervention() +
                    i.get_idInterRec() +
                    i.get_RG() +
                    i.get_amorce() +
                    i.get_couleur());
        }

    }


}