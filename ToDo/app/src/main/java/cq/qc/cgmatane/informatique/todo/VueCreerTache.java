package cq.qc.cgmatane.informatique.todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cq.qc.cgmatane.informatique.todo.donnees.TacheDAO;
import cq.qc.cgmatane.informatique.todo.modele.ListeDesTache;

public class VueCreerTache extends AppCompatActivity {


    protected TacheDAO accesseurTache = TacheDAO.getInstance();
    protected ListeDesTache tache;
    protected EditText champDescription;
    protected EditText champTitre;
    protected EditText champUrl;
    protected EditText champHeure;
    protected EditText champMois;
    protected EditText champJour;
    protected Button   valider;       //Declaration des button
    protected Button   annuler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_creer_tache);

        champTitre = (EditText) findViewById(R.id.champTitreCreerTache);
        champDescription = (EditText) findViewById(R.id.champDescriptionCreerTache);
        champUrl = (EditText) findViewById(R.id.champUrlCreerTache);
        champHeure= (EditText) findViewById(R.id.champHeureCreerTache);
        champMois = (EditText) findViewById(R.id.champMoisCreerTache);
        valider = (Button) findViewById(R.id.btnCreerTache);
        annuler = (Button) findViewById(R.id.btnAnnulezTache);

        valider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ajouterTache();
                naviguerRetourTache();
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naviguerRetourTache();
            }
        });
    }

    public void ajouterTache(){
        String titre = champTitre.getText().toString();
        String description = champDescription.getText().toString();
        String url = champUrl.getText().toString();
        String heure = champHeure.getText().toString();
        String mois = champMois.getText().toString();
        String jour = champJour.getText().toString();

        /*tache.setTitre(titre);
        tache.setDescription(description);
        tache.setUrl(url);
        tache.setJour(jour);
        tache.setMois(mois);
        tache.setHeure(heure);*/

        tache = new ListeDesTache(titre,description,url,heure,mois,jour);
        accesseurTache.ajouterTache(tache);

    }

    public void naviguerRetourTache() {
        this.finish();
    }

}