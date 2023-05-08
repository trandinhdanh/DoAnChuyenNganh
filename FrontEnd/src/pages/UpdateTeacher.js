import { Button, Container, TextField, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { format } from 'date-fns';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import teacherApi from '../services/TeacherAPI';

// import { TextField, Button } from '@material-ui/core';

export default function UpdateTeacher() {
  const navigate = useNavigate();
  const { id } = useParams(); // Standardized variable name
  const [name, setName] = useState('');
  const [gender, setGender] = useState('');
  const [birthday, setBirthday] = useState('');
  const [position, setPosition] = useState('');
  const [teacher, setTeacher] = useState({}); // Renamed teacherUpdate to teacher

  useEffect(() => {
    teacherApi.getById(id)
      .then((res) => {
        setTeacher(res); // Renamed setTeacherUpdate to setTeacher
        setName(res.fullName);
        setGender(res.gender);
        setBirthday(res.birthday);
        setPosition(res.position);
      })
      .catch((err) => {
        console.error(err);
      });
  }, [id]); // Added id to dependency array

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newDate = format(new Date(birthday), 'MM/dd/yyyy');
    const formData = new FormData();
    formData.append('fullName', name);
    formData.append('gender', gender);
    formData.append('birthday', newDate);
    formData.append('position', position);

    setName('');
    setGender('');
    setBirthday('');
    setPosition('');

    try {
      const response = await axios.put(`http://localhost:8027/teacher/${id}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      console.log(response.data);
    } catch (error) {
      console.error(error);
    }

    navigate("/dashboard/teacher");
  };

  return (
    <Container>
      <h1>Update Teacher</h1>
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
        <TextField
          label="Position"
          variant="outlined"
          value={position}
          onChange={(e) => setPosition(e.target.value)}
          margin="normal"
          fullWidth
          required
        />
        <Button type="submit" variant="contained" color="primary">
          Update Teacher
        </Button>
      </form>
    </Container>
  );
}
