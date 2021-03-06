import React, { Component } from 'react';
import Button from '@material-ui/core/Button';
import { Container, Typography, Box } from '@material-ui/core';
import HeaderComponent from './HeaderComponent';

class HealthCareHomeComponent extends Component {
    render() {
        return (
            <div>
                <Container>
                    <HeaderComponent />
                </Container>
                <Container maxWidth="sm">
                    <div>
                        <Typography variant="h3" gutterBottom>
                            Home Page
                        </Typography>
                        <Box mt={5}></Box>
                        <div>
                            <Button
                                variant="contained"
                                color="primary"
                                size="small"
                                href="http://localhost:3000/users">
                                User Module
                            </Button>
                            <Box mt={5}></Box>
                            <Button
                                variant="contained"
                                color="primary"
                                size="small"
                                href="http://localhost:3000/patients">
                                Patient Module
                            </Button>
                            <Box mt={5}></Box>
                            <Button
                                variant="contained"
                                color="primary"
                                size="small"
                                href="http://localhost:3000/vitalsign">
                                Vital Sign Module
                            </Button>
                            <Box mt={5}></Box>
                            <Button
                                variant="contained"
                                color="primary"
                                size="small"
                                href="http://localhost:3000/audit">
                                Audit Trail
                            </Button>
                        </div>
                    </div>
                </Container>
            </div>
        );
    }
}

export default HealthCareHomeComponent;