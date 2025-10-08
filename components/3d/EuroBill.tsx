'use client';

import { useRef, useState } from 'react';
import { useFrame } from '@react-three/fiber';
import { Mesh } from 'three';

interface EuroBillProps {
  position?: [number, number, number];
  rotation?: [number, number, number];
  scale?: number;
  interactive?: boolean;
}

export default function EuroBill({ 
  position = [0, 0, 0], 
  rotation = [0, 0, 0],
  scale = 1, 
  interactive = true 
}: EuroBillProps) {
  const meshRef = useRef<Mesh>(null);
  const [hovered, setHovered] = useState(false);

  useFrame((state) => {
    if (meshRef.current && interactive) {
      if (hovered) {
        meshRef.current.rotation.y = rotation[1] + Math.sin(state.clock.elapsedTime * 2) * 0.1;
        meshRef.current.position.y = position[1] + Math.sin(state.clock.elapsedTime * 3) * 0.05;
      } else {
        meshRef.current.rotation.y += 0.005;
      }
    }
  });

  return (
    <group>
      <mesh
        ref={meshRef}
        position={position}
        rotation={rotation}
        scale={[scale * 2, scale, scale * 0.01]}
        onPointerOver={() => interactive && setHovered(true)}
        onPointerOut={() => interactive && setHovered(false)}
      >
        <boxGeometry args={[1, 0.5, 1]} />
        <meshStandardMaterial
          color="#7CB342"
          metalness={0.3}
          roughness={0.4}
          envMapIntensity={1}
        />
      </mesh>
      {/* Holograma */}
      {hovered && (
        <mesh
          position={[position[0] + 0.5, position[1], position[2] + 0.01]}
          rotation={rotation}
          scale={[scale * 0.3, scale * 0.3, scale * 0.01]}
        >
          <planeGeometry args={[1, 1]} />
          <meshStandardMaterial
            color="#FFD700"
            metalness={0.9}
            roughness={0.1}
            transparent
            opacity={0.8}
            emissive="#FFD700"
            emissiveIntensity={0.5}
          />
        </mesh>
      )}
    </group>
  );
}
