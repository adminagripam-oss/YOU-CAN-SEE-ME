package androidx.lifecycle;
/* loaded from: classes.dex */
public interface DefaultLifecycleObserver extends l {
    default void d(m mVar) {
        d7.k.e(mVar, "owner");
    }

    default void onDestroy(m mVar) {
        d7.k.e(mVar, "owner");
    }

    default void onPause(m mVar) {
        d7.k.e(mVar, "owner");
    }

    default void onResume(m mVar) {
        d7.k.e(mVar, "owner");
    }

    default void onStart(m mVar) {
        d7.k.e(mVar, "owner");
    }

    default void onStop(m mVar) {
        d7.k.e(mVar, "owner");
    }
}
