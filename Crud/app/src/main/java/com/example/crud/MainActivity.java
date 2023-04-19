package com.example.crud;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import com.example.crud.model.Empleado;
import com.example.crud.sinterface.CrudEmpleadoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;





public class MainActivity extends AppCompatActivity {
    List<Empleado> listEmpleado;
    CrudEmpleadoInterface cruempleado;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getALL();
    }

    private void getALL(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<List<Empleado>> call = cruempleado.getALL();

        call.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if (!response.isSuccessful()){
                    //System.out.println(response.message());
                    Log.e("Response: err", response.message());
                    return;
                }
                listEmpleado = response.body();
                //listEmpleado.forEach(p -> System.out.println(p.toString()));
                listEmpleado.forEach(p-> Log.i("Empleados:  ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t){

                //System.out.println(t.getMessage());
                Log.e("Throw error:", t.getMessage() );
            }
        });

    }
}