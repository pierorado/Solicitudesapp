package com.rado.piero.solicitudesapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rado.piero.solicitudesapp.R;
import com.rado.piero.solicitudesapp.models.Service;
import com.rado.piero.solicitudesapp.services.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private List<Service>services;

    public ServiceAdapter(){
        this.services=new ArrayList<>();
    }
    public void setServices(List<Service>services){
        this.services=services;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoimage;
        public TextView tipotext;
        public TextView motivotext;
        public TextView correotext;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoimage= itemView.findViewById(R.id.foto_image);
            tipotext= itemView.findViewById(R.id.tipo_text);
            motivotext = itemView.findViewById(R.id.motivo_text);
            correotext=itemView.findViewById(R.id.correo_text);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Service service=this.services.get(position);
        viewHolder.correotext.setText("De"+service.getEmail());
        viewHolder.tipotext.setText(service.getTipo());
        viewHolder.motivotext.setText(service.getMotivo());

        String url= ApiService.API_BASE_URL+"/solicitudes/images/"+service.getImagen();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoimage);

    }

    @Override
    public int getItemCount() {
        return this.services.size();
    }
}
