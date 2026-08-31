package androidx.webkit.internal;

import java.util.Set;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;
/* loaded from: classes.dex */
public class f1 {

    /* renamed from: a  reason: collision with root package name */
    private final WebSettingsBoundaryInterface f4016a;

    public f1(WebSettingsBoundaryInterface webSettingsBoundaryInterface) {
        this.f4016a = webSettingsBoundaryInterface;
    }

    public int a() {
        return this.f4016a.getDisabledActionModeMenuItems();
    }

    public boolean b() {
        return this.f4016a.getEnterpriseAuthenticationAppLinkPolicyEnabled();
    }

    public int c() {
        return this.f4016a.getForceDark();
    }

    public boolean d() {
        return this.f4016a.getOffscreenPreRaster();
    }

    public Set e() {
        return this.f4016a.getRequestedWithHeaderOriginAllowList();
    }

    public boolean f() {
        return this.f4016a.getSafeBrowsingEnabled();
    }

    public boolean g() {
        return this.f4016a.isAlgorithmicDarkeningAllowed();
    }

    public void h(boolean z7) {
        this.f4016a.setAlgorithmicDarkeningAllowed(z7);
    }

    public void i(int i8) {
        this.f4016a.setDisabledActionModeMenuItems(i8);
    }

    public void j(boolean z7) {
        this.f4016a.setEnterpriseAuthenticationAppLinkPolicyEnabled(z7);
    }

    public void k(int i8) {
        this.f4016a.setForceDark(i8);
    }

    public void l(int i8) {
        this.f4016a.setForceDarkBehavior(i8);
    }

    public void m(boolean z7) {
        this.f4016a.setOffscreenPreRaster(z7);
    }

    public void n(Set set) {
        this.f4016a.setRequestedWithHeaderOriginAllowList(set);
    }

    public void o(boolean z7) {
        this.f4016a.setSafeBrowsingEnabled(z7);
    }
}
