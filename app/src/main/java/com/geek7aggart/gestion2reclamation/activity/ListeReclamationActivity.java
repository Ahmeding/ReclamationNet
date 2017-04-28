

package com.geek7aggart.gestion2reclamation.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geek7aggart.gestion2reclamation.Adapters.ReclamationAdapter;
import com.geek7aggart.gestion2reclamation.Model.Reclamation;
import com.geek7aggart.gestion2reclamation.R;
import com.geek7aggart.gestion2reclamation.utils.DAOdbReclamation;
import com.geek7aggart.gestion2reclamation.utils.DBhelper;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ListeReclamationActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

   // private static final String UPDATE_URL = "http://192.168.1.102:8081/Rest/update.php";
   private static final String UPDATE_URL = "http://192.168.1.102:8081/Rest/updatebyref.php";
    private static final String GETALL_URL = "http://192.168.1.102:8081/Rest/getall.php";
    private static final String PARAM_REFREC = "refRec";
    private static final String PARAM_TETET = "tete_TRec";
    private static final String PARAM_AMORCET = "amorce_TRec";
    private static final String PARAM_COULEURT = "couleur_TRec";
    private static final String PARAM_TETED = "tete_DRec";
    private static final String PARAM_AMORCED = "amorce_DRec";
    private static final String PARAM_COULEURD = "couleur_DRec";
    private static final String PARAM_OBSRVT = "observeTech";
    private static final String PARAM_ETAT = "etat";
    private static final String PARAM_SYNC = "syncRec";
    private static final String EN_ATTENTE = "En attente";
    private static final String EN_COURS = "En cours";
    private static final String TERMINER = "Terminer";

    private ProgressDialog pDialog;
    private ListView lvRec;
    private List<Reclamation> reclamations;
    private Reclamation reclamation, reclamation1;
    private DBhelper db;
    private DAOdbReclamation daOdbReclamation;
    ReclamationAdapter reclamationAdapter;
    private SwipeRefreshLayout swipeLayout;
    private TextView tvEA;
    private TextView tvEC;
    private TextView tvT;

    /*******************************************
     * ONCREATE
     *
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_reclamation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvRec = (ListView) findViewById(R.id.listVRec);
        daOdbReclamation = new DAOdbReclamation(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        reclamations = new ArrayList<>();


        initDAOREC();
        reclamationAdapter = new ReclamationAdapter(this, R.layout.raw_layout, reclamations);
        //lvRec.setAdapter(reclamationAdapter);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        tvEA = (TextView) findViewById(R.id.tv_EA);
        tvEC = (TextView) findViewById(R.id.tv_EC);
        tvT = (TextView) findViewById(R.id.tv_T);

        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.CYAN, Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE);
        addItemClickListener(lvRec);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Synchronisation...", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                getAllTerminer();
            }
        });
    }
/*****
 *
 * END ONCREATE
 */
    /******************************************
     * MENU ACTION BAR
     *
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.action_edit:
                initLV();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /***
     *
     * END MENU ACTION BAR
     *
     */


    /************************
     * INIT LISTVIEW FROM DATABASE
     */

    public void initDAOREC() {
        daOdbReclamation = new DAOdbReclamation(this);
        for (Reclamation r : daOdbReclamation.getReclamations()) {
            if (r.getEtat().toString().trim().equals("En attente")) {
                reclamations.add(r);
            }
        }
    }

    /********************
     * END INIT LISTVIEW FROM DATA BASE
     */
    public int getRecsByEtat(String etat, List<Reclamation> listReclamation) {
        int cpt = 0;

        for (Reclamation r : listReclamation) {
            if (r.getEtat().toString().trim().equals(etat)) {
                cpt++;
            }
        }
        return cpt;
    }

    /***********
     * IYEM CLICK LISTNER
     *
     * @param listView
     */
    private void addItemClickListener(final ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Reclamation reclamation = (Reclamation) listView.getItemAtPosition(position);
                Intent intent = new Intent(ListeReclamationActivity.this, ReclamtionActivity.class);
                intent.putExtra("REC", (new Gson().toJson(reclamation)));
                startActivity(intent);
            }
        });
    }


    /**************
     *
     * END CLICK LISTNER
     */
    /************
     * GETALLATERMINER
     */
    public void getAllTerminer() {
        daOdbReclamation = new DAOdbReclamation(this);
        List<Reclamation> _reclamations = new ArrayList<>();
        int i = 0;
        for (Reclamation r : daOdbReclamation.getReclamations()) {

            if (r.getEtat().equals(TERMINER)) {
                _reclamations.add(r);
                i++;
                // Log.i("syncAll", "---------------------------+ + +------------------>" +
                //         r.getIdReclamation() + r.getTitre() + r.getEtat());
                update(r.getRefRec(), r.getTete_TRec(), r.getAmorce_TRec(), r.getCouleur_TRec(),
                         r.getTete_DRec(), r.getAmorce_DRec(), r.getCouleur_DRec(), r.getObserveTech(), r.getEtat(), 1);
                daOdbReclamation.deleteReclamation(r);
            }


        }
        onRefresh();
    }

    /************
     * END GETALLTERMINER
     */


    /****************************
     * INIT LISTVIEW FROM REST
     */
    public void initLV() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(GETALL_URL, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                pDialog.setMessage("Synchronisation ...");
                showDialog();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (parseGetData(responseBody) != null) {
                    int i = 0;
                    List<Reclamation> recs = parseGetData(responseBody);
                    for (Reclamation rcs : recs) {
                        if (!daOdbReclamation.isRecExist(rcs)) {
                            daOdbReclamation.addReclamation(rcs);
                            i++;
                        }


                    }
                    toast(i + " reclamation(s) ont ajouté...");
                    reclamationAdapter.notifyDataSetChanged();

                    //    InetAddress ip = null;
                    //    try {
                    //        ip =InetAddress.getLocalHost();
                    //    } catch (UnknownHostException e) {
                    //        e.printStackTrace();
                    //    }
                    //    Log.i("IP","--------------------------------------------->>"+ ip);
                    Toast.makeText(ListeReclamationActivity.this, "Synchronisation achevé avec succé !", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                onRefresh();
                hideDialog();
            }
        });
    }

    private List parseGetData(byte[] src) {
        List<Reclamation> recs = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            recs = objectMapper.readValue(src, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Reclamation.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recs;
    }
/****
 *END IIT LISTVOEW FROM REST
 */
    /*************************
     * @param refRec
     * @param tete_Trec
     * @param amorce_Trec
     * @param couleur_Trec
     * @param tete_Drec
     * @param amorce_Drec
     * @param couleur_Drec
     * @param observTech
     * @param etat
     * @param syncRec
     */
    private void update(String refRec, String tete_Trec, String amorce_Trec, String couleur_Trec, String tete_Drec, String amorce_Drec, String couleur_Drec, String observTech, String etat, int syncRec) {

        AsyncHttpClient client = new AsyncHttpClient();
        //Params
        RequestParams params = new RequestParams();
        params.put(PARAM_REFREC, refRec);
        params.put(PARAM_TETET, tete_Trec);
        params.put(PARAM_AMORCET, amorce_Trec);
        params.put(PARAM_COULEURT, couleur_Trec);
        params.put(PARAM_TETED, tete_Drec);
        params.put(PARAM_AMORCED, amorce_Drec);
        params.put(PARAM_COULEURD, couleur_Drec);
        params.put(PARAM_OBSRVT, observTech);
        params.put(PARAM_ETAT, etat);
        params.put(PARAM_SYNC, syncRec);
        //Requete
        client.post(UPDATE_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                pDialog.setMessage("Synchronisation ...");
                showDialog();
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (parseData(responseBody)) {
                    Toast.makeText(ListeReclamationActivity.this, "Synchronisation achevé avec succé !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(ListeReclamationActivity.this, "Connection au serveur a échoué !", Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFinish() {
                super.onFinish();
                hideDialog();
            }
        });


    }

    private boolean parseData(byte[] src) {
        boolean _update = false;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(src);

            Log.i("oooo", "---------------------------------_----------------_-> " + rootNode.toString());
            if (rootNode.has("reclamation")) {
                _update = true;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return _update;
    }


    /******************
     * END UPDATE
     */


    /**************
     * SHOW DIALOG
     */

    private void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    /********
     * END SHOW DIALOG
     */


    /**
     * HIDE DIALOG
     */
    private void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    /****
     * END HIDE DIALOG
     */


    /*********
     * MESSAGE TOAST
     *
     * @param msg
     */

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    } //end toasttt

    /****
     * END MESSAGE TOAST
     */


    /**
     * ONREfRESH
     */
    @Override
    public void onRefresh() {
        daOdbReclamation.getall(this);
    }


    /****
     *
     * END ONREFRESH
     */

    /***
     * RELOAD
     *
     * @param listreclmtions
     */
    public void reload(List<Reclamation> listreclmtions) {

        this.reclamations = listreclmtions;
        reclamationAdapter.clear();

       tvEA.setText(String.valueOf(getRecsByEtat(EN_ATTENTE,listreclmtions)));
       tvEC.setText(String.valueOf(getRecsByEtat(EN_COURS,listreclmtions)));
        tvT.setText(String.valueOf(getRecsByEtat(TERMINER,listreclmtions)));

        reclamationAdapter = new ReclamationAdapter(this, R.layout.raw_layout, reclamations);

        lvRec.setAdapter(reclamationAdapter);
        reclamationAdapter.notifyDataSetChanged();
        swipeLayout.setRefreshing(false);
    }
    /*****
     *
     * END RELOAD
     */


    /***
     * ONRESUME
     */
    @Override
    protected void onResume() {
        super.onResume();
        swipeLayout.setRefreshing(true);
        onRefresh();
    }

    /**
     * END ONRSUME
     */
}
