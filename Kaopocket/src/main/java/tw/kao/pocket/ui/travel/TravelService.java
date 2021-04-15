package tw.kao.pocket.ui.travel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TravelService {
    @GET("api/service/get/9c8e1450-e833-499c-8320-29b36b7ace5c")
    Call<TravelResponse> getOpenData();

}
