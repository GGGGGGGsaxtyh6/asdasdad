'use client';

import { useState } from 'react';
import { motion } from 'framer-motion';
import { signIn } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { FiMail, FiLock, FiArrowLeft } from 'react-icons/fi';
import { Input } from '@/components/ui/Input';
import { Button } from '@/components/ui/Button';
import { GlassCard } from '@/components/ui/GlassCard';
import { Toast } from '@/components/ui/Toast';
import { Scene3D } from '@/components/3d/Scene3D';
import { CreditCard3D } from '@/components/3d/CreditCard3D';

export default function LoginPage() {
  const router = useRouter();
  const [formData, setFormData] = useState({
    identifier: '',
    password: '',
  });
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [loading, setLoading] = useState(false);
  const [toast, setToast] = useState({ show: false, message: '', type: 'info' as 'success' | 'error' | 'info' });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
    
    if (errors[name]) {
      setErrors(prev => ({ ...prev, [name]: '' }));
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!formData.identifier || !formData.password) {
      setErrors({
        identifier: !formData.identifier ? 'Email or username is required' : '',
        password: !formData.password ? 'Password is required' : '',
      });
      return;
    }

    setLoading(true);

    try {
      const result = await signIn('credentials', {
        identifier: formData.identifier,
        password: formData.password,
        redirect: false,
      });

      if (result?.error) {
        setToast({ show: true, message: 'Invalid credentials', type: 'error' });
      } else {
        setToast({ show: true, message: 'Login successful!', type: 'success' });
        setTimeout(() => {
          router.push('/dashboard');
        }, 1000);
      }
    } catch (error) {
      setToast({ show: true, message: 'An error occurred. Please try again.', type: 'error' });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center px-4 py-12 relative overflow-hidden">
      {/* Background effects */}
      <div className="absolute inset-0 z-0">
        <div className="absolute top-20 left-20 w-72 h-72 bg-smurf-500/20 rounded-full blur-3xl animate-float" />
        <div className="absolute bottom-20 right-20 w-96 h-96 bg-purple-500/20 rounded-full blur-3xl animate-float" style={{ animationDelay: '1s' }} />
      </div>

      <Toast
        isVisible={toast.show}
        message={toast.message}
        type={toast.type}
        onClose={() => setToast(prev => ({ ...prev, show: false }))}
      />

      <div className="container mx-auto max-w-6xl relative z-10">
        <div className="grid lg:grid-cols-2 gap-12 items-center">
          {/* Left side - 3D visual */}
          <motion.div
            initial={{ opacity: 0, x: -50 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ duration: 0.8 }}
            className="hidden lg:block"
          >
            <div className="h-[500px]">
              <Scene3D camera={{ position: [0, 0, 8] }}>
                <CreditCard3D />
              </Scene3D>
            </div>
            <div className="text-center mt-8">
              <h2 className="text-3xl font-bold mb-2 text-gray-900 dark:text-white">
                Welcome Back
              </h2>
              <p className="text-gray-600 dark:text-gray-400">
                Access your premium banking experience
              </p>
            </div>
          </motion.div>

          {/* Right side - Form */}
          <motion.div
            initial={{ opacity: 0, x: 50 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ duration: 0.8 }}
          >
            <Link href="/" className="inline-flex items-center gap-2 text-gray-600 dark:text-gray-400 hover:text-smurf-500 transition-colors mb-6">
              <FiArrowLeft />
              Back to Home
            </Link>

            <GlassCard className="p-8" hover={false}>
              <h1 className="text-3xl font-bold mb-2 text-gray-900 dark:text-white">
                Sign In
              </h1>
              <p className="text-gray-600 dark:text-gray-400 mb-8">
                Don't have an account?{' '}
                <Link href="/register" className="text-smurf-500 hover:text-smurf-600 font-medium">
                  Create one
                </Link>
              </p>

              <form onSubmit={handleSubmit} className="space-y-6">
                <Input
                  label="Email or Username"
                  name="identifier"
                  type="text"
                  placeholder="john@example.com or johndoe"
                  icon={<FiMail />}
                  value={formData.identifier}
                  onChange={handleChange}
                  error={errors.identifier}
                />

                <div>
                  <Input
                    label="Password"
                    name="password"
                    type="password"
                    placeholder="••••••••"
                    icon={<FiLock />}
                    value={formData.password}
                    onChange={handleChange}
                    error={errors.password}
                  />
                  <div className="mt-2 text-right">
                    <Link href="/forgot-password" className="text-sm text-smurf-500 hover:text-smurf-600">
                      Forgot password?
                    </Link>
                  </div>
                </div>

                <div className="flex items-center gap-2">
                  <input
                    type="checkbox"
                    id="remember"
                    className="rounded border-gray-300 text-smurf-500 focus:ring-smurf-500"
                  />
                  <label htmlFor="remember" className="text-sm text-gray-600 dark:text-gray-400">
                    Remember me for 30 days
                  </label>
                </div>

                <Button type="submit" className="w-full" loading={loading}>
                  Sign In
                </Button>
              </form>

              <div className="mt-8 pt-8 border-t border-gray-200/20 dark:border-gray-800/20">
                <p className="text-center text-sm text-gray-600 dark:text-gray-400">
                  Protected by bank-grade security
                </p>
              </div>
            </GlassCard>
          </motion.div>
        </div>
      </div>
    </div>
  );
}
