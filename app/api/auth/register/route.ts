import { NextRequest, NextResponse } from 'next/server';
import { createUser, getUserByEmail, getUserByUsername } from '@/lib/database';
import { hashPassword, generateToken, sanitizeUser } from '@/lib/auth';

export async function POST(request: NextRequest) {
  try {
    const body = await request.json();
    const { email, username, password, fullName } = body;

    // Validaciones
    if (!email || !username || !password || !fullName) {
      return NextResponse.json(
        { error: 'Todos los campos son obligatorios' },
        { status: 400 }
      );
    }

    // Validar email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      return NextResponse.json(
        { error: 'Email inválido' },
        { status: 400 }
      );
    }

    // Validar contraseña
    if (password.length < 8) {
      return NextResponse.json(
        { error: 'La contraseña debe tener al menos 8 caracteres' },
        { status: 400 }
      );
    }

    // Verificar si el usuario ya existe
    if (getUserByEmail(email)) {
      return NextResponse.json(
        { error: 'El email ya está registrado' },
        { status: 400 }
      );
    }

    if (getUserByUsername(username)) {
      return NextResponse.json(
        { error: 'El nombre de usuario ya está en uso' },
        { status: 400 }
      );
    }

    // Hash de la contraseña
    const hashedPassword = await hashPassword(password);

    // Crear usuario
    const user = createUser({
      email,
      username,
      password: hashedPassword,
      fullName,
      settings: {
        theme: 'dark',
        language: 'es',
        timezone: 'Europe/Madrid',
        reducedMotion: false,
        twoFactorEnabled: false,
      },
    });

    // Generar token
    const token = generateToken(user.id);

    return NextResponse.json({
      success: true,
      user: sanitizeUser(user),
      token,
    });
  } catch (error) {
    console.error('Error en registro:', error);
    return NextResponse.json(
      { error: 'Error al crear la cuenta' },
      { status: 500 }
    );
  }
}
