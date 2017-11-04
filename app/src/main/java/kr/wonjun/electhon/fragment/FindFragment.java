package kr.wonjun.electhon.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import kr.wonjun.electhon.PlaceRecyclerViewAdapter;
import kr.wonjun.electhon.R;
import kr.wonjun.electhon.databinding.FragmentFindBinding;
import kr.wonjun.electhon.models.Map;
import kr.wonjun.electhon.models.Place;
import kr.wonjun.electhon.utils.GpsInfo;
import kr.wonjun.electhon.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kr.wonjun.electhon.R.layout.fragment_find;

public class FindFragment extends Fragment implements OnMapReadyCallback {
    static final int REQUEST_PERMISSIONS = 100;
    FragmentFindBinding binding;
    GpsInfo gpsInfo;
    GoogleMap map;
    Map currentClickedMap = null;
    ArrayList<Place> arrayList = new ArrayList<>();
    PlaceRecyclerViewAdapter adapter;

    public FindFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, fragment_find, container, false);
        gpsInfo = new GpsInfo(getContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        adapter = new PlaceRecyclerViewAdapter(arrayList, getContext());
        binding.listView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.listView.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        Log.e("asdf", "" + gpsInfo.getLatitude() + "\n" + gpsInfo.getLongitude());
        if (gpsInfo.isGetLocation()) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gpsInfo.getLatitude(), gpsInfo.getLongitude()), 16.0f));
//            map.setMyLocationEnabled(true);
            NetworkHelper.getNetworkInstance().getMaps(gpsInfo.getLatitude(), gpsInfo.getLongitude()).enqueue(new Callback<ArrayList<Map>>() {
                @Override
                public void onResponse(Call<ArrayList<Map>> call, Response<ArrayList<Map>> response) {
                    switch (response.code()) {
                        case 200:
//                            arrayList.addAll(response.body());
//                            for (Map m : arrayList) {
//                                map.addMarker(new MarkerOptions().position(new LatLng(m.getLatitude(), m.getLongitude())).title(m.getTitle()));
//                            }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Map>> call, Throwable t) {

                }
            });
            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Map dest = null;
//                    for (Map m : arrayList) {
//                        if (m.getTitle().equals(marker.getTitle())) {
//                            dest = m;
//                            break;
//                        }
//                    }
                    currentClickedMap = dest;
//                    binding.cardViewContainer.setVisibility(View.VISIBLE);
//                    binding.title.setText(dest.getTitle());
//                    binding.content.setText(dest.getAddress());
                    return false;
                }
            });
//            binding.reservenow.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (currentClickedMap == null) return;
//                    startActivity(new Intent(getActivity(), ReserveActivity.class)
//                            .putExtra("map", currentClickedMap));
//                }
//            });
        }

    }
}
