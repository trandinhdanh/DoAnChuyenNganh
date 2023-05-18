import { Helmet } from 'react-helmet-async';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
// @mui
import { Grid, Button, Container, Stack, Typography } from '@mui/material';
// components
import { BlogPostCard, BlogPostsSort, BlogPostsSearch } from '../sections/@dashboard/blog';
// mock
import POSTS from '../_mock/blog';
import QuestionGroup from '../components/question-group';
import Timer from '../components/count-down';
import ExamTab from '../components/exam-tab';
import examAPI from '../services/examAPI';
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------

export default function ListExam() {
  const { id } = useParams();
  const [exams, setExams] = useState([]);
  const [examsComplete, setExamsComplete] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await examAPI.getAll(id);
        setExams(response);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, [id]);
  const handleCompleteExam = (examId) => {
   setExamsComplete((prevExamsCompleted) => [...prevExamsCompleted, examId]);
  };

  const getUncompletedExams = () => {
    return exams.filter((exam) => !examsComplete.includes(exam.id));
  };

  return (
    <>
      <Helmet>
        <title> Dashboard: Blog | Minimal UI </title>
      </Helmet>
      <Container>
        <Grid>
          <ExamTab exams={exams} onCompleteExam={handleCompleteExam} uncompletedExams={getUncompletedExams()} />
        </Grid>
      </Container>
    </>
  );
}
// ----------------------------------------------------------------
// import { Helmet } from 'react-helmet-async';
// import { useEffect , useState} from 'react';
// import { useParams } from 'react-router-dom';
// // @mui
// import { Grid, Button, Container, Stack, Typography } from '@mui/material';
// // components
// import { BlogPostCard, BlogPostsSort, BlogPostsSearch } from '../sections/@dashboard/blog';
// // mock
// import POSTS from '../_mock/blog';
// import QuestionGroup from '../components/question-group';
// import Timer from '../components/count-down';
// import ExamTab from "../components/exam-tab";
// // ----------------------------------------------------------------------

// // ----------------------------------------------------------------------

// export default function ListExam() {
//     // id của khoá học

//     return (
//         <>
//             <Helmet>
//                 <title> Dashboard: Blog | Minimal UI </title>
//             </Helmet>
//             <Container>
//                 <Grid >
//                     <ExamTab/>
//                 </Grid >
//             </Container>
//         </>
//     );
// }