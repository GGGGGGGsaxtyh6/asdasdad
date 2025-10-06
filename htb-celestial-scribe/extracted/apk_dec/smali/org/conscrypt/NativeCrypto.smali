.class public final Lorg/conscrypt/NativeCrypto;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;,
        Lorg/conscrypt/NativeCrypto$Range;
    }
.end annotation


# static fields
.field static final DEFAULT_PROTOCOLS:[Ljava/lang/String;

.field static final DEFAULT_PSK_CIPHER_SUITES:[Ljava/lang/String;

.field static final DEFAULT_X509_CIPHER_SUITES:[Ljava/lang/String;

.field static final EXTENSION_TYPE_CRITICAL:I = 0x1

.field static final EXTENSION_TYPE_NON_CRITICAL:I = 0x0

.field static final GN_STACK_ISSUER_ALT_NAME:I = 0x2

.field static final GN_STACK_SUBJECT_ALT_NAME:I = 0x1

.field private static final HAS_AES_HARDWARE:Z

.field static final OBSOLETE_PROTOCOL_SSLV3:Ljava/lang/String; = "SSLv3"

.field static final PKCS7_CERTS:I = 0x1

.field static final PKCS7_CRLS:I = 0x2

.field private static final SUPPORTED_LEGACY_CIPHER_SUITES_SET:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private static final SUPPORTED_PROTOCOLS:[Ljava/lang/String;

.field private static final SUPPORTED_PROTOCOL_TLSV1:Ljava/lang/String; = "TLSv1"

.field private static final SUPPORTED_PROTOCOL_TLSV1_1:Ljava/lang/String; = "TLSv1.1"

.field private static final SUPPORTED_PROTOCOL_TLSV1_2:Ljava/lang/String; = "TLSv1.2"

.field static final SUPPORTED_PROTOCOL_TLSV1_3:Ljava/lang/String; = "TLSv1.3"

.field private static final SUPPORTED_TLS_1_2_CIPHER_SUITES:[Ljava/lang/String;

.field static final SUPPORTED_TLS_1_2_CIPHER_SUITES_SET:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field static final SUPPORTED_TLS_1_3_CIPHER_SUITES:[Ljava/lang/String;

.field static final SUPPORTED_TLS_1_3_CIPHER_SUITES_SET:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field static final TLSV11_PROTOCOLS:[Ljava/lang/String;

.field static final TLSV12_PROTOCOLS:[Ljava/lang/String;

.field static final TLSV13_PROTOCOLS:[Ljava/lang/String;

.field static final TLSV1_PROTOCOLS:[Ljava/lang/String;

.field static final TLS_EMPTY_RENEGOTIATION_INFO_SCSV:Ljava/lang/String; = "TLS_EMPTY_RENEGOTIATION_INFO_SCSV"

.field private static final TLS_FALLBACK_SCSV:Ljava/lang/String; = "TLS_FALLBACK_SCSV"

.field private static final loadError:Ljava/lang/UnsatisfiedLinkError;


# direct methods
.method static constructor <clinit>()V
    .locals 19

    .line 1
    :try_start_0
    invoke-static {}, Lorg/conscrypt/NativeCryptoJni;->init()V

    .line 2
    .line 3
    .line 4
    invoke-static {}, Lorg/conscrypt/NativeCrypto;->clinit()V
    :try_end_0
    .catch Ljava/lang/UnsatisfiedLinkError; {:try_start_0 .. :try_end_0} :catch_0

    .line 5
    .line 6
    .line 7
    const/4 v0, 0x0

    .line 8
    goto :goto_0

    .line 9
    :catch_0
    move-exception v0

    .line 10
    :goto_0
    sput-object v0, Lorg/conscrypt/NativeCrypto;->loadError:Ljava/lang/UnsatisfiedLinkError;

    .line 11
    .line 12
    const-string v1, "TLS_AES_256_GCM_SHA384"

    .line 13
    .line 14
    const-string v2, "TLS_CHACHA20_POLY1305_SHA256"

    .line 15
    .line 16
    const-string v3, "TLS_AES_128_GCM_SHA256"

    .line 17
    .line 18
    filled-new-array {v3, v1, v2}, [Ljava/lang/String;

    .line 19
    .line 20
    .line 21
    move-result-object v1

    .line 22
    sput-object v1, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_3_CIPHER_SUITES:[Ljava/lang/String;

    .line 23
    .line 24
    new-instance v2, Ljava/util/HashSet;

    .line 25
    .line 26
    invoke-direct {v2}, Ljava/util/HashSet;-><init>()V

    .line 27
    .line 28
    .line 29
    sput-object v2, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_2_CIPHER_SUITES_SET:Ljava/util/Set;

    .line 30
    .line 31
    new-instance v2, Ljava/util/HashSet;

    .line 32
    .line 33
    invoke-direct {v2}, Ljava/util/HashSet;-><init>()V

    .line 34
    .line 35
    .line 36
    sput-object v2, Lorg/conscrypt/NativeCrypto;->SUPPORTED_LEGACY_CIPHER_SUITES_SET:Ljava/util/Set;

    .line 37
    .line 38
    new-instance v2, Ljava/util/HashSet;

    .line 39
    .line 40
    invoke-static {v1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    .line 41
    .line 42
    .line 43
    move-result-object v1

    .line 44
    invoke-direct {v2, v1}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    .line 45
    .line 46
    .line 47
    sput-object v2, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_3_CIPHER_SUITES_SET:Ljava/util/Set;

    .line 48
    .line 49
    const/4 v1, 0x1

    .line 50
    const/4 v2, 0x0

    .line 51
    if-nez v0, :cond_3

    .line 52
    .line 53
    const-string v0, "ALL:!DHE"

    .line 54
    .line 55
    invoke-static {v0}, Lorg/conscrypt/NativeCrypto;->get_cipher_names(Ljava/lang/String;)[Ljava/lang/String;

    .line 56
    .line 57
    .line 58
    move-result-object v0

    .line 59
    array-length v3, v0

    .line 60
    rem-int/lit8 v4, v3, 0x2

    .line 61
    .line 62
    if-nez v4, :cond_2

    .line 63
    .line 64
    div-int/lit8 v4, v3, 0x2

    .line 65
    .line 66
    add-int/lit8 v5, v4, 0x2

    .line 67
    .line 68
    new-array v5, v5, [Ljava/lang/String;

    .line 69
    .line 70
    sput-object v5, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_2_CIPHER_SUITES:[Ljava/lang/String;

    .line 71
    .line 72
    move v5, v2

    .line 73
    :goto_1
    if-ge v5, v3, :cond_0

    .line 74
    .line 75
    aget-object v6, v0, v5

    .line 76
    .line 77
    invoke-static {v6}, Lorg/conscrypt/NativeCrypto;->cipherSuiteToJava(Ljava/lang/String;)Ljava/lang/String;

    .line 78
    .line 79
    .line 80
    move-result-object v6

    .line 81
    sget-object v7, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_2_CIPHER_SUITES:[Ljava/lang/String;

    .line 82
    .line 83
    div-int/lit8 v8, v5, 0x2

    .line 84
    .line 85
    aput-object v6, v7, v8

    .line 86
    .line 87
    sget-object v7, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_2_CIPHER_SUITES_SET:Ljava/util/Set;

    .line 88
    .line 89
    invoke-interface {v7, v6}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 90
    .line 91
    .line 92
    sget-object v6, Lorg/conscrypt/NativeCrypto;->SUPPORTED_LEGACY_CIPHER_SUITES_SET:Ljava/util/Set;

    .line 93
    .line 94
    add-int/lit8 v7, v5, 0x1

    .line 95
    .line 96
    aget-object v7, v0, v7

    .line 97
    .line 98
    invoke-interface {v6, v7}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 99
    .line 100
    .line 101
    add-int/lit8 v5, v5, 0x2

    .line 102
    .line 103
    goto :goto_1

    .line 104
    :cond_0
    sget-object v0, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_2_CIPHER_SUITES:[Ljava/lang/String;

    .line 105
    .line 106
    const-string v3, "TLS_EMPTY_RENEGOTIATION_INFO_SCSV"

    .line 107
    .line 108
    aput-object v3, v0, v4

    .line 109
    .line 110
    add-int/2addr v4, v1

    .line 111
    const-string v3, "TLS_FALLBACK_SCSV"

    .line 112
    .line 113
    aput-object v3, v0, v4

    .line 114
    .line 115
    invoke-static {}, Lorg/conscrypt/NativeCrypto;->EVP_has_aes_hardware()I

    .line 116
    .line 117
    .line 118
    move-result v0

    .line 119
    if-ne v0, v1, :cond_1

    .line 120
    .line 121
    move v0, v1

    .line 122
    goto :goto_2

    .line 123
    :cond_1
    move v0, v2

    .line 124
    :goto_2
    sput-boolean v0, Lorg/conscrypt/NativeCrypto;->HAS_AES_HARDWARE:Z

    .line 125
    .line 126
    goto :goto_3

    .line 127
    :cond_2
    new-instance v0, Ljava/lang/IllegalArgumentException;

    .line 128
    .line 129
    const-string v1, "Invalid cipher list returned by get_cipher_names"

    .line 130
    .line 131
    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 132
    .line 133
    .line 134
    throw v0

    .line 135
    :cond_3
    sput-boolean v2, Lorg/conscrypt/NativeCrypto;->HAS_AES_HARDWARE:Z

    .line 136
    .line 137
    new-array v0, v2, [Ljava/lang/String;

    .line 138
    .line 139
    sput-object v0, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_2_CIPHER_SUITES:[Ljava/lang/String;

    .line 140
    .line 141
    :goto_3
    sget-boolean v0, Lorg/conscrypt/NativeCrypto;->HAS_AES_HARDWARE:Z

    .line 142
    .line 143
    const-string v3, "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA"

    .line 144
    .line 145
    const-string v5, "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA"

    .line 146
    .line 147
    const/4 v6, 0x7

    .line 148
    const-string v7, "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA"

    .line 149
    .line 150
    const/4 v8, 0x6

    .line 151
    const-string v9, "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256"

    .line 152
    .line 153
    const/4 v10, 0x5

    .line 154
    const-string v11, "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384"

    .line 155
    .line 156
    const/4 v12, 0x4

    .line 157
    const-string v13, "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256"

    .line 158
    .line 159
    const/4 v14, 0x3

    .line 160
    const-string v15, "TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256"

    .line 161
    .line 162
    const-string v16, "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384"

    .line 163
    .line 164
    const-string v17, "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256"

    .line 165
    .line 166
    const/16 v4, 0xe

    .line 167
    .line 168
    const/16 v18, 0x2

    .line 169
    .line 170
    if-eqz v0, :cond_4

    .line 171
    .line 172
    new-array v0, v4, [Ljava/lang/String;

    .line 173
    .line 174
    aput-object v17, v0, v2

    .line 175
    .line 176
    aput-object v16, v0, v1

    .line 177
    .line 178
    aput-object v15, v0, v18

    .line 179
    .line 180
    aput-object v13, v0, v14

    .line 181
    .line 182
    aput-object v11, v0, v12

    .line 183
    .line 184
    aput-object v9, v0, v10

    .line 185
    .line 186
    aput-object v7, v0, v8

    .line 187
    .line 188
    aput-object v5, v0, v6

    .line 189
    .line 190
    const/16 v1, 0x8

    .line 191
    .line 192
    aput-object v3, v0, v1

    .line 193
    .line 194
    const/16 v1, 0x9

    .line 195
    .line 196
    const-string v2, "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA"

    .line 197
    .line 198
    aput-object v2, v0, v1

    .line 199
    .line 200
    const/16 v1, 0xa

    .line 201
    .line 202
    const-string v2, "TLS_RSA_WITH_AES_128_GCM_SHA256"

    .line 203
    .line 204
    aput-object v2, v0, v1

    .line 205
    .line 206
    const/16 v1, 0xb

    .line 207
    .line 208
    const-string v2, "TLS_RSA_WITH_AES_256_GCM_SHA384"

    .line 209
    .line 210
    aput-object v2, v0, v1

    .line 211
    .line 212
    const/16 v1, 0xc

    .line 213
    .line 214
    const-string v2, "TLS_RSA_WITH_AES_128_CBC_SHA"

    .line 215
    .line 216
    aput-object v2, v0, v1

    .line 217
    .line 218
    const/16 v1, 0xd

    .line 219
    .line 220
    const-string v2, "TLS_RSA_WITH_AES_256_CBC_SHA"

    .line 221
    .line 222
    aput-object v2, v0, v1

    .line 223
    .line 224
    goto :goto_4

    .line 225
    :cond_4
    new-array v0, v4, [Ljava/lang/String;

    .line 226
    .line 227
    aput-object v15, v0, v2

    .line 228
    .line 229
    aput-object v17, v0, v1

    .line 230
    .line 231
    aput-object v16, v0, v18

    .line 232
    .line 233
    aput-object v9, v0, v14

    .line 234
    .line 235
    aput-object v13, v0, v12

    .line 236
    .line 237
    aput-object v11, v0, v10

    .line 238
    .line 239
    aput-object v7, v0, v8

    .line 240
    .line 241
    aput-object v5, v0, v6

    .line 242
    .line 243
    const/16 v1, 0x8

    .line 244
    .line 245
    aput-object v3, v0, v1

    .line 246
    .line 247
    const/16 v1, 0x9

    .line 248
    .line 249
    const-string v2, "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA"

    .line 250
    .line 251
    aput-object v2, v0, v1

    .line 252
    .line 253
    const/16 v1, 0xa

    .line 254
    .line 255
    const-string v2, "TLS_RSA_WITH_AES_128_GCM_SHA256"

    .line 256
    .line 257
    aput-object v2, v0, v1

    .line 258
    .line 259
    const/16 v1, 0xb

    .line 260
    .line 261
    const-string v2, "TLS_RSA_WITH_AES_256_GCM_SHA384"

    .line 262
    .line 263
    aput-object v2, v0, v1

    .line 264
    .line 265
    const/16 v1, 0xc

    .line 266
    .line 267
    const-string v2, "TLS_RSA_WITH_AES_128_CBC_SHA"

    .line 268
    .line 269
    aput-object v2, v0, v1

    .line 270
    .line 271
    const/16 v1, 0xd

    .line 272
    .line 273
    const-string v2, "TLS_RSA_WITH_AES_256_CBC_SHA"

    .line 274
    .line 275
    aput-object v2, v0, v1

    .line 276
    .line 277
    :goto_4
    sput-object v0, Lorg/conscrypt/NativeCrypto;->DEFAULT_X509_CIPHER_SUITES:[Ljava/lang/String;

    .line 278
    .line 279
    const-string v0, "TLS_PSK_WITH_AES_128_CBC_SHA"

    .line 280
    .line 281
    const-string v1, "TLS_PSK_WITH_AES_256_CBC_SHA"

    .line 282
    .line 283
    const-string v2, "TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256"

    .line 284
    .line 285
    const-string v3, "TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA"

    .line 286
    .line 287
    const-string v4, "TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA"

    .line 288
    .line 289
    filled-new-array {v2, v3, v4, v0, v1}, [Ljava/lang/String;

    .line 290
    .line 291
    .line 292
    move-result-object v0

    .line 293
    sput-object v0, Lorg/conscrypt/NativeCrypto;->DEFAULT_PSK_CIPHER_SUITES:[Ljava/lang/String;

    .line 294
    .line 295
    const-string v0, "TLSv1.3"

    .line 296
    .line 297
    const-string v1, "TLSv1"

    .line 298
    .line 299
    const-string v2, "TLSv1.1"

    .line 300
    .line 301
    const-string v3, "TLSv1.2"

    .line 302
    .line 303
    filled-new-array {v1, v2, v3, v0}, [Ljava/lang/String;

    .line 304
    .line 305
    .line 306
    move-result-object v0

    .line 307
    sput-object v0, Lorg/conscrypt/NativeCrypto;->TLSV13_PROTOCOLS:[Ljava/lang/String;

    .line 308
    .line 309
    filled-new-array {v1, v2, v3}, [Ljava/lang/String;

    .line 310
    .line 311
    .line 312
    move-result-object v4

    .line 313
    sput-object v4, Lorg/conscrypt/NativeCrypto;->TLSV12_PROTOCOLS:[Ljava/lang/String;

    .line 314
    .line 315
    sput-object v4, Lorg/conscrypt/NativeCrypto;->TLSV11_PROTOCOLS:[Ljava/lang/String;

    .line 316
    .line 317
    sput-object v4, Lorg/conscrypt/NativeCrypto;->TLSV1_PROTOCOLS:[Ljava/lang/String;

    .line 318
    .line 319
    sput-object v0, Lorg/conscrypt/NativeCrypto;->DEFAULT_PROTOCOLS:[Ljava/lang/String;

    .line 320
    .line 321
    const-string v0, "TLSv1.3"

    .line 322
    .line 323
    filled-new-array {v1, v2, v3, v0}, [Ljava/lang/String;

    .line 324
    .line 325
    .line 326
    move-result-object v0

    .line 327
    sput-object v0, Lorg/conscrypt/NativeCrypto;->SUPPORTED_PROTOCOLS:[Ljava/lang/String;

    .line 328
    .line 329
    return-void
    .line 330
    .line 331
    .line 332
    .line 333
    .line 334
    .line 335
    .line 336
    .line 337
    .line 338
    .line 339
    .line 340
    .line 341
    .line 342
    .line 343
    .line 344
    .line 345
    .line 346
    .line 347
    .line 348
    .line 349
    .line 350
    .line 351
    .line 352
    .line 353
    .line 354
    .line 355
    .line 356
    .line 357
    .line 358
    .line 359
    .line 360
    .line 361
    .line 362
    .line 363
    .line 364
    .line 365
    .line 366
    .line 367
    .line 368
    .line 369
    .line 370
    .line 371
    .line 372
    .line 373
    .line 374
    .line 375
    .line 376
    .line 377
    .line 378
    .line 379
    .line 380
    .line 381
    .line 382
    .line 383
    .line 384
    .line 385
    .line 386
    .line 387
    .line 388
    .line 389
    .line 390
    .line 391
    .line 392
    .line 393
    .line 394
    .line 395
    .line 396
    .line 397
    .line 398
    .line 399
    .line 400
    .line 401
    .line 402
    .line 403
    .line 404
    .line 405
    .line 406
    .line 407
    .line 408
    .line 409
    .line 410
    .line 411
    .line 412
    .line 413
    .line 414
    .line 415
    .line 416
    .line 417
    .line 418
    .line 419
    .line 420
    .line 421
    .line 422
    .line 423
    .line 424
    .line 425
    .line 426
    .line 427
    .line 428
    .line 429
    .line 430
    .line 431
    .line 432
    .line 433
    .line 434
    .line 435
    .line 436
    .line 437
    .line 438
    .line 439
    .line 440
    .line 441
    .line 442
    .line 443
    .line 444
    .line 445
    .line 446
    .line 447
    .line 448
    .line 449
    .line 450
    .line 451
    .line 452
    .line 453
    .line 454
    .line 455
    .line 456
    .line 457
    .line 458
    .line 459
    .line 460
    .line 461
    .line 462
    .line 463
    .line 464
    .line 465
    .line 466
    .line 467
    .line 468
    .line 469
    .line 470
    .line 471
    .line 472
    .line 473
    .line 474
    .line 475
    .line 476
    .line 477
    .line 478
    .line 479
    .line 480
    .line 481
    .line 482
    .line 483
    .line 484
    .line 485
    .line 486
    .line 487
    .line 488
    .line 489
    .line 490
    .line 491
    .line 492
    .line 493
    .line 494
    .line 495
    .line 496
    .line 497
    .line 498
    .line 499
    .line 500
    .line 501
    .line 502
    .line 503
    .line 504
    .line 505
    .line 506
    .line 507
    .line 508
    .line 509
    .line 510
    .line 511
    .line 512
    .line 513
    .line 514
    .line 515
    .line 516
    .line 517
    .line 518
    .line 519
    .line 520
    .line 521
    .line 522
    .line 523
    .line 524
    .line 525
    .line 526
    .line 527
    .line 528
    .line 529
    .line 530
    .line 531
    .line 532
    .line 533
    .line 534
    .line 535
    .line 536
    .line 537
    .line 538
    .line 539
    .line 540
    .line 541
    .line 542
    .line 543
    .line 544
    .line 545
    .line 546
    .line 547
    .line 548
    .line 549
    .line 550
    .line 551
    .line 552
    .line 553
    .line 554
    .line 555
    .line 556
    .line 557
    .line 558
    .line 559
    .line 560
    .line 561
    .line 562
    .line 563
    .line 564
    .line 565
    .line 566
    .line 567
    .line 568
    .line 569
    .line 570
    .line 571
    .line 572
    .line 573
    .line 574
    .line 575
    .line 576
    .line 577
    .line 578
    .line 579
    .line 580
    .line 581
    .line 582
    .line 583
    .line 584
    .line 585
    .line 586
    .line 587
    .line 588
    .line 589
    .line 590
    .line 591
    .line 592
    .line 593
    .line 594
    .line 595
    .line 596
    .line 597
    .line 598
    .line 599
    .line 600
    .line 601
    .line 602
    .line 603
    .line 604
    .line 605
    .line 606
    .line 607
    .line 608
    .line 609
    .line 610
    .line 611
    .line 612
    .line 613
    .line 614
    .line 615
    .line 616
    .line 617
    .line 618
    .line 619
    .line 620
    .line 621
    .line 622
    .line 623
    .line 624
    .line 625
    .line 626
    .line 627
    .line 628
    .line 629
    .line 630
    .line 631
    .line 632
    .line 633
    .line 634
    .line 635
    .line 636
    .line 637
    .line 638
    .line 639
    .line 640
    .line 641
    .line 642
    .line 643
    .line 644
    .line 645
    .line 646
    .line 647
    .line 648
    .line 649
    .line 650
    .line 651
    .line 652
    .line 653
    .line 654
    .line 655
    .line 656
    .line 657
    .line 658
    .line 659
    .line 660
    .line 661
    .line 662
    .line 663
    .line 664
    .line 665
    .line 666
    .line 667
    .line 668
    .line 669
    .line 670
    .line 671
    .line 672
    .line 673
    .line 674
    .line 675
    .line 676
    .line 677
    .line 678
    .line 679
    .line 680
    .line 681
    .line 682
    .line 683
    .line 684
    .line 685
    .line 686
    .line 687
    .line 688
    .line 689
    .line 690
    .line 691
    .line 692
    .line 693
    .line 694
    .line 695
    .line 696
    .line 697
    .line 698
    .line 699
    .line 700
    .line 701
    .line 702
    .line 703
    .line 704
    .line 705
    .line 706
    .line 707
    .line 708
    .line 709
    .line 710
    .line 711
    .line 712
    .line 713
    .line 714
    .line 715
    .line 716
    .line 717
    .line 718
    .line 719
    .line 720
    .line 721
    .line 722
    .line 723
    .line 724
    .line 725
    .line 726
    .line 727
    .line 728
    .line 729
    .line 730
    .line 731
    .line 732
    .line 733
    .line 734
    .line 735
    .line 736
    .line 737
    .line 738
    .line 739
    .line 740
    .line 741
    .line 742
    .line 743
    .line 744
    .line 745
    .line 746
    .line 747
    .line 748
    .line 749
    .line 750
    .line 751
    .line 752
    .line 753
    .line 754
    .line 755
    .line 756
    .line 757
    .line 758
    .line 759
    .line 760
    .line 761
    .line 762
    .line 763
    .line 764
    .line 765
    .line 766
    .line 767
    .line 768
    .line 769
    .line 770
    .line 771
    .line 772
    .line 773
    .line 774
    .line 775
    .line 776
    .line 777
    .line 778
    .line 779
    .line 780
    .line 781
    .line 782
    .line 783
    .line 784
    .line 785
    .line 786
    .line 787
    .line 788
    .line 789
    .line 790
    .line 791
    .line 792
    .line 793
    .line 794
    .line 795
    .line 796
    .line 797
    .line 798
    .line 799
    .line 800
    .line 801
    .line 802
    .line 803
    .line 804
    .line 805
    .line 806
    .line 807
    .line 808
    .line 809
    .line 810
    .line 811
    .line 812
    .line 813
    .line 814
    .line 815
    .line 816
    .line 817
    .line 818
    .line 819
    .line 820
    .line 821
    .line 822
    .line 823
    .line 824
    .line 825
    .line 826
    .line 827
    .line 828
    .line 829
    .line 830
    .line 831
    .line 832
    .line 833
    .line 834
    .line 835
    .line 836
    .line 837
    .line 838
    .line 839
    .line 840
    .line 841
    .line 842
    .line 843
    .line 844
    .line 845
    .line 846
    .line 847
    .line 848
    .line 849
    .line 850
    .line 851
    .line 852
    .line 853
    .line 854
    .line 855
    .line 856
    .line 857
    .line 858
    .line 859
    .line 860
    .line 861
    .line 862
    .line 863
    .line 864
    .line 865
    .line 866
    .line 867
    .line 868
    .line 869
    .line 870
    .line 871
    .line 872
    .line 873
    .line 874
    .line 875
    .line 876
    .line 877
    .line 878
    .line 879
    .line 880
    .line 881
    .line 882
    .line 883
    .line 884
    .line 885
    .line 886
    .line 887
    .line 888
    .line 889
    .line 890
    .line 891
    .line 892
    .line 893
    .line 894
    .line 895
    .line 896
    .line 897
    .line 898
    .line 899
    .line 900
    .line 901
    .line 902
    .line 903
    .line 904
    .line 905
    .line 906
    .line 907
    .line 908
    .line 909
    .line 910
    .line 911
    .line 912
    .line 913
    .line 914
    .line 915
    .line 916
    .line 917
    .line 918
    .line 919
    .line 920
    .line 921
    .line 922
    .line 923
    .line 924
    .line 925
    .line 926
    .line 927
    .line 928
    .line 929
    .line 930
    .line 931
    .line 932
    .line 933
    .line 934
    .line 935
    .line 936
    .line 937
    .line 938
    .line 939
    .line 940
    .line 941
    .line 942
    .line 943
    .line 944
    .line 945
    .line 946
    .line 947
    .line 948
    .line 949
    .line 950
    .line 951
    .line 952
    .line 953
    .line 954
    .line 955
    .line 956
    .line 957
    .line 958
    .line 959
    .line 960
    .line 961
    .line 962
    .line 963
    .line 964
    .line 965
    .line 966
    .line 967
    .line 968
    .line 969
    .line 970
    .line 971
    .line 972
    .line 973
    .line 974
    .line 975
    .line 976
    .line 977
    .line 978
    .line 979
    .line 980
    .line 981
    .line 982
    .line 983
    .line 984
    .line 985
    .line 986
    .line 987
    .line 988
    .line 989
    .line 990
    .line 991
    .line 992
    .line 993
    .line 994
    .line 995
    .line 996
    .line 997
    .line 998
    .line 999
    .line 1000
    .line 1001
    .line 1002
    .line 1003
    .line 1004
    .line 1005
    .line 1006
    .line 1007
    .line 1008
    .line 1009
    .line 1010
    .line 1011
    .line 1012
    .line 1013
    .line 1014
    .line 1015
    .line 1016
    .line 1017
    .line 1018
    .line 1019
    .line 1020
    .line 1021
    .line 1022
    .line 1023
    .line 1024
    .line 1025
    .line 1026
    .line 1027
    .line 1028
    .line 1029
    .line 1030
    .line 1031
    .line 1032
    .line 1033
    .line 1034
    .line 1035
    .line 1036
    .line 1037
    .line 1038
    .line 1039
    .line 1040
    .line 1041
    .line 1042
    .line 1043
    .line 1044
    .line 1045
    .line 1046
    .line 1047
    .line 1048
    .line 1049
    .line 1050
    .line 1051
    .line 1052
    .line 1053
    .line 1054
    .line 1055
    .line 1056
    .line 1057
    .line 1058
    .line 1059
    .line 1060
    .line 1061
    .line 1062
    .line 1063
    .line 1064
    .line 1065
    .line 1066
    .line 1067
    .line 1068
    .line 1069
    .line 1070
    .line 1071
    .line 1072
    .line 1073
    .line 1074
    .line 1075
    .line 1076
    .line 1077
    .line 1078
    .line 1079
    .line 1080
    .line 1081
    .line 1082
    .line 1083
    .line 1084
    .line 1085
    .line 1086
    .line 1087
    .line 1088
    .line 1089
    .line 1090
    .line 1091
    .line 1092
    .line 1093
    .line 1094
    .line 1095
    .line 1096
    .line 1097
    .line 1098
    .line 1099
    .line 1100
    .line 1101
    .line 1102
    .line 1103
    .line 1104
    .line 1105
    .line 1106
    .line 1107
    .line 1108
    .line 1109
    .line 1110
    .line 1111
    .line 1112
    .line 1113
    .line 1114
    .line 1115
    .line 1116
    .line 1117
    .line 1118
    .line 1119
    .line 1120
    .line 1121
    .line 1122
    .line 1123
    .line 1124
    .line 1125
    .line 1126
    .line 1127
    .line 1128
    .line 1129
    .line 1130
    .line 1131
    .line 1132
    .line 1133
    .line 1134
    .line 1135
    .line 1136
.end method

.method public constructor <init>()V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

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

.method public static native ASN1_TIME_to_Calendar(JLjava/util/Calendar;)V
.end method

.method public static native ASN1_seq_pack_X509([J)[B
.end method

.method public static native ASN1_seq_unpack_X509_bio(J)[J
.end method

.method public static native BIO_free_all(J)V
.end method

.method public static native BIO_read(J[B)I
.end method

.method public static native BIO_write(J[BII)V
.end method

.method public static native ECDH_compute_key([BILorg/conscrypt/NativeRef$EVP_PKEY;Lorg/conscrypt/NativeRef$EVP_PKEY;)I
.end method

.method public static native ECDSA_sign([B[BLorg/conscrypt/NativeRef$EVP_PKEY;)I
.end method

.method public static native ECDSA_size(Lorg/conscrypt/NativeRef$EVP_PKEY;)I
.end method

.method public static native ECDSA_verify([B[BLorg/conscrypt/NativeRef$EVP_PKEY;)I
.end method

.method public static native EC_GROUP_clear_free(J)V
.end method

.method public static native EC_GROUP_get_cofactor(Lorg/conscrypt/NativeRef$EC_GROUP;)[B
.end method

.method public static native EC_GROUP_get_curve(Lorg/conscrypt/NativeRef$EC_GROUP;)[[B
.end method

.method public static native EC_GROUP_get_curve_name(Lorg/conscrypt/NativeRef$EC_GROUP;)Ljava/lang/String;
.end method

.method public static native EC_GROUP_get_degree(Lorg/conscrypt/NativeRef$EC_GROUP;)I
.end method

.method public static native EC_GROUP_get_generator(Lorg/conscrypt/NativeRef$EC_GROUP;)J
.end method

.method public static native EC_GROUP_get_order(Lorg/conscrypt/NativeRef$EC_GROUP;)[B
.end method

.method public static native EC_GROUP_new_arbitrary([B[B[B[B[B[BI)J
.end method

.method public static native EC_GROUP_new_by_curve_name(Ljava/lang/String;)J
.end method

.method public static native EC_KEY_generate_key(Lorg/conscrypt/NativeRef$EC_GROUP;)J
.end method

.method public static native EC_KEY_get1_group(Lorg/conscrypt/NativeRef$EVP_PKEY;)J
.end method

.method public static native EC_KEY_get_private_key(Lorg/conscrypt/NativeRef$EVP_PKEY;)[B
.end method

.method public static native EC_KEY_get_public_key(Lorg/conscrypt/NativeRef$EVP_PKEY;)J
.end method

.method public static native EC_KEY_marshal_curve_name(Lorg/conscrypt/NativeRef$EC_GROUP;)[B
.end method

.method public static native EC_KEY_parse_curve_name([B)J
.end method

.method public static native EC_POINT_clear_free(J)V
.end method

.method public static native EC_POINT_get_affine_coordinates(Lorg/conscrypt/NativeRef$EC_GROUP;Lorg/conscrypt/NativeRef$EC_POINT;)[[B
.end method

.method public static native EC_POINT_new(Lorg/conscrypt/NativeRef$EC_GROUP;)J
.end method

.method public static native EC_POINT_set_affine_coordinates(Lorg/conscrypt/NativeRef$EC_GROUP;Lorg/conscrypt/NativeRef$EC_POINT;[B[B)V
.end method

.method public static native ENGINE_SSL_do_handshake(JLorg/conscrypt/NativeSsl;Lorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;)I
.end method

.method public static native ENGINE_SSL_force_read(JLorg/conscrypt/NativeSsl;Lorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;)V
.end method

.method public static native ENGINE_SSL_read_BIO_direct(JLorg/conscrypt/NativeSsl;JJILorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;)I
.end method

.method public static native ENGINE_SSL_read_BIO_heap(JLorg/conscrypt/NativeSsl;J[BIILorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;)I
.end method

.method public static native ENGINE_SSL_read_direct(JLorg/conscrypt/NativeSsl;JILorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;)I
.end method

.method public static native ENGINE_SSL_shutdown(JLorg/conscrypt/NativeSsl;Lorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;)V
.end method

.method public static native ENGINE_SSL_write_BIO_direct(JLorg/conscrypt/NativeSsl;JJILorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;)I
.end method

.method public static native ENGINE_SSL_write_BIO_heap(JLorg/conscrypt/NativeSsl;J[BIILorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;)I
.end method

.method public static native ENGINE_SSL_write_direct(JLorg/conscrypt/NativeSsl;JILorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;)I
.end method

.method public static native EVP_AEAD_CTX_open(J[BI[BI[B[BII[B)I
.end method

.method public static native EVP_AEAD_CTX_open_buf(J[BILjava/nio/ByteBuffer;[BLjava/nio/ByteBuffer;[B)I
.end method

.method public static native EVP_AEAD_CTX_seal(J[BI[BI[B[BII[B)I
.end method

.method public static native EVP_AEAD_CTX_seal_buf(J[BILjava/nio/ByteBuffer;[BLjava/nio/ByteBuffer;[B)I
.end method

.method public static native EVP_AEAD_max_overhead(J)I
.end method

.method public static native EVP_AEAD_nonce_length(J)I
.end method

.method public static native EVP_CIPHER_CTX_block_size(Lorg/conscrypt/NativeRef$EVP_CIPHER_CTX;)I
.end method

.method public static native EVP_CIPHER_CTX_free(J)V
.end method

.method public static native EVP_CIPHER_CTX_new()J
.end method

.method public static native EVP_CIPHER_CTX_set_key_length(Lorg/conscrypt/NativeRef$EVP_CIPHER_CTX;I)V
.end method

.method public static native EVP_CIPHER_CTX_set_padding(Lorg/conscrypt/NativeRef$EVP_CIPHER_CTX;Z)V
.end method

.method public static native EVP_CIPHER_iv_length(J)I
.end method

.method public static native EVP_CipherFinal_ex(Lorg/conscrypt/NativeRef$EVP_CIPHER_CTX;[BI)I
.end method

.method public static native EVP_CipherInit_ex(Lorg/conscrypt/NativeRef$EVP_CIPHER_CTX;J[B[BZ)V
.end method

.method public static native EVP_CipherUpdate(Lorg/conscrypt/NativeRef$EVP_CIPHER_CTX;[BI[BII)I
.end method

.method public static native EVP_DigestFinal_ex(Lorg/conscrypt/NativeRef$EVP_MD_CTX;[BI)I
.end method

.method public static native EVP_DigestInit_ex(Lorg/conscrypt/NativeRef$EVP_MD_CTX;J)I
.end method

.method public static native EVP_DigestSignFinal(Lorg/conscrypt/NativeRef$EVP_MD_CTX;)[B
.end method

.method public static native EVP_DigestSignInit(Lorg/conscrypt/NativeRef$EVP_MD_CTX;JLorg/conscrypt/NativeRef$EVP_PKEY;)J
.end method

.method public static native EVP_DigestSignUpdate(Lorg/conscrypt/NativeRef$EVP_MD_CTX;[BII)V
.end method

.method public static native EVP_DigestSignUpdateDirect(Lorg/conscrypt/NativeRef$EVP_MD_CTX;JI)V
.end method

.method public static native EVP_DigestUpdate(Lorg/conscrypt/NativeRef$EVP_MD_CTX;[BII)V
.end method

.method public static native EVP_DigestUpdateDirect(Lorg/conscrypt/NativeRef$EVP_MD_CTX;JI)V
.end method

.method public static native EVP_DigestVerifyFinal(Lorg/conscrypt/NativeRef$EVP_MD_CTX;[BII)Z
.end method

.method public static native EVP_DigestVerifyInit(Lorg/conscrypt/NativeRef$EVP_MD_CTX;JLorg/conscrypt/NativeRef$EVP_PKEY;)J
.end method

.method public static native EVP_DigestVerifyUpdate(Lorg/conscrypt/NativeRef$EVP_MD_CTX;[BII)V
.end method

.method public static native EVP_DigestVerifyUpdateDirect(Lorg/conscrypt/NativeRef$EVP_MD_CTX;JI)V
.end method

.method public static native EVP_MD_CTX_cleanup(Lorg/conscrypt/NativeRef$EVP_MD_CTX;)V
.end method

.method public static native EVP_MD_CTX_copy_ex(Lorg/conscrypt/NativeRef$EVP_MD_CTX;Lorg/conscrypt/NativeRef$EVP_MD_CTX;)I
.end method

.method public static native EVP_MD_CTX_create()J
.end method

.method public static native EVP_MD_CTX_destroy(J)V
.end method

.method public static native EVP_MD_size(J)I
.end method

.method public static native EVP_PKEY_CTX_free(J)V
.end method

.method public static native EVP_PKEY_CTX_set_rsa_mgf1_md(JJ)V
.end method

.method public static native EVP_PKEY_CTX_set_rsa_oaep_label(J[B)V
.end method

.method public static native EVP_PKEY_CTX_set_rsa_oaep_md(JJ)V
.end method

.method public static native EVP_PKEY_CTX_set_rsa_padding(JI)V
.end method

.method public static native EVP_PKEY_CTX_set_rsa_pss_saltlen(JI)V
.end method

.method public static native EVP_PKEY_cmp(Lorg/conscrypt/NativeRef$EVP_PKEY;Lorg/conscrypt/NativeRef$EVP_PKEY;)I
.end method

.method public static native EVP_PKEY_decrypt(Lorg/conscrypt/NativeRef$EVP_PKEY_CTX;[BI[BII)I
.end method

.method public static native EVP_PKEY_decrypt_init(Lorg/conscrypt/NativeRef$EVP_PKEY;)J
.end method

.method public static native EVP_PKEY_encrypt(Lorg/conscrypt/NativeRef$EVP_PKEY_CTX;[BI[BII)I
.end method

.method public static native EVP_PKEY_encrypt_init(Lorg/conscrypt/NativeRef$EVP_PKEY;)J
.end method

.method public static native EVP_PKEY_free(J)V
.end method

.method public static native EVP_PKEY_new_EC_KEY(Lorg/conscrypt/NativeRef$EC_GROUP;Lorg/conscrypt/NativeRef$EC_POINT;[B)J
.end method

.method public static native EVP_PKEY_new_RSA([B[B[B[B[B[B[B[B)J
.end method

.method public static native EVP_PKEY_print_params(Lorg/conscrypt/NativeRef$EVP_PKEY;)Ljava/lang/String;
.end method

.method public static native EVP_PKEY_print_public(Lorg/conscrypt/NativeRef$EVP_PKEY;)Ljava/lang/String;
.end method

.method public static native EVP_PKEY_type(Lorg/conscrypt/NativeRef$EVP_PKEY;)I
.end method

.method public static native EVP_aead_aes_128_gcm()J
.end method

.method public static native EVP_aead_aes_128_gcm_siv()J
.end method

.method public static native EVP_aead_aes_256_gcm()J
.end method

.method public static native EVP_aead_aes_256_gcm_siv()J
.end method

.method public static native EVP_aead_chacha20_poly1305()J
.end method

.method public static native EVP_get_cipherbyname(Ljava/lang/String;)J
.end method

.method public static native EVP_get_digestbyname(Ljava/lang/String;)J
.end method

.method public static native EVP_has_aes_hardware()I
.end method

.method public static native EVP_marshal_private_key(Lorg/conscrypt/NativeRef$EVP_PKEY;)[B
.end method

.method public static native EVP_marshal_public_key(Lorg/conscrypt/NativeRef$EVP_PKEY;)[B
.end method

.method public static native EVP_parse_private_key([B)J
.end method

.method public static native EVP_parse_public_key([B)J
.end method

.method public static native HMAC_CTX_free(J)V
.end method

.method public static native HMAC_CTX_new()J
.end method

.method public static native HMAC_Final(Lorg/conscrypt/NativeRef$HMAC_CTX;)[B
.end method

.method public static native HMAC_Init_ex(Lorg/conscrypt/NativeRef$HMAC_CTX;[BJ)V
.end method

.method public static native HMAC_Update(Lorg/conscrypt/NativeRef$HMAC_CTX;[BII)V
.end method

.method public static native HMAC_UpdateDirect(Lorg/conscrypt/NativeRef$HMAC_CTX;JI)V
.end method

.method public static native PEM_read_bio_PKCS7(JI)[J
.end method

.method public static native PEM_read_bio_PUBKEY(J)J
.end method

.method public static native PEM_read_bio_PrivateKey(J)J
.end method

.method public static native PEM_read_bio_X509(J)J
.end method

.method public static native PEM_read_bio_X509_CRL(J)J
.end method

.method public static native RAND_bytes([B)V
.end method

.method public static native RSA_generate_key_ex(I[B)J
.end method

.method public static native RSA_private_decrypt(I[B[BLorg/conscrypt/NativeRef$EVP_PKEY;I)I
.end method

.method public static native RSA_private_encrypt(I[B[BLorg/conscrypt/NativeRef$EVP_PKEY;I)I
.end method

.method public static native RSA_public_decrypt(I[B[BLorg/conscrypt/NativeRef$EVP_PKEY;I)I
.end method

.method public static native RSA_public_encrypt(I[B[BLorg/conscrypt/NativeRef$EVP_PKEY;I)I
.end method

.method public static native RSA_size(Lorg/conscrypt/NativeRef$EVP_PKEY;)I
.end method

.method public static native SSL_BIO_new(JLorg/conscrypt/NativeSsl;)J
.end method

.method public static native SSL_CIPHER_get_kx_name(J)Ljava/lang/String;
.end method

.method public static native SSL_CTX_free(JLorg/conscrypt/AbstractSessionContext;)V
.end method

.method public static native SSL_CTX_new()J
.end method

.method public static native SSL_CTX_set_session_id_context(JLorg/conscrypt/AbstractSessionContext;[B)V
.end method

.method public static native SSL_CTX_set_timeout(JLorg/conscrypt/AbstractSessionContext;J)J
.end method

.method public static native SSL_SESSION_cipher(J)Ljava/lang/String;
.end method

.method public static native SSL_SESSION_free(J)V
.end method

.method public static native SSL_SESSION_get_time(J)J
.end method

.method public static native SSL_SESSION_get_timeout(J)J
.end method

.method public static native SSL_SESSION_get_version(J)Ljava/lang/String;
.end method

.method public static native SSL_SESSION_session_id(J)[B
.end method

.method public static native SSL_SESSION_should_be_single_use(J)Z
.end method

.method public static native SSL_SESSION_up_ref(J)V
.end method

.method public static native SSL_accept_renegotiations(JLorg/conscrypt/NativeSsl;)V
.end method

.method public static native SSL_clear_error()V
.end method

.method public static native SSL_clear_mode(JLorg/conscrypt/NativeSsl;J)J
.end method

.method public static native SSL_clear_options(JLorg/conscrypt/NativeSsl;J)J
.end method

.method public static native SSL_do_handshake(JLorg/conscrypt/NativeSsl;Ljava/io/FileDescriptor;Lorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;I)V
.end method

.method public static native SSL_enable_ocsp_stapling(JLorg/conscrypt/NativeSsl;)V
.end method

.method public static native SSL_enable_signed_cert_timestamps(JLorg/conscrypt/NativeSsl;)V
.end method

.method public static native SSL_enable_tls_channel_id(JLorg/conscrypt/NativeSsl;)V
.end method

.method public static native SSL_export_keying_material(JLorg/conscrypt/NativeSsl;[B[BI)[B
.end method

.method public static native SSL_free(JLorg/conscrypt/NativeSsl;)V
.end method

.method public static native SSL_get0_peer_certificates(JLorg/conscrypt/NativeSsl;)[[B
.end method

.method public static native SSL_get1_session(JLorg/conscrypt/NativeSsl;)J
.end method

.method public static native SSL_get_ciphers(JLorg/conscrypt/NativeSsl;)[J
.end method

.method public static native SSL_get_current_cipher(JLorg/conscrypt/NativeSsl;)Ljava/lang/String;
.end method

.method public static native SSL_get_error(JLorg/conscrypt/NativeSsl;I)I
.end method

.method public static native SSL_get_mode(JLorg/conscrypt/NativeSsl;)J
.end method

.method public static native SSL_get_ocsp_response(JLorg/conscrypt/NativeSsl;)[B
.end method

.method public static native SSL_get_options(JLorg/conscrypt/NativeSsl;)J
.end method

.method public static native SSL_get_servername(JLorg/conscrypt/NativeSsl;)Ljava/lang/String;
.end method

.method public static native SSL_get_shutdown(JLorg/conscrypt/NativeSsl;)I
.end method

.method public static native SSL_get_signature_algorithm_key_type(I)I
.end method

.method public static native SSL_get_signed_cert_timestamp_list(JLorg/conscrypt/NativeSsl;)[B
.end method

.method public static native SSL_get_time(JLorg/conscrypt/NativeSsl;)J
.end method

.method public static native SSL_get_timeout(JLorg/conscrypt/NativeSsl;)J
.end method

.method public static native SSL_get_tls_channel_id(JLorg/conscrypt/NativeSsl;)[B
.end method

.method public static native SSL_get_tls_unique(JLorg/conscrypt/NativeSsl;)[B
.end method

.method public static native SSL_get_version(JLorg/conscrypt/NativeSsl;)Ljava/lang/String;
.end method

.method public static native SSL_interrupt(JLorg/conscrypt/NativeSsl;)V
.end method

.method public static native SSL_max_seal_overhead(JLorg/conscrypt/NativeSsl;)I
.end method

.method public static native SSL_new(JLorg/conscrypt/AbstractSessionContext;)J
.end method

.method public static native SSL_pending_readable_bytes(JLorg/conscrypt/NativeSsl;)I
.end method

.method public static native SSL_pending_written_bytes_in_BIO(J)I
.end method

.method public static native SSL_read(JLorg/conscrypt/NativeSsl;Ljava/io/FileDescriptor;Lorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;[BIII)I
.end method

.method public static native SSL_session_id(JLorg/conscrypt/NativeSsl;)[B
.end method

.method public static native SSL_session_reused(JLorg/conscrypt/NativeSsl;)Z
.end method

.method public static native SSL_set1_tls_channel_id(JLorg/conscrypt/NativeSsl;Lorg/conscrypt/NativeRef$EVP_PKEY;)V
.end method

.method public static native SSL_set_accept_state(JLorg/conscrypt/NativeSsl;)V
.end method

.method public static native SSL_set_cipher_lists(JLorg/conscrypt/NativeSsl;[Ljava/lang/String;)V
.end method

.method public static native SSL_set_client_CA_list(JLorg/conscrypt/NativeSsl;[[B)V
.end method

.method public static native SSL_set_connect_state(JLorg/conscrypt/NativeSsl;)V
.end method

.method public static native SSL_set_mode(JLorg/conscrypt/NativeSsl;J)J
.end method

.method public static native SSL_set_ocsp_response(JLorg/conscrypt/NativeSsl;[B)V
.end method

.method public static native SSL_set_options(JLorg/conscrypt/NativeSsl;J)J
.end method

.method public static native SSL_set_protocol_versions(JLorg/conscrypt/NativeSsl;II)I
.end method

.method public static native SSL_set_session(JLorg/conscrypt/NativeSsl;J)V
.end method

.method public static native SSL_set_session_creation_enabled(JLorg/conscrypt/NativeSsl;Z)V
.end method

.method public static native SSL_set_signed_cert_timestamp_list(JLorg/conscrypt/NativeSsl;[B)V
.end method

.method public static native SSL_set_timeout(JLorg/conscrypt/NativeSsl;J)J
.end method

.method public static native SSL_set_tlsext_host_name(JLorg/conscrypt/NativeSsl;Ljava/lang/String;)V
.end method

.method public static native SSL_set_verify(JLorg/conscrypt/NativeSsl;I)V
.end method

.method public static native SSL_shutdown(JLorg/conscrypt/NativeSsl;Ljava/io/FileDescriptor;Lorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;)V
.end method

.method public static native SSL_use_psk_identity_hint(JLorg/conscrypt/NativeSsl;Ljava/lang/String;)V
.end method

.method public static native SSL_write(JLorg/conscrypt/NativeSsl;Ljava/io/FileDescriptor;Lorg/conscrypt/NativeCrypto$SSLHandshakeCallbacks;[BIII)V
.end method

.method public static native X509_CRL_free(JLorg/conscrypt/OpenSSLX509CRL;)V
.end method

.method public static native X509_CRL_get0_by_cert(JLorg/conscrypt/OpenSSLX509CRL;JLorg/conscrypt/OpenSSLX509Certificate;)J
.end method

.method public static native X509_CRL_get0_by_serial(JLorg/conscrypt/OpenSSLX509CRL;[B)J
.end method

.method public static native X509_CRL_get_REVOKED(JLorg/conscrypt/OpenSSLX509CRL;)[J
.end method

.method public static native X509_CRL_get_ext(JLorg/conscrypt/OpenSSLX509CRL;Ljava/lang/String;)J
.end method

.method public static native X509_CRL_get_ext_oid(JLorg/conscrypt/OpenSSLX509CRL;Ljava/lang/String;)[B
.end method

.method public static native X509_CRL_get_issuer_name(JLorg/conscrypt/OpenSSLX509CRL;)[B
.end method

.method public static native X509_CRL_get_lastUpdate(JLorg/conscrypt/OpenSSLX509CRL;)J
.end method

.method public static native X509_CRL_get_nextUpdate(JLorg/conscrypt/OpenSSLX509CRL;)J
.end method

.method public static native X509_CRL_get_version(JLorg/conscrypt/OpenSSLX509CRL;)J
.end method

.method public static native X509_CRL_print(JJLorg/conscrypt/OpenSSLX509CRL;)V
.end method

.method public static native X509_CRL_verify(JLorg/conscrypt/OpenSSLX509CRL;Lorg/conscrypt/NativeRef$EVP_PKEY;)V
.end method

.method public static X509_NAME_hash(Ljavax/security/auth/x500/X500Principal;)I
    .locals 1

    .line 1
    const-string v0, "SHA1"

    invoke-static {p0, v0}, Lorg/conscrypt/NativeCrypto;->X509_NAME_hash(Ljavax/security/auth/x500/X500Principal;Ljava/lang/String;)I

    move-result p0

    return p0
.end method

.method private static X509_NAME_hash(Ljavax/security/auth/x500/X500Principal;Ljava/lang/String;)I
    .locals 1

    .line 2
    :try_start_0
    invoke-static {p1}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object p1

    invoke-virtual {p0}, Ljavax/security/auth/x500/X500Principal;->getEncoded()[B

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/security/MessageDigest;->digest([B)[B

    move-result-object p0

    const/4 p1, 0x0

    .line 3
    aget-byte p1, p0, p1

    and-int/lit16 p1, p1, 0xff

    const/4 v0, 0x1

    aget-byte v0, p0, v0

    and-int/lit16 v0, v0, 0xff

    shl-int/lit8 v0, v0, 0x8

    or-int/2addr p1, v0

    const/4 v0, 0x2

    aget-byte v0, p0, v0

    and-int/lit16 v0, v0, 0xff

    shl-int/lit8 v0, v0, 0x10

    or-int/2addr p1, v0

    const/4 v0, 0x3

    aget-byte p0, p0, v0
    :try_end_0
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_0 .. :try_end_0} :catch_0

    and-int/lit16 p0, p0, 0xff

    shl-int/lit8 p0, p0, 0x18

    or-int/2addr p0, p1

    return p0

    :catch_0
    move-exception p0

    .line 4
    new-instance p1, Ljava/lang/AssertionError;

    invoke-direct {p1, p0}, Ljava/lang/AssertionError;-><init>(Ljava/lang/Object;)V

    throw p1
.end method

.method public static X509_NAME_hash_old(Ljavax/security/auth/x500/X500Principal;)I
    .locals 1

    .line 1
    const-string v0, "MD5"

    .line 2
    .line 3
    invoke-static {p0, v0}, Lorg/conscrypt/NativeCrypto;->X509_NAME_hash(Ljavax/security/auth/x500/X500Principal;Ljava/lang/String;)I

    .line 4
    .line 5
    .line 6
    move-result p0

    .line 7
    return p0
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

.method public static native X509_REVOKED_dup(J)J
.end method

.method public static native X509_REVOKED_get_ext(JLjava/lang/String;)J
.end method

.method public static native X509_REVOKED_get_ext_oid(JLjava/lang/String;)[B
.end method

.method public static native X509_REVOKED_get_serialNumber(J)[B
.end method

.method public static native X509_REVOKED_print(JJ)V
.end method

.method public static native X509_check_issued(JLorg/conscrypt/OpenSSLX509Certificate;JLorg/conscrypt/OpenSSLX509Certificate;)I
.end method

.method public static native X509_cmp(JLorg/conscrypt/OpenSSLX509Certificate;JLorg/conscrypt/OpenSSLX509Certificate;)I
.end method

.method public static native X509_delete_ext(JLorg/conscrypt/OpenSSLX509Certificate;Ljava/lang/String;)V
.end method

.method public static native X509_dup(JLorg/conscrypt/OpenSSLX509Certificate;)J
.end method

.method public static native X509_free(JLorg/conscrypt/OpenSSLX509Certificate;)V
.end method

.method public static native X509_get_ext_oid(JLorg/conscrypt/OpenSSLX509Certificate;Ljava/lang/String;)[B
.end method

.method public static native X509_get_issuer_name(JLorg/conscrypt/OpenSSLX509Certificate;)[B
.end method

.method public static native X509_get_notAfter(JLorg/conscrypt/OpenSSLX509Certificate;)J
.end method

.method public static native X509_get_notBefore(JLorg/conscrypt/OpenSSLX509Certificate;)J
.end method

.method public static native X509_get_pubkey(JLorg/conscrypt/OpenSSLX509Certificate;)J
.end method

.method public static native X509_get_serialNumber(JLorg/conscrypt/OpenSSLX509Certificate;)[B
.end method

.method public static native X509_get_subject_name(JLorg/conscrypt/OpenSSLX509Certificate;)[B
.end method

.method public static native X509_get_version(JLorg/conscrypt/OpenSSLX509Certificate;)J
.end method

.method public static native X509_print_ex(JJLorg/conscrypt/OpenSSLX509Certificate;JJ)V
.end method

.method public static native X509_supported_extension(J)I
.end method

.method public static native X509_verify(JLorg/conscrypt/OpenSSLX509Certificate;Lorg/conscrypt/NativeRef$EVP_PKEY;)V
.end method

.method public static native asn1_read_free(J)V
.end method

.method public static native asn1_read_init([B)J
.end method

.method public static native asn1_read_is_empty(J)Z
.end method

.method public static native asn1_read_next_tag_is(JI)Z
.end method

.method public static native asn1_read_null(J)V
.end method

.method public static native asn1_read_octetstring(J)[B
.end method

.method public static native asn1_read_oid(J)Ljava/lang/String;
.end method

.method public static native asn1_read_sequence(J)J
.end method

.method public static native asn1_read_tagged(J)J
.end method

.method public static native asn1_read_uint64(J)J
.end method

.method public static native asn1_write_cleanup(J)V
.end method

.method public static native asn1_write_finish(J)[B
.end method

.method public static native asn1_write_flush(J)V
.end method

.method public static native asn1_write_free(J)V
.end method

.method public static native asn1_write_init()J
.end method

.method public static native asn1_write_null(J)V
.end method

.method public static native asn1_write_octetstring(J[B)V
.end method

.method public static native asn1_write_oid(JLjava/lang/String;)V
.end method

.method public static native asn1_write_sequence(J)J
.end method

.method public static native asn1_write_tag(JI)J
.end method

.method public static native asn1_write_uint64(JJ)V
.end method

.method public static native chacha20_encrypt_decrypt([BI[BII[B[BI)V
.end method

.method public static checkAvailability()V
    .locals 1

    .line 1
    sget-object v0, Lorg/conscrypt/NativeCrypto;->loadError:Ljava/lang/UnsatisfiedLinkError;

    .line 2
    .line 3
    if-nez v0, :cond_0

    .line 4
    .line 5
    return-void

    .line 6
    :cond_0
    throw v0
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

.method public static checkEnabledCipherSuites([Ljava/lang/String;)[Ljava/lang/String;
    .locals 4

    .line 1
    if-eqz p0, :cond_6

    .line 2
    .line 3
    const/4 v0, 0x0

    .line 4
    :goto_0
    array-length v1, p0

    .line 5
    if-ge v0, v1, :cond_5

    .line 6
    .line 7
    aget-object v1, p0, v0

    .line 8
    .line 9
    if-eqz v1, :cond_4

    .line 10
    .line 11
    const-string v2, "TLS_EMPTY_RENEGOTIATION_INFO_SCSV"

    .line 12
    .line 13
    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 14
    .line 15
    .line 16
    move-result v1

    .line 17
    if-nez v1, :cond_3

    .line 18
    .line 19
    aget-object v1, p0, v0

    .line 20
    .line 21
    const-string v2, "TLS_FALLBACK_SCSV"

    .line 22
    .line 23
    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 24
    .line 25
    .line 26
    move-result v1

    .line 27
    if-eqz v1, :cond_0

    .line 28
    .line 29
    goto :goto_1

    .line 30
    :cond_0
    sget-object v1, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_2_CIPHER_SUITES_SET:Ljava/util/Set;

    .line 31
    .line 32
    aget-object v2, p0, v0

    .line 33
    .line 34
    invoke-interface {v1, v2}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    .line 35
    .line 36
    .line 37
    move-result v1

    .line 38
    if-eqz v1, :cond_1

    .line 39
    .line 40
    goto :goto_1

    .line 41
    :cond_1
    sget-object v1, Lorg/conscrypt/NativeCrypto;->SUPPORTED_LEGACY_CIPHER_SUITES_SET:Ljava/util/Set;

    .line 42
    .line 43
    aget-object v2, p0, v0

    .line 44
    .line 45
    invoke-interface {v1, v2}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    .line 46
    .line 47
    .line 48
    move-result v1

    .line 49
    if-eqz v1, :cond_2

    .line 50
    .line 51
    goto :goto_1

    .line 52
    :cond_2
    new-instance v1, Ljava/lang/IllegalArgumentException;

    .line 53
    .line 54
    new-instance v2, Ljava/lang/StringBuilder;

    .line 55
    .line 56
    const-string v3, "cipherSuite "

    .line 57
    .line 58
    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 59
    .line 60
    .line 61
    aget-object p0, p0, v0

    .line 62
    .line 63
    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 64
    .line 65
    .line 66
    const-string p0, " is not supported."

    .line 67
    .line 68
    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 69
    .line 70
    .line 71
    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    .line 72
    .line 73
    .line 74
    move-result-object p0

    .line 75
    invoke-direct {v1, p0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 76
    .line 77
    .line 78
    throw v1

    .line 79
    :cond_3
    :goto_1
    add-int/lit8 v0, v0, 0x1

    .line 80
    .line 81
    goto :goto_0

    .line 82
    :cond_4
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 83
    .line 84
    const-string v1, "cipherSuites["

    .line 85
    .line 86
    const-string v2, "] == null"

    .line 87
    .line 88
    invoke-static {v1, v0, v2}, LA/e;->b(Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;

    .line 89
    .line 90
    .line 91
    move-result-object v0

    .line 92
    invoke-direct {p0, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 93
    .line 94
    .line 95
    throw p0

    .line 96
    :cond_5
    return-object p0

    .line 97
    :cond_6
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 98
    .line 99
    const-string v0, "cipherSuites == null"

    .line 100
    .line 101
    invoke-direct {p0, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 102
    .line 103
    .line 104
    throw p0
    .line 105
    .line 106
    .line 107
    .line 108
    .line 109
    .line 110
.end method

.method public static checkEnabledProtocols([Ljava/lang/String;)[Ljava/lang/String;
    .locals 4

    .line 1
    if-eqz p0, :cond_4

    .line 2
    .line 3
    array-length v0, p0

    .line 4
    const/4 v1, 0x0

    .line 5
    :goto_0
    if-ge v1, v0, :cond_3

    .line 6
    .line 7
    aget-object v2, p0, v1

    .line 8
    .line 9
    if-eqz v2, :cond_2

    .line 10
    .line 11
    const-string v3, "TLSv1"

    .line 12
    .line 13
    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 14
    .line 15
    .line 16
    move-result v3

    .line 17
    if-nez v3, :cond_1

    .line 18
    .line 19
    const-string v3, "TLSv1.1"

    .line 20
    .line 21
    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 22
    .line 23
    .line 24
    move-result v3

    .line 25
    if-nez v3, :cond_1

    .line 26
    .line 27
    const-string v3, "TLSv1.2"

    .line 28
    .line 29
    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 30
    .line 31
    .line 32
    move-result v3

    .line 33
    if-nez v3, :cond_1

    .line 34
    .line 35
    const-string v3, "TLSv1.3"

    .line 36
    .line 37
    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 38
    .line 39
    .line 40
    move-result v3

    .line 41
    if-nez v3, :cond_1

    .line 42
    .line 43
    const-string v3, "SSLv3"

    .line 44
    .line 45
    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 46
    .line 47
    .line 48
    move-result v3

    .line 49
    if-eqz v3, :cond_0

    .line 50
    .line 51
    goto :goto_1

    .line 52
    :cond_0
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 53
    .line 54
    const-string v0, "protocol "

    .line 55
    .line 56
    const-string v1, " is not supported"

    .line 57
    .line 58
    invoke-static {v0, v2, v1}, LA/e;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    .line 59
    .line 60
    .line 61
    move-result-object v0

    .line 62
    invoke-direct {p0, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 63
    .line 64
    .line 65
    throw p0

    .line 66
    :cond_1
    :goto_1
    add-int/lit8 v1, v1, 0x1

    .line 67
    .line 68
    goto :goto_0

    .line 69
    :cond_2
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 70
    .line 71
    const-string v0, "protocols contains null"

    .line 72
    .line 73
    invoke-direct {p0, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 74
    .line 75
    .line 76
    throw p0

    .line 77
    :cond_3
    return-object p0

    .line 78
    :cond_4
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 79
    .line 80
    const-string v0, "protocols == null"

    .line 81
    .line 82
    invoke-direct {p0, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 83
    .line 84
    .line 85
    throw p0
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

.method public static cipherSuiteFromJava(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 1
    const-string v0, "SSL_RSA_WITH_3DES_EDE_CBC_SHA"

    .line 2
    .line 3
    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 4
    .line 5
    .line 6
    move-result v0

    .line 7
    if-eqz v0, :cond_0

    .line 8
    .line 9
    const-string p0, "TLS_RSA_WITH_3DES_EDE_CBC_SHA"

    .line 10
    .line 11
    :cond_0
    return-object p0
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

.method public static cipherSuiteToJava(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 1
    const-string v0, "TLS_RSA_WITH_3DES_EDE_CBC_SHA"

    .line 2
    .line 3
    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 4
    .line 5
    .line 6
    move-result v0

    .line 7
    if-eqz v0, :cond_0

    .line 8
    .line 9
    const-string p0, "SSL_RSA_WITH_3DES_EDE_CBC_SHA"

    .line 10
    .line 11
    :cond_0
    return-object p0
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

.method private static native clinit()V
.end method

.method public static native create_BIO_InputStream(Lorg/conscrypt/OpenSSLBIOInputStream;Z)J
.end method

.method public static native create_BIO_OutputStream(Ljava/io/OutputStream;)J
.end method

.method public static native d2i_PKCS7_bio(JI)[J
.end method

.method public static native d2i_SSL_SESSION([B)J
.end method

.method public static native d2i_X509([B)J
.end method

.method public static native d2i_X509_CRL_bio(J)J
.end method

.method public static native d2i_X509_bio(J)J
.end method

.method public static native getApplicationProtocol(JLorg/conscrypt/NativeSsl;)[B
.end method

.method public static native getDirectBufferAddress(Ljava/nio/Buffer;)J
.end method

.method public static native getECPrivateKeyWrapper(Ljava/security/PrivateKey;Lorg/conscrypt/NativeRef$EC_GROUP;)J
.end method

.method private static getProtocolConstant(Ljava/lang/String;)I
    .locals 2

    .line 1
    const-string v0, "TLSv1"

    .line 2
    .line 3
    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 4
    .line 5
    .line 6
    move-result v0

    .line 7
    if-eqz v0, :cond_0

    .line 8
    .line 9
    const/16 p0, 0x301

    .line 10
    .line 11
    return p0

    .line 12
    :cond_0
    const-string v0, "TLSv1.1"

    .line 13
    .line 14
    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 15
    .line 16
    .line 17
    move-result v0

    .line 18
    if-eqz v0, :cond_1

    .line 19
    .line 20
    const/16 p0, 0x302

    .line 21
    .line 22
    return p0

    .line 23
    :cond_1
    const-string v0, "TLSv1.2"

    .line 24
    .line 25
    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 26
    .line 27
    .line 28
    move-result v0

    .line 29
    if-eqz v0, :cond_2

    .line 30
    .line 31
    const/16 p0, 0x303

    .line 32
    .line 33
    return p0

    .line 34
    :cond_2
    const-string v0, "TLSv1.3"

    .line 35
    .line 36
    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 37
    .line 38
    .line 39
    move-result v0

    .line 40
    if-eqz v0, :cond_3

    .line 41
    .line 42
    const/16 p0, 0x304

    .line 43
    .line 44
    return p0

    .line 45
    :cond_3
    new-instance v0, Ljava/lang/AssertionError;

    .line 46
    .line 47
    const-string v1, "Unknown protocol encountered: "

    .line 48
    .line 49
    invoke-virtual {v1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    .line 50
    .line 51
    .line 52
    move-result-object p0

    .line 53
    invoke-direct {v0, p0}, Ljava/lang/AssertionError;-><init>(Ljava/lang/Object;)V

    .line 54
    .line 55
    .line 56
    throw v0
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

.method private static getProtocolRange([Ljava/lang/String;)Lorg/conscrypt/NativeCrypto$Range;
    .locals 5

    .line 1
    invoke-static {p0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    .line 2
    .line 3
    .line 4
    move-result-object p0

    .line 5
    const/4 v0, 0x0

    .line 6
    const/4 v1, 0x0

    .line 7
    move v2, v1

    .line 8
    move-object v1, v0

    .line 9
    :goto_0
    sget-object v3, Lorg/conscrypt/NativeCrypto;->SUPPORTED_PROTOCOLS:[Ljava/lang/String;

    .line 10
    .line 11
    array-length v4, v3

    .line 12
    if-ge v2, v4, :cond_3

    .line 13
    .line 14
    aget-object v3, v3, v2

    .line 15
    .line 16
    invoke-interface {p0, v3}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    .line 17
    .line 18
    .line 19
    move-result v4

    .line 20
    if-eqz v4, :cond_1

    .line 21
    .line 22
    if-nez v0, :cond_0

    .line 23
    .line 24
    move-object v0, v3

    .line 25
    :cond_0
    move-object v1, v3

    .line 26
    goto :goto_1

    .line 27
    :cond_1
    if-eqz v0, :cond_2

    .line 28
    .line 29
    goto :goto_2

    .line 30
    :cond_2
    :goto_1
    add-int/lit8 v2, v2, 0x1

    .line 31
    .line 32
    goto :goto_0

    .line 33
    :cond_3
    :goto_2
    if-eqz v0, :cond_4

    .line 34
    .line 35
    if-eqz v1, :cond_4

    .line 36
    .line 37
    new-instance p0, Lorg/conscrypt/NativeCrypto$Range;

    .line 38
    .line 39
    invoke-direct {p0, v0, v1}, Lorg/conscrypt/NativeCrypto$Range;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 40
    .line 41
    .line 42
    return-object p0

    .line 43
    :cond_4
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 44
    .line 45
    const-string v0, "No protocols enabled."

    .line 46
    .line 47
    invoke-direct {p0, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 48
    .line 49
    .line 50
    throw p0
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

.method public static native getRSAPrivateKeyWrapper(Ljava/security/PrivateKey;[B)J
.end method

.method public static getSupportedCipherSuites()[Ljava/lang/String;
    .locals 2

    .line 1
    sget-object v0, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_3_CIPHER_SUITES:[Ljava/lang/String;

    .line 2
    .line 3
    sget-object v1, Lorg/conscrypt/NativeCrypto;->SUPPORTED_TLS_1_2_CIPHER_SUITES:[Ljava/lang/String;

    .line 4
    .line 5
    invoke-virtual {v1}, [Ljava/lang/String;->clone()Ljava/lang/Object;

    .line 6
    .line 7
    .line 8
    move-result-object v1

    .line 9
    check-cast v1, [Ljava/lang/String;

    .line 10
    .line 11
    filled-new-array {v0, v1}, [[Ljava/lang/String;

    .line 12
    .line 13
    .line 14
    move-result-object v0

    .line 15
    invoke-static {v0}, Lorg/conscrypt/SSLUtils;->concat([[Ljava/lang/String;)[Ljava/lang/String;

    .line 16
    .line 17
    .line 18
    move-result-object v0

    .line 19
    return-object v0
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

.method public static getSupportedProtocols()[Ljava/lang/String;
    .locals 1

    .line 1
    sget-object v0, Lorg/conscrypt/NativeCrypto;->SUPPORTED_PROTOCOLS:[Ljava/lang/String;

    .line 2
    .line 3
    invoke-virtual {v0}, [Ljava/lang/String;->clone()Ljava/lang/Object;

    .line 4
    .line 5
    .line 6
    move-result-object v0

    .line 7
    check-cast v0, [Ljava/lang/String;

    .line 8
    .line 9
    return-object v0
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

.method public static native get_EVP_CIPHER_CTX_buf_len(Lorg/conscrypt/NativeRef$EVP_CIPHER_CTX;)I
.end method

.method public static native get_EVP_CIPHER_CTX_final_used(Lorg/conscrypt/NativeRef$EVP_CIPHER_CTX;)Z
.end method

.method public static native get_RSA_private_params(Lorg/conscrypt/NativeRef$EVP_PKEY;)[[B
.end method

.method public static native get_RSA_public_params(Lorg/conscrypt/NativeRef$EVP_PKEY;)[[B
.end method

.method public static native get_X509_CRL_crl_enc(JLorg/conscrypt/OpenSSLX509CRL;)[B
.end method

.method public static native get_X509_CRL_ext_oids(JLorg/conscrypt/OpenSSLX509CRL;I)[Ljava/lang/String;
.end method

.method public static native get_X509_CRL_sig_alg_oid(JLorg/conscrypt/OpenSSLX509CRL;)Ljava/lang/String;
.end method

.method public static native get_X509_CRL_sig_alg_parameter(JLorg/conscrypt/OpenSSLX509CRL;)[B
.end method

.method public static native get_X509_CRL_signature(JLorg/conscrypt/OpenSSLX509CRL;)[B
.end method

.method public static native get_X509_GENERAL_NAME_stack(JLorg/conscrypt/OpenSSLX509Certificate;I)[[Ljava/lang/Object;
.end method

.method public static native get_X509_REVOKED_ext_oids(JI)[Ljava/lang/String;
.end method

.method public static native get_X509_REVOKED_revocationDate(J)J
.end method

.method public static native get_X509_cert_info_enc(JLorg/conscrypt/OpenSSLX509Certificate;)[B
.end method

.method public static native get_X509_ex_flags(JLorg/conscrypt/OpenSSLX509Certificate;)I
.end method

.method public static native get_X509_ex_kusage(JLorg/conscrypt/OpenSSLX509Certificate;)[Z
.end method

.method public static native get_X509_ex_pathlen(JLorg/conscrypt/OpenSSLX509Certificate;)I
.end method

.method public static native get_X509_ex_xkusage(JLorg/conscrypt/OpenSSLX509Certificate;)[Ljava/lang/String;
.end method

.method public static native get_X509_ext_oids(JLorg/conscrypt/OpenSSLX509Certificate;I)[Ljava/lang/String;
.end method

.method public static native get_X509_issuerUID(JLorg/conscrypt/OpenSSLX509Certificate;)[Z
.end method

.method public static native get_X509_pubkey_oid(JLorg/conscrypt/OpenSSLX509Certificate;)Ljava/lang/String;
.end method

.method public static native get_X509_sig_alg_oid(JLorg/conscrypt/OpenSSLX509Certificate;)Ljava/lang/String;
.end method

.method public static native get_X509_sig_alg_parameter(JLorg/conscrypt/OpenSSLX509Certificate;)[B
.end method

.method public static native get_X509_signature(JLorg/conscrypt/OpenSSLX509Certificate;)[B
.end method

.method public static native get_X509_subjectUID(JLorg/conscrypt/OpenSSLX509Certificate;)[Z
.end method

.method public static native get_cipher_names(Ljava/lang/String;)[Ljava/lang/String;
.end method

.method public static native get_ocsp_single_extension([BLjava/lang/String;JLorg/conscrypt/OpenSSLX509Certificate;JLorg/conscrypt/OpenSSLX509Certificate;)[B
.end method

.method public static native i2d_PKCS7([J)[B
.end method

.method public static native i2d_SSL_SESSION(J)[B
.end method

.method public static native i2d_X509(JLorg/conscrypt/OpenSSLX509Certificate;)[B
.end method

.method public static native i2d_X509_CRL(JLorg/conscrypt/OpenSSLX509CRL;)[B
.end method

.method public static native i2d_X509_PUBKEY(JLorg/conscrypt/OpenSSLX509Certificate;)[B
.end method

.method public static native i2d_X509_REVOKED(J)[B
.end method

.method public static native setApplicationProtocols(JLorg/conscrypt/NativeSsl;Z[B)V
.end method

.method public static setEnabledCipherSuites(JLorg/conscrypt/NativeSsl;[Ljava/lang/String;[Ljava/lang/String;)V
    .locals 4

    .line 1
    invoke-static {p3}, Lorg/conscrypt/NativeCrypto;->checkEnabledCipherSuites([Ljava/lang/String;)[Ljava/lang/String;

    .line 2
    .line 3
    .line 4
    invoke-static {p4}, Lorg/conscrypt/NativeCrypto;->getProtocolRange([Ljava/lang/String;)Lorg/conscrypt/NativeCrypto$Range;

    .line 5
    .line 6
    .line 7
    move-result-object p4

    .line 8
    iget-object p4, p4, Lorg/conscrypt/NativeCrypto$Range;->max:Ljava/lang/String;

    .line 9
    .line 10
    new-instance v0, Ljava/util/ArrayList;

    .line 11
    .line 12
    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 13
    .line 14
    .line 15
    const/4 v1, 0x0

    .line 16
    :goto_0
    array-length v2, p3

    .line 17
    if-ge v1, v2, :cond_3

    .line 18
    .line 19
    aget-object v2, p3, v1

    .line 20
    .line 21
    const-string v3, "TLS_EMPTY_RENEGOTIATION_INFO_SCSV"

    .line 22
    .line 23
    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 24
    .line 25
    .line 26
    move-result v3

    .line 27
    if-eqz v3, :cond_0

    .line 28
    .line 29
    goto :goto_1

    .line 30
    :cond_0
    const-string v3, "TLS_FALLBACK_SCSV"

    .line 31
    .line 32
    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 33
    .line 34
    .line 35
    move-result v3

    .line 36
    if-eqz v3, :cond_2

    .line 37
    .line 38
    const-string v3, "TLSv1"

    .line 39
    .line 40
    invoke-virtual {p4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 41
    .line 42
    .line 43
    move-result v3

    .line 44
    if-nez v3, :cond_1

    .line 45
    .line 46
    const-string v3, "TLSv1.1"

    .line 47
    .line 48
    invoke-virtual {p4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 49
    .line 50
    .line 51
    move-result v3

    .line 52
    if-eqz v3, :cond_2

    .line 53
    .line 54
    :cond_1
    const-wide/16 v2, 0x400

    .line 55
    .line 56
    invoke-static {p0, p1, p2, v2, v3}, Lorg/conscrypt/NativeCrypto;->SSL_set_mode(JLorg/conscrypt/NativeSsl;J)J

    .line 57
    .line 58
    .line 59
    goto :goto_1

    .line 60
    :cond_2
    invoke-static {v2}, Lorg/conscrypt/NativeCrypto;->cipherSuiteFromJava(Ljava/lang/String;)Ljava/lang/String;

    .line 61
    .line 62
    .line 63
    move-result-object v2

    .line 64
    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 65
    .line 66
    .line 67
    :goto_1
    add-int/lit8 v1, v1, 0x1

    .line 68
    .line 69
    goto :goto_0

    .line 70
    :cond_3
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    .line 71
    .line 72
    .line 73
    move-result p3

    .line 74
    new-array p3, p3, [Ljava/lang/String;

    .line 75
    .line 76
    invoke-virtual {v0, p3}, Ljava/util/ArrayList;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    .line 77
    .line 78
    .line 79
    move-result-object p3

    .line 80
    check-cast p3, [Ljava/lang/String;

    .line 81
    .line 82
    invoke-static {p0, p1, p2, p3}, Lorg/conscrypt/NativeCrypto;->SSL_set_cipher_lists(JLorg/conscrypt/NativeSsl;[Ljava/lang/String;)V

    .line 83
    .line 84
    .line 85
    return-void
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
    .line 111
    .line 112
    .line 113
    .line 114
    .line 115
    .line 116
    .line 117
    .line 118
    .line 119
    .line 120
    .line 121
    .line 122
    .line 123
    .line 124
    .line 125
    .line 126
    .line 127
    .line 128
    .line 129
    .line 130
    .line 131
    .line 132
    .line 133
    .line 134
    .line 135
    .line 136
    .line 137
    .line 138
    .line 139
    .line 140
    .line 141
    .line 142
    .line 143
    .line 144
    .line 145
    .line 146
    .line 147
    .line 148
    .line 149
    .line 150
    .line 151
    .line 152
    .line 153
    .line 154
    .line 155
    .line 156
    .line 157
    .line 158
    .line 159
    .line 160
    .line 161
    .line 162
    .line 163
    .line 164
    .line 165
    .line 166
    .line 167
    .line 168
    .line 169
    .line 170
    .line 171
    .line 172
    .line 173
    .line 174
    .line 175
    .line 176
    .line 177
    .line 178
    .line 179
    .line 180
    .line 181
    .line 182
    .line 183
    .line 184
    .line 185
    .line 186
    .line 187
    .line 188
    .line 189
    .line 190
    .line 191
    .line 192
    .line 193
    .line 194
    .line 195
    .line 196
    .line 197
    .line 198
    .line 199
    .line 200
    .line 201
    .line 202
    .line 203
    .line 204
    .line 205
    .line 206
    .line 207
    .line 208
    .line 209
    .line 210
    .line 211
    .line 212
    .line 213
    .line 214
    .line 215
    .line 216
    .line 217
    .line 218
    .line 219
    .line 220
    .line 221
    .line 222
    .line 223
    .line 224
    .line 225
    .line 226
    .line 227
    .line 228
    .line 229
    .line 230
    .line 231
    .line 232
    .line 233
    .line 234
    .line 235
    .line 236
    .line 237
    .line 238
    .line 239
    .line 240
    .line 241
    .line 242
    .line 243
    .line 244
    .line 245
    .line 246
    .line 247
    .line 248
    .line 249
    .line 250
    .line 251
    .line 252
    .line 253
    .line 254
    .line 255
    .line 256
    .line 257
    .line 258
    .line 259
    .line 260
    .line 261
    .line 262
    .line 263
    .line 264
    .line 265
    .line 266
    .line 267
    .line 268
    .line 269
    .line 270
    .line 271
    .line 272
    .line 273
    .line 274
    .line 275
    .line 276
    .line 277
    .line 278
    .line 279
    .line 280
    .line 281
    .line 282
    .line 283
    .line 284
    .line 285
    .line 286
    .line 287
    .line 288
    .line 289
    .line 290
    .line 291
    .line 292
    .line 293
    .line 294
    .line 295
    .line 296
    .line 297
    .line 298
    .line 299
    .line 300
    .line 301
    .line 302
    .line 303
    .line 304
    .line 305
    .line 306
    .line 307
    .line 308
    .line 309
    .line 310
    .line 311
    .line 312
    .line 313
    .line 314
    .line 315
    .line 316
    .line 317
    .line 318
    .line 319
    .line 320
    .line 321
    .line 322
    .line 323
    .line 324
    .line 325
    .line 326
    .line 327
    .line 328
    .line 329
    .line 330
    .line 331
    .line 332
    .line 333
    .line 334
    .line 335
    .line 336
    .line 337
    .line 338
    .line 339
    .line 340
    .line 341
    .line 342
    .line 343
    .line 344
    .line 345
    .line 346
    .line 347
    .line 348
    .line 349
    .line 350
    .line 351
    .line 352
    .line 353
    .line 354
    .line 355
    .line 356
    .line 357
    .line 358
    .line 359
    .line 360
    .line 361
    .line 362
    .line 363
    .line 364
    .line 365
    .line 366
    .line 367
    .line 368
    .line 369
    .line 370
    .line 371
    .line 372
    .line 373
    .line 374
    .line 375
    .line 376
    .line 377
    .line 378
    .line 379
    .line 380
    .line 381
    .line 382
    .line 383
    .line 384
    .line 385
    .line 386
    .line 387
    .line 388
    .line 389
    .line 390
    .line 391
    .line 392
    .line 393
    .line 394
    .line 395
    .line 396
    .line 397
    .line 398
    .line 399
    .line 400
    .line 401
    .line 402
    .line 403
    .line 404
    .line 405
    .line 406
    .line 407
    .line 408
    .line 409
    .line 410
    .line 411
    .line 412
    .line 413
    .line 414
    .line 415
    .line 416
    .line 417
    .line 418
    .line 419
    .line 420
    .line 421
    .line 422
    .line 423
    .line 424
    .line 425
    .line 426
    .line 427
    .line 428
    .line 429
    .line 430
    .line 431
    .line 432
    .line 433
    .line 434
    .line 435
    .line 436
    .line 437
    .line 438
    .line 439
    .line 440
    .line 441
    .line 442
    .line 443
    .line 444
    .line 445
    .line 446
    .line 447
    .line 448
    .line 449
    .line 450
    .line 451
    .line 452
    .line 453
    .line 454
    .line 455
    .line 456
    .line 457
    .line 458
    .line 459
    .line 460
    .line 461
    .line 462
    .line 463
    .line 464
    .line 465
    .line 466
    .line 467
    .line 468
    .line 469
    .line 470
    .line 471
    .line 472
    .line 473
    .line 474
    .line 475
    .line 476
    .line 477
    .line 478
    .line 479
    .line 480
    .line 481
    .line 482
    .line 483
    .line 484
    .line 485
    .line 486
    .line 487
    .line 488
    .line 489
    .line 490
    .line 491
    .line 492
    .line 493
    .line 494
    .line 495
    .line 496
    .line 497
    .line 498
    .line 499
    .line 500
    .line 501
    .line 502
    .line 503
    .line 504
    .line 505
    .line 506
    .line 507
    .line 508
    .line 509
    .line 510
    .line 511
    .line 512
    .line 513
    .line 514
    .line 515
    .line 516
    .line 517
    .line 518
    .line 519
    .line 520
    .line 521
    .line 522
    .line 523
    .line 524
    .line 525
    .line 526
    .line 527
    .line 528
    .line 529
    .line 530
    .line 531
    .line 532
    .line 533
    .line 534
    .line 535
    .line 536
    .line 537
    .line 538
    .line 539
    .line 540
    .line 541
    .line 542
    .line 543
    .line 544
    .line 545
    .line 546
    .line 547
    .line 548
    .line 549
    .line 550
    .line 551
    .line 552
    .line 553
    .line 554
    .line 555
    .line 556
    .line 557
    .line 558
    .line 559
    .line 560
    .line 561
    .line 562
    .line 563
    .line 564
    .line 565
    .line 566
    .line 567
    .line 568
    .line 569
    .line 570
    .line 571
    .line 572
    .line 573
    .line 574
    .line 575
    .line 576
    .line 577
    .line 578
    .line 579
    .line 580
    .line 581
    .line 582
    .line 583
    .line 584
    .line 585
    .line 586
    .line 587
    .line 588
    .line 589
    .line 590
    .line 591
    .line 592
    .line 593
    .line 594
    .line 595
    .line 596
    .line 597
    .line 598
    .line 599
    .line 600
    .line 601
    .line 602
    .line 603
    .line 604
    .line 605
    .line 606
    .line 607
    .line 608
    .line 609
    .line 610
    .line 611
    .line 612
    .line 613
    .line 614
    .line 615
    .line 616
    .line 617
    .line 618
    .line 619
    .line 620
    .line 621
    .line 622
    .line 623
    .line 624
    .line 625
    .line 626
    .line 627
    .line 628
    .line 629
    .line 630
    .line 631
    .line 632
    .line 633
    .line 634
    .line 635
    .line 636
    .line 637
    .line 638
    .line 639
    .line 640
    .line 641
    .line 642
    .line 643
    .line 644
    .line 645
    .line 646
    .line 647
    .line 648
    .line 649
    .line 650
    .line 651
    .line 652
    .line 653
    .line 654
    .line 655
    .line 656
    .line 657
    .line 658
    .line 659
    .line 660
    .line 661
    .line 662
    .line 663
    .line 664
    .line 665
    .line 666
    .line 667
    .line 668
    .line 669
    .line 670
    .line 671
    .line 672
    .line 673
    .line 674
    .line 675
    .line 676
    .line 677
    .line 678
    .line 679
    .line 680
    .line 681
    .line 682
    .line 683
    .line 684
    .line 685
    .line 686
    .line 687
    .line 688
    .line 689
    .line 690
    .line 691
    .line 692
    .line 693
    .line 694
    .line 695
    .line 696
    .line 697
    .line 698
    .line 699
    .line 700
    .line 701
    .line 702
    .line 703
    .line 704
    .line 705
    .line 706
    .line 707
    .line 708
    .line 709
    .line 710
    .line 711
    .line 712
    .line 713
    .line 714
    .line 715
    .line 716
    .line 717
    .line 718
    .line 719
    .line 720
    .line 721
    .line 722
    .line 723
    .line 724
    .line 725
    .line 726
    .line 727
    .line 728
    .line 729
    .line 730
    .line 731
    .line 732
    .line 733
    .line 734
    .line 735
    .line 736
    .line 737
    .line 738
    .line 739
    .line 740
    .line 741
    .line 742
    .line 743
    .line 744
    .line 745
    .line 746
    .line 747
    .line 748
    .line 749
    .line 750
    .line 751
    .line 752
    .line 753
    .line 754
    .line 755
    .line 756
    .line 757
    .line 758
    .line 759
    .line 760
    .line 761
    .line 762
    .line 763
    .line 764
    .line 765
    .line 766
    .line 767
    .line 768
    .line 769
    .line 770
    .line 771
    .line 772
    .line 773
    .line 774
    .line 775
    .line 776
    .line 777
    .line 778
    .line 779
    .line 780
.end method

.method public static setEnabledProtocols(JLorg/conscrypt/NativeSsl;[Ljava/lang/String;)V
    .locals 1

    .line 1
    invoke-static {p3}, Lorg/conscrypt/NativeCrypto;->checkEnabledProtocols([Ljava/lang/String;)[Ljava/lang/String;

    .line 2
    .line 3
    .line 4
    invoke-static {p3}, Lorg/conscrypt/NativeCrypto;->getProtocolRange([Ljava/lang/String;)Lorg/conscrypt/NativeCrypto$Range;

    .line 5
    .line 6
    .line 7
    move-result-object p3

    .line 8
    iget-object v0, p3, Lorg/conscrypt/NativeCrypto$Range;->min:Ljava/lang/String;

    .line 9
    .line 10
    invoke-static {v0}, Lorg/conscrypt/NativeCrypto;->getProtocolConstant(Ljava/lang/String;)I

    .line 11
    .line 12
    .line 13
    move-result v0

    .line 14
    iget-object p3, p3, Lorg/conscrypt/NativeCrypto$Range;->max:Ljava/lang/String;

    .line 15
    .line 16
    invoke-static {p3}, Lorg/conscrypt/NativeCrypto;->getProtocolConstant(Ljava/lang/String;)I

    .line 17
    .line 18
    .line 19
    move-result p3

    .line 20
    invoke-static {p0, p1, p2, v0, p3}, Lorg/conscrypt/NativeCrypto;->SSL_set_protocol_versions(JLorg/conscrypt/NativeSsl;II)I

    .line 21
    .line 22
    .line 23
    return-void
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
    .line 111
    .line 112
    .line 113
    .line 114
    .line 115
    .line 116
    .line 117
    .line 118
    .line 119
    .line 120
    .line 121
    .line 122
    .line 123
    .line 124
    .line 125
    .line 126
    .line 127
    .line 128
    .line 129
    .line 130
    .line 131
    .line 132
    .line 133
    .line 134
    .line 135
    .line 136
    .line 137
    .line 138
    .line 139
    .line 140
    .line 141
    .line 142
    .line 143
    .line 144
    .line 145
    .line 146
    .line 147
    .line 148
    .line 149
    .line 150
    .line 151
    .line 152
    .line 153
    .line 154
    .line 155
    .line 156
    .line 157
    .line 158
    .line 159
    .line 160
    .line 161
    .line 162
    .line 163
    .line 164
    .line 165
    .line 166
    .line 167
    .line 168
    .line 169
    .line 170
    .line 171
    .line 172
    .line 173
    .line 174
    .line 175
    .line 176
    .line 177
    .line 178
    .line 179
    .line 180
    .line 181
    .line 182
    .line 183
    .line 184
    .line 185
    .line 186
    .line 187
    .line 188
    .line 189
    .line 190
    .line 191
    .line 192
    .line 193
    .line 194
    .line 195
    .line 196
    .line 197
    .line 198
    .line 199
    .line 200
    .line 201
    .line 202
    .line 203
    .line 204
    .line 205
    .line 206
    .line 207
    .line 208
    .line 209
    .line 210
    .line 211
    .line 212
    .line 213
    .line 214
    .line 215
    .line 216
    .line 217
    .line 218
    .line 219
    .line 220
    .line 221
    .line 222
    .line 223
    .line 224
    .line 225
    .line 226
    .line 227
    .line 228
    .line 229
    .line 230
    .line 231
    .line 232
    .line 233
    .line 234
    .line 235
    .line 236
    .line 237
    .line 238
    .line 239
    .line 240
    .line 241
    .line 242
    .line 243
    .line 244
    .line 245
    .line 246
    .line 247
    .line 248
    .line 249
    .line 250
    .line 251
    .line 252
    .line 253
    .line 254
    .line 255
    .line 256
    .line 257
    .line 258
    .line 259
    .line 260
    .line 261
    .line 262
    .line 263
    .line 264
    .line 265
    .line 266
    .line 267
    .line 268
    .line 269
    .line 270
    .line 271
    .line 272
    .line 273
    .line 274
    .line 275
    .line 276
    .line 277
    .line 278
    .line 279
    .line 280
    .line 281
    .line 282
    .line 283
    .line 284
    .line 285
    .line 286
    .line 287
    .line 288
    .line 289
    .line 290
    .line 291
    .line 292
    .line 293
    .line 294
    .line 295
    .line 296
    .line 297
    .line 298
    .line 299
    .line 300
    .line 301
    .line 302
    .line 303
    .line 304
    .line 305
    .line 306
    .line 307
    .line 308
    .line 309
    .line 310
    .line 311
    .line 312
    .line 313
    .line 314
    .line 315
    .line 316
    .line 317
    .line 318
    .line 319
    .line 320
    .line 321
    .line 322
    .line 323
    .line 324
    .line 325
    .line 326
    .line 327
    .line 328
    .line 329
    .line 330
    .line 331
    .line 332
    .line 333
    .line 334
    .line 335
    .line 336
    .line 337
    .line 338
    .line 339
    .line 340
    .line 341
    .line 342
    .line 343
    .line 344
    .line 345
    .line 346
    .line 347
    .line 348
    .line 349
    .line 350
    .line 351
    .line 352
    .line 353
    .line 354
    .line 355
    .line 356
    .line 357
    .line 358
    .line 359
    .line 360
    .line 361
    .line 362
    .line 363
    .line 364
    .line 365
    .line 366
    .line 367
    .line 368
    .line 369
    .line 370
    .line 371
    .line 372
    .line 373
    .line 374
    .line 375
    .line 376
    .line 377
    .line 378
    .line 379
    .line 380
    .line 381
    .line 382
    .line 383
    .line 384
    .line 385
    .line 386
    .line 387
    .line 388
    .line 389
    .line 390
    .line 391
    .line 392
    .line 393
    .line 394
    .line 395
    .line 396
    .line 397
    .line 398
    .line 399
    .line 400
    .line 401
    .line 402
    .line 403
    .line 404
    .line 405
    .line 406
    .line 407
    .line 408
    .line 409
    .line 410
    .line 411
    .line 412
    .line 413
    .line 414
    .line 415
    .line 416
    .line 417
    .line 418
    .line 419
    .line 420
    .line 421
    .line 422
    .line 423
    .line 424
    .line 425
    .line 426
    .line 427
    .line 428
    .line 429
    .line 430
    .line 431
    .line 432
    .line 433
    .line 434
    .line 435
    .line 436
    .line 437
    .line 438
    .line 439
    .line 440
    .line 441
    .line 442
    .line 443
    .line 444
    .line 445
    .line 446
    .line 447
    .line 448
    .line 449
    .line 450
    .line 451
    .line 452
    .line 453
    .line 454
    .line 455
    .line 456
    .line 457
    .line 458
    .line 459
    .line 460
    .line 461
    .line 462
    .line 463
    .line 464
    .line 465
    .line 466
    .line 467
    .line 468
    .line 469
    .line 470
    .line 471
    .line 472
    .line 473
    .line 474
    .line 475
    .line 476
    .line 477
    .line 478
    .line 479
    .line 480
    .line 481
    .line 482
    .line 483
    .line 484
    .line 485
    .line 486
    .line 487
    .line 488
    .line 489
    .line 490
    .line 491
    .line 492
    .line 493
    .line 494
    .line 495
    .line 496
    .line 497
    .line 498
    .line 499
    .line 500
    .line 501
    .line 502
    .line 503
    .line 504
    .line 505
    .line 506
    .line 507
    .line 508
    .line 509
    .line 510
    .line 511
    .line 512
    .line 513
    .line 514
    .line 515
    .line 516
    .line 517
    .line 518
    .line 519
    .line 520
    .line 521
    .line 522
    .line 523
    .line 524
    .line 525
    .line 526
    .line 527
    .line 528
    .line 529
    .line 530
    .line 531
    .line 532
    .line 533
    .line 534
    .line 535
    .line 536
    .line 537
    .line 538
    .line 539
    .line 540
    .line 541
    .line 542
    .line 543
    .line 544
    .line 545
    .line 546
    .line 547
    .line 548
    .line 549
    .line 550
    .line 551
    .line 552
    .line 553
    .line 554
    .line 555
    .line 556
    .line 557
    .line 558
    .line 559
    .line 560
    .line 561
    .line 562
    .line 563
    .line 564
    .line 565
    .line 566
    .line 567
    .line 568
    .line 569
    .line 570
    .line 571
    .line 572
    .line 573
    .line 574
    .line 575
    .line 576
    .line 577
    .line 578
    .line 579
    .line 580
    .line 581
    .line 582
    .line 583
    .line 584
    .line 585
    .line 586
    .line 587
    .line 588
    .line 589
    .line 590
    .line 591
    .line 592
    .line 593
    .line 594
    .line 595
    .line 596
    .line 597
    .line 598
    .line 599
    .line 600
    .line 601
    .line 602
    .line 603
    .line 604
    .line 605
    .line 606
    .line 607
    .line 608
    .line 609
    .line 610
    .line 611
    .line 612
    .line 613
    .line 614
    .line 615
    .line 616
    .line 617
    .line 618
    .line 619
    .line 620
    .line 621
    .line 622
    .line 623
    .line 624
    .line 625
    .line 626
    .line 627
    .line 628
    .line 629
    .line 630
    .line 631
    .line 632
    .line 633
    .line 634
    .line 635
    .line 636
    .line 637
    .line 638
    .line 639
    .line 640
    .line 641
    .line 642
    .line 643
    .line 644
    .line 645
    .line 646
.end method

.method public static native setHasApplicationProtocolSelector(JLorg/conscrypt/NativeSsl;Z)V
.end method

.method public static native setLocalCertsAndPrivateKey(JLorg/conscrypt/NativeSsl;[[BLorg/conscrypt/NativeRef$EVP_PKEY;)V
.end method

.method public static native set_SSL_psk_client_callback_enabled(JLorg/conscrypt/NativeSsl;Z)V
.end method

.method public static native set_SSL_psk_server_callback_enabled(JLorg/conscrypt/NativeSsl;Z)V
.end method
