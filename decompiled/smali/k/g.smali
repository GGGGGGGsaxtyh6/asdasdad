.class public final Lk/g;
.super Lj/x;
.source "SourceFile"


# instance fields
.field public final synthetic l:I

.field public final synthetic m:Lk/k;


# direct methods
.method public constructor <init>(Lk/k;Landroid/content/Context;Lj/F;Landroid/view/View;)V
    .locals 7

    const/4 v0, 0x0

    iput v0, p0, Lk/g;->l:I

    .line 8
    iput-object p1, p0, Lk/g;->m:Lk/k;

    const/4 v6, 0x0

    const v2, 0x7f030022

    move-object v1, p0

    move-object v3, p2

    move-object v4, p4

    move-object v5, p3

    .line 9
    invoke-direct/range {v1 .. v6}, Lj/x;-><init>(ILandroid/content/Context;Landroid/view/View;Lj/n;Z)V

    .line 10
    iget-object p2, p3, Lj/F;->A:Lj/p;

    .line 11
    invoke-virtual {p2}, Lj/p;->f()Z

    move-result p2

    if-nez p2, :cond_1

    .line 12
    iget-object p2, p1, Lk/k;->i:Lk/j;

    if-nez p2, :cond_0

    .line 13
    iget-object p2, p1, Lk/k;->h:Lj/B;

    .line 14
    check-cast p2, Landroid/view/View;

    .line 15
    :cond_0
    iput-object p2, p0, Lj/x;->e:Landroid/view/View;

    .line 16
    :cond_1
    iget-object p1, p1, Lk/k;->w:LA/f;

    .line 17
    iput-object p1, p0, Lj/x;->h:Lj/y;

    .line 18
    iget-object p2, p0, Lj/x;->i:Lj/v;

    if-eqz p2, :cond_2

    .line 19
    invoke-interface {p2, p1}, Lj/z;->h(Lj/y;)V

    :cond_2
    return-void
.end method

.method public constructor <init>(Lk/k;Landroid/content/Context;Lj/n;Landroid/view/View;)V
    .locals 7

    const/4 v0, 0x1

    iput v0, p0, Lk/g;->l:I

    .line 1
    iput-object p1, p0, Lk/g;->m:Lk/k;

    const v2, 0x7f030022

    const/4 v6, 0x1

    move-object v1, p0

    move-object v3, p2

    move-object v4, p4

    move-object v5, p3

    .line 2
    invoke-direct/range {v1 .. v6}, Lj/x;-><init>(ILandroid/content/Context;Landroid/view/View;Lj/n;Z)V

    const p2, 0x800005

    .line 3
    iput p2, p0, Lj/x;->f:I

    .line 4
    iget-object p1, p1, Lk/k;->w:LA/f;

    .line 5
    iput-object p1, p0, Lj/x;->h:Lj/y;

    .line 6
    iget-object p2, p0, Lj/x;->i:Lj/v;

    if-eqz p2, :cond_0

    .line 7
    invoke-interface {p2, p1}, Lj/z;->h(Lj/y;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public final c()V
    .locals 3

    .line 1
    iget v0, p0, Lk/g;->l:I

    .line 2
    .line 3
    packed-switch v0, :pswitch_data_0

    .line 4
    .line 5
    .line 6
    iget-object v0, p0, Lk/g;->m:Lk/k;

    .line 7
    .line 8
    iget-object v1, v0, Lk/k;->c:Lj/n;

    .line 9
    .line 10
    if-eqz v1, :cond_0

    .line 11
    .line 12
    const/4 v2, 0x1

    .line 13
    invoke-virtual {v1, v2}, Lj/n;->c(Z)V

    .line 14
    .line 15
    .line 16
    :cond_0
    const/4 v1, 0x0

    .line 17
    iput-object v1, v0, Lk/k;->s:Lk/g;

    .line 18
    .line 19
    invoke-super {p0}, Lj/x;->c()V

    .line 20
    .line 21
    .line 22
    return-void

    .line 23
    :pswitch_0
    const/4 v0, 0x0

    .line 24
    iget-object v1, p0, Lk/g;->m:Lk/k;

    .line 25
    .line 26
    iput-object v0, v1, Lk/k;->t:Lk/g;

    .line 27
    .line 28
    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 29
    .line 30
    .line 31
    invoke-super {p0}, Lj/x;->c()V

    .line 32
    .line 33
    .line 34
    return-void

    .line 35
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
    .end packed-switch
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
