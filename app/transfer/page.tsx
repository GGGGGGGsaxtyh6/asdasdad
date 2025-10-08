'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Navbar from '@/components/layout/Navbar';
import Card from '@/components/ui/Card';
import Input from '@/components/ui/Input';
import Button from '@/components/ui/Button';
import { formatCurrency } from '@/lib/utils';
import { Search, User, DollarSign, MessageSquare, ArrowRight, CheckCircle, Coins } from 'lucide-react';

export default function TransferPage() {
  const router = useRouter();
  const [user, setUser] = useState<any>(null);
  const [searchQuery, setSearchQuery] = useState('');
  const [searchResults, setSearchResults] = useState<any[]>([]);
  const [selectedUser, setSelectedUser] = useState<any>(null);
  const [amount, setAmount] = useState('');
  const [note, setNote] = useState('');
  const [errors, setErrors] = useState<any>({});
  const [isLoading, setIsLoading] = useState(false);
  const [isSearching, setIsSearching] = useState(false);
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [transferSuccess, setTransferSuccess] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      router.push('/login');
      return;
    }

    fetchUser(token);
  }, [router]);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token || !searchQuery.trim()) {
      setSearchResults([]);
      return;
    }

    const timeoutId = setTimeout(() => {
      searchUsers(token, searchQuery);
    }, 300);

    return () => clearTimeout(timeoutId);
  }, [searchQuery]);

  const fetchUser = async (token: string) => {
    try {
      const res = await fetch('/api/auth/me', {
        headers: { Authorization: `Bearer ${token}` },
      });

      if (!res.ok) {
        router.push('/login');
        return;
      }

      const data = await res.json();
      setUser(data.user);
    } catch (error) {
      console.error('Error fetching user:', error);
    }
  };

  const searchUsers = async (token: string, query: string) => {
    setIsSearching(true);
    try {
      const res = await fetch(`/api/users?search=${encodeURIComponent(query)}`, {
        headers: { Authorization: `Bearer ${token}` },
      });

      if (res.ok) {
        const data = await res.json();
        setSearchResults(data.users);
      }
    } catch (error) {
      console.error('Error searching users:', error);
    } finally {
      setIsSearching(false);
    }
  };

  const handleUserSelect = (selectedUserData: any) => {
    setSelectedUser(selectedUserData);
    setSearchQuery('');
    setSearchResults([]);
  };

  const validateTransfer = () => {
    const newErrors: any = {};

    if (!selectedUser) {
      newErrors.user = 'Debes seleccionar un destinatario';
    }

    const amountNum = parseFloat(amount);
    if (!amount || isNaN(amountNum) || amountNum <= 0) {
      newErrors.amount = 'Ingresa un monto válido';
    } else if (amountNum > user?.balance) {
      newErrors.amount = 'Saldo insuficiente';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleContinue = () => {
    if (!validateTransfer()) return;
    setShowConfirmation(true);
  };

  const handleConfirmTransfer = async () => {
    setIsLoading(true);

    try {
      const token = localStorage.getItem('token');
      const res = await fetch('/api/transactions/transfer', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          toUserId: selectedUser.id,
          amount: parseFloat(amount),
          note,
        }),
      });

      const data = await res.json();

      if (!res.ok) {
        setErrors({ submit: data.error || 'Error al realizar la transferencia' });
        setShowConfirmation(false);
        return;
      }

      setTransferSuccess(true);
      
      // Actualizar balance del usuario
      const updatedUser = { ...user, balance: user.balance - parseFloat(amount) };
      setUser(updatedUser);

      // Resetear formulario después de 2 segundos
      setTimeout(() => {
        setSelectedUser(null);
        setAmount('');
        setNote('');
        setShowConfirmation(false);
        setTransferSuccess(false);
      }, 3000);
    } catch (error) {
      setErrors({ submit: 'Error de conexión. Intenta de nuevo.' });
      setShowConfirmation(false);
    } finally {
      setIsLoading(false);
    }
  };

  if (!user) {
    return (
      <main className="min-h-screen">
        <Navbar />
        <div className="pt-24 px-4 max-w-3xl mx-auto">
          <div className="animate-pulse">
            <div className="h-96 bg-dark-800 rounded-3xl" />
          </div>
        </div>
      </main>
    );
  }

  if (transferSuccess) {
    return (
      <main className="min-h-screen">
        <Navbar />
        <div className="pt-24 px-4 max-w-3xl mx-auto">
          <Card variant="premium" className="text-center py-16 animate-fade-in">
            <div className="w-20 h-20 rounded-full bg-green-500/20 mx-auto mb-6 flex items-center justify-center animate-bounce">
              <CheckCircle className="text-green-400" size={48} />
            </div>
            <h2 className="text-3xl font-bold text-white mb-4">¡Transferencia exitosa!</h2>
            <p className="text-platinum-300 mb-2">
              Has enviado {formatCurrency(parseFloat(amount))} Smurf a
            </p>
            <p className="text-xl font-semibold text-white mb-8">{selectedUser?.fullName}</p>
            <div className="flex gap-4 justify-center">
              <Button variant="secondary" onClick={() => router.push('/dashboard')}>
                Ir al Dashboard
              </Button>
              <Button variant="primary" onClick={() => setTransferSuccess(false)}>
                Nueva transferencia
              </Button>
            </div>
          </Card>
        </div>
      </main>
    );
  }

  if (showConfirmation) {
    return (
      <main className="min-h-screen">
        <Navbar />
        <div className="pt-24 px-4 max-w-3xl mx-auto">
          <div className="mb-6">
            <Button variant="ghost" onClick={() => setShowConfirmation(false)}>
              ← Volver
            </Button>
          </div>

          <Card variant="premium" className="animate-slide-up">
            <h2 className="text-2xl font-bold text-white mb-6">Confirmar transferencia</h2>
            
            <div className="space-y-4 mb-8">
              <div className="flex justify-between items-center p-4 bg-dark-800/50 rounded-xl">
                <span className="text-platinum-300">Destinatario</span>
                <div className="text-right">
                  <p className="text-white font-semibold">{selectedUser.fullName}</p>
                  <p className="text-platinum-400 text-sm">@{selectedUser.username}</p>
                </div>
              </div>

              <div className="flex justify-between items-center p-4 bg-dark-800/50 rounded-xl">
                <span className="text-platinum-300">Monto</span>
                <div className="text-right">
                  <p className="text-2xl font-bold text-metallic tabular-nums">
                    {formatCurrency(parseFloat(amount))}
                  </p>
                  <p className="text-gold-400 text-sm">Smurf</p>
                </div>
              </div>

              {note && (
                <div className="p-4 bg-dark-800/50 rounded-xl">
                  <p className="text-platinum-300 text-sm mb-1">Nota</p>
                  <p className="text-white">{note}</p>
                </div>
              )}

              <div className="flex justify-between items-center p-4 bg-smurf-900/20 rounded-xl border border-smurf-700/30">
                <span className="text-platinum-300">Nuevo balance</span>
                <p className="text-xl font-bold text-white tabular-nums">
                  {formatCurrency(user.balance - parseFloat(amount))} Smurf
                </p>
              </div>
            </div>

            {errors.submit && (
              <div className="p-4 bg-red-500/10 border border-red-500/30 rounded-xl mb-6">
                <p className="text-red-400 text-sm">{errors.submit}</p>
              </div>
            )}

            <div className="flex gap-4">
              <Button
                variant="secondary"
                size="lg"
                className="flex-1"
                onClick={() => setShowConfirmation(false)}
                disabled={isLoading}
              >
                Cancelar
              </Button>
              <Button
                variant="primary"
                size="lg"
                className="flex-1"
                onClick={handleConfirmTransfer}
                isLoading={isLoading}
              >
                Confirmar transferencia
              </Button>
            </div>
          </Card>
        </div>
      </main>
    );
  }

  return (
    <main className="min-h-screen">
      <Navbar />
      
      <div className="pt-24 pb-12 px-4 max-w-3xl mx-auto">
        <div className="mb-8 animate-slide-up">
          <h1 className="text-3xl font-bold text-white mb-2">Transferir Smurf</h1>
          <p className="text-platinum-300">
            Balance disponible: <span className="text-metallic font-semibold tabular-nums">{formatCurrency(user.balance)}</span> Smurf
          </p>
        </div>

        <Card variant="glass" className="animate-fade-in">
          <div className="space-y-6">
            {/* Search User */}
            <div>
              <label className="block text-sm font-medium text-platinum-200 mb-2">
                Buscar destinatario
              </label>
              {selectedUser ? (
                <div className="p-4 bg-smurf-900/20 border border-smurf-700/50 rounded-xl flex items-center justify-between">
                  <div className="flex items-center gap-3">
                    <div className="w-12 h-12 rounded-full bg-gradient-to-br from-gold-400 to-gold-600 flex items-center justify-center text-white font-semibold">
                      {selectedUser.fullName.charAt(0)}
                    </div>
                    <div>
                      <p className="text-white font-semibold">{selectedUser.fullName}</p>
                      <p className="text-platinum-400 text-sm">@{selectedUser.username}</p>
                    </div>
                  </div>
                  <Button variant="ghost" size="sm" onClick={() => setSelectedUser(null)}>
                    Cambiar
                  </Button>
                </div>
              ) : (
                <div className="relative">
                  <Input
                    type="text"
                    placeholder="Buscar por nombre, usuario o email..."
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                    icon={<Search size={20} />}
                    error={errors.user}
                  />
                  {searchResults.length > 0 && (
                    <div className="absolute top-full left-0 right-0 mt-2 bg-dark-800 border border-platinum-700/50 rounded-xl shadow-premium-lg z-10 max-h-64 overflow-y-auto">
                      {searchResults.map((result) => (
                        <button
                          key={result.id}
                          onClick={() => handleUserSelect(result)}
                          className="w-full p-4 hover:bg-dark-700 transition-colors flex items-center gap-3 border-b border-platinum-800/30 last:border-0"
                        >
                          <div className="w-10 h-10 rounded-full bg-gradient-to-br from-gold-400 to-gold-600 flex items-center justify-center text-white font-semibold">
                            {result.fullName.charAt(0)}
                          </div>
                          <div className="text-left">
                            <p className="text-white font-medium">{result.fullName}</p>
                            <p className="text-platinum-400 text-sm">@{result.username}</p>
                          </div>
                        </button>
                      ))}
                    </div>
                  )}
                  {isSearching && searchQuery && (
                    <div className="absolute top-full left-0 right-0 mt-2 bg-dark-800 border border-platinum-700/50 rounded-xl shadow-premium-lg p-4 text-center">
                      <p className="text-platinum-400">Buscando...</p>
                    </div>
                  )}
                </div>
              )}
            </div>

            {/* Amount */}
            <Input
              label="Monto"
              type="number"
              placeholder="0.00"
              value={amount}
              onChange={(e) => {
                setAmount(e.target.value);
                if (errors.amount) setErrors({ ...errors, amount: '' });
              }}
              error={errors.amount}
              icon={<Coins size={20} />}
              step="0.01"
              min="0"
            />

            {/* Note */}
            <div>
              <label className="block text-sm font-medium text-platinum-200 mb-2">
                Nota (opcional)
              </label>
              <textarea
                placeholder="Agrega un mensaje..."
                value={note}
                onChange={(e) => setNote(e.target.value)}
                className="w-full px-4 py-3 bg-dark-800/50 border border-platinum-700/30 rounded-xl text-platinum-100 placeholder:text-platinum-500 focus:outline-none focus:ring-2 focus:ring-smurf-500/50 focus:border-smurf-500/50 transition-all duration-200 backdrop-blur-sm shadow-inner-glow resize-none"
                rows={3}
                maxLength={200}
              />
              <p className="text-platinum-400 text-xs mt-1">{note.length}/200 caracteres</p>
            </div>

            <Button
              variant="primary"
              size="lg"
              className="w-full"
              onClick={handleContinue}
            >
              Continuar
              <ArrowRight size={20} />
            </Button>
          </div>
        </Card>
      </div>
    </main>
  );
}
