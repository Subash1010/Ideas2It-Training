import axios from "axios";

const USER_BASE_URL = "http://localhost:8080/authenticate/authenticate";

var JWT = "";


class LoginService {

    getAuthorization(userName, password) {
        const userAuthenticateRequest = {
            userName: userName,
            password: password
        }
        return axios.post(USER_BASE_URL, userAuthenticateRequest);
    }

    setJWT(jwt){
        JWT = jwt;
    }

    getJWT(){
       return JWT;
    }
}

export default new LoginService()