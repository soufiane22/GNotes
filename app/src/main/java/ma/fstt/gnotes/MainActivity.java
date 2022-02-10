package ma.fstt.gnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText note_tv;
    String selecteModule;
    Button saisir;
    ListView listView;
    TextView moyenne;
    HashMap<String , Number> item = new HashMap<>();
    ArrayList<String> modules = new ArrayList<>();
    List<Module> listModules  = new ArrayList<Module>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        note_tv = findViewById(R.id.editTextNumberDecimal);
        saisir = findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listview);
        moyenne = (TextView) findViewById(R.id.moyenne);

        createSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecteModule = parent.getItemAtPosition(position).toString();

                Toast.makeText(MainActivity.this, selecteModule, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saisir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(note_tv.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this , "Veuillez saisir la note", Toast.LENGTH_SHORT).show();
                }else{
                    double note = Double.valueOf(note_tv.getText().toString());
                    if(  chekModule(listModules,selecteModule) == false){

                        Toast.makeText(MainActivity.this, "Module déjà saisi!", Toast.LENGTH_SHORT).show();

                    }else{
                        System.out.println("3---ajouter module");
                        Module module = new Module(selecteModule , note);
                        listModules.add(module);
                        ModuleAdapter adapter = new ModuleAdapter(MainActivity.this,R.layout.custom_row , listModules);
                        listView.setAdapter(adapter);
                        note_tv.setText("");
                        if(listModules.size()== modules.size()){
                            getMoyenne(listModules);
                        }

                    }

                }






               // System.out.println("item "+item);


            }
        });
    }

    private void getMoyenne(List<Module> listModules) {
        int nbrModule = listModules.size();
        double somme =0.0;
        double moyenne =0.0;
        for(Module m: listModules){
            somme += m.getNote();
        }
        moyenne = somme/nbrModule;
        moyenne = (double)((int)(moyenne*100))/100;
        this.moyenne.setText(String.valueOf(moyenne));
    }

    private boolean chekModule(List<Module> listModules, String selecteModule) {
        for ( Module m : listModules){
            System.out.println("1---chek module");
            if(m.getLabel().equals(selecteModule)){

                return false;
            }

        }
        return true;

    }

    private void createSpinner(){
        modules.add(0,"Dévloppement Mobile");
        modules.add(1,"Systémes Multi agent");
        modules.add(2,"Cybersécurité");
        modules.add(3,"Iot");
        modules.add(4,"High performance computing ");
        modules.add(5,"Systèmes Distribués");
        modules.add(6,"Traîtement des images");
        modules.add(7,"Gestion des projets");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this , android.R.layout.simple_spinner_dropdown_item, modules);
        spinner.setAdapter(adapter);


    }

    private int getIndex(Spinner myspinner, String category_item) {

        for(int i =0 ; i < myspinner.getCount() ; i++){
            if(myspinner.getItemAtPosition(i).toString().equals(category_item)){
                //System.out.println(" index of category selected  "+i);
                return i;
            }
        }
        return 0;
    }


}