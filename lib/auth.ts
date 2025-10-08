import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';
import { User } from './database';

const JWT_SECRET = process.env.JWT_SECRET || 'smurf_bank_secret_key_change_in_production';

export async function hashPassword(password: string): Promise<string> {
  return bcrypt.hash(password, 10);
}

export async function verifyPassword(password: string, hashedPassword: string): Promise<boolean> {
  return bcrypt.compare(password, hashedPassword);
}

export function generateToken(userId: string): string {
  return jwt.sign({ userId }, JWT_SECRET, { expiresIn: '7d' });
}

export function verifyToken(token: string): { userId: string } | null {
  try {
    const decoded = jwt.verify(token, JWT_SECRET) as { userId: string };
    return decoded;
  } catch {
    return null;
  }
}

export function getUserFromToken(token: string): string | null {
  const decoded = verifyToken(token);
  return decoded ? decoded.userId : null;
}

export function sanitizeUser(user: User): Omit<User, 'password'> {
  const { password, ...sanitized } = user;
  return sanitized;
}
