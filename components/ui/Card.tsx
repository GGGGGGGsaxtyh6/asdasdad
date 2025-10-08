'use client';

import { HTMLAttributes, forwardRef } from 'react';
import { cn } from '@/lib/utils';

interface CardProps extends HTMLAttributes<HTMLDivElement> {
  variant?: 'default' | 'glass' | 'premium';
  hover?: boolean;
}

const Card = forwardRef<HTMLDivElement, CardProps>(
  ({ className, variant = 'default', hover = false, children, ...props }, ref) => {
    const variants = {
      default: 'bg-dark-800 border border-platinum-800/50',
      glass: 'bg-glass-gradient border border-platinum-700/30 backdrop-blur-xl shadow-glass',
      premium: 'bg-premium-gradient border border-gold-600/20 shadow-premium backdrop-blur-xl',
    };

    const hoverStyles = hover ? 'transition-all duration-300 hover:scale-[1.02] hover:shadow-premium-lg cursor-pointer' : '';

    return (
      <div
        ref={ref}
        className={cn('rounded-2xl p-6', variants[variant], hoverStyles, className)}
        {...props}
      >
        {children}
      </div>
    );
  }
);

Card.displayName = 'Card';

export default Card;
