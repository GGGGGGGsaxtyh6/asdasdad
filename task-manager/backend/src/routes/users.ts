import express from 'express';
import { authenticateToken, AuthRequest } from '../middleware/auth';
import { prisma } from '../index';

const router = express.Router();

// Apply authentication middleware to all routes
router.use(authenticateToken);

// Get user profile
router.get('/profile', async (req: AuthRequest, res) => {
  try {
    const userId = req.user!.id;

    const user = await prisma.user.findUnique({
      where: { id: userId },
      select: {
        id: true,
        name: true,
        email: true,
        createdAt: true,
        updatedAt: true,
      },
    });

    if (!user) {
      return res.status(404).json({
        success: false,
        error: 'User not found',
      });
    }

    res.json({
      success: true,
      data: { user },
    });
  } catch (error) {
    console.error('Get user profile error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Update user profile
router.put('/profile', async (req: AuthRequest, res) => {
  try {
    const { name, email } = req.body;
    const userId = req.user!.id;

    // Check if email is already taken by another user
    if (email) {
      const existingUser = await prisma.user.findFirst({
        where: {
          email,
          id: { not: userId },
        },
      });

      if (existingUser) {
        return res.status(400).json({
          success: false,
          error: 'Email already in use',
        });
      }
    }

    const user = await prisma.user.update({
      where: { id: userId },
      data: {
        ...(name && { name }),
        ...(email && { email }),
      },
      select: {
        id: true,
        name: true,
        email: true,
        createdAt: true,
        updatedAt: true,
      },
    });

    res.json({
      success: true,
      data: { user },
    });
  } catch (error) {
    console.error('Update user profile error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Get user dashboard statistics
router.get('/dashboard', async (req: AuthRequest, res) => {
  try {
    const userId = req.user!.id;

    const [
      totalTasks,
      completedTasks,
      pendingTasks,
      inProgressTasks,
      overdueTasks,
      totalCategories,
      recentTasks,
    ] = await Promise.all([
      prisma.task.count({ where: { userId } }),
      prisma.task.count({ where: { userId, status: 'COMPLETED' } }),
      prisma.task.count({ where: { userId, status: 'PENDING' } }),
      prisma.task.count({ where: { userId, status: 'IN_PROGRESS' } }),
      prisma.task.count({
        where: {
          userId,
          dueDate: { lt: new Date() },
          status: { not: 'COMPLETED' },
        },
      }),
      prisma.category.count({ where: { userId } }),
      prisma.task.findMany({
        where: { userId },
        take: 5,
        orderBy: { createdAt: 'desc' },
        include: {
          categories: {
            include: {
              category: true,
            },
          },
        },
      }),
    ]);

    const completionRate = totalTasks > 0 ? (completedTasks / totalTasks) * 100 : 0;

    // Transform recent tasks
    const transformedRecentTasks = recentTasks.map(task => ({
      ...task,
      categories: task.categories.map(tc => tc.category),
    }));

    res.json({
      success: true,
      data: {
        stats: {
          totalTasks,
          completedTasks,
          pendingTasks,
          inProgressTasks,
          overdueTasks,
          totalCategories,
          completionRate: Math.round(completionRate * 100) / 100,
        },
        recentTasks: transformedRecentTasks,
      },
    });
  } catch (error) {
    console.error('Get dashboard error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

export default router;