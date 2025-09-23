const axios = require('axios');

const API_BASE_URL = 'http://localhost:3001/api';

// Test data
const testUser = {
  name: 'Test User',
  email: 'test@example.com',
  password: 'password123'
};

const testTask = {
  title: 'Test Task',
  description: 'This is a test task',
  priority: 'HIGH',
  dueDate: new Date(Date.now() + 7 * 24 * 60 * 60 * 1000).toISOString() // 7 days from now
};

const testCategory = {
  name: 'Test Category',
  color: '#FF5733'
};

let authToken = '';
let userId = '';
let taskId = '';
let categoryId = '';

async function testAPI() {
  console.log('🚀 Starting API Tests...\n');

  try {
    // Test 1: Health Check
    console.log('1. Testing Health Check...');
    const healthResponse = await axios.get('http://localhost:3001/health');
    console.log('✅ Health Check:', healthResponse.data);
    console.log('');

    // Test 2: Register User
    console.log('2. Testing User Registration...');
    try {
      const registerResponse = await axios.post(`${API_BASE_URL}/auth/register`, testUser);
      console.log('✅ User Registration:', registerResponse.data);
      authToken = registerResponse.data.data.token;
      userId = registerResponse.data.data.user.id;
    } catch (error) {
      if (error.response?.status === 400 && error.response?.data?.error?.includes('already exists')) {
        console.log('ℹ️  User already exists, trying to login...');
        const loginResponse = await axios.post(`${API_BASE_URL}/auth/login`, {
          email: testUser.email,
          password: testUser.password
        });
        console.log('✅ User Login:', loginResponse.data);
        authToken = loginResponse.data.data.token;
        userId = loginResponse.data.data.user.id;
      } else {
        throw error;
      }
    }
    console.log('');

    // Test 3: Get Current User
    console.log('3. Testing Get Current User...');
    const userResponse = await axios.get(`${API_BASE_URL}/auth/me`, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Get Current User:', userResponse.data);
    console.log('');

    // Test 4: Create Category
    console.log('4. Testing Category Creation...');
    const categoryResponse = await axios.post(`${API_BASE_URL}/categories`, testCategory, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Category Creation:', categoryResponse.data);
    categoryId = categoryResponse.data.data.id;
    console.log('');

    // Test 5: Get Categories
    console.log('5. Testing Get Categories...');
    const categoriesResponse = await axios.get(`${API_BASE_URL}/categories`, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Get Categories:', categoriesResponse.data);
    console.log('');

    // Test 6: Create Task
    console.log('6. Testing Task Creation...');
    const taskData = { ...testTask, categoryIds: [categoryId] };
    const taskResponse = await axios.post(`${API_BASE_URL}/tasks`, taskData, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Task Creation:', taskResponse.data);
    taskId = taskResponse.data.data.id;
    console.log('');

    // Test 7: Get Tasks
    console.log('7. Testing Get Tasks...');
    const tasksResponse = await axios.get(`${API_BASE_URL}/tasks`, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Get Tasks:', tasksResponse.data);
    console.log('');

    // Test 8: Update Task
    console.log('8. Testing Task Update...');
    const updateData = { status: 'IN_PROGRESS', title: 'Updated Test Task' };
    const updateResponse = await axios.put(`${API_BASE_URL}/tasks/${taskId}`, updateData, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Task Update:', updateResponse.data);
    console.log('');

    // Test 9: Get Task Stats
    console.log('9. Testing Task Stats...');
    const statsResponse = await axios.get(`${API_BASE_URL}/tasks/stats/overview`, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Task Stats:', statsResponse.data);
    console.log('');

    // Test 10: Get Dashboard
    console.log('10. Testing Dashboard...');
    const dashboardResponse = await axios.get(`${API_BASE_URL}/users/dashboard`, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Dashboard:', dashboardResponse.data);
    console.log('');

    // Test 11: Update User Profile
    console.log('11. Testing User Profile Update...');
    const profileUpdateResponse = await axios.put(`${API_BASE_URL}/users/profile`, {
      name: 'Updated Test User'
    }, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Profile Update:', profileUpdateResponse.data);
    console.log('');

    // Test 12: Delete Task
    console.log('12. Testing Task Deletion...');
    const deleteTaskResponse = await axios.delete(`${API_BASE_URL}/tasks/${taskId}`, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Task Deletion:', deleteTaskResponse.data);
    console.log('');

    // Test 13: Delete Category
    console.log('13. Testing Category Deletion...');
    const deleteCategoryResponse = await axios.delete(`${API_BASE_URL}/categories/${categoryId}`, {
      headers: { Authorization: `Bearer ${authToken}` }
    });
    console.log('✅ Category Deletion:', deleteCategoryResponse.data);
    console.log('');

    console.log('🎉 All API tests passed successfully!');
    console.log('📊 Summary:');
    console.log('   - Health Check: ✅');
    console.log('   - User Registration/Login: ✅');
    console.log('   - Authentication: ✅');
    console.log('   - Category CRUD: ✅');
    console.log('   - Task CRUD: ✅');
    console.log('   - Dashboard: ✅');
    console.log('   - Profile Management: ✅');

  } catch (error) {
    console.error('❌ Test failed:', error.response?.data || error.message);
    process.exit(1);
  }
}

// Run tests
testAPI();