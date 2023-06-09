import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

const getAuthConfig = () => ({
  headers: {
      Authorization: `Bearer ${localStorage.getItem("access_token")}`
  }
});

const studentApi = {
  getAll: async () => {
    const response = await axios.get(`${BASE_URL}/api/v1/student/list`, getAuthConfig());
    console.log(response.data)
    return response.data;
  },

  getById: async (id) => {
    const response = await axios.get(`${BASE_URL}/api/v1/student/${id}`, getAuthConfig());
    return response.data;
  },

  create: async (student) => {
    const response = await axios.post(`${BASE_URL}/api/v1/student`, student,  {
      ...getAuthConfig(),
      'Content-Type': 'multipart/form-data'
});
    return response.data;
  },
  update: async (id, student) => {
    const response = await axios.put(`${BASE_URL}/api/v1/student/${id}`, student,  {
      ...getAuthConfig(),
      'Content-Type': 'multipart/form-data'
});
    return response.data;
  },

  delete: async (id) => {
    const response = await axios.delete(`${BASE_URL}/api/v1/student/${id}`, getAuthConfig());
    return response.data;
  }
};

export default studentApi;