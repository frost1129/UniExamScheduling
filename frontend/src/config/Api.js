import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/api";
const SERVER = "http://localhost:8080";

export const endpoints = {
    "info": `${SERVER_CONTEXT}/mist/admissions/`,

    
    "login": `${SERVER_CONTEXT}/users/login/`,
    "current-user": `${SERVER_CONTEXT}/users/current-user/`, 
    
    "top-posts": `${SERVER_CONTEXT}/posts/top-5/`,
    "post-detail": (postId) => `${SERVER_CONTEXT}/posts/${postId}`,
}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER, 
        headers: {
            "Authorization": "Bearer " + cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
});