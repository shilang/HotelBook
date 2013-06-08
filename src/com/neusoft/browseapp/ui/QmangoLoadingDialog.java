package com.neusoft.browseapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

public class QmangoLoadingDialog extends Dialog
{
  private static int default_height;
  private static int default_width = 320;

  static
  {
    default_height = 120;
  }

  public QmangoLoadingDialog(Context paramContext, int paramInt1, int paramInt2)
  {
    this(paramContext, default_width, default_height, paramInt1, paramInt2);
  }

  public QmangoLoadingDialog(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramContext, paramInt2);
    setContentView(paramInt1);
    Window localWindow = getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    localLayoutParams.width = -1;
    localLayoutParams.height = -2;
    localLayoutParams.gravity = paramInt3;
    localLayoutParams.alpha = 1.0F;
    localWindow.setAttributes(localLayoutParams);
  }

  public QmangoLoadingDialog(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramContext, paramInt4);
    setContentView(paramInt3);
    Window localWindow = getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    float f = getDensity(paramContext);
    localLayoutParams.width = (int)(f * paramInt1);
    localLayoutParams.height = (int)(f * paramInt2);
    localLayoutParams.gravity = 17;
    localLayoutParams.alpha = 1.0F;
    localWindow.setAttributes(localLayoutParams);
  }

  private float getDensity(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().density;
  }
}
