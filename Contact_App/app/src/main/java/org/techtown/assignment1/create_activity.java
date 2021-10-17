package org.techtown.assignment1;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class create_activity extends AppCompatActivity {
    //******************************************************
    //이름: 강태규
    //학번: 201614729
    //설명: MainActivity.java - 개인정보 생성
    //******************************************************

    EditText textView_Name_Input, textView_Phone_Input;
    private ImageView FemaleView, MaleView, UnspecifiedView;
    private RadioButton Male_Radio, Female_Radio, Unspecified_Radio;
    private RadioGroup radioGroup;

    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //Text Edit 설정
        textView_Name_Input = (EditText)findViewById(R.id.textView_Name_Input);
        textView_Phone_Input = (EditText)findViewById(R.id.textView_Phone_Input);
        //라디오 버튼 설정
        Male_Radio = (RadioButton) findViewById(R.id.Male_Radio);
        Female_Radio = (RadioButton) findViewById(R.id.Female_Radio);
        Unspecified_Radio = (RadioButton) findViewById(R.id.Unspecified_Radio);
        //이미지 설정
        FemaleView = findViewById(R.id.FemaleView);
        MaleView = findViewById(R.id.MaleView);
        UnspecifiedView = findViewById(R.id.UnspecifiedView);
        //이미지 visibility 설정
        FemaleView.setVisibility(View.INVISIBLE);
        MaleView.setVisibility(View.INVISIBLE);
        UnspecifiedView.setVisibility(View.VISIBLE);
        //라디오 그룹 설정
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        //CANCEL 버튼 클릭시 MAIN으로 Connect
        Button cancel_button = findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra("cancel","a");

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //계정 create 버튼 설정
        Button create_acc_button = findViewById(R.id.create_acc_button);
        //SAVE 버튼 클릭시 acc 데이터 전송하고 MAIN으로 Connect
        create_acc_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name",textView_Name_Input.getText().toString());
                intent.putExtra("phone",textView_Phone_Input.getText().toString());
                intent.putExtra("cancel","c");
                if(Male_Radio.isChecked()) {
                    intent.putExtra("image","male");
                }
                else if(Female_Radio.isChecked()) {
                    intent.putExtra("image","female");
                }
                else if(Unspecified_Radio.isChecked()) {
                    intent.putExtra("image","unspecifed");
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    //라디오 버튼 클릭 리스너, 클릭시 TOAST 창 보여줌
    RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener(){
        @Override
        public void onClick(View view) {
            //Toast.makeText(getApplicationContext(), "r_btn1 : "+Male_Radio.isChecked() + "r_btn2 : " +Female_Radio.isChecked()+ "r_btn3 : " +Unspecified_Radio.isChecked() , Toast.LENGTH_SHORT).show();
        }
    };

    //라디오 그룹 클릭 리스너, 클릭시 프로필 사진 색 변함
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if(i == R.id.Male_Radio){
                FemaleView.setVisibility(View.INVISIBLE);
                MaleView.setVisibility(View.VISIBLE);
                UnspecifiedView.setVisibility(View.INVISIBLE);
                //Toast.makeText(getApplicationContext(), "라디오 그룹 Male 눌렸습니다.", Toast.LENGTH_SHORT).show();
            }
            else if(i == R.id.Female_Radio){
                FemaleView.setVisibility(View.VISIBLE);
                MaleView.setVisibility(View.INVISIBLE);
                UnspecifiedView.setVisibility(View.INVISIBLE);
                //Toast.makeText(getApplicationContext(), "라디오 그룹 Female 눌렸습니다.", Toast.LENGTH_SHORT).show();
            }
            else if(i == R.id.Unspecified_Radio){
                FemaleView.setVisibility(View.INVISIBLE);
                MaleView.setVisibility(View.INVISIBLE);
                UnspecifiedView.setVisibility(View.VISIBLE);
                //Toast.makeText(getApplicationContext(), "라디오 그룹 Unspecified 눌렸습니다.", Toast.LENGTH_SHORT).show();
            }

        }
    };

}