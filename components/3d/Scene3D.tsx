'use client';

import { Canvas } from '@react-three/fiber';
import { OrbitControls, PerspectiveCamera, Environment } from '@react-three/drei';
import { ReactNode, Suspense } from 'react';

interface Scene3DProps {
  children: ReactNode;
  controls?: boolean;
  camera?: { position: [number, number, number]; fov?: number };
}

export function Scene3D({ children, controls = false, camera }: Scene3DProps) {
  return (
    <Canvas
      shadows
      style={{ background: 'transparent' }}
      gl={{ alpha: true, antialias: true }}
    >
      <Suspense fallback={null}>
        <PerspectiveCamera
          makeDefault
          position={camera?.position || [0, 0, 5]}
          fov={camera?.fov || 50}
        />
        
        <ambientLight intensity={0.5} />
        <spotLight
          position={[10, 10, 10]}
          angle={0.15}
          penumbra={1}
          intensity={1}
          castShadow
        />
        <pointLight position={[-10, -10, -10]} intensity={0.5} color="#0ea5e9" />
        
        <Environment preset="city" />
        
        {children}
        
        {controls && <OrbitControls enableZoom={false} />}
      </Suspense>
    </Canvas>
  );
}
