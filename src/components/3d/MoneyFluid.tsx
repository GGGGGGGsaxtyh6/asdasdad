import { useRef, useMemo } from 'react';
import { useFrame } from '@react-three/fiber';
import * as THREE from 'three';
import { MeshDistortMaterial } from '@react-three/drei';

export default function MoneyFluid({ amount }: { amount: number }) {
  const fluidRef = useRef<THREE.Group>(null);
  const particlesRef = useRef<THREE.Points>(null);

  // Calculate fluid level based on amount
  const fluidLevel = Math.min((amount / 50000) * 3, 3);
  const particleCount = Math.floor(amount / 10);

  const particlesPosition = useMemo(() => {
    const positions = new Float32Array(particleCount * 3);
    const colors = new Float32Array(particleCount * 3);
    
    for (let i = 0; i < particleCount; i++) {
      const radius = Math.random() * 2;
      const theta = Math.random() * Math.PI * 2;
      const y = Math.random() * fluidLevel;
      
      positions[i * 3] = Math.cos(theta) * radius;
      positions[i * 3 + 1] = y - fluidLevel / 2;
      positions[i * 3 + 2] = Math.sin(theta) * radius;
      
      // Gold color with variation
      colors[i * 3] = 0.96 + Math.random() * 0.04;
      colors[i * 3 + 1] = 0.62 + Math.random() * 0.1;
      colors[i * 3 + 2] = 0.04 + Math.random() * 0.1;
    }
    
    return { positions, colors };
  }, [amount, particleCount, fluidLevel]);

  useFrame(({ clock }) => {
    if (fluidRef.current) {
      fluidRef.current.rotation.y = Math.sin(clock.elapsedTime * 0.5) * 0.3;
    }

    if (particlesRef.current) {
      const positions = particlesRef.current.geometry.attributes.position.array as Float32Array;
      
      for (let i = 0; i < particleCount; i++) {
        // Fluid wave motion
        const i3 = i * 3;
        const x = positions[i3];
        const z = positions[i3 + 2];
        
        positions[i3 + 1] += Math.sin(clock.elapsedTime * 2 + x) * 0.01;
        
        // Circular flow
        const angle = Math.atan2(z, x);
        const radius = Math.sqrt(x * x + z * z);
        const newAngle = angle + 0.01;
        
        positions[i3] = Math.cos(newAngle) * radius;
        positions[i3 + 2] = Math.sin(newAngle) * radius;
        
        // Reset if too high
        if (positions[i3 + 1] > fluidLevel / 2) {
          positions[i3 + 1] = -fluidLevel / 2;
        }
      }
      
      particlesRef.current.geometry.attributes.position.needsUpdate = true;
    }
  });

  return (
    <group ref={fluidRef}>
      {/* Main fluid body */}
      <mesh position={[0, 0, 0]} scale={[2, fluidLevel, 2]}>
        <cylinderGeometry args={[1, 1, 1, 32, 32, true]} />
        <MeshDistortMaterial
          color="#F59E0B"
          transparent
          opacity={0.3}
          distort={0.4}
          speed={2}
          roughness={0.2}
          metalness={0.8}
        />
      </mesh>

      {/* Particle money */}
      <points ref={particlesRef}>
        <bufferGeometry>
          <bufferAttribute
            attach="attributes-position"
            count={particleCount}
            array={particlesPosition.positions}
            itemSize={3}
          />
          <bufferAttribute
            attach="attributes-color"
            count={particleCount}
            array={particlesPosition.colors}
            itemSize={3}
          />
        </bufferGeometry>
        <pointsMaterial
          size={0.08}
          vertexColors
          transparent
          opacity={0.9}
          sizeAttenuation
          blending={THREE.AdditiveBlending}
        />
      </points>

      {/* Surface reflection */}
      <mesh position={[0, fluidLevel / 2 + 0.01, 0]} rotation={[-Math.PI / 2, 0, 0]}>
        <circleGeometry args={[2, 64]} />
        <meshStandardMaterial
          color="#F59E0B"
          transparent
          opacity={0.5}
          metalness={1}
          roughness={0}
        />
      </mesh>
    </group>
  );
}
