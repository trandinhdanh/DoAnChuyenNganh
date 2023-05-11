import axios from 'axios';


const BASE_URL = 'http://localhost:8080';

const getAuthConfig = () => ({
    headers: {
        Authorization: `Bearer ${localStorage.getItem("access_token")}`
    }
})
export const login = async (credentials) => axios.post(`${BASE_URL}/api/v1/auth/login`, credentials);


