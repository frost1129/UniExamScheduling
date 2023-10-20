import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/api";
const SERVER = "http://localhost:8080";

export const endpoints = {
    // MIST
    "admissions": `${SERVER_CONTEXT}/mist/admissions/`,
    "admission-detail": (typeId) => `${SERVER_CONTEXT}/mist/admissions/${typeId}`,
    "faculties": `${SERVER_CONTEXT}/mist/faculties/`,
    "facultie-detail": (facultyId) => `${SERVER_CONTEXT}/mist/faculties/${facultyId}`,
    "slots":`${SERVER_CONTEXT}/mist/slots/`,
    "slot-detail": (slotId) => `${SERVER_CONTEXT}/mist/slots/${slotId}`,

    // COURSE SCHEDULES
    "upload-schedules": `${SERVER_CONTEXT}/schedules/upload/`,
    "schedule-years": `${SERVER_CONTEXT}/schedules/getYearCodes/`,
    "user-schedules": (yearCode) => `${SERVER_CONTEXT}/schedules/getByUserAndYear/${yearCode}`,
    "faculty-schedules": `${SERVER_CONTEXT}/schedules/getByFacultyAndYear/`,
    
    // SCHEDULED EXAMS
    "exam-years": `${SERVER_CONTEXT}/exams/getYearCodes/`,
    "year-exams": (yearCode) => `${SERVER_CONTEXT}/exams/getByYear/${yearCode}`,
    "faculty-exams": `${SERVER_CONTEXT}/exams/getByFacultyAndYear/`,
    
    // STUDENT JOIN COURSES
    "upload-registers": `${SERVER_CONTEXT}/course-register/upload/`,
    
    // USER & ACCOUNT
    "login": `${SERVER_CONTEXT}/users/login/`,
    "current-user": `${SERVER_CONTEXT}/users/current-user/`, 
    "users": `${SERVER_CONTEXT}/users/`, 
    "new-user": `${SERVER_CONTEXT}/users/new/`, 
    
    // POST
    "all-posts": `${SERVER_CONTEXT}/posts/`,
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