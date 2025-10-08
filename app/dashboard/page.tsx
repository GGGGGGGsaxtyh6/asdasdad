'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Navbar from '@/components/layout/Navbar';
import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import { formatCurrency, formatDateShort } from '@/lib/utils';
import { ArrowUpRight, ArrowDownRight, TrendingUp, Send, Clock } from 'lucide-react';
import Link from 'next/link';

export default function DashboardPage() {
  const router = useRouter();
  const [user, setUser] = useState<any>(null);
  const [transactions, setTransactions] = useState<any[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [balanceChange, setBalanceChange] = useState(0);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      router.push('/login');
      return;
    }

    fetchData(token);
  }, [router]);

  const fetchData = async (token: string) => {
    try {
      const [userRes, transactionsRes] = await Promise.all([
        fetch('/api/auth/me', {
          headers: { Authorization: `Bearer ${token}` },
        }),
        fetch('/api/transactions?limit=5', {
          headers: { Authorization: `Bearer ${token}` },
        }),
      ]);

      if (!userRes.ok || !transactionsRes.ok) {
        localStorage.removeItem('token');
        router.push('/login');
        return;
      }

      const userData = await userRes.json();
      const transactionsData = await transactionsRes.json();

      setUser(userData.user);
      setTransactions(transactionsData.transactions);

      // Calcular cambio de balance (últimos 7 días)
      const weekAgo = new Date();
      weekAgo.setDate(weekAgo.getDate() - 7);
      const recentTransactions = transactionsData.transactions.filter(
        (t: any) => new Date(t.createdAt) > weekAgo
      );
      const change = recentTransactions.reduce((sum: number, t: any) => {
        if (t.to === userData.user.id) return sum + t.amount;
        if (t.from === userData.user.id) return sum - t.amount;
        return sum;
      }, 0);
      setBalanceChange(change);
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setIsLoading(false);
    }
  };

  if (isLoading) {
    return (
      <main className="min-h-screen">
        <Navbar />
        <div className="pt-24 px-4 max-w-7xl mx-auto">
          <div className="animate-pulse space-y-8">
            <div className="h-64 bg-dark-800 rounded-3xl" />
            <div className="grid md:grid-cols-3 gap-6">
              <div className="h-32 bg-dark-800 rounded-2xl" />
              <div className="h-32 bg-dark-800 rounded-2xl" />
              <div className="h-32 bg-dark-800 rounded-2xl" />
            </div>
          </div>
        </div>
      </main>
    );
  }

  return (
    <main className="min-h-screen">
      <Navbar />
      
      <div className="pt-24 pb-12 px-4 max-w-7xl mx-auto space-y-8">
        {/* Welcome */}
        <div className="animate-slide-up">
          <h1 className="text-3xl font-bold text-white mb-2">
            Hola, {user?.fullName?.split(' ')[0] || 'Usuario'} 👋
          </h1>
          <p className="text-platinum-300">
            Bienvenido a tu dashboard de Smurf Bank
          </p>
        </div>

        {/* Balance Card */}
        <Card variant="premium" className="animate-fade-in card-3d">
          <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-6">
            <div>
              <p className="text-platinum-300 text-sm mb-2">Balance total</p>
              <div className="flex items-baseline gap-3">
                <h2 className="text-5xl font-bold text-metallic tabular-nums">
                  {user?.balance ? formatCurrency(user.balance) : '0.00'}
                </h2>
                <span className="text-2xl font-semibold text-gold-400">Smurf</span>
              </div>
              <div className="flex items-center gap-2 mt-3">
                {balanceChange >= 0 ? (
                  <>
                    <TrendingUp className="text-green-400" size={18} />
                    <span className="text-green-400 text-sm">
                      +{formatCurrency(Math.abs(balanceChange))} últimos 7 días
                    </span>
                  </>
                ) : (
                  <>
                    <TrendingUp className="text-red-400 rotate-180" size={18} />
                    <span className="text-red-400 text-sm">
                      -{formatCurrency(Math.abs(balanceChange))} últimos 7 días
                    </span>
                  </>
                )}
              </div>
            </div>
            <Link href="/transfer">
              <Button variant="primary" size="lg">
                <Send size={20} />
                Transferir
              </Button>
            </Link>
          </div>
        </Card>

        {/* Quick Stats */}
        <div className="grid md:grid-cols-3 gap-6">
          <Card variant="glass" className="animate-slide-up" style={{ animationDelay: '100ms' }}>
            <div className="flex items-center gap-4">
              <div className="w-12 h-12 rounded-xl bg-green-500/20 flex items-center justify-center">
                <ArrowDownRight className="text-green-400" size={24} />
              </div>
              <div>
                <p className="text-platinum-400 text-sm">Recibido</p>
                <p className="text-2xl font-bold text-white tabular-nums">
                  {formatCurrency(
                    transactions
                      .filter(t => t.to === user?.id && t.status === 'completed')
                      .reduce((sum, t) => sum + t.amount, 0)
                  )}
                </p>
              </div>
            </div>
          </Card>

          <Card variant="glass" className="animate-slide-up" style={{ animationDelay: '200ms' }}>
            <div className="flex items-center gap-4">
              <div className="w-12 h-12 rounded-xl bg-red-500/20 flex items-center justify-center">
                <ArrowUpRight className="text-red-400" size={24} />
              </div>
              <div>
                <p className="text-platinum-400 text-sm">Enviado</p>
                <p className="text-2xl font-bold text-white tabular-nums">
                  {formatCurrency(
                    transactions
                      .filter(t => t.from === user?.id && t.status === 'completed')
                      .reduce((sum, t) => sum + t.amount, 0)
                  )}
                </p>
              </div>
            </div>
          </Card>

          <Card variant="glass" className="animate-slide-up" style={{ animationDelay: '300ms' }}>
            <div className="flex items-center gap-4">
              <div className="w-12 h-12 rounded-xl bg-smurf-500/20 flex items-center justify-center">
                <Clock className="text-smurf-400" size={24} />
              </div>
              <div>
                <p className="text-platinum-400 text-sm">Transacciones</p>
                <p className="text-2xl font-bold text-white tabular-nums">
                  {transactions.length}
                </p>
              </div>
            </div>
          </Card>
        </div>

        {/* Recent Transactions */}
        <Card variant="glass" className="animate-fade-in">
          <div className="flex items-center justify-between mb-6">
            <h3 className="text-xl font-bold text-white">Actividad reciente</h3>
            <Link href="/history">
              <Button variant="ghost" size="sm">Ver todo</Button>
            </Link>
          </div>

          {transactions.length === 0 ? (
            <div className="text-center py-12">
              <div className="w-16 h-16 rounded-full bg-platinum-800/30 mx-auto mb-4 flex items-center justify-center">
                <Clock className="text-platinum-500" size={32} />
              </div>
              <p className="text-platinum-400 mb-4">No tienes transacciones aún</p>
              <Link href="/transfer">
                <Button variant="secondary">Hacer tu primera transferencia</Button>
              </Link>
            </div>
          ) : (
            <div className="space-y-4">
              {transactions.slice(0, 5).map((transaction) => {
                const isReceived = transaction.to === user?.id;
                const isTransfer = transaction.type === 'transfer';

                return (
                  <div
                    key={transaction.id}
                    className="flex items-center justify-between p-4 bg-dark-800/50 rounded-xl hover:bg-dark-800 transition-colors"
                  >
                    <div className="flex items-center gap-4">
                      <div className={`w-10 h-10 rounded-full flex items-center justify-center ${
                        isReceived ? 'bg-green-500/20' : 'bg-red-500/20'
                      }`}>
                        {isReceived ? (
                          <ArrowDownRight className="text-green-400" size={20} />
                        ) : (
                          <ArrowUpRight className="text-red-400" size={20} />
                        )}
                      </div>
                      <div>
                        <p className="text-white font-medium">
                          {isReceived ? `De ${transaction.fromName}` : `A ${transaction.toName}`}
                        </p>
                        <p className="text-platinum-400 text-sm">
                          {formatDateShort(transaction.createdAt)}
                        </p>
                      </div>
                    </div>
                    <div className="text-right">
                      <p className={`text-lg font-semibold tabular-nums ${
                        isReceived ? 'text-green-400' : 'text-red-400'
                      }`}>
                        {isReceived ? '+' : '-'}{formatCurrency(transaction.amount)}
                      </p>
                      <p className="text-platinum-400 text-sm">Smurf</p>
                    </div>
                  </div>
                );
              })}
            </div>
          )}
        </Card>
      </div>
    </main>
  );
}
