import { Canvas } from '@react-three/fiber';
import { OrbitControls } from '@react-three/drei';
import { Physics } from '@react-three/cannon';
import { motion, AnimatePresence } from 'framer-motion';
import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { useStore } from '../store/useStore';
import CoinCatcherGame from '../components/game/CoinCatcherGame';

export default function GamePage() {
  const navigate = useNavigate();
  const gameState = useStore((state) => state.gameState);
  const startGame = useStore((state) => state.startGame);
  const endGame = useStore((state) => state.endGame);
  const [timeLeft, setTimeLeft] = useState(30);
  const [collected, setCollected] = useState(0);

  useEffect(() => {
    if (gameState.isPlaying && timeLeft > 0) {
      const timer = setTimeout(() => setTimeLeft(timeLeft - 1), 1000);
      return () => clearTimeout(timer);
    } else if (timeLeft === 0 && gameState.isPlaying) {
      endGame(collected);
    }
  }, [gameState.isPlaying, timeLeft, collected, endGame]);

  const handleStart = () => {
    startGame();
    setTimeLeft(30);
    setCollected(0);
  };

  const handleCoinCollect = () => {
    setCollected(collected + 1);
  };

  return (
    <div className="relative w-full h-screen overflow-hidden bg-void-900">
      {/* Game Canvas */}
      <Canvas camera={{ position: [0, 5, 10], fov: 60 }}>
        <ambientLight intensity={0.5} />
        <pointLight position={[10, 10, 10]} intensity={1} color="#F59E0B" />
        <spotLight
          position={[0, 15, 0]}
          angle={0.5}
          penumbra={1}
          intensity={2}
          color="#8B5CF6"
        />

        {gameState.isPlaying && (
          <Physics gravity={[0, -9.8, 0]}>
            <CoinCatcherGame onCollect={handleCoinCollect} />
          </Physics>
        )}

        <OrbitControls enabled={!gameState.isPlaying} />
      </Canvas>

      {/* HUD */}
      <AnimatePresence>
        {gameState.isPlaying && (
          <motion.div
            initial={{ opacity: 0, y: -50 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -50 }}
            className="absolute top-8 left-1/2 transform -translate-x-1/2 flex gap-8"
          >
            <div className="glass px-8 py-4 rounded-2xl">
              <div className="text-purple-300 text-sm">Time Left</div>
              <div className="text-4xl font-bold holographic-text">{timeLeft}s</div>
            </div>
            <div className="glass px-8 py-4 rounded-2xl">
              <div className="text-purple-300 text-sm">Coins</div>
              <div className="text-4xl font-bold text-gold-500">{collected}</div>
            </div>
          </motion.div>
        )}
      </AnimatePresence>

      {/* Start Screen */}
      {!gameState.isPlaying && gameState.coinsCollected === 0 && (
        <motion.div
          initial={{ opacity: 0, scale: 0.8 }}
          animate={{ opacity: 1, scale: 1 }}
          className="absolute inset-0 flex items-center justify-center"
        >
          <div className="glass p-12 rounded-3xl text-center max-w-lg">
            <h1 className="text-5xl font-bold holographic-text mb-4">
              Coin Catcher
            </h1>
            <p className="text-purple-300 mb-6 text-lg">
              Catch falling Smurf coins in 30 seconds!
              <br />
              Each coin = 10 Smurf added to your balance
            </p>
            <p className="text-sm text-gray-400 mb-8">
              Use arrow keys or WASD to move the basket
            </p>
            <motion.button
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
              onClick={handleStart}
              className="quantum-btn px-12 py-4 bg-gradient-to-r from-purple-600 to-pink-600 rounded-full text-white text-xl font-bold shadow-2xl mb-4"
            >
              🎮 Start Game
            </motion.button>
            <button
              onClick={() => navigate('/dashboard')}
              className="block w-full px-6 py-3 glass rounded-full hover:bg-white/10 transition-all"
            >
              ← Back to Dashboard
            </button>
          </div>
        </motion.div>
      )}

      {/* Game Over Screen */}
      {!gameState.isPlaying && gameState.coinsCollected > 0 && (
        <motion.div
          initial={{ opacity: 0, scale: 0.8 }}
          animate={{ opacity: 1, scale: 1 }}
          className="absolute inset-0 flex items-center justify-center"
        >
          <div className="glass p-12 rounded-3xl text-center max-w-lg">
            <h1 className="text-5xl font-bold holographic-text mb-4">
              Game Over!
            </h1>
            <p className="text-purple-300 mb-6 text-xl">
              You collected {gameState.coinsCollected} coins!
            </p>
            <div className="mb-8 p-6 bg-gradient-to-r from-purple-900/50 to-pink-900/50 rounded-2xl">
              <div className="text-sm text-purple-300 mb-2">Reward Earned</div>
              <div className="text-5xl font-bold text-gold-500">
                +{gameState.coinsCollected * 10} Smurf
              </div>
            </div>
            <div className="flex gap-4">
              <motion.button
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
                onClick={handleStart}
                className="flex-1 quantum-btn px-8 py-4 bg-gradient-to-r from-purple-600 to-pink-600 rounded-full text-white font-bold"
              >
                Play Again
              </motion.button>
              <motion.button
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
                onClick={() => navigate('/dashboard')}
                className="flex-1 px-8 py-4 glass rounded-full hover:bg-white/10 transition-all"
              >
                Dashboard
              </motion.button>
            </div>
          </div>
        </motion.div>
      )}
    </div>
  );
}
