//package com.android.myapplicationtest.util;
//
//import android.app.Activity;
//import android.arch.lifecycle.MutableLiveData;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.provider.MediaStore;
//import android.support.v4.app.Fragment;
//
//import com.ichangtou.model.CropResult;
//
///**
// * @author xyjian
// * @desc 拍照，选择照片 builder 模式
// * @createtime: 2019/3/15
// * @updatetime:
// */
//public class TakePicture {
//
//    private static final int REQUEST_ALBUM = 88;//从相册获取照片请求码
//    private static final int REQUEST_CAMERA = 99;//从相机拍照 请求码
//    private static final int REQUEST_VIDEO = 111;//从相机录像 请求码
//    private Uri mCameraFile;//这个是内部使用的，不需要外部构建
//    private Uri mCameraVideoFile;//这个是内部使用的，不需要外部构建
//    private MutableLiveData<CropResult> mLiveData;//
//
//    private TakePicture() {
//
//    }
//
//    /**
//     * 从本地相册获取照片：
//     *
//     * @param activity
//     */
//    public void startAlbum(Activity activity) {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        activity.startActivityForResult(intent, REQUEST_ALBUM);
//    }
//
//    public void startAlbum(Fragment fragment) {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        fragment.startActivityForResult(intent, REQUEST_ALBUM);
//    }
//
//    /**
//     * 拍照
//     *
//     * @param activity
//     */
//    public void startCamera(Activity activity) {
//        mCameraFile = createImageUri(activity);
//        Intent intent = new Intent();
//        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCameraFile);//如果不设置EXTRA_OUTPUT getData()  获取的是bitmap数据  是压缩后的
//        activity.startActivityForResult(intent, REQUEST_CAMERA);
//    }
//
//    public void startCamera(Fragment fragment) {
//        mCameraFile = createImageUri(fragment.getContext());
//        Intent intent = new Intent();
//        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCameraFile);//如果不设置EXTRA_OUTPUT getData()  获取的是bitmap数据  是压缩后的
//        fragment.startActivityForResult(intent, REQUEST_CAMERA);
//    }
//
//    private Uri createImageUri(Context context) {
//        String name = "takePhoto" + System.currentTimeMillis();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MediaStore.Images.Media.TITLE, name);
//        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpeg");
//        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
//        return uri;
//    }
//
//    private Uri createVideoUri(Context context) {
//        String name = "takeVideo" + System.currentTimeMillis();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MediaStore.Video.Media.TITLE, name);
//        contentValues.put(MediaStore.Video.Media.DISPLAY_NAME, name + ".mp4");
//        contentValues.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
//        Uri uri = context.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
//        return uri;
//    }
//
//
//    public void startVideo(Fragment fragment) {
//        mCameraVideoFile = createVideoUri(fragment.getContext());
//        Intent intent = new Intent();
//        intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCameraVideoFile);//如果不设置EXTRA_OUTPUT getData()  获取的是bitmap数据  是压缩后的
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        fragment.startActivityForResult(intent, REQUEST_VIDEO);
//    }
//    public void startVideo(Activity activity) {
//        mCameraVideoFile = createVideoUri(activity);
//        Intent intent = new Intent();
//        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCameraVideoFile);//如果不设置EXTRA_OUTPUT getData()  获取的是bitmap数据  是压缩后的
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        activity.startActivityForResult(intent, REQUEST_VIDEO);
//    }
//
//
//    public MutableLiveData<CropResult> getLiveData() {
//        return mLiveData;
//    }
//
//    private void setLiveData(MutableLiveData<CropResult> mLiveData) {
//        this.mLiveData = mLiveData;
//    }
//
//    /**
//     * @author xyjian
//     * @desc 裁剪图片 后的回调
//     * @createtime: 2019/3/15
//     * @updatetime:
//     */
//    public void dealPhotoResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != Activity.RESULT_OK) {
//            setResult(3, "取消", null);
//            return;
//        }
//        if (requestCode == REQUEST_CAMERA) {
//            if (mCameraFile != null) {
//                setResult(1, mCameraFile.getPath(), mCameraFile);
//            } else {
//                setResult(3, "mCameraFile为null", null);
//            }
//
//        } else if (requestCode == REQUEST_ALBUM) {
//            if (data != null && data.getData() != null) {
//                setResult(1, data.getData().getPath(), data.getData());
//            } else {
//                setResult(3, "data为null", null);
//            }
//        }else if (requestCode == REQUEST_VIDEO) {
//            if (mCameraVideoFile != null) {
//                setResult(1,mCameraVideoFile.getPath(), mCameraVideoFile);
//            } else {
//                setResult(3, "data为null", null);
//            }
//        }
//    }
//
//    private void setResult(int resultCode, String imagePath, Uri imageUri) {
//        if (mLiveData != null) {
//            mLiveData.setValue(new CropResult(resultCode, imagePath).setResultUri(imageUri));
//        }
//    }
//
//    public static class Builder {
//
//        private TakePicture mTakePicture;
//
//        public Builder() {
//            mTakePicture = new TakePicture();
//        }
//
//        public Builder setResultLiveData(MutableLiveData<CropResult> liveData) {
//            mTakePicture.setLiveData(liveData);
//            return this;
//        }
//
//        public TakePicture build() {
//            return mTakePicture;
//        }
//    }
//
//}
