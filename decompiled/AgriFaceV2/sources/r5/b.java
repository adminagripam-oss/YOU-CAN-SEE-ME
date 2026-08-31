package r5;

import a4.ca;
import a4.de;
import a4.he;
import a4.je;
import a4.le;
import a4.oe;
import a4.qd;
import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.face.ModuleDescriptor;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b implements c {

    /* renamed from: a  reason: collision with root package name */
    private final Context f11323a;

    /* renamed from: b  reason: collision with root package name */
    private final q5.e f11324b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f11325c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f11326d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f11327e;

    /* renamed from: f  reason: collision with root package name */
    private final qd f11328f;

    /* renamed from: g  reason: collision with root package name */
    private le f11329g;

    /* renamed from: h  reason: collision with root package name */
    private le f11330h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context, q5.e eVar, qd qdVar) {
        this.f11323a = context;
        this.f11324b = eVar;
        this.f11328f = qdVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(Context context) {
        return DynamiteModule.a(context, ModuleDescriptor.MODULE_ID) > 0;
    }

    private final void e() {
        he heVar;
        if (this.f11324b.c() == 2) {
            if (this.f11329g == null) {
                this.f11329g = f(new he(this.f11324b.e(), 1, 1, 2, false, this.f11324b.a()));
            }
            if ((this.f11324b.d() != 2 && this.f11324b.b() != 2 && this.f11324b.e() != 2) || this.f11330h != null) {
                return;
            }
            heVar = new he(this.f11324b.e(), this.f11324b.d(), this.f11324b.b(), 1, this.f11324b.g(), this.f11324b.a());
        } else if (this.f11330h != null) {
            return;
        } else {
            heVar = new he(this.f11324b.e(), this.f11324b.d(), this.f11324b.b(), 1, this.f11324b.g(), this.f11324b.a());
        }
        this.f11330h = f(heVar);
    }

    private final le f(he heVar) {
        DynamiteModule.b bVar;
        String str;
        String str2;
        if (this.f11326d) {
            bVar = DynamiteModule.f5090c;
            str = ModuleDescriptor.MODULE_ID;
            str2 = "com.google.mlkit.vision.face.bundled.internal.ThickFaceDetectorCreator";
        } else {
            bVar = DynamiteModule.f5089b;
            str = "com.google.android.gms.vision.face";
            str2 = "com.google.android.gms.vision.face.mlkit.FaceDetectorCreator";
        }
        return d(bVar, str, str2, heVar);
    }

    private static List g(le leVar, o5.a aVar) {
        if (aVar.h() == -1) {
            aVar = o5.a.c(p5.c.c().b(aVar, false), aVar.m(), aVar.i(), aVar.l(), 17);
        }
        try {
            List<je> d02 = leVar.d0(p5.d.b().a(aVar), new de(aVar.h(), aVar.m(), aVar.i(), p5.b.a(aVar.l()), SystemClock.elapsedRealtime()));
            ArrayList arrayList = new ArrayList();
            for (je jeVar : d02) {
                arrayList.add(new q5.a(jeVar, aVar.g()));
            }
            return arrayList;
        } catch (RemoteException e8) {
            throw new i5.a("Failed to run face detector.", 13, e8);
        }
    }

    @Override // r5.c
    public final Pair a(o5.a aVar) {
        List list;
        if (this.f11330h == null && this.f11329g == null) {
            b();
        }
        if (!this.f11325c) {
            try {
                le leVar = this.f11330h;
                if (leVar != null) {
                    leVar.c();
                }
                le leVar2 = this.f11329g;
                if (leVar2 != null) {
                    leVar2.c();
                }
                this.f11325c = true;
            } catch (RemoteException e8) {
                throw new i5.a("Failed to init face detector.", 13, e8);
            }
        }
        le leVar3 = this.f11330h;
        List list2 = null;
        if (leVar3 != null) {
            list = g(leVar3, aVar);
            if (!this.f11324b.g()) {
                i.m(list);
            }
        } else {
            list = null;
        }
        le leVar4 = this.f11329g;
        if (leVar4 != null) {
            list2 = g(leVar4, aVar);
            i.m(list2);
        }
        return new Pair(list, list2);
    }

    @Override // r5.c
    public final boolean b() {
        if (this.f11330h == null && this.f11329g == null) {
            if (DynamiteModule.a(this.f11323a, ModuleDescriptor.MODULE_ID) > 0) {
                this.f11326d = true;
                try {
                    e();
                } catch (RemoteException e8) {
                    throw new i5.a("Failed to create thick face detector.", 13, e8);
                } catch (DynamiteModule.a e9) {
                    throw new i5.a("Failed to load the bundled face module.", 13, e9);
                }
            } else {
                this.f11326d = false;
                try {
                    e();
                } catch (RemoteException e10) {
                    k.c(this.f11328f, this.f11326d, ca.OPTIONAL_MODULE_INIT_ERROR);
                    throw new i5.a("Failed to create thin face detector.", 13, e10);
                } catch (DynamiteModule.a e11) {
                    if (!this.f11327e) {
                        m5.l.a(this.f11323a, "face");
                        this.f11327e = true;
                    }
                    k.c(this.f11328f, this.f11326d, ca.OPTIONAL_MODULE_NOT_AVAILABLE);
                    throw new i5.a("Waiting for the face module to be downloaded. Please wait.", 14, e11);
                }
            }
            k.c(this.f11328f, this.f11326d, ca.NO_ERROR);
            return this.f11326d;
        }
        return this.f11326d;
    }

    final le d(DynamiteModule.b bVar, String str, String str2, he heVar) {
        return oe.d(DynamiteModule.d(this.f11323a, bVar, str).c(str2)).N(t3.b.d0(this.f11323a), heVar);
    }

    @Override // r5.c
    public final void zzb() {
        try {
            le leVar = this.f11330h;
            if (leVar != null) {
                leVar.P();
                this.f11330h = null;
            }
            le leVar2 = this.f11329g;
            if (leVar2 != null) {
                leVar2.P();
                this.f11329g = null;
            }
        } catch (RemoteException e8) {
            Log.e("DecoupledFaceDelegate", "Failed to release face detector.", e8);
        }
        this.f11325c = false;
    }
}
