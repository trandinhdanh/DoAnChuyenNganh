import { Helmet } from 'react-helmet-async';
import { useEffect, useState } from 'react';
// @mui
import { Grid, Button, Container, Stack, Typography } from '@mui/material';
// components
import { BlogPostCard, BlogPostsSort, BlogPostsSearch } from '../sections/@dashboard/blog';
// mock
import CourseList from '../components/course-list/index';
import courseAPI from '../services/courseAPI';
// ----------------------------------------------------------------------

const SORT_OPTIONS = [
  { value: 'latest', label: 'Latest' },
  { value: 'popular', label: 'Popular' },
  { value: 'oldest', label: 'Oldest' },
];

// ----------------------------------------------------------------------

export default function MyCourse() {

    const [data,setData] = useState([])

    useEffect(()=>{
        (async ()=>{
            const response = await courseAPI.getAll()
            setData(response);
        })()
    },[])
    
    

  return (
    <>
      <Helmet>
        <title> Dashboard: Blog | Minimal UI </title>
      </Helmet>
      <Container>
        <Grid container spacing={3}>
        <CourseList data={data}/>
        </Grid>
      </Container>
    </>
  );
}
