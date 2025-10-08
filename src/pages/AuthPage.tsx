import { useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { useNavigate } from 'react-router-dom';
import { useStore } from '../store/useStore';
import { Canvas } from '@react-three/fiber';
import { OrbitControls } from '@react-three/drei';
import BiometricScanner from '../components/3d/BiometricScanner';

export default function AuthPage() {
  const [isLogin, setIsLogin] = useState(true);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [username, setUsername] = useState('');
  const [scanning, setScanning] = useState(false);
  
  const navigate = useNavigate();
  const login = useStore((state) => state.login);
  const register = useStore((state) => state.register);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setScanning(true);
    
    // Biometric scanning animation
    setTimeout(async () => {
      if (isLogin) {
        await login(email, password);
      } else {
        await register(username, email, password);
      }
      navigate('/dashboard');
    }, 2000);
  };

  return (
    <div className="relative w-full h-screen overflow-hidden bg-void-900">
      {/* 3D Background - Biometric Scanner */}
      <div className="absolute inset-0">
        <Canvas camera={{ position: [0, 0, 5] }}>
          <ambientLight intensity={0.5} />
          <pointLight position={[10, 10, 10]} color="#8B5CF6" intensity={1} />
          <BiometricScanner scanning={scanning} />
          <OrbitControls enableZoom={false} autoRotate={!scanning} />
        </Canvas>
      </div>

      {/* Auth Form */}
      <div className="absolute inset-0 flex items-center justify-center">
        <motion.div
          initial={{ opacity: 0, scale: 0.8 }}
          animate={{ opacity: 1, scale: 1 }}
          className="glass p-12 rounded-3xl w-full max-w-md relative z-10"
        >
          <h2 className="text-4xl font-bold mb-2 holographic-text text-center">
            {isLogin ? 'Quantum Login' : 'Create Vault'}
          </h2>
          <p className="text-center text-purple-300 mb-8">
            {isLogin ? 'Access your quantum vault' : 'Initialize your quantum account'}
          </p>

          <AnimatePresence mode="wait">
            {scanning ? (
              <motion.div
                key="scanning"
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                exit={{ opacity: 0 }}
                className="text-center py-12"
              >
                <div className="text-6xl mb-4">👁️</div>
                <p className="text-2xl font-bold text-purple-400 mb-2">Scanning Biometrics...</p>
                <div className="flex gap-2 justify-center">
                  <div className="w-3 h-3 bg-purple-500 rounded-full animate-pulse" />
                  <div className="w-3 h-3 bg-blue-500 rounded-full animate-pulse delay-100" />
                  <div className="w-3 h-3 bg-cyan-500 rounded-full animate-pulse delay-200" />
                </div>
              </motion.div>
            ) : (
              <motion.form
                key="form"
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                exit={{ opacity: 0 }}
                onSubmit={handleSubmit}
                className="space-y-6"
              >
                {!isLogin && (
                  <div>
                    <label className="block text-sm font-medium text-purple-300 mb-2">
                      Username
                    </label>
                    <input
                      type="text"
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                      className="w-full px-4 py-3 bg-void-800/50 border border-purple-500/30 rounded-xl text-white focus:outline-none focus:border-purple-500 focus:ring-2 focus:ring-purple-500/20 transition-all"
                      required
                      placeholder="Enter your quantum ID"
                    />
                  </div>
                )}

                <div>
                  <label className="block text-sm font-medium text-purple-300 mb-2">
                    Email
                  </label>
                  <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="w-full px-4 py-3 bg-void-800/50 border border-purple-500/30 rounded-xl text-white focus:outline-none focus:border-purple-500 focus:ring-2 focus:ring-purple-500/20 transition-all"
                    required
                    placeholder="your@email.com"
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-purple-300 mb-2">
                    Password
                  </label>
                  <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="w-full px-4 py-3 bg-void-800/50 border border-purple-500/30 rounded-xl text-white focus:outline-none focus:border-purple-500 focus:ring-2 focus:ring-purple-500/20 transition-all"
                    required
                    placeholder="••••••••"
                  />
                </div>

                <motion.button
                  whileHover={{ scale: 1.02 }}
                  whileTap={{ scale: 0.98 }}
                  type="submit"
                  className="w-full quantum-btn py-4 bg-gradient-to-r from-purple-600 to-blue-600 rounded-xl text-white font-bold text-lg shadow-2xl hover:shadow-purple-500/50 transition-all"
                >
                  {isLogin ? '🔓 Unlock Vault' : '✨ Create Vault'}
                </motion.button>

                <button
                  type="button"
                  onClick={() => setIsLogin(!isLogin)}
                  className="w-full text-center text-purple-400 hover:text-purple-300 transition-colors"
                >
                  {isLogin ? "Don't have a vault? Create one" : 'Already have a vault? Login'}
                </button>
              </motion.form>
            )}
          </AnimatePresence>
        </motion.div>
      </div>
    </div>
  );
}
