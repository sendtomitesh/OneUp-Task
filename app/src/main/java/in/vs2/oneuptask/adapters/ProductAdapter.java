package in.vs2.oneuptask.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.vs2.oneuptask.R;
import in.vs2.oneuptask.models.Product;

public class ProductAdapter extends BaseAdapter {

	private static final String tag = "ProductAdapter";
	private ArrayList<Product> mProducts = null;

	private Context context;
	private LayoutInflater mInflater;
	


	public ProductAdapter(ArrayList<Product> products, Context context) {
		super();
		this.mProducts = products;
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mProducts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mProducts.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_product, null);
			holder = new ViewHolder();
			holder.textTitle = (TextView) convertView
					.findViewById(R.id.title);
			holder.textPrice = (TextView) convertView
					.findViewById(R.id.desc);
			holder.imageProduct = (ImageView) convertView.findViewById(R.id.icon);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			
			holder.textTitle.setText(""+mProducts.get(position).getName());
			holder.textPrice.setText(""+mProducts.get(position).getPrice());
			holder.imageProduct.setImageResource(mProducts.get(position).getImageResource());

			
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(tag, "getView Error : " + e.toString());
		}
		
		return convertView;
	}

	public class ViewHolder {
		TextView textTitle,textPrice;
		ImageView imageProduct;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
