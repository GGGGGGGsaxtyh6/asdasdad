'use client';

import { SessionProvider } from 'next-auth/react';
import { useEffect } from 'react';
import { useThemeStore } from '@/lib/store';

export function Providers({ children }: { children: React.ReactNode }) {
  const { theme } = useThemeStore();

  useEffect(() => {
    if (theme === 'dark') {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
  }, [theme]);

  return (
    <SessionProvider>
      {children}
    </SessionProvider>
  );
}
