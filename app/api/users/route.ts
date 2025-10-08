import { NextRequest, NextResponse } from 'next/server';
import { getUsers } from '@/lib/database';
import { getUserFromToken, sanitizeUser } from '@/lib/auth';

export async function GET(request: NextRequest) {
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

    const searchQuery = request.nextUrl.searchParams.get('search');
    let users = getUsers();

    if (searchQuery) {
      const query = searchQuery.toLowerCase();
      users = users.filter(
        u =>
          u.username.toLowerCase().includes(query) ||
          u.fullName.toLowerCase().includes(query) ||
          u.email.toLowerCase().includes(query)
      );
    }

    // No devolver al usuario actual ni las contraseñas
    const sanitizedUsers = users
      .filter(u => u.id !== userId)
      .map(u => sanitizeUser(u))
      .slice(0, 10); // Limitar a 10 resultados

    return NextResponse.json({
      success: true,
      users: sanitizedUsers,
    });
  } catch (error) {
    console.error('Error al buscar usuarios:', error);
    return NextResponse.json(
      { error: 'Error al buscar usuarios' },
      { status: 500 }
    );
  }
}
