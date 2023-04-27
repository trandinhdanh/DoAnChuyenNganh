import { Button, TextField } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { format } from 'date-fns';
import teacherApi from '../services/TeacherAPI';

// import { TextField, Button } from '@material-ui/core';

export default function AddTeacherForm({ onAddTeacher }) {
  const [name, setName] = useState('');
  const [gender, setGender] = useState('');
  const [birthday, setBirthday] = useState('');
  const [position, setPosition] = useState('');
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    const newDate =  format(new Date(birthday), 'MM/dd/yyyy')
    console.log(name , gender , newDate , position)
    const formData = new FormData();
    formData.append('name', name);
    formData.append('gender', gender);
    formData.append('birthday', birthday);
    formData.append('position', position);
   
    // setName('');
    // setGender('');
    // setBirthday('');
    // setPosition('');
    
    teacherApi.create(formData).then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          });
    
  };

  return (
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
        Add Teacher
      </Button>
    </form>
  );
}