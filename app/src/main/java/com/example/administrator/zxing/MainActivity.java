package com.example.administrator.zxing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends Activity {
    private TextView mTvResult;
    private EditText mInput;
    private ImageView image;
    private CheckBox cb_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mInput = (EditText) findViewById(R.id.et_text);
        image = (ImageView) findViewById(R.id.iv_result);
        cb_logo = (CheckBox) findViewById(R.id.cb_logo);
    }

    public void scan(View view) {
        startActivityForResult(new Intent(MainActivity.this,
                CaptureActivity.class),0);
    }

    public void make(View view) {
        String input = mInput.getText().toString();
        if(input.equals("")) {
            Toast.makeText(MainActivity.this,"文本内容不能为空",Toast.LENGTH_LONG).show();
        }else {
            Bitmap bitmap = EncodingUtils.createQRCode(input,
                    500,500,
                    cb_logo.isChecked()?
                            BitmapFactory.decodeResource(getResources(),R.drawable.me) : null);
            image.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mTvResult.setText(result);
        }
    }
}
