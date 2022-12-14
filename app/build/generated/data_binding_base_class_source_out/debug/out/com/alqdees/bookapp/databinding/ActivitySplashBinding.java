// Generated by view binder compiler. Do not edit!
package com.alqdees.bookapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.MotionButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.alqdees.bookapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySplashBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final MotionButton allBook;

  @NonNull
  public final TextView appName;

  @NonNull
  public final MotionButton downLoad;

  @NonNull
  public final ImageView logo;

  private ActivitySplashBinding(@NonNull RelativeLayout rootView, @NonNull MotionButton allBook,
      @NonNull TextView appName, @NonNull MotionButton downLoad, @NonNull ImageView logo) {
    this.rootView = rootView;
    this.allBook = allBook;
    this.appName = appName;
    this.downLoad = downLoad;
    this.logo = logo;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_splash, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySplashBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.allBook;
      MotionButton allBook = ViewBindings.findChildViewById(rootView, id);
      if (allBook == null) {
        break missingId;
      }

      id = R.id.appName;
      TextView appName = ViewBindings.findChildViewById(rootView, id);
      if (appName == null) {
        break missingId;
      }

      id = R.id.downLoad;
      MotionButton downLoad = ViewBindings.findChildViewById(rootView, id);
      if (downLoad == null) {
        break missingId;
      }

      id = R.id.logo;
      ImageView logo = ViewBindings.findChildViewById(rootView, id);
      if (logo == null) {
        break missingId;
      }

      return new ActivitySplashBinding((RelativeLayout) rootView, allBook, appName, downLoad, logo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
