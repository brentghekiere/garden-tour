package cz.mendelu.busItWeek;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
<<<<<<< HEAD
=======
import android.widget.TextView;
>>>>>>> 6b5dabd227a7e48a53cac7c4d4abdfb5adc073f4

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.SphericalUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.mendelu.busItWeek.library.BeaconTask;
import cz.mendelu.busItWeek.library.CodeTask;
import cz.mendelu.busItWeek.library.GPSTask;
import cz.mendelu.busItWeek.library.ImageSelectPuzzle;
import cz.mendelu.busItWeek.library.Puzzle;
import cz.mendelu.busItWeek.library.SimplePuzzle;
import cz.mendelu.busItWeek.library.StoryLine;
import cz.mendelu.busItWeek.library.Task;
import cz.mendelu.busItWeek.library.beacons.BeaconDefinition;
import cz.mendelu.busItWeek.library.beacons.BeaconUtil;
import cz.mendelu.busItWeek.library.map.MapUtil;
import cz.mendelu.busItWeek.library.qrcode.QRCodeUtil;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnMarkerClickListener {

    private static final int SIMPLE_PUZZLE_REQUEST_CODE = 1;
    private static final int IMAGE_PUZZLE_REQUEST_CODE = 2;
    private GoogleMap mMap;

    private StoryLine storyLine;
    private Task currentTask;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    private HashMap<Task, Marker> mapOfMarkers;

    private BeaconUtil beaconUtil;
    private ImageButton readQRCodeButton;

<<<<<<< HEAD
    @BindView(R.id.progressBar4)
    ProgressBar menuBar;
    private Integer currentPoints;
=======
    @BindView(R.id.points)
    TextView points;
    @BindView(R.id.time_tv)
    TextView timer;

    @BindView(R.id.progress)
    ProgressBar progressBar;
>>>>>>> 6b5dabd227a7e48a53cac7c4d4abdfb5adc073f4

    /**
     * Update the user's info in the top menubar of the application
     * @param points Integer The points the user currently has
     * @param progress Integer the amount of progress made in the storyline so far
     */
    private void setUserInfo(Integer point, Integer progress) {
        String text = "";
        Integer currentPoints = 0;

        try{
            points.setText("30");
            progressBar.setProgress(progress);
            timer.setText("30:00"); // TODO: implement timer
        }catch(Exception e) {
            points.setText("ERROR: " + e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

<<<<<<< HEAD
=======
        // Get layout views
//        progressBar = (ProgressBar) findViewById(R.id.progress);
//        points = (TextView) findViewById(R.id.points);
//        timer = (TextView) findViewById(R.id.time);

        // Set the menubar values
        ButterKnife.bind(this);
        setUserInfo(0, 40);
>>>>>>> 6b5dabd227a7e48a53cac7c4d4abdfb5adc073f4

        storyLine = StoryLine.open(this, MyDemoStoryLineDBHelper.class);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);

        mapOfMarkers = new HashMap<>();

        beaconUtil = new BeaconUtil(this);

        readQRCodeButton = (ImageButton) findViewById(R.id.read_qr_btn);
    }

    private void initializeTasks() {
        for (Task task : storyLine.taskList()) {
            Marker newMarker = null;

            // GPS Task
            if (task instanceof GPSTask) {
                newMarker = MapUtil.createColoredMarker(
                        this,
                        mMap,
                        task.getName(),
                        R.color.colorGPS,
                        R.style.marker_text_style,
                        new LatLng(task.getLatitude(), task.getLongitude())
                );
            }

            // BEACON Task
            if (task instanceof BeaconTask) {
                newMarker = MapUtil.createColoredMarker(
                        this,
                        mMap,
                        task.getName(),
                        R.color.colorBeacon,
                        R.style.marker_text_style,
                        new LatLng(task.getLatitude(), task.getLongitude())
                );

                BeaconDefinition definition = new BeaconDefinition((BeaconTask) task) {
                    @Override
                    public void execute() {
                        runPuzzleActivity(currentTask.getPuzzle());
                    }
                };
                beaconUtil.addBeacon(definition);


            }

            // CODE Task
            if (task instanceof CodeTask) {
                newMarker = MapUtil.createColoredMarker(
                        this,
                        mMap,
                        task.getName(),
                        R.color.colorQR,
                        R.style.marker_text_style,
                        new LatLng(task.getLatitude(), task.getLongitude())
                );

            }

            mapOfMarkers.put(task, newMarker);

        }
    }

    private void initializeListeners() {
        if (currentTask != null) {
            if (currentTask instanceof GPSTask) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                if (googleApiClient.isConnected()) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, (LocationListener) this);
                }
            }
            if (currentTask instanceof BeaconTask) {
                beaconUtil.startRanging();
            }
            if (currentTask instanceof CodeTask) {
                readQRCodeButton.setVisibility(View.VISIBLE);
            } else {
                readQRCodeButton.setVisibility(View.GONE);
            }
        }
    }

    private void updateMarkers() {
        for (Map.Entry<Task, Marker> entry : mapOfMarkers.entrySet()) {
            if (currentTask != null) {
                if (entry.getKey().getName().equals(currentTask.getName())) {
                    entry.getValue().setVisible(true);
                } else {
                    entry.getValue().setVisible(false);
                }
            } else {
                entry.getValue().setVisible(false);
            }
        }
    }

    private void cancelListeners() {
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            if (beaconUtil.isRanging()) {
                beaconUtil.stopRanging();
            }
        }
        readQRCodeButton.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        currentTask = storyLine.currentTask();
        if (currentTask == null) {
            // game finished, no more task

        } else {
            // not finished, still some tasks left
            initializeListeners();
        }

        updateMarkers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelListeners();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        initializeTasks();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            // return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMarkerClickListener(this);

        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (currentTask != null && currentTask instanceof GPSTask) {
            double radius = ((GPSTask) currentTask).getRadius();
            LatLng user = new LatLng(location.getLatitude(), location.getLongitude());
            LatLng taskPosition = new LatLng(currentTask.getLatitude(), currentTask.getLongitude());
            if (SphericalUtil.computeDistanceBetween(user, taskPosition) < radius) {
                // I should do smth
            }
        }
    }

    private void runPuzzleActivity(Puzzle puzzle) {
        if (puzzle instanceof SimplePuzzle) {
            Intent intent = new Intent(this, SimplePuzzleActivity.class);
            startActivityForResult(intent, SIMPLE_PUZZLE_REQUEST_CODE);
        }

        if (puzzle instanceof ImageSelectPuzzle) {
            Intent intent = new Intent(this, ImageSelectPuzzleActivity.class);
            startActivityForResult(intent, IMAGE_PUZZLE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        currentTask = storyLine.currentTask();
        if (currentTask != null && currentTask instanceof CodeTask) {
            String result = QRCodeUtil.onScanResult(this, requestCode, resultCode, data);
            CodeTask codeTask = (CodeTask) currentTask;
            if (codeTask.getQR().equals(result)) {
                runPuzzleActivity(currentTask.getPuzzle());
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Skip task")
                .setMessage("Do you want to skip current task?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // todo user wants to skip
                        dialogInterface.dismiss();
                        currentTask.skip();
                        currentTask = storyLine.currentTask();
                        if (currentTask == null) {
                            // game is finished
                        } else {
                            cancelListeners();
                            initializeListeners();
                        }
                        updateMarkers();
                        if (googleApiClient.isConnected()) {
                            if (ActivityCompat.checkSelfPermission(MapActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            if (currentTask != null) {
                                Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                                zoomToNewTask(new LatLng(location.getLatitude(), location.getLongitude()), new LatLng(currentTask.getLatitude(), currentTask.getLongitude()));
                            }
                        }
                    }
                });
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();

        return false;
    }

    private void zoomToNewTask(LatLng userPosition, LatLng taskPosition) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(userPosition);
        builder.include(taskPosition);

        LatLngBounds bounds = builder.build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 100);

        mMap.animateCamera(cameraUpdate);
    }

    public void readQRCode(View view) {
        QRCodeUtil.startQRScan(this);
    }
}