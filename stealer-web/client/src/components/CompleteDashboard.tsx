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
  CircularProgress,
  Tabs,
  Tab
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
  Download as DownloadIcon,
  CheckCircle as CheckIcon,
  Error as ErrorIcon,
  BugReport as BugReportIcon,
  Person as PersonIcon,
  Lock as LockIcon,
  Shield as ShieldIcon,
  Timeline as TimelineIcon
} from '@mui/icons-material';
import { io, Socket } from 'socket.io-client';
import ProfessionalTerminal from './ProfessionalTerminal';

interface CompleteDashboardProps {
  user: any;
  token: string;
}

const CompleteDashboard: React.FC<CompleteDashboardProps> = ({ user, token }) => {
  const [activeTab, setActiveTab] = useState(0);
  const [socket, setSocket] = useState<Socket | null>(null);
  const [isConnected, setIsConnected] = useState(false);
  const [systemInfo, setSystemInfo] = useState<any>({});
  const [processes, setProcesses] = useState<any[]>([]);
  const [networkData, setNetworkData] = useState<any>({});
  const [files, setFiles] = useState<any>({ directory: '/', files: [] });
  const [securityData, setSecurityData] = useState<any>({});
  const [notifications, setNotifications] = useState<any[]>([]);
  const [autoRefresh, setAutoRefresh] = useState(true);
  const [refreshInterval, setRefreshInterval] = useState(5000);
  const [isTerminalFullscreen, setIsTerminalFullscreen] = useState(false);
  const [currentDirectory, setCurrentDirectory] = useState('/');
  const [showSettings, setShowSettings] = useState(false);
  const [realTimeMetrics, setRealTimeMetrics] = useState<any>({});

  // Conectar socket
  useEffect(() => {
    const socketInstance = io('https://7c2def2c86ac.ngrok-free.app/metrics', {
      transports: ['websocket'],
      upgrade: true,
      rememberUpgrade: true,
      timeout: 20000,
      forceNew: true
    });

    socketInstance.emit('authenticate', token);

    socketInstance.on('authenticated', () => {
      setIsConnected(true);
      addNotification('success', 'Connected', 'Successfully connected to metrics server');
      loadSystemInfo();
      loadProcesses();
      loadNetworkData();
      loadFiles('/');
      loadSecurityData();
    });

    socketInstance.on('systemMetrics', (data) => {
      setRealTimeMetrics(prev => ({
        ...prev,
        [data.type]: data.data
      }));
    });

    socketInstance.on('metricsUpdate', (data) => {
      setRealTimeMetrics(prev => ({
        ...prev,
        [data.type]: data.data,
        timestamp: new Date().toISOString()
      }));
    });

    socketInstance.on('disconnect', () => {
      setIsConnected(false);
      addNotification('error', 'Disconnected', 'Lost connection to metrics server');
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
      socket.emit('requestMetrics', { type: 'system' });
    }
  };

  const loadProcesses = () => {
    if (socket) {
      socket.emit('requestMetrics', { type: 'processes' });
    }
  };

  const loadNetworkData = () => {
    if (socket) {
      socket.emit('requestMetrics', { type: 'network' });
    }
  };

  const loadFiles = (directory: string) => {
    if (socket) {
      socket.emit('requestMetrics', { type: 'files', directory });
      setCurrentDirectory(directory);
    }
  };

  const loadSecurityData = () => {
    if (socket) {
      socket.emit('requestMetrics', { type: 'security' });
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
      systemInfo: realTimeMetrics,
      notifications
    };
    
    const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `complete-dashboard-${Date.now()}.json`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
    
    addNotification('success', 'Exported', 'Dashboard data exported successfully');
  };

  const renderHeader = () => (
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
            Complete System Dashboard
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
  );

  const renderSystemInfo = () => (
    <Grid container spacing={3}>
      <Grid item xs={12} md={6}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <CpuIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              CPU Information
            </Typography>
            <Box sx={{ mt: 2 }}>
              {realTimeMetrics.cpu ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.cpu}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>

      <Grid item xs={12} md={6}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <MemoryIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              Memory Usage
            </Typography>
            <Box sx={{ mt: 2 }}>
              {realTimeMetrics.memory ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.memory}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>

      <Grid item xs={12} md={6}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <StorageIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              Disk Usage
            </Typography>
            <Box sx={{ mt: 2 }}>
              {realTimeMetrics.disk ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.disk}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>

      <Grid item xs={12} md={6}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <TimelineIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              System Uptime & Load
            </Typography>
            <Box sx={{ mt: 2 }}>
              {realTimeMetrics.uptime && (
                <Box sx={{ mb: 2 }}>
                  <Typography variant="body2" sx={{ fontFamily: 'monospace' }}>
                    Uptime: {realTimeMetrics.uptime}
                  </Typography>
                </Box>
              )}
              {realTimeMetrics.load && (
                <Typography variant="body2" sx={{ fontFamily: 'monospace' }}>
                  Load: {realTimeMetrics.load}
                </Typography>
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>
    </Grid>
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
              {(realTimeMetrics.processes || []).slice(0, 50).map((process: any, index: number) => (
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
    <Grid container spacing={3}>
      <Grid item xs={12}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <NetworkIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              Network Connections
            </Typography>
            <Box sx={{ mt: 2, maxHeight: 400, overflow: 'auto' }}>
              {realTimeMetrics.network?.connections ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.network.connections}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>

      <Grid item xs={12} md={6}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <NetworkIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              Network Interfaces
            </Typography>
            <Box sx={{ mt: 2, maxHeight: 300, overflow: 'auto' }}>
              {realTimeMetrics.network?.interfaces ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.network.interfaces}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>

      <Grid item xs={12} md={6}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <NetworkIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              Network Statistics
            </Typography>
            <Box sx={{ mt: 2, maxHeight: 300, overflow: 'auto' }}>
              {realTimeMetrics.network?.stats ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.network.stats}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>
    </Grid>
  );

  const renderFiles = () => (
    <Grid container spacing={3}>
      <Grid item xs={12}>
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
              {realTimeMetrics.files?.length > 0 ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.files.join('\n')}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>
    </Grid>
  );

  const renderSecurity = () => (
    <Grid container spacing={3}>
      <Grid item xs={12} md={6}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <PersonIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              Logged Users
            </Typography>
            <Box sx={{ mt: 2, maxHeight: 200, overflow: 'auto' }}>
              {realTimeMetrics.security?.logged_users ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.security.logged_users}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>

      <Grid item xs={12} md={6}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <SecurityIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              Last Logins
            </Typography>
            <Box sx={{ mt: 2, maxHeight: 200, overflow: 'auto' }}>
              {realTimeMetrics.security?.last_logins ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.security.last_logins}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>

      <Grid item xs={12} md={6}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <LockIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              Privileged Processes
            </Typography>
            <Box sx={{ mt: 2, maxHeight: 200, overflow: 'auto' }}>
              {realTimeMetrics.security?.privileged_processes ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.security.privileged_processes}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>

      <Grid item xs={12} md={6}>
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              <ShieldIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              SUID Files
            </Typography>
            <Box sx={{ mt: 2, maxHeight: 200, overflow: 'auto' }}>
              {realTimeMetrics.security?.suid_files ? (
                <Typography variant="body2" sx={{ fontFamily: 'monospace', whiteSpace: 'pre-wrap' }}>
                  {realTimeMetrics.security.suid_files}
                </Typography>
              ) : (
                <CircularProgress size={20} />
              )}
            </Box>
          </CardContent>
        </Card>
      </Grid>
    </Grid>
  );

  const renderTerminal = () => (
    <ProfessionalTerminal
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
      {renderHeader()}

      {/* Tabs */}
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={activeTab} onChange={(_, newValue) => setActiveTab(newValue)}>
          <Tab icon={<CpuIcon />} label="System Info" />
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
                  <strong>Complete System Access:</strong> This dashboard shows real system data.
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
        open={notifications.length > 0}
        autoHideDuration={6000}
        onClose={() => setNotifications([])}
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

export default CompleteDashboard;