package org.chromium.support_lib_boundary;

import java.util.Set;
/* loaded from: classes.dex */
public interface ServiceWorkerWebSettingsBoundaryInterface {
    boolean getAllowContentAccess();

    boolean getAllowFileAccess();

    boolean getBlockNetworkLoads();

    int getCacheMode();

    Set<String> getRequestedWithHeaderOriginAllowList();

    void setAllowContentAccess(boolean z7);

    void setAllowFileAccess(boolean z7);

    void setBlockNetworkLoads(boolean z7);

    void setCacheMode(int i8);

    void setRequestedWithHeaderOriginAllowList(Set<String> set);
}
