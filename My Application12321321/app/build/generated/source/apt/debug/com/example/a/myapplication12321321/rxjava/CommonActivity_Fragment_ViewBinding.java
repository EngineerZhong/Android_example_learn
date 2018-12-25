// Generated code from Butter Knife. Do not modify!
package com.example.a.myapplication12321321.rxjava;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.a.myapplication12321321.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommonActivity_Fragment_ViewBinding implements Unbinder {
  private CommonActivity_Fragment target;

  @UiThread
  public CommonActivity_Fragment_ViewBinding(CommonActivity_Fragment target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CommonActivity_Fragment_ViewBinding(CommonActivity_Fragment target, View source) {
    this.target = target;

    target.llFrameLayout = Utils.findRequiredViewAsType(source, R.id.ll_frame, "field 'llFrameLayout'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CommonActivity_Fragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llFrameLayout = null;
  }
}
