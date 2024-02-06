package anna.ruiz.annaruiz2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import anna.ruiz.annaruiz2.Modelos.Deuda;
import anna.ruiz.annaruiz2.R;

public class DeudaAdapter extends RecyclerView.Adapter<DeudaAdapter.DeudaVH> {
    private Context context;
    private int resource;
    private List<Deuda> objects;

    public DeudaAdapter(Context context, int resource, List<Deuda> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public DeudaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View deudaView = LayoutInflater.from(context).inflate(resource,null);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        deudaView.setLayoutParams(lp);

        return new DeudaVH(deudaView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeudaVH holder, int position) {
        Deuda d = objects.get(position);
        holder.lbNombre.setText(d.getNombre());
        holder.lbCantidad.setText((int)d.getCantidad());
        holder.lbMotivo.setText(d.getMotivo());

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class DeudaVH extends RecyclerView.ViewHolder{
        private TextView lbNombre, lbCantidad, lbMotivo;

        public DeudaVH(@NonNull View itemView) {
            super(itemView);
            lbNombre = itemView.findViewById(R.id.lbNombreDeudaViewHolder);
            lbCantidad = itemView.findViewById(R.id.lbCantidadDeudaViewHolder);
            lbMotivo = itemView.findViewById(R.id.lbMotivoDeudaViewHolder);

        }
    }
}
