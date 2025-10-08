import { useSphere } from '@react-three/cannon';
import { useRef } from 'react';
import { useFrame } from '@react-three/fiber';
import * as THREE from 'three';

function FloatingCoin({ position }: { position: [number, number, number] }) {
  const [ref, api] = useSphere(() => ({
    mass: 0.1,
    position,
    args: [0.2],
  }));

  const meshRef = useRef<THREE.Mesh>(null);

  useFrame(() => {
    if (meshRef.current) {
      meshRef.current.rotation.y += 0.05;
    }

    // Apply gentle upward force periodically
    if (Math.random() > 0.98) {
      api.applyForce([0, 2, 0], [0, 0, 0]);
    }
  });

  return (
    <mesh ref={ref as any} castShadow>
      <cylinderGeometry args={[0.2, 0.2, 0.05, 32]} />
      <meshStandardMaterial
        color="#F59E0B"
        metalness={1}
        roughness={0.1}
        emissive="#F59E0B"
        emissiveIntensity={0.3}
      />
    </mesh>
  );
}

export default function FloatingCoins({ count }: { count: number }) {
  const coins = Array.from({ length: count }, (_, i) => {
    const angle = (i / count) * Math.PI * 2;
    const radius = 2 + Math.random() * 3;
    return [
      Math.cos(angle) * radius,
      1 + Math.random() * 3,
      Math.sin(angle) * radius,
    ] as [number, number, number];
  });

  return (
    <>
      {coins.map((pos, i) => (
        <FloatingCoin key={i} position={pos} />
      ))}
    </>
  );
}
