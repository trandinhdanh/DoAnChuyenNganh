import axios from 'axios';

const BASE_URL = 'http://localhost:8027';

const teacherApi = {
  getAll: async () => {
    const response = await axios.get(`${BASE_URL}/teacher/list`);
    console.log(response.data)
    return response.data;
  },

  getById: async (id) => {
    const response = await axios.get(`${BASE_URL}/teachers/${id}`);
    return response.data;
  },

  create: async (teacher) => {
    const response = await axios.post(`${BASE_URL}/teacher`, teacher);
    return response.data;
  },
  update: async (id, teacher) => {
    const response = await axios.put(`${BASE_URL}/teachers/${id}`, teacher);
    return response.data;
  },

  delete: async (id) => {
    const response = await axios.delete(`${BASE_URL}/teachers/${id}`);
    return response.data;
  }
};

export default teacherApi;