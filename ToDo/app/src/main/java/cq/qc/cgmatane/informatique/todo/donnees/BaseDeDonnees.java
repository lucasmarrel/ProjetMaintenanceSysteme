package cq.qc.cgmatane.informatique.todo.donnees;

/**
 * Created by Gauthier on 19/09/2017.
 */

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static BaseDeDonnees getInstance(Context contexte) {
        if (null == instance) instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance() {
        return instance;
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, "tache", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table tache(id_tache INTEGER PRIMARY KEY, titre TEXT, description TEXT, URL TEXT,  mois DATE, heure TIME)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        String DELETE = "delete from tache where 1 = 1";
        db.execSQL(DELETE);

        String INSERT_1 = "insert into tache(id_tache, titre, description, url, mois, heure) VALUES('1', 'Android pour les nuls', 'apprendre a coder une application Android','https://stackoverflow.com/questions/454315/how-do-you-format-date-and-time-in-android' ,'23:59', 'Septembre', '23')";
        String INSERT_2 = "insert into tache(id_tache, titre, description, url, mois, heure) VALUES('2','Java pour les nuls', 'apprendre a coder en java','https://openclassroom/java.com' ,'23:59', 'Septembre', '23')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);

    }

}
