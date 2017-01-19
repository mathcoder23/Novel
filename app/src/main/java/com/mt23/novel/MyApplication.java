package com.mt23.novel;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.provider.SyncStateContract;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by mathcoder23 on 11/9/16.
 */
public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initImageLoader(this);
    }
    public static DisplayImageOptions getOption(){
        DisplayImageOptions option=new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.novel_cover)//设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.novel_cover)//设置图片uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.novel_cover)//设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)//设置图片在加载前是否重置、复位
//.delayBeforeLoading(1000)//下载前的延迟时间
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在sd卡中
                .considerExifParams(false)//思考可交换的参数
//                .imageScaleType(ImageScaleType.)//设置图片的显示比例
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
//                .displayer(new RoundedBitmapDisplayer(40))//设置图片的圆角半径
//                .displayer(new FadeInBitmapDisplayer(3000))//设置图片显示的透明度过程的时间
                .build();

        return option;
    }

    public static void initImageLoader(Context context) {

        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(getOption())
//                .defaultDisplayImageOptions(d)
                .writeDebugLogs() // Remove for release app
                .diskCacheSize(100 * 1024 * 1024) //硬盘的缓存大小。
                .diskCacheFileCount(1000) //缓存的File数量
                .diskCache(new UnlimitedDiscCache(context.getExternalCacheDir()))// 自定义缓存路径
                .build();
//		 Initialize ImageLoader with configuration.
        if(ImageLoader.getInstance().isInited())
            ImageLoader.getInstance().destroy();
        ImageLoader.getInstance().init(config);
    }
}
