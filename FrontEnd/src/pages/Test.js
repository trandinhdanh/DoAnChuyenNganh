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
        <div className="test-container">
          <div className="test-title">
            <h1>Bài thi: Chuyên đề Java</h1>
          </div>
          {quizzItems.map((quizz) => {
            return <Question id={quizz.id} question={quizz.question} answers={quizz.answers} />;
          })}

          <Button onClick={handleSumit} variant="contained">
            Nộp bài
          </Button>
        </div>
      ) : (
        <div>
          <div>Bạn giỏi quá Kết quả của bạn là : {count}</div>
          <Button variant="contained">Quay lại trang chủ</Button>
        </div>
      )}
    </div>
  );
}

export default Test;
