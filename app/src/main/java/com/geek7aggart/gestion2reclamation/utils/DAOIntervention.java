

package com.geek7aggart.gestion2reclamation.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.geek7aggart.gestion2reclamation.Model.Intervention;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BARA' on 09/05/2016.
 */
public class DAOIntervention implements IIntervention {


    private SQLiteDatabase database;
    private DBhelper dBhelper;

    public DAOIntervention(Context context) {
        dBhelper = new DBhelper(context);
        database = dBhelper.getWritableDatabase();
    }

    public void close() {
        dBhelper.close();
    }

    @Override
    public long newIntervention(Intervention intervention) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dBhelper.COLUMN_REF_INTER, intervention.get_idInterv());
        contentValues.put(dBhelper.COLUMN_RG, intervention.get_RG());
        contentValues.put(dBhelper.COLUMN_SR, intervention.get_SR());
        contentValues.put(dBhelper.COLUMN_TETE, intervention.get_tete());
        contentValues.put(dBhelper.COLUMN_AMORCE, intervention.get_amorce());
        contentValues.put(dBhelper.COLUMN_ETAT_PANE, intervention.getEtatPane());
        contentValues.put(dBhelper.COLUMN_DATE_INTER, intervention.getDatetimeIntervention());
        contentValues.put(dBhelper.COLUMN_DESC_INTER, intervention.getDescription());
        contentValues.put(dBhelper.COLUMN_COULEUR, intervention.get_couleur());
        contentValues.put(dBhelper.COLUMN_ID_IR, intervention.get_idInterRec());

        return database.insert(dBhelper.TABLE_INTERVENTION, null, contentValues);

    }

    @Override
    public boolean updateIntervention(Intervention intervention) {

        String whereClause = dBhelper.COLUMN_ID_INTER + "=?";
        String[] whereArgs = new String[]{String.valueOf(intervention.get_idInterv())};
        ContentValues contentValues = new ContentValues();
        contentValues.put(dBhelper.COLUMN_REF_INTER, intervention.get_idInterv());
        contentValues.put(dBhelper.COLUMN_RG, intervention.get_RG());
        contentValues.put(dBhelper.COLUMN_SR, intervention.get_SR());
        contentValues.put(dBhelper.COLUMN_TETE, intervention.get_tete());
        contentValues.put(dBhelper.COLUMN_AMORCE, intervention.get_amorce());
        contentValues.put(dBhelper.COLUMN_ETAT_PANE, intervention.getEtatPane());
        contentValues.put(dBhelper.COLUMN_DATE_INTER, intervention.getDatetimeIntervention());
        contentValues.put(dBhelper.COLUMN_DESC_INTER, intervention.getDescription());
        contentValues.put(dBhelper.COLUMN_COULEUR, intervention.get_couleur());
        contentValues.put(dBhelper.COLUMN_ID_IR, intervention.get_idInterRec());

        database.update(dBhelper.TABLE_INTERVENTION, contentValues, whereClause, whereArgs);
        return true;
    }

    @Override
    public void deleteIntervention(Intervention intervention) {
        String whereClause = dBhelper.COLUMN_ID_INTER + "=?";
        String[] whereArgs = new String[]{String.valueOf(intervention.get_idInterv())};

        database.delete(dBhelper.TABLE_INTERVENTION, whereClause, whereArgs);
    }

    @Override
    public List<Intervention> getAllIntervention() {
        List<Intervention> interventions = new ArrayList<>();
        Cursor cursor = database.query(dBhelper.TABLE_INTERVENTION, null, null, null, null, null, dBhelper.COLUMN_ID_INTER + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Intervention intervention = cursorToIntervention(cursor);
            interventions.add(intervention);
            cursor.moveToNext();
        }
        cursor.close();
        return interventions;
    }

    private Intervention cursorToIntervention(Cursor cursor) {

        Intervention intervention = new Intervention();
        intervention.set_idInterRec(cursor.getInt(cursor.getColumnIndex(dBhelper.COLUMN_ID_IR)));
        intervention.set_idInterv(cursor.getInt(cursor.getColumnIndex(dBhelper.COLUMN_ID_INTER)));
        intervention.set_RG(cursor.getString(cursor.getColumnIndex(dBhelper.COLUMN_RG)));
        intervention.set_SR(cursor.getString(cursor.getColumnIndex(dBhelper.COLUMN_SR)));
        intervention.set_amorce(cursor.getString(cursor.getColumnIndex(dBhelper.COLUMN_AMORCE)));
        intervention.set_couleur(cursor.getString(cursor.getColumnIndex(dBhelper.COLUMN_COULEUR)));
        intervention.set_tete(cursor.getString(cursor.getColumnIndex(dBhelper.COLUMN_TETE)));
        intervention.setDatetimeIntervention(cursor.getString(cursor.getColumnIndex(dBhelper.COLUMN_DATE_INTER)));
        intervention.setDescription(cursor.getString(cursor.getColumnIndex(dBhelper.COLUMN_DESC_INTER)));
        intervention.setEtatPane(cursor.getString(cursor.getColumnIndex(dBhelper.COLUMN_ETAT_PANE)));

        return intervention;
    }
}
