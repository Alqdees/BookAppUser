// Generated by view binder compiler. Do not edit!
package com.alqdees.bookapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.alqdees.bookapp.R;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextInputLayout edEmail;

  @NonNull
  public final TextInputLayout edPass;

  @NonNull
  public final EditText editEmail;

  @NonNull
  public final EditText editPassword;

  @NonNull
  public final ImageView imView;

  @NonNull
  public final TextView noAccount;

  @NonNull
  public final RelativeLayout toolbar;

  @NonNull
  public final TextView tvForget;

  @NonNull
  public final Button userBtn;

  private ActivityLoginBinding(@NonNull RelativeLayout rootView, @NonNull TextInputLayout edEmail,
      @NonNull TextInputLayout edPass, @NonNull EditText editEmail, @NonNull EditText editPassword,
      @NonNull ImageView imView, @NonNull TextView noAccount, @NonNull RelativeLayout toolbar,
      @NonNull TextView tvForget, @NonNull Button userBtn) {
    this.rootView = rootView;
    this.edEmail = edEmail;
    this.edPass = edPass;
    this.editEmail = editEmail;
    this.editPassword = editPassword;
    this.imView = imView;
    this.noAccount = noAccount;
    this.toolbar = toolbar;
    this.tvForget = tvForget;
    this.userBtn = userBtn;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ed_email;
      TextInputLayout edEmail = ViewBindings.findChildViewById(rootView, id);
      if (edEmail == null) {
        break missingId;
      }

      id = R.id.ed_pass;
      TextInputLayout edPass = ViewBindings.findChildViewById(rootView, id);
      if (edPass == null) {
        break missingId;
      }

      id = R.id.editEmail;
      EditText editEmail = ViewBindings.findChildViewById(rootView, id);
      if (editEmail == null) {
        break missingId;
      }

      id = R.id.editPassword;
      EditText editPassword = ViewBindings.findChildViewById(rootView, id);
      if (editPassword == null) {
        break missingId;
      }

      id = R.id.im_view;
      ImageView imView = ViewBindings.findChildViewById(rootView, id);
      if (imView == null) {
        break missingId;
      }

      id = R.id.noAccount;
      TextView noAccount = ViewBindings.findChildViewById(rootView, id);
      if (noAccount == null) {
        break missingId;
      }

      id = R.id.toolbar;
      RelativeLayout toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tv_forget;
      TextView tvForget = ViewBindings.findChildViewById(rootView, id);
      if (tvForget == null) {
        break missingId;
      }

      id = R.id.userBtn;
      Button userBtn = ViewBindings.findChildViewById(rootView, id);
      if (userBtn == null) {
        break missingId;
      }

      return new ActivityLoginBinding((RelativeLayout) rootView, edEmail, edPass, editEmail,
          editPassword, imView, noAccount, toolbar, tvForget, userBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
