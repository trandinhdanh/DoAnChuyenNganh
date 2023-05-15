import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';
import { useAuth } from "../../context/AuthContext";

export default function CourseItem({ data }) {
  const navigate = useNavigate()
  
  const { getScopes } = useAuth();

  function handleClickCourse() {
    const scope = getScopes()
    let redirectUrl = ''
    if(scope[0] === 'STUDENT'){
      redirectUrl = `/dashboard/mycourse/${data.id}`
    }
    navigate(redirectUrl)
  }

  return (
    <div>
      <Card className='course-item' onClick={()=>handleClickCourse()}>
        <CardMedia
          component="img"
          image='https://files.fullstack.edu.vn/f8-prod/courses/7.png'
          width="100%"
        />
        <CardContent>
        <div style={{overflow: "hidden", textOverflow: "ellipsis", width: '11rem'}}> 
          <Typography  noWrap gutterBottom variant="body2" component="div">
            {data.name}
          </Typography>
        </div>
          <Typography variant="body3" color="text.secondary">
            {data.signed}
          </Typography>
        </CardContent>
        <CardActions>
          {/* <Button size="small">Xem khoá học</Button> */}
          {/* <Button size="small">{data.signed}</Button> */}
        </CardActions>
      </Card>
    </div>
  );
}