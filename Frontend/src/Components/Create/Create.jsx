import React, { useState } from 'react';
import {
  TextField,
  Button,
  Grid,
  Box,
  Typography,
} from '@mui/material';
import { useDispatch } from 'react-redux';
import { createDepartment } from '../../store/departmentReducer';
import { useNavigate } from 'react-router-dom';
import './Create.css';

const Create = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    deptname: '',
    capacity: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleCreate = () => {
    if (formData.deptname.length === 0 || formData.capacity.length === 0) {
      alert('Please enter details');
    } else {
      const object = {
        name: formData.deptname,
        capacity: formData.capacity,
      };
      dispatch(createDepartment({ j: localStorage.getItem('jwt'), obj: object }));
      navigate('/');
    }
  };

  return (
    <div className="create-main">
      <Box sx={{
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        width: '100%',
        maxWidth: '600px',
        padding: '20px',
        backgroundColor: '#fafafa',
        borderRadius: '8px',
        boxShadow: '0 10px 15px rgba(0, 0, 0, 0.1)',
      }}>
        <Typography variant="h4" sx={{ fontWeight: 'bold', marginBottom: '20px' }}>
          Create Department
        </Typography>

        <form>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                label="Department Name"
                fullWidth
                name="deptname"
                value={formData.deptname}
                onChange={handleChange}
                variant="outlined"
                sx={{ borderRadius: '8px', padding: '10px' }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Capacity"
                fullWidth
                name="capacity"
                value={formData.capacity}
                onChange={handleChange}
                variant="outlined"
                sx={{ borderRadius: '8px', padding: '10px' }}
              />
            </Grid>
            <Grid item xs={12}>
              <Button
                sx={{
                  padding: '.8rem',
                  backgroundColor: '#0288ff',
                  color: 'white',
                  borderRadius: '8px',
                  '&:hover': {
                    backgroundColor: '#0277bd',
                  },
                }}
                fullWidth
                variant="contained"
                onClick={handleCreate}
              >
                Create
              </Button>
            </Grid>
          </Grid>
        </form>
      </Box>
    </div>
  );
};

export default Create;