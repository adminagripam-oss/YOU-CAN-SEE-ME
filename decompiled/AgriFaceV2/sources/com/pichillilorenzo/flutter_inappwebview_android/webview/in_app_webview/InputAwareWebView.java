package com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ListPopupWindow;
/* loaded from: classes.dex */
public class InputAwareWebView extends WebView {
    private static final String LOG_TAG = "InputAwareWebView";
    public View containerView;
    private ThreadedInputConnectionProxyAdapterView proxyAdapterView;
    private View threadedInputConnectionProxyView;
    private boolean useHybridComposition;

    public InputAwareWebView(Context context) {
        super(context);
        this.useHybridComposition = false;
        this.containerView = null;
    }

    private boolean isCalledFromListPopupWindowShow() {
        StackTraceElement[] stackTrace;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (stackTraceElement.getClassName().equals(ListPopupWindow.class.getCanonicalName()) && stackTraceElement.getMethodName().equals("show")) {
                return true;
            }
        }
        return false;
    }

    private void resetInputConnection() {
        if (this.proxyAdapterView == null) {
            return;
        }
        View view = this.containerView;
        if (view == null) {
            Log.e(LOG_TAG, "Can't reset the input connection to the container view because there is none.");
        } else {
            setInputConnectionTarget(view);
        }
    }

    private void setInputConnectionTarget(final View view) {
        if (this.containerView == null) {
            Log.e(LOG_TAG, "Can't set the input connection target because there is no containerView to use as a handler.");
            return;
        }
        view.requestFocus();
        this.containerView.post(new Runnable() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InputAwareWebView.1
            @Override // java.lang.Runnable
            public void run() {
                InputAwareWebView inputAwareWebView = InputAwareWebView.this;
                if (inputAwareWebView.containerView == null) {
                    Log.e(InputAwareWebView.LOG_TAG, "Can't set the input connection target because there is no containerView to use as a handler.");
                    return;
                }
                InputMethodManager inputMethodManager = (InputMethodManager) inputAwareWebView.getContext().getSystemService("input_method");
                view.onWindowFocusChanged(true);
                if (Build.VERSION.SDK_INT < 29) {
                    inputMethodManager.isActive(InputAwareWebView.this.containerView);
                }
            }
        });
    }

    @Override // android.view.View
    public boolean checkInputConnectionProxy(View view) {
        if (this.useHybridComposition) {
            return super.checkInputConnectionProxy(view);
        }
        View view2 = this.threadedInputConnectionProxyView;
        this.threadedInputConnectionProxyView = view;
        if (view2 == view) {
            return super.checkInputConnectionProxy(view);
        }
        View view3 = this.containerView;
        if (view3 == null) {
            Log.e(LOG_TAG, "Can't create a proxy view because there's no container view. Text input may not work.");
        } else {
            ThreadedInputConnectionProxyAdapterView threadedInputConnectionProxyAdapterView = new ThreadedInputConnectionProxyAdapterView(view3, view, view.getHandler());
            this.proxyAdapterView = threadedInputConnectionProxyAdapterView;
            setInputConnectionTarget(threadedInputConnectionProxyAdapterView);
        }
        return super.checkInputConnectionProxy(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        super.clearFocus();
        if (this.useHybridComposition) {
            return;
        }
        resetInputConnection();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispose() {
        if (this.useHybridComposition) {
            return;
        }
        resetInputConnection();
    }

    public void lockInputConnection() {
        ThreadedInputConnectionProxyAdapterView threadedInputConnectionProxyAdapterView = this.proxyAdapterView;
        if (threadedInputConnectionProxyAdapterView == null) {
            return;
        }
        threadedInputConnectionProxyAdapterView.setLocked(true);
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onFocusChanged(boolean z7, int i8, Rect rect) {
        if (this.useHybridComposition) {
            super.onFocusChanged(z7, i8, rect);
        } else if (Build.VERSION.SDK_INT >= 28 || !isCalledFromListPopupWindowShow() || z7) {
            super.onFocusChanged(z7, i8, rect);
        }
    }

    public void setContainerView(View view) {
        this.containerView = view;
        if (this.proxyAdapterView == null) {
            return;
        }
        Log.w(LOG_TAG, "The containerView has changed while the proxyAdapterView exists.");
        if (view != null) {
            setInputConnectionTarget(this.proxyAdapterView);
        }
    }

    public void unlockInputConnection() {
        ThreadedInputConnectionProxyAdapterView threadedInputConnectionProxyAdapterView = this.proxyAdapterView;
        if (threadedInputConnectionProxyAdapterView == null) {
            return;
        }
        threadedInputConnectionProxyAdapterView.setLocked(false);
    }

    public InputAwareWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.useHybridComposition = false;
        this.containerView = null;
    }

    public InputAwareWebView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.useHybridComposition = false;
        this.containerView = null;
    }

    public InputAwareWebView(Context context, View view, Boolean bool) {
        super(context);
        this.useHybridComposition = false;
        this.containerView = view;
        this.useHybridComposition = bool != null ? bool.booleanValue() : false;
    }
}
