import { NextRequest, NextResponse } from 'next/server';
import { getTransactionsByUserId } from '@/lib/database';
import { getUserFromToken } from '@/lib/auth';

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

    const transactions = getTransactionsByUserId(userId);

    // Filtros opcionales
    const typeFilter = request.nextUrl.searchParams.get('type');
    const statusFilter = request.nextUrl.searchParams.get('status');
    const limitParam = request.nextUrl.searchParams.get('limit');

    let filteredTransactions = transactions;

    if (typeFilter) {
      filteredTransactions = filteredTransactions.filter(t => t.type === typeFilter);
    }

    if (statusFilter) {
      filteredTransactions = filteredTransactions.filter(t => t.status === statusFilter);
    }

    if (limitParam) {
      const limit = parseInt(limitParam, 10);
      filteredTransactions = filteredTransactions.slice(0, limit);
    }

    return NextResponse.json({
      success: true,
      transactions: filteredTransactions,
    });
  } catch (error) {
    console.error('Error al obtener transacciones:', error);
    return NextResponse.json(
      { error: 'Error al obtener historial' },
      { status: 500 }
    );
  }
}
