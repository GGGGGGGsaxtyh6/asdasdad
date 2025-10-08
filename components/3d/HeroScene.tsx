'use client';

import { Suspense } from 'react';
import { Canvas } from '@react-three/fiber';
import { OrbitControls, Environment, PerspectiveCamera } from '@react-three/drei';
import EuroCoin from './EuroCoin';
import EuroBill from './EuroBill';

export default function HeroScene() {
  return (
    <div className="w-full h-[600px] relative">
      <Canvas>
        <Suspense fallback={null}>
          <PerspectiveCamera makeDefault position={[0, 0, 10]} />
          <ambientLight intensity={0.5} />
          <pointLight position={[10, 10, 10]} intensity={1} />
          <pointLight position={[-10, -10, -10]} intensity={0.5} color="#D4A574" />
          
          {/* Esfera central Smurf */}
          <mesh position={[0, 0, 0]}>
            <sphereGeometry args={[1.5, 32, 32]} />
            <meshStandardMaterial
              color="#0084ff"
              metalness={0.8}
              roughness={0.2}
              envMapIntensity={1.5}
              emissive="#0066cc"
              emissiveIntensity={0.3}
            />
          </mesh>

          {/* Monedas orbitando */}
          <EuroCoin position={[3, 2, 0]} scale={0.6} />
          <EuroCoin position={[-3, -2, 0]} scale={0.7} />
          <EuroCoin position={[0, 3, -2]} scale={0.5} />
          <EuroCoin position={[0, -3, 2]} scale={0.8} />

          {/* Billetes */}
          <EuroBill position={[4, -1, -1]} rotation={[0.2, 0, 0.1]} scale={0.6} />
          <EuroBill position={[-4, 1, 1]} rotation={[-0.2, 0, -0.1]} scale={0.7} />
          <EuroBill position={[2, 3, -3]} rotation={[0.3, 0.5, 0]} scale={0.5} />

          <Environment preset="city" />
          <OrbitControls enableZoom={false} autoRotate autoRotateSpeed={0.5} />
        </Suspense>
      </Canvas>
    </div>
  );
}
