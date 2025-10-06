package B0;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import java.util.ArrayList;
import org.conscrypt.PSKKeyManager;

/* loaded from: classes.dex */
public final class C {

    /* renamed from: a, reason: collision with root package name */
    public final int f72a;

    /* renamed from: b, reason: collision with root package name */
    public final int f73b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f74c;

    public C(Context context, XmlResourceParser xmlResourceParser) {
        this.f74c = new ArrayList();
        this.f73b = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), v.r.h);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == 0) {
                this.f72a = obtainStyledAttributes.getResourceId(index, this.f72a);
            } else if (index == 1) {
                int resourceId = obtainStyledAttributes.getResourceId(index, this.f73b);
                this.f73b = resourceId;
                String resourceTypeName = context.getResources().getResourceTypeName(resourceId);
                context.getResources().getResourceName(resourceId);
                if ("layout".equals(resourceTypeName)) {
                    new v.n().a(context, resourceId);
                }
            }
        }
        obtainStyledAttributes.recycle();
    }

    public C() {
        this.f74c = new C[PSKKeyManager.MAX_KEY_LENGTH_BYTES];
        this.f72a = 0;
        this.f73b = 0;
    }

    public C(int i2, int i3) {
        this.f74c = null;
        this.f72a = i2;
        int i4 = i3 & 7;
        this.f73b = i4 == 0 ? 8 : i4;
    }
}
