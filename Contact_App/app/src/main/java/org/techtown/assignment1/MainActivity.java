package org.techtown.assignment1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //******************************************************
    //이름: TaeKyu, Kang
    //학번: -
    //설명: MainActivity.java - 메인 뷰를 볼 수 있는 화면
    //******************************************************

    ArrayList<String> nameList = new ArrayList();
    ArrayList<String> phoneList = new ArrayList();
    ArrayList<String> imageList = new ArrayList();
    LayoutInflater layoutInflater;
    LinearLayout Container;
    View view;
    Context context;

    public static final int REQUEST_CODE_MENU = 101;
    public static final String KEY_SIMPLE_DATA = "data";

    private int i = 0;
    int t = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        //ADD CONTACT 버튼 클릭시
        Button create_button = findViewById(R.id.create_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), create_activity.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });



        //EXIT 버튼 클릭시
        Button exit_button = findViewById(R.id.Exit_bt);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit");
                builder.setMessage("Would like to exit the App ??");
                builder.setPositiveButton("YES, EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){

                        finish();

                    }
                });
                builder.setNegativeButton("NO, CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });



    }
    @Override
    public void onBackPressed() {
    }


    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "onStart 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "onDestroy 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this, "onPause 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "onResume 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MENU) {

            if (resultCode == RESULT_OK) {

                if(data.getStringExtra("cancel").equals("c")){
                    String name = data.getStringExtra("name");
                    String phone = data.getStringExtra("phone");
                    String image = data.getStringExtra("image");

                    nameList.add(name);
                    phoneList.add(phone);
                    imageList.add(image);

                    //Toast.makeText(this, "응답으로 전달된 name : " + image, Toast.LENGTH_LONG).show();

                    Container = findViewById(R.id.Container);
                    layoutInflater =  (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


                    if (nameList.size() != 0){
                        view =layoutInflater.inflate(R.layout.sub1, null, false);

                        ImageView male = view.findViewById(R.id.male);
                        ImageView female = view.findViewById(R.id.female);
                        ImageView unspecified = view.findViewById(R.id.unspecified);


                        TextView nameText = view.findViewById(R.id.Name2);
                        nameText.setText(nameList.get(t));

                        TextView namePhone = view.findViewById(R.id.Phone2);
                        namePhone.setText(phoneList.get(t));

                        Button button = view.findViewById(R.id.call2);
                        String txt =  phoneList.get(t);

                        //전화버튼 클릭시 반응
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", txt, null));
                                startActivity(intent);
                            }
                        });

                        if(imageList.get(t).equals("male")){
                            female.setVisibility(View.INVISIBLE);
                            male.setVisibility(View.VISIBLE);
                            unspecified.setVisibility(View.INVISIBLE);
                        }
                        else if(imageList.get(t).equals("female")){
                            male.setVisibility(View.INVISIBLE);
                            female.setVisibility(View.VISIBLE);
                            unspecified.setVisibility(View.INVISIBLE);
                        }
                        else if(imageList.get(t).equals("unspecifed")){
                            male.setVisibility(View.INVISIBLE);
                            female.setVisibility(View.INVISIBLE);
                            unspecified.setVisibility(View.VISIBLE);
                        }

                        Container.addView(view);

                        t = t+1;
                    }

                }
                else if(data.getStringExtra("cancel").equals("a")) {

                }

            }
            else{
                //Toast.makeText(this, "onActivityResult 호출됨. Request code : " + requestCode + ", Result code : "+ resultCode, Toast.LENGTH_LONG).show();
            }
        }
    }
}

