import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HealthcareAuditTrail from './components/AuditTrail/HealthcareAuditTrail';
import HealthCareHomeComponent from './components/HomePage/HealthCareHomeComponent';
import LoginPageComponent from './components/LoginPage/LoginPage';
import AddPatientComponent from "./components/PatientModule/AddPatientComponent";
import PatientListComponent from "./components/PatientModule/PatientListComponent";
import UpdatePatientComponent from "./components/PatientModule/UpdatePatientComponent";
import AddUserComponent from './components/UserModule/AddUserComponent';
import UpdateUserComponent from './components/UserModule/UpdateUserComponent';
import UserListComponent from './components/UserModule/UserListComponent';
import AddVSComponent from './components/VitalSignModule/AddVSComponent';
import UpdateVSComponent from './components/VitalSignModule/UpdateVSComponent';
import ViewVSComponent from './components/VitalSignModule/ViewVSComponent';
import VSListComponent from './components/VitalSignModule/VSListComponent';

function App() {
  return (
    <div>
      <Router>
          <div>
            <Switch>
              <Route path = "/" exact component = {HealthCareHomeComponent}></Route>
              <Route path = "/login" exact component = {LoginPageComponent}></Route>
              <Route path = "/users" component = {UserListComponent}></Route>
              <Route path = "/add-user" component = {AddUserComponent}></Route>
              <Route path = "/update-user/:id" component = {UpdateUserComponent}></Route>
              <Route path = "/patients" component = {PatientListComponent}></Route>
              <Route path = "/add-patient" component = {AddPatientComponent}></Route>
              <Route path = "/update-patient/:id" component = {UpdatePatientComponent}></Route>
              <Route path = "/vitalsign" component = {VSListComponent}></Route>
              <Route path = "/add-vitalsign" component = {AddVSComponent}></Route>
              <Route path = "/view/:pid/:cdate" component = {ViewVSComponent}></Route>
              <Route path = "/update-vitalsign/:pid/:cdate" component = {UpdateVSComponent}></Route>
              <Route path = "/audit" component = {HealthcareAuditTrail}></Route>
            </Switch>
          </div>
      </Router>
    </div>
  )
}

export default App;
