package aurora.ghl.com.helloproj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageProd ;
    TextView txtName , txtDesc ,txtPrice ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent() ;
        imageProd = findViewById(R.id.imageProd) ;
        txtName = findViewById(R.id.txtName) ;
        txtDesc =findViewById(R.id.txtDesc) ;
        txtPrice = findViewById(R.id.txtPrice) ;

        txtName.setText(intent.getStringExtra("nameProd"));
        txtDesc.setText(intent.getStringExtra("descProd"));
        txtPrice.setText(String.valueOf(intent.getDoubleExtra("priceProd",0.0)));
        Picasso.with(DetailsActivity.this)
                .load(intent.getStringExtra("imageProd"))
                .into(imageProd);


    }
}
