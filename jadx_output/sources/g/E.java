package g;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public final class E implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final View f1354a;

    /* renamed from: b, reason: collision with root package name */
    public final String f1355b;

    /* renamed from: c, reason: collision with root package name */
    public Method f1356c;

    /* renamed from: d, reason: collision with root package name */
    public Context f1357d;

    public E(View view, String str) {
        this.f1354a = view;
        this.f1355b = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        Method method;
        if (this.f1356c == null) {
            View view2 = this.f1354a;
            Context context = view2.getContext();
            while (true) {
                String str2 = this.f1355b;
                if (context != null) {
                    try {
                        if (!context.isRestricted() && (method = context.getClass().getMethod(str2, View.class)) != null) {
                            this.f1356c = method;
                            this.f1357d = context;
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                    if (context instanceof ContextWrapper) {
                        context = ((ContextWrapper) context).getBaseContext();
                    } else {
                        context = null;
                    }
                } else {
                    int id = view2.getId();
                    if (id == -1) {
                        str = BuildConfig.FLAVOR;
                    } else {
                        str = " with id '" + view2.getContext().getResources().getResourceEntryName(id) + "'";
                    }
                    throw new IllegalStateException("Could not find method " + str2 + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + view2.getClass() + str);
                }
            }
        }
        try {
            this.f1356c.invoke(this.f1357d, view);
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", e2);
        } catch (InvocationTargetException e3) {
            throw new IllegalStateException("Could not execute method for android:onClick", e3);
        }
    }
}
