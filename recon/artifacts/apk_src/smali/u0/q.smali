.class public final Lu0/q;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Cloneable;


# static fields
.field public static final A:Ljava/util/List;

.field public static final z:Ljava/util/List;


# instance fields
.field public final a:LM/v;

.field public final b:LA/f;

.field public final c:Ljava/util/List;

.field public final d:Ljava/util/List;

.field public final e:LH/h;

.field public final f:Z

.field public final g:Lu0/b;

.field public final h:Z

.field public final i:Z

.field public final j:Lu0/b;

.field public final k:Lu0/b;

.field public final l:Ljava/net/ProxySelector;

.field public final m:Lu0/b;

.field public final n:Ljavax/net/SocketFactory;

.field public final o:Ljavax/net/ssl/SSLSocketFactory;

.field public final p:Ljavax/net/ssl/X509TrustManager;

.field public final q:Ljava/util/List;

.field public final r:Ljava/util/List;

.field public final s:Ljavax/net/ssl/HostnameVerifier;

.field public final t:Lu0/e;

.field public final u:LC0/f;

.field public final v:I

.field public final w:I

.field public final x:I

.field public final y:Lx0/c;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    sget-object v0, Lu0/r;->e:Lu0/r;

    .line 2
    .line 3
    sget-object v1, Lu0/r;->c:Lu0/r;

    .line 4
    .line 5
    filled-new-array {v0, v1}, [Lu0/r;

    .line 6
    .line 7
    .line 8
    move-result-object v0

    .line 9
    invoke-static {v0}, Lv0/b;->k([Ljava/lang/Object;)Ljava/util/List;

    .line 10
    .line 11
    .line 12
    move-result-object v0

    .line 13
    sput-object v0, Lu0/q;->z:Ljava/util/List;

    .line 14
    .line 15
    sget-object v0, Lu0/h;->e:Lu0/h;

    .line 16
    .line 17
    sget-object v1, Lu0/h;->f:Lu0/h;

    .line 18
    .line 19
    filled-new-array {v0, v1}, [Lu0/h;

    .line 20
    .line 21
    .line 22
    move-result-object v0

    .line 23
    invoke-static {v0}, Lv0/b;->k([Ljava/lang/Object;)Ljava/util/List;

    .line 24
    .line 25
    .line 26
    move-result-object v0

    .line 27
    sput-object v0, Lu0/q;->A:Ljava/util/List;

    .line 28
    .line 29
    return-void
    .line 30
    .line 31
    .line 32
    .line 33
    .line 34
    .line 35
    .line 36
    .line 37
    .line 38
    .line 39
    .line 40
    .line 41
    .line 42
    .line 43
    .line 44
    .line 45
    .line 46
    .line 47
    .line 48
    .line 49
    .line 50
    .line 51
    .line 52
    .line 53
    .line 54
    .line 55
    .line 56
    .line 57
    .line 58
    .line 59
    .line 60
    .line 61
    .line 62
    .line 63
    .line 64
    .line 65
    .line 66
    .line 67
    .line 68
    .line 69
    .line 70
    .line 71
    .line 72
    .line 73
    .line 74
    .line 75
    .line 76
    .line 77
    .line 78
    .line 79
    .line 80
    .line 81
    .line 82
    .line 83
    .line 84
    .line 85
    .line 86
    .line 87
    .line 88
    .line 89
.end method

.method public constructor <init>()V
    .locals 1

    .line 91
    new-instance v0, Lu0/p;

    invoke-direct {v0}, Lu0/p;-><init>()V

    invoke-direct {p0, v0}, Lu0/q;-><init>(Lu0/p;)V

    return-void
.end method

.method public constructor <init>(Lu0/p;)V
    .locals 4

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2
    iget-object v0, p1, Lu0/p;->a:LM/v;

    .line 3
    iput-object v0, p0, Lu0/q;->a:LM/v;

    .line 4
    iget-object v0, p1, Lu0/p;->b:LA/f;

    .line 5
    iput-object v0, p0, Lu0/q;->b:LA/f;

    .line 6
    iget-object v0, p1, Lu0/p;->c:Ljava/util/ArrayList;

    .line 7
    invoke-static {v0}, Lv0/b;->w(Ljava/util/List;)Ljava/util/List;

    move-result-object v0

    iput-object v0, p0, Lu0/q;->c:Ljava/util/List;

    .line 8
    iget-object v0, p1, Lu0/p;->d:Ljava/util/ArrayList;

    .line 9
    invoke-static {v0}, Lv0/b;->w(Ljava/util/List;)Ljava/util/List;

    move-result-object v0

    iput-object v0, p0, Lu0/q;->d:Ljava/util/List;

    .line 10
    iget-object v0, p1, Lu0/p;->e:LH/h;

    .line 11
    iput-object v0, p0, Lu0/q;->e:LH/h;

    .line 12
    iget-boolean v0, p1, Lu0/p;->f:Z

    .line 13
    iput-boolean v0, p0, Lu0/q;->f:Z

    .line 14
    iget-object v0, p1, Lu0/p;->g:Lu0/b;

    .line 15
    iput-object v0, p0, Lu0/q;->g:Lu0/b;

    .line 16
    iget-boolean v0, p1, Lu0/p;->h:Z

    .line 17
    iput-boolean v0, p0, Lu0/q;->h:Z

    .line 18
    iget-boolean v0, p1, Lu0/p;->i:Z

    .line 19
    iput-boolean v0, p0, Lu0/q;->i:Z

    .line 20
    iget-object v0, p1, Lu0/p;->j:Lu0/b;

    .line 21
    iput-object v0, p0, Lu0/q;->j:Lu0/b;

    .line 22
    iget-object v0, p1, Lu0/p;->k:Lu0/b;

    .line 23
    iput-object v0, p0, Lu0/q;->k:Lu0/b;

    .line 24
    invoke-static {}, Ljava/net/ProxySelector;->getDefault()Ljava/net/ProxySelector;

    move-result-object v0

    if-nez v0, :cond_0

    sget-object v0, LE0/a;->a:LE0/a;

    .line 25
    :cond_0
    iput-object v0, p0, Lu0/q;->l:Ljava/net/ProxySelector;

    .line 26
    iget-object v0, p1, Lu0/p;->l:Lu0/b;

    .line 27
    iput-object v0, p0, Lu0/q;->m:Lu0/b;

    .line 28
    iget-object v0, p1, Lu0/p;->m:Ljavax/net/SocketFactory;

    .line 29
    iput-object v0, p0, Lu0/q;->n:Ljavax/net/SocketFactory;

    .line 30
    iget-object v0, p1, Lu0/p;->p:Ljava/util/List;

    .line 31
    iput-object v0, p0, Lu0/q;->q:Ljava/util/List;

    .line 32
    iget-object v1, p1, Lu0/p;->q:Ljava/util/List;

    .line 33
    iput-object v1, p0, Lu0/q;->r:Ljava/util/List;

    .line 34
    iget-object v1, p1, Lu0/p;->r:Ljavax/net/ssl/HostnameVerifier;

    .line 35
    iput-object v1, p0, Lu0/q;->s:Ljavax/net/ssl/HostnameVerifier;

    .line 36
    iget v1, p1, Lu0/p;->u:I

    .line 37
    iput v1, p0, Lu0/q;->v:I

    .line 38
    iget v1, p1, Lu0/p;->v:I

    .line 39
    iput v1, p0, Lu0/q;->w:I

    .line 40
    iget v1, p1, Lu0/p;->w:I

    .line 41
    iput v1, p0, Lu0/q;->x:I

    .line 42
    new-instance v1, Lx0/c;

    invoke-direct {v1}, Lx0/c;-><init>()V

    iput-object v1, p0, Lu0/q;->y:Lx0/c;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 43
    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_1

    goto/16 :goto_2

    .line 44
    :cond_1
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_2
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_6

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lu0/h;

    .line 45
    iget-boolean v2, v2, Lu0/h;->a:Z

    if-eqz v2, :cond_2

    .line 46
    iget-object v0, p1, Lu0/p;->n:Ljavax/net/ssl/SSLSocketFactory;

    if-eqz v0, :cond_4

    .line 47
    iput-object v0, p0, Lu0/q;->o:Ljavax/net/ssl/SSLSocketFactory;

    .line 48
    iget-object v0, p1, Lu0/p;->t:LC0/f;

    .line 49
    invoke-static {v0}, Lj0/d;->b(Ljava/lang/Object;)V

    iput-object v0, p0, Lu0/q;->u:LC0/f;

    .line 50
    iget-object v2, p1, Lu0/p;->o:Ljavax/net/ssl/X509TrustManager;

    .line 51
    invoke-static {v2}, Lj0/d;->b(Ljava/lang/Object;)V

    iput-object v2, p0, Lu0/q;->p:Ljavax/net/ssl/X509TrustManager;

    .line 52
    iget-object p1, p1, Lu0/p;->s:Lu0/e;

    .line 53
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 54
    iget-object v2, p1, Lu0/e;->b:LC0/f;

    invoke-static {v2, v0}, Lj0/d;->a(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_3

    goto :goto_0

    .line 55
    :cond_3
    new-instance v2, Lu0/e;

    iget-object p1, p1, Lu0/e;->a:Ljava/util/Set;

    invoke-direct {v2, p1, v0}, Lu0/e;-><init>(Ljava/util/Set;LC0/f;)V

    move-object p1, v2

    .line 56
    :goto_0
    iput-object p1, p0, Lu0/q;->t:Lu0/e;

    goto :goto_3

    .line 57
    :cond_4
    sget-object v0, LC0/o;->a:LC0/o;

    .line 58
    sget-object v0, LC0/o;->a:LC0/o;

    .line 59
    invoke-virtual {v0}, LC0/o;->m()Ljavax/net/ssl/X509TrustManager;

    move-result-object v0

    iput-object v0, p0, Lu0/q;->p:Ljavax/net/ssl/X509TrustManager;

    .line 60
    sget-object v2, LC0/o;->a:LC0/o;

    .line 61
    invoke-virtual {v2, v0}, LC0/o;->l(Ljavax/net/ssl/X509TrustManager;)Ljavax/net/ssl/SSLSocketFactory;

    move-result-object v2

    iput-object v2, p0, Lu0/q;->o:Ljavax/net/ssl/SSLSocketFactory;

    .line 62
    sget-object v2, LC0/o;->a:LC0/o;

    .line 63
    invoke-virtual {v2, v0}, LC0/o;->b(Ljavax/net/ssl/X509TrustManager;)LC0/f;

    move-result-object v0

    .line 64
    iput-object v0, p0, Lu0/q;->u:LC0/f;

    .line 65
    iget-object p1, p1, Lu0/p;->s:Lu0/e;

    .line 66
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 67
    iget-object v2, p1, Lu0/e;->b:LC0/f;

    invoke-static {v2, v0}, Lj0/d;->a(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_5

    goto :goto_1

    .line 68
    :cond_5
    new-instance v2, Lu0/e;

    iget-object p1, p1, Lu0/e;->a:Ljava/util/Set;

    invoke-direct {v2, p1, v0}, Lu0/e;-><init>(Ljava/util/Set;LC0/f;)V

    move-object p1, v2

    .line 69
    :goto_1
    iput-object p1, p0, Lu0/q;->t:Lu0/e;

    goto :goto_3

    .line 70
    :cond_6
    :goto_2
    iput-object v1, p0, Lu0/q;->o:Ljavax/net/ssl/SSLSocketFactory;

    .line 71
    iput-object v1, p0, Lu0/q;->u:LC0/f;

    .line 72
    iput-object v1, p0, Lu0/q;->p:Ljavax/net/ssl/X509TrustManager;

    .line 73
    sget-object p1, Lu0/e;->c:Lu0/e;

    iput-object p1, p0, Lu0/q;->t:Lu0/e;

    .line 74
    :goto_3
    iget-object p1, p0, Lu0/q;->c:Ljava/util/List;

    invoke-interface {p1, v1}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_12

    .line 75
    iget-object p1, p0, Lu0/q;->d:Ljava/util/List;

    invoke-interface {p1, v1}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_11

    .line 76
    iget-object p1, p0, Lu0/q;->p:Ljavax/net/ssl/X509TrustManager;

    iget-object v0, p0, Lu0/q;->u:LC0/f;

    iget-object v1, p0, Lu0/q;->o:Ljavax/net/ssl/SSLSocketFactory;

    iget-object v2, p0, Lu0/q;->q:Ljava/util/List;

    if-eqz v2, :cond_7

    invoke-interface {v2}, Ljava/util/Collection;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_7

    goto :goto_4

    .line 77
    :cond_7
    invoke-interface {v2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_8
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_c

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lu0/h;

    .line 78
    iget-boolean v3, v3, Lu0/h;->a:Z

    if-eqz v3, :cond_8

    if-eqz v1, :cond_b

    if-eqz v0, :cond_a

    if-eqz p1, :cond_9

    goto :goto_5

    .line 79
    :cond_9
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "x509TrustManager == null"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 80
    :cond_a
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "certificateChainCleaner == null"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 81
    :cond_b
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "sslSocketFactory == null"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 82
    :cond_c
    :goto_4
    const-string v2, "Check failed."

    if-nez v1, :cond_10

    if-nez v0, :cond_f

    if-nez p1, :cond_e

    .line 83
    iget-object p1, p0, Lu0/q;->t:Lu0/e;

    sget-object v0, Lu0/e;->c:Lu0/e;

    invoke-static {p1, v0}, Lj0/d;->a(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_d

    :goto_5
    return-void

    :cond_d
    new-instance p1, Ljava/lang/IllegalStateException;

    invoke-direct {p1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 84
    :cond_e
    new-instance p1, Ljava/lang/IllegalStateException;

    invoke-direct {p1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 85
    :cond_f
    new-instance p1, Ljava/lang/IllegalStateException;

    invoke-direct {p1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 86
    :cond_10
    new-instance p1, Ljava/lang/IllegalStateException;

    invoke-direct {p1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 87
    :cond_11
    const-string v0, "Null network interceptor: "

    invoke-static {p1, v0}, Lj0/d;->h(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 88
    new-instance v0, Ljava/lang/IllegalStateException;

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 89
    :cond_12
    const-string v0, "Null interceptor: "

    invoke-static {p1, v0}, Lj0/d;->h(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 90
    new-instance v0, Ljava/lang/IllegalStateException;

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method


# virtual methods
.method public final clone()Ljava/lang/Object;
    .locals 1

    .line 1
    invoke-super {p0}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    .line 2
    .line 3
    .line 4
    move-result-object v0

    .line 5
    return-object v0
    .line 6
    .line 7
    .line 8
    .line 9
    .line 10
    .line 11
    .line 12
    .line 13
    .line 14
    .line 15
    .line 16
    .line 17
    .line 18
    .line 19
    .line 20
    .line 21
    .line 22
    .line 23
    .line 24
    .line 25
    .line 26
    .line 27
    .line 28
    .line 29
    .line 30
    .line 31
    .line 32
    .line 33
    .line 34
    .line 35
    .line 36
    .line 37
    .line 38
    .line 39
    .line 40
    .line 41
    .line 42
    .line 43
    .line 44
    .line 45
    .line 46
    .line 47
    .line 48
    .line 49
    .line 50
    .line 51
    .line 52
    .line 53
    .line 54
    .line 55
    .line 56
    .line 57
    .line 58
    .line 59
    .line 60
    .line 61
    .line 62
    .line 63
    .line 64
    .line 65
    .line 66
    .line 67
    .line 68
    .line 69
    .line 70
    .line 71
    .line 72
    .line 73
    .line 74
    .line 75
    .line 76
    .line 77
    .line 78
    .line 79
    .line 80
    .line 81
    .line 82
    .line 83
    .line 84
    .line 85
    .line 86
    .line 87
    .line 88
    .line 89
.end method
