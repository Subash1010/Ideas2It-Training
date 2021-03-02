import React, { Component } from 'react';
import { Container, Typography, Box } from '@material-ui/core';
import PatientService from '../../service/PatientService';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import Tooltip from '@material-ui/core/Tooltip';
import Button from '@material-ui/core/Button';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import TextField from '@material-ui/core/TextField';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';

class PatientListComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            patients: [],
            searchInput: ''
        }

        this.updatePatient = this.updatePatient.bind(this);
        this.deletePatient = this.deletePatient.bind(this);
        this.searchInput = this.searchInput.bind(this);
    }

    componentDidMount() {
        PatientService.getAllPatients().then((res) => {
            this.setState({ patients: res.data });
        })
    }

    updatePatient(id) {
        this.props.history.push(`/update-patient/${id}`);
    }

    deletePatient(id) {
        PatientService.deletePatient(id).then((res) => {
            this.componentDidMount();
        });
    }

    searchInput = (event) => {
        this.setState({ searchInput: event.target.value });

        PatientService.getPatientByName(event.target.value).then((res) => {
            let patient = res.data;
            this.setState({ firstName: patient.firstName });
            this.setState({ lastName: patient.lastName});
            if((patient.dob !== undefined && patient.dob !== null && patient.dob !== "")){
                this.setState({ dob: patient.dob.slice(0, 10) });
            } else {
                this.setState({ dob: ""});
            }
            this.setState({ age: patient.age });
            this.setState({ phoneNumber: patient.phoneNumber });
            this.setState({ alternatePhoneNumber: patient.alternatePhoneNumber });
            this.setState({ gender: patient.gender });
            this.setState({ permanentAddress: patient.permanentAddress });
            this.setState({ communicationAddress: patient.communicationAddress });
            this.setState({ email: patient.email });
            if((patient.initialAdmitDate !== undefined && patient.initialAdmitDate !== null && patient.initialAdmitDate !== "")){
                this.setState({ initialAdmitDate: patient.initialAdmitDate.slice(0, 10) });
            } else {
                this.setState({ initialAdmitDate: ""});
            }
            if((patient.latestAdmitDate !== undefined && patient.latestAdmitDate !== null && patient.latestAdmitDate !== "")){
                this.setState({ latestAdmitDate: patient.latestAdmitDate.slice(0, 10) });
            } else {
                this.setState({ latestAdmitDate: ""});
            }
        });
    }

    render() {
        return (
            
            <Container>
                <Box mt={5}></Box>
                <Container maxWidth="sm">
                    <Button
                        variant="contained"
                        color="default"
                        href="http://localhost:3000/"
                        size="small"
                        startIcon={<ArrowBackIcon />}>
                        Back
                        </Button>
                </Container>
                <Box mt={5}>
                </Box>
                <Container maxWidth="sm">
                    <Typography variant="h3" gutterBottom>
                        Patient List
                </Typography>
                    <TextField
                        id="search"
                        label="Search"
                        variant="outlined"
                        onChange={this.searchInput}
                        fullWidth= {true}
                    />
                    <Box mt={5}>
                        <div className="addIcon">
                            <Tooltip title="Add" aria-label="add">
                                <Fab color="primary" aria-label="add" href="http://localhost:3000/add-patient">
                                    <AddIcon />
                                </Fab>
                            </Tooltip>
                        </div>
                    </Box>
                    <Box mt={5}>
                        <TableContainer component={Paper}>
                            <Table aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Patient ID</TableCell>
                                        <TableCell align="right">First Name</TableCell>
                                        <TableCell align="right">Last Name</TableCell>
                                        <TableCell align="right">Phone Number</TableCell>
                                        <TableCell align="right">Actions</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {this.state.patients.map((patient) => (
                                        <TableRow key={patient.patientId}>
                                            <TableCell align="right">{patient.patientId}</TableCell>
                                            <TableCell align="right">{patient.firstName}</TableCell>
                                            <TableCell align="right">{patient.lastName}</TableCell>
                                            <TableCell align="right">{patient.phoneNumber}</TableCell>
                                            <TableCell align="right">
                                                <Button
                                                    variant="contained"
                                                    color="primary"
                                                    size="small"
                                                    startIcon={<EditIcon />}
                                                    onClick={() => this.updatePatient(patient.patientId)}>
                                                </Button>
                                                <Button
                                                    variant="contained"
                                                    color="secondary"
                                                    size="small"
                                                    startIcon={<DeleteIcon />}
                                                    onClick={() => this.deletePatient(patient.patientId)}>
                                                </Button>
                                            </TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Box>
                </Container>
            </Container>
        );

    }
}

export default PatientListComponent;