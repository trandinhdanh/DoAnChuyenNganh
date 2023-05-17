import * as React from 'react';
import { experimentalStyled as styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import { useNavigate } from 'react-router-dom';
import { useAuth } from "../../context/AuthContext";

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(2),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));

export default function ExamList({data}) {
    const navigate = useNavigate()
    const { getScopes } = useAuth();
    function handleClickExamp(id){
        const scope = getScopes()
        let redirectUrl = ''
        if(scope[0] === 'STUDENT'){
            redirectUrl = `/dashboard/mycourse/${id}`
        }
        navigate(redirectUrl)
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                {data.map((item,idx)=>{
                return   <Grid onClick={()=>handleClickExamp(item)} key={idx} item xs={2} sm={4} md={4}>
                    <Item>Examp {item}</Item>
                </Grid>
                })}
            </Grid>
        </Box>
    );
}
