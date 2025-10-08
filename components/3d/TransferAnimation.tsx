'use client';

import { useRef, useState } from 'react';
import { useFrame } from '@react-three/fiber';
import { Mesh, Vector3 } from 'three';

export function TransferAnimation({ onComplete }: { onComplete?: () => void }) {
  const meshRef = useRef<Mesh>(null);
  const [progress, setProgress] = useState(0);

  useFrame(() => {
    if (meshRef.current && progress < 1) {
      const newProgress = progress + 0.02;
      setProgress(newProgress);

      // Animate along a curved path
      const x = -3 + newProgress * 6;
      const y = Math.sin(newProgress * Math.PI) * 2;
      meshRef.current.position.set(x, y, 0);
      meshRef.current.rotation.z += 0.1;

      // Scale effect
      const scale = 1 + Math.sin(newProgress * Math.PI) * 0.5;
      meshRef.current.scale.set(scale, scale, scale);

      if (newProgress >= 1 && onComplete) {
        onComplete();
      }
    }
  });

  return (
    <group>
      <mesh ref={meshRef} position={[-3, 0, 0]}>
        <sphereGeometry args={[0.3, 32, 32]} />
        <meshStandardMaterial
          color="#0ea5e9"
          metalness={0.9}
          roughness={0.1}
          emissive="#0ea5e9"
          emissiveIntensity={0.5}
        />
      </mesh>
      
      {/* Trail effect */}
      {progress > 0.1 && (
        <mesh position={[meshRef.current?.position.x || 0 - 0.5, meshRef.current?.position.y || 0, 0]}>
          <sphereGeometry args={[0.2, 16, 16]} />
          <meshStandardMaterial
            color="#0ea5e9"
            transparent
            opacity={0.5}
            emissive="#0ea5e9"
            emissiveIntensity={0.3}
          />
        </mesh>
      )}
    </group>
  );
}
