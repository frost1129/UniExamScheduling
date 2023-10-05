import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/scheduling";
const SERVER = "http://localhost:8080";

export const endpoints = {
    "info": `${SERVER_CONTEXT}/api/info/`,
}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER, 
        headers: {
            "Authorization": cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
});