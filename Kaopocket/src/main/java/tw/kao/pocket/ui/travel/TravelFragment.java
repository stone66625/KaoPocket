package tw.kao.pocket.ui.travel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import tw.kao.pocket.Main;
import tw.kao.pocket.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class TravelFragment extends Fragment {

    private TravelViewModel travelViewModel;
    private ImageView bannerImage;
    private Spinner areaSpinner;
    private RecyclerView attractionsRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        travelViewModel = new ViewModelProvider(this).get(TravelViewModel.class);
        View view = inflater.inflate(R.layout.fragment_travel, container, false);
        setupViewComponent(view);
        return view;

    }

    private void setupViewComponent(View view) {

        bannerImage = (ImageView)view.findViewById(R.id.bannerImage);
        areaSpinner = (Spinner)view.findViewById(R.id.AreaSpinner);
        attractionsRecyclerView = (RecyclerView)view.findViewById(R.id.AttractionsRecyclerView);
        travelViewModel.getTravelOpenData().observe(getViewLifecycleOwner(), new Observer<ArrayList<HashMap<String, String>>>() {
           @Override
           public void onChanged(ArrayList<HashMap<String, String>> travelOpenData) {
               //-----set Spinner
               Set<String> set = new HashSet<String>();
               ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
               for(int i=0; i<travelOpenData.size(); i++){
                   set.add( travelOpenData.get(i).get("section") );
               }

               Iterator iterator = set.iterator(); //Iterator疊代器
               while ( iterator.hasNext() ){
                   sp_adapter.add( iterator.next().toString() );
               }

               sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               areaSpinner.setAdapter(sp_adapter);

               //-----set RecyclerView
               TravelRecyclerAdapter tr_adapter = new TravelRecyclerAdapter(getActivity(),travelOpenData);
               tr_adapter.setOnItemClickListener(new TravelRecyclerAdapter.OnItemClickListener() {
                   @Override
                   public void onItemClick(View view, int position) {
                       Glide.with(getActivity())
                               .load(travelOpenData.get(position).get("picture1"))
                                //.skipMemoryCache(true)
                                //.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                               .override(1000,750)
                               .transition(withCrossFade())
                               .error(
                                       Glide.with(getActivity())
                                               .load("https://aboutyfc.com/android_mysql_connect/nopic1.jpg"))
                               .into(bannerImage);
                   }
               });
               attractionsRecyclerView.setHasFixedSize(true);
               //設置RecyclerView為列表型態
               attractionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
               //設置格線
               attractionsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
               attractionsRecyclerView.setAdapter(tr_adapter);


           }
       });
    }

    //==============================生命週期======================================
    @Override
    public void onResume() {
        super.onResume();
        bannerImage.setImageURI("https://aboutyfc.com/android_mysql_connect/nopic1.jpg");
    }
}