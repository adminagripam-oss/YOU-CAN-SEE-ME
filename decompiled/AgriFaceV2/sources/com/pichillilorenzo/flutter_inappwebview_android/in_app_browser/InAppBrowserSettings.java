package com.pichillilorenzo.flutter_inappwebview_android.in_app_browser;

import android.view.Menu;
import android.widget.ProgressBar;
import androidx.appcompat.app.a;
import androidx.browser.customtabs.b;
import com.pichillilorenzo.flutter_inappwebview_android.ISettings;
import com.pichillilorenzo.flutter_inappwebview_android.R;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class InAppBrowserSettings implements ISettings<InAppBrowserActivity> {
    public static final String LOG_TAG = "InAppBrowserSettings";
    public Boolean allowGoBackWithBackButton;
    public Boolean closeOnCannotGoBack;
    public Boolean hidden;
    public Boolean hideDefaultMenuItems;
    public Boolean hideProgressBar;
    public Boolean hideTitleBar;
    public Boolean hideToolbarTop;
    public Boolean hideUrlBar;
    public Boolean shouldCloseOnBackButtonPressed;
    public String toolbarTopBackgroundColor;
    public String toolbarTopFixedTitle;

    public InAppBrowserSettings() {
        Boolean bool = Boolean.FALSE;
        this.hidden = bool;
        this.hideToolbarTop = bool;
        this.hideUrlBar = bool;
        this.hideProgressBar = bool;
        this.hideTitleBar = bool;
        Boolean bool2 = Boolean.TRUE;
        this.closeOnCannotGoBack = bool2;
        this.allowGoBackWithBackButton = bool2;
        this.shouldCloseOnBackButtonPressed = bool;
        this.hideDefaultMenuItems = bool;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> getRealSettings(InAppBrowserActivity inAppBrowserActivity) {
        Map<String, Object> map = toMap();
        map.put("hidden", Boolean.valueOf(inAppBrowserActivity.isHidden));
        a aVar = inAppBrowserActivity.actionBar;
        boolean z7 = false;
        map.put("hideToolbarTop", Boolean.valueOf(aVar == null || !aVar.n()));
        Menu menu = inAppBrowserActivity.menu;
        map.put("hideUrlBar", Boolean.valueOf(menu == null || !menu.findItem(R.id.menu_search).isVisible()));
        ProgressBar progressBar = inAppBrowserActivity.progressBar;
        if (progressBar == null || progressBar.getMax() == 0) {
            z7 = true;
        }
        map.put("hideProgressBar", Boolean.valueOf(z7));
        return map;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public /* bridge */ /* synthetic */ ISettings<InAppBrowserActivity> parse(Map map) {
        return parse2((Map<String, Object>) map);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("hidden", this.hidden);
        hashMap.put("hideToolbarTop", this.hideToolbarTop);
        hashMap.put("toolbarTopBackgroundColor", this.toolbarTopBackgroundColor);
        hashMap.put("toolbarTopFixedTitle", this.toolbarTopFixedTitle);
        hashMap.put("hideUrlBar", this.hideUrlBar);
        hashMap.put("hideTitleBar", this.hideTitleBar);
        hashMap.put("closeOnCannotGoBack", this.closeOnCannotGoBack);
        hashMap.put("hideProgressBar", this.hideProgressBar);
        hashMap.put("allowGoBackWithBackButton", this.allowGoBackWithBackButton);
        hashMap.put("shouldCloseOnBackButtonPressed", this.shouldCloseOnBackButtonPressed);
        hashMap.put("hideDefaultMenuItems", this.hideDefaultMenuItems);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /* renamed from: parse  reason: avoid collision after fix types in other method */
    public ISettings<InAppBrowserActivity> parse2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key.hashCode();
                char c8 = 65535;
                switch (key.hashCode()) {
                    case -1307803139:
                        if (key.equals("hideTitleBar")) {
                            c8 = 0;
                            break;
                        }
                        break;
                    case -1217487446:
                        if (key.equals("hidden")) {
                            c8 = 1;
                            break;
                        }
                        break;
                    case -842349289:
                        if (key.equals("allowGoBackWithBackButton")) {
                            c8 = 2;
                            break;
                        }
                        break;
                    case -809085252:
                        if (key.equals("hideToolbarTop")) {
                            c8 = 3;
                            break;
                        }
                        break;
                    case -299596350:
                        if (key.equals("hideDefaultMenuItems")) {
                            c8 = 4;
                            break;
                        }
                        break;
                    case -118493506:
                        if (key.equals("toolbarTopFixedTitle")) {
                            c8 = 5;
                            break;
                        }
                        break;
                    case 3851268:
                        if (key.equals("hideProgressBar")) {
                            c8 = 6;
                            break;
                        }
                        break;
                    case 90270825:
                        if (key.equals("closeOnCannotGoBack")) {
                            c8 = 7;
                            break;
                        }
                        break;
                    case 406250502:
                        if (key.equals("hideUrlBar")) {
                            c8 = '\b';
                            break;
                        }
                        break;
                    case 2111633307:
                        if (key.equals("toolbarTopBackgroundColor")) {
                            c8 = '\t';
                            break;
                        }
                        break;
                    case 2140270213:
                        if (key.equals("shouldCloseOnBackButtonPressed")) {
                            c8 = '\n';
                            break;
                        }
                        break;
                }
                switch (c8) {
                    case 0:
                        this.hideTitleBar = (Boolean) value;
                        continue;
                    case 1:
                        this.hidden = (Boolean) value;
                        continue;
                    case 2:
                        this.allowGoBackWithBackButton = (Boolean) value;
                        continue;
                    case 3:
                        this.hideToolbarTop = (Boolean) value;
                        continue;
                    case 4:
                        this.hideDefaultMenuItems = (Boolean) value;
                        continue;
                    case 5:
                        this.toolbarTopFixedTitle = (String) value;
                        continue;
                    case b.TAB_HIDDEN /* 6 */:
                        this.hideProgressBar = (Boolean) value;
                        continue;
                    case 7:
                        this.closeOnCannotGoBack = (Boolean) value;
                        continue;
                    case '\b':
                        this.hideUrlBar = (Boolean) value;
                        continue;
                    case '\t':
                        this.toolbarTopBackgroundColor = (String) value;
                        continue;
                    case '\n':
                        this.shouldCloseOnBackButtonPressed = (Boolean) value;
                        continue;
                }
            }
        }
        return this;
    }
}
