package B0;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import g.C0105c;
import g.C0106d;
import g.C0109g;
import g.DialogC0110h;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Collections;
import org.conscrypt.PSKKeyManager;
import org.conscrypt.ct.CTConstants;
import q.C0231b;

/* loaded from: classes.dex */
public final class F {

    /* renamed from: a, reason: collision with root package name */
    public int f79a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f80b;

    public F(int i2) {
        switch (i2) {
            case 1:
                this.f79a = 1;
                this.f80b = Collections.singletonList(null);
                return;
            case 2:
            default:
                this.f80b = new int[10];
                return;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                this.f80b = new Object[PSKKeyManager.MAX_KEY_LENGTH_BYTES];
                return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1, types: [android.widget.ListAdapter] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    public DialogC0110h a() {
        int i2;
        C0106d c0106d = (C0106d) this.f80b;
        DialogC0110h dialogC0110h = new DialogC0110h(c0106d.f1415a, this.f79a);
        View view = c0106d.f1419e;
        C0109g c0109g = dialogC0110h.f1452f;
        if (view != null) {
            c0109g.f1437n = view;
        } else {
            CharSequence charSequence = c0106d.f1418d;
            if (charSequence != null) {
                c0109g.f1428d = charSequence;
                TextView textView = c0109g.f1435l;
                if (textView != null) {
                    textView.setText(charSequence);
                }
            }
            Drawable drawable = c0106d.f1417c;
            if (drawable != null) {
                c0109g.f1433j = drawable;
                ImageView imageView = c0109g.f1434k;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    c0109g.f1434k.setImageDrawable(drawable);
                }
            }
        }
        if (c0106d.f1421g != null) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) c0106d.f1416b.inflate(c0109g.f1441r, (ViewGroup) null);
            if (c0106d.f1422i) {
                i2 = c0109g.f1442s;
            } else {
                i2 = c0109g.f1443t;
            }
            Object obj = c0106d.f1421g;
            ?? r7 = obj;
            if (obj == null) {
                r7 = new ArrayAdapter(c0106d.f1415a, i2, R.id.text1, (Object[]) null);
            }
            c0109g.f1438o = r7;
            c0109g.f1439p = c0106d.f1423j;
            if (c0106d.h != null) {
                alertController$RecycleListView.setOnItemClickListener(new C0105c(c0106d, c0109g));
            }
            if (c0106d.f1422i) {
                alertController$RecycleListView.setChoiceMode(1);
            }
            c0109g.f1429e = alertController$RecycleListView;
        }
        dialogC0110h.setCancelable(true);
        dialogC0110h.setCanceledOnTouchOutside(true);
        dialogC0110h.setOnCancelListener(null);
        dialogC0110h.setOnDismissListener(null);
        j.o oVar = c0106d.f1420f;
        if (oVar != null) {
            dialogC0110h.setOnKeyListener(oVar);
        }
        return dialogC0110h;
    }

    public int b() {
        if ((this.f79a & 128) != 0) {
            return ((int[]) this.f80b)[7];
        }
        return 65535;
    }

    public boolean c() {
        if (this.f79a < ((ArrayList) this.f80b).size()) {
            return true;
        }
        return false;
    }

    public void d(F f2) {
        AbstractC0150d.e(f2, "other");
        int i2 = 0;
        while (i2 < 10) {
            int i3 = i2 + 1;
            boolean z2 = true;
            if (((1 << i2) & f2.f79a) == 0) {
                z2 = false;
            }
            if (z2) {
                f(i2, ((int[]) f2.f80b)[i2]);
            }
            i2 = i3;
        }
    }

    public void e(C0231b c0231b) {
        int i2 = this.f79a;
        Object[] objArr = (Object[]) this.f80b;
        if (i2 < objArr.length) {
            objArr[i2] = c0231b;
            this.f79a = i2 + 1;
        }
    }

    public void f(int i2, int i3) {
        if (i2 >= 0) {
            int[] iArr = (int[]) this.f80b;
            if (i2 < iArr.length) {
                this.f79a = (1 << i2) | this.f79a;
                iArr[i2] = i3;
            }
        }
    }

    public F(Context context) {
        int h = DialogC0110h.h(context, 0);
        this.f80b = new C0106d(new ContextThemeWrapper(context, DialogC0110h.h(context, h)));
        this.f79a = h;
    }

    public F(ArrayList arrayList, int i2) {
        switch (i2) {
            case 4:
                this.f80b = arrayList;
                return;
            default:
                this.f79a = 0;
                this.f80b = arrayList;
                return;
        }
    }
}
