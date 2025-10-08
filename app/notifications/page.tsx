'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Navbar from '@/components/layout/Navbar';
import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import { formatDate } from '@/lib/utils';
import { Bell, CheckCheck, ArrowDownRight, ArrowUpRight, Shield, Info } from 'lucide-react';

export default function NotificationsPage() {
  const router = useRouter();
  const [notifications, setNotifications] = useState<any[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      router.push('/login');
      return;
    }

    fetchNotifications(token);
  }, [router]);

  const fetchNotifications = async (token: string) => {
    try {
      const res = await fetch('/api/notifications', {
        headers: { Authorization: `Bearer ${token}` },
      });

      if (!res.ok) {
        router.push('/login');
        return;
      }

      const data = await res.json();
      setNotifications(data.notifications);
    } catch (error) {
      console.error('Error fetching notifications:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const markAsRead = async (notificationId: string) => {
    try {
      const token = localStorage.getItem('token');
      await fetch('/api/notifications', {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ notificationId }),
      });

      setNotifications(prev =>
        prev.map(n => (n.id === notificationId ? { ...n, read: true } : n))
      );
    } catch (error) {
      console.error('Error marking notification as read:', error);
    }
  };

  const markAllAsRead = async () => {
    try {
      const token = localStorage.getItem('token');
      await fetch('/api/notifications', {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ markAll: true }),
      });

      setNotifications(prev => prev.map(n => ({ ...n, read: true })));
    } catch (error) {
      console.error('Error marking all notifications as read:', error);
    }
  };

  const getIcon = (type: string) => {
    switch (type) {
      case 'transfer':
        return <ArrowUpRight className="text-red-400" size={24} />;
      case 'received':
        return <ArrowDownRight className="text-green-400" size={24} />;
      case 'security':
        return <Shield className="text-yellow-400" size={24} />;
      default:
        return <Info className="text-smurf-400" size={24} />;
    }
  };

  const getIconBg = (type: string) => {
    switch (type) {
      case 'transfer':
        return 'bg-red-500/20';
      case 'received':
        return 'bg-green-500/20';
      case 'security':
        return 'bg-yellow-500/20';
      default:
        return 'bg-smurf-500/20';
    }
  };

  const unreadCount = notifications.filter(n => !n.read).length;

  if (isLoading) {
    return (
      <main className="min-h-screen">
        <Navbar />
        <div className="pt-24 px-4 max-w-4xl mx-auto">
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
      
      <div className="pt-24 pb-12 px-4 max-w-4xl mx-auto">
        <div className="mb-8 animate-slide-up flex items-center justify-between">
          <div>
            <h1 className="text-3xl font-bold text-white mb-2">Notificaciones</h1>
            <p className="text-platinum-300">
              {unreadCount > 0 ? `Tienes ${unreadCount} notificación${unreadCount > 1 ? 'es' : ''} sin leer` : 'Todas las notificaciones leídas'}
            </p>
          </div>
          {unreadCount > 0 && (
            <Button variant="secondary" size="sm" onClick={markAllAsRead}>
              <CheckCheck size={18} />
              Marcar todas como leídas
            </Button>
          )}
        </div>

        <Card variant="glass" className="animate-fade-in">
          {notifications.length === 0 ? (
            <div className="text-center py-16">
              <div className="w-16 h-16 rounded-full bg-platinum-800/30 mx-auto mb-4 flex items-center justify-center">
                <Bell className="text-platinum-500" size={32} />
              </div>
              <p className="text-platinum-400">No tienes notificaciones</p>
            </div>
          ) : (
            <div className="space-y-3">
              {notifications.map((notification) => (
                <div
                  key={notification.id}
                  onClick={() => !notification.read && markAsRead(notification.id)}
                  className={`p-4 rounded-xl transition-all cursor-pointer ${
                    notification.read
                      ? 'bg-dark-800/30'
                      : 'bg-dark-800/70 hover:bg-dark-800 border border-smurf-500/20'
                  }`}
                >
                  <div className="flex items-start gap-4">
                    <div className={`w-12 h-12 rounded-full flex items-center justify-center flex-shrink-0 ${getIconBg(notification.type)}`}>
                      {getIcon(notification.type)}
                    </div>
                    
                    <div className="flex-1 min-w-0">
                      <div className="flex items-start justify-between gap-2 mb-1">
                        <h3 className="text-white font-semibold">{notification.title}</h3>
                        {!notification.read && (
                          <div className="w-2 h-2 rounded-full bg-smurf-500 flex-shrink-0 mt-1.5" />
                        )}
                      </div>
                      <p className="text-platinum-300 text-sm mb-2">{notification.message}</p>
                      <p className="text-platinum-500 text-xs">{formatDate(notification.createdAt)}</p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          )}
        </Card>
      </div>
    </main>
  );
}
