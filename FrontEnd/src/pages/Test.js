// eslint-disable-next-line import/no-unresolved
import Question from 'src/components/question/Question';
// eslint-disable-next-line import/no-unresolved
import { quizzItems } from 'src/components/question/QuizzItem';
import { Button } from '@mui/material';
import { useState } from 'react';

export const data = quizzItems;
function Test() {
  const [content, setContent] = useState(true);
  const [count, setCount] = useState(0);

  const handleSumit = () => {
    // eslint-disable-next-line no-plusplus
    for (let i = 0; i < data.length; i++) {
      // eslint-disable-next-line eqeqeq
      if (data[i] === 1) {
        setCount((count) => count + 1);
      }
    }
    setContent(false);
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
              <h1>Bài thi: Chuyên đề Java</h1>
              <h3 style={{ margin: 0 }}>Thời gian làm bài: 90p</h3>
            </div>
          </div>
          <div className="question-answer" style={{ margin: '50px' }}>
            {quizzItems.map((quizz, index) => {
              return <Question index={index} id={quizz.id} question={quizz.question} answers={quizz.answers} />;
            })}
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
            <h2>Bài thi môn: Java</h2>
            <h4 style={{ margin: '0' }}>Thời gian: 90p</h4>
            <div style={{ margin: '15px 0' }}>Kết quả của bạn là : {(10 / data.length) * count}</div>
            <Button variant="contained">Quay lại trang chủ</Button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Test;
