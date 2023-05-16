
import { Helmet } from 'react-helmet-async';
import { filter } from 'lodash';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

// @mui
import {
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
import { fDate } from '../../utils/formatTime';


// ----------------------------------------------------------------------

const TABLE_HEAD = [
  { id: 'name', label: 'Name', alignRight: false },
  { id: 'company', label: 'Gender', alignRight: false },
  { id: 'role', label: 'Positon', alignRight: false },
  { id: 'isVerified', label: 'Verified', alignRight: false },
  { id: 'status', label: 'Status', alignRight: false },
  { id: '' },
];

// ----------------------------------------------------------------------

function descendingComparator(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function getComparator(order, orderBy) {
  return order === 'desc'
    ? (a, b) => descendingComparator(a, b, orderBy)
    : (a, b) => -descendingComparator(a, b, orderBy);
}

function applySortFilter(array, comparator, query) {
  const stabilizedThis = array.map((el, index) => [el, index]);
  stabilizedThis.sort((a, b) => {
    const order = comparator(a[0], b[0]);
    if (order !== 0) return order;
    return a[1] - b[1];
  });
  if (query) {
    return filter(array, (_user) => _user.name.toLowerCase().indexOf(query.toLowerCase()) !== -1);
  }
  return stabilizedThis.map((el) => el[0]);
}

export default function StudentPage() {
  const [open, setOpen] = useState(null);

  const [page, setPage] = useState(0);

  const [order, setOrder] = useState('asc');

  const [selected, setSelected] = useState([]);

  const [orderBy, setOrderBy] = useState('name');

  const [filterName, setFilterName] = useState('');

  // lấy id của row trong bảng 
  const [idRow, setIdRow] = useState(-1)

  const [rowsPerPage, setRowsPerPage] = useState(5);

  const [students, setStudent] = useState([]);
  useEffect(() => {
    const fetchTeachers = async () => {
      const data = await studentApi.getAll();
      setStudent(data);
    };
    fetchTeachers();
  }, []);
  const navigate = useNavigate()
  const handleNavigateNew = () => {
    navigate('/dashboard/studentNew')
  }
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
      const response = await studentApi.delete(id);
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }

  }
  return (
    <>
      <Helmet>
        <title> Student | Minimal UI </title>
      </Helmet>

      <Container>
        <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
          <Typography variant="h4" gutterBottom>
            Student
          </Typography>
          <Button onClick={handleNavigateNew} variant="contained" startIcon={<Iconify icon="eva:plus-fill" />}>
            New Student
          </Button>
        </Stack>

        <Card>
          <UserListToolbar numSelected={selected.length} filterName={filterName} onFilterName={handleFilterByName} />

          <Scrollbar>
            <TableContainer sx={{ minWidth: 800 }}>
              <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell align="right">Id</TableCell>
                    <TableCell align="right">Name</TableCell>
                    <TableCell align="right">Birth Day</TableCell>
                    <TableCell align="right">Action</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {students.map((row) => (
                    <TableRow
                      key={row.id}
                      sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >

                      <TableCell align="right">{row.id}</TableCell>
                      <TableCell align="right">{row.fullName}</TableCell>
                      <TableCell align="right">{fDate(row.birthday)}</TableCell>
                      <TableCell align="right" onClick={() => { console.log("delete") }}>

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
            count={students.length}
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
                          navigate(`/dashboard/studentUpdate/${idRow}`)
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
       {/* <Button onClick={() => {
                          handleDelete(row.id)
                        }} variant="outlined" color="error">
                          Delete
                        </Button>
                        <Button onClick={() => {
                          navigate(`/dashboard/studentUpdate/${row.id}`)
                        }} variant="contained" startIcon={<Iconify icon="eva:plus-fill" />}>
                          Update
                        </Button> */}
    </>
  );
}
