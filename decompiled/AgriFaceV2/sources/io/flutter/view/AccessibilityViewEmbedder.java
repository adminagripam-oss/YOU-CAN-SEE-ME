package io.flutter.view;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.AccessibilityRecord;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
class AccessibilityViewEmbedder {
    private static final String TAG = "AccessibilityBridge";
    private int nextFlutterId;
    private final View rootAccessibilityView;
    private final b reflectionAccessors = new b();
    private final SparseArray<c> flutterIdToOrigin = new SparseArray<>();
    private final Map<c, Integer> originToFlutterId = new HashMap();
    private final Map<View, Rect> embeddedViewToDisplayBounds = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final Method f8031a;

        /* renamed from: b  reason: collision with root package name */
        private final Method f8032b;

        /* renamed from: c  reason: collision with root package name */
        private final Method f8033c;

        /* renamed from: d  reason: collision with root package name */
        private final Method f8034d;

        /* renamed from: e  reason: collision with root package name */
        private final Field f8035e;

        /* renamed from: f  reason: collision with root package name */
        private final Method f8036f;

        /* JADX WARN: Multi-variable type inference failed */
        private b() {
            Method method;
            Method method2;
            Method method3;
            Method method4;
            Field field;
            Method method5;
            Method method6 = null;
            try {
                method = AccessibilityNodeInfo.class.getMethod("getSourceNodeId", new Class[0]);
            } catch (NoSuchMethodException unused) {
                z5.b.g(AccessibilityViewEmbedder.TAG, "can't invoke AccessibilityNodeInfo#getSourceNodeId with reflection");
                method = null;
            }
            try {
                method2 = AccessibilityRecord.class.getMethod("getSourceNodeId", new Class[0]);
            } catch (NoSuchMethodException unused2) {
                z5.b.g(AccessibilityViewEmbedder.TAG, "can't invoke AccessibiiltyRecord#getSourceNodeId with reflection");
                method2 = null;
            }
            if (Build.VERSION.SDK_INT <= 26) {
                try {
                    method3 = AccessibilityNodeInfo.class.getMethod("getParentNodeId", new Class[0]);
                } catch (NoSuchMethodException unused3) {
                    z5.b.g(AccessibilityViewEmbedder.TAG, "can't invoke getParentNodeId with reflection");
                    method3 = null;
                }
                try {
                    method4 = AccessibilityNodeInfo.class.getMethod("getChildId", Integer.TYPE);
                    field = null;
                } catch (NoSuchMethodException unused4) {
                    z5.b.g(AccessibilityViewEmbedder.TAG, "can't invoke getChildId with reflection");
                    method4 = null;
                    field = null;
                }
                method5 = field;
                method6 = method3;
            } else {
                try {
                    Field declaredField = AccessibilityNodeInfo.class.getDeclaredField("mChildNodeIds");
                    declaredField.setAccessible(true);
                    method5 = Class.forName("android.util.LongArray").getMethod("get", Integer.TYPE);
                    field = declaredField;
                    method4 = null;
                } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException | NullPointerException unused5) {
                    z5.b.g(AccessibilityViewEmbedder.TAG, "can't access childNodeIdsField with reflection");
                    method4 = null;
                    field = null;
                    method5 = 0;
                }
            }
            this.f8031a = method;
            this.f8032b = method6;
            this.f8033c = method2;
            this.f8034d = method4;
            this.f8035e = field;
            this.f8036f = method5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Long f(AccessibilityNodeInfo accessibilityNodeInfo, int i8) {
            String str;
            Method method = this.f8034d;
            if (method == null && (this.f8035e == null || this.f8036f == null)) {
                return null;
            }
            if (method != null) {
                try {
                    return (Long) method.invoke(accessibilityNodeInfo, Integer.valueOf(i8));
                } catch (IllegalAccessException e8) {
                    e = e8;
                    str = "Failed to access getChildId method.";
                } catch (InvocationTargetException e9) {
                    e = e9;
                    str = "The getChildId method threw an exception when invoked.";
                }
            } else {
                try {
                    return Long.valueOf(((Long) this.f8036f.invoke(this.f8035e.get(accessibilityNodeInfo), Integer.valueOf(i8))).longValue());
                } catch (ArrayIndexOutOfBoundsException e10) {
                    e = e10;
                    str = "The longArrayGetIndex method threw an exception when invoked.";
                    z5.b.h(AccessibilityViewEmbedder.TAG, str, e);
                    return null;
                } catch (IllegalAccessException e11) {
                    e = e11;
                    str = "Failed to access longArrayGetIndex method or the childNodeId field.";
                } catch (InvocationTargetException e12) {
                    e = e12;
                    str = "The longArrayGetIndex method threw an exception when invoked.";
                    z5.b.h(AccessibilityViewEmbedder.TAG, str, e);
                    return null;
                }
            }
            z5.b.h(AccessibilityViewEmbedder.TAG, str, e);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Long g(AccessibilityNodeInfo accessibilityNodeInfo) {
            String str;
            Method method = this.f8032b;
            if (method != null) {
                try {
                    return Long.valueOf(((Long) method.invoke(accessibilityNodeInfo, new Object[0])).longValue());
                } catch (IllegalAccessException e8) {
                    e = e8;
                    str = "Failed to access getParentNodeId method.";
                    z5.b.h(AccessibilityViewEmbedder.TAG, str, e);
                    return l(accessibilityNodeInfo);
                } catch (InvocationTargetException e9) {
                    e = e9;
                    str = "The getParentNodeId method threw an exception when invoked.";
                    z5.b.h(AccessibilityViewEmbedder.TAG, str, e);
                    return l(accessibilityNodeInfo);
                }
            }
            return l(accessibilityNodeInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Long h(AccessibilityRecord accessibilityRecord) {
            String str;
            Method method = this.f8033c;
            if (method == null) {
                return null;
            }
            try {
                return (Long) method.invoke(accessibilityRecord, new Object[0]);
            } catch (IllegalAccessException e8) {
                e = e8;
                str = "Failed to access the getRecordSourceNodeId method.";
                z5.b.h(AccessibilityViewEmbedder.TAG, str, e);
                return null;
            } catch (InvocationTargetException e9) {
                e = e9;
                str = "The getRecordSourceNodeId method threw an exception when invoked.";
                z5.b.h(AccessibilityViewEmbedder.TAG, str, e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Long i(AccessibilityNodeInfo accessibilityNodeInfo) {
            String str;
            Method method = this.f8031a;
            if (method == null) {
                return null;
            }
            try {
                return (Long) method.invoke(accessibilityNodeInfo, new Object[0]);
            } catch (IllegalAccessException e8) {
                e = e8;
                str = "Failed to access getSourceNodeId method.";
                z5.b.h(AccessibilityViewEmbedder.TAG, str, e);
                return null;
            } catch (InvocationTargetException e9) {
                e = e9;
                str = "The getSourceNodeId method threw an exception when invoked.";
                z5.b.h(AccessibilityViewEmbedder.TAG, str, e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int j(long j8) {
            return (int) (j8 >> 32);
        }

        private static boolean k(long j8, int i8) {
            return (j8 & (1 << i8)) != 0;
        }

        private static Long l(AccessibilityNodeInfo accessibilityNodeInfo) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain(accessibilityNodeInfo);
            Parcel obtain2 = Parcel.obtain();
            obtain2.setDataPosition(0);
            obtain.writeToParcel(obtain2, 0);
            obtain2.setDataPosition(0);
            long readLong = obtain2.readLong();
            if (k(readLong, 0)) {
                obtain2.readInt();
            }
            if (k(readLong, 1)) {
                obtain2.readLong();
            }
            if (k(readLong, 2)) {
                obtain2.readInt();
            }
            Long valueOf = k(readLong, 3) ? Long.valueOf(obtain2.readLong()) : null;
            obtain2.recycle();
            return valueOf;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        final View f8037a;

        /* renamed from: b  reason: collision with root package name */
        final int f8038b;

        private c(View view, int i8) {
            this.f8037a = view;
            this.f8038b = i8;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof c) {
                c cVar = (c) obj;
                return this.f8038b == cVar.f8038b && this.f8037a.equals(cVar.f8037a);
            }
            return false;
        }

        public int hashCode() {
            return ((this.f8037a.hashCode() + 31) * 31) + this.f8038b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessibilityViewEmbedder(View view, int i8) {
        this.rootAccessibilityView = view;
        this.nextFlutterId = i8;
    }

    private void addChildrenToFlutterNode(AccessibilityNodeInfo accessibilityNodeInfo, View view, AccessibilityNodeInfo accessibilityNodeInfo2) {
        int i8;
        for (int i9 = 0; i9 < accessibilityNodeInfo.getChildCount(); i9++) {
            Long f8 = this.reflectionAccessors.f(accessibilityNodeInfo, i9);
            if (f8 != null) {
                int j8 = b.j(f8.longValue());
                c cVar = new c(view, j8);
                if (this.originToFlutterId.containsKey(cVar)) {
                    i8 = this.originToFlutterId.get(cVar).intValue();
                } else {
                    int i10 = this.nextFlutterId;
                    this.nextFlutterId = i10 + 1;
                    cacheVirtualIdMappings(view, j8, i10);
                    i8 = i10;
                }
                accessibilityNodeInfo2.addChild(this.rootAccessibilityView, i8);
            }
        }
    }

    private void cacheVirtualIdMappings(View view, int i8, int i9) {
        c cVar = new c(view, i8);
        this.originToFlutterId.put(cVar, Integer.valueOf(i9));
        this.flutterIdToOrigin.put(i9, cVar);
    }

    private AccessibilityNodeInfo convertToFlutterNode(AccessibilityNodeInfo accessibilityNodeInfo, int i8, View view) {
        AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain(this.rootAccessibilityView, i8);
        obtain.setPackageName(this.rootAccessibilityView.getContext().getPackageName());
        obtain.setSource(this.rootAccessibilityView, i8);
        obtain.setClassName(accessibilityNodeInfo.getClassName());
        copyAccessibilityFields(accessibilityNodeInfo, obtain);
        setFlutterNodesTranslateBounds(accessibilityNodeInfo, this.embeddedViewToDisplayBounds.get(view), obtain);
        addChildrenToFlutterNode(accessibilityNodeInfo, view, obtain);
        setFlutterNodeParent(accessibilityNodeInfo, view, obtain);
        return obtain;
    }

    private void copyAccessibilityFields(AccessibilityNodeInfo accessibilityNodeInfo, AccessibilityNodeInfo accessibilityNodeInfo2) {
        accessibilityNodeInfo2.setAccessibilityFocused(accessibilityNodeInfo.isAccessibilityFocused());
        accessibilityNodeInfo2.setCheckable(accessibilityNodeInfo.isCheckable());
        accessibilityNodeInfo2.setChecked(accessibilityNodeInfo.isChecked());
        accessibilityNodeInfo2.setContentDescription(accessibilityNodeInfo.getContentDescription());
        accessibilityNodeInfo2.setEnabled(accessibilityNodeInfo.isEnabled());
        accessibilityNodeInfo2.setClickable(accessibilityNodeInfo.isClickable());
        accessibilityNodeInfo2.setFocusable(accessibilityNodeInfo.isFocusable());
        accessibilityNodeInfo2.setFocused(accessibilityNodeInfo.isFocused());
        accessibilityNodeInfo2.setLongClickable(accessibilityNodeInfo.isLongClickable());
        accessibilityNodeInfo2.setMovementGranularities(accessibilityNodeInfo.getMovementGranularities());
        accessibilityNodeInfo2.setPassword(accessibilityNodeInfo.isPassword());
        accessibilityNodeInfo2.setScrollable(accessibilityNodeInfo.isScrollable());
        accessibilityNodeInfo2.setSelected(accessibilityNodeInfo.isSelected());
        accessibilityNodeInfo2.setText(accessibilityNodeInfo.getText());
        accessibilityNodeInfo2.setVisibleToUser(accessibilityNodeInfo.isVisibleToUser());
        accessibilityNodeInfo2.setEditable(accessibilityNodeInfo.isEditable());
        accessibilityNodeInfo2.setCanOpenPopup(accessibilityNodeInfo.canOpenPopup());
        accessibilityNodeInfo2.setCollectionInfo(accessibilityNodeInfo.getCollectionInfo());
        accessibilityNodeInfo2.setCollectionItemInfo(accessibilityNodeInfo.getCollectionItemInfo());
        accessibilityNodeInfo2.setContentInvalid(accessibilityNodeInfo.isContentInvalid());
        accessibilityNodeInfo2.setDismissable(accessibilityNodeInfo.isDismissable());
        accessibilityNodeInfo2.setInputType(accessibilityNodeInfo.getInputType());
        accessibilityNodeInfo2.setLiveRegion(accessibilityNodeInfo.getLiveRegion());
        accessibilityNodeInfo2.setMultiLine(accessibilityNodeInfo.isMultiLine());
        accessibilityNodeInfo2.setRangeInfo(accessibilityNodeInfo.getRangeInfo());
        accessibilityNodeInfo2.setError(accessibilityNodeInfo.getError());
        accessibilityNodeInfo2.setMaxTextLength(accessibilityNodeInfo.getMaxTextLength());
        accessibilityNodeInfo2.setContextClickable(accessibilityNodeInfo.isContextClickable());
        accessibilityNodeInfo2.setDrawingOrder(accessibilityNodeInfo.getDrawingOrder());
        accessibilityNodeInfo2.setImportantForAccessibility(accessibilityNodeInfo.isImportantForAccessibility());
        accessibilityNodeInfo2.setAvailableExtraData(accessibilityNodeInfo.getAvailableExtraData());
        accessibilityNodeInfo2.setHintText(accessibilityNodeInfo.getHintText());
        accessibilityNodeInfo2.setShowingHintText(accessibilityNodeInfo.isShowingHintText());
    }

    private void setFlutterNodeParent(AccessibilityNodeInfo accessibilityNodeInfo, View view, AccessibilityNodeInfo accessibilityNodeInfo2) {
        Long g8 = this.reflectionAccessors.g(accessibilityNodeInfo);
        if (g8 == null) {
            return;
        }
        Integer num = this.originToFlutterId.get(new c(view, b.j(g8.longValue())));
        if (num != null) {
            accessibilityNodeInfo2.setParent(this.rootAccessibilityView, num.intValue());
        }
    }

    private void setFlutterNodesTranslateBounds(AccessibilityNodeInfo accessibilityNodeInfo, Rect rect, AccessibilityNodeInfo accessibilityNodeInfo2) {
        Rect rect2 = new Rect();
        accessibilityNodeInfo.getBoundsInParent(rect2);
        accessibilityNodeInfo2.setBoundsInParent(rect2);
        Rect rect3 = new Rect();
        accessibilityNodeInfo.getBoundsInScreen(rect3);
        rect3.offset(rect.left, rect.top);
        accessibilityNodeInfo2.setBoundsInScreen(rect3);
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i8) {
        AccessibilityNodeInfo createAccessibilityNodeInfo;
        c cVar = this.flutterIdToOrigin.get(i8);
        if (cVar == null || !this.embeddedViewToDisplayBounds.containsKey(cVar.f8037a) || cVar.f8037a.getAccessibilityNodeProvider() == null || (createAccessibilityNodeInfo = cVar.f8037a.getAccessibilityNodeProvider().createAccessibilityNodeInfo(cVar.f8038b)) == null) {
            return null;
        }
        return convertToFlutterNode(createAccessibilityNodeInfo, i8, cVar.f8037a);
    }

    public Integer getRecordFlutterId(View view, AccessibilityRecord accessibilityRecord) {
        Long h8 = this.reflectionAccessors.h(accessibilityRecord);
        if (h8 == null) {
            return null;
        }
        return this.originToFlutterId.get(new c(view, b.j(h8.longValue())));
    }

    public AccessibilityNodeInfo getRootNode(View view, int i8, Rect rect) {
        AccessibilityNodeInfo createAccessibilityNodeInfo = view.createAccessibilityNodeInfo();
        Long i9 = this.reflectionAccessors.i(createAccessibilityNodeInfo);
        if (i9 == null) {
            return null;
        }
        this.embeddedViewToDisplayBounds.put(view, rect);
        cacheVirtualIdMappings(view, b.j(i9.longValue()), i8);
        return convertToFlutterNode(createAccessibilityNodeInfo, i8, view);
    }

    public boolean onAccessibilityHoverEvent(int i8, MotionEvent motionEvent) {
        c cVar = this.flutterIdToOrigin.get(i8);
        if (cVar == null) {
            return false;
        }
        Rect rect = this.embeddedViewToDisplayBounds.get(cVar.f8037a);
        int pointerCount = motionEvent.getPointerCount();
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i9 = 0; i9 < motionEvent.getPointerCount(); i9++) {
            MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
            pointerPropertiesArr[i9] = pointerProperties;
            motionEvent.getPointerProperties(i9, pointerProperties);
            MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
            motionEvent.getPointerCoords(i9, pointerCoords);
            MotionEvent.PointerCoords pointerCoords2 = new MotionEvent.PointerCoords(pointerCoords);
            pointerCoordsArr[i9] = pointerCoords2;
            pointerCoords2.x -= rect.left;
            pointerCoords2.y -= rect.top;
        }
        return cVar.f8037a.dispatchGenericMotionEvent(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getPointerCount(), pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags()));
    }

    public boolean performAction(int i8, int i9, Bundle bundle) {
        AccessibilityNodeProvider accessibilityNodeProvider;
        c cVar = this.flutterIdToOrigin.get(i8);
        if (cVar == null || (accessibilityNodeProvider = cVar.f8037a.getAccessibilityNodeProvider()) == null) {
            return false;
        }
        return accessibilityNodeProvider.performAction(cVar.f8038b, i9, bundle);
    }

    public View platformViewOfNode(int i8) {
        c cVar = this.flutterIdToOrigin.get(i8);
        if (cVar == null) {
            return null;
        }
        return cVar.f8037a;
    }

    public boolean requestSendAccessibilityEvent(View view, View view2, AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(accessibilityEvent);
        Long h8 = this.reflectionAccessors.h(accessibilityEvent);
        if (h8 == null) {
            return false;
        }
        int j8 = b.j(h8.longValue());
        Integer num = this.originToFlutterId.get(new c(view, j8));
        if (num == null) {
            int i8 = this.nextFlutterId;
            this.nextFlutterId = i8 + 1;
            num = Integer.valueOf(i8);
            cacheVirtualIdMappings(view, j8, num.intValue());
        }
        obtain.setSource(this.rootAccessibilityView, num.intValue());
        obtain.setClassName(accessibilityEvent.getClassName());
        obtain.setPackageName(accessibilityEvent.getPackageName());
        for (int i9 = 0; i9 < obtain.getRecordCount(); i9++) {
            AccessibilityRecord record = obtain.getRecord(i9);
            Long h9 = this.reflectionAccessors.h(record);
            if (h9 == null) {
                return false;
            }
            c cVar = new c(view, b.j(h9.longValue()));
            if (!this.originToFlutterId.containsKey(cVar)) {
                return false;
            }
            record.setSource(this.rootAccessibilityView, this.originToFlutterId.get(cVar).intValue());
        }
        return this.rootAccessibilityView.getParent().requestSendAccessibilityEvent(view2, obtain);
    }
}
