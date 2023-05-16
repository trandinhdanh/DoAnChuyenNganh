import { Button, TextField } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { format } from 'date-fns';
import { useNavigate, useParams } from 'react-router-dom';
import courseAPI from '../../services/courseAPI';

export default function UpdateCourseProcess() {
  const {id} = useParams();
  const [nameCourse, setNameCourse] = useState('');

  const navigate = useNavigate();

  useEffect(() => {
    const fetchTeachers = async () => {
      const data = await courseAPI.getById(id);
      setNameCourse(data.name);
      console.log(data.name);
    };
    fetchTeachers();
  }, []);
  const handleSubmit = async (e) => {
    e.preventDefault();
    try{
    const response = await courseAPI.update(id, {name : nameCourse});
    console.log(response);
  } catch (error) {
    console.error(error);
  }
  navigate("/dashboard/course");
  };

  return (
   <>
        <h1>Update Course</h1>
        <form onSubmit={handleSubmit}>
      <TextField
        label="Name"
        variant="outlined"
        value={nameCourse}
        onChange={(e) => setNameCourse(e.target.value)}
        margin="normal"
        fullWidth
        required
      />
      <Button type="submit" variant="contained" color="primary">
        Update Course
      </Button>
    </form>
   </>
  );
}