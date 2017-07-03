package com.trust.ebikeapp.tool.updateapp;




/**
 * Created by Trust on 2017/6/19.
 */

public class APPDownLoad {
    /*
    String cachePath;
    String obbPath;

    boolean first = false;
    public void downLoad(final Context context){

        final ProgressDialog pd;    //进度条对话框

        pd = new  ProgressDialog(context);

        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        pd.setMessage("正在下载更新");

        pd.show();
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File paht = Environment.getExternalStorageDirectory();
            rx.Subscription subscription = RxDownload.getInstance()
                    .maxThread(10)     //设置最大线程
                    .maxRetryCount(10) //设置下载失败重试次数
                    .defaultSavePath(Environment.getExternalStorageDirectory().getAbsolutePath())//设置默认的下载路径
                    .download("https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk","QQ.apk",null) //开始下载
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<DownloadStatus>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("lhh","下载失败:"+e.toString());
                        }

                        @Override
                        public void onNext(final DownloadStatus status) {
                            synchronized (this){
                                pd.setMax((int) status.getTotalSize());
                                Log.d("lhh","下载的进度:"+status.getPercent());
                                pd.setProgress((int) status.getDownloadSize());
                                if(status.getPercent().equals("100.00%") && !first){
                                    pd.dismiss();
                                    first = true;
                                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "QQ.apk");
                                    installApk(file,context);
                                }
                            }

                        }
                    });
        }else{
            Log.e("lhh","Environment.getExternalStorageState():"+Environment.getExternalStorageState());
        }






    }

    //安装apk

    public void installApk(File file, Context context) {

        Intent intent = new Intent();

        //执行动作

        intent.setAction(Intent.ACTION_VIEW);

        //执行的数据类型

        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");

        context.startActivity(intent);



    }


    public  String getSystemFilePath(Context context) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
//            cachePath = context.getExternalCacheDir().getPath();//也可以这么写，只是返回的路径不一样，具体打log看
        } else {
            cachePath = context.getFilesDir().getAbsolutePath();
//            cachePath = context.getCacheDir().getPath();//也可以这么写，只是返回的路径不一样，具体打log看
        }

        obbPath = context.getObbDir().getAbsolutePath();
        Log.v("lhh", "obbPath = " + obbPath);
        Log.v("lhh", "cachePath = " + cachePath);

        downLoad(context);

        return cachePath;
    }
*/

}
