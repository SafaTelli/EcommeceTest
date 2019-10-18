package aurora.ghl.com.helloproj.Services;

import aurora.ghl.com.helloproj.models.ResultProduct;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by safa telli on 18/10/2019.
 */

public interface ApiInterface {

    @GET("products?size=1000")
    Call<ResultProduct> getProducts();
}
