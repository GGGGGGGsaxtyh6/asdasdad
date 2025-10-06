.class public final Lk/I;
.super Lk/D;
.source "SourceFile"


# instance fields
.field public final e:Lk/H;

.field public f:Landroid/graphics/drawable/Drawable;

.field public g:Landroid/content/res/ColorStateList;

.field public h:Landroid/graphics/PorterDuff$Mode;

.field public i:Z

.field public j:Z


# direct methods
.method public constructor <init>(Lk/H;)V
    .locals 1

    .line 1
    invoke-direct {p0, p1}, Lk/D;-><init>(Landroid/widget/AbsSeekBar;)V

    .line 2
    .line 3
    .line 4
    const/4 v0, 0x0

    .line 5
    iput-object v0, p0, Lk/I;->g:Landroid/content/res/ColorStateList;

    .line 6
    .line 7
    iput-object v0, p0, Lk/I;->h:Landroid/graphics/PorterDuff$Mode;

    .line 8
    .line 9
    const/4 v0, 0x0

    .line 10
    iput-boolean v0, p0, Lk/I;->i:Z

    .line 11
    .line 12
    iput-boolean v0, p0, Lk/I;->j:Z

    .line 13
    .line 14
    iput-object p1, p0, Lk/I;->e:Lk/H;

    .line 15
    .line 16
    return-void
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
.method public final b(Landroid/util/AttributeSet;I)V
    .locals 7

    .line 1
    const v5, 0x7f0301b0

    .line 2
    .line 3
    .line 4
    invoke-super {p0, p1, v5}, Lk/D;->b(Landroid/util/AttributeSet;I)V

    .line 5
    .line 6
    .line 7
    iget-object p2, p0, Lk/I;->e:Lk/H;

    .line 8
    .line 9
    invoke-virtual {p2}, Landroid/view/View;->getContext()Landroid/content/Context;

    .line 10
    .line 11
    .line 12
    move-result-object v0

    .line 13
    sget-object v2, Lf/a;->g:[I

    .line 14
    .line 15
    invoke-static {v0, p1, v2, v5}, LD0/h;->p(Landroid/content/Context;Landroid/util/AttributeSet;[II)LD0/h;

    .line 16
    .line 17
    .line 18
    move-result-object v6

    .line 19
    invoke-virtual {p2}, Landroid/view/View;->getContext()Landroid/content/Context;

    .line 20
    .line 21
    .line 22
    move-result-object v1

    .line 23
    iget-object v0, v6, LD0/h;->c:Ljava/lang/Object;

    .line 24
    .line 25
    move-object v4, v0

    .line 26
    check-cast v4, Landroid/content/res/TypedArray;

    .line 27
    .line 28
    move-object v0, p2

    .line 29
    move-object v3, p1

    .line 30
    invoke-static/range {v0 .. v5}, LH/N;->g(Landroid/view/View;Landroid/content/Context;[ILandroid/util/AttributeSet;Landroid/content/res/TypedArray;I)V

    .line 31
    .line 32
    .line 33
    const/4 p1, 0x0

    .line 34
    invoke-virtual {v6, p1}, LD0/h;->k(I)Landroid/graphics/drawable/Drawable;

    .line 35
    .line 36
    .line 37
    move-result-object p1

    .line 38
    if-eqz p1, :cond_0

    .line 39
    .line 40
    invoke-virtual {p2, p1}, Landroid/widget/AbsSeekBar;->setThumb(Landroid/graphics/drawable/Drawable;)V

    .line 41
    .line 42
    .line 43
    :cond_0
    const/4 p1, 0x1

    .line 44
    invoke-virtual {v6, p1}, LD0/h;->j(I)Landroid/graphics/drawable/Drawable;

    .line 45
    .line 46
    .line 47
    move-result-object v0

    .line 48
    iget-object v1, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 49
    .line 50
    if-eqz v1, :cond_1

    .line 51
    .line 52
    const/4 v2, 0x0

    .line 53
    invoke-virtual {v1, v2}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 54
    .line 55
    .line 56
    :cond_1
    iput-object v0, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 57
    .line 58
    if-eqz v0, :cond_3

    .line 59
    .line 60
    invoke-virtual {v0, p2}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 61
    .line 62
    .line 63
    invoke-virtual {p2}, Landroid/view/View;->getLayoutDirection()I

    .line 64
    .line 65
    .line 66
    move-result v1

    .line 67
    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setLayoutDirection(I)Z

    .line 68
    .line 69
    .line 70
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->isStateful()Z

    .line 71
    .line 72
    .line 73
    move-result v1

    .line 74
    if-eqz v1, :cond_2

    .line 75
    .line 76
    invoke-virtual {p2}, Landroid/view/View;->getDrawableState()[I

    .line 77
    .line 78
    .line 79
    move-result-object v1

    .line 80
    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 81
    .line 82
    .line 83
    :cond_2
    invoke-virtual {p0}, Lk/I;->f()V

    .line 84
    .line 85
    .line 86
    :cond_3
    invoke-virtual {p2}, Landroid/view/View;->invalidate()V

    .line 87
    .line 88
    .line 89
    iget-object p2, v6, LD0/h;->c:Ljava/lang/Object;

    .line 90
    .line 91
    check-cast p2, Landroid/content/res/TypedArray;

    .line 92
    .line 93
    const/4 v0, 0x3

    .line 94
    invoke-virtual {p2, v0}, Landroid/content/res/TypedArray;->hasValue(I)Z

    .line 95
    .line 96
    .line 97
    move-result v1

    .line 98
    if-eqz v1, :cond_4

    .line 99
    .line 100
    const/4 v1, -0x1

    .line 101
    invoke-virtual {p2, v0, v1}, Landroid/content/res/TypedArray;->getInt(II)I

    .line 102
    .line 103
    .line 104
    move-result v0

    .line 105
    iget-object v1, p0, Lk/I;->h:Landroid/graphics/PorterDuff$Mode;

    .line 106
    .line 107
    invoke-static {v0, v1}, Lk/n0;->b(ILandroid/graphics/PorterDuff$Mode;)Landroid/graphics/PorterDuff$Mode;

    .line 108
    .line 109
    .line 110
    move-result-object v0

    .line 111
    iput-object v0, p0, Lk/I;->h:Landroid/graphics/PorterDuff$Mode;

    .line 112
    .line 113
    iput-boolean p1, p0, Lk/I;->j:Z

    .line 114
    .line 115
    :cond_4
    const/4 v0, 0x2

    .line 116
    invoke-virtual {p2, v0}, Landroid/content/res/TypedArray;->hasValue(I)Z

    .line 117
    .line 118
    .line 119
    move-result p2

    .line 120
    if-eqz p2, :cond_5

    .line 121
    .line 122
    invoke-virtual {v6, v0}, LD0/h;->i(I)Landroid/content/res/ColorStateList;

    .line 123
    .line 124
    .line 125
    move-result-object p2

    .line 126
    iput-object p2, p0, Lk/I;->g:Landroid/content/res/ColorStateList;

    .line 127
    .line 128
    iput-boolean p1, p0, Lk/I;->i:Z

    .line 129
    .line 130
    :cond_5
    invoke-virtual {v6}, LD0/h;->r()V

    .line 131
    .line 132
    .line 133
    invoke-virtual {p0}, Lk/I;->f()V

    .line 134
    .line 135
    .line 136
    return-void
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
.end method

.method public final f()V
    .locals 2

    .line 1
    iget-object v0, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 2
    .line 3
    if-eqz v0, :cond_3

    .line 4
    .line 5
    iget-boolean v1, p0, Lk/I;->i:Z

    .line 6
    .line 7
    if-nez v1, :cond_0

    .line 8
    .line 9
    iget-boolean v1, p0, Lk/I;->j:Z

    .line 10
    .line 11
    if-eqz v1, :cond_3

    .line 12
    .line 13
    :cond_0
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    .line 14
    .line 15
    .line 16
    move-result-object v0

    .line 17
    iput-object v0, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 18
    .line 19
    iget-boolean v1, p0, Lk/I;->i:Z

    .line 20
    .line 21
    if-eqz v1, :cond_1

    .line 22
    .line 23
    iget-object v1, p0, Lk/I;->g:Landroid/content/res/ColorStateList;

    .line 24
    .line 25
    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setTintList(Landroid/content/res/ColorStateList;)V

    .line 26
    .line 27
    .line 28
    :cond_1
    iget-boolean v0, p0, Lk/I;->j:Z

    .line 29
    .line 30
    if-eqz v0, :cond_2

    .line 31
    .line 32
    iget-object v0, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 33
    .line 34
    iget-object v1, p0, Lk/I;->h:Landroid/graphics/PorterDuff$Mode;

    .line 35
    .line 36
    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setTintMode(Landroid/graphics/PorterDuff$Mode;)V

    .line 37
    .line 38
    .line 39
    :cond_2
    iget-object v0, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 40
    .line 41
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->isStateful()Z

    .line 42
    .line 43
    .line 44
    move-result v0

    .line 45
    if-eqz v0, :cond_3

    .line 46
    .line 47
    iget-object v0, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 48
    .line 49
    iget-object v1, p0, Lk/I;->e:Lk/H;

    .line 50
    .line 51
    invoke-virtual {v1}, Landroid/view/View;->getDrawableState()[I

    .line 52
    .line 53
    .line 54
    move-result-object v1

    .line 55
    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 56
    .line 57
    .line 58
    :cond_3
    return-void
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

.method public final g(Landroid/graphics/Canvas;)V
    .locals 7

    .line 1
    iget-object v0, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 2
    .line 3
    if-eqz v0, :cond_3

    .line 4
    .line 5
    iget-object v0, p0, Lk/I;->e:Lk/H;

    .line 6
    .line 7
    invoke-virtual {v0}, Landroid/widget/ProgressBar;->getMax()I

    .line 8
    .line 9
    .line 10
    move-result v1

    .line 11
    const/4 v2, 0x1

    .line 12
    if-le v1, v2, :cond_3

    .line 13
    .line 14
    iget-object v3, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 15
    .line 16
    invoke-virtual {v3}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    .line 17
    .line 18
    .line 19
    move-result v3

    .line 20
    iget-object v4, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 21
    .line 22
    invoke-virtual {v4}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    .line 23
    .line 24
    .line 25
    move-result v4

    .line 26
    if-ltz v3, :cond_0

    .line 27
    .line 28
    div-int/lit8 v3, v3, 0x2

    .line 29
    .line 30
    goto :goto_0

    .line 31
    :cond_0
    move v3, v2

    .line 32
    :goto_0
    if-ltz v4, :cond_1

    .line 33
    .line 34
    div-int/lit8 v2, v4, 0x2

    .line 35
    .line 36
    :cond_1
    iget-object v4, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 37
    .line 38
    neg-int v5, v3

    .line 39
    neg-int v6, v2

    .line 40
    invoke-virtual {v4, v5, v6, v3, v2}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 41
    .line 42
    .line 43
    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    .line 44
    .line 45
    .line 46
    move-result v2

    .line 47
    invoke-virtual {v0}, Landroid/view/View;->getPaddingLeft()I

    .line 48
    .line 49
    .line 50
    move-result v3

    .line 51
    sub-int/2addr v2, v3

    .line 52
    invoke-virtual {v0}, Landroid/view/View;->getPaddingRight()I

    .line 53
    .line 54
    .line 55
    move-result v3

    .line 56
    sub-int/2addr v2, v3

    .line 57
    int-to-float v2, v2

    .line 58
    int-to-float v3, v1

    .line 59
    div-float/2addr v2, v3

    .line 60
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 61
    .line 62
    .line 63
    move-result v3

    .line 64
    invoke-virtual {v0}, Landroid/view/View;->getPaddingLeft()I

    .line 65
    .line 66
    .line 67
    move-result v4

    .line 68
    int-to-float v4, v4

    .line 69
    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    .line 70
    .line 71
    .line 72
    move-result v0

    .line 73
    div-int/lit8 v0, v0, 0x2

    .line 74
    .line 75
    int-to-float v0, v0

    .line 76
    invoke-virtual {p1, v4, v0}, Landroid/graphics/Canvas;->translate(FF)V

    .line 77
    .line 78
    .line 79
    const/4 v0, 0x0

    .line 80
    :goto_1
    if-gt v0, v1, :cond_2

    .line 81
    .line 82
    iget-object v4, p0, Lk/I;->f:Landroid/graphics/drawable/Drawable;

    .line 83
    .line 84
    invoke-virtual {v4, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 85
    .line 86
    .line 87
    const/4 v4, 0x0

    .line 88
    invoke-virtual {p1, v2, v4}, Landroid/graphics/Canvas;->translate(FF)V

    .line 89
    .line 90
    .line 91
    add-int/lit8 v0, v0, 0x1

    .line 92
    .line 93
    goto :goto_1

    .line 94
    :cond_2
    invoke-virtual {p1, v3}, Landroid/graphics/Canvas;->restoreToCount(I)V

    .line 95
    .line 96
    .line 97
    :cond_3
    return-void
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
