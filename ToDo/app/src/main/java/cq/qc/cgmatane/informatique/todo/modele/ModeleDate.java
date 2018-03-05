package cq.qc.cgmatane.informatique.todo.modele;

/**
 * Created by 1743002 on 2018-02-22.
 */

import java.util.Calendar;

/**
 * Created by 1743002 on 2017-09-14.
 */

public class ModeleDate {

    public static String dateFrancaise(Calendar date){

        String dateConvertie = String.valueOf(date.get(Calendar.DATE)) + " " + trouverMois(date.get(Calendar.MONTH)+1)
                + " " + String.valueOf(date.get(Calendar.YEAR)) + " " + String.format("%02d",date.get(Calendar.HOUR_OF_DAY))
                + ":" + String.format("%02d",date.get(Calendar.MINUTE));

        return dateConvertie;
    }

    public static String trouverMois(int numMois){

        String mois = "";

        switch (numMois){
            case 1 :
                mois = "Janvier";
                break;
            case 2 :
                mois = "Février";
                break;
            case 3 :
                mois = "Mars";
                break;
            case 4 :
                mois = "Avril";
                break;
            case 5 :
                mois = "Mai";
                break;
            case 6 :
                mois = "Juin";
                break;
            case 7 :
                mois = "Juillet";
                break;
            case 8 :
                mois = "Août";
                break;
            case 9 :
                mois = "Septembre";
                break;
            case 10 :
                mois = "Octobre";
                break;
            case 11 :
                mois = "Novembre";
                break;
            case 12 :
                mois = "Décembre";
                break;
        }

        return  mois;
    }

    public static Calendar construireDate(String date, String heure){

        String[] separateurDate = date.split("/");
        String[] separateurHeure = heure.split(":");

        int annee = Integer.parseInt(separateurDate[0]);
        int mois = Integer.parseInt(separateurDate[1])-1;
        int jour = Integer.parseInt(separateurDate[2]);

        int heureSeparee = Integer.parseInt(separateurHeure[0]);
        int minuteSeparee = Integer.parseInt(separateurHeure[1]);

        Calendar dateFinale = Calendar.getInstance();

        dateFinale.set(annee,mois,jour,heureSeparee,minuteSeparee,00);

        return dateFinale;

    }
}
