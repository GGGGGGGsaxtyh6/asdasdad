package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class l implements LayoutInflater.Factory2 {

    /* renamed from: a, reason: collision with root package name */
    public final t f1061a;

    public l(t tVar) {
        this.f1061a = tVar;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z2;
        boolean equals = j.class.getName().equals(str);
        t tVar = this.f1061a;
        if (equals) {
            return new j(context, attributeSet, tVar);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, P.a.f607a);
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (attributeValue != null) {
            try {
                z2 = d.class.isAssignableFrom(q.b(context.getClassLoader(), attributeValue));
            } catch (ClassNotFoundException unused) {
                z2 = false;
            }
            if (z2) {
                int id = view != null ? view.getId() : 0;
                if (id == -1 && resourceId == -1 && string == null) {
                    throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                }
                if (resourceId != -1) {
                    tVar.g();
                }
                if (string != null) {
                    y yVar = tVar.f1085c;
                    ArrayList arrayList = yVar.f1131a;
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        if (arrayList.get(size) != null) {
                            throw new ClassCastException();
                        }
                    }
                    Iterator it = yVar.f1132b.values().iterator();
                    while (it.hasNext()) {
                        if (it.next() != null) {
                            throw new ClassCastException();
                        }
                    }
                }
                if (id != -1) {
                    tVar.g();
                }
                q qVar = tVar.f1102u;
                context.getClassLoader();
                qVar.a(attributeValue);
                throw null;
            }
        }
        return null;
    }
}
