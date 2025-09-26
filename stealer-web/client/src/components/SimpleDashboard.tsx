import React, { useState, useEffect } from 'react';
import {
  Box,
  Paper,
  Typography,
  Grid,
  Card,
  CardContent,
  Button,
  Chip,
  IconButton,
  Tooltip,
  Badge,
  LinearProgress,
  Tabs,
  Tab,
  Switch,
  FormControlLabel,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Alert,
  Snackbar,
  Container
} from '@mui/material';
import {
  Security,
  Terminal,
  FolderOpen,
  NetworkCheck,
  Memory,
  Storage,
  Speed,
  Warning,
  CheckCircle,
  Error,
  Info,
  Settings,
  Refresh,
  Download,
  Upload,
  Delete,
  Edit,
  Add,
  Remove,
  PlayArrow,
  Pause,
  Stop,
  Fullscreen,
  FullscreenExit,
  DarkMode,
  LightMode,
  Palette,
  Notifications,
  AccountCircle,
  ExitToApp,
  Dashboard as DashboardIcon,
  Computer,
  CloudSync,
  BugReport,
  Analytics,
  Timeline,
  ShowChart,
  BarChart,
  PieChart,
  TableChart,
  ExpandMore,
  ExpandLess,
  KeyboardArrowUp,
  KeyboardArrowDown,
  FilterList,
  Search,
  Sort,
  MoreVert,
  Visibility,
  VisibilityOff,
  Lock,
  LockOpen,
  VpnKey,
  Shield,
  Security as SecurityIcon,
  MonitorHeart,
  DeveloperMode,
  Code,
  Build,
  Extension,
  Api,
  Webhook,
  CloudQueue,
  CloudDone,
  CloudOff,
  Sync,
  SyncProblem,
  Wifi,
  WifiOff,
  Router,
  Lan,
  Hub,
  DeviceHub,
  Power,
  BatteryFull,
  BatteryStd,
  BatteryAlert,
  BatteryUnknown,
  Thermostat,
  AcUnit,
  Whatshot,
  WbSunny,
  WbCloudy,
  Cloud,
  CloudDownload,
  CloudUpload,
  CloudSync as CloudSyncIcon,
  CloudDone as CloudDoneIcon,
  CloudOff as CloudOffIcon,
  CloudQueue as CloudQueueIcon
} from '@mui/icons-material';
import { motion, AnimatePresence } from 'framer-motion';
import { useHotkeys } from 'react-hotkeys-hook';
import AdvancedTerminal from './AdvancedTerminal';

interface SimpleDashboardProps {
  token: string;
  user: any;
  onDisconnect: () => void;
}

interface SystemMetrics {
  cpu: number;
  memory: number;
  disk: number;
  network: number;
  processes: number;
  connections: number;
  uptime: string;
  temperature: number;
}

const SimpleDashboard: React.FC<SimpleDashboardProps> = ({ token, user, onDisconnect }) => {
  const [activeTab, setActiveTab] = useState(0);
  const [darkMode, setDarkMode] = useState(true);
  const [autoRefresh, setAutoRefresh] = useState(true);
  const [refreshInterval, setRefreshInterval] = useState(5);
  const [systemMetrics, setSystemMetrics] = useState<SystemMetrics>({
    cpu: 0,
    memory: 0,
    disk: 0,
    network: 0,
    processes: 0,
    connections: 0,
    uptime: '0d 0h 0m',
    temperature: 45
  });
  const [isFullscreen, setIsFullscreen] = useState(false);
  const [showSettings, setShowSettings] = useState(false);
  const [terminalSessions, setTerminalSessions] = useState<any[]>([]);
  const [activeTerminal, setActiveTerminal] = useState(0);
  const [securityStatus, setSecurityStatus] = useState('secure');
  const [threatLevel, setThreatLevel] = useState('low');

  // Hotkeys
  useHotkeys('ctrl+shift+d', () => setDarkMode(!darkMode));
  useHotkeys('ctrl+shift+f', () => setIsFullscreen(!isFullscreen));
  useHotkeys('ctrl+shift+s', () => setShowSettings(true));
  useHotkeys('ctrl+shift+t', () => setActiveTab(1));
  useHotkeys('ctrl+shift+p', () => setActiveTab(2));
  useHotkeys('ctrl+shift+m', () => setActiveTab(3));

  // Simulación de datos en tiempo real
  useEffect(() => {
    const interval = setInterval(() => {
      if (autoRefresh) {
        setSystemMetrics({
          cpu: Math.random() * 100,
          memory: Math.random() * 100,
          disk: Math.random() * 100,
          network: Math.random() * 100,
          processes: Math.floor(Math.random() * 200) + 50,
          connections: Math.floor(Math.random() * 50) + 10,
          uptime: `${Math.floor(Math.random() * 30)}d ${Math.floor(Math.random() * 24)}h ${Math.floor(Math.random() * 60)}m`,
          temperature: Math.random() * 20 + 40
        });
      }
    }, refreshInterval * 1000);

    return () => clearInterval(interval);
  }, [autoRefresh, refreshInterval]);

  const getThreatColor = (level: string) => {
    switch (level) {
      case 'low': return 'success';
      case 'medium': return 'warning';
      case 'high': return 'error';
      default: return 'default';
    }
  };

  const renderSystemMetrics = () => (
    <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap', mb: 3 }}>
      {/* CPU Usage */}
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
      >
        <Card sx={{ 
          background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
          color: 'white',
          minWidth: 250,
          flex: 1
        }}>
          <CardContent>
            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
              <Box>
                <Typography variant="h6" gutterBottom>
                  CPU Usage
                </Typography>
                <Typography variant="h3" fontWeight="bold">
                  {systemMetrics.cpu.toFixed(1)}%
                </Typography>
              </Box>
              <Speed sx={{ fontSize: 40, opacity: 0.8 }} />
            </Box>
            <LinearProgress 
              variant="determinate" 
              value={systemMetrics.cpu} 
              sx={{ 
                mt: 2, 
                height: 8, 
                borderRadius: 4,
                backgroundColor: 'rgba(255,255,255,0.3)',
                '& .MuiLinearProgress-bar': {
                  backgroundColor: 'white'
                }
              }} 
            />
          </CardContent>
        </Card>
      </motion.div>

      {/* Memory Usage */}
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5, delay: 0.1 }}
      >
        <Card sx={{ 
          background: 'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)',
          color: 'white',
          minWidth: 250,
          flex: 1
        }}>
          <CardContent>
            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
              <Box>
                <Typography variant="h6" gutterBottom>
                  Memory
                </Typography>
                <Typography variant="h3" fontWeight="bold">
                  {systemMetrics.memory.toFixed(1)}%
                </Typography>
              </Box>
              <Memory sx={{ fontSize: 40, opacity: 0.8 }} />
            </Box>
            <LinearProgress 
              variant="determinate" 
              value={systemMetrics.memory} 
              sx={{ 
                mt: 2, 
                height: 8, 
                borderRadius: 4,
                backgroundColor: 'rgba(255,255,255,0.3)',
                '& .MuiLinearProgress-bar': {
                  backgroundColor: 'white'
                }
              }} 
            />
          </CardContent>
        </Card>
      </motion.div>

      {/* Disk Usage */}
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5, delay: 0.2 }}
      >
        <Card sx={{ 
          background: 'linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)',
          color: 'white',
          minWidth: 250,
          flex: 1
        }}>
          <CardContent>
            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
              <Box>
                <Typography variant="h6" gutterBottom>
                  Disk Usage
                </Typography>
                <Typography variant="h3" fontWeight="bold">
                  {systemMetrics.disk.toFixed(1)}%
                </Typography>
              </Box>
              <Storage sx={{ fontSize: 40, opacity: 0.8 }} />
            </Box>
            <LinearProgress 
              variant="determinate" 
              value={systemMetrics.disk} 
              sx={{ 
                mt: 2, 
                height: 8, 
                borderRadius: 4,
                backgroundColor: 'rgba(255,255,255,0.3)',
                '& .MuiLinearProgress-bar': {
                  backgroundColor: 'white'
                }
              }} 
            />
          </CardContent>
        </Card>
      </motion.div>

      {/* Network Activity */}
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5, delay: 0.3 }}
      >
        <Card sx={{ 
          background: 'linear-gradient(135deg, #43e97b 0%, #38f9d7 100%)',
          color: 'white',
          minWidth: 250,
          flex: 1
        }}>
          <CardContent>
            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
              <Box>
                <Typography variant="h6" gutterBottom>
                  Network
                </Typography>
                <Typography variant="h3" fontWeight="bold">
                  {systemMetrics.network.toFixed(1)}%
                </Typography>
              </Box>
              <NetworkCheck sx={{ fontSize: 40, opacity: 0.8 }} />
            </Box>
            <LinearProgress 
              variant="determinate" 
              value={systemMetrics.network} 
              sx={{ 
                mt: 2, 
                height: 8, 
                borderRadius: 4,
                backgroundColor: 'rgba(255,255,255,0.3)',
                '& .MuiLinearProgress-bar': {
                  backgroundColor: 'white'
                }
              }} 
            />
          </CardContent>
        </Card>
      </motion.div>
    </Box>
  );

  const renderTerminalTabs = () => (
    <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
      <AdvancedTerminal
        token={token}
        user={user}
        isFullscreen={isFullscreen}
        onToggleFullscreen={() => setIsFullscreen(!isFullscreen)}
        sessionId={`session_${activeTerminal}`}
        onSessionChange={(sessionId) => {
          const sessionIndex = terminalSessions.findIndex(s => s.id === sessionId);
          if (sessionIndex !== -1) {
            setActiveTerminal(sessionIndex);
          }
        }}
      />
    </Card>
  );

  return (
    <Box sx={{ height: '100vh', display: 'flex', flexDirection: 'column', background: darkMode ? '#0d1117' : '#f5f5f5' }}>
      {/* Header */}
      <Paper sx={{ 
        p: 2, 
        background: darkMode ? 'linear-gradient(135deg, #1e3c72 0%, #2a5298 100%)' : 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        color: 'white',
        boxShadow: 3
      }}>
        <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <Box sx={{ display: 'flex', alignItems: 'center' }}>
            <Security sx={{ fontSize: 32, mr: 2 }} />
            <Box>
              <Typography variant="h4" fontWeight="bold">
                Stealer Web - Advanced Dashboard
              </Typography>
              <Typography variant="subtitle1">
                Professional Security Terminal Interface
              </Typography>
            </Box>
          </Box>
          
          <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
            <Chip 
              icon={<Shield />}
              label={`Security: ${securityStatus.toUpperCase()}`}
              color={securityStatus === 'secure' ? 'success' : 'error'}
              variant="outlined"
              sx={{ color: 'white', borderColor: 'white' }}
            />
            <Chip 
              icon={<Warning />}
              label={`Threat: ${threatLevel.toUpperCase()}`}
              color={getThreatColor(threatLevel) as any}
              variant="outlined"
              sx={{ color: 'white', borderColor: 'white' }}
            />
            
            <IconButton onClick={() => setDarkMode(!darkMode)} sx={{ color: 'white' }}>
              {darkMode ? <LightMode /> : <DarkMode />}
            </IconButton>
            
            <IconButton onClick={() => setShowSettings(true)} sx={{ color: 'white' }}>
              <Settings />
            </IconButton>
            
            <IconButton onClick={onDisconnect} sx={{ color: 'white' }}>
              <ExitToApp />
            </IconButton>
          </Box>
        </Box>
      </Paper>

      {/* Tabs */}
      <Box sx={{ borderBottom: 1, borderColor: 'divider', background: darkMode ? '#161b22' : 'white' }}>
        <Tabs 
          value={activeTab} 
          onChange={(_, newValue) => setActiveTab(newValue)}
          sx={{ px: 2 }}
        >
          <Tab icon={<DashboardIcon />} label="Dashboard" />
          <Tab icon={<Terminal />} label="Terminal" />
          <Tab icon={<Computer />} label="Processes" />
          <Tab icon={<FolderOpen />} label="Files" />
          <Tab icon={<NetworkCheck />} label="Network" />
          <Tab icon={<Analytics />} label="Analytics" />
          <Tab icon={<SecurityIcon />} label="Security" />
        </Tabs>
      </Box>

      {/* Content */}
      <Box sx={{ flex: 1, p: 3, overflow: 'auto' }}>
        <AnimatePresence mode="wait">
          {activeTab === 0 && (
            <motion.div
              key="dashboard"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: -20 }}
              transition={{ duration: 0.3 }}
            >
              {renderSystemMetrics()}
            </motion.div>
          )}
          
          {activeTab === 1 && (
            <motion.div
              key="terminal"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: -20 }}
              transition={{ duration: 0.3 }}
            >
              {renderTerminalTabs()}
            </motion.div>
          )}
        </AnimatePresence>
      </Box>

      {/* Settings Dialog */}
      <Dialog open={showSettings} onClose={() => setShowSettings(false)} maxWidth="md" fullWidth>
        <DialogTitle>Advanced Settings</DialogTitle>
        <DialogContent>
          <Box sx={{ pt: 2 }}>
            <FormControlLabel
              control={<Switch checked={autoRefresh} onChange={(e) => setAutoRefresh(e.target.checked)} />}
              label="Auto Refresh"
            />
            <Box sx={{ mt: 2 }}>
              <Typography gutterBottom>Refresh Interval (seconds)</Typography>
              <TextField
                type="number"
                value={refreshInterval}
                onChange={(e) => setRefreshInterval(Number(e.target.value))}
                inputProps={{ min: 1, max: 30 }}
                sx={{ width: 100 }}
              />
            </Box>
            <FormControlLabel
              control={<Switch checked={darkMode} onChange={(e) => setDarkMode(e.target.checked)} />}
              label="Dark Mode"
            />
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowSettings(false)}>Close</Button>
          <Button variant="contained" onClick={() => setShowSettings(false)}>Save</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default SimpleDashboard;