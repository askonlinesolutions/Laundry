package com.laundry.ui.currentLocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.laundry.R;
import com.laundry.Utils.Utility;
import com.laundry.databinding.ActivityAddAddressBinding;
import com.laundry.ui.AddNewAddress.AddNewAddressActivity;
import com.laundry.ui.Pick_up.PickupActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AddAddressActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    boolean isVisible = true;
    ActivityAddAddressBinding binding;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static String TAG = "MAP LOCATION";
    Context mContext;
    private LatLng mCenterLatLong;
    double latitute, longitute;
    private MapView mMapView;
    //    private ApiResponse.UserDataBean userDataBean = new ApiResponse.UserDataBean();
    private TextView txtPlaceName, edtHouse, txtConfirm, edtLandmark;
    //    private EditText/* edtHouse,*/ /*edtLandmark*/;
//    TinyDB  tinyDB ;
    //  private AutoCompleteTextView txtLocatName;
    String title, foodie_id, address;
    private Gson gson = new Gson();
    private String name = "", address_id;
    private boolean isEdit;
    private static int REQUEST_CODE_AUTOCOMPLETE = 10;
    AutoCompleteTextView autocompleteView;
    String editKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_address);
        mContext = this;
        getPosition();
        init(savedInstanceState);

    }

    private void getPosition() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editKey = extras.getString("NewAddress");
//            serviseList=getIntent().getSerializableExtra("arraylist");
        }

    }

    private void init(Bundle savedInstanceState) {

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(AddAddressActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_AUTOCOMPLETE);
            return;
        }
        mMapView = (MapView) findViewById(R.id.mapView);
        edtHouse = findViewById(R.id.add_address_edtHouse);
        edtLandmark = findViewById(R.id.add_address_edtLandMark);
        //  txtLocatName = findViewById(R.id.activity_add_aaddres_txtLocaname);
        autocompleteView = (AutoCompleteTextView) findViewById(R.id.autocomplete);

        txtPlaceName = findViewById(R.id.activity_add_aaddres_txtLoc);
        txtConfirm = findViewById(R.id.add_address_btnConfirm);
        if (getIntent().hasExtra("address_id")) {
            address_id = getIntent().getStringExtra("address_id");
            name = getIntent().getStringExtra("name");
            txtPlaceName.setText(name);
            autocompleteView.setText(name);
            isEdit = getIntent().getBooleanExtra("edit", false);
            title = getIntent().getStringExtra("title");
        }

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        View locationButton = ((View) mMapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
// position on right bottom
//        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        mMapView.getMapAsync(this);
        if (checkPlayServices()) {
            // If this check succeeds, proceed with normal processing.
            // Otherwise, prompt user to get valid Play Services APK.
            if (!Utility.isLocationEnabled(mContext)) {
                // notify user
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setMessage("Location not enabled!");
                dialog.setPositiveButton("Open location settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        // TODO Auto-generated method stub
                    }
                });
                dialog.show();
            }
            buildGoogleApiClient();
        } else {
            Toast.makeText(mContext, "Location not supported in this device", Toast.LENGTH_SHORT).show();
        }

        binding.mapBackIv.setOnClickListener(this);
        binding.autocomplete.setOnClickListener(this);
        binding.confirmBtn.setOnClickListener(this);

    }


    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, AddAddressActivity.this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                //finish();
            }
            return false;
        }
        return true;

    }


    private void changeMap(Location location) {

        Log.d(TAG, "Reaching map" + mMap);

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // check if map is created successfully or not
        if (mMap != null) {
            mMap.getUiSettings().setZoomControlsEnabled(false);
            LatLng latLong;
            latLong = new LatLng(location.getLatitude(), location.getLongitude());
           /* mMap.addMarker(new MarkerOptions().position(latLong)
                    .icon(BaseClass.bitmapDescriptorFromVectorR(getApplicationContext())));*/
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLong).zoom(19f).build();
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mGoogleApiClient.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public StringBuffer getAddress(LatLng latLng) throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        StringBuffer result = new StringBuffer();
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();


//            binding.topLocationEt.setText(address);
            binding.locationTv.setText(address);
            binding.houseNoEt.setText(city);
            binding.landmarkEt.setText(country);


            result.append(address);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void onStop() {
        super.onStop();
        try {

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);


        if (mLastLocation != null) {
            changeMap(mLastLocation);

            Log.d(TAG, "ON connected");

        } else
            try {
                LocationServices.FusedLocationApi.removeLocationUpdates(
                        mGoogleApiClient, this);

            } catch (Exception e) {
                e.printStackTrace();
            }
        try {
            @SuppressLint("RestrictedApi")
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(10000);
            mLocationRequest.setFastestInterval(5000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            if (location != null)
                changeMap(location);
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "OnMapReady");
        mMap = googleMap;
//        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(false);

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Log.d("Camera posItion change" + "", cameraPosition + "");
                mCenterLatLong = cameraPosition.target;
                //   mMap.clear();

                try {
                    Location mLocation = new Location("");
                    mLocation.setLatitude(mCenterLatLong.latitude);
                    mLocation.setLongitude(mCenterLatLong.longitude);
                    StringBuffer stringBuffer = new StringBuffer();
                    try {
                        stringBuffer = getAddress(new LatLng(mCenterLatLong.latitude, mCenterLatLong.longitude));
                        if (isEdit) {
                            txtPlaceName.setText(name);
                            autocompleteView.setText(name);
//                            getLatLong(name);
                            isEdit = false;
                        } else {
                            txtPlaceName.setText(stringBuffer);
                            autocompleteView.setText(stringBuffer);
                        }


                        latitute = mCenterLatLong.latitude;
                        longitute =  mCenterLatLong.longitude;
//

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.autocomplete:
              /*  if (isVisible) {
                    binding.cardLoocation.setVisibility(View.GONE);
                    isVisible = false;
                } else {
                    binding.cardLoocation.setVisibility(View.VISIBLE);
                    isVisible = true;
                }*/

              startActivity(new Intent(AddAddressActivity.this,SearchLocationActivity.class));
                break;
            case R.id.map_back_iv:
                onBackPressed();
                break;
            case R.id.confirm_btn:

                if (editKey != null && editKey.equals("NewAddress")) {
                    Intent intent = new Intent(AddAddressActivity.this, AddNewAddressActivity.class);
                    intent.putExtra("longitute", longitute);
                    intent.putExtra("latitute", latitute);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(AddAddressActivity.this, PickupActivity.class);
                    intent.putExtra("longitute", longitute);
                    intent.putExtra("latitute", latitute);
                    startActivity(intent);
                }

                break;
        }


    }
}
