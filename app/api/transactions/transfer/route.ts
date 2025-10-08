import { NextRequest, NextResponse } from 'next/server';
import { getUserById, updateUser, createTransaction, createNotification } from '@/lib/database';
import { getUserFromToken } from '@/lib/auth';

export async function POST(request: NextRequest) {
  try {
    const authHeader = request.headers.get('authorization');
    if (!authHeader || !authHeader.startsWith('Bearer ')) {
      return NextResponse.json(
        { error: 'No autorizado' },
        { status: 401 }
      );
    }

    const token = authHeader.substring(7);
    const fromUserId = getUserFromToken(token);

    if (!fromUserId) {
      return NextResponse.json(
        { error: 'Token inválido' },
        { status: 401 }
      );
    }

    const body = await request.json();
    const { toUserId, amount, note } = body;

    if (!toUserId || !amount || amount <= 0) {
      return NextResponse.json(
        { error: 'Datos inválidos' },
        { status: 400 }
      );
    }

    const fromUser = getUserById(fromUserId);
    const toUser = getUserById(toUserId);

    if (!fromUser || !toUser) {
      return NextResponse.json(
        { error: 'Usuario no encontrado' },
        { status: 404 }
      );
    }

    if (fromUser.balance < amount) {
      return NextResponse.json(
        { error: 'Saldo insuficiente' },
        { status: 400 }
      );
    }

    // Actualizar balances
    updateUser(fromUserId, { balance: fromUser.balance - amount });
    updateUser(toUserId, { balance: toUser.balance + amount });

    // Crear transacciones
    const transaction = createTransaction({
      type: 'transfer',
      amount,
      from: fromUserId,
      to: toUserId,
      fromName: fromUser.fullName,
      toName: toUser.fullName,
      note,
      status: 'completed',
    });

    // Crear notificaciones
    createNotification({
      userId: fromUserId,
      type: 'transfer',
      title: 'Transferencia enviada',
      message: `Has enviado ${amount.toFixed(2)} Smurf a ${toUser.fullName}`,
      read: false,
    });

    createNotification({
      userId: toUserId,
      type: 'received',
      title: 'Transferencia recibida',
      message: `Has recibido ${amount.toFixed(2)} Smurf de ${fromUser.fullName}`,
      read: false,
    });

    return NextResponse.json({
      success: true,
      transaction,
    });
  } catch (error) {
    console.error('Error en transferencia:', error);
    return NextResponse.json(
      { error: 'Error al realizar la transferencia' },
      { status: 500 }
    );
  }
}
