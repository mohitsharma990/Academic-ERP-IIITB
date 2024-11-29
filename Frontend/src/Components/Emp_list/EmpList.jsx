import React, { useEffect } from 'react';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  Pagination,
  TableHead,
  TableRow,
  Paper,
  Button,
  MenuItem,
  InputLabel,
  Select,
  FormControl,
  Modal,
  TextField,
  Box,
  TablePagination,
} from '@mui/material';
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { getEmployee } from '../../store/departmentReducer';

const EmpList = () => {
  const dispatch = useDispatch();
  const { dept } = useSelector(store => store);
  const { id } = useParams();

  useEffect(() => {
    dispatch(getEmployee({ j: localStorage.getItem("jwt"), id: id }));
  }, [dispatch, id]);

  return (
    <>
      <div>
        {
          dept.employee.length > 0 ? (
            <TableContainer component={Paper} sx={{
              background: "white",
              borderRadius: 10,
              boxShadow: 3,
              padding: 2,
              marginTop: 4
            }}>
              <Table>
                <TableHead sx={{
                  backgroundColor: '#0288ff',
                  color: '#fff',
                  borderRadius: '10px 10px 0 0'
                }}>
                  <TableRow>
                    <TableCell sx={{
                      fontWeight: 'bold',
                      fontSize: '16px',
                      color: '#fff'
                    }}>Employee Name</TableCell>
                    <TableCell sx={{
                      fontWeight: 'bold',
                      fontSize: '16px',
                      color: '#fff'
                    }}>Email</TableCell>
                    <TableCell sx={{
                      fontWeight: 'bold',
                      fontSize: '16px',
                      color: '#fff'
                    }}>Title</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {dept.employee.map((domain) => (
                    <TableRow key={domain.id} sx={{
                      '&:hover': {
                        backgroundColor: '#f1f1f1',
                      },
                      cursor: 'pointer',
                      transition: 'background-color 0.3s ease'
                    }}>
                      <TableCell>{domain.name}</TableCell>
                      <TableCell>{domain.email}</TableCell>
                      <TableCell>{domain.title}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          ) : (
            <div style={{ textAlign: 'center', marginTop: '20px' }}>
              <p>No employees exist</p>
            </div>
          )
        }
      </div>
    </>
  );
};

export default EmpList;