import { Helmet } from 'react-helmet-async';
import { filter } from 'lodash';
import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
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

export default function ExamPage() {
  const {id} = useParams()
  const [open, setOpen] = useState(null);
  const { userDTO} = useAuth();
  const [page, setPage] = useState(0);


  const [selected, setSelected] = useState([]);

  const [filterName, setFilterName] = useState('');

  // lấy id của row trong bảng 
  const [idRow, setIdRow] = useState(-1)

  const [rowsPerPage, setRowsPerPage] = useState(5);

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

  const handleClose = () => {
    setOpenCourse(false);
  };

  const handleOk = async () =>  {
    console.log(userDTO);
    try {
      const response = await examAPI.create({name : examName},id);
      console.log(response.data);
      window.location.reload();
    } catch (error) {
      console.error("error create student", error);
    }
   
  };

  const handleCancel = () => {
    // Xử lý logic khi nhấn nút Hủy bỏ
    handleClose();
  };

  const handleChange = (event) => {
    setExamName(event.target.value);
    console.log(examName)
  };
  // end
  const handleOpenMenu = (event, id) => {
    setIdRow(id);
    setOpen(event.currentTarget);
  };

  const handleCloseMenu = () => {
    setOpen(null);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setPage(0);
    setRowsPerPage(parseInt(event.target.value, 10));
  };

  const handleFilterByName = (event) => {
    setPage(0);
    setFilterName(event.target.value);
  };
  const handleDelete = async (id) => {
    try {
      const response = await examAPI.delete(id);
      console.log(response.data);
      window.location.reload();
    } catch (error) {
      console.error(error);
    }

  }
  

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
                    <TableCell align="right">Action</TableCell>
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

      <Popover
        open={Boolean(open)}
        anchorEl={open}
        onClose={handleCloseMenu}
        anchorOrigin={{ vertical: 'top', horizontal: 'left' }}
        transformOrigin={{ vertical: 'top', horizontal: 'right' }}
        PaperProps={{
          sx: {
            p: 1,
            width: 140,
            '& .MuiMenuItem-root': {
              px: 1,
              typography: 'body2',
              borderRadius: 0.75,
            },
          },
        }}
      >
        <MenuItem onClick={() => {
                          navigate(`/dashboard/examUpdate/${idRow}`)
                        }} >
          <Iconify icon={'eva:edit-fill'} sx={{ mr: 2 }} />
          Edit
        </MenuItem>

        <MenuItem sx={{ color: 'error.main' }} onClick={() => {
                          handleDelete(idRow)
                        }}>
          <Iconify icon={'eva:trash-2-outline'} sx={{ mr: 2 }} />
          Delete
        </MenuItem>
      </Popover>
      <Modal open={openCourse} onClose={handleClose}>
        <div style={{ margin: 'auto', marginTop: 100, width: 300, backgroundColor: '#FFF', padding: 20 }}>
          <TextField
            label="Tên khóa học"
            value={examName}
            onChange={handleChange}
            fullWidth
            margin="normal"
          />
          <div style={{ textAlign: 'right', marginTop: 10 }}>
            {examName.length === 0 ? 
           ""
          :  <Button onClick={handleOk} color="primary" variant="contained" style={{ marginRight: 10 }}>
          OK
        </Button>}
            <Button onClick={handleCancel} color="secondary" variant="contained">
              Hủy bỏ
            </Button>
          </div>
        </div>
      </Modal>
    </>
  );
}