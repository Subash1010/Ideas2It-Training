import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import { Container, Box } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';
import PatientService from '../../service/PatientService';
import HeaderComponent from '../HomePage/HeaderComponent';

class AddPatientComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            patientId: '',
            firstName: '',
            lastName: '',
            dob: '',
            age: '',
            phoneNumber: '',
            alternatePhoneNumber: '',
            gender: '',
            permanentAddress: '',
            communicationAddress: '',
            email: '',
            initialAdmitDate: '',
            latestAdmitDate: ''
        }

        this.updateFirstName = this.updateFirstName.bind(this);
        this.updateLastName = this.updateLastName.bind(this);
        this.updateDOB = this.updateDOB.bind(this);
        this.updateAge = this.updateAge.bind(this);
        this.updatePhoneNumber = this.updatePhoneNumber.bind(this);
        this.updateAlternatePhoneNumber = this.updateAlternatePhoneNumber.bind(this);
        this.updateGender = this.updateGender.bind(this);
        this.updatePermanentAddress = this.updatePermanentAddress.bind(this);
        this.updateCommunicationAddress = this.updateCommunicationAddress.bind(this);
        this.updateEmail = this.updateEmail.bind(this);
        this.updateInitialAdmitDate = this.updateInitialAdmitDate.bind(this);
        this.updateLatestAdmitDate = this.updateLatestAdmitDate.bind(this);
        this.onSave = this.onSave.bind(this);
    }

    classes = makeStyles((theme) => ({
        root: {
            '& .MuiTextField-root': {
                margin: theme.spacing(2),
                width: '25ch',
            },
        },
    }));


    updateFirstName = (event) => {
        this.setState({ firstName: event.target.value });
    }

    updateLastName = (event) => {
        this.setState({ lastName: event.target.value });
    }

    updateDOB = (event) => {
        this.setState({ dob: event.target.value });
    }

    updateAge = (event) => {
        this.setState({ age: event.target.value });
    }

    updatePhoneNumber = (event) => {
        this.setState({ phoneNumber: event.target.value });
    }

    updateAlternatePhoneNumber = (event) => {
        this.setState({ alternatePhoneNumber: event.target.value });
    }

    updateGender = (event) => {
        this.setState({ gender: event.target.value });
    }

    updatePermanentAddress = (event) => {
        this.setState({ permanentAddress: event.target.value });
    }

    updateCommunicationAddress = (event) => {
        this.setState({ communicationAddress: event.target.value });
    }

    updateEmail = (event) => {
        this.setState({ email: event.target.value });
    }

    updateInitialAdmitDate = (event) => {
        this.setState({ initialAdmitDate: event.target.value });
    }

    updateLatestAdmitDate = (event) => {
        this.setState({ latestAdmitDate: event.target.value });
    }

    onSave = (event) => {
        let patient = {
            patientId: this.state.patientId,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            dob: this.state.dob,
            age: this.state.age,
            phoneNumber: this.state.phoneNumber,
            alternatePhoneNumber: this.state.alternatePhoneNumber,
            gender: this.state.gender,
            permanentAddress: this.state.permanentAddress,
            communicationAddress: this.state.communicationAddress,
            email: this.state.email,
            initialAdmitDate: this.state.initialAdmitDate,
            latestAdmitDate: this.state.latestAdmitDate
        }
        if ((patient.firstName !== undefined && patient.firstName !== null && patient.firstName !== "")) {
            PatientService.addPatient(patient).then(res => {
                this.props.history.push("/patients");
            });
        }

    }

    render() {
        return (
            <div>
                <Container>
                    <HeaderComponent />
                </Container>
                <Container maxWidth="sm">
                    <div>
                        <Box mt={5}></Box>
                        <form className={this.classes.root} noValidate autoComplete="off">
                            <div>
                                <Box mt={5}></Box>
                                <TextField
                                    required
                                    id="firstName"
                                    label="First Name"
                                    variant="outlined"
                                    onChange={this.updateFirstName}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="lastName"
                                    label="Last Name"
                                    variant="outlined"
                                    onChange={this.updateLastName}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="dob"
                                    label="DOB"
                                    type="date"
                                    onChange={this.updateDOB}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="age"
                                    label="Age"
                                    variant="outlined"
                                    onChange={this.updateAge}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="phoneNo"
                                    label="Phone Number"
                                    variant="outlined"
                                    onChange={this.updatePhoneNumber}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="alternatePhoneNo"
                                    label="Alternate Phone Number"
                                    variant="outlined"
                                    onChange={this.updateAlternatePhoneNumber}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="gender"
                                    label="Gender"
                                    variant="outlined"
                                    onChange={this.updateGender}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="permanentaddress"
                                    label="Permanent Address"
                                    variant="outlined"
                                    onChange={this.updatePermanentAddress}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="communicationaddress"
                                    label="Communication Address"
                                    variant="outlined"
                                    onChange={this.updateCommunicationAddress}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="email"
                                    label="Email Id"
                                    variant="outlined"
                                    onChange={this.updateEmail}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="initialAdmitDate"
                                    label="Initial Admit Date"
                                    type="date"
                                    onChange={this.updateInitialAdmitDate}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="latestAdmitDate"
                                    label="Latest Admit Date"
                                    type="date"
                                    onChange={this.updateLatestAdmitDate}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </div>
                            <Box mt={5}>
                                <Button
                                    variant="contained"
                                    color="primary"
                                    onClick={this.onSave}
                                    size="small"
                                    startIcon={<SaveIcon />}>
                                    Save Record
                            </Button>
                                <Button
                                    variant="contained"
                                    color="secondary"
                                    size="small"
                                    href="http://localhost:3000/patients">
                                    Cancel Record
                        </Button>
                            </Box>
                        </form>
                    </div>
                </Container>
            </div>
        );
    }
}



export default AddPatientComponent;
