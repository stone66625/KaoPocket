package tw.kao.pocket.ui.travel;

import android.widget.SimpleAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TravelViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private MutableLiveData<Integer> mNumber = new MutableLiveData<>();
    private String name,tel,add,px,py,picture1;

    public TravelViewModel() {

        mText.setValue("hahaha");
        mNumber.setValue(1);
        Load_TravelOpenData();


    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Integer> getNumber(){
        return mNumber;
    }

    private void Load_TravelOpenData() {

        String url = "https://api.kcg.gov.tw/";
        //原網址 = "https://api.kcg.gov.tw/api/service/get/9c8e1450-e833-499c-8320-29b36b7ace5c";
        //他要拆解成這樣 https://api.kcg.gov.tw/  這段在這邊(估計是網頁分成第一段，決定是哪個api的部分為第二段)
        //第二段 api/service/get/9c8e1450-e833-499c-8320-29b36b7ace5c 在TravelService.class另一邊

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TravelService service = retrofit.create(TravelService.class);
        retrofit2.Call<TravelResponse> call =service.getOpenData();

        call.enqueue(new Callback<TravelResponse>() {
            @Override
            public void onResponse(Call<TravelResponse> call, Response<TravelResponse> response) {

                if(response.code()==200)
                {
                    ArrayList<Info> travelDataArray = new ArrayList<Info>();
                    travelDataArray = response.body().data.XML_Head.Infos.Info;

                    for(int i=0; i<travelDataArray.size(); i++){

                        name=travelDataArray.get(i).Name;
                        tel=travelDataArray.get(i).Tel;
                        add=travelDataArray.get(i).Add;
                        px=travelDataArray.get(i).Px;
                        py=travelDataArray.get(i).Py;
                        picture1=travelDataArray.get(i).Picture1;

                        HashMap<String, String> data = new HashMap<>();
                        data.put("name", name);
                        data.put("tel", tel);
                        data.put("add", add);
                        data.put("px", px);
                        data.put("py", py);
                        data.put("picture1", picture1);


                    }
                    int a=0;
                }

            }

            @Override
            public void onFailure(Call<TravelResponse> call, Throwable t) {
                int a=0;
            }

        });




    }
}