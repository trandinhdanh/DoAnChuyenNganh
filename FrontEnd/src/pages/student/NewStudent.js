import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Stack, TextField } from '@mui/material';
import { format } from 'date-fns';
import axios from 'axios';
import studentApi from '../../services/StudentAPI';

// import { TextField, Button } from '@material-ui/core';

export default function NewStudent() {
  const [name, setName] = useState('');
  const [gender, setGender] = useState('');
  const [birthday, setBirthday] = useState('');
  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    const newDate = format(new Date(birthday), 'MM/dd/yyyy')
    console.log(name, gender, newDate)
    const formData = new FormData();
    formData.append('fullName', name);
    formData.append('gender', gender);
    formData.append('birthday', newDate);
    setName('');
    setGender('');
    setBirthday('');
    try {
      const response = await studentApi.create(formData);
      console.log(response.data);
    } catch (error) {
      console.error("error create student", error);
    }
    navigate("/dashboard/student");
  };

  return (
    <>
      <h1>Add Student</h1>
      <form onSubmit={handleSubmit}>
        <TextField
          label="Name"
          variant="outlined"
          value={name}
          onChange={(e) => setName(e.target.value)}
          margin="normal"
          fullWidth
          required
        />
        <TextField
          label="Gender"
          variant="outlined"
          value={gender}
          onChange={(e) => setGender(e.target.value)}
          margin="normal"
          fullWidth
          required
        />
        <TextField
          label="Birthday"
          type="date"
          variant="outlined"
          value={birthday}
          onChange={(e) => setBirthday(e.target.value)}
          margin="normal"
          fullWidth
          required
          InputLabelProps={{
            shrink: true,
          }}
        />

        <Button type="submit" variant="contained" color="primary">
          Add Student
        </Button>
      </form>
    </>
  );
}