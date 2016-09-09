package com.hebing.sahre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hebing.sahre.model.WeatherInfo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaAndRetrofitTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_and_retrofit_test);
        //测试retrofit
        findViewById(R.id.click_me_BN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.weather.com.cn/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                ApiStores apiStores = retrofit.create(ApiStores.class);
                Observable<WeatherInfo> call = apiStores.getWeather("101010100");
                call.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<WeatherInfo>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(RxJavaAndRetrofitTestActivity.this, "请求出错:" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onNext(WeatherInfo weatherInfo) {
                                Toast.makeText(RxJavaAndRetrofitTestActivity.this, "城市：" + weatherInfo.getWeatherinfo().getCity() + " 当前风向：" + weatherInfo.getWeatherinfo().getWD(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }

    public interface ApiStores {
        @GET("adat/sk/{cityId}.html")
        Observable<WeatherInfo> getWeather(@Path("cityId") String cityId);
    }
}
