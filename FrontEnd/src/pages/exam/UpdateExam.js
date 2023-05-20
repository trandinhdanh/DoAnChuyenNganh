import { Button, TextField } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { format } from 'date-fns';
import { useNavigate, useParams } from 'react-router-dom';
import courseAPI from '../../services/courseAPI';
import examAPI from '../../services/examAPI';

export default function UpdateExam() {
  const {id} = useParams();
  const [nameExam, setNameExam] = useState('');

  const navigate = useNavigate();

  useEffect(() => {
    const fetchTeachers = async () => {
      const data = await examAPI.getById(id);
      setNameExam(data.name);
      console.log(data.name);
    };
    fetchTeachers();
  }, []);
  const handleSubmit = async (e) => {
    e.preventDefault();
    try{
    const response = await examAPI.update( {name : nameExam},id);
    window.location.reload();
    console.log(response);
  } catch (error) {
    console.error(error);
  }
  // navigate("/dashboard/exam");
  };

  return (
   <>
        <h1>Update Exam</h1>
        <form onSubmit={handleSubmit}>
      <TextField
        label="Name"
        variant="outlined"
        value={nameExam}
        onChange={(e) => setNameExam(e.target.value)}
        margin="normal"
        fullWidth
        required
      />
      <Button type="submit" variant="contained" color="primary">
        Update Exam
      </Button>
    </form>
   </>
  );
}