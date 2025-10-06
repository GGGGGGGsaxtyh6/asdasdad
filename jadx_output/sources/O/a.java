package O;

import M.x;
import android.text.Editable;

/* loaded from: classes.dex */
public final class a extends Editable.Factory {

    /* renamed from: a, reason: collision with root package name */
    public static final Object f587a = new Object();

    /* renamed from: b, reason: collision with root package name */
    public static volatile a f588b;

    /* renamed from: c, reason: collision with root package name */
    public static Class f589c;

    @Override // android.text.Editable.Factory
    public final Editable newEditable(CharSequence charSequence) {
        Class cls = f589c;
        if (cls != null) {
            return new x(cls, charSequence);
        }
        return super.newEditable(charSequence);
    }
}
