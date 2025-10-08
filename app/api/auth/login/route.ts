import { NextRequest, NextResponse } from 'next/server';
import { getUserByEmail, getUserByUsername } from '@/lib/database';
import { verifyPassword, generateToken, sanitizeUser } from '@/lib/auth';

export async function POST(request: NextRequest) {
  try {
    const body = await request.json();
    const { identifier, password } = body; // identifier puede ser email o username

    if (!identifier || !password) {
      return NextResponse.json(
        { error: 'Email/usuario y contraseña son obligatorios' },
        { status: 400 }
      );
    }

    // Buscar usuario por email o username
    const user = getUserByEmail(identifier) || getUserByUsername(identifier);

    if (!user) {
      return NextResponse.json(
        { error: 'Credenciales incorrectas' },
        { status: 401 }
      );
    }

    // Verificar contraseña
    const isValidPassword = await verifyPassword(password, user.password);

    if (!isValidPassword) {
      return NextResponse.json(
        { error: 'Credenciales incorrectas' },
        { status: 401 }
      );
    }

    // Generar token
    const token = generateToken(user.id);

    return NextResponse.json({
      success: true,
      user: sanitizeUser(user),
      token,
    });
  } catch (error) {
    console.error('Error en login:', error);
    return NextResponse.json(
      { error: 'Error al iniciar sesión' },
      { status: 500 }
    );
  }
}
