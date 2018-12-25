// Generated code from Butter Knife. Do not modify!
package com.example.a.myapplication12321321;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecyclerActivity_ViewBinding implements Unbinder {
  private RecyclerActivity target;

  @UiThread
  public RecyclerActivity_ViewBinding(RecyclerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RecyclerActivity_ViewBinding(RecyclerActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecyclerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
  }
}
