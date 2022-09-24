// Generated by view binder compiler. Do not edit!
package com.alqdees.bookapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.alqdees.bookapp.R;
import com.github.barteksc.pdfviewer.PDFView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPdfDetailBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView DownloadLabelTv;

  @NonNull
  public final TextView LabelTv;

  @NonNull
  public final ImageButton backBtn;

  @NonNull
  public final LinearLayout btnLayout;

  @NonNull
  public final TextView categoryTv;

  @NonNull
  public final TextView dateLabelTv;

  @NonNull
  public final TextView dateTv;

  @NonNull
  public final TextView descriptionTv;

  @NonNull
  public final Button downLoad;

  @NonNull
  public final TextView downloadTv;

  @NonNull
  public final Button favoriteBtn;

  @NonNull
  public final RelativeLayout imView;

  @NonNull
  public final TextView pageLab;

  @NonNull
  public final TextView pageTv;

  @NonNull
  public final PDFView pdfViewer;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final Button readPdf;

  @NonNull
  public final ScrollView scroll;

  @NonNull
  public final TextView sizeLabelTv;

  @NonNull
  public final TextView sizeTv;

  @NonNull
  public final RelativeLayout table;

  @NonNull
  public final TextView titleTv;

  @NonNull
  public final RelativeLayout toolbarRl;

  @NonNull
  public final TextView viewLabelTv;

  @NonNull
  public final TextView viewTv;

  private ActivityPdfDetailBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView DownloadLabelTv, @NonNull TextView LabelTv, @NonNull ImageButton backBtn,
      @NonNull LinearLayout btnLayout, @NonNull TextView categoryTv, @NonNull TextView dateLabelTv,
      @NonNull TextView dateTv, @NonNull TextView descriptionTv, @NonNull Button downLoad,
      @NonNull TextView downloadTv, @NonNull Button favoriteBtn, @NonNull RelativeLayout imView,
      @NonNull TextView pageLab, @NonNull TextView pageTv, @NonNull PDFView pdfViewer,
      @NonNull ProgressBar progressBar, @NonNull Button readPdf, @NonNull ScrollView scroll,
      @NonNull TextView sizeLabelTv, @NonNull TextView sizeTv, @NonNull RelativeLayout table,
      @NonNull TextView titleTv, @NonNull RelativeLayout toolbarRl, @NonNull TextView viewLabelTv,
      @NonNull TextView viewTv) {
    this.rootView = rootView;
    this.DownloadLabelTv = DownloadLabelTv;
    this.LabelTv = LabelTv;
    this.backBtn = backBtn;
    this.btnLayout = btnLayout;
    this.categoryTv = categoryTv;
    this.dateLabelTv = dateLabelTv;
    this.dateTv = dateTv;
    this.descriptionTv = descriptionTv;
    this.downLoad = downLoad;
    this.downloadTv = downloadTv;
    this.favoriteBtn = favoriteBtn;
    this.imView = imView;
    this.pageLab = pageLab;
    this.pageTv = pageTv;
    this.pdfViewer = pdfViewer;
    this.progressBar = progressBar;
    this.readPdf = readPdf;
    this.scroll = scroll;
    this.sizeLabelTv = sizeLabelTv;
    this.sizeTv = sizeTv;
    this.table = table;
    this.titleTv = titleTv;
    this.toolbarRl = toolbarRl;
    this.viewLabelTv = viewLabelTv;
    this.viewTv = viewTv;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPdfDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPdfDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_pdf_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPdfDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.DownloadLabelTv;
      TextView DownloadLabelTv = ViewBindings.findChildViewById(rootView, id);
      if (DownloadLabelTv == null) {
        break missingId;
      }

      id = R.id.LabelTv;
      TextView LabelTv = ViewBindings.findChildViewById(rootView, id);
      if (LabelTv == null) {
        break missingId;
      }

      id = R.id.backBtn;
      ImageButton backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.btnLayout;
      LinearLayout btnLayout = ViewBindings.findChildViewById(rootView, id);
      if (btnLayout == null) {
        break missingId;
      }

      id = R.id.categoryTv;
      TextView categoryTv = ViewBindings.findChildViewById(rootView, id);
      if (categoryTv == null) {
        break missingId;
      }

      id = R.id.dateLabelTv;
      TextView dateLabelTv = ViewBindings.findChildViewById(rootView, id);
      if (dateLabelTv == null) {
        break missingId;
      }

      id = R.id.dateTv;
      TextView dateTv = ViewBindings.findChildViewById(rootView, id);
      if (dateTv == null) {
        break missingId;
      }

      id = R.id.descriptionTv;
      TextView descriptionTv = ViewBindings.findChildViewById(rootView, id);
      if (descriptionTv == null) {
        break missingId;
      }

      id = R.id.downLoad;
      Button downLoad = ViewBindings.findChildViewById(rootView, id);
      if (downLoad == null) {
        break missingId;
      }

      id = R.id.downloadTv;
      TextView downloadTv = ViewBindings.findChildViewById(rootView, id);
      if (downloadTv == null) {
        break missingId;
      }

      id = R.id.favoriteBtn;
      Button favoriteBtn = ViewBindings.findChildViewById(rootView, id);
      if (favoriteBtn == null) {
        break missingId;
      }

      id = R.id.im_view;
      RelativeLayout imView = ViewBindings.findChildViewById(rootView, id);
      if (imView == null) {
        break missingId;
      }

      id = R.id.pageLab;
      TextView pageLab = ViewBindings.findChildViewById(rootView, id);
      if (pageLab == null) {
        break missingId;
      }

      id = R.id.pageTv;
      TextView pageTv = ViewBindings.findChildViewById(rootView, id);
      if (pageTv == null) {
        break missingId;
      }

      id = R.id.pdfViewer;
      PDFView pdfViewer = ViewBindings.findChildViewById(rootView, id);
      if (pdfViewer == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.readPdf;
      Button readPdf = ViewBindings.findChildViewById(rootView, id);
      if (readPdf == null) {
        break missingId;
      }

      id = R.id.scroll;
      ScrollView scroll = ViewBindings.findChildViewById(rootView, id);
      if (scroll == null) {
        break missingId;
      }

      id = R.id.sizeLabelTv;
      TextView sizeLabelTv = ViewBindings.findChildViewById(rootView, id);
      if (sizeLabelTv == null) {
        break missingId;
      }

      id = R.id.sizeTv;
      TextView sizeTv = ViewBindings.findChildViewById(rootView, id);
      if (sizeTv == null) {
        break missingId;
      }

      id = R.id.table;
      RelativeLayout table = ViewBindings.findChildViewById(rootView, id);
      if (table == null) {
        break missingId;
      }

      id = R.id.titleTv;
      TextView titleTv = ViewBindings.findChildViewById(rootView, id);
      if (titleTv == null) {
        break missingId;
      }

      id = R.id.toolbarRl;
      RelativeLayout toolbarRl = ViewBindings.findChildViewById(rootView, id);
      if (toolbarRl == null) {
        break missingId;
      }

      id = R.id.viewLabelTv;
      TextView viewLabelTv = ViewBindings.findChildViewById(rootView, id);
      if (viewLabelTv == null) {
        break missingId;
      }

      id = R.id.viewTv;
      TextView viewTv = ViewBindings.findChildViewById(rootView, id);
      if (viewTv == null) {
        break missingId;
      }

      return new ActivityPdfDetailBinding((RelativeLayout) rootView, DownloadLabelTv, LabelTv,
          backBtn, btnLayout, categoryTv, dateLabelTv, dateTv, descriptionTv, downLoad, downloadTv,
          favoriteBtn, imView, pageLab, pageTv, pdfViewer, progressBar, readPdf, scroll,
          sizeLabelTv, sizeTv, table, titleTv, toolbarRl, viewLabelTv, viewTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}