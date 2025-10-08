# Smurf Bank - Premium Digital Banking Platform

A luxurious and modern banking application with 3D interactive elements, built with Next.js 14, TypeScript, Three.js, and Prisma.

## Features

✨ **Premium UI/UX**
- Ultra-modern glass morphism design
- 3D interactive elements powered by Three.js
- Smooth animations with Framer Motion
- Dark/Light theme with seamless transitions

🔐 **Authentication & Security**
- Secure registration and login system
- Password recovery flow
- Two-factor authentication (2FA) ready
- Session management with NextAuth

💰 **Banking Features**
- Real-time balance tracking
- Instant peer-to-peer transfers
- Transaction history with advanced filters
- Interactive charts and analytics
- Notification system

⚙️ **User Settings**
- Profile management
- Security settings
- Theme preferences
- Notification preferences

## Tech Stack

- **Frontend:** Next.js 14 (App Router), React 18, TypeScript
- **Styling:** Tailwind CSS, Framer Motion
- **3D Graphics:** Three.js, React Three Fiber, Drei
- **Authentication:** NextAuth.js
- **Database:** PostgreSQL with Prisma ORM
- **Charts:** Chart.js, React ChartJS 2
- **State Management:** Zustand

## Getting Started

### Prerequisites

- Node.js 18+ 
- PostgreSQL database (or Neon/Supabase/Railway for cloud)
- npm or yarn

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd smurf-bank
```

2. Install dependencies:
```bash
npm install
```

3. Set up environment variables:
```bash
cp .env.example .env
```

Edit `.env` with your configuration:
```env
DATABASE_URL="postgresql://user:password@host:5432/smurfbank"
NEXTAUTH_URL="https://your-domain.vercel.app"
NEXTAUTH_SECRET="your-super-secret-key-change-this"
```

4. Set up the database:
```bash
npx prisma generate
npx prisma db push
```

5. Run the development server:
```bash
npm run dev
```

Open [http://localhost:3000](http://localhost:3000) in your browser.

## Deployment on Vercel

### Step 1: Prepare Database

#### Option A: Neon (Recommended - Free PostgreSQL)

1. Go to [neon.tech](https://neon.tech)
2. Create a free account
3. Create a new project
4. Copy the connection string

#### Option B: Supabase

1. Go to [supabase.com](https://supabase.com)
2. Create a project
3. Get the connection string from Settings > Database

#### Option C: Railway

1. Go to [railway.app](https://railway.app)
2. Create a PostgreSQL database
3. Copy the connection string

### Step 2: Deploy to Vercel

1. Push your code to GitHub:
```bash
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin <your-repo-url>
git push -u origin main
```

2. Go to [vercel.com](https://vercel.com)
3. Click "Import Project"
4. Select your GitHub repository
5. Configure environment variables:
   - `DATABASE_URL`: Your PostgreSQL connection string
   - `NEXTAUTH_URL`: Your Vercel deployment URL (e.g., https://smurf-bank.vercel.app)
   - `NEXTAUTH_SECRET`: Generate with: `openssl rand -base64 32`

6. Deploy!

### Step 3: Initialize Database

After deployment, run migrations:
```bash
npx prisma db push
```

Or use Vercel's CLI:
```bash
vercel env pull .env.local
npx prisma generate
npx prisma db push
```

## Project Structure

```
smurf-bank/
├── app/                          # Next.js App Router
│   ├── api/                      # API routes
│   │   ├── auth/                 # Authentication endpoints
│   │   ├── transactions/         # Transaction endpoints
│   │   ├── notifications/        # Notification endpoints
│   │   └── user/                 # User profile endpoints
│   ├── dashboard/                # Protected dashboard pages
│   │   ├── transfer/            # Transfer page
│   │   ├── history/             # Transaction history
│   │   ├── notifications/       # Notifications page
│   │   └── settings/            # Settings page
│   ├── login/                    # Login page
│   ├── register/                 # Registration page
│   ├── forgot-password/          # Password recovery
│   └── page.tsx                  # Landing page
├── components/
│   ├── 3d/                       # Three.js 3D components
│   ├── ui/                       # Reusable UI components
│   └── layout/                   # Layout components
├── lib/                          # Utilities and configurations
│   ├── prisma.ts                 # Prisma client
│   ├── auth.ts                   # NextAuth configuration
│   ├── store.ts                  # Zustand state management
│   └── utils.ts                  # Helper functions
├── prisma/
│   └── schema.prisma             # Database schema
└── public/                       # Static assets
```

## Environment Variables

| Variable | Description | Required |
|----------|-------------|----------|
| `DATABASE_URL` | PostgreSQL connection string | Yes |
| `NEXTAUTH_URL` | Application URL | Yes |
| `NEXTAUTH_SECRET` | Secret for JWT encryption | Yes |

## Database Schema

### User
- id, email, username, name, password
- balance, avatar, theme, notificationPref
- twoFactorEnabled, twoFactorSecret
- Timestamps

### Transaction
- id, amount, type, status, description
- senderId, receiverId
- Timestamps

### Notification
- id, userId, title, message, type
- read status
- Timestamps

## Default Test Users

After deployment, you can create test users through the registration page. Each new user receives:
- **10,000 Smurf** welcome bonus
- Welcome notification
- Full access to all features

## Features Demo

### Landing Page
- Elegant hero section with 3D elements
- Feature showcase
- Responsive design

### Authentication
- Real-time form validation
- Secure password requirements
- Remember me functionality

### Dashboard
- Interactive balance display with 3D coin
- Balance history chart (30d, 90d, 1y views)
- Recent transactions feed
- Quick actions

### Transfer
- Step-by-step transfer flow
- Real-time balance validation
- 3D transfer animation
- Confirmation summary

### Transaction History
- Searchable transactions
- Advanced filters (type, status, date)
- Expandable transaction details
- Pagination support

### Notifications
- Real-time notification system
- Read/unread tracking
- Filter by status
- Mark all as read

### Settings
- Profile management
- Security settings with 2FA toggle
- Theme switcher (light/dark)
- Notification preferences

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/[...nextauth]` - NextAuth handlers

### User
- `GET /api/user/profile` - Get user profile
- `PATCH /api/user/profile` - Update profile
- `GET /api/user/balance` - Get current balance

### Transactions
- `GET /api/transactions` - List transactions (with filters)
- `POST /api/transactions` - Create new transaction

### Notifications
- `GET /api/notifications` - List notifications
- `PATCH /api/notifications` - Mark as read

## Performance Optimizations

- Server-side rendering (SSR) with Next.js 14
- Automatic code splitting
- Image optimization
- Static asset caching
- Database query optimization with Prisma
- Lazy loading of 3D components

## Security Features

- Password hashing with bcrypt
- JWT-based authentication
- CSRF protection
- SQL injection prevention via Prisma
- XSS protection
- Secure HTTP headers
- Session management

## Browser Support

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)

## Contributing

This is a demonstration project. Feel free to fork and modify for your needs.

## License

MIT License

## Support

For issues or questions, please open an issue on GitHub.

---

Built with ❤️ using Next.js, Three.js, and modern web technologies.
