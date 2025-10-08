'use client';

import { useRef, useState } from 'react';
import { useFrame } from '@react-three/fiber';
import { Mesh } from 'three';

interface EuroCoinProps {
  position?: [number, number, number];
  scale?: number;
  interactive?: boolean;
}

export default function EuroCoin({ position = [0, 0, 0], scale = 1, interactive = true }: EuroCoinProps) {
  const meshRef = useRef<Mesh>(null);
  const [hovered, setHovered] = useState(false);

  useFrame((state) => {
    if (meshRef.current) {
      meshRef.current.rotation.y += 0.01;
      if (hovered && interactive) {
        meshRef.current.rotation.x = Math.sin(state.clock.elapsedTime * 2) * 0.2;
        meshRef.current.position.y = position[1] + Math.sin(state.clock.elapsedTime * 2) * 0.1;
      }
    }
  });

  return (
    <mesh
      ref={meshRef}
      position={position}
      scale={scale}
      onPointerOver={() => interactive && setHovered(true)}
      onPointerOut={() => interactive && setHovered(false)}
    >
      <cylinderGeometry args={[1, 1, 0.15, 32]} />
      <meshStandardMaterial
        color="#D4A574"
        metalness={0.9}
        roughness={0.2}
        envMapIntensity={1.5}
      />
      {/* Borde estriado */}
      <mesh rotation={[0, 0, 0]}>
        <torusGeometry args={[1, 0.08, 16, 100]} />
        <meshStandardMaterial
          color="#B8860B"
          metalness={0.95}
          roughness={0.15}
        />
      </mesh>
    </mesh>
  );
}
