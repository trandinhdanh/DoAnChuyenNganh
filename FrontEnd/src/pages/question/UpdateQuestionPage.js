import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Button, Stack, Container, TextField, FormControlLabel, Radio } from '@mui/material';
import questionAPI from '../../services/questionAPI';

export default function UpdateQuestionPage() {
  const { id } = useParams();
  const [questionState, setQuestionState] = useState('');
  const [answers, setAnswers] = useState([]);
  const [question, setQuestion] = useState('');

  useEffect(() => {
    const fetchQuestion = async () => {
      const response = await questionAPI.getById(id);
      setQuestion(response);
      setQuestionState(response.question);
      setAnswers(response.answers);
    };
    fetchQuestion();
  }, [id]);

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
  };

  const handleSendQuestion = async () => {
    const formattedAnswers = answers.map((answer, index) => ({
      answer: answer.answer,
      correctAnswer: answer.correctAnswer,
    }));

    const questionData = {
      question: questionState,
      answers: formattedAnswers,
    };

    try {
      const response = await questionAPI.update(id, questionData);
      console.log(response.data);
      // window.location.reload();
    } catch (error) {
      console.error('error update question', error);
    }

    console.log(questionData);
  };

  return (
    <Container>
      <Stack spacing={2}>
        <TextField
          fullWidth
          margin="normal"
          required
          label="Enter Question"
          value={questionState}
          onChange={(e) => setQuestionState(e.target.value)}
        />

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
                    onChange={() => handleIsCorrectChange(index)}
                  />
                }
                label="Is correct?"
              />
            </Stack>
          ))}
        <Button sx={{ width: '200px' }} variant="contained" onClick={handleSendQuestion}>
          Send Question
        </Button>
      </Stack>
    </Container>
  );
}
