// Generated code from Butter Knife. Do not modify!
package com.example.a.myapplication12321321;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GreenDaoActivity_ViewBinding implements Unbinder {
  private GreenDaoActivity target;

  @UiThread
  public GreenDaoActivity_ViewBinding(GreenDaoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GreenDaoActivity_ViewBinding(GreenDaoActivity target, View source) {
    this.target = target;

    target.btnInsertdata = Utils.findRequiredViewAsType(source, R.id.btn_insertdata, "field 'btnInsertdata'", Button.class);
    target.btnDeletedata = Utils.findRequiredViewAsType(source, R.id.btn_deletedata, "field 'btnDeletedata'", Button.class);
    target.btnUpdatedata = Utils.findRequiredViewAsType(source, R.id.btn_updatedata, "field 'btnUpdatedata'", Button.class);
    target.btnQuerydata = Utils.findRequiredViewAsType(source, R.id.btn_querydata, "field 'btnQuerydata'", Button.class);
    target.btnQuerythroughcondition = Utils.findRequiredViewAsType(source, R.id.btn_querythroughcondition, "field 'btnQuerythroughcondition'", Button.class);
    target.dataRecyclerview = Utils.findRequiredViewAsType(source, R.id.data_recyclerview, "field 'dataRecyclerview'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GreenDaoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnInsertdata = null;
    target.btnDeletedata = null;
    target.btnUpdatedata = null;
    target.btnQuerydata = null;
    target.btnQuerythroughcondition = null;
    target.dataRecyclerview = null;
  }
}
