package com.neusoft.browseapp.util;


import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class BitmapUtil
{
  private static final String TAG = "BitmapUtil";
  private static BitmapUtil bitmapUtil;

  public static Drawable createRepeater(Activity paramActivity)
  {
    BitmapDrawable localBitmapDrawable = new BitmapDrawable(BitmapFactory.decodeResource(paramActivity.getResources(), 2130837550));
    localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    localBitmapDrawable.setDither(true);
    return localBitmapDrawable;
  }

  public static Drawable drawMemberTop(Activity paramActivity, int paramInt)
  {
    BitmapDrawable localBitmapDrawable = new BitmapDrawable(BitmapFactory.decodeResource(paramActivity.getResources(), paramInt));
    localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    localBitmapDrawable.setDither(true);
    return localBitmapDrawable;
  }

  public static BitmapUtil getInstance()
  {
    if (bitmapUtil == null)
      bitmapUtil = new BitmapUtil();
    return bitmapUtil;
  }

  public Bitmap getBitmapByPath(String paramString)
  {
    Bitmap localBitmap = null;
    if ((paramString == null) || (paramString.equals("")))
      label15: return localBitmap;
    while (true)
    {
      File localFile;
      BitmapFactory.Options localOptions;
      try
      {
        localFile = new File(paramString);
        if ((localFile.exists()) && (localFile.length() > 0L));
        localOptions = new BitmapFactory.Options();
        if (localFile.length() < 50000L)
        {
          localOptions.inSampleSize = 2;
          localBitmap = BitmapFactory.decodeFile(localFile.getPath(), localOptions);
        }
        if (localFile.length() >= 100000L)
          break label119;
        label119: localOptions.inSampleSize = 4;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        Utility.log("BitmapUtil", localOutOfMemoryError.getMessage());
        System.gc();
        break label15:
        if (localFile.length() >= 200000L)
          break label158;
        localOptions.inSampleSize = 6;
      }
      catch (Exception localException)
      {
        Utility.log("BitmapUtil", localException.getMessage());
        System.gc();
      }
      break label15:
      if (localFile.length() < 442500L)
        label158: localOptions.inSampleSize = 8;
      if (localFile.length() < 885000L)
        localOptions.inSampleSize = 10;
      if (localFile.length() < 1770000L)
        localOptions.inSampleSize = 12;
      if (localFile.length() < 3540000L)
        localOptions.inSampleSize = 14;
      localOptions.inSampleSize = 20;
    }
  }

  public Bitmap getBitmapByWidth(String paramString, int paramInt)
  {
    Object localObject = null;
    if ((paramString == null) || (paramString.equals("")));
    while (true)
    {
      label15: return localObject;
      try
      {
        File localFile = new File(paramString);
        if ((localFile.exists()) && (localFile.length() > 0L));
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(paramString, localOptions);
        int i = (int)(localOptions.outWidth / paramInt);
        if (i <= 0)
        {
          j = 2;
          localOptions.inSampleSize = j;
          localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
          localOptions.inPurgeable = true;
          localOptions.inInputShareable = true;
          localOptions.inJustDecodeBounds = false;
          Bitmap localBitmap = BitmapFactory.decodeFile(paramString, localOptions);
          localObject = localBitmap;
        }
        int j = i + 4;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        Utility.log("BitmapUtil", localOutOfMemoryError.getMessage());
        System.gc();
        break label15:
      }
      catch (Exception localException)
      {
        Utility.log("BitmapUtil", localException.getMessage());
        System.gc();
        break label15:
      }
    }
  }

  public Drawable getDrawableByPath(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")));
    for (BitmapDrawable localBitmapDrawable = null; ; localBitmapDrawable = null)
    {
      label15: return localBitmapDrawable;
      while (true)
      {
        File localFile;
        BitmapFactory.Options localOptions;
        try
        {
          localFile = new File(paramString);
          if ((!localFile.exists()) || (localFile.length() <= 0L))
            break label263;
          localOptions = new BitmapFactory.Options();
          if (localFile.length() < 50000L)
          {
            localOptions.inSampleSize = 1;
            localBitmapDrawable = new BitmapDrawable(BitmapFactory.decodeFile(localFile.getPath(), localOptions));
          }
          if (localFile.length() >= 100000L)
            break label130;
          label130: localOptions.inSampleSize = 2;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          Utility.log("BitmapUtil", localOutOfMemoryError.getMessage());
          System.gc();
          localBitmapDrawable = null;
          break label15:
          if (localFile.length() >= 200000L)
            break label170;
          localOptions.inSampleSize = 3;
        }
        catch (Exception localException)
        {
          Utility.log("BitmapUtil", localException.getMessage());
          System.gc();
          localBitmapDrawable = null;
        }
        break label15:
        if (localFile.length() < 442500L)
          label170: localOptions.inSampleSize = 4;
        if (localFile.length() < 885000L)
          localOptions.inSampleSize = 6;
        if (localFile.length() < 1770000L)
          localOptions.inSampleSize = 10;
        if (localFile.length() < 3540000L)
          localOptions.inSampleSize = 12;
        label263: localOptions.inSampleSize = 19;
      }
    }
  }

  public Bitmap getThumbnailBitmapByPath(String paramString, int paramInt)
  {
    Object localObject = null;
    if ((paramString == null) || (paramString.equals("")));
    while (true)
    {
      return localObject;
      try
      {
        File localFile = new File(paramString);
        if ((localFile.exists()) && (localFile.length() > 0L));
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(paramString, localOptions);
        int i = (int)(localOptions.outHeight / paramInt);
        if (i <= 0)
          i = 1;
        localOptions.inSampleSize = i;
        localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        localOptions.inPurgeable = true;
        localOptions.inInputShareable = true;
        localOptions.inJustDecodeBounds = false;
        Bitmap localBitmap = BitmapFactory.decodeFile(paramString, localOptions);
        localObject = localBitmap;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        Utility.log("BitmapUtil", localOutOfMemoryError.getMessage());
        System.gc();
      }
      catch (Exception localException)
      {
        Utility.log("BitmapUtil", localException.getMessage());
        System.gc();
      }
    }
  }

  public Bitmap readLocalBitmap(Context paramContext, int paramInt)
  {
    Object localObject = null;
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
      localOptions.inPurgeable = true;
      localOptions.inInputShareable = true;
      Bitmap localBitmap = BitmapFactory.decodeStream(paramContext.getResources().openRawResource(paramInt), null, localOptions);
      localObject = localBitmap;
      return localObject;
    }
    catch (Exception localException)
    {
      Utility.log("BitmapUtil", localException.getMessage());
    }
  }
}
