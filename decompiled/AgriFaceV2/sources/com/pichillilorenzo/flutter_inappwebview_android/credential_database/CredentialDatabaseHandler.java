package com.pichillilorenzo.flutter_inappwebview_android.credential_database;

import android.content.Context;
import android.webkit.WebViewDatabase;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLCredentialContract;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLProtectionSpaceContract;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLCredential;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLProtectionSpace;
import i6.i;
import i6.j;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes.dex */
public class CredentialDatabaseHandler extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "CredentialDatabaseHandler";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_credential_database";
    public static CredentialDatabase credentialDatabase;
    public InAppWebViewFlutterPlugin plugin;

    public CredentialDatabaseHandler(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new j(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.plugin = inAppWebViewFlutterPlugin;
    }

    public static void init(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        if (credentialDatabase == null) {
            credentialDatabase = CredentialDatabase.getInstance(inAppWebViewFlutterPlugin.applicationContext);
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.plugin = null;
        credentialDatabase = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v11, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r14v12, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r14v16, types: [java.util.List, java.util.ArrayList] */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        Context context;
        Object obj;
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null) {
            init(inAppWebViewFlutterPlugin);
        }
        String str = iVar.f7535a;
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1851697792:
                if (str.equals("clearAllAuthCredentials")) {
                    c8 = 0;
                    break;
                }
                break;
            case -410271914:
                if (str.equals("getHttpAuthCredentials")) {
                    c8 = 1;
                    break;
                }
                break;
            case 589173355:
                if (str.equals("removeHttpAuthCredential")) {
                    c8 = 2;
                    break;
                }
                break;
            case 998955721:
                if (str.equals("setHttpAuthCredential")) {
                    c8 = 3;
                    break;
                }
                break;
            case 1084504936:
                if (str.equals("removeHttpAuthCredentials")) {
                    c8 = 4;
                    break;
                }
                break;
            case 1930845769:
                if (str.equals("getAllAuthCredentials")) {
                    c8 = 5;
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                CredentialDatabase credentialDatabase2 = credentialDatabase;
                if (credentialDatabase2 != null) {
                    credentialDatabase2.clearAllAuthCredentials();
                    InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin2 = this.plugin;
                    if (inAppWebViewFlutterPlugin2 != null && (context = inAppWebViewFlutterPlugin2.applicationContext) != null) {
                        WebViewDatabase.getInstance(context).clearHttpAuthUsernamePassword();
                    }
                    obj = Boolean.TRUE;
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 1:
                ArrayList arrayList = new ArrayList();
                if (credentialDatabase != null) {
                    for (URLCredential uRLCredential : credentialDatabase.getHttpAuthCredentials((String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST), (String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PROTOCOL), (String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_REALM), (Integer) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT))) {
                        arrayList.add(uRLCredential.toMap());
                    }
                }
                dVar.success(arrayList);
                return;
            case 2:
                if (credentialDatabase != null) {
                    credentialDatabase.removeHttpAuthCredential((String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST), (String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PROTOCOL), (String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_REALM), (Integer) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT), (String) iVar.a(URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME), (String) iVar.a(URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD));
                    obj = Boolean.TRUE;
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 3:
                if (credentialDatabase != null) {
                    credentialDatabase.setHttpAuthCredential((String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST), (String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PROTOCOL), (String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_REALM), (Integer) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT), (String) iVar.a(URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME), (String) iVar.a(URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD));
                    obj = Boolean.TRUE;
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 4:
                if (credentialDatabase != null) {
                    credentialDatabase.removeHttpAuthCredentials((String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST), (String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PROTOCOL), (String) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_REALM), (Integer) iVar.a(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT));
                    obj = Boolean.TRUE;
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 5:
                obj = new ArrayList();
                CredentialDatabase credentialDatabase3 = credentialDatabase;
                if (credentialDatabase3 != null) {
                    for (URLProtectionSpace uRLProtectionSpace : credentialDatabase3.protectionSpaceDao.getAll()) {
                        ArrayList arrayList2 = new ArrayList();
                        for (URLCredential uRLCredential2 : credentialDatabase.credentialDao.getAllByProtectionSpaceId(uRLProtectionSpace.getId())) {
                            arrayList2.add(uRLCredential2.toMap());
                        }
                        HashMap hashMap = new HashMap();
                        hashMap.put("protectionSpace", uRLProtectionSpace.toMap());
                        hashMap.put("credentials", arrayList2);
                        obj.add(hashMap);
                    }
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
