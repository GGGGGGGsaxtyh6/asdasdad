'use client';

import { motion } from 'framer-motion';
import { InputHTMLAttributes, useState } from 'react';
import { FiEye, FiEyeOff } from 'react-icons/fi';

interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
  label?: string;
  error?: string;
  icon?: React.ReactNode;
}

export function Input({ label, error, icon, type, className = '', ...props }: InputProps) {
  const [showPassword, setShowPassword] = useState(false);
  const [focused, setFocused] = useState(false);

  const inputType = type === 'password' && showPassword ? 'text' : type;

  return (
    <div className="w-full">
      {label && (
        <motion.label
          className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2"
          initial={{ opacity: 0, x: -10 }}
          animate={{ opacity: 1, x: 0 }}
        >
          {label}
        </motion.label>
      )}
      
      <div className="relative">
        {icon && (
          <div className="absolute left-4 top-1/2 -translate-y-1/2 text-gray-400 dark:text-gray-500">
            {icon}
          </div>
        )}
        
        <motion.input
          type={inputType}
          className={`
            w-full px-4 py-3 ${icon ? 'pl-12' : ''} ${type === 'password' ? 'pr-12' : ''}
            bg-white/10 dark:bg-white/5 backdrop-blur-md
            border-2 ${error ? 'border-red-500' : focused ? 'border-smurf-500' : 'border-white/20 dark:border-white/10'}
            rounded-xl
            text-gray-900 dark:text-white
            placeholder-gray-500 dark:placeholder-gray-400
            transition-all duration-200
            focus:outline-none focus:border-smurf-500 focus:ring-4 focus:ring-smurf-500/20
            ${className}
          `}
          onFocus={() => setFocused(true)}
          onBlur={() => setFocused(false)}
          animate={{
            scale: focused ? 1.01 : 1,
          }}
          {...(props as any)}
        />
        
        {type === 'password' && (
          <button
            type="button"
            onClick={() => setShowPassword(!showPassword)}
            className="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600 dark:hover:text-gray-300 transition-colors"
          >
            {showPassword ? <FiEyeOff size={20} /> : <FiEye size={20} />}
          </button>
        )}
      </div>
      
      {error && (
        <motion.p
          className="mt-2 text-sm text-red-500"
          initial={{ opacity: 0, y: -5 }}
          animate={{ opacity: 1, y: 0 }}
        >
          {error}
        </motion.p>
      )}
    </div>
  );
}
