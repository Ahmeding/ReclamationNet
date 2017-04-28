
package com.geek7aggart.gestion2reclamation.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by BARA' on 07/04/2016.
 */
public class DBhelper extends SQLiteOpenHelper {
    private static final String TAG = SQLiteHandler.class.getSimpleName();
    public static final String DB_NAME = "sqliteReclamtion.db";
    public static final int DB_VERSION = 1;

    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE = " TEXT";
    public static final String NUMERIC_TYPE = " INTEGER ";

    public static final String TABLE_PERSONEL = "personnel";
    public static final String TABLE_RECLAMATION = "reclamation";
    public static final String TABLE_INTERVENTION = "intervention";

    //Login Table Name
    private static final String TABLE_USER = "user";
    //Login Table Columns name
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";


    String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_NAME + " TEXT," +
            KEY_EMAIL + " TEXT UNIQUE," +
            KEY_PASSWORD + " TEXT)";

//Colone Personnel

    public static final String COLUMN_IDPERS = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_PRENOM = "prenom";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";


    //REQUET CREATION TABLE PERSONNEL
    private static final String DELETE_TABLEPERS = "DROP TABLE IF EXISTE " + TABLE_PERSONEL;
    private static final String CREATE_TABLEPERS = "CREATE TABLE " + TABLE_PERSONEL + "(" +
            COLUMN_IDPERS + NUMERIC_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            COLUMN_NOM + TEXT_TYPE + COMMA_SEP +
            COLUMN_PRENOM + TEXT_TYPE + COMMA_SEP +
            COLUMN_LOGIN + TEXT_TYPE + COMMA_SEP +
            COLUMN_PASSWORD + TEXT_TYPE +
            ")";

    //Colone Reclamation
    public static final String COLUMN_ID_REC = "_id";
    public static final String COLUMN_TITRE_REC = "titre";
    public static final String COLUMN_DESC_REC = "description";
    public static final String COLUMN_DATETIME_REC = "datetimeRec";
    public static final String COLUMN_ETAT_REC = "etat";
    public static final String COLUMN_REF_REC = "ref";
    public static final String COLUMN_CLIENT_REC = "client";
    public static final String COLUMN_ADRESSE_REC = "adresse";
    public static final String COLUMN_CONTACT_TEL_REC = "contacttel";
    public static final String COLUMN_RG_REC = "re_rec";
    public static final String COLUMN_SR_REC = "sr_rec";
    public static final String COLUMN_TETE_TREC = "tete_trec";
    public static final String COLUMN_AMORCE_TREC = "amorce_trec";
    public static final String COLUMN_COULEUR_TREC = "couleur_trec";
    public static final String COLUMN_TETE_DREC = "tete_drec";
    public static final String COLUMN_AMORCE_DREC = "amorce_drec";
    public static final String COLUMN_COULEUR_DREC = "couleur_drec";
    public static final String COLUMN_SGNL_PAR_REC = "snglPar";
    public static final String COLUMN_OBSERV_INIT_REC = "observinit";
    public static final String COLUMN_OBSERV_TECH_REC = "obseevtechR";
    public static final String COLUMN_SYNCREC = "synckRec";


//REQUET CREATION TABLE RECLAMATION

    private static final String DELETE_TABLEREC = "DROP TABLE IF EXISTE " + TABLE_RECLAMATION;

    private static final String CREATE_TABLEREC = "CREATE TABLE " + TABLE_RECLAMATION + "(" +
            COLUMN_ID_REC + NUMERIC_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            COLUMN_TITRE_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_DESC_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_REF_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_CLIENT_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_ADRESSE_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_CONTACT_TEL_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_DATETIME_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_RG_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_SR_REC+ TEXT_TYPE + COMMA_SEP +
            COLUMN_TETE_TREC + TEXT_TYPE + COMMA_SEP +
            COLUMN_AMORCE_TREC + TEXT_TYPE + COMMA_SEP +
            COLUMN_COULEUR_TREC + TEXT_TYPE + COMMA_SEP +
            COLUMN_TETE_DREC + TEXT_TYPE + COMMA_SEP +
            COLUMN_AMORCE_DREC + TEXT_TYPE + COMMA_SEP +
            COLUMN_COULEUR_DREC + TEXT_TYPE + COMMA_SEP +
            COLUMN_SGNL_PAR_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_OBSERV_INIT_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_OBSERV_TECH_REC + TEXT_TYPE + COMMA_SEP +
            COLUMN_SYNCREC + NUMERIC_TYPE + COMMA_SEP +
            COLUMN_ETAT_REC + TEXT_TYPE + ")";


    //Colone Intervaention
    public static final String COLUMN_ID_INTER = "_id";
    public static final String COLUMN_REF_INTER = "ref";
    public static final String COLUMN_DESC_INTER = "description";
    public static final String COLUMN_RG = "rg";
    public static final String COLUMN_SR = "sr";
    public static final String COLUMN_TETE = "tete";
    public static final String COLUMN_AMORCE = "amorce";
    public static final String COLUMN_COULEUR = "couleur";
    public static final String COLUMN_DATE_INTER = "date_intervention";
    public static final String COLUMN_ETAT_PANE = "etat";
    public static final String COLUMN_ID_IR = "idInterRec";


//REQUET CREATION TABLE Intervention

    private static final String DELETE_TABLEINT = "DROP TABLE IF EXISTE " + TABLE_INTERVENTION;
    private static final String CREATE_TABLEINT = "CREATE TABLE " + TABLE_INTERVENTION + "(" +
            COLUMN_ID_INTER + NUMERIC_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            COLUMN_REF_INTER + TEXT_TYPE + COMMA_SEP +
            COLUMN_DESC_INTER + TEXT_TYPE + COMMA_SEP +
            COLUMN_RG + TEXT_TYPE + COMMA_SEP +
            COLUMN_SR + TEXT_TYPE + COMMA_SEP +
            COLUMN_TETE + TEXT_TYPE + COMMA_SEP +
            COLUMN_AMORCE + TEXT_TYPE + COMMA_SEP +
            COLUMN_COULEUR + TEXT_TYPE + COMMA_SEP +
            COLUMN_DATE_INTER + TEXT_TYPE + COMMA_SEP +
            COLUMN_ETAT_PANE + TEXT_TYPE +COMMA_SEP +
            COLUMN_ID_IR + NUMERIC_TYPE +COMMA_SEP +
            " FOREIGN KEY(" + COLUMN_ID_IR + ")" +
            " REFERENCES "+TABLE_RECLAMATION+"( " + COLUMN_ID_REC + " )" +
            ");";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating required tables
        db.execSQL(CREATE_TABLEPERS);
        db.execSQL(CREATE_TABLEREC);
        db.execSQL(CREATE_TABLEINT);
        db.execSQL(CREATE_LOGIN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_TABLEPERS);
        db.execSQL(DELETE_TABLEREC);
        db.execSQL(DELETE_TABLEINT);
        db.execSQL("DROP TABLE IF EXISTE "+TABLE_USER);
        onCreate(db);
    }


    /**
     * Storing user details in database
     * */
    public void addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_PASSWORD, password); // Email


        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }
    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("password",cursor.getString(3));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }
    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
}
