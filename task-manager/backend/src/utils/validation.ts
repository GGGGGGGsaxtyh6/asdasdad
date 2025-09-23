import Joi from 'joi';

// User validation schemas
export const registerSchema = Joi.object({
  name: Joi.string().min(2).max(50).required(),
  email: Joi.string().email().required(),
  password: Joi.string().min(6).required(),
});

export const loginSchema = Joi.object({
  email: Joi.string().email().required(),
  password: Joi.string().required(),
});

// Task validation schemas
export const createTaskSchema = Joi.object({
  title: Joi.string().min(1).max(200).required(),
  description: Joi.string().max(1000).optional(),
  priority: Joi.string().valid('LOW', 'MEDIUM', 'HIGH', 'URGENT').optional(),
  dueDate: Joi.date().iso().optional(),
  categoryIds: Joi.array().items(Joi.number().integer().positive()).optional(),
});

export const updateTaskSchema = Joi.object({
  title: Joi.string().min(1).max(200).optional(),
  description: Joi.string().max(1000).optional(),
  status: Joi.string().valid('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED').optional(),
  priority: Joi.string().valid('LOW', 'MEDIUM', 'HIGH', 'URGENT').optional(),
  dueDate: Joi.date().iso().optional(),
  categoryIds: Joi.array().items(Joi.number().integer().positive()).optional(),
});

// Category validation schemas
export const createCategorySchema = Joi.object({
  name: Joi.string().min(1).max(50).required(),
  color: Joi.string().pattern(/^#[0-9A-F]{6}$/i).optional(),
});

export const updateCategorySchema = Joi.object({
  name: Joi.string().min(1).max(50).optional(),
  color: Joi.string().pattern(/^#[0-9A-F]{6}$/i).optional(),
});

// Validation middleware
export const validate = (schema: Joi.ObjectSchema) => {
  return (req: any, res: any, next: any) => {
    const { error } = schema.validate(req.body);
    if (error) {
      return res.status(400).json({
        success: false,
        error: error.details[0].message,
      });
    }
    next();
  };
};