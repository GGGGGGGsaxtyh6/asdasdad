'use client';

import { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { FiUser, FiDollarSign, FiMessageSquare, FiCheck, FiArrowRight } from 'react-icons/fi';
import { GlassCard } from '@/components/ui/GlassCard';
import { Input } from '@/components/ui/Input';
import { Button } from '@/components/ui/Button';
import { Toast } from '@/components/ui/Toast';
import { Scene3D } from '@/components/3d/Scene3D';
import { TransferAnimation } from '@/components/3d/TransferAnimation';
import { formatCurrency } from '@/lib/utils';

export default function TransferPage() {
  const [step, setStep] = useState<'form' | 'confirm' | 'processing' | 'success'>('form');
  const [profile, setProfile] = useState<any>(null);
  const [formData, setFormData] = useState({
    receiverUsername: '',
    amount: '',
    description: '',
  });
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [toast, setToast] = useState({ show: false, message: '', type: 'info' as 'success' | 'error' | 'info' });
  const [receiverInfo, setReceiverInfo] = useState<any>(null);
  const [showAnimation, setShowAnimation] = useState(false);

  useEffect(() => {
    const fetchProfile = async () => {
      const res = await fetch('/api/user/profile');
      if (res.ok) {
        const data = await res.json();
        setProfile(data);
      }
    };
    fetchProfile();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
    if (errors[name]) {
      setErrors(prev => ({ ...prev, [name]: '' }));
    }
  };

  const validate = () => {
    const newErrors: Record<string, string> = {};

    if (!formData.receiverUsername.trim()) {
      newErrors.receiverUsername = 'Username is required';
    }

    const amount = parseFloat(formData.amount);
    if (!formData.amount || isNaN(amount) || amount <= 0) {
      newErrors.amount = 'Please enter a valid amount';
    } else if (profile && amount > profile.balance) {
      newErrors.amount = 'Insufficient balance';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!validate()) return;

    // For demo purposes, simulate receiver lookup
    setReceiverInfo({
      username: formData.receiverUsername,
      name: formData.receiverUsername.charAt(0).toUpperCase() + formData.receiverUsername.slice(1),
    });
    setStep('confirm');
  };

  const handleConfirm = async () => {
    setStep('processing');
    setShowAnimation(true);

    try {
      const response = await fetch('/api/transactions', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          receiverUsername: formData.receiverUsername,
          amount: parseFloat(formData.amount),
          description: formData.description,
        }),
      });

      if (!response.ok) {
        const data = await response.json();
        throw new Error(data.error || 'Transfer failed');
      }

      // Wait for animation to complete
      setTimeout(() => {
        setStep('success');
        setShowAnimation(false);
        
        // Update profile balance
        if (profile) {
          setProfile({
            ...profile,
            balance: profile.balance - parseFloat(formData.amount),
          });
        }
      }, 3000);
    } catch (error: any) {
      setToast({ show: true, message: error.message, type: 'error' });
      setStep('form');
      setShowAnimation(false);
    }
  };

  const handleReset = () => {
    setFormData({ receiverUsername: '', amount: '', description: '' });
    setErrors({});
    setStep('form');
    setReceiverInfo(null);
  };

  return (
    <div className="max-w-4xl mx-auto space-y-8">
      <Toast
        isVisible={toast.show}
        message={toast.message}
        type={toast.type}
        onClose={() => setToast(prev => ({ ...prev, show: false }))}
      />

      {/* Header */}
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
      >
        <h1 className="text-4xl font-bold text-gray-900 dark:text-white mb-2">
          Send Smurf
        </h1>
        <p className="text-gray-600 dark:text-gray-400">
          Transfer funds instantly to any Smurf Bank user
        </p>
      </motion.div>

      {/* Balance Display */}
      {profile && (
        <GlassCard className="p-6">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Available Balance</p>
              <p className="text-3xl font-bold text-gray-900 dark:text-white">
                {formatCurrency(profile.balance)} <span className="text-lg text-smurf-500">Smurf</span>
              </p>
            </div>
          </div>
        </GlassCard>
      )}

      <AnimatePresence mode="wait">
        {/* Form Step */}
        {step === 'form' && (
          <motion.div
            key="form"
            initial={{ opacity: 0, x: 20 }}
            animate={{ opacity: 1, x: 0 }}
            exit={{ opacity: 0, x: -20 }}
          >
            <GlassCard className="p-8">
              <form onSubmit={handleSubmit} className="space-y-6">
                <Input
                  label="Recipient Username"
                  name="receiverUsername"
                  type="text"
                  placeholder="Enter username"
                  icon={<FiUser />}
                  value={formData.receiverUsername}
                  onChange={handleChange}
                  error={errors.receiverUsername}
                />

                <Input
                  label="Amount (Smurf)"
                  name="amount"
                  type="number"
                  step="0.01"
                  placeholder="0.00"
                  icon={<FiDollarSign />}
                  value={formData.amount}
                  onChange={handleChange}
                  error={errors.amount}
                />

                <div>
                  <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                    Description (Optional)
                  </label>
                  <textarea
                    name="description"
                    placeholder="Add a note..."
                    value={formData.description}
                    onChange={handleChange}
                    rows={3}
                    className="
                      w-full px-4 py-3
                      bg-white/10 dark:bg-white/5 backdrop-blur-md
                      border-2 border-white/20 dark:border-white/10
                      rounded-xl
                      text-gray-900 dark:text-white
                      placeholder-gray-500 dark:placeholder-gray-400
                      transition-all duration-200
                      focus:outline-none focus:border-smurf-500 focus:ring-4 focus:ring-smurf-500/20
                    "
                  />
                </div>

                <Button type="submit" className="w-full" size="lg">
                  Continue
                  <FiArrowRight />
                </Button>
              </form>
            </GlassCard>
          </motion.div>
        )}

        {/* Confirmation Step */}
        {step === 'confirm' && (
          <motion.div
            key="confirm"
            initial={{ opacity: 0, x: 20 }}
            animate={{ opacity: 1, x: 0 }}
            exit={{ opacity: 0, x: -20 }}
          >
            <GlassCard className="p-8">
              <h2 className="text-2xl font-bold text-gray-900 dark:text-white mb-6">
                Confirm Transfer
              </h2>

              <div className="space-y-4 mb-8">
                <div className="flex justify-between p-4 bg-white/50 dark:bg-white/5 rounded-xl">
                  <span className="text-gray-600 dark:text-gray-400">Recipient</span>
                  <span className="font-medium text-gray-900 dark:text-white">
                    {receiverInfo?.name} (@{receiverInfo?.username})
                  </span>
                </div>

                <div className="flex justify-between p-4 bg-white/50 dark:bg-white/5 rounded-xl">
                  <span className="text-gray-600 dark:text-gray-400">Amount</span>
                  <span className="font-bold text-2xl text-gray-900 dark:text-white">
                    {formatCurrency(parseFloat(formData.amount))} Smurf
                  </span>
                </div>

                {formData.description && (
                  <div className="p-4 bg-white/50 dark:bg-white/5 rounded-xl">
                    <span className="text-gray-600 dark:text-gray-400 block mb-2">Description</span>
                    <span className="text-gray-900 dark:text-white">{formData.description}</span>
                  </div>
                )}

                <div className="flex justify-between p-4 bg-smurf-500/10 rounded-xl border-2 border-smurf-500/20">
                  <span className="text-gray-600 dark:text-gray-400">New Balance</span>
                  <span className="font-bold text-gray-900 dark:text-white">
                    {formatCurrency(profile.balance - parseFloat(formData.amount))} Smurf
                  </span>
                </div>
              </div>

              <div className="flex gap-4">
                <Button variant="secondary" onClick={() => setStep('form')} className="flex-1">
                  Back
                </Button>
                <Button onClick={handleConfirm} className="flex-1">
                  Confirm Transfer
                </Button>
              </div>
            </GlassCard>
          </motion.div>
        )}

        {/* Processing Step */}
        {step === 'processing' && (
          <motion.div
            key="processing"
            initial={{ opacity: 0, scale: 0.9 }}
            animate={{ opacity: 1, scale: 1 }}
          >
            <GlassCard className="p-8">
              <div className="text-center">
                <div className="h-64 mb-6">
                  {showAnimation && (
                    <Scene3D camera={{ position: [0, 0, 10] }}>
                      <TransferAnimation onComplete={() => {}} />
                    </Scene3D>
                  )}
                </div>
                <h2 className="text-2xl font-bold text-gray-900 dark:text-white mb-2">
                  Processing Transfer
                </h2>
                <p className="text-gray-600 dark:text-gray-400">
                  Please wait while we process your transaction...
                </p>
              </div>
            </GlassCard>
          </motion.div>
        )}

        {/* Success Step */}
        {step === 'success' && (
          <motion.div
            key="success"
            initial={{ opacity: 0, scale: 0.9 }}
            animate={{ opacity: 1, scale: 1 }}
          >
            <GlassCard className="p-8 text-center">
              <div className="w-20 h-20 bg-green-500/20 rounded-full flex items-center justify-center mx-auto mb-6">
                <FiCheck className="w-10 h-10 text-green-500" />
              </div>

              <h2 className="text-3xl font-bold text-gray-900 dark:text-white mb-2">
                Transfer Successful!
              </h2>
              <p className="text-gray-600 dark:text-gray-400 mb-8">
                You've successfully sent {formatCurrency(parseFloat(formData.amount))} Smurf to {receiverInfo?.name}
              </p>

              <div className="flex gap-4 justify-center">
                <Button onClick={handleReset}>
                  Make Another Transfer
                </Button>
                <Button variant="secondary" onClick={() => window.location.href = '/dashboard'}>
                  Back to Dashboard
                </Button>
              </div>
            </GlassCard>
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
}
