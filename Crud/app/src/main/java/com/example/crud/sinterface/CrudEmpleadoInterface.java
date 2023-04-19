package com.example.crud.sinterface;

import com.example.crud.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CrudEmpleadoInterface {

    @GET("/consultarALL")
    Call<List<Empleado>> getALL();
}