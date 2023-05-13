import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, TextField, RadioGroup, FormControlLabel, Radio, Stack, Container } from '@mui/material';
import questionAPI from '../../services/questionAPI';

export default function NewQuestionPage() {
  const {id} = useParams()
  const [questionState, setQuestionState] = useState('');
  const [answers, setAnswers] = useState([]);

  const handleAddAnswer = () => {
    setAnswers((prevAnswers) => [...prevAnswers, { answer: '', isCorrect: false }]);
  };

  const handleAnswerChange = (index, value) => {
    setAnswers((prevAnswers) => {
      const updatedAnswers = [...prevAnswers];
      updatedAnswers[index].answer = value;
      return updatedAnswers;
    });
  };

  const handleIsCorrectChange = (index, value) => {
    setAnswers((prevAnswers) => {
      const updatedAnswers = [...prevAnswers];
      updatedAnswers[index].isCorrect = value;
      return updatedAnswers;
    });
  };

  const handleSendQuestion = async () => {
    const questionData = {
      question: questionState,
      answers: answers.map((answer) => ({ [answer.answer]: answer.isCorrect })),
    };
    
    try {
      const response = await questionAPI.create(id,questionData);
      console.log(response.data);
    //   window.location.reload();
    } catch (error) {
      console.error("error create question", error);
    }
   
    // Gửi request API tại đây với dữ liệu questionData
    console.log(questionData);
  };

  return (
    <Container>
      <Stack spacing={2}>
        <TextField fullWidth label="Enter Question" value={questionState} onChange={(e) => setQuestionState(e.target.value)} />

        {answers.map((answer, index) => (
          <Stack key={index} spacing={2} direction="row">
            <TextField
              fullWidth
              label={`Enter Answer ${index + 1}`}
              value={answer.answer}
              onChange={(e) => handleAnswerChange(index, e.target.value)}
            />
            <FormControlLabel
              control={
                <Radio
                  checked={answer.isCorrect}
                  onChange={(e) => handleIsCorrectChange(index, e.target.checked)}
                />
              }
              label="Is correct?"
            />
          </Stack>
        ))}

        <Button sx={{width:'200px'}} variant="contained" onClick={handleAddAnswer}>
          Add Answer
        </Button>

        <Button sx={{width:'200px'}}  variant="contained" onClick={handleSendQuestion}>
          Send Question
        </Button>
      </Stack>
    </Container>
  );
}