.class public final Ln/l;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Cloneable;


# instance fields
.field public synthetic a:[I

.field public synthetic b:[Ljava/lang/Object;

.field public synthetic c:I


# direct methods
.method public constructor <init>()V
    .locals 4

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2
    .line 3
    .line 4
    const/4 v0, 0x4

    .line 5
    move v1, v0

    .line 6
    :goto_0
    const/16 v2, 0x20

    .line 7
    .line 8
    const/16 v3, 0x28

    .line 9
    .line 10
    if-ge v1, v2, :cond_1

    .line 11
    .line 12
    const/4 v2, 0x1

    .line 13
    shl-int/2addr v2, v1

    .line 14
    add-int/lit8 v2, v2, -0xc

    .line 15
    .line 16
    if-gt v3, v2, :cond_0

    .line 17
    .line 18
    move v3, v2

    .line 19
    goto :goto_1

    .line 20
    :cond_0
    add-int/lit8 v1, v1, 0x1

    .line 21
    .line 22
    goto :goto_0

    .line 23
    :cond_1
    :goto_1
    div-int/2addr v3, v0

    .line 24
    new-array v0, v3, [I

    .line 25
    .line 26
    iput-object v0, p0, Ln/l;->a:[I

    .line 27
    .line 28
    new-array v0, v3, [Ljava/lang/Object;

    .line 29
    .line 30
    iput-object v0, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 31
    .line 32
    return-void
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
.method public final a(ILjava/lang/Object;)V
    .locals 6

    .line 1
    iget v0, p0, Ln/l;->c:I

    .line 2
    .line 3
    if-eqz v0, :cond_6

    .line 4
    .line 5
    iget-object v1, p0, Ln/l;->a:[I

    .line 6
    .line 7
    add-int/lit8 v2, v0, -0x1

    .line 8
    .line 9
    aget v1, v1, v2

    .line 10
    .line 11
    if-gt p1, v1, :cond_6

    .line 12
    .line 13
    iget-object v0, p0, Ln/l;->a:[I

    .line 14
    .line 15
    iget v1, p0, Ln/l;->c:I

    .line 16
    .line 17
    invoke-static {v1, p1, v0}, Lo/a;->a(II[I)I

    .line 18
    .line 19
    .line 20
    move-result v0

    .line 21
    if-ltz v0, :cond_0

    .line 22
    .line 23
    iget-object p1, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 24
    .line 25
    aput-object p2, p1, v0

    .line 26
    .line 27
    goto/16 :goto_4

    .line 28
    .line 29
    :cond_0
    not-int v0, v0

    .line 30
    iget v1, p0, Ln/l;->c:I

    .line 31
    .line 32
    if-ge v0, v1, :cond_1

    .line 33
    .line 34
    iget-object v2, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 35
    .line 36
    aget-object v3, v2, v0

    .line 37
    .line 38
    sget-object v4, Ln/i;->b:Ljava/lang/Object;

    .line 39
    .line 40
    if-ne v3, v4, :cond_1

    .line 41
    .line 42
    iget-object v1, p0, Ln/l;->a:[I

    .line 43
    .line 44
    aput p1, v1, v0

    .line 45
    .line 46
    aput-object p2, v2, v0

    .line 47
    .line 48
    goto/16 :goto_4

    .line 49
    .line 50
    :cond_1
    iget-object v2, p0, Ln/l;->a:[I

    .line 51
    .line 52
    array-length v2, v2

    .line 53
    const/4 v3, 0x1

    .line 54
    if-lt v1, v2, :cond_4

    .line 55
    .line 56
    add-int/2addr v1, v3

    .line 57
    const/4 v2, 0x4

    .line 58
    mul-int/2addr v1, v2

    .line 59
    move v4, v2

    .line 60
    :goto_0
    const/16 v5, 0x20

    .line 61
    .line 62
    if-ge v4, v5, :cond_3

    .line 63
    .line 64
    shl-int v5, v3, v4

    .line 65
    .line 66
    add-int/lit8 v5, v5, -0xc

    .line 67
    .line 68
    if-gt v1, v5, :cond_2

    .line 69
    .line 70
    move v1, v5

    .line 71
    goto :goto_1

    .line 72
    :cond_2
    add-int/lit8 v4, v4, 0x1

    .line 73
    .line 74
    goto :goto_0

    .line 75
    :cond_3
    :goto_1
    div-int/2addr v1, v2

    .line 76
    iget-object v2, p0, Ln/l;->a:[I

    .line 77
    .line 78
    invoke-static {v2, v1}, Ljava/util/Arrays;->copyOf([II)[I

    .line 79
    .line 80
    .line 81
    move-result-object v2

    .line 82
    const-string v4, "copyOf(this, newSize)"

    .line 83
    .line 84
    invoke-static {v2, v4}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 85
    .line 86
    .line 87
    iput-object v2, p0, Ln/l;->a:[I

    .line 88
    .line 89
    iget-object v2, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 90
    .line 91
    invoke-static {v2, v1}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    .line 92
    .line 93
    .line 94
    move-result-object v1

    .line 95
    invoke-static {v1, v4}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 96
    .line 97
    .line 98
    iput-object v1, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 99
    .line 100
    :cond_4
    iget v1, p0, Ln/l;->c:I

    .line 101
    .line 102
    sub-int v2, v1, v0

    .line 103
    .line 104
    if-eqz v2, :cond_5

    .line 105
    .line 106
    iget-object v2, p0, Ln/l;->a:[I

    .line 107
    .line 108
    add-int/lit8 v4, v0, 0x1

    .line 109
    .line 110
    invoke-static {v4, v0, v1, v2, v2}, Lb0/g;->w(III[I[I)V

    .line 111
    .line 112
    .line 113
    iget-object v1, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 114
    .line 115
    iget v2, p0, Ln/l;->c:I

    .line 116
    .line 117
    invoke-static {v1, v1, v4, v0, v2}, Lb0/g;->y([Ljava/lang/Object;[Ljava/lang/Object;III)V

    .line 118
    .line 119
    .line 120
    :cond_5
    iget-object v1, p0, Ln/l;->a:[I

    .line 121
    .line 122
    aput p1, v1, v0

    .line 123
    .line 124
    iget-object p1, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 125
    .line 126
    aput-object p2, p1, v0

    .line 127
    .line 128
    iget p1, p0, Ln/l;->c:I

    .line 129
    .line 130
    add-int/2addr p1, v3

    .line 131
    iput p1, p0, Ln/l;->c:I

    .line 132
    .line 133
    goto :goto_4

    .line 134
    :cond_6
    iget-object v1, p0, Ln/l;->a:[I

    .line 135
    .line 136
    array-length v1, v1

    .line 137
    const/4 v2, 0x1

    .line 138
    if-lt v0, v1, :cond_9

    .line 139
    .line 140
    add-int/lit8 v1, v0, 0x1

    .line 141
    .line 142
    const/4 v3, 0x4

    .line 143
    mul-int/2addr v1, v3

    .line 144
    move v4, v3

    .line 145
    :goto_2
    const/16 v5, 0x20

    .line 146
    .line 147
    if-ge v4, v5, :cond_8

    .line 148
    .line 149
    shl-int v5, v2, v4

    .line 150
    .line 151
    add-int/lit8 v5, v5, -0xc

    .line 152
    .line 153
    if-gt v1, v5, :cond_7

    .line 154
    .line 155
    move v1, v5

    .line 156
    goto :goto_3

    .line 157
    :cond_7
    add-int/lit8 v4, v4, 0x1

    .line 158
    .line 159
    goto :goto_2

    .line 160
    :cond_8
    :goto_3
    div-int/2addr v1, v3

    .line 161
    iget-object v3, p0, Ln/l;->a:[I

    .line 162
    .line 163
    invoke-static {v3, v1}, Ljava/util/Arrays;->copyOf([II)[I

    .line 164
    .line 165
    .line 166
    move-result-object v3

    .line 167
    const-string v4, "copyOf(this, newSize)"

    .line 168
    .line 169
    invoke-static {v3, v4}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 170
    .line 171
    .line 172
    iput-object v3, p0, Ln/l;->a:[I

    .line 173
    .line 174
    iget-object v3, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 175
    .line 176
    invoke-static {v3, v1}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    .line 177
    .line 178
    .line 179
    move-result-object v1

    .line 180
    invoke-static {v1, v4}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 181
    .line 182
    .line 183
    iput-object v1, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 184
    .line 185
    :cond_9
    iget-object v1, p0, Ln/l;->a:[I

    .line 186
    .line 187
    aput p1, v1, v0

    .line 188
    .line 189
    iget-object p1, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 190
    .line 191
    aput-object p2, p1, v0

    .line 192
    .line 193
    add-int/2addr v0, v2

    .line 194
    iput v0, p0, Ln/l;->c:I

    .line 195
    .line 196
    :goto_4
    return-void
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
.end method

.method public final clone()Ljava/lang/Object;
    .locals 2

    .line 1
    invoke-super {p0}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    .line 2
    .line 3
    .line 4
    move-result-object v0

    .line 5
    const-string v1, "null cannot be cast to non-null type androidx.collection.SparseArrayCompat<E of androidx.collection.SparseArrayCompat>"

    .line 6
    .line 7
    invoke-static {v0, v1}, Lj0/d;->c(Ljava/lang/Object;Ljava/lang/String;)V

    .line 8
    .line 9
    .line 10
    check-cast v0, Ln/l;

    .line 11
    .line 12
    iget-object v1, p0, Ln/l;->a:[I

    .line 13
    .line 14
    invoke-virtual {v1}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    .line 15
    .line 16
    .line 17
    move-result-object v1

    .line 18
    check-cast v1, [I

    .line 19
    .line 20
    iput-object v1, v0, Ln/l;->a:[I

    .line 21
    .line 22
    iget-object v1, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 23
    .line 24
    invoke-virtual {v1}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    .line 25
    .line 26
    .line 27
    move-result-object v1

    .line 28
    check-cast v1, [Ljava/lang/Object;

    .line 29
    .line 30
    iput-object v1, v0, Ln/l;->b:[Ljava/lang/Object;

    .line 31
    .line 32
    return-object v0
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

.method public final toString()Ljava/lang/String;
    .locals 4

    .line 1
    iget v0, p0, Ln/l;->c:I

    .line 2
    .line 3
    if-gtz v0, :cond_0

    .line 4
    .line 5
    const-string v0, "{}"

    .line 6
    .line 7
    goto :goto_2

    .line 8
    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    .line 9
    .line 10
    mul-int/lit8 v0, v0, 0x1c

    .line 11
    .line 12
    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    .line 13
    .line 14
    .line 15
    const/16 v0, 0x7b

    .line 16
    .line 17
    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 18
    .line 19
    .line 20
    iget v0, p0, Ln/l;->c:I

    .line 21
    .line 22
    const/4 v2, 0x0

    .line 23
    :goto_0
    if-ge v2, v0, :cond_3

    .line 24
    .line 25
    if-lez v2, :cond_1

    .line 26
    .line 27
    const-string v3, ", "

    .line 28
    .line 29
    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 30
    .line 31
    .line 32
    :cond_1
    iget-object v3, p0, Ln/l;->a:[I

    .line 33
    .line 34
    aget v3, v3, v2

    .line 35
    .line 36
    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 37
    .line 38
    .line 39
    const/16 v3, 0x3d

    .line 40
    .line 41
    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 42
    .line 43
    .line 44
    iget-object v3, p0, Ln/l;->b:[Ljava/lang/Object;

    .line 45
    .line 46
    aget-object v3, v3, v2

    .line 47
    .line 48
    if-eq v3, p0, :cond_2

    .line 49
    .line 50
    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 51
    .line 52
    .line 53
    goto :goto_1

    .line 54
    :cond_2
    const-string v3, "(this Map)"

    .line 55
    .line 56
    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 57
    .line 58
    .line 59
    :goto_1
    add-int/lit8 v2, v2, 0x1

    .line 60
    .line 61
    goto :goto_0

    .line 62
    :cond_3
    const/16 v0, 0x7d

    .line 63
    .line 64
    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 65
    .line 66
    .line 67
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    .line 68
    .line 69
    .line 70
    move-result-object v0

    .line 71
    const-string v1, "buffer.toString()"

    .line 72
    .line 73
    invoke-static {v0, v1}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 74
    .line 75
    .line 76
    :goto_2
    return-object v0
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
