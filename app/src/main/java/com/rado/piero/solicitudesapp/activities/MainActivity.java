package com.rado.piero.solicitudesapp.activities;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.rado.piero.solicitudesapp.R;
import com.rado.piero.solicitudesapp.adapters.ServiceAdapter;
import com.rado.piero.solicitudesapp.models.Service;
import com.rado.piero.solicitudesapp.services.ApiService;
import com.rado.piero.solicitudesapp.services.ApiServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView servicesList;


    private Integer id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        servicesList=findViewById(R.id.recyclerview);
        servicesList.setLayoutManager(new LinearLayoutManager(this));

        servicesList.setAdapter(new ServiceAdapter());

        initialize();
    }

    private void initialize() {
        final ApiService service= ApiServiceGenerator.createService(ApiService.class);
        Call<List<Service>> call=service.getservice();
        call.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                try {


                int statuscode=response.code();
                Log.d(TAG, "HTTP status code: " + statuscode);
                if (response.isSuccessful()){
                    List<Service> services=response.body();
                    Log.d(TAG,"solicitudes :" +service);

                    ServiceAdapter adapter=(ServiceAdapter)servicesList.getAdapter();
                    adapter.setServices(services);
                    adapter.notifyDataSetChanged();

                }else {
                    Log.e(TAG, "onError: " + response.errorBody().string());
                    throw new Exception("Error en el servicio");
                }
                }catch (Throwable t){
                    try {


                    Log.e(TAG, "onThrowable: " + t.toString(), t);
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
