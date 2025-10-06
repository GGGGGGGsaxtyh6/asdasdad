package E;

import B0.F;
import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import z.AbstractC0257b;

/* loaded from: classes.dex */
public abstract class f {

    /* renamed from: a, reason: collision with root package name */
    public static final n.j f276a = new n.j(2);

    /* renamed from: b, reason: collision with root package name */
    public static final d f277b = new Object();

    public static F a(Context context, List list) {
        C0.f.c("FontProvider.getFontFamilyResult");
        try {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                g gVar = (g) list.get(i2);
                ProviderInfo b2 = b(context.getPackageManager(), gVar, context.getResources());
                if (b2 == null) {
                    F f2 = new F(1);
                    Trace.endSection();
                    return f2;
                }
                arrayList.add(c(context, gVar, b2.authority));
            }
            F f3 = new F(arrayList, 1);
            Trace.endSection();
            return f3;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.Object, E.e] */
    public static ProviderInfo b(PackageManager packageManager, g gVar, Resources resources) {
        C0.f.c("FontProvider.getProvider");
        try {
            List list = gVar.f281d;
            String str = gVar.f278a;
            String str2 = gVar.f279b;
            if (list == null) {
                list = AbstractC0257b.d(resources, 0);
            }
            ?? obj = new Object();
            obj.f273a = str;
            obj.f274b = str2;
            obj.f275c = list;
            n.j jVar = f276a;
            ProviderInfo providerInfo = (ProviderInfo) jVar.a(obj);
            if (providerInfo != null) {
                return providerInfo;
            }
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(str, 0);
            if (resolveContentProvider != null) {
                if (resolveContentProvider.packageName.equals(str2)) {
                    Signature[] signatureArr = packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures;
                    ArrayList arrayList = new ArrayList();
                    for (Signature signature : signatureArr) {
                        arrayList.add(signature.toByteArray());
                    }
                    d dVar = f277b;
                    Collections.sort(arrayList, dVar);
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        ArrayList arrayList2 = new ArrayList((Collection) list.get(i2));
                        Collections.sort(arrayList2, dVar);
                        if (arrayList.size() == arrayList2.size()) {
                            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                                if (!Arrays.equals((byte[]) arrayList.get(i3), (byte[]) arrayList2.get(i3))) {
                                    break;
                                }
                            }
                            jVar.b(obj, resolveContentProvider);
                            return resolveContentProvider;
                        }
                    }
                    Trace.endSection();
                    return null;
                }
                throw new PackageManager.NameNotFoundException("Found content provider " + str + ", but package was not " + str2);
            }
            throw new PackageManager.NameNotFoundException("No package found for authority: " + str);
        } finally {
            Trace.endSection();
        }
    }

    public static l[] c(Context context, g gVar, String str) {
        int i2;
        int i3;
        Uri withAppendedId;
        int i4;
        boolean z2;
        C0.f.c("FontProvider.query");
        try {
            ArrayList arrayList = new ArrayList();
            Uri build = new Uri.Builder().scheme("content").authority(str).build();
            Uri build2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
            ContentProviderClient acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(build);
            Cursor cursor = null;
            try {
                String[] strArr = {"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"};
                C0.f.c("ContentQueryWrapper.query");
                try {
                    String[] strArr2 = {gVar.f280c};
                    if (acquireUnstableContentProviderClient != null) {
                        try {
                            cursor = acquireUnstableContentProviderClient.query(build, strArr, "query = ?", strArr2, null, null);
                        } catch (RemoteException e2) {
                            Log.w("FontsProvider", "Unable to query the content provider", e2);
                        }
                    }
                    Trace.endSection();
                    if (cursor != null && cursor.getCount() > 0) {
                        int columnIndex = cursor.getColumnIndex("result_code");
                        ArrayList arrayList2 = new ArrayList();
                        int columnIndex2 = cursor.getColumnIndex("_id");
                        int columnIndex3 = cursor.getColumnIndex("file_id");
                        int columnIndex4 = cursor.getColumnIndex("font_ttc_index");
                        int columnIndex5 = cursor.getColumnIndex("font_weight");
                        int columnIndex6 = cursor.getColumnIndex("font_italic");
                        while (cursor.moveToNext()) {
                            if (columnIndex != -1) {
                                i2 = cursor.getInt(columnIndex);
                            } else {
                                i2 = 0;
                            }
                            if (columnIndex4 != -1) {
                                i3 = cursor.getInt(columnIndex4);
                            } else {
                                i3 = 0;
                            }
                            if (columnIndex3 == -1) {
                                withAppendedId = ContentUris.withAppendedId(build, cursor.getLong(columnIndex2));
                            } else {
                                withAppendedId = ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3));
                            }
                            Uri uri = withAppendedId;
                            if (columnIndex5 != -1) {
                                i4 = cursor.getInt(columnIndex5);
                            } else {
                                i4 = 400;
                            }
                            int i5 = i4;
                            if (columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            arrayList2.add(new l(uri, i3, i5, z2, i2));
                        }
                        arrayList = arrayList2;
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                    return (l[]) arrayList.toArray(new l[0]);
                } finally {
                    Trace.endSection();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (acquireUnstableContentProviderClient != null) {
                    acquireUnstableContentProviderClient.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }
}
