import { Canvas } from '@react-three/fiber';
import { OrbitControls, Sphere, MeshDistortMaterial, Float } from '@react-three/drei';
import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import { EffectComposer, Bloom } from '@react-three/postprocessing';
import QuantumParticles from '../components/3d/QuantumParticles';

function HeroSphere() {
  return (
    <Float speed={2} rotationIntensity={1} floatIntensity={2}>
      <Sphere args={[1, 64, 64]} scale={2}>
        <MeshDistortMaterial
          color="#8B5CF6"
          attach="material"
          distort={0.6}
          speed={2}
          roughness={0}
          metalness={0.8}
        />
      </Sphere>
    </Float>
  );
}


export default function LandingPage() {
  const navigate = useNavigate();

  return (
    <div className="relative w-full h-screen overflow-hidden">
      {/* 3D Background */}
      <Canvas camera={{ position: [0, 0, 5], fov: 75 }}>
        <ambientLight intensity={0.5} />
        <pointLight position={[10, 10, 10]} intensity={1} color="#8B5CF6" />
        <pointLight position={[-10, -10, -10]} intensity={0.5} color="#3B82F6" />
        
        <HeroSphere />
        <QuantumParticles count={100} />
        
        <EffectComposer>
          <Bloom luminanceThreshold={0.3} luminanceSmoothing={0.9} intensity={1.5} />
        </EffectComposer>
        
        <OrbitControls enableZoom={false} autoRotate autoRotateSpeed={0.5} />
      </Canvas>

      {/* Content Overlay */}
      <div className="absolute inset-0 flex flex-col items-center justify-center pointer-events-none">
        <motion.div
          initial={{ opacity: 0, y: 50 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 1 }}
          className="text-center z-10"
        >
          <h1 className="text-8xl font-bold mb-6 holographic-text neon">
            SMURF
          </h1>
          <h2 className="text-5xl font-bold mb-4 text-white">
            Quantum Vault
          </h2>
          <p className="text-xl text-purple-300 mb-12 max-w-2xl mx-auto px-4">
            Experience banking in the 4th dimension. Where physics meets finance.
            <br />
            Dive into your vault, catch coins in zero gravity, and watch your money flow like water.
          </p>
          
          <div className="flex gap-6 justify-center pointer-events-auto">
            <motion.button
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
              onClick={() => navigate('/auth')}
              className="quantum-btn px-12 py-4 bg-gradient-to-r from-purple-600 to-blue-600 rounded-full text-white text-lg font-bold shadow-2xl hover:shadow-purple-500/50 transition-all relative z-10"
            >
              Enter the Vault
            </motion.button>
            
            <motion.button
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
              onClick={() => {
                document.getElementById('features')?.scrollIntoView({ behavior: 'smooth' });
              }}
              className="px-12 py-4 glass rounded-full text-white text-lg font-semibold hover:bg-white/10 transition-all relative z-10"
            >
              Explore Features
            </motion.button>
          </div>
        </motion.div>
      </div>

      {/* Features Section */}
      <motion.div
        id="features"
        initial={{ opacity: 0 }}
        whileInView={{ opacity: 1 }}
        viewport={{ once: true }}
        className="absolute bottom-0 left-0 right-0 p-12 bg-gradient-to-t from-void-900 to-transparent"
      >
        <div className="max-w-6xl mx-auto grid grid-cols-1 md:grid-cols-3 gap-8">
          <FeatureCard
            title="Quantum Physics"
            description="Real-time particle simulation with gravity, collisions, and fluid dynamics"
            icon="⚛️"
          />
          <FeatureCard
            title="3D Money Flow"
            description="Watch your Smurf coins flow like liquid gold through space and time"
            icon="💰"
          />
          <FeatureCard
            title="Vault Dive Mode"
            description="Enter your vault in first-person and swim through your riches"
            icon="🏊"
          />
        </div>
      </motion.div>
    </div>
  );
}

function FeatureCard({ title, description, icon }: { title: string; description: string; icon: string }) {
  return (
    <motion.div
      whileHover={{ y: -10, scale: 1.02 }}
      className="glass p-6 rounded-2xl backdrop-blur-xl"
    >
      <div className="text-5xl mb-4">{icon}</div>
      <h3 className="text-2xl font-bold mb-2 text-purple-300">{title}</h3>
      <p className="text-gray-300">{description}</p>
    </motion.div>
  );
}
