package com.example.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MypageActivity extends AppCompatActivity {

    TextView user_info_email, user_info_name;
    Button btn_wordbook, btn_change_password, btn_logout, btn_go_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        user_info_email = (TextView) findViewById(R.id.user_info_email) ; // 유저 이메일 정보
        user_info_name = (TextView) findViewById(R.id.user_info_name) ; // 유저명 정보
        btn_change_password = (Button) findViewById(R.id.btn_change_password); // 비밀번호 변경 버튼
        btn_logout = (Button) findViewById(R.id.btn_logout); // 로그아웃 버튼
        btn_wordbook = (Button) findViewById(R.id.btn_wordbook); // 나의 단어장 버튼
        btn_go_main = (Button) findViewById(R.id.btn_go_main); // 메인으로 돌아가는 버튼


        // TODO : DB와 연동하여 로그인 유저의 이메일과 사용자명이 뜨도록
        // 내 정보 텍스트뷰

        // 로그아웃 버튼 클릭 시 로그아웃 프로세스 진행
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 로그아웃 버튼 클릭 이벤트 처리
                AlertDialog.Builder builder = new AlertDialog.Builder(MypageActivity.this);
                builder.setMessage("정말 로그아웃 하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // "예" 버튼 클릭 이벤트 처리
                                SharedPreferences sharedPreferences = getSharedPreferences("login_data", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("is_logged_in", false);
                                editor.apply();

                                Intent intent = new Intent(MypageActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(MypageActivity.this, "로그아웃했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // 나의 단어장 버튼 클릭 이벤트 (나의 단어장으로 이동)
        btn_wordbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_wd = new Intent(MypageActivity.this, WordbookActivity.class);
                startActivity(intent_wd); // 나의 단어장 화면으로 이동함
            }
        });

        // 비밀번호 변경 버튼 클릭 시 비밀번호 변경 화면으로 이동
        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_pw = new Intent(MypageActivity.this, ChangePasswordActivity.class);
                startActivity(intent_pw); // 비밀번호 변경 화면으로 이동함
            }
        });

        // BACK 버튼 클릭 이벤트 (메인으로 이동)
        btn_go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_main = new Intent(MypageActivity.this, MainActivity.class);
                startActivity(intent_main);
            }
        });

    }

}
