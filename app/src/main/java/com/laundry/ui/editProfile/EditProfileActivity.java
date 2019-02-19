package com.laundry.ui.editProfile;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.maps.internal.ApiResponse;
import com.laundry.R;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityEditProfileBinding;
import com.laundry.ui.editProfile.vo.EditProfileResponse;
import com.laundry.ui.profile.ProfileActivity;
import com.laundry.ui.settings.vo.SettingResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    private ActivityEditProfileBinding binding;
    private boolean isVisible = true;
    private static String TAG = EditProfileActivity.class.getName();

    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null, bitmapString = "a";
    Uri selectedImage;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    private double latitude, longitude;
    byte[] byteArray;
    String userName, phoneNo, userId;
    ByteArrayOutputStream bytes;
    String base_64_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);

        getUser_Id();
        init();

    }

    private void init() {
        binding.editBackIv.setOnClickListener(this);
        binding.saveBtnTv.setOnClickListener(this);
//        binding.eyeImage.setOnClickListener(this);
        binding.selectImageIv.setOnClickListener(this);

    }

    private void getUser_Id() {
        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        userId = mySharedPreference.getUserId();
        Log.e("MyUserId", userId);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.edit_back_iv:
                onBackPressed();
                break;

            case R.id.save_btn_tv:

                if (isNetworkConnected(this)) {
                    callEditProfileApi();
                } else {
                    Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
                }

              /*  Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);*/
                break;

            case R.id.select_image_iv:
                setImageFromCamera();
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setImageFromCamera() {

        try {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    callCamera();
                }
            } else {
                callCamera();
            }
//            } else
//                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    private void callCamera() {


        PackageManager pm = getPackageManager();
//            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
//            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
        final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    dialog.dismiss();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, PICK_IMAGE_CAMERA);
                } else if (options[item].equals("Choose From Gallery")) {
                    dialog.dismiss();
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {
                selectedImage = data.getData();
                bitmap = (Bitmap) data.getExtras().get("data");
                bytes = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                Log.e("Activity", "Pick from Camera::>>> ");

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                destination = new File(Environment.getExternalStorageDirectory() + "/" +
                        getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imgPath = destination.getAbsolutePath();
                binding.userImageIv.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                bytes = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");
             /*  byteArray = bytes.toByteArray();
                base_64_image = Base64.encodeToString(byteArray, Base64.DEFAULT);
*/
                imgPath = getRealPathFromURI(selectedImage);
                destination = new File(imgPath.toString());
                binding.userImageIv.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    private void callEditProfileApi() {

        new Utility().showProgressDialog(this);
        userName = binding.nameEt.getText().toString().trim();
        phoneNo = binding.phoneNoEt.getText().toString().trim();


        bitmapString = Utility.BitMapToString(bitmap);
//        if (PICK_IMAGE_CAMERA==1 &&PICK_IMAGE_GALLERY==2){
        binding.userImageIv.setImageBitmap(bitmap);
        base_64_image = bitmapString;


        Call<EditProfileResponse> call = APIClient.getInstance().getApiInterface().editProile(userId /*company_id*/, userName,/*"1"*/ phoneNo, base_64_image);
        Log.e("", call.request().url().toString());
        new ResponseListner(this).getResponse(call);


    }


    @Override
    public void onApiResponse(Object response) {

        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof EditProfileResponse) {
                    EditProfileResponse editProfileResponse = (EditProfileResponse) response;
                    new Utility().hideDialog();
                    if (editProfileResponse.isStatus()) {

                        Toast.makeText(this, editProfileResponse.getMsg(), Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(ChangePaawordActivity.this, DryCleanerActivity.class);
//                        startActivity(i);


                    }
                }

            } catch (Exception e) {
                Log.d("TAG", "onApiResponse: " + e.getMessage());
            }
        } else {
            new Utility().hideDialog();
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onApiFailure(String message) {

        new Utility().hideDialog();
        Log.d(TAG, "onApiFailure: " + message);

    }
//    String username = getIntent().getExtras().getString("username");
}
