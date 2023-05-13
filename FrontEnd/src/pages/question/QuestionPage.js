import { Button, Typography, List, ListItem, ListItemText, Radio, RadioGroup, FormControlLabel, Dialog, DialogTitle, DialogContent, DialogActions, TextField, FormControl, Stack } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { Helmet } from 'react-helmet-async';
import { useParams, useNavigate } from 'react-router-dom';
import Iconify from '../../components/iconify/Iconify';
import questionAPI from '../../services/questionAPI';

export default function QuestionPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [questions, setQuestions] = useState([]);
  const [openDialog, setOpenDialog] = useState(false);
  const [dialogMode, setDialogMode] = useState('');
  const [dialogQuestion, setDialogQuestion] = useState('');
  const [dialogAnswers, setDialogAnswers] = useState([]);
  const [selectedQuestion, setSelectedQuestion] = useState(null);

  useEffect(() => {
    // Tạo dữ liệu mẫu
    // const sampleQuestions = [
    //   {
    //     id: 1,
    //     question: 'Đây là câu hỏi 1?',
    //     answers: [
    //       { id: 1, answer: '2', correct: false },
    //       { id: 2, answer: '3', correct: true },
    //       { id: 3, answer: '4', correct: false },
    //       { id: 4, answer: '5', correct: false },
    //     ],
    //   },
    //   {
    //     id: 2,
    //     question: 'Đây là câu hỏi 2?',
    //     answers: [
    //       { id: 5, answer: '2', correct: false },
    //       { id: 6, answer: '3', correct: false },
    //       { id: 7, answer: '4', correct: true },
    //       { id: 8, answer: '5', correct: false },
    //     ],
    //   },
    //   // Thêm các câu hỏi khác tại đây
    // ];
    // setQuestions(sampleQuestions)
    const fetchQuestion = async () => {
        const response = await questionAPI.getAll();
        setQuestions(response.data);
      };
      fetchQuestion();
  }, []);



  const handleEditQuestion = (question) => {

  };

  const handleDeleteQuestion = (question) => {
    // const updatedQuestions = questions.filter((q) => q.id !== question.id);
    // setQuestions(updatedQuestions);
  };



  return (
    <>
     <Helmet>
        <title> Question | Minimal UI </title>
      </Helmet>

        <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
          <Typography variant="h4" gutterBottom>
           Course / Exam / Question
          </Typography>
          <Button onClick={() => {  navigate(`/dashboard/questionNew/${id}`) }} variant="contained" startIcon={<Iconify icon="eva:plus-fill" />}>
            New quesiton
          </Button>
        </Stack>
      <List>
        {questions?.map((question) => (
             <ListItem key={question.id} sx={{ border: '1px solid', borderRadius: '4px', my: 2 }}>
             <ListItemText primary={`${question.id}. ${question.question}`} />
             <FormControl component="fieldset">
               <RadioGroup>
                    {question.answers.map((answer, index) => (
                        <FormControlLabel
                        key={answer.id}
                        value={answer.answer}
                        control={<Radio color="primary" />}
                        label={`${String.fromCharCode(65 + index)}. ${answer.answer}`}
                        disabled
                        sx={{
                            color: answer.correct ? 'green' : 'inherit',
                        }}
                        checked={answer.correct} // Kiểm tra câu trả lời đúng
                        />
                    ))}
                </RadioGroup>
             </FormControl>
             <Button variant="outlined" onClick={() => handleEditQuestion(question)}>Edit</Button>
             <Button variant="outlined" onClick={() => handleDeleteQuestion(question)}>Delete</Button>
           </ListItem>
         ))}
       </List>
 
       
     </>
   );
 }