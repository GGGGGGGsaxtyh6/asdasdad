import React, { useEffect, useState, useRef } from 'react';
import {
  Box,
  Paper,
  Typography,
  Grid,
  Card,
  CardContent,
  IconButton,
  Chip,
  List,
  ListItem,
  ListItemText,
  ListItemIcon,
  Divider,
  LinearProgress,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
  Alert,
  Snackbar,
  Tooltip,
  Switch,
  FormControlLabel,
  Badge,
  CircularProgress,
  Tabs,
  Tab,
  Accordion,
  AccordionSummary,
  AccordionDetails,
  TablePagination
} from '@mui/material';
import {
  Dashboard as DashboardIcon,
  Terminal as TerminalIcon,
  Storage as StorageIcon,
  NetworkCheck as NetworkIcon,
  Memory as MemoryIcon,
  Computer as CpuIcon,
  Security as SecurityIcon,
  Folder as FolderIcon,
  Settings as SettingsIcon,
  Refresh as RefreshIcon,
  PlayArrow as PlayIcon,
  Stop as StopIcon,
  Download as DownloadIcon,
  Warning as WarningIcon,
  CheckCircle as CheckIcon,
  Error as ErrorIcon,
  Info as InfoIcon,
  ExpandMore as ExpandMoreIcon,
  Computer as ComputerIcon,
  Person as PersonIcon,
  Lock as LockIcon,
  Public as PublicIcon,
  Router as RouterIcon,
  Timeline as TimelineIcon,
  Assessment as AssessmentIcon,
  BugReport as BugReportIcon,
  Shield as ShieldIcon
} from '@mui/icons-material';
import { io, Socket } from 'socket.io-client';
import RealTerminal from './RealTerminal';
import { LineChart, Line, AreaChart, Area, BarChart, Bar, PieChart, Pie, Cell, XAxis, YAxis, CartesianGrid, Tooltip as RechartsTooltip, Legend, ResponsiveContainer } from 'recharts';

interface RealDashboardProps {
  user: any;
  token: string;
}

interface SystemInfo {
  cpu?: string;
  memory?: string;
  disk?: string;
  uptime?: string;
  load?: string;
}

interface ProcessData {
  user: string;
  pid: string;
  cpu: string;
  mem: string;
  command: string;
}

interface NetworkData {
  connections?: string;
  interfaces?: string;
  stats?: string;
}

interface FileData {
  directory: string;
  files: string[];
}

interface SecurityData {
  logged_users?: string;
  last_logins?: string;
  privileged_processes?: string;
  suid_files?: string;
}

const RealDashboard: React.FC<RealDashboardProps> = ({ user, token }) => {
  const [activeTab, setActiveTab] = useState(0);
  const [socket, setSocket] = useState<Socket | null>(null);
  const [isConnected, setIsConnected] = useState(false);
  const [systemInfo, setSystemInfo] = useState<SystemInfo>({});
  const [processes, setProcesses] = useState<ProcessData[]>([]);
  const [networkData, setNetworkData] = useState<NetworkData>({});
  const [files, setFiles] = useState<FileData>({ directory: '/', files: [] });
  const [securityData, setSecurityData] = useState<SecurityData>({});
  const [notifications, setNotifications] = useState<any[]>([]);
  const [autoRefresh, setAutoRefresh] = useState(true);
  const [refreshInterval, setRefreshInterval] = useState(5000);
  const [isTerminalFullscreen, setIsTerminalFullscreen] = useState(false);
  const [currentDirectory, setCurrentDirectory] = useState('/');
  const [showSettings, setShowSettings] = useState(false);
  const [showNotifications, setShowNotifications] = useState(false);

  // Conectar socket
  useEffect(() => {
    const socketInstance = io('https://d1ecf16241f3.ngrok-free.app', {
      transports: ['websocket'],
      upgrade: true,
      rememberUpgrade: true,
      timeout: 20000,
      forceNew: true
    });

    socketInstance.emit('authenticate', token);

    socketInstance.on('authenticated', () => {
      setIsConnected(true);
      addNotification('success', 'Connected', 'Successfully connected to server');
      
      // Cargar datos iniciales
      loadSystemInfo();
      loadProcesses();
      loadNetworkData();
      loadFiles('/');
      loadSecurityData();
    });

    socketInstance.on('systemInfo', (data) => {
      setSystemInfo(prev => ({
        ...prev,
        [data.type]: data.data
      }));
    });

    socketInstance.on('processesData', (data) => {
      if (data.type === 'processes') {
        const lines = data.data.split('\n').slice(1); // Skip header
        const processList = lines.map((line: string) => {
          const parts = line.trim().split(/\s+/);
          if (parts.length >= 11) {
            return {
              user: parts[0],
              pid: parts[1],
              cpu: parts[2],
              mem: parts[3],
              command: parts.slice(10).join(' ')
            };
          }
          return null;
        }).filter(Boolean) as ProcessData[];
        setProcesses(processList);
      }
    });

    socketInstance.on('networkData', (data) => {
      setNetworkData(prev => ({
        ...prev,
        [data.type]: data.data
      }));
    });

    socketInstance.on('filesData', (data) => {
      if (data.type === 'files') {
        const lines = data.data.split('\n');
        setFiles({
          directory: data.directory,
          files: lines.filter((line: string) => line.trim())
        });
      }
    });

    socketInstance.on('securityData', (data) => {
      setSecurityData(prev => ({
        ...prev,
        [data.type]: data.data
      }));
    });

    socketInstance.on('disconnect', () => {
      setIsConnected(false);
      addNotification('error', 'Disconnected', 'Lost connection to server');
    });

    setSocket(socketInstance);

    return () => {
      socketInstance.disconnect();
    };
  }, [token]);

  // Auto refresh
  useEffect(() => {
    if (!autoRefresh || !socket) return;

    const interval = setInterval(() => {
      if (activeTab === 0) loadSystemInfo();
      if (activeTab === 1) loadProcesses();
      if (activeTab === 2) loadNetworkData();
      if (activeTab === 3) loadFiles(currentDirectory);
      if (activeTab === 4) loadSecurityData();
    }, refreshInterval);

    return () => clearInterval(interval);
  }, [autoRefresh, refreshInterval, activeTab, currentDirectory, socket]);

  const addNotification = (type: 'success' | 'error' | 'warning' | 'info', title: string, message: string) => {
    const notification = {
      id: Date.now(),
      type,
      title,
      message,
      timestamp: new Date().toISOString()
    };
    setNotifications(prev => [notification, ...prev.slice(0, 9)]);
  };

  const loadSystemInfo = () => {
    if (socket) {
      socket.emit('getSystemInfo');
    }
  };

  const loadProcesses = () => {
    if (socket) {
      socket.emit('getProcesses');
    }
  };

  const loadNetworkData = () => {
    if (socket) {
      socket.emit('getNetworkConnections');
    }
  };

  const loadFiles = (directory: string) => {
    if (socket) {
      socket.emit('getFiles', { directory });
      setCurrentDirectory(directory);
    }
  };

  const loadSecurityData = () => {
    if (socket) {
      socket.emit('getSecurityInfo');
    }
  };

  const refreshAll = () => {
    loadSystemInfo();
    loadProcesses();
    loadNetworkData();
    loadFiles(currentDirectory);
    loadSecurityData();
    addNotification('info', 'Refreshed', 'All data refreshed');
  };

  const exportData = () => {
    const data = {
      timestamp: new Date().toISOString(),
      user: user.username,
      systemInfo,
      processes,
      networkData,
      files,
      securityData
    };
    
    const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `system-data-${Date.now()}.json`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
    
    addNotification('success', 'Exported', 'System data exported successfully');
  };

  const renderSystemInfo = () => (
    <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(400px, 1fr))', gap: 3 }}>
      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <CpuIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            CPU Information
          </Typography>
          <Box sx={{ mt: 2 }}>
            {systemInfo.cpu ? (
              <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                {systemInfo.cpu}
              </Typography>
            ) : (
              <CircularProgress size={20} />
            )}
          </Box>
        </CardContent>
      </Card>

      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <MemoryIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            Memory Usage
          </Typography>
          <Box sx={{ mt: 2 }}>
            {systemInfo.memory ? (
              <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                {systemInfo.memory}
              </Typography>
            ) : (
              <CircularProgress size={20} />
            )}
          </Box>
        </CardContent>
      </Card>

      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <StorageIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            Disk Usage
          </Typography>
          <Box sx={{ mt: 2 }}>
            {systemInfo.disk ? (
              <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                {systemInfo.disk}
              </Typography>
            ) : (
              <CircularProgress size={20} />
            )}
          </Box>
        </CardContent>
      </Card>

      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <TimelineIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            System Uptime & Load
          </Typography>
          <Box sx={{ mt: 2 }}>
            {systemInfo.uptime && (
              <Box sx={{ mb: 2 }}>
                <Typography variant="body2" sx={{ fontFamily: 'monospace' }}>
                  Uptime: {systemInfo.uptime}
                </Typography>
              </Box>
            )}
            {systemInfo.load && (
              <Typography variant="body2" sx={{ fontFamily: 'monospace' }}>
                Load: {systemInfo.load}
              </Typography>
            )}
          </Box>
        </CardContent>
      </Card>
    </Box>
  );

  const renderProcesses = () => (
    <Card>
      <CardContent>
        <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
          <Typography variant="h6">
            <BugReportIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            Running Processes (Top 50)
          </Typography>
          <IconButton onClick={loadProcesses}>
            <RefreshIcon />
          </IconButton>
        </Box>
        
        <TableContainer>
          <Table size="small">
            <TableHead>
              <TableRow>
                <TableCell>User</TableCell>
                <TableCell>PID</TableCell>
                <TableCell>CPU%</TableCell>
                <TableCell>Memory%</TableCell>
                <TableCell>Command</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {processes.map((process, index) => (
                <TableRow key={index}>
                  <TableCell>{process.user}</TableCell>
                  <TableCell>{process.pid}</TableCell>
                  <TableCell>
                    <Chip 
                      label={`${process.cpu}%`} 
                      color={parseFloat(process.cpu) > 50 ? 'error' : parseFloat(process.cpu) > 25 ? 'warning' : 'success'}
                      size="small"
                    />
                  </TableCell>
                  <TableCell>
                    <Chip 
                      label={`${process.mem}%`} 
                      color={parseFloat(process.mem) > 50 ? 'error' : parseFloat(process.mem) > 25 ? 'warning' : 'success'}
                      size="small"
                    />
                  </TableCell>
                  <TableCell sx={{ fontFamily: 'monospace', fontSize: '0.8rem' }}>
                    {process.command.length > 50 ? `${process.command.substring(0, 50)}...` : process.command}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </CardContent>
    </Card>
  );

  const renderNetwork = () => (
    <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(400px, 1fr))', gap: 3 }}>
      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <NetworkIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            Network Connections
          </Typography>
          <Box sx={{ mt: 2, maxHeight: 400, overflow: 'auto' }}>
            {networkData.connections ? (
              <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                {networkData.connections}
              </Typography>
            ) : (
              <CircularProgress size={20} />
            )}
          </Box>
        </CardContent>
      </Card>

      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <RouterIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            Network Interfaces
          </Typography>
          <Box sx={{ mt: 2, maxHeight: 300, overflow: 'auto' }}>
            {networkData.interfaces ? (
              <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                {networkData.interfaces}
              </Typography>
            ) : (
              <CircularProgress size={20} />
            )}
          </Box>
        </CardContent>
      </Card>

      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <AssessmentIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            Network Statistics
          </Typography>
          <Box sx={{ mt: 2, maxHeight: 300, overflow: 'auto' }}>
            {networkData.stats ? (
              <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                {networkData.stats}
              </Typography>
            ) : (
              <CircularProgress size={20} />
            )}
          </Box>
        </CardContent>
      </Card>
    </Box>
  );

  const renderFiles = () => (
    <Card>
      <CardContent>
        <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
          <Typography variant="h6">
            <FolderIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            File System Explorer
          </Typography>
          <Box>
            <TextField
              size="small"
              label="Directory"
              value={currentDirectory}
              onChange={(e) => setCurrentDirectory(e.target.value)}
              onKeyPress={(e) => {
                if (e.key === 'Enter') {
                  loadFiles(currentDirectory);
                }
              }}
              sx={{ mr: 2, minWidth: 300 }}
            />
            <Button variant="contained" onClick={() => loadFiles(currentDirectory)}>
              Navigate
            </Button>
          </Box>
        </Box>
        
        <Box sx={{ mt: 2, maxHeight: 400, overflow: 'auto' }}>
          {files.files.length > 0 ? (
            <List>
              {files.files.map((file, index) => (
                <ListItem 
                  key={index}
                  component="button"
                  sx={{ cursor: 'pointer' }}
                  onClick={() => {
                    if (file.startsWith('d')) {
                      const parts = file.split(/\s+/);
                      const dirName = parts[parts.length - 1];
                      const newPath = currentDirectory === '/' ? `/${dirName}` : `${currentDirectory}/${dirName}`;
                      loadFiles(newPath);
                    }
                  }}
                >
                  <ListItemIcon>
                    <FolderIcon color={file.startsWith('d') ? 'primary' : 'action'} />
                  </ListItemIcon>
                  <ListItemText
                    primary={file}
                    sx={{ fontFamily: 'monospace', fontSize: '0.9rem' }}
                  />
                </ListItem>
              ))}
            </List>
          ) : (
            <CircularProgress size={20} />
          )}
        </Box>
      </CardContent>
    </Card>
  );

  const renderSecurity = () => (
    <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(400px, 1fr))', gap: 3 }}>
      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <PersonIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            Logged Users
          </Typography>
          <Box sx={{ mt: 2, maxHeight: 200, overflow: 'auto' }}>
            {securityData.logged_users ? (
              <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                {securityData.logged_users}
              </Typography>
            ) : (
              <CircularProgress size={20} />
            )}
          </Box>
        </CardContent>
      </Card>

      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <SecurityIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            Last Logins
          </Typography>
          <Box sx={{ mt: 2, maxHeight: 200, overflow: 'auto' }}>
            {securityData.last_logins ? (
              <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                {securityData.last_logins}
              </Typography>
            ) : (
              <CircularProgress size={20} />
            )}
          </Box>
        </CardContent>
      </Card>

      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <LockIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            Privileged Processes
          </Typography>
          <Box sx={{ mt: 2, maxHeight: 200, overflow: 'auto' }}>
            {securityData.privileged_processes ? (
              <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                {securityData.privileged_processes}
              </Typography>
            ) : (
              <CircularProgress size={20} />
            )}
          </Box>
        </CardContent>
      </Card>

      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            <ShieldIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            SUID Files
          </Typography>
          <Box sx={{ mt: 2, maxHeight: 200, overflow: 'auto' }}>
            {securityData.suid_files ? (
              <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                {securityData.suid_files}
              </Typography>
            ) : (
              <CircularProgress size={20} />
            )}
          </Box>
        </CardContent>
      </Card>
    </Box>
  );

  const renderTerminal = () => (
    <RealTerminal
      token={token}
      user={user}
      isFullscreen={isTerminalFullscreen}
      onToggleFullscreen={() => setIsTerminalFullscreen(!isTerminalFullscreen)}
    />
  );

  if (isTerminalFullscreen) {
    return renderTerminal();
  }

  return (
    <Box sx={{ height: '100vh', display: 'flex', flexDirection: 'column' }}>
      {/* Header */}
      <Paper sx={{ 
        p: 2, 
        background: 'linear-gradient(135deg, #1e3c72 0%, #2a5298 100%)',
        color: 'white',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between'
      }}>
        <Box display="flex" alignItems="center" gap={2}>
          <DashboardIcon sx={{ fontSize: 32 }} />
          <Box>
            <Typography variant="h4" fontWeight="bold">
              Real System Dashboard
            </Typography>
            <Typography variant="subtitle1">
              Professional Security Interface - Real System Access
            </Typography>
          </Box>
        </Box>
        
        <Box display="flex" alignItems="center" gap={2}>
          <Chip 
            icon={isConnected ? <CheckIcon /> : <ErrorIcon />}
            label={isConnected ? 'Connected' : 'Disconnected'}
            color={isConnected ? 'success' : 'error'}
          />
          <IconButton onClick={refreshAll} sx={{ color: 'white' }}>
            <RefreshIcon />
          </IconButton>
          <IconButton onClick={exportData} sx={{ color: 'white' }}>
            <DownloadIcon />
          </IconButton>
          <IconButton onClick={() => setShowSettings(true)} sx={{ color: 'white' }}>
            <SettingsIcon />
          </IconButton>
        </Box>
      </Paper>

      {/* Tabs */}
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={activeTab} onChange={(_, newValue) => setActiveTab(newValue)}>
          <Tab icon={<ComputerIcon />} label="System Info" />
          <Tab icon={<BugReportIcon />} label="Processes" />
          <Tab icon={<NetworkIcon />} label="Network" />
          <Tab icon={<FolderIcon />} label="Files" />
          <Tab icon={<SecurityIcon />} label="Security" />
          <Tab icon={<TerminalIcon />} label="Terminal" />
        </Tabs>
      </Box>

      {/* Content */}
      <Box sx={{ flex: 1, p: 3, overflow: 'auto' }}>
        {activeTab === 0 && renderSystemInfo()}
        {activeTab === 1 && renderProcesses()}
        {activeTab === 2 && renderNetwork()}
        {activeTab === 3 && renderFiles()}
        {activeTab === 4 && renderSecurity()}
        {activeTab === 5 && renderTerminal()}
      </Box>

      {/* Settings Dialog */}
      <Dialog open={showSettings} onClose={() => setShowSettings(false)} maxWidth="md" fullWidth>
        <DialogTitle>Dashboard Settings</DialogTitle>
        <DialogContent>
          <Box sx={{ pt: 2 }}>
            <FormControlLabel
              control={<Switch checked={autoRefresh} onChange={(e) => setAutoRefresh(e.target.checked)} />}
              label="Auto Refresh"
            />
            <Box sx={{ mt: 2 }}>
              <TextField
                label="Refresh Interval (ms)"
                type="number"
                value={refreshInterval}
                onChange={(e) => setRefreshInterval(Number(e.target.value))}
                size="small"
                sx={{ width: 200 }}
              />
            </Box>
            <Box sx={{ mt: 2 }}>
              <Alert severity="warning">
                <Typography variant="body2">
                  <strong>Real System Access:</strong> This dashboard shows real system data.
                  All commands are executed on the actual system. Use responsibly.
                </Typography>
              </Alert>
            </Box>
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowSettings(false)}>Close</Button>
          <Button variant="contained" onClick={() => setShowSettings(false)}>Save</Button>
        </DialogActions>
      </Dialog>

      {/* Notifications */}
      <Snackbar
        open={showNotifications}
        autoHideDuration={6000}
        onClose={() => setShowNotifications(false)}
        anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
      >
        <Box>
          {notifications.slice(0, 3).map((notification) => (
            <Alert 
              key={notification.id} 
              severity={notification.type as any}
              sx={{ mb: 1 }}
            >
              <Typography variant="subtitle2">{notification.title}</Typography>
              <Typography variant="body2">{notification.message}</Typography>
            </Alert>
          ))}
        </Box>
      </Snackbar>
    </Box>
  );
};

export default RealDashboard;