import axios from "axios";
import authHeader from "./AuthHeader";

const API_URL = "http://localhost:9090/api/auth/";

class AuthService {
    login(username, password) {
        return axios
            .post(API_URL + "login", { username, password })
            .then((response) => {
                if (response.data.token) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                return response.data;
            });
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(firstName, lastName, username, email, password, repeatedPassword) {
        return axios.post(API_URL + "signup", {
            firstName,
            lastName,
            username,
            email,
            password,
            repeatedPassword
        });
    }

    whoAmI(){
        return axios.get(API_URL+'whoami', {headers:authHeader()});
    }
}

export default new AuthService();
