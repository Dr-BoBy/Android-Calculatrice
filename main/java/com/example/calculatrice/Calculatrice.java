package com.example.calculatrice;

import java.text.DecimalFormat;
import java.util.List;

public class Calculatrice {
    protected String affichage;
    private int courant;
    private float coef;
    private boolean virgule;
    private DecimalFormat format;
    private boolean reset;
    private float memoire;
    private char operation;
    protected String historique;

    public Calculatrice(){
        historique="";
        memoire=0;
        operation='?';
        raz();
        reset= false;
    }

    public void chiffre(int n) {
        if(reset) {
            reset=false;
            raz();
        }
        courant = 10 * courant + n;
        if (virgule) {
            coef /= 10;
        }
        affichage += n;
    }

    public void virgule() {
        if(!virgule) {
            if (reset) {
                reset=false;
                raz();
                affichage += 0;
            }
            virgule = true;
            affichage += ".";
        }
    }

    public void operation(char o) {
        if (!reset) {
            if (operation == '?') {
                memoire = courant * coef;
                affichage=format.format(memoire);
                historique +=format.format(memoire);
            }
            if (operation == '+') {
                memoire += (courant * coef);
                affichage=format.format(memoire);
                historique += "+" + format.format(courant * coef);
            }
            if (operation == '-') {
                memoire -= (courant * coef);
                affichage=format.format(memoire);
                historique += "-" + format.format(courant * coef);
            }
            if (operation == '*') {
                memoire *= (courant * coef);
                affichage=format.format(memoire);
                historique += "*" + format.format(courant * coef);
            }
            if (operation == '/') {

                if (courant * coef == 0) {
                    affichage = "ERREUR";
                    historique+="/0=ERREUR"+"\n";
                    memoire = 0;
                    raz();
                    operation = '?';
                    reset = true;
                    return;

                } else {
                    memoire /= (courant * coef);
                    affichage=format.format(memoire);
                    historique += "/" + format.format(courant * coef);
                }
            }
            reset = true;
            operation = o;

        }
    }

    public void raz(){
        format = new DecimalFormat("#.######");
        affichage="";
        courant=0;
        coef=1;
        virgule=false;
        if(operation=='='){
            operation='?';
            historique+="="+format.format(memoire)+"\n";
        }




    }
}
