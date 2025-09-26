import React, { useState, useEffect } from 'react';
import { ThemeProvider, createTheme, CssBaseline } from '@mui/material';
import Login from './components/Login';
import SimpleDashboard from './components/SimpleDashboard';

// Tema personalizado
const theme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#667eea',
    },
    secondary: {
      main: '#764ba2',
    },
    background: {
      default: '#0d1117',
      paper: '#161b22',
    },
    text: {
      primary: '#c9d1d9',
      secondary: '#8b949e',
    },
  },
  typography: {
    fontFamily: '"Fira Code", "Consolas", "Monaco", monospace',
    h1: {
      fontWeight: 700,
    },
    h2: {
      fontWeight: 600,
    },
    h3: {
      fontWeight: 600,
    },
    h4: {
      fontWeight: 600,
    },
    h5: {
      fontWeight: 600,
    },
    h6: {
      fontWeight: 600,
    },
  },
  components: {
    MuiButton: {
      styleOverrides: {
        root: {
          textTransform: 'none',
          borderRadius: 8,
          fontWeight: 600,
        },
      },
    },
    MuiCard: {
      styleOverrides: {
        root: {
          borderRadius: 12,
          background: 'linear-gradient(145deg, #161b22 0%, #21262d 100%)',
          border: '1px solid #30363d',
        },
      },
    },
    MuiTextField: {
      styleOverrides: {
        root: {
          '& .MuiOutlinedInput-root': {
            borderRadius: 8,
            '&:hover fieldset': {
              borderColor: '#667eea',
            },
            '&.Mui-focused fieldset': {
              borderColor: '#667eea',
            },
          },
        },
      },
    },
  },
});

interface User {
  id: number;
  username: string;
  role: string;
}

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [token, setToken] = useState<string | null>(null);
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    // Verificar si hay un token guardado
    const savedToken = localStorage.getItem('stealer-web-token');
    const savedUser = localStorage.getItem('stealer-web-user');

    if (savedToken && savedUser) {
      // Verificar si el token sigue siendo válido
      fetch('https://7c2def2c86ac.ngrok-free.app/api/verify', {
        headers: {
          'Authorization': `Bearer ${savedToken}`,
        },
      })
        .then(response => {
          if (response.ok) {
            setToken(savedToken);
            setUser(JSON.parse(savedUser));
            setIsAuthenticated(true);
          } else {
            // Token inválido, limpiar storage
            localStorage.removeItem('stealer-web-token');
            localStorage.removeItem('stealer-web-user');
          }
        })
        .catch(() => {
          // Error de conexión, limpiar storage
          localStorage.removeItem('stealer-web-token');
          localStorage.removeItem('stealer-web-user');
        });
    }
  }, []);

  const handleLogin = (newToken: string, newUser: User) => {
    setToken(newToken);
    setUser(newUser);
    setIsAuthenticated(true);
    
    // Guardar en localStorage
    localStorage.setItem('stealer-web-token', newToken);
    localStorage.setItem('stealer-web-user', JSON.stringify(newUser));
  };

  const handleDisconnect = () => {
    setToken(null);
    setUser(null);
    setIsAuthenticated(false);
    
    // Limpiar localStorage
    localStorage.removeItem('stealer-web-token');
    localStorage.removeItem('stealer-web-user');
  };

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <div style={{ height: '100vh', overflow: 'hidden' }}>
        {isAuthenticated && token && user ? (
          <SimpleDashboard
            token={token}
            user={user}
            onDisconnect={handleDisconnect}
          />
        ) : (
          <Login onLogin={handleLogin} />
        )}
      </div>
    </ThemeProvider>
  );
}

export default App;
