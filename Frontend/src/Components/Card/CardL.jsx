import React, { useState } from 'react';
import './CardL.css';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import CardActionArea from '@mui/material/CardActionArea';
import CardActions from '@mui/material/CardActions';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { deleteDepartment, updateDepartment } from '../../store/departmentReducer';
import Backdrop from '@mui/material/Backdrop';
import Fade from '@mui/material/Fade';
import { TextField, Button, Grid, Modal, Box } from '@mui/material';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

const CardL = ({ deptName }) => {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    id: deptName.id,
    deptname: deptName.name,
    capacity: deptName.capacity,
  });

  const handleClick = () => {
    navigate(`dept/${deptName.id}`);
  };

  const handleDelete = () => {
    dispatch(deleteDepartment({ j: localStorage.getItem('jwt'), id: deptName.id }));
  };

  const handleUpdate = () => {
    const object = {
      id: deptName.id,
      name: formData.deptname,
      capacity: formData.capacity,
    };
    dispatch(updateDepartment({ j: localStorage.getItem('jwt'), obj: object }));
    handleClose();
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  return (
    <>
      <Card className="card-container">
        <CardActionArea onClick={handleClick}>
          <CardContent className="card-content">
            <Typography gutterBottom variant="h5" component="div">
              <p>
                <b>DEPARTMENT : </b>
                <span>{deptName.name}</span>
              </p>
            </Typography>

            <Typography gutterBottom variant="h5" component="div">
              <p>
                <b>CAPACITY : </b>
                <span>{deptName.capacity}</span>
              </p>
            </Typography>
          </CardContent>
        </CardActionArea>
        <CardActions className="card-actions">
          <Button size="small" color="primary" onClick={handleOpen}>
            Update
          </Button>
          <Button size="small" color="primary" onClick={handleDelete}>
            Delete
          </Button>
        </CardActions>
      </Card>

      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={open}
        onClose={handleClose}
        closeAfterTransition
        slots={{ backdrop: Backdrop }}
        slotProps={{
          backdrop: {
            timeout: 500,
          },
        }}
      >
        <Fade in={open}>
          <Box sx={style} className="modal-box">
            <form className="modal-form">
              <Grid container spacing={2} alignItems="center">
                <Grid item xs={12}>
                  <TextField
                    label="deptname"
                    fullWidth
                    name="deptname"
                    value={formData.deptname}
                    onChange={handleChange}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    label="capacity"
                    fullWidth
                    name="capacity"
                    value={formData.capacity}
                    onChange={handleChange}
                  />
                </Grid>
                <Grid item xs={12}>
                  <Button
                    sx={{ padding: '.8rem' }}
                    fullWidth
                    className="customeButton"
                    variant="contained"
                    onClick={handleUpdate}
                  >
                    Update
                  </Button>
                </Grid>
              </Grid>
            </form>
          </Box>
        </Fade>
      </Modal>
    </>
  );
};

export default CardL;