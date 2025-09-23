export interface User {
  id: number;
  name: string;
  email: string;
  createdAt: string;
  updatedAt: string;
}

export interface Category {
  id: number;
  name: string;
  color: string;
  createdAt: string;
  _count?: {
    tasks: number;
  };
}

export interface Task {
  id: number;
  title: string;
  description?: string;
  status: TaskStatus;
  priority: Priority;
  dueDate?: string;
  createdAt: string;
  updatedAt: string;
  userId: number;
  categories: Category[];
}

export type TaskStatus = 'PENDING' | 'IN_PROGRESS' | 'COMPLETED' | 'CANCELLED';
export type Priority = 'LOW' | 'MEDIUM' | 'HIGH' | 'URGENT';

export interface AuthResponse {
  success: boolean;
  data: {
    user: User;
    token: string;
  };
}

export interface ApiResponse<T> {
  success: boolean;
  data: T;
  error?: string;
}

export interface TaskStats {
  totalTasks: number;
  completedTasks: number;
  pendingTasks: number;
  inProgressTasks: number;
  overdueTasks: number;
  completionRate: number;
}

export interface DashboardData {
  stats: TaskStats;
  recentTasks: Task[];
}

export interface CreateTaskData {
  title: string;
  description?: string;
  priority?: Priority;
  dueDate?: string;
  categoryIds?: number[];
}

export interface UpdateTaskData {
  title?: string;
  description?: string;
  status?: TaskStatus;
  priority?: Priority;
  dueDate?: string;
  categoryIds?: number[];
}

export interface CreateCategoryData {
  name: string;
  color?: string;
}

export interface UpdateCategoryData {
  name?: string;
  color?: string;
}