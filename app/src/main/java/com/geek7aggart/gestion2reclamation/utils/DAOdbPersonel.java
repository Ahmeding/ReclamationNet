package com.geek7aggart.gestion2reclamation.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.geek7aggart.gestion2reclamation.Model.Personnel;

/**
 * Created by BARA' on 07/04/2016.
 */
public class DAOdbPersonel {
    private SQLiteDatabase database;
    private DBhelper dbHelper;

    public DAOdbPersonel(Context context) {
        dbHelper = new DBhelper(context);
        database = dbHelper.getWritableDatabase();


    }

    public void close(){
        dbHelper.close();
    }

    public long addPrsonnel(Personnel personnel){
        ContentValues cv = new ContentValues();
       // cv.put(dbHelper.COLUMN_IDPERS, personnel.getIdPersonel());
        cv.put(dbHelper.COLUMN_NOM,personnel.getNomPersonnel());
        cv.put(dbHelper.COLUMN_PRENOM,personnel.getPrenomPersonnel());
        cv.put(dbHelper.COLUMN_LOGIN,personnel.getLoginPersonnel());
        cv.put(dbHelper.COLUMN_PASSWORD, personnel.getPasswordPersonnel());
        return database.insert(dbHelper.TABLE_PERSONEL,null,cv);
        }

    public void dletePersonnel(Personnel personnel){
        String whereClause =
                dbHelper.COLUMN_IDPERS+"=?";
        String[]whereArgs=new String[]{String.valueOf(personnel.getIdPersonel())};

        database.delete(dbHelper.TABLE_PERSONEL, whereClause, whereArgs);
    }


public List<Personnel> getPersonnels(){
    List<Personnel> personnels= new ArrayList<>();
    Cursor cursor =
            database.query(dbHelper.TABLE_PERSONEL, null,null,null,null,
                    null,dbHelper.COLUMN_IDPERS+" DESC");
    cursor.moveToFirst();
while(!cursor.isAfterLast()){
    Personnel personnel=cursorToPersonnel(cursor);
    personnels.add(personnel);
    cursor.moveToNext();
}
    cursor.close();
    return personnels;
}

    private Personnel cursorToPersonnel(Cursor cursor) {
        Personnel personnel =new Personnel();
        personnel.setIdPersonel(
                cursor.getColumnIndex(dbHelper.COLUMN_IDPERS));
        personnel.setPrenomPersonnel(cursor.getString(
                cursor.getColumnIndex(dbHelper.COLUMN_PRENOM)));
        personnel.setNomPersonnel(cursor.getString(
                cursor.getColumnIndex(dbHelper.COLUMN_NOM)));
        personnel.setLoginPersonnel(cursor.getString(
                cursor.getColumnIndex(dbHelper.COLUMN_LOGIN)));
        personnel.setPasswordPersonnel(cursor.getString(
                cursor.getColumnIndex(dbHelper.COLUMN_PASSWORD)));
        return personnel;

    }

}
