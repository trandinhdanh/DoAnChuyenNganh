import { Helmet } from 'react-helmet-async';
import { filter } from 'lodash';
import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
// @mui
import {
  Modal,
  TextField,
  Card,
  Table,
  Stack,
  Paper,
  Avatar,
  Button,
  Popover,
  Checkbox,
  TableRow,
  TableHead,
  MenuItem,
  TableBody,
  TableCell,
  Container,
  Typography,
  IconButton,
  TableContainer,
  TablePagination,
} from '@mui/material';
// components
import Iconify from '../../components/iconify';
import Scrollbar from '../../components/scrollbar';
// sections
import { UserListToolbar } from '../../sections/@dashboard/user';
// mock
import studentApi from '../../services/StudentAPI';
import courseAPI from '../../services/courseAPI';
import { useAuth } from '../../context/AuthContext';
import examAPI from '../../services/examAPI';
import questionAPI from '../../services/questionAPI';

export default function ProgressExamPage() {
  const {id} = useParams()
  const [open, setOpen] = useState(null);
  const { userDTO} = useAuth();
  const [page, setPage] = useState(0);


  const [selected, setSelected] = useState([]);

  const [filterName, setFilterName] = useState('');

  // lấy id của row trong bảng 
  const [idRow, setIdRow] = useState(-1)

  const [rowsPerPage, setRowsPerPage] = useState(5);
  const [completed, setCompleted] = useState(false);
  const [completedQuestions, setCompletedQuestions] = useState([]);
  const [exam, setExam] = useState([]);
  useEffect(() => {
    const fetchTeachers = async () => {
      const data = await examAPI.getAll(id);
      setExam(data);
    };
    fetchTeachers();
  }, []);
  const navigate = useNavigate()

  // new exam
  const [openCourse, setOpenCourse] = useState(false);
  const [examName, setExamName] = useState('');

  const handleOpenCouse = () => {
    setOpenCourse(true);
  };
  const handleOpenMenu = (event, id) => {
    setIdRow(id);
    setOpen(event.currentTarget);
  };


  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setPage(0);
    setRowsPerPage(parseInt(event.target.value, 10));
  };


  // useEffect(() => {
  //   const checkCompletion = () => {
  //     let isCompleted = true;
  //     examAPI.forEach((row) => {
  //       if (!completedQuestions.includes(row.id)) {
  //         isCompleted = false;
  //       }
  //     });
  //     setCompleted(isCompleted);
  //   };

  //   checkCompletion();
  // }, [completedQuestions, exam]);

  return (
    <>
      <Helmet>
        <title> Exam | Minimal UI </title>
      </Helmet>

      <Container>
        <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
          <Typography variant="h4" gutterBottom>
           Course / Exam
          </Typography>
          <Button onClick={handleOpenCouse} variant="contained" startIcon={<Iconify icon="eva:plus-fill" />}>
            New Exam
          </Button>
        </Stack>

        <Card>

          <Scrollbar>
            <TableContainer sx={{ minWidth: 800 }}>
              <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell align="right">Id</TableCell>
                    <TableCell align="right">Name Exam</TableCell>
                    <TableCell align="right">Progress</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {exam.map((row) => (
                    <TableRow
                      key={row.id}
                      sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >

                      <TableCell align="right">{row.id}</TableCell>
                      <TableCell align="right"><Link to={`/dashboard/question/${row.id}`}>{row.name}</Link></TableCell>
                      <TableCell align="right" >
                      {/* {completedQuestions.includes(row.id) ? 'Hoàn thành' : 'Chưa hoàn thành'} */}
                      <IconButton size="large" color="inherit" onClick={(event) => handleOpenMenu(event, row.id)}>
                          <Iconify icon={'eva:more-vertical-fill'} />
                        </IconButton>
                       
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Scrollbar>

          <TablePagination
            rowsPerPageOptions={[5, 10, 25]}
            component="div"
            count={exam.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </Card>
      </Container>

      
    </>
  );
}
