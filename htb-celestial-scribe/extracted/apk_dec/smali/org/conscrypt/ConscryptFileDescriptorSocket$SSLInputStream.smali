.class Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;
.super Ljava/io/InputStream;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/conscrypt/ConscryptFileDescriptorSocket;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "SSLInputStream"
.end annotation


# instance fields
.field private final readLock:Ljava/lang/Object;

.field final synthetic this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;


# direct methods
.method public constructor <init>(Lorg/conscrypt/ConscryptFileDescriptorSocket;)V
    .locals 0

    .line 1
    iput-object p1, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;

    .line 2
    .line 3
    invoke-direct {p0}, Ljava/io/InputStream;-><init>()V

    .line 4
    .line 5
    .line 6
    new-instance p1, Ljava/lang/Object;

    .line 7
    .line 8
    invoke-direct {p1}, Ljava/lang/Object;-><init>()V

    .line 9
    .line 10
    .line 11
    iput-object p1, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->readLock:Ljava/lang/Object;

    .line 12
    .line 13
    return-void
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


# virtual methods
.method public available()I
    .locals 1

    .line 1
    iget-object v0, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;

    .line 2
    .line 3
    invoke-static {v0}, Lorg/conscrypt/ConscryptFileDescriptorSocket;->access$100(Lorg/conscrypt/ConscryptFileDescriptorSocket;)Lorg/conscrypt/NativeSsl;

    .line 4
    .line 5
    .line 6
    move-result-object v0

    .line 7
    invoke-virtual {v0}, Lorg/conscrypt/NativeSsl;->getPendingReadableBytes()I

    .line 8
    .line 9
    .line 10
    move-result v0

    .line 11
    return v0
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

.method public awaitPendingOps()V
    .locals 2

    .line 1
    iget-object v0, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->readLock:Ljava/lang/Object;

    .line 2
    .line 3
    monitor-enter v0

    .line 4
    :try_start_0
    monitor-exit v0

    .line 5
    return-void

    .line 6
    :catchall_0
    move-exception v1

    .line 7
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 8
    throw v1
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

.method public read()I
    .locals 4

    const/4 v0, 0x1

    .line 1
    new-array v1, v0, [B

    const/4 v2, 0x0

    .line 2
    invoke-virtual {p0, v1, v2, v0}, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->read([BII)I

    move-result v0

    const/4 v3, -0x1

    if-eq v0, v3, :cond_0

    .line 3
    aget-byte v0, v1, v2

    and-int/lit16 v3, v0, 0xff

    :cond_0
    return v3
.end method

.method public read([BII)I
    .locals 10

    .line 4
    invoke-static {}, Lorg/conscrypt/Platform;->blockGuardOnNetwork()V

    .line 5
    iget-object v0, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;

    invoke-virtual {v0}, Lorg/conscrypt/AbstractConscryptSocket;->checkOpen()V

    .line 6
    array-length v0, p1

    invoke-static {v0, p2, p3}, Lorg/conscrypt/ArrayUtils;->checkOffsetAndCount(III)V

    if-nez p3, :cond_0

    const/4 p1, 0x0

    return p1

    .line 7
    :cond_0
    iget-object v0, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->readLock:Ljava/lang/Object;

    monitor-enter v0

    .line 8
    :try_start_0
    iget-object v1, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;

    invoke-static {v1}, Lorg/conscrypt/ConscryptFileDescriptorSocket;->access$100(Lorg/conscrypt/ConscryptFileDescriptorSocket;)Lorg/conscrypt/NativeSsl;

    move-result-object v1

    monitor-enter v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 9
    :try_start_1
    iget-object v2, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;

    invoke-static {v2}, Lorg/conscrypt/ConscryptFileDescriptorSocket;->access$200(Lorg/conscrypt/ConscryptFileDescriptorSocket;)I

    move-result v2

    const/16 v3, 0x8

    if-eq v2, v3, :cond_3

    .line 10
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_2

    .line 11
    :try_start_2
    iget-object v1, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;

    invoke-static {v1}, Lorg/conscrypt/ConscryptFileDescriptorSocket;->access$100(Lorg/conscrypt/ConscryptFileDescriptorSocket;)Lorg/conscrypt/NativeSsl;

    move-result-object v4

    iget-object v1, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;

    iget-object v1, v1, Lorg/conscrypt/AbstractConscryptSocket;->socket:Ljava/net/Socket;

    .line 12
    invoke-static {v1}, Lorg/conscrypt/Platform;->getFileDescriptor(Ljava/net/Socket;)Ljava/io/FileDescriptor;

    move-result-object v5

    iget-object v1, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;

    invoke-virtual {v1}, Lorg/conscrypt/AbstractConscryptSocket;->getSoTimeout()I

    move-result v9

    move-object v6, p1

    move v7, p2

    move v8, p3

    .line 13
    invoke-virtual/range {v4 .. v9}, Lorg/conscrypt/NativeSsl;->read(Ljava/io/FileDescriptor;[BIII)I

    move-result p1

    const/4 p2, -0x1

    if-ne p1, p2, :cond_2

    .line 14
    iget-object p2, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;

    invoke-static {p2}, Lorg/conscrypt/ConscryptFileDescriptorSocket;->access$100(Lorg/conscrypt/ConscryptFileDescriptorSocket;)Lorg/conscrypt/NativeSsl;

    move-result-object p2

    monitor-enter p2
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 15
    :try_start_3
    iget-object p3, p0, Lorg/conscrypt/ConscryptFileDescriptorSocket$SSLInputStream;->this$0:Lorg/conscrypt/ConscryptFileDescriptorSocket;

    invoke-static {p3}, Lorg/conscrypt/ConscryptFileDescriptorSocket;->access$200(Lorg/conscrypt/ConscryptFileDescriptorSocket;)I

    move-result p3

    if-eq p3, v3, :cond_1

    .line 16
    monitor-exit p2

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_0

    .line 17
    :cond_1
    new-instance p1, Ljava/net/SocketException;

    const-string p3, "socket is closed"

    invoke-direct {p1, p3}, Ljava/net/SocketException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 18
    :goto_0
    monitor-exit p2
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    :try_start_4
    throw p1

    :catchall_1
    move-exception p1

    goto :goto_3

    .line 19
    :cond_2
    :goto_1
    monitor-exit v0
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    return p1

    :catchall_2
    move-exception p1

    goto :goto_2

    .line 20
    :cond_3
    :try_start_5
    new-instance p1, Ljava/net/SocketException;

    const-string p2, "socket is closed"

    invoke-direct {p1, p2}, Ljava/net/SocketException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 21
    :goto_2
    monitor-exit v1
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_2

    :try_start_6
    throw p1

    .line 22
    :goto_3
    monitor-exit v0
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    throw p1
.end method
