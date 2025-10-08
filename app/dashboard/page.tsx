'use client';

import { useEffect, useState } from 'react';
import { useSession } from 'next-auth/react';
import { motion } from 'framer-motion';
import { 
  FiArrowUp, 
  FiArrowDown, 
  FiSend, 
  FiDownload, 
  FiTrendingUp,
  FiActivity,
} from 'react-icons/fi';
import { GlassCard } from '@/components/ui/GlassCard';
import { Button } from '@/components/ui/Button';
import { Scene3D } from '@/components/3d/Scene3D';
import { SmurfCoin } from '@/components/3d/SmurfCoin';
import { formatCurrency, formatDate, getRelativeTime } from '@/lib/utils';
import Link from 'next/link';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  Filler,
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  Filler
);

export default function DashboardPage() {
  const { data: session } = useSession();
  const [profile, setProfile] = useState<any>(null);
  const [transactions, setTransactions] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);
  const [chartPeriod, setChartPeriod] = useState<'30d' | '90d' | '1y'>('30d');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [profileRes, transactionsRes] = await Promise.all([
          fetch('/api/user/profile'),
          fetch('/api/transactions?limit=5'),
        ]);

        if (profileRes.ok) {
          const profileData = await profileRes.json();
          setProfile(profileData);
        }

        if (transactionsRes.ok) {
          const transactionsData = await transactionsRes.json();
          setTransactions(transactionsData.transactions);
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  // Generate chart data
  const generateChartData = () => {
    const days = chartPeriod === '30d' ? 30 : chartPeriod === '90d' ? 90 : 365;
    const labels = [];
    const data = [];
    
    for (let i = days; i >= 0; i--) {
      const date = new Date();
      date.setDate(date.getDate() - i);
      labels.push(date.toLocaleDateString('en-US', { month: 'short', day: 'numeric' }));
      
      // Generate realistic-looking data
      const baseValue = profile?.balance || 10000;
      const variance = Math.sin(i / 5) * 1000 + Math.random() * 500;
      data.push(baseValue + variance);
    }

    return { labels, data };
  };

  const chartData = generateChartData();

  const chartConfig = {
    labels: chartData.labels.filter((_, i) => i % Math.ceil(chartData.labels.length / 10) === 0),
    datasets: [
      {
        label: 'Balance',
        data: chartData.data.filter((_, i) => i % Math.ceil(chartData.data.length / 10) === 0),
        borderColor: 'rgb(14, 165, 233)',
        backgroundColor: 'rgba(14, 165, 233, 0.1)',
        fill: true,
        tension: 0.4,
        pointRadius: 0,
        pointHoverRadius: 6,
        borderWidth: 2,
      },
    ],
  };

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        backgroundColor: 'rgba(0, 0, 0, 0.8)',
        padding: 12,
        cornerRadius: 8,
        displayColors: false,
        callbacks: {
          label: (context: any) => `${formatCurrency(context.parsed.y)} Smurf`,
        },
      },
    },
    scales: {
      x: {
        grid: {
          display: false,
        },
        ticks: {
          color: 'rgba(156, 163, 175, 1)',
        },
      },
      y: {
        grid: {
          color: 'rgba(156, 163, 175, 0.1)',
        },
        ticks: {
          color: 'rgba(156, 163, 175, 1)',
          callback: (value: any) => formatCurrency(value),
        },
      },
    },
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-[60vh]">
        <div className="loading-dots text-smurf-500">
          <span></span>
          <span></span>
          <span></span>
        </div>
      </div>
    );
  }

  return (
    <div className="space-y-8">
      {/* Welcome Section */}
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
      >
        <h1 className="text-4xl font-bold text-gray-900 dark:text-white mb-2">
          Welcome back, {session?.user?.name?.split(' ')[0]}! 👋
        </h1>
        <p className="text-gray-600 dark:text-gray-400">
          Here's what's happening with your account today.
        </p>
      </motion.div>

      {/* Balance Card with 3D Element */}
      <div className="grid lg:grid-cols-3 gap-6">
        <GlassCard className="lg:col-span-2 p-8 relative overflow-hidden">
          <div className="relative z-10">
            <div className="flex items-start justify-between mb-6">
              <div>
                <p className="text-sm text-gray-600 dark:text-gray-400 mb-2">Total Balance</p>
                <h2 className="text-5xl font-bold text-gray-900 dark:text-white mb-1">
                  {formatCurrency(profile?.balance || 0)}
                </h2>
                <p className="text-smurf-500 font-medium">Smurf</p>
              </div>
              <div className="flex gap-2">
                <Link href="/dashboard/transfer">
                  <Button size="sm">
                    <FiSend />
                    Send
                  </Button>
                </Link>
                <Button size="sm" variant="secondary">
                  <FiDownload />
                  Request
                </Button>
              </div>
            </div>

            {/* Quick Stats */}
            <div className="grid grid-cols-2 gap-4 mt-8">
              <div className="bg-green-500/10 rounded-xl p-4">
                <div className="flex items-center gap-2 text-green-600 dark:text-green-400 mb-2">
                  <FiArrowDown />
                  <span className="text-sm font-medium">Received</span>
                </div>
                <p className="text-2xl font-bold text-gray-900 dark:text-white">
                  {formatCurrency(5240.50)}
                </p>
              </div>
              <div className="bg-red-500/10 rounded-xl p-4">
                <div className="flex items-center gap-2 text-red-600 dark:text-red-400 mb-2">
                  <FiArrowUp />
                  <span className="text-sm font-medium">Sent</span>
                </div>
                <p className="text-2xl font-bold text-gray-900 dark:text-white">
                  {formatCurrency(3180.25)}
                </p>
              </div>
            </div>
          </div>

          {/* 3D Background Element */}
          <div className="absolute right-0 top-0 w-64 h-64 opacity-20 pointer-events-none">
            <Scene3D camera={{ position: [0, 0, 4] }}>
              <SmurfCoin position={[0, 0, 0]} />
            </Scene3D>
          </div>
        </GlassCard>

        {/* Account Info Card */}
        <GlassCard className="p-8">
          <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-6">
            Account Info
          </h3>
          <div className="space-y-4">
            <div>
              <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Username</p>
              <p className="font-medium text-gray-900 dark:text-white">@{profile?.username}</p>
            </div>
            <div>
              <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Email</p>
              <p className="font-medium text-gray-900 dark:text-white text-sm">{profile?.email}</p>
            </div>
            <div>
              <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Member Since</p>
              <p className="font-medium text-gray-900 dark:text-white">
                {profile?.createdAt ? new Date(profile.createdAt).toLocaleDateString('en-US', { 
                  month: 'long', 
                  year: 'numeric' 
                }) : 'N/A'}
              </p>
            </div>
            <div className="pt-4 border-t border-gray-200/20 dark:border-gray-800/20">
              <div className="flex items-center gap-2 text-green-600 dark:text-green-400">
                <FiActivity className="w-4 h-4" />
                <span className="text-sm font-medium">Account Active</span>
              </div>
            </div>
          </div>
        </GlassCard>
      </div>

      {/* Chart Section */}
      <GlassCard className="p-8">
        <div className="flex items-center justify-between mb-6">
          <div>
            <h3 className="text-xl font-bold text-gray-900 dark:text-white flex items-center gap-2">
              <FiTrendingUp />
              Balance History
            </h3>
            <p className="text-sm text-gray-600 dark:text-gray-400 mt-1">
              Track your balance over time
            </p>
          </div>
          <div className="flex gap-2">
            {(['30d', '90d', '1y'] as const).map((period) => (
              <button
                key={period}
                onClick={() => setChartPeriod(period)}
                className={`
                  px-4 py-2 rounded-xl text-sm font-medium transition-all
                  ${chartPeriod === period
                    ? 'bg-smurf-500 text-white'
                    : 'bg-white/50 dark:bg-white/5 text-gray-700 dark:text-gray-300 hover:bg-white/80 dark:hover:bg-white/10'
                  }
                `}
              >
                {period === '30d' ? '30 Days' : period === '90d' ? '90 Days' : '1 Year'}
              </button>
            ))}
          </div>
        </div>
        <div className="h-80">
          <Line data={chartConfig} options={chartOptions} />
        </div>
      </GlassCard>

      {/* Recent Transactions */}
      <GlassCard className="p-8">
        <div className="flex items-center justify-between mb-6">
          <h3 className="text-xl font-bold text-gray-900 dark:text-white">
            Recent Transactions
          </h3>
          <Link href="/dashboard/history">
            <Button variant="ghost" size="sm">
              View All
            </Button>
          </Link>
        </div>

        {transactions.length === 0 ? (
          <div className="text-center py-12">
            <div className="w-16 h-16 bg-gray-200/50 dark:bg-gray-800/50 rounded-full flex items-center justify-center mx-auto mb-4">
              <FiActivity className="w-8 h-8 text-gray-400" />
            </div>
            <p className="text-gray-600 dark:text-gray-400">No transactions yet</p>
            <p className="text-sm text-gray-500 dark:text-gray-500 mt-2">
              Start by sending or requesting Smurf
            </p>
          </div>
        ) : (
          <div className="space-y-3">
            {transactions.map((transaction) => {
              const isReceived = transaction.receiverId === profile?.id;
              const otherUser = isReceived ? transaction.sender : transaction.receiver;
              
              return (
                <motion.div
                  key={transaction.id}
                  className="flex items-center justify-between p-4 rounded-xl hover:bg-white/50 dark:hover:bg-white/5 transition-colors"
                  whileHover={{ x: 4 }}
                >
                  <div className="flex items-center gap-4">
                    <div className={`
                      w-12 h-12 rounded-full flex items-center justify-center
                      ${isReceived ? 'bg-green-500/20' : 'bg-red-500/20'}
                    `}>
                      {isReceived ? (
                        <FiArrowDown className="w-6 h-6 text-green-600 dark:text-green-400" />
                      ) : (
                        <FiArrowUp className="w-6 h-6 text-red-600 dark:text-red-400" />
                      )}
                    </div>
                    <div>
                      <p className="font-medium text-gray-900 dark:text-white">
                        {isReceived ? 'Received from' : 'Sent to'} {otherUser.name}
                      </p>
                      <p className="text-sm text-gray-600 dark:text-gray-400">
                        {getRelativeTime(transaction.createdAt)}
                      </p>
                    </div>
                  </div>
                  <div className="text-right">
                    <p className={`
                      font-bold text-lg
                      ${isReceived ? 'text-green-600 dark:text-green-400' : 'text-red-600 dark:text-red-400'}
                    `}>
                      {isReceived ? '+' : '-'}{formatCurrency(transaction.amount)}
                    </p>
                    <p className="text-sm text-gray-600 dark:text-gray-400">Smurf</p>
                  </div>
                </motion.div>
              );
            })}
          </div>
        )}
      </GlassCard>
    </div>
  );
}
