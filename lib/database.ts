import fs from 'fs';
import path from 'path';

const DATA_DIR = path.join(process.cwd(), 'data');
const USERS_FILE = path.join(DATA_DIR, 'users.json');
const TRANSACTIONS_FILE = path.join(DATA_DIR, 'transactions.json');
const NOTIFICATIONS_FILE = path.join(DATA_DIR, 'notifications.json');

// Tipos
export interface User {
  id: string;
  email: string;
  username: string;
  password: string;
  fullName: string;
  balance: number;
  avatar?: string;
  createdAt: string;
  settings: {
    theme: 'light' | 'dark' | 'auto';
    language: 'es' | 'en';
    timezone: string;
    reducedMotion: boolean;
    twoFactorEnabled: boolean;
  };
}

export interface Transaction {
  id: string;
  type: 'transfer' | 'received' | 'deposit' | 'withdrawal';
  amount: number;
  from?: string;
  to?: string;
  fromName?: string;
  toName?: string;
  note?: string;
  status: 'completed' | 'pending' | 'failed';
  createdAt: string;
}

export interface Notification {
  id: string;
  userId: string;
  type: 'transfer' | 'received' | 'security' | 'system';
  title: string;
  message: string;
  read: boolean;
  createdAt: string;
  link?: string;
}

// Inicializar archivos de datos
function ensureDataDir() {
  if (!fs.existsSync(DATA_DIR)) {
    fs.mkdirSync(DATA_DIR, { recursive: true });
  }
  if (!fs.existsSync(USERS_FILE)) {
    fs.writeFileSync(USERS_FILE, JSON.stringify([], null, 2));
  }
  if (!fs.existsSync(TRANSACTIONS_FILE)) {
    fs.writeFileSync(TRANSACTIONS_FILE, JSON.stringify([], null, 2));
  }
  if (!fs.existsSync(NOTIFICATIONS_FILE)) {
    fs.writeFileSync(NOTIFICATIONS_FILE, JSON.stringify([], null, 2));
  }
}

// Usuarios
export function getUsers(): User[] {
  ensureDataDir();
  const data = fs.readFileSync(USERS_FILE, 'utf-8');
  return JSON.parse(data);
}

export function saveUsers(users: User[]) {
  ensureDataDir();
  fs.writeFileSync(USERS_FILE, JSON.stringify(users, null, 2));
}

export function getUserById(id: string): User | undefined {
  return getUsers().find(u => u.id === id);
}

export function getUserByEmail(email: string): User | undefined {
  return getUsers().find(u => u.email.toLowerCase() === email.toLowerCase());
}

export function getUserByUsername(username: string): User | undefined {
  return getUsers().find(u => u.username.toLowerCase() === username.toLowerCase());
}

export function createUser(user: Omit<User, 'id' | 'createdAt' | 'balance'>): User {
  const users = getUsers();
  const newUser: User = {
    ...user,
    id: `user_${Date.now()}_${Math.random().toString(36).substr(2, 9)}`,
    balance: 1000, // Balance inicial de 1000 Smurf
    createdAt: new Date().toISOString(),
  };
  users.push(newUser);
  saveUsers(users);
  return newUser;
}

export function updateUser(id: string, updates: Partial<User>): User | null {
  const users = getUsers();
  const index = users.findIndex(u => u.id === id);
  if (index === -1) return null;
  users[index] = { ...users[index], ...updates };
  saveUsers(users);
  return users[index];
}

// Transacciones
export function getTransactions(): Transaction[] {
  ensureDataDir();
  const data = fs.readFileSync(TRANSACTIONS_FILE, 'utf-8');
  return JSON.parse(data);
}

export function saveTransactions(transactions: Transaction[]) {
  ensureDataDir();
  fs.writeFileSync(TRANSACTIONS_FILE, JSON.stringify(transactions, null, 2));
}

export function getTransactionsByUserId(userId: string): Transaction[] {
  return getTransactions()
    .filter(t => t.from === userId || t.to === userId)
    .sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime());
}

export function createTransaction(transaction: Omit<Transaction, 'id' | 'createdAt'>): Transaction {
  const transactions = getTransactions();
  const newTransaction: Transaction = {
    ...transaction,
    id: `tx_${Date.now()}_${Math.random().toString(36).substr(2, 9)}`,
    createdAt: new Date().toISOString(),
  };
  transactions.push(newTransaction);
  saveTransactions(transactions);
  return newTransaction;
}

// Notificaciones
export function getNotifications(): Notification[] {
  ensureDataDir();
  const data = fs.readFileSync(NOTIFICATIONS_FILE, 'utf-8');
  return JSON.parse(data);
}

export function saveNotifications(notifications: Notification[]) {
  ensureDataDir();
  fs.writeFileSync(NOTIFICATIONS_FILE, JSON.stringify(notifications, null, 2));
}

export function getNotificationsByUserId(userId: string): Notification[] {
  return getNotifications()
    .filter(n => n.userId === userId)
    .sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime());
}

export function createNotification(notification: Omit<Notification, 'id' | 'createdAt'>): Notification {
  const notifications = getNotifications();
  const newNotification: Notification = {
    ...notification,
    id: `notif_${Date.now()}_${Math.random().toString(36).substr(2, 9)}`,
    createdAt: new Date().toISOString(),
  };
  notifications.push(newNotification);
  saveNotifications(notifications);
  return newNotification;
}

export function markNotificationAsRead(id: string): boolean {
  const notifications = getNotifications();
  const index = notifications.findIndex(n => n.id === id);
  if (index === -1) return false;
  notifications[index].read = true;
  saveNotifications(notifications);
  return true;
}

export function markAllNotificationsAsRead(userId: string): boolean {
  const notifications = getNotifications();
  let updated = false;
  notifications.forEach(n => {
    if (n.userId === userId && !n.read) {
      n.read = true;
      updated = true;
    }
  });
  if (updated) {
    saveNotifications(notifications);
  }
  return updated;
}
