package com.rado.piero.solicitudesapp.services;

import com.rado.piero.solicitudesapp.models.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


        String API_BASE_URL = "http://192.168.1.7:8089";

        @GET("/solicitudes")
        Call<List<Service>> getservice();



}
