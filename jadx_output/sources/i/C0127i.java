package i;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SubMenu;
import f.AbstractC0101a;
import j.q;
import java.io.IOException;
import k.AbstractC0191n0;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: i.i, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0127i extends MenuInflater {

    /* renamed from: e, reason: collision with root package name */
    public static final Class[] f1556e;

    /* renamed from: f, reason: collision with root package name */
    public static final Class[] f1557f;

    /* renamed from: a, reason: collision with root package name */
    public final Object[] f1558a;

    /* renamed from: b, reason: collision with root package name */
    public final Object[] f1559b;

    /* renamed from: c, reason: collision with root package name */
    public final Context f1560c;

    /* renamed from: d, reason: collision with root package name */
    public Object f1561d;

    static {
        Class[] clsArr = {Context.class};
        f1556e = clsArr;
        f1557f = clsArr;
    }

    public C0127i(Context context) {
        super(context);
        this.f1560c = context;
        Object[] objArr = {context};
        this.f1558a = objArr;
        this.f1559b = objArr;
    }

    public static Object a(Object obj) {
        if (obj instanceof Activity) {
            return obj;
        }
        if (obj instanceof ContextWrapper) {
            return a(((ContextWrapper) obj).getBaseContext());
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r4v60 */
    public final void b(XmlResourceParser xmlResourceParser, AttributeSet attributeSet, Menu menu) {
        ?? r4;
        int i2;
        boolean z2;
        char charAt;
        char charAt2;
        boolean z3;
        ColorStateList colorStateList;
        int resourceId;
        C0126h c0126h = new C0126h(this, menu);
        int eventType = xmlResourceParser.getEventType();
        while (true) {
            r4 = 1;
            i2 = 2;
            if (eventType == 2) {
                String name = xmlResourceParser.getName();
                if (name.equals("menu")) {
                    eventType = xmlResourceParser.next();
                } else {
                    throw new RuntimeException("Expecting menu, got ".concat(name));
                }
            } else {
                eventType = xmlResourceParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        boolean z4 = false;
        boolean z5 = false;
        String str = null;
        while (!z4) {
            if (eventType != r4) {
                if (eventType != i2) {
                    if (eventType == 3) {
                        String name2 = xmlResourceParser.getName();
                        if (z5 && name2.equals(str)) {
                            z2 = r4;
                            z5 = false;
                            str = null;
                            eventType = xmlResourceParser.next();
                            r4 = z2;
                            i2 = 2;
                            z5 = z5;
                        } else if (name2.equals("group")) {
                            c0126h.f1533b = 0;
                            c0126h.f1534c = 0;
                            c0126h.f1535d = 0;
                            c0126h.f1536e = 0;
                            c0126h.f1537f = r4;
                            c0126h.f1538g = r4;
                        } else if (name2.equals("item")) {
                            if (!c0126h.h) {
                                q qVar = c0126h.f1555z;
                                if (qVar != null && qVar.f1712b.hasSubMenu()) {
                                    c0126h.h = r4;
                                    c0126h.b(c0126h.f1532a.addSubMenu(c0126h.f1533b, c0126h.f1539i, c0126h.f1540j, c0126h.f1541k).getItem());
                                } else {
                                    c0126h.h = r4;
                                    c0126h.b(c0126h.f1532a.add(c0126h.f1533b, c0126h.f1539i, c0126h.f1540j, c0126h.f1541k));
                                }
                            }
                        } else if (name2.equals("menu")) {
                            z2 = r4;
                            z4 = z2;
                        }
                    }
                    z2 = r4;
                } else {
                    if (!z5) {
                        String name3 = xmlResourceParser.getName();
                        boolean equals = name3.equals("group");
                        C0127i c0127i = c0126h.f1531E;
                        if (equals) {
                            TypedArray obtainStyledAttributes = c0127i.f1560c.obtainStyledAttributes(attributeSet, AbstractC0101a.f1276p);
                            c0126h.f1533b = obtainStyledAttributes.getResourceId(r4, 0);
                            c0126h.f1534c = obtainStyledAttributes.getInt(3, 0);
                            c0126h.f1535d = obtainStyledAttributes.getInt(4, 0);
                            c0126h.f1536e = obtainStyledAttributes.getInt(5, 0);
                            c0126h.f1537f = obtainStyledAttributes.getBoolean(2, r4);
                            c0126h.f1538g = obtainStyledAttributes.getBoolean(0, r4);
                            obtainStyledAttributes.recycle();
                        } else {
                            if (name3.equals("item")) {
                                Context context = c0127i.f1560c;
                                TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, AbstractC0101a.f1277q);
                                c0126h.f1539i = obtainStyledAttributes2.getResourceId(2, 0);
                                c0126h.f1540j = (obtainStyledAttributes2.getInt(5, c0126h.f1534c) & (-65536)) | (obtainStyledAttributes2.getInt(6, c0126h.f1535d) & 65535);
                                c0126h.f1541k = obtainStyledAttributes2.getText(7);
                                c0126h.f1542l = obtainStyledAttributes2.getText(8);
                                c0126h.f1543m = obtainStyledAttributes2.getResourceId(0, 0);
                                String string = obtainStyledAttributes2.getString(9);
                                if (string == null) {
                                    charAt = 0;
                                } else {
                                    charAt = string.charAt(0);
                                }
                                c0126h.f1544n = charAt;
                                c0126h.f1545o = obtainStyledAttributes2.getInt(16, 4096);
                                String string2 = obtainStyledAttributes2.getString(10);
                                if (string2 == null) {
                                    charAt2 = 0;
                                } else {
                                    charAt2 = string2.charAt(0);
                                }
                                c0126h.f1546p = charAt2;
                                c0126h.f1547q = obtainStyledAttributes2.getInt(20, 4096);
                                if (obtainStyledAttributes2.hasValue(11)) {
                                    c0126h.f1548r = obtainStyledAttributes2.getBoolean(11, false) ? 1 : 0;
                                } else {
                                    c0126h.f1548r = c0126h.f1536e;
                                }
                                c0126h.f1549s = obtainStyledAttributes2.getBoolean(3, false);
                                c0126h.f1550t = obtainStyledAttributes2.getBoolean(4, c0126h.f1537f);
                                c0126h.f1551u = obtainStyledAttributes2.getBoolean(1, c0126h.f1538g);
                                c0126h.f1552v = obtainStyledAttributes2.getInt(21, -1);
                                c0126h.y = obtainStyledAttributes2.getString(12);
                                c0126h.f1553w = obtainStyledAttributes2.getResourceId(13, 0);
                                c0126h.f1554x = obtainStyledAttributes2.getString(15);
                                String string3 = obtainStyledAttributes2.getString(14);
                                if (string3 != null) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                if (z3 && c0126h.f1553w == 0 && c0126h.f1554x == null) {
                                    c0126h.f1555z = (q) c0126h.a(string3, f1557f, c0127i.f1559b);
                                } else {
                                    if (z3) {
                                        Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                                    }
                                    c0126h.f1555z = null;
                                }
                                c0126h.f1527A = obtainStyledAttributes2.getText(17);
                                c0126h.f1528B = obtainStyledAttributes2.getText(22);
                                if (obtainStyledAttributes2.hasValue(19)) {
                                    c0126h.f1530D = AbstractC0191n0.b(obtainStyledAttributes2.getInt(19, -1), c0126h.f1530D);
                                } else {
                                    c0126h.f1530D = null;
                                }
                                if (obtainStyledAttributes2.hasValue(18)) {
                                    if (!obtainStyledAttributes2.hasValue(18) || (resourceId = obtainStyledAttributes2.getResourceId(18, 0)) == 0 || (colorStateList = C0.d.t(context, resourceId)) == null) {
                                        colorStateList = obtainStyledAttributes2.getColorStateList(18);
                                    }
                                    c0126h.f1529C = colorStateList;
                                } else {
                                    c0126h.f1529C = null;
                                }
                                obtainStyledAttributes2.recycle();
                                c0126h.h = false;
                                z2 = true;
                            } else if (name3.equals("menu")) {
                                z2 = true;
                                c0126h.h = true;
                                SubMenu addSubMenu = c0126h.f1532a.addSubMenu(c0126h.f1533b, c0126h.f1539i, c0126h.f1540j, c0126h.f1541k);
                                c0126h.b(addSubMenu.getItem());
                                b(xmlResourceParser, attributeSet, addSubMenu);
                            } else {
                                z2 = true;
                                str = name3;
                                z5 = true;
                            }
                            eventType = xmlResourceParser.next();
                            r4 = z2;
                            i2 = 2;
                            z5 = z5;
                        }
                    }
                    z2 = r4;
                }
                eventType = xmlResourceParser.next();
                r4 = z2;
                i2 = 2;
                z5 = z5;
            } else {
                throw new RuntimeException("Unexpected end of document");
            }
        }
    }

    @Override // android.view.MenuInflater
    public final void inflate(int i2, Menu menu) {
        if (!(menu instanceof j.n)) {
            super.inflate(i2, menu);
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        boolean z2 = false;
        try {
            try {
                xmlResourceParser = this.f1560c.getResources().getLayout(i2);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xmlResourceParser);
                if (menu instanceof j.n) {
                    j.n nVar = (j.n) menu;
                    if (!nVar.f1672p) {
                        nVar.w();
                        z2 = true;
                    }
                }
                b(xmlResourceParser, asAttributeSet, menu);
                if (z2) {
                    ((j.n) menu).v();
                }
                xmlResourceParser.close();
            } catch (IOException e2) {
                throw new InflateException("Error inflating menu XML", e2);
            } catch (XmlPullParserException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } catch (Throwable th) {
            if (z2) {
                ((j.n) menu).v();
            }
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }
}
