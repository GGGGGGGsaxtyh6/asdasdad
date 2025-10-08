import { NextRequest, NextResponse } from 'next/server';
import { getUserById, updateUser } from '@/lib/database';
import { getUserFromToken, sanitizeUser } from '@/lib/auth';

export async function PATCH(request: NextRequest) {
  try {
    const authHeader = request.headers.get('authorization');
    if (!authHeader || !authHeader.startsWith('Bearer ')) {
      return NextResponse.json(
        { error: 'No autorizado' },
        { status: 401 }
      );
    }

    const token = authHeader.substring(7);
    const userId = getUserFromToken(token);

    if (!userId) {
      return NextResponse.json(
        { error: 'Token inválido' },
        { status: 401 }
      );
    }

    const user = getUserById(userId);
    if (!user) {
      return NextResponse.json(
        { error: 'Usuario no encontrado' },
        { status: 404 }
      );
    }

    const body = await request.json();
    const { fullName, avatar, settings } = body;

    const updates: any = {};
    if (fullName !== undefined) updates.fullName = fullName;
    if (avatar !== undefined) updates.avatar = avatar;
    if (settings !== undefined) updates.settings = { ...user.settings, ...settings };

    const updatedUser = updateUser(userId, updates);

    if (!updatedUser) {
      return NextResponse.json(
        { error: 'Error al actualizar usuario' },
        { status: 500 }
      );
    }

    return NextResponse.json({
      success: true,
      user: sanitizeUser(updatedUser),
    });
  } catch (error) {
    console.error('Error al actualizar usuario:', error);
    return NextResponse.json(
      { error: 'Error al actualizar perfil' },
      { status: 500 }
    );
  }
}
