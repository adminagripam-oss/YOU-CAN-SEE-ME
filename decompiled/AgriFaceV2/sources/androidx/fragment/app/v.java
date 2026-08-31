package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
/* loaded from: classes.dex */
abstract class v {

    /* loaded from: classes.dex */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        public final Animation f3678a;

        /* renamed from: b  reason: collision with root package name */
        public final AnimatorSet f3679b;

        a(Animator animator) {
            this.f3678a = null;
            AnimatorSet animatorSet = new AnimatorSet();
            this.f3679b = animatorSet;
            animatorSet.play(animator);
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }

        a(Animation animation) {
            this.f3678a = animation;
            this.f3679b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }
    }

    /* loaded from: classes.dex */
    static class b extends AnimationSet implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        private final ViewGroup f3680e;

        /* renamed from: f  reason: collision with root package name */
        private final View f3681f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f3682g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f3683h;

        /* renamed from: i  reason: collision with root package name */
        private boolean f3684i;

        /* JADX INFO: Access modifiers changed from: package-private */
        public b(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f3684i = true;
            this.f3680e = viewGroup;
            this.f3681f = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j8, Transformation transformation) {
            this.f3684i = true;
            if (this.f3682g) {
                return !this.f3683h;
            }
            if (!super.getTransformation(j8, transformation)) {
                this.f3682g = true;
                androidx.core.view.k0.a(this.f3680e, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3682g || !this.f3684i) {
                this.f3680e.endViewTransition(this.f3681f);
                this.f3683h = true;
                return;
            }
            this.f3684i = false;
            this.f3680e.post(this);
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j8, Transformation transformation, float f8) {
            this.f3684i = true;
            if (this.f3682g) {
                return !this.f3683h;
            }
            if (!super.getTransformation(j8, transformation, f8)) {
                this.f3682g = true;
                androidx.core.view.k0.a(this.f3680e, this);
            }
            return true;
        }
    }

    private static int a(p pVar, boolean z7, boolean z8) {
        return z8 ? z7 ? pVar.E() : pVar.F() : z7 ? pVar.q() : pVar.t();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a b(Context context, p pVar, boolean z7, boolean z8) {
        int A = pVar.A();
        int a8 = a(pVar, z7, z8);
        boolean z9 = false;
        pVar.p1(0, 0, 0, 0);
        ViewGroup viewGroup = pVar.I;
        if (viewGroup != null && viewGroup.getTag(r1.b.f11312c) != null) {
            pVar.I.setTag(r1.b.f11312c, null);
        }
        ViewGroup viewGroup2 = pVar.I;
        if (viewGroup2 == null || viewGroup2.getLayoutTransition() == null) {
            Animation k02 = pVar.k0(A, z7, a8);
            if (k02 != null) {
                return new a(k02);
            }
            Animator l02 = pVar.l0(A, z7, a8);
            if (l02 != null) {
                return new a(l02);
            }
            if (a8 == 0 && A != 0) {
                a8 = d(context, A, z7);
            }
            if (a8 != 0) {
                boolean equals = "anim".equals(context.getResources().getResourceTypeName(a8));
                if (equals) {
                    try {
                        Animation loadAnimation = AnimationUtils.loadAnimation(context, a8);
                        if (loadAnimation != null) {
                            return new a(loadAnimation);
                        }
                        z9 = true;
                    } catch (Resources.NotFoundException e8) {
                        throw e8;
                    } catch (RuntimeException unused) {
                    }
                }
                if (!z9) {
                    try {
                        Animator loadAnimator = AnimatorInflater.loadAnimator(context, a8);
                        if (loadAnimator != null) {
                            return new a(loadAnimator);
                        }
                    } catch (RuntimeException e9) {
                        if (equals) {
                            throw e9;
                        }
                        Animation loadAnimation2 = AnimationUtils.loadAnimation(context, a8);
                        if (loadAnimation2 != null) {
                            return new a(loadAnimation2);
                        }
                    }
                }
            }
            return null;
        }
        return null;
    }

    private static int c(Context context, int i8) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(16973825, new int[]{i8});
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private static int d(Context context, int i8, boolean z7) {
        int i9;
        if (i8 == 4097) {
            return z7 ? r1.a.f11308e : r1.a.f11309f;
        } else if (i8 == 8194) {
            return z7 ? r1.a.f11304a : r1.a.f11305b;
        } else {
            if (i8 == 8197) {
                i9 = z7 ? 16842938 : 16842939;
            } else if (i8 == 4099) {
                return z7 ? r1.a.f11306c : r1.a.f11307d;
            } else if (i8 != 4100) {
                return -1;
            } else {
                i9 = z7 ? 16842936 : 16842937;
            }
            return c(context, i9);
        }
    }
}
