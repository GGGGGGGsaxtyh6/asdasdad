.class public final LZ/q;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# instance fields
.field public final synthetic a:I

.field public final synthetic b:Ljava/lang/Object;


# direct methods
.method public synthetic constructor <init>(ILjava/lang/Object;)V
    .locals 0

    .line 1
    iput p1, p0, LZ/q;->a:I

    iput-object p2, p0, LZ/q;->b:Ljava/lang/Object;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onClick(Landroid/view/View;)V
    .locals 2

    .line 1
    iget p1, p0, LZ/q;->a:I

    .line 2
    .line 3
    packed-switch p1, :pswitch_data_0

    .line 4
    .line 5
    .line 6
    iget-object p1, p0, LZ/q;->b:Ljava/lang/Object;

    .line 7
    .line 8
    check-cast p1, Landroidx/appcompat/widget/Toolbar;

    .line 9
    .line 10
    iget-object p1, p1, Landroidx/appcompat/widget/Toolbar;->M:Lk/Z0;

    .line 11
    .line 12
    if-nez p1, :cond_0

    .line 13
    .line 14
    const/4 p1, 0x0

    .line 15
    goto :goto_0

    .line 16
    :cond_0
    iget-object p1, p1, Lk/Z0;->b:Lj/p;

    .line 17
    .line 18
    :goto_0
    if-eqz p1, :cond_1

    .line 19
    .line 20
    invoke-virtual {p1}, Lj/p;->collapseActionView()Z

    .line 21
    .line 22
    .line 23
    :cond_1
    return-void

    .line 24
    :pswitch_0
    iget-object p1, p0, LZ/q;->b:Ljava/lang/Object;

    .line 25
    .line 26
    check-cast p1, Li/b;

    .line 27
    .line 28
    invoke-virtual {p1}, Li/b;->a()V

    .line 29
    .line 30
    .line 31
    return-void

    .line 32
    :pswitch_1
    iget-object p1, p0, LZ/q;->b:Ljava/lang/Object;

    .line 33
    .line 34
    check-cast p1, Lg/g;

    .line 35
    .line 36
    iget-object v0, p1, Lg/g;->f:Landroid/widget/Button;

    .line 37
    .line 38
    iget-object v0, p1, Lg/g;->v:Lg/e;

    .line 39
    .line 40
    const/4 v1, 0x1

    .line 41
    iget-object p1, p1, Lg/g;->b:Lg/h;

    .line 42
    .line 43
    invoke-virtual {v0, v1, p1}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    .line 44
    .line 45
    .line 46
    move-result-object p1

    .line 47
    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    .line 48
    .line 49
    .line 50
    return-void

    .line 51
    :pswitch_2
    iget-object p1, p0, LZ/q;->b:Ljava/lang/Object;

    .line 52
    .line 53
    check-cast p1, LZ/p;

    .line 54
    .line 55
    iget-object v0, p1, LZ/p;->a:Lhtb/d3vnu11/securenotes/MainActivity;

    .line 56
    .line 57
    invoke-static {v0}, Lhtb/d3vnu11/securenotes/MainActivity;->f(Lhtb/d3vnu11/securenotes/MainActivity;)LZ/i;

    .line 58
    .line 59
    .line 60
    move-result-object v0

    .line 61
    invoke-virtual {v0}, LZ/i;->c()V

    .line 62
    .line 63
    .line 64
    iget-object p1, p1, LZ/p;->a:Lhtb/d3vnu11/securenotes/MainActivity;

    .line 65
    .line 66
    invoke-static {p1}, Lhtb/d3vnu11/securenotes/MainActivity;->n(Lhtb/d3vnu11/securenotes/MainActivity;)V

    .line 67
    .line 68
    .line 69
    invoke-static {p1}, Lhtb/d3vnu11/securenotes/MainActivity;->j(Lhtb/d3vnu11/securenotes/MainActivity;)V

    .line 70
    .line 71
    .line 72
    return-void

    .line 73
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
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
    .line 90
    .line 91
    .line 92
    .line 93
    .line 94
    .line 95
    .line 96
    .line 97
    .line 98
    .line 99
    .line 100
    .line 101
    .line 102
    .line 103
    .line 104
    .line 105
    .line 106
    .line 107
    .line 108
    .line 109
    .line 110
.end method
