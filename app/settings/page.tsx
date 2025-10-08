'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Navbar from '@/components/layout/Navbar';
import Card from '@/components/ui/Card';
import Input from '@/components/ui/Input';
import Button from '@/components/ui/Button';
import { User, Moon, Sun, Monitor, Globe, Shield, Bell } from 'lucide-react';

export default function SettingsPage() {
  const router = useRouter();
  const [user, setUser] = useState<any>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [isSaving, setIsSaving] = useState(false);
  const [fullName, setFullName] = useState('');
  const [theme, setTheme] = useState('dark');
  const [language, setLanguage] = useState('es');
  const [reducedMotion, setReducedMotion] = useState(false);
  const [twoFactorEnabled, setTwoFactorEnabled] = useState(false);
  const [saveMessage, setSaveMessage] = useState('');

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      router.push('/login');
      return;
    }

    fetchUser(token);
  }, [router]);

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
      setFullName(data.user.fullName);
      setTheme(data.user.settings.theme);
      setLanguage(data.user.settings.language);
      setReducedMotion(data.user.settings.reducedMotion);
      setTwoFactorEnabled(data.user.settings.twoFactorEnabled);
    } catch (error) {
      console.error('Error fetching user:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleSave = async () => {
    setIsSaving(true);
    setSaveMessage('');

    try {
      const token = localStorage.getItem('token');
      const res = await fetch('/api/users/update', {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          fullName,
          settings: {
            theme,
            language,
            reducedMotion,
            twoFactorEnabled,
          },
        }),
      });

      if (res.ok) {
        const data = await res.json();
        setUser(data.user);
        setSaveMessage('Cambios guardados correctamente');
        
        // Aplicar tema
        if (theme === 'dark') {
          document.documentElement.classList.add('dark');
        } else if (theme === 'light') {
          document.documentElement.classList.remove('dark');
        } else {
          // Auto: usar preferencia del sistema
          if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
            document.documentElement.classList.add('dark');
          } else {
            document.documentElement.classList.remove('dark');
          }
        }

        setTimeout(() => setSaveMessage(''), 3000);
      } else {
        setSaveMessage('Error al guardar los cambios');
      }
    } catch (error) {
      setSaveMessage('Error de conexión');
    } finally {
      setIsSaving(false);
    }
  };

  if (isLoading) {
    return (
      <main className="min-h-screen">
        <Navbar />
        <div className="pt-24 px-4 max-w-4xl mx-auto">
          <div className="animate-pulse">
            <div className="h-96 bg-dark-800 rounded-3xl" />
          </div>
        </div>
      </main>
    );
  }

  return (
    <main className="min-h-screen">
      <Navbar />
      
      <div className="pt-24 pb-12 px-4 max-w-4xl mx-auto">
        <div className="mb-8 animate-slide-up">
          <h1 className="text-3xl font-bold text-white mb-2">Ajustes</h1>
          <p className="text-platinum-300">
            Personaliza tu experiencia en Smurf Bank
          </p>
        </div>

        <div className="space-y-6">
          {/* Perfil */}
          <Card variant="glass" className="animate-fade-in">
            <div className="flex items-center gap-3 mb-6">
              <div className="w-10 h-10 rounded-xl bg-gradient-to-br from-smurf-500 to-smurf-700 flex items-center justify-center">
                <User className="text-white" size={20} />
              </div>
              <h2 className="text-xl font-bold text-white">Perfil</h2>
            </div>

            <div className="space-y-4">
              <Input
                label="Nombre completo"
                type="text"
                value={fullName}
                onChange={(e) => setFullName(e.target.value)}
              />

              <div>
                <label className="block text-sm font-medium text-platinum-200 mb-2">
                  Email
                </label>
                <p className="text-platinum-100 px-4 py-3 bg-dark-800/50 border border-platinum-700/30 rounded-xl">
                  {user?.email}
                </p>
              </div>

              <div>
                <label className="block text-sm font-medium text-platinum-200 mb-2">
                  Nombre de usuario
                </label>
                <p className="text-platinum-100 px-4 py-3 bg-dark-800/50 border border-platinum-700/30 rounded-xl">
                  @{user?.username}
                </p>
              </div>
            </div>
          </Card>

          {/* Apariencia */}
          <Card variant="glass" className="animate-fade-in">
            <div className="flex items-center gap-3 mb-6">
              <div className="w-10 h-10 rounded-xl bg-gradient-to-br from-gold-500 to-gold-700 flex items-center justify-center">
                {theme === 'light' ? <Sun className="text-white" size={20} /> : theme === 'dark' ? <Moon className="text-white" size={20} /> : <Monitor className="text-white" size={20} />}
              </div>
              <h2 className="text-xl font-bold text-white">Apariencia</h2>
            </div>

            <div className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-platinum-200 mb-3">
                  Tema
                </label>
                <div className="grid grid-cols-3 gap-3">
                  <button
                    onClick={() => setTheme('light')}
                    className={`p-4 rounded-xl border-2 transition-all ${
                      theme === 'light'
                        ? 'border-smurf-500 bg-smurf-500/10'
                        : 'border-platinum-700/30 bg-dark-800/30 hover:border-platinum-600/50'
                    }`}
                  >
                    <Sun className="mx-auto mb-2" size={24} />
                    <p className="text-sm text-platinum-200">Claro</p>
                  </button>

                  <button
                    onClick={() => setTheme('dark')}
                    className={`p-4 rounded-xl border-2 transition-all ${
                      theme === 'dark'
                        ? 'border-smurf-500 bg-smurf-500/10'
                        : 'border-platinum-700/30 bg-dark-800/30 hover:border-platinum-600/50'
                    }`}
                  >
                    <Moon className="mx-auto mb-2" size={24} />
                    <p className="text-sm text-platinum-200">Oscuro</p>
                  </button>

                  <button
                    onClick={() => setTheme('auto')}
                    className={`p-4 rounded-xl border-2 transition-all ${
                      theme === 'auto'
                        ? 'border-smurf-500 bg-smurf-500/10'
                        : 'border-platinum-700/30 bg-dark-800/30 hover:border-platinum-600/50'
                    }`}
                  >
                    <Monitor className="mx-auto mb-2" size={24} />
                    <p className="text-sm text-platinum-200">Auto</p>
                  </button>
                </div>
              </div>

              <div>
                <label className="block text-sm font-medium text-platinum-200 mb-3">
                  Idioma
                </label>
                <select
                  value={language}
                  onChange={(e) => setLanguage(e.target.value)}
                  className="w-full px-4 py-3 bg-dark-800/50 border border-platinum-700/30 rounded-xl text-platinum-100 focus:outline-none focus:ring-2 focus:ring-smurf-500/50 focus:border-smurf-500/50 transition-all"
                >
                  <option value="es">Español</option>
                  <option value="en">English</option>
                </select>
              </div>
            </div>
          </Card>

          {/* Accesibilidad */}
          <Card variant="glass" className="animate-fade-in">
            <div className="flex items-center gap-3 mb-6">
              <div className="w-10 h-10 rounded-xl bg-gradient-to-br from-platinum-500 to-platinum-700 flex items-center justify-center">
                <Globe className="text-white" size={20} />
              </div>
              <h2 className="text-xl font-bold text-white">Accesibilidad</h2>
            </div>

            <div className="space-y-4">
              <div className="flex items-center justify-between p-4 bg-dark-800/30 rounded-xl">
                <div>
                  <p className="text-white font-medium">Reducir movimiento</p>
                  <p className="text-platinum-400 text-sm">Desactiva animaciones complejas</p>
                </div>
                <button
                  onClick={() => setReducedMotion(!reducedMotion)}
                  className={`relative w-12 h-6 rounded-full transition-colors ${
                    reducedMotion ? 'bg-smurf-500' : 'bg-platinum-700'
                  }`}
                >
                  <div
                    className={`absolute top-0.5 w-5 h-5 rounded-full bg-white transition-transform ${
                      reducedMotion ? 'translate-x-6' : 'translate-x-0.5'
                    }`}
                  />
                </button>
              </div>
            </div>
          </Card>

          {/* Seguridad */}
          <Card variant="glass" className="animate-fade-in">
            <div className="flex items-center gap-3 mb-6">
              <div className="w-10 h-10 rounded-xl bg-gradient-to-br from-red-500 to-red-700 flex items-center justify-center">
                <Shield className="text-white" size={20} />
              </div>
              <h2 className="text-xl font-bold text-white">Seguridad</h2>
            </div>

            <div className="space-y-4">
              <div className="flex items-center justify-between p-4 bg-dark-800/30 rounded-xl">
                <div>
                  <p className="text-white font-medium">Autenticación de dos factores</p>
                  <p className="text-platinum-400 text-sm">Capa adicional de seguridad</p>
                </div>
                <button
                  onClick={() => setTwoFactorEnabled(!twoFactorEnabled)}
                  className={`relative w-12 h-6 rounded-full transition-colors ${
                    twoFactorEnabled ? 'bg-smurf-500' : 'bg-platinum-700'
                  }`}
                >
                  <div
                    className={`absolute top-0.5 w-5 h-5 rounded-full bg-white transition-transform ${
                      twoFactorEnabled ? 'translate-x-6' : 'translate-x-0.5'
                    }`}
                  />
                </button>
              </div>
            </div>
          </Card>

          {/* Save Button */}
          <div className="flex items-center gap-4">
            <Button
              variant="primary"
              size="lg"
              onClick={handleSave}
              isLoading={isSaving}
              className="flex-1"
            >
              Guardar cambios
            </Button>
            {saveMessage && (
              <p className={`text-sm ${saveMessage.includes('Error') ? 'text-red-400' : 'text-green-400'}`}>
                {saveMessage}
              </p>
            )}
          </div>
        </div>
      </div>
    </main>
  );
}
