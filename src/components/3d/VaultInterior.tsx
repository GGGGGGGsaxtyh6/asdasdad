import { usePlane, useBox } from '@react-three/cannon';

export default function VaultInterior() {
  // Floor
  const [floorRef] = usePlane(() => ({
    rotation: [-Math.PI / 2, 0, 0],
    position: [0, 0, 0],
  }));

  // Walls
  const [backWallRef] = usePlane(() => ({
    rotation: [0, 0, 0],
    position: [0, 5, -10],
  }));

  const [leftWallRef] = usePlane(() => ({
    rotation: [0, Math.PI / 2, 0],
    position: [-10, 5, 0],
  }));

  const [rightWallRef] = usePlane(() => ({
    rotation: [0, -Math.PI / 2, 0],
    position: [10, 5, 0],
  }));

  return (
    <>
      {/* Floor - Golden */}
      <mesh ref={floorRef as any} receiveShadow>
        <planeGeometry args={[20, 20]} />
        <meshStandardMaterial
          color="#F59E0B"
          metalness={0.9}
          roughness={0.1}
        />
      </mesh>

      {/* Back Wall - Purple gradient */}
      <mesh ref={backWallRef as any} receiveShadow>
        <planeGeometry args={[20, 10]} />
        <meshStandardMaterial
          color="#8B5CF6"
          metalness={0.5}
          roughness={0.5}
        />
      </mesh>

      {/* Left Wall */}
      <mesh ref={leftWallRef as any} receiveShadow>
        <planeGeometry args={[20, 10]} />
        <meshStandardMaterial
          color="#3B82F6"
          metalness={0.5}
          roughness={0.5}
        />
      </mesh>

      {/* Right Wall */}
      <mesh ref={rightWallRef as any} receiveShadow>
        <planeGeometry args={[20, 10]} />
        <meshStandardMaterial
          color="#06B6D4"
          metalness={0.5}
          roughness={0.5}
        />
      </mesh>

      {/* Money Piles */}
      <MoneyPile position={[-3, 0.5, -3]} />
      <MoneyPile position={[3, 0.5, -3]} />
      <MoneyPile position={[-3, 0.5, 3]} />
      <MoneyPile position={[3, 0.5, 3]} />

      {/* Vault Door */}
      <mesh position={[0, 2.5, 9.9]} castShadow>
        <cylinderGeometry args={[2, 2, 0.5, 32]} />
        <meshStandardMaterial
          color="#718096"
          metalness={1}
          roughness={0.3}
        />
      </mesh>

      {/* Vault Door Handle */}
      <mesh position={[0, 2.5, 10.2]} rotation={[0, 0, Math.PI / 2]}>
        <torusGeometry args={[0.5, 0.1, 16, 32]} />
        <meshStandardMaterial
          color="#F59E0B"
          metalness={1}
          roughness={0.2}
        />
      </mesh>
    </>
  );
}

function MoneyPile({ position }: { position: [number, number, number] }) {
  const [ref] = useBox(() => ({
    mass: 0,
    position,
    args: [1.5, 1, 1.5],
  }));

  return (
    <mesh ref={ref as any} castShadow>
      <boxGeometry args={[1.5, 1, 1.5]} />
      <meshStandardMaterial
        color="#F59E0B"
        metalness={0.8}
        roughness={0.2}
      />
    </mesh>
  );
}
