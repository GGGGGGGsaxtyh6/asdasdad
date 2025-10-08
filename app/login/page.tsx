'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import Navbar from '@/components/layout/Navbar';
import Input from '@/components/ui/Input';
import Button from '@/components/ui/Button';
import { User, Lock } from 'lucide-react';

export default function LoginPage() {
  const router = useRouter();
  const [formData, setFormData] = useState({
    identifier: '',
    password: '',
  });
  const [errors, setErrors] = useState<any>({});
  const [isLoading, setIsLoading] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
    
    if (errors[name]) {
      setErrors((prev: any) => ({ ...prev, [name]: '' }));
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    const newErrors: any = {};

    if (!formData.identifier.trim()) {
      newErrors.identifier = 'Email o usuario es obligatorio';
    }

    if (!formData.password.trim()) {
      newErrors.password = 'La contraseña es obligatoria';
    }

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    setIsLoading(true);

    try {
      const res = await fetch('/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
      });

      const data = await res.json();

      if (!res.ok) {
        setErrors({ submit: data.error || 'Error al iniciar sesión' });
        return;
      }

      localStorage.setItem('token', data.token);
      router.push('/dashboard');
    } catch (error) {
      setErrors({ submit: 'Error de conexión. Intenta de nuevo.' });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <main className="min-h-screen">
      <Navbar />
      
      <div className="pt-32 pb-12 px-4">
        <div className="max-w-md mx-auto">
          <div className="text-center mb-8 animate-slide-up">
            <h1 className="text-4xl font-bold text-white mb-2">Bienvenido de nuevo</h1>
            <p className="text-platinum-300">
              Inicia sesión en tu cuenta de Smurf Bank
            </p>
          </div>

          <div className="bg-glass-gradient border border-platinum-700/30 rounded-3xl p-8 backdrop-blur-xl shadow-premium animate-fade-in">
            <form onSubmit={handleSubmit} className="space-y-6">
              <Input
                label="Email o usuario"
                name="identifier"
                type="text"
                placeholder="juan@ejemplo.com o juanperez"
                value={formData.identifier}
                onChange={handleChange}
                error={errors.identifier}
                icon={<User size={20} />}
                autoComplete="username"
              />

              <Input
                label="Contraseña"
                name="password"
                type="password"
                placeholder="Tu contraseña"
                value={formData.password}
                onChange={handleChange}
                error={errors.password}
                icon={<Lock size={20} />}
                autoComplete="current-password"
              />

              {errors.submit && (
                <div className="p-4 bg-red-500/10 border border-red-500/30 rounded-xl">
                  <p className="text-red-400 text-sm">{errors.submit}</p>
                </div>
              )}

              <Button type="submit" variant="primary" size="lg" className="w-full" isLoading={isLoading}>
                Iniciar sesión
              </Button>

              <p className="text-center text-platinum-400 text-sm">
                ¿No tienes cuenta?{' '}
                <Link href="/register" className="text-smurf-400 hover:text-smurf-300 transition-colors">
                  Crear cuenta
                </Link>
              </p>
            </form>
          </div>
        </div>
      </div>
    </main>
  );
}
