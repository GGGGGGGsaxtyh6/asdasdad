import { Canvas } from '@react-three/fiber';
import { OrbitControls } from '@react-three/drei';
import { motion } from 'framer-motion';
import { useNavigate } from 'react-router-dom';
import { useStore } from '../store/useStore';
import { EffectComposer, Bloom } from '@react-three/postprocessing';
import MoneyFluid from '../components/3d/MoneyFluid';
import HolographicChart from '../components/3d/HolographicChart';

export default function DashboardPage() {
  const navigate = useNavigate();
  const user = useStore((state) => state.user);
  const transactions = useStore((state) => state.transactions);
  const viewMode = useStore((state) => state.viewMode);
  const setViewMode = useStore((state) => state.setViewMode);
  const logout = useStore((state) => state.logout);

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <div className="relative w-full h-screen overflow-hidden bg-void-900">
      {/* Top Navigation */}
      <motion.nav
        initial={{ y: -100 }}
        animate={{ y: 0 }}
        className="absolute top-0 left-0 right-0 z-20 glass p-4"
      >
        <div className="max-w-7xl mx-auto flex items-center justify-between">
          <div className="flex items-center gap-4">
            <h1 className="text-2xl font-bold holographic-text">QUANTUM VAULT</h1>
            <div className="text-purple-300">@{user?.username}</div>
          </div>
          
          <div className="flex items-center gap-4">
            <button
              onClick={() => navigate('/game')}
              className="px-6 py-2 bg-gradient-to-r from-pink-600 to-purple-600 rounded-full font-semibold hover:scale-105 transition-transform"
            >
              🎮 Play & Earn
            </button>
            <button
              onClick={handleLogout}
              className="px-6 py-2 glass rounded-full hover:bg-white/10 transition-all"
            >
              Logout
            </button>
          </div>
        </div>
      </motion.nav>

      {/* 3D Visualization Area */}
      <div className="absolute inset-0 pt-20">
        <Canvas camera={{ position: [0, 2, 8], fov: 60 }}>
          <ambientLight intensity={0.5} />
          <pointLight position={[10, 10, 10]} intensity={1} color="#8B5CF6" />
          <pointLight position={[-10, 5, -5]} intensity={0.8} color="#3B82F6" />
          
          {viewMode === '3d' && <MoneyFluid amount={user?.balance || 0} />}
          {viewMode === 'particles' && <HolographicChart data={transactions} />}
          
          <EffectComposer>
            <Bloom luminanceThreshold={0.3} intensity={1.5} />
          </EffectComposer>
          
          <OrbitControls enableZoom={true} maxDistance={15} minDistance={3} />
        </Canvas>
      </div>

      {/* Control Panel - Bottom */}
      <motion.div
        initial={{ y: 100 }}
        animate={{ y: 0 }}
        className="absolute bottom-0 left-0 right-0 p-6 bg-gradient-to-t from-void-900 via-void-900/95 to-transparent z-10"
      >
        <div className="max-w-7xl mx-auto">
          {/* Balance Display */}
          <div className="glass p-8 rounded-3xl mb-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-purple-300 text-sm mb-1">Total Balance</p>
                <h2 className="text-6xl font-bold holographic-text tabular-nums">
                  {user?.balance.toLocaleString()}
                </h2>
                <p className="text-2xl text-gold-500 font-semibold mt-2">SMURF</p>
              </div>
              
              <motion.button
                whileHover={{ scale: 1.05, rotate: 360 }}
                transition={{ duration: 0.5 }}
                onClick={() => {
                  const modes: ('3d' | 'particles')[] = ['3d', 'particles'];
                  const currentIndex = modes.indexOf(viewMode as any);
                  const nextMode = modes[(currentIndex + 1) % modes.length];
                  setViewMode(nextMode);
                }}
                className="w-20 h-20 rounded-full bg-gradient-to-br from-purple-600 to-blue-600 flex items-center justify-center text-4xl shadow-2xl"
              >
                🔄
              </motion.button>
            </div>
          </div>

          {/* Quick Actions */}
          <div className="grid grid-cols-4 gap-4">
            <QuickActionButton
              icon="💸"
              label="Transfer"
              onClick={() => navigate('/transfer')}
            />
            <QuickActionButton
              icon="🏊"
              label="Dive In Vault"
              onClick={() => navigate('/vault')}
            />
            <QuickActionButton
              icon="📊"
              label="Analytics"
              gradient="from-cyan-600 to-blue-600"
            />
            <QuickActionButton
              icon="⚙️"
              label="Settings"
              gradient="from-gray-600 to-gray-700"
            />
          </div>
        </div>
      </motion.div>
    </div>
  );
}

function QuickActionButton({ 
  icon, 
  label, 
  onClick,
  gradient = "from-purple-600 to-pink-600" 
}: { 
  icon: string; 
  label: string; 
  onClick?: () => void;
  gradient?: string;
}) {
  return (
    <motion.button
      whileHover={{ scale: 1.05, y: -5 }}
      whileTap={{ scale: 0.95 }}
      onClick={onClick}
      className={`quantum-btn p-6 bg-gradient-to-br ${gradient} rounded-2xl shadow-xl hover:shadow-2xl transition-all`}
    >
      <div className="text-4xl mb-2">{icon}</div>
      <div className="font-semibold">{label}</div>
    </motion.button>
  );
}
