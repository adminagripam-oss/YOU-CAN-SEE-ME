package com.pichillilorenzo.flutter_inappwebview_android.find_interaction;

import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.FindSession;
import i6.i;
import i6.j;
import java.util.HashMap;
/* loaded from: classes.dex */
public class FindInteractionChannelDelegate extends ChannelDelegateImpl {
    private FindInteractionController findInteractionController;

    public FindInteractionChannelDelegate(FindInteractionController findInteractionController, j jVar) {
        super(jVar);
        this.findInteractionController = findInteractionController;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.findInteractionController = null;
    }

    public void onFindResultReceived(int i8, int i9, boolean z7) {
        FindInteractionController findInteractionController;
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        if (z7 && (findInteractionController = this.findInteractionController) != null && findInteractionController.webView != null) {
            findInteractionController.activeFindSession = new FindSession(i9, i8);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("activeMatchOrdinal", Integer.valueOf(i8));
        hashMap.put("numberOfMatches", Integer.valueOf(i9));
        hashMap.put("isDoneCounting", Boolean.valueOf(z7));
        channel.c("onFindResultReceived", hashMap);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        Object obj;
        FindSession findSession;
        String str = iVar.f7535a;
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1008221461:
                if (str.equals("getSearchText")) {
                    c8 = 0;
                    break;
                }
                break;
            case -853211864:
                if (str.equals("findAll")) {
                    c8 = 1;
                    break;
                }
                break;
            case -679382964:
                if (str.equals("findNext")) {
                    c8 = 2;
                    break;
                }
                break;
            case -234090249:
                if (str.equals("setSearchText")) {
                    c8 = 3;
                    break;
                }
                break;
            case 817048102:
                if (str.equals("clearMatches")) {
                    c8 = 4;
                    break;
                }
                break;
            case 2137531137:
                if (str.equals("getActiveFindSession")) {
                    c8 = 5;
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                FindInteractionController findInteractionController = this.findInteractionController;
                if (findInteractionController != null) {
                    obj = findInteractionController.searchText;
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 1:
                if (this.findInteractionController != null) {
                    this.findInteractionController.findAll((String) iVar.a("find"));
                }
                obj = Boolean.TRUE;
                break;
            case 2:
                if (this.findInteractionController != null) {
                    this.findInteractionController.findNext(((Boolean) iVar.a("forward")).booleanValue());
                }
                obj = Boolean.TRUE;
                break;
            case 3:
                FindInteractionController findInteractionController2 = this.findInteractionController;
                if (findInteractionController2 != null) {
                    findInteractionController2.searchText = (String) iVar.a("searchText");
                    obj = Boolean.TRUE;
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 4:
                FindInteractionController findInteractionController3 = this.findInteractionController;
                if (findInteractionController3 != null) {
                    findInteractionController3.clearMatches();
                }
                obj = Boolean.TRUE;
                break;
            case 5:
                FindInteractionController findInteractionController4 = this.findInteractionController;
                if (findInteractionController4 != null && (findSession = findInteractionController4.activeFindSession) != null) {
                    obj = findSession.toMap();
                    break;
                } else {
                    obj = null;
                    break;
                }
                break;
            default:
                dVar.notImplemented();
                return;
        }
        dVar.success(obj);
    }
}
