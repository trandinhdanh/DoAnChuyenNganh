import { Helmet } from 'react-helmet-async';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
// @mui
import { Grid, Button, Container, Stack, Typography } from '@mui/material';
// components
import { BlogPostCard, BlogPostsSort, BlogPostsSearch } from '../sections/@dashboard/blog';
// mock
import POSTS from '../_mock/blog';
import QuestionGroup from '../components/question-group';
import Timer from '../components/count-down';
import ExamTab from "../components/exam-tab";
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------

export default function ListExam() {
    // id của khoá học

    return (
        <>
            <Helmet>
                <title> Dashboard: Blog | Minimal UI </title>
            </Helmet>
            <Container>
                <Grid >
                    <ExamTab/>
                </Grid >
            </Container>
        </>
    );
}