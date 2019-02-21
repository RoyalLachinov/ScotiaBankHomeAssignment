package api;

import model.DailyAstronomyPictureDetail;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
    @GET("apod?api_key=KfLaI1yU2DBiJtSoyHZwX2Vz2lAFXDkdj3CB9Izd")
    Call<DailyAstronomyPictureDetail> getDailyPicture();

}