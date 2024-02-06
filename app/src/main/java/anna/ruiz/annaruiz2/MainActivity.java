package anna.ruiz.annaruiz2;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

import anna.ruiz.annaruiz2.Adapters.DeudaAdapter;
import anna.ruiz.annaruiz2.Constantes.Constantes;
import anna.ruiz.annaruiz2.Modelos.Deuda;
import anna.ruiz.annaruiz2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DeudaAdapter adapter;
    private RecyclerView.LayoutManager lm;
    private ArrayList<Deuda> listaDeudas;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaDeudas = new ArrayList<>();

        //inicializar bd
        database = FirebaseDatabase.getInstance("https://annaruiz2-default-rtdb.europe-west1.firebasedatabase.app/");
        sp = getSharedPreferences(Constantes.DATOS, MODE_PRIVATE);


        //inicializar recycler
        adapter = new DeudaAdapter(this, R.layout.deuda_view_holder, listaDeudas);
        lm = new LinearLayoutManager(this);

        binding.contenedor.contenedorRecycler.setAdapter(adapter);
        binding.contenedor.contenedorRecycler.setLayoutManager(lm);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDeuda().show();

            }
        });
    }

    private AlertDialog addDeuda(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Añadir Deuda");
        builder.setCancelable(false);

        View deudaView = LayoutInflater.from(this).inflate(R.layout.deuda_view_model, null);
        EditText lbNombre = deudaView.findViewById(R.id.lbNombreAddDeuda);
        EditText lbCantidad = deudaView.findViewById(R.id.lbCantidadAddDeuda);
        EditText lbMotivo = deudaView.findViewById(R.id.lbMotivoAddDeuda);
        builder.setView(deudaView);

        builder.setNegativeButton("Cancelar", null);
        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!lbNombre.getText().toString().isEmpty() && !lbCantidad.getText().toString().isEmpty() && !lbMotivo.getText().toString().isEmpty()){

                    reference = database.getReference(Constantes.FIREBASE);

                    //Prueba a recoger datos de la bd
                    reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()){
                                GenericTypeIndicator<ArrayList<Deuda>> gti = new GenericTypeIndicator<ArrayList<Deuda>>(){};

                                ArrayList<Deuda> temp = task.getResult().getValue(gti);

                                if (temp != null){
                                    listaDeudas.clear();
                                    listaDeudas.addAll(temp);
                                }

                                Deuda d = new Deuda(lbNombre.getText().toString(), Float.parseFloat(lbCantidad.getText().toString()), lbMotivo.getText().toString());


                                listaDeudas.add(d);
                                Toast.makeText(MainActivity.this, "Deuda insertada", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                 /*   //prueba a añadir a la lista para ver en el recycler....
                    Deuda d = new Deuda(lbNombre.getText().toString(), Float.parseFloat(lbCantidad.getText().toString()), lbMotivo.getText().toString());
                    listaDeudas.add(d);
                    adapter.notifyDataSetChanged();*/
                }else {
                    Toast.makeText(MainActivity.this, "Hay datos por rellenar", Toast.LENGTH_SHORT).show();
                }

            }
        });



        return builder.create();
    }

}