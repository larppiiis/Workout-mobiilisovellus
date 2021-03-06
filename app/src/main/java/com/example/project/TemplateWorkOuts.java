

package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Objects;

/**
 * Activity, jossa nakyy treeniohjelma ja avoimet tekstikentat, joihin kayttaja
 * voi syottaa painojen, toistojen ja sarjojen maarat. Tayttamisen jalkeen kayttaja
 * voi tallentaa suorituksen Profile-activityyn.
 * @author Karoliina Multas
 * @version 0.1
 */

public class TemplateWorkOuts extends AppCompatActivity {


    public HashMap<String, Integer> templateHashmap;
    private TextView exerciseOneTV, exerciseTwoTV, exerciseThreeTV, exerciseFourTV, nameTV;
    private EditText weight1,weight2,weight3,weight4, set1, set2, set3, set4, rep1, rep2, rep3, rep4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_work_outs);


        loadData();
        Button saveButton = findViewById(R.id.saveButton);
        //Kun Save-buttonia painetaan toteutuu saveData-metodi
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });


        //Etsitään treeniohjelman nimen ja yksittäisten liikkeiden TextViewit
        Bundle b = getIntent().getExtras();
        int i = b.getInt(TemplateActivity.EXTRA, 0);
        nameTV = findViewById(R.id.nameTextView);
        exerciseOneTV = findViewById(R.id.exercise1);
        exerciseTwoTV = findViewById(R.id.exercise2);
        exerciseThreeTV = findViewById(R.id.exercise3);
        exerciseFourTV = findViewById(R.id.exercise4);


        //Asetetaan tekstit Singletonin kautta treeniohjelman nimen ja liikkeiden TextVieweihin
        nameTV.setText(TemplateSingleton.getInstance().getTemplate(i).getName());
        exerciseOneTV.setText(TemplateSingleton.getInstance().getTemplate(i).getExercise1());
        exerciseTwoTV.setText(TemplateSingleton.getInstance().getTemplate(i).getExercise2());
        exerciseThreeTV.setText(TemplateSingleton.getInstance().getTemplate(i).getExercise3());
        exerciseFourTV.setText(TemplateSingleton.getInstance().getTemplate(i).getExercise4());

        //Yläpalkin nimi
        Objects.requireNonNull(getSupportActionBar()).setTitle(nameTV.getText());
    }
    /*Metodi, joka luo alert dialog ikkunan, kun painetaan save-nappia. Ikkuna ilmoittaa
      tietojen tallentuneen.*/

    /**
     * Luo alert dialog ikkunan, joka ilmoittaa tietojen tallentuneen.
     */
    //lähde: https://developer.android.com/guide/topics/ui/dialogs
    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
                Toast.makeText(getApplicationContext(),"Workout saved!",
                        Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setTitle("Saved!");
        dialog.show();

    }

    /*Etsitään EditText kentät id:n avulla ja lisätään käyttäjän syöttämät kilot, sarjat,
      ja toistot Hashmapiin*/

    /**
     * tallentaa kayttajan syottomat kilot, sarjat ja toistot hashmapiin
     */
    private void saveData() {



        weight1 = findViewById(R.id.weightEditText);
        weight2 = findViewById(R.id.weightEditText2);
        weight3 = findViewById(R.id.weightEditText3);
        weight4 = findViewById(R.id.weightEditText4);
        set1 = findViewById(R.id.setsEditText);
        set2 = findViewById(R.id.setsEditText2);
        set3 = findViewById(R.id.setsEditText3);
        set4 = findViewById(R.id.setsEditText4);
        rep1 = findViewById(R.id.repsEditText);
        rep2 = findViewById(R.id.repsEditText2);
        rep3 = findViewById(R.id.repsEditText3);
        rep4 = findViewById(R.id.repsEditText4);

        //input validointi, käyttäjä ei voi jättää tyhjää kenttää tai syöttää negatiivisia lukuja
        if (weight1.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(weight1.getText())) < 0) {

            weight1.setError( "Not valid weight!" );
        }
        if (weight2.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(weight2.getText())) < 0) {

            weight2.setError( "Not valid weight!" );
        }
        if (weight3.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(weight3.getText())) < 0) {

            weight3.setError( "Not valid weight!" );
        }
        if (weight4.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(weight4.getText())) < 0) {

            weight4.setError( "Not valid weight!" );
        }
        if (rep1.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(rep1.getText())) < 0) {

            rep1.setError( "Not valid rep!" );
        }
        if (rep2.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(rep2.getText())) < 0) {

            rep2.setError( "Not valid rep!" );
        }
        if (rep3.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(rep3.getText())) < 0) {

            rep3.setError( "Not valid rep!" );
        }
        if (rep4.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(rep4.getText())) < 0) {

            rep4.setError( "Not valid rep!" );
        }
        if (set1.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(set1.getText())) < 0) {

            set1.setError( "Not valid set!" );
        }
        if (set2.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(set2.getText())) < 0) {

            set2.setError( "Not valid set!" );
        }
        if (set3.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(set3.getText())) < 0) {

            set3.setError( "Not valid set!" );
        }
        if (set4.getText().toString().trim().equals("") || Integer.parseInt(String.valueOf(set4.getText())) < 0) {

            set4.setError( "Not valid set!" );
        } else {
            //lisätään käyttäjän syöttämät arvot hashmapiin
            templateHashmap.put("weight1", Integer.parseInt(weight1.getText().toString()));
            templateHashmap.put("weight2", Integer.parseInt(weight2.getText().toString()));
            templateHashmap.put("weight3", Integer.parseInt(weight3.getText().toString()));
            templateHashmap.put("weight4", Integer.parseInt(weight4.getText().toString()));
            templateHashmap.put("set1", Integer.parseInt(set1.getText().toString()));
            templateHashmap.put("set2", Integer.parseInt(set2.getText().toString()));
            templateHashmap.put("set3", Integer.parseInt(set3.getText().toString()));
            templateHashmap.put("set4", Integer.parseInt(set4.getText().toString()));
            templateHashmap.put("rep1", Integer.parseInt(rep1.getText().toString()));
            templateHashmap.put("rep2", Integer.parseInt(rep2.getText().toString()));
            templateHashmap.put("rep3", Integer.parseInt(rep3.getText().toString()));
            templateHashmap.put("rep4", Integer.parseInt(rep4.getText().toString()));
            alertDialog();
        }

    }

    //Tallennetaan Hashmap Sharedpreferenceihin onPausessa kääntämällä se Jsoniksi

    /**
     * onPausessa tallennetaan hashmap shared preferenceihin
     */
    @Override
    public void onPause () {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("workouts", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson Gson = new Gson();
        String json = Gson.toJson(templateHashmap);
        editor.putString("saving templates", json);
        editor.commit();

    }

    /**
     * loadData metodi hakee datan shared preferenceista
     */
    //Datan deserialisointi, eli muutetaan json takaisin
    private void loadData() {
        //Weights
        SharedPreferences prefs = getSharedPreferences("workouts", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("saving templates", null);
        Type type = new TypeToken<HashMap<String, Integer>>() {}.getType();
        templateHashmap = gson.fromJson(json, type);
        //Jos lista on tyhjä, luodaan uusi tyhjä lista.
        if (templateHashmap == null){
            templateHashmap = new HashMap<>();
        }

    }
}
