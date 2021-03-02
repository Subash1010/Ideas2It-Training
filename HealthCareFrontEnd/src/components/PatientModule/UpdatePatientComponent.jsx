import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import { Container, Box } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';
import PatientService from '../../service/PatientService';

class AddPatientComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            patientId: this.props.match.params.id,
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
        this.onUpdate = this.onUpdate.bind(this);
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

    onUpdate = (event) => {
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
        if ((patient.patientId !== undefined && patient.patientId !== null && patient.patientId !== "") &&
            (patient.firstName !== undefined && patient.firstName !== null && patient.firstName !== "")) {
            PatientService.updatePatient(patient).then(res => {
                this.props.history.push("/patients");
            });
        }

    }

    componentDidMount() {
        PatientService.getPatientById(this.state.patientId).then((res) => {
            let patient = res.data;
            this.setState({ firstName: patient.firstName });
            this.setState({ lastName: patient.lastName});
            this.setState({ dob: patient.dob.slice(0, 10) });
            this.setState({ age: patient.age });
            this.setState({ phoneNumber: patient.phoneNumber });
            this.setState({ alternatePhoneNumber: patient.alternatePhoneNumber });
            this.setState({ gender: patient.gender });
            this.setState({ permanentAddress: patient.permanentAddress });
            this.setState({ communicationAddress: patient.communicationAddress });
            this.setState({ email: patient.email });
            this.setState({ initialAdmitDate: patient.initialAdmitDate.slice(0, 10) });
            this.setState({ latestAdmitDate: patient.latestAdmitDate.slice(0, 10) });
        });
    }

    render() {
        return (
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
                                value={this.state.firstName}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                id="lastName"
                                label="Last Name"
                                variant="outlined"
                                onChange={this.updateLastName}
                                value={this.state.lastName}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                id="dob"
                                label="DOB"
                                type="date"
                                onChange={this.updateDOB}
                                value={this.state.dob}
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
                                value={this.state.age}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                id="phoneNo"
                                label="Phone Number"
                                variant="outlined"
                                onChange={this.updatePhoneNumber}
                                value={this.state.phoneNumber}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                id="alternatePhoneNo"
                                label="Alternate Phone Number"
                                variant="outlined"
                                onChange={this.updateAlternatePhoneNumber}
                                value={this.state.alternatePhoneNumber}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                id="gender"
                                label="Gender"
                                variant="outlined"
                                onChange={this.updateGender}
                                value={this.state.gender}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                id="permanentaddress"
                                label="Permanent Address"
                                variant="outlined"
                                onChange={this.updatePermanentAddress}
                                value={this.state.permanentAddress}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                id="communicationaddress"
                                label="Communication Address"
                                variant="outlined"
                                onChange={this.updateCommunicationAddress}
                                value={this.state.communicationAddress}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                id="email"
                                label="Email Id"
                                variant="outlined"
                                onChange={this.updateEmail}
                                value={this.state.email}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                id="initialAdmitDate"
                                label="Initial Admit Date"
                                type="date"
                                onChange={this.updateInitialAdmitDate}
                                value={this.state.initialAdmitDate}
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
                                value={this.state.latestAdmitDate}
                                InputLabelProps={{
                                    shrink: true,
                                }}
                            />
                        </div>
                        <Box mt={5}>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={this.onUpdate}
                                size="small"
                                startIcon={<SaveIcon />}>
                                Update Record
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
        );
    }
}



export default AddPatientComponent;
