package cq.qc.cgmatane.informatique.todo.donnees;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cq.qc.cgmatane.informatique.todo.modele.ListeDesTache;

import static android.R.attr.description;

public class TacheDAO extends AppCompatActivity {

    private static TacheDAO instance = null;
    private ListeDesTache tache;
    private List<ListeDesTache> listeTache;
    private BaseDeDonnees accesseurBaseDeDonnees;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static  TacheDAO getInstance(){

        if (instance == null){
            instance = new TacheDAO();
        }
        return  instance;
    }

    public TacheDAO() {
        super();

        try {
            accesseurBaseDeDonnees = BaseDeDonnees.getInstance();
            listeTache = new ArrayList<>();

            listeTache = listerTache();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  List<ListeDesTache> listerTache(){

        try {
            String LISTER_TACHES = "SELECT * FROM tache ORDER BY date";
            Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase().rawQuery(LISTER_TACHES,null);

            listeTache.clear();
            int index_id = curseur.getColumnIndex("id_tache");
            int index_titre = curseur.getColumnIndex("titre");
            int index_description = curseur.getColumnIndex("description");
            int index_url = curseur.getColumnIndex("url");
            int index_date = curseur.getColumnIndex("date");

            for (curseur.moveToFirst(); !curseur.isAfterLast();curseur.moveToNext()) {

                int id = curseur.getInt(index_id);
                String titre = curseur.getString(index_titre);
                String description = curseur.getString(index_description);
                String url = curseur.getString(index_url);
                String date = curseur.getString(index_date);


                Calendar calendrier = Calendar.getInstance();
                calendrier.setTime(dateFormat.parse(date));
                Log.d("ERREUR","TEST");
                tache = new ListeDesTache(id,titre, description, url, calendrier);
                Log.d("ERREUR",tache.toString());
                listeTache.add(tache);
            }

        } catch (Exception e) {
            System.out.println("ERREUR : " + e.getMessage());
        }



        return  listeTache;

    }

    public ListeDesTache trouverTache(int id){

        for (ListeDesTache tache : this.listeTache){
            if (tache.getId() == id){
                return  tache;
            }
        }
        return  null;
    }

    public void ajouterTache(ListeDesTache tache){
        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put("titre", tache.getTitre());
            contentValues.put("description", tache.getDescription());
            contentValues.put("url", tache.getUrl());
            contentValues.put("date", dateFormat.format(tache.getDate().getTime()));
            accesseurBaseDeDonnees.getWritableDatabase().insertOrThrow("tache","", contentValues);

        } catch (Exception e) {
            System.out.println("ERREUR : " + e.getMessage());
        }
    }

    public List<HashMap<String,String>> listerTacheEnHashmap(){

        List<ListeDesTache> listeTache = listerTache();

        List<HashMap<String,String>> listeTacheEnHashmap = new ArrayList<>();

        for (ListeDesTache tache : listeTache){
            listeTacheEnHashmap.add(tache.exporterHashMap());
        }

        return  listeTacheEnHashmap;
    }

    public void modifierTache(ListeDesTache tache){
        accesseurBaseDeDonnees.getWritableDatabase().execSQL("UPDATE tache SET titre =\""+ tache.getTitre() +
                "\", description = \""+ tache.getDescription() + "\", url = \"" + tache.getUrl()
                + "\", date = \""+ dateFormat.format(tache.getDate().getTime()) + "\" WHERE id_tache = "+ tache.getId());

    }

    public void supprimerEvenement(int id){
        accesseurBaseDeDonnees.getWritableDatabase().delete("tache","tache = " + id,null);
    }
}