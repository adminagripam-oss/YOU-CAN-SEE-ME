package r;

import b0.w2;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class a4 {
    public static List a(int i8, boolean z7, boolean z8) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(f());
        if (i8 == 0 || i8 == 1 || i8 == 3) {
            arrayList.addAll(h());
        }
        if (i8 == 1 || i8 == 3) {
            arrayList.addAll(e());
        }
        if (z7) {
            arrayList.addAll(j());
        }
        if (z8 && i8 == 0) {
            arrayList.addAll(c());
        }
        if (i8 == 3) {
            arrayList.addAll(g());
        }
        return arrayList;
    }

    public static List b() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.PRIV;
        w2.a aVar = w2.a.MAXIMUM;
        v2Var.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        w2.b bVar2 = w2.b.YUV;
        v2Var2.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var2);
        b0.v2 v2Var3 = new b0.v2();
        w2.a aVar2 = w2.a.PREVIEW;
        v2Var3.a(b0.w2.a(bVar, aVar2));
        w2.b bVar3 = w2.b.JPEG;
        v2Var3.a(b0.w2.a(bVar3, aVar));
        arrayList.add(v2Var3);
        b0.v2 v2Var4 = new b0.v2();
        v2Var4.a(b0.w2.a(bVar, aVar2));
        v2Var4.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var4);
        b0.v2 v2Var5 = new b0.v2();
        v2Var5.a(b0.w2.a(bVar2, aVar2));
        v2Var5.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var5);
        b0.v2 v2Var6 = new b0.v2();
        v2Var6.a(b0.w2.a(bVar, aVar2));
        w2.a aVar3 = w2.a.RECORD;
        v2Var6.a(b0.w2.a(bVar, aVar3));
        arrayList.add(v2Var6);
        b0.v2 v2Var7 = new b0.v2();
        v2Var7.a(b0.w2.a(bVar, aVar2));
        v2Var7.a(b0.w2.a(bVar, aVar3));
        v2Var7.a(b0.w2.a(bVar2, aVar3));
        arrayList.add(v2Var7);
        b0.v2 v2Var8 = new b0.v2();
        v2Var8.a(b0.w2.a(bVar, aVar2));
        v2Var8.a(b0.w2.a(bVar, aVar3));
        v2Var8.a(b0.w2.a(bVar3, aVar3));
        arrayList.add(v2Var8);
        return arrayList;
    }

    public static List c() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.PRIV;
        w2.a aVar = w2.a.PREVIEW;
        v2Var.a(b0.w2.a(bVar, aVar));
        w2.a aVar2 = w2.a.MAXIMUM;
        v2Var.a(b0.w2.a(bVar, aVar2));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        v2Var2.a(b0.w2.a(bVar, aVar));
        w2.b bVar2 = w2.b.YUV;
        v2Var2.a(b0.w2.a(bVar2, aVar2));
        arrayList.add(v2Var2);
        b0.v2 v2Var3 = new b0.v2();
        v2Var3.a(b0.w2.a(bVar2, aVar));
        v2Var3.a(b0.w2.a(bVar2, aVar2));
        arrayList.add(v2Var3);
        return arrayList;
    }

    public static List d() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.YUV;
        w2.a aVar = w2.a.s1440p;
        v2Var.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        w2.b bVar2 = w2.b.PRIV;
        v2Var2.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var2);
        b0.v2 v2Var3 = new b0.v2();
        w2.b bVar3 = w2.b.JPEG;
        v2Var3.a(b0.w2.a(bVar3, aVar));
        arrayList.add(v2Var3);
        b0.v2 v2Var4 = new b0.v2();
        w2.a aVar2 = w2.a.s720p;
        v2Var4.a(b0.w2.a(bVar, aVar2));
        v2Var4.a(b0.w2.a(bVar3, aVar));
        arrayList.add(v2Var4);
        b0.v2 v2Var5 = new b0.v2();
        v2Var5.a(b0.w2.a(bVar2, aVar2));
        v2Var5.a(b0.w2.a(bVar3, aVar));
        arrayList.add(v2Var5);
        b0.v2 v2Var6 = new b0.v2();
        v2Var6.a(b0.w2.a(bVar, aVar2));
        v2Var6.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var6);
        b0.v2 v2Var7 = new b0.v2();
        v2Var7.a(b0.w2.a(bVar, aVar2));
        v2Var7.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var7);
        b0.v2 v2Var8 = new b0.v2();
        v2Var8.a(b0.w2.a(bVar2, aVar2));
        v2Var8.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var8);
        b0.v2 v2Var9 = new b0.v2();
        v2Var9.a(b0.w2.a(bVar2, aVar2));
        v2Var9.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var9);
        return arrayList;
    }

    public static List e() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.PRIV;
        w2.a aVar = w2.a.PREVIEW;
        v2Var.a(b0.w2.a(bVar, aVar));
        w2.a aVar2 = w2.a.MAXIMUM;
        v2Var.a(b0.w2.a(bVar, aVar2));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        v2Var2.a(b0.w2.a(bVar, aVar));
        w2.b bVar2 = w2.b.YUV;
        v2Var2.a(b0.w2.a(bVar2, aVar2));
        arrayList.add(v2Var2);
        b0.v2 v2Var3 = new b0.v2();
        v2Var3.a(b0.w2.a(bVar2, aVar));
        v2Var3.a(b0.w2.a(bVar2, aVar2));
        arrayList.add(v2Var3);
        b0.v2 v2Var4 = new b0.v2();
        v2Var4.a(b0.w2.a(bVar, aVar));
        v2Var4.a(b0.w2.a(bVar, aVar));
        v2Var4.a(b0.w2.a(w2.b.JPEG, aVar2));
        arrayList.add(v2Var4);
        b0.v2 v2Var5 = new b0.v2();
        w2.a aVar3 = w2.a.VGA;
        v2Var5.a(b0.w2.a(bVar2, aVar3));
        v2Var5.a(b0.w2.a(bVar, aVar));
        v2Var5.a(b0.w2.a(bVar2, aVar2));
        arrayList.add(v2Var5);
        b0.v2 v2Var6 = new b0.v2();
        v2Var6.a(b0.w2.a(bVar2, aVar3));
        v2Var6.a(b0.w2.a(bVar2, aVar));
        v2Var6.a(b0.w2.a(bVar2, aVar2));
        arrayList.add(v2Var6);
        return arrayList;
    }

    public static List f() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.PRIV;
        w2.a aVar = w2.a.MAXIMUM;
        v2Var.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        w2.b bVar2 = w2.b.JPEG;
        v2Var2.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var2);
        b0.v2 v2Var3 = new b0.v2();
        w2.b bVar3 = w2.b.YUV;
        v2Var3.a(b0.w2.a(bVar3, aVar));
        arrayList.add(v2Var3);
        b0.v2 v2Var4 = new b0.v2();
        w2.a aVar2 = w2.a.PREVIEW;
        v2Var4.a(b0.w2.a(bVar, aVar2));
        v2Var4.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var4);
        b0.v2 v2Var5 = new b0.v2();
        v2Var5.a(b0.w2.a(bVar3, aVar2));
        v2Var5.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var5);
        b0.v2 v2Var6 = new b0.v2();
        v2Var6.a(b0.w2.a(bVar, aVar2));
        v2Var6.a(b0.w2.a(bVar, aVar2));
        arrayList.add(v2Var6);
        b0.v2 v2Var7 = new b0.v2();
        v2Var7.a(b0.w2.a(bVar, aVar2));
        v2Var7.a(b0.w2.a(bVar3, aVar2));
        arrayList.add(v2Var7);
        b0.v2 v2Var8 = new b0.v2();
        v2Var8.a(b0.w2.a(bVar, aVar2));
        v2Var8.a(b0.w2.a(bVar3, aVar2));
        v2Var8.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var8);
        return arrayList;
    }

    public static List g() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.PRIV;
        w2.a aVar = w2.a.PREVIEW;
        v2Var.a(b0.w2.a(bVar, aVar));
        w2.a aVar2 = w2.a.VGA;
        v2Var.a(b0.w2.a(bVar, aVar2));
        w2.b bVar2 = w2.b.YUV;
        w2.a aVar3 = w2.a.MAXIMUM;
        v2Var.a(b0.w2.a(bVar2, aVar3));
        w2.b bVar3 = w2.b.RAW;
        v2Var.a(b0.w2.a(bVar3, aVar3));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        v2Var2.a(b0.w2.a(bVar, aVar));
        v2Var2.a(b0.w2.a(bVar, aVar2));
        v2Var2.a(b0.w2.a(w2.b.JPEG, aVar3));
        v2Var2.a(b0.w2.a(bVar3, aVar3));
        arrayList.add(v2Var2);
        return arrayList;
    }

    public static List h() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.PRIV;
        w2.a aVar = w2.a.PREVIEW;
        v2Var.a(b0.w2.a(bVar, aVar));
        w2.a aVar2 = w2.a.RECORD;
        v2Var.a(b0.w2.a(bVar, aVar2));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        v2Var2.a(b0.w2.a(bVar, aVar));
        w2.b bVar2 = w2.b.YUV;
        v2Var2.a(b0.w2.a(bVar2, aVar2));
        arrayList.add(v2Var2);
        b0.v2 v2Var3 = new b0.v2();
        v2Var3.a(b0.w2.a(bVar2, aVar));
        v2Var3.a(b0.w2.a(bVar2, aVar2));
        arrayList.add(v2Var3);
        b0.v2 v2Var4 = new b0.v2();
        v2Var4.a(b0.w2.a(bVar, aVar));
        v2Var4.a(b0.w2.a(bVar, aVar2));
        w2.b bVar3 = w2.b.JPEG;
        v2Var4.a(b0.w2.a(bVar3, aVar2));
        arrayList.add(v2Var4);
        b0.v2 v2Var5 = new b0.v2();
        v2Var5.a(b0.w2.a(bVar, aVar));
        v2Var5.a(b0.w2.a(bVar2, aVar2));
        v2Var5.a(b0.w2.a(bVar3, aVar2));
        arrayList.add(v2Var5);
        b0.v2 v2Var6 = new b0.v2();
        v2Var6.a(b0.w2.a(bVar2, aVar));
        v2Var6.a(b0.w2.a(bVar2, aVar));
        v2Var6.a(b0.w2.a(bVar3, w2.a.MAXIMUM));
        arrayList.add(v2Var6);
        return arrayList;
    }

    public static List i() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.PRIV;
        w2.a aVar = w2.a.s1440p;
        v2Var.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        w2.b bVar2 = w2.b.YUV;
        v2Var2.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var2);
        b0.v2 v2Var3 = new b0.v2();
        v2Var3.a(b0.w2.a(bVar, aVar));
        w2.b bVar3 = w2.b.JPEG;
        w2.a aVar2 = w2.a.MAXIMUM;
        v2Var3.a(b0.w2.a(bVar3, aVar2));
        arrayList.add(v2Var3);
        b0.v2 v2Var4 = new b0.v2();
        v2Var4.a(b0.w2.a(bVar2, aVar));
        v2Var4.a(b0.w2.a(bVar3, aVar2));
        arrayList.add(v2Var4);
        b0.v2 v2Var5 = new b0.v2();
        v2Var5.a(b0.w2.a(bVar, aVar));
        v2Var5.a(b0.w2.a(bVar2, aVar2));
        arrayList.add(v2Var5);
        b0.v2 v2Var6 = new b0.v2();
        v2Var6.a(b0.w2.a(bVar2, aVar));
        v2Var6.a(b0.w2.a(bVar2, aVar2));
        arrayList.add(v2Var6);
        b0.v2 v2Var7 = new b0.v2();
        w2.a aVar3 = w2.a.PREVIEW;
        v2Var7.a(b0.w2.a(bVar, aVar3));
        v2Var7.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var7);
        b0.v2 v2Var8 = new b0.v2();
        v2Var8.a(b0.w2.a(bVar2, aVar3));
        v2Var8.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var8);
        b0.v2 v2Var9 = new b0.v2();
        v2Var9.a(b0.w2.a(bVar, aVar3));
        v2Var9.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var9);
        b0.v2 v2Var10 = new b0.v2();
        v2Var10.a(b0.w2.a(bVar2, aVar3));
        v2Var10.a(b0.w2.a(bVar2, aVar));
        arrayList.add(v2Var10);
        return arrayList;
    }

    public static List j() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.RAW;
        w2.a aVar = w2.a.MAXIMUM;
        v2Var.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        w2.b bVar2 = w2.b.PRIV;
        w2.a aVar2 = w2.a.PREVIEW;
        v2Var2.a(b0.w2.a(bVar2, aVar2));
        v2Var2.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var2);
        b0.v2 v2Var3 = new b0.v2();
        w2.b bVar3 = w2.b.YUV;
        v2Var3.a(b0.w2.a(bVar3, aVar2));
        v2Var3.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var3);
        b0.v2 v2Var4 = new b0.v2();
        v2Var4.a(b0.w2.a(bVar2, aVar2));
        v2Var4.a(b0.w2.a(bVar2, aVar2));
        v2Var4.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var4);
        b0.v2 v2Var5 = new b0.v2();
        v2Var5.a(b0.w2.a(bVar2, aVar2));
        v2Var5.a(b0.w2.a(bVar3, aVar2));
        v2Var5.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var5);
        b0.v2 v2Var6 = new b0.v2();
        v2Var6.a(b0.w2.a(bVar3, aVar2));
        v2Var6.a(b0.w2.a(bVar3, aVar2));
        v2Var6.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var6);
        b0.v2 v2Var7 = new b0.v2();
        v2Var7.a(b0.w2.a(bVar2, aVar2));
        w2.b bVar4 = w2.b.JPEG;
        v2Var7.a(b0.w2.a(bVar4, aVar));
        v2Var7.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var7);
        b0.v2 v2Var8 = new b0.v2();
        v2Var8.a(b0.w2.a(bVar3, aVar2));
        v2Var8.a(b0.w2.a(bVar4, aVar));
        v2Var8.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var8);
        return arrayList;
    }

    public static List k() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.PRIV;
        w2.a aVar = w2.a.s1440p;
        v2Var.a(b0.w2.b(bVar, aVar, 4L));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        w2.b bVar2 = w2.b.YUV;
        v2Var2.a(b0.w2.b(bVar2, aVar, 4L));
        arrayList.add(v2Var2);
        b0.v2 v2Var3 = new b0.v2();
        w2.a aVar2 = w2.a.RECORD;
        v2Var3.a(b0.w2.b(bVar, aVar2, 3L));
        arrayList.add(v2Var3);
        b0.v2 v2Var4 = new b0.v2();
        v2Var4.a(b0.w2.b(bVar2, aVar2, 3L));
        arrayList.add(v2Var4);
        b0.v2 v2Var5 = new b0.v2();
        w2.b bVar3 = w2.b.JPEG;
        w2.a aVar3 = w2.a.MAXIMUM;
        v2Var5.a(b0.w2.b(bVar3, aVar3, 2L));
        arrayList.add(v2Var5);
        b0.v2 v2Var6 = new b0.v2();
        v2Var6.a(b0.w2.b(bVar2, aVar3, 2L));
        arrayList.add(v2Var6);
        b0.v2 v2Var7 = new b0.v2();
        w2.a aVar4 = w2.a.PREVIEW;
        v2Var7.a(b0.w2.b(bVar, aVar4, 1L));
        v2Var7.a(b0.w2.b(bVar3, aVar3, 2L));
        arrayList.add(v2Var7);
        b0.v2 v2Var8 = new b0.v2();
        v2Var8.a(b0.w2.b(bVar, aVar4, 1L));
        v2Var8.a(b0.w2.b(bVar2, aVar3, 2L));
        arrayList.add(v2Var8);
        b0.v2 v2Var9 = new b0.v2();
        v2Var9.a(b0.w2.b(bVar, aVar4, 1L));
        v2Var9.a(b0.w2.b(bVar, aVar2, 3L));
        arrayList.add(v2Var9);
        b0.v2 v2Var10 = new b0.v2();
        v2Var10.a(b0.w2.b(bVar, aVar4, 1L));
        v2Var10.a(b0.w2.b(bVar2, aVar2, 3L));
        arrayList.add(v2Var10);
        b0.v2 v2Var11 = new b0.v2();
        v2Var11.a(b0.w2.b(bVar, aVar4, 1L));
        v2Var11.a(b0.w2.b(bVar2, aVar4, 1L));
        arrayList.add(v2Var11);
        b0.v2 v2Var12 = new b0.v2();
        v2Var12.a(b0.w2.b(bVar, aVar4, 1L));
        v2Var12.a(b0.w2.b(bVar, aVar2, 3L));
        v2Var12.a(b0.w2.b(bVar3, aVar2, 2L));
        arrayList.add(v2Var12);
        b0.v2 v2Var13 = new b0.v2();
        v2Var13.a(b0.w2.b(bVar, aVar4, 1L));
        v2Var13.a(b0.w2.b(bVar2, aVar2, 3L));
        v2Var13.a(b0.w2.b(bVar3, aVar2, 2L));
        arrayList.add(v2Var13);
        b0.v2 v2Var14 = new b0.v2();
        v2Var14.a(b0.w2.b(bVar, aVar4, 1L));
        v2Var14.a(b0.w2.b(bVar2, aVar4, 1L));
        v2Var14.a(b0.w2.b(bVar3, aVar3, 2L));
        arrayList.add(v2Var14);
        return arrayList;
    }

    public static List l() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.JPEG_R;
        w2.a aVar = w2.a.MAXIMUM;
        v2Var.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        v2Var2.a(b0.w2.a(w2.b.PRIV, w2.a.PREVIEW));
        v2Var2.a(b0.w2.a(bVar, aVar));
        arrayList.add(v2Var2);
        return arrayList;
    }

    public static List m() {
        ArrayList arrayList = new ArrayList();
        b0.v2 v2Var = new b0.v2();
        w2.b bVar = w2.b.YUV;
        w2.a aVar = w2.a.ULTRA_MAXIMUM;
        v2Var.a(b0.w2.a(bVar, aVar));
        w2.b bVar2 = w2.b.PRIV;
        w2.a aVar2 = w2.a.PREVIEW;
        v2Var.a(b0.w2.a(bVar2, aVar2));
        w2.a aVar3 = w2.a.RECORD;
        v2Var.a(b0.w2.a(bVar2, aVar3));
        arrayList.add(v2Var);
        b0.v2 v2Var2 = new b0.v2();
        w2.b bVar3 = w2.b.JPEG;
        v2Var2.a(b0.w2.a(bVar3, aVar));
        v2Var2.a(b0.w2.a(bVar2, aVar2));
        v2Var2.a(b0.w2.a(bVar2, aVar3));
        arrayList.add(v2Var2);
        b0.v2 v2Var3 = new b0.v2();
        w2.b bVar4 = w2.b.RAW;
        v2Var3.a(b0.w2.a(bVar4, aVar));
        v2Var3.a(b0.w2.a(bVar2, aVar2));
        v2Var3.a(b0.w2.a(bVar2, aVar3));
        arrayList.add(v2Var3);
        b0.v2 v2Var4 = new b0.v2();
        v2Var4.a(b0.w2.a(bVar, aVar));
        v2Var4.a(b0.w2.a(bVar2, aVar2));
        w2.a aVar4 = w2.a.MAXIMUM;
        v2Var4.a(b0.w2.a(bVar3, aVar4));
        arrayList.add(v2Var4);
        b0.v2 v2Var5 = new b0.v2();
        v2Var5.a(b0.w2.a(bVar3, aVar));
        v2Var5.a(b0.w2.a(bVar2, aVar2));
        v2Var5.a(b0.w2.a(bVar3, aVar4));
        arrayList.add(v2Var5);
        b0.v2 v2Var6 = new b0.v2();
        v2Var6.a(b0.w2.a(bVar4, aVar));
        v2Var6.a(b0.w2.a(bVar2, aVar2));
        v2Var6.a(b0.w2.a(bVar3, aVar4));
        arrayList.add(v2Var6);
        b0.v2 v2Var7 = new b0.v2();
        v2Var7.a(b0.w2.a(bVar, aVar));
        v2Var7.a(b0.w2.a(bVar2, aVar2));
        v2Var7.a(b0.w2.a(bVar, aVar4));
        arrayList.add(v2Var7);
        b0.v2 v2Var8 = new b0.v2();
        v2Var8.a(b0.w2.a(bVar3, aVar));
        v2Var8.a(b0.w2.a(bVar2, aVar2));
        v2Var8.a(b0.w2.a(bVar, aVar4));
        arrayList.add(v2Var8);
        b0.v2 v2Var9 = new b0.v2();
        v2Var9.a(b0.w2.a(bVar4, aVar));
        v2Var9.a(b0.w2.a(bVar2, aVar2));
        v2Var9.a(b0.w2.a(bVar, aVar4));
        arrayList.add(v2Var9);
        b0.v2 v2Var10 = new b0.v2();
        v2Var10.a(b0.w2.a(bVar, aVar));
        v2Var10.a(b0.w2.a(bVar2, aVar2));
        v2Var10.a(b0.w2.a(bVar4, aVar4));
        arrayList.add(v2Var10);
        b0.v2 v2Var11 = new b0.v2();
        v2Var11.a(b0.w2.a(bVar3, aVar));
        v2Var11.a(b0.w2.a(bVar2, aVar2));
        v2Var11.a(b0.w2.a(bVar4, aVar4));
        arrayList.add(v2Var11);
        b0.v2 v2Var12 = new b0.v2();
        v2Var12.a(b0.w2.a(bVar4, aVar));
        v2Var12.a(b0.w2.a(bVar2, aVar2));
        v2Var12.a(b0.w2.a(bVar4, aVar4));
        arrayList.add(v2Var12);
        return arrayList;
    }
}
