import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

const getAuthConfig = () => ({
  headers: {
      Authorization: `Bearer ${localStorage.getItem("access_token")}`
  }
});
const questionAPI = {
  getAll: async () => {
    const response = await axios.get(`${BASE_URL}/api/v1/question/list`, getAuthConfig());
    console.log(response.data)
    return response.data;
  },

  getById: async (id) => {
    const response = await axios.get(`${BASE_URL}/api/v1/question/${id}`, getAuthConfig());
    return response.data;
  },
  getByIdExam: async (id) => {
    const response = await axios.get(`${BASE_URL}/api/v1/question/exam/${id}`, getAuthConfig());
    return response.data;
  },
  create: async (idExam,question) => {
    const response = await axios.post(`${BASE_URL}/api/v1/question/${idExam}`, question,  {
      ...getAuthConfig(),
      'Content-Type': 'multipart/form-data'
});
    return response.data;
  },
  update: async ( id,question) => {
    const response = await axios.put(`${BASE_URL}/api/v1/question/${id}`, question,  {
      ...getAuthConfig(),
      'Content-Type': 'multipart/form-data'
});
    return response.data;
  },

  delete: async (id) => {
    const response = await axios.delete(`${BASE_URL}/api/v1/question/${id}`, getAuthConfig());
    return response.data;
  }
};

export default questionAPI;