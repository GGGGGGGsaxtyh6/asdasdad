import express from 'express';
import { createTaskSchema, updateTaskSchema, validate } from '../utils/validation';
import { authenticateToken, AuthRequest } from '../middleware/auth';
import { prisma } from '../index';

const router = express.Router();

// Apply authentication middleware to all routes
router.use(authenticateToken);

// Get all tasks for the authenticated user
router.get('/', async (req: AuthRequest, res) => {
  try {
    const { status, priority, categoryId, search } = req.query;
    const userId = req.user!.id;

    const where: any = {
      userId,
    };

    // Add filters
    if (status) {
      where.status = status;
    }

    if (priority) {
      where.priority = priority;
    }

    if (search) {
      where.OR = [
        { title: { contains: search as string, mode: 'insensitive' } },
        { description: { contains: search as string, mode: 'insensitive' } },
      ];
    }

    if (categoryId) {
      where.categories = {
        some: {
          categoryId: parseInt(categoryId as string),
        },
      };
    }

    const tasks = await prisma.task.findMany({
      where,
      include: {
        categories: {
          include: {
            category: true,
          },
        },
      },
      orderBy: [
        { priority: 'desc' },
        { dueDate: 'asc' },
        { createdAt: 'desc' },
      ],
    });

    // Transform the data to include categories directly
    const transformedTasks = tasks.map(task => ({
      ...task,
      categories: task.categories.map(tc => tc.category),
    }));

    res.json({
      success: true,
      data: transformedTasks,
    });
  } catch (error) {
    console.error('Get tasks error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Get task by ID
router.get('/:id', async (req: AuthRequest, res) => {
  try {
    const { id } = req.params;
    const userId = req.user!.id;

    const task = await prisma.task.findFirst({
      where: {
        id: parseInt(id),
        userId,
      },
      include: {
        categories: {
          include: {
            category: true,
          },
        },
      },
    });

    if (!task) {
      return res.status(404).json({
        success: false,
        error: 'Task not found',
      });
    }

    // Transform the data
    const transformedTask = {
      ...task,
      categories: task.categories.map(tc => tc.category),
    };

    res.json({
      success: true,
      data: transformedTask,
    });
  } catch (error) {
    console.error('Get task error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Create new task
router.post('/', validate(createTaskSchema), async (req: AuthRequest, res) => {
  try {
    const { title, description, priority, dueDate, categoryIds } = req.body;
    const userId = req.user!.id;

    // Validate categories belong to user
    if (categoryIds && categoryIds.length > 0) {
      const categories = await prisma.category.findMany({
        where: {
          id: { in: categoryIds },
          userId,
        },
      });

      if (categories.length !== categoryIds.length) {
        return res.status(400).json({
          success: false,
          error: 'One or more categories not found',
        });
      }
    }

    const task = await prisma.task.create({
      data: {
        title,
        description,
        priority: priority || 'MEDIUM',
        dueDate: dueDate ? new Date(dueDate) : null,
        userId,
        categories: categoryIds ? {
          create: categoryIds.map((categoryId: number) => ({
            categoryId,
          })),
        } : undefined,
      },
      include: {
        categories: {
          include: {
            category: true,
          },
        },
      },
    });

    // Transform the data
    const transformedTask = {
      ...task,
      categories: task.categories.map(tc => tc.category),
    };

    res.status(201).json({
      success: true,
      data: transformedTask,
    });
  } catch (error) {
    console.error('Create task error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Update task
router.put('/:id', validate(updateTaskSchema), async (req: AuthRequest, res) => {
  try {
    const { id } = req.params;
    const { title, description, status, priority, dueDate, categoryIds } = req.body;
    const userId = req.user!.id;

    // Check if task exists and belongs to user
    const existingTask = await prisma.task.findFirst({
      where: {
        id: parseInt(id),
        userId,
      },
    });

    if (!existingTask) {
      return res.status(404).json({
        success: false,
        error: 'Task not found',
      });
    }

    // Validate categories if provided
    if (categoryIds && categoryIds.length > 0) {
      const categories = await prisma.category.findMany({
        where: {
          id: { in: categoryIds },
          userId,
        },
      });

      if (categories.length !== categoryIds.length) {
        return res.status(400).json({
          success: false,
          error: 'One or more categories not found',
        });
      }
    }

    // Update task
    const task = await prisma.task.update({
      where: { id: parseInt(id) },
      data: {
        ...(title && { title }),
        ...(description !== undefined && { description }),
        ...(status && { status }),
        ...(priority && { priority }),
        ...(dueDate !== undefined && { dueDate: dueDate ? new Date(dueDate) : null }),
      },
      include: {
        categories: {
          include: {
            category: true,
          },
        },
      },
    });

    // Update categories if provided
    if (categoryIds !== undefined) {
      // Remove existing categories
      await prisma.taskCategory.deleteMany({
        where: { taskId: parseInt(id) },
      });

      // Add new categories
      if (categoryIds.length > 0) {
        await prisma.taskCategory.createMany({
          data: categoryIds.map((categoryId: number) => ({
            taskId: parseInt(id),
            categoryId,
          })),
        });
      }

      // Fetch updated task with categories
      const updatedTask = await prisma.task.findUnique({
        where: { id: parseInt(id) },
        include: {
          categories: {
            include: {
              category: true,
            },
          },
        },
      });

      const transformedTask = {
        ...updatedTask,
        categories: updatedTask!.categories.map(tc => tc.category),
      };

      return res.json({
        success: true,
        data: transformedTask,
      });
    }

    // Transform the data
    const transformedTask = {
      ...task,
      categories: task.categories.map(tc => tc.category),
    };

    res.json({
      success: true,
      data: transformedTask,
    });
  } catch (error) {
    console.error('Update task error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Delete task
router.delete('/:id', async (req: AuthRequest, res) => {
  try {
    const { id } = req.params;
    const userId = req.user!.id;

    const task = await prisma.task.findFirst({
      where: {
        id: parseInt(id),
        userId,
      },
    });

    if (!task) {
      return res.status(404).json({
        success: false,
        error: 'Task not found',
      });
    }

    await prisma.task.delete({
      where: { id: parseInt(id) },
    });

    res.json({
      success: true,
      message: 'Task deleted successfully',
    });
  } catch (error) {
    console.error('Delete task error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Get task statistics
router.get('/stats/overview', async (req: AuthRequest, res) => {
  try {
    const userId = req.user!.id;

    const [
      totalTasks,
      completedTasks,
      pendingTasks,
      inProgressTasks,
      overdueTasks,
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
    ]);

    const completionRate = totalTasks > 0 ? (completedTasks / totalTasks) * 100 : 0;

    res.json({
      success: true,
      data: {
        totalTasks,
        completedTasks,
        pendingTasks,
        inProgressTasks,
        overdueTasks,
        completionRate: Math.round(completionRate * 100) / 100,
      },
    });
  } catch (error) {
    console.error('Get task stats error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

export default router;