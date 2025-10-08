import { create } from 'zustand';

interface Transaction {
  id: string;
  type: 'send' | 'receive' | 'game_reward';
  amount: number;
  from?: string;
  to?: string;
  timestamp: number;
  status: 'completed' | 'pending';
}

interface User {
  id: string;
  username: string;
  email: string;
  balance: number;
  avatar?: string;
}

interface GameState {
  score: number;
  coinsCollected: number;
  isPlaying: boolean;
}

interface StoreState {
  // Auth
  user: User | null;
  token: string | null;
  isAuthenticated: boolean;
  login: (email: string, password: string) => Promise<void>;
  register: (username: string, email: string, password: string) => Promise<void>;
  logout: () => void;
  
  // Balance & Transactions
  transactions: Transaction[];
  addTransaction: (transaction: Omit<Transaction, 'id' | 'timestamp'>) => void;
  updateBalance: (amount: number) => void;
  
  // UI State
  viewMode: '3d' | 'matrix' | 'particles' | 'vault';
  setViewMode: (mode: '3d' | 'matrix' | 'particles' | 'vault') => void;
  
  // Game
  gameState: GameState;
  startGame: () => void;
  endGame: (coinsCollected: number) => void;
  
  // Audio
  audioEnabled: boolean;
  toggleAudio: () => void;
  
  // Effects
  particleEffect: boolean;
  setParticleEffect: (enabled: boolean) => void;
}

export const useStore = create<StoreState>((set, get) => ({
  // Initial Auth State
  user: null,
  token: localStorage.getItem('token'),
  isAuthenticated: false,
  
  login: async (email: string, _password: string) => {
    // Simulated login
    const mockUser: User = {
      id: '1',
      username: email.split('@')[0],
      email,
      balance: 15000,
    };
    
    const mockToken = 'quantum-vault-token-' + Date.now();
    localStorage.setItem('token', mockToken);
    
    set({
      user: mockUser,
      token: mockToken,
      isAuthenticated: true,
    });
  },
  
  register: async (username: string, email: string, _password: string) => {
    const mockUser: User = {
      id: Date.now().toString(),
      username,
      email,
      balance: 10000, // Starting bonus
    };
    
    const mockToken = 'quantum-vault-token-' + Date.now();
    localStorage.setItem('token', mockToken);
    
    set({
      user: mockUser,
      token: mockToken,
      isAuthenticated: true,
    });
  },
  
  logout: () => {
    localStorage.removeItem('token');
    set({
      user: null,
      token: null,
      isAuthenticated: false,
    });
  },
  
  // Transactions
  transactions: [],
  
  addTransaction: (transaction) => {
    const newTransaction: Transaction = {
      ...transaction,
      id: Date.now().toString(),
      timestamp: Date.now(),
    };
    
    set((state) => ({
      transactions: [newTransaction, ...state.transactions],
    }));
  },
  
  updateBalance: (amount) => {
    set((state) => ({
      user: state.user ? { ...state.user, balance: state.user.balance + amount } : null,
    }));
  },
  
  // UI
  viewMode: '3d',
  setViewMode: (mode) => set({ viewMode: mode }),
  
  // Game
  gameState: {
    score: 0,
    coinsCollected: 0,
    isPlaying: false,
  },
  
  startGame: () => {
    set({
      gameState: {
        score: 0,
        coinsCollected: 0,
        isPlaying: true,
      },
    });
  },
  
  endGame: (coinsCollected) => {
    const reward = coinsCollected * 10;
    
    get().updateBalance(reward);
    get().addTransaction({
      type: 'game_reward',
      amount: reward,
      status: 'completed',
    });
    
    set((state) => ({
      gameState: {
        ...state.gameState,
        coinsCollected,
        isPlaying: false,
      },
    }));
  },
  
  // Audio
  audioEnabled: true,
  toggleAudio: () => set((state) => ({ audioEnabled: !state.audioEnabled })),
  
  // Effects
  particleEffect: true,
  setParticleEffect: (enabled) => set({ particleEffect: enabled }),
}));
