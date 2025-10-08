import { Canvas } from '@react-three/fiber';
import { PointerLockControls, Sky } from '@react-three/drei';
import { Physics } from '@react-three/cannon';
import { motion } from 'framer-motion';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import VaultInterior from '../components/3d/VaultInterior';
import FloatingCoins from '../components/3d/FloatingCoins';

export default function VaultPage() {
  const navigate = useNavigate();
  const [locked, setLocked] = useState(false);

  return (
    <div className="relative w-full h-screen overflow-hidden bg-void-900">
      {/* Instructions */}
      {!locked && (
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 glass p-8 rounded-3xl text-center z-50"
        >
          <h2 className="text-4xl font-bold holographic-text mb-4">Welcome to Your Vault</h2>
          <p className="text-purple-300 mb-6">Click anywhere to enter first-person mode</p>
          <p className="text-sm text-gray-400 mb-4">
            Use WASD to move • Mouse to look around • ESC to exit
          </p>
          <button
            onClick={() => navigate('/dashboard')}
            className="px-6 py-3 glass rounded-full hover:bg-white/10 transition-all"
          >
            ← Back to Dashboard
          </button>
        </motion.div>
      )}

      {/* 3D First Person Vault Experience */}
      <Canvas camera={{ position: [0, 1.6, 5], fov: 75 }}>
        <Sky sunPosition={[0, 1, 0]} />
        <ambientLight intensity={0.3} />
        <pointLight position={[0, 5, 0]} intensity={1} color="#F59E0B" />
        <spotLight
          position={[5, 10, 5]}
          angle={0.3}
          penumbra={1}
          intensity={2}
          color="#8B5CF6"
          castShadow
        />

        <Physics gravity={[0, -9.8, 0]}>
          <VaultInterior />
          <FloatingCoins count={50} />
        </Physics>

        <PointerLockControls 
          onLock={() => setLocked(true)}
          onUnlock={() => setLocked(false)}
        />
      </Canvas>

      {/* Controls Info */}
      {locked && (
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          className="absolute bottom-8 left-8 glass p-4 rounded-2xl"
        >
          <div className="text-sm text-purple-300 space-y-1">
            <div>🎮 WASD - Move</div>
            <div>🖱️ Mouse - Look</div>
            <div>⎋ ESC - Exit</div>
          </div>
        </motion.div>
      )}
    </div>
  );
}
