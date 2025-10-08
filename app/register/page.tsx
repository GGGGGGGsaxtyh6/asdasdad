'use client';

import { useState } from 'react';
import { motion } from 'framer-motion';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { FiUser, FiMail, FiLock, FiArrowLeft } from 'react-icons/fi';
import { Input } from '@/components/ui/Input';
import { Button } from '@/components/ui/Button';
import { GlassCard } from '@/components/ui/GlassCard';
import { Toast } from '@/components/ui/Toast';
import { Scene3D } from '@/components/3d/Scene3D';
import { SmurfCoin } from '@/components/3d/SmurfCoin';
import { validateEmail, validateUsername, validatePassword } from '@/lib/utils';

export default function RegisterPage() {
  const router = useRouter();
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    username: '',
    password: '',
    confirmPassword: '',
  });
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [loading, setLoading] = useState(false);
  const [toast, setToast] = useState({ show: false, message: '', type: 'info' as 'success' | 'error' | 'info' });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
    
    // Real-time validation
    if (errors[name]) {
      setErrors(prev => ({ ...prev, [name]: '' }));
    }
  };

  const validate = () => {
    const newErrors: Record<string, string> = {};

    if (!formData.name.trim()) {
      newErrors.name = 'Name is required';
    }

    if (!formData.email) {
      newErrors.email = 'Email is required';
    } else if (!validateEmail(formData.email)) {
      newErrors.email = 'Invalid email format';
    }

    if (!formData.username) {
      newErrors.username = 'Username is required';
    } else if (!validateUsername(formData.username)) {
      newErrors.username = 'Username must be 3-20 characters (letters, numbers, underscore only)';
    }

    if (!formData.password) {
      newErrors.password = 'Password is required';
    } else {
      const passwordValidation = validatePassword(formData.password);
      if (!passwordValidation.valid) {
        newErrors.password = passwordValidation.errors[0];
      }
    }

    if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = 'Passwords do not match';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!validate()) return;

    setLoading(true);

    try {
      const response = await fetch('/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          name: formData.name,
          email: formData.email,
          username: formData.username,
          password: formData.password,
        }),
      });

      const data = await response.json();

      if (!response.ok) {
        setToast({ show: true, message: data.error || 'Registration failed', type: 'error' });
        return;
      }

      setToast({ show: true, message: 'Account created successfully!', type: 'success' });
      setTimeout(() => {
        router.push('/login');
      }, 1500);
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
            <div className="h-[600px]">
              <Scene3D camera={{ position: [0, 0, 5] }}>
                <SmurfCoin position={[0, 0, 0]} />
              </Scene3D>
            </div>
            <div className="text-center mt-8">
              <h2 className="text-3xl font-bold mb-2 text-gray-900 dark:text-white">
                Join Smurf Bank
              </h2>
              <p className="text-gray-600 dark:text-gray-400">
                Start your journey with 10,000 Smurf welcome bonus
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
                Create Account
              </h1>
              <p className="text-gray-600 dark:text-gray-400 mb-8">
                Already have an account?{' '}
                <Link href="/login" className="text-smurf-500 hover:text-smurf-600 font-medium">
                  Sign in
                </Link>
              </p>

              <form onSubmit={handleSubmit} className="space-y-6">
                <Input
                  label="Full Name"
                  name="name"
                  type="text"
                  placeholder="John Doe"
                  icon={<FiUser />}
                  value={formData.name}
                  onChange={handleChange}
                  error={errors.name}
                />

                <Input
                  label="Email Address"
                  name="email"
                  type="email"
                  placeholder="john@example.com"
                  icon={<FiMail />}
                  value={formData.email}
                  onChange={handleChange}
                  error={errors.email}
                />

                <Input
                  label="Username"
                  name="username"
                  type="text"
                  placeholder="johndoe"
                  icon={<FiUser />}
                  value={formData.username}
                  onChange={handleChange}
                  error={errors.username}
                />

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

                <Input
                  label="Confirm Password"
                  name="confirmPassword"
                  type="password"
                  placeholder="••••••••"
                  icon={<FiLock />}
                  value={formData.confirmPassword}
                  onChange={handleChange}
                  error={errors.confirmPassword}
                />

                <div className="flex items-start gap-2">
                  <input
                    type="checkbox"
                    id="terms"
                    required
                    className="mt-1 rounded border-gray-300 text-smurf-500 focus:ring-smurf-500"
                  />
                  <label htmlFor="terms" className="text-sm text-gray-600 dark:text-gray-400">
                    I agree to the{' '}
                    <Link href="#" className="text-smurf-500 hover:text-smurf-600">
                      Terms of Service
                    </Link>{' '}
                    and{' '}
                    <Link href="#" className="text-smurf-500 hover:text-smurf-600">
                      Privacy Policy
                    </Link>
                  </label>
                </div>

                <Button type="submit" className="w-full" loading={loading}>
                  Create Account
                </Button>
              </form>
            </GlassCard>
          </motion.div>
        </div>
      </div>
    </div>
  );
}
