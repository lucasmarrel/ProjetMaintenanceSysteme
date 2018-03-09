package cq.qc.cgmatane.informatique.todo.donnees;

/**
 * Created by Gauthier on 19/09/2017.
 */

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static BaseDeDonnees getInstance(Context contexte)
    {

        if(null == instance) instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance()
    {
        return instance;
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, "tache", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table tache(id_tache INTEGER PRIMARY KEY AUTOINCREMENT, titre TEXT, description TEXT, url TEXT, date DATETIME)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
//        String DETRUIRE_TABLE = "drop table tache";
//        db.execSQL(DETRUIRE_TABLE);
//        String CREATE_TABLE = "create table tache(id_tache INTEGER PRIMARY KEY AUTOINCREMENT, titre TEXT, description TEXT, url TEXT, date DATETIME)";
//        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        try{
//            String INSERT_1 = "insert into tache(titre, description, url, date) VALUES(\"Laver le linge\",\"Utiliser le savon bio\",\"www.laversonlinge.fr\",\"2018-12-26 14:30:00\")";
//            String INSERT_2 = "insert into tache(titre, description, url, date) VALUES(\"Inscription site ecole\",\"A faire rapidement\",\"www.ecoledu69.fr\",\"2016-09-07 20:45:00\")";
//
//            db.execSQL(INSERT_1);
//            db.execSQL(INSERT_2);

        }catch (Exception ex){
            Log.d("APPERROR",ex.getMessage());
        }

    }

}
