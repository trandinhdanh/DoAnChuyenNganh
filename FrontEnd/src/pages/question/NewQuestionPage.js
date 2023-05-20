import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Button, Stack, Container, TextField, FormControlLabel, RadioGroup, Radio } from '@mui/material';
import questionAPI from '../../services/questionAPI';

export default function NewQuestionPage() {
  const { id } = useParams();
  const navigate = useNavigate()
  const [questionState, setQuestionState] = useState('');
  const [answers, setAnswers] = useState([]);
  const [selectedAnswer, setSelectedAnswer] = useState(-1);

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

  const handleIsCorrectChange = (index) => {
    setAnswers((prevAnswers) => {
      const updatedAnswers = prevAnswers.map((answer, i) => ({
        ...answer,
        isCorrect: i === index,
      }));
      return updatedAnswers;
    });
    setSelectedAnswer(index);
  };

  const handleSendQuestion = async () => {
    const formattedAnswers = answers.map((answer, index) => ({
      answer: answer.answer,
      correctAnswer: index === selectedAnswer ? 1 : 0,
    }));

    const questionData = {
      question: questionState,
      answers: formattedAnswers,
    };
    try {
      const response = await questionAPI.create(id, questionData);
      console.log(response.data);
 
      window.location.reload();
    } catch (error) {
      console.error('error create question', error);
    }

    console.log(questionData);
  };

  return (
    <Container>
      <Stack spacing={2}>
        <TextField
          fullWidth
          label="Enter Question"
          value={questionState}
          onChange={(e) => setQuestionState(e.target.value)}
        />

        <RadioGroup value={selectedAnswer.toString()} onChange={(e) => handleIsCorrectChange(parseInt(e.target.value, 10))}>
          {answers.map((answer, index) => (
            <Stack key={index} spacing={2} direction="row" sx={{ marginTop: '8px', marginBottom: '8px' }}>
              <TextField
                fullWidth
                label={`Enter Answer ${index + 1}`}
                value={answer.answer}
                onChange={(e) => handleAnswerChange(index, e.target.value)}
              />
              <FormControlLabel
                value={index.toString()}
                control={<Radio />}
                label="Is correct?"
              />
            </Stack>
          ))}
        </RadioGroup>

        <Button sx={{ width: '200px' }} variant="contained" onClick={handleAddAnswer}>
          Add Answer
        </Button>

        <Button sx={{ width: '200px' }} variant="contained" onClick={handleSendQuestion}>
          Send Question
        </Button>
      </Stack>
    </Container>
  );
}
