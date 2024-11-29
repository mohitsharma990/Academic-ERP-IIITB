import { React, useEffect, useState } from 'react'
import Button from '@mui/material/Button'
import Menu from '@mui/material/Menu'
import MenuItem from '@mui/material/MenuItem'
import Fade from '@mui/material/Fade'
import './Navbar.css'
import { Avatar } from '@mui/material'
import { Link, useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import { getUserProfile, logout } from '../../store/AuthReducer';


const Navbar = () => {
  const {auth}=useSelector(store=>store);
  const dispatch=useDispatch();
 const navigate=useNavigate();

  const [anchorEl, setAnchorEl] = useState(null)
  const open = Boolean(anchorEl)
  const handleClick = event => {
    setAnchorEl(event.currentTarget)
  }
  const handleClose = () => {
    setAnchorEl(null)
  }

  const handlelogout = () => {
    dispatch(logout())
    handleClose()
    navigate("/");

  }

  useEffect(()=>{
      dispatch(getUserProfile(localStorage.getItem("jwt")))
  },[])

  return (
    <>
   
   <div className="main">
  <div className="left-panel">
    <b >Academic ERP</b>
  </div>

  <div className="create">
    <Link to="create">Add Department</Link>
  </div>

  <div className="right-panel">
    <span>{auth.user.fullName}</span>
    <div>
      <Button
        id="fade-button"
        aria-controls={open ? 'fade-menu' : undefined}
        aria-haspopup="true"
        aria-expanded={open ? 'true' : undefined}
        onClick={handleClick}
      >
        <Avatar alt="Profile">{auth.user.fullName?.[0] || 'U'}</Avatar>
      </Button>
      <Menu
        id="fade-menu"
        MenuListProps={{
          'aria-labelledby': 'fade-button',
        }}
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        TransitionComponent={Fade}
      >
        <MenuItem onClick={handlelogout}>Logout</MenuItem>
      </Menu>
    </div>
  </div>
</div>
      
    </>
  )
}

export default Navbar