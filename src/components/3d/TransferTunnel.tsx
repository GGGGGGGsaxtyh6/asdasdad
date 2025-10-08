import { useRef } from 'react';
import { useFrame } from '@react-three/fiber';
import * as THREE from 'three';
import { MeshDistortMaterial } from '@react-three/drei';

export default function TransferTunnel({ active }: { active: boolean }) {
  const tunnelRef = useRef<THREE.Group>(null);
  const particlesRef = useRef<THREE.Points>(null);

  const particleCount = 1000;
  const positions = new Float32Array(particleCount * 3);
  const colors = new Float32Array(particleCount * 3);

  for (let i = 0; i < particleCount; i++) {
    const angle = (i / particleCount) * Math.PI * 2 * 10;
    const radius = 1 + Math.random() * 0.5;
    const z = (i / particleCount) * 20 - 10;

    positions[i * 3] = Math.cos(angle) * radius;
    positions[i * 3 + 1] = Math.sin(angle) * radius;
    positions[i * 3 + 2] = z;

    const colorValue = Math.random();
    colors[i * 3] = colorValue > 0.5 ? 0.54 : 0.23;
    colors[i * 3 + 1] = colorValue > 0.5 ? 0.36 : 0.51;
    colors[i * 3 + 2] = colorValue > 0.5 ? 0.96 : 0.96;
  }

  useFrame(({ clock }) => {
    if (tunnelRef.current && active) {
      tunnelRef.current.rotation.z = clock.elapsedTime * 2;
    }

    if (particlesRef.current) {
      const positions = particlesRef.current.geometry.attributes.position.array as Float32Array;
      
      for (let i = 0; i < particleCount; i++) {
        if (active) {
          positions[i * 3 + 2] -= 0.2;
          
          if (positions[i * 3 + 2] < -10) {
            positions[i * 3 + 2] = 10;
          }
        }
      }
      
      particlesRef.current.geometry.attributes.position.needsUpdate = true;
    }
  });

  return (
    <group ref={tunnelRef}>
      {/* Tunnel rings */}
      {[...Array(10)].map((_, i) => (
        <mesh key={i} position={[0, 0, i * 2 - 10]} rotation={[0, 0, (i * Math.PI) / 5]}>
          <torusGeometry args={[1.5, 0.1, 16, 32]} />
          <meshStandardMaterial
            color={active ? "#8B5CF6" : "#3B82F6"}
            emissive={active ? "#8B5CF6" : "#3B82F6"}
            emissiveIntensity={active ? 2 : 0.5}
            transparent
            opacity={0.6}
          />
        </mesh>
      ))}

      {/* Distorted tunnel surface */}
      <mesh>
        <cylinderGeometry args={[1.8, 1.8, 20, 32, 1, true]} />
        <MeshDistortMaterial
          color={active ? "#8B5CF6" : "#3B82F6"}
          transparent
          opacity={0.2}
          distort={active ? 0.5 : 0.2}
          speed={active ? 5 : 1}
        />
      </mesh>

      {/* Particle stream */}
      <points ref={particlesRef}>
        <bufferGeometry>
          <bufferAttribute
            attach="attributes-position"
            count={particleCount}
            array={positions}
            itemSize={3}
          />
          <bufferAttribute
            attach="attributes-color"
            count={particleCount}
            array={colors}
            itemSize={3}
          />
        </bufferGeometry>
        <pointsMaterial
          size={0.05}
          vertexColors
          transparent
          opacity={active ? 1 : 0.5}
          sizeAttenuation
          blending={THREE.AdditiveBlending}
        />
      </points>

      {/* Coin traveling through tunnel */}
      {active && (
        <mesh position={[0, 0, 0]}>
          <cylinderGeometry args={[0.4, 0.4, 0.1, 32]} />
          <meshStandardMaterial
            color="#F59E0B"
            metalness={1}
            roughness={0.1}
            emissive="#F59E0B"
            emissiveIntensity={2}
          />
        </mesh>
      )}
    </group>
  );
}
