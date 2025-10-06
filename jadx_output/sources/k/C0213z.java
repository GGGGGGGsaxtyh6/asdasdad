package k;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import f.AbstractC0101a;
import j0.AbstractC0150d;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

/* renamed from: k.z, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0213z {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2068a;

    /* renamed from: b, reason: collision with root package name */
    public int f2069b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f2070c;

    /* renamed from: d, reason: collision with root package name */
    public Object f2071d;

    public /* synthetic */ C0213z(Object obj, int i2, Serializable serializable, int i3) {
        this.f2068a = i3;
        this.f2070c = obj;
        this.f2069b = i2;
        this.f2071d = serializable;
    }

    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Object, byte[], java.io.Serializable] */
    public static final C0213z b(u0.o oVar, String str) {
        AbstractC0150d.e(str, "content");
        Charset charset = q0.a.f2219a;
        if (oVar != null) {
            Pattern pattern = u0.o.f2491c;
            Charset a2 = oVar.a(null);
            if (a2 == null) {
                oVar = C0.m.n(oVar + "; charset=utf-8");
            } else {
                charset = a2;
            }
        }
        ?? bytes = str.getBytes(charset);
        AbstractC0150d.d(bytes, "this as java.lang.String).getBytes(charset)");
        int length = bytes.length;
        v0.b.b(bytes.length, 0, length);
        return new C0213z(oVar, length, bytes, 1);
    }

    public void a() {
        U0 u02;
        ImageView imageView = (ImageView) this.f2070c;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            AbstractC0191n0.a(drawable);
        }
        if (drawable != null && (u02 = (U0) this.f2071d) != null) {
            C0203u.d(drawable, u02, imageView.getDrawableState());
        }
    }

    public void c(AttributeSet attributeSet, int i2) {
        int resourceId;
        ImageView imageView = (ImageView) this.f2070c;
        Context context = imageView.getContext();
        int[] iArr = AbstractC0101a.f1267f;
        D0.h p2 = D0.h.p(context, attributeSet, iArr, i2);
        H.N.g(imageView, imageView.getContext(), iArr, attributeSet, (TypedArray) p2.f259c, i2);
        try {
            Drawable drawable = imageView.getDrawable();
            TypedArray typedArray = (TypedArray) p2.f259c;
            if (drawable == null && (resourceId = typedArray.getResourceId(1, -1)) != -1 && (drawable = C0.d.v(imageView.getContext(), resourceId)) != null) {
                imageView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                AbstractC0191n0.a(drawable);
            }
            if (typedArray.hasValue(2)) {
                imageView.setImageTintList(p2.i(2));
            }
            if (typedArray.hasValue(3)) {
                imageView.setImageTintMode(AbstractC0191n0.b(typedArray.getInt(3, -1), null));
            }
            p2.r();
        } catch (Throwable th) {
            p2.r();
            throw th;
        }
    }

    public String toString() {
        switch (this.f2068a) {
            case 2:
                StringBuilder sb = new StringBuilder();
                if (((u0.r) this.f2070c) == u0.r.HTTP_1_0) {
                    sb.append("HTTP/1.0");
                } else {
                    sb.append("HTTP/1.1");
                }
                sb.append(' ');
                sb.append(this.f2069b);
                sb.append(' ');
                sb.append((String) this.f2071d);
                String sb2 = sb.toString();
                AbstractC0150d.d(sb2, "StringBuilder().apply(builderAction).toString()");
                return sb2;
            default:
                return super.toString();
        }
    }

    public C0213z(ImageView imageView) {
        this.f2068a = 0;
        this.f2069b = 0;
        this.f2070c = imageView;
    }
}
