import api from './api';
import { Category, CreateCategoryData, UpdateCategoryData } from '../types';

export const categoryService = {
  async getCategories(): Promise<{ success: boolean; data: Category[] }> {
    const response = await api.get('/categories');
    return response.data;
  },

  async getCategory(id: number): Promise<{ success: boolean; data: Category }> {
    const response = await api.get(`/categories/${id}`);
    return response.data;
  },

  async createCategory(data: CreateCategoryData): Promise<{ success: boolean; data: Category }> {
    const response = await api.post('/categories', data);
    return response.data;
  },

  async updateCategory(id: number, data: UpdateCategoryData): Promise<{ success: boolean; data: Category }> {
    const response = await api.put(`/categories/${id}`, data);
    return response.data;
  },

  async deleteCategory(id: number): Promise<{ success: boolean; message: string }> {
    const response = await api.delete(`/categories/${id}`);
    return response.data;
  },
};