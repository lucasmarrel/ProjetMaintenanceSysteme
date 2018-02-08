package cq.qc.cgmatane.informatique.todo.modele;

import java.util.Calendar;
import java.util.HashMap;

public class ListeDesTache  {

    protected int id;
    protected String titre;
    protected String description;
    protected String url;

    protected String heure;
    protected String minute;
    protected String mois;
    protected String jour;


    public ListeDesTache(int id, String titre, String description , String url ,String heure, String mois){
        super();
        this.id = id;
        this.description = description;
        this.titre = titre;
        this.url = url;
        this.heure = heure;
        this.mois = mois;
    }

    public ListeDesTache(String titre, String description ,String url, String jour,String heure, String mois){
        super();
        this.description = description;
        this.titre = titre;
        this.url = url;
        this.heure = heure;
        this.mois = mois;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String  getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }


    public HashMap<String, String> exporterHashMap() {
        HashMap<String, String> tache = new HashMap<String, String>();
        tache.put("id_tache", String.valueOf(this.id));
        tache.put("titre", this.titre);
        tache.put("description", this.description);
        tache.put("URL",this.url);
        tache.put("date",this.mois);


        return tache;
    }
}
