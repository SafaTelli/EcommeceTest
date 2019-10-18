package aurora.ghl.com.helloproj;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import aurora.ghl.com.helloproj.DB.RealmController;
import aurora.ghl.com.helloproj.Services.ApiClient;
import aurora.ghl.com.helloproj.Services.ApiInterface;
import aurora.ghl.com.helloproj.models.Product;
import aurora.ghl.com.helloproj.models.ResultProduct;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    Realm realm ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        realm = RealmController.with(this).getRealm();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();


            }
        }, 5000);
    }



    private void getProducts() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        //create call request
        Call<ResultProduct> call = apiInterface.getProducts();
        //execute request
        call.enqueue(new Callback<ResultProduct>() {
            @Override
            public void onResponse(@NonNull Call<ResultProduct> call, @NonNull Response<ResultProduct> response) {


                //get code response
                if (response.body() != null && response.code() == 200) {
                    //save all article in DB
                    for (Product product : response.body().getProducts()) {


                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(product);
                        realm.commitTransaction();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<ResultProduct> call, @NonNull Throwable t) {

            }
        });
    }


}
