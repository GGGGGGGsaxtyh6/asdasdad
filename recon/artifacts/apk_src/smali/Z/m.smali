.class public final synthetic LZ/m;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# instance fields
.field public final synthetic a:Lhtb/d3vnu11/securenotes/MainActivity;

.field public final synthetic b:Landroid/widget/EditText;

.field public final synthetic c:Landroid/widget/EditText;


# direct methods
.method public synthetic constructor <init>(Lhtb/d3vnu11/securenotes/MainActivity;Landroid/widget/EditText;Landroid/widget/EditText;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, LZ/m;->a:Lhtb/d3vnu11/securenotes/MainActivity;

    iput-object p2, p0, LZ/m;->b:Landroid/widget/EditText;

    iput-object p3, p0, LZ/m;->c:Landroid/widget/EditText;

    return-void
.end method


# virtual methods
.method public final onClick(Landroid/content/DialogInterface;I)V
    .locals 3

    .line 1
    iget-object v0, p0, LZ/m;->b:Landroid/widget/EditText;

    iget-object v1, p0, LZ/m;->c:Landroid/widget/EditText;

    iget-object v2, p0, LZ/m;->a:Lhtb/d3vnu11/securenotes/MainActivity;

    invoke-static {v2, v0, v1, p1, p2}, Lhtb/d3vnu11/securenotes/MainActivity;->e(Lhtb/d3vnu11/securenotes/MainActivity;Landroid/widget/EditText;Landroid/widget/EditText;Landroid/content/DialogInterface;I)V

    return-void
.end method
