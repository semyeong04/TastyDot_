// Generated by view binder compiler. Do not edit!
package com.project.project3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.project.project3.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddCouponBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout b;

  @NonNull
  public final Button btnJoin;

  @NonNull
  public final EditText et1000;

  @NonNull
  public final EditText et2000;

  @NonNull
  public final EditText et3000;

  @NonNull
  public final EditText et5000;

  @NonNull
  public final Guideline guideline4;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ImageView imghome1;

  @NonNull
  public final ImageView imginfo1;

  @NonNull
  public final TextView textView1;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textView18;

  @NonNull
  public final TextView textView19;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView20;

  @NonNull
  public final TextView textView22;

  @NonNull
  public final TextView textView23;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView8;

  private ActivityAddCouponBinding(@NonNull ConstraintLayout rootView, @NonNull ConstraintLayout b,
      @NonNull Button btnJoin, @NonNull EditText et1000, @NonNull EditText et2000,
      @NonNull EditText et3000, @NonNull EditText et5000, @NonNull Guideline guideline4,
      @NonNull ImageView imageView3, @NonNull ImageView imageView4, @NonNull ImageView imghome1,
      @NonNull ImageView imginfo1, @NonNull TextView textView1, @NonNull TextView textView11,
      @NonNull TextView textView12, @NonNull TextView textView18, @NonNull TextView textView19,
      @NonNull TextView textView2, @NonNull TextView textView20, @NonNull TextView textView22,
      @NonNull TextView textView23, @NonNull TextView textView6, @NonNull TextView textView8) {
    this.rootView = rootView;
    this.b = b;
    this.btnJoin = btnJoin;
    this.et1000 = et1000;
    this.et2000 = et2000;
    this.et3000 = et3000;
    this.et5000 = et5000;
    this.guideline4 = guideline4;
    this.imageView3 = imageView3;
    this.imageView4 = imageView4;
    this.imghome1 = imghome1;
    this.imginfo1 = imginfo1;
    this.textView1 = textView1;
    this.textView11 = textView11;
    this.textView12 = textView12;
    this.textView18 = textView18;
    this.textView19 = textView19;
    this.textView2 = textView2;
    this.textView20 = textView20;
    this.textView22 = textView22;
    this.textView23 = textView23;
    this.textView6 = textView6;
    this.textView8 = textView8;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddCouponBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddCouponBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_coupon, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddCouponBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout b = (ConstraintLayout) rootView;

      id = R.id.btnJoin;
      Button btnJoin = ViewBindings.findChildViewById(rootView, id);
      if (btnJoin == null) {
        break missingId;
      }

      id = R.id.et1000;
      EditText et1000 = ViewBindings.findChildViewById(rootView, id);
      if (et1000 == null) {
        break missingId;
      }

      id = R.id.et2000;
      EditText et2000 = ViewBindings.findChildViewById(rootView, id);
      if (et2000 == null) {
        break missingId;
      }

      id = R.id.et3000;
      EditText et3000 = ViewBindings.findChildViewById(rootView, id);
      if (et3000 == null) {
        break missingId;
      }

      id = R.id.et5000;
      EditText et5000 = ViewBindings.findChildViewById(rootView, id);
      if (et5000 == null) {
        break missingId;
      }

      id = R.id.guideline4;
      Guideline guideline4 = ViewBindings.findChildViewById(rootView, id);
      if (guideline4 == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.imghome1;
      ImageView imghome1 = ViewBindings.findChildViewById(rootView, id);
      if (imghome1 == null) {
        break missingId;
      }

      id = R.id.imginfo1;
      ImageView imginfo1 = ViewBindings.findChildViewById(rootView, id);
      if (imginfo1 == null) {
        break missingId;
      }

      id = R.id.textView1;
      TextView textView1 = ViewBindings.findChildViewById(rootView, id);
      if (textView1 == null) {
        break missingId;
      }

      id = R.id.textView11;
      TextView textView11 = ViewBindings.findChildViewById(rootView, id);
      if (textView11 == null) {
        break missingId;
      }

      id = R.id.textView12;
      TextView textView12 = ViewBindings.findChildViewById(rootView, id);
      if (textView12 == null) {
        break missingId;
      }

      id = R.id.textView18;
      TextView textView18 = ViewBindings.findChildViewById(rootView, id);
      if (textView18 == null) {
        break missingId;
      }

      id = R.id.textView19;
      TextView textView19 = ViewBindings.findChildViewById(rootView, id);
      if (textView19 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView20;
      TextView textView20 = ViewBindings.findChildViewById(rootView, id);
      if (textView20 == null) {
        break missingId;
      }

      id = R.id.textView22;
      TextView textView22 = ViewBindings.findChildViewById(rootView, id);
      if (textView22 == null) {
        break missingId;
      }

      id = R.id.textView23;
      TextView textView23 = ViewBindings.findChildViewById(rootView, id);
      if (textView23 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      return new ActivityAddCouponBinding((ConstraintLayout) rootView, b, btnJoin, et1000, et2000,
          et3000, et5000, guideline4, imageView3, imageView4, imghome1, imginfo1, textView1,
          textView11, textView12, textView18, textView19, textView2, textView20, textView22,
          textView23, textView6, textView8);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}