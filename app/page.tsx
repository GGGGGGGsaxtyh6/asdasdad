'use client';

import { Suspense } from 'react';
import dynamic from 'next/dynamic';
import Link from 'next/link';
import Navbar from '@/components/layout/Navbar';
import { ArrowRight, Shield, Zap, TrendingUp } from 'lucide-react';

const HeroScene = dynamic(() => import('@/components/3d/HeroScene'), {
  ssr: false,
  loading: () => <div className="w-full h-[600px] bg-dark-800 animate-pulse rounded-3xl" />,
});

export default function Home() {
  return (
    <main className="min-h-screen">
      <Navbar />
      
      {/* Hero Section */}
      <section className="relative pt-32 pb-20 px-4 overflow-hidden">
        <div className="absolute inset-0 bg-gradient-to-b from-smurf-900/20 to-transparent pointer-events-none" />
        
        <div className="max-w-7xl mx-auto">
          <div className="grid lg:grid-cols-2 gap-12 items-center">
            <div className="space-y-8 animate-slide-up">
              <div className="inline-flex items-center px-4 py-2 bg-glass-gradient rounded-full border border-platinum-700/30 backdrop-blur-xl">
                <span className="text-smurf-400 text-sm font-medium">✨ Banca Digital Premium</span>
              </div>
              
              <h1 className="text-6xl lg:text-7xl font-bold leading-tight">
                <span className="text-white">El futuro de</span>
                <br />
                <span className="text-gradient">tus finanzas</span>
              </h1>
              
              <p className="text-xl text-platinum-300 leading-relaxed max-w-lg">
                Experimenta la banca digital de próxima generación con Smurf, nuestra moneda exclusiva. 
                Transferencias instantáneas, seguridad premium y una interfaz que te encantará.
              </p>
              
              <div className="flex flex-col sm:flex-row gap-4">
                <Link
                  href="/register"
                  className="inline-flex items-center justify-center px-8 py-4 bg-gradient-to-r from-smurf-600 to-smurf-500 text-white rounded-xl font-semibold hover:from-smurf-700 hover:to-smurf-600 transition-all shadow-premium hover:shadow-premium-lg active:scale-[0.98] group"
                >
                  Comenzar ahora
                  <ArrowRight className="ml-2 group-hover:translate-x-1 transition-transform" size={20} />
                </Link>
                
                <Link
                  href="/login"
                  className="inline-flex items-center justify-center px-8 py-4 bg-glass-gradient text-platinum-100 border border-platinum-700/50 rounded-xl font-semibold hover:border-platinum-600/50 transition-all shadow-glass hover:shadow-glass-lg backdrop-blur-xl active:scale-[0.98]"
                >
                  Iniciar sesión
                </Link>
              </div>
            </div>
            
            <div className="relative">
              <Suspense fallback={<div className="w-full h-[600px] bg-dark-800 animate-pulse rounded-3xl" />}>
                <HeroScene />
              </Suspense>
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-20 px-4">
        <div className="max-w-7xl mx-auto">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-white mb-4">
              ¿Por qué Smurf Bank?
            </h2>
            <p className="text-xl text-platinum-300">
              La experiencia bancaria que siempre quisiste
            </p>
          </div>
          
          <div className="grid md:grid-cols-3 gap-8">
            <div className="group p-8 bg-glass-gradient border border-platinum-700/30 rounded-3xl backdrop-blur-xl hover:border-smurf-500/50 transition-all hover:shadow-premium-lg">
              <div className="w-14 h-14 rounded-2xl bg-gradient-to-br from-smurf-500 to-smurf-700 flex items-center justify-center mb-6 group-hover:scale-110 transition-transform shadow-lg">
                <Zap className="text-white" size={28} />
              </div>
              <h3 className="text-2xl font-bold text-white mb-4">Instantáneo</h3>
              <p className="text-platinum-300">
                Transferencias en tiempo real. Tu dinero llega al instante, sin esperas ni complicaciones.
              </p>
            </div>

            <div className="group p-8 bg-glass-gradient border border-platinum-700/30 rounded-3xl backdrop-blur-xl hover:border-gold-500/50 transition-all hover:shadow-premium-lg">
              <div className="w-14 h-14 rounded-2xl bg-gradient-to-br from-gold-500 to-gold-700 flex items-center justify-center mb-6 group-hover:scale-110 transition-transform shadow-lg">
                <Shield className="text-white" size={28} />
              </div>
              <h3 className="text-2xl font-bold text-white mb-4">Seguro</h3>
              <p className="text-platinum-300">
                Protección de nivel bancario. Tus fondos están seguros con encriptación de última generación.
              </p>
            </div>

            <div className="group p-8 bg-glass-gradient border border-platinum-700/30 rounded-3xl backdrop-blur-xl hover:border-platinum-500/50 transition-all hover:shadow-premium-lg">
              <div className="w-14 h-14 rounded-2xl bg-gradient-to-br from-platinum-500 to-platinum-700 flex items-center justify-center mb-6 group-hover:scale-110 transition-transform shadow-lg">
                <TrendingUp className="text-white" size={28} />
              </div>
              <h3 className="text-2xl font-bold text-white mb-4">Inteligente</h3>
              <p className="text-platinum-300">
                Visualiza tus finanzas como nunca antes. Gráficos 3D y análisis en tiempo real.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-20 px-4">
        <div className="max-w-4xl mx-auto">
          <div className="relative p-12 bg-premium-gradient border border-gold-600/20 rounded-3xl backdrop-blur-xl shadow-premium overflow-hidden">
            <div className="absolute inset-0 bg-gradient-to-br from-smurf-600/10 to-gold-600/10 pointer-events-none" />
            
            <div className="relative text-center space-y-6">
              <h2 className="text-4xl font-bold text-white">
                Únete a Smurf Bank hoy
              </h2>
              <p className="text-xl text-platinum-200">
                Crea tu cuenta en menos de 2 minutos y recibe 1,000 Smurf de bienvenida
              </p>
              <Link
                href="/register"
                className="inline-flex items-center justify-center px-10 py-4 bg-gradient-to-r from-smurf-600 to-smurf-500 text-white rounded-xl font-semibold hover:from-smurf-700 hover:to-smurf-600 transition-all shadow-premium hover:shadow-premium-lg active:scale-[0.98] text-lg group"
              >
                Crear cuenta gratis
                <ArrowRight className="ml-2 group-hover:translate-x-1 transition-transform" size={22} />
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="py-12 px-4 border-t border-platinum-800/30">
        <div className="max-w-7xl mx-auto text-center">
          <p className="text-platinum-400">
            © 2025 Smurf Bank. Banco digital premium.
          </p>
        </div>
      </footer>
    </main>
  );
}
