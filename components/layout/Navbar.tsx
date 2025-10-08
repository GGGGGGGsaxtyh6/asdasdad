'use client';

import Link from 'next/link';
import { usePathname, useRouter } from 'next/navigation';
import { Bell, User, Settings, LogOut, Menu, X } from 'lucide-react';
import { useState, useEffect } from 'react';

export default function Navbar() {
  const pathname = usePathname();
  const router = useRouter();
  const [user, setUser] = useState<any>(null);
  const [notifications, setNotifications] = useState<any[]>([]);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      fetchUser(token);
      fetchNotifications(token);
    }
  }, []);

  const fetchUser = async (token: string) => {
    try {
      const res = await fetch('/api/auth/me', {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (res.ok) {
        const data = await res.json();
        setUser(data.user);
      }
    } catch (error) {
      console.error('Error fetching user:', error);
    }
  };

  const fetchNotifications = async (token: string) => {
    try {
      const res = await fetch('/api/notifications', {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (res.ok) {
        const data = await res.json();
        setNotifications(data.notifications.filter((n: any) => !n.read));
      }
    } catch (error) {
      console.error('Error fetching notifications:', error);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    router.push('/');
  };

  const isPublicPage = pathname === '/' || pathname === '/register' || pathname === '/login';

  if (isPublicPage) {
    return (
      <nav className="fixed top-0 left-0 right-0 z-50 bg-dark-900/80 backdrop-blur-xl border-b border-platinum-800/30">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex items-center justify-between h-16">
            <Link href="/" className="flex items-center space-x-3">
              <div className="w-10 h-10 rounded-xl bg-gradient-to-br from-smurf-500 to-smurf-700 flex items-center justify-center shadow-lg">
                <span className="text-white font-bold text-xl">S</span>
              </div>
              <span className="text-2xl font-bold bg-gradient-to-r from-smurf-400 to-gold-400 bg-clip-text text-transparent">
                Smurf Bank
              </span>
            </Link>
            <div className="flex items-center space-x-4">
              <Link
                href="/login"
                className="text-platinum-200 hover:text-white transition-colors px-4 py-2"
              >
                Iniciar Sesión
              </Link>
              <Link
                href="/register"
                className="bg-gradient-to-r from-smurf-600 to-smurf-500 text-white px-6 py-2 rounded-xl hover:from-smurf-700 hover:to-smurf-600 transition-all shadow-lg"
              >
                Registrarse
              </Link>
            </div>
          </div>
        </div>
      </nav>
    );
  }

  return (
    <nav className="fixed top-0 left-0 right-0 z-50 bg-dark-900/80 backdrop-blur-xl border-b border-platinum-800/30">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16">
          <div className="flex items-center space-x-8">
            <Link href="/dashboard" className="flex items-center space-x-3">
              <div className="w-10 h-10 rounded-xl bg-gradient-to-br from-smurf-500 to-smurf-700 flex items-center justify-center shadow-lg">
                <span className="text-white font-bold text-xl">S</span>
              </div>
              <span className="text-xl font-bold text-white hidden sm:block">Smurf Bank</span>
            </Link>
            
            <div className="hidden md:flex items-center space-x-2">
              <Link
                href="/dashboard"
                className={`px-4 py-2 rounded-lg transition-colors ${
                  pathname === '/dashboard'
                    ? 'bg-smurf-600/20 text-smurf-400'
                    : 'text-platinum-300 hover:text-white hover:bg-platinum-800/30'
                }`}
              >
                Dashboard
              </Link>
              <Link
                href="/transfer"
                className={`px-4 py-2 rounded-lg transition-colors ${
                  pathname === '/transfer'
                    ? 'bg-smurf-600/20 text-smurf-400'
                    : 'text-platinum-300 hover:text-white hover:bg-platinum-800/30'
                }`}
              >
                Transferir
              </Link>
              <Link
                href="/history"
                className={`px-4 py-2 rounded-lg transition-colors ${
                  pathname === '/history'
                    ? 'bg-smurf-600/20 text-smurf-400'
                    : 'text-platinum-300 hover:text-white hover:bg-platinum-800/30'
                }`}
              >
                Historial
              </Link>
            </div>
          </div>

          <div className="flex items-center space-x-4">
            <Link
              href="/notifications"
              className="relative p-2 text-platinum-300 hover:text-white transition-colors"
            >
              <Bell size={24} />
              {notifications.length > 0 && (
                <span className="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full w-5 h-5 flex items-center justify-center">
                  {notifications.length}
                </span>
              )}
            </Link>

            <Link
              href="/settings"
              className="p-2 text-platinum-300 hover:text-white transition-colors"
            >
              <Settings size={24} />
            </Link>

            <button
              onClick={handleLogout}
              className="p-2 text-platinum-300 hover:text-red-400 transition-colors"
              aria-label="Cerrar sesión"
            >
              <LogOut size={24} />
            </button>

            <div className="hidden sm:flex items-center space-x-3 pl-4 border-l border-platinum-700/50">
              <div className="w-10 h-10 rounded-full bg-gradient-to-br from-gold-400 to-gold-600 flex items-center justify-center text-white font-semibold shadow-lg">
                {user?.fullName?.charAt(0) || 'U'}
              </div>
              <div className="hidden lg:block">
                <p className="text-sm font-medium text-white">{user?.fullName || 'Usuario'}</p>
                <p className="text-xs text-platinum-400">@{user?.username || 'user'}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
}
