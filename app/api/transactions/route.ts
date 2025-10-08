import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { prisma } from '@/lib/prisma';

export async function GET(req: NextRequest) {
  try {
    const session = await getServerSession(authOptions);

    if (!session || !session.user) {
      return NextResponse.json(
        { error: 'Unauthorized' },
        { status: 401 }
      );
    }

    const { searchParams } = new URL(req.url);
    const limit = parseInt(searchParams.get('limit') || '10');
    const offset = parseInt(searchParams.get('offset') || '0');
    const type = searchParams.get('type');
    const status = searchParams.get('status');

    const userId = (session.user as any).id;

    const where: any = {
      OR: [
        { senderId: userId },
        { receiverId: userId }
      ]
    };

    if (type) where.type = type;
    if (status) where.status = status;

    const transactions = await prisma.transaction.findMany({
      where,
      include: {
        sender: {
          select: { id: true, name: true, username: true, avatar: true }
        },
        receiver: {
          select: { id: true, name: true, username: true, avatar: true }
        }
      },
      orderBy: { createdAt: 'desc' },
      take: limit,
      skip: offset,
    });

    const total = await prisma.transaction.count({ where });

    return NextResponse.json({
      transactions,
      total,
      hasMore: offset + limit < total,
    });
  } catch (error) {
    console.error('Transactions fetch error:', error);
    return NextResponse.json(
      { error: 'Internal server error' },
      { status: 500 }
    );
  }
}

export async function POST(req: NextRequest) {
  try {
    const session = await getServerSession(authOptions);

    if (!session || !session.user) {
      return NextResponse.json(
        { error: 'Unauthorized' },
        { status: 401 }
      );
    }

    const { receiverUsername, amount, description } = await req.json();

    if (!receiverUsername || !amount || amount <= 0) {
      return NextResponse.json(
        { error: 'Invalid transaction data' },
        { status: 400 }
      );
    }

    const senderId = (session.user as any).id;

    // Get sender
    const sender = await prisma.user.findUnique({
      where: { id: senderId }
    });

    if (!sender) {
      return NextResponse.json(
        { error: 'Sender not found' },
        { status: 404 }
      );
    }

    // Get receiver
    const receiver = await prisma.user.findUnique({
      where: { username: receiverUsername }
    });

    if (!receiver) {
      return NextResponse.json(
        { error: 'Receiver not found' },
        { status: 404 }
      );
    }

    if (sender.id === receiver.id) {
      return NextResponse.json(
        { error: 'Cannot send to yourself' },
        { status: 400 }
      );
    }

    // Check balance
    if (sender.balance < amount) {
      return NextResponse.json(
        { error: 'Insufficient balance' },
        { status: 400 }
      );
    }

    // Create transaction and update balances
    const transaction = await prisma.$transaction(async (tx) => {
      // Update sender balance
      await tx.user.update({
        where: { id: senderId },
        data: { balance: { decrement: amount } }
      });

      // Update receiver balance
      await tx.user.update({
        where: { id: receiver.id },
        data: { balance: { increment: amount } }
      });

      // Create transaction record
      const newTransaction = await tx.transaction.create({
        data: {
          senderId,
          receiverId: receiver.id,
          amount,
          type: 'send',
          status: 'completed',
          description,
        },
        include: {
          sender: {
            select: { id: true, name: true, username: true, avatar: true }
          },
          receiver: {
            select: { id: true, name: true, username: true, avatar: true }
          }
        }
      });

      // Create notification for receiver
      await tx.notification.create({
        data: {
          userId: receiver.id,
          title: 'Money Received',
          message: `You received ${amount} Smurf from ${sender.name}`,
          type: 'transaction',
        }
      });

      return newTransaction;
    });

    return NextResponse.json(transaction, { status: 201 });
  } catch (error) {
    console.error('Transaction error:', error);
    return NextResponse.json(
      { error: 'Internal server error' },
      { status: 500 }
    );
  }
}
