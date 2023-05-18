import { Helmet } from 'react-helmet-async';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

// @mui
import { Grid, Button, Container, Stack, Typography } from '@mui/material';
// components
import { BlogPostCard, BlogPostsSort, BlogPostsSearch } from '../sections/@dashboard/blog';
// mock
import POSTS from '../_mock/blog';
import QuestionGroup from '../components/question-group';
import Timer from '../components/count-down';
import examAPI from '../services/examAPI';
import questionAPI from '../services/questionAPI';
// ----------------------------------------------------------------------

const SORT_OPTIONS = [
  { value: 'latest', label: 'Latest' },
  { value: 'popular', label: 'Popular' },
  { value: 'oldest', label: 'Oldest' },
];

// ----------------------------------------------------------------------

// export default function MyExam() {
//   // id của khoá học
//   const { id } = useParams();
//   console.log(`Lấy danh sách câu hỏi từ id=${id}`);
//   const data = [
//     {
//       question: "What is 10/2?",
//       answers: {
//         a: '3',
//         b: '5',
//         c: '115'
//       },
//       correctAnswer: 'b'
//     },
//     {
//       question: "What is 10/2?",
//       answers: {
//         a: '3',
//         b: '5',
//         c: '115'
//       },
//       correctAnswer: 'b'
//     },
//     {
//       question: "What is 10/2?",
//       answers: {
//         a: '3',
//         b: '5',
//         c: '115'
//       },
//       correctAnswer: 'b'
//     },
//     {
//       question: "What is 10/2?",
//       answers: {
//         a: '3',
//         b: '5',
//         c: '115'
//       },
//       correctAnswer: 'b'
//     },
//     {
//       question: "What is 10/2?",
//       answers: {
//         a: '3',
//         b: '5',
//         c: '115'
//       },
//       correctAnswer: 'b'
//     },
//   ]

//   useEffect(() => {

//   });


//   return (
//     <>
//       <Helmet>
//         <title> Dashboard: Blog | Minimal UI </title>
//       </Helmet>
//       <Container>
//         Working time <Timer initialMinute={30} />
//         <Grid >
//           {data.map((item, idx) => {
//             return <QuestionGroup data={item} key={idx} />
//           })}
//           <div style={{ textAlign: 'center' }}>
//             <Button variant="contained">Submit</Button>
//           </div>
//         </Grid>
//       </Container>
//     </>
//   );
// }

// ---------------------------------------------------------------------
export default function MyExam() {
  const { id } = useParams();
  const [questions, setQuestions] = useState([]);

  useEffect(() => {
    const fetchExamQuestions = async () => {
      try {
        const response = await questionAPI.getAll(id);
        console.log(response); // In ra response để kiểm tra dữ liệu trả về
        setQuestions(response); // Cập nhật danh sách câu hỏi trong state của component
      } catch (error) {
        console.error(error);
      }
    };

    fetchExamQuestions();
  }, [id]);

  return (
    <>
      <Helmet>
        <title>Dashboard: Blog | Minimal UI</title>
      </Helmet>
      <Container>
        {/* Working time <Timer initialMinute={30} /> */}
        <Grid>
          {questions.map((item, idx) => (
            <QuestionGroup data={item} key={idx} />
          ))}
          <div style={{ textAlign: 'center' }}>
            {/* <Button variant="contained">Submit</Button> */}
          </div>
        </Grid>
      </Container>
    </>
  );
}