'use client';

import { motion, AnimatePresence } from 'framer-motion';
import { FiCheckCircle, FiXCircle, FiAlertCircle, FiInfo } from 'react-icons/fi';
import { useEffect } from 'react';

interface ToastProps {
  message: string;
  type?: 'success' | 'error' | 'warning' | 'info';
  isVisible: boolean;
  onClose: () => void;
  duration?: number;
}

export function Toast({ message, type = 'info', isVisible, onClose, duration = 3000 }: ToastProps) {
  useEffect(() => {
    if (isVisible && duration > 0) {
      const timer = setTimeout(onClose, duration);
      return () => clearTimeout(timer);
    }
  }, [isVisible, duration, onClose]);

  const icons = {
    success: <FiCheckCircle className="text-green-500" size={24} />,
    error: <FiXCircle className="text-red-500" size={24} />,
    warning: <FiAlertCircle className="text-yellow-500" size={24} />,
    info: <FiInfo className="text-blue-500" size={24} />,
  };

  const colors = {
    success: 'border-green-500/50 bg-green-500/10',
    error: 'border-red-500/50 bg-red-500/10',
    warning: 'border-yellow-500/50 bg-yellow-500/10',
    info: 'border-blue-500/50 bg-blue-500/10',
  };

  return (
    <AnimatePresence>
      {isVisible && (
        <motion.div
          className="fixed top-4 right-4 z-[100]"
          initial={{ opacity: 0, y: -50, x: 100 }}
          animate={{ opacity: 1, y: 0, x: 0 }}
          exit={{ opacity: 0, x: 100 }}
          transition={{ type: 'spring', damping: 25 }}
        >
          <div className={`
            flex items-center gap-3 px-6 py-4 rounded-2xl
            backdrop-blur-xl border-2
            ${colors[type]}
            shadow-xl
          `}>
            {icons[type]}
            <p className="text-sm font-medium text-gray-900 dark:text-white">
              {message}
            </p>
          </div>
        </motion.div>
      )}
    </AnimatePresence>
  );
}
