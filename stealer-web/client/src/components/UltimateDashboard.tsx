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
  TablePagination,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Slider,
  Menu,
  Avatar,
  ListItemAvatar,
  ListItemSecondaryAction
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
  Shield as ShieldIcon,
  Timeline as TimelineIcon2,
  Speed as SpeedIcon,
  Storage as StorageIcon2,
  NetworkCheck as NetworkIcon2,
  Security as SecurityIcon2,
  Analytics as AnalyticsIcon,
  TrendingUp as TrendingUpIcon,
  TrendingDown as TrendingDownIcon,
  Equalizer as EqualizerIcon,
  ShowChart as ShowChartIcon,
  PieChart as PieChartIcon,
  BarChart as BarChartIcon,
  LineChart as LineChartIcon,
  TableChart as TableChartIcon,
  CloudUpload as CloudUploadIcon,
  CloudDownload as CloudDownloadIcon,
  Schedule as ScheduleIcon,
  Event as EventIcon,
  Notifications as NotificationsIcon,
  NotificationsActive as NotificationsActiveIcon,
  NotificationsOff as NotificationsOffIcon,
  FilterList as FilterListIcon,
  Search as SearchIcon,
  Sort as SortIcon,
  ViewList as ViewListIcon,
  ViewModule as ViewModuleIcon,
  ViewComfy as ViewComfyIcon,
  Fullscreen as FullscreenIcon,
  FullscreenExit as FullscreenExitIcon,
  ZoomIn as ZoomInIcon,
  ZoomOut as ZoomOutIcon,
  RotateLeft as RotateLeftIcon,
  RotateRight as RotateRightIcon,
  Autorenew as AutorenewIcon,
  Sync as SyncIcon,
  SyncAlt as SyncAltIcon,
  Cached as CachedIcon,
  GetApp as GetAppIcon,
  Publish as PublishIcon,
  Share as ShareIcon,
  Bookmark as BookmarkIcon,
  BookmarkBorder as BookmarkBorderIcon,
  Star as StarIcon,
  StarBorder as StarBorderIcon,
  Favorite as FavoriteIcon,
  FavoriteBorder as FavoriteBorderIcon,
  ThumbUp as ThumbUpIcon,
  ThumbDown as ThumbDownIcon,
  ThumbUpAlt as ThumbUpAltIcon,
  ThumbDownAlt as ThumbDownAltIcon,
  Visibility as VisibilityIcon,
  VisibilityOff as VisibilityOffIcon,
  Edit as EditIcon,
  Delete as DeleteIcon,
  Add as AddIcon,
  Remove as RemoveIcon,
  Clear as ClearIcon,
  Close as CloseIcon,
  Check as CheckIcon2,
  Cancel as CancelIcon,
  Save as SaveIcon,
  Undo as UndoIcon,
  Redo as RedoIcon,
  Print as PrintIcon,
  Email as EmailIcon,
  Phone as PhoneIcon,
  LocationOn as LocationOnIcon,
  Language as LanguageIcon,
  Translate as TranslateIcon,
  Help as HelpIcon,
  HelpOutline as HelpOutlineIcon,
  Info as InfoIcon2,
  InfoOutline as InfoOutlineIcon,
  Warning as WarningIcon2,
  Error as ErrorIcon2,
  ErrorOutline as ErrorOutlineIcon,
  CheckCircle as CheckCircleIcon,
  CheckCircleOutline as CheckCircleOutlineIcon,
  Cancel as CancelIcon2,
  CancelOutlined as CancelOutlinedIcon,
  AccessTime as AccessTimeIcon,
  AccessTimeFilled as AccessTimeFilledIcon,
  Schedule as ScheduleIcon2,
  Event as EventIcon2,
  EventAvailable as EventAvailableIcon,
  EventBusy as EventBusyIcon,
  EventNote as EventNoteIcon,
  Today as TodayIcon,
  DateRange as DateRangeIcon,
  CalendarMonth as CalendarMonthIcon,
  CalendarToday as CalendarTodayIcon,
  CalendarViewDay as CalendarViewDayIcon,
  CalendarViewMonth as CalendarViewMonthIcon,
  CalendarViewWeek as CalendarViewWeekIcon,
  KeyboardArrowDown as KeyboardArrowDownIcon,
  KeyboardArrowLeft as KeyboardArrowLeftIcon,
  KeyboardArrowRight as KeyboardArrowRightIcon,
  KeyboardArrowUp as KeyboardArrowUpIcon,
  KeyboardBackspace as KeyboardBackspaceIcon,
  KeyboardTab as KeyboardTabIcon,
  KeyboardReturn as KeyboardReturnIcon,
  KeyboardArrowDown as KeyboardArrowDownIcon2
} from '@mui/icons-material';
import { io, Socket } from 'socket.io-client';
import ProfessionalTerminal from './ProfessionalTerminal';
import { 
  LineChart, 
  Line, 
  AreaChart, 
  Area, 
  BarChart, 
  Bar, 
  PieChart, 
  Pie, 
  Cell, 
  XAxis, 
  YAxis, 
  CartesianGrid, 
  Tooltip as RechartsTooltip, 
  Legend, 
  ResponsiveContainer,
  ScatterChart,
  Scatter,
  RadarChart,
  PolarGrid,
  PolarAngleAxis,
  PolarRadiusAxis,
  Radar,
  ComposedChart,
  ReferenceLine,
  ReferenceArea,
  Brush,
  ErrorBar,
  LabelList
} from 'recharts';

interface UltimateDashboardProps {
  user: any;
  token: string;
}

const UltimateDashboard: React.FC<UltimateDashboardProps> = ({ user, token }) => {
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
  const [refreshInterval, setRefreshInterval] = useState(2000);
  const [isTerminalFullscreen, setIsTerminalFullscreen] = useState(false);
  const [currentDirectory, setCurrentDirectory] = useState('/');
  const [showSettings, setShowSettings] = useState(false);
  const [showNotifications, setShowNotifications] = useState(false);
  const [viewMode, setViewMode] = useState<'grid' | 'list' | 'compact'>('grid');
  const [sortBy, setSortBy] = useState('name');
  const [sortOrder, setSortOrder] = useState<'asc' | 'desc'>('asc');
  const [filterText, setFilterText] = useState('');
  const [selectedItems, setSelectedItems] = useState<string[]>([]);
  const [showFilters, setShowFilters] = useState(false);
  const [realTimeMetrics, setRealTimeMetrics] = useState<any>({});
  const [historicalData, setHistoricalData] = useState<any[]>([]);
  const [alerts, setAlerts] = useState<any[]>([]);
  const [isRecording, setIsRecording] = useState(false);
  const [recordingData, setRecordingData] = useState<any[]>([]);

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
      
      // Cargar datos iniciales
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

      // Agregar a datos históricos
      setHistoricalData(prev => {
        const newData = {
          timestamp: new Date().toISOString(),
          ...data.data
        };
        return [newData, ...prev.slice(0, 999)]; // Mantener últimos 1000 registros
      });
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
    setNotifications(prev => [notification, ...prev.slice(0, 99)]);
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
      historicalData,
      alerts,
      notifications
    };
    
    const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `ultimate-dashboard-${Date.now()}.json`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
    
    addNotification('success', 'Exported', 'Dashboard data exported successfully');
  };

  const renderUltimateHeader = () => (
    <Paper sx={{ 
      p: 3, 
      background: 'linear-gradient(135deg, #1e3c72 0%, #2a5298 50%, #667eea 100%)',
      color: 'white',
      position: 'relative',
      overflow: 'hidden'
    }}>
      {/* Background Pattern */}
      <Box
        sx={{
          position: 'absolute',
          top: 0,
          left: 0,
          right: 0,
          bottom: 0,
          background: `
            radial-gradient(circle at 20% 80%, rgba(120, 119, 198, 0.3) 0%, transparent 50%),
            radial-gradient(circle at 80% 20%, rgba(255, 255, 255, 0.1) 0%, transparent 50%),
            radial-gradient(circle at 40% 40%, rgba(120, 119, 198, 0.2) 0%, transparent 50%)
          `,
          zIndex: 0
        }}
      />
      
      <Box sx={{ position: 'relative', zIndex: 1 }}>
        <Box display="flex" alignItems="center" justifyContent="space-between" mb={2}>
          <Box display="flex" alignItems="center" gap={3}>
            <Avatar sx={{ width: 56, height: 56, bgcolor: 'rgba(255,255,255,0.2)' }}>
              <DashboardIcon sx={{ fontSize: 32 }} />
            </Avatar>
            <Box>
              <Typography variant="h3" fontWeight="bold" sx={{ mb: 1 }}>
                Ultimate System Dashboard
              </Typography>
              <Typography variant="h6" sx={{ opacity: 0.9 }}>
                Professional Security Interface - Real System Access & Analytics
              </Typography>
              <Box display="flex" alignItems="center" gap={2} mt={1}>
                <Chip 
                  icon={<PersonIcon />}
                  label={`User: ${user.username}`}
                  variant="outlined"
                  sx={{ color: 'white', borderColor: 'white' }}
                />
                <Chip 
                  icon={<SecurityIcon />}
                  label={`Role: ${user.role}`}
                  variant="outlined"
                  sx={{ color: 'white', borderColor: 'white' }}
                />
                <Chip 
                  icon={<CheckIcon />}
                  label={`Permissions: ${user.permissions.join(', ')}`}
                  variant="outlined"
                  sx={{ color: 'white', borderColor: 'white' }}
                />
              </Box>
            </Box>
          </Box>
          
          <Box display="flex" alignItems="center" gap={2}>
            <Chip 
              icon={isConnected ? <CheckCircleIcon /> : <ErrorIcon />}
              label={isConnected ? 'Connected' : 'Disconnected'}
              color={isConnected ? 'success' : 'error'}
              sx={{ bgcolor: 'rgba(255,255,255,0.2)' }}
            />
            
            <Tooltip title="Refresh All">
              <IconButton onClick={refreshAll} sx={{ color: 'white' }}>
                <RefreshIcon />
              </IconButton>
            </Tooltip>
            
            <Tooltip title="Export Data">
              <IconButton onClick={exportData} sx={{ color: 'white' }}>
                <DownloadIcon />
              </IconButton>
            </Tooltip>
            
            <Tooltip title="Settings">
              <IconButton onClick={() => setShowSettings(true)} sx={{ color: 'white' }}>
                <SettingsIcon />
              </IconButton>
            </Tooltip>
            
            <Tooltip title="Notifications">
              <IconButton onClick={() => setShowNotifications(true)} sx={{ color: 'white' }}>
                <Badge badgeContent={notifications.length} color="error">
                  <NotificationsIcon />
                </Badge>
              </IconButton>
            </Tooltip>
          </Box>
        </Box>

        {/* Real-time Metrics Bar */}
        <Box display="flex" alignItems="center" gap={4} flexWrap="wrap">
          <Box display="flex" alignItems="center" gap={1}>
            <CpuIcon sx={{ fontSize: 20 }} />
            <Typography variant="body2">
              CPU: {realTimeMetrics.cpu?.usage || 0}%
            </Typography>
          </Box>
          <Box display="flex" alignItems="center" gap={1}>
            <MemoryIcon sx={{ fontSize: 20 }} />
            <Typography variant="body2">
              RAM: {realTimeMetrics.memory ? 
                Math.round((realTimeMetrics.memory.used / realTimeMetrics.memory.total) * 100) : 0}%
            </Typography>
          </Box>
          <Box display="flex" alignItems="center" gap={1}>
            <StorageIcon sx={{ fontSize: 20 }} />
            <Typography variant="body2">
              Disk: {realTimeMetrics.disk?.usage || 0}%
            </Typography>
          </Box>
          <Box display="flex" alignItems="center" gap={1}>
            <NetworkIcon sx={{ fontSize: 20 }} />
            <Typography variant="body2">
              Network: {realTimeMetrics.network?.connections || 0} connections
            </Typography>
          </Box>
          <Box display="flex" alignItems="center" gap={1}>
            <BugReportIcon sx={{ fontSize: 20 }} />
            <Typography variant="body2">
              Processes: {realTimeMetrics.processes?.length || 0}
            </Typography>
          </Box>
        </Box>
      </Box>
    </Paper>
  );

  // Continuará en la siguiente parte...