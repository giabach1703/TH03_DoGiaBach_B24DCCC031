
package com.example.quanlykhohang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BaseActivity extends Activity {

 protected DatabaseHelper db;

 @Override
 protected void onCreate(Bundle b) {
  super.onCreate(b);
  db = new DatabaseHelper(this);
 }

 protected void screen(int layout) {
  setContentView(layout);

  View root = findViewById(R.id.root);

  ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {

   Insets bars = insets.getInsets(
           WindowInsetsCompat.Type.systemBars()
                   | WindowInsetsCompat.Type.displayCutout()
   );

   Insets keyboard = insets.getInsets(
           WindowInsetsCompat.Type.ime()
   );

   view.setPadding(
           bars.left,
           bars.top,
           bars.right,
           Math.max(bars.bottom, keyboard.bottom)
   );

   return insets;
  });

  ViewCompat.requestApplyInsets(root);
 }

 protected void toast(String s) {
  Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
 }

 protected String input(int id) {
  return ((EditText) findViewById(id))
          .getText().toString().trim();
 }

 protected void text(int id, String s) {
  ((TextView) findViewById(id)).setText(s);
 }

 @Override
 protected void onDestroy() {
  db.close();
  super.onDestroy();
 }
}
