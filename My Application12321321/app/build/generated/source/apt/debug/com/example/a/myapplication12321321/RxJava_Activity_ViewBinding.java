// Generated code from Butter Knife. Do not modify!
package com.example.a.myapplication12321321;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RxJava_Activity_ViewBinding implements Unbinder {
  private RxJava_Activity target;

  private View view2131296329;

  private View view2131296326;

  private View view2131296328;

  private View view2131296327;

  @UiThread
  public RxJava_Activity_ViewBinding(RxJava_Activity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RxJava_Activity_ViewBinding(final RxJava_Activity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_rxjava_simple, "field 'btnRxjavaSimple' and method 'widgetOnClick'");
    target.btnRxjavaSimple = Utils.castView(view, R.id.btn_rxjava_simple, "field 'btnRxjavaSimple'", Button.class);
    view2131296329 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_rxjava_better, "field 'btnRxjavaBetter' and method 'widgetOnClick'");
    target.btnRxjavaBetter = Utils.castView(view, R.id.btn_rxjava_better, "field 'btnRxjavaBetter'", Button.class);
    view2131296326 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    target.gridRecyclerview = Utils.findRequiredViewAsType(source, R.id.grid_recyclerview, "field 'gridRecyclerview'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btn_rxjava_map, "field 'btnRxjavaMap' and method 'widgetOnClick'");
    target.btnRxjavaMap = Utils.castView(view, R.id.btn_rxjava_map, "field 'btnRxjavaMap'", Button.class);
    view2131296328 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_rxjava_concat, "field 'btnRxjavaConcat' and method 'widgetOnClick'");
    target.btnRxjavaConcat = Utils.castView(view, R.id.btn_rxjava_concat, "field 'btnRxjavaConcat'", Button.class);
    view2131296327 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    target.imgConcatImg = Utils.findRequiredViewAsType(source, R.id.img_concat_img, "field 'imgConcatImg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RxJava_Activity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnRxjavaSimple = null;
    target.btnRxjavaBetter = null;
    target.gridRecyclerview = null;
    target.btnRxjavaMap = null;
    target.btnRxjavaConcat = null;
    target.imgConcatImg = null;

    view2131296329.setOnClickListener(null);
    view2131296329 = null;
    view2131296326.setOnClickListener(null);
    view2131296326 = null;
    view2131296328.setOnClickListener(null);
    view2131296328 = null;
    view2131296327.setOnClickListener(null);
    view2131296327 = null;
  }
}
