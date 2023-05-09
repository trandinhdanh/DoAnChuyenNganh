import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
// @mui
import { Link, Stack, IconButton, InputAdornment, TextField, Checkbox, FormControlLabel, FormControl, FormLabel, FormHelperText, Button } from '@mui/material';
import { LoadingButton } from '@mui/lab';
// components
import Iconify from '../../../components/iconify';
import { useAuth } from "../../../context/AuthContext";
// ----------------------------------------------------------------------


export default function LoginForm() {

  const navigate = useNavigate();
  const { login, getScopes } = useAuth();
  const adminDefaultPath = "/dashboard/app";
  const teacherDefaultPath = "/dashboard/student";
  const studentDefaultPath = "/dashboard/blog";

  const [values, setValues] = useState({
    username: '',
    password: '',
  });

  const { username, password } = values;

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
      console.log("login success");
      if (getScopes().includes("ADMIN")) {
        navigate(adminDefaultPath)
      }
      else if (getScopes().includes("TEACHER")) {
        navigate(teacherDefaultPath)
      }
      else if (getScopes().includes("STUDENT")) {
        navigate(studentDefaultPath)
      }
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

        <Stack direction="row" alignItems="center" justifyContent="space-between" sx={{ my: 2 }}>
          <FormControlLabel control={<Checkbox defaultChecked name="remember" />} label="Remember me" />

          <Link variant="subtitle2" underline="hover">
            Forgot password?
          </Link>
        </Stack>

        <LoadingButton fullWidth size="large" type="submit" variant="contained"  >
          Login
        </LoadingButton>
      </form>
    </>
  );
}
