package com.example.quanlykhohang;
import android.content.*; import android.database.Cursor; import android.database.sqlite.*; import java.util.ArrayList;
public class DatabaseHelper extends SQLiteOpenHelper {
 public DatabaseHelper(Context c){super(c,"warehouse.db",null,1);}
 public void onCreate(SQLiteDatabase db){
 db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL UNIQUE, password TEXT NOT NULL)");
 db.execSQL("CREATE TABLE products(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,price INTEGER NOT NULL CHECK(price>0),quantity INTEGER NOT NULL CHECK(quantity>=0),category TEXT NOT NULL,active INTEGER NOT NULL CHECK(active IN (0,1)),description TEXT)");
 insertUser(db,"admin","123456");
 insertProduct(db,new Product(0,"Chuột không dây",150000,20,"Điện tử",true,"Chuột kết nối không dây"));
 insertProduct(db,new Product(0,"Bàn phím cơ",450000,3,"Điện tử",true,"Bàn phím có đèn nền"));
 insertProduct(db,new Product(0,"Sổ tay A5",25000,40,"Văn phòng phẩm",true,"Sổ ghi chép A5")); }
 public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){}
 private long insertUser(SQLiteDatabase db,String u,String p){ContentValues c=new ContentValues();c.put("username",u);c.put("password",p);return db.insert("users",null,c);}
 public long insertUser(String u,String p){return insertUser(getWritableDatabase(),u,p);}
 public boolean checkLogin(String u,String p){try(Cursor c=getReadableDatabase().rawQuery("SELECT id FROM users WHERE username=? AND password=?",new String[]{u,p})){return c.moveToFirst();}}
 private long insertProduct(SQLiteDatabase db,Product p){ContentValues c=new ContentValues();c.put("name",p.name);c.put("price",p.price);c.put("quantity",p.quantity);c.put("category",p.category);c.put("active",p.active?1:0);c.put("description",p.description);return db.insert("products",null,c);}
 public long insertProduct(Product p){return insertProduct(getWritableDatabase(),p);}
 private Product read(Cursor c){return new Product(c.getInt(c.getColumnIndexOrThrow("id")),c.getString(c.getColumnIndexOrThrow("name")),c.getInt(c.getColumnIndexOrThrow("price")),c.getInt(c.getColumnIndexOrThrow("quantity")),c.getString(c.getColumnIndexOrThrow("category")),c.getInt(c.getColumnIndexOrThrow("active"))==1,c.getString(c.getColumnIndexOrThrow("description")));}
 public ArrayList<Product> getAllProducts(){ArrayList<Product> a=new ArrayList<>();try(Cursor c=getReadableDatabase().rawQuery("SELECT * FROM products ORDER BY id",null)){while(c.moveToNext())a.add(read(c));}return a;}
 public Product getProductById(int id){try(Cursor c=getReadableDatabase().rawQuery("SELECT * FROM products WHERE id=?",new String[]{String.valueOf(id)})){return c.moveToFirst()?read(c):null;}}
 public int updateQuantity(int id,int q){if(q<0)return 0;ContentValues c=new ContentValues();c.put("quantity",q);return getWritableDatabase().update("products",c,"id=?",new String[]{String.valueOf(id)});}
 public int deleteProduct(int id){return getWritableDatabase().delete("products","id=?",new String[]{String.valueOf(id)});}
 public long getTotalValue(){try(Cursor c=getReadableDatabase().rawQuery("SELECT COALESCE(SUM(price * quantity),0) FROM products",null)){return c.moveToFirst()?c.getLong(0):0;}}
 public int countLowStock(){try(Cursor c=getReadableDatabase().rawQuery("SELECT COUNT(*) FROM products WHERE quantity < 5",null)){return c.moveToFirst()?c.getInt(0):0;}}
}