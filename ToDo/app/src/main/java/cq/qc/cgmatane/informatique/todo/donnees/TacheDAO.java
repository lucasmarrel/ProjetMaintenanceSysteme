package cq.qc.cgmatane.informatique.todo.donnees;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cq.qc.cgmatane.informatique.todo.modele.ListeDesTache;

import static android.R.attr.description;

public class TacheDAO extends AppCompatActivity {

   private BaseDeDonnees accesseurBaseDeDonnees;

    private TacheDAO()
    {
        super();

       // Log.d("DAO", "instance bdd : " + this.accesseurBaseDeDonnees);
        accesseurBaseDeDonnees = BaseDeDonnees.getInstance();
        listeTache = new ArrayList<ListeDesTache>();
        listeTache = listerTache();

        /*
        ListeDesTache tache;
        tache = new ListeDesTache(33, "Android pour les nuls", "apprendre a coder une application Android","https://stackoverflow.com/questions/454315/how-do-you-format-date-and-time-in-android" ,"23:59", "Septembre", "23");
        listeTache.add(tache);
        tache = new ListeDesTache(34, "Java pour les nuls", "apprendre a coder en java","https://openclassroom/java.com" ,"23:59", "Septembre", "23");
        listeTache.add(tache);
        */

    }



    public  List<ListeDesTache> listerTache(){

        ListeDesTache tache;
        try {

            String LISTER_TACHE = "SELECT * FROM tache";
            Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase().rawQuery(LISTER_TACHE,null);

            listeTache.clear();
            int index_id = curseur.getColumnIndex("id_tache");
            int index_titre = curseur.getColumnIndex("titre");
            int index_description = curseur.getColumnIndex("description");
            int index_url = curseur.getColumnIndex("url");
            int index_mois = curseur.getColumnIndex("mois");
            int index_heure = curseur.getColumnIndex("heure");

            for (curseur.moveToFirst(); !curseur.isAfterLast();curseur.moveToNext()) {

                int id = curseur.getInt(index_id);
                String titre = curseur.getString(index_titre);
                String description = curseur.getString(index_description);
                String url = curseur.getString(index_url);
                String mois = curseur.getString(index_mois);
                String heure = curseur.getString(index_heure);


                tache = new ListeDesTache(titre,description,url,heure,mois);


                listeTache.add(tache);
            }

        } catch (Exception e) {
            System.out.println("ERREUR : " + e.getMessage());
        }



        return  listeTache;

    }


    // patron Singleton
    private  static  TacheDAO instance = null;

    public static TacheDAO getInstance() {
        if(null == instance){
            instance = new TacheDAO();
        }
        return instance;
    }
    // fin singleton

    List<ListeDesTache> listeTache;

    public ListeDesTache trouverTache(int id)
    {
        for (ListeDesTache tache: this.listeTache)
        {
            if(tache.getId() == id)
                return tache;
        }
        return null;
    }


    public List<HashMap<String,String>> listerTacheEnHashmap()
    {
       // this.listerLestaches();
        List<HashMap<String,String>> listerTacheEnHashmap =
                new ArrayList<HashMap<String,String >>();

        for(ListeDesTache tache : this.listeTache){
            listerTacheEnHashmap.add(tache.exporterHashMap());
        }
        return listerTacheEnHashmap;
    }


    public void modifierTache(ListeDesTache tache)
    {
        for (ListeDesTache tacheTeste : this.listeTache)
        {
            if (tacheTeste.getId() == tache.getId())
            {
                tacheTeste.setDescription(tache.getDescription());
                tacheTeste.setTitre(tache.getTitre());
                tacheTeste.setUrl(tache.getUrl());
                tacheTeste.setDate(tache.getDate());
                return;
            }
        }
    }

    public void ajouterTache (ListeDesTache tache){
        listeTache.add(tache);
    }

}