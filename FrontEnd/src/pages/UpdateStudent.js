import { Button, TextField } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { format } from 'date-fns';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import teacherApi from '../services/TeacherAPI';

// import { TextField, Button } from '@material-ui/core';

export default function UpdateStudent() {
  const {id} = useParams();
  const [name, setName] = useState('');
  const [gender, setGender] = useState('');
  const [birthday, setBirthday] = useState('');
  const [teacherUpdate, setTeacherUpdate] = useState({});
  
  useEffect(() => { 
    console.log(id);
    teacherApi.getById(id).then((res) => {
      console.log(res);
      setTeacherUpdate(res.data)
          })
          .catch((err) => {
            console.log(err);
          });
   },[])
  const handleSubmit = async (e) => {
    e.preventDefault();
    const newDate =  format(new Date(birthday), 'MM/dd/yyyy')
    console.log(name , gender , newDate )
    const formData = new FormData();
    formData.append('fullName', name);
    formData.append('gender', gender);
    formData.append('birthday', newDate);
    setName('');
    setGender('');
    setBirthday('');
    
    const response = await axios.put(`http://localhost:8027/student/${id}`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    
    console.log(response.data);
  };

  return (
   <>
        <h1>Update Student</h1>
        <form onSubmit={handleSubmit}>
      <TextField
        label="Name"
        variant="outlined"
        value={teacherUpdate?.fullName}
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
        Update Student
      </Button>
    </form>
   </>
  );
}