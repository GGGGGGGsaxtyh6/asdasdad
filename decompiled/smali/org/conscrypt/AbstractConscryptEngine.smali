.class abstract Lorg/conscrypt/AbstractConscryptEngine;
.super Ljavax/net/ssl/SSLEngine;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljavax/net/ssl/SSLEngine;-><init>()V

    .line 2
    .line 3
    .line 4
    return-void
    .line 5
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


# virtual methods
.method public abstract exportKeyingMaterial(Ljava/lang/String;[BI)[B
.end method

.method public abstract getApplicationProtocol()Ljava/lang/String;
.end method

.method public abstract getApplicationProtocols()[Ljava/lang/String;
.end method

.method public abstract getChannelId()[B
.end method

.method public abstract getHandshakeApplicationProtocol()Ljava/lang/String;
.end method

.method public final getHandshakeSession()Ljavax/net/ssl/SSLSession;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lorg/conscrypt/AbstractConscryptEngine;->handshakeSession()Ljavax/net/ssl/SSLSession;

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

.method public abstract getHostname()Ljava/lang/String;
.end method

.method public abstract getPeerHost()Ljava/lang/String;
.end method

.method public abstract getPeerPort()I
.end method

.method public abstract getTlsUnique()[B
.end method

.method public abstract handshakeSession()Ljavax/net/ssl/SSLSession;
.end method

.method public abstract maxSealOverhead()I
.end method

.method public abstract setApplicationProtocolSelector(Lorg/conscrypt/ApplicationProtocolSelector;)V
.end method

.method public abstract setApplicationProtocols([Ljava/lang/String;)V
.end method

.method public abstract setBufferAllocator(Lorg/conscrypt/BufferAllocator;)V
.end method

.method public abstract setChannelIdEnabled(Z)V
.end method

.method public abstract setChannelIdPrivateKey(Ljava/security/PrivateKey;)V
.end method

.method public abstract setHandshakeListener(Lorg/conscrypt/HandshakeListener;)V
.end method

.method public abstract setHostname(Ljava/lang/String;)V
.end method

.method public abstract setUseSessionTickets(Z)V
.end method

.method public abstract unwrap(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)Ljavax/net/ssl/SSLEngineResult;
.end method

.method public abstract unwrap(Ljava/nio/ByteBuffer;[Ljava/nio/ByteBuffer;)Ljavax/net/ssl/SSLEngineResult;
.end method

.method public abstract unwrap(Ljava/nio/ByteBuffer;[Ljava/nio/ByteBuffer;II)Ljavax/net/ssl/SSLEngineResult;
.end method

.method public abstract unwrap([Ljava/nio/ByteBuffer;II[Ljava/nio/ByteBuffer;II)Ljavax/net/ssl/SSLEngineResult;
.end method

.method public abstract unwrap([Ljava/nio/ByteBuffer;[Ljava/nio/ByteBuffer;)Ljavax/net/ssl/SSLEngineResult;
.end method

.method public abstract wrap(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)Ljavax/net/ssl/SSLEngineResult;
.end method

.method public abstract wrap([Ljava/nio/ByteBuffer;IILjava/nio/ByteBuffer;)Ljavax/net/ssl/SSLEngineResult;
.end method
