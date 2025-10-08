'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Navbar from '@/components/layout/Navbar';
import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import { formatCurrency, formatDate } from '@/lib/utils';
import { ArrowUpRight, ArrowDownRight, Download, Filter, ChevronDown } from 'lucide-react';

export default function HistoryPage() {
  const router = useRouter();
  const [user, setUser] = useState<any>(null);
  const [transactions, setTransactions] = useState<any[]>([]);
  const [filteredTransactions, setFilteredTransactions] = useState<any[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [typeFilter, setTypeFilter] = useState<string>('all');
  const [statusFilter, setStatusFilter] = useState<string>('all');

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      router.push('/login');
      return;
    }

    fetchData(token);
  }, [router]);

  useEffect(() => {
    applyFilters();
  }, [transactions, typeFilter, statusFilter]);

  const fetchData = async (token: string) => {
    try {
      const [userRes, transactionsRes] = await Promise.all([
        fetch('/api/auth/me', {
          headers: { Authorization: `Bearer ${token}` },
        }),
        fetch('/api/transactions', {
          headers: { Authorization: `Bearer ${token}` },
        }),
      ]);

      if (!userRes.ok || !transactionsRes.ok) {
        router.push('/login');
        return;
      }

      const userData = await userRes.json();
      const transactionsData = await transactionsRes.json();

      setUser(userData.user);
      setTransactions(transactionsData.transactions);
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const applyFilters = () => {
    let filtered = [...transactions];

    if (typeFilter !== 'all') {
      if (typeFilter === 'received') {
        filtered = filtered.filter(t => t.to === user?.id);
      } else if (typeFilter === 'sent') {
        filtered = filtered.filter(t => t.from === user?.id);
      }
    }

    if (statusFilter !== 'all') {
      filtered = filtered.filter(t => t.status === statusFilter);
    }

    setFilteredTransactions(filtered);
  };

  const exportToCSV = () => {
    const headers = ['Fecha', 'Tipo', 'Monto', 'De/Para', 'Estado', 'Nota'];
    const rows = filteredTransactions.map(t => {
      const isReceived = t.to === user?.id;
      return [
        formatDate(t.createdAt),
        isReceived ? 'Recibido' : 'Enviado',
        t.amount.toFixed(2),
        isReceived ? t.fromName : t.toName,
        t.status === 'completed' ? 'Completado' : t.status === 'pending' ? 'Pendiente' : 'Fallido',
        t.note || '',
      ];
    });

    const csv = [
      headers.join(','),
      ...rows.map(row => row.map(cell => `"${cell}"`).join(',')),
    ].join('\n');

    const blob = new Blob([csv], { type: 'text/csv' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `smurf-bank-historial-${new Date().toISOString().split('T')[0]}.csv`;
    a.click();
  };

  if (isLoading) {
    return (
      <main className="min-h-screen">
        <Navbar />
        <div className="pt-24 px-4 max-w-7xl mx-auto">
          <div className="animate-pulse">
            <div className="h-64 bg-dark-800 rounded-3xl" />
          </div>
        </div>
      </main>
    );
  }

  return (
    <main className="min-h-screen">
      <Navbar />
      
      <div className="pt-24 pb-12 px-4 max-w-7xl mx-auto">
        <div className="mb-8 animate-slide-up">
          <h1 className="text-3xl font-bold text-white mb-2">Historial de transacciones</h1>
          <p className="text-platinum-300">
            Todas tus transacciones en Smurf Bank
          </p>
        </div>

        {/* Filters */}
        <Card variant="glass" className="mb-6 animate-fade-in">
          <div className="flex flex-col md:flex-row gap-4 items-start md:items-center justify-between">
            <div className="flex flex-col sm:flex-row gap-3 flex-1">
              <div className="relative">
                <select
                  value={typeFilter}
                  onChange={(e) => setTypeFilter(e.target.value)}
                  className="appearance-none px-4 py-2 pr-10 bg-dark-800/50 border border-platinum-700/30 rounded-xl text-platinum-100 focus:outline-none focus:ring-2 focus:ring-smurf-500/50 focus:border-smurf-500/50 transition-all cursor-pointer"
                >
                  <option value="all">Todos los tipos</option>
                  <option value="received">Recibidos</option>
                  <option value="sent">Enviados</option>
                </select>
                <ChevronDown className="absolute right-3 top-1/2 -translate-y-1/2 text-platinum-400 pointer-events-none" size={18} />
              </div>

              <div className="relative">
                <select
                  value={statusFilter}
                  onChange={(e) => setStatusFilter(e.target.value)}
                  className="appearance-none px-4 py-2 pr-10 bg-dark-800/50 border border-platinum-700/30 rounded-xl text-platinum-100 focus:outline-none focus:ring-2 focus:ring-smurf-500/50 focus:border-smurf-500/50 transition-all cursor-pointer"
                >
                  <option value="all">Todos los estados</option>
                  <option value="completed">Completados</option>
                  <option value="pending">Pendientes</option>
                  <option value="failed">Fallidos</option>
                </select>
                <ChevronDown className="absolute right-3 top-1/2 -translate-y-1/2 text-platinum-400 pointer-events-none" size={18} />
              </div>
            </div>

            <Button variant="secondary" onClick={exportToCSV}>
              <Download size={18} />
              Exportar CSV
            </Button>
          </div>
        </Card>

        {/* Transactions List */}
        <Card variant="glass" className="animate-fade-in">
          {filteredTransactions.length === 0 ? (
            <div className="text-center py-16">
              <div className="w-16 h-16 rounded-full bg-platinum-800/30 mx-auto mb-4 flex items-center justify-center">
                <Filter className="text-platinum-500" size={32} />
              </div>
              <p className="text-platinum-400">No se encontraron transacciones</p>
            </div>
          ) : (
            <div className="space-y-3">
              {filteredTransactions.map((transaction) => {
                const isReceived = transaction.to === user?.id;
                const statusColors = {
                  completed: 'bg-green-500/20 text-green-400',
                  pending: 'bg-yellow-500/20 text-yellow-400',
                  failed: 'bg-red-500/20 text-red-400',
                };

                return (
                  <div
                    key={transaction.id}
                    className="p-4 bg-dark-800/50 rounded-xl hover:bg-dark-800 transition-all group"
                  >
                    <div className="flex items-start justify-between gap-4">
                      <div className="flex items-start gap-4 flex-1">
                        <div className={`w-12 h-12 rounded-full flex items-center justify-center flex-shrink-0 ${
                          isReceived ? 'bg-green-500/20' : 'bg-red-500/20'
                        }`}>
                          {isReceived ? (
                            <ArrowDownRight className="text-green-400" size={24} />
                          ) : (
                            <ArrowUpRight className="text-red-400" size={24} />
                          )}
                        </div>
                        
                        <div className="flex-1 min-w-0">
                          <div className="flex items-center gap-2 mb-1">
                            <p className="text-white font-medium truncate">
                              {isReceived ? `De ${transaction.fromName}` : `A ${transaction.toName}`}
                            </p>
                            <span className={`px-2 py-0.5 rounded-full text-xs font-medium ${statusColors[transaction.status as keyof typeof statusColors]}`}>
                              {transaction.status === 'completed' ? 'Completado' : transaction.status === 'pending' ? 'Pendiente' : 'Fallido'}
                            </span>
                          </div>
                          <p className="text-platinum-400 text-sm mb-1">
                            {formatDate(transaction.createdAt)}
                          </p>
                          {transaction.note && (
                            <p className="text-platinum-300 text-sm mt-2 p-2 bg-dark-900/50 rounded-lg">
                              "{transaction.note}"
                            </p>
                          )}
                        </div>
                      </div>

                      <div className="text-right flex-shrink-0">
                        <p className={`text-xl font-bold tabular-nums ${
                          isReceived ? 'text-green-400' : 'text-red-400'
                        }`}>
                          {isReceived ? '+' : '-'}{formatCurrency(transaction.amount)}
                        </p>
                        <p className="text-platinum-400 text-sm">Smurf</p>
                      </div>
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
