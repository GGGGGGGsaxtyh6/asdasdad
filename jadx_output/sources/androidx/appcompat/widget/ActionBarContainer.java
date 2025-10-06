package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import f.AbstractC0101a;
import k.C0166b;
import k.Q0;
import org.conscrypt.R;

/* loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    public boolean f824a;

    /* renamed from: b, reason: collision with root package name */
    public View f825b;

    /* renamed from: c, reason: collision with root package name */
    public View f826c;

    /* renamed from: d, reason: collision with root package name */
    public Drawable f827d;

    /* renamed from: e, reason: collision with root package name */
    public Drawable f828e;

    /* renamed from: f, reason: collision with root package name */
    public Drawable f829f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f830g;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public final int f831i;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackground(new C0166b(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0101a.f1262a);
        boolean z2 = false;
        this.f827d = obtainStyledAttributes.getDrawable(0);
        this.f828e = obtainStyledAttributes.getDrawable(2);
        this.f831i = obtainStyledAttributes.getDimensionPixelSize(13, -1);
        if (getId() == R.id.split_action_bar) {
            this.f830g = true;
            this.f829f = obtainStyledAttributes.getDrawable(1);
        }
        obtainStyledAttributes.recycle();
        if (!this.f830g ? !(this.f827d != null || this.f828e != null) : this.f829f == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f827d;
        if (drawable != null && drawable.isStateful()) {
            this.f827d.setState(getDrawableState());
        }
        Drawable drawable2 = this.f828e;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f828e.setState(getDrawableState());
        }
        Drawable drawable3 = this.f829f;
        if (drawable3 != null && drawable3.isStateful()) {
            this.f829f.setState(getDrawableState());
        }
    }

    public View getTabContainer() {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f827d;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f828e;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f829f;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f825b = findViewById(R.id.action_bar);
        this.f826c = findViewById(R.id.action_context_bar);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f824a && !super.onInterceptTouchEvent(motionEvent)) {
            return false;
        }
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        boolean z3 = true;
        if (this.f830g) {
            Drawable drawable = this.f829f;
            if (drawable != null) {
                drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z3 = false;
            }
        } else {
            if (this.f827d != null) {
                if (this.f825b.getVisibility() == 0) {
                    this.f827d.setBounds(this.f825b.getLeft(), this.f825b.getTop(), this.f825b.getRight(), this.f825b.getBottom());
                } else {
                    View view = this.f826c;
                    if (view != null && view.getVisibility() == 0) {
                        this.f827d.setBounds(this.f826c.getLeft(), this.f826c.getTop(), this.f826c.getRight(), this.f826c.getBottom());
                    } else {
                        this.f827d.setBounds(0, 0, 0, 0);
                    }
                }
            } else {
                z3 = false;
            }
            this.h = false;
        }
        if (z3) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        int i4;
        if (this.f825b == null && View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE && (i4 = this.f831i) >= 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.min(i4, View.MeasureSpec.getSize(i3)), Integer.MIN_VALUE);
        }
        super.onMeasure(i2, i3);
        if (this.f825b == null) {
            return;
        }
        View.MeasureSpec.getMode(i3);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f827d;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f827d);
        }
        this.f827d = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f825b;
            if (view != null) {
                this.f827d.setBounds(view.getLeft(), this.f825b.getTop(), this.f825b.getRight(), this.f825b.getBottom());
            }
        }
        boolean z2 = false;
        if (!this.f830g ? !(this.f827d != null || this.f828e != null) : this.f829f == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        invalidate();
        invalidateOutline();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f829f;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f829f);
        }
        this.f829f = drawable;
        boolean z2 = this.f830g;
        boolean z3 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (z2 && (drawable2 = this.f829f) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!z2 ? !(this.f827d != null || this.f828e != null) : this.f829f == null) {
            z3 = true;
        }
        setWillNotDraw(z3);
        invalidate();
        invalidateOutline();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2 = this.f828e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f828e);
        }
        this.f828e = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.h && this.f828e != null) {
                throw null;
            }
        }
        boolean z2 = false;
        if (!this.f830g ? !(this.f827d != null || this.f828e != null) : this.f829f == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        invalidate();
        invalidateOutline();
    }

    public void setTransitioning(boolean z2) {
        int i2;
        this.f824a = z2;
        if (z2) {
            i2 = 393216;
        } else {
            i2 = 262144;
        }
        setDescendantFocusability(i2);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        boolean z2;
        super.setVisibility(i2);
        if (i2 == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        Drawable drawable = this.f827d;
        if (drawable != null) {
            drawable.setVisible(z2, false);
        }
        Drawable drawable2 = this.f828e;
        if (drawable2 != null) {
            drawable2.setVisible(z2, false);
        }
        Drawable drawable3 = this.f829f;
        if (drawable3 != null) {
            drawable3.setVisible(z2, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        Drawable drawable2 = this.f827d;
        boolean z2 = this.f830g;
        if ((drawable == drawable2 && !z2) || ((drawable == this.f828e && this.h) || ((drawable == this.f829f && z2) || super.verifyDrawable(drawable)))) {
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i2) {
        if (i2 != 0) {
            return super.startActionModeForChild(view, callback, i2);
        }
        return null;
    }

    public void setTabContainer(Q0 q02) {
    }
}
