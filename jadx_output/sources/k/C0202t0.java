package k;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.InvocationTargetException;
import org.conscrypt.R;

/* renamed from: k.t0, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0202t0 extends ListView {

    /* renamed from: a, reason: collision with root package name */
    public final Rect f2018a;

    /* renamed from: b, reason: collision with root package name */
    public int f2019b;

    /* renamed from: c, reason: collision with root package name */
    public int f2020c;

    /* renamed from: d, reason: collision with root package name */
    public int f2021d;

    /* renamed from: e, reason: collision with root package name */
    public int f2022e;

    /* renamed from: f, reason: collision with root package name */
    public int f2023f;

    /* renamed from: g, reason: collision with root package name */
    public C0198r0 f2024g;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f2025i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f2026j;

    /* renamed from: k, reason: collision with root package name */
    public K.d f2027k;

    /* renamed from: l, reason: collision with root package name */
    public E.b f2028l;

    public C0202t0(Context context, boolean z2) {
        super(context, null, R.attr.dropDownListViewStyle);
        this.f2018a = new Rect();
        this.f2019b = 0;
        this.f2020c = 0;
        this.f2021d = 0;
        this.f2022e = 0;
        this.f2025i = z2;
        setCacheColorHint(0);
    }

    public final int a(int i2, int i3) {
        int makeMeasureSpec;
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int i4 = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i5 = 0;
        View view = null;
        for (int i6 = 0; i6 < count; i6++) {
            int itemViewType = adapter.getItemViewType(i6);
            if (itemViewType != i5) {
                view = null;
                i5 = itemViewType;
            }
            view = adapter.getView(i6, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i7 = layoutParams.height;
            if (i7 > 0) {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
            } else {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i2, makeMeasureSpec);
            view.forceLayout();
            if (i6 > 0) {
                i4 += dividerHeight;
            }
            i4 += view.getMeasuredHeight();
            if (i4 >= i3) {
                return i3;
            }
        }
        return i4;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0148 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(android.view.MotionEvent r17, int r18) {
        /*
            Method dump skipped, instructions count: 393
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: k.C0202t0.b(android.view.MotionEvent, int):boolean");
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Drawable selector;
        Rect rect = this.f2018a;
        if (!rect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(rect);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        if (this.f2028l != null) {
            return;
        }
        super.drawableStateChanged();
        C0198r0 c0198r0 = this.f2024g;
        if (c0198r0 != null) {
            c0198r0.f2006b = true;
        }
        Drawable selector = getSelector();
        if (selector != null && this.f2026j && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean hasFocus() {
        if (!this.f2025i && !super.hasFocus()) {
            return false;
        }
        return true;
    }

    @Override // android.view.View
    public final boolean hasWindowFocus() {
        if (!this.f2025i && !super.hasWindowFocus()) {
            return false;
        }
        return true;
    }

    @Override // android.view.View
    public final boolean isFocused() {
        if (!this.f2025i && !super.isFocused()) {
            return false;
        }
        return true;
    }

    @Override // android.view.View
    public final boolean isInTouchMode() {
        if ((this.f2025i && this.h) || super.isInTouchMode()) {
            return true;
        }
        return false;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.f2028l = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f2028l == null) {
            E.b bVar = new E.b(8, this);
            this.f2028l = bVar;
            post(bVar);
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked != 9 && actionMasked != 7) {
            setSelection(-1);
        } else {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (pointToPosition != -1 && pointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    requestFocus();
                    if (i2 >= 30 && AbstractC0195p0.f1997d) {
                        try {
                            AbstractC0195p0.f1994a.invoke(this, Integer.valueOf(pointToPosition), childAt, Boolean.FALSE, -1, -1);
                            AbstractC0195p0.f1995b.invoke(this, Integer.valueOf(pointToPosition));
                            AbstractC0195p0.f1996c.invoke(this, Integer.valueOf(pointToPosition));
                        } catch (IllegalAccessException e2) {
                            e2.printStackTrace();
                        } catch (InvocationTargetException e3) {
                            e3.printStackTrace();
                        }
                    } else {
                        setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                    }
                }
                Drawable selector = getSelector();
                if (selector != null && this.f2026j && isPressed()) {
                    selector.setState(getDrawableState());
                }
            }
        }
        return onHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f2023f = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        E.b bVar = this.f2028l;
        if (bVar != null) {
            C0202t0 c0202t0 = (C0202t0) bVar.f269b;
            c0202t0.f2028l = null;
            c0202t0.removeCallbacks(bVar);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z2) {
        this.h = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [k.r0, android.graphics.drawable.Drawable$Callback, android.graphics.drawable.Drawable] */
    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        C0198r0 c0198r0 = null;
        if (drawable != 0) {
            ?? drawable2 = new Drawable();
            Drawable drawable3 = drawable2.f2005a;
            if (drawable3 != null) {
                drawable3.setCallback(null);
            }
            drawable2.f2005a = drawable;
            drawable.setCallback(drawable2);
            drawable2.f2006b = true;
            c0198r0 = drawable2;
        }
        this.f2024g = c0198r0;
        super.setSelector(c0198r0);
        Rect rect = new Rect();
        if (drawable != 0) {
            drawable.getPadding(rect);
        }
        this.f2019b = rect.left;
        this.f2020c = rect.top;
        this.f2021d = rect.right;
        this.f2022e = rect.bottom;
    }
}
