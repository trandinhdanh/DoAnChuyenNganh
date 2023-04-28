import axios from 'axios';

const BASE_URL = 'http://localhost:8027';

const studentApi = {
  getAll: async () => {
    const response = await axios.get(`${BASE_URL}/student/list`);
    console.log(response.data)
    return response.data;
  },

  getById: async (id) => {
    const response = await axios.get(`${BASE_URL}/student/${id}`);
    return response.data;
  },

  create: async (student) => {
    const response = await axios.post(`${BASE_URL}/student`, student);
    return response.data;
  },
  update: async (id, student) => {
    const response = await axios.put(`${BASE_URL}/student/${id}`, student);
    return response.data;
  },

  delete: async (id) => {
    const response = await axios.delete(`${BASE_URL}/student/${id}`);
    return response.data;
  }
};

export default studentApi;