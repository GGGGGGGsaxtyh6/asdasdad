import { useRef } from 'react';
import { useFrame } from '@react-three/fiber';
import * as THREE from 'three';
import { Text } from '@react-three/drei';

interface Transaction {
  amount: number;
  timestamp: number;
  type: string;
}

export default function HolographicChart({ data }: { data: Transaction[] }) {
  const groupRef = useRef<THREE.Group>(null);

  useFrame(({ clock }) => {
    if (groupRef.current) {
      groupRef.current.rotation.y = clock.elapsedTime * 0.2;
    }
  });

  // Take last 10 transactions
  const recentData = data.slice(0, 10).reverse();
  const maxAmount = Math.max(...recentData.map(t => t.amount), 1);

  return (
    <group ref={groupRef}>
      {recentData.map((transaction, i) => {
        const height = (transaction.amount / maxAmount) * 3;
        const angle = (i / recentData.length) * Math.PI * 2;
        const radius = 3;
        const x = Math.cos(angle) * radius;
        const z = Math.sin(angle) * radius;

        return (
          <group key={i} position={[x, 0, z]}>
            {/* Bar */}
            <mesh position={[0, height / 2, 0]}>
              <boxGeometry args={[0.3, height, 0.3]} />
              <meshStandardMaterial
                color={transaction.type === 'send' ? "#EC4899" : "#06B6D4"}
                emissive={transaction.type === 'send' ? "#EC4899" : "#06B6D4"}
                emissiveIntensity={1}
                transparent
                opacity={0.8}
              />
            </mesh>

            {/* Value label */}
            <Text
              position={[0, height + 0.5, 0]}
              fontSize={0.3}
              color="#F59E0B"
              anchorX="center"
              anchorY="middle"
            >
              {transaction.amount.toFixed(0)}
            </Text>

            {/* Connection line to center */}
            <mesh>
              <cylinderGeometry args={[0.02, 0.02, radius, 8]} />
              <meshBasicMaterial
                color="#8B5CF6"
                transparent
                opacity={0.3}
              />
            </mesh>
          </group>
        );
      })}

      {/* Central orb */}
      <mesh>
        <sphereGeometry args={[0.5, 32, 32]} />
        <meshStandardMaterial
          color="#8B5CF6"
          emissive="#8B5CF6"
          emissiveIntensity={2}
          transparent
          opacity={0.7}
        />
      </mesh>
    </group>
  );
}
