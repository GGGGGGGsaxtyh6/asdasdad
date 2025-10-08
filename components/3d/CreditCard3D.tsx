'use client';

import { useRef } from 'react';
import { useFrame } from '@react-three/fiber';
import { Mesh } from 'three';
import { RoundedBox } from '@react-three/drei';

export function CreditCard3D() {
  const cardRef = useRef<Mesh>(null);

  useFrame((state) => {
    if (cardRef.current) {
      cardRef.current.rotation.y = Math.sin(state.clock.elapsedTime * 0.5) * 0.1;
      cardRef.current.rotation.x = Math.cos(state.clock.elapsedTime * 0.3) * 0.05;
    }
  });

  return (
    <group>
      <RoundedBox
        ref={cardRef}
        args={[3.5, 2.2, 0.1]}
        radius={0.1}
        smoothness={4}
        position={[0, 0, 0]}
      >
        <meshStandardMaterial
          color="#1e293b"
          metalness={0.8}
          roughness={0.2}
          emissive="#0ea5e9"
          emissiveIntensity={0.1}
        />
      </RoundedBox>
      
      {/* Chip */}
      <RoundedBox
        args={[0.5, 0.4, 0.05]}
        radius={0.05}
        smoothness={4}
        position={[-1, 0.3, 0.06]}
      >
        <meshStandardMaterial
          color="#f59e0b"
          metalness={0.9}
          roughness={0.1}
        />
      </RoundedBox>

      {/* Light effects */}
      <pointLight position={[0, 0, 2]} intensity={0.5} color="#0ea5e9" />
    </group>
  );
}
