package com.example.sharedreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharedreference.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextMSV;
    private Button buttonSave, buttonLoad, buttonDelete;
    private TextView textViewResult;

    // tên file lưu
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_MSV = "MSV";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextMSV = findViewById(R.id.editTextMSV);
        buttonSave = findViewById(R.id.buttonSave);
        buttonLoad = findViewById(R.id.buttonLoad);
        buttonDelete = findViewById(R.id.buttonDelete);
        textViewResult = findViewById(R.id.textViewResult);

        // lưu username và MSV
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUsername();
            }
        });

        // hiển thị username và MSV
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUsername();
            }
        });

        // xóa dữ liệu
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
    }

    // lưu
    private void saveUsername() {
        String username = editTextUsername.getText().toString();
        String MSV = editTextMSV.getText().toString();
        // khởi tạo SharedPreferences và Editor
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // lưu giá trị vào file
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_MSV, MSV);
        editor.apply();
        Toast.makeText(MainActivity.this, "Lưu dữ liệu thành công", Toast.LENGTH_SHORT).show();
    }

    // Hiển thị dữ liệu
    private void loadUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String username = sharedPreferences.getString(KEY_USERNAME, "No username saved");
        String MSV = sharedPreferences.getString(KEY_MSV, "No MSV saved");
        textViewResult.setText("Username: " + username + "\nMSV: " + MSV);
    }

    // Hàm xóa dữ liệu trong SharedPreferences
    private void deleteData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();  // Xóa tất cả dữ liệu
        editor.apply();  // Xác nhận thay đổi
        textViewResult.setText("");
        editTextUsername.setText("");
        editTextMSV.setText("");
        Toast.makeText(MainActivity.this, "Dữ liệu đã được reset", Toast.LENGTH_SHORT).show();
    }
}
