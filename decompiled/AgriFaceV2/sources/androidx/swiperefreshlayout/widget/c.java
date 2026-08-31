package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ListView;
import androidx.core.view.d0;
import androidx.core.view.e0;
import androidx.core.view.f0;
import androidx.core.view.g0;
import androidx.core.view.p0;
/* loaded from: classes.dex */
public abstract class c extends ViewGroup implements f0, e0 {
    private static final int ALPHA_ANIMATION_DURATION = 300;
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    static final int CIRCLE_DIAMETER = 40;
    static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    public static final int DEFAULT = 1;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    public static final int DEFAULT_SLINGSHOT_DISTANCE = -1;
    private static final float DRAG_RATE = 0.5f;
    private static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    private static final int[] LAYOUT_ATTRS = {16842766};
    private static final String LOG_TAG = "c";
    private static final int MAX_ALPHA = 255;
    private static final float MAX_PROGRESS_ANGLE = 0.8f;
    private static final int SCALE_DOWN_DURATION = 150;
    private static final int STARTING_PROGRESS_ALPHA = 76;
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private i mChildScrollUpCallback;
    private int mCircleDiameter;
    androidx.swiperefreshlayout.widget.a mCircleView;
    private int mCircleViewIndex;
    int mCurrentTargetOffsetTop;
    int mCustomSlingshotDistance;
    private final DecelerateInterpolator mDecelerateInterpolator;
    private boolean mEnableLegacyRequestDisallowInterceptTouch;
    protected int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    j mListener;
    private int mMediumAnimationDuration;
    private boolean mNestedScrollInProgress;
    private final d0 mNestedScrollingChildHelper;
    private final g0 mNestedScrollingParentHelper;
    private final int[] mNestedScrollingV2ConsumedCompat;
    boolean mNotify;
    protected int mOriginalOffsetTop;
    private final int[] mParentOffsetInWindow;
    private final int[] mParentScrollConsumed;
    androidx.swiperefreshlayout.widget.b mProgress;
    private Animation.AnimationListener mRefreshListener;
    boolean mRefreshing;
    private boolean mReturningToStart;
    boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    int mSpinnerOffsetEnd;
    float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance;
    private float mTotalUnconsumed;
    private int mTouchSlop;
    boolean mUsingCustomStart;

    /* loaded from: classes.dex */
    class a implements Animation.AnimationListener {
        a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            j jVar;
            c cVar = c.this;
            if (!cVar.mRefreshing) {
                cVar.reset();
                return;
            }
            cVar.mProgress.setAlpha(c.MAX_ALPHA);
            c.this.mProgress.start();
            c cVar2 = c.this;
            if (cVar2.mNotify && (jVar = cVar2.mListener) != null) {
                jVar.onRefresh();
            }
            c cVar3 = c.this;
            cVar3.mCurrentTargetOffsetTop = cVar3.mCircleView.getTop();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends Animation {
        b() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            c.this.setAnimationProgress(f8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.swiperefreshlayout.widget.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0051c extends Animation {
        C0051c() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            c.this.setAnimationProgress(1.0f - f8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends Animation {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f3976e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ int f3977f;

        d(int i8, int i9) {
            this.f3976e = i8;
            this.f3977f = i9;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            androidx.swiperefreshlayout.widget.b bVar = c.this.mProgress;
            int i8 = this.f3976e;
            bVar.setAlpha((int) (i8 + ((this.f3977f - i8) * f8)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements Animation.AnimationListener {
        e() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            c cVar = c.this;
            if (cVar.mScale) {
                return;
            }
            cVar.startScaleDownAnimation(null);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* loaded from: classes.dex */
    class f extends Animation {
        f() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            c cVar = c.this;
            int abs = !cVar.mUsingCustomStart ? cVar.mSpinnerOffsetEnd - Math.abs(cVar.mOriginalOffsetTop) : cVar.mSpinnerOffsetEnd;
            c cVar2 = c.this;
            int i8 = cVar2.mFrom;
            c.this.setTargetOffsetTopAndBottom((i8 + ((int) ((abs - i8) * f8))) - cVar2.mCircleView.getTop());
            c.this.mProgress.e(1.0f - f8);
        }
    }

    /* loaded from: classes.dex */
    class g extends Animation {
        g() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            c.this.moveToStart(f8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h extends Animation {
        h() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f8, Transformation transformation) {
            c cVar = c.this;
            float f9 = cVar.mStartingScale;
            cVar.setAnimationProgress(f9 + ((-f9) * f8));
            c.this.moveToStart(f8);
        }
    }

    /* loaded from: classes.dex */
    public interface i {
        boolean canChildScrollUp(c cVar, View view);
    }

    /* loaded from: classes.dex */
    public interface j {
        void onRefresh();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class k extends View.BaseSavedState {
        public static final Parcelable.Creator<k> CREATOR = new a();

        /* renamed from: a  reason: collision with root package name */
        final boolean f3983a;

        /* loaded from: classes.dex */
        class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public k createFromParcel(Parcel parcel) {
                return new k(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public k[] newArray(int i8) {
                return new k[i8];
            }
        }

        k(Parcel parcel) {
            super(parcel);
            this.f3983a = parcel.readByte() != 0;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeByte(this.f3983a ? (byte) 1 : (byte) 0);
        }

        k(Parcelable parcelable, boolean z7) {
            super(parcelable);
            this.f3983a = z7;
        }
    }

    public c(Context context) {
        this(context, null);
    }

    private void a(int i8, Animation.AnimationListener animationListener) {
        this.mFrom = i8;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200L);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.b(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void b(int i8, Animation.AnimationListener animationListener) {
        if (this.mScale) {
            n(i8, animationListener);
            return;
        }
        this.mFrom = i8;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(200L);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.b(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToStartPosition);
    }

    private void c() {
        this.mCircleView = new androidx.swiperefreshlayout.widget.a(getContext());
        androidx.swiperefreshlayout.widget.b bVar = new androidx.swiperefreshlayout.widget.b(getContext());
        this.mProgress = bVar;
        bVar.l(1);
        this.mCircleView.setImageDrawable(this.mProgress);
        this.mCircleView.setVisibility(8);
        addView(this.mCircleView);
    }

    private void d() {
        if (this.mTarget == null) {
            for (int i8 = 0; i8 < getChildCount(); i8++) {
                View childAt = getChildAt(i8);
                if (!childAt.equals(this.mCircleView)) {
                    this.mTarget = childAt;
                    return;
                }
            }
        }
    }

    private void e(float f8) {
        if (f8 > this.mTotalDragDistance) {
            i(true, true);
            return;
        }
        this.mRefreshing = false;
        this.mProgress.j(0.0f, 0.0f);
        b(this.mCurrentTargetOffsetTop, !this.mScale ? new e() : null);
        this.mProgress.d(false);
    }

    private boolean f(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    private void g(float f8) {
        this.mProgress.d(true);
        float min = Math.min(1.0f, Math.abs(f8 / this.mTotalDragDistance));
        float max = (((float) Math.max(min - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float abs = Math.abs(f8) - this.mTotalDragDistance;
        int i8 = this.mCustomSlingshotDistance;
        if (i8 <= 0) {
            i8 = this.mUsingCustomStart ? this.mSpinnerOffsetEnd - this.mOriginalOffsetTop : this.mSpinnerOffsetEnd;
        }
        float f9 = i8;
        double max2 = Math.max(0.0f, Math.min(abs, f9 * DECELERATE_INTERPOLATION_FACTOR) / f9) / 4.0f;
        float pow = ((float) (max2 - Math.pow(max2, 2.0d))) * DECELERATE_INTERPOLATION_FACTOR;
        int i9 = this.mOriginalOffsetTop + ((int) ((f9 * min) + (f9 * pow * DECELERATE_INTERPOLATION_FACTOR)));
        if (this.mCircleView.getVisibility() != 0) {
            this.mCircleView.setVisibility(0);
        }
        if (!this.mScale) {
            this.mCircleView.setScaleX(1.0f);
            this.mCircleView.setScaleY(1.0f);
        }
        if (this.mScale) {
            setAnimationProgress(Math.min(1.0f, f8 / this.mTotalDragDistance));
        }
        if (f8 < this.mTotalDragDistance) {
            if (this.mProgress.getAlpha() > STARTING_PROGRESS_ALPHA && !f(this.mAlphaStartAnimation)) {
                m();
            }
        } else if (this.mProgress.getAlpha() < MAX_ALPHA && !f(this.mAlphaMaxAnimation)) {
            l();
        }
        this.mProgress.j(0.0f, Math.min((float) MAX_PROGRESS_ANGLE, max * MAX_PROGRESS_ANGLE));
        this.mProgress.e(Math.min(1.0f, max));
        this.mProgress.g((((max * 0.4f) - 0.25f) + (pow * DECELERATE_INTERPOLATION_FACTOR)) * DRAG_RATE);
        setTargetOffsetTopAndBottom(i9 - this.mCurrentTargetOffsetTop);
    }

    private void h(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    private void i(boolean z7, boolean z8) {
        if (this.mRefreshing != z7) {
            this.mNotify = z8;
            d();
            this.mRefreshing = z7;
            if (z7) {
                a(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            } else {
                startScaleDownAnimation(this.mRefreshListener);
            }
        }
    }

    private Animation j(int i8, int i9) {
        d dVar = new d(i8, i9);
        dVar.setDuration(300L);
        this.mCircleView.b(null);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(dVar);
        return dVar;
    }

    private void k(float f8) {
        float f9 = this.mInitialDownY;
        int i8 = this.mTouchSlop;
        if (f8 - f9 <= i8 || this.mIsBeingDragged) {
            return;
        }
        this.mInitialMotionY = f9 + i8;
        this.mIsBeingDragged = true;
        this.mProgress.setAlpha(STARTING_PROGRESS_ALPHA);
    }

    private void l() {
        this.mAlphaMaxAnimation = j(this.mProgress.getAlpha(), MAX_ALPHA);
    }

    private void m() {
        this.mAlphaStartAnimation = j(this.mProgress.getAlpha(), STARTING_PROGRESS_ALPHA);
    }

    private void n(int i8, Animation.AnimationListener animationListener) {
        this.mFrom = i8;
        this.mStartingScale = this.mCircleView.getScaleX();
        h hVar = new h();
        this.mScaleDownToStartAnimation = hVar;
        hVar.setDuration(150L);
        if (animationListener != null) {
            this.mCircleView.b(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
    }

    private void o(Animation.AnimationListener animationListener) {
        this.mCircleView.setVisibility(0);
        this.mProgress.setAlpha(MAX_ALPHA);
        b bVar = new b();
        this.mScaleAnimation = bVar;
        bVar.setDuration(this.mMediumAnimationDuration);
        if (animationListener != null) {
            this.mCircleView.b(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleAnimation);
    }

    private void setColorViewAlpha(int i8) {
        this.mCircleView.getBackground().setAlpha(i8);
        this.mProgress.setAlpha(i8);
    }

    public boolean canChildScrollUp() {
        i iVar = this.mChildScrollUpCallback;
        if (iVar != null) {
            return iVar.canChildScrollUp(this, this.mTarget);
        }
        View view = this.mTarget;
        return view instanceof ListView ? androidx.core.widget.g.a((ListView) view, -1) : view.canScrollVertically(-1);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f8, float f9, boolean z7) {
        return this.mNestedScrollingChildHelper.a(f8, f9, z7);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f8, float f9) {
        return this.mNestedScrollingChildHelper.b(f8, f9);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i8, int i9, int[] iArr, int[] iArr2) {
        return this.mNestedScrollingChildHelper.c(i8, i9, iArr, iArr2);
    }

    public void dispatchNestedScroll(int i8, int i9, int i10, int i11, int[] iArr, int i12, int[] iArr2) {
        if (i12 == 0) {
            this.mNestedScrollingChildHelper.e(i8, i9, i10, i11, iArr, i12, iArr2);
        }
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i8, int i9) {
        int i10 = this.mCircleViewIndex;
        return i10 < 0 ? i9 : i9 == i8 + (-1) ? i10 : i9 >= i10 ? i9 + 1 : i9;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.a();
    }

    public int getProgressCircleDiameter() {
        return this.mCircleDiameter;
    }

    public int getProgressViewEndOffset() {
        return this.mSpinnerOffsetEnd;
    }

    public int getProgressViewStartOffset() {
        return this.mOriginalOffsetTop;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.k();
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.m();
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    void moveToStart(float f8) {
        int i8 = this.mFrom;
        setTargetOffsetTopAndBottom((i8 + ((int) ((this.mOriginalOffsetTop - i8) * f8))) - this.mCircleView.getTop());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        reset();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        d();
        int actionMasked = motionEvent.getActionMasked();
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp() || this.mRefreshing || this.mNestedScrollInProgress) {
            return false;
        }
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int i8 = this.mActivePointerId;
                    if (i8 == -1) {
                        Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                        return false;
                    }
                    int findPointerIndex = motionEvent.findPointerIndex(i8);
                    if (findPointerIndex < 0) {
                        return false;
                    }
                    k(motionEvent.getY(findPointerIndex));
                } else if (actionMasked != 3) {
                    if (actionMasked == 6) {
                        h(motionEvent);
                    }
                }
            }
            this.mIsBeingDragged = false;
            this.mActivePointerId = -1;
        } else {
            setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop());
            int pointerId = motionEvent.getPointerId(0);
            this.mActivePointerId = pointerId;
            this.mIsBeingDragged = false;
            int findPointerIndex2 = motionEvent.findPointerIndex(pointerId);
            if (findPointerIndex2 < 0) {
                return false;
            }
            this.mInitialDownY = motionEvent.getY(findPointerIndex2);
        }
        return this.mIsBeingDragged;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.mTarget == null) {
            d();
        }
        View view = this.mTarget;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.mCircleView.getMeasuredWidth();
        int measuredHeight2 = this.mCircleView.getMeasuredHeight();
        int i12 = measuredWidth / 2;
        int i13 = measuredWidth2 / 2;
        int i14 = this.mCurrentTargetOffsetTop;
        this.mCircleView.layout(i12 - i13, i14, i12 + i13, measuredHeight2 + i14);
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        if (this.mTarget == null) {
            d();
        }
        View view = this.mTarget;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.mCircleView.measure(View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824));
        this.mCircleViewIndex = -1;
        for (int i10 = 0; i10 < getChildCount(); i10++) {
            if (getChildAt(i10) == this.mCircleView) {
                this.mCircleViewIndex = i10;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f8, float f9, boolean z7) {
        return dispatchNestedFling(f8, f9, z7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f8, float f9) {
        return dispatchNestedPreFling(f8, f9);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i8, int i9, int[] iArr) {
        if (i9 > 0) {
            float f8 = this.mTotalUnconsumed;
            if (f8 > 0.0f) {
                float f9 = i9;
                if (f9 > f8) {
                    iArr[1] = (int) f8;
                    this.mTotalUnconsumed = 0.0f;
                } else {
                    this.mTotalUnconsumed = f8 - f9;
                    iArr[1] = i9;
                }
                g(this.mTotalUnconsumed);
            }
        }
        if (this.mUsingCustomStart && i9 > 0 && this.mTotalUnconsumed == 0.0f && Math.abs(i9 - iArr[1]) > 0) {
            this.mCircleView.setVisibility(8);
        }
        int[] iArr2 = this.mParentScrollConsumed;
        if (dispatchNestedPreScroll(i8 - iArr[0], i9 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11) {
        onNestedScroll(view, i8, i9, i10, i11, 0, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i8) {
        this.mNestedScrollingParentHelper.b(view, view2, i8);
        startNestedScroll(i8 & 2);
        this.mTotalUnconsumed = 0.0f;
        this.mNestedScrollInProgress = true;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        k kVar = (k) parcelable;
        super.onRestoreInstanceState(kVar.getSuperState());
        setRefreshing(kVar.f3983a);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        return new k(super.onSaveInstanceState(), this.mRefreshing);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i8) {
        return (!isEnabled() || this.mReturningToStart || this.mRefreshing || (i8 & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        this.mNestedScrollingParentHelper.d(view);
        this.mNestedScrollInProgress = false;
        float f8 = this.mTotalUnconsumed;
        if (f8 > 0.0f) {
            e(f8);
            this.mTotalUnconsumed = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp() || this.mRefreshing || this.mNestedScrollInProgress) {
            return false;
        }
        if (actionMasked == 0) {
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mIsBeingDragged = false;
        } else if (actionMasked == 1) {
            int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
            if (findPointerIndex < 0) {
                Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                return false;
            }
            if (this.mIsBeingDragged) {
                float y7 = (motionEvent.getY(findPointerIndex) - this.mInitialMotionY) * DRAG_RATE;
                this.mIsBeingDragged = false;
                e(y7);
            }
            this.mActivePointerId = -1;
            return false;
        } else if (actionMasked == 2) {
            int findPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
            if (findPointerIndex2 < 0) {
                Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                return false;
            }
            float y8 = motionEvent.getY(findPointerIndex2);
            k(y8);
            if (this.mIsBeingDragged) {
                float f8 = (y8 - this.mInitialMotionY) * DRAG_RATE;
                if (f8 <= 0.0f) {
                    return false;
                }
                getParent().requestDisallowInterceptTouchEvent(true);
                g(f8);
            }
        } else if (actionMasked == 3) {
            return false;
        } else {
            if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                if (actionIndex < 0) {
                    Log.e(LOG_TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                    return false;
                }
                this.mActivePointerId = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                h(motionEvent);
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z7) {
        ViewParent parent;
        View view = this.mTarget;
        if (view == null || p0.A(view)) {
            super.requestDisallowInterceptTouchEvent(z7);
        } else if (this.mEnableLegacyRequestDisallowInterceptTouch || (parent = getParent()) == null) {
        } else {
            parent.requestDisallowInterceptTouchEvent(z7);
        }
    }

    void reset() {
        this.mCircleView.clearAnimation();
        this.mProgress.stop();
        this.mCircleView.setVisibility(8);
        setColorViewAlpha(MAX_ALPHA);
        if (this.mScale) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCurrentTargetOffsetTop);
        }
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    void setAnimationProgress(float f8) {
        this.mCircleView.setScaleX(f8);
        this.mCircleView.setScaleY(f8);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        d();
        this.mProgress.f(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i8 = 0; i8 < iArr.length; i8++) {
            iArr2[i8] = androidx.core.content.a.b(context, iArr[i8]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i8) {
        this.mTotalDragDistance = i8;
    }

    @Override // android.view.View
    public void setEnabled(boolean z7) {
        super.setEnabled(z7);
        if (z7) {
            return;
        }
        reset();
    }

    @Deprecated
    public void setLegacyRequestDisallowInterceptTouchEventEnabled(boolean z7) {
        this.mEnableLegacyRequestDisallowInterceptTouch = z7;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z7) {
        this.mNestedScrollingChildHelper.n(z7);
    }

    public void setOnChildScrollUpCallback(i iVar) {
        this.mChildScrollUpCallback = iVar;
    }

    public void setOnRefreshListener(j jVar) {
        this.mListener = jVar;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i8) {
        setProgressBackgroundColorSchemeResource(i8);
    }

    public void setProgressBackgroundColorSchemeColor(int i8) {
        this.mCircleView.setBackgroundColor(i8);
    }

    public void setProgressBackgroundColorSchemeResource(int i8) {
        setProgressBackgroundColorSchemeColor(androidx.core.content.a.b(getContext(), i8));
    }

    public void setProgressViewEndTarget(boolean z7, int i8) {
        this.mSpinnerOffsetEnd = i8;
        this.mScale = z7;
        this.mCircleView.invalidate();
    }

    public void setProgressViewOffset(boolean z7, int i8, int i9) {
        this.mScale = z7;
        this.mOriginalOffsetTop = i8;
        this.mSpinnerOffsetEnd = i9;
        this.mUsingCustomStart = true;
        reset();
        this.mRefreshing = false;
    }

    public void setRefreshing(boolean z7) {
        if (!z7 || this.mRefreshing == z7) {
            i(z7, false);
            return;
        }
        this.mRefreshing = z7;
        setTargetOffsetTopAndBottom((!this.mUsingCustomStart ? this.mSpinnerOffsetEnd + this.mOriginalOffsetTop : this.mSpinnerOffsetEnd) - this.mCurrentTargetOffsetTop);
        this.mNotify = false;
        o(this.mRefreshListener);
    }

    public void setSize(int i8) {
        if (i8 == 0 || i8 == 1) {
            this.mCircleDiameter = (int) (getResources().getDisplayMetrics().density * (i8 == 0 ? 56.0f : 40.0f));
            this.mCircleView.setImageDrawable(null);
            this.mProgress.l(i8);
            this.mCircleView.setImageDrawable(this.mProgress);
        }
    }

    public void setSlingshotDistance(int i8) {
        this.mCustomSlingshotDistance = i8;
    }

    void setTargetOffsetTopAndBottom(int i8) {
        this.mCircleView.bringToFront();
        p0.E(this.mCircleView, i8);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i8) {
        return this.mNestedScrollingChildHelper.p(i8);
    }

    void startScaleDownAnimation(Animation.AnimationListener animationListener) {
        C0051c c0051c = new C0051c();
        this.mScaleDownAnimation = c0051c;
        c0051c.setDuration(150L);
        this.mCircleView.b(animationListener);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownAnimation);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.r();
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRefreshing = false;
        this.mTotalDragDistance = -1.0f;
        this.mParentScrollConsumed = new int[2];
        this.mParentOffsetInWindow = new int[2];
        this.mNestedScrollingV2ConsumedCompat = new int[2];
        this.mActivePointerId = -1;
        this.mCircleViewIndex = -1;
        this.mRefreshListener = new a();
        this.mAnimateToCorrectPosition = new f();
        this.mAnimateToStartPosition = new g();
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMediumAnimationDuration = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mCircleDiameter = (int) (displayMetrics.density * 40.0f);
        c();
        setChildrenDrawingOrderEnabled(true);
        int i8 = (int) (displayMetrics.density * 64.0f);
        this.mSpinnerOffsetEnd = i8;
        this.mTotalDragDistance = i8;
        this.mNestedScrollingParentHelper = new g0(this);
        this.mNestedScrollingChildHelper = new d0(this);
        setNestedScrollingEnabled(true);
        int i9 = -this.mCircleDiameter;
        this.mCurrentTargetOffsetTop = i9;
        this.mOriginalOffsetTop = i9;
        moveToStart(1.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
    }

    public boolean dispatchNestedPreScroll(int i8, int i9, int[] iArr, int[] iArr2, int i10) {
        return i10 == 0 && dispatchNestedPreScroll(i8, i9, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i8, int i9, int i10, int i11, int[] iArr) {
        return this.mNestedScrollingChildHelper.f(i8, i9, i10, i11, iArr);
    }

    public boolean hasNestedScrollingParent(int i8) {
        return i8 == 0 && hasNestedScrollingParent();
    }

    @Override // androidx.core.view.e0
    public void onNestedPreScroll(View view, int i8, int i9, int[] iArr, int i10) {
        if (i10 == 0) {
            onNestedPreScroll(view, i8, i9, iArr);
        }
    }

    @Override // androidx.core.view.e0
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11, int i12) {
        onNestedScroll(view, i8, i9, i10, i11, i12, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // androidx.core.view.e0
    public void onNestedScrollAccepted(View view, View view2, int i8, int i9) {
        if (i9 == 0) {
            onNestedScrollAccepted(view, view2, i8);
        }
    }

    @Override // androidx.core.view.e0
    public boolean onStartNestedScroll(View view, View view2, int i8, int i9) {
        if (i9 == 0) {
            return onStartNestedScroll(view, view2, i8);
        }
        return false;
    }

    @Override // androidx.core.view.e0
    public void onStopNestedScroll(View view, int i8) {
        if (i8 == 0) {
            onStopNestedScroll(view);
        }
    }

    public boolean startNestedScroll(int i8, int i9) {
        return i9 == 0 && startNestedScroll(i8);
    }

    public void stopNestedScroll(int i8) {
        if (i8 == 0) {
            stopNestedScroll();
        }
    }

    public boolean dispatchNestedScroll(int i8, int i9, int i10, int i11, int[] iArr, int i12) {
        return i12 == 0 && this.mNestedScrollingChildHelper.g(i8, i9, i10, i11, iArr, i12);
    }

    @Override // androidx.core.view.f0
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
        if (i12 != 0) {
            return;
        }
        int i13 = iArr[1];
        dispatchNestedScroll(i8, i9, i10, i11, this.mParentOffsetInWindow, i12, iArr);
        int i14 = i11 - (iArr[1] - i13);
        int i15 = i14 == 0 ? i11 + this.mParentOffsetInWindow[1] : i14;
        if (i15 >= 0 || canChildScrollUp()) {
            return;
        }
        float abs = this.mTotalUnconsumed + Math.abs(i15);
        this.mTotalUnconsumed = abs;
        g(abs);
        iArr[1] = iArr[1] + i14;
    }
}
