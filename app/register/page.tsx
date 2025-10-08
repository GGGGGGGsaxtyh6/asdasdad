'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import Navbar from '@/components/layout/Navbar';
import Input from '@/components/ui/Input';
import Button from '@/components/ui/Button';
import { validatePassword } from '@/lib/utils';
import { User, Mail, Lock, UserCircle } from 'lucide-react';

export default function RegisterPage() {
  const router = useRouter();
  const [formData, setFormData] = useState({
    fullName: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
  });
  const [errors, setErrors] = useState<any>({});
  const [isLoading, setIsLoading] = useState(false);
  const [passwordStrength, setPasswordStrength] = useState<any>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
    
    if (errors[name]) {
      setErrors((prev: any) => ({ ...prev, [name]: '' }));
    }

    if (name === 'password') {
      const validation = validatePassword(value);
      setPasswordStrength(validation);
    }
  };

  const validate = () => {
    const newErrors: any = {};

    if (!formData.fullName.trim()) {
      newErrors.fullName = 'El nombre completo es obligatorio';
    }

    if (!formData.username.trim()) {
      newErrors.username = 'El nombre de usuario es obligatorio';
    } else if (formData.username.length < 3) {
      newErrors.username = 'El nombre de usuario debe tener al menos 3 caracteres';
    }

    if (!formData.email.trim()) {
      newErrors.email = 'El email es obligatorio';
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
      newErrors.email = 'Email inválido';
    }

    const passwordValidation = validatePassword(formData.password);
    if (!passwordValidation.isValid) {
      newErrors.password = passwordValidation.message;
    }

    if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = 'Las contraseñas no coinciden';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!validate()) return;

    setIsLoading(true);

    try {
      const res = await fetch('/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          fullName: formData.fullName,
          username: formData.username,
          email: formData.email,
          password: formData.password,
        }),
      });

      const data = await res.json();

      if (!res.ok) {
        setErrors({ submit: data.error || 'Error al crear la cuenta' });
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

  const strengthColors: Record<'weak' | 'medium' | 'strong', string> = {
    weak: 'bg-red-500',
    medium: 'bg-yellow-500',
    strong: 'bg-green-500',
  };

  return (
    <main className="min-h-screen">
      <Navbar />
      
      <div className="pt-24 pb-12 px-4">
        <div className="max-w-md mx-auto">
          <div className="text-center mb-8 animate-slide-up">
            <h1 className="text-4xl font-bold text-white mb-2">Crear cuenta</h1>
            <p className="text-platinum-300">
              Únete a Smurf Bank y recibe 1,000 Smurf de bienvenida
            </p>
          </div>

          <div className="bg-glass-gradient border border-platinum-700/30 rounded-3xl p-8 backdrop-blur-xl shadow-premium animate-fade-in">
            <form onSubmit={handleSubmit} className="space-y-6">
              <Input
                label="Nombre completo"
                name="fullName"
                type="text"
                placeholder="Juan Pérez"
                value={formData.fullName}
                onChange={handleChange}
                error={errors.fullName}
                icon={<User size={20} />}
                autoComplete="name"
              />

              <Input
                label="Nombre de usuario"
                name="username"
                type="text"
                placeholder="juanperez"
                value={formData.username}
                onChange={handleChange}
                error={errors.username}
                icon={<UserCircle size={20} />}
                autoComplete="username"
              />

              <Input
                label="Email"
                name="email"
                type="email"
                placeholder="juan@ejemplo.com"
                value={formData.email}
                onChange={handleChange}
                error={errors.email}
                icon={<Mail size={20} />}
                autoComplete="email"
              />

              <div>
                <Input
                  label="Contraseña"
                  name="password"
                  type="password"
                  placeholder="Mínimo 8 caracteres"
                  value={formData.password}
                  onChange={handleChange}
                  error={errors.password}
                  icon={<Lock size={20} />}
                  autoComplete="new-password"
                />
                {passwordStrength && formData.password && (
                  <div className="mt-3 space-y-2">
                    <div className="flex gap-2">
                      <div className={`h-1 flex-1 rounded-full ${passwordStrength.strength === 'weak' ? strengthColors.weak : 'bg-dark-700'}`} />
                      <div className={`h-1 flex-1 rounded-full ${passwordStrength.strength === 'medium' || passwordStrength.strength === 'strong' ? strengthColors[passwordStrength.strength as 'medium' | 'strong'] : 'bg-dark-700'}`} />
                      <div className={`h-1 flex-1 rounded-full ${passwordStrength.strength === 'strong' ? strengthColors.strong : 'bg-dark-700'}`} />
                    </div>
                    <p className={`text-xs ${passwordStrength.strength === 'strong' ? 'text-green-400' : passwordStrength.strength === 'medium' ? 'text-yellow-400' : 'text-red-400'}`}>
                      {passwordStrength.message}
                    </p>
                  </div>
                )}
              </div>

              <Input
                label="Confirmar contraseña"
                name="confirmPassword"
                type="password"
                placeholder="Repite tu contraseña"
                value={formData.confirmPassword}
                onChange={handleChange}
                error={errors.confirmPassword}
                icon={<Lock size={20} />}
                autoComplete="new-password"
              />

              {errors.submit && (
                <div className="p-4 bg-red-500/10 border border-red-500/30 rounded-xl">
                  <p className="text-red-400 text-sm">{errors.submit}</p>
                </div>
              )}

              <Button type="submit" variant="primary" size="lg" className="w-full" isLoading={isLoading}>
                Crear cuenta
              </Button>

              <p className="text-center text-platinum-400 text-sm">
                ¿Ya tienes cuenta?{' '}
                <Link href="/login" className="text-smurf-400 hover:text-smurf-300 transition-colors">
                  Iniciar sesión
                </Link>
              </p>
            </form>
          </div>
        </div>
      </div>
    </main>
  );
}
