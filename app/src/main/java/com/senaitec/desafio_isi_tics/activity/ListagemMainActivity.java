package com.senaitec.desafio_isi_tics.activity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.senaitec.desafio_isi_tics.R;
import com.senaitec.desafio_isi_tics.adapter.AdapterList;
import com.senaitec.desafio_isi_tics.model.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ListagemMainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewdados;
    private RecyclerView.Adapter adapter;
    private SearchView searchViewDados;
    private List<Usuario> listaUsuario ;
    private String nomePesq;
    private String emailPesq;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_main);

        ListagemMainActivity.MyTask task = new ListagemMainActivity.MyTask();
        String urlAPI = "https://randomuser.me/api/?nat=BR&results=25";
        task.execute(urlAPI);

        recyclerViewdados = (RecyclerView) findViewById(R.id.recyclerLista);
        searchViewDados = (SearchView) findViewById(R.id.searchLista);


        searchViewDados.setQueryHint("Buscar Usuário");


        // Seria a pesquisa, no entanto, não deu tempo
        searchViewDados.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }



            @Override
            public boolean onQueryTextChange(String s) {

                return true;
            }
        });

        recyclerViewdados.setLayoutManager(new LinearLayoutManager(this));
        listaUsuario = new ArrayList<>();



    }


    class MyTask extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream = null;
            InputStreamReader inputSteamReader = null;
            StringBuffer buffer = null;

            try {
                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                //recupera os dados em bytes
                inputStream = conexao.getInputStream();

                inputSteamReader = new InputStreamReader(inputStream);

                BufferedReader reader = new BufferedReader(inputSteamReader);
                buffer = new StringBuffer();

                String linha = "";
                // Ler linha por linha
                while ((linha = reader.readLine()) != null) {
                    buffer.append(linha);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();


        }
        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);



            String records = null;
            String email = null;
            String imagemReal = null;
            String primeironome = null;
            String enderecoReal = null;
            String cidadeReal = null;
            String estadoReal = null;
            String dataNascimentoReal = null;
            String telefoneReal = null;
            String sobrenomeReal = null;
            String latitudeReal = null;
            String longitudeReal = null;


            try {
                JSONObject jsonObject = new JSONObject(resultado);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject j = jsonArray.getJSONObject(i);
                    JSONObject nome = j.getJSONObject("name");
                    primeironome = nome.getString("first");
                    sobrenomeReal = nome.getString("last");
                    JSONObject imagem = j.getJSONObject("picture");
                    imagemReal = imagem.getString("large");
                    JSONObject end = j.getJSONObject("location");
                    enderecoReal = end.getString("street");
                    cidadeReal = end.getString("city");
                    estadoReal = end.getString("state");
                    telefoneReal = j.getString("phone");
                    JSONObject data = j.getJSONObject("dob");
                    JSONObject coord = end.getJSONObject("coordinates");
                    latitudeReal = coord.getString("latitude");
                    longitudeReal = coord.getString("longitude");
                    dataNascimentoReal = data.getString("date");
                    email = j.getString("email");
                    listaUsuario.add(new Usuario(primeironome, sobrenomeReal,  imagemReal, email, enderecoReal, cidadeReal,
                            estadoReal, dataNascimentoReal,telefoneReal, latitudeReal, longitudeReal));
                }

                adapter = new AdapterList(listaUsuario, getApplicationContext());
                recyclerViewdados.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }



}
