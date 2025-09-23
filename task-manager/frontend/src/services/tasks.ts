import api from './api';
import { Task, CreateTaskData, UpdateTaskData, TaskStats } from '../types';

export const taskService = {
  async getTasks(params?: {
    status?: string;
    priority?: string;
    categoryId?: string;
    search?: string;
  }): Promise<{ success: boolean; data: Task[] }> {
    const response = await api.get('/tasks', { params });
    return response.data;
  },

  async getTask(id: number): Promise<{ success: boolean; data: Task }> {
    const response = await api.get(`/tasks/${id}`);
    return response.data;
  },

  async createTask(data: CreateTaskData): Promise<{ success: boolean; data: Task }> {
    const response = await api.post('/tasks', data);
    return response.data;
  },

  async updateTask(id: number, data: UpdateTaskData): Promise<{ success: boolean; data: Task }> {
    const response = await api.put(`/tasks/${id}`, data);
    return response.data;
  },

  async deleteTask(id: number): Promise<{ success: boolean; message: string }> {
    const response = await api.delete(`/tasks/${id}`);
    return response.data;
  },

  async getTaskStats(): Promise<{ success: boolean; data: TaskStats }> {
    const response = await api.get('/tasks/stats/overview');
    return response.data;
  },
};