import { Button, Typography, List, ListItem, ListItemText, Radio, RadioGroup, FormControlLabel, Dialog, DialogTitle, DialogContent, DialogActions, TextField, FormControl, Stack } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { Helmet } from 'react-helmet-async';
import { useParams, useNavigate } from 'react-router-dom';
import Iconify from '../../components/iconify/Iconify';
import questionAPI from '../../services/questionAPI';
import examAPI from '../../services/examAPI';

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
    //       { id: 1, answer: '2', correctAnswer: false },
    //       { id: 2, answer: '3', correctAnswer: true },
    //       { id: 3, answer: '4', correctAnswer: false },
    //       { id: 4, answer: '5', correctAnswer: false },
    //     ],
    //   },
    //   {
    //     id: 2,
    //     question: 'Đây là câu hỏi 2?',
    //     answers: [
    //       { id: 5, answer: '2', correctAnswer: false },
    //       { id: 6, answer: '3', correctAnswer: false },
    //       { id: 7, answer: '4', correctAnswer: true },
    //       { id: 8, answer: '5', correctAnswer: false },
    //     ],
    //   },
    //   // Thêm các câu hỏi khác tại đây
    // ];
    // setQuestions(sampleQuestions)
    const fetchQuestion = async () => {
        const response = await examAPI.getById(id);
        setQuestions(response.questions);
        console.log(response)
      };
      fetchQuestion();
  }, []);



  const handleEditQuestion = (id) => {
    navigate(`/dashboard/questionUpdate/${id}`)
  };

  const handleDeleteQuestion = async (id) => {
    try {
      const response = await questionAPI.delete(id);
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
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
        {questions?.map((question,index) => (
             <ListItem key={question.id} sx={{ border: '1px solid', borderRadius: '4px', my: 2 }}>
             <ListItemText primary={`${index + 1}. ${question.question}`} />
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
                            color: answer.correctAnswer ? 'green' : 'inherit',
                        }}
                        checked={answer.correctAnswer} // Kiểm tra câu trả lời đúng
                        />
                    ))}
                </RadioGroup>
             </FormControl>
             <Button variant="outlined" onClick={() => handleEditQuestion(question.id)}>Edit</Button>
             <Button variant="outlined" onClick={() => handleDeleteQuestion(question.id)}>Delete</Button>
           </ListItem>
         ))}
       </List>
 
       
     </>
   );
 }