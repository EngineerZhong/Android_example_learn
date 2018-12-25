// Generated code from Butter Knife. Do not modify!
package com.example.a.myapplication12321321;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SecondActivity_ViewBinding implements Unbinder {
  private SecondActivity target;

  private View view2131296334;

  private View view2131296311;

  private View view2131296338;

  private View view2131296322;

  @UiThread
  public SecondActivity_ViewBinding(SecondActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SecondActivity_ViewBinding(final SecondActivity target, View source) {
    this.target = target;

    View view;
    target.tvSecMain = Utils.findRequiredViewAsType(source, R.id.tv_sec_main, "field 'tvSecMain'", TextView.class);
    target.pgbLoading = Utils.findRequiredViewAsType(source, R.id.pgb_loading, "field 'pgbLoading'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.btn_send_ok_http, "field 'btnSendOkHttp' and method 'btnOkHttp'");
    target.btnSendOkHttp = Utils.castView(view, R.id.btn_send_ok_http, "field 'btnSendOkHttp'", Button.class);
    view2131296334 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.btnOkHttp();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_download_ok_http, "field 'btnDownloadOkHttp' and method 'btnDownload'");
    target.btnDownloadOkHttp = Utils.castView(view, R.id.btn_download_ok_http, "field 'btnDownloadOkHttp'", Button.class);
    view2131296311 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.btnDownload();
      }
    });
    target.pgbDownloading = Utils.findRequiredViewAsType(source, R.id.pgb_downloading, "field 'pgbDownloading'", ProgressBar.class);
    target.tvDownloadingProgress = Utils.findRequiredViewAsType(source, R.id.tv_downloadingProgress, "field 'tvDownloadingProgress'", TextView.class);
    target.tvDownloadingSpeed = Utils.findRequiredViewAsType(source, R.id.tv_downloadingSpeed, "field 'tvDownloadingSpeed'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_upload_ok_http, "field 'btnUploadOkHttp' and method 'btnUploadFile'");
    target.btnUploadOkHttp = Utils.castView(view, R.id.btn_upload_ok_http, "field 'btnUploadOkHttp'", Button.class);
    view2131296338 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.btnUploadFile();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_retrofit_ok_http, "field 'btnRetrofitOkHttp' and method 'btnRetrofitTest'");
    target.btnRetrofitOkHttp = Utils.castView(view, R.id.btn_retrofit_ok_http, "field 'btnRetrofitOkHttp'", Button.class);
    view2131296322 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.btnRetrofitTest();
      }
    });
    target.tvTextContent = Utils.findRequiredViewAsType(source, R.id.tv_textContent, "field 'tvTextContent'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SecondActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvSecMain = null;
    target.pgbLoading = null;
    target.btnSendOkHttp = null;
    target.btnDownloadOkHttp = null;
    target.pgbDownloading = null;
    target.tvDownloadingProgress = null;
    target.tvDownloadingSpeed = null;
    target.btnUploadOkHttp = null;
    target.btnRetrofitOkHttp = null;
    target.tvTextContent = null;

    view2131296334.setOnClickListener(null);
    view2131296334 = null;
    view2131296311.setOnClickListener(null);
    view2131296311 = null;
    view2131296338.setOnClickListener(null);
    view2131296338 = null;
    view2131296322.setOnClickListener(null);
    view2131296322 = null;
  }
}
