import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Navbar from "../Navbar/Navbar";
import CardL from '../Card/CardL';
import { getDepartment } from '../../store/departmentReducer';
import { Box, Grid, Typography, Card, CardContent } from '@mui/material';

const Home = () => {
  const dispatch = useDispatch();
  const dept = useSelector((state) => state.dept);

  useEffect(() => {
    const jwtToken = localStorage.getItem("jwt");
    if (jwtToken) {
      dispatch(getDepartment({ j: jwtToken }));
    }
  }, [dispatch]);

  const hasDepartments = dept?.department?.length > 0;

  return (
    <>
      <Navbar />
      <Box 
        className="home-main" 
        sx={{
          mt: 3,
          px: 2,
          minHeight: '100vh',
          backgroundColor: '#f4f7fc',
          borderRadius: '10px',
        }}
      >
        {hasDepartments ? (
          <Grid container spacing={3} justifyContent="center">
            {dept.department.map((item, index) => (
              <Grid item xs={12} sm={6} md={4} lg={3} key={index}>
                <Card elevation={4}>
                  <CardContent>
                    <CardL deptName={item} />
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        ) : (
          <Box
            sx={{
              textAlign: 'center',
              py: 6,
              px: 3,
              background: '#fff',
              border: '2px solid #ddd',
              borderRadius: '10px',
              boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
              transition: 'transform 0.3s ease, box-shadow 0.3s ease',
              '&:hover': {
                transform: 'scale(1.01)',
                boxShadow: '0 6px 12px rgba(0, 0, 0, 0.2)',
              },
            }}
          >
            <Typography variant="h6" color="textSecondary">
              Nothing to show...
            </Typography>
          </Box>
        )}
      </Box>
    </>
  );
};

export default Home;