import { Helmet } from 'react-helmet-async';
import { filter } from 'lodash';
import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

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
  Chip,
} from '@mui/material';
// components
import Iconify from '../../components/iconify';
import Scrollbar from '../../components/scrollbar';
// sections
import { UserListToolbar } from '../../sections/@dashboard/user';
// mock
import courseAPI from '../../services/courseAPI';
import { fDate } from '../../utils/formatTime';






export default function ResultPage() {
  const [open, setOpen] = useState(null);

  const [page, setPage] = useState(0);


  const [selected, setSelected] = useState([]);


  const [filterName, setFilterName] = useState('');

  // lấy id của row trong bảng 
  const [idRow, setIdRow] = useState(-1)

  const [rowsPerPage, setRowsPerPage] = useState(5);

  const [result, setResult] = useState([]);
  const { id } = useParams();
  const [idCourse, idUser] = id.split('-');
  useEffect(() => {
    const fetchScoreByUser = async () => {
      const data = await courseAPI.getScoreUser(idCourse,idUser);
      setResult(data);
      console.log(data);
    };
    fetchScoreByUser();
  }, []);

  const navigate = useNavigate()



  const handleFilterByName = (event) => {
    setPage(0);
    setFilterName(event.target.value);
  };

  return (
    <>
      <Helmet>
        <title> Student | Minimal UI </title>
      </Helmet>

      <Container>
        <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
          <Typography variant="h4" gutterBottom>
            Course / List student / Result
          </Typography>
        
        </Stack>

        <Card>

          <Scrollbar>
            <TableContainer sx={{ minWidth: 800 }}>
              <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell align="right">Id</TableCell>
                    <TableCell align="right">Exam Name</TableCell>
                    <TableCell align="right">Score</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {result.map((row,index) => (
                    <TableRow
                      key={index}
                      sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >

                      <TableCell align="right">{index + 1}</TableCell>
                      <TableCell align="right">{row.nameExam}</TableCell>
                      <TableCell align="right">{row.complete ?  <Chip label={row.score} color="success" variant="outlined" /> : <Chip label={'Is not done'} color="primary" variant="outlined" />}</TableCell>
                      
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Scrollbar>

        </Card>
      </Container>
    </>
  );
}
