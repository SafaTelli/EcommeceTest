package aurora.ghl.com.helloproj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import aurora.ghl.com.helloproj.DB.RealmController;
import aurora.ghl.com.helloproj.Services.ApiClient;
import aurora.ghl.com.helloproj.Services.ApiInterface;
import aurora.ghl.com.helloproj.adapter.ProductAdapter;
import aurora.ghl.com.helloproj.models.Product;
import aurora.ghl.com.helloproj.models.ResultProduct;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView listProduct ;
    ApiInterface apiInterface ;
    ProgressBar progressBar ;
    ProductAdapter productAdapter ;
    EditText editSearch ;
    List<Product> products;
    Realm realm ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listProduct = findViewById(R.id.listProduct) ;
        progressBar = findViewById(R.id.progressbar) ;
        realm = RealmController.with(this).getRealm();

      //  List<Product> products =RealmController.getInstance().getProducts() ;
     //   RealmController.getInstance().getProducts();


        editSearch = findViewById(R.id.search) ;
        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product  = (Product) listProduct.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("nameProd",product.getProductName());
                intent.putExtra("imageProd",product.getImageUrl());
                intent.putExtra("descProd",product.getDescription());
                intent.putExtra("priceProd",product.getPrice());
                startActivity(intent);

            }
        });
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResultProduct> call = apiInterface.getProducts();
        call.enqueue(new Callback<ResultProduct>() {
            @Override
            public void onResponse(Call<ResultProduct> call, Response<ResultProduct> response) {

                if( response.code() ==200)
                {
                    progressBar.setVisibility(View.GONE);
                    listProduct.setVisibility(View.VISIBLE);
                    productAdapter =new ProductAdapter(MainActivity.this,response.body().getProducts());
                    listProduct.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResultProduct> call, Throwable t) {

                Toast.makeText(MainActivity.this, "network error",Toast.LENGTH_LONG).show();
            }
        });
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editSearch.getText().toString().toLowerCase(Locale.getDefault());
                productAdapter.filter(text);
            }
        });



    }
}
