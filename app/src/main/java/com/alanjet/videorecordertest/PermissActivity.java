package com.alanjet.videorecordertest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class PermissActivity extends AppCompatActivity {
  private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permiss);
        mContext=this;
        reqPer();
    }
    private void showToast(String text) {
        Toast.makeText(PermissActivity.this, text, Toast.LENGTH_SHORT).show();
    }
    private void reqPer(){

        List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
        permissonItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取权限", R.drawable.permission_ic_phone));
        permissonItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入权限", R.drawable.permission_ic_phone));
        permissonItems.add(new PermissionItem(Manifest.permission.RECORD_AUDIO, "录音权限", R.drawable.permission_ic_phone));
        permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "拍照权限", R.drawable.permission_ic_phone));
        HiPermission.create(mContext)
                .permissions(permissonItems)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        showToast(getString(R.string.permission_on_close));
                    }

                    @Override
                    public void onFinish() {
                        showToast(getString(R.string.permission_completed));
                        Intent intent =new Intent(mContext,MainActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onDeny(String permisson, int position) {
                    }

                    @Override
                    public void onGuarantee(String permisson, int position) {
                    }
                });
    }
}
