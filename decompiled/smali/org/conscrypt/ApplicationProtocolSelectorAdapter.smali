.class final Lorg/conscrypt/ApplicationProtocolSelectorAdapter;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final NO_PROTOCOL_SELECTED:I = -0x1


# instance fields
.field private final engine:Ljavax/net/ssl/SSLEngine;

.field private final selector:Lorg/conscrypt/ApplicationProtocolSelector;

.field private final socket:Ljavax/net/ssl/SSLSocket;


# direct methods
.method public constructor <init>(Ljavax/net/ssl/SSLEngine;Lorg/conscrypt/ApplicationProtocolSelector;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2
    const-string v0, "engine"

    invoke-static {p1, v0}, Lorg/conscrypt/Preconditions;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljavax/net/ssl/SSLEngine;

    iput-object p1, p0, Lorg/conscrypt/ApplicationProtocolSelectorAdapter;->engine:Ljavax/net/ssl/SSLEngine;

    const/4 p1, 0x0

    .line 3
    iput-object p1, p0, Lorg/conscrypt/ApplicationProtocolSelectorAdapter;->socket:Ljavax/net/ssl/SSLSocket;

    .line 4
    const-string p1, "selector"

    invoke-static {p2, p1}, Lorg/conscrypt/Preconditions;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/conscrypt/ApplicationProtocolSelector;

    iput-object p1, p0, Lorg/conscrypt/ApplicationProtocolSelectorAdapter;->selector:Lorg/conscrypt/ApplicationProtocolSelector;

    return-void
.end method

.method public constructor <init>(Ljavax/net/ssl/SSLSocket;Lorg/conscrypt/ApplicationProtocolSelector;)V
    .locals 1

    .line 5
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    .line 6
    iput-object v0, p0, Lorg/conscrypt/ApplicationProtocolSelectorAdapter;->engine:Ljavax/net/ssl/SSLEngine;

    .line 7
    const-string v0, "socket"

    invoke-static {p1, v0}, Lorg/conscrypt/Preconditions;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljavax/net/ssl/SSLSocket;

    iput-object p1, p0, Lorg/conscrypt/ApplicationProtocolSelectorAdapter;->socket:Ljavax/net/ssl/SSLSocket;

    .line 8
    const-string p1, "selector"

    invoke-static {p2, p1}, Lorg/conscrypt/Preconditions;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/conscrypt/ApplicationProtocolSelector;

    iput-object p1, p0, Lorg/conscrypt/ApplicationProtocolSelectorAdapter;->selector:Lorg/conscrypt/ApplicationProtocolSelector;

    return-void
.end method


# virtual methods
.method public selectApplicationProtocol([B)I
    .locals 5

    .line 1
    const/4 v0, -0x1

    .line 2
    if-eqz p1, :cond_4

    .line 3
    .line 4
    array-length v1, p1

    .line 5
    if-nez v1, :cond_0

    .line 6
    .line 7
    goto :goto_2

    .line 8
    :cond_0
    invoke-static {p1}, Lorg/conscrypt/SSLUtils;->decodeProtocols([B)[Ljava/lang/String;

    .line 9
    .line 10
    .line 11
    move-result-object p1

    .line 12
    invoke-static {p1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    .line 13
    .line 14
    .line 15
    move-result-object p1

    .line 16
    iget-object v1, p0, Lorg/conscrypt/ApplicationProtocolSelectorAdapter;->engine:Ljavax/net/ssl/SSLEngine;

    .line 17
    .line 18
    if-eqz v1, :cond_1

    .line 19
    .line 20
    iget-object v2, p0, Lorg/conscrypt/ApplicationProtocolSelectorAdapter;->selector:Lorg/conscrypt/ApplicationProtocolSelector;

    .line 21
    .line 22
    invoke-virtual {v2, v1, p1}, Lorg/conscrypt/ApplicationProtocolSelector;->selectApplicationProtocol(Ljavax/net/ssl/SSLEngine;Ljava/util/List;)Ljava/lang/String;

    .line 23
    .line 24
    .line 25
    move-result-object v1

    .line 26
    goto :goto_0

    .line 27
    :cond_1
    iget-object v1, p0, Lorg/conscrypt/ApplicationProtocolSelectorAdapter;->selector:Lorg/conscrypt/ApplicationProtocolSelector;

    .line 28
    .line 29
    iget-object v2, p0, Lorg/conscrypt/ApplicationProtocolSelectorAdapter;->socket:Ljavax/net/ssl/SSLSocket;

    .line 30
    .line 31
    invoke-virtual {v1, v2, p1}, Lorg/conscrypt/ApplicationProtocolSelector;->selectApplicationProtocol(Ljavax/net/ssl/SSLSocket;Ljava/util/List;)Ljava/lang/String;

    .line 32
    .line 33
    .line 34
    move-result-object v1

    .line 35
    :goto_0
    if-eqz v1, :cond_4

    .line 36
    .line 37
    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    .line 38
    .line 39
    .line 40
    move-result v2

    .line 41
    if-eqz v2, :cond_2

    .line 42
    .line 43
    goto :goto_2

    .line 44
    :cond_2
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    .line 45
    .line 46
    .line 47
    move-result-object p1

    .line 48
    const/4 v2, 0x0

    .line 49
    :goto_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    .line 50
    .line 51
    .line 52
    move-result v3

    .line 53
    if-eqz v3, :cond_4

    .line 54
    .line 55
    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 56
    .line 57
    .line 58
    move-result-object v3

    .line 59
    check-cast v3, Ljava/lang/String;

    .line 60
    .line 61
    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 62
    .line 63
    .line 64
    move-result v4

    .line 65
    if-eqz v4, :cond_3

    .line 66
    .line 67
    return v2

    .line 68
    :cond_3
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    .line 69
    .line 70
    .line 71
    move-result v3

    .line 72
    add-int/lit8 v3, v3, 0x1

    .line 73
    .line 74
    add-int/2addr v2, v3

    .line 75
    goto :goto_1

    .line 76
    :cond_4
    :goto_2
    return v0
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
