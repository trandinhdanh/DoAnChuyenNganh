// eslint-disable-next-line import/no-unresolved
import { data } from 'src/pages/Test';

function Question({ index, id, question, answers }) {
  const handlePoint = (isCorrect) => {
    data[index] = isCorrect;
    console.log(index);
  };
  return (
    <div className="container">
      <h3>
        Câu {id}: {question}
      </h3>
      <div className="answer">
        {answers.map((ans, index) => {
          return (
            <div key={index}>
              <input
                key={index}
                name={question}
                type="radio"
                value={ans.answer}
                onChange={() => handlePoint(ans.correctAnswer)}
              />
              {ans.answer}
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Question;
