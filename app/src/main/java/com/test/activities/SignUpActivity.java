package com.test.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.test.R;
import com.test.api.ApiService;
import com.test.models.RegisterBody;
import com.test.models.RegisterResp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up_screen);

//        final SQLSever sqlSever = new SQLSever(this);

        final EditText fullname = (EditText) findViewById(R.id.fullname);
        final EditText account = (EditText) findViewById(R.id.Account);
        final EditText mphone = (EditText) findViewById(R.id.phone);
        final EditText role = (EditText) findViewById(R.id.role);
        final EditText password1 = (EditText) findViewById(R.id.pass);
        final EditText password2 = (EditText) findViewById(R.id.pass2);
        Button login = (Button) findViewById(R.id.login);
        Button signup = (Button) findViewById(R.id.SignUp);

        password1.setInputType(InputType.TYPE_CLASS_TEXT |//ẩn Text để làm mật khẩu
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password2.setInputType(InputType.TYPE_CLASS_TEXT |//ẩn Text để làm mật khẩu
                InputType.TYPE_TEXT_VARIATION_PASSWORD);

//        final SQLSever sql_user = new SQLSever(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String acc = account.getText().toString();
                final String ten = fullname.getText().toString();
                final String phone = mphone.getText().toString();
                final String mk1 = password1.getText().toString();
                final String mk2 = password2.getText().toString();
                final String roLe = password2.getText().toString();
                if (ten.equals("") || phone.equals("") || mk1.equals("") || mk2.equals("") || acc.equals("")) {
                    Toast.makeText(SignUpActivity.this, "Vui Lòng Điền Đủ Thông tin!!!", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(SignUpActivity.this);
                    b.setTitle("Sign Up");
                    b.setMessage("Bạn có muốn Đăng Ký?");
                    b.setIcon(R.drawable.icons8_adduser);
                    b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mk1.equals(mk2)) {
                                register(acc, mk1, ten,phone,roLe);
                            } else {
                                Toast.makeText(SignUpActivity.this, "Mật Khẩu Nhập Lại Không đúng!!!", Toast.LENGTH_SHORT).show();
                                password2.setText("");
                                password1.setText("");
                            }
                        }
                    });
                    b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    b.create().show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLogin();
            }
        });
    }

    private void register(String username, String password, String fullname, String phone,String role) {

        RegisterBody body = new RegisterBody(username, password, fullname, phone, role);
        ApiService.apiService.register(body)
                .enqueue(new Callback<RegisterResp>() {
                    @Override
                    public void onResponse(Call<RegisterResp> call, Response<RegisterResp> response) {
                        RegisterResp data = response.body();
                        if (data != null) {
                            Toast.makeText(SignUpActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            OpenLogin();
                        } else {
                                Toast.makeText(SignUpActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResp> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void OpenLogin() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}


