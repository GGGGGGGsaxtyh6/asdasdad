import express from 'express';
import { createCategorySchema, updateCategorySchema, validate } from '../utils/validation';
import { authenticateToken, AuthRequest } from '../middleware/auth';
import { prisma } from '../index';

const router = express.Router();

// Apply authentication middleware to all routes
router.use(authenticateToken);

// Get all categories for the authenticated user
router.get('/', async (req: AuthRequest, res) => {
  try {
    const userId = req.user!.id;

    const categories = await prisma.category.findMany({
      where: { userId },
      include: {
        _count: {
          select: { tasks: true },
        },
      },
      orderBy: { name: 'asc' },
    });

    res.json({
      success: true,
      data: categories,
    });
  } catch (error) {
    console.error('Get categories error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Get category by ID
router.get('/:id', async (req: AuthRequest, res) => {
  try {
    const { id } = req.params;
    const userId = req.user!.id;

    const category = await prisma.category.findFirst({
      where: {
        id: parseInt(id),
        userId,
      },
      include: {
        tasks: {
          include: {
            task: true,
          },
        },
        _count: {
          select: { tasks: true },
        },
      },
    });

    if (!category) {
      return res.status(404).json({
        success: false,
        error: 'Category not found',
      });
    }

    // Transform the data
    const transformedCategory = {
      ...category,
      tasks: category.tasks.map(tc => tc.task),
    };

    res.json({
      success: true,
      data: transformedCategory,
    });
  } catch (error) {
    console.error('Get category error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Create new category
router.post('/', validate(createCategorySchema), async (req: AuthRequest, res) => {
  try {
    const { name, color } = req.body;
    const userId = req.user!.id;

    // Check if category with same name already exists for this user
    const existingCategory = await prisma.category.findFirst({
      where: {
        name,
        userId,
      },
    });

    if (existingCategory) {
      return res.status(400).json({
        success: false,
        error: 'Category with this name already exists',
      });
    }

    const category = await prisma.category.create({
      data: {
        name,
        color: color || '#3B82F6',
        userId,
      },
    });

    res.status(201).json({
      success: true,
      data: category,
    });
  } catch (error) {
    console.error('Create category error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Update category
router.put('/:id', validate(updateCategorySchema), async (req: AuthRequest, res) => {
  try {
    const { id } = req.params;
    const { name, color } = req.body;
    const userId = req.user!.id;

    // Check if category exists and belongs to user
    const existingCategory = await prisma.category.findFirst({
      where: {
        id: parseInt(id),
        userId,
      },
    });

    if (!existingCategory) {
      return res.status(404).json({
        success: false,
        error: 'Category not found',
      });
    }

    // Check if new name conflicts with existing category
    if (name && name !== existingCategory.name) {
      const conflictingCategory = await prisma.category.findFirst({
        where: {
          name,
          userId,
          id: { not: parseInt(id) },
        },
      });

      if (conflictingCategory) {
        return res.status(400).json({
          success: false,
          error: 'Category with this name already exists',
        });
      }
    }

    const category = await prisma.category.update({
      where: { id: parseInt(id) },
      data: {
        ...(name && { name }),
        ...(color && { color }),
      },
    });

    res.json({
      success: true,
      data: category,
    });
  } catch (error) {
    console.error('Update category error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

// Delete category
router.delete('/:id', async (req: AuthRequest, res) => {
  try {
    const { id } = req.params;
    const userId = req.user!.id;

    const category = await prisma.category.findFirst({
      where: {
        id: parseInt(id),
        userId,
      },
    });

    if (!category) {
      return res.status(404).json({
        success: false,
        error: 'Category not found',
      });
    }

    // Check if category has tasks
    const taskCount = await prisma.taskCategory.count({
      where: { categoryId: parseInt(id) },
    });

    if (taskCount > 0) {
      return res.status(400).json({
        success: false,
        error: 'Cannot delete category with existing tasks. Please remove all tasks from this category first.',
      });
    }

    await prisma.category.delete({
      where: { id: parseInt(id) },
    });

    res.json({
      success: true,
      message: 'Category deleted successfully',
    });
  } catch (error) {
    console.error('Delete category error:', error);
    res.status(500).json({
      success: false,
      error: 'Internal server error',
    });
  }
});

export default router;