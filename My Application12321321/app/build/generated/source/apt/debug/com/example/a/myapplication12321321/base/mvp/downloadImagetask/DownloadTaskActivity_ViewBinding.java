// Generated code from Butter Knife. Do not modify!
package com.example.a.myapplication12321321.base.mvp.downloadImagetask;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.a.myapplication12321321.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DownloadTaskActivity_ViewBinding implements Unbinder {
  private DownloadTaskActivity target;

  @UiThread
  public DownloadTaskActivity_ViewBinding(DownloadTaskActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DownloadTaskActivity_ViewBinding(DownloadTaskActivity target, View source) {
    this.target = target;

    target.pgbDownloadingImg = Utils.findRequiredViewAsType(source, R.id.pgb_downloadingImg, "field 'pgbDownloadingImg'", ProgressBar.class);
    target.tvLoading = Utils.findRequiredViewAsType(source, R.id.tv_loading, "field 'tvLoading'", TextView.class);
    target.imgDownload = Utils.findRequiredViewAsType(source, R.id.img_download, "field 'imgDownload'", ImageView.class);
    target.btnDownloadTask = Utils.findRequiredViewAsType(source, R.id.btn_downloadTask, "field 'btnDownloadTask'", Button.class);
    target.btnDownloadfromstreamTask = Utils.findRequiredViewAsType(source, R.id.btn_downloadfromstreamTask, "field 'btnDownloadfromstreamTask'", Button.class);
    target.btnOnclick = Utils.findRequiredViewAsType(source, R.id.btn_onclick, "field 'btnOnclick'", Button.class);
    target.pbBar = Utils.findRequiredViewAsType(source, R.id.pb_bar, "field 'pbBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DownloadTaskActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pgbDownloadingImg = null;
    target.tvLoading = null;
    target.imgDownload = null;
    target.btnDownloadTask = null;
    target.btnDownloadfromstreamTask = null;
    target.btnOnclick = null;
    target.pbBar = null;
  }
}
