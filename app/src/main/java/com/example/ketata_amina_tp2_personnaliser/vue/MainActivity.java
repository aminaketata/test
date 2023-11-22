package com.example.ketata_amina_tp2_personnaliser.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ketata_amina_tp2_personnaliser.R;
import com.example.ketata_amina_tp2_personnaliser.controller.Controller;

public class MainActivity extends AppCompatActivity {
    private EditText etValeur;
    private Button bConsulter;
    private TextView tvAge,tvRésultat;
    private RadioButton rbOui,rbNon;
    private SeekBar sbAge;
    private Controller controller=Controller.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //listener SeekBar
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                Log.i("Information", "onProgressChanged " + i);
                tvAge.setText("Votre âge : "+ i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
        bConsulter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int age;
                float valeurMesurer;
                boolean verifAge=false,verifValeur=false;
                if(sbAge.getProgress()!=0)
                    verifAge=true;
                else
                    Toast.makeText(MainActivity.this, "Veillez verifier votre age", Toast.LENGTH_SHORT).show();
                if(!etValeur.getText().toString().isEmpty())
                    verifValeur=true;
                else
                    Toast.makeText(MainActivity.this, "veillez verifier la valeur mesurer", Toast.LENGTH_LONG).show();
                if (verifAge && verifValeur)
                {
                    age = sbAge.getProgress();
                    valeurMesurer = Float.valueOf(etValeur.getText().toString());
                    boolean fasting = rbOui.isChecked();
                    //userAction:view---->Controller
                    controller.createPatient(age,valeurMesurer,fasting);
                    //Update Controller----->View
                    tvRésultat.setText(controller.getResult());
                }
            }
        });
    }
    private void init()
    {
        etValeur=(EditText) findViewById(R.id.etValeur);
        sbAge=(SeekBar) findViewById(R.id.sbage);
        tvAge=(TextView) findViewById(R.id.tvAge);
        rbOui=(RadioButton) findViewById(R.id.rbtOui);
        rbNon=(RadioButton) findViewById(R.id.rbtNon);
        tvRésultat=(TextView) findViewById(R.id.tvRésultat);
        bConsulter=(Button) findViewById(R.id.btnConsulter);
    }

}

