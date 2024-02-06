package anna.ruiz.annaruiz2;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import anna.ruiz.annaruiz2.Adapters.DeudaAdapter;
import anna.ruiz.annaruiz2.Modelos.Deuda;
import anna.ruiz.annaruiz2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DeudaAdapter adapter;
    private RecyclerView.LayoutManager lm;
    private ArrayList<Deuda> listaDeudas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaDeudas = new ArrayList<>();

        adapter = new DeudaAdapter(this, R.layout.deuda_view_holder, listaDeudas);
        lm = new LinearLayoutManager(this);

        binding.contenedor.contenedorRecycler.setAdapter(adapter);
        binding.contenedor.contenedorRecycler.setLayoutManager(lm);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}