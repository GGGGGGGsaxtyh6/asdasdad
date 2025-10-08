'use client';

import { motion } from 'framer-motion';
import Link from 'next/link';
import { FiArrowRight, FiShield, FiZap, FiTrendingUp, FiGlobe, FiLock, FiCreditCard } from 'react-icons/fi';
import { Scene3D } from '@/components/3d/Scene3D';
import { SmurfCoin } from '@/components/3d/SmurfCoin';
import { CreditCard3D } from '@/components/3d/CreditCard3D';
import { Button } from '@/components/ui/Button';
import { GlassCard } from '@/components/ui/GlassCard';

export default function LandingPage() {
  const features = [
    {
      icon: <FiShield className="w-8 h-8" />,
      title: 'Bank-Grade Security',
      description: 'Military-grade encryption and multi-factor authentication protect your assets 24/7.',
    },
    {
      icon: <FiZap className="w-8 h-8" />,
      title: 'Instant Transfers',
      description: 'Send and receive Smurf instantly with zero fees and real-time confirmations.',
    },
    {
      icon: <FiTrendingUp className="w-8 h-8" />,
      title: 'Smart Analytics',
      description: 'Powerful insights and visualizations to track your spending and growth.',
    },
    {
      icon: <FiGlobe className="w-8 h-8" />,
      title: 'Global Access',
      description: 'Access your account securely from anywhere in the world, anytime.',
    },
    {
      icon: <FiLock className="w-8 h-8" />,
      title: 'Privacy First',
      description: 'Your data belongs to you. We never sell or share your information.',
    },
    {
      icon: <FiCreditCard className="w-8 h-8" />,
      title: 'Premium Cards',
      description: 'Exclusive credit cards with luxury benefits and rewards.',
    },
  ];

  return (
    <div className="min-h-screen relative overflow-hidden">
      {/* Hero Section */}
      <section className="relative min-h-screen flex items-center justify-center px-4 py-20">
        {/* Animated background */}
        <div className="absolute inset-0 z-0">
          <div className="absolute top-20 left-20 w-72 h-72 bg-smurf-500/20 rounded-full blur-3xl animate-float" />
          <div className="absolute bottom-20 right-20 w-96 h-96 bg-purple-500/20 rounded-full blur-3xl animate-float" style={{ animationDelay: '1s' }} />
          <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[600px] h-[600px] bg-blue-500/10 rounded-full blur-3xl animate-pulse-slow" />
        </div>

        <div className="container mx-auto max-w-7xl relative z-10">
          <div className="grid lg:grid-cols-2 gap-12 items-center">
            {/* Left Content */}
            <motion.div
              initial={{ opacity: 0, x: -50 }}
              animate={{ opacity: 1, x: 0 }}
              transition={{ duration: 0.8 }}
            >
              <motion.div
                className="inline-block mb-4 px-4 py-2 bg-smurf-500/10 rounded-full border border-smurf-500/20"
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ delay: 0.2 }}
              >
                <span className="text-smurf-600 dark:text-smurf-400 font-medium">
                  The Future of Banking
                </span>
              </motion.div>

              <h1 className="text-6xl lg:text-7xl font-bold mb-6 bg-clip-text text-transparent bg-gradient-to-r from-smurf-600 via-purple-600 to-pink-600">
                Welcome to Smurf Bank
              </h1>

              <p className="text-xl text-gray-700 dark:text-gray-300 mb-8 leading-relaxed">
                Experience banking reimagined. Ultra-secure, lightning-fast transactions with a luxury experience that sets new standards in digital finance.
              </p>

              <div className="flex flex-wrap gap-4">
                <Link href="/register">
                  <Button size="lg" className="group">
                    Get Started
                    <FiArrowRight className="group-hover:translate-x-1 transition-transform" />
                  </Button>
                </Link>
                <Link href="/login">
                  <Button size="lg" variant="secondary">
                    Sign In
                  </Button>
                </Link>
              </div>

              <div className="mt-12 flex items-center gap-8">
                <div>
                  <div className="text-3xl font-bold text-gray-900 dark:text-white">100K+</div>
                  <div className="text-sm text-gray-600 dark:text-gray-400">Active Users</div>
                </div>
                <div>
                  <div className="text-3xl font-bold text-gray-900 dark:text-white">$50M+</div>
                  <div className="text-sm text-gray-600 dark:text-gray-400">Transactions</div>
                </div>
                <div>
                  <div className="text-3xl font-bold text-gray-900 dark:text-white">99.9%</div>
                  <div className="text-sm text-gray-600 dark:text-gray-400">Uptime</div>
                </div>
              </div>
            </motion.div>

            {/* Right Content - 3D Scene */}
            <motion.div
              initial={{ opacity: 0, x: 50 }}
              animate={{ opacity: 1, x: 0 }}
              transition={{ duration: 0.8, delay: 0.2 }}
              className="h-[600px]"
            >
              <Scene3D camera={{ position: [0, 0, 8] }}>
                <CreditCard3D />
              </Scene3D>
            </motion.div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-20 px-4 relative z-10">
        <div className="container mx-auto max-w-7xl">
          <motion.div
            initial={{ opacity: 0, y: 30 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="text-center mb-16"
          >
            <h2 className="text-5xl font-bold mb-4 text-gray-900 dark:text-white">
              Why Choose Smurf?
            </h2>
            <p className="text-xl text-gray-600 dark:text-gray-400">
              Premium features designed for the modern digital era
            </p>
          </motion.div>

          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
            {features.map((feature, index) => (
              <motion.div
                key={index}
                initial={{ opacity: 0, y: 30 }}
                whileInView={{ opacity: 1, y: 0 }}
                viewport={{ once: true }}
                transition={{ delay: index * 0.1 }}
              >
                <GlassCard className="p-8 h-full">
                  <div className="text-smurf-500 dark:text-smurf-400 mb-4">
                    {feature.icon}
                  </div>
                  <h3 className="text-2xl font-bold mb-3 text-gray-900 dark:text-white">
                    {feature.title}
                  </h3>
                  <p className="text-gray-600 dark:text-gray-400">
                    {feature.description}
                  </p>
                </GlassCard>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-32 px-4 relative z-10">
        <div className="container mx-auto max-w-4xl">
          <GlassCard className="p-12 text-center">
            <motion.div
              initial={{ opacity: 0, scale: 0.9 }}
              whileInView={{ opacity: 1, scale: 1 }}
              viewport={{ once: true }}
            >
              <div className="h-32 mb-8">
                <Scene3D camera={{ position: [0, 0, 5] }}>
                  <SmurfCoin position={[0, 0, 0]} />
                </Scene3D>
              </div>

              <h2 className="text-4xl md:text-5xl font-bold mb-6 text-gray-900 dark:text-white">
                Ready to Get Started?
              </h2>
              <p className="text-xl text-gray-600 dark:text-gray-400 mb-8">
                Join thousands of users experiencing the future of banking today.
              </p>
              <Link href="/register">
                <Button size="lg" className="group">
                  Create Your Account
                  <FiArrowRight className="group-hover:translate-x-1 transition-transform" />
                </Button>
              </Link>
            </motion.div>
          </GlassCard>
        </div>
      </section>

      {/* Footer */}
      <footer className="py-12 px-4 border-t border-gray-200/20 dark:border-gray-800/20 relative z-10">
        <div className="container mx-auto max-w-7xl">
          <div className="grid md:grid-cols-4 gap-8">
            <div>
              <h3 className="text-2xl font-bold mb-4 bg-clip-text text-transparent bg-gradient-to-r from-smurf-600 to-purple-600">
                Smurf Bank
              </h3>
              <p className="text-gray-600 dark:text-gray-400">
                The future of digital banking, today.
              </p>
            </div>
            <div>
              <h4 className="font-bold mb-4 text-gray-900 dark:text-white">Product</h4>
              <ul className="space-y-2 text-gray-600 dark:text-gray-400">
                <li><Link href="#" className="hover:text-smurf-500 transition-colors">Features</Link></li>
                <li><Link href="#" className="hover:text-smurf-500 transition-colors">Security</Link></li>
                <li><Link href="#" className="hover:text-smurf-500 transition-colors">Pricing</Link></li>
              </ul>
            </div>
            <div>
              <h4 className="font-bold mb-4 text-gray-900 dark:text-white">Company</h4>
              <ul className="space-y-2 text-gray-600 dark:text-gray-400">
                <li><Link href="#" className="hover:text-smurf-500 transition-colors">About</Link></li>
                <li><Link href="#" className="hover:text-smurf-500 transition-colors">Careers</Link></li>
                <li><Link href="#" className="hover:text-smurf-500 transition-colors">Contact</Link></li>
              </ul>
            </div>
            <div>
              <h4 className="font-bold mb-4 text-gray-900 dark:text-white">Legal</h4>
              <ul className="space-y-2 text-gray-600 dark:text-gray-400">
                <li><Link href="#" className="hover:text-smurf-500 transition-colors">Privacy</Link></li>
                <li><Link href="#" className="hover:text-smurf-500 transition-colors">Terms</Link></li>
                <li><Link href="#" className="hover:text-smurf-500 transition-colors">Compliance</Link></li>
              </ul>
            </div>
          </div>
          <div className="mt-12 pt-8 border-t border-gray-200/20 dark:border-gray-800/20 text-center text-gray-600 dark:text-gray-400">
            <p>&copy; 2024 Smurf Bank. All rights reserved.</p>
          </div>
        </div>
      </footer>
    </div>
  );
}
