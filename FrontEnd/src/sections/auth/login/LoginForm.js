import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import * as Yup from 'yup';
// @mui
import { Link, Stack, IconButton, InputAdornment, TextField, Checkbox, FormControlLabel, FormControl, FormLabel, FormHelperText, Button } from '@mui/material';
import { LoadingButton } from '@mui/lab';
// components
import Iconify from '../../../components/iconify';
import { useAuth } from "../../../context/AuthContext";

// import {  login as performLogin } from "../../../services/client";
// import { request, setAuthHeader } from '../../../services/helpers/axios_helper';
// ----------------------------------------------------------------------


export default function LoginForm() {

  const navigate = useNavigate();
  const { login } = useAuth();
  const { logout } = useAuth();

//   const logout = () => {
//     localStorage.removeItem("auth_token");
//     console.log("logout success")
//     // setUser(null)
// }
// const setAuthHeader = (token) => {
//   window.localStorage.setItem('auth_token', token);
// };

  const [values, setValues] = useState({
    username: '',
    password: '',
  });

  const{ username, password } = values;
  // const [errors, setErrors] = useState({});
  // const [touched, setTouched] = useState({});

  const handleChange = (event) => {
    const { name, value } = event.target;
    setValues({
      ...values,
      [name]: value,
    });
  };


  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await login(values);
      console.log("login success")
      navigate('/dashboard/app');
      // Handle successful login
    } catch (error) {
      console.log("login error", error)
      // Handle login error
    }
  };

  const [showPassword, setShowPassword] = useState(false);

  return (
    <>
      <form onSubmit={handleSubmit}>
        <Stack spacing={3}>
          
            <TextField
              id="username"
              name="username"
              type="text"
              placeholder="hello@gmail.com"
              value={username}
              onChange={handleChange}
          />
           
            <TextField
              id="password"
              name="password"
              placeholder="password123"
              value={password}
              type={showPassword ? 'text' : 'password'}
              onChange={handleChange}
              InputProps={{
                endAdornment: (
                  <InputAdornment position="end">
                    <IconButton onClick={() => setShowPassword(!showPassword)} edge="end">
                      <Iconify icon={showPassword ? 'eva:eye-fill' : 'eva:eye-off-fill'} />
                    </IconButton>
                  </InputAdornment>
                ),
              }}

            />
         

        </Stack>

        {/* <Stack direction="row" alignItems="center" justifyContent="space-between" sx={{ my: 2 }}>
          <FormControlLabel control={<Checkbox defaultChecked name="remember" />} label="Remember me" />

          <Link variant="subtitle2" underline="hover">
            Forgot password?
          </Link>
        </Stack> */}

        <LoadingButton fullWidth size="large" type="submit" variant="contained"  >
          Login
        </LoadingButton>
      </form>
      <Button onClick={() => logout()}>Logout</Button>
    </>
  );
}
