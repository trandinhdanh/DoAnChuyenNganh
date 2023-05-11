import { Button, TextField } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { format } from 'date-fns';
import { useNavigate, useParams } from 'react-router-dom';
import studentApi from '../../services/StudentAPI';

export default function UpdateStudent() {
  const {id} = useParams();
  const [name, setName] = useState('');
  const [gender, setGender] = useState('');
  const [birthday, setBirthday] = useState('');
  const [studentUpdate, setStudentUpdate] = useState({});
  const navigate = useNavigate();

  useEffect(() => { 
    console.log(id);
    studentApi.getById(id).then((res) => {
      console.log(res);
      setStudentUpdate(res);

      setName(res.fullName);
      setGender(res.gender);
      setBirthday(res.birthday);
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
    try{
    const response = await studentApi.update(id, formData);
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }

  navigate("/dashboard/teacher");
  };

  return (
   <>
        <h1>Update Student</h1>
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
        Update Student
      </Button>
    </form>
   </>
  );
}