'use client';

import { useRef, useState, useMemo } from 'react';
import { useFrame } from '@react-three/fiber';
import { Mesh } from 'three';

interface OptimizedCoinProps {
  position?: [number, number, number];
  scale?: number;
  interactive?: boolean;
  lod?: 'high' | 'medium' | 'low';
}

export default function OptimizedCoin({ 
  position = [0, 0, 0], 
  scale = 1, 
  interactive = true,
  lod = 'high'
}: OptimizedCoinProps) {
  const meshRef = useRef<Mesh>(null);
  const [hovered, setHovered] = useState(false);

  // LOD - Level of Detail optimization
  const segments = useMemo(() => {
    switch (lod) {
      case 'high': return 32;
      case 'medium': return 16;
      case 'low': return 8;
      default: return 32;
    }
  }, [lod]);

  useFrame((state) => {
    if (meshRef.current) {
      // Optimize rotation - only when visible
      meshRef.current.rotation.y += 0.01;
      
      if (hovered && interactive) {
        const time = state.clock.elapsedTime;
        meshRef.current.rotation.x = Math.sin(time * 2) * 0.2;
        meshRef.current.position.y = position[1] + Math.sin(time * 2) * 0.1;
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
      frustumCulled={true}
    >
      <cylinderGeometry args={[1, 1, 0.15, segments]} />
      <meshStandardMaterial
        color="#D4A574"
        metalness={0.9}
        roughness={0.2}
        envMapIntensity={1.5}
      />
      {lod !== 'low' && (
        <mesh rotation={[0, 0, 0]}>
          <torusGeometry args={[1, 0.08, segments / 2, segments * 3]} />
          <meshStandardMaterial
            color="#B8860B"
            metalness={0.95}
            roughness={0.15}
          />
        </mesh>
      )}
    </mesh>
  );
}
