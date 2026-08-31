package com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview;

import android.webkit.WebSettings;
import com.pichillilorenzo.flutter_inappwebview_android.ISettings;
import com.pichillilorenzo.flutter_inappwebview_android.types.PreferredContentModeOptionType;
import com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class InAppWebViewSettings implements ISettings<InAppWebViewInterface> {
    public static final String LOG_TAG = "InAppWebViewSettings";
    public Boolean algorithmicDarkeningAllowed;
    public Boolean allowBackgroundAudioPlaying;
    public Boolean allowContentAccess;
    public Boolean allowFileAccess;
    public Boolean allowFileAccessFromFileURLs;
    public Boolean allowUniversalAccessFromFileURLs;
    public String appCachePath;
    public String applicationNameForUserAgent;
    public Boolean blockNetworkImage;
    public Boolean blockNetworkLoads;
    public Boolean builtInZoomControls;
    public Boolean cacheEnabled;
    public Integer cacheMode;
    @Deprecated
    public Boolean clearCache;
    @Deprecated
    public Boolean clearSessionCache;
    public List<Map<String, Map<String, Object>>> contentBlockers;
    public String cursiveFontFamily;
    public Boolean databaseEnabled;
    public Integer defaultFixedFontSize;
    public Integer defaultFontSize;
    public String defaultTextEncodingName;
    public byte[] defaultVideoPoster;
    public Boolean disableContextMenu;
    public Boolean disableDefaultErrorPage;
    public Boolean disableHorizontalScroll;
    public Boolean disableVerticalScroll;
    public Integer disabledActionModeMenuItems;
    public Boolean displayZoomControls;
    public Boolean domStorageEnabled;
    public Boolean enterpriseAuthenticationAppLinkPolicyEnabled;
    public String fantasyFontFamily;
    public String fixedFontFamily;
    public Integer forceDark;
    public Integer forceDarkStrategy;
    public Boolean geolocationEnabled;
    public Boolean hardwareAcceleration;
    public Boolean horizontalScrollBarEnabled;
    public String horizontalScrollbarThumbColor;
    public String horizontalScrollbarTrackColor;
    public Boolean incognito;
    public Integer initialScale;
    public Boolean interceptOnlyAsyncAjaxRequests;
    public Boolean javaScriptCanOpenWindowsAutomatically;
    public Boolean javaScriptEnabled;
    public WebSettings.LayoutAlgorithm layoutAlgorithm;
    public Boolean loadWithOverviewMode;
    public Boolean loadsImagesAutomatically;
    public Boolean mediaPlaybackRequiresUserGesture;
    public Integer minimumFontSize;
    public Integer minimumLogicalFontSize;
    public Integer mixedContentMode;
    public Boolean needInitialFocus;
    public Boolean networkAvailable;
    public Boolean offscreenPreRaster;
    public Integer overScrollMode;
    public Integer preferredContentMode;
    public String regexToCancelSubFramesLoading;
    public Map<String, Object> rendererPriorityPolicy;
    public Set<String> requestedWithHeaderOriginAllowList;
    public List<String> resourceCustomSchemes;
    public Boolean safeBrowsingEnabled;
    public String sansSerifFontFamily;
    public Boolean saveFormData;
    public Integer scrollBarDefaultDelayBeforeFade;
    public Integer scrollBarFadeDuration;
    public Integer scrollBarStyle;
    public Boolean scrollbarFadingEnabled;
    public String serifFontFamily;
    public String standardFontFamily;
    public Boolean supportMultipleWindows;
    public Boolean supportZoom;
    public Integer textZoom;
    public Boolean thirdPartyCookiesEnabled;
    public Boolean transparentBackground;
    public Boolean useHybridComposition;
    public Boolean useOnDownloadStart;
    public Boolean useOnLoadResource;
    public Boolean useOnRenderProcessGone;
    public Boolean useShouldInterceptAjaxRequest;
    public Boolean useShouldInterceptFetchRequest;
    public Boolean useShouldInterceptRequest;
    public Boolean useShouldOverrideUrlLoading;
    public Boolean useWideViewPort;
    public String userAgent;
    public Boolean verticalScrollBarEnabled;
    public Integer verticalScrollbarPosition;
    public String verticalScrollbarThumbColor;
    public String verticalScrollbarTrackColor;
    public Map<String, Object> webViewAssetLoader;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewSettings$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$webkit$WebSettings$LayoutAlgorithm;

        static {
            int[] iArr = new int[WebSettings.LayoutAlgorithm.values().length];
            $SwitchMap$android$webkit$WebSettings$LayoutAlgorithm = iArr;
            try {
                iArr[WebSettings.LayoutAlgorithm.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$webkit$WebSettings$LayoutAlgorithm[WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$webkit$WebSettings$LayoutAlgorithm[WebSettings.LayoutAlgorithm.NARROW_COLUMNS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public InAppWebViewSettings() {
        Boolean bool = Boolean.FALSE;
        this.useShouldOverrideUrlLoading = bool;
        this.useOnLoadResource = bool;
        this.useOnDownloadStart = bool;
        this.clearCache = bool;
        this.userAgent = "";
        this.applicationNameForUserAgent = "";
        Boolean bool2 = Boolean.TRUE;
        this.javaScriptEnabled = bool2;
        this.javaScriptCanOpenWindowsAutomatically = bool;
        this.mediaPlaybackRequiresUserGesture = bool2;
        this.minimumFontSize = 8;
        this.verticalScrollBarEnabled = bool2;
        this.horizontalScrollBarEnabled = bool2;
        this.resourceCustomSchemes = new ArrayList();
        this.contentBlockers = new ArrayList();
        this.preferredContentMode = Integer.valueOf(PreferredContentModeOptionType.RECOMMENDED.toValue());
        this.useShouldInterceptAjaxRequest = bool;
        this.interceptOnlyAsyncAjaxRequests = bool2;
        this.useShouldInterceptFetchRequest = bool;
        this.incognito = bool;
        this.cacheEnabled = bool2;
        this.transparentBackground = bool;
        this.disableVerticalScroll = bool;
        this.disableHorizontalScroll = bool;
        this.disableContextMenu = bool;
        this.supportZoom = bool2;
        this.allowFileAccessFromFileURLs = bool;
        this.allowUniversalAccessFromFileURLs = bool;
        this.allowBackgroundAudioPlaying = bool;
        this.textZoom = 100;
        this.clearSessionCache = bool;
        this.builtInZoomControls = bool2;
        this.displayZoomControls = bool;
        this.databaseEnabled = bool;
        this.domStorageEnabled = bool2;
        this.useWideViewPort = bool2;
        this.safeBrowsingEnabled = bool2;
        this.allowContentAccess = bool2;
        this.allowFileAccess = bool2;
        this.blockNetworkImage = bool;
        this.blockNetworkLoads = bool;
        this.cacheMode = -1;
        this.cursiveFontFamily = "cursive";
        this.defaultFixedFontSize = 16;
        this.defaultFontSize = 16;
        this.defaultTextEncodingName = "UTF-8";
        this.fantasyFontFamily = "fantasy";
        this.fixedFontFamily = "monospace";
        this.forceDark = 0;
        this.forceDarkStrategy = 2;
        this.geolocationEnabled = bool2;
        this.loadWithOverviewMode = bool2;
        this.loadsImagesAutomatically = bool2;
        this.minimumLogicalFontSize = 8;
        this.initialScale = 0;
        this.needInitialFocus = bool2;
        this.offscreenPreRaster = bool;
        this.sansSerifFontFamily = "sans-serif";
        this.serifFontFamily = "sans-serif";
        this.standardFontFamily = "sans-serif";
        this.saveFormData = bool2;
        this.thirdPartyCookiesEnabled = bool2;
        this.hardwareAcceleration = bool2;
        this.supportMultipleWindows = bool;
        this.overScrollMode = 1;
        this.networkAvailable = null;
        this.scrollBarStyle = 0;
        this.verticalScrollbarPosition = 0;
        this.scrollBarDefaultDelayBeforeFade = null;
        this.scrollbarFadingEnabled = bool2;
        this.scrollBarFadeDuration = null;
        this.rendererPriorityPolicy = null;
        this.useShouldInterceptRequest = bool;
        this.useOnRenderProcessGone = bool;
        this.disableDefaultErrorPage = bool;
        this.useHybridComposition = bool2;
        this.algorithmicDarkeningAllowed = bool;
        this.enterpriseAuthenticationAppLinkPolicyEnabled = bool2;
    }

    private String getLayoutAlgorithm() {
        WebSettings.LayoutAlgorithm layoutAlgorithm = this.layoutAlgorithm;
        if (layoutAlgorithm != null) {
            int i8 = AnonymousClass1.$SwitchMap$android$webkit$WebSettings$LayoutAlgorithm[layoutAlgorithm.ordinal()];
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 != 3) {
                        return null;
                    }
                    return "NARROW_COLUMNS";
                }
                return "TEXT_AUTOSIZING";
            }
            return "NORMAL";
        }
        return null;
    }

    private void setLayoutAlgorithm(String str) {
        if (str != null) {
            char c8 = 65535;
            switch (str.hashCode()) {
                case -1986416409:
                    if (str.equals("NORMAL")) {
                        c8 = 0;
                        break;
                    }
                    break;
                case 1055046617:
                    if (str.equals("NARROW_COLUMNS")) {
                        c8 = 1;
                        break;
                    }
                    break;
                case 1561826623:
                    if (str.equals("TEXT_AUTOSIZING")) {
                        c8 = 2;
                        break;
                    }
                    break;
            }
            switch (c8) {
                case 1:
                    this.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS;
                case 0:
                    this.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL;
                    break;
                case 2:
                    break;
                default:
                    return;
            }
            this.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0301  */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Map<java.lang.String, java.lang.Object> getRealSettings(com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewInterface r8) {
        /*
            Method dump skipped, instructions count: 784
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewSettings.getRealSettings(com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewInterface):java.util.Map");
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public /* bridge */ /* synthetic */ ISettings<InAppWebViewInterface> parse(Map map) {
        return parse2((Map<String, Object>) map);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("useShouldOverrideUrlLoading", this.useShouldOverrideUrlLoading);
        hashMap.put("useOnLoadResource", this.useOnLoadResource);
        hashMap.put("useOnDownloadStart", this.useOnDownloadStart);
        hashMap.put("clearCache", this.clearCache);
        hashMap.put("userAgent", this.userAgent);
        hashMap.put("applicationNameForUserAgent", this.applicationNameForUserAgent);
        hashMap.put("javaScriptEnabled", this.javaScriptEnabled);
        hashMap.put("javaScriptCanOpenWindowsAutomatically", this.javaScriptCanOpenWindowsAutomatically);
        hashMap.put("mediaPlaybackRequiresUserGesture", this.mediaPlaybackRequiresUserGesture);
        hashMap.put("minimumFontSize", this.minimumFontSize);
        hashMap.put("verticalScrollBarEnabled", this.verticalScrollBarEnabled);
        hashMap.put("horizontalScrollBarEnabled", this.horizontalScrollBarEnabled);
        hashMap.put("resourceCustomSchemes", this.resourceCustomSchemes);
        hashMap.put("contentBlockers", this.contentBlockers);
        hashMap.put("preferredContentMode", this.preferredContentMode);
        hashMap.put("useShouldInterceptAjaxRequest", this.useShouldInterceptAjaxRequest);
        hashMap.put("interceptOnlyAsyncAjaxRequests", this.interceptOnlyAsyncAjaxRequests);
        hashMap.put("useShouldInterceptFetchRequest", this.useShouldInterceptFetchRequest);
        hashMap.put("incognito", this.incognito);
        hashMap.put("cacheEnabled", this.cacheEnabled);
        hashMap.put("transparentBackground", this.transparentBackground);
        hashMap.put("disableVerticalScroll", this.disableVerticalScroll);
        hashMap.put("disableHorizontalScroll", this.disableHorizontalScroll);
        hashMap.put("disableContextMenu", this.disableContextMenu);
        hashMap.put("textZoom", this.textZoom);
        hashMap.put("clearSessionCache", this.clearSessionCache);
        hashMap.put("builtInZoomControls", this.builtInZoomControls);
        hashMap.put("displayZoomControls", this.displayZoomControls);
        hashMap.put("supportZoom", this.supportZoom);
        hashMap.put("databaseEnabled", this.databaseEnabled);
        hashMap.put("domStorageEnabled", this.domStorageEnabled);
        hashMap.put("useWideViewPort", this.useWideViewPort);
        hashMap.put("safeBrowsingEnabled", this.safeBrowsingEnabled);
        hashMap.put("mixedContentMode", this.mixedContentMode);
        hashMap.put("allowContentAccess", this.allowContentAccess);
        hashMap.put("allowFileAccess", this.allowFileAccess);
        hashMap.put("allowFileAccessFromFileURLs", this.allowFileAccessFromFileURLs);
        hashMap.put("allowUniversalAccessFromFileURLs", this.allowUniversalAccessFromFileURLs);
        hashMap.put("appCachePath", this.appCachePath);
        hashMap.put("blockNetworkImage", this.blockNetworkImage);
        hashMap.put("blockNetworkLoads", this.blockNetworkLoads);
        hashMap.put("cacheMode", this.cacheMode);
        hashMap.put("cursiveFontFamily", this.cursiveFontFamily);
        hashMap.put("defaultFixedFontSize", this.defaultFixedFontSize);
        hashMap.put("defaultFontSize", this.defaultFontSize);
        hashMap.put("defaultTextEncodingName", this.defaultTextEncodingName);
        hashMap.put("disabledActionModeMenuItems", this.disabledActionModeMenuItems);
        hashMap.put("fantasyFontFamily", this.fantasyFontFamily);
        hashMap.put("fixedFontFamily", this.fixedFontFamily);
        hashMap.put("forceDark", this.forceDark);
        hashMap.put("forceDarkStrategy", this.forceDarkStrategy);
        hashMap.put("geolocationEnabled", this.geolocationEnabled);
        hashMap.put("layoutAlgorithm", getLayoutAlgorithm());
        hashMap.put("loadWithOverviewMode", this.loadWithOverviewMode);
        hashMap.put("loadsImagesAutomatically", this.loadsImagesAutomatically);
        hashMap.put("minimumLogicalFontSize", this.minimumLogicalFontSize);
        hashMap.put("initialScale", this.initialScale);
        hashMap.put("needInitialFocus", this.needInitialFocus);
        hashMap.put("offscreenPreRaster", this.offscreenPreRaster);
        hashMap.put("sansSerifFontFamily", this.sansSerifFontFamily);
        hashMap.put("serifFontFamily", this.serifFontFamily);
        hashMap.put("standardFontFamily", this.standardFontFamily);
        hashMap.put("saveFormData", this.saveFormData);
        hashMap.put("thirdPartyCookiesEnabled", this.thirdPartyCookiesEnabled);
        hashMap.put("hardwareAcceleration", this.hardwareAcceleration);
        hashMap.put("supportMultipleWindows", this.supportMultipleWindows);
        hashMap.put("regexToCancelSubFramesLoading", this.regexToCancelSubFramesLoading);
        hashMap.put("overScrollMode", this.overScrollMode);
        hashMap.put("networkAvailable", this.networkAvailable);
        hashMap.put("scrollBarStyle", this.scrollBarStyle);
        hashMap.put("verticalScrollbarPosition", this.verticalScrollbarPosition);
        hashMap.put("scrollBarDefaultDelayBeforeFade", this.scrollBarDefaultDelayBeforeFade);
        hashMap.put("scrollbarFadingEnabled", this.scrollbarFadingEnabled);
        hashMap.put("scrollBarFadeDuration", this.scrollBarFadeDuration);
        hashMap.put("rendererPriorityPolicy", this.rendererPriorityPolicy);
        hashMap.put("useShouldInterceptRequest", this.useShouldInterceptRequest);
        hashMap.put("useOnRenderProcessGone", this.useOnRenderProcessGone);
        hashMap.put("disableDefaultErrorPage", this.disableDefaultErrorPage);
        hashMap.put("useHybridComposition", this.useHybridComposition);
        hashMap.put("verticalScrollbarThumbColor", this.verticalScrollbarThumbColor);
        hashMap.put("verticalScrollbarTrackColor", this.verticalScrollbarTrackColor);
        hashMap.put("horizontalScrollbarThumbColor", this.horizontalScrollbarThumbColor);
        hashMap.put("horizontalScrollbarTrackColor", this.horizontalScrollbarTrackColor);
        hashMap.put("algorithmicDarkeningAllowed", this.algorithmicDarkeningAllowed);
        hashMap.put("enterpriseAuthenticationAppLinkPolicyEnabled", this.enterpriseAuthenticationAppLinkPolicyEnabled);
        hashMap.put("allowBackgroundAudioPlaying", this.allowBackgroundAudioPlaying);
        hashMap.put("defaultVideoPoster", this.defaultVideoPoster);
        hashMap.put("requestedWithHeaderOriginAllowList", this.requestedWithHeaderOriginAllowList != null ? new ArrayList(this.requestedWithHeaderOriginAllowList) : null);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /* renamed from: parse  reason: avoid collision after fix types in other method */
    public ISettings<InAppWebViewInterface> parse2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key.hashCode();
                char c8 = 65535;
                switch (key.hashCode()) {
                    case -2116596967:
                        if (key.equals("disableHorizontalScroll")) {
                            c8 = 0;
                            break;
                        }
                        break;
                    case -2095822429:
                        if (key.equals("scrollBarDefaultDelayBeforeFade")) {
                            c8 = 1;
                            break;
                        }
                        break;
                    case -2020146208:
                        if (key.equals("useWideViewPort")) {
                            c8 = 2;
                            break;
                        }
                        break;
                    case -2014672109:
                        if (key.equals("allowFileAccessFromFileURLs")) {
                            c8 = 3;
                            break;
                        }
                        break;
                    case -1931277743:
                        if (key.equals("defaultFontSize")) {
                            c8 = 4;
                            break;
                        }
                        break;
                    case -1851090878:
                        if (key.equals("supportZoom")) {
                            c8 = 5;
                            break;
                        }
                        break;
                    case -1845480382:
                        if (key.equals("scrollbarFadingEnabled")) {
                            c8 = 6;
                            break;
                        }
                        break;
                    case -1834028884:
                        if (key.equals("defaultTextEncodingName")) {
                            c8 = 7;
                            break;
                        }
                        break;
                    case -1746033750:
                        if (key.equals("needInitialFocus")) {
                            c8 = '\b';
                            break;
                        }
                        break;
                    case -1712086669:
                        if (key.equals("useShouldOverrideUrlLoading")) {
                            c8 = '\t';
                            break;
                        }
                        break;
                    case -1673892229:
                        if (key.equals("preferredContentMode")) {
                            c8 = '\n';
                            break;
                        }
                        break;
                    case -1657552268:
                        if (key.equals("allowContentAccess")) {
                            c8 = 11;
                            break;
                        }
                        break;
                    case -1626497241:
                        if (key.equals("fixedFontFamily")) {
                            c8 = '\f';
                            break;
                        }
                        break;
                    case -1615103092:
                        if (key.equals("builtInZoomControls")) {
                            c8 = '\r';
                            break;
                        }
                        break;
                    case -1607633676:
                        if (key.equals("javaScriptEnabled")) {
                            c8 = 14;
                            break;
                        }
                        break;
                    case -1578962296:
                        if (key.equals("hardwareAcceleration")) {
                            c8 = 15;
                            break;
                        }
                        break;
                    case -1578507205:
                        if (key.equals("networkAvailable")) {
                            c8 = 16;
                            break;
                        }
                        break;
                    case -1574470051:
                        if (key.equals("useShouldInterceptFetchRequest")) {
                            c8 = 17;
                            break;
                        }
                        break;
                    case -1480607106:
                        if (key.equals("loadsImagesAutomatically")) {
                            c8 = 18;
                            break;
                        }
                        break;
                    case -1455624881:
                        if (key.equals("resourceCustomSchemes")) {
                            c8 = 19;
                            break;
                        }
                        break;
                    case -1443655540:
                        if (key.equals("disabledActionModeMenuItems")) {
                            c8 = 20;
                            break;
                        }
                        break;
                    case -1423657812:
                        if (key.equals("incognito")) {
                            c8 = 21;
                            break;
                        }
                        break;
                    case -1410791825:
                        if (key.equals("allowBackgroundAudioPlaying")) {
                            c8 = 22;
                            break;
                        }
                        break;
                    case -1349570230:
                        if (key.equals("webViewAssetLoader")) {
                            c8 = 23;
                            break;
                        }
                        break;
                    case -1321236988:
                        if (key.equals("overScrollMode")) {
                            c8 = 24;
                            break;
                        }
                        break;
                    case -1146673624:
                        if (key.equals("domStorageEnabled")) {
                            c8 = 25;
                            break;
                        }
                        break;
                    case -1143245914:
                        if (key.equals("disableContextMenu")) {
                            c8 = 26;
                            break;
                        }
                        break;
                    case -1038715033:
                        if (key.equals("useShouldInterceptAjaxRequest")) {
                            c8 = 27;
                            break;
                        }
                        break;
                    case -1003454816:
                        if (key.equals("textZoom")) {
                            c8 = 28;
                            break;
                        }
                        break;
                    case -868328270:
                        if (key.equals("interceptOnlyAsyncAjaxRequests")) {
                            c8 = 29;
                            break;
                        }
                        break;
                    case -800676066:
                        if (key.equals("minimumFontSize")) {
                            c8 = 30;
                            break;
                        }
                        break;
                    case -767637403:
                        if (key.equals("layoutAlgorithm")) {
                            c8 = 31;
                            break;
                        }
                        break;
                    case -759238347:
                        if (key.equals("clearCache")) {
                            c8 = ' ';
                            break;
                        }
                        break;
                    case -742944736:
                        if (key.equals("transparentBackground")) {
                            c8 = '!';
                            break;
                        }
                        break;
                    case -741649011:
                        if (key.equals("enterpriseAuthenticationAppLinkPolicyEnabled")) {
                            c8 = '\"';
                            break;
                        }
                        break;
                    case -728016272:
                        if (key.equals("allowUniversalAccessFromFileURLs")) {
                            c8 = '#';
                            break;
                        }
                        break;
                    case -710246074:
                        if (key.equals("databaseEnabled")) {
                            c8 = '$';
                            break;
                        }
                        break;
                    case -706772569:
                        if (key.equals("useShouldInterceptRequest")) {
                            c8 = '%';
                            break;
                        }
                        break;
                    case -553792443:
                        if (key.equals("cacheMode")) {
                            c8 = '&';
                            break;
                        }
                        break;
                    case -443422049:
                        if (key.equals("horizontalScrollBarEnabled")) {
                            c8 = '\'';
                            break;
                        }
                        break;
                    case -435719349:
                        if (key.equals("scrollBarStyle")) {
                            c8 = '(';
                            break;
                        }
                        break;
                    case -421090202:
                        if (key.equals("initialScale")) {
                            c8 = ')';
                            break;
                        }
                        break;
                    case -321425255:
                        if (key.equals("verticalScrollbarPosition")) {
                            c8 = '*';
                            break;
                        }
                        break;
                    case -229178645:
                        if (key.equals("disableVerticalScroll")) {
                            c8 = '+';
                            break;
                        }
                        break;
                    case -227577491:
                        if (key.equals("javaScriptCanOpenWindowsAutomatically")) {
                            c8 = ',';
                            break;
                        }
                        break;
                    case -225496870:
                        if (key.equals("horizontalScrollbarTrackColor")) {
                            c8 = '-';
                            break;
                        }
                        break;
                    case -225165915:
                        if (key.equals("offscreenPreRaster")) {
                            c8 = '.';
                            break;
                        }
                        break;
                    case -216514471:
                        if (key.equals("fantasyFontFamily")) {
                            c8 = '/';
                            break;
                        }
                        break;
                    case -158057575:
                        if (key.equals("rendererPriorityPolicy")) {
                            c8 = '0';
                            break;
                        }
                        break;
                    case -98561827:
                        if (key.equals("sansSerifFontFamily")) {
                            c8 = '1';
                            break;
                        }
                        break;
                    case 57717170:
                        if (key.equals("regexToCancelSubFramesLoading")) {
                            c8 = '2';
                            break;
                        }
                        break;
                    case 100868168:
                        if (key.equals("verticalScrollbarTrackColor")) {
                            c8 = '3';
                            break;
                        }
                        break;
                    case 174479508:
                        if (key.equals("useOnDownloadStart")) {
                            c8 = '4';
                            break;
                        }
                        break;
                    case 229242772:
                        if (key.equals("forceDarkStrategy")) {
                            c8 = '5';
                            break;
                        }
                        break;
                    case 257886264:
                        if (key.equals("cursiveFontFamily")) {
                            c8 = '6';
                            break;
                        }
                        break;
                    case 273267153:
                        if (key.equals("mediaPlaybackRequiresUserGesture")) {
                            c8 = '7';
                            break;
                        }
                        break;
                    case 296040698:
                        if (key.equals("blockNetworkImage")) {
                            c8 = '8';
                            break;
                        }
                        break;
                    case 298870764:
                        if (key.equals("blockNetworkLoads")) {
                            c8 = '9';
                            break;
                        }
                        break;
                    case 311430650:
                        if (key.equals("userAgent")) {
                            c8 = ':';
                            break;
                        }
                        break;
                    case 387230482:
                        if (key.equals("useOnRenderProcessGone")) {
                            c8 = ';';
                            break;
                        }
                        break;
                    case 393481210:
                        if (key.equals("useOnLoadResource")) {
                            c8 = '<';
                            break;
                        }
                        break;
                    case 397237599:
                        if (key.equals("cacheEnabled")) {
                            c8 = '=';
                            break;
                        }
                        break;
                    case 408799019:
                        if (key.equals("saveFormData")) {
                            c8 = '>';
                            break;
                        }
                        break;
                    case 477312960:
                        if (key.equals("requestedWithHeaderOriginAllowList")) {
                            c8 = '?';
                            break;
                        }
                        break;
                    case 487904071:
                        if (key.equals("useHybridComposition")) {
                            c8 = '@';
                            break;
                        }
                        break;
                    case 590869196:
                        if (key.equals("applicationNameForUserAgent")) {
                            c8 = 'A';
                            break;
                        }
                        break;
                    case 760962753:
                        if (key.equals("mixedContentMode")) {
                            c8 = 'B';
                            break;
                        }
                        break;
                    case 849171798:
                        if (key.equals("scrollBarFadeDuration")) {
                            c8 = 'C';
                            break;
                        }
                        break;
                    case 1138246185:
                        if (key.equals("allowFileAccess")) {
                            c8 = 'D';
                            break;
                        }
                        break;
                    case 1156608422:
                        if (key.equals("appCachePath")) {
                            c8 = 'E';
                            break;
                        }
                        break;
                    case 1193086767:
                        if (key.equals("horizontalScrollbarThumbColor")) {
                            c8 = 'F';
                            break;
                        }
                        break;
                    case 1301942064:
                        if (key.equals("standardFontFamily")) {
                            c8 = 'G';
                            break;
                        }
                        break;
                    case 1320461707:
                        if (key.equals("displayZoomControls")) {
                            c8 = 'H';
                            break;
                        }
                        break;
                    case 1344414299:
                        if (key.equals("geolocationEnabled")) {
                            c8 = 'I';
                            break;
                        }
                        break;
                    case 1409728424:
                        if (key.equals("loadWithOverviewMode")) {
                            c8 = 'J';
                            break;
                        }
                        break;
                    case 1474890029:
                        if (key.equals("safeBrowsingEnabled")) {
                            c8 = 'K';
                            break;
                        }
                        break;
                    case 1496887792:
                        if (key.equals("serifFontFamily")) {
                            c8 = 'L';
                            break;
                        }
                        break;
                    case 1519451805:
                        if (key.equals("verticalScrollbarThumbColor")) {
                            c8 = 'M';
                            break;
                        }
                        break;
                    case 1527546113:
                        if (key.equals("forceDark")) {
                            c8 = 'N';
                            break;
                        }
                        break;
                    case 1583791742:
                        if (key.equals("disableDefaultErrorPage")) {
                            c8 = 'O';
                            break;
                        }
                        break;
                    case 1759079762:
                        if (key.equals("contentBlockers")) {
                            c8 = 'P';
                            break;
                        }
                        break;
                    case 1774215812:
                        if (key.equals("supportMultipleWindows")) {
                            c8 = 'Q';
                            break;
                        }
                        break;
                    case 1793491907:
                        if (key.equals("defaultFixedFontSize")) {
                            c8 = 'R';
                            break;
                        }
                        break;
                    case 1812525393:
                        if (key.equals("thirdPartyCookiesEnabled")) {
                            c8 = 'S';
                            break;
                        }
                        break;
                    case 1956631083:
                        if (key.equals("minimumLogicalFontSize")) {
                            c8 = 'T';
                            break;
                        }
                        break;
                    case 2011947505:
                        if (key.equals("verticalScrollBarEnabled")) {
                            c8 = 'U';
                            break;
                        }
                        break;
                    case 2038327673:
                        if (key.equals("clearSessionCache")) {
                            c8 = 'V';
                            break;
                        }
                        break;
                    case 2056553639:
                        if (key.equals("defaultVideoPoster")) {
                            c8 = 'W';
                            break;
                        }
                        break;
                    case 2086547246:
                        if (key.equals("algorithmicDarkeningAllowed")) {
                            c8 = 'X';
                            break;
                        }
                        break;
                }
                switch (c8) {
                    case 0:
                        this.disableHorizontalScroll = (Boolean) value;
                        continue;
                    case 1:
                        this.scrollBarDefaultDelayBeforeFade = (Integer) value;
                        continue;
                    case 2:
                        this.useWideViewPort = (Boolean) value;
                        continue;
                    case 3:
                        this.allowFileAccessFromFileURLs = (Boolean) value;
                        continue;
                    case 4:
                        this.defaultFontSize = (Integer) value;
                        continue;
                    case 5:
                        this.supportZoom = (Boolean) value;
                        continue;
                    case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                        this.scrollbarFadingEnabled = (Boolean) value;
                        continue;
                    case 7:
                        this.defaultTextEncodingName = (String) value;
                        continue;
                    case '\b':
                        this.needInitialFocus = (Boolean) value;
                        continue;
                    case '\t':
                        this.useShouldOverrideUrlLoading = (Boolean) value;
                        continue;
                    case '\n':
                        this.preferredContentMode = (Integer) value;
                        continue;
                    case 11:
                        this.allowContentAccess = (Boolean) value;
                        continue;
                    case '\f':
                        this.fixedFontFamily = (String) value;
                        continue;
                    case '\r':
                        this.builtInZoomControls = (Boolean) value;
                        continue;
                    case 14:
                        this.javaScriptEnabled = (Boolean) value;
                        continue;
                    case 15:
                        this.hardwareAcceleration = (Boolean) value;
                        continue;
                    case 16:
                        this.networkAvailable = (Boolean) value;
                        continue;
                    case 17:
                        this.useShouldInterceptFetchRequest = (Boolean) value;
                        continue;
                    case 18:
                        this.loadsImagesAutomatically = (Boolean) value;
                        continue;
                    case 19:
                        this.resourceCustomSchemes = (List) value;
                        continue;
                    case 20:
                        this.disabledActionModeMenuItems = (Integer) value;
                        continue;
                    case 21:
                        this.incognito = (Boolean) value;
                        continue;
                    case 22:
                        this.allowBackgroundAudioPlaying = (Boolean) value;
                        continue;
                    case 23:
                        this.webViewAssetLoader = (Map) value;
                        continue;
                    case 24:
                        this.overScrollMode = (Integer) value;
                        continue;
                    case 25:
                        this.domStorageEnabled = (Boolean) value;
                        continue;
                    case 26:
                        this.disableContextMenu = (Boolean) value;
                        continue;
                    case 27:
                        this.useShouldInterceptAjaxRequest = (Boolean) value;
                        continue;
                    case 28:
                        this.textZoom = (Integer) value;
                        continue;
                    case 29:
                        this.interceptOnlyAsyncAjaxRequests = (Boolean) value;
                        continue;
                    case 30:
                        this.minimumFontSize = (Integer) value;
                        continue;
                    case 31:
                        setLayoutAlgorithm((String) value);
                        continue;
                    case ' ':
                        this.clearCache = (Boolean) value;
                        continue;
                    case '!':
                        this.transparentBackground = (Boolean) value;
                        continue;
                    case '\"':
                        this.enterpriseAuthenticationAppLinkPolicyEnabled = (Boolean) value;
                        continue;
                    case '#':
                        this.allowUniversalAccessFromFileURLs = (Boolean) value;
                        continue;
                    case '$':
                        this.databaseEnabled = (Boolean) value;
                        continue;
                    case '%':
                        this.useShouldInterceptRequest = (Boolean) value;
                        continue;
                    case '&':
                        this.cacheMode = (Integer) value;
                        continue;
                    case '\'':
                        this.horizontalScrollBarEnabled = (Boolean) value;
                        continue;
                    case '(':
                        this.scrollBarStyle = (Integer) value;
                        continue;
                    case ')':
                        this.initialScale = (Integer) value;
                        continue;
                    case '*':
                        this.verticalScrollbarPosition = (Integer) value;
                        continue;
                    case '+':
                        this.disableVerticalScroll = (Boolean) value;
                        continue;
                    case ',':
                        this.javaScriptCanOpenWindowsAutomatically = (Boolean) value;
                        continue;
                    case '-':
                        this.horizontalScrollbarTrackColor = (String) value;
                        continue;
                    case '.':
                        this.offscreenPreRaster = (Boolean) value;
                        continue;
                    case '/':
                        this.fantasyFontFamily = (String) value;
                        continue;
                    case '0':
                        this.rendererPriorityPolicy = (Map) value;
                        continue;
                    case '1':
                        this.sansSerifFontFamily = (String) value;
                        continue;
                    case '2':
                        this.regexToCancelSubFramesLoading = (String) value;
                        continue;
                    case '3':
                        this.verticalScrollbarTrackColor = (String) value;
                        continue;
                    case '4':
                        this.useOnDownloadStart = (Boolean) value;
                        continue;
                    case '5':
                        this.forceDarkStrategy = (Integer) value;
                        continue;
                    case '6':
                        this.cursiveFontFamily = (String) value;
                        continue;
                    case '7':
                        this.mediaPlaybackRequiresUserGesture = (Boolean) value;
                        continue;
                    case '8':
                        this.blockNetworkImage = (Boolean) value;
                        continue;
                    case '9':
                        this.blockNetworkLoads = (Boolean) value;
                        continue;
                    case ':':
                        this.userAgent = (String) value;
                        continue;
                    case ';':
                        this.useOnRenderProcessGone = (Boolean) value;
                        continue;
                    case '<':
                        this.useOnLoadResource = (Boolean) value;
                        continue;
                    case '=':
                        this.cacheEnabled = (Boolean) value;
                        continue;
                    case '>':
                        this.saveFormData = (Boolean) value;
                        continue;
                    case '?':
                        this.requestedWithHeaderOriginAllowList = new HashSet((List) value);
                        continue;
                    case '@':
                        this.useHybridComposition = (Boolean) value;
                        continue;
                    case 'A':
                        this.applicationNameForUserAgent = (String) value;
                        continue;
                    case 'B':
                        this.mixedContentMode = (Integer) value;
                        continue;
                    case 'C':
                        this.scrollBarFadeDuration = (Integer) value;
                        continue;
                    case 'D':
                        this.allowFileAccess = (Boolean) value;
                        continue;
                    case 'E':
                        this.appCachePath = (String) value;
                        continue;
                    case 'F':
                        this.horizontalScrollbarThumbColor = (String) value;
                        continue;
                    case 'G':
                        this.standardFontFamily = (String) value;
                        continue;
                    case 'H':
                        this.displayZoomControls = (Boolean) value;
                        continue;
                    case 'I':
                        this.geolocationEnabled = (Boolean) value;
                        continue;
                    case 'J':
                        this.loadWithOverviewMode = (Boolean) value;
                        continue;
                    case 'K':
                        this.safeBrowsingEnabled = (Boolean) value;
                        continue;
                    case 'L':
                        this.serifFontFamily = (String) value;
                        continue;
                    case 'M':
                        this.verticalScrollbarThumbColor = (String) value;
                        continue;
                    case 'N':
                        this.forceDark = (Integer) value;
                        continue;
                    case 'O':
                        this.disableDefaultErrorPage = (Boolean) value;
                        continue;
                    case 'P':
                        this.contentBlockers = (List) value;
                        continue;
                    case 'Q':
                        this.supportMultipleWindows = (Boolean) value;
                        continue;
                    case 'R':
                        this.defaultFixedFontSize = (Integer) value;
                        continue;
                    case 'S':
                        this.thirdPartyCookiesEnabled = (Boolean) value;
                        continue;
                    case 'T':
                        this.minimumLogicalFontSize = (Integer) value;
                        continue;
                    case 'U':
                        this.verticalScrollBarEnabled = (Boolean) value;
                        continue;
                    case 'V':
                        this.clearSessionCache = (Boolean) value;
                        continue;
                    case 'W':
                        this.defaultVideoPoster = (byte[]) value;
                        continue;
                    case 'X':
                        this.algorithmicDarkeningAllowed = (Boolean) value;
                        continue;
                }
            }
        }
        return this;
    }
}
