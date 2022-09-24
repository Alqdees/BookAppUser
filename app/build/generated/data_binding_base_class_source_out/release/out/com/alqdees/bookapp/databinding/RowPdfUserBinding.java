// Generated by view binder compiler. Do not edit!
package com.alqdees.bookapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.alqdees.bookapp.R;
import com.github.barteksc.pdfviewer.PDFView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RowPdfUserBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView categoryTv;

  @NonNull
  public final TextView dateTv;

  @NonNull
  public final TextView descriptionTv;

  @NonNull
  public final RelativeLayout pdfRl;

  @NonNull
  public final PDFView pdfViewer;

  @NonNull
  public final ProgressBar progressBsr;

  @NonNull
  public final TextView sizeTv;

  @NonNull
  public final TextView titleTv;

  private RowPdfUserBinding(@NonNull CardView rootView, @NonNull TextView categoryTv,
      @NonNull TextView dateTv, @NonNull TextView descriptionTv, @NonNull RelativeLayout pdfRl,
      @NonNull PDFView pdfViewer, @NonNull ProgressBar progressBsr, @NonNull TextView sizeTv,
      @NonNull TextView titleTv) {
    this.rootView = rootView;
    this.categoryTv = categoryTv;
    this.dateTv = dateTv;
    this.descriptionTv = descriptionTv;
    this.pdfRl = pdfRl;
    this.pdfViewer = pdfViewer;
    this.progressBsr = progressBsr;
    this.sizeTv = sizeTv;
    this.titleTv = titleTv;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static RowPdfUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RowPdfUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.row_pdf_user, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RowPdfUserBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.categoryTv;
      TextView categoryTv = ViewBindings.findChildViewById(rootView, id);
      if (categoryTv == null) {
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

      id = R.id.pdfRl;
      RelativeLayout pdfRl = ViewBindings.findChildViewById(rootView, id);
      if (pdfRl == null) {
        break missingId;
      }

      id = R.id.pdfViewer;
      PDFView pdfViewer = ViewBindings.findChildViewById(rootView, id);
      if (pdfViewer == null) {
        break missingId;
      }

      id = R.id.progressBsr;
      ProgressBar progressBsr = ViewBindings.findChildViewById(rootView, id);
      if (progressBsr == null) {
        break missingId;
      }

      id = R.id.sizeTv;
      TextView sizeTv = ViewBindings.findChildViewById(rootView, id);
      if (sizeTv == null) {
        break missingId;
      }

      id = R.id.titleTv;
      TextView titleTv = ViewBindings.findChildViewById(rootView, id);
      if (titleTv == null) {
        break missingId;
      }

      return new RowPdfUserBinding((CardView) rootView, categoryTv, dateTv, descriptionTv, pdfRl,
          pdfViewer, progressBsr, sizeTv, titleTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
