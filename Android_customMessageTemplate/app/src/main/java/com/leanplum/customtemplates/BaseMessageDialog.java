// Copyright 2014, Leanplum, Inc.

package com.leanplum.customtemplates;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.utils.BitmapUtil;
import com.leanplum.utils.SizeUtil;
import com.leanplum.views.BackgroundImageView;
import com.leanplum.views.CloseButton;

/**
 * Base dialog used to display the Center Popup, Interstitial and Web Interstitial.
 * @author Martin Yanakiev 
 */
public class BaseMessageDialog extends Dialog {
  private RelativeLayout dialogView;
  protected BaseMessageOptions options;
  protected WebInterstitialOptions webOptions;
  private boolean isWeb = false;
  private boolean isClosing = false;

  protected BaseMessageDialog(Activity activity, boolean fullscreen, BaseMessageOptions options, WebInterstitialOptions webOptions) {
    super(activity, getTheme(activity));
    SizeUtil.init(activity);
    this.options = options;
    this.webOptions = webOptions;
    if (webOptions != null) {
      isWeb = true;
    }
    dialogView = new RelativeLayout(activity);
    LayoutParams layoutParams = new LayoutParams(
        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    dialogView.setBackgroundColor(0x00000000);
    dialogView.setLayoutParams(layoutParams);

    RelativeLayout view = createContainerView(activity, fullscreen);
    view.setId(108);
    dialogView.addView(view, view.getLayoutParams());

    if (!isWeb || webOptions.hasDismissButton()) {
      CloseButton closeButton = createCloseButton(activity, fullscreen, view);
      dialogView.addView(closeButton, closeButton.getLayoutParams());
    }

    setContentView(dialogView, dialogView.getLayoutParams());

    dialogView.setAnimation(createFadeInAnimation());

    if (!fullscreen) {
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
      if (Build.VERSION.SDK_INT >= 14) {
        getWindow().setDimAmount(0.7f);
      }
    }
  }

  private Animation createFadeInAnimation() {
    Animation fadeIn = new AlphaAnimation(0, 1);
    fadeIn.setInterpolator(new DecelerateInterpolator());
    fadeIn.setDuration(350);
    return fadeIn;
  }

  private Animation createFadeOutAnimation() {
    Animation fadeOut = new AlphaAnimation(1, 0);
    fadeOut.setInterpolator(new AccelerateInterpolator());
    fadeOut.setDuration(350);
    return fadeOut;
  }

  @Override
  public void cancel() {
    if (isClosing) {
      return;
    }
    isClosing = true;
    Animation animation = createFadeOutAnimation();
    animation.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {}
      @Override public void onAnimationRepeat(Animation animation) {}
      @Override
      public void onAnimationEnd(Animation animation) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              BaseMessageDialog.super.cancel();
            }
        }, 10);
      }
    });
    dialogView.startAnimation(animation);
  }

  private CloseButton createCloseButton(Activity context, boolean fullscreen, View parent) {
    CloseButton closeButton = new CloseButton(context);
    closeButton.setId(103);
    RelativeLayout.LayoutParams closeLayout = new RelativeLayout.LayoutParams(
        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    if (fullscreen) {
      closeLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP, dialogView.getId());
      closeLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, dialogView.getId());
      closeLayout.setMargins(0, SizeUtil.dp5, SizeUtil.dp5, 0);
    } else {
      closeLayout.addRule(RelativeLayout.ALIGN_TOP, parent.getId());
      closeLayout.addRule(RelativeLayout.ALIGN_RIGHT, parent.getId());
      closeLayout.setMargins(0, -SizeUtil.dp7, -SizeUtil.dp7, 0);
    }
    closeButton.setLayoutParams(closeLayout);
    closeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View arg0) {
        cancel();
      }
    });
    return closeButton;
  }

  @SuppressWarnings("deprecation")
  private RelativeLayout createContainerView(Activity context, boolean fullscreen) {
    RelativeLayout view = new RelativeLayout(context);

    // Positions the dialog.
    RelativeLayout.LayoutParams layoutParams;
    if (fullscreen) {
      layoutParams = new RelativeLayout.LayoutParams(
          LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    } else {
      int width = SizeUtil.dpToPx(context, ((CenterPopupOptions) options).getWidth());
      int height = SizeUtil.dpToPx(context, ((CenterPopupOptions) options).getHeight());

      // Make sure the dialog fits on screen.
      Display display = context.getWindowManager().getDefaultDisplay();
      Point size = new Point();
      if (Build.VERSION.SDK_INT >= 13) {
        display.getSize(size);
      } else {
        size = new Point(display.getHeight(), display.getHeight());
      }
      int maxWidth = size.x - SizeUtil.dp20;
      int maxHeight = size.y - SizeUtil.dp20;
      double aspectRatio = width / (double) height;
      if (width > maxWidth && (int) (width / aspectRatio) < maxHeight) {
        width = maxWidth;
        height = (int) (width / aspectRatio);
      }
      if (height > maxHeight && (int) (height * aspectRatio) < maxWidth) {
        height = maxHeight;
        width = (int) (height * aspectRatio);
      }

      layoutParams = new RelativeLayout.LayoutParams(width, height);
    }
    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
    view.setLayoutParams(layoutParams);

    ShapeDrawable footerBackground = new ShapeDrawable();
    footerBackground.setShape(createRoundRect(fullscreen ? 0 : SizeUtil.dp20));
    footerBackground.getPaint().setColor(0x00000000);
    if (Build.VERSION.SDK_INT >= 16) {
      view.setBackground(footerBackground);
    } else {
      view.setBackgroundDrawable(footerBackground);
    }

    if (!isWeb) {
      ImageView image = createBackgroundImageView(context, fullscreen);
      view.addView(image, image.getLayoutParams());

      View title = createTitleView(context);
      title.setId(104);
      view.addView(title, title.getLayoutParams());

      View button = createAcceptButton(context);
      button.setId(105);
      view.addView(button, button.getLayoutParams());

      View message = createMessageView(context);
      ((RelativeLayout.LayoutParams) message.getLayoutParams())
          .addRule(RelativeLayout.BELOW, title.getId());
      ((RelativeLayout.LayoutParams) message.getLayoutParams())
          .addRule(RelativeLayout.ABOVE, button.getId());
      view.addView(message, message.getLayoutParams());
    } else {
      WebView webView = createWebView(context);
      view.addView(webView, webView.getLayoutParams());
    }

    return view;
  }

  private Shape createRoundRect(int cornerRadius) {
    int c = cornerRadius;
    float[] outerRadii = new float[] { c, c, c, c, c, c, c, c };
    return new RoundRectShape(outerRadii, null, null);
  }

  @SuppressWarnings("deprecation")
  private ImageView createBackgroundImageView(Context context, boolean fullscreen) {
    BackgroundImageView view = new BackgroundImageView(context, fullscreen);
    view.setScaleType(ImageView.ScaleType.CENTER_CROP);
    int cornerRadius;
    if (!fullscreen) {
      cornerRadius = SizeUtil.dp20;
    } else {
      cornerRadius = 0;
    }
    view.setImageBitmap(options.getBackgroundImage());
    ShapeDrawable footerBackground = new ShapeDrawable();
    footerBackground.setShape(createRoundRect(cornerRadius));
    footerBackground.getPaint().setColor(options.getBackgroundColor());
    if (Build.VERSION.SDK_INT >= 16) {
      view.setBackground(footerBackground);
    } else {
      view.setBackgroundDrawable(footerBackground);
    }
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    view.setLayoutParams(layoutParams);
    return view;
  }

  private RelativeLayout createTitleView(Context context) {
    RelativeLayout view = new RelativeLayout(context);
    view.setLayoutParams(new LayoutParams(
        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

    TextView title = new TextView(context);
    title.setPadding(0, SizeUtil.dp5, 0, SizeUtil.dp5);
    title.setGravity(Gravity.CENTER);
    title.setText(options.getTitle());
    title.setTextColor(options.getTitleColor());
    title.setTextSize(TypedValue.COMPLEX_UNIT_SP, SizeUtil.textSize0);
    title.setTypeface(null, Typeface.BOLD);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
    layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
    title.setLayoutParams(layoutParams);

    view.addView(title, title.getLayoutParams());
    return view;
  }

  private TextView createMessageView(Context context) {
    TextView view = new TextView(context);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    view.setLayoutParams(layoutParams);
    view.setGravity(Gravity.CENTER);
    view.setText(options.getMessageText());
    view.setTextColor(options.getMessageColor());
    view.setTextSize(TypedValue.COMPLEX_UNIT_SP, SizeUtil.textSize0_1);
    return view;
  }
  
  private WebView createWebView(Context context) {
    WebView view = new WebView(context);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    view.setLayoutParams(layoutParams);
    view.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView wView, String url) {
        if (url.contains(webOptions.getCloseUrl())) {
          cancel();
          String[] urlComponents = url.split("\\?");
          if (urlComponents.length > 1) {
            String queryString = urlComponents[1];
            String[] parameters = queryString.split("&");
            for (String parameter : parameters) {
              String[] parameterComponents = parameter.split("=");
              if (parameterComponents.length > 1 && parameterComponents[0].equals("result")) {
                Leanplum.track(parameterComponents[1]);
              }
            }
          }
          return true;
        }
        return false;
      }
    });
    view.loadUrl(webOptions.getUrl());
    return view;
  }

  private TextView createAcceptButton(Context context) {
    TextView view = new TextView(context);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
    layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
    layoutParams.setMargins(0, 0, 0, SizeUtil.dp5);

    view.setPadding(SizeUtil.dp20, SizeUtil.dp5, SizeUtil.dp20, SizeUtil.dp5);
    view.setLayoutParams(layoutParams);
    view.setText(options.getAcceptButtonText());
    view.setTextColor(options.getAcceptButtonTextColor());
    view.setTypeface(null, Typeface.BOLD);

    BitmapUtil.stateBackgroundDarkerByPercentage(view,
        options.getAcceptButtonBackgroundColor(), 30);

    view.setTextSize(TypedValue.COMPLEX_UNIT_SP, SizeUtil.textSize0_1);
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View arg0) {
        if (!isClosing) {
          options.accept();
          cancel();
        }
      }
    });
    return view;
  }

  private static int getTheme(Activity activity) {
    boolean full = (activity.getWindow().getAttributes().flags &
        WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    if (full) {
      return android.R.style.Theme_Translucent_NoTitleBar_Fullscreen;
    } else {
      return android.R.style.Theme_Translucent_NoTitleBar;
    }
  }
}
