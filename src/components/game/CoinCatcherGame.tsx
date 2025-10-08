import { useEffect, useState } from 'react';
import { useBox, useSphere } from '@react-three/cannon';
import { useFrame } from '@react-three/fiber';

function FallingCoin({ position, onCollect }: { position: [number, number, number]; onCollect: () => void }) {
  const [ref, api] = useSphere(() => ({
    mass: 1,
    position,
    args: [0.3],
    onCollide: (e) => {
      if (e.body) {
        onCollect();
        api.position.set(1000, 1000, 1000); // Move off screen
      }
    },
  }));

  useFrame(() => {
    if (ref.current) {
      (ref.current as any).rotation.y += 0.1;
    }
  });

  return (
    <mesh ref={ref as any} castShadow>
      <cylinderGeometry args={[0.3, 0.3, 0.1, 32]} />
      <meshStandardMaterial
        color="#F59E0B"
        metalness={1}
        roughness={0.1}
        emissive="#F59E0B"
        emissiveIntensity={0.5}
      />
    </mesh>
  );
}

function Basket({ position }: { position: [number, number, number] }) {
  const [ref, api] = useBox(() => ({
    mass: 1,
    type: 'Kinematic',
    position,
    args: [2, 0.5, 2],
  }));

  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      const speed = 0.5;
      const currentPos = position;

      switch (e.key.toLowerCase()) {
        case 'arrowleft':
        case 'a':
          api.position.set(currentPos[0] - speed, currentPos[1], currentPos[2]);
          break;
        case 'arrowright':
        case 'd':
          api.position.set(currentPos[0] + speed, currentPos[1], currentPos[2]);
          break;
        case 'arrowup':
        case 'w':
          api.position.set(currentPos[0], currentPos[1], currentPos[2] - speed);
          break;
        case 'arrowdown':
        case 's':
          api.position.set(currentPos[0], currentPos[1], currentPos[2] + speed);
          break;
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [api, position]);

  return (
    <mesh ref={ref as any} receiveShadow>
      <boxGeometry args={[2, 0.5, 2]} />
      <meshStandardMaterial
        color="#8B5CF6"
        metalness={0.7}
        roughness={0.3}
        transparent
        opacity={0.8}
      />
    </mesh>
  );
}

export default function CoinCatcherGame({ onCollect }: { onCollect: () => void }) {
  const [coins, setCoins] = useState<Array<{ id: number; position: [number, number, number] }>>([]);
  const [basketPos] = useState<[number, number, number]>([0, 0, 0]);

  useEffect(() => {
    const interval = setInterval(() => {
      const newCoin = {
        id: Date.now(),
        position: [
          (Math.random() - 0.5) * 8,
          10 + Math.random() * 5,
          (Math.random() - 0.5) * 8,
        ] as [number, number, number],
      };
      setCoins((prev) => [...prev, newCoin]);
    }, 800);

    return () => clearInterval(interval);
  }, []);

  // Ground
  const [groundRef] = useBox(() => ({
    type: 'Static',
    position: [0, -1, 0],
    args: [20, 0.5, 20],
  }));

  return (
    <>
      {/* Ground */}
      <mesh ref={groundRef as any} receiveShadow>
        <boxGeometry args={[20, 0.5, 20]} />
        <meshStandardMaterial color="#1a1a2e" />
      </mesh>

      {/* Basket */}
      <Basket position={basketPos} />

      {/* Falling Coins */}
      {coins.map((coin) => (
        <FallingCoin
          key={coin.id}
          position={coin.position}
          onCollect={onCollect}
        />
      ))}

      {/* Lights for dramatic effect */}
      <pointLight position={[0, 10, 0]} intensity={1} color="#F59E0B" />
    </>
  );
}
