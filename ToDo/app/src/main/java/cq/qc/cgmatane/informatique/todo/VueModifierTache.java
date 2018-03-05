package cq.qc.cgmatane.informatique.todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cq.qc.cgmatane.informatique.todo.donnees.TacheDAO;
import cq.qc.cgmatane.informatique.todo.modele.ListeDesTache;

public class VueModifierTache extends AppCompatActivity {

    protected TacheDAO accesseurTache = TacheDAO.getInstance();
    protected  ListeDesTache tache;
    protected  EditText champDescription;
    protected  EditText champTitre;
    protected  EditText champUrl;
    protected  EditText champHeure;
    protected  EditText champMois;
  //  protected  EditText champJour;
    protected  Button   valider;       //Declaration des button
    protected  Button   annuler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_tache);

        champTitre = (EditText) findViewById(R.id.champTitreModifierTache);
        champDescription = (EditText) findViewById(R.id.champDescriptionModifierTache);
        champUrl = (EditText) findViewById(R.id.champUrlModifierTache);
        champHeure= (EditText) findViewById(R.id.champHeureModifierTache);
        champMois = (EditText) findViewById(R.id.champMoisCreerTache);
     //   champJour = (EditText) findViewById(R.id.champJourCreerTache);
        valider = (Button) findViewById(R.id.btnModifierTache);
        annuler = (Button) findViewById(R.id.btnAnnulezTache);

        Bundle parametre = this.getIntent().getExtras();
        int id_tache = Integer.parseInt((String) parametre.get("id_tache"));
        tache = accesseurTache.trouverTache(id_tache);

        champTitre.setText(tache.getTitre());
        champDescription.setText(tache.getDescription());
        champUrl.setText(tache.getUrl());
        champMois.setText(tache.getAnnee()+"/"+tache.getMois()+"/"+tache.getJour());
        champHeure.setText(tache.getHeure()+":"+tache.getMinutes());

      valider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                modifierTache();
                naviguerRetourTache();
            }
      });

       annuler.setOnClickListener(new View.OnClickListener() { // fait la fonction modifier dans le boutton annuler
            @Override
            public void onClick(View v) {
                naviguerRetourTache();
            }
       });

    }

    public void modifierTache(){
        String titre = champTitre.getText().toString();
        String description = champDescription.getText().toString();
        String url = champUrl.getText().toString();
        String heure = champHeure.getText().toString();
        String date = champMois.getText().toString();

        tache = new ListeDesTache(tache.getId(),titre,description,url,date,heure);

        accesseurTache.modifierTache(tache);

    }

    public void naviguerRetourTache() {
        this.finish();
    }

}


/*
  String parametre_id_tache = (String) parametres.get("id_tache");
        int id_tache = Integer.parseInt(parametre_id_tache);

        Toast test = Toast.makeText(getApplicationContext(),
                "Valeur recue " + id_tache,
                Toast.LENGTH_LONG);
        test.show();

        ListeToDo tache = accesseurTache.trouverTache(id_tache);

 public void modifierTache() {
        tache.setTitre(champTitre.getText().toString());
        tache.setDescription(champDescription.getText().toString());
        accesseurTache.modifierTache(tache);
        naviguerRetourTache();
    }

 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_creer_to_do);

        Button next = (Button) findViewById(R.id.btnCreerTache);
        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View  view){
                Intent myIntent = new Intent(view.getContext(), VueToDo.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

        Button next = (Button) findViewById(R.id.btnModifierTache);
        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View  view){
                Intent myIntent = new Intent(view.getContext(), VueToDo.class);
                startActivityForResult(myIntent, 0);

            }
        });

    final String TACHE_TITRE = "champTitreCreerTache";
    final String TACHE_DESCRIPTION = "champDescriptionCreerTache";

    final EditText Titre = (EditText) findViewById(R.id.champTitreCreerTache);
    final EditText Description = (EditText) findViewById(R.id.champDescriptionCreerTache);
    final Button ButtonCreer = (Button) findViewById(R.id.btnCreerTache);

    ButtonCreer.setOnClickListener(new View.OnClickListener()
    {
        public void onClick(View v) {
        Intent intent = new Intent(VueCreerToDo.this, VueListToDo.class);
        intent.putExtra(TACHE_TITRE, Titre.getText().toString());
        intent.putExtra(TACHE_DESCRIPTION, Description.getText().toString());

        startActivity(intent);
    }
    });


    */
