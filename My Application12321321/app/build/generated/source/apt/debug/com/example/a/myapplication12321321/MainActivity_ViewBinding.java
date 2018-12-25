// Generated code from Butter Knife. Do not modify!
package com.example.a.myapplication12321321;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131296332;

  private View view2131296331;

  private View view2131296330;

  private View view2131296333;

  private View view2131296314;

  private View view2131296315;

  private View view2131296325;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.tvTextContent = Utils.findRequiredViewAsType(source, R.id.tv_textContent, "field 'tvTextContent'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_sendMain, "field 'btnSendMain' and method 'widgetOnClick'");
    target.btnSendMain = Utils.castView(view, R.id.btn_sendMain, "field 'btnSendMain'", Button.class);
    view2131296332 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_sendBackGroundThread, "field 'btnSendBackGroundThread' and method 'widgetOnClick'");
    target.btnSendBackGroundThread = Utils.castView(view, R.id.btn_sendBackGroundThread, "field 'btnSendBackGroundThread'", Button.class);
    view2131296331 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_sendBackForceThread, "field 'btnSendBackForceThread' and method 'widgetOnClick'");
    target.btnSendBackForceThread = Utils.castView(view, R.id.btn_sendBackForceThread, "field 'btnSendBackForceThread'", Button.class);
    view2131296330 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_sendSameThread, "field 'btnSendSameThread' and method 'widgetOnClick'");
    target.btnSendSameThread = Utils.castView(view, R.id.btn_sendSameThread, "field 'btnSendSameThread'", Button.class);
    view2131296333 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_goto, "field 'btnGoto' and method 'widgetOnClick'");
    target.btnGoto = Utils.castView(view, R.id.btn_goto, "field 'btnGoto'", Button.class);
    view2131296314 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_greedao, "field 'btnGreedao' and method 'widgetOnClick'");
    target.btnGreedao = Utils.castView(view, R.id.btn_greedao, "field 'btnGreedao'", Button.class);
    view2131296315 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_rxjava, "field 'btnRxjava' and method 'widgetOnClick'");
    target.btnRxjava = Utils.castView(view, R.id.btn_rxjava, "field 'btnRxjava'", Button.class);
    view2131296325 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.widgetOnClick(p0);
      }
    });
    target.btnRxandroid = Utils.findRequiredViewAsType(source, R.id.btn_rxandroid, "field 'btnRxandroid'", Button.class);
    target.btnDesignviewMainActivity = Utils.findRequiredViewAsType(source, R.id.btn_designview_main_activity, "field 'btnDesignviewMainActivity'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTextContent = null;
    target.btnSendMain = null;
    target.btnSendBackGroundThread = null;
    target.btnSendBackForceThread = null;
    target.btnSendSameThread = null;
    target.btnGoto = null;
    target.btnGreedao = null;
    target.btnRxjava = null;
    target.btnRxandroid = null;
    target.btnDesignviewMainActivity = null;

    view2131296332.setOnClickListener(null);
    view2131296332 = null;
    view2131296331.setOnClickListener(null);
    view2131296331 = null;
    view2131296330.setOnClickListener(null);
    view2131296330 = null;
    view2131296333.setOnClickListener(null);
    view2131296333 = null;
    view2131296314.setOnClickListener(null);
    view2131296314 = null;
    view2131296315.setOnClickListener(null);
    view2131296315 = null;
    view2131296325.setOnClickListener(null);
    view2131296325 = null;
  }
}
