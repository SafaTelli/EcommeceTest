package aurora.ghl.com.helloproj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import aurora.ghl.com.helloproj.R;
import aurora.ghl.com.helloproj.models.Product;

/**
 * Created by safa telli on 18/10/2019.
 */

public class ProductAdapter extends BaseAdapter {


    List<Product> products ;
    ArrayList<Product> listProduct ;
    Context context ;

    public ProductAdapter(Context context, List<Product> products)
    {
        this.context =context ;
        this.products = products ;

        this.listProduct = new ArrayList<Product>();
        this.listProduct.addAll(products);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View layout;
        TextView txtDesc , txtName , txtPrice , txtCat ;
        ImageView image  ;
        if(convertView==null)
            layout = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false) ;
        else
            layout=convertView ;

        txtName = layout.findViewById(R.id.nameProd) ;
        txtDesc = layout.findViewById(R.id.descProd) ;
        txtCat = layout.findViewById(R.id.catProd) ;
        image=layout.findViewById(R.id.imageProd) ;

        txtName.setText(products.get(position).getProductName());
        txtDesc.setText(products.get(position).getDescription());
        txtCat.setText(products.get(position).getCategory());
        Picasso.with(context)
                .load(products.get(position).getImageUrl())
                .into(image);

        return layout;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        products.clear();
        if (charText.length() == 0) {
            products.addAll(listProduct);
        }
        else
        {
            for (Product product : listProduct)
            {
                if (product.getProductName().toLowerCase(Locale.getDefault()).contains(charText) || product.getCategory().toLowerCase(Locale.getDefault()).contains(charText) )
                {
                    products.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }
}
