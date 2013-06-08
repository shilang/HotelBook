package com.neusoft.browseapp.util;


import java.lang.reflect.Field;
import java.security.MessageDigest;

import android.app.ProgressDialog;
import android.content.DialogInterface;

public class Utility
{
  public static void allowDialogDismissed(DialogInterface paramDialogInterface, boolean paramBoolean)
  {
    try
    {
      Class localClass = paramDialogInterface.getClass().getSuperclass();
      if (paramDialogInterface instanceof ProgressDialog)
        localClass = localClass.getSuperclass();
      Field localField = localClass.getDeclaredField("mShowing");
      localField.setAccessible(true);
      localField.set(paramDialogInterface, Boolean.valueOf(paramBoolean));
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static void dbug(String paramString)
  {
  }

  public static String getMD5Hash(String paramString)
  {
    Object localObject;
    try
    {
      char[] arrayOfChar1 = new char[16];
      arrayOfChar1[0] = 48;
      arrayOfChar1[1] = 49;
      arrayOfChar1[2] = 50;
      arrayOfChar1[3] = 51;
      arrayOfChar1[4] = 52;
      arrayOfChar1[5] = 53;
      arrayOfChar1[6] = 54;
      arrayOfChar1[7] = 55;
      arrayOfChar1[8] = 56;
      arrayOfChar1[9] = 57;
      arrayOfChar1[10] = 97;
      arrayOfChar1[11] = 98;
      arrayOfChar1[12] = 99;
      arrayOfChar1[13] = 100;
      arrayOfChar1[14] = 101;
      arrayOfChar1[15] = 102;
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      byte[] arrayOfByte1 = paramString.getBytes();
      localMessageDigest.update(arrayOfByte1, 0, arrayOfByte1.length);
      byte[] arrayOfByte2 = new byte[16];
      int i = localMessageDigest.digest(arrayOfByte2, 0, arrayOfByte2.length);
      char[] arrayOfChar2 = new char[i * 2];
      int j = 0;
      int k = 0;
      while (true)
      {
        if (j >= i)
        {
          localObject = new String(arrayOfChar2);
          if (((String)localObject).length() < 32)
            break;
        }
        int l = arrayOfByte2[j];
        int i1 = k + 1;
        arrayOfChar2[k] = arrayOfChar1[(0xF & l >>> 4)];
        k = i1 + 1;
        arrayOfChar2[i1] = arrayOfChar1[(l & 0xF)];
        ++j;
      }
      String str = "0" + (String)localObject;
      localObject = str;
    }
    catch (Exception localException)
    {
      localObject = null;
    }
    label259: return (String)localObject;
  }

  public static void log(String paramString1, String paramString2)
  {
  }

  public static void sleep(int paramInt)
  {
    long l = paramInt;
    try
    {
      Thread.sleep(l);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
  }

  public static void system(String paramString1, String paramString2)
  {
  }
}