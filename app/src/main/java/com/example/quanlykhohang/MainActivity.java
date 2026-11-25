package com.example.quanlykhohang;
import android.os.Bundle; import android.content.Intent; import android.widget.*; import java.util.ArrayList;
public class MainActivity extends BaseActivity {
 protected void onCreate(Bundle b){super.onCreate(b);screen(R.layout.activity_main);findViewById(R.id.add).setOnClickListener(v->startActivity(new Intent(this,AddProductActivity.class)));}
 protected void onResume(){super.onResume();ArrayList<Product> items=db.getAllProducts();text(R.id.count,""+items.size());text(R.id.total,Product.money(db.getTotalValue()));text(R.id.low,""+db.countLowStock());ListView list=findViewById(R.id.products);list.setEmptyView(findViewById(R.id.empty));list.setAdapter(new ProductAdapter(this,items));list.setOnItemClickListener((a,v,pos,id)->startActivity(new Intent(this,ProductDetailActivity.class).putExtra("id",items.get(pos).id)));}
}