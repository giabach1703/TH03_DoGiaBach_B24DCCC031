package com.example.quanlykhohang;
import android.os.Bundle; import android.content.Intent; import android.widget.*;
public class LoginActivity extends BaseActivity {
 protected void onCreate(Bundle b){super.onCreate(b);screen(R.layout.activity_login);findViewById(R.id.login).setOnClickListener(v->{String u=input(R.id.username),p=input(R.id.password);if(u.isEmpty()||p.isEmpty()){text(R.id.error,"Vui lòng nhập đầy đủ thông tin");return;}if(db.checkLogin(u,p)){startActivity(new Intent(this,MainActivity.class));finish();}else text(R.id.error,"Sai tên đăng nhập hoặc mật khẩu");});}
}