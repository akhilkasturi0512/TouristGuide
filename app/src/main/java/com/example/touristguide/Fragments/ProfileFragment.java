package com.example.touristguide.Fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.ProfileResBean;
import com.example.touristguide.ApiCall.Models.UpdateProfileResBean;
import com.example.touristguide.ApiCall.Presenters.ProfilePresenter;
import com.example.touristguide.ApiCall.View.IProfileView;
import com.example.touristguide.BuildConfig;
import com.example.touristguide.R;
import com.example.touristguide.Utils.ApiConstants;
import com.example.touristguide.Utils.NetworkCheck;
import com.example.touristguide.Utils.PermissionCaller;
import com.example.touristguide.Utils.SharedPreferenceData;
import com.example.touristguide.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileFragment extends Fragment implements IProfileView {
    FragmentProfileBinding binding;
    public Uri uriProfile = null;
    public static final int REQUEST_CAPTURE = 1001;
    public Uri captureMediaFile = null;

    ProfilePresenter presenter;
    ProfileResBean profileResBean;
    SharedPreferenceData profileData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false);

        presenter = new ProfilePresenter();
        presenter.setView(this);
        profileData = new SharedPreferenceData(getContext());

        binding.edtName.setText(profileData.getUser_name());
        binding.edtEmail.setText(profileData.getEmail());
        binding.edtMobile.setText(profileData.getMobile_no());
        Picasso.get().load(ApiConstants.BASE_IMAGE_URL+profileData.getProfile_image()).into(binding.imgProfile);

       /* if(NetworkCheck.isConnected(getContext())){
            presenter.ProfileCall(getActivity(),profileData.getACCESS_TOKEN());
        }else{
            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }*/

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtName.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                } else if (binding.edtEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                } else if (binding.edtMobile.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please enter mobile number", Toast.LENGTH_SHORT).show();
                } else if (binding.edtMobile.getText().toString().length() != 10) {
                    Toast.makeText(getContext(), "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
                } else if (NetworkCheck.isConnected(getContext())) {

                    MultipartBody.Part user_image = null;

                    if (uriProfile != null) {
                        String fileName = new File(uriProfile.getPath()).getName();
                        File actualfile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                                new File(uriProfile.getPath()).getName());
                        user_image = MultipartBody.Part.createFormData("profile_pic", fileName, RequestBody.
                                create(MediaType.parse("multipart/form_Data"), actualfile));

                        presenter.UpdateProfileCall(getActivity(), profileData.getACCESS_TOKEN(), binding.edtName.getText().toString(), binding.edtMobile.getText().toString(),
                                binding.edtEmail.getText().toString(), user_image);
                    } else {

                        presenter.UpdateProfileCall(getActivity(), profileData.getACCESS_TOKEN(), binding.edtName.getText().toString(), binding.edtMobile.getText().toString(),
                                binding.edtEmail.getText().toString(), user_image);
                    }

                } else {
                    NetworkCheck.showNetworkFailureAlert(getContext());
                }
            }

        });

        binding.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpictureDialog();

            }
        });

        return binding.getRoot();
    }

    private void showpictureDialog() {

        android.app.AlertDialog.Builder pictureDialog = new android.app.AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Take photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                pickImageFromCamera();
                                break;
                        }
                    }
                });

        pictureDialog.show();
    }

    private void pickImageFromCamera() {

        if (!PermissionCaller.getInstance(getActivity()).isListOfPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA}, REQUEST_CAPTURE))
            return;

        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            int random =  (int)(Math.random()*(1000-0+1)+0);
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            path.mkdir();
            String photoFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/images" + random + ".jpg";
            File imageFile = new File(photoFilePath);

            captureMediaFile = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", imageFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, captureMediaFile);

            List<ResolveInfo> resInfoList = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                getActivity().grantUriPermission(packageName, captureMediaFile, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CAPTURE:
                    if (captureMediaFile != null) {
                        uriProfile = captureMediaFile;
                        binding.imgProfile.setImageURI(null);
                        binding.imgProfile.setImageURI(uriProfile);
                    }
                    break;
            }
        }
    }

    @Override
    public void onProfileSuccess(ProfileResBean item) {

        if(item.isStatus()){
            /*profileResBean = item;
            Picasso.get().load(ApiConstants.BASE_IMAGE_URL + item.getData().getProfileImage()).into(binding.imgProfile);
            binding.edtName.setText(item.getData().getName());
            binding.edtEmail.setText(item.getData().getEmail());
            binding.edtMobile.setText(item.getData().getMobile());*/

        }
    }

    @Override
    public void onUpdateProfileSuccess(UpdateProfileResBean item) {

        if(item.isStatus()){
            new SharedPreferenceData(getActivity()).SavedUpdateProfileData(item);
            Toast.makeText(getContext(), item.getMessage(), Toast.LENGTH_LONG).show();
            /*Picasso.get().load(ApiConstants.BASE_IMAGE_URL + item.getData().getProfileImage()).into(binding.imgProfile);
            binding.edtName.setText(item.getData().getName());
            binding.edtEmail.setText(item.getData().getEmail());
            binding.edtMobile.setText(item.getData().getMobile());*/
        }
    }

    @Override
    public Context getContext(){
        return getActivity();
    }

    @Override
    public void enableLoadingBar(Context context, boolean enable) {

    }

    @Override
    public void onError(String reason) {

    }
}