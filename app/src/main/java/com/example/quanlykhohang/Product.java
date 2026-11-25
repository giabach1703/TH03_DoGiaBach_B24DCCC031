package com.example.quanlykhohang;
public class Product {
 public int id, price, quantity; public String name, category, description; public boolean active;
 public Product(int id,String name,int price,int quantity,String category,boolean active,String description){this.id=id;this.name=name;this.price=price;this.quantity=quantity;this.category=category;this.active=active;this.description=description;}
 public static String money(long n){return String.format(new java.util.Locale("vi","VN"),"%,d đ",n);}
}