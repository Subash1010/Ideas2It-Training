import React, { Component } from "react";
import { Grid, Paper, Avatar, TextField, Box, Button } from "@material-ui/core";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import LoginService from "../../service/LoginService";


const paperStyle = { padding: 20, height: '90vh', width: 280, margin: "20px auto" }

class LoginPageComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            userName: '',
            password: ''
        }

        this.isValidCredentials = this.isValidCredentials.bind(this);
        this.updateUserName = this.updateUserName.bind(this);
        this.updatePassword = this.updatePassword.bind(this);
    }

    isValidCredentials() {
        LoginService.getAuthorization(this.state.userName, this.state.password).then((res) => {
            if(res.data.jwt === ""){
                alert("Invalid Credentials")
            } else {
                LoginService.setJWT(res.data.jwt);
                this.props.history.push("/");
            }
        })
        console.log(this.state.userName + " " + this.state.password);
    }

    updateUserName = (event) => {
        this.setState({ userName: event.target.value });
    }

    updatePassword = (event) => {
        this.setState({ password: event.target.value });
    }

    render() {
        return (
            <Grid>
                <Paper elevation={10} style={paperStyle}>
                    <Grid align="center">
                        <Avatar>
                            <LockOutlinedIcon />
                        </Avatar>
                        <h2>Sign In</h2>
                    </Grid>
                    <Box mt={5}></Box>
                    <TextField label="User Name" placeholder="Enter User Name" onChange={this.updateUserName} variant="outlined" fullWidth required></TextField>
                    <Box mt={5}></Box>
                    <TextField label="Password" placeholder="Enter Password" onChange={this.updatePassword} variant="outlined" type="password" fullWidth required></TextField>
                    <Box mt={5}></Box>
                    <Button
                        variant="contained"
                        color="primary"
                        type="submit"
                        fullWidth
                        onClick={() => this.isValidCredentials()}>
                        Login
                </Button>
                </Paper>
            </Grid>

        )
    }
}

export default LoginPageComponent;