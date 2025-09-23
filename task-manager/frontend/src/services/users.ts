import api from './api';
import { User, DashboardData } from '../types';

export const userService = {
  async getProfile(): Promise<{ success: boolean; data: { user: User } }> {
    const response = await api.get('/users/profile');
    return response.data;
  },

  async updateProfile(data: { name?: string; email?: string }): Promise<{ success: boolean; data: { user: User } }> {
    const response = await api.put('/users/profile', data);
    return response.data;
  },

  async getDashboard(): Promise<{ success: boolean; data: DashboardData }> {
    const response = await api.get('/users/dashboard');
    return response.data;
  },
};