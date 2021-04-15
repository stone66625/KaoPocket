package tw.kao.pocket.ui.youbike;

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

import tw.kao.pocket.R;

public class YoubikeFragment extends Fragment {

    private YoubikeViewModel youbikeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        youbikeViewModel =
                new ViewModelProvider(this).get(YoubikeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_youbike, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        youbikeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}