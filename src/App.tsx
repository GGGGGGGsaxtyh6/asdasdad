import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useStore } from './store/useStore';
import LandingPage from './pages/LandingPage';
import AuthPage from './pages/AuthPage';
import DashboardPage from './pages/DashboardPage';
import VaultPage from './pages/VaultPage';
import GamePage from './pages/GamePage';
import TransferPage from './pages/TransferPage';
import CursorGlow from './components/effects/CursorGlow';
import MatrixRain from './components/effects/MatrixRain';

function App() {
  const isAuthenticated = useStore((state) => state.isAuthenticated);

  return (
    <BrowserRouter>
      <CursorGlow />
      <MatrixRain />
      
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route 
          path="/auth" 
          element={isAuthenticated ? <Navigate to="/dashboard" /> : <AuthPage />} 
        />
        <Route 
          path="/dashboard" 
          element={isAuthenticated ? <DashboardPage /> : <Navigate to="/auth" />} 
        />
        <Route 
          path="/vault" 
          element={isAuthenticated ? <VaultPage /> : <Navigate to="/auth" />} 
        />
        <Route 
          path="/game" 
          element={isAuthenticated ? <GamePage /> : <Navigate to="/auth" />} 
        />
        <Route 
          path="/transfer" 
          element={isAuthenticated ? <TransferPage /> : <Navigate to="/auth" />} 
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
