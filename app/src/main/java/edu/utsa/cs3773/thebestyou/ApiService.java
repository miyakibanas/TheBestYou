package edu.utsa.cs3773.thebestyou;

import edu.utsa.cs3773.thebestyou.model.UserProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("create-profile/")
    Call<Void> createUserProfile(@Body UserProfile userProfile);
}
