package com.ca.scotiabankhomeassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import api.ApiClient;
import api.ApiService;
import model.DailyAstronomyPictureDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.NetworkConnection;

public class MainActivity extends AppCompatActivity {

    View parentView;
    ImageView imgDailyPicture;
    TextView txtPictureTitle;
    public String url, title;

    public String text = "";
    public int position = 0;
    Animation fadeiInAnimationObject;
    Animation textDisplayAnimationObject;
    Animation delayBetweenAnimations;
    Animation fadeOutAnimationObject;
    int fadeEffectDuration;
    int delayDuration;
    int displayFor;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        parentView = findViewById(R.id.parentLayout);
        imgDailyPicture = findViewById(R.id.imgDailyPicture);
        txtPictureTitle = findViewById(R.id.txtPictureTitle);


        /**
         * Checking Internet Connection
         */
        if (NetworkConnection.checkConnection(MainActivity.this)) {
            //Creating an object of our api interface
            ApiService api = ApiClient.getApiService();

            /**
             * Calling JSON
             */
            Call<DailyAstronomyPictureDetail> call = api.getDailyPicture();

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<DailyAstronomyPictureDetail>() {
                @Override
                public void onResponse(Call<DailyAstronomyPictureDetail> call, Response<DailyAstronomyPictureDetail> response) {

                    if (response.isSuccessful()) {
                        /**
                         * Got Successfully
                         */
                        DailyAstronomyPictureDetail dailyInfo = response.body();
                        url = dailyInfo.getUrl();
                        title = dailyInfo.getTitle();
                        //Picasso.get().load(dailyInfo.getUrl()).into(imgDailyPicture);
                        Picasso.get().load("https://apod.nasa.gov/apod/image/1804/IC4592_WiseAntonucciR_960.jpg").into(imgDailyPicture);
                        //txtPictureTitle.setText(title);
                        MainActivity animator = new MainActivity(txtPictureTitle, title);
                        animator.startAnimation();
                    } else {
                        Toast.makeText(MainActivity.this, R.string.string_some_thing_wrong, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DailyAstronomyPictureDetail> call, Throwable t) {
                }
            });

        } else {
            Toast.makeText(MainActivity.this, R.string.string_network_connection_not_available, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickImageView(View view) {
        Intent intent = new Intent(MainActivity.this, PictureFullScreen.class);
        intent.putExtra("url", "https://apod.nasa.gov/apod/image/1804/IC4592_WiseAntonucciR_960.jpg");
        intent.putExtra("title", title);
        startActivity(intent);
    }

    public void startAnimation() {
        txtPictureTitle.startAnimation(fadeOutAnimationObject);
    }

    private void InnitializeAnimation() {
        fadeiInAnimationObject = new AlphaAnimation(0f, 1f);
        fadeiInAnimationObject.setDuration(fadeEffectDuration);
        textDisplayAnimationObject = new AlphaAnimation(1f, 1f);
        textDisplayAnimationObject.setDuration(displayFor);
        delayBetweenAnimations = new AlphaAnimation(0f, 0f);
        delayBetweenAnimations.setDuration(delayDuration);
        fadeOutAnimationObject = new AlphaAnimation(1f, 0f);
        fadeOutAnimationObject.setDuration(fadeEffectDuration);
        fadeiInAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                position++;
                if (position >= text.length()) {
                    position = 0;
                }
                txtPictureTitle.setText(text);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                txtPictureTitle.startAnimation(textDisplayAnimationObject);
            }
        });
        textDisplayAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                txtPictureTitle.startAnimation(fadeOutAnimationObject);
            }
        });
        fadeOutAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                txtPictureTitle.startAnimation(delayBetweenAnimations);
            }
        });
        delayBetweenAnimations.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                txtPictureTitle.startAnimation(fadeiInAnimationObject);
            }
        });
    }

    public MainActivity(TextView textV, String textList) {
        this(textV, 700, 1000, 350, textList);
    }

    public MainActivity(TextView textView, int fadeEffectDuration, int delayDuration, int displayLength, String textList) {
        txtPictureTitle = textView;
        text = textList;
        this.fadeEffectDuration = fadeEffectDuration;
        this.delayDuration = delayDuration;
        this.displayFor = displayLength;
        InnitializeAnimation();
    }

    public MainActivity() {
    }

}
