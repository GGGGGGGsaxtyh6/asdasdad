'use client';

import { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import { 
  FiUser, 
  FiLock, 
  FiBell, 
  FiMoon,
  FiSun,
  FiShield,
  FiSave,
} from 'react-icons/fi';
import { GlassCard } from '@/components/ui/GlassCard';
import { Input } from '@/components/ui/Input';
import { Button } from '@/components/ui/Button';
import { Toast } from '@/components/ui/Toast';
import { useThemeStore } from '@/lib/store';

export default function SettingsPage() {
  const [activeTab, setActiveTab] = useState<'profile' | 'security' | 'preferences'>('profile');
  const [profile, setProfile] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [toast, setToast] = useState({ show: false, message: '', type: 'info' as 'success' | 'error' | 'info' });
  const { theme, setTheme } = useThemeStore();

  // Profile form
  const [profileForm, setProfileForm] = useState({
    name: '',
  });

  // Security form
  const [securityForm, setSecurityForm] = useState({
    currentPassword: '',
    newPassword: '',
    confirmPassword: '',
    twoFactorEnabled: false,
  });

  // Preferences form
  const [preferencesForm, setPreferencesForm] = useState({
    theme: 'dark',
    notificationPref: 'all',
  });

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const res = await fetch('/api/user/profile');
        if (res.ok) {
          const data = await res.json();
          setProfile(data);
          setProfileForm({ name: data.name });
          setSecurityForm(prev => ({ ...prev, twoFactorEnabled: data.twoFactorEnabled }));
          setPreferencesForm({
            theme: data.theme,
            notificationPref: data.notificationPref,
          });
          setTheme(data.theme);
        }
      } catch (error) {
        console.error('Error fetching profile:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, []);

  const handleSaveProfile = async () => {
    setSaving(true);
    try {
      const res = await fetch('/api/user/profile', {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(profileForm),
      });

      if (res.ok) {
        const data = await res.json();
        setProfile(data);
        setToast({ show: true, message: 'Profile updated successfully!', type: 'success' });
      } else {
        setToast({ show: true, message: 'Failed to update profile', type: 'error' });
      }
    } catch (error) {
      setToast({ show: true, message: 'An error occurred', type: 'error' });
    } finally {
      setSaving(false);
    }
  };

  const handleSavePreferences = async () => {
    setSaving(true);
    try {
      const res = await fetch('/api/user/profile', {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(preferencesForm),
      });

      if (res.ok) {
        const data = await res.json();
        setProfile(data);
        setTheme(preferencesForm.theme as 'light' | 'dark');
        setToast({ show: true, message: 'Preferences updated successfully!', type: 'success' });
      } else {
        setToast({ show: true, message: 'Failed to update preferences', type: 'error' });
      }
    } catch (error) {
      setToast({ show: true, message: 'An error occurred', type: 'error' });
    } finally {
      setSaving(false);
    }
  };

  const tabs = [
    { id: 'profile', label: 'Profile', icon: FiUser },
    { id: 'security', label: 'Security', icon: FiLock },
    { id: 'preferences', label: 'Preferences', icon: FiBell },
  ];

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-[60vh]">
        <div className="loading-dots text-smurf-500">
          <span></span>
          <span></span>
          <span></span>
        </div>
      </div>
    );
  }

  return (
    <div className="max-w-4xl mx-auto space-y-6">
      <Toast
        isVisible={toast.show}
        message={toast.message}
        type={toast.type}
        onClose={() => setToast(prev => ({ ...prev, show: false }))}
      />

      {/* Header */}
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
      >
        <h1 className="text-4xl font-bold text-gray-900 dark:text-white mb-2">
          Settings
        </h1>
        <p className="text-gray-600 dark:text-gray-400">
          Manage your account settings and preferences
        </p>
      </motion.div>

      <div className="grid lg:grid-cols-4 gap-6">
        {/* Tabs Sidebar */}
        <GlassCard className="p-4 lg:col-span-1 h-fit">
          <nav className="space-y-1">
            {tabs.map((tab) => {
              const Icon = tab.icon;
              return (
                <button
                  key={tab.id}
                  onClick={() => setActiveTab(tab.id as any)}
                  className={`
                    w-full flex items-center gap-3 px-4 py-3 rounded-xl transition-all
                    ${activeTab === tab.id
                      ? 'bg-smurf-500 text-white'
                      : 'text-gray-700 dark:text-gray-300 hover:bg-white/50 dark:hover:bg-white/5'
                    }
                  `}
                >
                  <Icon className="w-5 h-5" />
                  <span className="font-medium">{tab.label}</span>
                </button>
              );
            })}
          </nav>
        </GlassCard>

        {/* Content */}
        <div className="lg:col-span-3">
          <GlassCard className="p-8">
            {activeTab === 'profile' && (
              <motion.div
                key="profile"
                initial={{ opacity: 0, x: 20 }}
                animate={{ opacity: 1, x: 0 }}
                className="space-y-6"
              >
                <div>
                  <h2 className="text-2xl font-bold text-gray-900 dark:text-white mb-2">
                    Profile Information
                  </h2>
                  <p className="text-gray-600 dark:text-gray-400">
                    Update your personal information
                  </p>
                </div>

                <div className="space-y-6">
                  <Input
                    label="Full Name"
                    name="name"
                    type="text"
                    value={profileForm.name}
                    onChange={(e) => setProfileForm({ ...profileForm, name: e.target.value })}
                    icon={<FiUser />}
                  />

                  <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                      Email
                    </label>
                    <div className="px-4 py-3 bg-white/50 dark:bg-white/5 rounded-xl text-gray-600 dark:text-gray-400">
                      {profile?.email}
                    </div>
                    <p className="text-xs text-gray-500 dark:text-gray-500 mt-2">
                      Email cannot be changed for security reasons
                    </p>
                  </div>

                  <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                      Username
                    </label>
                    <div className="px-4 py-3 bg-white/50 dark:bg-white/5 rounded-xl text-gray-600 dark:text-gray-400">
                      @{profile?.username}
                    </div>
                    <p className="text-xs text-gray-500 dark:text-gray-500 mt-2">
                      Username cannot be changed
                    </p>
                  </div>

                  <Button onClick={handleSaveProfile} loading={saving} className="w-full">
                    <FiSave />
                    Save Changes
                  </Button>
                </div>
              </motion.div>
            )}

            {activeTab === 'security' && (
              <motion.div
                key="security"
                initial={{ opacity: 0, x: 20 }}
                animate={{ opacity: 1, x: 0 }}
                className="space-y-6"
              >
                <div>
                  <h2 className="text-2xl font-bold text-gray-900 dark:text-white mb-2">
                    Security Settings
                  </h2>
                  <p className="text-gray-600 dark:text-gray-400">
                    Manage your account security
                  </p>
                </div>

                <div className="space-y-6">
                  <div className="p-6 bg-smurf-500/10 border-2 border-smurf-500/20 rounded-xl">
                    <div className="flex items-start gap-4">
                      <div className="w-12 h-12 bg-smurf-500/20 rounded-full flex items-center justify-center flex-shrink-0">
                        <FiShield className="w-6 h-6 text-smurf-500" />
                      </div>
                      <div className="flex-1">
                        <h3 className="font-bold text-gray-900 dark:text-white mb-1">
                          Two-Factor Authentication
                        </h3>
                        <p className="text-sm text-gray-600 dark:text-gray-400 mb-4">
                          Add an extra layer of security to your account
                        </p>
                        <div className="flex items-center gap-4">
                          <button
                            onClick={() => {
                              setSecurityForm(prev => ({ 
                                ...prev, 
                                twoFactorEnabled: !prev.twoFactorEnabled 
                              }));
                              setToast({ 
                                show: true, 
                                message: `2FA ${!securityForm.twoFactorEnabled ? 'enabled' : 'disabled'}`, 
                                type: 'success' 
                              });
                            }}
                            className={`
                              relative inline-flex h-6 w-11 items-center rounded-full transition-colors
                              ${securityForm.twoFactorEnabled ? 'bg-smurf-500' : 'bg-gray-300 dark:bg-gray-600'}
                            `}
                          >
                            <span
                              className={`
                                inline-block h-4 w-4 transform rounded-full bg-white transition-transform
                                ${securityForm.twoFactorEnabled ? 'translate-x-6' : 'translate-x-1'}
                              `}
                            />
                          </button>
                          <span className="text-sm font-medium text-gray-900 dark:text-white">
                            {securityForm.twoFactorEnabled ? 'Enabled' : 'Disabled'}
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div className="border-t border-gray-200/20 dark:border-gray-800/20 pt-6">
                    <h3 className="font-bold text-gray-900 dark:text-white mb-4">
                      Change Password
                    </h3>
                    
                    <div className="space-y-4">
                      <Input
                        label="Current Password"
                        name="currentPassword"
                        type="password"
                        placeholder="••••••••"
                        icon={<FiLock />}
                        value={securityForm.currentPassword}
                        onChange={(e) => setSecurityForm({ ...securityForm, currentPassword: e.target.value })}
                      />

                      <Input
                        label="New Password"
                        name="newPassword"
                        type="password"
                        placeholder="••••••••"
                        icon={<FiLock />}
                        value={securityForm.newPassword}
                        onChange={(e) => setSecurityForm({ ...securityForm, newPassword: e.target.value })}
                      />

                      <Input
                        label="Confirm New Password"
                        name="confirmPassword"
                        type="password"
                        placeholder="••••••••"
                        icon={<FiLock />}
                        value={securityForm.confirmPassword}
                        onChange={(e) => setSecurityForm({ ...securityForm, confirmPassword: e.target.value })}
                      />

                      <Button 
                        onClick={() => setToast({ show: true, message: 'Password change feature coming soon', type: 'info' })}
                        className="w-full"
                      >
                        Update Password
                      </Button>
                    </div>
                  </div>
                </div>
              </motion.div>
            )}

            {activeTab === 'preferences' && (
              <motion.div
                key="preferences"
                initial={{ opacity: 0, x: 20 }}
                animate={{ opacity: 1, x: 0 }}
                className="space-y-6"
              >
                <div>
                  <h2 className="text-2xl font-bold text-gray-900 dark:text-white mb-2">
                    Preferences
                  </h2>
                  <p className="text-gray-600 dark:text-gray-400">
                    Customize your experience
                  </p>
                </div>

                <div className="space-y-6">
                  <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-3">
                      Theme
                    </label>
                    <div className="grid grid-cols-2 gap-4">
                      <button
                        onClick={() => setPreferencesForm({ ...preferencesForm, theme: 'light' })}
                        className={`
                          p-6 rounded-xl border-2 transition-all
                          ${preferencesForm.theme === 'light'
                            ? 'border-smurf-500 bg-smurf-500/10'
                            : 'border-gray-200/20 dark:border-gray-800/20 bg-white/20 dark:bg-white/5 hover:bg-white/30 dark:hover:bg-white/10'
                          }
                        `}
                      >
                        <FiSun className="w-8 h-8 mx-auto mb-2 text-yellow-500" />
                        <p className="font-medium text-gray-900 dark:text-white">Light</p>
                      </button>

                      <button
                        onClick={() => setPreferencesForm({ ...preferencesForm, theme: 'dark' })}
                        className={`
                          p-6 rounded-xl border-2 transition-all
                          ${preferencesForm.theme === 'dark'
                            ? 'border-smurf-500 bg-smurf-500/10'
                            : 'border-gray-200/20 dark:border-gray-800/20 bg-white/20 dark:bg-white/5 hover:bg-white/30 dark:hover:bg-white/10'
                          }
                        `}
                      >
                        <FiMoon className="w-8 h-8 mx-auto mb-2 text-blue-500" />
                        <p className="font-medium text-gray-900 dark:text-white">Dark</p>
                      </button>
                    </div>
                  </div>

                  <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-3">
                      Notification Preferences
                    </label>
                    <div className="space-y-2">
                      {(['all', 'important', 'none'] as const).map((pref) => (
                        <button
                          key={pref}
                          onClick={() => setPreferencesForm({ ...preferencesForm, notificationPref: pref })}
                          className={`
                            w-full p-4 rounded-xl border-2 transition-all text-left
                            ${preferencesForm.notificationPref === pref
                              ? 'border-smurf-500 bg-smurf-500/10'
                              : 'border-gray-200/20 dark:border-gray-800/20 bg-white/20 dark:bg-white/5 hover:bg-white/30 dark:hover:bg-white/10'
                            }
                          `}
                        >
                          <p className="font-medium text-gray-900 dark:text-white capitalize mb-1">
                            {pref === 'all' ? 'All Notifications' : pref === 'important' ? 'Important Only' : 'None'}
                          </p>
                          <p className="text-sm text-gray-600 dark:text-gray-400">
                            {pref === 'all' && 'Receive all notifications'}
                            {pref === 'important' && 'Only critical notifications'}
                            {pref === 'none' && 'Disable all notifications'}
                          </p>
                        </button>
                      ))}
                    </div>
                  </div>

                  <Button onClick={handleSavePreferences} loading={saving} className="w-full">
                    <FiSave />
                    Save Preferences
                  </Button>
                </div>
              </motion.div>
            )}
          </GlassCard>
        </div>
      </div>
    </div>
  );
}
