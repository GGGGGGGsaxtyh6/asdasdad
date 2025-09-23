import { useQuery } from 'react-query';
import { userService } from '../services/users';
import { taskService } from '../services/tasks';
import { 
  CheckSquare, 
  Clock, 
  AlertTriangle, 
  TrendingUp,
  Calendar,
  Target
} from 'lucide-react';
import { format } from 'date-fns';

export const Dashboard = () => {
  const { data: dashboardData, isLoading: dashboardLoading } = useQuery(
    'dashboard',
    () => userService.getDashboard(),
    {
      refetchInterval: 30000, // Refetch every 30 seconds
    }
  );

  const { data: taskStats, isLoading: statsLoading } = useQuery(
    'taskStats',
    () => taskService.getTaskStats()
  );

  if (dashboardLoading || statsLoading) {
    return (
      <div className="flex items-center justify-center h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600"></div>
      </div>
    );
  }

  const stats = dashboardData?.data.stats;
  const recentTasks = dashboardData?.data.recentTasks || [];

  const statCards = [
    {
      title: 'Total Tasks',
      value: stats?.totalTasks || 0,
      icon: CheckSquare,
      color: 'bg-blue-500',
    },
    {
      title: 'Completed',
      value: stats?.completedTasks || 0,
      icon: Target,
      color: 'bg-green-500',
    },
    {
      title: 'In Progress',
      value: stats?.inProgressTasks || 0,
      icon: Clock,
      color: 'bg-yellow-500',
    },
    {
      title: 'Overdue',
      value: stats?.overdueTasks || 0,
      icon: AlertTriangle,
      color: 'bg-red-500',
    },
  ];

  const getPriorityColor = (priority: string) => {
    switch (priority) {
      case 'URGENT':
        return 'bg-red-100 text-red-800';
      case 'HIGH':
        return 'bg-orange-100 text-orange-800';
      case 'MEDIUM':
        return 'bg-yellow-100 text-yellow-800';
      case 'LOW':
        return 'bg-green-100 text-green-800';
      default:
        return 'bg-gray-100 text-gray-800';
    }
  };

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'COMPLETED':
        return 'bg-green-100 text-green-800';
      case 'IN_PROGRESS':
        return 'bg-blue-100 text-blue-800';
      case 'PENDING':
        return 'bg-yellow-100 text-yellow-800';
      case 'CANCELLED':
        return 'bg-red-100 text-red-800';
      default:
        return 'bg-gray-100 text-gray-800';
    }
  };

  return (
    <div className="space-y-6">
      {/* Welcome message */}
      <div className="bg-gradient-to-r from-primary-500 to-primary-600 rounded-lg p-6 text-white">
        <h1 className="text-2xl font-bold">Welcome back!</h1>
        <p className="text-primary-100 mt-1">
          Here's an overview of your tasks and productivity.
        </p>
      </div>

      {/* Stats cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        {statCards.map((stat) => (
          <div key={stat.title} className="card p-6">
            <div className="flex items-center">
              <div className={`p-3 rounded-lg ${stat.color}`}>
                <stat.icon className="h-6 w-6 text-white" />
              </div>
              <div className="ml-4">
                <p className="text-sm font-medium text-gray-600">{stat.title}</p>
                <p className="text-2xl font-bold text-gray-900">{stat.value}</p>
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* Completion rate */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="card p-6">
          <div className="flex items-center justify-between mb-4">
            <h3 className="text-lg font-semibold text-gray-900">Completion Rate</h3>
            <TrendingUp className="h-5 w-5 text-green-500" />
          </div>
          <div className="flex items-center">
            <div className="flex-1">
              <div className="bg-gray-200 rounded-full h-3">
                <div
                  className="bg-green-500 h-3 rounded-full transition-all duration-300"
                  style={{ width: `${stats?.completionRate || 0}%` }}
                ></div>
              </div>
              <p className="text-sm text-gray-600 mt-2">
                {stats?.completionRate || 0}% of tasks completed
              </p>
            </div>
            <div className="ml-4 text-2xl font-bold text-green-600">
              {Math.round(stats?.completionRate || 0)}%
            </div>
          </div>
        </div>

        <div className="card p-6">
          <div className="flex items-center justify-between mb-4">
            <h3 className="text-lg font-semibold text-gray-900">Recent Activity</h3>
            <Calendar className="h-5 w-5 text-blue-500" />
          </div>
          <div className="space-y-3">
            {recentTasks.length > 0 ? (
              recentTasks.slice(0, 3).map((task) => (
                <div key={task.id} className="flex items-center justify-between">
                  <div className="flex-1">
                    <p className="text-sm font-medium text-gray-900 truncate">
                      {task.title}
                    </p>
                    <p className="text-xs text-gray-500">
                      {format(new Date(task.createdAt), 'MMM d, yyyy')}
                    </p>
                  </div>
                  <div className="flex items-center space-x-2">
                    <span className={`px-2 py-1 text-xs font-medium rounded-full ${getStatusColor(task.status)}`}>
                      {task.status.replace('_', ' ')}
                    </span>
                    <span className={`px-2 py-1 text-xs font-medium rounded-full ${getPriorityColor(task.priority)}`}>
                      {task.priority}
                    </span>
                  </div>
                </div>
              ))
            ) : (
              <p className="text-sm text-gray-500">No recent tasks</p>
            )}
          </div>
        </div>
      </div>

      {/* Recent tasks */}
      <div className="card">
        <div className="px-6 py-4 border-b border-gray-200">
          <h3 className="text-lg font-semibold text-gray-900">Recent Tasks</h3>
        </div>
        <div className="divide-y divide-gray-200">
          {recentTasks.length > 0 ? (
            recentTasks.map((task) => (
              <div key={task.id} className="px-6 py-4 hover:bg-gray-50">
                <div className="flex items-center justify-between">
                  <div className="flex-1">
                    <h4 className="text-sm font-medium text-gray-900">{task.title}</h4>
                    {task.description && (
                      <p className="text-sm text-gray-500 mt-1 line-clamp-2">
                        {task.description}
                      </p>
                    )}
                    <div className="flex items-center space-x-4 mt-2">
                      <span className={`px-2 py-1 text-xs font-medium rounded-full ${getStatusColor(task.status)}`}>
                        {task.status.replace('_', ' ')}
                      </span>
                      <span className={`px-2 py-1 text-xs font-medium rounded-full ${getPriorityColor(task.priority)}`}>
                        {task.priority}
                      </span>
                      {task.dueDate && (
                        <span className="text-xs text-gray-500">
                          Due: {format(new Date(task.dueDate), 'MMM d, yyyy')}
                        </span>
                      )}
                    </div>
                  </div>
                  <div className="ml-4">
                    {task.categories.length > 0 && (
                      <div className="flex space-x-1">
                        {task.categories.slice(0, 2).map((category) => (
                          <span
                            key={category.id}
                            className="px-2 py-1 text-xs font-medium rounded-full text-white"
                            style={{ backgroundColor: category.color }}
                          >
                            {category.name}
                          </span>
                        ))}
                        {task.categories.length > 2 && (
                          <span className="px-2 py-1 text-xs font-medium rounded-full bg-gray-100 text-gray-600">
                            +{task.categories.length - 2}
                          </span>
                        )}
                      </div>
                    )}
                  </div>
                </div>
              </div>
            ))
          ) : (
            <div className="px-6 py-8 text-center">
              <CheckSquare className="mx-auto h-12 w-12 text-gray-400" />
              <h3 className="mt-2 text-sm font-medium text-gray-900">No tasks yet</h3>
              <p className="mt-1 text-sm text-gray-500">
                Get started by creating your first task.
              </p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};