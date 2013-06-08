package com.neusoft.browseapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class QmangoListView extends ListView
{
  public QmangoListView(Context paramContext)
  {
    super(paramContext);
  }

  public QmangoListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public QmangoListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.makeMeasureSpec(536870911, -2147483648);
    setMeasuredDimension(paramInt1, i);
    super.onMeasure(paramInt1, i);
  }
}