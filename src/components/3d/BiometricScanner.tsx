import { useRef } from 'react';
import { useFrame } from '@react-three/fiber';
import * as THREE from 'three';
import { Ring } from '@react-three/drei';

export default function BiometricScanner({ scanning }: { scanning: boolean }) {
  const groupRef = useRef<THREE.Group>(null);
  const ringsRef = useRef<THREE.Mesh[]>([]);

  useFrame(({ clock }) => {
    if (groupRef.current) {
      groupRef.current.rotation.y = clock.elapsedTime * 0.3;
      
      if (scanning) {
        groupRef.current.rotation.y = clock.elapsedTime * 2;
        
        ringsRef.current.forEach((ring, i) => {
          if (ring) {
            ring.scale.setScalar(1 + Math.sin(clock.elapsedTime * 3 + i) * 0.2);
          }
        });
      }
    }
  });

  return (
    <group ref={groupRef}>
      {/* Outer scanning rings */}
      {[1, 1.5, 2, 2.5].map((radius, i) => (
        <Ring
          key={i}
          ref={(el) => { if (el) ringsRef.current[i] = el as any; }}
          args={[radius, radius + 0.05, 64]}
          rotation={[Math.PI / 2, 0, 0]}
        >
          <meshStandardMaterial
            color={scanning ? "#8B5CF6" : "#3B82F6"}
            emissive={scanning ? "#8B5CF6" : "#3B82F6"}
            emissiveIntensity={scanning ? 2 : 0.5}
            transparent
            opacity={0.6}
          />
        </Ring>
      ))}
      
      {/* Central eye */}
      <mesh>
        <sphereGeometry args={[0.5, 32, 32]} />
        <meshStandardMaterial
          color={scanning ? "#EC4899" : "#06B6D4"}
          emissive={scanning ? "#EC4899" : "#06B6D4"}
          emissiveIntensity={scanning ? 3 : 1}
        />
      </mesh>

      {/* Iris */}
      <mesh position={[0, 0, 0.51]}>
        <ringGeometry args={[0.15, 0.25, 32]} />
        <meshStandardMaterial
          color="#000000"
          transparent
          opacity={0.8}
        />
      </mesh>

      {/* Scanning beams */}
      {scanning && (
        <>
          <mesh rotation={[0, 0, 0]} position={[0, 0, 0]}>
            <cylinderGeometry args={[0.02, 0.02, 5, 8]} />
            <meshStandardMaterial
              color="#8B5CF6"
              emissive="#8B5CF6"
              emissiveIntensity={2}
              transparent
              opacity={0.6}
            />
          </mesh>
          <mesh rotation={[0, Math.PI / 2, 0]} position={[0, 0, 0]}>
            <cylinderGeometry args={[0.02, 0.02, 5, 8]} />
            <meshStandardMaterial
              color="#3B82F6"
              emissive="#3B82F6"
              emissiveIntensity={2}
              transparent
              opacity={0.6}
            />
          </mesh>
        </>
      )}
    </group>
  );
}
