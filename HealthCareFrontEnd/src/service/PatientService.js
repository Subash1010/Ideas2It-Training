import axios from "axios";

const PATIENT_BASE_URL = "http://localhost:8080/patients";

const PATIENT_SEARCH_BASE_URL = "http://localhost:8080/patientsearch";

const BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyMDAxIiwiZXhwIjoxNjE0OTgwNTQyLCJpYXQiOjE2MTQ5NDQ1NDJ9.WpfWgALE7RhtLluZ5BUia6uzPSlDagatUVF3SwB-QHk";

class PatientService {

    getAllPatients() {
        const CURRENT_URL = PATIENT_BASE_URL + "/"
        return axios.get(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    addPatient(patient) {
        const CURRENT_URL = PATIENT_BASE_URL + "/"
        return axios.post(CURRENT_URL, patient, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    getPatientById(id) {
        const CURRENT_URL = PATIENT_BASE_URL + "/" + id;
        return axios.get(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    updatePatient(patient) {
        const CURRENT_URL = PATIENT_BASE_URL + "/"
        return axios.put(CURRENT_URL, patient, {
            headers: { 
                "Authorization": BEARER_TOKEN
             }
        });
    }

    deletePatient(id) {
        const CURRENT_URL = PATIENT_BASE_URL + "/" + id;
        return axios.delete(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    getPatientByName(searchInput) {
        const CURRENT_URL = PATIENT_SEARCH_BASE_URL + "/search";
        const params = {
            searchData: searchInput
          }
        return axios.get(CURRENT_URL, {
            params: params,
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }
}

export default new PatientService()