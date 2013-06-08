package com.neusoft.browseapp.ui;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

import android.graphics.Bitmap;

public class BmpMemoy {

	private static final int HARD_CACHE_CAPACITY = 0;
	private final HashMap<String, Bitmap>mHardBitmapCache = new LinkedHashMap<String, Bitmap>(HARD_CACHE_CAPACITY/ 2, 0.75f, true) {  
        @Override  
        protected boolean removeEldestEntry(LinkedHashMap.Entry<String, Bitmap> eldest) {  
            if (size() >HARD_CACHE_CAPACITY) {  
               //当map的size大于30时，把最近不常用的key放到mSoftBitmapCache中，从而保证mHardBitmapCache的效率  
               mSoftBitmapCache.put(eldest.getKey(), new SoftReference<Bitmap>(eldest.getValue()));  
                return true;  
            } else  
                return false;  
        }  
    };  
    
    /** 
     *当mHardBitmapCache的key大于30的时候，会根据LRU算法把最近没有被使用的key放入到这个缓存中。 
     *Bitmap使用了SoftReference，当内存空间不足时，此cache中的bitmap会被垃圾回收掉 
     */  
    private final static ConcurrentHashMap<String, SoftReference<Bitmap>> mSoftBitmapCache =new ConcurrentHashMap<String,SoftReference<Bitmap>>(HARD_CACHE_CAPACITY / 2);
	public static final String TAG = null;  
    
    /** 
     * 从缓存中获取图片 
     */  
    private Bitmap getBitmapFromCache(String url) {  
        // 先从mHardBitmapCache缓存中获取  
        synchronized (mHardBitmapCache) {  
            final Bitmap bitmap =mHardBitmapCache.get(url);  
            if (bitmap != null) {  
                //如果找到的话，把元素移到linkedhashmap的最前面，从而保证在LRU算法中是最后被删除  
                mHardBitmapCache.remove(url);  
                mHardBitmapCache.put(url,bitmap);  
                return bitmap;  
            }  
        }  
        //如果mHardBitmapCache中找不到，到mSoftBitmapCache中找  
        SoftReference<Bitmap>bitmapReference = mSoftBitmapCache.get(url);  
        if (bitmapReference != null) {  
            final Bitmap bitmap =bitmapReference.get();  
            if (bitmap != null) {  
                return bitmap;  
            } else {  
                mSoftBitmapCache.remove(url);  
            }  
        }  
        return null;  
    }  
}
    
