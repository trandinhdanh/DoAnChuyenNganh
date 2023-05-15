import { styled } from '@mui/material/styles';
import { Typography } from '@mui/material';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';

import CourseItem from './course-item';

const Item = styled(Paper)(({ theme }) => ({
    padding: theme.spacing(1),
    textAlign: 'center',
    display: 'flex',
    justifyContent: 'center',
    background: 'none'
}));

export default function CourseList({data}) {

    return (
        <div>
            <Typography gutterBottom variant="h5" component="div">Danh sách khoá học</Typography>
            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }} >
                {data?.map((item, idx) => {
                    return <Grid item xs={3} key={idx}>
                        <Item>
                            <CourseItem data={item} />
                        </Item>
                    </Grid>
                })}
            </Grid>
        </div>
    )
}
