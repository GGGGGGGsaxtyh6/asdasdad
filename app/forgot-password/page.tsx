'use client';

import { useState } from 'react';
import { motion } from 'framer-motion';
import Link from 'next/link';
import { FiMail, FiArrowLeft, FiCheckCircle } from 'react-icons/fi';
import { Input } from '@/components/ui/Input';
import { Button } from '@/components/ui/Button';
import { GlassCard } from '@/components/ui/GlassCard';
import { Toast } from '@/components/ui/Toast';
import { validateEmail } from '@/lib/utils';

export default function ForgotPasswordPage() {
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);
  const [toast, setToast] = useState({ show: false, message: '', type: 'info' as 'success' | 'error' | 'info' });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!email) {
      setError('Email is required');
      return;
    }

    if (!validateEmail(email)) {
      setError('Invalid email format');
      return;
    }

    setLoading(true);

    // Simulate API call - in production, this would send a reset email
    setTimeout(() => {
      setSuccess(true);
      setToast({ show: true, message: 'Password reset instructions sent to your email', type: 'success' });
      setLoading(false);
    }, 1500);
  };

  if (success) {
    return (
      <div className="min-h-screen flex items-center justify-center px-4 py-12 relative overflow-hidden">
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

        <motion.div
          initial={{ opacity: 0, scale: 0.9 }}
          animate={{ opacity: 1, scale: 1 }}
          className="relative z-10 max-w-md w-full"
        >
          <GlassCard className="p-8 text-center" hover={false}>
            <div className="w-16 h-16 bg-green-500/20 rounded-full flex items-center justify-center mx-auto mb-6">
              <FiCheckCircle className="w-8 h-8 text-green-500" />
            </div>
            
            <h1 className="text-2xl font-bold mb-4 text-gray-900 dark:text-white">
              Check Your Email
            </h1>
            
            <p className="text-gray-600 dark:text-gray-400 mb-8">
              We've sent password reset instructions to <strong>{email}</strong>. 
              Please check your inbox and follow the link to reset your password.
            </p>

            <div className="space-y-4">
              <Link href="/login">
                <Button className="w-full">
                  Back to Sign In
                </Button>
              </Link>
              
              <button
                onClick={() => setSuccess(false)}
                className="text-sm text-smurf-500 hover:text-smurf-600"
              >
                Didn't receive the email? Try again
              </button>
            </div>
          </GlassCard>
        </motion.div>
      </div>
    );
  }

  return (
    <div className="min-h-screen flex items-center justify-center px-4 py-12 relative overflow-hidden">
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

      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        className="relative z-10 max-w-md w-full"
      >
        <Link href="/login" className="inline-flex items-center gap-2 text-gray-600 dark:text-gray-400 hover:text-smurf-500 transition-colors mb-6">
          <FiArrowLeft />
          Back to Sign In
        </Link>

        <GlassCard className="p-8" hover={false}>
          <h1 className="text-3xl font-bold mb-2 text-gray-900 dark:text-white">
            Forgot Password?
          </h1>
          <p className="text-gray-600 dark:text-gray-400 mb-8">
            No worries, we'll send you reset instructions.
          </p>

          <form onSubmit={handleSubmit} className="space-y-6">
            <Input
              label="Email Address"
              name="email"
              type="email"
              placeholder="john@example.com"
              icon={<FiMail />}
              value={email}
              onChange={(e) => {
                setEmail(e.target.value);
                if (error) setError('');
              }}
              error={error}
            />

            <Button type="submit" className="w-full" loading={loading}>
              Send Reset Instructions
            </Button>
          </form>
        </GlassCard>
      </motion.div>
    </div>
  );
}
