import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import { Container, Typography, Box } from '@material-ui/core';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import { makeStyles } from '@material-ui/core/styles';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';
import VSService from '../../service/VSService';
import HeaderComponent from '../HomePage/HeaderComponent';

class AddVSComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            patientId: '',
            checkupId: '',
            checkupDate: '',
            pulseRate: '',
            bloodPressure: '',
            temperature: '',
            respirationRate: '',
            bloodSugar: '',
            height: '',
            weight: '',
        }

        this.updatePatientId = this.updatePatientId.bind(this);
        this.updateCheckupId = this.updateCheckupId.bind(this);
        this.updateCheckupDate = this.updateCheckupDate.bind(this);
        this.updatePulseRate = this.updatePulseRate.bind(this);
        this.updateBloodPressure = this.updateBloodPressure.bind(this);
        this.updateTemperature = this.updateTemperature.bind(this);
        this.updateRespirationRate = this.updateRespirationRate.bind(this);
        this.updateBloodSugar = this.updateBloodSugar.bind(this);
        this.updateHeight = this.updateHeight.bind(this);
        this.updateWeight = this.updateWeight.bind(this);
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

    updatePatientId = (event) => {
        this.setState({ patientId: event.target.value });
    }

    updateCheckupId = (event) => {
        this.setState({ checkupId: event.target.value });
    }

    updateCheckupDate = (event) => {
        this.setState({ checkupDate: event.target.value });
    }

    updatePulseRate = (event) => {
        this.setState({ pulseRate: event.target.value });
    }

    updateBloodPressure = (event) => {
        this.setState({ bloodPressure: event.target.value });
    }

    updateTemperature = (event) => {
        this.setState({ temperature: event.target.value });
    }

    updateRespirationRate = (event) => {
        this.setState({ respirationRate: event.target.value });
    }

    updateBloodSugar = (event) => {
        this.setState({ bloodSugar: event.target.value });
    }

    updateHeight = (event) => {
        this.setState({ height: event.target.value });
    }

    updateWeight = (event) => {
        this.setState({ weight: event.target.value });
    }

    onSave = (event) => {
        let users = {
            patientId: this.state.patientId,
            checkupDate: this.state.checkupDate,
            pulseRate: this.state.pulseRate,
            bloodPressure: this.state.bloodPressure,
            temperature: this.state.temperature,
            respirationRate: this.state.respirationRate,
            bloodSugar: this.state.bloodSugar,
            height: this.state.height,
            weight: this.state.weight,
        }
        if ((users.patientId !== undefined && users.patientId !== null && users.patientId !== "") &&
            (users.checkupDate !== undefined && users.checkupDate !== null && users.checkupDate !== "")) {
            VSService.addCheckUpDetails(users).then(res => {
                this.props.history.push("/vitalsign");
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
                        <Button
                            variant="contained"
                            color="default"
                            href="http://localhost:3000/vitalsign"
                            size="small"
                            startIcon={<ArrowBackIcon />}>
                            Back
                        </Button>
                        <Box mt={5}></Box>
                        <Typography variant="h3" gutterBottom>
                            Add Patient Vital Sign
                        </Typography>
                        <form className={this.classes.root} noValidate autoComplete="off">
                            <div>
                                <TextField
                                    required
                                    id="patientId"
                                    label="Patient ID"
                                    defaultValue=""
                                    variant="outlined"
                                    onChange={this.updatePatientId}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    required
                                    id="checkupDate"
                                    label="CheckUp Date"
                                    type="date"
                                    onChange={this.updateCheckupDate}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="pulseRate"
                                    label="Pulse Rate"
                                    variant="outlined"
                                    onChange={this.updatePulseRate}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="bloodPressure"
                                    label="Blood Pressure"
                                    variant="outlined"
                                    onChange={this.updateBloodPressure}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="temperature"
                                    label="Temperature"
                                    variant="outlined"
                                    onChange={this.updateTemperature}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="respirationRate"
                                    label="Respiration Rate"
                                    variant="outlined"
                                    onChange={this.updateRespirationRate}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="bloodSugar"
                                    label="Blood Sugar"
                                    variant="outlined"
                                    onChange={this.updateBloodSugar}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="height"
                                    label="Height"
                                    variant="outlined"
                                    onChange={this.updateHeight}
                                />
                                <Box mt={5}></Box>
                                <TextField
                                    id="weight"
                                    label="Weight"
                                    variant="outlined"
                                    onChange={this.updateWeight}
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
                                    href="http://localhost:3000/vitalsign">
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



export default AddVSComponent;
