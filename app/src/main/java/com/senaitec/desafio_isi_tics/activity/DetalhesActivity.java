package com.senaitec.desafio_isi_tics.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.senaitec.desafio_isi_tics.R;
import com.senaitec.desafio_isi_tics.adapter.AdapterList;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetalhesActivity extends AppCompatActivity  implements OnMapReadyCallback {

    CircleImageView profileImageView;
    TextView nomePerfil;
    TextView sobrenomePerfil;
    TextView enderecoPerfil;
    TextView emailPerfil;
    TextView cidadePerfil;
    TextView estadoPerfil;
    TextView telefonePerfil;
    TextView dataNasciPerfil;
    private String latitudePerfil;
    private String longitudePerfil;
    private double latitudeDouble;
    private double longitudeDouble;
    GoogleMap googleMap;
    MapView mapView;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);


        CircleImageView profileImageView = (CircleImageView) findViewById(R.id.profileImageView);
        nomePerfil = (TextView) findViewById(R.id.textNome);
        sobrenomePerfil = (TextView) findViewById(R.id.textSobreNome);
        enderecoPerfil = (TextView) findViewById(R.id.textEndereco);
        emailPerfil = (TextView) findViewById(R.id.textEmail);
        cidadePerfil = (TextView) findViewById(R.id.textCidade);
        estadoPerfil = (TextView) findViewById(R.id.textEstado);
        telefonePerfil = (TextView) findViewById(R.id.textTelefone);
        dataNasciPerfil = (TextView) findViewById(R.id.textDataNascimento);


        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = (MapView) findViewById(R.id.myMapa);

        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);










        Intent intent = getIntent();
        final String profileNome = intent.getStringExtra(AdapterList.KEY_NOME);
        String image = intent.getStringExtra(AdapterList.KEY_IMAGEM);
        final String profileSobrenome = intent.getStringExtra(AdapterList.KEY_SOBRENOME);
        final String profileEndereco = intent.getStringExtra(AdapterList.KEY_ENDERECO);
        final String profileEmail = intent.getStringExtra(AdapterList.KEY_EMAIL);
        final String profileCidade = intent.getStringExtra(AdapterList.KEY_CIDADE);
        final String profileEstado = intent.getStringExtra(AdapterList.KEY_ESTADO);
        final String profileTelefone = intent.getStringExtra(AdapterList.KEY_TELEFONE);
        final String profileDataNascimento = intent.getStringExtra(AdapterList.KEY_DATANASC);
        final String profileLatitude = intent.getStringExtra(AdapterList.KEY_LATITUDE);
        final String profileLongitude = intent.getStringExtra(AdapterList.KEY_LONGITUDE);


        Picasso.with(this)
                .load(image)
                .into(profileImageView);

        nomePerfil.setText(profileNome);
        emailPerfil.setText(profileEmail);
        sobrenomePerfil.setText(profileSobrenome);
        enderecoPerfil.setText(profileEndereco);
        cidadePerfil.setText(profileCidade);
        estadoPerfil.setText(profileEstado);
        telefonePerfil.setText(profileTelefone);
        dataNasciPerfil.setText(profileDataNascimento);

        latitudeDouble = Double.parseDouble(profileLatitude);
        longitudeDouble = Double.parseDouble(profileLongitude);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }





    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap = googleMap;
        googleMap.setMinZoomPreference(16);
        LatLng ny = new LatLng(latitudeDouble, longitudeDouble);
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setRotateGesturesEnabled(true);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setTiltGesturesEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny);
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }


}
