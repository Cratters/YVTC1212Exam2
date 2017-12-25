package demo.com.yvtc1212exam;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng[] taipei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        taipei = new LatLng[Main2Activity.idArray.size()];

        for(int i = 0; i <=Main2Activity.idArray.size()-1; i++){
            taipei[i] = new LatLng(Main2Activity.latitudeArray.get(i), Main2Activity.longitudeArray.get(i));
            mMap.addMarker(new MarkerOptions().position(taipei[i]).title(Main2Activity.stitleArray.get(i)));
        }
//        mMap.addMarker(new MarkerOptions().position(taipei).title("Marker in Taipei").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//        mMap.addMarker(new MarkerOptions().position(taipei1).title("Marker in Taipei").icon(BitmapDescriptorFactory.fromResource(R.drawable.mobs1002)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taipei[0], 15));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
