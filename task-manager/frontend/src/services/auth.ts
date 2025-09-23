import api from './api';
import { AuthResponse, User } from '../types';

export const authService = {
  async register(data: { name: string; email: string; password: string }): Promise<AuthResponse> {
    const response = await api.post('/auth/register', data);
    return response.data;
  },

  async login(data: { email: string; password: string }): Promise<AuthResponse> {
    const response = await api.post('/auth/login', data);
    return response.data;
  },

  async getCurrentUser(): Promise<{ success: boolean; data: { user: User } }> {
    const response = await api.get('/auth/me');
    return response.data;
  },

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  },

  getToken(): string | null {
    return localStorage.getItem('token');
  },

  getUser(): User | null {
    const userStr = localStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : null;
  },

  setAuthData(user: User, token: string): void {
    localStorage.setItem('user', JSON.stringify(user));
    localStorage.setItem('token', token);
  },

  isAuthenticated(): boolean {
    return !!this.getToken();
  },
};