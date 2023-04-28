import { Button, TextField, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { format } from 'date-fns';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import teacherApi from '../services/TeacherAPI';

// import { TextField, Button } from '@material-ui/core';

export default function UpdateTeacher() {
  const {id} = useParams();
  const [name, setName] = useState('');
  const [gender, setGender] = useState('');
  const [birthday, setBirthday] = useState('');
  const [position, setPosition] = useState('');
  const [teacherUpdate, setTeacherUpdate] = useState({});
  
  useEffect(() => { 
    console.log(id);
    teacherApi.getById(id).then((res) => {
      setTeacherUpdate(res.data)
          })
          .catch((err) => {
            console.log(err);
          });
   },[id, teacherUpdate])
  const handleSubmit = async (e) => {
    e.preventDefault();
    const newDate =  format(new Date(birthday), 'MM/dd/yyyy')
    console.log(name , gender , newDate , position)
    const formData = new FormData();
    formData.append('fullName', name);
    formData.append('gender', gender);
    formData.append('birthday', newDate);
    formData.append('position', position);
    setName('');
    setGender('');
    setBirthday('');
    setPosition('');
    
    const response = await axios.put(`http://localhost:8027/teacher/${id}`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    
    console.log(response.data);
  };

  return (
   <> 
        <h1>Update Teacher</h1>
        <form onSubmit={handleSubmit}>
         
      <TextField
        label="Name"
        variant="outlined"
        value={teacherUpdate.fullName || ""}
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
   </>
  );
}