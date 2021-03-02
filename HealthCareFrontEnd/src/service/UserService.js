import axios from "axios";

const USER_BASE_URL = "http://localhost:8080/users";

const BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyMDAxIiwiZXhwIjoxNjE0NzIyMzk5LCJpYXQiOjE2MTQ2ODYzOTl9.E6k0_3TakqSAiJxcZDStAZve90Ue55qmI8Fq87oq-1A";

class UserService {

    getAllUsers() {
        const CURRENT_URL = USER_BASE_URL + "/"
        return axios.get(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    addUser(users) {
        const CURRENT_URL = USER_BASE_URL + "/"
        return axios.post(CURRENT_URL, users, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    getUserById(id) {
        const CURRENT_URL = USER_BASE_URL + "/" + id;
        return axios.get(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    updateUser(users) {
        const CURRENT_URL = USER_BASE_URL + "/"
        return axios.put(CURRENT_URL, users, {
            headers: { 
                "Authorization": BEARER_TOKEN
             }
        });
    }

    deleteUser(id) {
        const CURRENT_URL = USER_BASE_URL + "/" + id;
        return axios.delete(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }
}

export default new UserService()