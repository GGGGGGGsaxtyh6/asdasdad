package a;

import C0.m;
import H.C0019m;
import H.C0020n;
import H.C0021o;
import H.InterfaceC0023q;
import a0.C0050d;
import a0.InterfaceC0048b;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Trace;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.AbstractC0068o;
import androidx.lifecycle.B;
import androidx.lifecycle.C0064k;
import androidx.lifecycle.D;
import androidx.lifecycle.EnumC0066m;
import androidx.lifecycle.EnumC0067n;
import androidx.lifecycle.H;
import androidx.lifecycle.InterfaceC0062i;
import androidx.lifecycle.InterfaceC0072t;
import androidx.lifecycle.J;
import androidx.lifecycle.O;
import androidx.lifecycle.P;
import androidx.lifecycle.S;
import androidx.lifecycle.T;
import b.C0073a;
import b.InterfaceC0074b;
import d.AbstractC0097a;
import i0.InterfaceC0131a;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import k0.InterfaceC0215a;
import org.conscrypt.R;

/* renamed from: a.m, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractActivityC0046m extends x.f implements T, InterfaceC0062i, U.g {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private static final C0040g Companion = new Object();
    private S _viewModelStore;
    private final c.j activityResultRegistry;
    private int contentLayoutId;
    private final C0073a contextAwareHelper = new C0073a();
    private final InterfaceC0048b defaultViewModelProviderFactory$delegate;
    private boolean dispatchingOnMultiWindowModeChanged;
    private boolean dispatchingOnPictureInPictureModeChanged;
    private final InterfaceC0048b fullyDrawnReporter$delegate;
    private final C0021o menuHostHelper;
    private final AtomicInteger nextLocalRequestCode;
    private final InterfaceC0048b onBackPressedDispatcher$delegate;
    private final CopyOnWriteArrayList<G.a> onConfigurationChangedListeners;
    private final CopyOnWriteArrayList<G.a> onMultiWindowModeChangedListeners;
    private final CopyOnWriteArrayList<G.a> onNewIntentListeners;
    private final CopyOnWriteArrayList<G.a> onPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList<G.a> onTrimMemoryListeners;
    private final CopyOnWriteArrayList<Runnable> onUserLeaveHintListeners;
    private final InterfaceExecutorC0042i reportFullyDrawnExecutor;
    private final U.f savedStateRegistryController;

    public AbstractActivityC0046m() {
        final androidx.fragment.app.i iVar = (androidx.fragment.app.i) this;
        this.menuHostHelper = new C0021o(new RunnableC0036c(iVar, 0));
        U.f fVar = new U.f(this);
        this.savedStateRegistryController = fVar;
        this.reportFullyDrawnExecutor = new ViewTreeObserverOnDrawListenerC0043j(iVar);
        this.fullyDrawnReporter$delegate = new C0050d(new C0045l(iVar, 2));
        this.nextLocalRequestCode = new AtomicInteger();
        this.activityResultRegistry = new c.j();
        this.onConfigurationChangedListeners = new CopyOnWriteArrayList<>();
        this.onTrimMemoryListeners = new CopyOnWriteArrayList<>();
        this.onNewIntentListeners = new CopyOnWriteArrayList<>();
        this.onMultiWindowModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onPictureInPictureModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onUserLeaveHintListeners = new CopyOnWriteArrayList<>();
        if (getLifecycle() != null) {
            getLifecycle().a(new C0037d(0, iVar));
            int i2 = 1;
            getLifecycle().a(new C0037d(i2, iVar));
            getLifecycle().a(new U.b(i2, iVar));
            fVar.a();
            EnumC0067n enumC0067n = ((androidx.lifecycle.v) getLifecycle()).f1207c;
            if (enumC0067n != EnumC0067n.f1197b && enumC0067n != EnumC0067n.f1198c) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (getSavedStateRegistry().b() == null) {
                J j2 = new J(getSavedStateRegistry(), this);
                getSavedStateRegistry().c("androidx.lifecycle.internal.SavedStateHandlesProvider", j2);
                getLifecycle().a(new U.b(3, j2));
            }
            getSavedStateRegistry().c(ACTIVITY_RESULT_TAG, new C0038e(0, iVar));
            addOnContextAvailableListener(new InterfaceC0074b() { // from class: a.f
                @Override // b.InterfaceC0074b
                public final void a(AbstractActivityC0046m abstractActivityC0046m) {
                    AbstractActivityC0046m.a(androidx.fragment.app.i.this, abstractActivityC0046m);
                }
            });
            this.defaultViewModelProviderFactory$delegate = new C0050d(new C0045l(iVar, 0));
            this.onBackPressedDispatcher$delegate = new C0050d(new C0045l(iVar, 3));
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
    }

    public static void a(androidx.fragment.app.i iVar, AbstractActivityC0046m abstractActivityC0046m) {
        String name;
        AbstractC0150d.e(abstractActivityC0046m, "it");
        Bundle a2 = iVar.getSavedStateRegistry().a(ACTIVITY_RESULT_TAG);
        if (a2 != null) {
            c.j jVar = ((AbstractActivityC0046m) iVar).activityResultRegistry;
            jVar.getClass();
            ArrayList<Integer> integerArrayList = a2.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = a2.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (stringArrayList != null && integerArrayList != null) {
                ArrayList<String> stringArrayList2 = a2.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                if (stringArrayList2 != null) {
                    jVar.f1253d.addAll(stringArrayList2);
                }
                Bundle bundle = a2.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
                Bundle bundle2 = jVar.f1256g;
                if (bundle != null) {
                    bundle2.putAll(bundle);
                }
                int size = stringArrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    String str = stringArrayList.get(i2);
                    LinkedHashMap linkedHashMap = jVar.f1251b;
                    boolean containsKey = linkedHashMap.containsKey(str);
                    LinkedHashMap linkedHashMap2 = jVar.f1250a;
                    if (containsKey) {
                        Integer num = (Integer) linkedHashMap.remove(str);
                        if (bundle2.containsKey(str)) {
                            continue;
                        } else if (!(linkedHashMap2 instanceof InterfaceC0215a)) {
                            linkedHashMap2.remove(num);
                        } else {
                            if (linkedHashMap2 == null) {
                                name = "null";
                            } else {
                                name = linkedHashMap2.getClass().getName();
                            }
                            ClassCastException classCastException = new ClassCastException(name.concat(" cannot be cast to kotlin.collections.MutableMap"));
                            AbstractC0150d.g(classCastException, j0.j.class.getName());
                            throw classCastException;
                        }
                    }
                    Integer num2 = integerArrayList.get(i2);
                    AbstractC0150d.d(num2, "rcs[i]");
                    int intValue = num2.intValue();
                    String str2 = stringArrayList.get(i2);
                    AbstractC0150d.d(str2, "keys[i]");
                    String str3 = str2;
                    linkedHashMap2.put(Integer.valueOf(intValue), str3);
                    linkedHashMap.put(str3, Integer.valueOf(intValue));
                }
            }
        }
    }

    public static final void access$ensureViewModelStore(AbstractActivityC0046m abstractActivityC0046m) {
        if (abstractActivityC0046m._viewModelStore == null) {
            C0041h c0041h = (C0041h) abstractActivityC0046m.getLastNonConfigurationInstance();
            if (c0041h != null) {
                abstractActivityC0046m._viewModelStore = c0041h.f748b;
            }
            if (abstractActivityC0046m._viewModelStore == null) {
                abstractActivityC0046m._viewModelStore = new S();
            }
        }
    }

    public static void b(androidx.fragment.app.i iVar, InterfaceC0072t interfaceC0072t, EnumC0066m enumC0066m) {
        if (enumC0066m == EnumC0066m.ON_DESTROY) {
            ((AbstractActivityC0046m) iVar).contextAwareHelper.f1217b = null;
            if (!iVar.isChangingConfigurations()) {
                iVar.getViewModelStore().a();
            }
            ViewTreeObserverOnDrawListenerC0043j viewTreeObserverOnDrawListenerC0043j = (ViewTreeObserverOnDrawListenerC0043j) ((AbstractActivityC0046m) iVar).reportFullyDrawnExecutor;
            androidx.fragment.app.i iVar2 = viewTreeObserverOnDrawListenerC0043j.f752d;
            iVar2.getWindow().getDecorView().removeCallbacks(viewTreeObserverOnDrawListenerC0043j);
            iVar2.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(viewTreeObserverOnDrawListenerC0043j);
        }
    }

    public static Bundle c(androidx.fragment.app.i iVar) {
        Bundle bundle = new Bundle();
        c.j jVar = ((AbstractActivityC0046m) iVar).activityResultRegistry;
        jVar.getClass();
        LinkedHashMap linkedHashMap = jVar.f1251b;
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(linkedHashMap.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(linkedHashMap.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(jVar.f1253d));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", new Bundle(jVar.f1256g));
        return bundle;
    }

    public void addMenuProvider(InterfaceC0023q interfaceC0023q) {
        AbstractC0150d.e(interfaceC0023q, "provider");
        C0021o c0021o = this.menuHostHelper;
        c0021o.f385b.add(interfaceC0023q);
        c0021o.f384a.run();
    }

    public final void addOnConfigurationChangedListener(G.a aVar) {
        AbstractC0150d.e(aVar, "listener");
        this.onConfigurationChangedListeners.add(aVar);
    }

    public final void addOnContextAvailableListener(InterfaceC0074b interfaceC0074b) {
        AbstractC0150d.e(interfaceC0074b, "listener");
        C0073a c0073a = this.contextAwareHelper;
        c0073a.getClass();
        AbstractActivityC0046m abstractActivityC0046m = c0073a.f1217b;
        if (abstractActivityC0046m != null) {
            interfaceC0074b.a(abstractActivityC0046m);
        }
        c0073a.f1216a.add(interfaceC0074b);
    }

    public final void addOnMultiWindowModeChangedListener(G.a aVar) {
        AbstractC0150d.e(aVar, "listener");
        this.onMultiWindowModeChangedListeners.add(aVar);
    }

    public final void addOnNewIntentListener(G.a aVar) {
        AbstractC0150d.e(aVar, "listener");
        this.onNewIntentListeners.add(aVar);
    }

    public final void addOnPictureInPictureModeChangedListener(G.a aVar) {
        AbstractC0150d.e(aVar, "listener");
        this.onPictureInPictureModeChangedListeners.add(aVar);
    }

    public final void addOnTrimMemoryListener(G.a aVar) {
        AbstractC0150d.e(aVar, "listener");
        this.onTrimMemoryListeners.add(aVar);
    }

    public final void addOnUserLeaveHintListener(Runnable runnable) {
        AbstractC0150d.e(runnable, "listener");
        this.onUserLeaveHintListeners.add(runnable);
    }

    public final c.j getActivityResultRegistry() {
        return this.activityResultRegistry;
    }

    @Override // androidx.lifecycle.InterfaceC0062i
    public Q.b getDefaultViewModelCreationExtras() {
        Bundle bundle;
        Q.c cVar = new Q.c(Q.a.f609b);
        Application application = getApplication();
        LinkedHashMap linkedHashMap = cVar.f610a;
        if (application != null) {
            A.m mVar = O.f1180d;
            Application application2 = getApplication();
            AbstractC0150d.d(application2, "application");
            linkedHashMap.put(mVar, application2);
        }
        linkedHashMap.put(H.f1161a, this);
        linkedHashMap.put(H.f1162b, this);
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getExtras();
        } else {
            bundle = null;
        }
        if (bundle != null) {
            linkedHashMap.put(H.f1163c, bundle);
        }
        return cVar;
    }

    public P getDefaultViewModelProviderFactory() {
        return (P) ((C0050d) this.defaultViewModelProviderFactory$delegate).a();
    }

    public n getFullyDrawnReporter() {
        return (n) ((C0050d) this.fullyDrawnReporter$delegate).a();
    }

    public Object getLastCustomNonConfigurationInstance() {
        C0041h c0041h = (C0041h) getLastNonConfigurationInstance();
        if (c0041h != null) {
            return c0041h.f747a;
        }
        return null;
    }

    @Override // x.f, androidx.lifecycle.InterfaceC0072t
    public AbstractC0068o getLifecycle() {
        return super.getLifecycle();
    }

    public final v getOnBackPressedDispatcher() {
        return (v) ((C0050d) this.onBackPressedDispatcher$delegate).a();
    }

    @Override // U.g
    public final U.e getSavedStateRegistry() {
        return this.savedStateRegistryController.f672b;
    }

    @Override // androidx.lifecycle.T
    public S getViewModelStore() {
        if (getApplication() != null) {
            if (this._viewModelStore == null) {
                C0041h c0041h = (C0041h) getLastNonConfigurationInstance();
                if (c0041h != null) {
                    this._viewModelStore = c0041h.f748b;
                }
                if (this._viewModelStore == null) {
                    this._viewModelStore = new S();
                }
            }
            S s2 = this._viewModelStore;
            AbstractC0150d.b(s2);
            return s2;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    public void initializeViewTreeOwners() {
        View decorView = getWindow().getDecorView();
        AbstractC0150d.d(decorView, "window.decorView");
        decorView.setTag(R.id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        AbstractC0150d.d(decorView2, "window.decorView");
        decorView2.setTag(R.id.view_tree_view_model_store_owner, this);
        View decorView3 = getWindow().getDecorView();
        AbstractC0150d.d(decorView3, "window.decorView");
        decorView3.setTag(R.id.view_tree_saved_state_registry_owner, this);
        View decorView4 = getWindow().getDecorView();
        AbstractC0150d.d(decorView4, "window.decorView");
        decorView4.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
        View decorView5 = getWindow().getDecorView();
        AbstractC0150d.d(decorView5, "window.decorView");
        decorView5.setTag(R.id.report_drawn, this);
    }

    public void invalidateMenu() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (!this.activityResultRegistry.a(i2, i3, intent)) {
            super.onActivityResult(i2, i3, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        getOnBackPressedDispatcher().b();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        AbstractC0150d.e(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        Iterator<G.a> it = this.onConfigurationChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().a(configuration);
        }
    }

    @Override // x.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.savedStateRegistryController.b(bundle);
        C0073a c0073a = this.contextAwareHelper;
        c0073a.getClass();
        c0073a.f1217b = this;
        Iterator it = c0073a.f1216a.iterator();
        while (it.hasNext()) {
            ((InterfaceC0074b) it.next()).a(this);
        }
        super.onCreate(bundle);
        int i2 = D.f1150b;
        B.b(this);
        int i3 = this.contentLayoutId;
        if (i3 != 0) {
            setContentView(i3);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        AbstractC0150d.e(menu, "menu");
        if (i2 == 0) {
            super.onCreatePanelMenu(i2, menu);
            C0021o c0021o = this.menuHostHelper;
            getMenuInflater();
            c0021o.a();
            return true;
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        AbstractC0150d.e(menuItem, "item");
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        if (i2 == 0) {
            this.menuHostHelper.b();
            return false;
        }
        return false;
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z2) {
        if (this.dispatchingOnMultiWindowModeChanged) {
            return;
        }
        Iterator<G.a> it = this.onMultiWindowModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().a(new x.g(z2));
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        AbstractC0150d.e(intent, "intent");
        super.onNewIntent(intent);
        Iterator<G.a> it = this.onNewIntentListeners.iterator();
        while (it.hasNext()) {
            it.next().a(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        AbstractC0150d.e(menu, "menu");
        Iterator it = this.menuHostHelper.f385b.iterator();
        while (it.hasNext()) {
            androidx.fragment.app.t tVar = ((androidx.fragment.app.p) ((InterfaceC0023q) it.next())).f1070a;
            if (tVar.f1099r >= 1) {
                Iterator it2 = tVar.f1085c.c().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        throw new ClassCastException();
                    }
                }
            }
        }
        super.onPanelClosed(i2, menu);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z2) {
        if (this.dispatchingOnPictureInPictureModeChanged) {
            return;
        }
        Iterator<G.a> it = this.onPictureInPictureModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().a(new x.h(z2));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i2, View view, Menu menu) {
        AbstractC0150d.e(menu, "menu");
        if (i2 == 0) {
            super.onPreparePanel(i2, view, menu);
            this.menuHostHelper.c();
            return true;
        }
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        AbstractC0150d.e(strArr, "permissions");
        AbstractC0150d.e(iArr, "grantResults");
        if (!this.activityResultRegistry.a(i2, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
        }
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, a.h] */
    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        C0041h c0041h;
        Object onRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        S s2 = this._viewModelStore;
        if (s2 == null && (c0041h = (C0041h) getLastNonConfigurationInstance()) != null) {
            s2 = c0041h.f748b;
        }
        if (s2 == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        ?? obj = new Object();
        obj.f747a = onRetainCustomNonConfigurationInstance;
        obj.f748b = s2;
        return obj;
    }

    @Override // x.f, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        AbstractC0150d.e(bundle, "outState");
        if (getLifecycle() instanceof androidx.lifecycle.v) {
            AbstractC0068o lifecycle = getLifecycle();
            AbstractC0150d.c(lifecycle, "null cannot be cast to non-null type androidx.lifecycle.LifecycleRegistry");
            androidx.lifecycle.v vVar = (androidx.lifecycle.v) lifecycle;
            EnumC0067n enumC0067n = EnumC0067n.f1198c;
            vVar.d("setCurrentState");
            vVar.f(enumC0067n);
        }
        super.onSaveInstanceState(bundle);
        this.savedStateRegistryController.c(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i2) {
        super.onTrimMemory(i2);
        Iterator<G.a> it = this.onTrimMemoryListeners.iterator();
        while (it.hasNext()) {
            it.next().a(Integer.valueOf(i2));
        }
    }

    @Override // android.app.Activity
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        Iterator<Runnable> it = this.onUserLeaveHintListeners.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
    }

    public Context peekAvailableContext() {
        return this.contextAwareHelper.f1217b;
    }

    /* JADX WARN: Type inference failed for: r6v6, types: [c.c, java.lang.Object] */
    public final <I, O> c.c registerForActivityResult(final AbstractC0097a abstractC0097a, final c.j jVar, final c.b bVar) {
        AbstractC0150d.e(abstractC0097a, "contract");
        AbstractC0150d.e(jVar, "registry");
        AbstractC0150d.e(bVar, "callback");
        final String str = "activity_rq#" + this.nextLocalRequestCode.getAndIncrement();
        AbstractC0150d.e(str, "key");
        AbstractC0068o lifecycle = getLifecycle();
        androidx.lifecycle.v vVar = (androidx.lifecycle.v) lifecycle;
        if (vVar.f1207c.compareTo(EnumC0067n.f1199d) < 0) {
            jVar.c(str);
            LinkedHashMap linkedHashMap = jVar.f1252c;
            c.f fVar = (c.f) linkedHashMap.get(str);
            if (fVar == null) {
                fVar = new c.f(lifecycle);
            }
            androidx.lifecycle.r rVar = new androidx.lifecycle.r() { // from class: c.d
                @Override // androidx.lifecycle.r
                public final void f(InterfaceC0072t interfaceC0072t, EnumC0066m enumC0066m) {
                    EnumC0066m enumC0066m2 = EnumC0066m.ON_START;
                    j jVar2 = j.this;
                    String str2 = str;
                    LinkedHashMap linkedHashMap2 = jVar2.f1254e;
                    if (enumC0066m2 == enumC0066m) {
                        b bVar2 = bVar;
                        AbstractC0097a abstractC0097a2 = abstractC0097a;
                        linkedHashMap2.put(str2, new e(abstractC0097a2, bVar2));
                        LinkedHashMap linkedHashMap3 = jVar2.f1255f;
                        if (linkedHashMap3.containsKey(str2)) {
                            Object obj = linkedHashMap3.get(str2);
                            linkedHashMap3.remove(str2);
                            bVar2.a(obj);
                        }
                        Bundle bundle = jVar2.f1256g;
                        C0095a c0095a = (C0095a) m.j(str2, bundle);
                        if (c0095a != null) {
                            bundle.remove(str2);
                            bVar2.a(abstractC0097a2.a(c0095a.f1238b, c0095a.f1237a));
                            return;
                        }
                        return;
                    }
                    if (EnumC0066m.ON_STOP == enumC0066m) {
                        linkedHashMap2.remove(str2);
                    } else if (EnumC0066m.ON_DESTROY == enumC0066m) {
                        jVar2.d(str2);
                    }
                }
            };
            fVar.f1245a.a(rVar);
            fVar.f1246b.add(rVar);
            linkedHashMap.put(str, fVar);
            return new Object();
        }
        throw new IllegalStateException(("LifecycleOwner " + this + " is attempting to register while current state is " + vVar.f1207c + ". LifecycleOwners must call register before they are STARTED.").toString());
    }

    public void removeMenuProvider(InterfaceC0023q interfaceC0023q) {
        AbstractC0150d.e(interfaceC0023q, "provider");
        this.menuHostHelper.d(interfaceC0023q);
    }

    public final void removeOnConfigurationChangedListener(G.a aVar) {
        AbstractC0150d.e(aVar, "listener");
        this.onConfigurationChangedListeners.remove(aVar);
    }

    public final void removeOnContextAvailableListener(InterfaceC0074b interfaceC0074b) {
        AbstractC0150d.e(interfaceC0074b, "listener");
        C0073a c0073a = this.contextAwareHelper;
        c0073a.getClass();
        c0073a.f1216a.remove(interfaceC0074b);
    }

    public final void removeOnMultiWindowModeChangedListener(G.a aVar) {
        AbstractC0150d.e(aVar, "listener");
        this.onMultiWindowModeChangedListeners.remove(aVar);
    }

    public final void removeOnNewIntentListener(G.a aVar) {
        AbstractC0150d.e(aVar, "listener");
        this.onNewIntentListeners.remove(aVar);
    }

    public final void removeOnPictureInPictureModeChangedListener(G.a aVar) {
        AbstractC0150d.e(aVar, "listener");
        this.onPictureInPictureModeChangedListeners.remove(aVar);
    }

    public final void removeOnTrimMemoryListener(G.a aVar) {
        AbstractC0150d.e(aVar, "listener");
        this.onTrimMemoryListeners.remove(aVar);
    }

    public final void removeOnUserLeaveHintListener(Runnable runnable) {
        AbstractC0150d.e(runnable, "listener");
        this.onUserLeaveHintListeners.remove(runnable);
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
        try {
            if (C0.f.u()) {
                C0.f.c("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            n fullyDrawnReporter = getFullyDrawnReporter();
            synchronized (fullyDrawnReporter.f755a) {
                try {
                    fullyDrawnReporter.f756b = true;
                    Iterator it = fullyDrawnReporter.f757c.iterator();
                    while (it.hasNext()) {
                        ((InterfaceC0131a) it.next()).a();
                    }
                    fullyDrawnReporter.f757c.clear();
                } finally {
                }
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // android.app.Activity
    public abstract void setContentView(int i2);

    @Override // android.app.Activity
    public void setContentView(View view) {
        initializeViewTreeOwners();
        InterfaceExecutorC0042i interfaceExecutorC0042i = this.reportFullyDrawnExecutor;
        View decorView = getWindow().getDecorView();
        AbstractC0150d.d(decorView, "window.decorView");
        ViewTreeObserverOnDrawListenerC0043j viewTreeObserverOnDrawListenerC0043j = (ViewTreeObserverOnDrawListenerC0043j) interfaceExecutorC0042i;
        viewTreeObserverOnDrawListenerC0043j.getClass();
        if (!viewTreeObserverOnDrawListenerC0043j.f751c) {
            viewTreeObserverOnDrawListenerC0043j.f751c = true;
            decorView.getViewTreeObserver().addOnDrawListener(viewTreeObserverOnDrawListenerC0043j);
        }
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i2) {
        AbstractC0150d.e(intent, "intent");
        super.startActivityForResult(intent, i2);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5) {
        AbstractC0150d.e(intentSender, "intent");
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        AbstractC0150d.e(intent, "intent");
        super.startActivityForResult(intent, i2, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        AbstractC0150d.e(intentSender, "intent");
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z2, Configuration configuration) {
        AbstractC0150d.e(configuration, "newConfig");
        this.dispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(z2, configuration);
            this.dispatchingOnMultiWindowModeChanged = false;
            Iterator<G.a> it = this.onMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().a(new x.g(z2));
            }
        } catch (Throwable th) {
            this.dispatchingOnMultiWindowModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z2, Configuration configuration) {
        AbstractC0150d.e(configuration, "newConfig");
        this.dispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(z2, configuration);
            this.dispatchingOnPictureInPictureModeChanged = false;
            Iterator<G.a> it = this.onPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().a(new x.h(z2));
            }
        } catch (Throwable th) {
            this.dispatchingOnPictureInPictureModeChanged = false;
            throw th;
        }
    }

    public void addMenuProvider(InterfaceC0023q interfaceC0023q, InterfaceC0072t interfaceC0072t) {
        AbstractC0150d.e(interfaceC0023q, "provider");
        AbstractC0150d.e(interfaceC0072t, "owner");
        C0021o c0021o = this.menuHostHelper;
        c0021o.f385b.add(interfaceC0023q);
        c0021o.f384a.run();
        AbstractC0068o lifecycle = interfaceC0072t.getLifecycle();
        HashMap hashMap = c0021o.f386c;
        C0020n c0020n = (C0020n) hashMap.remove(interfaceC0023q);
        if (c0020n != null) {
            c0020n.f382a.b(c0020n.f383b);
            c0020n.f383b = null;
        }
        hashMap.put(interfaceC0023q, new C0020n(lifecycle, new C0019m(c0021o, interfaceC0023q, 0)));
    }

    public void addMenuProvider(final InterfaceC0023q interfaceC0023q, InterfaceC0072t interfaceC0072t, final EnumC0067n enumC0067n) {
        AbstractC0150d.e(interfaceC0023q, "provider");
        AbstractC0150d.e(interfaceC0072t, "owner");
        AbstractC0150d.e(enumC0067n, "state");
        final C0021o c0021o = this.menuHostHelper;
        c0021o.getClass();
        AbstractC0068o lifecycle = interfaceC0072t.getLifecycle();
        HashMap hashMap = c0021o.f386c;
        C0020n c0020n = (C0020n) hashMap.remove(interfaceC0023q);
        if (c0020n != null) {
            c0020n.f382a.b(c0020n.f383b);
            c0020n.f383b = null;
        }
        hashMap.put(interfaceC0023q, new C0020n(lifecycle, new androidx.lifecycle.r() { // from class: H.l
            @Override // androidx.lifecycle.r
            public final void f(InterfaceC0072t interfaceC0072t2, EnumC0066m enumC0066m) {
                EnumC0066m enumC0066m2;
                C0021o c0021o2 = C0021o.this;
                c0021o2.getClass();
                EnumC0066m.Companion.getClass();
                EnumC0067n enumC0067n2 = enumC0067n;
                int ordinal = enumC0067n2.ordinal();
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        if (ordinal != 4) {
                            enumC0066m2 = null;
                        } else {
                            enumC0066m2 = EnumC0066m.ON_RESUME;
                        }
                    } else {
                        enumC0066m2 = EnumC0066m.ON_START;
                    }
                } else {
                    enumC0066m2 = EnumC0066m.ON_CREATE;
                }
                InterfaceC0023q interfaceC0023q2 = interfaceC0023q;
                Runnable runnable = c0021o2.f384a;
                CopyOnWriteArrayList copyOnWriteArrayList = c0021o2.f385b;
                if (enumC0066m == enumC0066m2) {
                    copyOnWriteArrayList.add(interfaceC0023q2);
                    runnable.run();
                } else if (enumC0066m == EnumC0066m.ON_DESTROY) {
                    c0021o2.d(interfaceC0023q2);
                } else if (enumC0066m == C0064k.a(enumC0067n2)) {
                    copyOnWriteArrayList.remove(interfaceC0023q2);
                    runnable.run();
                }
            }
        }));
    }

    public final <I, O> c.c registerForActivityResult(AbstractC0097a abstractC0097a, c.b bVar) {
        AbstractC0150d.e(abstractC0097a, "contract");
        AbstractC0150d.e(bVar, "callback");
        return registerForActivityResult(abstractC0097a, this.activityResultRegistry, bVar);
    }

    public static /* synthetic */ void getOnBackPressedDispatcher$annotations() {
    }
}
