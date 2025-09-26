import React, { useState, useEffect } from 'react';
import {
  Box,
  Paper,
  Typography,
  Card,
  CardContent,
  Button,
  Chip,
  IconButton,
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
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TablePagination,
  List,
  ListItem,
  ListItemText,
  ListItemIcon,
  Divider
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
  Add,
  PlayArrow,
  Pause,
  Stop,
  Fullscreen,
  FullscreenExit,
  DarkMode,
  LightMode,
  ExitToApp,
  Dashboard as DashboardIcon,
  Computer,
  Analytics,
  Timeline,
  ShowChart,
  BarChart,
  PieChart,
  TableChart,
  ExpandMore,
  ExpandLess,
  FilterList,
  Search,
  Sort,
  MoreVert,
  Visibility,
  VisibilityOff,
  Lock,
  LockOpen,
  Shield,
  Security as SecurityIcon,
  MonitorHeart,
  DeveloperMode,
  Code,
  Build,
  Extension,
  Api,
  Webhook,
  CloudSync,
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
  Thermostat,
  AcUnit,
  Whatshot,
  WbSunny,
  WbCloudy,
  Cloud,
  CloudDownload,
  CloudUpload
} from '@mui/icons-material';
import { motion, AnimatePresence } from 'framer-motion';
import { useHotkeys } from 'react-hotkeys-hook';
import WorkingTerminal from './WorkingTerminal';

interface FullDashboardProps {
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

interface ProcessInfo {
  pid: number;
  name: string;
  cpu: number;
  memory: number;
  status: string;
  user: string;
  command: string;
}

interface FileInfo {
  name: string;
  size: string;
  type: string;
  modified: string;
  permissions: string;
  path: string;
}

interface NetworkConnection {
  id: number;
  protocol: string;
  local: string;
  remote: string;
  status: string;
  pid: number;
  program: string;
}

const FullDashboard: React.FC<FullDashboardProps> = ({ token, user, onDisconnect }) => {
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
  const [processes, setProcesses] = useState<ProcessInfo[]>([]);
  const [files, setFiles] = useState<FileInfo[]>([]);
  const [networkConnections, setNetworkConnections] = useState<NetworkConnection[]>([]);
  const [isFullscreen, setIsFullscreen] = useState(false);
  const [showSettings, setShowSettings] = useState(false);
  const [securityStatus, setSecurityStatus] = useState('secure');
  const [threatLevel, setThreatLevel] = useState('low');
  const [processPage, setProcessPage] = useState(0);
  const [processRowsPerPage, setProcessRowsPerPage] = useState(10);
  const [notifications, setNotifications] = useState<any[]>([]);

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
        // Simular métricas del sistema
        setSystemMetrics(prev => ({
          cpu: Math.random() * 100,
          memory: Math.random() * 100,
          disk: Math.random() * 100,
          network: Math.random() * 100,
          processes: Math.floor(Math.random() * 200) + 50,
          connections: Math.floor(Math.random() * 50) + 10,
          uptime: `${Math.floor(Math.random() * 30)}d ${Math.floor(Math.random() * 24)}h ${Math.floor(Math.random() * 60)}m`,
          temperature: Math.random() * 20 + 40
        }));

        // Simular procesos
        const processNames = ['node', 'chrome', 'firefox', 'code', 'bash', 'python', 'java', 'docker', 'nginx', 'mysql'];
        const newProcesses: ProcessInfo[] = Array.from({ length: 25 }, (_, i) => ({
          pid: Math.floor(Math.random() * 10000) + 1000,
          name: processNames[Math.floor(Math.random() * processNames.length)],
          cpu: Math.random() * 100,
          memory: Math.random() * 100,
          status: ['running', 'sleeping', 'zombie', 'stopped'][Math.floor(Math.random() * 4)],
          user: ['root', 'user', 'admin', 'www-data'][Math.floor(Math.random() * 4)],
          command: `/usr/bin/${processNames[Math.floor(Math.random() * processNames.length)]}`
        }));
        setProcesses(newProcesses);

        // Simular archivos
        const fileExtensions = ['txt', 'js', 'py', 'json', 'md', 'log', 'conf', 'xml'];
        const newFiles: FileInfo[] = Array.from({ length: 20 }, (_, i) => ({
          name: `file_${i + 1}.${fileExtensions[Math.floor(Math.random() * fileExtensions.length)]}`,
          size: `${Math.floor(Math.random() * 1000)}KB`,
          type: Math.random() > 0.3 ? 'file' : 'directory',
          modified: new Date(Date.now() - Math.random() * 86400000).toLocaleString(),
          permissions: 'rwxr-xr-x',
          path: `/home/${user.username}/documents/file_${i + 1}`
        }));
        setFiles(newFiles);

        // Simular conexiones de red
        const protocols = ['TCP', 'UDP', 'HTTP', 'HTTPS', 'SSH', 'FTP'];
        const statuses = ['ESTABLISHED', 'LISTEN', 'TIME_WAIT', 'CLOSE_WAIT'];
        const programs = ['node', 'nginx', 'ssh', 'mysql', 'apache2'];
        
        const newConnections: NetworkConnection[] = Array.from({ length: 15 }, (_, i) => ({
          id: i,
          protocol: protocols[Math.floor(Math.random() * protocols.length)],
          local: `192.168.1.${Math.floor(Math.random() * 254) + 1}:${Math.floor(Math.random() * 65535)}`,
          remote: `10.0.${Math.floor(Math.random() * 254) + 1}.${Math.floor(Math.random() * 254) + 1}:${Math.floor(Math.random() * 65535)}`,
          status: statuses[Math.floor(Math.random() * statuses.length)],
          pid: Math.floor(Math.random() * 1000) + 1000,
          program: programs[Math.floor(Math.random() * programs.length)]
        }));
        setNetworkConnections(newConnections);
      }
    }, refreshInterval * 1000);

    return () => clearInterval(interval);
  }, [autoRefresh, refreshInterval, user.username]);

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'running': return 'success';
      case 'sleeping': return 'info';
      case 'zombie': return 'error';
      case 'stopped': return 'warning';
      default: return 'default';
    }
  };

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

  const renderProcesses = () => (
    <Card>
      <CardContent>
        <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
          <Typography variant="h6">
            Running Processes ({processes.length})
          </Typography>
          <Box>
            <IconButton onClick={() => setProcessPage(0)}>
              <Refresh />
            </IconButton>
            <IconButton onClick={() => setShowSettings(true)}>
              <Settings />
            </IconButton>
          </Box>
        </Box>
        
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>PID</TableCell>
                <TableCell>Name</TableCell>
                <TableCell>CPU %</TableCell>
                <TableCell>Memory %</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>User</TableCell>
                <TableCell>Command</TableCell>
                <TableCell>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {processes
                .slice(processPage * processRowsPerPage, processPage * processRowsPerPage + processRowsPerPage)
                .map((process) => (
                  <TableRow key={process.pid} hover>
                    <TableCell>{process.pid}</TableCell>
                    <TableCell>
                      <Box display="flex" alignItems="center">
                        <Computer sx={{ mr: 1, fontSize: 20 }} />
                        {process.name}
                      </Box>
                    </TableCell>
                    <TableCell>
                      <Box display="flex" alignItems="center">
                        <LinearProgress 
                          variant="determinate" 
                          value={process.cpu} 
                          sx={{ width: 60, mr: 1 }}
                        />
                        {process.cpu.toFixed(1)}%
                      </Box>
                    </TableCell>
                    <TableCell>
                      <Box display="flex" alignItems="center">
                        <LinearProgress 
                          variant="determinate" 
                          value={process.memory} 
                          sx={{ width: 60, mr: 1 }}
                        />
                        {process.memory.toFixed(1)}%
                      </Box>
                    </TableCell>
                    <TableCell>
                      <Chip 
                        label={process.status} 
                        color={getStatusColor(process.status) as any}
                        size="small"
                      />
                    </TableCell>
                    <TableCell>{process.user}</TableCell>
                    <TableCell>
                      <Typography variant="body2" noWrap sx={{ maxWidth: 200 }}>
                        {process.command}
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <IconButton size="small">
                        <MoreVert />
                      </IconButton>
                    </TableCell>
                  </TableRow>
                ))}
            </TableBody>
          </Table>
        </TableContainer>
        
        <TablePagination
          rowsPerPageOptions={[5, 10, 25]}
          component="div"
          count={processes.length}
          rowsPerPage={processRowsPerPage}
          page={processPage}
          onPageChange={(_, newPage) => setProcessPage(newPage)}
          onRowsPerPageChange={(e) => {
            setProcessRowsPerPage(parseInt(e.target.value));
            setProcessPage(0);
          }}
        />
      </CardContent>
    </Card>
  );

  const renderFileManager = () => (
    <Box sx={{ display: 'flex', gap: 3, height: '100%' }}>
      <Card sx={{ flex: 1 }}>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            File Explorer
          </Typography>
          <List>
            {files.map((file, index) => (
              <ListItem key={index} component="button" sx={{ cursor: 'pointer' }}>
                <ListItemIcon>
                  {file.type === 'directory' ? <FolderOpen /> : <Storage />}
                </ListItemIcon>
                <ListItemText 
                  primary={file.name}
                  secondary={`${file.size} • ${file.modified}`}
                />
                <IconButton size="small">
                  <MoreVert />
                </IconButton>
              </ListItem>
            ))}
          </List>
        </CardContent>
      </Card>
      
      <Card sx={{ flex: 1 }}>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            Network Connections
          </Typography>
          <List>
            {networkConnections.slice(0, 10).map((conn) => (
              <ListItem key={conn.id}>
                <ListItemIcon>
                  <NetworkCheck />
                </ListItemIcon>
                <ListItemText 
                  primary={`${conn.protocol} ${conn.local}`}
                  secondary={`→ ${conn.remote} (${conn.status}) • ${conn.program}`}
                />
                <Chip 
                  label={conn.status} 
                  size="small"
                  color={conn.status === 'ESTABLISHED' ? 'success' : 'default'}
                />
              </ListItem>
            ))}
          </List>
        </CardContent>
      </Card>
    </Box>
  );

  const renderAnalytics = () => (
    <Card>
      <CardContent>
        <Typography variant="h6" gutterBottom>
          System Analytics
        </Typography>
        <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap' }}>
          <Card sx={{ flex: 1, minWidth: 200 }}>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                System Information
              </Typography>
              <Box sx={{ display: 'flex', flexDirection: 'column', gap: 1 }}>
                <Typography variant="body2">
                  <strong>Uptime:</strong> {systemMetrics.uptime}
                </Typography>
                <Typography variant="body2">
                  <strong>Processes:</strong> {systemMetrics.processes}
                </Typography>
                <Typography variant="body2">
                  <strong>Connections:</strong> {systemMetrics.connections}
                </Typography>
                <Typography variant="body2">
                  <strong>Temperature:</strong> {systemMetrics.temperature.toFixed(1)}°C
                </Typography>
              </Box>
            </CardContent>
          </Card>
          
          <Card sx={{ flex: 1, minWidth: 200 }}>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                Security Status
              </Typography>
              <Box sx={{ display: 'flex', flexDirection: 'column', gap: 1 }}>
                <Chip 
                  icon={<Shield />}
                  label={`Security: ${securityStatus.toUpperCase()}`}
                  color={securityStatus === 'secure' ? 'success' : 'error'}
                />
                <Chip 
                  icon={<Warning />}
                  label={`Threat: ${threatLevel.toUpperCase()}`}
                  color={getThreatColor(threatLevel) as any}
                />
                <Typography variant="body2" sx={{ mt: 1 }}>
                  Last scan: {new Date().toLocaleString()}
                </Typography>
              </Box>
            </CardContent>
          </Card>
        </Box>
      </CardContent>
    </Card>
  );

  const renderTerminal = () => (
    <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
      <WorkingTerminal
        token={token}
        user={user}
        isFullscreen={isFullscreen}
        onToggleFullscreen={() => setIsFullscreen(!isFullscreen)}
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
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Box display="flex" alignItems="center">
            <Security sx={{ fontSize: 32, mr: 2 }} />
            <Box>
              <Typography variant="h4" fontWeight="bold">
                Stealer Web - Full Dashboard
              </Typography>
              <Typography variant="subtitle1">
                Professional Security Terminal Interface
              </Typography>
            </Box>
          </Box>
          
          <Box display="flex" alignItems="center" gap={2}>
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
              {renderTerminal()}
            </motion.div>
          )}
          
          {activeTab === 2 && (
            <motion.div
              key="processes"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: -20 }}
              transition={{ duration: 0.3 }}
            >
              {renderProcesses()}
            </motion.div>
          )}
          
          {activeTab === 3 && (
            <motion.div
              key="files"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: -20 }}
              transition={{ duration: 0.3 }}
            >
              {renderFileManager()}
            </motion.div>
          )}
          
          {activeTab === 4 && (
            <motion.div
              key="network"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: -20 }}
              transition={{ duration: 0.3 }}
            >
              {renderFileManager()}
            </motion.div>
          )}
          
          {activeTab === 5 && (
            <motion.div
              key="analytics"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: -20 }}
              transition={{ duration: 0.3 }}
            >
              {renderAnalytics()}
            </motion.div>
          )}
          
          {activeTab === 6 && (
            <motion.div
              key="security"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: -20 }}
              transition={{ duration: 0.3 }}
            >
              {renderAnalytics()}
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
            <Box sx={{ mt: 2 }}>
              <Typography gutterBottom>Security Status</Typography>
              <TextField
                select
                value={securityStatus}
                onChange={(e) => setSecurityStatus(e.target.value)}
                sx={{ width: 200 }}
              >
                <option value="secure">Secure</option>
                <option value="warning">Warning</option>
                <option value="danger">Danger</option>
              </TextField>
            </Box>
            <Box sx={{ mt: 2 }}>
              <Typography gutterBottom>Threat Level</Typography>
              <TextField
                select
                value={threatLevel}
                onChange={(e) => setThreatLevel(e.target.value)}
                sx={{ width: 200 }}
              >
                <option value="low">Low</option>
                <option value="medium">Medium</option>
                <option value="high">High</option>
              </TextField>
            </Box>
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

export default FullDashboard;