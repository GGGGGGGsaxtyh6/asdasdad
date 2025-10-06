package K;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.Editable;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class j implements ActionMode.Callback {

    /* renamed from: a, reason: collision with root package name */
    public final ActionMode.Callback f509a;

    /* renamed from: b, reason: collision with root package name */
    public final TextView f510b;

    /* renamed from: c, reason: collision with root package name */
    public Class f511c;

    /* renamed from: d, reason: collision with root package name */
    public Method f512d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f513e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f514f = false;

    public j(ActionMode.Callback callback, TextView textView) {
        this.f509a = callback;
        this.f510b = textView;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.f509a.onActionItemClicked(actionMode, menuItem);
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.f509a.onCreateActionMode(actionMode, menu);
    }

    @Override // android.view.ActionMode.Callback
    public final void onDestroyActionMode(ActionMode actionMode) {
        this.f509a.onDestroyActionMode(actionMode);
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Method declaredMethod;
        boolean z2;
        TextView textView = this.f510b;
        Context context = textView.getContext();
        PackageManager packageManager = context.getPackageManager();
        if (!this.f514f) {
            this.f514f = true;
            try {
                Class<?> cls = Class.forName("com.android.internal.view.menu.MenuBuilder");
                this.f511c = cls;
                this.f512d = cls.getDeclaredMethod("removeItemAt", Integer.TYPE);
                this.f513e = true;
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                this.f511c = null;
                this.f512d = null;
                this.f513e = false;
            }
        }
        try {
            if (this.f513e && this.f511c.isInstance(menu)) {
                declaredMethod = this.f512d;
            } else {
                declaredMethod = menu.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE);
            }
            for (int size = menu.size() - 1; size >= 0; size--) {
                MenuItem item = menu.getItem(size);
                if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                    declaredMethod.invoke(menu, Integer.valueOf(size));
                }
            }
            ArrayList arrayList = new ArrayList();
            if (context instanceof Activity) {
                for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain"), 0)) {
                    if (!context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                        ActivityInfo activityInfo = resolveInfo.activityInfo;
                        if (activityInfo.exported) {
                            String str = activityInfo.permission;
                            if (str != null && context.checkSelfPermission(str) != 0) {
                            }
                        }
                    }
                    arrayList.add(resolveInfo);
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                ResolveInfo resolveInfo2 = (ResolveInfo) arrayList.get(i2);
                MenuItem add = menu.add(0, 0, i2 + 100, resolveInfo2.loadLabel(packageManager));
                Intent type = new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
                if ((textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                Intent putExtra = type.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !z2);
                ActivityInfo activityInfo2 = resolveInfo2.activityInfo;
                add.setIntent(putExtra.setClassName(activityInfo2.packageName, activityInfo2.name)).setShowAsAction(1);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
        }
        return this.f509a.onPrepareActionMode(actionMode, menu);
    }
}
