import { Helmet } from 'react-helmet-async';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
// @mui
import { Stack, Container, Typography, Grid, Card, CardContent, CardMedia } from '@mui/material';
// components
import courseAPI from '../../services/courseAPI';

export default function GuestCoursePage() {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    const userId = localStorage.getItem('user');
    console.log(userId);
    // const fetchCourses = async (id) => {
    //   try {
    //     const data = await courseAPI.getCourseByUser(id);
    //     setCourses(data);
    //   } catch (error) {
    //     console.error('Failed to fetch courses:', error);
    //   }
    // };

    // fetchCourses();
  }, []);

  return (
    <>
      <Helmet>
        <title>Course | Minimal UI</title>
      </Helmet>

      <Container>
        <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
          <Typography variant="h4" gutterBottom>
            My Courses
          </Typography>
        </Stack>

        <Grid container spacing={2}>
          {courses.map((course) => (
            <Grid item key={course.id} xs={4}>
              <Card sx={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
                <CardMedia
                  component="img"
                  sx={{ width: '100%', objectFit: 'cover' }}
                  image="https://leverageedublog.s3.ap-south-1.amazonaws.com/blog/wp-content/uploads/2019/10/23170101/List-of-Professional-Courses-after-Graduation.gif"
                  alt={course.name}
                />
                <CardContent sx={{ flexGrow: 1 }}>
                  <Typography variant="body1" align="center" sx={{ fontWeight: 'bold' }}>
                    <Link to={`/dashboard/courseStudent/${course.id}`}>{course.name}</Link>
                  </Typography>
                  {/* Add additional course details if needed */}
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>
    </>
  );
}

             
