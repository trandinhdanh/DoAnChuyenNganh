import axios from 'axios';

const BASE_URL = 'http://localhost:8080';


const getAuthConfig = () => ({
  headers: {
      Authorization: `Bearer ${localStorage.getItem("access_token")}`
  }
});

const teacherApi = {
  getAll: async () => {
    const response = await axios.get(`${BASE_URL}/api/v1/teacher/list`, getAuthConfig());
    console.log(response.data)
    return response.data;
  },

  getById: async (id) => {
    const response = await axios.get(`${BASE_URL}/api/v1/teacher/${id}`, getAuthConfig());
    return response.data;
  },

  create: async (teacher) => {
    const response = await axios.post(`${BASE_URL}/api/v1/teacher`, teacher,  {
                  ...getAuthConfig(),
                  'Content-Type': 'multipart/form-data'
    });
    return response.data;
  },


  update: async (id, teacher) => {
    const response = await axios.put(`${BASE_URL}/api/v1/teacher/${id}`, teacher, {
      ...getAuthConfig(),
      'Content-Type': 'multipart/form-data'
});
    return response.data;
  },

  delete: async (id) => {
    const response = await axios.delete(`${BASE_URL}/api/v1/teacher/${id}`, getAuthConfig());
    return response;
  }
};

export default teacherApi;