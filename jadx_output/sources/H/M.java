package H;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.conscrypt.R;

/* loaded from: classes.dex */
public final class M {

    /* renamed from: d, reason: collision with root package name */
    public static final ArrayList f323d = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    public WeakHashMap f324a;

    /* renamed from: b, reason: collision with root package name */
    public SparseArray f325b;

    /* renamed from: c, reason: collision with root package name */
    public WeakReference f326c;

    public final View a(View view) {
        int size;
        WeakHashMap weakHashMap = this.f324a;
        if (weakHashMap != null && weakHashMap.containsKey(view)) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                    View a2 = a(viewGroup.getChildAt(childCount));
                    if (a2 != null) {
                        return a2;
                    }
                }
            }
            ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
            if (arrayList != null && arrayList.size() - 1 >= 0) {
                arrayList.get(size).getClass();
                throw new ClassCastException();
            }
        }
        return null;
    }
}
