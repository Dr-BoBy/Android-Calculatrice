package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static Calculatrice calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(calc==null){
            this.calc=new Calculatrice();
        }

    }

    public void saisir(View vue){
        Button button = (Button) vue;
        char text = button.getText().charAt(0);
        if(text>='0' && text<='9'){calc.chiffre(text-'0');}
        if(text>=42 && text<=47 || text == 61){
            if(text == 46){calc.virgule();}
            else{calc.operation(text);}
        }
        TextView zoneTexte = findViewById(R.id.affichage);
        zoneTexte.setText(calc.affichage);
        TextView zoneTexteHistorique = findViewById(R.id.historique);
        if(zoneTexteHistorique!=null){
            zoneTexteHistorique.setText(calc.historique);
        }
    }
}