package cq.qc.cgmatane.informatique.todo.modele;

import java.util.Calendar;
import java.util.HashMap;

public class ListeDesTache  {

    protected int id;
    protected String titre;
    protected String description;
    protected String url;

    protected String heure;
    protected Calendar date = Calendar.getInstance();


    public ListeDesTache(int id, String titre, String description , String url, String date, String heure){
        super();
        this.description = description;
        this.titre = titre;
        this.url = url;
        this.date = ModeleDate.construireDate(date,heure);
    }

    public ListeDesTache(String titre, String description ,String url, String date, String heure){
        super();
        this.description = description;
        this.titre = titre;
        this.url = url;
        this.date = ModeleDate.construireDate(date,heure);
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(int jour, int mois, int annee, int heure, int minute) {
        this.date.set(annee,mois-1,jour,heure,minute,00);
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getJour(){
        return date.get(Calendar.DATE);
    }

    public int getMois(){
        return date.get(Calendar.MONTH)+1;
    }

    public int getAnnee(){
        return date.get(Calendar.YEAR);
    }

    public int getHeure(){
        return date.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinutes(){
        return date.get(Calendar.MINUTE);
    }


    public HashMap<String, String> exporterHashMap() {
        HashMap<String, String> tache = new HashMap<String, String>();
        tache.put("id_tache", String.valueOf(this.id));
        tache.put("titre", this.titre);
        tache.put("description", this.description);
        tache.put("URL",this.url);
        tache.put("date",ModeleDate.dateFrancaise(this.getDate()));


        return tache;
    }

    @Override
    public String toString() {
        return "ListeDesTache{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", date=" + ModeleDate.dateFrancaise(this.getDate()) +
                '}';
    }
}
