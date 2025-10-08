'use client';

import { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import { 
  FiBell, 
  FiCheck, 
  FiCheckCircle, 
  FiAlertCircle, 
  FiInfo,
  FiTrash2,
} from 'react-icons/fi';
import { GlassCard } from '@/components/ui/GlassCard';
import { Button } from '@/components/ui/Button';
import { getRelativeTime } from '@/lib/utils';
import { useNotificationStore } from '@/lib/store';

export default function NotificationsPage() {
  const [notifications, setNotifications] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);
  const [filter, setFilter] = useState<'all' | 'unread'>('all');
  const { setUnreadCount } = useNotificationStore();

  const fetchNotifications = async () => {
    try {
      const res = await fetch(`/api/notifications?unreadOnly=${filter === 'unread'}`);
      if (res.ok) {
        const data = await res.json();
        setNotifications(data.notifications);
        setUnreadCount(data.unreadCount);
      }
    } catch (error) {
      console.error('Error fetching notifications:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchNotifications();
  }, [filter]);

  const markAsRead = async (notificationIds: string[]) => {
    try {
      await fetch('/api/notifications', {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ notificationIds, read: true }),
      });
      fetchNotifications();
    } catch (error) {
      console.error('Error marking as read:', error);
    }
  };

  const markAllAsRead = async () => {
    const unreadIds = notifications.filter(n => !n.read).map(n => n.id);
    if (unreadIds.length > 0) {
      await markAsRead(unreadIds);
    }
  };

  const getNotificationIcon = (type: string) => {
    switch (type) {
      case 'transaction':
        return <FiCheckCircle className="w-6 h-6 text-green-500" />;
      case 'security':
        return <FiAlertCircle className="w-6 h-6 text-red-500" />;
      default:
        return <FiInfo className="w-6 h-6 text-blue-500" />;
    }
  };

  const filteredNotifications = filter === 'all' 
    ? notifications 
    : notifications.filter(n => !n.read);

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
    <div className="max-w-4xl mx-auto space-y-6">
      {/* Header */}
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        className="flex items-center justify-between"
      >
        <div>
          <h1 className="text-4xl font-bold text-gray-900 dark:text-white mb-2">
            Notifications
          </h1>
          <p className="text-gray-600 dark:text-gray-400">
            Stay updated with your account activity
          </p>
        </div>
      </motion.div>

      {/* Filter and Actions */}
      <GlassCard className="p-6">
        <div className="flex items-center justify-between">
          <div className="flex gap-2">
            <button
              onClick={() => setFilter('all')}
              className={`
                px-4 py-2 rounded-xl text-sm font-medium transition-all
                ${filter === 'all'
                  ? 'bg-smurf-500 text-white'
                  : 'bg-white/50 dark:bg-white/5 text-gray-700 dark:text-gray-300 hover:bg-white/80 dark:hover:bg-white/10'
                }
              `}
            >
              All
            </button>
            <button
              onClick={() => setFilter('unread')}
              className={`
                px-4 py-2 rounded-xl text-sm font-medium transition-all flex items-center gap-2
                ${filter === 'unread'
                  ? 'bg-smurf-500 text-white'
                  : 'bg-white/50 dark:bg-white/5 text-gray-700 dark:text-gray-300 hover:bg-white/80 dark:hover:bg-white/10'
                }
              `}
            >
              Unread
              {notifications.filter(n => !n.read).length > 0 && (
                <span className="w-5 h-5 bg-red-500 rounded-full text-white text-xs flex items-center justify-center font-bold">
                  {notifications.filter(n => !n.read).length}
                </span>
              )}
            </button>
          </div>

          {notifications.some(n => !n.read) && (
            <Button
              variant="ghost"
              size="sm"
              onClick={markAllAsRead}
              className="flex items-center gap-2"
            >
              <FiCheck />
              Mark all as read
            </Button>
          )}
        </div>
      </GlassCard>

      {/* Notifications List */}
      <GlassCard className="p-6">
        {filteredNotifications.length === 0 ? (
          <div className="text-center py-12">
            <div className="w-16 h-16 bg-gray-200/50 dark:bg-gray-800/50 rounded-full flex items-center justify-center mx-auto mb-4">
              <FiBell className="w-8 h-8 text-gray-400" />
            </div>
            <p className="text-gray-600 dark:text-gray-400 mb-2">
              {filter === 'unread' ? 'No unread notifications' : 'No notifications yet'}
            </p>
            <p className="text-sm text-gray-500 dark:text-gray-500">
              {filter === 'unread' ? "You're all caught up!" : "We'll notify you when something happens"}
            </p>
          </div>
        ) : (
          <div className="space-y-2">
            {filteredNotifications.map((notification, index) => (
              <motion.div
                key={notification.id}
                initial={{ opacity: 0, x: -20 }}
                animate={{ opacity: 1, x: 0 }}
                transition={{ delay: index * 0.05 }}
                className={`
                  flex items-start gap-4 p-4 rounded-xl transition-all
                  ${notification.read 
                    ? 'bg-white/20 dark:bg-white/5' 
                    : 'bg-smurf-500/10 border-2 border-smurf-500/20'
                  }
                  hover:bg-white/30 dark:hover:bg-white/10
                `}
              >
                <div className="flex-shrink-0 mt-1">
                  {getNotificationIcon(notification.type)}
                </div>

                <div className="flex-1 min-w-0">
                  <div className="flex items-start justify-between gap-4 mb-1">
                    <h3 className="font-medium text-gray-900 dark:text-white">
                      {notification.title}
                    </h3>
                    <span className="text-xs text-gray-600 dark:text-gray-400 flex-shrink-0">
                      {getRelativeTime(notification.createdAt)}
                    </span>
                  </div>
                  <p className="text-sm text-gray-600 dark:text-gray-400">
                    {notification.message}
                  </p>
                </div>

                {!notification.read && (
                  <button
                    onClick={() => markAsRead([notification.id])}
                    className="flex-shrink-0 p-2 rounded-lg hover:bg-white/50 dark:hover:bg-white/5 transition-colors text-gray-600 dark:text-gray-400"
                    title="Mark as read"
                  >
                    <FiCheck className="w-4 h-4" />
                  </button>
                )}
              </motion.div>
            ))}
          </div>
        )}
      </GlassCard>
    </div>
  );
}
