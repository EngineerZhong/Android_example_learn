// Generated code from Butter Knife. Do not modify!
package com.example.a.myapplication12321321.rxjava.rx_pubu_fragment_zrn;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.a.myapplication12321321.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PubuFragment_ViewBinding implements Unbinder {
  private PubuFragment target;

  @UiThread
  public PubuFragment_ViewBinding(PubuFragment target, View source) {
    this.target = target;

    target.rvPubuFr = Utils.findRequiredViewAsType(source, R.id.rv_pubu_fr, "field 'rvPubuFr'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PubuFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvPubuFr = null;
  }
}
