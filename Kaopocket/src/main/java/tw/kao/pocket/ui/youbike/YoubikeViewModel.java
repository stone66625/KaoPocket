package tw.kao.pocket.ui.youbike;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YoubikeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public YoubikeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}