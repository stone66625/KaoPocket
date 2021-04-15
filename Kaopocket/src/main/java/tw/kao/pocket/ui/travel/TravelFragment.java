package tw.kao.pocket.ui.travel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import tw.kao.pocket.Main;
import tw.kao.pocket.R;

public class TravelFragment extends Fragment {

    private TravelViewModel travelViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        travelViewModel = new ViewModelProvider(this).get(TravelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_travel, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        travelViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });

        travelViewModel.getNumber().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });
        return root;



    }
}