package v;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import java.util.HashMap;
import s.C0234a;

/* renamed from: v.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0253c extends View {

    /* renamed from: a, reason: collision with root package name */
    public int[] f2599a;

    /* renamed from: b, reason: collision with root package name */
    public int f2600b;

    /* renamed from: c, reason: collision with root package name */
    public Context f2601c;

    /* renamed from: d, reason: collision with root package name */
    public C0234a f2602d;

    /* renamed from: e, reason: collision with root package name */
    public String f2603e;

    /* renamed from: f, reason: collision with root package name */
    public String f2604f;

    /* renamed from: g, reason: collision with root package name */
    public HashMap f2605g;

    /* JADX WARN: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.String r6) {
        /*
            r5 = this;
            if (r6 == 0) goto La5
            int r0 = r6.length()
            if (r0 != 0) goto La
            goto La5
        La:
            android.content.Context r0 = r5.f2601c
            if (r0 != 0) goto Lf
            return
        Lf:
            java.lang.String r6 = r6.trim()
            android.view.ViewParent r1 = r5.getParent()
            boolean r1 = r1 instanceof androidx.constraintlayout.widget.ConstraintLayout
            if (r1 == 0) goto L21
            android.view.ViewParent r1 = r5.getParent()
            androidx.constraintlayout.widget.ConstraintLayout r1 = (androidx.constraintlayout.widget.ConstraintLayout) r1
        L21:
            android.view.ViewParent r1 = r5.getParent()
            boolean r1 = r1 instanceof androidx.constraintlayout.widget.ConstraintLayout
            r2 = 0
            if (r1 == 0) goto L31
            android.view.ViewParent r1 = r5.getParent()
            androidx.constraintlayout.widget.ConstraintLayout r1 = (androidx.constraintlayout.widget.ConstraintLayout) r1
            goto L32
        L31:
            r1 = r2
        L32:
            boolean r3 = r5.isInEditMode()
            if (r3 == 0) goto L59
            if (r1 == 0) goto L59
            if (r6 == 0) goto L4d
            java.util.HashMap r3 = r1.f965m
            if (r3 == 0) goto L4d
            boolean r3 = r3.containsKey(r6)
            if (r3 == 0) goto L4d
            java.util.HashMap r3 = r1.f965m
            java.lang.Object r3 = r3.get(r6)
            goto L4e
        L4d:
            r3 = r2
        L4e:
            boolean r4 = r3 instanceof java.lang.Integer
            if (r4 == 0) goto L59
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            goto L5a
        L59:
            r3 = 0
        L5a:
            if (r3 != 0) goto L62
            if (r1 == 0) goto L62
            int r3 = r5.d(r1, r6)
        L62:
            if (r3 != 0) goto L6e
            java.lang.Class<v.q> r1 = v.q.class
            java.lang.reflect.Field r1 = r1.getField(r6)     // Catch: java.lang.Exception -> L6e
            int r3 = r1.getInt(r2)     // Catch: java.lang.Exception -> L6e
        L6e:
            if (r3 != 0) goto L7e
            android.content.res.Resources r1 = r0.getResources()
            java.lang.String r2 = "id"
            java.lang.String r0 = r0.getPackageName()
            int r3 = r1.getIdentifier(r6, r2, r0)
        L7e:
            if (r3 == 0) goto L8d
            java.util.HashMap r0 = r5.f2605g
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            r0.put(r1, r6)
            r5.b(r3)
            goto La5
        L8d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Could not find id of \""
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r6 = "\""
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.lang.String r0 = "ConstraintHelper"
            android.util.Log.w(r0, r6)
        La5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: v.AbstractC0253c.a(java.lang.String):void");
    }

    public final void b(int i2) {
        if (i2 == getId()) {
            return;
        }
        int i3 = this.f2600b + 1;
        int[] iArr = this.f2599a;
        if (i3 > iArr.length) {
            this.f2599a = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f2599a;
        int i4 = this.f2600b;
        iArr2[i4] = i2;
        this.f2600b = i4 + 1;
    }

    public final void c(String str) {
        ConstraintLayout constraintLayout;
        if (str == null || str.length() == 0 || this.f2601c == null) {
            return;
        }
        String trim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
            constraintLayout = (ConstraintLayout) getParent();
        } else {
            constraintLayout = null;
        }
        if (constraintLayout == null) {
            Log.w("ConstraintHelper", "Parent not a ConstraintLayout");
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof e) && trim.equals(((e) layoutParams).f2631Y)) {
                if (childAt.getId() == -1) {
                    Log.w("ConstraintHelper", "to use ConstraintTag view " + childAt.getClass().getSimpleName() + " must have an ID");
                } else {
                    b(childAt.getId());
                }
            }
        }
    }

    public final int d(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        String str2;
        if (str == null || (resources = this.f2601c.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            if (childAt.getId() != -1) {
                try {
                    str2 = resources.getResourceEntryName(childAt.getId());
                } catch (Resources.NotFoundException unused) {
                    str2 = null;
                }
                if (str.equals(str2)) {
                    return childAt.getId();
                }
            }
        }
        return 0;
    }

    public final void e() {
        if (this.f2602d == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof e) {
            ((e) layoutParams).f2661p0 = this.f2602d;
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f2599a, this.f2600b);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f2603e;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.f2604f;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        setMeasuredDimension(0, 0);
    }

    public void setIds(String str) {
        this.f2603e = str;
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.f2600b = 0;
        while (true) {
            int indexOf = str.indexOf(44, i2);
            if (indexOf == -1) {
                a(str.substring(i2));
                return;
            } else {
                a(str.substring(i2, indexOf));
                i2 = indexOf + 1;
            }
        }
    }

    public void setReferenceTags(String str) {
        this.f2604f = str;
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.f2600b = 0;
        while (true) {
            int indexOf = str.indexOf(44, i2);
            if (indexOf == -1) {
                c(str.substring(i2));
                return;
            } else {
                c(str.substring(i2, indexOf));
                i2 = indexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.f2603e = null;
        this.f2600b = 0;
        for (int i2 : iArr) {
            b(i2);
        }
    }

    @Override // android.view.View
    public final void setTag(int i2, Object obj) {
        super.setTag(i2, obj);
        if (obj == null && this.f2603e == null) {
            b(i2);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
    }
}
