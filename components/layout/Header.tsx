'use client';

import { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import { signOut, useSession } from 'next-auth/react';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { 
  FiBell, 
  FiSettings, 
  FiLogOut, 
  FiSun, 
  FiMoon,
  FiMenu,
  FiX,
  FiHome,
  FiSend,
  FiClock,
} from 'react-icons/fi';
import { useThemeStore, useNotificationStore } from '@/lib/store';

export function Header() {
  const { data: session } = useSession();
  const pathname = usePathname();
  const { theme, toggleTheme } = useThemeStore();
  const { unreadCount } = useNotificationStore();
  const [showUserMenu, setShowUserMenu] = useState(false);
  const [showMobileMenu, setShowMobileMenu] = useState(false);

  const navigation = [
    { name: 'Dashboard', href: '/dashboard', icon: FiHome },
    { name: 'Transfer', href: '/dashboard/transfer', icon: FiSend },
    { name: 'History', href: '/dashboard/history', icon: FiClock },
  ];

  return (
    <header className="sticky top-0 z-40 w-full backdrop-blur-xl bg-white/80 dark:bg-gray-900/80 border-b border-gray-200/20 dark:border-gray-800/20">
      <div className="container mx-auto px-4">
        <div className="flex items-center justify-between h-16">
          {/* Logo */}
          <Link href="/dashboard" className="flex items-center gap-2">
            <div className="text-2xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-smurf-600 to-purple-600">
              Smurf Bank
            </div>
          </Link>

          {/* Desktop Navigation */}
          <nav className="hidden md:flex items-center gap-1">
            {navigation.map((item) => {
              const Icon = item.icon;
              const isActive = pathname === item.href;
              return (
                <Link key={item.href} href={item.href}>
                  <motion.div
                    className={`
                      flex items-center gap-2 px-4 py-2 rounded-xl transition-colors
                      ${isActive 
                        ? 'bg-smurf-500 text-white' 
                        : 'text-gray-700 dark:text-gray-300 hover:bg-white/50 dark:hover:bg-white/5'
                      }
                    `}
                    whileHover={{ scale: 1.05 }}
                    whileTap={{ scale: 0.95 }}
                  >
                    <Icon className="w-4 h-4" />
                    <span className="font-medium">{item.name}</span>
                  </motion.div>
                </Link>
              );
            })}
          </nav>

          {/* Right side actions */}
          <div className="flex items-center gap-2">
            {/* Theme toggle */}
            <motion.button
              onClick={toggleTheme}
              className="p-2 rounded-xl hover:bg-white/50 dark:hover:bg-white/5 transition-colors"
              whileHover={{ scale: 1.1 }}
              whileTap={{ scale: 0.9 }}
            >
              {theme === 'dark' ? (
                <FiSun className="w-5 h-5 text-yellow-500" />
              ) : (
                <FiMoon className="w-5 h-5 text-gray-700" />
              )}
            </motion.button>

            {/* Notifications */}
            <Link href="/dashboard/notifications">
              <motion.button
                className="relative p-2 rounded-xl hover:bg-white/50 dark:hover:bg-white/5 transition-colors"
                whileHover={{ scale: 1.1 }}
                whileTap={{ scale: 0.9 }}
              >
                <FiBell className="w-5 h-5 text-gray-700 dark:text-gray-300" />
                {unreadCount > 0 && (
                  <span className="absolute -top-1 -right-1 w-5 h-5 bg-red-500 rounded-full text-white text-xs flex items-center justify-center font-bold">
                    {unreadCount > 9 ? '9+' : unreadCount}
                  </span>
                )}
              </motion.button>
            </Link>

            {/* User menu */}
            <div className="relative">
              <motion.button
                onClick={() => setShowUserMenu(!showUserMenu)}
                className="flex items-center gap-3 p-2 rounded-xl hover:bg-white/50 dark:hover:bg-white/5 transition-colors"
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
              >
                <div className="w-8 h-8 rounded-full bg-gradient-to-r from-smurf-500 to-purple-500 flex items-center justify-center text-white font-bold">
                  {session?.user?.name?.[0]?.toUpperCase()}
                </div>
                <span className="hidden md:block text-sm font-medium text-gray-700 dark:text-gray-300">
                  {session?.user?.name}
                </span>
              </motion.button>

              {showUserMenu && (
                <>
                  <div
                    className="fixed inset-0 z-40"
                    onClick={() => setShowUserMenu(false)}
                  />
                  <motion.div
                    initial={{ opacity: 0, y: 10 }}
                    animate={{ opacity: 1, y: 0 }}
                    className="absolute right-0 mt-2 w-48 bg-white/95 dark:bg-gray-900/95 backdrop-blur-xl rounded-2xl shadow-xl border border-gray-200/20 dark:border-gray-800/20 z-50 overflow-hidden"
                  >
                    <Link href="/dashboard/settings">
                      <button
                        className="w-full flex items-center gap-3 px-4 py-3 hover:bg-white/50 dark:hover:bg-white/5 transition-colors text-gray-700 dark:text-gray-300"
                        onClick={() => setShowUserMenu(false)}
                      >
                        <FiSettings className="w-4 h-4" />
                        <span>Settings</span>
                      </button>
                    </Link>
                    <button
                      onClick={() => signOut({ callbackUrl: '/' })}
                      className="w-full flex items-center gap-3 px-4 py-3 hover:bg-red-50 dark:hover:bg-red-900/20 transition-colors text-red-600 dark:text-red-400"
                    >
                      <FiLogOut className="w-4 h-4" />
                      <span>Sign Out</span>
                    </button>
                  </motion.div>
                </>
              )}
            </div>

            {/* Mobile menu button */}
            <motion.button
              onClick={() => setShowMobileMenu(!showMobileMenu)}
              className="md:hidden p-2 rounded-xl hover:bg-white/50 dark:hover:bg-white/5 transition-colors"
              whileHover={{ scale: 1.1 }}
              whileTap={{ scale: 0.9 }}
            >
              {showMobileMenu ? (
                <FiX className="w-6 h-6 text-gray-700 dark:text-gray-300" />
              ) : (
                <FiMenu className="w-6 h-6 text-gray-700 dark:text-gray-300" />
              )}
            </motion.button>
          </div>
        </div>

        {/* Mobile menu */}
        {showMobileMenu && (
          <motion.nav
            initial={{ opacity: 0, height: 0 }}
            animate={{ opacity: 1, height: 'auto' }}
            exit={{ opacity: 0, height: 0 }}
            className="md:hidden py-4 border-t border-gray-200/20 dark:border-gray-800/20"
          >
            {navigation.map((item) => {
              const Icon = item.icon;
              const isActive = pathname === item.href;
              return (
                <Link key={item.href} href={item.href}>
                  <div
                    className={`
                      flex items-center gap-3 px-4 py-3 rounded-xl transition-colors mb-1
                      ${isActive 
                        ? 'bg-smurf-500 text-white' 
                        : 'text-gray-700 dark:text-gray-300 hover:bg-white/50 dark:hover:bg-white/5'
                      }
                    `}
                    onClick={() => setShowMobileMenu(false)}
                  >
                    <Icon className="w-5 h-5" />
                    <span className="font-medium">{item.name}</span>
                  </div>
                </Link>
              );
            })}
          </motion.nav>
        )}
      </div>
    </header>
  );
}
