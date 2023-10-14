import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/api";
const SERVER = "http://localhost:8080";

export const endpoints = {
    "admissions": `${SERVER_CONTEXT}/mist/admissions/`,
    "faculties": `${SERVER_CONTEXT}/mist/faculties/`,

    
    "login": `${SERVER_CONTEXT}/users/login/`,
    "current-user": `${SERVER_CONTEXT}/users/current-user/`, 
    "users": `${SERVER_CONTEXT}/users/`, 
    
    "top-posts": `${SERVER_CONTEXT}/posts/top-5/`,
    "post-detail": (postId) => `${SERVER_CONTEXT}/posts/${postId}`,
    "new-post": `${SERVER_CONTEXT}/posts/create/`,
    "update-post": (postId) => `${SERVER_CONTEXT}/posts/update/${postId}`,
    "delete-post": (postId) => `${SERVER_CONTEXT}/posts/delete/${postId}`,
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