import { Canvas } from '@react-three/fiber';
import { OrbitControls } from '@react-three/drei';
import { motion } from 'framer-motion';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useStore } from '../store/useStore';
import TransferTunnel from '../components/3d/TransferTunnel';

export default function TransferPage() {
  const navigate = useNavigate();
  const user = useStore((state) => state.user);
  const addTransaction = useStore((state) => state.addTransaction);
  const updateBalance = useStore((state) => state.updateBalance);
  
  const [recipient, setRecipient] = useState('');
  const [amount, setAmount] = useState('');
  const [isTransferring, setIsTransferring] = useState(false);
  const [success, setSuccess] = useState(false);

  const handleTransfer = async (e: React.FormEvent) => {
    e.preventDefault();
    
    const transferAmount = parseFloat(amount);
    if (transferAmount <= 0 || transferAmount > (user?.balance || 0)) {
      return;
    }

    setIsTransferring(true);

    // Simulate transfer with tunnel animation
    setTimeout(() => {
      updateBalance(-transferAmount);
      addTransaction({
        type: 'send',
        amount: transferAmount,
        to: recipient,
        status: 'completed',
      });

      setIsTransferring(false);
      setSuccess(true);

      setTimeout(() => {
        setSuccess(false);
        setRecipient('');
        setAmount('');
      }, 3000);
    }, 3000);
  };

  return (
    <div className="relative w-full h-screen overflow-hidden bg-void-900">
      {/* 3D Transfer Tunnel */}
      <div className="absolute inset-0">
        <Canvas camera={{ position: [0, 0, 5], fov: 75 }}>
          <ambientLight intensity={0.3} />
          <pointLight position={[0, 0, 10]} intensity={2} color="#8B5CF6" />
          <TransferTunnel active={isTransferring} />
          <OrbitControls enableZoom={false} />
        </Canvas>
      </div>

      {/* Transfer Form */}
      <div className="absolute inset-0 flex items-center justify-center p-8">
        {success ? (
          <motion.div
            initial={{ opacity: 0, scale: 0.8 }}
            animate={{ opacity: 1, scale: 1 }}
            className="glass p-12 rounded-3xl text-center max-w-md"
          >
            <div className="text-7xl mb-6">✨</div>
            <h2 className="text-4xl font-bold holographic-text mb-4">Transfer Complete!</h2>
            <p className="text-purple-300 text-lg mb-6">
              {amount} Smurf sent successfully to {recipient}
            </p>
            <button
              onClick={() => navigate('/dashboard')}
              className="px-8 py-3 glass rounded-full hover:bg-white/10 transition-all"
            >
              Return to Dashboard
            </button>
          </motion.div>
        ) : (
          <motion.form
            initial={{ opacity: 0, y: 50 }}
            animate={{ opacity: 1, y: 0 }}
            onSubmit={handleTransfer}
            className="glass p-8 rounded-3xl w-full max-w-md"
          >
            <h2 className="text-3xl font-bold holographic-text mb-2 text-center">
              Quantum Transfer
            </h2>
            <p className="text-center text-purple-300 mb-8">
              Send Smurf through the quantum tunnel
            </p>

            <div className="space-y-6">
              <div>
                <label className="block text-sm font-medium text-purple-300 mb-2">
                  Recipient
                </label>
                <input
                  type="text"
                  value={recipient}
                  onChange={(e) => setRecipient(e.target.value)}
                  className="w-full px-4 py-3 bg-void-800/50 border border-purple-500/30 rounded-xl text-white focus:outline-none focus:border-purple-500 focus:ring-2 focus:ring-purple-500/20 transition-all"
                  placeholder="Enter recipient address"
                  required
                  disabled={isTransferring}
                />
              </div>

              <div>
                <label className="block text-sm font-medium text-purple-300 mb-2">
                  Amount (Smurf)
                </label>
                <input
                  type="number"
                  value={amount}
                  onChange={(e) => setAmount(e.target.value)}
                  className="w-full px-4 py-3 bg-void-800/50 border border-purple-500/30 rounded-xl text-white focus:outline-none focus:border-purple-500 focus:ring-2 focus:ring-purple-500/20 transition-all"
                  placeholder="0.00"
                  step="0.01"
                  min="0"
                  max={user?.balance}
                  required
                  disabled={isTransferring}
                />
                <p className="text-sm text-purple-400 mt-2">
                  Available: {user?.balance.toLocaleString()} Smurf
                </p>
              </div>

              <motion.button
                whileHover={{ scale: isTransferring ? 1 : 1.02 }}
                whileTap={{ scale: isTransferring ? 1 : 0.98 }}
                type="submit"
                disabled={isTransferring}
                className={`w-full quantum-btn py-4 bg-gradient-to-r from-purple-600 to-blue-600 rounded-xl text-white font-bold text-lg shadow-2xl transition-all ${
                  isTransferring ? 'opacity-50 cursor-not-allowed' : 'hover:shadow-purple-500/50'
                }`}
              >
                {isTransferring ? (
                  <span className="flex items-center justify-center gap-3">
                    <div className="w-6 h-6 border-2 border-white border-t-transparent rounded-full animate-spin" />
                    Sending through quantum tunnel...
                  </span>
                ) : (
                  '🚀 Send Transfer'
                )}
              </motion.button>

              <button
                type="button"
                onClick={() => navigate('/dashboard')}
                className="w-full px-6 py-3 glass rounded-full hover:bg-white/10 transition-all"
                disabled={isTransferring}
              >
                Cancel
              </button>
            </div>
          </motion.form>
        )}
      </div>
    </div>
  );
}
