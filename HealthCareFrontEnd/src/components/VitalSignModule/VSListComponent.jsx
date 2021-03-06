import React, { Component } from 'react';
import { Container, Typography, Box } from '@material-ui/core';
import VSService from '../../service/VSService';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import Tooltip from '@material-ui/core/Tooltip';
import Button from '@material-ui/core/Button';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import HeaderComponent from '../HomePage/HeaderComponent';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import PageviewIcon from '@material-ui/icons/Pageview';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';

class VSListComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            vitalSigns: [],
            searchInput: ''
        }

        this.viewUser = this.viewUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);
        this.searchInput = this.searchInput.bind(this);
    }

    componentDidMount() {
        VSService.getAllCheckUpDetails().then((res) => {
            this.setState({vitalSigns: res.data});
          })
    }

    viewUser(pId, date) {
        this.props.history.push(`/view/${pId}/${date}`);
    }

    updateUser(pId, date) {
        this.props.history.push(`/update-vitalsign/${pId}/${date}`);
    }

    deleteUser(pId, date) {
        VSService.deleteUser(pId, date).then((res) => {
            this.componentDidMount();
        });
    }

    searchInput = (event) => {
        this.setState({ searchInput: event.target.value });
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
                        Patient Vital Sign List
                </Typography>
                    <Box mt={5}>
                        <div className="addIcon">
                            <Tooltip title="Add" aria-label="add">
                                <Fab color="primary" aria-label="add" href="http://localhost:3000/add-vitalsign">
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
                                        <TableCell align="right">CheckUp ID</TableCell>
                                        <TableCell align="right">CheckUp Date</TableCell>
                                        <TableCell align="right">Actions</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {this.state.vitalSigns.map((vitalSign) => (
                                        <TableRow key={vitalSign.patientId + "_" + vitalSign.checkupId}>
                                            <TableCell align="right">{vitalSign.patientId}</TableCell>
                                            <TableCell align="right">{vitalSign.checkupId}</TableCell>
                                            <TableCell align="right">{vitalSign.checkupDate.slice(0, 10)}</TableCell>
                                            <TableCell align="right">
                                                <Button
                                                    variant="contained"
                                                    color="primary"
                                                    size="small"
                                                    startIcon={<PageviewIcon />}
                                                    onClick={() => this.viewUser(vitalSign.patientId, vitalSign.checkupDate.slice(0, 10))}>
                                                </Button>
                                                <Button
                                                    variant="contained"
                                                    color="primary"
                                                    size="small"
                                                    startIcon={<EditIcon />}
                                                    onClick={() => this.updateUser(vitalSign.patientId, vitalSign.checkupDate.slice(0, 10))}>
                                                </Button>
                                                <Button
                                                    variant="contained"
                                                    color="secondary"
                                                    size="small"
                                                    startIcon={<DeleteIcon />}
                                                    onClick={() => this.deleteUser(vitalSign.patientId, vitalSign.checkupDate.slice(0, 10))}>
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

export default VSListComponent;