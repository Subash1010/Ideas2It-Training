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
import HeaderComponent from '../HomePage/HeaderComponent';

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
        this.searchResult = this.searchResult.bind(this);
    }

    componentDidMount(searchInput) {
        if(searchInput === "" || searchInput === undefined){
            PatientService.getAllPatients().then((res) => {
                this.setState({ patients: res.data });
            });
        } else {
            this.searchResult(searchInput);
        }
    }

    async searchResult(searchInput) {
        var searchHitIds = [];
        await PatientService.getPatientByName(searchInput).then((res) => {
            console.log(res);
            (res.data.searchHits).map(currentHit => {
                return searchHitIds[currentHit.id] = currentHit.id;
            });
        });
        var searchresult = [];
        await PatientService.getAllPatients().then((response) => {
            response.data.forEach(currentRecord => {
                if(searchHitIds[currentRecord.patientId] !== undefined && 
                    searchHitIds[currentRecord.patientId] !== null &&
                    searchHitIds[currentRecord.patientId] !== "" ){
                        searchresult.push(currentRecord);
                    }
                });
            });
        await this.setState({ patients: searchresult });
    }

    updatePatient(id) {
        this.props.history.push(`/update-patient/${id}`);
    }

    deletePatient(id) {
        PatientService.deletePatient(id).then((res) => {
            this.componentDidMount("");
        });
    }

    searchInput = (event) => {
        this.setState({ searchInput: event.target.value });
        this.componentDidMount(event.target.value);
    }

    render() {
        return (

            <Container>
                <HeaderComponent />
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
                        fullWidth={true}
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