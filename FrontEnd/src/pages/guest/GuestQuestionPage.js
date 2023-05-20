import { Button, Chip, FormControl, FormControlLabel, List, ListItem, ListItemText, Radio, RadioGroup, Stack, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import examAPI from '../../services/examAPI';
import questionAPI from '../../services/questionAPI';


function GuestQuestionPage() {
  const { id } = useParams();
  const [content, setContent] = useState(false);
  const [count, setCount] = useState(0);
  const [selectedAnswers, setSelectedAnswers] = useState([]);
  const [exam, setExam] = useState([]);
  const [questions, setQuestions] = useState([]);
  const [result,setResult] = useState({})
  const navigate = useNavigate()
  const handleSumit = () => {
    let score = 0;
    questions.forEach((question, index) => {
      const correctAnswerIndex = question.answers.findIndex(answer => answer.correctAnswer === true);
      if (parseInt(selectedAnswers[index], 10) === correctAnswerIndex) {
         score += 1;
      }
      console.log("Câu ", index, ' -> selectedAnswers: ', selectedAnswers[index], "|| correctAnswerIndex: ", correctAnswerIndex, " => totalScore: ", score);
    })
    const goal = (10 / questions.length) * score;
    try {
      const response = examAPI.submit(localStorage.getItem("user"),id,{score : goal});
      console.log(response.data);
    } catch (error) {
      console.error("error create student", error);
    }
    setCount(score);
    console.log("goal: ", goal);
    console.log("selectedAnswers", selectedAnswers);
    setContent(true);
  };
  const userId = localStorage.getItem('user');
  useEffect(() => {
    const fetchQuestion = async () => {
      try {
        const response = await examAPI.getById(id);
        setExam(response.exam);
        setQuestions(response.questions);
        setSelectedAnswers(Array(response.questions.length).fill(null));

        console.log(response);
      } catch (error) {
        console.error('Failed to fetch questions:', error);
      }
    };
    fetchQuestion();
    
    const fetchIsComplete = async () => {
      try {
        const response = await questionAPI.checked(userId,id);
        console.log(response);
        setResult(response)
        setContent(response.complete)
      } catch (error) {
        console.error('Failed to fetch questions:', error);
      }
    };
    fetchIsComplete();

  }, [id]);

  const handleAnswerChange = (questionIndex, answerIndex) => {
    setSelectedAnswers((prevSelectedAnswers) => {
      const updatedSelectedAnswers = [...prevSelectedAnswers];
      updatedSelectedAnswers[questionIndex] = answerIndex;
      return updatedSelectedAnswers;
    });
    console.log(selectedAnswers);
  };
  const hanldeBack = () => { 
    navigate('/dashboard/courseStudent')
   }
  return (
    <div>
      {content ? (
        <div style={{ display: 'flex', justifyContent: 'center', marginTop: '50px' }}>
        <div
          className="result"
          style={{
            display: 'flex',
            justifyContent: 'center',
            flexDirection: 'column',
            alignItems: 'center',
            textAlign: 'center',
            height: '300px',
            width: '600px',
            border: '0.5px solid #dfdfdf',
            borderRadius: '8px',
            backgroundColor: '#efefef',
          }}
        >
         {result.complete ? (
          <>
              <Typography variant="h3">Bài thi: {exam.name}</Typography>
          <Typography variant="h4" style={{ margin: '0' }}>Bạn đã hoàn thành bài thi</Typography>
          <Typography variant='body1' sx={{ mb: '15px' }}>Điểm số: {result.score} điểm.</Typography>
          <Button onClick={hanldeBack} variant="contained" >Quay lại trang chủ</Button>
          </>
         ):(
          <>
            <Typography variant="h3">Bài thi: {exam.name}</Typography>
          <Typography variant='subtitle1' sx={{ m: '15px 0' }}>Bạn làm được : {count} / {questions.length} câu hỏi.</Typography>
          <Typography variant='body1' sx={{ mb: '15px' }}>Điểm số: {(10 / questions.length) * count} điểm.</Typography>
          <Button onClick={hanldeBack} variant="contained" >Quay lại trang chủ</Button>
          </>
         )}
        </div>
      </div>
       
      ) : (
        <div className="test-container" style={{ paddingBottom: '30px' }}>
        <div
          className="test-title"
          style={{
            display: 'flex',
            justifyContent: 'center',
            flexDirection: 'column',
            alignItems: 'center',
            textAlign: 'center',
            marginTop: '20px',
          }}
        >
          <div
            style={{
              display: 'flex',
              justifyContent: 'center',
              flexDirection: 'column',
              alignItems: 'center',
              textAlign: 'center',
              backgroundColor: '#efefef',
              minWidth: '500px',
              height: '220px',
              padding: '30px',
              borderRadius: '20px',
            }}
          >
            <Typography variant="h3">Bài thi: {exam.name}</Typography>
          </div>
        </div>
        <div className="question-answer" style={{ margin: '50px' }}>
          <List>
            {questions?.map((question, questionIndex) => (
              <ListItem key={questionIndex} sx={{ border: '1px solid', borderRadius: '4px', my: 2 }}>
                <Stack spacing={2} direction={'column'}>
                <ListItemText primary={`Câu ${questionIndex + 1}. ${question.question}`} />
                <FormControl component="fieldset">
                  <RadioGroup
                    value={selectedAnswers[questionIndex] || ""}
                    onChange={(e) => handleAnswerChange(questionIndex, e.target.value)}
                  >
                    {question.answers.map((answer, answerIndex) => (
                      <FormControlLabel
                        key={answerIndex}
                        value={answerIndex.toString()}
                        control={<Radio color="primary" />}
                        label={`${String.fromCharCode(65 + answerIndex)}. ${answer.answer}`}
                      />
                    ))}
                  </RadioGroup>
                </FormControl>
                </Stack>
              </ListItem>
            ))}
          </List>
        </div>
        <div className="submit" style={{ display: 'flex', justifyContent: 'center' }}>
          <Button onClick={handleSumit} variant="contained">
            Nộp bài
          </Button>
        </div>
      </div>
      )}
    </div>
  );
}

export default GuestQuestionPage;