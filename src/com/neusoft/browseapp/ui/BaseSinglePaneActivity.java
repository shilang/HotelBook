package com.neusoft.browseapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public abstract class BaseSinglePaneActivity extends FragmentActivity
{
  private Fragment mFragment;

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    setContentView(2130903041);
    if (paramBundle != null)
      return;
    this.mFragment = onCreatePane();
    getSupportFragmentManager().beginTransaction().add(2131361795, this.mFragment).commit();
  }

  protected abstract Fragment onCreatePane();
}