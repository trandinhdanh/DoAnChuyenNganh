import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

const getAuthConfig = () => ({
  headers: {
      Authorization: `Bearer ${localStorage.getItem("access_token")}`
  }
});
const examAPI = {
  getAll: async (idCourse) => {
    const response = await axios.get(`${BASE_URL}/api/v1/exam/course/${idCourse}`, getAuthConfig());
    console.log(response.data)
    return response.data;
  },

  getStudents: async (id) => {
    const response = await axios.get(`${BASE_URL}/api/v1/exam/${id}/students`, getAuthConfig());
    console.log(response.data)
    return response.data;
  },
  getById: async (id) => {
    const response = await axios.get(`${BASE_URL}/api/v1/exam/${id}`, getAuthConfig());
    return response.data;
  },

  create: async (exam,id) => {
    const response = await axios.post(`${BASE_URL}/api/v1/exam/${id}`, exam,  {
      ...getAuthConfig(),
      'Content-Type': 'multipart/form-data'
});
    return response.data;
  },
  update: async ( exam,id) => {
    const response = await axios.put(`${BASE_URL}/api/v1/exam/${id}`, exam,  {
      ...getAuthConfig(),
      'Content-Type': 'multipart/form-data'
});
    return response.data;
  },

  delete: async (id) => {
    const response = await axios.delete(`${BASE_URL}/api/v1/exam/${id}`, getAuthConfig());
    return response.data;
  },
  submit: async (idUser,idExam,score) => {
    const response = await axios.put(`${BASE_URL}/api/v1/submit/${idUser}/${idExam}`,score, {
      ...getAuthConfig(),
      'Content-Type': 'multipart/form-data'
});
    return response.data;
  }
};

export default examAPI;