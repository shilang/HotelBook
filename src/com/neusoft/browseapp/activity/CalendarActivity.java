package com.neusoft.browseapp.activity;


import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.neusoft.browseapp.ui.DateWidgetDayCell;

public class CalendarActivity extends Activity
  implements View.OnClickListener
{
  public static final int SELECT_DATE_REQUEST = 111;
  public static final int SELECT_ENDDATE_REQUEST = 222;
  private static final String TAG = "CalendarActivity";
  private Calendar calCalendar = Calendar.getInstance();
  private Calendar calSelected = Calendar.getInstance();
  private Calendar calSelectedStartDate = Calendar.getInstance();
  private Calendar calStartDate = Calendar.getInstance();
  private Calendar calToday = Calendar.getInstance();
  private TextView calType;
  private ScrollView calendarLayout;
  private ArrayList<DateWidgetDayCell> days = new ArrayList();
  private int iDaycellSize;
  private int iFirstDayOfWeek;
  private boolean iHotelDate = true;
  private int iMonthViewCurrentMonth = 0;
  private int iMonthViewCurrentYear = 0;
  private boolean iStartDate = true;
  private LinearLayout layContent = null;
  private LinearLayout linearLayout;
  public DateWidgetDayCell.OnItemClick mOnDayCellClick = new CalendarActivity.1(this);
  private TextView monthTitle;
  private ImageView nextIv;
  private ImageView preIv;
  private LinearLayout todayLayout;

  public static long GetSelectedDateOnActivityResult(int paramInt1, int paramInt2, Bundle paramBundle, Calendar paramCalendar)
  {
    long l;
    if ((((paramInt1 == 111) || (paramInt1 == 222))) && (paramInt2 == -1) && (paramBundle.containsKey("date")))
    {
      l = paramBundle.getLong("date");
      if (l == 0L)
        paramCalendar.setTimeInMillis(0L);
    }
    while (true)
    {
      return l;
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTimeInMillis(l);
      paramCalendar.set(1, localCalendar.get(1));
      paramCalendar.set(2, localCalendar.get(2));
      paramCalendar.set(5, localCalendar.get(5));
      continue;
      l = -1L;
    }
  }

  public static void Open(Activity paramActivity, boolean paramBoolean, Calendar paramCalendar, int paramInt)
  {
    Intent localIntent = new Intent(paramActivity, CalendarActivity.class);
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("iHotelDate", paramBoolean);
    localBundle.putBoolean("iStartDate", true);
    localBundle.putLong("date", paramCalendar.getTimeInMillis());
    localBundle.putInt("firstDayOfWeek", paramInt);
    localIntent.putExtras(localBundle);
    paramActivity.startActivityForResult(localIntent, 111);
  }

  public static void OpenFromEndDate(Activity paramActivity, boolean paramBoolean, Calendar paramCalendar, int paramInt, long paramLong)
  {
    Intent localIntent = new Intent(paramActivity, CalendarActivity.class);
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("iHotelDate", paramBoolean);
    localBundle.putBoolean("iStartDate", false);
    localBundle.putLong("calSelectedStartDate", paramLong);
    localBundle.putLong("date", paramCalendar.getTimeInMillis());
    localBundle.putInt("firstDayOfWeek", paramInt);
    localIntent.putExtras(localBundle);
    paramActivity.startActivityForResult(localIntent, 222);
  }

  private void UpdateCurrentMonthDisplay()
  {
    this.monthTitle.setText(this.calStartDate.get(1) + " " + "年" + (1 + this.calStartDate.get(2)) + " " + "月");
  }

  private void UpdateStartDateForMonth()
  {
    this.iMonthViewCurrentMonth = this.calStartDate.get(2);
    this.iMonthViewCurrentYear = this.calStartDate.get(1);
    this.calStartDate.set(5, 1);
    UpdateCurrentMonthDisplay();
    int i = 0;
    int j = this.iFirstDayOfWeek;
    if (j == 2)
    {
      i = -2 + this.calStartDate.get(7);
      if (i < 0)
        i = 6;
    }
    if (j == 1)
    {
      i = -1 + this.calStartDate.get(7);
      if (i < 0)
        i = 6;
    }
    this.calStartDate.add(7, -i);
  }

  private void generateCalendar(LinearLayout paramLinearLayout)
  {
    this.days.clear();
    for (int i = 0; ; ++i)
    {
      if (i >= 6)
        return;
      paramLinearLayout.addView(generateCalendarRow());
    }
  }

  private View generateCalendarRow()
  {
    LinearLayout localLinearLayout = createLayout(0);
    for (int i = 0; ; ++i)
    {
      if (i >= 7)
        return localLinearLayout;
      DateWidgetDayCell localDateWidgetDayCell = new DateWidgetDayCell(this, 0, this.iDaycellSize);
      this.days.add(localDateWidgetDayCell);
      localLinearLayout.addView(localDateWidgetDayCell);
    }
  }

  private Calendar getCalendarStartDate()
  {
    this.calToday.setTimeInMillis(System.currentTimeMillis());
    this.calToday.setFirstDayOfWeek(this.iFirstDayOfWeek);
    if (this.calSelected.getTimeInMillis() == 0L)
    {
      this.calStartDate.setTimeInMillis(System.currentTimeMillis());
      this.calStartDate.setFirstDayOfWeek(this.iFirstDayOfWeek);
    }
    while (true)
    {
      UpdateStartDateForMonth();
      return this.calStartDate;
      this.calStartDate.setTimeInMillis(this.calSelected.getTimeInMillis());
      this.calStartDate.setFirstDayOfWeek(this.iFirstDayOfWeek);
    }
  }

  private void init()
  {
    this.calendarLayout = ((ScrollView)findViewById(2131361806));
    this.calendarLayout.setBackgroundDrawable(BitmapUtil.createRepeater(this));
    this.iDaycellSize = (getWindowManager().getDefaultDisplay().getWidth() / 7);
    this.calSelected.setTimeInMillis(0L);
    this.calToday.setTimeInMillis(System.currentTimeMillis());
    this.iFirstDayOfWeek = 1;
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
    {
      if (localBundle.containsKey("iHotelDate"))
        this.iHotelDate = localBundle.getBoolean("iHotelDate");
      if (localBundle.containsKey("iStartDate"))
        this.iStartDate = localBundle.getBoolean("iStartDate");
      if (localBundle.containsKey("calSelectedStartDate"))
        this.calSelectedStartDate.setTimeInMillis(localBundle.getLong("calSelectedStartDate"));
      if (localBundle.containsKey("date"))
        this.calSelected.setTimeInMillis(localBundle.getLong("date"));
      if (localBundle.containsKey("firstDayOfWeek"))
        this.iFirstDayOfWeek = localBundle.getInt("firstDayOfWeek");
    }
    this.calType = ((TextView)findViewById(2131361805));
    if (this.iStartDate)
      this.calType.setText(getString(2131230743));
    while (true)
    {
      this.monthTitle = ((TextView)findViewById(2131361809));
      this.preIv = ((ImageView)findViewById(2131361808));
      this.preIv.setOnClickListener(this);
      this.nextIv = ((ImageView)findViewById(2131361810));
      this.nextIv.setOnClickListener(this);
      this.todayLayout = ((LinearLayout)findViewById(2131361811));
      this.todayLayout.setOnClickListener(this);
      this.layContent = createLayout(1);
      generateCalendar(this.layContent);
      this.linearLayout = ((LinearLayout)findViewById(2131361807));
      this.linearLayout.addView(this.layContent);
      this.calStartDate = getCalendarStartDate();
      DateWidgetDayCell localDateWidgetDayCell = updateCalendar();
      if (localDateWidgetDayCell != null)
        localDateWidgetDayCell.requestFocus();
      return;
      this.calType.setText(getString(2131230744));
    }
  }

  private DateWidgetDayCell updateCalendar()
  {
    Object localObject = null;
    if (this.calSelected.getTimeInMillis() != 0L);
    int j;
    int k;
    int l;
    int i1;
    for (int i = 1; ; i = 0)
    {
      j = this.calSelected.get(1);
      k = this.calSelected.get(2);
      l = this.calSelected.get(5);
      this.calCalendar.setTimeInMillis(this.calStartDate.getTimeInMillis());
      i1 = 0;
      label62: if (i1 < this.days.size())
        break;
      this.layContent.invalidate();
      return localObject;
    }
    int i2 = this.calCalendar.get(1);
    int i3 = this.calCalendar.get(2);
    int i4 = this.calCalendar.get(5);
    DateWidgetDayCell localDateWidgetDayCell = (DateWidgetDayCell)this.days.get(i1);
    boolean bool1 = false;
    if ((this.calToday.get(1) == i2) && (this.calToday.get(2) == i3) && (this.calToday.get(5) == i4))
      bool1 = true;
    localDateWidgetDayCell.setData(i2, i3, i4, bool1, this.iMonthViewCurrentMonth);
    int i5 = 0;
    if (this.iStartDate)
    {
      int i8 = DateUtil.daysBetween(this.calToday, localDateWidgetDayCell.getDate());
      if ((i8 > 0) && (i8 < 59))
        i5 = 1;
      label233: boolean bool2 = false;
      if ((i != 0) && (l == i4) && (k == i3) && (j == i2))
        bool2 = true;
      localDateWidgetDayCell.setSelected(bool2);
      if (bool2)
        localObject = localDateWidgetDayCell;
      if (i5 != 0)
        break label378;
      localDateWidgetDayCell.setItemClick(null);
      if (i5 == 0)
        break label372;
    }
    for (boolean bool4 = false; ; bool4 = true)
    {
      localDateWidgetDayCell.setDarkColor(bool4);
      label304: this.calCalendar.add(5, 1);
      ++i1;
      break label62:
      int i6 = DateUtil.daysBetween(this.calToday, localDateWidgetDayCell.getDate());
      int i7 = DateUtil.daysBetween(this.calSelectedStartDate, localDateWidgetDayCell.getDate());
      if ((i7 > 0) && (i7 < 60) && (i6 < 60));
      i5 = 1;
      label372: break label233:
    }
    label378: localDateWidgetDayCell.setItemClick(this.mOnDayCellClick);
    if (i5 != 0);
    for (boolean bool3 = false; ; bool3 = true)
    {
      localDateWidgetDayCell.setDarkColor(bool3);
      break label304:
    }
  }

  public void OnClose()
  {
    Bundle localBundle = new Bundle();
    localBundle.putLong("date", this.calSelected.getTimeInMillis());
    Intent localIntent = new Intent("");
    localIntent.putExtras(localBundle);
    setResult(-1, localIntent);
    finish();
  }

  public LinearLayout createLayout(int paramInt)
  {
    LinearLayout localLinearLayout = new LinearLayout(this);
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    localLinearLayout.setOrientation(paramInt);
    localLinearLayout.setBackgroundColor(-8801200);
    return localLinearLayout;
  }

  public void deselectAll()
  {
    this.calSelected.setTimeInMillis(0L);
    for (int i = 0; ; ++i)
    {
      if (i >= this.days.size())
      {
        this.layContent.invalidate();
        return;
      }
      DateWidgetDayCell localDateWidgetDayCell = (DateWidgetDayCell)this.days.get(i);
      if (!localDateWidgetDayCell.getSelected())
        continue;
      localDateWidgetDayCell.setSelected(false);
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131361809:
    default:
    case 2131361808:
    case 2131361810:
    case 2131361811:
    }
    while (true)
    {
      return;
      setPrevViewItem();
      continue;
      setNextViewItem();
      continue;
      setTodayViewItem();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    setContentView(2130903045);
    Utility.log("CalendarActivity", "onCreate");
    ScreenManager.getScreenManager().pushActivity(this);
    init();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
      finish();
    return false;
  }

  public void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  public void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }

  public void setNextViewItem()
  {
    this.iMonthViewCurrentMonth = (1 + this.iMonthViewCurrentMonth);
    if (this.iMonthViewCurrentMonth == 12)
    {
      this.iMonthViewCurrentMonth = 0;
      this.iMonthViewCurrentYear = (1 + this.iMonthViewCurrentYear);
    }
    this.calStartDate.set(5, 1);
    this.calStartDate.set(2, this.iMonthViewCurrentMonth);
    this.calStartDate.set(1, this.iMonthViewCurrentYear);
    UpdateStartDateForMonth();
    updateCalendar();
  }

  public void setPrevViewItem()
  {
    this.iMonthViewCurrentMonth = (-1 + this.iMonthViewCurrentMonth);
    if (this.iMonthViewCurrentMonth == -1)
    {
      this.iMonthViewCurrentMonth = 11;
      this.iMonthViewCurrentYear = (-1 + this.iMonthViewCurrentYear);
    }
    this.calStartDate.set(5, 1);
    this.calStartDate.set(2, this.iMonthViewCurrentMonth);
    this.calStartDate.set(1, this.iMonthViewCurrentYear);
    UpdateStartDateForMonth();
    updateCalendar();
  }

  public void setTodayViewItem()
  {
    this.calToday.setTimeInMillis(System.currentTimeMillis());
    this.calToday.setFirstDayOfWeek(this.iFirstDayOfWeek);
    this.calStartDate.setTimeInMillis(this.calToday.getTimeInMillis());
    this.calStartDate.setFirstDayOfWeek(this.iFirstDayOfWeek);
    UpdateStartDateForMonth();
    updateCalendar();
  }
}