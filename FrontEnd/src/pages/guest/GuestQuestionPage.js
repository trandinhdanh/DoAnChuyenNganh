import { Button, FormControl, FormControlLabel, List, ListItem, ListItemText, Radio, RadioGroup, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import examAPI from '../../services/examAPI';

function GuestQuestionPage() {
  const { id } = useParams();
  const [content, setContent] = useState(true);
  const [count, setCount] = useState(0);
  const [selectedAnswers, setSelectedAnswers] = useState([]);
  const [questions, setQuestions] = useState([]);

  const handleSumit = () => {
    let score = 0;
    questions.forEach((question, questionIndex) => {
      const correctAnswerIndex = question.answers.findIndex((answer) => answer.correctAnswer === 1);
      if (selectedAnswers[questionIndex] === correctAnswerIndex) {
        score += 1;
      }
    });
    setCount(score);
    setContent(false);
  };

  useEffect(() => {
    const fetchQuestion = async () => {
      try {
        const response = await examAPI.getById(id);
        setQuestions(response.questions);
        setSelectedAnswers(Array(response.questions.length).fill(null));
        console.log(response);
      } catch (error) {
        console.error('Failed to fetch questions:', error);
      }
    };
    fetchQuestion();
  }, [id]);

  const handleAnswerChange = (questionIndex, answerIndex) => {
    setSelectedAnswers((prevSelectedAnswers) => {
      const updatedSelectedAnswers = [...prevSelectedAnswers];
      updatedSelectedAnswers[questionIndex] = answerIndex;
      return updatedSelectedAnswers;
    });
    console.log(selectedAnswers);
  };

  return (
    <div>
    {content ? (
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
              borderRadius: '20px',
            }}
          >
            <Typography variant="h1">Bài thi: Chuyên đề Java</Typography>
            <Typography variant="h3" style={{ margin: 0 }}>Thời gian làm bài: 90p</Typography>
          </div>
        </div>
        <div className="question-answer" style={{ margin: '50px' }}>
          <List>
            {questions?.map((question, questionIndex) => (
              <ListItem key={questionIndex} sx={{ border: '1px solid', borderRadius: '4px', my: 2 }}>
                <ListItemText primary={`${questionIndex + 1}. ${question.question}`} />
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
    ) : (
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
          <Typography variant="h2">Bài thi môn: Java</Typography>
          <Typography variant="h4" style={{ margin: '0' }}>Thời gian: 90p</Typography>
          <div style={{ margin: '15px 0' }}>Kết quả của bạn là: {(10 / questions.length) * count}</div>
          <Button variant="contained">Quay lại trang chủ</Button>
        </div>
      </div>
    )}
  </div>
);
}

export default GuestQuestionPage;