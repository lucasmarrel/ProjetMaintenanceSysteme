package cq.qc.cgmatane.informatique.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.HashMap;
import java.util.List;

import cq.qc.cgmatane.informatique.todo.donnees.BaseDeDonnees;
import cq.qc.cgmatane.informatique.todo.donnees.TacheDAO;


import static cq.qc.cgmatane.informatique.todo.R.id.btnAjouterTache;

public class VueTache extends AppCompatActivity {


    protected TacheDAO accesseurTache = TacheDAO.getInstance();
    protected List<HashMap<String, String>> listeTache;
    protected ListView vueListeTache;
    protected Button AjouterTache;

    static final public int ACTIVITE_AJOUTER_TACHE=1;
    static final public int ACTIVITE_MODIFIER_TACHE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_list_tache);

        AjouterTache = (Button) findViewById(btnAjouterTache);
        AjouterTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentionNaviguerCreerTache = new Intent(VueTache.this, VueCreerTache.class);
                startActivityForResult(intentionNaviguerCreerTache, ACTIVITE_AJOUTER_TACHE);
            }
        });

        vueListeTache = (ListView) findViewById(R.id.listView);
        vueListeTache.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View vue,int positionDansAdapter, long positionItem) {
                ListView vueListeTache = (ListView) vue.getParent();
                HashMap<String, String> tache =(HashMap<String, String>) vueListeTache.getItemAtPosition((int) positionItem);

                Intent intentionNaviguerModifierTache = new Intent(VueTache.this, VueModifierTache.class);
                intentionNaviguerModifierTache.putExtra("id_tache", tache.get("id_tache"));
                startActivityForResult(intentionNaviguerModifierTache, ACTIVITE_MODIFIER_TACHE);
            }

        });
        BaseDeDonnees.getInstance(getApplicationContext());
        accesseurTache = TacheDAO.getInstance();
        afficherTousLesTache();
    }

    public void afficherTousLesTache()
    {
        listeTache = accesseurTache.listerTacheEnHashmap();
        SimpleAdapter adapterVueListeTache = new SimpleAdapter
                (
                        this,
                        listeTache,
                        android.R.layout.two_line_list_item,
                        new String[]{"titre","description"},
                        new int[] {android.R.id.text1,android.R.id.text2}
                );
        vueListeTache.setAdapter(adapterVueListeTache);
    }

    protected void onActivityResult(int activite, int resultat, Intent donnees)
    {
        switch(activite)
        {
            case ACTIVITE_MODIFIER_TACHE:
            afficherTousLesTache();
            break;

            case ACTIVITE_AJOUTER_TACHE:
                afficherTousLesTache();
                break;
        }
    }
}

/*
             CreerTache = (Button) findViewById(btnAjouterTache);
        Intent intentionNaviguerCreerTache = new Intent(VueTache.this, VueCreerTache.class);
        startActivityForResult(intentionNaviguerCreerTache, ACTIVITE_AJOUTER_TACHE);



        AjouterTache = (Button) findViewById(btnAjouterTache);
        AjouterTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentionNaviguerCreerTache = new Intent(VueTache.this, VueCreerTache.class);
                startActivityForResult(intentionNaviguerCreerTache, ACTIVITE_AJOUTER_TACHE);
            }
        });
*/