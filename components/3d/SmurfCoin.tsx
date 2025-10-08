'use client';

import { useRef } from 'react';
import { useFrame } from '@react-three/fiber';
import { Mesh } from 'three';

export function SmurfCoin({ position = [0, 0, 0] }: { position?: [number, number, number] }) {
  const meshRef = useRef<Mesh>(null);

  useFrame((state) => {
    if (meshRef.current) {
      meshRef.current.rotation.y += 0.01;
      meshRef.current.position.y = position[1] + Math.sin(state.clock.elapsedTime) * 0.1;
    }
  });

  return (
    <mesh ref={meshRef} position={position}>
      <cylinderGeometry args={[1, 1, 0.2, 32]} />
      <meshStandardMaterial
        color="#0ea5e9"
        metalness={0.9}
        roughness={0.1}
        emissive="#0284c7"
        emissiveIntensity={0.2}
      />
    </mesh>
  );
}
