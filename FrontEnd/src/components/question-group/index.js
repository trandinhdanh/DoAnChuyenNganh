import * as React from 'react';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';

export default function QuestionGroup({ data = {} }) {
  const question = ['a', 'b', 'c', 'd', 'e']
  return (
    <div>
      <FormControl>
        <FormLabel id="demo-radio-buttons-group-label">{data.question}</FormLabel>
        <RadioGroup
          aria-labelledby="demo-radio-buttons-group-label"
          defaultValue="female"
          name="radio-buttons-group"
        >

          {question.map((item, idx) => {
            if (data.answers[item]) {
              return <FormControlLabel key={idx} value={item} control={<Radio />} label={data.answers[item]} />
            }
            return <div key={idx} />
          })}
        </RadioGroup>
      </FormControl>
    </div>
  );
}