package com.example.quanlykhohang;
import android.content.Context; import android.view.*; import android.widget.*; import android.graphics.Color; import java.util.ArrayList;
public class ProductAdapter extends BaseAdapter {
 private final Context c; private final ArrayList<Product> items;
 public ProductAdapter(Context c,ArrayList<Product> items){this.c=c;this.items=items;}
 public int getCount(){return items.size();} public Product getItem(int p){return items.get(p);} public long getItemId(int p){return getItem(p).id;}
 public View getView(int position,View view,ViewGroup parent){if(view==null)view=LayoutInflater.from(c).inflate(R.layout.item_product,parent,false);Product p=getItem(position);((TextView)view.findViewById(R.id.itemName)).setText(p.name);((TextView)view.findViewById(R.id.itemPrice)).setText("Giá bán: "+Product.money(p.price));((TextView)view.findViewById(R.id.itemCategory)).setText(p.category);TextView q=view.findViewById(R.id.itemQuantity);q.setText("SL: "+p.quantity);q.setTextColor(Color.parseColor(p.quantity<5?"#C62828":"#20332F"));view.findViewById(R.id.itemWarning).setVisibility(p.quantity<5?View.VISIBLE:View.GONE);return view;}
}