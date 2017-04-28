
package com.geek7aggart.gestion2reclamation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.geek7aggart.gestion2reclamation.Model.Reclamation;
import com.geek7aggart.gestion2reclamation.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ReclamtionActivity extends AppCompatActivity {


    String keyIntent;
    Reclamation reclamation;

    private TextView tvAffRecRef;
    private TextView tvAffRecClient;
    private TextView tvAffRecAdrs;
    private TextView tvAffRecContact;
    private TextView tvAffRecRG;
    private TextView tvAffRecSR;
    private TextView tvAffRecTete1;
    private TextView tvAffRecTete2;
    private TextView tvAffRecAmorce1;
    private TextView tvAffRecAmorce2;
    private TextView tvAffRecColr1;
    private TextView tvAffRecColr2;
    private TextView tvAffRecDateRec;
    private TextView tvAffRecSglPar;
    private TextView tvAffRecObservRec;
    private TextView tvAffRecObservRecTech;
    private TextView tvAffRecEtat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamtion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reclamation = new Reclamation();


        tvAffRecRef = (TextView) findViewById(R.id.tv_AffRecRef);
        tvAffRecClient = (TextView) findViewById(R.id.tv_AffRecClient);
        tvAffRecAdrs = (TextView) findViewById(R.id.tv_AffRecAdrs);
        tvAffRecContact = (TextView) findViewById(R.id.tv_AffRecContact);

        tvAffRecRG = (TextView) findViewById(R.id.tv_AffRecRG);
        tvAffRecSR = (TextView) findViewById(R.id.tv_AffRecSR);
        tvAffRecTete1 = (TextView) findViewById(R.id.tv_AffRecTete1);
        tvAffRecTete2 = (TextView) findViewById(R.id.tv_AffRecTete2);
//
        tvAffRecAmorce1 = (TextView) findViewById(R.id.tv_AffRecAmorce1);
        tvAffRecAmorce2 = (TextView) findViewById(R.id.tv_AffRecAmorce2);
        tvAffRecColr1 = (TextView) findViewById(R.id.tv_AffRecColr1);
        tvAffRecColr2 = (TextView) findViewById(R.id.tv_AffRecColr2);
//
        tvAffRecDateRec = (TextView) findViewById(R.id.tv_AffRecDateRec);
        tvAffRecSglPar = (TextView) findViewById(R.id.tv_AffRecSglPar);
        tvAffRecObservRec = (TextView) findViewById(R.id.tv_AffRecObservRec);
        tvAffRecObservRecTech = (TextView) findViewById(R.id.tv_AffRecObservRectech);
        tvAffRecEtat = (TextView) findViewById(R.id.tv_AffRecEtat);


        Bundle KEY_INTENT_bundle = getIntent().getExtras();
        if (KEY_INTENT_bundle != null) {
            keyIntent = KEY_INTENT_bundle.getString("REC");
            Log.i("REC","--------------------------             ---------------------"+keyIntent);
        }
        reclamation = getRec(keyIntent);

        tvAffRecRef.setText(reclamation.getRefRec());
        tvAffRecClient.setText(reclamation.get_client());
        tvAffRecAdrs.setText(reclamation.getAdresse());
        tvAffRecContact.setText(reclamation.getContactTel());
        tvAffRecRG.setText(reclamation.getRG_Rec());
        tvAffRecSR.setText(reclamation.getSR_Rec());
        tvAffRecTete1.setText(reclamation.getTete_TRec());
        tvAffRecTete2.setText(reclamation.getTete_DRec());
        tvAffRecAmorce1.setText(reclamation.getAmorce_TRec());
        tvAffRecAmorce2.setText(reclamation.getAmorce_DRec());
        tvAffRecColr1.setText(reclamation.getCouleur_TRec());
        tvAffRecColr2.setText(reclamation.getCouleur_DRec());
        tvAffRecDateRec.setText(reclamation.getDateRec());
        tvAffRecSglPar.setText(reclamation.getSgnlPar());
        tvAffRecObservRec.setText(reclamation.getObservInit());
        tvAffRecObservRecTech.setText(reclamation.getObserveTech());
        tvAffRecEtat.setText(reclamation.getEtat());

    }

    private Reclamation getRec(String keyIntent) {
        JSONObject jsonObj;
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


    public void btnActionOnclick(View v) {
        Intent intent = new Intent(ReclamtionActivity.this, InterventionActivity.class);
        intent.putExtra("REC", (new Gson().toJson(reclamation)));
        startActivity(intent);
    }

    public void btnEffacerOnclick(View v) {
        /*DAOdbReclamation db = new DAOdbReclamation((this));
        db.deleteReclamation(reclamation);
        db.close();*/
        toast("EFFACER !");
        startActivity(new Intent(this, ListeReclamationActivity.class));
        finish();
    }

    public void btnRetourOnclick(View v) {

        toast("Retour!");

        startActivity(new Intent(this, ListeReclamationActivity.class));
        finish();
    }

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    } //en
}
