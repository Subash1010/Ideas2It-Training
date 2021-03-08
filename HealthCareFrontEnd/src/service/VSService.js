import axios from "axios";

const USER_BASE_URL = "http://localhost:8082/vitalsign";

class VSService {

    getAllCheckUpDetails() {
        const CURRENT_URL = USER_BASE_URL + "/";
        return axios.get(CURRENT_URL);
    }

    getcheckupDetails(pId, date) {
        const CURRENT_URL = USER_BASE_URL + "/" + pId + "/" + date;
        return axios.get(CURRENT_URL);
    }

    addCheckUpDetails(users) {
        const CURRENT_URL = USER_BASE_URL + "/"
        return axios.post(CURRENT_URL, users);
    }

    updateUser(pId, date, users) {
        const CURRENT_URL = USER_BASE_URL + "/" + pId + "/" + date + "/";
        return axios.put(CURRENT_URL, users);
    }

    deleteUser(pId, date) {
        const CURRENT_URL = USER_BASE_URL + "/" + pId + "/" + date;
        return axios.delete(CURRENT_URL);
    }
}

export default new VSService()