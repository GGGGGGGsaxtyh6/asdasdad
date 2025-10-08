'use client';

import { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { 
  FiArrowUp, 
  FiArrowDown, 
  FiFilter, 
  FiSearch,
  FiChevronDown,
  FiCheck,
  FiX,
} from 'react-icons/fi';
import { GlassCard } from '@/components/ui/GlassCard';
import { Button } from '@/components/ui/Button';
import { formatCurrency, formatDate, getRelativeTime } from '@/lib/utils';

export default function HistoryPage() {
  const [profile, setProfile] = useState<any>(null);
  const [transactions, setTransactions] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);
  const [hasMore, setHasMore] = useState(false);
  const [expandedId, setExpandedId] = useState<string | null>(null);
  
  // Filters
  const [searchTerm, setSearchTerm] = useState('');
  const [typeFilter, setTypeFilter] = useState<'all' | 'send' | 'receive'>('all');
  const [statusFilter, setStatusFilter] = useState<'all' | 'completed' | 'pending' | 'failed'>('all');
  const [showFilters, setShowFilters] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [profileRes, transactionsRes] = await Promise.all([
          fetch('/api/user/profile'),
          fetch('/api/transactions?limit=50'),
        ]);

        if (profileRes.ok) {
          const profileData = await profileRes.json();
          setProfile(profileData);
        }

        if (transactionsRes.ok) {
          const data = await transactionsRes.json();
          setTransactions(data.transactions);
          setHasMore(data.hasMore);
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const filteredTransactions = transactions.filter(transaction => {
    // Type filter
    if (typeFilter !== 'all') {
      const isReceived = transaction.receiverId === profile?.id;
      if (typeFilter === 'send' && isReceived) return false;
      if (typeFilter === 'receive' && !isReceived) return false;
    }

    // Status filter
    if (statusFilter !== 'all' && transaction.status !== statusFilter) {
      return false;
    }

    // Search filter
    if (searchTerm) {
      const isReceived = transaction.receiverId === profile?.id;
      const otherUser = isReceived ? transaction.sender : transaction.receiver;
      const searchLower = searchTerm.toLowerCase();
      
      return (
        otherUser.name.toLowerCase().includes(searchLower) ||
        otherUser.username.toLowerCase().includes(searchLower) ||
        transaction.description?.toLowerCase().includes(searchLower)
      );
    }

    return true;
  });

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
    <div className="max-w-6xl mx-auto space-y-6">
      {/* Header */}
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        className="flex items-center justify-between"
      >
        <div>
          <h1 className="text-4xl font-bold text-gray-900 dark:text-white mb-2">
            Transaction History
          </h1>
          <p className="text-gray-600 dark:text-gray-400">
            View and manage all your transactions
          </p>
        </div>
      </motion.div>

      {/* Filters Bar */}
      <GlassCard className="p-6">
        <div className="space-y-4">
          {/* Search and Filter Toggle */}
          <div className="flex gap-4">
            <div className="flex-1 relative">
              <FiSearch className="absolute left-4 top-1/2 -translate-y-1/2 text-gray-400" />
              <input
                type="text"
                placeholder="Search by name, username, or description..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="
                  w-full pl-12 pr-4 py-3
                  bg-white/50 dark:bg-white/5 backdrop-blur-md
                  border-2 border-white/20 dark:border-white/10
                  rounded-xl
                  text-gray-900 dark:text-white
                  placeholder-gray-500 dark:placeholder-gray-400
                  transition-all duration-200
                  focus:outline-none focus:border-smurf-500 focus:ring-4 focus:ring-smurf-500/20
                "
              />
            </div>
            <Button
              variant="secondary"
              onClick={() => setShowFilters(!showFilters)}
              className="flex items-center gap-2"
            >
              <FiFilter />
              Filters
              <FiChevronDown className={`transition-transform ${showFilters ? 'rotate-180' : ''}`} />
            </Button>
          </div>

          {/* Advanced Filters */}
          <AnimatePresence>
            {showFilters && (
              <motion.div
                initial={{ opacity: 0, height: 0 }}
                animate={{ opacity: 1, height: 'auto' }}
                exit={{ opacity: 0, height: 0 }}
                className="grid md:grid-cols-2 gap-4 pt-4 border-t border-gray-200/20 dark:border-gray-800/20"
              >
                <div>
                  <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                    Transaction Type
                  </label>
                  <div className="flex gap-2">
                    {(['all', 'send', 'receive'] as const).map((type) => (
                      <button
                        key={type}
                        onClick={() => setTypeFilter(type)}
                        className={`
                          flex-1 px-4 py-2 rounded-xl text-sm font-medium transition-all capitalize
                          ${typeFilter === type
                            ? 'bg-smurf-500 text-white'
                            : 'bg-white/50 dark:bg-white/5 text-gray-700 dark:text-gray-300 hover:bg-white/80 dark:hover:bg-white/10'
                          }
                        `}
                      >
                        {type}
                      </button>
                    ))}
                  </div>
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                    Status
                  </label>
                  <div className="flex gap-2">
                    {(['all', 'completed', 'pending', 'failed'] as const).map((status) => (
                      <button
                        key={status}
                        onClick={() => setStatusFilter(status)}
                        className={`
                          flex-1 px-4 py-2 rounded-xl text-sm font-medium transition-all capitalize
                          ${statusFilter === status
                            ? 'bg-smurf-500 text-white'
                            : 'bg-white/50 dark:bg-white/5 text-gray-700 dark:text-gray-300 hover:bg-white/80 dark:hover:bg-white/10'
                          }
                        `}
                      >
                        {status}
                      </button>
                    ))}
                  </div>
                </div>
              </motion.div>
            )}
          </AnimatePresence>
        </div>

        {/* Active Filters Summary */}
        {(searchTerm || typeFilter !== 'all' || statusFilter !== 'all') && (
          <div className="mt-4 pt-4 border-t border-gray-200/20 dark:border-gray-800/20">
            <div className="flex items-center justify-between">
              <p className="text-sm text-gray-600 dark:text-gray-400">
                Showing {filteredTransactions.length} of {transactions.length} transactions
              </p>
              <button
                onClick={() => {
                  setSearchTerm('');
                  setTypeFilter('all');
                  setStatusFilter('all');
                }}
                className="text-sm text-smurf-500 hover:text-smurf-600 font-medium"
              >
                Clear Filters
              </button>
            </div>
          </div>
        )}
      </GlassCard>

      {/* Transactions List */}
      <GlassCard className="p-6">
        {filteredTransactions.length === 0 ? (
          <div className="text-center py-12">
            <div className="w-16 h-16 bg-gray-200/50 dark:bg-gray-800/50 rounded-full flex items-center justify-center mx-auto mb-4">
              <FiSearch className="w-8 h-8 text-gray-400" />
            </div>
            <p className="text-gray-600 dark:text-gray-400 mb-2">No transactions found</p>
            <p className="text-sm text-gray-500 dark:text-gray-500">
              {searchTerm || typeFilter !== 'all' || statusFilter !== 'all'
                ? 'Try adjusting your filters'
                : 'Start by making a transfer'}
            </p>
          </div>
        ) : (
          <div className="space-y-2">
            {filteredTransactions.map((transaction, index) => {
              const isReceived = transaction.receiverId === profile?.id;
              const otherUser = isReceived ? transaction.sender : transaction.receiver;
              const isExpanded = expandedId === transaction.id;
              
              return (
                <motion.div
                  key={transaction.id}
                  initial={{ opacity: 0, y: 20 }}
                  animate={{ opacity: 1, y: 0 }}
                  transition={{ delay: index * 0.05 }}
                  className="rounded-xl overflow-hidden hover:bg-white/30 dark:hover:bg-white/5 transition-colors"
                >
                  <button
                    onClick={() => setExpandedId(isExpanded ? null : transaction.id)}
                    className="w-full flex items-center justify-between p-4 text-left"
                  >
                    <div className="flex items-center gap-4 flex-1">
                      <div className={`
                        w-12 h-12 rounded-full flex items-center justify-center flex-shrink-0
                        ${isReceived ? 'bg-green-500/20' : 'bg-red-500/20'}
                      `}>
                        {isReceived ? (
                          <FiArrowDown className="w-6 h-6 text-green-600 dark:text-green-400" />
                        ) : (
                          <FiArrowUp className="w-6 h-6 text-red-600 dark:text-red-400" />
                        )}
                      </div>
                      
                      <div className="flex-1 min-w-0">
                        <p className="font-medium text-gray-900 dark:text-white truncate">
                          {isReceived ? 'Received from' : 'Sent to'} {otherUser.name}
                        </p>
                        <p className="text-sm text-gray-600 dark:text-gray-400">
                          {getRelativeTime(transaction.createdAt)}
                        </p>
                      </div>
                    </div>
                    
                    <div className="flex items-center gap-6">
                      <div className="text-right">
                        <p className={`
                          font-bold text-lg
                          ${isReceived ? 'text-green-600 dark:text-green-400' : 'text-red-600 dark:text-red-400'}
                        `}>
                          {isReceived ? '+' : '-'}{formatCurrency(transaction.amount)}
                        </p>
                        <div className="flex items-center gap-2 justify-end">
                          <span
                            className={`
                              inline-flex items-center gap-1 px-2 py-0.5 rounded-full text-xs font-medium
                              ${transaction.status === 'completed' 
                                ? 'bg-green-500/20 text-green-600 dark:text-green-400' 
                                : transaction.status === 'pending'
                                ? 'bg-yellow-500/20 text-yellow-600 dark:text-yellow-400'
                                : 'bg-red-500/20 text-red-600 dark:text-red-400'
                              }
                            `}
                          >
                            {transaction.status === 'completed' && <FiCheck className="w-3 h-3" />}
                            {transaction.status === 'failed' && <FiX className="w-3 h-3" />}
                            {transaction.status}
                          </span>
                        </div>
                      </div>
                      
                      <FiChevronDown
                        className={`w-5 h-5 text-gray-400 transition-transform ${isExpanded ? 'rotate-180' : ''}`}
                      />
                    </div>
                  </button>

                  <AnimatePresence>
                    {isExpanded && (
                      <motion.div
                        initial={{ opacity: 0, height: 0 }}
                        animate={{ opacity: 1, height: 'auto' }}
                        exit={{ opacity: 0, height: 0 }}
                        className="border-t border-gray-200/20 dark:border-gray-800/20"
                      >
                        <div className="p-4 space-y-3 bg-white/20 dark:bg-white/5">
                          <div className="grid grid-cols-2 gap-4">
                            <div>
                              <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Transaction ID</p>
                              <p className="text-sm font-mono text-gray-900 dark:text-white">
                                {transaction.id.slice(0, 12)}...
                              </p>
                            </div>
                            <div>
                              <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Date & Time</p>
                              <p className="text-sm text-gray-900 dark:text-white">
                                {formatDate(transaction.createdAt)}
                              </p>
                            </div>
                          </div>
                          
                          {transaction.description && (
                            <div>
                              <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Description</p>
                              <p className="text-sm text-gray-900 dark:text-white">
                                {transaction.description}
                              </p>
                            </div>
                          )}

                          <div>
                            <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">
                              {isReceived ? 'From' : 'To'}
                            </p>
                            <p className="text-sm text-gray-900 dark:text-white">
                              {otherUser.name} (@{otherUser.username})
                            </p>
                          </div>
                        </div>
                      </motion.div>
                    )}
                  </AnimatePresence>
                </motion.div>
              );
            })}
          </div>
        )}
      </GlassCard>
    </div>
  );
}
