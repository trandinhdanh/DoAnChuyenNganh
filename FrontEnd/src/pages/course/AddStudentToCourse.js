import React, { useEffect, useState } from 'react'

import { useNavigate, useParams } from 'react-router-dom';
import {
  Container,
  Grid,
  Typography,
  Card,
  CardContent,
  Stack,
  CardHeader,
  TableCell,  
  TableRow,
  TableBody,
  TableContainer,
  Table,
  Checkbox,
  Avatar,
  Button,
} from '@mui/material';

import studentApi from '../../services/StudentAPI';
import courseAPI from '../../services/courseAPI';
import Scrollbar from '../../components/scrollbar/Scrollbar';
import CartListHead from './CartListHead';
import Iconify from '../../components/iconify/Iconify';
import { fDate } from '../../utils/formatTime';


const TABLE_HEAD = [
  { id: 'id', label: 'ID', alignRight: false },
  { id: 'name', label: 'Name', alignRight: false },
  { id: 'bd', label: 'Birth Day', alignRight: false },

];


function AddStudentToCourse() {
  const [selected, setSelected] = useState([]);
  // load sản phẩm 
const { id } = useParams();
const  navigate  = useNavigate();
  const [students, setStudent] = useState([]);

  useEffect(() => {
    const fetchTeachers = async () => {
      const data = await studentApi.getAll();
      setStudent(data);
    };
    fetchTeachers();
  }, []);

  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelecteds = students.map((n) => n.id);
      setSelected(newSelecteds);
      return;
    }
    setSelected([]);
  };

  const handleOk = async () =>  {
    try {
      const response = await courseAPI.addStudents(id, selected);
      console.log(response.data);
      navigate(`/dashboard/course/${id}/students`)
    } catch (error) {
      console.error("error create student", error);
    }
   
      // navigate(`/dashboard/course`);
  };

  const handleClick = (event, id) => {
    const selectedIndex = selected.indexOf(id);
    let newSelected = [];
    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, id);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(selected.slice(0, selectedIndex), selected.slice(selectedIndex + 1));
    }
    console.log(selected);
    setSelected(newSelected);
  };


  return (
    <Container >
      <Grid container spacing={3}>
        <Grid item xs={12} >
          <Card>
            <CardHeader
              title={
                <Stack direction={'row'} alignItems='center' justifyContent="space-between" spacing={1}>
                  <Typography variant='h6'>
                    Selected {selected.length} students to add to the course
                  </Typography>
                  <Button variant="contained" onClick={handleOk} >add</Button>
                </Stack>
              }
            />
            <CardContent sx={{ px: 0, pt: 3 }}>

              <Scrollbar>

                {/* if non-empty cart */}
                <TableContainer>
                  <Table sx={{ minWidth: '720px', width: '100%' }}>
                    <CartListHead
                      headLabel={TABLE_HEAD}
                      rowCount={students.length}
                      numSelected={selected.length}
                      onSelectAllClick={handleSelectAllClick}
                    />
                    <TableBody>
                      {/* fake data nên khi có api bỏ .slice */}
                      {students.slice(0, 8).map((student, index) => {
                        const selectedProduct = selected.indexOf(student.id) !== -1;
                        return (

                          // 1 row có:
                          <TableRow hover key={index} tabIndex={-1} role="checkbox" selected={selectedProduct}>

                            {/* checkbox */}
                            <TableCell padding="checkbox">
                              <Checkbox size='small' checked={selectedProduct} onChange={(event) => handleClick(event, student.id)} />
                            </TableCell>
                            {/* Giá thành */}
                            <TableCell align="left">
                              {student.id}
                            </TableCell>

                            {/* hình + tên sản phẩm */}
                            <TableCell component="th" scope="row" padding="none" >
                              <Stack direction="row" alignItems="center" spacing={2}>
                                <Avatar
                                  alt={student.fullName}
                                  src={student.fullName}
                                  variant="rounded"
                                  sx={{ width: 55, height: 55, my: 1 }} />

                                <Typography
                                  variant="subtitle2"
                                  component="div"
                                  sx={{
                                    display: '-webkit-box',
                                    overflow: 'hidden',
                                    textOverflow: 'ellipsis',
                                    WebkitLineClamp: 3,
                                    WebkitBoxOrient: 'vertical',
                                    lineHeight: 1.2,
                                    maxHeight: '3.6em', // 3 lines * line-height of 1.2
                                  }}
                                >
                                  {student.fullName}
                                </Typography>
                              </Stack>
                            </TableCell>


                            {/* Giá thành */}
                            <TableCell align="left">
                              {fDate(student.birthday)}
                            </TableCell>

                          </TableRow>

                        );
                      })}

                    </TableBody>


                  </Table>
                </TableContainer>
              </Scrollbar>


            </CardContent>
          </Card>
          {/* --------------------------------------- BUTTON --------------------------------------------------- */}

          {/* <Button sx={{ color: '#000', mt: 3 }} href='/home'>
            <Iconify icon='ic:outline-keyboard-arrow-left' mr={1} />
            Continue Shopping
          </Button> */}


        </Grid>

      </Grid>
    </Container >);
};

export default AddStudentToCourse