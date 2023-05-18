import { Helmet } from 'react-helmet-async';
import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
// @mui
import { Stack, Container, Typography, Card, CardContent, CardMedia, Chip } from '@mui/material';
// components
import courseAPI from '../../services/courseAPI';
import examAPI from '../../services/examAPI';
import Iconify from '../../components/iconify/Iconify';

export default function GuestExamPage() {
  const [exams, setExams] = useState([]);
  const { id } = useParams()
  useEffect(() => {
    const fetchExams = async () => {
      try {
        const data = await examAPI.getAll(id);
        setExams(data);
        console.log(data);
      } catch (error) {
        console.error('Failed to fetch courses:', error);
      }
    };

    fetchExams();
  }, []);

  return (
    <>
      <Helmet>
        <title>Exams | Minimal UI</title>
      </Helmet>

      <Container>
        <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
          <Typography variant="h4" gutterBottom>
            My Exams
          </Typography>
        </Stack>

        {exams.map((exam) => (
          <Card key={exam.id} sx={{ display: 'flex', marginBottom: 2 }}>
            <CardMedia
              component="img"
              sx={{ width: 200, objectFit: 'cover' }}
              image="https://prod-discovery.edx-cdn.org/media/course/image/0e575a39-da1e-4e33-bb3b-e96cc6ffc58e-8372a9a276c1.png"
              alt={exam.name}
            />
            <CardContent sx={{ width: '100%' }}>
              <Stack
                direction="row"
                justifyContent="space-between"
                alignItems="center"
                spacing={2}
              >
                <Typography variant="body1" component="div">
                  <Link to={`/dashboard/questionStudent/${exam.id}`}>{exam.name}</Link>
                </Typography>

                <Chip color="success" label='success' icon={<Iconify icon="teenyicons:tick-circle-outline" />} />

              </Stack>
            </CardContent>
          </Card>
        ))}
      </Container>
    </>
  );
}
